#!/usr/bin/env python3
"""Fail if the iOS backend has an UN-ANNOTATED silent stub.

A "silent stub" is a function/getter whose whole body is a bare default (returns
null/false/0/0L/0.0/""/emptyList()/emptyMap() or an empty {} body) — i.e. it looks like it
does something but no-ops. Those caused real deep-debug bugs (unwired methods/signals).
This check fails unless every such site is annotated with a KANAMA-IOS-STUB or
KANAMA-IOS-HANDWRITTEN marker within the 2 lines above it — so you cannot add a new silent
stub without recording it (and it shows up in scripts/ios_handwritten_report.py).

Scans ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/*.kt.
Run: python3 scripts/check_ios_no_silent_stubs.py   (exit 1 on un-annotated stub)
"""

from __future__ import annotations

import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
API_DIR = ROOT / "ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api"
MARKER = "KANAMA-IOS-"

DEFAULT = r"(?:null|false|true|0|0L|0\.0|0\.0f|\"\"|emptyList\(\)|emptyMap\(\)|emptySet\(\))"
# fun foo(...): T = <bare default>      (whole-body expression)
FUN_EXPR = re.compile(rf"^\s*(?:override\s+|open\s+|internal\s+|private\s+)*fun\s+\w+\s*\([^)]*\)\s*:\s*[\w<>?.]+\s*=\s*{DEFAULT}\s*(?://.*)?$")
# get() = <bare default>
GETTER = re.compile(rf"^\s*get\(\)\s*=\s*{DEFAULT}\s*(?://.*)?$")
# fun foo(...) { }  or  fun foo(...): T { }   (empty block on one line)
FUN_EMPTY = re.compile(r"^\s*(?:override\s+|open\s+|internal\s+|private\s+)*fun\s+\w+\s*\([^)]*\)\s*(?::\s*[\w<>?.]+\s*)?\{\s*\}\s*$")
# val/property whose default value is bare (const handled by exclusion below)
VAL_DEFAULT = re.compile(rf"^\s*(?:override\s+)?val\s+\w+\s*:\s*[\w<>?.]+\s*=\s*{DEFAULT}\s*(?://.*)?$")


def covered_by_marker(lines: list[str], idx: int) -> bool:
    # marker on the property line itself, or within the 2 comment lines above
    for j in range(idx, max(-1, idx - 3), -1):
        if MARKER in lines[j]:
            return True
    return False


def main() -> int:
    offenders: list[str] = []
    marked = 0
    for path in sorted(API_DIR.glob("*.kt")):
        lines = path.read_text(encoding="utf-8").splitlines()
        for idx, line in enumerate(lines):
            if "const " in line:  # enum/const values are not stubs
                continue
            if not (FUN_EXPR.match(line) or GETTER.match(line) or FUN_EMPTY.match(line) or VAL_DEFAULT.match(line)):
                continue
            if covered_by_marker(lines, idx):
                marked += 1
            else:
                rel = path.relative_to(ROOT).as_posix()
                offenders.append(f"{rel}:{idx + 1}: {line.strip()}")

    if offenders:
        print("[check_ios_no_silent_stubs] FAIL — un-annotated silent stub(s):")
        for o in offenders:
            print(f"  {o}")
        print("\nAdd a `// KANAMA-IOS-STUB: ...` or `// KANAMA-IOS-HANDWRITTEN: ...` marker "
              "above each, then re-run scripts/ios_handwritten_report.py.")
        return 1

    print(f"[check_ios_no_silent_stubs] PASS — {marked} bare-default site(s), all annotated.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
