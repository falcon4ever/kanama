#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'EOF'
usage: scripts/package_install_smoke.sh [options] /path/to/kanama-package.zip [/path/to/godot]
       scripts/package_install_smoke.sh [options] /path/to/godot /path/to/kanama-package.zip

Unzips a packaged Kanama desktop kit or store addon into a temporary project,
builds the starter Kotlin script without a sibling Kanama source checkout, and
optionally launches Godot: the starter must run, and a pack export must hold
nothing from the Gradle build directory. With --android it also exports the
project for Android from the package alone and checks the APK.

Options:
  --desktop-kit                  Require a desktop-kit zip.
  --store-addon                  Require a store-addon zip.
  --ios-addon                    Require an iOS mobile-addon zip (structure
                                 checks only; device-free, no Godot needed).
  --android                      After the desktop checks, run the documented
                                 Android commands (buildAndroidScripts, then a
                                 headless Godot export) and check the APK holds
                                 the scripts, their registrars, the runtime and
                                 libkanama_bootstrap.so, and nothing from build/.
                                 Needs the Godot binary, Godot's Android export
                                 templates, an Android SDK (ANDROID_HOME or
                                 Godot's editor setting) and the editor's Java
                                 SDK Path set for Godot's Android build. No device.
  --require-all-store-platforms  Require all store-addon desktop native libs.
  --work-dir DIR                 Existing empty or non-existing workspace dir.
  --keep-work-dir                Do not delete a generated temporary workspace.
  --help, -h                     Show this help.
EOF
}

work_dir=""
keep_work_dir=0
zip_path=""
godot_bin=""
package_kind="auto"
require_all_store_platforms=0
android_export=0

while [[ $# -gt 0 ]]; do
  case "$1" in
    --desktop-kit)
      package_kind="desktop-kit"
      shift
      ;;
    --store-addon)
      package_kind="store-addon"
      shift
      ;;
    --ios-addon)
      package_kind="ios-addon"
      shift
      ;;
    --android)
      android_export=1
      shift
      ;;
    --require-all-store-platforms)
      require_all_store_platforms=1
      shift
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
      echo "[package_install_smoke] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      if [[ "$1" == *.zip && -z "$zip_path" ]]; then
        zip_path="$1"
      elif [[ -z "$godot_bin" ]]; then
        godot_bin="$1"
      elif [[ -z "$zip_path" ]]; then
        zip_path="$1"
      else
        echo "[package_install_smoke] unexpected extra argument: $1" >&2
        usage
        exit 2
      fi
      shift
      ;;
  esac
done

if [[ -z "$zip_path" ]]; then
  echo "[package_install_smoke] missing package zip" >&2
  usage
  exit 2
fi
if [[ ! -f "$zip_path" ]]; then
  echo "[package_install_smoke] package zip does not exist: $zip_path" >&2
  exit 2
fi
if [[ -n "$godot_bin" && ! -x "$godot_bin" ]]; then
  echo "[package_install_smoke] Godot binary is not executable: $godot_bin" >&2
  exit 2
fi
if [[ "$android_export" -eq 1 && -z "$godot_bin" ]]; then
  echo "[package_install_smoke] --android needs the Godot binary: the APK is a real Godot export" >&2
  exit 2
fi

created_work_dir=0
if [[ -z "$work_dir" ]]; then
  work_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_package_install.XXXXXX")"
  created_work_dir=1
else
  mkdir -p "$work_dir"
fi

own_gradle_home=0
cleanup() {
  if [[ "$own_gradle_home" -eq 1 ]]; then
    # The Gradle daemons this run started (the Android inner build's and Godot's Android Gradle build's) run
    # from its own GRADLE_USER_HOME, and nothing else does.
    # justified: no daemon left is the normal case, and pkill then exits 1.
    pkill -f "$GRADLE_USER_HOME/" 2>/dev/null || true
  fi
  if [[ "$created_work_dir" -eq 1 && "$keep_work_dir" != "1" ]]; then
    rm -rf "$work_dir" 2>/dev/null || true  # justified: scratch-dir cleanup after the verdict
  fi
}
trap cleanup EXIT

