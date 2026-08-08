#!/usr/bin/env bash
# Task 63 (issue #102) — exported-game smoke: prove the unpack-and-play story.
#
# Exports the example project as a real desktop game, assembles the bundled
# jlink runtime with scripts/export_game_assemble.sh, then launches the export
# headless with JAVA_HOME unset and PATH stripped of any JDK. The run must
# boot from the APP-RELATIVE bundled runtime (asserted on the bootstrap's
# logged libjvm path, which is probed before JAVA_HOME and every dev
# fallback), construct a Kanama script, and tear down clean. This is the
# artifact that turns "we bundled a JVM" into "exported-game support".
#
# Runs on macOS, Linux, and Windows (Git Bash). `--runtime DIR` feeds it a
# runtime image built elsewhere, which is how CI proves the thing that actually
# matters: the Windows and Linux runtimes are CROSS-BUILT on a macOS job and
# consumed here. A runner that jlinks its own runtime would only prove same-OS
# packaging, which is the limitation this task exists to remove.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/export_game_smoke.sh [options] /absolute/path/to/godot_binary

Requires the matching Godot export templates to be installed (the same
version as the Godot binary).

Options:
  --runtime DIR    Use this jlink runtime image instead of building one for the
                   host. Point it at a cross-built image (see
                   `./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=...`)
                   to prove an export runs on a runtime produced elsewhere.
  --work-dir DIR   Existing empty or non-existing workspace dir.
  --keep-work-dir  Do not delete a generated temporary workspace.
  --help, -h       Show this help.
EOF
}

work_dir=""
keep_work_dir=0
godot_bin=""
runtime_dir=""

while [[ $# -gt 0 ]]; do
  case "$1" in
    --runtime)
      runtime_dir="${2:-}"
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
    --help|-h)
      usage
      exit 0
      ;;
    --*)
      echo "[export_game_smoke] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      if [[ -n "$godot_bin" ]]; then
        echo "[export_game_smoke] unexpected extra argument: $1" >&2
        usage
        exit 2
      fi
      godot_bin="$1"
      shift
      ;;
  esac
done

if [[ -z "$godot_bin" ]]; then
  usage
  exit 2
fi
if [[ ! -x "$godot_bin" ]]; then
  echo "[export_game_smoke] Godot binary is not executable: $godot_bin" >&2
  exit 2
fi

host_arch="x86_64"
if [[ "$(uname -m)" == "aarch64" || "$(uname -m)" == "arm64" ]]; then
  host_arch="arm64"
fi

host_os="$(uname -s)"
case "$host_os" in
  Darwin)
    preset_name="macOS"
    preset_platform="macOS"
    export_artifact="KanamaExportSmoke.app"
    # The official macOS export template ships a universal binary only.
    preset_arch="universal"
    ;;
  Linux)
    preset_name="Linux"
    preset_platform="Linux"
    export_artifact="kanama_export_smoke.$host_arch"
    preset_arch="$host_arch"
    ;;
  MINGW*|MSYS*|CYGWIN*)
    preset_name="Windows Desktop"
    preset_platform="Windows Desktop"
    export_artifact="kanama_export_smoke.exe"
    preset_arch="x86_64"
    ;;
  *)
    echo "[export_game_smoke] unsupported host OS: $host_os" >&2
    exit 2
    ;;
esac

created_work_dir=0
if [[ -z "$work_dir" ]]; then
  work_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_export_game_smoke.XXXXXX")"
  created_work_dir=1
else
  mkdir -p "$work_dir"
fi
# Canonicalize: the bootstrap logs dladdr-resolved real paths (macOS: /var is
# a symlink to /private/var), and the log assertions compare path prefixes.
work_dir="$(cd "$work_dir" && pwd -P)"

cleanup() {
  if [[ "$created_work_dir" -eq 1 && "$keep_work_dir" != "1" ]]; then
    rm -rf "$work_dir" 2>/dev/null || true
  fi
}
trap cleanup EXIT

echo "[export_game_smoke] workspace: $work_dir"

# 1. Build the pieces: bootstrap + kanama.jar + example scripts jar (synced
#    into example_project/addons/kanama) and, unless one was handed in, the
#    jlink runtime image for this platform.
if [[ -n "$runtime_dir" ]]; then
  if [[ ! -d "$runtime_dir" ]]; then
    echo "[export_game_smoke] --runtime image not found: $runtime_dir" >&2
    exit 2
  fi
  runtime_dir="$(cd "$runtime_dir" && pwd -P)"
  echo "[export_game_smoke] runtime image (supplied): $runtime_dir"
  "$ROOT_DIR/gradlew" --no-daemon -p "$ROOT_DIR" buildNativeBootstrap syncExampleAddonJar >/dev/null
