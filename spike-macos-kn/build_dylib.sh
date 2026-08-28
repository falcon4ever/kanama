#!/usr/bin/env bash
# Task 75 spike — stage B: build a macOS arm64 GDExtension .dylib from the
# iOS C shim + the Kotlin/Native macosArm64 runtime.
#
# Two steps, because only Kotlin/Native's own linker invocation knows the
# platform framework set its platform.darwin / platform.posix caches need:
#   1. clang compiles the shim to a .o here
#   2. Gradle's linkDebugSharedMacosArm64 links that .o into the K/N dylib
#      (see the SPIKE block in ios-runtime/build.gradle.kts)
#
# Usage: spike-macos-kn/build_dylib.sh
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
OUT="$ROOT/spike-macos-kn/build"
SDK="$(xcrun --sdk macosx --show-sdk-path)"
MIN_MACOS=13.0

mkdir -p "$OUT"

MODE="${1:-debug}"
case "$MODE" in
  debug)   COPT="-O0"; GRADLE_TASK="linkDebugSharedMacosArm64";   BINDIR="debugShared" ;;
  release) COPT="-O2"; GRADLE_TASK="linkReleaseSharedMacosArm64"; BINDIR="releaseShared" ;;
  *) echo "usage: $0 [debug|release]" >&2; exit 2 ;;
esac
shift || true

echo "==> [1/2] compiling shim (macOS arm64, $MODE, $COPT)"
clang -arch arm64 \
  -isysroot "$SDK" \
  -mmacosx-version-min="$MIN_MACOS" \
  "$COPT" \
  -fvisibility=hidden \
  -I "$ROOT/gdextension" \
  -c "$ROOT/ios/bootstrap/kanama_ios_shim.c" \
  -o "$OUT/kanama_shim_$MODE.o"
ls -lh "$OUT/kanama_shim_$MODE.o"

echo "==> [2/2] linking dylib via Kotlin/Native"
(cd "$ROOT" && ./gradlew ":ios-runtime:$GRADLE_TASK" --console=plain "$@")

DYLIB="$ROOT/ios-runtime/build/bin/macosArm64/$BINDIR/libkanama_macos_kn.dylib"
echo "==> result"
ls -lh "$DYLIB"
echo "-- kanama_entry exported?"
nm -gU "$DYLIB" | grep "kanama_entry" || echo "   !! NOT EXPORTED"
