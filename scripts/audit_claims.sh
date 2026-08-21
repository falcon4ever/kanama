#!/usr/bin/env bash
# audit_claims.sh -- run every "is this still true?" check in one place (task 85).
#
# Task 85 exists because a codebase accumulates CLAIMS -- comments, docs, status tables,
# version numbers -- that were true when written and quietly stopped being true. Kanama had
# twelve code-vs-code audits and zero claim audits when it was filed.
#
# The individual gates were added one incident at a time. This is the entry point that
# answers the whole question at once, so "are our claims still true?" is one command before
# a release, a handoff, or trusting an index.
#
# WHY AN AGGREGATOR AND NOT JUST MORE CI: the checks below live in two repos and one of them
# (kanama-tasks) is local-only with no remote and no CI. Nothing can schedule it. A single
# command that a human runs is the honest mechanism, so this prints what it SKIPPED as
# loudly as what it checked -- a claim audit that quietly examined half of what it names
# would be the exact failure it exists to prevent.
#
# Usage:  scripts/audit_claims.sh [--prs]
#         --prs also cross-checks kanama-tasks' PR statuses against GitHub (needs gh).

set -uo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TASKS_DIR="${KANAMA_TASKS_DIR:-$ROOT_DIR/../kanama-tasks}"
WITH_PRS=0
[[ "${1:-}" == "--prs" ]] && WITH_PRS=1

ran=0
failed=0
skipped=0
declare -a RESULTS=()

# Every check runs only if its script is actually there. Without this the "everything was
# skipped" guard below is UNREACHABLE -- the four core checks were unconditional, so `ran`
# could never be 0 and the guard was decoration. Found by trying to falsify it.
require_script() {
  local label="$1" path="$2"
  if [[ ! -f "$path" ]]; then
    skip "$label" "missing $path"
    return 1
  fi
  return 0
}

check() {
  local label="$1"; shift
  echo "── ${label}"
  if "$@"; then
    RESULTS+=("PASS    ${label}")
    ran=$((ran + 1))
  else
    RESULTS+=("FAIL    ${label}")
    ran=$((ran + 1)); failed=$((failed + 1))
  fi
  echo
}

skip() {
  echo "── ${1} -- SKIPPED: ${2}"
  RESULTS+=("SKIP    ${1} (${2})")
  skipped=$((skipped + 1))
  echo
}

echo "=== Kanama claim audit (task 85) ==="
echo

# 1. Marked documentation claims vs the code they describe.
require_script "documentation claims vs sources" "$ROOT_DIR/scripts/check_doc_claims.py" && \
  check "documentation claims vs sources" python3 "$ROOT_DIR/scripts/check_doc_claims.py" --root "$ROOT_DIR"

# 2. KANAMA-BLOCKED markers -- these fail when a limitation is LIFTED, which is the
#    direction nobody remembers to check.
require_script "stale blocker markers" "$ROOT_DIR/scripts/audit_stale_blockers.py" && \
  check "stale blocker markers" python3 "$ROOT_DIR/scripts/audit_stale_blockers.py"

# 3. The protocol number, which is restated by hand in six places.
require_script "web protocol pin agreement" "$ROOT_DIR/scripts/check_protocol_pins.py" && \
  check "web protocol pin agreement" python3 "$ROOT_DIR/scripts/check_protocol_pins.py"

# 4. Lifecycle annotations imported but never applied (a dead hook claims behaviour it
#    does not have).
if ! require_script "unapplied lifecycle annotations" "$ROOT_DIR/scripts/check_unapplied_annotations.py"; then
  :
elif [[ -d "${KANAMA_DEMOS_DIR:-/nonexistent}" ]]; then
  check "unapplied lifecycle annotations (kanama + demos)" \
    python3 "$ROOT_DIR/scripts/check_unapplied_annotations.py" "$ROOT_DIR" "${KANAMA_DEMOS_DIR}"
else
  check "unapplied lifecycle annotations (kanama only)" \
    python3 "$ROOT_DIR/scripts/check_unapplied_annotations.py" "$ROOT_DIR"
fi

# 5. The task repo's own links and PR statuses. Local-only, no CI, so this is the ONLY
#    thing that ever checks it -- and it is where the worst rot was found (18 dead links,
#    5 statuses that outlived their PRs, two of which sent a session at the wrong task).
if [[ -f "$TASKS_DIR/check_task_index.py" ]]; then
  if [[ $WITH_PRS -eq 1 ]]; then
    check "task index links + PR statuses" python3 "$TASKS_DIR/check_task_index.py" --prs
  else
    check "task index links" python3 "$TASKS_DIR/check_task_index.py"
  fi
else
  skip "task index" "no checker at $TASKS_DIR (set KANAMA_TASKS_DIR)"
fi

echo "=== summary ==="
for line in "${RESULTS[@]}"; do echo "  $line"; done
echo

if [[ $ran -eq 0 ]]; then
  echo "[audit_claims] FAIL every check was skipped -- refusing to report a vacuous pass"
  exit 2
fi
if [[ $failed -gt 0 ]]; then
  echo "[audit_claims] FAIL ${failed} of ${ran} check(s) failed (${skipped} skipped)"
  exit 1
fi
if [[ $skipped -gt 0 ]]; then
  echo "[audit_claims] PASS ${ran} check(s) -- but ${skipped} SKIPPED, so this is not a"
  echo "               clean bill of health for what was not run"
  exit 0
fi
echo "[audit_claims] PASS all ${ran} checks"
