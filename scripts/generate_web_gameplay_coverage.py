#!/usr/bin/env python3
"""Generate the explicit Kotlin/Wasm gameplay-call backlog for Task 57."""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path


BLOCKING_MARKER = re.compile(r'unsupportedWebGameplayCall\("([^"]+)"\)')
NONBLOCKING_MARKER = re.compile(r'unsupportedWebGameplayFamily\("([^"]+)"\)')


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--output", type=Path, required=True)
    parser.add_argument("sources", nargs="+", type=Path)
    args = parser.parse_args()

    blocking_calls: set[str] = set()
    nonblocking_calls: set[str] = set()
    for source in args.sources:
        text = source.read_text(encoding="utf-8")
        blocking_calls.update(BLOCKING_MARKER.findall(text))
        nonblocking_calls.update(NONBLOCKING_MARKER.findall(text))

    if not blocking_calls and not nonblocking_calls:
        raise SystemExit("no explicit Web gameplay coverage markers found")

    payload = {
        "schemaVersion": 2,
        "status": "blocking" if blocking_calls else "unblocked",
        "task": "57e",
        "blockingCallCount": len(blocking_calls),
        "blockingCalls": sorted(blocking_calls),
        "nonblockingUnsupportedCallCount": len(nonblocking_calls),
        "nonblockingUnsupportedCalls": sorted(nonblocking_calls),
    }
    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")
    print(
        f"[web_gameplay_coverage] blocking={len(blocking_calls)} "
        f"nonblocking={len(nonblocking_calls)} output={args.output}"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
