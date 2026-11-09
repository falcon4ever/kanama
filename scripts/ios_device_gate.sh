#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
DEMOS_ROOT="${KANAMA_DEMOS_ROOT:-"$ROOT_DIR/../kanama-demos"}"
GODOT_BIN="${KANAMA_GODOT_BIN:-/Applications/Godot.app/Contents/MacOS/Godot}"
XCODE_DEVELOPER_DIR="${DEVELOPER_DIR:-/Applications/Xcode.app/Contents/Developer}"
OUTPUT_DIR=""
RUN_FRESH=1
RUN_DEMOS=1
ALLOW_PROVISIONING_UPDATES=0
PAUSE_SECONDS=8
BUILD_JOBS=1
CONSOLE_SECONDS=30
DEVICE_LABEL="${KANAMA_IOS_DEVICE_LABEL:-}"
START_AT=""
CHECK_RESULTS=""
CHECK_CLEANUP_PLAN=""
CLEAN_INSTALLED=1
GATE_BUNDLE_ID="${KANAMA_IOS_GATE_BUNDLE_ID:-net.multigesture.kanama.devicegate}"
# Task 111: the demos runner reads this for the launch stage (see its usage); default on.
export KANAMA_IOS_SMOKE_QUIT="${KANAMA_IOS_SMOKE_QUIT:-1}"

usage() {
  cat <<'EOF'
usage: scripts/ios_device_gate.sh [options]

Runs Kanama's public-safe iOS physical-device validation gate:

  1. Fresh starter project via createStarterProject -> installIosAddon -> export.
  2. The current nine-demo iOS matrix from the companion kanama-demos checkout.

Required environment:
  KANAMA_IOS_DEVICE=<device udid>
  KANAMA_IOS_TEAM=<Apple development team id>

Optional environment:
  KANAMA_DEMOS_ROOT=/path/to/kanama-demos
  KANAMA_GODOT_BIN=/Applications/Godot.app/Contents/MacOS/Godot
  KANAMA_IOS_DEVICE_LABEL="iPhone 15 Pro, iOS 26.5"
  KANAMA_IOS_GATE_BUNDLE_ID=net.multigesture.kanama.devicegate
  KANAMA_IOS_SMOKE_QUIT=1|0   Launch each demo with KANAMA_DEMO_SMOKE_QUIT=1 so its smoke script (the kotlin-src/Smoke*.kt that reads it)
                              (spawn / damage / free / quit) runs on the phone inside the console window;
                              the step then also requires the smoke's completion line (task 111).
                              Default 1; set 0 for a launch-only matrix.
  DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer

Options:
  --godot BIN                  Godot editor binary.
  --demos-root DIR             Companion kanama-demos checkout.
  --output-dir DIR             Gate output/log directory. Defaults to /tmp.
  --device-label TEXT          Public-safe device model/iOS label for summary.
  --gate-bundle-id ID          Reusable bundle ID for copied demo exports.
                               Defaults to KANAMA_IOS_GATE_BUNDLE_ID or
                               net.multigesture.kanama.devicegate.
  --allow-provisioning-updates Allow Xcode automatic provisioning in the fresh
                               starter smoke. Demo runs already use it.
  --fresh-only                 Run only the fresh starter-project path.
  --demos-only                 Run only the nine-demo matrix.
  --start-at NAME              Start the demo matrix at NAME. Useful for
                               resuming after an interrupted run.
  --keep-installed             Do not uninstall older Kanama gate apps. By
                               default each step installs its app first and
                               then removes the other known Kanama apps (before
                               it only to stay within a free account's three
                               apps), so the phone always keeps one and with it
                               the trust in the developer.
  --pause-seconds N            Warning countdown before each device launch.
  --build-jobs N               Build the demo apps N at a time (default 1). Each job builds in
                               its own throwaway worktree of this repo at HEAD (the tree must be
                               clean), because installIosAddon writes into the repo's build
                               directories; the device steps still run one demo at a time, in
                               order. Needs a demos checkout whose ios_device_run.sh supports
                               KANAMA_IOS_RUN_STAGE. The native link of each demo runs on one
                               core, so N=3 or 4 on a 10-core Mac cuts the run roughly N-fold.
  --console-seconds N          After launching each demo, stream the device console for N
                               seconds and FAIL the step on a crash signature (task 105; default
                               30, 0 = launch-only as before). Needs a demos checkout whose
                               ios_device_run.sh supports KANAMA_IOS_CONSOLE_SECONDS. The log is
                               kept as <output-dir>/<App>.console.log. Costs 9 x N seconds per run.
  --check-results FILE         Only apply the gate's verdict (no FAIL row; exactly one row per planned step,
                               SKIP for a demo before --start-at, PASS for the rest) to a saved results.tsv
                               and exit; with --fresh-only / --demos-only / --start-at it judges that plan
                               (an unknown --start-at exits 2, as a run does). Needs no device or
                               environment (the verdict's red runs, task 138 item 10).
  --check-cleanup-plan FILE    Only replay the gate's app cleanup over a saved
                               `devicectl device info apps --json-output` listing
                               and exit: FAIL when a step would leave the phone
                               with no Kanama app or more than three developer
                               apps. No device.
  --help, -h                   Show this help.

The script reads private device/signing values only from the environment. Do not
copy raw logs, device UDIDs, team IDs, or local paths into public docs.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --godot)
      GODOT_BIN="${2:-}"
      shift 2
      ;;
    --demos-root)
      DEMOS_ROOT="${2:-}"
      shift 2
      ;;
    --output-dir)
      OUTPUT_DIR="${2:-}"
      shift 2
      ;;
    --device-label)
      DEVICE_LABEL="${2:-}"
      shift 2
      ;;
    --gate-bundle-id)
      GATE_BUNDLE_ID="${2:-}"
      shift 2
      ;;
    --allow-provisioning-updates)
      ALLOW_PROVISIONING_UPDATES=1
      shift
      ;;
    --fresh-only)
      RUN_FRESH=1
      RUN_DEMOS=0
      shift
      ;;
    --demos-only)
      RUN_FRESH=0
      RUN_DEMOS=1
      shift
      ;;
    --start-at)
      START_AT="${2:-}"
      shift 2
      ;;
    --keep-installed)
      CLEAN_INSTALLED=0
      shift
      ;;
    --pause-seconds)
      PAUSE_SECONDS="${2:-}"
      shift 2
      ;;
    --build-jobs)
      BUILD_JOBS="${2:-}"
      shift 2
      ;;
    --console-seconds)
      CONSOLE_SECONDS="${2:-}"
      shift 2
      ;;
    --check-results)
      CHECK_RESULTS="${2:-}"
      shift 2
      ;;
    --check-cleanup-plan)
      CHECK_CLEANUP_PLAN="${2:-}"
      shift 2
      ;;
    --help|-h)
      usage
      exit 0
      ;;
    --*)
      echo "[ios_device_gate] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      echo "[ios_device_gate] unexpected argument: $1" >&2
      usage
      exit 2
      ;;
  esac
