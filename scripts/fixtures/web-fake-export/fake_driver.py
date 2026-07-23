#!/usr/bin/env python3
"""Deterministic fake browser driver for the export-smoke scaffold self-test.

This driver launches no browser. It accepts the same explicit flags a real
driver does (``--url``, ``--result``, ``--demo``, ``--timeout``,
``--source-checksum``, optional ``--browser-binary``) plus a ``--mode`` selector,
and produces one of the outcomes the shell must handle. It exists only to prove
the shell's mechanics; it never claims a real demo passed.

Modes:
  success          write a valid, passing result envelope; exit 0
  fail             write a valid envelope with a failed assertion (pass=false); exit 0
  malformed        write non-JSON to the result path; exit 0
  schema-violation write JSON missing a required field; exit 0
  wrong-checksum   write a passing envelope with a mismatched source checksum; exit 0
  crash            write nothing and exit non-zero
  timeout          sleep past the shell's deadline (never writes a result)
  mutate           write into --mutate-path (in the served tree) then succeed
"""

from __future__ import annotations

import argparse
import json
import sys
import time


def _envelope(demo: str, checksum: str, *, passing: bool) -> dict:
    failed = 0 if passing else 1
    checks = {"fakeStartup": True, "fakeGameplay": passing, "fakeTeardown": True}
    passed = sum(1 for value in checks.values() if value)
    total = len(checks)
    return {
        "schemaVersion": 1,
        "demo": demo,
        "protocolVersion": 6,
        "pass": passing,
        "durationMs": 12,
        "artifact": {
            "url": "http://127.0.0.1:0/",
            "files": [
                {"name": "index.html", "bytes": 512},
                {"name": "kanama-web-fake.js", "bytes": 160},
            ],
            "totalBytes": 672,
            "sourceTreeChecksum": checksum,
        },
        "browser": {"engine": "chrome", "name": "fake-fixture", "version": "0"},
        "startup": {"loaded": True, "outcome": "ready", "durationMs": 5},
        "assertions": {
            "summary": {"total": total, "passed": passed, "failed": failed, "skipped": 0},
            "checks": checks,
        },
        "crossings": {"total": 3},
        "handles": {"liveAfterGameplay": 4, "liveAfterTeardown": 0, "staleRejected": 1},
        "callbacks": {"pending": 0},
        "connections": {"open": 0},
        "scheduler": {"pendingCoroutines": 0},
        "console": {"errors": [], "boundaryErrors": []},
        "teardown": {"outcome": "clean", "ownerRegistriesToBaseline": True},
    }


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--url", required=True)
    parser.add_argument("--result", required=True)
    parser.add_argument("--demo", required=True)
    parser.add_argument("--timeout", type=int, required=True)
    parser.add_argument("--source-checksum", required=True)
    parser.add_argument("--export-dir", default=None)
    parser.add_argument("--browser-binary", default=None)
    parser.add_argument("--mode", default="success")
    parser.add_argument("--mutate-path", default=None)
    args = parser.parse_args(argv)

    if args.mode == "timeout":
        # Sleep well past the shell's deadline; the shell must kill us.
        time.sleep(args.timeout + 120)
        return 0

    if args.mode == "crash":
        print("fake_driver: simulated browser crash", file=sys.stderr)
        return 3

    if args.mode == "malformed":
        with open(args.result, "w", encoding="utf-8") as handle:
            handle.write("this is not json{")
        return 0

    if args.mode == "schema-violation":
        envelope = _envelope(args.demo, args.source_checksum, passing=True)
        del envelope["handles"]  # drop a required top-level group
        with open(args.result, "w", encoding="utf-8") as handle:
            json.dump(envelope, handle)
        return 0

    if args.mode == "mutate":
        if args.mutate_path:
            with open(args.mutate_path, "a", encoding="utf-8") as handle:
                handle.write("\n<!-- mutated by fake driver -->\n")
        envelope = _envelope(args.demo, args.source_checksum, passing=True)
        with open(args.result, "w", encoding="utf-8") as handle:
            json.dump(envelope, handle)
        return 0

    passing = args.mode != "fail"
    checksum = "deadbeef" if args.mode == "wrong-checksum" else args.source_checksum
    envelope = _envelope(args.demo, checksum, passing=passing)
    with open(args.result, "w", encoding="utf-8") as handle:
        json.dump(envelope, handle, indent=2)
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
