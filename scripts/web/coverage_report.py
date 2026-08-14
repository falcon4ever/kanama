#!/usr/bin/env python3
"""Report script members that NO driver exercises (task 80 slice 5).

The exercised-member census tells you which members a run actually dispatched. It
cannot tell you what it *missed*, because it only ever lists what happened — so a
member no driver has ever touched is invisible by construction. That is how
`Enemy.damage` stayed dark for months: fps's driver never fires a shot, and nothing
reported the omission. The same blind spot is why the corpus could be "green on Web"
while enemies were unkillable.

This pairs the export's protocol manifest (what the generator DECLARED) with a run's
census (what the driver actually REACHED) and names the difference.

It is a REPORT, not a gate: it always exits 0 unless its own inputs are unusable.
Coverage gaps are a backlog to work down, and failing the build on one would just
train people to stop reading it. What it must never do is stay quiet — it prints
totals even when everything is covered, and it prints the paths it used, so a
zero-gap report can be distinguished from a report that read nothing.

Signals: the census records Kotlin-lambda signal callbacks under one aggregate key
(`~kotlinSignalCallback`) because an anonymous lambda has no stable member name, so
per-signal coverage is NOT observable today. That limit is stated in the output
rather than papered over.

Usage:
    python3 scripts/web/coverage_report.py --result <envelope.json> \
        --manifest <export>/kanama-web/KanamaWebProtocol.generated.json
    python3 scripts/web/coverage_report.py --result <envelope.json> --export-dir <export>
"""

from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path
from typing import Any

MANIFEST_RELATIVE = Path("kanama-web") / "KanamaWebProtocol.generated.json"


def _load(path: Path, label: str) -> Any:
    if not path.is_file():
        print(f"coverage_report: FAIL — {label} not found at {path}", file=sys.stderr)
        raise SystemExit(2)
    try:
        return json.loads(path.read_text())
    except json.JSONDecodeError as error:
        print(f"coverage_report: FAIL — {label} at {path} is not valid JSON: {error}", file=sys.stderr)
        raise SystemExit(2)


def _short(class_name: str) -> str:
    """`net.multigesture.kanama.demo.Enemy` -> `Enemy`; the census keys on the tail."""
    return class_name.rsplit(".", 1)[-1]


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--result", type=Path, required=True, help="smoke result envelope")
    parser.add_argument("--manifest", type=Path, help="KanamaWebProtocol.generated.json")
    parser.add_argument("--export-dir", type=Path, help="export dir holding the manifest")
    args = parser.parse_args()

    manifest_path = args.manifest
    if manifest_path is None:
        if args.export_dir is None:
            print("coverage_report: FAIL — pass --manifest or --export-dir", file=sys.stderr)
            return 2
        manifest_path = args.export_dir / MANIFEST_RELATIVE

    envelope = _load(args.result, "result envelope")
    manifest = _load(manifest_path, "protocol manifest")

    scripts = manifest.get("scripts") or []
    # Assert a non-empty extraction before believing any conclusion: a manifest that
    # parsed but yielded no scripts would otherwise report "no gaps" for a whole demo.
    if not scripts:
        print(
            f"coverage_report: FAIL — manifest {manifest_path} declares 0 scripts; "
            "refusing to report coverage against an empty declaration",
            file=sys.stderr,
        )
        return 2

    census = envelope.get("exercisedMembers")
    if census is None:
        print(
            f"coverage_report: FAIL — {args.result} carries no exercisedMembers census "
            "(the driver could not read the bridge, or predates the gate)",
            file=sys.stderr,
        )
        return 2

    demo = envelope.get("demo", "<unknown>")
    declared_total = 0
    reached_total = 0
    gaps: list[tuple[str, str, str]] = []  # (script, kind, name)

    for script in scripts:
        short = _short(script.get("className", ""))
        # The census keys on whatever name the bridge recorded at recordReady; match on
        # the tail so `demo.Enemy` and `Enemy` both resolve.
        exercised = {}
        for key, members in census.items():
            if _short(key) == short and isinstance(members, dict):
                exercised.update(members)

        for virtual in script.get("virtuals") or []:
            name = virtual.get("name", "?")
            declared_total += 1
            if exercised.get(name):
                reached_total += 1
            else:
                gaps.append((short, "virtual", name))

        for method in script.get("methods") or []:
            name = method.get("name", "?")
            method_id = method.get("id")
            declared_total += 1
            # The bridge records registered methods under `method#<id>` internally, but the
            # engine drivers resolve that id to the manifest NAME before writing the
            # envelope -- so by the time a census reaches this script the key is the name.
            # Both forms are accepted: keying only on `method#<id>` reported every exercised
            # method as dark (measured against a passing fps run, which really does dispatch
            # `damage`), and keying only on the name would miss any pre-resolution envelope.
            if exercised.get(name) or (method_id is not None and exercised.get(f"method#{method_id}")):
                reached_total += 1
            else:
                gaps.append((short, "method", name))

    print(f"coverage_report: {demo}")
    print(f"  manifest : {manifest_path}")
    print(f"  result   : {args.result}")
    print(f"  reached  : {reached_total}/{declared_total} declared virtuals + registered methods")

    # WebSpikeScript is the transport-benchmark harness, compiled into every export but
    # not part of any demo's gameplay. Reported separately rather than filtered, so the
    # output never hides something it decided was uninteresting.
    demo_gaps = [g for g in gaps if g[0] != "WebSpikeScript"]
    harness_gaps = [g for g in gaps if g[0] == "WebSpikeScript"]

    if demo_gaps:
        print(f"  NEVER EXERCISED BY THIS RUN ({len(demo_gaps)}):")
        for script_name, kind, name in demo_gaps:
            print(f"    {script_name}.{name}  ({kind})")
    else:
        print("  no gaps: every declared virtual and registered method was dispatched")
    if harness_gaps:
        print(
            f"  ({len(harness_gaps)} more in WebSpikeScript, the benchmark harness — "
            "not demo gameplay, expected dark outside the spike cell)"
        )

    print(
        "  note: per-SIGNAL coverage is not observable — Kotlin-lambda subscriptions "
        "are censused under one aggregate key (~kotlinSignalCallback)"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
