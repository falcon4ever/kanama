#!/usr/bin/env python3
"""Check the gate-evidence ledger against the current Godot pin (task 99, review R17).

The local-only gates -- Android and iOS device matrices, the Safari corpus, the
Windows/Linux revalidations -- leave their last-run dates in prose only. When the
pin moved from 4.7.0 to 4.7.2 (task 91) the mobile and Web evidence stayed dated on
the old templates and NOTHING could go red. `evidence/gates.json` is the
machine-checkable record; this script is what makes a stale pin fail.

Rules (one entry = one run of one gate):

  * every entry carries exactly the known fields; an unknown field FAILS (a typo in
    `acceptedStaleUntil` must not silently disable the acceptance it names);
  * an entry whose `godotPin` differs from `kanamaGodotVersion` in gradle.properties
    is STALE, unless a later entry for the same `gate` is on the current pin (then it
    is history, and reported as superseded);
  * a stale entry FAILS unless it carries `acceptedStaleUntil` (a future ISO date)
    AND a non-empty `acceptedStaleReason`. An accepted-stale entry passes with a loud
    WARN line -- the quarantine pattern, with an expiry, so acceptance cannot outlive
    the decision that granted it;
  * an `acceptedStaleUntil` in the past FAILS;
  * an empty ledger FAILS (vacuous-pass guard, as the other checkers do).

Usage:
    python3 scripts/check_gate_evidence.py            # from anywhere in the checkout
    python3 scripts/check_gate_evidence.py --ledger evidence/gates.json --today 2026-11-01
"""

from __future__ import annotations

import argparse
import datetime as dt
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
LEDGER = ROOT / "evidence/gates.json"
GRADLE_PROPERTIES = ROOT / "gradle.properties"

REQUIRED_FIELDS = ("gate", "claim", "godotPin", "kanamaSha", "date", "where", "result", "source")
OPTIONAL_FIELDS = ("acceptedStaleUntil", "acceptedStaleReason")
NON_EMPTY_FIELDS = ("gate", "godotPin", "date", "where", "result")
RESULTS = ("PASS", "FAIL", "PARTIAL")
ISO_DATE = re.compile(r"^\d{4}-\d{2}-\d{2}$")
TAG = "[check_gate_evidence]"


def current_pin(path: Path = GRADLE_PROPERTIES) -> str:
    match = re.search(r"^kanamaGodotVersion=(.+)$", path.read_text(encoding="utf-8"), re.MULTILINE)
    if not match:
        raise SystemExit(f"{TAG} FAIL kanamaGodotVersion missing from {path}")
    return match.group(1).strip()


def parse_date(value: object, label: str, errors: list[str]) -> dt.date | None:
    if not isinstance(value, str) or not ISO_DATE.match(value):
        errors.append(f"{label}: expected an ISO date (YYYY-MM-DD), got {value!r}")
        return None
    try:
        return dt.date.fromisoformat(value)
    except ValueError:
        errors.append(f"{label}: not a calendar date: {value!r}")
        return None