done

demo_names=(
  "Bunnymark"
  "Starter-Kit-Match3"
  "Starter-Kit-3D-Platformer"
  "godot-demo-2d-dodge-the-creeps"
  "godot-demo-3d-squash-the-creeps"
  "godot-4-3d-character-controller"
  "Starter-Kit-Racing"
  "Starter-Kit-FPS"
  "godot-4-3d-third-person-controller"
)
demo_dirs=(
  "Bunnymark"
  "Starter-Kit-Match3"
  "Starter-Kit-3D-Platformer"
  "godot-demo-2d-dodge-the-creeps"
  "godot-demo-3d-squash-the-creeps"
  "godot-4-3d-character-controller-tutorial"
  "Starter-Kit-Racing"
  "Starter-Kit-FPS"
  "godot-4-3d-third-person-controller"
)
demo_bundles=(
  "net.multigesture.kanama.bunnymark"
  "net.multigesture.kanama.match3"
  "net.multigesture.kanama.platformer3d"
  "net.multigesture.kanama.dodge"
  "net.multigesture.kanama.squash3d"
  "net.multigesture.kanama.charactercontroller"
  "net.multigesture.kanama.racing"
  "net.multigesture.kanama.fps"
  "net.multigesture.kanama.thirdperson"
)
fresh_bundle="net.multigesture.kanama.iosvisualsmoke"
demo_apps=(
  "KanamaBunnymark"
  "KanamaMatch3"
  "KanamaPlatformer3D"
  "KanamaDodge"
  "KanamaSquash3D"
  "KanamaCharacterController"
  "KanamaRacing"
  "KanamaFPS"
  "KanamaThirdPerson"
)

# Index of the --start-at demo (matched by name, directory or app), 0 without one; prints nothing and returns 1
# when START_AT names no demo.
start_at_index() {
  local idx
  if [[ -z "$START_AT" ]]; then
    echo 0
    return 0
  fi
  for idx in "${!demo_names[@]}"; do
    if [[ "$START_AT" == "${demo_names[$idx]}" || "$START_AT" == "${demo_dirs[$idx]}" || "$START_AT" == "${demo_apps[$idx]}" ]]; then
      echo "$idx"
      return 0
    fi
  done
  return 1
}

# The gate's verdict over a results.tsv: no FAIL row is not enough (task 118) -- every planned step must have a row
# (a step that never ran leaves none, and the old check printed PASS for the rows that did). Held per step name, not
# by a row count: a --start-at run writes a SKIP row for every demo before the start demo, so its results.tsv has a
# row for the whole matrix whether or not the demos it ran all finished. Each planned step must have exactly one row:
# SKIP for a demo before the start demo, PASS or FAIL for a step the run executed; and no row may name a step the plan
# does not have. Returns 2 when --start-at names no demo (as a real run exits), 1 on a red verdict.
results_verdict() {
  local tsv="$1" start_idx idx name want rows count status problems=0
  local -a plan_names=() plan_want=()
  if ! start_idx="$(start_at_index)"; then
    echo "[ios_device_gate] --start-at did not match any demo: $START_AT" >&2
    return 2
  fi
  if grep -Eq $'\tFAIL\t' "$tsv"; then
    echo "[ios_device_gate] one or more gate steps failed; summary: ${SUMMARY:-$tsv}" >&2
    return 1
  fi
  if [[ "$RUN_FRESH" -eq 1 ]]; then
    plan_names+=("fresh-starter-project")
    plan_want+=("run")
  fi
  if [[ "$RUN_DEMOS" -eq 1 ]]; then
    for idx in "${!demo_names[@]}"; do
      plan_names+=("${demo_names[$idx]}")
      if [[ "$idx" -lt "$start_idx" ]]; then plan_want+=("skip"); else plan_want+=("run"); fi
    done
  fi
  for idx in "${!plan_names[@]}"; do
    name="${plan_names[$idx]}"
    want="${plan_want[$idx]}"
    if ! rows="$(awk -F'\t' -v n="$name" 'NR > 1 && $1 == n { c++; s = s " " $2 } END { printf "%d%s", c, s }' "$tsv")"; then
      echo "[ios_device_gate] could not read $tsv" >&2
      return 1
    fi
    count="${rows%% *}"
    status="${rows#"$count"}"
    status="${status# }"
    if [[ "$count" -eq 0 ]]; then
      echo "[ios_device_gate] no result row for the planned step '$name'; a step did not run" >&2
      problems=$((problems + 1))
    elif [[ "$count" -ne 1 ]]; then
      echo "[ios_device_gate] $count result rows for the step '$name' ($status), expected exactly one" >&2
      problems=$((problems + 1))
    elif [[ "$want" == "skip" && "$status" != "SKIP" ]]; then
      echo "[ios_device_gate] the step '$name' comes before --start-at $START_AT but its row is $status, not SKIP" >&2
      problems=$((problems + 1))
    elif [[ "$want" == "run" && "$status" != "PASS" ]]; then
      echo "[ios_device_gate] the planned step '$name' has a $status row, not PASS; a step did not run" >&2
      problems=$((problems + 1))
    fi
  done
  # A row the plan does not name (another matrix, a renamed demo) would otherwise pass unnoticed.
  local unplanned
  if ! unplanned="$(printf '%s\n' ${plan_names[@]+"${plan_names[@]}"} |
      awk -F'\t' 'FNR == NR { known[$0] = 1; next } FNR > 1 && !($1 in known) { print $1 }' - "$tsv")"; then
    echo "[ios_device_gate] could not read $tsv" >&2
    return 1
  fi
  if [[ -n "$unplanned" ]]; then
    echo "[ios_device_gate] result row(s) for a step this plan does not have: $(printf '%s' "$unplanned" | tr '\n' ' ')" >&2
    problems=$((problems + 1))
  fi
  if [[ "$problems" -ne 0 ]]; then
    echo "[ios_device_gate] results verdict: $problems problem(s) in $tsv (fresh=$RUN_FRESH demos=$RUN_DEMOS start-at=${START_AT:-none})" >&2
    return 1
  fi
}

