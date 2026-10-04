"""The one way a Python gate may skip a check (task 118); the shell twin is scripts/gate_skip.sh.

`skip(check_id, reason)` always prints `SKIP: <id>: <reason>`. Under CI (`CI=true`, which GitHub
Actions sets) it then raises SystemExit(1) -- a skipped check is a red run there -- unless the id is
listed in the comma-separated `KANAMA_ALLOW_SKIP`. Outside CI it returns, and the SKIP line on
screen is the record. Never print "skipping X" and carry on: that is a green for a check that did
not run.
"""

from __future__ import annotations

import os
import sys


def skip(check_id: str, reason: str) -> None:
    print(f"SKIP: {check_id}: {reason}")
    if os.environ.get("CI", "") not in ("true", "1"):
        return
    allowed = {entry.strip() for entry in os.environ.get("KANAMA_ALLOW_SKIP", "").split(",") if entry.strip()}
    if check_id in allowed:
        print(f"SKIP: {check_id}: allowed by KANAMA_ALLOW_SKIP in CI", file=sys.stderr)
        return
    print(
        f"FAIL: {check_id} was skipped in CI. Fix the runner, or list '{check_id}' in "
        "KANAMA_ALLOW_SKIP (an explicit, reviewed opt-out).",
        file=sys.stderr,
    )
    raise SystemExit(1)
