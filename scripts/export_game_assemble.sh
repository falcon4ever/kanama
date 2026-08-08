#!/usr/bin/env bash
# Task 63 (issue #102) — assemble an exported desktop game into an
# unpack-and-play layout: place the jlink runtime image, kanama.jar, and the
# project kanama-scripts.jar next to the exported Kanama bootstrap library so
# players never install a JDK.
#
# Godot's export copies the gdextension bootstrap library into the export but
# knows nothing about the jars or the runtime image; this script is the v1
# assembly step (a `kanama_tools` export plugin automating it is a later
# upgrade). The bootstrap probes `runtime/<jvm layout>` app-relative BEFORE
# JAVA_HOME (bootstrap/bootstrap.c), so the assembled game prefers its bundled
# runtime wherever it is unpacked.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/export_game_assemble.sh [options] /absolute/path/to/exported-game

  exported-game: the exported macOS .app bundle, or the export directory that
                 contains the exported binary (Windows/Linux).

Options:
  --runtime DIR       jlink runtime image produced by `./gradlew jlinkGameRuntime`
                      (default: <repo>/build/game-runtime/runtime). For an export
                      targeting another platform, point this at the image from
                      `./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=...`
                      (build/game-runtime/<classifier>/runtime). The image's
                      platform must match the export's.
  --kanama-jar FILE   Kanama runtime jar (default: <repo>/build/libs/kanama.jar)
  --scripts-jar FILE  The project's compiled kanama-scripts.jar (required).
  --help, -h          Show this help.
EOF
}

runtime_dir="$ROOT_DIR/build/game-runtime/runtime"
kanama_jar="$ROOT_DIR/build/libs/kanama.jar"
scripts_jar=""
export_target=""

while [[ $# -gt 0 ]]; do
  case "$1" in
    --runtime)
      runtime_dir="${2:-}"
      shift 2
      ;;
    --kanama-jar)
      kanama_jar="${2:-}"
      shift 2
      ;;
    --scripts-jar)
      scripts_jar="${2:-}"
      shift 2
      ;;
    --help|-h)
      usage
      exit 0
      ;;
    --*)
      echo "[export_game_assemble] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      if [[ -n "$export_target" ]]; then
        echo "[export_game_assemble] unexpected extra argument: $1" >&2
        usage
        exit 2
      fi
      export_target="$1"
      shift
      ;;
  esac
done

if [[ -z "$export_target" || -z "$scripts_jar" ]]; then
  echo "[export_game_assemble] exported-game path and --scripts-jar are required" >&2
  usage
  exit 2
fi
if [[ ! -d "$export_target" ]]; then
  echo "[export_game_assemble] exported game not found: $export_target" >&2
  exit 2
fi
if [[ ! -f "$kanama_jar" ]]; then
  echo "[export_game_assemble] kanama.jar not found: $kanama_jar (run ./gradlew jar)" >&2
  exit 2
fi
if [[ ! -f "$scripts_jar" ]]; then
  echo "[export_game_assemble] scripts jar not found: $scripts_jar" >&2
  exit 2
fi

# The runtime image must contain a server JVM library, and it must be the one
# the EXPORT's platform needs. Assembly is host-independent: a macOS developer
# exporting a Windows game runs this with a Windows runtime image built by
# `./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64`. Note the
# Windows layout is bin\server\jvm.dll, not lib/server.
jvm_server_lib=""
runtime_platform=""
for candidate in "macos:lib/server/libjvm.dylib" "linux:lib/server/libjvm.so" "windows:bin/server/jvm.dll"; do
  if [[ -f "$runtime_dir/${candidate#*:}" ]]; then
    runtime_platform="${candidate%%:*}"
    jvm_server_lib="${candidate#*:}"
    break
  fi
done
if [[ -z "$jvm_server_lib" ]]; then
  echo "[export_game_assemble] no server JVM library under: $runtime_dir" >&2
  echo "[export_game_assemble] run: ./gradlew jlinkGameRuntime          (this platform)" >&2
  echo "[export_game_assemble] or:  ./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64" >&2
  exit 2
fi

