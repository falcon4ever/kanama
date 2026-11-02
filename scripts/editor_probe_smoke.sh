#!/usr/bin/env bash
# editor_probe_smoke.sh -- A saved Kotlin edit in a real editor is built on save by the checkout's Gradle build,
# reloaded, and seen by the editor: the status light, the new members, the inspector, the saved scene, and a
# compile error's red light.
#
# A scratch Godot project beside this checkout (kanama/tools/repo_dir, so Build Scripts runs installAddonJar here, the
# example_project layout) holds a @ScriptClass in a scene; scripts/check_editor_probes.gd runs as an editor plugin
# beside Kanama Tools and drives it. Each of its five probes prints its own PASS or FAIL line. The scratch project,
# its scripts build under project-scripts/build/ and the scratch editor HOME are removed afterwards.
#
# usage: scripts/editor_probe_smoke.sh /path/to/godot
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TAG="[editor_probe_smoke]"
if [[ $# -ne 1 ]]; then
  echo "usage: $0 /path/to/godot" >&2
  exit 2
fi
GODOT_BIN="$1"

# shellcheck source=scripts/run_logs.sh
source "$ROOT_DIR/scripts/run_logs.sh"
# shellcheck source=scripts/editor_check_lib.sh
source "$ROOT_DIR/scripts/editor_check_lib.sh"
# shellcheck source=scripts/gate_skip.sh
source "$ROOT_DIR/scripts/gate_skip.sh"

case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*)
    gate_skip editor-probes-windows "the probes start the checkout's POSIX gradlew from the editor; Windows has store_addon_editor_smoke.sh" || exit 1
    exit 0
    ;;
esac

LOG_FILE="${KANAMA_EDITOR_PROBE_LOG:-}"
if [[ -z "$LOG_FILE" ]]; then
  run_logs_dir kanama_editor_probes
  LOG_FILE="$RUN_LOGS_DIR/editor_probes.log"
fi
echo "$TAG log: $LOG_FILE"

# The physical path (macOS: /private/tmp, not /tmp): Godot reports the project under it, and the plugin's builds
# then name the same scripts folder as the install below, so they share its build folder.
SCRATCH="$(cd "$(mktemp -d "${TMPDIR:-/tmp}/kanama_editor_probe_scratch.XXXXXX")" && pwd -P)"
PROJECT="$SCRATCH/project"
# The project's scripts build in project-scripts/build/<first 16 hex digits of the SHA-256 of the
# -PkanamaProjectScriptsDir value> (shortHash in project-scripts/build.gradle.kts). Only the folders of the values
# this run passed are removed: its own install's, and each one the editor's builds name in the log.
SCRIPT_BUILDS="$ROOT_DIR/project-scripts/build"
remove_script_builds() {
  local dir values
  # justified: an editor that never started a build leaves no such line; the install's own value is still listed.
  values="$( { printf '%s\n' "$PROJECT/"; sed -n 's/.*-PkanamaProjectScriptsDir=\([^ ]*\).*/\1/p' "$LOG_FILE" 2>/dev/null || true; } | sort -u)"
  for dir in $(printf '%s\n' "$values" | python3 -c 'import hashlib, sys
for line in sys.stdin.read().splitlines():
    if line:
        print(hashlib.sha256(line.encode("utf-8")).hexdigest()[:16])'); do
    rm -rf "${SCRIPT_BUILDS:?}/$dir"
  done
}
finish() {
  local status=$?
  run_bounded_stop
  remove_script_builds
  rm -rf "$SCRATCH"
  run_logs_finish "$status" editor_probe_smoke
}
trap finish EXIT
run_bounded_signal_traps

mkdir -p "$PROJECT/addons"
cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$PROJECT/addons/"
editor_check_plugin "$PROJECT" editor_probes "$ROOT_DIR/scripts/check_editor_probes.gd"
editor_check_project_godot "$PROJECT" kanama-editor-probes "kanama_tools editor_probes" \
  '[kanama]' '' "tools/repo_dir=\"$ROOT_DIR\"" 'tools/java_preflight_enabled=false'
cp "$ROOT_DIR/scripts/fixtures/editor-probes/ProbeScript.kt" "$ROOT_DIR/scripts/fixtures/editor-probes/probe.tscn" "$PROJECT/"
editor_check_home "$SCRATCH/editor_home"

# The project starts built, as Build Scripts leaves it: the same installAddonJar the plugin runs for a project
# beside a checkout (_current_project_install_args in plugin.gd).
# No Gradle or Kotlin daemon outlives the run (local_ci.sh's convention): --no-daemon and in-process Kotlin compiles,
# here and, through the environment, for the builds the editor starts.
if ! run_bounded 600 "$TAG" "$ROOT_DIR/gradlew" -p "$ROOT_DIR" -q --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  installAddonJar "-PkanamaProjectDir=$PROJECT/" "-PkanamaProjectScriptsDir=$PROJECT/" >"$LOG_FILE.install" 2>&1; then
  tail -n 60 "$LOG_FILE.install" >&2
  echo "$TAG FAIL: installAddonJar into the probe project failed (log: $LOG_FILE.install)" >&2
  exit 1
fi

# The check quits the editor with its verdict (it gives up itself after 1200 s); the bound covers an editor that
# never gets that far, and both stay inside ci.yml local-ci's budget. The builds the editor starts are the checkout's
# gradlew on the machine's Gradle cache; GRADLE_OPTS and the ORG_GRADLE_PROJECT_ property reach them through the
# plugin's launch.
# A fatal JVM error writes its report beside the log (KANAMA_JVM_OPTIONS reaches the editor's JVM).
rc=0
run_bounded 1300 "$TAG" editor_env GRADLE_OPTS="${GRADLE_OPTS:+$GRADLE_OPTS }-Dorg.gradle.daemon=false" \
  ORG_GRADLE_PROJECT_kotlin.compiler.execution.strategy=in-process \
  KANAMA_JVM_OPTIONS="${KANAMA_JVM_OPTIONS:+$KANAMA_JVM_OPTIONS }-XX:ErrorFile=$LOG_FILE.hs_err_pid%p.log" \
  "$GODOT_BIN" --headless --editor --path "$PROJECT" >"$LOG_FILE" 2>&1 || rc=$?
grep -E "^\[editor_probe\] (PASS|FAIL|the build)" "$LOG_FILE" || true # justified: the verdict is checked below; these lines are the summary
fail=0
# After each successful build the plugin reloads the open scene only once the runtime has loaded the new jar
# (_wait_for_runtime_reload): a scene reloaded first keeps the old build's code.
early_reload="$(awk '/\[kanama:tools\] Running: /{loaded=0} /hot-reload: reloaded scripts from/{loaded=1}
  /\[kanama:tools\] Reloaded scene after sync/{if (!loaded) print NR}' "$LOG_FILE")"
if [[ -n "$early_reload" ]]; then
  echo "$TAG FAIL: the plugin reloaded the scene before the runtime loaded the rebuilt jar (log lines: $(printf '%s' "$early_reload" | tr '\n' ' '))" >&2
  fail=1
fi
for probe in 1 2 3 4 5; do
  if ! grep -Eq "^\[editor_probe\] PASS probe $probe: " "$LOG_FILE"; then
    echo "$TAG FAIL: probe $probe did not pass" >&2
    fail=1
  fi
done
if [[ "$rc" -ne 0 || "$fail" -ne 0 ]] || ! grep -Fxq "[editor_probe] PASS" "$LOG_FILE" || grep -Fq "SCRIPT ERROR" "$LOG_FILE"; then
  # Everything goes to the job output: a CI runner's /tmp, and the kept logs with it, is gone after the job.
  echo "$TAG the probe, build and reload lines:" >&2
  grep -E "editor_probe|kanama:tools|SCRIPT ERROR|hot-reload" "$LOG_FILE" | tail -n 80 >&2 || true # justified: diagnostics; the verdict is already FAIL
  echo "$TAG the editor log's last 150 lines (stdout and stderr):" >&2
  tail -n 150 "$LOG_FILE" >&2
  for hs_err in "$LOG_FILE".hs_err_pid*.log; do
    if [[ -f "$hs_err" ]]; then
      echo "$TAG the JVM's fatal error report $hs_err (first 80 lines):" >&2
      head -n 80 "$hs_err" >&2
    fi
  done
  case "$rc" in
    134) echo "$TAG the editor aborted (exit 134, SIGABRT)" >&2 ;;
    139) echo "$TAG the editor crashed (exit 139, SIGSEGV); without a Godot backtrace above it crashed after Godot's crash handler was gone" >&2 ;;
    143 | 137) echo "$TAG the editor was stopped at the bound (exit $rc)" >&2 ;;
  esac
  echo "$TAG FAIL (editor exit $rc): the editor probes (log: $LOG_FILE)" >&2
  exit 1
fi
echo "$TAG PASS"
