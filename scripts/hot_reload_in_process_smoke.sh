#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="$ROOT_DIR/example_project"
SCRIPT_FILE="$PROJECT_DIR/HelloScript.kt"
LOG_FILE="${KANAMA_HOTRELOAD_IN_PROCESS_LOG:-/tmp/kanama_hot_reload_in_process.log}"

if [[ $# -lt 1 ]]; then
  echo "usage: $0 /absolute/path/to/godot_binary"
  exit 2
fi

GODOT_BIN="$1"
PROJECT_DIR_FOR_GODOT="$PROJECT_DIR"
case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      GODOT_BIN="$(cygpath -u "$GODOT_BIN")"
      PROJECT_DIR_FOR_GODOT="$(cygpath -m "$PROJECT_DIR")"
    fi
    ;;
esac

BACKUP="$(mktemp /tmp/kanama_hello_backup.XXXXXX.kt)"
SIGNAL_FILE="$(mktemp /tmp/kanama_hot_reload_signal.XXXXXX)"
STAGE_FILE="$(mktemp /tmp/kanama_hot_reload_stage.XXXXXX)"
rm -f "$SIGNAL_FILE" "$STAGE_FILE" "$LOG_FILE"
cp "$SCRIPT_FILE" "$BACKUP"

GODOT_PID=""

restore() {
  if [[ -n "$GODOT_PID" ]] && kill -0 "$GODOT_PID" 2>/dev/null; then
    kill "$GODOT_PID" 2>/dev/null || true
    wait "$GODOT_PID" 2>/dev/null || true
  fi
  cp "$BACKUP" "$SCRIPT_FILE"
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null || true
  rm -f "$BACKUP" "$SIGNAL_FILE" "$STAGE_FILE"
}
trap restore EXIT

set_marker() {
  local marker="$1"
  perl -0pi -e "s/HelloScript\\(file\\)\\._ready(?:\\[[^\\]]*\\])?/HelloScript(file)._ready[$marker]/g" "$SCRIPT_FILE"
}

# See runtime_smoke.sh: the reason is restated after the log tail so it is the last thing
# printed, rather than being buried under ~160 lines of Godot verbose output.
smoke_fail() {
  local kind="$1" pattern="$2"
  echo "[hot_reload_in_process_smoke] $kind: $pattern" >&2
  tail -n 160 "$LOG_FILE" 2>/dev/null >&2 || true
  echo >&2
  echo "[hot_reload_in_process_smoke] FAIL -- $kind: $pattern" >&2
  echo "[hot_reload_in_process_smoke] full log: $LOG_FILE" >&2
  exit 1
}

wait_for_pattern() {
  local pattern="$1"
  local timeout_seconds="$2"
  local start
  start="$(date +%s)"
  while true; do
    if [[ -f "$LOG_FILE" ]] && grep -Eq -- "$pattern" "$LOG_FILE"; then
      return 0
    fi
    if [[ -n "$GODOT_PID" ]] && ! kill -0 "$GODOT_PID" 2>/dev/null; then
      smoke_fail "Godot exited before pattern" "$pattern"
    fi
    if (( "$(date +%s)" - start > timeout_seconds )); then
      smoke_fail "timeout waiting for pattern" "$pattern"
    fi
    sleep 0.2
  done
}

check_absent() {
  local pattern="$1"
  if grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "unexpected pattern" "$pattern"
  fi
}

set_marker "A"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null

KANAMA_IN_PROCESS_HOT_RELOAD_SMOKE=1 \
KANAMA_IN_PROCESS_HOT_RELOAD_SIGNAL="$SIGNAL_FILE" \
KANAMA_IN_PROCESS_HOT_RELOAD_STAGE="$STAGE_FILE" \
  "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" >"$LOG_FILE" 2>&1 &
GODOT_PID="$!"

wait_for_pattern "HelloScript\\(file\\)\\._ready\\[A\\]" 30
wait_for_pattern "in-process hot reload smoke ready" 30

set_marker "B"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
touch "$SIGNAL_FILE"

wait_for_pattern "hot-reload: reloaded scripts from .*kanama-scripts\\.jar \\(loader=2, old_loader=1, rebound=[0-9]+\\)" 30
wait_for_pattern "in-process hot reload smoke reload_scene" 30
wait_for_pattern "HelloScript\\(file\\)\\._ready\\[B\\]" 30
wait_for_pattern "hot-reload: retired-loader-check collected=1 alive=0" 30
wait_for_pattern "in-process hot reload smoke quit" 30

wait "$GODOT_PID"
GODOT_PID=""

check_absent "hot-reload: failed"
check_absent "placeholder=true"
check_absent "Cannot ptrcall nil constructor"
check_absent "Orphan StringName"
check_absent "unclaimed string names"

echo "[hot_reload_in_process_smoke] PASS"