# Which apps the gate removes, and when. With a free Apple account (the usual way to run this gate) iOS trusts the
# developer only while at least one app that developer signed is installed: when the last one leaves the phone the
# trust goes with it, and every later launch is refused ("its profile has not been explicitly trusted by the user")
# until someone taps Settings > General > VPN & Device Management on the phone. The account also allows at most
# three apps from its team on one device; other developers' apps do not use those slots. The gate used to uninstall
# every known Kanama app before each step, which left the phone with none between the uninstall and the install.
# The order now, for a step that installs KEEP:
#   before  only to make room: when KEEP is not installed yet and the phone already holds IOS_FREE_APP_LIMIT apps of
#           this team, remove other known Kanama apps until one fewer remain, but never the last Kanama app. When
#           that is not enough (the team's other apps fill the slots), warn: the install will be refused, and only
#           a person can say which of those apps may go. A reinstall of KEEP needs no room.
#   after   once KEEP is on the phone, remove the other known Kanama apps; KEEP stays. When the step did not install
#           KEEP (a failed build), leave everything as it is.
# Only bundles the gate knows (the starter, the demos, the gate id) are ever removed. Which apps are this team's:
# a listing that names each app's team (`teamIdentifier`) is compared with KANAMA_IOS_TEAM; devicectl's listing
# names none (Xcode 27), and then the known Kanama bundles are this team's and every other app another team's.
# The apps are passed as `<bundle id>@ours` / `<bundle id>@other` (apps_from_listing). Prints the bundle ids to
# uninstall, one per line, in order; the warning goes to stderr.
IOS_FREE_APP_LIMIT=3
all_gate_bundles=("$fresh_bundle" "${demo_bundles[@]}" "$GATE_BUNDLE_ID")

is_gate_bundle() {
  local candidate="$1" bundle_id
  for bundle_id in "${all_gate_bundles[@]}"; do
    [[ "$candidate" == "$bundle_id" ]] && return 0
  done
  return 1
}

cleanup_plan() {
  local phase="$1" keep="$2"
  shift 2
  local count=0 keep_installed=0 app bundle_id
  local -a removable=()
  for app in "$@"; do
    bundle_id="${app%@*}"
    [[ "${app##*@}" == "ours" ]] && count=$((count + 1))
    if [[ "$bundle_id" == "$keep" ]]; then
      keep_installed=1
    elif [[ "${app##*@}" == "ours" ]] && is_gate_bundle "$bundle_id"; then
      removable+=("$bundle_id")
    fi
  done
  case "$phase" in
    before)
      [[ "$keep_installed" -eq 1 ]] && return 0
      local kanama=${#removable[@]}
      for bundle_id in ${removable[@]+"${removable[@]}"}; do
        # Never the last Kanama app: the trust leaves with it.
        if [[ "$count" -lt "$IOS_FREE_APP_LIMIT" || "$kanama" -le 1 ]]; then
          break
        fi
        echo "$bundle_id"
        count=$((count - 1))
        kanama=$((kanama - 1))
      done
      if [[ "$count" -ge "$IOS_FREE_APP_LIMIT" ]]; then
        echo "[ios_device_gate] warning: cannot make room for $keep: the phone holds $count apps from this team (the free account's limit is $IOS_FREE_APP_LIMIT) and the gate keeps the last Kanama app, so the install will be refused. Remove one of this team's other apps from the phone by hand, then re-run." >&2
      fi
      ;;
    after)
      # The step did not install KEEP (a failed build): leave every app where it is.
      [[ "$keep_installed" -eq 1 ]] || return 0  # justified: removing nothing never costs the trust; the step's own row is the verdict
      for bundle_id in ${removable[@]+"${removable[@]}"}; do
        echo "$bundle_id"
      done
      ;;
  esac
}

# The developer apps in a `devicectl device info apps --json-output` file, one `<bundle id>@ours|other` per line
# (see cleanup_plan for which apps are this team's). TEAM is KANAMA_IOS_TEAM, empty when unset.
apps_from_listing() {
  python3 - "$1" "${KANAMA_IOS_TEAM:-${KANAMA_IOS_DEVELOPMENT_TEAM:-}}" "${all_gate_bundles[@]}" <<'PY'
import json
import sys

listing, team, known = sys.argv[1], sys.argv[2], set(sys.argv[3:])
with open(listing, encoding="utf-8") as handle:
    apps = json.load(handle)["result"]["apps"]
for app in apps:
    bundle = app.get("bundleIdentifier")
    if not bundle or not app.get("builtByDeveloper", True):
        continue  # justified: an app with no bundle id, or one no developer installed, takes no slot of the free account
    app_team = app.get("teamIdentifier")
    ours = (app_team == team) if (app_team and team) else bundle in known
    print(f"{bundle}@{'ours' if ours else 'other'}")
PY
}

