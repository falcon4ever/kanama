# shellcheck shell=bash
# gate_skip.sh -- the one way a gate script may skip a check (task 118). Source it; do not run it.
#
#   gate_skip <id> <reason>
#
# Always prints `SKIP: <id>: <reason>`. Under CI (`CI=true`, which GitHub Actions sets) it then
# returns 1 -- a skipped check is a red run there -- unless the id is listed in the comma-separated
# `KANAMA_ALLOW_SKIP` (for example `KANAMA_ALLOW_SKIP=kdoc-staleness,task-index`). Outside CI it
# returns 0: a maintainer's machine may legitimately lack a Godot docs checkout, and the SKIP line
# on screen is the whole record. Never write "skipping X" to stdout and carry on: that is a green
# for a check that did not run. Callers write `gate_skip id "why" || exit 1` (or `return 1`).
gate_skip() {
  local id="$1" reason="$2" ci entry
  local -a allowed
  echo "SKIP: ${id}: ${reason}"
  # Same rules as gate_skip.py: CI is true/1/yes in any case; KANAMA_ALLOW_SKIP entries are trimmed.
  ci="$(printf '%s' "${CI:-}" | tr '[:upper:]' '[:lower:]' | tr -d '[:space:]')"
  case "$ci" in
    true | 1 | yes)
      IFS=',' read -r -a allowed <<<"${KANAMA_ALLOW_SKIP:-}"
      for entry in ${allowed[@]+"${allowed[@]}"}; do
        entry="${entry#"${entry%%[![:space:]]*}"}"
        entry="${entry%"${entry##*[![:space:]]}"}"
        if [[ "$entry" == "$id" ]]; then
          echo "SKIP: ${id}: allowed by KANAMA_ALLOW_SKIP in CI" >&2
          return 0
        fi
      done
      echo "FAIL: ${id} was skipped in CI. Fix the runner, or list '${id}' in KANAMA_ALLOW_SKIP (an explicit, reviewed opt-out)." >&2
      return 1
      ;;
  esac
  return 0
}