def load_ledger(path: Path) -> list[dict]:
    if not path.is_file():
        raise SystemExit(f"{TAG} FAIL ledger missing: {path}")
    text = path.read_text(encoding="utf-8")
    if not text.strip():
        raise SystemExit(f"{TAG} FAIL ledger is empty: {path}")
    try:
        data = json.loads(text)
    except json.JSONDecodeError as error:
        raise SystemExit(f"{TAG} FAIL ledger is not valid JSON: {path}: {error}") from error
    if not isinstance(data, dict) or data.get("schemaVersion") != 1:
        raise SystemExit(f"{TAG} FAIL ledger must be an object with schemaVersion 1: {path}")
    if set(data) != {"schemaVersion", "gates"}:
        raise SystemExit(f"{TAG} FAIL ledger top level must be exactly schemaVersion + gates, got {sorted(data)}")
    gates = data["gates"]
    if not isinstance(gates, list) or not gates:
        raise SystemExit(f"{TAG} FAIL ledger has no gate entries -- refusing to report a vacuous pass: {path}")
    return gates


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--ledger", type=Path, default=LEDGER)
    parser.add_argument("--today", type=dt.date.fromisoformat, default=dt.date.today(), help="override today (tests)")
    args = parser.parse_args()

    pin = current_pin()
    entries = load_ledger(args.ledger)
    errors: list[str] = []
    warnings: list[str] = []
    superseded: list[str] = []

    # Latest date per gate ON the current pin: anything older on another pin is history.
    current_by_gate: dict[str, dt.date] = {}
    for index, entry in enumerate(entries):
        if not isinstance(entry, dict):
            errors.append(f"gates[{index}]: entry must be an object")
            continue
        label = f"gates[{index}] {entry.get('gate', '?')} {entry.get('date', '?')}"
        unknown = set(entry) - set(REQUIRED_FIELDS) - set(OPTIONAL_FIELDS)
        if unknown:
            errors.append(f"{label}: unknown field(s) {sorted(unknown)}")
        for field in REQUIRED_FIELDS:
            if field not in entry:
                errors.append(f"{label}: missing required field {field!r}")
            elif not isinstance(entry[field], str):
                errors.append(f"{label}: field {field!r} must be a string")
            elif field in NON_EMPTY_FIELDS and not entry[field].strip():
                errors.append(f"{label}: field {field!r} must not be empty")
        if entry.get("result") not in RESULTS:
            errors.append(f"{label}: result must be one of {RESULTS}, got {entry.get('result')!r}")
        date = parse_date(entry.get("date"), f"{label}: date", errors)
        if date is not None and entry.get("godotPin") == pin:
            current_by_gate[entry["gate"]] = max(date, current_by_gate.get(entry["gate"], date))

    for index, entry in enumerate(entries):
        if not isinstance(entry, dict) or entry.get("godotPin") == pin:
            continue
        label = f"{entry.get('gate', '?')} ({entry.get('date', '?')}, pin {entry.get('godotPin', '?')})"
        date = dt.date.fromisoformat(entry["date"]) if ISO_DATE.match(str(entry.get("date", ""))) else None
        newer = current_by_gate.get(entry.get("gate", ""))
        if date is not None and newer is not None and newer >= date:
            superseded.append(f"{label} superseded by a {pin} run on {newer}")
            continue
        until_raw = entry.get("acceptedStaleUntil")
        reason = entry.get("acceptedStaleReason", "")
        if until_raw is None:
            errors.append(
                f"{label}: STALE -- evidence predates the current pin {pin} and carries no "
                "acceptedStaleUntil; re-run the gate or accept the staleness with an expiry + reason",
            )
            continue
        until = parse_date(until_raw, f"{label}: acceptedStaleUntil", errors)
        if until is None:
            continue
        if not isinstance(reason, str) or not reason.strip():
            errors.append(f"{label}: acceptedStaleUntil without a non-empty acceptedStaleReason")
            continue
        if until < args.today:
            errors.append(
                f"{label}: acceptedStaleUntil {until} has passed (today {args.today}); "
                "re-run the gate on the current pin or renew the acceptance deliberately",
            )
            continue
        warnings.append(f"{label} accepted stale until {until} -- {reason}")

    for line in superseded:
        print(f"{TAG} superseded: {line}")
    for line in warnings:
        print(f"{TAG} WARN accepted-stale: {line}")
    if errors:
        print(f"{TAG} FAIL {len(errors)} problem(s) in {args.ledger} (current pin {pin}):", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1
    on_pin = sum(1 for entry in entries if entry.get("godotPin") == pin)
    print(
        f"{TAG} PASS {len(entries)} entries: {on_pin} on pin {pin}, {len(warnings)} accepted-stale (WARN above), "
        f"{len(superseded)} superseded",
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