# Anchor everything on the exported bootstrap library: the bootstrap resolves
# kanama.jar, kanama-scripts.jar, and runtime/ relative to its own location
# (own directory, then up to two parents, each level also probed through a
# Resources/ subdirectory), so wherever Godot placed it is what the payload is
# anchored to. Windows/Linux: payload sits right next to the library (and the
# exported binary). macOS: Godot puts the dylib in Contents/Frameworks, but
# codesign treats jars and loose runtime files as unsigned "nested code"
# under Frameworks/ or Contents/, so the payload goes into
# Contents/Resources/ (next to the exported .pck) where the bundle seal
# hashes it as plain resources — still inside the bootstrap's probe walk.
bootstrap_lib="$(
  find "$export_target" \
    \( -name libkanama_bootstrap.dylib \
       -o -name libkanama_bootstrap.so \
       -o -name kanama_bootstrap.dll \) \
    -type f -print | head -n 1
)"
if [[ -z "$bootstrap_lib" ]]; then
  echo "[export_game_assemble] no Kanama bootstrap library inside: $export_target" >&2
  echo "[export_game_assemble] export the game from Godot first; the Kanama gdextension must be part of the export" >&2
  exit 1
fi

# The bootstrap library Godot exported names the export's platform. Refuse a
# runtime image built for a different one: shipping a macOS runtime inside a
# Windows export produces a game that dies at launch on the player's machine
# with nothing but a missing-libjvm message, and there is no later gate that
# would catch it.
case "$bootstrap_lib" in
  *.dylib) export_platform="macos" ;;
  *.so) export_platform="linux" ;;
  *.dll) export_platform="windows" ;;
  *) export_platform="unknown" ;;
esac
if [[ "$export_platform" != "$runtime_platform" ]]; then
  echo "[export_game_assemble] platform mismatch: the export is $export_platform" >&2
  echo "[export_game_assemble]   ($bootstrap_lib)" >&2
  echo "[export_game_assemble] but the runtime image is $runtime_platform" >&2
  echo "[export_game_assemble]   ($runtime_dir/$jvm_server_lib)" >&2
  echo "[export_game_assemble] build the matching image:" >&2
  echo "[export_game_assemble]   ./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=$export_platform-<arch>" >&2
  echo "[export_game_assemble] then pass --runtime build/game-runtime/$export_platform-<arch>/runtime" >&2
  exit 1
fi

dest_dir="$(dirname "$bootstrap_lib")"
if [[ "$(basename "$dest_dir")" == "Frameworks" && -f "$dest_dir/../Info.plist" ]]; then
  dest_dir="$(cd "$dest_dir/.." && pwd)/Resources"
  mkdir -p "$dest_dir"
fi

echo "[export_game_assemble] bootstrap: $bootstrap_lib ($export_platform)"
cp "$kanama_jar" "$dest_dir/kanama.jar"
cp "$scripts_jar" "$dest_dir/kanama-scripts.jar"
rm -rf "$dest_dir/runtime"
cp -R "$runtime_dir" "$dest_dir/runtime"

if [[ ! -f "$dest_dir/runtime/$jvm_server_lib" ]]; then
  echo "[export_game_assemble] assembly failed: missing $dest_dir/runtime/$jvm_server_lib" >&2
  exit 1
fi

echo "[export_game_assemble] assembled:"
echo "  $dest_dir/kanama.jar"
echo "  $dest_dir/kanama-scripts.jar"
echo "  $dest_dir/runtime/$jvm_server_lib"

case "$bootstrap_lib" in
  *.dylib)
    # Godot signs the .app during export (hardened runtime); adding the
    # payload afterwards breaks the bundle seal and macOS SIGKILLs the app at
    # launch. Re-seal ad-hoc, preserving the export's entitlements (a Kanama
    # game needs allow_jit_code_execution / allow_unsigned_executable_memory /
    # disable_library_validation for the embedded JVM — enable them in the
    # macOS export preset). Distribution-grade signing/notarization of the
    # bundled runtime is a separate, pre-existing release track (task 63
    # non-goal): this reseal is for locally-built and unzip-and-play testing
    # flows, not notarized distribution.
    app_bundle="$export_target"
    case "$app_bundle" in
      *.app) ;;
      *) app_bundle="$(cd "$(dirname "$bootstrap_lib")/../.." && pwd)" ;;
    esac
    if command -v codesign >/dev/null 2>&1; then
      codesign --force --sign - --preserve-metadata=entitlements,flags,identifier "$app_bundle"
      echo "[export_game_assemble] re-sealed (ad-hoc): $app_bundle"
    else
      echo "[export_game_assemble] warning: codesign not found; the modified .app will not launch until re-signed" >&2
    fi
    echo "[export_game_assemble] note: macOS distribution signing/notarization is a separate release track"
    ;;
esac

echo "[export_game_assemble] DONE"
