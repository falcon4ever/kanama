#!/usr/bin/env bash
# Self-test for the editor export guard (addons/kanama_tools/kanama_export_guard.gd): headless Web
# exports of a scratch project with the real desktop addon from example_project, three ways:
#   green   — the scene attaches SelfSmoke.kt, which kanama-scripts.jar contains: export exits 0 and
#             prints nothing from the guard;
#   stale   — the scene attaches NotInJar.kt, which the jar does not contain: export exits non-zero, the
#             message names the script and the jar, and no scene was converted (no stripped copy cached);
#   missing — the desktop addon (addons/kanama) is removed: export exits non-zero, the message says no
#             .kt loader exists.
# Needs the pinned Godot's Web export templates (web_nothreads_release.zip) and a JDK 25+ (JAVA_HOME).
#
# usage: scripts/export_guard_selftest.sh /path/to/godot
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
GODOT_BIN="${1:?usage: $0 /path/to/godot}"
FIXTURE="$ROOT_DIR/scripts/fixtures/export_guard"
work="$(mktemp -d "${TMPDIR:-/tmp}/kanama_export_guard_selftest.XXXXXX")"
trap 'rm -rf "$work"' EXIT

fail() {
  echo "[export_guard_selftest] FAIL: $1" >&2
  if [[ -n "${2:-}" && -f "$2" ]]; then
    tail -n 40 "$2" >&2
  fi
  exit 1
}

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >"$work/sync.log" 2>&1 || fail "syncExampleAddonJar failed" "$work/sync.log"

# project <name> <script> [keep-addon]: a scratch project whose only scene attaches res://<script>.
project() {
  local dir="$work/$1" script="$2" addon="${3:-yes}"
  mkdir -p "$dir/addons"
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

# export_project <name>: sets rc and writes the log; the export is expected to fail in the red runs.
# Godot 4.7.2 with the Kanama addon can crash (exit 134, after the error is printed) at the end of a
# FAILING export of a project that has no .godot folder yet, whatever the cause of the failure; the
# red runs only need a non-zero exit, so both 1 and 134 pass.
export_project() {
  local dir="$work/$1"
  mkdir -p "$dir/build"
  rc=0
  "$GODOT_BIN" --headless --path "$dir" --export-release Web "$dir/build/index.html" >"$work/$1.log" 2>&1 || rc=$?
}

echo "[export_guard_selftest] green: a script the jar contains must export without a word from the guard"
project green SelfSmoke.kt
export_project green
[[ "$rc" -eq 0 ]] || fail "the green export exited $rc, expected 0 (Web templates and JDK 25 installed?)" "$work/green.log"
if grep -q "Export stopped" "$work/green.log"; then
  fail "the guard spoke on a project whose scripts all bind" "$work/green.log"
fi

echo "[export_guard_selftest] red: a script missing from kanama-scripts.jar must stop the export"
project stale NotInJar.kt
export_project stale
[[ "$rc" -ne 0 ]] || fail "the stale export exited 0, expected non-zero" "$work/stale.log"
grep -q "Export stopped" "$work/stale.log" || fail "the stale export did not print the guard message" "$work/stale.log"
grep -q "res://NotInJar.kt (not in kanama-scripts.jar)" "$work/stale.log" || fail "the message does not name the script and the jar" "$work/stale.log"
grep -q "installAddonJar" "$work/stale.log" || fail "the message does not name the fix" "$work/stale.log"
if [[ -d "$work/stale/.godot/exported" ]] && find "$work/stale/.godot/exported" -name '*.scn' | grep -q .; then
  fail "a scene was converted although the guard stopped the export (a stripped copy is now cached)"
fi

echo "[export_guard_selftest] red: no desktop addon must stop the export"
project missing SelfSmoke.kt no
export_project missing
[[ "$rc" -ne 0 ]] || fail "the export without the addon exited 0, expected non-zero" "$work/missing.log"
grep -q "res://SelfSmoke.kt (no .kt loader" "$work/missing.log" || fail "the message does not say the .kt loader is missing" "$work/missing.log"

echo "[export_guard_selftest] PASS (green exported, stale jar and missing addon both stopped with the message)"
