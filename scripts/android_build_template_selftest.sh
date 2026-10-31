#!/usr/bin/env bash
# Self-test for drop_stale_android_build_template (scripts/android_common.sh, task 139 P14 / U18): Godot 4.8
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
