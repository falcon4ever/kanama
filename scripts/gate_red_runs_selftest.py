#!/usr/bin/env python3
"""How gate_red_runs.py stops a case: the deadline, a signal, and a process that holds on to the output.

The harness is what reports every other gate's red run, so a stop that leaves a browser or a Godot running against
a deleted workspace, or that hides a deadline as a broken gate, shows up in no other result. This runs the real
harness (`main()`, in a child process) on a fixture tree shaped like the repo's slow gates: a leader whose TERM trap
stops a driver in a session of its own, beside a member that ignores TERM with its output elsewhere.

Usage: python3 scripts/gate_red_runs_selftest.py     (a few seconds; no Godot, no Gradle)
"""

from __future__ import annotations

import os
import signal
import subprocess
import sys
import tempfile
import time
from pathlib import Path

SCRIPTS = Path(__file__).resolve().parent
TAG = "[gate_red_runs_selftest]"
LIMIT = 25  # seconds a harness run gets here: a harness that waits for ever fails on this, it does not hang the gate
SESSION_LEADER = 'import os, sys\nos.setsid()\nos.execvp("sleep", ["sleep", "60"])'

TREE = f"""
trap 'kill -TERM "$driver"; echo done >"$FIXTURE/cleaned"; exit 1' TERM
python3 -c '{SESSION_LEADER}' >/dev/null 2>&1 &
driver=$!
(trap '' TERM; exec sleep 60) >/dev/null 2>&1 &
stubborn=$!
echo "$driver $stubborn $$" >"$FIXTURE/pids.tmp"
mv "$FIXTURE/pids.tmp" "$FIXTURE/pids"
wait
"""

# Leaves the case's group and keeps its stdout, which the leader's exit does not close.
ESCAPE = f"""
python3 -c '{SESSION_LEADER}' &
echo "$! $$" >"$FIXTURE/pids.tmp"
mv "$FIXTURE/pids.tmp" "$FIXTURE/pids"
wait
"""


def child(fixture: Path, shell: str, *harness_args: str) -> int:
    """The harness's own main(), on one fixture case, with the workspace of the fixture in place of a git worktree."""
    sys.path.insert(0, str(SCRIPTS))
    import gate_red_runs as harness

    harness.STOP_GRACE = 2
    harness.make_workspace = lambda: (fixture, fixture)
    harness.drop_workspace = lambda _tmp, _tree: None
    harness.CASES[:] = [harness.Case("fixture", ["bash", "-c", shell], [], "", "a fixture", env={"FIXTURE": str(fixture)}, timeout=1 if shell == ESCAPE else 600)]
    sys.argv = ["gate_red_runs.py", *harness_args]
    return harness.main()


def start(fixture: Path, shell: str, *harness_args: str, env: dict[str, str] | None = None) -> subprocess.Popen:
    return subprocess.Popen(
        [sys.executable, str(Path(__file__).resolve()), "--child", str(fixture), shell, *harness_args],
        stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, env={**os.environ, **(env or {})},
    )


def pids(fixture: Path) -> list[int]:
    path = fixture / "pids"
    return [int(pid) for pid in path.read_text().split()] if path.exists() else []


def alive(pid: int) -> bool:
    try:
        os.kill(pid, 0)
    except ProcessLookupError:  # justified: gone is the answer; the callers report what is still alive
        return False
    return True


def wait_gone(fixture: Path) -> list[int]:
    """The fixture's processes that are still alive a few seconds on (an orphan takes a moment to be reaped)."""
    until = time.monotonic() + 5
    while any(alive(pid) for pid in pids(fixture)) and time.monotonic() < until:
        time.sleep(0.05)
    return [pid for pid in pids(fixture) if alive(pid)]


def finish(proc: subprocess.Popen) -> tuple[int | None, str]:
    """The harness's exit status and output; None when it was still running at LIMIT."""
    try:
        out, _ = proc.communicate(timeout=LIMIT)
        return proc.returncode, out
    except subprocess.TimeoutExpired:  # justified: the None status is the failure, the callers report it
        proc.kill()
        return None, proc.communicate()[0]


def checks_of(name: str, fixture: Path, status: int | None, out: str, expected: int, wanted: list[str]) -> list[str]:
    errors = []
    if status is None:
        errors.append(f"{name}: the harness was still running after {LIMIT}s")
    elif status != expected:
        errors.append(f"{name}: the harness exited {status}, expected {expected}")
    errors += [f"{name}: the log lacks `{text}`" for text in wanted if text not in out]
    return errors


def tree_checks(name: str, fixture: Path) -> list[str]:
    errors = [] if (fixture / "cleaned").exists() else [f"{name}: the gate's TERM cleanup did not run"]
    left = wait_gone(fixture)
    if left:
        errors.append(f"{name}: left running: {left}")
    return errors


def deadline(fixture: Path) -> list[str]:
    # 3 seconds, so the tree is up and its trap set before the cut. The only case is the one cut: nothing is left
    # for the loop to find unrun, and the run must say "deadline" all the same.
    proc = start(fixture, TREE, "--deadline-minutes", "0.05", env={"GITHUB_ACTIONS": "true"})
    status, out = finish(proc)
    errors = checks_of("deadline", fixture, status, out, 1, ["fixture: cut by the deadline at the baseline run", "::error title=gate_red_runs deadline::fixture: cut by the deadline"])
    if "not green" in out or "exit 124" in out:
        errors.append("deadline: the cut is reported as a broken gate")
    return errors + tree_checks("deadline", fixture)


def interrupted(fixture: Path) -> list[str]:
    proc = start(fixture, TREE)
    until = time.monotonic() + LIMIT
    while not pids(fixture) and proc.poll() is None and time.monotonic() < until:
        time.sleep(0.05)
    proc.send_signal(signal.SIGINT)
    status, out = finish(proc)
    return checks_of("Ctrl-C", fixture, status, out, 128 + signal.SIGINT, []) + tree_checks("Ctrl-C", fixture)


def held_output(fixture: Path) -> list[str]:
    status, out = finish(start(fixture, ESCAPE))
    return checks_of("held output", fixture, status, out, 1, ["still holds its output", "timeout after"])


def main() -> int:
    if sys.argv[1:2] == ["--child"]:
        return child(Path(sys.argv[2]), sys.argv[3], *sys.argv[4:])
    errors: list[str] = []
    for scenario in (deadline, interrupted, held_output):
        with tempfile.TemporaryDirectory(prefix="gate_red_runs_selftest.") as tmp:
            fixture = Path(tmp).resolve()
            try:
                errors += scenario(fixture)
            finally:
                for pid in pids(fixture):  # a failed scenario must not leave what it found
                    if alive(pid):
                        os.kill(pid, signal.SIGKILL)
    if errors:
        print(f"{TAG} FAIL", file=sys.stderr)
        for error in errors:
            print(f"- {error}", file=sys.stderr)
        return 1
    print(f"{TAG} PASS the deadline, Ctrl-C and a held output stop a case and report it; nothing left running")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
