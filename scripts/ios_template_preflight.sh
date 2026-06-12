#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'EOF'
usage: scripts/ios_template_preflight.sh [options] [/path/to/ios.zip]

Checks that a Godot iOS export template contains arm64 simulator engine
archives. Apple Silicon iOS simulators require an arm64 iphonesimulator slice;
real devices use a separate arm64 iphoneos slice.

Options:
  --xcode-developer-dir DIR  Xcode Developer dir. Defaults to DEVELOPER_DIR or
                             /Applications/Xcode.app/Contents/Developer.
  --godot-version VERSION    Version folder for the default template lookup.
                             Defaults to 4.7.rc2.
  --work-dir DIR             Existing or new temporary extraction directory.
  --keep-work-dir            Keep the temporary extraction directory.
  --debug-only               Check only the debug template.
  --help, -h                 Show this help.

Default template path:
  ~/Library/Application Support/Godot/export_templates/<version>/ios.zip
EOF
}

xcode_developer_dir="${DEVELOPER_DIR:-/Applications/Xcode.app/Contents/Developer}"
godot_version="4.7.rc2"
work_dir=""
keep_work_dir=0
debug_only=0
template_zip=""

while [[ $# -gt 0 ]]; do
  case "$1" in
    --xcode-developer-dir)
      xcode_developer_dir="${2:-}"
      shift 2
      ;;
    --godot-version)
      godot_version="${2:-}"
      shift 2
      ;;
    --work-dir)
      work_dir="${2:-}"
      shift 2
      ;;
    --keep-work-dir)
      keep_work_dir=1
      shift
      ;;
    --debug-only)
      debug_only=1
      shift
      ;;
    --help|-h)
      usage
      exit 0
      ;;
    --*)
      echo "[ios_template_preflight] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      if [[ -n "$template_zip" ]]; then
        echo "[ios_template_preflight] unexpected extra argument: $1" >&2
        usage
        exit 2
      fi
      template_zip="$1"
      shift
      ;;
  esac
done

if [[ -z "$template_zip" ]]; then
  template_zip="$HOME/Library/Application Support/Godot/export_templates/$godot_version/ios.zip"
fi

if [[ ! -d "$xcode_developer_dir" ]]; then
  echo "[ios_template_preflight] Xcode Developer dir does not exist: $xcode_developer_dir" >&2
  exit 2
fi
if [[ ! -f "$template_zip" ]]; then
  echo "[ios_template_preflight] Godot iOS template zip does not exist: $template_zip" >&2
  exit 2
fi
if ! command -v unzip >/dev/null 2>&1; then
  echo "[ios_template_preflight] unzip is required" >&2
  exit 2
fi

created_work_dir=0
if [[ -z "$work_dir" ]]; then
  work_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_ios_template_preflight.XXXXXX")"
  created_work_dir=1
else
  mkdir -p "$work_dir"
fi

cleanup() {
  if [[ "$created_work_dir" -eq 1 && "$keep_work_dir" -ne 1 ]]; then
    rm -rf "$work_dir" 2>/dev/null || true
  fi
}
trap cleanup EXIT

echo "[ios_template_preflight] template: $template_zip"
echo "[ios_template_preflight] work dir: $work_dir"

configs=(debug)
if [[ "$debug_only" -ne 1 ]]; then
  configs+=(release)
fi

for config in "${configs[@]}"; do
  framework="libgodot.ios.$config.xcframework"
  simulator_archive="$framework/ios-arm64_x86_64-simulator/libgodot.a"
  device_archive="$framework/ios-arm64/libgodot.a"

  unzip -q -o "$template_zip" "$simulator_archive" "$device_archive" -d "$work_dir"

  for archive in "$simulator_archive" "$device_archive"; do
    if [[ ! -f "$work_dir/$archive" ]]; then
      echo "[ios_template_preflight] missing archive in template: $archive" >&2
      exit 1
    fi
  done

  simulator_archs="$(DEVELOPER_DIR="$xcode_developer_dir" xcrun lipo -archs "$work_dir/$simulator_archive")"
  device_archs="$(DEVELOPER_DIR="$xcode_developer_dir" xcrun lipo -archs "$work_dir/$device_archive")"

  echo "[ios_template_preflight] $config simulator archs: $simulator_archs"
  echo "[ios_template_preflight] $config device archs: $device_archs"

  if ! grep -Eq '(^|[[:space:]])arm64($|[[:space:]])' <<<"$simulator_archs"; then
    cat >&2 <<EOF
[ios_template_preflight] $config simulator template is missing arm64.

Apple Silicon iOS simulators require an arm64 iphonesimulator libgodot.a.
Install a corrected Godot iOS export template or build one from source with:

  scons platform=ios target=template_$config arch=arm64 simulator=yes precision=single
EOF
    exit 1
  fi
  if ! grep -Eq '(^|[[:space:]])arm64($|[[:space:]])' <<<"$device_archs"; then
    echo "[ios_template_preflight] $config device template is missing arm64" >&2
    exit 1
  fi
done

echo "[ios_template_preflight] OK"