project_dir="$work_dir/project"
log_file="$work_dir/godot.log"
import_log_file="$work_dir/godot.import.log"
if [[ -z "${GRADLE_USER_HOME:-}" ]]; then
  # A fresh Gradle home: every Kanama artifact must come from the package's addons/kanama/maven.
  GRADLE_USER_HOME="$work_dir/gradle-home"
  own_gradle_home=1
fi
export GRADLE_USER_HOME
mkdir -p "$project_dir" "$GRADLE_USER_HOME"

echo "[package_install_smoke] workspace: $work_dir"
unzip -q "$zip_path" -d "$project_dir"

case "$(uname -s)" in
  Darwin)
    if command -v xattr >/dev/null 2>&1; then
      # justified: clearing quarantine is best effort; a quarantined binary that cannot load fails the Godot launch below.
      xattr -dr com.apple.quarantine "$project_dir" 2>/dev/null || true
    fi
    ;;
esac

check_file() {
  local path="$1"
  if [[ ! -f "$project_dir/$path" ]]; then
    echo "[package_install_smoke] missing packaged file: $path" >&2
    exit 1
  fi
}

write_smoke_project_files() {
  cat >"$project_dir/project.godot" <<'EOF'
; Engine configuration file.
; It's best edited using the editor UI and not directly.

config_version=5

[application]

config/name="Kanama Store Addon Smoke"
run/main_scene="res://main.tscn"
config/features=PackedStringArray("4.7")

[editor_plugins]

enabled=PackedStringArray("res://addons/kanama_tools/plugin.cfg")

[kanama]

tools/kotlin_sources_dir="res://kotlin-src"
tools/auto_build_on_save=false
tools/auto_build_debounce_ms=800
tools/reload_scene_after_sync=true
EOF

  cat >"$project_dir/main.tscn" <<'EOF'
[gd_scene load_steps=2 format=3]

[ext_resource type="Script" path="res://kotlin-src/HelloScript.kt" id="1"]

[node name="Main" type="Node2D"]
script = ExtResource("1")

[node name="Status" type="Label" parent="."]
offset_left = -160.0
offset_top = -32.0
offset_right = 160.0
offset_bottom = 32.0
text = "Build Kanama scripts, then press Play."
horizontal_alignment = 1
vertical_alignment = 1
EOF
}

host_platform_classifier() {
  local os
  local arch
  case "$(uname -s)" in
    Darwin) os="macos" ;;
    Linux) os="linux" ;;
    MINGW*|MSYS*|CYGWIN*) os="windows" ;;
    *)
      echo "[package_install_smoke] unsupported host OS: $(uname -s)" >&2
      exit 2
      ;;
  esac
  case "$(uname -m)" in
    arm64|aarch64) arch="arm64" ;;
    x86_64|amd64) arch="x64" ;;
    *)
      echo "[package_install_smoke] unsupported host architecture: $(uname -m)" >&2
      exit 2
      ;;
  esac
  printf '%s-%s\n' "$os" "$arch"
}

native_library_for_platform() {
  case "$1" in
    windows-*) printf 'kanama_bootstrap.dll\n' ;;
    macos-*) printf 'libkanama_bootstrap.dylib\n' ;;
    *) printf 'libkanama_bootstrap.so\n' ;;
  esac
}

require_native_library() {
  local classifier="$1"
  local library_name
  library_name="$(native_library_for_platform "$classifier")"
  check_file "addons/kanama/bin/$classifier/$library_name"
}

if [[ -f "$project_dir/project.godot" && -f "$project_dir/build.gradle.kts" ]]; then
  detected_kind="desktop-kit"
elif [[ -f "$project_dir/addons/kanama/templates/release-kit/build.gradle.kts" ]]; then
  detected_kind="store-addon"
elif [[ -d "$project_dir/addons/kanama/bin/ios" ]]; then
  detected_kind="ios-addon"
else
  echo "[package_install_smoke] could not detect package kind for: $zip_path" >&2
  exit 1
fi

if [[ "$package_kind" != "auto" && "$package_kind" != "$detected_kind" ]]; then
  echo "[package_install_smoke] expected $package_kind package, detected $detected_kind" >&2
  exit 1
fi

