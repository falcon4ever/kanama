#!/usr/bin/env bash
# Download the macOS Godot editor of GODOT_VERSION (the dash form, the GitHub release tag) to <zip>.
# The release builds differ: 4.8's prereleases ship `macos.arm64` and `macos.x86_64` and no universal
# zip, 4.7.2 and its rc ship only `macos.universal`. Every macOS runner here is arm64, so the arm64 zip
# comes first. A release with neither fails the download. Both zips unpack to Godot.app.
set -euo pipefail

zip="${1:?usage: GODOT_VERSION=4.8-stable scripts/download_godot_macos.sh <zip>}"
base="https://github.com/godotengine/godot-builds/releases/download/${GODOT_VERSION:?GODOT_VERSION is not set}"
for suffix in macos.arm64.zip macos.universal.zip; do
  # A 404 is the expected answer for the name a release does not ship; the loop tries the next.
  if curl -fL --retry 3 -o "$zip" "$base/Godot_v${GODOT_VERSION}_${suffix}"; then
    echo "[download_godot_macos] $suffix of $GODOT_VERSION"
    exit 0
  fi
done
echo "[download_godot_macos] FAIL no macOS arm64 or universal editor in the $GODOT_VERSION release" >&2
exit 1