else
  "$ROOT_DIR/gradlew" --no-daemon -p "$ROOT_DIR" \
    buildNativeBootstrap syncExampleAddonJar jlinkGameRuntime >/dev/null
  runtime_dir="$ROOT_DIR/build/game-runtime/runtime"
fi

# 2. Stage an exportable copy of the example project. The smoke boots
#    self_smoke.tscn (script construction + wrapper call) instead of the full
#    editor smoke suite: main.gd's probe fixtures assume an editor-flow
#    filesystem, not a packed export.
project_dir="$work_dir/project"
mkdir -p "$project_dir"
# python3 rather than rsync: rsync is not on the Windows runners' Git Bash, and
# python3 is already a hard dependency of this script.
python3 - "$ROOT_DIR/example_project" "$project_dir" <<'EOF'
import shutil, sys
shutil.copytree(
    sys.argv[1], sys.argv[2], dirs_exist_ok=True,
    ignore=shutil.ignore_patterns(".godot", ".DS_Store"),
)
EOF
mkdir -p "$project_dir/.godot"
printf 'res://addons/kanama/kanama.gdextension\n' > "$project_dir/.godot/extension_list.cfg"
python3 - "$project_dir/project.godot" <<'EOF'
import re, sys
path = sys.argv[1]
text = open(path).read()
text, n = re.subn(r'run/main_scene="[^"]*"', 'run/main_scene="res://self_smoke.tscn"', text)
assert n == 1, "run/main_scene not found in project.godot"
# Universal/arm64 exports require the ETC2 ASTC VRAM texture format.
if "import_etc2_astc" not in text:
    text += '\n[rendering]\n\ntextures/vram_compression/import_etc2_astc=true\n'
open(path, "w").write(text)
EOF

platform_options=""
if [[ "$preset_platform" == "Windows Desktop" ]]; then
  # modify_resources needs rcedit (icon/version stamping) which CI does not
  # install, and the console wrapper is the reliable way to capture a GUI-
  # subsystem Godot binary's stdout/stderr.
  platform_options="$(cat <<'EOF'
application/modify_resources=false
debug/export_console_wrapper=2
codesign/enable=false
EOF
)"
fi
if [[ "$preset_platform" == "macOS" ]]; then
  # The macOS entitlements are required for the embedded JVM: without
  # allow_jit_code_execution/allow_unsigned_executable_memory the hardened
  # runtime aborts JNI_CreateJavaVM (pthread_jit_write_protect_np SIGTRAP), and
  # without disable_library_validation the ad-hoc-signed export may not dlopen
  # the Temurin-signed libjvm.
  platform_options="$(cat <<'EOF'
export/distribution_type=0
codesign/codesign=1
codesign/entitlements/allow_jit_code_execution=true
codesign/entitlements/allow_unsigned_executable_memory=true
codesign/entitlements/disable_library_validation=true
application/bundle_identifier="net.multigesture.kanama.exportsmoke"
EOF
)"
fi

cat > "$project_dir/export_presets.cfg" <<EOF
[preset.0]

name="$preset_name"
platform="$preset_platform"
runnable=true
advanced_options=false
dedicated_server=false
custom_features=""
export_filter="all_resources"
include_filter=""
exclude_filter=""
export_path=""
patches=PackedStringArray()
encryption_include_filters=""
encryption_exclude_filters=""
seed=0
encrypt_pck=false
encrypt_directory=false
script_export_mode=2

[preset.0.options]

binary_format/architecture="$preset_arch"
$platform_options
EOF

# 3. Import, then export the game.
export_dir="$work_dir/export"
mkdir -p "$export_dir"
import_log="$work_dir/godot.import.log"
export_log="$work_dir/godot.export.log"
"$godot_bin" --headless --import --path "$project_dir" >"$import_log" 2>&1 || {
  echo "[export_game_smoke] project import failed; log tail:" >&2
  tail -n 40 "$import_log" >&2
  exit 1
}
if ! "$godot_bin" --headless --path "$project_dir" --export-release "$preset_name" \
    "$export_dir/$export_artifact" >"$export_log" 2>&1; then
  echo "[export_game_smoke] export failed; log tail:" >&2
  tail -n 40 "$export_log" >&2
  exit 1
fi

# 4. Assemble the unpack-and-play layout (runtime image + jars) into the
#    export.
case "$preset_platform" in
  macOS) assemble_target="$export_dir/$export_artifact" ;;
  *) assemble_target="$export_dir" ;;
