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
if [[ $# -ne 2 ]]; then
  echo "usage: $0 /path/to/kanama-store-addon.zip /path/to/godot" >&2
  exit 2
fi
zip_path="$1"
godot_bin="$2"
[[ -f "$zip_path" ]] || { echo "$TAG no such zip: $zip_path" >&2; exit 2; }

work="$(mktemp -d "${TMPDIR:-/tmp}/kanama_store_editor.XXXXXX")"
trap 'rm -rf "$work"' EXIT
project="$work/project"
mkdir -p "$project/addons/project_setup_test"
python3 - "$zip_path" "$project" <<'PY'
import sys, zipfile
zipfile.ZipFile(sys.argv[1]).extractall(sys.argv[2])
PY
cp "$ROOT_DIR/scripts/check_editor_project_setup.gd" "$project/addons/project_setup_test/plugin.gd"
printf '%s\n' '[plugin]' '' 'name="project_setup_test"' 'description=""' 'author=""' 'version=""' 'script="plugin.gd"' \
  >"$project/addons/project_setup_test/plugin.cfg"
printf '%s\n' 'config_version=5' '' '[application]' '' 'config/name="kanama-plain-project"' '' '[editor_plugins]' '' \
  'enabled=PackedStringArray("res://addons/kanama_tools/plugin.cfg", "res://addons/project_setup_test/plugin.cfg")' '' \
  '[kanama]' '' 'tools/java_preflight_enabled=false' >"$project/project.godot"
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
# --quit-after bounds the run if the check plugin never finishes (it quits on its own when done).
"$godot_bin" --headless --editor --path "$project_for_godot" --quit-after 200000 >"$log" 2>&1 || rc=$?
if [[ "$rc" -ne 0 ]] || ! grep -Fq "[project_setup] PASS" "$log" || grep -Fq "SCRIPT ERROR" "$log"; then
  grep -E "project_setup|kanama:tools|kanama\]|SCRIPT ERROR|ERROR:|at: " "$log" >&2 || tail -n 80 "$log" >&2
  echo "$TAG FAIL (exit $rc): the store addon's Set Up Kanama Project and Build Scripts in the editor" >&2
  exit 1
fi
grep -E "\[kanama:tools\] (Running|Build JDK|Set Up)" "$log" || true # justified: context lines for the log only
echo "$TAG PASS"
