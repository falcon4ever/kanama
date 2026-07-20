#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="$ROOT_DIR/example_project"
SCRIPT_FILE="$PROJECT_DIR/HelloScript.kt"
LOG1="${KANAMA_HOTRELOAD_LOG1:-/tmp/kanama_hot_reload_1.log}"
LOG2="${KANAMA_HOTRELOAD_LOG2:-/tmp/kanama_hot_reload_2.log}"

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
cp "$SCRIPT_FILE" "$BACKUP"

restore() {
  cp "$BACKUP" "$SCRIPT_FILE"
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null || true
  rm -f "$BACKUP"
}
trap restore EXIT

set_marker() {
  local marker="$1"
  perl -0pi -e "s/HelloScript\\(file\\)\\._ready(?:\\[[^\\]]*\\])?/HelloScript(file)._ready[$marker]/g" "$SCRIPT_FILE"
}

# See runtime_smoke.sh: the reason is restated after the log tail so it is the last thing
# printed, rather than being buried under ~120 lines of Godot verbose output.
smoke_fail() {
  local kind="$1" pattern="$2" log_file="$3"
  echo "[hot_reload_smoke] $kind: $pattern" >&2
  tail -n 120 "$log_file" >&2
  echo >&2
  echo "[hot_reload_smoke] FAIL -- $kind: $pattern" >&2
  echo "[hot_reload_smoke] full log: $log_file" >&2
  exit 1
}

check_marker() {
  local marker="$1"
  local log_file="$2"
  if ! grep -Eq -- "HelloScript\\(file\\)\\._ready\\[$marker\\]" "$log_file"; then
    smoke_fail "missing marker" "$marker" "$log_file"
  fi
}

check() {
  local pattern="$1"
  local log_file="$2"
  if ! grep -Eq -- "$pattern" "$log_file"; then
    smoke_fail "missing pattern" "$pattern" "$log_file"
  fi
}

check_absent() {
  local pattern="$1"
  local log_file="$2"
  if grep -Eq -- "$pattern" "$log_file"; then
    smoke_fail "unexpected pattern" "$pattern" "$log_file"
  fi
}

check_common_invariants() {
  local log_file="$1"
  check "hot-reload: reloaded scripts from .*kanama-scripts\\.jar \\(loader=[0-9]+, old_loader=[0-9]+, rebound=[0-9]+\\)" "$log_file"
  check "callInstanceCreate: net\\.multigesture\\.kanama\\.example\\.HelloScript .* placeholder=false" "$log_file"
  check "destroyed [0-9]+/[0-9]+ tracked KanamaScript object\\(s\\)" "$log_file"
  check_absent "hot-reload: failed" "$log_file"
  # HelloScript is @Tool so it must never be placeholder'd in editor mode.
  # Other scripts (e.g. NonToolScript) legitimately go through the placeholder
  # path in editor — that's the editor-gating behaviour we want.
  check_absent "HelloScript .* placeholder=true" "$log_file"
  check_absent "Cannot ptrcall nil constructor" "$log_file"
  check_absent "Orphan StringName" "$log_file"
  check_absent "unclaimed string names" "$log_file"
}

set_marker "A"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
KANAMA_TRACE_INSTANCES=1 "$GODOT_BIN" --headless --editor --quit-after 120 --path "$PROJECT_DIR_FOR_GODOT" >"$LOG1" 2>&1
check_marker "A" "$LOG1"
check_common_invariants "$LOG1"

set_marker "B"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
KANAMA_TRACE_INSTANCES=1 "$GODOT_BIN" --headless --editor --quit-after 120 --path "$PROJECT_DIR_FOR_GODOT" >"$LOG2" 2>&1
check_marker "B" "$LOG2"
check_common_invariants "$LOG2"

echo "[hot_reload_smoke] PASS"