esac
"$ROOT_DIR/scripts/export_game_assemble.sh" \
  --runtime "$runtime_dir" \
  --scripts-jar "$ROOT_DIR/example_project/addons/kanama/kanama-scripts.jar" \
  "$assemble_target"

# 5. Launch the export with no reachable dev JDK: JAVA_HOME unset and PATH
#    stripped. The bootstrap probes the bundled runtime BEFORE JAVA_HOME and
#    the hardcoded dev fallbacks, so the logged libjvm path inside the export
#    is the proof the bundled runtime booted the game (a dev JDK existing
#    elsewhere on the machine never gets consulted first).
case "$preset_platform" in
  macOS) game_bin="$export_dir/$export_artifact/Contents/MacOS/Kanama Test" ;;
  "Windows Desktop")
    # Godot's Windows binaries are GUI-subsystem; the exported console wrapper
    # is the reliable way to get the bootstrap's stderr into a file.
    game_bin="$export_dir/${export_artifact%.exe}.console.exe"
    [[ -f "$game_bin" ]] || game_bin="$export_dir/$export_artifact"
    ;;
  *) game_bin="$export_dir/$export_artifact" ;;
esac
if [[ ! -x "$game_bin" ]]; then
  echo "[export_game_smoke] exported game binary not found: $game_bin" >&2
  exit 1
fi
echo "[export_game_smoke] launching: $game_bin"

# A PATH with no JDK on it. Windows needs its system directories to stay
# reachable; everything else gets the bare minimum.
if [[ "$preset_platform" == "Windows Desktop" ]]; then
  system_root="$(cygpath -u "${SYSTEMROOT:-C:\\Windows}" 2>/dev/null || printf '/c/Windows')"
  clean_path="$system_root/System32:$system_root:$system_root/System32/Wbem"
else
  clean_path="/usr/bin:/bin"
fi

log_file="$work_dir/game.log"
run_status=0
(
  unset JAVA_HOME
  export PATH="$clean_path"
  exec "$game_bin" --headless --quit --verbose
) >"$log_file" 2>&1 || run_status=$?
if [[ "$run_status" -ne 0 ]]; then
  echo "[export_game_smoke] exported game exited with status $run_status; log tail:" >&2
  tail -n 60 "$log_file" >&2
  exit 1
fi

smoke_fail() {
  local kind="$1" pattern="$2"
  echo "[export_game_smoke] $kind: $pattern" >&2
  echo "[export_game_smoke] log tail:" >&2
  tail -n 60 "$log_file" >&2
  echo >&2
  echo "[export_game_smoke] FAIL -- $kind: $pattern" >&2
  echo "[export_game_smoke] full log: $log_file" >&2
  exit 1
}

# The bootstrap logs native paths: backslashes and a drive letter on Windows,
# where $export_dir is an MSYS path. Normalise separators once and match
# case-insensitively so one set of assertions covers every platform.
log_match="$work_dir/game.match.log"
tr '\\' '/' < "$log_file" > "$log_match"

check() {
  local pattern="$1"
  if ! grep -Eiq -- "$pattern" "$log_match"; then
    smoke_fail "missing pattern" "$pattern"
  fi
}

check_absent() {
  local pattern="$1"
  if grep -Eiq -- "$pattern" "$log_match"; then
    smoke_fail "unexpected pattern" "$pattern"
  fi
}

if [[ "$preset_platform" == "Windows Desktop" ]]; then
  export_dir_native="$(cygpath -m "$export_dir" 2>/dev/null || printf '%s' "$export_dir")"
else
  export_dir_native="$export_dir"
fi
export_dir_regex="$(printf '%s' "$export_dir_native" | sed 's/[.[\*^$()+?{|]/\\&/g')"

# The bundled runtime inside the export booted the JVM...
check "\\[kanama\\] bundled runtime: $export_dir_regex/.*runtime/"
check "\\[kanama\\] using libjvm: $export_dir_regex/.*runtime/"
# ...and kanama.jar was resolved app-relative too, not from the checkout.
check "\\[kanama\\] jar: $export_dir_regex/"
check_absent "checked JAVA_HOME"
check_absent "error: libjvm not found"
# A Kanama script constructed against the packed .kt resource and ran.
check "SelfSmoke self_class=Node3D same_object=true"
# Clean teardown.
check "destroyed [0-9]+/[0-9]+ tracked KanamaScript object\\(s\\)"
check "unregistered [0-9]+ extension class\\(es\\)"

echo "[export_game_smoke] PASS"
