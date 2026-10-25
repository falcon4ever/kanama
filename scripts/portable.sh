# shellcheck shell=bash
# portable.sh -- the one place a script branches on the platform (task 138 item 34). Source it; do not run it.
#
# Gate scripts run on a maintainer's Mac and on GitHub's ubuntu runners, so they use only the tools both
# have (`grep -E`, `sed`, `awk`, `python3`, bash 3.2 features): no `rg`, no `grep -P`, no bash 4. Where
# the two platforms genuinely differ, the difference lives here, once, and the scripts call the helper.
# scripts/check_script_portability.py enforces it (a `sed -i` anywhere but here is a CI failure).
#
#   sed_inplace <file> <sed-args...>    `sed -i` that behaves the same under BSD sed and GNU sed
#
# BSD sed wants `-i EXT` (`-i ''` for none) and GNU sed takes `-i[EXT]`; the attached-suffix spelling
# `-i.EXT` is the one both accept, so the helper edits with a backup suffix and removes the backup
# when sed succeeded.
sed_inplace() {
  local file="$1"
  shift
  sed -i.portable-bak "$@" "$file" || return
  rm -f "${file}.portable-bak"
}

#   run_with_timeout <seconds> <cmd...> run a command in its own process group and kill the whole group
#                                       after <seconds> (exit 124), like coreutils `timeout`, which macOS lacks
#
# Used for every Godot or Gradle call a gate script makes that has no timeout of its own: a hung editor
# must fail the script, not leave a process behind. python3 is on both platforms; the group kill also
# reaps what the command started, and an interrupt (Ctrl-C, SIGTERM) kills the group before exiting.
run_with_timeout() {
  local seconds="$1"
  shift
  python3 -c '
import os, signal, subprocess, sys
seconds, cmd = float(sys.argv[1]), sys.argv[2:]
proc = subprocess.Popen(cmd, start_new_session=True)
def kill_group(sig):
    try:
        os.killpg(proc.pid, sig)
    except ProcessLookupError:  # justified: the group already exited
        pass
def on_signal(signum, _frame):
    kill_group(signal.SIGKILL)
    sys.exit(128 + signum)
for signum in (signal.SIGINT, signal.SIGTERM, signal.SIGHUP):
    signal.signal(signum, on_signal)
try:
    sys.exit(proc.wait(timeout=seconds))
except subprocess.TimeoutExpired:
    kill_group(signal.SIGTERM)
    try:
        proc.wait(timeout=10)
    except subprocess.TimeoutExpired:  # justified: SIGKILL below, then the exit 124
        pass
    kill_group(signal.SIGKILL)
    print("[run_with_timeout] timed out after %gs, process group killed: %s" % (seconds, " ".join(cmd)), file=sys.stderr)
    sys.exit(124)
' "$seconds" "$@"
}