# `--check-cleanup-plan APPS_JSON` (no device): replays a whole gate run over a saved `devicectl device info apps`
# listing with the plan above (the fresh starter, then every demo under the gate id), once with every step installing
# its app and once with every step failing before its install. It fails when a step would leave the phone with no
# Kanama app of this team, when an install would pass the team's limit although the plan could have made room, and
# when the plan's "cannot make room" warning is missing where room cannot be made or printed where it can. An
# install the phone has no room for is refused there too, so the replay then goes on without it.
cleanup_plan_verdict() {
  local listing="$1" initial outcome keep app bundle_id step_problems=0 warnings
  local -a phone=() next=() steps=("$fresh_bundle")
  if ! initial="$(apps_from_listing "$listing")"; then
    echo "[ios_device_gate] could not read the app listing $listing" >&2
    return 1
  fi
  warnings="$(mktemp "${TMPDIR:-/tmp}/kanama_ios_plan.XXXXXX")"
  for bundle_id in "${demo_bundles[@]}"; do steps+=("$GATE_BUNDLE_ID"); done
  for outcome in installed failed; do
    phone=()
    for app in $initial; do phone+=("$app"); done
    if [[ "$(ours_kanama_count ${phone[@]+"${phone[@]}"})" -eq 0 ]]; then
      echo "[ios_device_gate] the listing $listing holds no Kanama app of this team; the plan check needs at least one" >&2
      rm -f "$warnings"
      return 1
    fi
    for keep in "${steps[@]}"; do
      local phase
      for phase in before install after; do
        if [[ "$phase" == "install" ]]; then
          if [[ "$outcome" == "installed" ]] && ! printf '%s\n' ${phone[@]+"${phone[@]}"} | grep -qxF "$keep@ours"; then
            local ours kanama_others
            ours="$(printf '%s\n' ${phone[@]+"${phone[@]}"} | grep -c '@ours$' || true)"  # justified: grep -c prints 0 and exits 1 when no line matches
            kanama_others="$(ours_kanama_count ${phone[@]+"${phone[@]}"})"
            if [[ "$ours" -lt "$IOS_FREE_APP_LIMIT" ]]; then
              phone+=("$keep@ours")
            elif [[ "$kanama_others" -gt 1 ]]; then
              echo "[ios_device_gate] the install of $keep would make $((ours + 1)) apps from this team ($outcome steps), more than the free account's $IOS_FREE_APP_LIMIT, although the plan could have removed another Kanama app first: the install is refused" >&2
              step_problems=$((step_problems + 1))
              break 2
            fi  # else: no room the gate may make; iOS refuses the install, and the plan must have said so (below)
          fi
        else
          local removals
          removals="$(cleanup_plan "$phase" "$keep" ${phone[@]+"${phone[@]}"} 2>"$warnings")"
          if [[ "$phase" == "before" ]]; then
            local need_warning=0
            if ! printf '%s\n' ${phone[@]+"${phone[@]}"} | grep -qxF "$keep@ours" &&
               [[ "$(printf '%s\n' ${phone[@]+"${phone[@]}"} | grep -c '@ours$' || true)" -ge "$IOS_FREE_APP_LIMIT" ]] &&  # justified: grep -c prints 0 and exits 1 when no line matches
               [[ "$(ours_kanama_count ${phone[@]+"${phone[@]}"})" -le 1 ]]; then
              need_warning=1
            fi
          fi
          next=()
          for app in ${phone[@]+"${phone[@]}"}; do
            printf '%s\n' "$removals" | grep -qxF "${app%@*}" || next+=("$app")
          done
          phone=(${next[@]+"${next[@]}"})
        fi
        if [[ "$(ours_kanama_count ${phone[@]+"${phone[@]}"})" -eq 0 ]]; then
          echo "[ios_device_gate] the cleanup would leave the phone with no Kanama app ($outcome steps, $phase $keep): iOS then drops the trust in the developer and refuses every launch" >&2
          step_problems=$((step_problems + 1))
          break 2
        fi
        # Judged after the removals, so a plan that removes the last Kanama app is named for that first.
        if [[ "$phase" == "before" ]]; then
          if [[ "$need_warning" -eq 1 ]] && ! grep -q 'cannot make room' "$warnings"; then
            echo "[ios_device_gate] no 'cannot make room' warning for $keep ($outcome steps) although this team's other apps fill the phone: the install fails with no hint of what to remove" >&2
            step_problems=$((step_problems + 1))
            break 2
          fi
          if [[ "$need_warning" -eq 0 ]] && grep -q 'cannot make room' "$warnings"; then
            echo "[ios_device_gate] a 'cannot make room' warning for $keep ($outcome steps) although the phone has room for it or the gate can make some: only this team's apps count toward the limit" >&2
            step_problems=$((step_problems + 1))
            break 2
          fi
        fi
      done
    done
  done
  rm -f "$warnings"
  [[ "$step_problems" -eq 0 ]]
}

# How many of the given `<bundle id>@ours|other` apps are known Kanama bundles of this team.
ours_kanama_count() {
  local app n=0
  for app in "$@"; do
    if [[ "${app##*@}" == "ours" ]] && is_gate_bundle "${app%@*}"; then n=$((n + 1)); fi
  done
  echo "$n"
}

if [[ -n "$CHECK_CLEANUP_PLAN" ]]; then
  if [[ ! -f "$CHECK_CLEANUP_PLAN" ]]; then
    echo "[ios_device_gate] --check-cleanup-plan: no such file: $CHECK_CLEANUP_PLAN" >&2
    exit 2
  fi
  cleanup_plan_verdict "$CHECK_CLEANUP_PLAN" || exit 1
  echo "[ios_device_gate] cleanup plan: PASS (a Kanama app stays on the phone, room is made whenever the gate may make it, and a full phone is named; $CHECK_CLEANUP_PLAN)"
  exit 0
fi

if [[ -n "$CHECK_RESULTS" ]]; then
  if [[ ! -f "$CHECK_RESULTS" ]]; then
    echo "[ios_device_gate] --check-results: no such file: $CHECK_RESULTS" >&2
    exit 2
  fi
  if [[ -n "$START_AT" && "$RUN_DEMOS" -ne 1 ]]; then
    echo "[ios_device_gate] --start-at only applies when running the demo matrix." >&2
    exit 2
  fi
  verdict_status=0
  results_verdict "$CHECK_RESULTS" || verdict_status=$?
  if [[ "$verdict_status" -ne 0 ]]; then
    exit "$verdict_status"
  fi
  echo "[ios_device_gate] results verdict: PASS ($CHECK_RESULTS)"
  exit 0
fi

DEVICE_ID="${KANAMA_IOS_DEVICE:-}"
DEVELOPMENT_TEAM="${KANAMA_IOS_TEAM:-${KANAMA_IOS_DEVELOPMENT_TEAM:-}}"

