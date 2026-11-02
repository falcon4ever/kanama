#!/usr/bin/env bash
# store_addon_editor_smoke.sh -- the store addon's editor path, as a user takes it: a plain Godot project with
# a settings.gradle.kts of its own, the packaged store addon unzipped into it, and the Kanama Tools editor
# plugin's Set Up Kanama Project and Build Scripts (scripts/check_editor_project_setup.gd drives them in a
# headless editor). The setup runs the addon's .sh, or its .ps1 on Windows; the build starts the Gradle client
# the way the plugin does on this OS; the status light must end green. package.yml runs it on Windows, where
# no other check reaches the plugin's launch code.
#
# usage: scripts/store_addon_editor_smoke.sh /path/to/kanama-store-addon.zip /path/to/godot
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TAG="[store_addon_editor_smoke]"
# shellcheck source=scripts/editor_check_lib.sh
source "$ROOT_DIR/scripts/editor_check_lib.sh"
if [[ $# -ne 2 ]]; then
  echo "usage: $0 /path/to/kanama-store-addon.zip /path/to/godot" >&2
  exit 2
fi
zip_path="$1"
godot_bin="$2"
[[ -f "$zip_path" ]] || { echo "$TAG no such zip: $zip_path" >&2; exit 2; }

work="$(mktemp -d "${TMPDIR:-/tmp}/kanama_store_editor.XXXXXX")"
# An interrupted run stops the editor and its Gradle client first (editor_check_lib.sh, run_bounded_stop).
trap 'run_bounded_stop; rm -rf "$work"' EXIT
run_bounded_signal_traps
project="$work/project"
mkdir -p "$project"
python3 - "$zip_path" "$project" <<'PY'
import sys, zipfile
zipfile.ZipFile(sys.argv[1]).extractall(sys.argv[2])
PY
editor_check_plugin "$project" project_setup_test "$ROOT_DIR/scripts/check_editor_project_setup.gd"
editor_check_project_godot "$project" kanama-plain-project "kanama_tools project_setup_test" \
  '[kanama]' '' 'tools/java_preflight_enabled=false'
# The settings file check_editor_project_setup.gd expects the project to start with.
printf '%s\n' "// the user's own settings.gradle.kts" 'rootProject.name = "mine"' >"$project/settings.gradle.kts"

project_for_godot="$project"
case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      project_for_godot="$(cygpath -m "$project")"
    fi
    ;;
esac

log="$work/editor.log"
rc=0
# The check plugin quits the editor when it is done (it gives up itself after 420 s); the bound covers an
# editor that never gets that far.
# No Gradle daemon (GRADLE_OPTS reaches the build through the plugin's launch): a daemon would outlive the
# check, and on Windows it inherits the console Godot's output pipe, so the console wrapper never saw it close
# and the editor never exited.
run_bounded 900 "$TAG" env GRADLE_OPTS="${GRADLE_OPTS:+$GRADLE_OPTS }-Dorg.gradle.daemon=false" \
  "$godot_bin" --headless --editor --path "$project_for_godot" >"$log" 2>&1 || rc=$?
if [[ "$rc" -ne 0 ]] || ! grep -Fq "[project_setup] PASS" "$log" || grep -Fq "SCRIPT ERROR" "$log"; then
  grep -E "project_setup|kanama:tools|kanama\]|SCRIPT ERROR|ERROR:|at: " "$log" >&2 || true # justified: the tail below
  echo "$TAG editor log tail:" >&2
  tail -n 80 "$log" >&2
  echo "$TAG FAIL (exit $rc): the store addon's Set Up Kanama Project and Build Scripts in the editor" >&2
  exit 1
fi
grep -E "\[kanama:tools\] (Running|Build JDK|Set Up)" "$log" || true # justified: context lines for the log only
echo "$TAG PASS"
