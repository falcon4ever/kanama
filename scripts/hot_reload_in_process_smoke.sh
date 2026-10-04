#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="$ROOT_DIR/example_project"
SCRIPT_FILE="$PROJECT_DIR/HelloScript.kt"
# Task 133 C2: the Kotlin scene autoload, rebuilt with a new build marker and a changed type.
AUTOLOAD_FILE="$PROJECT_DIR/KanamaSceneAutoload.kt"
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
AUTOLOAD_BACKUP="$(mktemp /tmp/kanama_scene_autoload_backup.XXXXXX.kt)"
cp "$AUTOLOAD_FILE" "$AUTOLOAD_BACKUP"

GODOT_PID=""

restore() {
  local status=$?
  if [[ -n "$GODOT_PID" ]] && kill -0 "$GODOT_PID" 2>/dev/null; then
    # justified: cleanup of our own child after the verdict; it may already be gone.
    kill "$GODOT_PID" 2>/dev/null || true
    # justified: cleanup of our own child after the verdict; it may already be gone.
    wait "$GODOT_PID" 2>/dev/null || true
  fi
  cp "$BACKUP" "$SCRIPT_FILE"
  cp "$AUTOLOAD_BACKUP" "$AUTOLOAD_FILE"
  # A failed re-sync would leave the mutated scripts jar in the example project for the NEXT gate, so it
  # fails THIS run (task 118): the exit status of an EXIT trap that calls `exit` is the script's.
  if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null; then
    echo "[$(basename "$0" .sh)] FAIL: restoring the example addon jar failed; run ./gradlew syncExampleAddonJar before the next gate" >&2
    status=1
  fi
  rm -f "$BACKUP" "$AUTOLOAD_BACKUP" "$SIGNAL_FILE" "$STAGE_FILE"
  exit "$status"
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
  # Redirect order matters: `>&2` first duplicates the real stderr, then `2>/dev/null`
  # silences tail's own errors when the log does not exist yet. Reversing them points
  # stdout at /dev/null and silently drops the whole dump.
  # justified: diagnostics only; the next lines print the reason and exit 1 either way.
  tail -n 160 "$LOG_FILE" >&2 2>/dev/null || true
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

check() {
  local pattern="$1"
  if ! grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "missing pattern" "$pattern"
  fi
}

set_marker "A"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null

KANAMA_TRACE_NATIVE_ADAPTERS=1 \
KANAMA_IN_PROCESS_HOT_RELOAD_SMOKE=1 \
KANAMA_IN_PROCESS_HOT_RELOAD_SIGNAL="$SIGNAL_FILE" \
KANAMA_IN_PROCESS_HOT_RELOAD_STAGE="$STAGE_FILE" \
  "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" >"$LOG_FILE" 2>&1 &
GODOT_PID="$!"

wait_for_pattern "HelloScript\\(file\\)\\._ready\\[A\\]" 30
wait_for_pattern "in-process hot reload smoke ready" 30

set_marker "B"
# Build B of the scene autoload: a new marker, and `mood` changes from Long to String.
perl -pi -e 's/const val BUILD = "A"/const val BUILD = "B"/; s/var mood: Long = 3/var mood: String = "calm"/' "$AUTOLOAD_FILE"
grep -q 'var mood: String = "calm"' "$AUTOLOAD_FILE" || smoke_fail "the build-B edit of KanamaSceneAutoload.kt did not apply" "var mood: String"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
touch "$SIGNAL_FILE"

# task 133 C2 -- the Kotlin autoloads are reset in place with the new build's script objects (and
# their _ready again): KanamaKotlinAutoload and the KanamaSceneAutoload root.
wait_for_pattern "hot-reload: reloaded scripts from .*kanama-scripts\\.jar \\(loader=2, old_loader=1, rebound=[0-9]+, autoloads=2\\)" 30
wait_for_pattern "in-process hot reload smoke reload_scene" 30
wait_for_pattern "HelloScript\\(file\\)\\._ready\\[B\\]" 30
wait_for_pattern "hot-reload: retired-loader-check collected=1 alive=0" 30
wait_for_pattern "in-process hot reload smoke quit" 30

wait "$GODOT_PID"
GODOT_PID=""

# justified: grep -c exits 1 when it counts 0 rows; the count below is the verdict.
autoload_rows="$(grep -c "autoload kotlin=autoload:1 gd=KanamaSmokeAutoload:5 missing=true wrong_class=true wrong_script=true" "$LOG_FILE" || true)"
if [[ "$autoload_rows" -lt 2 ]]; then
  smoke_fail "the Kotlin autoload was not re-created by the hot reload (autoload rows: $autoload_rows)" "autoload kotlin=autoload:1"
fi

# task 133 C2 -- the scene autoload, read by its GDScript global before and after the reload: the
# same node, `level` keeps its runtime value (same type), `mood` (Long -> String) takes the new
# default, one runtime child, the scene child kept, and only the new build's handler runs.
check "scene autoload before build=A level=9 mood=5 handled=1 runtime=1 fixed=true"
check "scene autoload after build=B level=9 mood=calm handled=1 runtime=1 fixed=true"
for build in A B; do
  # justified: grep -c exits 1 when it counts 0 rows; the count below is the verdict.
  handler_rows="$(grep -c "scene autoload handler build=$build" "$LOG_FILE" || true)"
  if [[ "$handler_rows" -ne 1 ]]; then
    smoke_fail "the build-$build handler ran $handler_rows time(s), not once (an old lambda survived the reload?)" "scene autoload handler build=$build"
  fi
done
check_absent "Leaked instance"
check_absent "instances leaked at exit"
check_absent "hot-reload: failed"
check_absent "placeholder=true"
check_absent "Cannot ptrcall nil constructor"
check_absent "Orphan StringName"
check_absent "unclaimed string names"
# task 83 -- no native call adapter may be generated inside a Godot->JVM upcall.
# Assert the trace is present first, so a dropped KANAMA_TRACE_NATIVE_ADAPTERS
# fails loudly instead of making the absence check pass vacuously.
check "\\[kanama:adapter\\] boundary first-lifecycle-upcall-install"
check_absent "\\[kanama:adapter\\] downcall .* phase=post-boundary"

echo "[hot_reload_in_process_smoke] PASS"
