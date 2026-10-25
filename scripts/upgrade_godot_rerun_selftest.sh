#!/usr/bin/env bash
# upgrade_godot_rerun_selftest.sh -- prove that a Godot bump that stops half-way can be re-run.
#
# usage: scripts/upgrade_godot_rerun_selftest.sh [upgrade_godot.sh options] /absolute/path/to/godot_binary
#
# Run it from a checkout that carries the pin edit of the bump to test (the files
# `check_godot_version_pin.py --list-files` names), with the same options you give upgrade_godot.sh.
# It never touches that checkout: everything runs in a throwaway worktree of HEAD plus the pin edit.
#
#   A  the bump, start to end: the reference result (its exit status and the tree it leaves)
#   B  the bump stopped after step 5 (KANAMA_UPGRADE_FAIL_AFTER_STEP=5), then --reset, then the bump
#   C  the bump stopped after step 6, its half-done result COMMITTED, then the bump with
#      --baseline-ref set to the pre-bump commit
#
# B and C must end with A's exit status, A's tree (every file, tracked or not) and A's list of the
# classes this Godot added. With --dry-run in the options the test is: a dry run stopped after step 5
# leaves the checkout as it found it, and a dry run after it passes.
#
# Local only (a Godot binary, jextract and a Godot source checkout); a bump run takes 2-4 minutes, so
# the whole test takes about 10-15.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TAG="[upgrade_rerun_selftest]"
DRY_RUN=0
for arg in "$@"; do
  if [[ "$arg" == --dry-run ]]; then
    DRY_RUN=1
  fi
  if [[ "$arg" == --reset || "$arg" == --baseline-ref || "$arg" == --plan ]]; then
    echo "$TAG $arg is the test's own; pass the options of the bump under test only" >&2
    exit 2
  fi
