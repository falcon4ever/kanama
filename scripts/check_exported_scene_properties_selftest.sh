#!/usr/bin/env bash
# Self-test for scripts/check_exported_scene_properties.gd (task 119): a red run and a green run.
#
# Copies scripts/fixtures/exported_scene_check/ (GDScript only — no Kanama needed) to a temp dir,
# exports it headless for Web so Godot converts the text scenes to binary, then:
#   green — the converted main.scn keeps the override `amp = 0.0` on the instanced sub-scene's
#           CHILD node (an override node: no type/instance/script of its own), so the check PASSES
#           and reports one script property checked on main.tscn;
#   red   — a property is added to the SOURCE main.tscn after the export (`extra = 5`, default 1),
#           so the converted scene lacks it and the check must FAIL naming node `Sub/inner`.
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
green="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" 2>&1 || true)"
printf '%s\n' "$green" | grep -E '^\[check_exported_scenes\]' | sed 's/^/    /'
if ! printf '%s\n' "$green" | grep -q 'PASS: 2 converted scene(s)'; then
  echo "[scene_check_selftest] FAIL: expected PASS over 2 converted scenes" >&2
  exit 1
fi
if ! printf '%s\n' "$green" | grep -qE 'ok res://main.tscn \([0-9]+ nodes, 1 script properties checked\)'; then
  echo "[scene_check_selftest] FAIL: the override node's property was not checked (expected 1 on main.tscn)" >&2
  exit 1
fi

echo "[scene_check_selftest] red run: a source property the export never saw must be reported"
# Insert beside the existing override (a property line after the [editable] tag would not parse).
/usr/bin/sed -i '' 's/^amp = 0.0$/amp = 0.0\
extra = 5/' "$work/main.tscn"
red="$("$GODOT_BIN" --headless --path "$work" --script "$CHECK" 2>&1 || true)"
printf '%s\n' "$red" | grep -E '^\[check_exported_scenes\] FAIL' | sed 's/^/    /'
if ! printf '%s\n' "$red" | grep -qE "FAIL res://main.tscn: node '[^']*inner' \(Node\) lost script property 'extra'"; then
  echo "[scene_check_selftest] FAIL: the missing override property was not reported" >&2
  exit 1
fi
echo "[scene_check_selftest] PASS (green run kept the override, red run caught the loss)"
