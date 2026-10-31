#!/usr/bin/env bash
# Self-test for ensure_android_build_template and drop_stale_android_build_template (scripts/android_common.sh, task 139 P14 / U18): Godot
# refuses an export when res://android/build comes from another Godot version, so a template whose
# android/.build_version differs from the pin must be deleted, and a current one must be kept.
# No Godot, Gradle or device needed. usage: scripts/android_build_template_selftest.sh
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
ANDROID_TAG="[android-template-selftest]"
# shellcheck source=scripts/android_common.sh
source "$ROOT_DIR/scripts/android_common.sh"

SCRATCH="$(mktemp -d)"
trap 'rm -rf "$SCRATCH"' EXIT
DEMO_DIR="$SCRATCH/demo"

pin="$(android_godot_version)"

fixture() { # <build_version content, or "-" for none>
  rm -rf "$DEMO_DIR"
  mkdir -p "$DEMO_DIR/android/build"
  : >"$DEMO_DIR/android/build/build.gradle"
  if [[ "$1" != "-" ]]; then printf '%s\n' "$1" >"$DEMO_DIR/android/.build_version"; fi
}

# Through ensure_android_build_template, with a fake editor and a fake android_source.zip, so deleting the
# call from it goes red.
FAKE_BIN="$SCRATCH/godot"
# shellcheck disable=SC2016  # the fake script expands these itself
printf '#!/bin/sh\n[ "$1" = "--version" ] && echo "${FAKE_GODOT_VERSION}.official.0123456"\nexit 0\n' >"$FAKE_BIN"
chmod +x "$FAKE_BIN"
export KANAMA_GODOT_TEMPLATES_DIR="$SCRATCH/templates"
mkdir -p "$KANAMA_GODOT_TEMPLATES_DIR/$pin"
python3 - "$KANAMA_GODOT_TEMPLATES_DIR/$pin/android_source.zip" <<'PY'
import sys, zipfile
with zipfile.ZipFile(sys.argv[1], "w") as z:
    z.writestr("build.gradle", "fresh\n")
PY
GODOT_BIN="$FAKE_BIN"
APK_PATH="$SCRATCH/out.apk"

fixture "4.6.stable"
FAKE_GODOT_VERSION="$pin" ensure_android_build_template >/dev/null
if [[ "$(tr -d '[:space:]' <"$DEMO_DIR/android/.build_version")" != "$pin" || "$(cat "$DEMO_DIR/android/build/build.gradle")" != "fresh" ]]; then
  echo "$ANDROID_TAG FAIL: a template for Godot 4.6.stable (pin $pin) was kept by ensure_android_build_template" >&2
  exit 1
fi

fixture "4.6.stable"
if out="$(FAKE_GODOT_VERSION="${pin}.mono" ensure_android_build_template 2>&1)"; then
  echo "$ANDROID_TAG FAIL: an editor that is not the pinned Godot was accepted" >&2
  exit 1
fi
if [[ "$out" != *"kanamaGodotVersion pins '$pin'"* || -e "$DEMO_DIR/android/build/.gdignore" ]]; then
  echo "$ANDROID_TAG FAIL: the editor/pin mismatch did not stop with the one-line message: $out" >&2
  exit 1
fi

fixture "4.6.stable"
drop_stale_android_build_template
if [[ -e "$DEMO_DIR/android/build" || -e "$DEMO_DIR/android/.build_version" ]]; then
  echo "$ANDROID_TAG FAIL: a template for Godot 4.6.stable (pin $pin) was kept" >&2
  exit 1
fi

fixture "-"
drop_stale_android_build_template
if [[ -e "$DEMO_DIR/android/build" ]]; then
  echo "$ANDROID_TAG FAIL: a template with no .build_version was kept" >&2
  exit 1
fi

fixture "$pin"
drop_stale_android_build_template
if [[ ! -f "$DEMO_DIR/android/build/build.gradle" ]]; then
  echo "$ANDROID_TAG FAIL: a template for the pinned Godot ($pin) was deleted" >&2
  exit 1
fi
echo "$ANDROID_TAG OK: stale and unversioned templates are deleted, the pinned one ($pin) is kept"
