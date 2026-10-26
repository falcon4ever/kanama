#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="${KANAMA_PROJECT_DIR:-$ROOT_DIR/example_project}"
if [[ $# -lt 1 ]]; then
  echo "usage: $0 /absolute/path/to/godot_binary"
  exit 2
fi

# shellcheck source=scripts/run_logs.sh
source "$ROOT_DIR/scripts/run_logs.sh"
# Logs default to a directory of this run's own (scripts/run_logs.sh): deleted when the run passes, kept and
# named when it fails. A fixed /tmp path was shared by concurrent runs.
LOG_FILE="${KANAMA_TOOL_SMOKE_LOG:-}"
if [[ -z "$LOG_FILE" ]]; then
  run_logs_dir kanama_tool_smoke
  LOG_FILE="$RUN_LOGS_DIR/tool_smoke.log"
fi
trap 'run_logs_finish "$?" tool_smoke' EXIT
echo "[tool_smoke] log: $LOG_FILE"

GODOT_BIN="$1"
PROJECT_DIR_FOR_GODOT="$PROJECT_DIR"
GUARD_CHECK_FOR_GODOT="$ROOT_DIR/scripts/check_export_guard.gd"

case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      GODOT_BIN="$(cygpath -u "$GODOT_BIN")"
      PROJECT_DIR_FOR_GODOT="$(cygpath -m "$PROJECT_DIR")"
      GUARD_CHECK_FOR_GODOT="$(cygpath -m "$GUARD_CHECK_FOR_GODOT")"
    fi
    ;;
esac

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
export KANAMA_TRACE_NATIVE_ADAPTERS=1
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

# task 83 -- no native call adapter may be generated inside a Godot->JVM upcall.
# The trace (KANAMA_TRACE_NATIVE_ADAPTERS=1, set above) timestamps every adapter and
# the moment the first lifecycle upcall is installed. Assert the boundary line is
# present FIRST, so a dropped env var fails loudly instead of making the absence
# check pass vacuously.
check "\[kanama:adapter\] boundary first-lifecycle-upcall-install"
check_absent "\[kanama:adapter\] downcall .* phase=post-boundary"

# kanama#277 -- Build Scripts must pick a JDK >= 25 itself: the 'kanama/build/jdk_path' setting,
# then JAVA_HOME, then the install locations, and fail with one clear message instead of running a
# build that dies in the native bootstrap's compile. The editor plugin lives twice
# (the starter template and the example project) and the two must not drift.
for plugin_file in plugin.gd kanama_export_guard.gd; do
  if ! cmp -s "$ROOT_DIR/templates/starter/addons/kanama_tools/$plugin_file" \
    "$ROOT_DIR/example_project/addons/kanama_tools/$plugin_file"; then
    smoke_fail "plugin copies differ" "templates/starter/addons/kanama_tools/$plugin_file vs example_project/addons/kanama_tools/$plugin_file"
  fi
done
JDK_SCRATCH="$(mktemp -d "${TMPDIR:-/tmp}/kanama_jdk_resolution_gd.XXXXXX")"
exit_jdk_scratch() {
  local status=$?
  rm -rf "$JDK_SCRATCH"
  run_logs_finish "$status" tool_smoke
}
trap exit_jdk_scratch EXIT
mkdir -p "$JDK_SCRATCH/project/addons" "$JDK_SCRATCH/jdks"
cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$JDK_SCRATCH/project/addons/"
cp "$ROOT_DIR/scripts/check_editor_jdk_resolution.gd" "$JDK_SCRATCH/project/"
printf 'config_version=5\n\n[application]\nconfig/name="kanama-jdk-resolution"\n' >"$JDK_SCRATCH/project/project.godot"
JDK_PROJECT_FOR_GODOT="$JDK_SCRATCH/project"
JDK_TMP_FOR_GODOT="$JDK_SCRATCH/jdks"
if command -v cygpath >/dev/null 2>&1; then
  JDK_PROJECT_FOR_GODOT="$(cygpath -m "$JDK_SCRATCH/project")"
  JDK_TMP_FOR_GODOT="$(cygpath -m "$JDK_SCRATCH/jdks")"
fi
# JAVA_HOME is removed from the process on purpose: the editor of the bug report started without it.
if ! env -u JAVA_HOME KANAMA_JDK_TEST_TMP="$JDK_TMP_FOR_GODOT" "$GODOT_BIN" --headless \
  --path "$JDK_PROJECT_FOR_GODOT" --script res://check_editor_jdk_resolution.gd >"$LOG_FILE.jdk" 2>&1; then
  cat "$LOG_FILE.jdk" >&2
  smoke_fail "JDK resolution test failed" "scripts/check_editor_jdk_resolution.gd (log: $LOG_FILE.jdk)"
fi
if ! grep -Fq "[jdk_test] PASS" "$LOG_FILE.jdk"; then
  cat "$LOG_FILE.jdk" >&2
  smoke_fail "JDK resolution test did not report PASS" "scripts/check_editor_jdk_resolution.gd (log: $LOG_FILE.jdk)"
fi

# Task 143 item 1: the plugin names a scripts jar built for another Kanama than the addon and offers Sync Addon.
cp "$ROOT_DIR/scripts/check_addon_version_lock.gd" "$JDK_SCRATCH/project/"
if ! "$GODOT_BIN" --headless --path "$JDK_PROJECT_FOR_GODOT" --script res://check_addon_version_lock.gd \
  >"$LOG_FILE.version" 2>&1 || ! grep -Fq "[version_lock] PASS" "$LOG_FILE.version"; then
  cat "$LOG_FILE.version" >&2
  smoke_fail "addon version lock test failed" "scripts/check_addon_version_lock.gd (log: $LOG_FILE.version)"
fi

# A failing headless export of a project without .godot/ must exit 1 like a GDScript project, not crash
# on exit (134). The Kanama extension loads during that first scan, which restarts the editor's doc
# thread right before the export fails; the editor plugin's _exit_tree lets that thread finish
# (templates/starter/addons/kanama_tools/plugin.gd, _finish_pending_editor_docs).
EXPORT_PROJECT="$JDK_SCRATCH/fresh_export"
cp -R "$PROJECT_DIR" "$EXPORT_PROJECT"
rm -rf "$EXPORT_PROJECT/.godot" "$EXPORT_PROJECT/export_presets.cfg"
# Run from inside the copy with relative paths, so no path needs a Windows conversion.
export_rc=0
(cd "$EXPORT_PROJECT" && "$GODOT_BIN" --headless --path . --export-release Nope build/x) >"$LOG_FILE.export" 2>&1 ||
  export_rc=$?
if [[ "$export_rc" -ne 1 ]] || grep -Eq "handle_crash|Program crashed" "$LOG_FILE.export"; then
  tail -n 40 "$LOG_FILE.export" >&2
  smoke_fail "failing export of a fresh project exited $export_rc, expected 1 without a crash" "$LOG_FILE.export"
fi
if ! grep -Fq "doesn't have an \`export_presets.cfg\` file" "$LOG_FILE.export"; then
  tail -n 40 "$LOG_FILE.export" >&2
  smoke_fail "failing export of a fresh project did not print Godot's export error" "$LOG_FILE.export"
fi

# The editor export guard (kanama_export_guard.gd) refuses an export whose scripts do not bind. Its check runs here
# on the example project, whose scenes attach real scripts from the freshly synced jar: all must bind. The exports
# that prove the refusal itself are scripts/export_guard_selftest.sh.
if ! "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" --script "$GUARD_CHECK_FOR_GODOT" >"$LOG_FILE.guard" 2>&1; then
  cat "$LOG_FILE.guard" >&2
  smoke_fail "export guard check failed" "scripts/check_export_guard.gd (log: $LOG_FILE.guard)"
fi
if ! grep -Fq "[export_guard] PASS" "$LOG_FILE.guard"; then
  cat "$LOG_FILE.guard" >&2
  smoke_fail "export guard check did not report PASS" "scripts/check_export_guard.gd (log: $LOG_FILE.guard)"
fi

# The guard's one-click deploy clean-up (is_one_click_deploy, discard_export) runs only in the editor: an
# editor plugin in a scratch project (scripts/check_export_guard_discard.gd) makes Godot-named and user files
# in the editor's temp folder and checks which ones the guard deletes. --quit-after bounds the run if the
# plugin never loads (then there is no PASS line).
DISCARD_PROJECT="$JDK_SCRATCH/guard_discard"
mkdir -p "$DISCARD_PROJECT/addons/guard_discard_test"
cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$DISCARD_PROJECT/addons/"
cp "$ROOT_DIR/scripts/check_export_guard_discard.gd" "$DISCARD_PROJECT/addons/guard_discard_test/plugin.gd"
printf '[plugin]\n\nname="guard_discard_test"\ndescription=""\nauthor=""\nversion=""\nscript="plugin.gd"\n' \
  >"$DISCARD_PROJECT/addons/guard_discard_test/plugin.cfg"
printf 'config_version=5\n\n[application]\nconfig/name="kanama-guard-discard"\n\n[editor_plugins]\nenabled=PackedStringArray("res://addons/guard_discard_test/plugin.cfg")\n' \
  >"$DISCARD_PROJECT/project.godot"
discard_rc=0
(cd "$DISCARD_PROJECT" && "$GODOT_BIN" --headless --editor --path . --quit-after 3000) >"$LOG_FILE.discard" 2>&1 || discard_rc=$?
if [[ "$discard_rc" -ne 0 ]] || ! grep -Fq "[export_guard_discard] PASS" "$LOG_FILE.discard" ||
  grep -Fq "SCRIPT ERROR" "$LOG_FILE.discard"; then
  grep -E "export_guard_discard|SCRIPT ERROR|ERROR:|at: " "$LOG_FILE.discard" >&2 || tail -n 40 "$LOG_FILE.discard" >&2
  smoke_fail "export guard one-click clean-up test failed (exit $discard_rc)" "scripts/check_export_guard_discard.gd (log: $LOG_FILE.discard)"
fi

echo "[tool_smoke] PASS"
