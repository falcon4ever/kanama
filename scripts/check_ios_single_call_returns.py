#!/usr/bin/env python3
"""Gate: every iOS Kotlin function runs a Godot method at most once per call.

The iOS runtime reads a String / Packed*Array / Array return back through a C entry point of
`ios/bootstrap/kanama_ios_shim.c`. Until task 129 D the no-arg helpers used a two-call length
protocol: call with a NULL buffer to measure, allocate, call again to fill. Each Kotlin call ran
the engine method TWICE, which is invisible for a pure getter and wrong for any method with a side
effect: `FileAccess.get_line()` and `DirAccess.get_next()` consumed a line / an entry on the
measuring call and returned "" on the iPhone, while desktop (one ptrcall) was right. Every return
shape now runs the method once and drains a value too long for the inline buffer from a C pending
slot (`kanama_ios_godot_take_pending_*`).

This gate keeps it that way, statically, on the host, over every Kotlin file under `src/iosMain/`.

A function is METHOD-RUNNING when it runs a Godot method: it names a C entry point
(`kanama_ios_*ptrcall*` / `*_call*` / `*_ret_*`), a private `*Dispatch` wrapper of one, or any
other method-running function. The set is computed TRANSITIVELY over the whole tree (by function
name; overloads are merged), so a higher-level helper such as `ptrcallRetUtf8` counts like the
entry point under it. The pending-slot drains `kanama_ios_godot_take_pending_*` are the second half
of a single call and are exempt. In every function:

  * method-running functions are named at most ONCE IN TOTAL (the same helper twice, or two
    different helpers, are two engine calls);
  * none is called where it can run more than once: inside `while` / `for` / `do` (braced or
    brace-less), or inside the body of a lambda handed to anything but a known run-once scope
    (`memScoped`, `run`, `let`, `use`, `lazy`, ...), which covers `List(n) { .. }`,
    `(0 until n).map { .. }`, `mapIndexed`, `forEach`, `repeat`, and a lambda kept in a `val`;
  * none is aliased with a callable reference (`val call = ::xDispatch`), which hides the count.

A function that must run two different binds on purpose goes in ALLOWED_MULTI (or, to run in a loop or
lambda, ALLOWED_LOOPS) with its reason; the gate prints every allowlisted function it used, and
fails on an entry that no longer excuses anything. Usage:

    python3 scripts/check_ios_single_call_returns.py            # gate
    python3 scripts/check_ios_single_call_returns.py --root DIR # scan another tree (red runs)
"""

from __future__ import annotations

import argparse
import re
import sys
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
DEFAULT_SCAN = ROOT / "src" / "iosMain"
TAG = "[ios_single_call]"

# Function ("name" or "File.kt:name") -> why it may run more than one engine call: exempt from the
# at-most-once-in-total count ONLY. Printed on every run; a stale entry fails the gate.
ALLOWED_MULTI: dict[str, str] = {
    "ptrcallWithObjectStringLongArgsRetLong": (
        "ResourceSaver.save reference guard (issue #81): reference() before and get_reference_count() / "
        "unreference() after the one save call, three different binds"
    ),
    "scriptInstanceRefcountIncremented": (
        "script refcount callback: get_reference_count() decides the path, get_path() is read only on the "
        "re-referenced path; two different binds, each once"
    ),
    "ownerLinkKeepOnlyScriptObject": (
        "self-test fixture (task 132): creates the script instance, then drops its refcount; two different calls"
    ),
    "variantRoundTrip": "self-test helper: StreamPeer.put_var then get_var, two different binds by design",
    "scriptAccessSelfTestRows": "self-test rows: create then ready a script instance, two different calls",
    "kanamaIosRuntimeObjectCallsSelfTest": "self-test: a sequence of independent rows, each its own call",
    "kanamaIosRuntimeObjectCallsSelfTestFrame": "self-test: a sequence of independent rows, each its own call",
}
# Function -> why it may run an engine call where it can repeat (a lambda / loop). Loop sites are
# otherwise forbidden. Printed on every run; a stale entry fails the gate.
ALLOWED_LOOPS: dict[str, str] = {
    "kanamaIosRuntimeObjectCallsSelfTest": "self-test: repeat(3) put_u8 / a fixed list of signal names, each iteration a distinct input row",
    "scriptInstanceRefcountIncremented": "onIncremented { referenceCount(..) }: the lambda runs once per engine callback",
    "scriptInstanceRefcountDecremented": "onDecremented { referenceCount(..) }: the lambda runs once per engine callback",
}

