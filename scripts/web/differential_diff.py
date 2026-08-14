#!/usr/bin/env python3
"""Diff a demo's hydrated state between desktop and Web (task 80 slice 6).

Task 64 made one Kotlin source compile for both platforms, which makes this possible:
the same `differential_probe()` runs on desktop and on Web, so its output must be
byte-identical. A difference is by definition a bug — one of the two hydration paths
delivered a different value for the same declaration.

This is the only technique in task 80 that catches the silent-wrong-VALUE class.
Everything else — the degradation manifest, the census, the coverage report — proves a
member *dispatched*, not that it carried the right value. cc's tilt limits and fps's
`max_distance` dispatched perfectly on both platforms and simply held different numbers.

WHAT IT DELIBERATELY DOES NOT COMPARE: object identity. Handle numbers legitimately
differ between platforms, so the probe reports object fields as presence (0/1) rather
than value. Comparing identity would report a difference that is not a defect, and a
check that cries wolf gets ignored.

Inputs:
  --web      a smoke result envelope carrying `differential`
  --desktop  a desktop headless smoke log containing a `KANAMA-DIFF <script> <payload>` line

Usage:
    python3 scripts/web/differential_diff.py --web result.json --desktop desktop.log
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

DESKTOP_LINE = re.compile(r"KANAMA-DIFF\s+(?P<script>\S+)\s+(?P<payload>.+?)\s*$")


def _parse_payload(payload: str) -> dict[str, str]:
    fields: dict[str, str] = {}
    for entry in payload.split(";"):
        entry = entry.strip()
        if not entry:
            continue
        key, _, value = entry.partition("=")
        fields[key.strip()] = value.strip()
    return fields


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--web", type=Path, required=True)
    parser.add_argument("--desktop", type=Path, required=True)
    args = parser.parse_args()

    if not args.web.is_file():
        print(f"differential_diff: FAIL — web envelope not found: {args.web}", file=sys.stderr)
        return 2
    if not args.desktop.is_file():
        print(f"differential_diff: FAIL — desktop log not found: {args.desktop}", file=sys.stderr)
        return 2

    envelope = json.loads(args.web.read_text())
    block = envelope.get("differential")
    if not block:
        print(
            f"differential_diff: FAIL — {args.web} carries no `differential` section; the "
            "driver did not run the probe",
            file=sys.stderr,
        )
        return 2
    if not block.get("available"):
        print(
            f"differential_diff: FAIL — the Web run reported the probe unavailable: "
            f"{block.get('reason', 'no reason given')}",
            file=sys.stderr,
        )
        return 2

    web_script = block.get("script")
    web_payload = block.get("payload")
    if not web_payload:
        print(
            "differential_diff: FAIL — the Web probe returned an empty payload; the call "
            "reached the bridge but produced nothing to compare",
            file=sys.stderr,
        )
        return 2

    desktop_payload = None
    for line in args.desktop.read_text(errors="replace").splitlines():
        match = DESKTOP_LINE.search(line)
        if match and match.group("script") == web_script:
            desktop_payload = match.group("payload")
    if desktop_payload is None:
        print(
            f"differential_diff: FAIL — no `KANAMA-DIFF {web_script} ...` line in "
            f"{args.desktop}. The desktop run did not emit the probe (is "
            "KANAMA_DEMO_SMOKE_QUIT=1 set?), so there is nothing to compare — which is "
            "NOT the same as the two agreeing.",
            file=sys.stderr,
        )
        return 2

    # A differential that compares a BROKEN desktop run against a healthy Web one reports
    # garbage as a platform bug. MEASURED on the first run of this tool: a worktree whose
    # assets had not been imported produced `textures=0` and null cursors on desktop
    # against `textures=5` on Web -- three "divergences" that were entirely a missing
    # `--import` pass. Resource-load failures invalidate the comparison, so refuse it.
    # (Leaked-RID and "resources still in use at exit" errors are normal on a headless
    # quit and are deliberately NOT treated as invalidating.)
    load_failures = [
        line.strip()
        for line in args.desktop.read_text(errors="replace").splitlines()
        if "Failed loading resource" in line or "Unable to open file" in line
    ]
    if load_failures:
        print(
            f"differential_diff: FAIL — the desktop run could not load "
            f"{len(load_failures)} resource(s), so its values are not comparable. Run "
            f"`godot --headless --import --path <project>` first.",
            file=sys.stderr,
        )
        for failure in load_failures[:5]:
            print(f"  {failure}", file=sys.stderr)
        return 2

    web_fields = _parse_payload(web_payload)
    desktop_fields = _parse_payload(desktop_payload)
    # A comparison over zero fields would "agree" vacuously.
    if not web_fields or not desktop_fields:
        print(
            "differential_diff: FAIL — a payload parsed to zero fields "
            f"(web={len(web_fields)}, desktop={len(desktop_fields)})",
            file=sys.stderr,
        )
        return 2

    differences: list[str] = []
    for key in sorted(set(web_fields) | set(desktop_fields)):
        web_value = web_fields.get(key)
        desktop_value = desktop_fields.get(key)
        if web_value != desktop_value:
            differences.append(
                f"  {key}: desktop={desktop_value!r} web={web_value!r}"
                + (" (missing on web)" if web_value is None else "")
                + (" (missing on desktop)" if desktop_value is None else "")
            )

    print(f"differential_diff: {web_script}")
    print(f"  desktop : {desktop_payload}")
    print(f"  web     : {web_payload}")

    if differences:
        print(
            f"differential_diff: FAIL — {len(differences)} field(s) differ between "
            f"platforms for the SAME Kotlin source:",
            file=sys.stderr,
        )
        for difference in differences:
            print(difference, file=sys.stderr)
        return 1

    print(f"  MATCH — {len(web_fields)} field(s) identical on both platforms")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
