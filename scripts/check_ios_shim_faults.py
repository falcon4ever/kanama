#!/usr/bin/env python3
"""Gate: no guarded early return in the iOS C shim returns silently (task 124).

The bridge's default failure mode was silence. `ios/bootstrap/kanama_ios_shim.c` is the whole
iOS surface -- every Godot call a Kotlin/Native game makes goes through one of its exported
entry points -- and each of those entry points opens with a stack of guards that `return` when
the engine API did not resolve, the method bind is zero, the instance is zero, a C-string
parameter is NULL, or nothing is pending. None of them said anything. That is how every Godot
static method reached through the shared wrapper tree stayed a no-op on iOS for six days (task
117 P2', 119 item 35): the call returned the default, the self-test's 205 checks passed, two
demo smokes passed, and the phone showed a still screen with a clean console.

Task 124 gave the shim one fault sink (`kanama_ios_fault`) and made every guarded early return
report to it before returning exactly what it returned before. This gate is the standing proof
that the property holds for the NEXT guard somebody adds, not just for the ones instrumented
that day.

The rule, one line: every `if (...)` whose body returns, inside an exported `kanama_ios_*`
entry point or a `static ..._dispatch` body, must call `kanama_ios_fault(` in that same block --
unless the pair is one of the two documented benign returns below.

Scope is derived, never hand-listed:

  * exported entry points: a top-level function in the shim whose name starts with
    `kanama_ios_` and whose definition is not `static`. These are the symbols in
    `ios/include/kanama_ios.h` that Kotlin/Native reaches through cinterop.
  * `_dispatch` bodies: the `static` halves of the `entry / _dispatch / _static` splits
    (commit 30c949a1). They carry the `!resolve || method_bind == 0` guards for BOTH the
    instance and the static entry point, so leaving them out would exempt most of the shim.

A guarded early return is an `if` whose body contains a `return` at the body's own brace depth.
A `return` nested deeper (inside a loop, a switch, or a second `if`) belongs to that inner
construct and is ruled by its own guard, not this one.

BENIGN holds the returns that are CORRECT to make silently, each with the reason. It can only
shrink: a listed pair that no longer exists in the shim fails the gate, so the list cannot rot
into an exemption nobody re-reads.

Failures, one line each:
  * silent-return -- a guarded early return whose block never calls `kanama_ios_fault(`;
  * stale-benign  -- a BENIGN entry that matches nothing in the shim any more;
  * parse-error   -- the shim did not parse. Never silenced: a file this gate cannot read is a
                     file it is not guarding.

Usage:
    python3 scripts/check_ios_shim_faults.py
    python3 scripts/check_ios_shim_faults.py --shim ios/bootstrap/kanama_ios_shim.c
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SHIM = ROOT / "ios/bootstrap/kanama_ios_shim.c"
TAG = "[check-ios-shim-faults]"

# (function, guard condition as written, why it is correct to return silently).
# Both are documented in the function's own comment in the shim; see the task 124 contract table.
BENIGN = [
    (
        "kanama_ios_godot_is_instance_id_valid",
        "instance_id == 0",
        "a zero instance id is a legitimate question whose honest answer is 'invalid'",
    ),
    (
        "kanama_ios_godot_ptrcall_ret_callable_dispatch",
        "target == NULL",
        "an empty (object-less) Callable is a legitimate value; desktop readCallable returns null too",
    ),
]


def strip_code(text: str) -> str:
    """Blank out comments and string/char literals, preserving every offset and newline."""
    out: list[str] = []
    i, n = 0, len(text)
    while i < n:
        c = text[i]
        if c == "/" and i + 1 < n and text[i + 1] == "/":
            while i < n and text[i] != "\n":
                out.append(" ")
                i += 1
            continue
        if c == "/" and i + 1 < n and text[i + 1] == "*":
            out.append("  ")
            i += 2
            while i + 1 < n and not (text[i] == "*" and text[i + 1] == "/"):
                out.append("\n" if text[i] == "\n" else " ")
                i += 1
            out.append("  ")
            i += 2
            continue
        if c in "\"'":
            quote = c
            out.append(" ")
            i += 1
            while i < n:
                if text[i] == "\\":
                    out.append("  ")
                    i += 2
                    continue
                if text[i] == quote:
                    out.append(" ")
                    i += 1
                    break
                out.append("\n" if text[i] == "\n" else " ")
                i += 1
            continue
        out.append(c)
        i += 1
    return "".join(out)


def top_level_functions(code: str):
    """Yield (name, is_static, body_open_offset, body_close_offset) for every definition."""
    depth = 0
    sig_start = 0
    cur = None
    i, n = 0, len(code)
    while i < n:
        c = code[i]
        if c == "{":
            if depth == 0:
                sig = code[sig_start:i]
                names = re.findall(r"([A-Za-z_][A-Za-z_0-9]*)\s*\(", sig)
                # Drop preprocessor lines (and the blanks left by comments) before reading the
                # storage class: `#if ...` between two definitions is not part of the signature.
                decl = "\n".join(
                    ln for ln in sig.splitlines() if not ln.lstrip().startswith("#")
                ).strip()
                cur = (names[-1] if names else "?", decl.startswith("static"), i)
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0 and cur is not None:
                yield (cur[0], cur[1], cur[2], i)
                cur = None
                sig_start = i + 1
        elif depth == 0 and c == ";":
            sig_start = i + 1
        i += 1


def match_paren(code: str, i: int) -> int:
    depth = 0
    while i < len(code):
        if code[i] == "(":
            depth += 1
        elif code[i] == ")":
            depth -= 1
            if depth == 0:
                return i + 1
        i += 1
    raise ValueError("unbalanced parentheses")


def match_brace(code: str, i: int) -> int:
    depth = 0
    while i < len(code):
        if code[i] == "{":
            depth += 1
        elif code[i] == "}":
            depth -= 1
            if depth == 0:
                return i + 1
        i += 1
    raise ValueError("unbalanced braces")


def in_scope(name: str, is_static: bool) -> bool:
    if name.endswith("_dispatch"):
        return True
    return name.startswith("kanama_ios_") and not is_static


def guarded_returns(src: str):
    """Yield one dict per guarded early return in an in-scope function."""
    code = strip_code(src)
    for name, is_static, body_open, body_close in top_level_functions(code):
        if not in_scope(name, is_static):
            continue
        for m in re.finditer(r"\bif\s*(?=\()", code[body_open:body_close]):
            if_off = body_open + m.start()
            cond_open = code.index("(", if_off)
            cond_close = match_paren(code, cond_open)
            j = cond_close
            while j < body_close and code[j] in " \t\n":
                j += 1
            if code[j] == "{":
                end = match_brace(code, j)
                block = code[j:end]
                depth = 0
                direct = False
                for token in re.finditer(r"[{}]|\breturn\b", block):
                    text = token.group(0)
                    if text == "{":
                        depth += 1
                    elif text == "}":
                        depth -= 1
                    elif depth == 1:
                        direct = True
                        break
                if not direct:
                    continue
            else:
                end = code.index(";", j) + 1
                if not re.match(r"\s*return\b", code[j:end]):
                    continue
            yield {
                "func": name,
                "static": is_static,
                "line": src.count("\n", 0, if_off) + 1,
                "cond": re.sub(r"\s+", " ", src[cond_open + 1 : cond_close - 1]).strip(),
                "block": src[j:end],
            }


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--shim", type=Path, default=SHIM)
    args = parser.parse_args()

    try:
        src = args.shim.read_text(encoding="utf-8")
        guards = list(guarded_returns(src))
    except Exception as exc:  # noqa: BLE001 - a shim this gate cannot read is a shim it is not guarding
        print(f"{TAG} FAIL parse-error {args.shim}: {exc}", file=sys.stderr)
        return 1

    benign_index = {(func, cond): why for func, cond, why in BENIGN}
    benign_hits: set[tuple[str, str]] = set()
    findings: list[str] = []
    reporting = 0

    for guard in guards:
        # A guard may be one term of a condition the contract table rules term by term; a benign
        # term is matched by its own spelling wherever it survived the split.
        key = None
        for func, cond in benign_index:
            if guard["func"] == func and cond in guard["cond"]:
                key = (func, cond)
                break
        if key is not None:
            benign_hits.add(key)
            continue
        if "kanama_ios_fault(" in guard["block"]:
            reporting += 1
            continue
        findings.append(
            f"silent-return {args.shim.name}:{guard['line']} {guard['func']}: "
            f"if ({guard['cond']}) returns without calling kanama_ios_fault("
        )

    for func, cond in benign_index:
        if (func, cond) not in benign_hits:
            findings.append(
                f"stale-benign {func}: `if ({cond})` is no longer in the shim — "
                "delete the BENIGN entry in scripts/check_ios_shim_faults.py"
            )

    code = strip_code(src)
    entry_points = sorted(
        {n for n, st, _, _ in top_level_functions(code) if n.startswith("kanama_ios_") and not st}
    )
    dispatch_bodies = sorted({n for n, _, _, _ in top_level_functions(code) if n.endswith("_dispatch")})
    guarded_funcs = sorted({g["func"] for g in guards})

    if findings:
        for finding in findings:
            print(f"{TAG} FAIL {finding}", file=sys.stderr)
        print(
            f"{TAG} FAIL {len(findings)} finding(s): the iOS bridge can still fail quietly",
            file=sys.stderr,
        )
        print(
            "  Every guarded early return in an exported entry point or a `_dispatch` body must\n"
            "  call kanama_ios_fault(__func__, \"<reason>\", <detail or NULL>) before returning what\n"
            "  it returns today. Return values and signatures do not change. The reason tokens are\n"
            "  fixed (api-unresolved, null-bind, bind-lookup-failed, null-instance, null-handle,\n"
            "  null-arg, pending-protocol, callable-build, unknown-tag, encode-failed) and are\n"
            "  documented in docs/contributing/backends/ios.md. If the return is genuinely correct\n"
            "  to make in silence, say why in the function's own comment and add the pair to BENIGN\n"
            "  in this script — that list is read by reviewers, so it must earn each line.",
            file=sys.stderr,
        )
        return 1

    print(
        f"{TAG} PASS {len(entry_points)} exported entry point(s) and {len(dispatch_bodies)} "
        f"`_dispatch` body(ies) in {args.shim.name}; {len(guards)} guarded early return(s) across "
        f"{len(guarded_funcs)} function(s), {reporting} reporting, {len(benign_hits)} documented benign"
    )
    for func, cond, why in BENIGN:
        print(f"  benign  {func}: if ({cond}) — {why}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
