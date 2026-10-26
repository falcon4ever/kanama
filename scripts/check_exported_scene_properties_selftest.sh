#!/usr/bin/env bash
# Self-test for scripts/check_exported_scene_properties.gd (task 119): a red run and a green run.
#
# Copies scripts/fixtures/exported_scene_check/ (GDScript only — no Kanama needed) to a temp dir,
# exports it headless for Web so Godot converts the text scenes to binary, then:
#   green — the converted main.scn keeps the override `amp = 0.0` on the instanced sub-scene's
#           CHILD node (an override node: no type/instance/script of its own), so the check PASSES
#           and reports one script property checked on main.tscn;
#   red   — a property is added to the SOURCE main.tscn after the export (`extra = 5`, default 1),
#           so the converted scene lacks it and the check must FAIL naming node `Sub/inner`;
#   none  — before the export, `-- --require-converted` must FAIL (nothing converted), and pass after it;
#   off   — with editor/export/convert_text_resources_to_binary=false, `--require-converted` passes with a note;
#   leak  — the fixture's autoplaying Music autoload must not leak at exit (the green run's output).
# The green run also covers a scriptless AnimationPlayer whose `libraries` (a property ClassDB does not
# list) the export re-packs away: no script, so no loss to report (the TPS demo's Skeleton3D bones too).
# Needs the pinned Godot's Web export templates (web_nothreads_*.zip) installed.
# Finding while building this (task 119): Godot's re-pack keeps an override on an instanced child
# ONLY when the instance is editable (`[editable path="Sub"]`, which the editor always writes); a
# hand-written .tscn without it loses the override node entirely on export — and the check reports
# exactly that, so the fixture carries the marker.
#
# usage: scripts/check_exported_scene_properties_selftest.sh /path/to/godot
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
GODOT_BIN="${1:?usage: $0 /path/to/godot}"
CHECK="$ROOT_DIR/scripts/check_exported_scene_properties.gd"
work="$(mktemp -d "${TMPDIR:-/tmp}/kanama_scene_check_selftest.XXXXXX")"
trap 'rm -rf "$work"' EXIT
cp -R "$ROOT_DIR/scripts/fixtures/exported_scene_check/." "$work/"
mkdir -p "$work/build"

echo "[scene_check_selftest] red run: --require-converted before any export (nothing converted) must fail"
none_rc=0
none="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" -- --require-converted 2>&1)" || none_rc=$?
if [[ "$none_rc" -eq 0 ]] || ! printf '%s\n' "$none" | grep -q 'FAIL: no res://.godot/exported directory'; then
  echo "[scene_check_selftest] FAIL: --require-converted passed (exit $none_rc) on a project with no converted scene" >&2
  printf '%s\n' "$none" | tail -5 >&2
  exit 1
fi
rm -rf "$work/.godot"

echo "[scene_check_selftest] export (Web, headless) -> converts main.tscn/sub.tscn to binary"
if ! "$GODOT_BIN" --headless --path "$work" --export-release Web "$work/build/index.html" >"$work/export.log" 2>&1; then
  echo "[scene_check_selftest] FAIL: the fixture export failed (Web templates installed for this Godot?)" >&2
  tail -20 "$work/export.log" >&2
  exit 1
fi
if [[ ! -d "$work/.godot/exported" ]]; then
  echo "[scene_check_selftest] FAIL: no .godot/exported after the export" >&2
  exit 1
fi

echo "[scene_check_selftest] green run: the override node's non-default value must be kept and checked"
green_rc=0
green="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" 2>&1)" || green_rc=$?
# justified: echoing the evidence lines; the assertions on "$green" follow.
printf '%s\n' "$green" | grep -E '^\[check_exported_scenes\]' | sed 's/^/    /' || true
# Task 118: the exit status of the green run is part of the verdict (a check that prints PASS and exits non-zero is broken).
if [[ "$green_rc" -ne 0 ]]; then
  echo "[scene_check_selftest] FAIL: the green run exited $green_rc, expected 0" >&2
  exit 1
fi
if ! printf '%s\n' "$green" | grep -q 'PASS: 2 converted scene(s)'; then
  echo "[scene_check_selftest] FAIL: expected PASS over 2 converted scenes" >&2
  exit 1
fi
if ! "$GODOT_BIN" --headless --path "$work" --script "$CHECK" -- --require-converted >"$work/require.log" 2>&1; then
  echo "[scene_check_selftest] FAIL: --require-converted failed although 2 scenes were converted" >&2
  tail -5 "$work/require.log" >&2
  exit 1
