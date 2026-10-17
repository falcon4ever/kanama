# shellcheck shell=bash
# run_logs.sh -- a smoke's default output directory, one per run. Source it; do not run it.
#
# A fixed default under /tmp was shared by concurrent runs (two runtime smokes interleaved one log
# and failed a pattern check), so a smoke's default logs go into a directory of the run's own:
#
#   run_logs_dir NAME             create ${TMPDIR:-/tmp}/NAME.XXXXXX and set RUN_LOGS_DIR to it
#   run_logs_finish STATUS TAG    from the EXIT trap: on status 0 delete RUN_LOGS_DIR; otherwise keep it
#                                 and print where it is, so a failed run's logs can be read
#
# A log the caller names through its KANAMA_*_LOG variable lives wherever the caller put it and is
# never deleted here.
RUN_LOGS_DIR=""

run_logs_dir() {
  RUN_LOGS_DIR="$(mktemp -d "${TMPDIR:-/tmp}/$1.XXXXXX")"
}

run_logs_finish() {
  local status="$1" tag="$2"
  if [[ -z "$RUN_LOGS_DIR" || ! -d "$RUN_LOGS_DIR" ]]; then
    return 0
  fi
  if [[ "$status" -eq 0 ]]; then
    rm -rf "$RUN_LOGS_DIR"
  else
    echo "[$tag] exit $status: logs kept in $RUN_LOGS_DIR" >&2
  fi
}