if [[ "$detected_kind" == "store-addon" ]]; then
  nested_project="$(find "$project_dir/addons" -name project.godot -print -quit)"
  if [[ -n "$nested_project" ]]; then
    echo "[package_install_smoke] store addon contains nested Godot project file: ${nested_project#$project_dir/}" >&2
    exit 1
  fi

  write_smoke_project_files
  (cd "$project_dir" && sh addons/kanama/setup-kanama-project.sh)

  require_native_library "$(host_platform_classifier)"
  if [[ "$require_all_store_platforms" -eq 1 ]]; then
    require_native_library "macos-arm64"
    require_native_library "linux-x64"
    require_native_library "linux-arm64"
    require_native_library "windows-x64"
  fi
fi

echo "[package_install_smoke] package kind: $detected_kind"

if [[ "$detected_kind" == "ios-addon" ]]; then
  # Device-free structure validation of the packaged iOS runtime (B3 design,
  # §7 mobile promotion bar, internal task repo): both device xcframework variants with
  # their arm64 static slices, the descriptor merge fragment, and the README
  # carrying the script-compile caveat. Script compilation and on-device
  # behavior stay covered by installIosAddon + ios_device_gate.sh.
  for variant in debug release; do
    check_file "addons/kanama/bin/ios/kanama_ios.$variant.xcframework/Info.plist"
    check_file "addons/kanama/bin/ios/kanama_ios.$variant.xcframework/ios-arm64/libkanama_ios.a"
    if find "$project_dir/addons/kanama/bin/ios/kanama_ios.$variant.xcframework" \
        -type d -name "*simulator*" | grep -q .; then
      echo "[package_install_smoke] packaged iOS addon must be device-only ($variant has a simulator slice)" >&2
      exit 1
    fi
  done
  check_file "kanama.gdextension-ios-entries.txt"
  check_file "README.md"
  for entry in "ios.debug.arm64" "ios.release.arm64"; do
    if ! grep -q "^$entry = " "$project_dir/kanama.gdextension-ios-entries.txt"; then
      echo "[package_install_smoke] descriptor fragment missing $entry entry" >&2
      exit 1
    fi
  done
  if ! grep -q "still requires a Kanama source" "$project_dir/README.md"; then
    echo "[package_install_smoke] README lost the script-compile caveat" >&2
    exit 1
  fi
  echo "[package_install_smoke] PASS (ios-addon structure checks)"
  exit 0
fi

check_file "project.godot"
check_file "build.gradle.kts"
check_file "settings.gradle.kts"
check_file "gradlew"
check_file "kotlin-src/HelloScript.kt"
check_file "addons/kanama/kanama.jar"
check_file "addons/kanama/kanama.gdextension"
check_file "addons/kanama/kanama-project.gradle.kts"
check_file "addons/kanama/kanama.version"
check_file ".godot/extension_list.cfg"

if ! grep -Fxq "res://addons/kanama/kanama.gdextension" "$project_dir/.godot/extension_list.cfg"; then
  echo "[package_install_smoke] extension_list.cfg does not register Kanama" >&2
  exit 1
fi

if [[ -x "$project_dir/gradlew" ]]; then
  "$project_dir/gradlew" --no-daemon -p "$project_dir" buildScripts
else
  bash "$project_dir/gradlew" --no-daemon -p "$project_dir" buildScripts
fi

check_file "addons/kanama/kanama-scripts.jar"
jar tf "$project_dir/addons/kanama/kanama-scripts.jar" | grep -Fq "net/multigesture/kanama/generated/KanamaScriptRegistry"
# The version lock (task 143 item 1): buildScripts stamps the jar with the addon's version.
check_file "addons/kanama/kanama-scripts.version"
if ! cmp -s "$project_dir/addons/kanama/kanama.version" "$project_dir/addons/kanama/kanama-scripts.version"; then
  echo "[package_install_smoke] kanama-scripts.version does not match kanama.version after buildScripts" >&2
  exit 1
fi

if [[ -z "$godot_bin" ]]; then
  echo "[package_install_smoke] PASS (Godot launch skipped)"
  exit 0
fi

project_dir_for_godot="$project_dir"
case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      godot_bin="$(cygpath -u "$godot_bin")"
      project_dir_for_godot="$(cygpath -m "$project_dir")"
    fi
    ;;
esac