if [[ -z "$DEVICE_ID" ]]; then
  echo "[ios_device_gate] KANAMA_IOS_DEVICE is required." >&2
  exit 2
fi
if [[ -z "$DEVELOPMENT_TEAM" ]]; then
  echo "[ios_device_gate] KANAMA_IOS_TEAM or KANAMA_IOS_DEVELOPMENT_TEAM is required." >&2
  exit 2
fi
if [[ ! -x "$GODOT_BIN" ]]; then
  echo "[ios_device_gate] Godot binary is not executable: $GODOT_BIN" >&2
  exit 2
fi
if [[ ! -d "$XCODE_DEVELOPER_DIR" ]]; then
  echo "[ios_device_gate] Xcode Developer dir does not exist: $XCODE_DEVELOPER_DIR" >&2
  exit 2
fi
if [[ "$RUN_DEMOS" -eq 1 && ! -x "$DEMOS_ROOT/scripts/ios_device_run.sh" ]]; then
  echo "[ios_device_gate] demo runner is not executable: $DEMOS_ROOT/scripts/ios_device_run.sh" >&2
  exit 2
fi
if [[ -n "$START_AT" && "$RUN_DEMOS" -ne 1 ]]; then
  echo "[ios_device_gate] --start-at only applies when running the demo matrix." >&2
  exit 2
fi
if ! [[ "$PAUSE_SECONDS" =~ ^[0-9]+$ ]]; then
  echo "[ios_device_gate] --pause-seconds must be a non-negative integer." >&2
  exit 2
fi
if ! [[ "$BUILD_JOBS" =~ ^[1-9][0-9]*$ ]]; then
  echo "[ios_device_gate] --build-jobs must be a positive integer." >&2
  exit 2
fi
if [[ ! "$CONSOLE_SECONDS" =~ ^[0-9]+$ ]]; then
  echo "[ios_device_gate] --console-seconds must be a non-negative integer." >&2
  exit 2
fi
if [[ "$CONSOLE_SECONDS" -gt 0 && "$RUN_DEMOS" -eq 1 ]]; then
  if ! grep -q "KANAMA_IOS_CONSOLE_SECONDS" "$DEMOS_ROOT/scripts/ios_device_run.sh"; then
    echo "[ios_device_gate] --console-seconds > 0 needs a demos checkout whose scripts/ios_device_run.sh supports KANAMA_IOS_CONSOLE_SECONDS (pass --console-seconds 0 for the launch-only behaviour)." >&2
    exit 2
  fi
fi
# Task 138 item 24: the runtime self-tests are compiled only into a -PkanamaIosSelfTest=true build. A demos
# runner that does not pass it builds apps without them, and its console check would have nothing to verify. The
# match is the argument itself: a continuation line holding only it, or a gradlew command line outside quotes. The
# runner's comments and messages name the flag too, so a plain grep would accept a runner that only mentions it.
if [[ "$RUN_DEMOS" -eq 1 ]] &&
   ! grep -Eq -- '^[[:space:]]*-PkanamaIosSelfTest=true[[:space:]]*\\?$|^[^#"'"'"']*gradlew[^#"'"'"']*[[:space:]]-PkanamaIosSelfTest=true([[:space:]]|$)' \
     "$DEMOS_ROOT/scripts/ios_device_run.sh"; then
  echo "[ios_device_gate] the demos checkout's scripts/ios_device_run.sh does not build the runtime self-tests (-PkanamaIosSelfTest=true, kanama task 138 item 24); update kanama-demos." >&2
  exit 2
fi
# The generated API exercise prints its own summary after the frame-1 rows; a demos runner that does not
# require it would pass a build whose exercise never ran.
if [[ "$RUN_DEMOS" -eq 1 ]] && ! grep -q 'api exercise' "$DEMOS_ROOT/scripts/ios_device_run.sh"; then
  echo "[ios_device_gate] the demos checkout's scripts/ios_device_run.sh does not require the API exercise summary ('OBJECTCALLS SELFTEST (api exercise)'); update kanama-demos." >&2
  exit 2
fi
if [[ "$BUILD_JOBS" -gt 1 && "$RUN_DEMOS" -eq 1 ]]; then
  if ! grep -q "KANAMA_IOS_RUN_STAGE" "$DEMOS_ROOT/scripts/ios_device_run.sh"; then
    echo "[ios_device_gate] --build-jobs > 1 needs a demos checkout whose scripts/ios_device_run.sh supports KANAMA_IOS_RUN_STAGE." >&2
    exit 2
  fi
  if [[ -n "$(git -C "$ROOT_DIR" status --porcelain)" ]]; then
    echo "[ios_device_gate] --build-jobs > 1 builds in worktrees of HEAD; commit or stash the working tree first (it is dirty)." >&2
    exit 2
  fi
fi

if [[ -z "$OUTPUT_DIR" ]]; then
  OUTPUT_DIR="$(mktemp -d "${TMPDIR:-/tmp}/kanama_ios_device_gate.XXXXXX")"
else
  mkdir -p "$OUTPUT_DIR"
  OUTPUT_DIR="$(cd "$OUTPUT_DIR" && pwd)"
fi

SUMMARY="$OUTPUT_DIR/summary.md"
RESULTS_TSV="$OUTPUT_DIR/results.tsv"
RUN_STARTED="$(date -u '+%Y-%m-%dT%H:%M:%SZ')"

warn_before_launch() {
  local label="$1"
  if [[ "$PAUSE_SECONDS" -gt 0 ]]; then
    echo "[ios_device_gate] About to build/install/launch '$label' on the connected iPhone."
    echo "[ios_device_gate] Unlock the device and keep it awake. Continuing in ${PAUSE_SECONDS}s..."
    sleep "$PAUSE_SECONDS"
  fi
}

# A launch refused because the phone does not trust the developer refuses every later step too, until someone
# re-trusts it on the phone: stop the matrix instead of building every demo to the same refusal. The step's own log
# carries the message (ios_visual_smoke.sh and the demos runner print "VPN & Device Management" with what to tap).
stop_on_untrusted_developer() {
  local log_path="$1"
  if [[ -f "$log_path" ]] && grep -q 'VPN & Device Management' "$log_path"; then
    echo "[ios_device_gate] iOS refused the launch, most likely because the phone does not trust the developer. On the phone open Settings > General > VPN & Device Management, trust the Apple Development entry, then re-run (--start-at resumes the matrix). Stopping here; see $log_path" >&2
    exit 1
  fi
}

