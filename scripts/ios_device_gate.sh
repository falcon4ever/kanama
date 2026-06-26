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
DEVICE_LABEL="${KANAMA_IOS_DEVICE_LABEL:-}"
START_AT=""
CLEAN_INSTALLED=1
GATE_BUNDLE_ID="${KANAMA_IOS_GATE_BUNDLE_ID:-net.multigesture.kanama.devicegate}"

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
  --keep-installed             Do not uninstall older Kanama gate apps before
                               each step. By default the gate removes older
                               known Kanama gate apps before installing the
                               next one, but leaves the active app installed.
  --pause-seconds N            Warning countdown before each device launch.
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

if [[ -z "$OUTPUT_DIR" ]]; then
  OUTPUT_DIR="$(mktemp -d "${TMPDIR:-/tmp}/kanama_ios_device_gate.XXXXXX")"
else
  mkdir -p "$OUTPUT_DIR"
  OUTPUT_DIR="$(cd "$OUTPUT_DIR" && pwd)"
fi

SUMMARY="$OUTPUT_DIR/summary.md"
RESULTS_TSV="$OUTPUT_DIR/results.tsv"
RUN_STARTED="$(date -u '+%Y-%m-%dT%H:%M:%SZ')"

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

warn_before_launch() {
  local label="$1"
  if [[ "$PAUSE_SECONDS" -gt 0 ]]; then
    echo "[ios_device_gate] About to build/install/launch '$label' on the connected iPhone."
    echo "[ios_device_gate] Unlock the device and keep it awake. Continuing in ${PAUSE_SECONDS}s..."
    sleep "$PAUSE_SECONDS"
  fi
}

append_result() {
  local name="$1"
  local status="$2"
  local elapsed="$3"
  local log_path="$4"
  printf '%s\t%s\t%s\t%s\n' "$name" "$status" "$elapsed" "$log_path" >>"$RESULTS_TSV"
}

all_gate_bundles=("$fresh_bundle" "${demo_bundles[@]}")

uninstall_bundle() {
  local bundle_id="$1"
  if [[ "$CLEAN_INSTALLED" -ne 1 ]]; then
    return
  fi
  DEVELOPER_DIR="$XCODE_DEVELOPER_DIR" xcrun devicectl device uninstall app \
    --device "$DEVICE_ID" \
    "$bundle_id" >/dev/null 2>&1 || true
}

uninstall_other_gate_bundles() {
  local keep_bundle="$1"
  local bundle_id
  for bundle_id in "${all_gate_bundles[@]}" "$GATE_BUNDLE_ID"; do
    if [[ "$bundle_id" != "$keep_bundle" ]]; then
      uninstall_bundle "$bundle_id"
    fi
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
    echo
    echo "| Path | Status | Gate elapsed | Log |"
    echo "|---|---:|---:|---|"
    tail -n +2 "$RESULTS_TSV" | while IFS=$'\t' read -r name status elapsed log_path; do
      echo "| $name | $status | ${elapsed}s | $log_path |"
    done
  } >"$SUMMARY"
}

# Always emit the summary, even if a step fails or the run is interrupted, so the
# partial pass/fail matrix is recoverable for the baselines table.
trap write_summary EXIT

export KANAMA_IOS_DEVICE="$DEVICE_ID"
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
  uninstall_other_gate_bundles "$fresh_bundle"
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
  # run_step records the FAIL itself; keep going so the demo matrix still runs
  # and the final aggregation below decides the overall exit status.
  run_step "fresh-starter-project" "$OUTPUT_DIR/fresh-starter.log" "${fresh_args[@]}" || true
fi

if [[ "$RUN_DEMOS" -eq 1 ]]; then
  start_found=0
  if [[ -z "$START_AT" ]]; then
    start_found=1
  fi
  for i in "${!demo_names[@]}"; do
    if [[ "$start_found" -eq 0 ]]; then
      if [[ "$START_AT" == "${demo_names[$i]}" || "$START_AT" == "${demo_dirs[$i]}" || "$START_AT" == "${demo_apps[$i]}" ]]; then
        start_found=1
      else
        append_result "${demo_names[$i]}" "SKIP" "0" "resumed after this demo"
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
    uninstall_other_gate_bundles "$GATE_BUNDLE_ID"
    run_step \
      "${demo_names[$i]}" \
      "$OUTPUT_DIR/${demo_apps[$i]}.log" \
      "$DEMOS_ROOT/scripts/ios_device_run.sh" \
      "$GODOT_BIN" \
      "$demo_run_path" \
      "$GATE_BUNDLE_ID" \
      "${demo_apps[$i]}" \
      "$OUTPUT_DIR/${demo_apps[$i]}" || true
  done
  if [[ "$start_found" -eq 0 ]]; then
    echo "[ios_device_gate] --start-at did not match any demo: $START_AT" >&2
    exit 2
  fi
fi

if rg -q $'\tFAIL\t' "$RESULTS_TSV"; then
  echo "[ios_device_gate] one or more gate steps failed; summary: $SUMMARY" >&2
  exit 1
fi

echo "[ios_device_gate] PASS"
echo "[ios_device_gate] summary: $SUMMARY"
