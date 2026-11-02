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

SCRATCH="$(mktemp -d "${TMPDIR:-/tmp}/kanama_editor_probe_scratch.XXXXXX")"
PROJECT="$SCRATCH/project"
# The project's scripts build in project-scripts/build/<hash of the scripts folder> (project-scripts/build.gradle.kts):
# a folder per scratch project, removed with it.
SCRIPT_BUILDS="$ROOT_DIR/project-scripts/build"
mkdir -p "$SCRIPT_BUILDS"
builds_before=" "
for dir in "$SCRIPT_BUILDS"/*; do
  if [[ -e "$dir" ]]; then
    builds_before="$builds_before${dir##*/} "
  fi
done
finish() {
  local status=$?
  local dir
  for dir in "$SCRIPT_BUILDS"/*; do
    case "$builds_before" in
      *" ${dir##*/} "*) ;;
      *) rm -rf "$dir" ;;
    esac
  done
  rm -rf "$SCRATCH"
  run_logs_finish "$status" editor_probe_smoke
}
trap finish EXIT

mkdir -p "$PROJECT/addons"
cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$PROJECT/addons/"
editor_check_plugin "$PROJECT" editor_probes "$ROOT_DIR/scripts/check_editor_probes.gd"
editor_check_project_godot "$PROJECT" kanama-editor-probes "kanama_tools editor_probes" \
  '[kanama]' '' "tools/repo_dir=\"$ROOT_DIR\"" 'tools/java_preflight_enabled=false'
cp "$ROOT_DIR/scripts/fixtures/editor-probes/ProbeScript.kt" "$ROOT_DIR/scripts/fixtures/editor-probes/probe.tscn" "$PROJECT/"
editor_check_home "$SCRATCH/editor_home"

# The project starts built, as Build Scripts leaves it: the same installAddonJar the plugin runs for a project
# beside a checkout (_current_project_install_args in plugin.gd).
if ! run_bounded 900 "$TAG" "$ROOT_DIR/gradlew" -p "$ROOT_DIR" -q installAddonJar "-PkanamaProjectDir=$PROJECT/" \
  "-PkanamaProjectScriptsDir=$PROJECT/" >"$LOG_FILE.install" 2>&1; then
  tail -n 60 "$LOG_FILE.install" >&2
  echo "$TAG FAIL: installAddonJar into the probe project failed (log: $LOG_FILE.install)" >&2
  exit 1
fi

# The check quits the editor with its verdict (it gives up itself after 1500 s); the bound covers an editor that
# never gets that far. The builds it starts are the checkout's gradlew, on the machine's Gradle cache and daemon.
rc=0
(cd "$PROJECT" && run_bounded 1800 "$TAG" editor_env "$GODOT_BIN" --headless --editor --path .) >"$LOG_FILE" 2>&1 || rc=$?
grep -E "^\[editor_probe\] (PASS|FAIL|the build)" "$LOG_FILE" || true # justified: the verdict is checked below; these lines are the summary
fail=0
for probe in 1 2 3 4 5; do
  if ! grep -Eq "^\[editor_probe\] PASS probe $probe: " "$LOG_FILE"; then
    echo "$TAG FAIL: probe $probe did not pass" >&2
    fail=1
  fi
done
if [[ "$rc" -ne 0 || "$fail" -ne 0 ]] || ! grep -Fxq "[editor_probe] PASS" "$LOG_FILE" || grep -Fq "SCRIPT ERROR" "$LOG_FILE"; then
  grep -E "editor_probe|kanama:tools|SCRIPT ERROR|ERROR:|at: |hot-reload" "$LOG_FILE" | tail -n 80 >&2 || tail -n 80 "$LOG_FILE" >&2
  echo "$TAG FAIL (editor exit $rc): the editor probes (log: $LOG_FILE)" >&2
  exit 1
fi
echo "$TAG PASS"