fi
if ! printf '%s\n' "$green" | grep -qE 'ok res://main.tscn \([0-9]+ nodes, 1 script properties checked\)'; then
  echo "[scene_check_selftest] FAIL: the override node's property was not checked (expected 1 on main.tscn)" >&2
  exit 1
fi
# The fixture's Music autoload autoplays, as Squash the Creeps' MusicPlayer does. A check that lets it
# start leaks the playing stream at exit (Godot never drains its playback list), and every caller's
# scene-check step (android_smoke.sh, ios_visual_smoke.sh, ...) fails on that leak.
if printf '%s\n' "$green" | grep -qE 'leaked at exit|still in use at exit'; then
  printf '%s\n' "$green" | grep -E 'leaked at exit|still in use at exit' | sed 's/^/    /' >&2
  echo "[scene_check_selftest] FAIL: the scene check leaked at exit (the Music autoload's stream?)" >&2
  exit 1
fi

echo "[scene_check_selftest] red run: a source property the export never saw must be reported"
# Insert beside the existing override (a property line after the [editable] tag would not parse).
# perl, not `sed -i`: BSD and GNU sed disagree on the in-place flag and this must run on Linux CI too.
perl -pi -e 's/^amp = 0\.0$/amp = 0.0\nextra = 5/' "$work/main.tscn"
red_rc=0
red="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" 2>&1)" || red_rc=$?
# justified: echoing the evidence lines; the assertion on "$red" follows.
printf '%s\n' "$red" | grep -E '^\[check_exported_scenes\] FAIL' | sed 's/^/    /' || true
if [[ "$red_rc" -eq 0 ]]; then
  echo "[scene_check_selftest] FAIL: the red run exited 0, expected non-zero" >&2
  exit 1
fi
if ! printf '%s\n' "$red" | grep -qE "FAIL res://main.tscn: node '[^']*inner' \(Node\) lost script property 'extra'"; then
  echo "[scene_check_selftest] FAIL: the missing override property was not reported" >&2
  exit 1
fi
echo "[scene_check_selftest] conversion off: a project with convert_text_resources_to_binary=false must pass --require-converted"
# The demos' third-person controller sets this: its scenes ship as text with their values, and the cache
# left by the export above must not be compared (the red edit to main.tscn is still in place).
perl -pi -e 's/^export\/convert_text_resources_to_binary=true$/export\/convert_text_resources_to_binary=false/' "$work/project.godot"
grep -q '^export/convert_text_resources_to_binary=false$' "$work/project.godot" ||
  { echo "[scene_check_selftest] FAIL: could not turn conversion off in the fixture" >&2; exit 1; }
off_rc=0
off="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" -- --require-converted 2>&1)" || off_rc=$?
if [[ "$off_rc" -ne 0 ]] || ! printf '%s\n' "$off" | grep -q 'PASS: conversion disabled by the project'; then
  echo "[scene_check_selftest] FAIL: --require-converted did not pass (exit $off_rc) on a project that turns conversion off" >&2
  printf '%s\n' "$off" | tail -5 >&2
  exit 1
fi
echo "[scene_check_selftest] conversion off with a feature override: a '.web=true' override converts again, so the check must compare"
# Godot reads the setting with the preset's features: an export for that feature converts, and the red edit
# to main.tscn is still in place, so the check must fail on the lost property instead of passing early.
perl -pi -e 's/^(export\/convert_text_resources_to_binary=false)$/$1\nexport\/convert_text_resources_to_binary.web=true/' "$work/project.godot"
grep -q '^export/convert_text_resources_to_binary.web=true$' "$work/project.godot" ||
  { echo "[scene_check_selftest] FAIL: could not add the feature override in the fixture" >&2; exit 1; }
ovr_rc=0
ovr="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" -- --require-converted 2>&1)" || ovr_rc=$?
if [[ "$ovr_rc" -eq 0 ]] || printf '%s\n' "$ovr" | grep -q 'PASS: conversion disabled by the project'; then
  echo "[scene_check_selftest] FAIL: a feature override that converts again was ignored (exit $ovr_rc)" >&2
  printf '%s\n' "$ovr" | tail -5 >&2
  exit 1
fi
echo "[scene_check_selftest] PASS (green run kept the override and left no leak at exit, red run caught the loss, --require-converted fails with nothing converted and passes with conversion off unless a feature override converts again)"