# Task 105: the runner writes the demo's device console to <output-dir>/<App>/console.log; keep a
# copy beside the step log so the summary's log column and the crash evidence sit together.
keep_console_log() {
  local app="$1"
  if [[ -f "$OUTPUT_DIR/$app/console.log" ]]; then
    cp "$OUTPUT_DIR/$app/console.log" "$OUTPUT_DIR/$app.console.log"
  fi
}

append_result() {
  local name="$1"
  local status="$2"
  local elapsed="$3"
  local log_path="$4"
  printf '%s\t%s\t%s\t%s\n' "$name" "$status" "$elapsed" "$log_path" >>"$RESULTS_TSV"
}

uninstall_bundle() {
  local bundle_id="$1"
  echo "[ios_device_gate] uninstalling $bundle_id"
  DEVELOPER_DIR="$XCODE_DEVELOPER_DIR" xcrun devicectl device uninstall app \
    --device "$DEVICE_ID" \
    "$bundle_id" >/dev/null 2>&1 || true  # justified: best-effort tidying; the next install and launch are the checks
}

# The developer-built apps on the phone, one `<bundle id>@ours|other` per line, from `devicectl device info apps` (it
# lists only apps a developer installed). Returns 1 when the phone cannot be listed.
installed_developer_apps() {
  local listing
  listing="$(mktemp "${TMPDIR:-/tmp}/kanama_ios_apps.XXXXXX")"
  if ! DEVELOPER_DIR="$XCODE_DEVELOPER_DIR" xcrun devicectl device info apps --device "$DEVICE_ID" \
       --json-output "$listing" >/dev/null 2>&1; then  # justified: the status is the answer; the caller warns and skips the cleanup
    rm -f "$listing"
    return 1
  fi
  local status=0
  apps_from_listing "$listing" || status=$?
  rm -f "$listing"
  return "$status"
}

# Remove or keep apps around a step: run `cleanup_plan before|after KEEP` and uninstall what it names. Skipped with
# --keep-installed, and (with a warning) when the phone cannot be listed: removing nothing never costs the trust.
tidy_gate_apps() {
  local phase="$1" keep="$2" installed bundle_id
  [[ "$CLEAN_INSTALLED" -eq 1 ]] || return 0  # justified: --keep-installed asks for no cleanup; the steps' rows are the verdict
  if ! installed="$(installed_developer_apps)"; then
    echo "[ios_device_gate] warning: could not list the apps on the phone; leaving them as they are ($phase $keep)" >&2
    return 0
  fi
  # shellcheck disable=SC2086  # one app per line, no spaces
  for bundle_id in $(cleanup_plan "$phase" "$keep" $installed); do
    uninstall_bundle "$bundle_id"
  done
}

prepare_demo_copy() {
  local source_dir="$1"
  local app_name="$2"
  local target_dir="$OUTPUT_DIR/project-copies/$app_name"
  rm -rf "$target_dir"
  mkdir -p "$(dirname "$target_dir")"
  cp -R "$source_dir" "$target_dir"
  if [[ -f "$target_dir/export_presets.cfg" ]]; then
    perl -0pi -e "s/application\\/bundle_identifier=\"[^\"]+\"/application\\/bundle_identifier=\"$GATE_BUNDLE_ID\"/g" \
      "$target_dir/export_presets.cfg"
  fi
  printf '%s\n' "$target_dir"
}

run_step() {
  local name="$1"
  local log_path="$2"
  shift 2

  warn_before_launch "$name"
  local start
  start="$(date +%s)"
  echo "[ios_device_gate] START $name"
  if "$@" >"$log_path" 2>&1; then
    local end
    end="$(date +%s)"
    local elapsed=$((end - start))
    echo "[ios_device_gate] PASS $name (${elapsed}s)"
    append_result "$name" "PASS" "$elapsed" "$log_path"
  else
    local exit_code=$?
    local end
    end="$(date +%s)"
    local elapsed=$((end - start))
    echo "[ios_device_gate] FAIL $name (${elapsed}s); see $log_path" >&2
    append_result "$name" "FAIL" "$elapsed" "$log_path"
    return "$exit_code"
  fi
}

cat >"$RESULTS_TSV" <<'EOF'
name	status	elapsed_seconds	log
EOF

write_summary() {
  {
    echo "# iOS Device Gate Summary"
    echo
    echo "- Started: $RUN_STARTED"
    if [[ -n "$DEVICE_LABEL" ]]; then
      echo "- Device: $DEVICE_LABEL"
    else
      echo "- Device: not recorded; rerun with --device-label for public docs"
    fi
    echo "- Godot: $GODOT_BIN"
    echo "- Output: $OUTPUT_DIR"
    echo "- Demo console window: ${CONSOLE_SECONDS}s per demo step (0 = launch-only; task 105)"
    echo
    echo "| Path | Status | Gate elapsed | Log |"
    echo "|---|---:|---:|---|"
    tail -n +2 "$RESULTS_TSV" | while IFS=$'\t' read -r name status elapsed log_path; do
      echo "| $name | $status | ${elapsed}s | $log_path |"
    done
  } >"$SUMMARY"
}

# Job worktrees of the parallel build phase (see run_demo_matrix_parallel). Removed on EXIT as
# well as after the build phase, so a killed or aborted run never leaves them registered: git
# refuses to re-add a path that is "missing but already registered", which would abort the next
# run's build phase right after its fresh-starter step.
PARALLEL_JOB_ROOTS=()
cleanup_job_worktrees() {
  local root
  for root in ${PARALLEL_JOB_ROOTS[@]+"${PARALLEL_JOB_ROOTS[@]}"}; do
    # justified: a throwaway worktree; if git cannot remove it the directory is deleted instead.
    git -C "$ROOT_DIR" worktree remove --force "$root" >/dev/null 2>&1 || rm -rf "$root"
  done
  PARALLEL_JOB_ROOTS=()
  # justified: housekeeping of throwaway worktrees; a stale registration is cleared again before the next build phase.
  git -C "$ROOT_DIR" worktree prune >/dev/null 2>&1 || true
}

