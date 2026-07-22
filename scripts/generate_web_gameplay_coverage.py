#!/usr/bin/env python3
"""Generate the explicit Kotlin/Wasm gameplay-call backlog for Task 57."""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path


MARKER = re.compile(r'unsupportedWebGameplayCall\("([^"]+)"\)')


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--output", type=Path, required=True)
    parser.add_argument("sources", nargs="+", type=Path)
    args = parser.parse_args()

    blocking_calls: set[str] = set()
    for source in args.sources:
        text = source.read_text(encoding="utf-8")
        blocking_calls.update(MARKER.findall(text))

    if not blocking_calls:
        raise SystemExit("no explicit Web gameplay backlog markers found")

    payload = {
        "schemaVersion": 1,
        "status": "blocking",
        "task": "57e",
        "blockingCallCount": len(blocking_calls),
        "blockingCalls": sorted(blocking_calls),
    }
    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")
    print(
        f"[web_gameplay_coverage] blocking={len(blocking_calls)} output={args.output}"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
