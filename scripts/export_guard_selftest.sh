#!/usr/bin/env bash
# Self-test for the editor export guard (addons/kanama_tools/kanama_export_guard.gd): headless exports of
# a scratch project with the real desktop addon from example_project:
#   green          — Web: the scene attaches SelfSmoke.kt, which kanama-scripts.jar contains: the export
#                    exits 0, writes the game and prints nothing from the guard;
#   stale          — Web: the scene attaches NotInJar.kt, which the jar does not contain;
#   missing        — Web: the desktop addon (addons/kanama) is removed;
#   stale android  — Android APK (no Gradle), when the Android export template is installed;
#   stale macos    — macOS, when the macOS export template is installed;
#   min sdk        — Android Gradle build with Min SDK 26 and a script that binds: the guard's Android 13
#                    rule must stop it (a stand-in build.gradle satisfies Godot's template check; the guard
#                    ends the export before Gradle would run).
# Every red export must print the guard's stable line `Kanama: Export stopped` with the reason, exit
# non-zero, and leave nothing behind: no file in the export folder and no converted scene in the cache.
# Godot's Android, iOS and macOS exporters and --export-pack ignore a plugin's error and exit 0, so the
# guard ends a command-line export before it writes anything; these rows hold it to that on each exporter.
# Needs the pinned Godot's Web export templates and a JDK 25+ (JAVA_HOME). A platform row whose template
# is not installed, or whose editor has no Android SDK or keystore configured, prints SKIP with the reason.
#
# usage: scripts/export_guard_selftest.sh /path/to/godot
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
GODOT_BIN="${1:?usage: $0 /path/to/godot}"
FIXTURE="$ROOT_DIR/scripts/fixtures/export_guard"
STOP_LINE="Kanama: Export stopped:"
work="$(mktemp -d "${TMPDIR:-/tmp}/kanama_export_guard_selftest.XXXXXX")"
trap 'rm -rf "$work"' EXIT

fail() {
  echo "[export_guard_selftest] FAIL: $1" >&2
  if [[ -n "${2:-}" && -f "$2" ]]; then
    tail -n 40 "$2" >&2
  fi
  exit 1
}

godot_version="$("$GODOT_BIN" --version | head -n 1 | awk -F. '{print $1"."$2"."$3"."$4}')"
templates_root="${KANAMA_GODOT_TEMPLATES_DIR:-}"
if [[ -z "$templates_root" ]]; then
  if [[ -d "$HOME/Library/Application Support/Godot/export_templates" ]]; then
    templates_root="$HOME/Library/Application Support/Godot/export_templates"
  else
    templates_root="${XDG_DATA_HOME:-$HOME/.local/share}/godot/export_templates"
  fi
fi
templates="$templates_root/$godot_version"

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >"$work/sync.log" 2>&1 || fail "syncExampleAddonJar failed" "$work/sync.log"

# project <name> <script> [keep-addon]: a scratch project whose only scene attaches res://<script>.
# No separate --import: the export imports the project first, and a stopped export ends before
# Godot's shutdown runs.
project() {
  local dir="$work/$1" script="$2" addon="${3:-yes}"
  mkdir -p "$dir/addons" "$dir/build"
  cp "$FIXTURE/project.godot" "$FIXTURE/export_presets.cfg" "$dir/"
  cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$dir/addons/"
  if [[ "$addon" == "yes" ]]; then
    cp -R "$ROOT_DIR/example_project/addons/kanama" "$dir/addons/"
  fi
  case "$script" in
    SelfSmoke.kt) cp "$ROOT_DIR/example_project/SelfSmoke.kt" "$dir/" ;;
    *) printf 'class NotInJar\n' >"$dir/$script" ;;
  esac
  sed "s#__SCRIPT__#$script#" "$FIXTURE/main.tscn.in" >"$dir/main.tscn"
}

# export_project <name> <preset> <file> [debug|release]: sets rc and writes the log. Android exports
# debug (a release export needs a release keystore); the others release.
export_project() {
  local dir="$work/$1"
  rc=0
  "$GODOT_BIN" --headless --path "$dir" "--export-${4:-release}" "$2" "$dir/build/$3" >"$work/$1.log" 2>&1 || rc=$?
}

