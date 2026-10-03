#!/usr/bin/env python3
"""The JDK install-location table exists twice and the two copies must be equal (kanama#277).

`bootstrap/bootstrap.c` (`k_jdk_locations`, what the runtime scans for libjvm when neither a bundled
runtime, the plugin's recorded `kanama/build/jdk_path`, nor JAVA_HOME gives one) and the editor
plugin (`JDK_LOCATIONS` in both copies of `plugin.gd`, which the startup preflight and Build Scripts
use) must list the same [os, parent, suffix, prefix] rows in the same order, or the preflight says OK
where the runtime says "libjvm not found" (or the other way round). Each table sits between
`KANAMA_JDK_LOCATIONS_BEGIN` and `KANAMA_JDK_LOCATIONS_END`. The behaviour on a real layout is held
by `scripts/check_jdk_lookup_parity.sh`; this gate holds the data.

Usage: python3 scripts/check_jdk_locations_parity.py
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[check_jdk_locations_parity]"
SOURCES = (
    ROOT / "bootstrap/bootstrap.c",
    ROOT / "templates/starter/addons/kanama_tools/plugin.gd",
    ROOT / "example_project/addons/kanama_tools/plugin.gd",
)
STRING_RE = re.compile(r'"([^"]*)"')


# One row, in either language: {"a", "b", "c", "d"},  (C)  or  ["a", "b", "c", "d"],  (GDScript).
ROW_RE = re.compile(r'^\s*[\{\[]\s*"[^"]*"\s*(?:,\s*"[^"]*"\s*){3}[\}\]]\s*,?\s*$')
COMMENT_PREFIXES = ("//", "/*", "*", "#")


def table(path: Path) -> list[tuple[str, ...]]:
    text = path.read_text(encoding="utf-8")
    begin = text.find("KANAMA_JDK_LOCATIONS_BEGIN")
    end = text.find("KANAMA_JDK_LOCATIONS_END")
    if begin < 0 or end < begin:
        raise SystemExit(f"{TAG} FAIL {path.relative_to(ROOT)}: no KANAMA_JDK_LOCATIONS_BEGIN/END markers")
    rows = []
    # Skip the marker line itself (it carries prose in parentheses, not a row). A commented-out row is
    # not a row: it drops out of the table, so a row disabled on one side only is a difference.
    for line in text[begin:end].splitlines()[1:]:
        stripped = line.strip()
        if not stripped or stripped.startswith(COMMENT_PREFIXES):
            continue
        if stripped in ("static const JdkLocation k_jdk_locations[] = {", "const JDK_LOCATIONS := [", "};", "]"):
            continue
        if not ROW_RE.match(line):
            raise SystemExit(f"{TAG} FAIL {path.relative_to(ROOT)}: unrecognised line in the table: {stripped}")
        rows.append(tuple(STRING_RE.findall(line)))
    if not rows:
        raise SystemExit(f"{TAG} FAIL {path.relative_to(ROOT)}: empty table")
    return rows


def main() -> int:
    tables = {path: table(path) for path in SOURCES}
    reference_path = SOURCES[0]
    reference = tables[reference_path]
    failed = False
    for path, rows in tables.items():
        if rows != reference:
            failed = True
            print(f"{TAG} FAIL {path.relative_to(ROOT)} differs from {reference_path.relative_to(ROOT)}", file=sys.stderr)
            for index in range(max(len(rows), len(reference))):
                left = reference[index] if index < len(reference) else None
                right = rows[index] if index < len(rows) else None
                if left != right:
                    print(f"{TAG}   row {index}: {left} != {right}", file=sys.stderr)
    if failed:
        return 1
    print(f"{TAG} PASS {len(reference)} location rows identical in {len(SOURCES)} files")
    return 0


if __name__ == "__main__":
    sys.exit(main())
