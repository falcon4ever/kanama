#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/portable.sh
source "$ROOT_DIR/scripts/portable.sh"
OUT_DIR="$ROOT_DIR/gdextension"
RUN_JEXTRACT=1
RUN_VALIDATE=1
# Each headless dump takes seconds; a hung editor fails the refresh instead of waiting forever.
DUMP_TIMEOUT="${KANAMA_GODOT_DUMP_TIMEOUT:-300}"

usage() {
  cat <<'EOF'
usage: scripts/refresh_godot_api.sh [--skip-jextract] [--skip-validate] /absolute/path/to/godot_binary

Dumps Godot's GDExtension API files into Kanama's local inputs:
  - extension_api.json
  - gdextension/gdextension_interface.h

Then runs scripts/jextract.sh (unless --skip-jextract), scripts/validate_godot_api.py
(unless --skip-validate) and the virtual-signature table generator.

--skip-validate is for scripts/upgrade_godot.sh: before the wrapper tree is re-adopted the
committed wrappers still name the methods the new API removed, so validating here would stop
every bump that removes a wrapped method. The upgrade validates after the re-adopt instead.

Each Godot dump runs under a timeout (KANAMA_GODOT_DUMP_TIMEOUT seconds, default 300).
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --help|-h)
      usage
      exit 0
      ;;
    --skip-jextract)
      RUN_JEXTRACT=0
      shift
      ;;
    --skip-validate)
      RUN_VALIDATE=0
      shift
      ;;
    --*)
      echo "[refresh_godot_api] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      GODOT_BIN="$1"
      shift
      ;;
  esac
done

if [[ -z "${GODOT_BIN:-}" ]]; then
  echo "[refresh_godot_api] missing Godot binary" >&2
  usage
  exit 2
fi

if [[ ! -x "$GODOT_BIN" ]]; then
  echo "[refresh_godot_api] Godot binary is not executable: $GODOT_BIN" >&2
  exit 2
fi

TMP_DIR="$(mktemp -d "${TMPDIR:-/tmp}/kanama-godot-api.XXXXXX")"
cleanup() {
  rm -rf "$TMP_DIR"
}
trap cleanup EXIT

echo "[refresh_godot_api] dumping API from: $GODOT_BIN"
(
  cd "$TMP_DIR"
  run_with_timeout "$DUMP_TIMEOUT" "$GODOT_BIN" --headless --log-file "$TMP_DIR/godot-extension-api.log" --dump-extension-api
  run_with_timeout "$DUMP_TIMEOUT" "$GODOT_BIN" --headless --log-file "$TMP_DIR/godot-gdextension-interface.log" --dump-gdextension-interface
)

mkdir -p "$OUT_DIR"
cp "$TMP_DIR/extension_api.json" "$ROOT_DIR/extension_api.json"
cp "$TMP_DIR/gdextension_interface.h" "$OUT_DIR/gdextension_interface.h"

if [[ $RUN_JEXTRACT -eq 1 ]]; then
  echo "[refresh_godot_api] regenerating Panama bindings"
  "$ROOT_DIR/scripts/jextract.sh"
else
  echo "[refresh_godot_api] skipping jextract"
fi

if [[ $RUN_VALIDATE -eq 1 ]]; then
  echo "[refresh_godot_api] validating dumped API"
  python3 "$ROOT_DIR/scripts/validate_godot_api.py"
else
  echo "[refresh_godot_api] skipping validation (--skip-validate; the caller validates after the re-adopt)"
fi

echo "[refresh_godot_api] regenerating virtual-method signature table"
python3 "$ROOT_DIR/scripts/generate_virtual_signature_table.py"

echo "[refresh_godot_api] PASS"