# Always emit the summary, even if a step fails or the run is interrupted, so the
# partial pass/fail matrix is recoverable for the baselines table.
trap 'cleanup_job_worktrees; write_summary' EXIT

export KANAMA_IOS_DEVICE="$DEVICE_ID"
# Task 105: the demo steps stream the device console for this long and fail on a crash signature.
export KANAMA_IOS_CONSOLE_SECONDS="$CONSOLE_SECONDS"
export KANAMA_IOS_TEAM="$DEVELOPMENT_TEAM"
export DEVELOPER_DIR="$XCODE_DEVELOPER_DIR"
export KANAMA_ROOT="$ROOT_DIR"

echo "[ios_device_gate] output: $OUTPUT_DIR"
echo "[ios_device_gate] started: $RUN_STARTED"
if [[ -n "$DEVICE_LABEL" ]]; then
  echo "[ios_device_gate] public device label: $DEVICE_LABEL"
fi
echo "[ios_device_gate] private device/team values are read from environment only."

if [[ "$RUN_FRESH" -eq 1 ]]; then
  tidy_gate_apps before "$fresh_bundle"
  fresh_args=(
    "$ROOT_DIR/scripts/ios_visual_smoke.sh"
    --godot "$GODOT_BIN"
    --physical-device
    --kanama-user-script-probe
    --work-dir "$OUTPUT_DIR/fresh-starter"
  )
  if [[ "$ALLOW_PROVISIONING_UPDATES" -eq 1 ]]; then
    fresh_args+=(--allow-provisioning-updates)
  fi
  # justified: run_step records the FAIL itself; keep going so the demo matrix still runs
  # and the final aggregation below decides the overall exit status.
  run_step "fresh-starter-project" "$OUTPUT_DIR/fresh-starter.log" "${fresh_args[@]}" || true
  tidy_gate_apps after "$fresh_bundle"
  stop_on_untrusted_developer "$OUTPUT_DIR/fresh-starter.log"
fi

# Demo matrix, serial (the original path): build + install + launch per demo, in order.
run_demo_matrix_serial() {
  local i
  for i in "${!demo_names[@]}"; do
    if [[ "$start_found" -eq 0 ]]; then
      if [[ "$START_AT" == "${demo_names[$i]}" || "$START_AT" == "${demo_dirs[$i]}" || "$START_AT" == "${demo_apps[$i]}" ]]; then
        start_found=1
      else
        append_result "${demo_names[$i]}" "SKIP" "0" "resumed after this demo"
        # justified: this demo was already recorded as a SKIP row (resumed after the --start-at demo).
        continue
      fi
    fi
    demo_path="$DEMOS_ROOT/${demo_dirs[$i]}"
    if [[ ! -f "$demo_path/project.godot" ]]; then
      echo "[ios_device_gate] Missing demo project: $demo_path" >&2
      append_result "${demo_names[$i]}" "FAIL" "0" "missing project.godot"
      exit 1
    fi
    demo_run_path="$(prepare_demo_copy "$demo_path" "${demo_apps[$i]}")"
    tidy_gate_apps before "$GATE_BUNDLE_ID"
    # justified: run_step records the FAIL itself; the final aggregation below decides the exit status.
    run_step \
      "${demo_names[$i]}" \
      "$OUTPUT_DIR/${demo_apps[$i]}.log" \
      "$DEMOS_ROOT/scripts/ios_device_run.sh" \
      "$GODOT_BIN" \
      "$demo_run_path" \
      "$GATE_BUNDLE_ID" \
      "${demo_apps[$i]}" \
      "$OUTPUT_DIR/${demo_apps[$i]}" || true  # justified: run_step already recorded the FAIL row
    keep_console_log "${demo_apps[$i]}"
    tidy_gate_apps after "$GATE_BUNDLE_ID"
    stop_on_untrusted_developer "$OUTPUT_DIR/${demo_apps[$i]}.log"
  done
}