# expect_stopped <name> <reason text>: the red verdict shared by every exporter.
expect_stopped() {
  local name="$1" reason="$2" dir="$work/$1" log="$work/$1.log"
  [[ "$rc" -ne 0 ]] || fail "the $name export exited 0, expected non-zero" "$log"
  grep -qF "$STOP_LINE" "$log" || fail "the $name export did not print the guard's line '$STOP_LINE'" "$log"
  grep -qF "$reason" "$log" || fail "the $name message does not name the script and the reason ($reason)" "$log"
  grep -q "installAddonJar" "$log" || fail "the $name message does not name the fix" "$log"
  grep -q "kanama/tools/export_guard_enabled" "$log" || fail "the $name message does not name the opt-out setting" "$log"
  if find "$dir/build" -mindepth 1 | grep -q .; then
    fail "the $name export left files in its export folder: $(find "$dir/build" -mindepth 1 | head -3 | tr '\n' ' ')"
  fi
  if [[ -d "$dir/.godot/exported" ]] && find "$dir/.godot/exported" -name '*.scn' | grep -q .; then
    fail "the $name export converted a scene although the guard stopped it (a stripped copy is now cached)"
  fi
  echo "[export_guard_selftest]   stopped: exit $rc, export folder empty, nothing converted"
}

echo "[export_guard_selftest] green (Web): a script the jar contains must export without a word from the guard"
project green SelfSmoke.kt
export_project green Web index.html
[[ "$rc" -eq 0 ]] || fail "the green export exited $rc, expected 0 (Web templates and JDK 25 installed?)" "$work/green.log"
if grep -q "Kanama: Export stopped" "$work/green.log"; then
  fail "the guard spoke on a project whose scripts all bind" "$work/green.log"
fi
[[ -f "$work/green/build/index.pck" ]] || fail "the green export wrote no index.pck" "$work/green.log"

echo "[export_guard_selftest] red (Web): a script missing from kanama-scripts.jar must stop the export"
project stale NotInJar.kt
export_project stale Web index.html
expect_stopped stale "res://NotInJar.kt (not in kanama-scripts.jar"

echo "[export_guard_selftest] red (Web): no desktop addon must stop the export"
project missing SelfSmoke.kt no
export_project missing Web index.html
expect_stopped missing "res://SelfSmoke.kt (no .kt loader"

# platform_row <name> <preset> <file> <template file> <debug|release>: a red export on an exporter that
# ignores plugin errors.
platform_row() {
  local name="$1" preset="$2" file="$3" template="$4" mode="$5"
  if [[ ! -f "$templates/$template" ]]; then
    echo "[export_guard_selftest] SKIP $name: no $preset export template ($templates/$template)"
    return
  fi
  echo "[export_guard_selftest] red ($preset): a script missing from kanama-scripts.jar must stop the export"
  project "$name" NotInJar.kt
  export_project "$name" "$preset" "$file" "$mode"
  if grep -q "due to configuration errors" "$work/$name.log"; then
    echo "[export_guard_selftest] SKIP $name: this editor cannot export $preset at all (configuration errors before the guard runs):"
    grep -A3 "due to configuration errors" "$work/$name.log" | sed 's/^/    /'
    return
  fi
  expect_stopped "$name" "res://NotInJar.kt (not in kanama-scripts.jar"
}

platform_row stale_android Android game.apk android_debug.apk debug
platform_row stale_macos macOS game.zip macos.zip release

if [[ ! -f "$templates/android_debug.apk" ]]; then
  echo "[export_guard_selftest] SKIP min_sdk: no Android export template ($templates/android_debug.apk)"
else
  echo "[export_guard_selftest] red (Android Gradle): Min SDK 26 must stop the export"
  project min_sdk SelfSmoke.kt
  mkdir -p "$work/min_sdk/android/build"
  : >"$work/min_sdk/android/build/.gdignore"
  printf '// stand-in: the guard stops the export before Gradle runs\n' >"$work/min_sdk/android/build/build.gradle"
  export_project min_sdk AndroidGradle game.apk debug
  if grep -q "due to configuration errors" "$work/min_sdk.log"; then
    echo "[export_guard_selftest] SKIP min_sdk: this editor cannot export Android at all (configuration errors before the guard runs):"
    grep -A3 "due to configuration errors" "$work/min_sdk.log" | sed 's/^/    /'
  else
    [[ "$rc" -ne 0 ]] || fail "the min_sdk export exited 0, expected non-zero" "$work/min_sdk.log"
    grep -qF "$STOP_LINE Android 13 or newer is required, and this preset's Min SDK is 26. Set Min SDK to 33" "$work/min_sdk.log" ||
      fail "the min_sdk export did not print the guard's Min SDK line" "$work/min_sdk.log"
    if grep -q "not in kanama-scripts.jar" "$work/min_sdk.log"; then
      fail "the min_sdk export reported a script problem; only the Min SDK may stop it" "$work/min_sdk.log"
    fi
    if find "$work/min_sdk/build" -mindepth 1 | grep -q .; then
      fail "the min_sdk export left files in its export folder"
    fi
    echo "[export_guard_selftest]   stopped: exit $rc, the Min SDK line, export folder empty"
  fi
fi

echo "[export_guard_selftest] PASS (green exported; stale jar, missing addon and Android Min SDK 26 stopped every export with the message and no artifact)"