# The seeds: the C entry points that run a bound method (the ptrcall / call / `_ret_` read-back
# families) and the private `*Dispatch` wrappers ObjectCalls.kt routes them through (fault-sink
# and object-creation entries do not run a bound method and are out of scope).
SEED_RE = re.compile(r"^(?:kanama_ios_\w*(?:ptrcall|_call|_ret_)\w*|\w+Dispatch)$")
DRAIN_PREFIX = "kanama_ios_godot_take_pending_"
FUN_RE = re.compile(r"^(?P<indent>\s*)(?:[a-z]+\s+)*fun\s+(?:<[^>]+>\s+)?(?:[\w.]+\.)?(?P<name>\w+)\s*\(")
CALL_RE = re.compile(r"(?<![\w.])(?:[\w.]*\.)?(\w+)\s*\(|\.(\w+)\s*\(")
IDENT_CALL_RE = re.compile(r"(\w+)\s*\(")
REF_RE = re.compile(r"::\s*(\w+)")
LOOP_KW_RE = re.compile(r"\b(for|while)\b\s*\(")
DO_RE = re.compile(r"\bdo\s*\{")

# Lambda scopes that run their block exactly once (or hand it to the engine once): a method-running
# call inside them is not a loop. Anything else taking a trailing lambda is treated as an iterator.
RUN_ONCE_SCOPES = frozenset({
    "memScoped", "run", "let", "apply", "also", "with", "use", "lazy", "synchronized", "runCatching",
    "takeIf", "takeUnless", "autoreleasepool", "withContext", "getOrElse", "getOrNull", "onFailure",
    "onSuccess", "mapCatching", "recoverCatching", "ifEmpty", "ifBlank", "elvis",
})
# Names never added by propagation: lifecycle / stdlib-shaped names every wrapper class shares, so
# merging by name would make `x.close()` anywhere "run a Godot method" because one `close()` does.
# (A function with such a name that names a seed entry point directly is still checked itself.)
NOT_PROPAGATED = frozenset({"close", "release", "invoke"})
BLOCK_KEYWORDS = frozenset({"else", "try", "finally", "do", "init", "when", "if", "interface", "object"})
CONTROL_CALLS = frozenset({"if", "for", "while", "when", "catch", "switch", "constructor", "get", "set", "init"})


def strip_code(text: str) -> str:
    """[text] with comments and string/char literal contents blanked (same length per line), so
    braces and names inside them do not count."""
    out: list[str] = []
    i, n = 0, len(text)
    while i < n:
        c = text[i]
        if text.startswith("//", i):
            j = text.find("\n", i)
            j = n if j < 0 else j
            out.append(" " * (j - i))
            i = j
        elif text.startswith("/*", i):
            j = text.find("*/", i + 2)
            j = n if j < 0 else j + 2
            out.append("".join(ch if ch == "\n" else " " for ch in text[i:j]))
            i = j
        elif text.startswith('"""', i):
            j = text.find('"""', i + 3)
            j = n if j < 0 else j + 3
            out.append("".join(ch if ch == "\n" else " " for ch in text[i:j]))
            i = j
        elif c in "\"'":
            j = i + 1
            while j < n and text[j] != c and text[j] != "\n":
                j += 2 if text[j] == "\\" else 1
            j = min(j + 1, n)
            out.append(c + " " * (j - i - 2) + (c if j - i >= 2 else ""))
            i = j
        else:
            out.append(c)
            i += 1
    return "".join(out)


def functions(lines: list[str]) -> list[tuple[str, int, int]]:
    """(name, first line, last line) of every `fun` (members, top-level and local), 0-based. The
    body ends where the bracket depth is back to zero and the next code line is not indented
    deeper than the declaration (or continues it with `)` / `}` / `.` / an operator)."""
    found = []
    for start, line in enumerate(lines):
        m = FUN_RE.match(line)
        if not m:
            continue
        indent = len(m.group("indent"))
        depth = 0
        end = start
        k = start
        while k < len(lines):
            depth += sum(lines[k].count(ch) for ch in "({[") - sum(lines[k].count(ch) for ch in ")}]")
            end = k
            if depth <= 0:
                nxt = k + 1
                while nxt < len(lines) and not lines[nxt].strip():
                    nxt += 1
                if nxt >= len(lines):
                    break
                following = lines[nxt]
                follow_indent = len(following) - len(following.lstrip())
                if follow_indent <= indent and not re.match(r"\s*(?:[)}\].]|\?:|=|else\b)", following):
                    break
            k += 1
        found.append((m.group("name"), start, end))
    return found


def match(text: str, i: int) -> int:
    """Index just past the bracket that closes the one at [i] (len(text) when unbalanced)."""
    pairs = {"(": ")", "{": "}", "[": "]"}
    open_ch, close_ch = text[i], pairs[text[i]]
    depth = 0
    for j in range(i, len(text)):
        if text[j] == open_ch:
            depth += 1
        elif text[j] == close_ch:
            depth -= 1
            if depth == 0:
                return j + 1
    return len(text)