# Demo matrix, parallel builds: phase 1 builds the apps BUILD_JOBS at a time, each job in its own
# worktree of this repo at HEAD (installIosAddon writes into the repo's build directories, so two
# builds cannot share one checkout); phase 2 installs and launches one demo at a time, in the
# original order, exactly like the serial path. A demo whose build failed is recorded FAIL and its
# launch is skipped. Build logs: <app>.build.log; launch logs: <app>.log.
run_demo_matrix_parallel() {
  local roots_dir="$OUTPUT_DIR/build-roots"
  local -a job_roots=()
  local k i
  mkdir -p "$roots_dir"
  # A previous run that was killed mid-build leaves its job worktrees registered (and possibly
  # their directories behind): prune the registrations and clear the paths before re-adding.
  # justified: housekeeping; a stale registration would make the `worktree add` below fail loudly.
  git -C "$ROOT_DIR" worktree prune >/dev/null 2>&1 || true
  for ((k = 0; k < BUILD_JOBS; k++)); do
    local root="$roots_dir/job$k"
    if [[ -e "$root" ]]; then
      # justified: a throwaway worktree; if git cannot remove it the directory is deleted instead.
      git -C "$ROOT_DIR" worktree remove --force "$root" >/dev/null 2>&1 || rm -rf "$root"
    fi
    git -C "$ROOT_DIR" worktree add --detach --quiet "$root" HEAD
    job_roots+=("$root")
    PARALLEL_JOB_ROOTS+=("$root")
  done
  echo "[ios_device_gate] build phase: ${#demo_names[@]} demos, $BUILD_JOBS job(s), worktrees under $roots_dir"
  local build_started
  build_started="$(date +%s)"
  local -a selected=()
  local found=$start_found
  for i in "${!demo_names[@]}"; do
    if [[ "$found" -eq 0 ]]; then
      if [[ "$START_AT" == "${demo_names[$i]}" || "$START_AT" == "${demo_dirs[$i]}" || "$START_AT" == "${demo_apps[$i]}" ]]; then
        found=1
      else
        append_result "${demo_names[$i]}" "SKIP" "0" "resumed after this demo"
        # justified: this demo was already recorded as a SKIP row (resumed after the --start-at demo).
        continue
      fi
    fi
    if [[ ! -f "$DEMOS_ROOT/${demo_dirs[$i]}/project.godot" ]]; then
      echo "[ios_device_gate] Missing demo project: $DEMOS_ROOT/${demo_dirs[$i]}" >&2
      append_result "${demo_names[$i]}" "FAIL" "0" "missing project.godot"
      exit 1
    fi
    selected+=("$i")
    prepare_demo_copy "$DEMOS_ROOT/${demo_dirs[$i]}" "${demo_apps[$i]}" >/dev/null
  done
  start_found=$found
  # Task 118: marker files and a built .app left by an EARLIER run in this output dir must not stand in for
  # this run's build: clear them before phase 1 so only a build of this run can create them.
  local stale_i
  for stale_i in ${selected[@]+"${selected[@]}"}; do
    rm -f "$OUTPUT_DIR/${demo_apps[$stale_i]}.build.ok" "$OUTPUT_DIR/${demo_apps[$stale_i]}.build.failed"
    rm -rf "$OUTPUT_DIR/${demo_apps[$stale_i]}/DerivedData/Build/Products/Debug-iphoneos/${demo_apps[$stale_i]}.app"
  done
  # Round-robin the selected demos over the jobs; each job builds its share sequentially.
  local -a pids=()
  for ((k = 0; k < BUILD_JOBS; k++)); do
    (
      local n
      for ((n = k; n < ${#selected[@]}; n += BUILD_JOBS)); do
        i="${selected[$n]}"
        local app="${demo_apps[$i]}"
        local log="$OUTPUT_DIR/$app.build.log"
        local t0 t1
        t0="$(date +%s)"
        if KANAMA_ROOT="${job_roots[$k]}" KANAMA_IOS_RUN_STAGE=build \
           KANAMA_IOS_RUN_GRADLE_ARGS="--no-daemon -Pkotlin.compiler.execution.strategy=in-process" \
           "$DEMOS_ROOT/scripts/ios_device_run.sh" "$GODOT_BIN" "$OUTPUT_DIR/project-copies/$app" \
           "$GATE_BUNDLE_ID" "$app" "$OUTPUT_DIR/$app" >"$log" 2>&1; then
          t1="$(date +%s)"; echo "$((t1 - t0))" >"$OUTPUT_DIR/$app.build.ok"
          echo "[ios_device_gate] BUILT ${demo_names[$i]} (job $k, $((t1 - t0))s)"
        else
          t1="$(date +%s)"; echo "$((t1 - t0))" >"$OUTPUT_DIR/$app.build.failed"
          echo "[ios_device_gate] BUILD FAILED ${demo_names[$i]} (job $k, $((t1 - t0))s); see $log" >&2
        fi
      done
    ) &
    pids+=("$!")
  done
  local pid
  # justified: a build job's subshell status is not the result; success is the .build.ok marker it writes, and phase 2
  # records FAIL for every demo that has no .build.ok (a job that died writes neither marker).
  for pid in "${pids[@]}"; do wait "$pid" || true; done
  local build_ended
  build_ended="$(date +%s)"
  echo "[ios_device_gate] build phase done in $((build_ended - build_started))s"
  cleanup_job_worktrees
  # Phase 2: the device, one demo at a time, in order.
  local idx
  for idx in "${selected[@]}"; do
    local app="${demo_apps[$idx]}"
    if [[ -f "$OUTPUT_DIR/$app.build.failed" ]]; then
      append_result "${demo_names[$idx]}" "FAIL" "$(cat "$OUTPUT_DIR/$app.build.failed")" "$OUTPUT_DIR/$app.build.log"
      # justified: the demo was just recorded as a FAIL row with its build log.
      continue
    fi
    if [[ ! -f "$OUTPUT_DIR/$app.build.ok" ]]; then
      echo "[ios_device_gate] no build result for ${demo_names[$idx]} (neither .build.ok nor .build.failed); see $OUTPUT_DIR/$app.build.log" >&2
      append_result "${demo_names[$idx]}" "FAIL" "0" "$OUTPUT_DIR/$app.build.log"
      # justified: the demo was just recorded as a FAIL row (no build result at all).
      continue
    fi
    tidy_gate_apps before "$GATE_BUNDLE_ID"
    # justified: run_step records the FAIL itself; the final aggregation below decides the exit status.
    KANAMA_IOS_RUN_STAGE=launch run_step \
      "${demo_names[$idx]}" \
      "$OUTPUT_DIR/$app.log" \
      "$DEMOS_ROOT/scripts/ios_device_run.sh" \
      "$GODOT_BIN" \
      "$OUTPUT_DIR/project-copies/$app" \
      "$GATE_BUNDLE_ID" \
      "$app" \
      "$OUTPUT_DIR/$app" || true  # justified: run_step already recorded the FAIL row
    keep_console_log "$app"
    tidy_gate_apps after "$GATE_BUNDLE_ID"
    stop_on_untrusted_developer "$OUTPUT_DIR/$app.log"
  done
}

if [[ "$RUN_DEMOS" -eq 1 ]]; then
  start_found=0
  if [[ -z "$START_AT" ]]; then
    start_found=1
  fi
  if [[ "$BUILD_JOBS" -gt 1 ]]; then
    run_demo_matrix_parallel
  else
    run_demo_matrix_serial
  fi
  if [[ "$start_found" -eq 0 ]]; then
    echo "[ios_device_gate] --start-at did not match any demo: $START_AT" >&2
    exit 2
  fi
fi
results_verdict "$RESULTS_TSV" || exit 1

echo "[ios_device_gate] PASS"
echo "[ios_device_gate] summary: $SUMMARY"
# Ledger the run (task 99): the Godot pin + Kanama commit this device gate passed on. A partial
# matrix (--start-at) is not the full gate and must not be ledgered as one (task 105).
if [[ -n "$START_AT" ]]; then
  echo "[ios_device_gate] partial run (--start-at $START_AT): not recorded in evidence/gates.json"
else
  python3 "$ROOT_DIR/scripts/record_gate_evidence.py" --gate ios-device-gate --claim "iOS Supported" \
    --result PASS --where "${DEVICE_LABEL:-$DEVICE_ID}" --source scripts/ios_device_gate.sh
fi