done
if [[ $# -eq 0 ]]; then
  sed -n '2,20p' "$0" >&2
  exit 2
fi

WORK="$(mktemp -d "${TMPDIR:-/tmp}/kanama-upgrade-rerun.XXXXXX")"
TREE="$WORK/tree"
cleanup() {
  # justified: best effort; the rm and the prune below finish the job when the worktree is half-gone.
  git -C "$ROOT_DIR" worktree remove --force "$TREE" >/dev/null 2>&1 || true
  rm -rf "$WORK"
  # justified: housekeeping of a worktree registration that is already gone.
  git -C "$ROOT_DIR" worktree prune >/dev/null 2>&1 || true
}
trap cleanup EXIT

git -C "$ROOT_DIR" worktree add --detach --quiet "$TREE" HEAD
BASE="$(git -C "$TREE" rev-parse HEAD)"
while IFS= read -r pin; do
  cp "$ROOT_DIR/$pin" "$TREE/$pin"
done < <(python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" --list-files)

# run <name> [env assignments...] -- <upgrade options...>: run the worktree's upgrade_godot.sh, log to
# $WORK/<name>.log, leave the exit status in RC.
run() {
  local name="$1"
  shift
  local envs=()
  while [[ "$1" != -- ]]; do
    envs+=("$1")
    shift
  done
  shift
  echo "$TAG run $name"
  RC=0
  env ${envs[@]+"${envs[@]}"} bash "$TREE/scripts/upgrade_godot.sh" "$@" >"$WORK/$name.log" 2>&1 || RC=$?
  echo "$TAG   exit $RC (log: $WORK/$name.log)"
}

# Every file of the worktree, tracked or not (ignored build output aside), as one git tree id.
snapshot() {
  git -C "$TREE" add -A
  git -C "$TREE" write-tree
  git -C "$TREE" reset -q
}

added_list() {
  # justified: a bump that adds no class lists none; the comparison of the (empty) lists still runs.
  sed -n '/classes this Godot added/,/^\[upgrade_godot\] [^ ]/p' "$1" | grep '^    ' || true
}

fail() {
  echo "$TAG FAIL $*" >&2
  exit 1
}

if [[ $DRY_RUN -eq 1 ]]; then
  before="$(git -C "$TREE" status --porcelain)"
  run dry-stopped KANAMA_UPGRADE_FAIL_AFTER_STEP=5 -- "$@"
  [[ $RC -eq 1 ]] || fail "the stopped dry run exited $RC, expected 1"
  [[ "$(git -C "$TREE" status --porcelain)" == "$before" ]] || fail "the stopped dry run left changes: $(git -C "$TREE" status --short)"
  grep -q "restored: the tree is as the dry run found it" "$WORK/dry-stopped.log" || fail "the stopped dry run did not report its restore"
  run dry-after -- "$@"
  [[ $RC -eq 0 ]] || fail "the dry run after a stopped one exited $RC"
  echo "$TAG PASS a dry run stopped after step 5 restored the checkout, and the next dry run passed"
  exit 0
fi

run A -- "$@"
[[ $RC -eq 0 || $RC -eq 3 ]] || fail "the reference bump exited $RC (fix the bump first; log above)"
RC_A=$RC
TREE_A="$(snapshot)"
added_list "$WORK/A.log" >"$WORK/A.added"

bash "$TREE/scripts/upgrade_godot.sh" --reset >"$WORK/reset-0.log" 2>&1
run B-stopped KANAMA_UPGRADE_FAIL_AFTER_STEP=5 -- "$@"
[[ $RC -eq 1 ]] || fail "the bump stopped after step 5 exited $RC, expected 1"
bash "$TREE/scripts/upgrade_godot.sh" --reset >"$WORK/reset-B.log" 2>&1
# justified: grep -v exits 1 when only the pin files are left, which is what --reset must leave.
unexpected="$(git -C "$TREE" status --porcelain | grep -v '^??' | grep -vF "$(python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" --list-files)" || true)"
[[ -z "$unexpected" ]] || fail "--reset left tracked changes besides the pin files: $unexpected"
run B -- "$@"
[[ $RC -eq $RC_A ]] || fail "the re-run after --reset exited $RC, the reference $RC_A"
[[ "$(snapshot)" == "$TREE_A" ]] || fail "the re-run after --reset left a different tree than the reference"
added_list "$WORK/B.log" | cmp -s - "$WORK/A.added" || fail "the re-run after --reset lists other added classes"
echo "$TAG PASS stop after step 5, --reset, re-run: same exit ($RC), same tree, same added classes"

bash "$TREE/scripts/upgrade_godot.sh" --reset >"$WORK/reset-1.log" 2>&1
run C-stopped KANAMA_UPGRADE_FAIL_AFTER_STEP=6 -- "$@"
[[ $RC -eq 1 ]] || fail "the bump stopped after step 6 exited $RC, expected 1"
git -C "$TREE" add -A
git -C "$TREE" -c user.name=selftest -c user.email=selftest@invalid commit -q -m "half-done bump (self-test)"
run C -- --baseline-ref "$BASE" "$@"
[[ $RC -eq $RC_A ]] || fail "the re-run on the committed half-bump exited $RC, the reference $RC_A"
[[ "$(snapshot)" == "$TREE_A" ]] || fail "the re-run on the committed half-bump left a different tree than the reference"
added_list "$WORK/C.log" | cmp -s - "$WORK/A.added" || fail "the re-run on the committed half-bump lists other added classes"
echo "$TAG PASS stop after step 6, commit, re-run with --baseline-ref: same exit ($RC), same tree, same added classes"
echo "$TAG PASS $(wc -l <"$WORK/A.added" | tr -d ' ') added class(es) listed by every run"
echo "$TAG the reference run's summary:"
sed -n '/^\[upgrade_godot\] classes this Godot added/,/^\[upgrade_godot\] STOPPING/p' "$WORK/A.log" | grep -v '^    ' | sed 's/^/    /'