def statement_end(text: str, i: int) -> int:
    """End of the brace-less statement starting at [i]: the first newline at bracket depth 0."""
    depth = 0
    for j in range(i, len(text)):
        c = text[j]
        if c in "({[":
            depth += 1
        elif c in ")}]":
            depth -= 1
        elif c == "\n" and depth <= 0:
            return j
    return len(text)


def skip_ws(text: str, i: int) -> int:
    while i < len(text) and text[i].isspace():
        i += 1
    return i


def lambda_scope(body: str, brace: int) -> tuple[bool, str]:
    """(is a lambda body, scope name or "" if anonymous) for the `{` at [brace]. Blocks of `if`,
    `else`, `try`, `when`, `catch`, function / class / object bodies and `->` branches are not
    lambdas."""
    before = body[:brace].rstrip()
    if not before:
        return False, ""
    last = before[-1]
    if last in "};{":
        return False, ""
    if last == "=":
        return True, ""  # val call = { ... }: a lambda that can be invoked any number of times
    if last in "(,":
        return True, ""
    if last in ">?":
        return False, ""  # `-> {` branch, or a generic / nullable type before a declaration body
    if last == ")":
        depth = 0
        k = len(before) - 1
        while k >= 0:
            if before[k] == ")":
                depth += 1
            elif before[k] == "(":
                depth -= 1
                if depth == 0:
                    break
            k -= 1
        head = before[:k].rstrip()
        m = re.search(r"(\w+)$", head)
        if not m:
            return True, ""
        name = m.group(1)
        pre = head[: m.start()].rstrip()
        if name in CONTROL_CALLS or name in BLOCK_KEYWORDS:
            return False, ""
        if re.search(r"\b(?:fun|class)\s*(?:<[^>]*>\s*)?(?:[\w.]+\.)?$", pre + " "):
            return False, ""
        if pre.endswith(":") and not pre.endswith("?:"):
            return False, ""  # `class A(..) : Base(..) {`
        return True, name
    m = re.search(r"(\w+)$", before)
    if not m:
        return False, ""
    name = m.group(1)
    pre = before[: m.start()].rstrip()
    if name in BLOCK_KEYWORDS:
        return False, ""
    if pre.endswith(":") and not pre.endswith("?:"):
        return False, ""  # `fun f(): Int {` / `class A : Base {` / `object : T {`
    return True, name


def loop_regions(body: str) -> list[tuple[int, int, str]]:
    """(start, end, why) char spans of [body] where a call can run more than once."""
    regions: list[tuple[int, int, str]] = []
    for m in LOOP_KW_RE.finditer(body):
        open_p = m.end() - 1
        close_p = match(body, open_p)
        before = body[: m.start()].rstrip()
        if m.group(1) == "while" and before.endswith("}"):
            # `} while (cond)`: the tail of a do-while (the body is found by DO_RE); the condition
            # is evaluated every round
            regions.append((m.start(), close_p, "do-while condition"))
            continue
        j = skip_ws(body, close_p)
        if j < len(body) and body[j] == "{":
            end = match(body, j)
        else:
            end = statement_end(body, j)
        start = open_p if m.group(1) == "for" else m.start()  # a `for` iterable is evaluated once
        regions.append((start, end, m.group(1) + " loop"))
    for m in DO_RE.finditer(body):
        regions.append((m.start(), match(body, m.end() - 1), "do loop"))
    for i, ch in enumerate(body):
        if ch != "{":
            continue
        is_lambda, scope = lambda_scope(body, i)
        if is_lambda and scope not in RUN_ONCE_SCOPES:
            regions.append((i, match(body, i), f"lambda{' of ' + scope if scope else ''}"))
    return regions


def collect(root: Path):
    """[(path, name, first line, last line, body text, line offsets)] of every function."""
    out = []
    for path in sorted(root.rglob("*.kt")):
        lines = strip_code(path.read_text(encoding="utf-8")).split("\n")
        for name, first, last in functions(lines):
            decl = FUN_RE.match(lines[first])
            chunk = lines[first : last + 1]
            out.append((path, name, first, last, "\n".join(chunk), decl.start("name")))
    return out


def callee_names(body: str, decl_pos: int) -> list[tuple[str, int]]:
    """(name, offset) of every `name(` in [body] except the declaration's own name."""
    return [(m.group(1), m.start(1)) for m in IDENT_CALL_RE.finditer(body) if m.start(1) != decl_pos]


