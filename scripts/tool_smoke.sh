#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="${KANAMA_PROJECT_DIR:-$ROOT_DIR/example_project}"
LOG_FILE="${KANAMA_TOOL_SMOKE_LOG:-/tmp/kanama_tool_smoke.log}"

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

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
if [[ "${KANAMA_TOOL_SMOKE_TRACE_INSTANCES:-0}" == "1" ]]; then
  KANAMA_TRACE_INSTANCES=1 "$GODOT_BIN" --headless --editor --quit --path "$PROJECT_DIR_FOR_GODOT" >"$LOG_FILE" 2>&1
else
  "$GODOT_BIN" --headless --editor --quit --path "$PROJECT_DIR_FOR_GODOT" >"$LOG_FILE" 2>&1
fi

# See runtime_smoke.sh: the reason is restated after the log tail so it is the last thing
# printed, rather than being buried under ~120 lines of Godot verbose output.
smoke_fail() {
  local kind="$1" pattern="$2"
  echo "[tool_smoke] $kind: $pattern" >&2
  echo "[tool_smoke] log tail:" >&2
  tail -n 120 "$LOG_FILE" >&2
  echo >&2
  echo "[tool_smoke] FAIL -- $kind: $pattern" >&2
  echo "[tool_smoke] full log: $LOG_FILE" >&2
  exit 1
}

check() {
  local pattern="$1"
  if ! grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "missing pattern" "$pattern"
  fi
}

check_absent() {
  local pattern="$1"
  if grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "unexpected pattern" "$pattern"
  fi
}

check "registered class HelloKanama : Node"
check "KanamaScript.construct kotlinClass=net\\.multigesture\\.kanama\\.example\\.HelloScript globalName=HelloScript"
check "HelloKanama\\._ready\\(\\)"
check "HelloScript\\(file\\)\\._ready health=99 speed=5\\.1 label=from_tscn"
check "Node3D body found=true target_path=\\.\\./SceneTarget3D target_path_tscn=true"
check "registered class NonToolHelloKanama : Node"
check "unregistered [0-9]+ extension class\\(es\\)"
# Non-tool @ScriptClass must not run in editor: its callbacks should never fire.
check_absent "NonToolScript\\._ready"
check_absent "NonToolScript\\._process"
# Non-tool @RegisterClass must not run in editor either: Godot uses
# is_runtime=true → placeholder substitution, so _ready never fires.
check_absent "NonToolHelloKanama\\._ready fired"
check_absent "Orphan StringName"
check_absent "unclaimed string names"
check_absent "Cannot ptrcall nil constructor"
# issue #38 — newScriptInstance() must build a REAL script-backed resource even in
# the editor (a non-@Tool resource class would otherwise instantiate as a placeholder
# and the reified cast would fail). Assert creation + save succeed here; the on-disk
# script-reference round-trip is asserted in runtime_smoke where the global class list
# is reliably populated.
check "ResourceForgeSmoke live=true save_error=0"
check_absent "ResourceForgeSmoke create_failed"
# task 56 — the force-real override for newScriptInstance() must be scoped to the specific
# owner being created, not thread-wide. ReentrantForgeProbe's construction (inside the create
# window) reentrantly instantiates a *different* non-@Tool script; reentered=true proves that
# reentrant instantiation ran, and the inner CONSTRUCTED marker must stay ABSENT because that
# inner owner still gets an editor placeholder. Pre-fix (thread-wide flag) the marker fired.
check "ReentrantForgeProbe reentered=true"
check_absent "ReentrantPlaceholderProbe CONSTRUCTED"

echo "[tool_smoke] PASS"