# run_bounded <seconds> <command...>: the command's exit status, or a failure once the limit passed (the command
# and its children are then stopped). Stock macOS has no `timeout`.
run_bounded() {
  local limit="$1" pid watchdog rc=0
  shift
  "$@" &
  pid=$!
  (
    sleep "$limit"
    echo "[package_install_smoke] timed out after ${limit}s: $*" >&2
    # justified: the command may have exited between the sleep and the kill.
    pkill -TERM -P "$pid" 2>/dev/null || true
    # justified: as above.
    kill -TERM "$pid" 2>/dev/null || true
  ) &
  watchdog=$!
  wait "$pid" || rc=$?
  # justified: the watchdog and its sleep only have to be gone; it may already have fired.
  pkill -P "$watchdog" 2>/dev/null || true
  # justified: as above.
  kill "$watchdog" 2>/dev/null || true
  # justified: reaping the watchdog; its status is not the verdict.
  wait "$watchdog" 2>/dev/null || true
  return "$rc"
}

run_bounded 600 "$godot_bin" --headless --editor --quit-after 120 --path "$project_dir_for_godot" >"$import_log_file" 2>&1
run_bounded 600 "$godot_bin" --headless --path "$project_dir_for_godot" --quit --verbose >"$log_file" 2>&1

if ! grep -Fq "[kanama:starter] Hello from Kanama" "$log_file"; then
  echo "[package_install_smoke] starter script did not run" >&2
  echo "[package_install_smoke] log tail:" >&2
  tail -n 120 "$log_file" >&2
  exit 1
fi

# An export carries the project's resources and nothing from the Gradle build directory: without build/.gdignore
# Godot imports KSP's .kt registrars as a second copy of every script class and ships them, and the Android
# inner build's sources with them. A pack export needs no export template. The Android preset is the one
# Export for Android tells the user to make, as Godot saves it.
if [[ ! -f "$project_dir/export_presets.cfg" ]]; then
  cat >"$project_dir/export_presets.cfg" <<'EOF'
[preset.0]

name="Pack"
platform="Linux"
runnable=false
export_filter="all_resources"
export_path=""

[preset.0.options]

[preset.1]

name="Android"
platform="Android"
runnable=true
export_filter="all_resources"
export_path=""

[preset.1.options]

gradle_build/use_gradle_build=true
gradle_build/min_sdk="33"
architectures/arm64-v8a=true
package/unique_name="net.multigesture.kanama.packagesmoke"
package/name="Kanama Package Smoke"
plugins/KanamaAndroid=true
EOF
fi

# check_export_contents <zip-or-apk> <resource prefix>: no file from build/, no .kt outside kotlin-src/.
check_export_contents() {
  python3 - "$1" "$2" <<'PY'
import sys
import zipfile

names = zipfile.ZipFile(sys.argv[1]).namelist()
prefix = sys.argv[2]
problems = []
leaked = [n for n in names if n.startswith(prefix + "build/")]
if leaked:
    problems.append(f"the export carries {len(leaked)} file(s) from build/ (is build/.gdignore missing?): {', '.join(leaked[:4])}")
stray = [n for n in names if n.endswith(".kt") and not n.startswith(prefix + "kotlin-src/")]
if stray:
    problems.append(f"the export carries .kt sources outside kotlin-src/: {', '.join(stray[:4])}")
for problem in problems:
    print(f"[package_install_smoke] {sys.argv[1].rsplit('/', 1)[-1]}: {problem}", file=sys.stderr)
sys.exit(1 if problems else 0)
PY
}

pack="$work_dir/kanama-package-smoke.zip"
pack_log="$work_dir/godot.pack.log"
if ! run_bounded 600 "$godot_bin" --headless --path "$project_dir_for_godot" --export-pack Pack "$pack" >"$pack_log" 2>&1 ||
  [[ ! -f "$pack" ]]; then
  tail -n 40 "$pack_log" >&2
  echo "[package_install_smoke] FAIL -- the pack export failed (log: $pack_log)" >&2
  exit 1
fi
if ! check_export_contents "$pack" ""; then
  echo "[package_install_smoke] FAIL -- the exported pack holds files from the Gradle build: $pack" >&2
  exit 1
fi

if [[ "$android_export" -eq 0 ]]; then
  echo "[package_install_smoke] PASS"
  exit 0
fi

