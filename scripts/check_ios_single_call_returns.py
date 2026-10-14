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

This gate keeps it that way, statically, on the host. In every function of every Kotlin file under
`src/iosMain/`:

  * an entry point that runs a bound method (`kanama_ios_*ptrcall*` / `*_call*` / `*_ret_*`) or a
    private `*Dispatch` wrapper of one is named at most ONCE
    (the pending-slot drains `kanama_ios_godot_take_pending_*` are the second half of a single
    call and are exempt);
  * none is called inside a loop (`while` / `for` / `do` / `repeat` / `forEach`), the other way to
    re-run the method (the old PackedByteArray read-back grew its buffer and called again).

A function that must call an entry point twice on purpose goes in ALLOWED with its reason.
Usage:

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

# Function name -> why it may name one entry point twice (none today).
ALLOWED: dict[str, str] = {}

# The entry points that run a Godot method: the ptrcall / call / `_ret_` read-back families and the
# private `*Dispatch` wrappers ObjectCalls.kt routes them through (fault-sink and object-creation
# entries do not run a bound method and are out of scope).
ENTRY_RE = re.compile(r"\b(kanama_ios_\w*(?:ptrcall|_call|_ret_)\w*|\w+Dispatch)\s*\(")
DRAIN_PREFIX = "kanama_ios_godot_take_pending_"
FUN_RE = re.compile(r"^(?P<indent>\s*)(?:[a-z]+\s+)*fun\s+(?:<[^>]+>\s+)?(?:[\w.]+\.)?(?P<name>\w+)\s*\(")
LOOP_RE = re.compile(r"\b(?:while|for|do)\b\s*[({]?|\.(?:repeat|forEach|forEachIndexed)\s*\{|\brepeat\s*\(")


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


def loop_spans(lines: list[str], first: int, last: int) -> list[tuple[int, int]]:
    """Line spans (inclusive) of the loop bodies opened between [first] and [last]."""
    spans = []
    for i in range(first, last + 1):
        if not LOOP_RE.search(lines[i]):
            continue
        depth = 0
        opened = False
        j = i
        while j <= last:
            depth += lines[j].count("{") - lines[j].count("}")
            opened = opened or "{" in lines[j]
            if opened and depth <= 0:
                break
            j += 1
        if opened:
            spans.append((i, j))
    return spans


def scan(root: Path) -> list[str]:
    findings: list[str] = []
    files = sorted(root.rglob("*.kt"))
    if not files:
        return [f"no Kotlin files under {root}: the gate would be vacuous"]
    for path in files:
        lines = strip_code(path.read_text(encoding="utf-8")).split("\n")
        try:
            where = path.relative_to(ROOT)
        except ValueError:  # justified: only the path printed in a finding; no verdict reads it
            where = path
        for name, first, last in functions(lines):
            if name in ALLOWED:
                continue
            calls = Counter()
            for i in range(first, last + 1):
                for m in ENTRY_RE.finditer(lines[i]):
                    callee = m.group(1)
                    if callee.startswith(DRAIN_PREFIX):
                        continue
                    if i == first and callee == name:
                        continue  # the declaration itself
                    calls[callee] += 1
            for callee, count in sorted(calls.items()):
                if count > 1:
                    findings.append(
                        f"double-call {where}:{first + 1} {name}() names {callee} {count} times: the "
                        "engine method runs once per name; read the return in one call (a pending slot "
                        "drains a long value)"
                    )
            for loop_first, loop_last in loop_spans(lines, first, last):
                for i in range(loop_first, loop_last + 1):
                    for m in ENTRY_RE.finditer(lines[i]):
                        callee = m.group(1)
                        if callee.startswith(DRAIN_PREFIX) or (i == first and callee == name):
                            continue
                        findings.append(
                            f"loop-call {where}:{i + 1} {name}() calls {callee} inside a loop: the "
                            "engine method may run more than once"
                        )
    return sorted(set(findings))


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n", 1)[0])
    parser.add_argument("--root", type=Path, default=DEFAULT_SCAN)
    args = parser.parse_args()
    findings = scan(args.root)
    if findings:
        print(f"{TAG} FAIL {len(findings)} iOS read-back(s) can run a Godot method more than once:", file=sys.stderr)
        for finding in findings:
            print(f"  {finding}", file=sys.stderr)
        return 1
    print(f"{TAG} PASS every iOS read-back runs its Godot method once: no entry point named twice in a function or called in a loop")
    return 0


if __name__ == "__main__":
    sys.exit(main())
