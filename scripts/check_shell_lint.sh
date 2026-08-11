#!/usr/bin/env bash
# check_shell_lint.sh -- shellcheck gate over the repo's gate/smoke shell scripts.
#
# Motivation (same parcel as the web-driver ESLint gate): task 86 was an unused
# variable that no human read for two weeks. Mechanical lint catches that class
# of defect at introduction; this gate does the same for the shell layer that
# decides whether CI is green.
#
# Severity is PINNED at `warning`: error + warning findings gate, info + style
# do not. The info/style classes are where shellcheck's opinions turn into
# style wars (SC2086 on an intentional word-split of $DRIVER_CMD in
# web_export_smoke.sh, SC2016 on literal-$ sed patterns in export_game_smoke.sh,
# SC2004 arithmetic dollar style); anything in them that IS a real defect can be
# promoted by fixing it, not by widening the gate. Exclusions above the pin are
# never silent: they live as `# shellcheck disable=` directives next to the code
# with a reason (see scripts/web/demos.sh for the sourced-registry SC2034 case,
# scripts/web_export_smoke.sh for SC2086).
#
# The file set is EXPLICIT (scripts/*.sh + scripts/web/*.sh, non-recursive) so a
# new script dropped in either directory is linted on arrival, and the gate
# fails if the globs ever match nothing -- an empty file set passing vacuously
# is the "two empty dirs diff identical" trap.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

if ! command -v shellcheck >/dev/null 2>&1; then
  echo "[shell_lint] shellcheck is required" >&2
  echo "[shell_lint]   macOS:   brew install shellcheck" >&2
  echo "[shell_lint]   Ubuntu:  sudo apt-get install -y shellcheck" >&2
  echo "[shell_lint] (GitHub ubuntu runners ship it preinstalled; if this fired in CI," >&2
  echo "[shell_lint]  the runner image changed -- install it in the workflow step.)" >&2
  exit 2
fi

shopt -s nullglob
shell_files=("$ROOT_DIR"/scripts/*.sh "$ROOT_DIR"/scripts/web/*.sh)
shopt -u nullglob

if [[ ${#shell_files[@]} -eq 0 ]]; then
  echo "[shell_lint] file set matched nothing; the scripts tree moved and this gate is vacuous" >&2
  exit 2
fi

shellcheck --severity=warning "${shell_files[@]}"

echo "[shell_lint] PASS files=${#shell_files[@]} severity=warning shellcheck=$(shellcheck --version | sed -n 's/^version: //p')"
