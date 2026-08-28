#!/usr/bin/env bash
# Task 75 spike — stages C/D: load the Kotlin/Native dylib as a desktop
# GDExtension and run the probe scene headless.
set -uo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
GODOT="${KANAMA_GODOT_BIN:-/Applications/Godot.app/Contents/MacOS/Godot}"
PROJ="$ROOT/spike-macos-kn/probe-project"
DYLIB="$ROOT/ios-runtime/build/bin/macosArm64/debugShared/libkanama_macos_kn.dylib"

[ -f "$DYLIB" ] || { echo "missing dylib: $DYLIB (run build_dylib.sh)" >&2; exit 1; }

mkdir -p "$PROJ/addons/kanama"
cp "$DYLIB" "$PROJ/addons/kanama/libkanama_macos_kn.dylib"

echo "==> godot: $("$GODOT" --version)"
echo "==> running headless (60 frames)"
"$GODOT" --headless --path "$PROJ" --quit-after 60 2>&1
echo "==> exit=$?"