def method_running(funs) -> set[str]:
    """The names of the functions that run a Godot method, transitively."""
    called = {}
    for _path, name, _f, _l, body, decl_pos in funs:
        called.setdefault(name, set()).update(c for c, _ in callee_names(body, decl_pos))
    mentioned = set(called) | {c for cs in called.values() for c in cs}
    mentioned |= {m.group(1) for _p, _n, _f, _l, body, _d in funs for m in REF_RE.finditer(body)}
    running = {c for c in mentioned if SEED_RE.match(c) and not c.startswith(DRAIN_PREFIX)}
    running -= NOT_PROPAGATED
    changed = True
    while changed:
        changed = False
        for name, cs in called.items():
            if name not in running and name not in NOT_PROPAGATED and cs & running:
                running.add(name)
                changed = True
    return running


def allowed(table: dict[str, str], where_name: str, name: str):
    for key in (where_name, name):
        if key in table:
            return key, table[key]
    return None, None


def dispatch_twin(name: str, calls: list[tuple[str, int]]) -> bool:
    """A `*Dispatch` wrapper naming an entry point and its `_static` twin: it picks one by the
    instance == 0 test, so exactly one runs."""
    names = sorted({c for c, _ in calls})
    return name.endswith("Dispatch") and len(names) == 2 and names[0] + "_static" == names[1] and len(calls) == 2


def scan(root: Path, report: list[str] | None = None) -> list[str]:
    findings: list[str] = []
    funs = collect(root)
    if not funs:
        return [f"no Kotlin functions under {root}: the gate would be vacuous"]
    running = method_running(funs)
    used: set[str] = set()
    reported_loops: set[str] = set()
    twins: list[str] = []
    for path, name, first, _last, body, decl_pos in funs:
        try:
            where = path.relative_to(ROOT)
        except ValueError:  # justified: only the path printed in a finding; no verdict reads it
            where = path
        line_at = lambda pos: first + 1 + body.count("\n", 0, pos)  # noqa: E731
        calls = [(c, pos) for c, pos in callee_names(body, decl_pos) if c in running and c != name]
        for m in REF_RE.finditer(body):
            if m.group(1) in running:
                findings.append(
                    f"alias {where}:{line_at(m.start())} {name}() takes a callable reference to {m.group(1)}: "
                    "an alias hides how often the engine method runs; call it directly"
                )
        if len(calls) > 1:
            key, why = allowed(ALLOWED_MULTI, f"{path.name}:{name}", name)
            if key is None and dispatch_twin(name, calls):
                twins.append(name)
            elif key is None:
                counts = Counter(c for c, _ in calls)
                names = ", ".join(f"{c} x{n}" if n > 1 else c for c, n in sorted(counts.items()))
                findings.append(
                    f"double-call {where}:{first + 1} {name}() runs {len(calls)} engine calls ({names}): "
                    "read the return in one call (a pending slot drains a long value)"
                )
            else:
                used.add("M:" + key)
                if report is not None:
                    report.append(f"allowlisted {where}:{first + 1} {name}() runs {len(calls)} engine calls: {why}")
        if calls:
            regions = loop_regions(body)
            loop_key, loop_why = allowed(ALLOWED_LOOPS, f"{path.name}:{name}", name)
            for c, pos in calls:
                for start, end, why in regions:
                    if start <= pos < end:
                        if loop_key is not None:
                            used.add("L:" + loop_key)
                            if report is not None and loop_key not in reported_loops:
                                reported_loops.add(loop_key)
                                report.append(f"allowlisted {where}:{first + 1} {name}() calls in loops / lambdas: {loop_why}")
                            break
                        findings.append(
                            f"loop-call {where}:{line_at(pos)} {name}() calls {c} inside a {why}: "
                            "the engine method may run more than once"
                        )
                        break
    if twins and report is not None:
        report.append(
            f"{len(twins)} *Dispatch wrappers each name an entry point and its _static twin behind one "
            f"instance == 0 test, so one runs: {', '.join(sorted(twins))}"
        )
    for table, tag in ((ALLOWED_MULTI, "M:"), (ALLOWED_LOOPS, "L:")) if root == DEFAULT_SCAN else ():
        for key in sorted(k for k in table if tag + k not in used):
            findings.append(f"stale-allow {'ALLOWED_MULTI' if tag == 'M:' else 'ALLOWED_LOOPS'}[{key!r}] excuses nothing: remove it")
    return sorted(set(findings))


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n", 1)[0])
    parser.add_argument("--root", type=Path, default=DEFAULT_SCAN)
    args = parser.parse_args()
    report: list[str] = []
    findings = scan(args.root, report)
    for line in report:
        print(f"{TAG} {line}")
    if findings:
        print(f"{TAG} FAIL {len(findings)} iOS read-back(s) can run a Godot method more than once:", file=sys.stderr)
        for finding in findings:
            print(f"  {finding}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS every iOS function runs a Godot method at most once: no method-running function "
        f"named twice, looped, lambda-repeated or aliased ({len(report)} allowlist line(s) above)"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
