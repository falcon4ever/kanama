#!/usr/bin/env python3
"""Append one gate run to the evidence ledger (task 99, review R17).

Called at the PASS point of the local-only gate scripts (and by hand for the ones
that live in the demos repo), so `evidence/gates.json` records the Godot pin and
Kanama commit a gate was actually run on. `scripts/check_gate_evidence.py` then
fails the claim audit when the latest run of a gate predates the current pin.

The pin is read from gradle.properties and the commit from `git rev-parse HEAD`;
neither is taken from the command line, because a ledger entry that can be typed is
a ledger entry that can be typed wrong.

Usage:
    python3 scripts/record_gate_evidence.py --gate ios-device-gate --result PASS \
        --where "iPhone 15 Pro, iOS 26.5" [--date 2026-09-09] [--claim "iOS Supported"] \
        [--source scripts/ios_device_gate.sh]
"""

from __future__ import annotations

import argparse
import datetime as dt
import json
import subprocess
from pathlib import Path

from check_gate_evidence import LEDGER, RESULTS, TAG, current_pin, load_ledger


def head_sha() -> str:
    try:
        return subprocess.run(
            ["git", "-C", str(LEDGER.parent.parent), "rev-parse", "HEAD"],
            capture_output=True,
            text=True,
            check=True,
        ).stdout.strip()
    except (OSError, subprocess.CalledProcessError):
        return "unknown"


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--gate", required=True, help="stable gate id, e.g. ios-device-gate")
    parser.add_argument("--result", required=True, choices=RESULTS)
    parser.add_argument("--where", required=True, help="device / browser / host the gate ran on")
    parser.add_argument("--date", type=dt.date.fromisoformat, default=dt.date.today())
    parser.add_argument("--claim", default="", help="the support claim this run backs")
    parser.add_argument("--source", default="scripts/record_gate_evidence.py")
    parser.add_argument("--ledger", type=Path, default=LEDGER)
    args = parser.parse_args()

    entries = load_ledger(args.ledger) if args.ledger.is_file() and args.ledger.stat().st_size else []
    entry = {
        "gate": args.gate,
        "claim": args.claim,
        "godotPin": current_pin(),
        "kanamaSha": head_sha(),
        "date": args.date.isoformat(),
        "where": args.where,
        "result": args.result,
        "source": args.source,
    }
    entries.append(entry)
    args.ledger.parent.mkdir(parents=True, exist_ok=True)
    args.ledger.write_text(json.dumps({"schemaVersion": 1, "gates": entries}, indent=2) + "\n", encoding="utf-8")
    print(f"{TAG.replace('check', 'record')} appended {args.gate} {args.result} ({entry['godotPin']} @ {entry['kanamaSha'][:12]}) to {args.ledger}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