# --- Android from the package alone (task 135 item 5): the commands Export for Android documents. ---

android_log="$work_dir/android.log"
echo "[package_install_smoke] android: ./gradlew buildAndroidScripts"
if ! run_bounded 1800 "$project_dir/gradlew" --no-daemon -p "$project_dir" buildAndroidScripts >"$android_log" 2>&1; then
  tail -n 80 "$android_log" >&2
  echo "[package_install_smoke] FAIL -- buildAndroidScripts failed (log: $android_log)" >&2
  exit 1
fi
for file in KanamaAndroid.debug.aar KanamaAndroidScripts.debug.aar KanamaAndroid.gdap; do
  check_file "android/plugins/$file"
done
if ! grep -q '^android_aar_plugin = true' "$project_dir/addons/kanama/kanama.gdextension"; then
  echo "[package_install_smoke] buildAndroidScripts did not add the Android entries to kanama.gdextension" >&2
  exit 1
fi

apk="$work_dir/kanama-package-smoke.apk"
export_log="$work_dir/android-export.log"
echo "[package_install_smoke] android: godot --headless --export-debug Android"
rm -rf "$project_dir/.godot/exported"
# --install-android-build-template is Project > Install Android Build Template; run headless it only takes
# effect together with an export.
export_rc=0
run_bounded 1800 "$godot_bin" --headless --path "$project_dir_for_godot" --install-android-build-template \
  --export-debug Android "$apk" >"$export_log" 2>&1 || export_rc=$?
if [[ "$export_rc" -ne 0 || ! -f "$apk" ]]; then
  tail -n 80 "$export_log" >&2
  echo "[package_install_smoke] FAIL -- the Android export failed (exit $export_rc, log: $export_log)" >&2
  exit 1
fi

# The APK: the scripts and their registrars (the scripts AAR), the runtime and PanamaPort (the runtime AAR and
# its remote dependency), the native bootstrap, and nothing from the Gradle build directory.
if ! python3 - "$apk" <<'PY'
import sys
import zipfile

apk = zipfile.ZipFile(sys.argv[1])
names = apk.namelist()
dex = b"".join(apk.read(n) for n in names if n.startswith("classes") and n.endswith(".dex"))
problems = []
for label, needle in (
    ("the starter script", b"Lcom/example/game/HelloScript;"),
    ("its registrar", b"Lnet/multigesture/kanama/generated/HelloScriptScriptRegistrar;"),
    ("the script registry", b"Lnet/multigesture/kanama/generated/KanamaRegistry;"),
    ("the Kanama runtime", b"Lnet/multigesture/kanama/KanamaBinding;"),
    ("the Android plugin", b"Lnet/multigesture/kanama/android/"),
    ("PanamaPort", b"Lcom/v7878/foreign/MemorySegment;"),
):
    if needle not in dex:
        problems.append(f"no {label} ({needle.decode()}) in the APK's dex")
if "lib/arm64-v8a/libkanama_bootstrap.so" not in names:
    problems.append("no lib/arm64-v8a/libkanama_bootstrap.so")
for problem in problems:
    print(f"[package_install_smoke] APK: {problem}", file=sys.stderr)
sys.exit(1 if problems else 0)
PY
then
  echo "[package_install_smoke] FAIL -- the exported APK is incomplete: $apk" >&2
  exit 1
fi
if ! check_export_contents "$apk" "assets/"; then
  echo "[package_install_smoke] FAIL -- the exported APK holds files from the Gradle build: $apk" >&2
  exit 1
fi
# The scene check Export for Android tells the user to run, as the package ships it.
check_file "addons/kanama/checks/check_exported_scene_properties.gd"
scene_check_log="$work_dir/android-scene-check.log"
if ! run_bounded 600 "$godot_bin" --headless --path "$project_dir_for_godot" \
  --script res://addons/kanama/checks/check_exported_scene_properties.gd -- --require-converted >"$scene_check_log" 2>&1; then
  tail -n 40 "$scene_check_log" >&2
  echo "[package_install_smoke] FAIL -- the exported scenes lost script properties, or the check did not run (log: $scene_check_log)" >&2
  exit 1
fi
echo "[package_install_smoke] PASS (android: buildAndroidScripts and a headless export from the package alone)"
