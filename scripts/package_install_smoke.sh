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
project for Android from the package alone and checks the APK; with --ios
(on a Mac) it exports an Xcode project and builds it for the device SDK.

Options:
  --desktop-kit                  Require a desktop-kit zip.
  --store-addon                  Require a store-addon zip.
  --android                      After the desktop checks, run the documented
                                 Android commands (buildAndroidScripts, then a
                                 headless Godot export) and check the APK holds
                                 the scripts, their registrars, the runtime and
                                 libkanama_bootstrap.so, and nothing from build/.
                                 Needs the Godot binary, Godot's Android export
                                 templates, an Android SDK (ANDROID_HOME or
                                 Godot's editor setting) and the editor's Java
                                 SDK Path set for Godot's Android build. No device.
  --ios                          After the desktop checks (and --android), run
                                 the documented iOS commands (buildIosScripts,
                                 then a headless Godot export of an Xcode
                                 project) and build that project with xcodebuild
                                 for the device SDK, unsigned. Needs a Mac with
                                 Xcode, the Godot binary and Godot's iOS export
                                 templates. No device.
  --require-all-store-platforms  Require all store-addon desktop native libs
                                 and the iOS runtime.
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
ios_export=0

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
    --android)
      android_export=1
      shift
      ;;
    --ios)
      ios_export=1
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
if [[ "$ios_export" -eq 1 && -z "$godot_bin" ]]; then
  echo "[package_install_smoke] --ios needs the Godot binary: the Xcode project is a real Godot export" >&2
  exit 2
fi
if [[ "$ios_export" -eq 1 && "$(uname -s)" != "Darwin" ]]; then
  echo "[package_install_smoke] --ios needs a Mac with Xcode" >&2
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
config/icon="res://icon.svg"
config/features=PackedStringArray("4.7")

[editor_plugins]

enabled=PackedStringArray("res://addons/kanama_tools/plugin.cfg")

[kanama]

tools/kotlin_sources_dir="res://kotlin-src"
tools/auto_build_on_save=false
tools/auto_build_debounce_ms=800
tools/reload_scene_after_sync=true
EOF

  # A Godot project has an app icon (the new-project wizard writes icon.svg); an iOS export stops without one.
  cat >"$project_dir/icon.svg" <<'EOF'
<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128"><rect width="128" height="128" fill="#478cbf"/></svg>
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
    # The iOS runtime, which the macOS kit job builds.
    store_version="$(tr -d '[:space:]' <"$project_dir/addons/kanama/kanama.version")"
    check_file "addons/kanama/maven/net/multigesture/kanama/kanama-iosarm64/$store_version/kanama-iosarm64-$store_version.klib"
    check_file "addons/kanama/maven/net/multigesture/kanama/kanama-ios-shim/$store_version/kanama-ios-shim-$store_version.zip"
  fi
fi

echo "[package_install_smoke] package kind: $detected_kind"

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
# Compared as the readers compare them (settings.gradle.kts and the editor plugin trim the line).
addon_version="$(tr -d '[:space:]' <"$project_dir/addons/kanama/kanama.version")"
scripts_version="$(tr -d '[:space:]' <"$project_dir/addons/kanama/kanama-scripts.version")"
if [[ -z "$addon_version" || "$addon_version" != "$scripts_version" ]]; then
  echo "[package_install_smoke] kanama-scripts.version ($scripts_version) does not match kanama.version ($addon_version) after buildScripts" >&2
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

[preset.2]

name="iOS"
platform="iOS"
runnable=false
export_filter="all_resources"
include_filter=""
exclude_filter=""
export_path=""

[preset.2.options]

architectures/arm64=true
application/app_store_team_id="ABCDE12345"
application/bundle_identifier="net.multigesture.kanama.packagesmoke"
application/export_project_only=true
application/min_ios_version="15.0"
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

if [[ "$android_export" -eq 0 && "$ios_export" -eq 0 ]]; then
  echo "[package_install_smoke] PASS"
  exit 0
fi

# check_scene_properties <label>: the scene check the export pages tell the user to run, as the package ships it.
check_scene_properties() {
  local scene_check_log="$work_dir/$1-scene-check.log"
  check_file "addons/kanama/checks/check_exported_scene_properties.gd"
  if ! run_bounded 600 "$godot_bin" --headless --path "$project_dir_for_godot" \
    --script res://addons/kanama/checks/check_exported_scene_properties.gd -- --require-converted >"$scene_check_log" 2>&1; then
    tail -n 40 "$scene_check_log" >&2
    echo "[package_install_smoke] FAIL -- the exported scenes lost script properties, or the check did not run (log: $scene_check_log)" >&2
    exit 1
  fi
}

if [[ "$android_export" -eq 1 ]]; then
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
  check_scene_properties android
  echo "[package_install_smoke] android: buildAndroidScripts and a headless export from the package alone"
fi

# --- iOS from the package alone (task 135 item 5): the commands Export for iOS documents, then the Xcode build a
# user runs, for the device SDK without signing (that proves the link of the scripts against the prebuilt runtime).
if [[ "$ios_export" -eq 1 ]]; then
  ios_log="$work_dir/ios.log"
  echo "[package_install_smoke] ios: ./gradlew buildIosScripts"
  if ! run_bounded 3600 "$project_dir/gradlew" --no-daemon -p "$project_dir" buildIosScripts >"$ios_log" 2>&1; then
    tail -n 80 "$ios_log" >&2
    echo "[package_install_smoke] FAIL -- buildIosScripts failed (log: $ios_log)" >&2
    exit 1
  fi
  for variant in debug release; do
    check_file "addons/kanama/bin/ios/kanama_ios.$variant.xcframework/Info.plist"
    check_file "addons/kanama/bin/ios/kanama_ios.$variant.xcframework/ios-arm64/libkanama_ios.a"
    if ! grep -q "^ios.$variant.arm64 = " "$project_dir/addons/kanama/kanama.gdextension"; then
      echo "[package_install_smoke] buildIosScripts did not add ios.$variant.arm64 to kanama.gdextension" >&2
      exit 1
    fi
  done

  ios_export_dir="$work_dir/ios-export"
  export_log="$work_dir/ios-export.log"
  echo "[package_install_smoke] ios: godot --headless --export-debug iOS"
  rm -rf "$project_dir/.godot/exported" "$ios_export_dir"
  mkdir -p "$ios_export_dir"
  export_rc=0
  run_bounded 1800 "$godot_bin" --headless --path "$project_dir_for_godot" \
    --export-debug iOS "$ios_export_dir/KanamaSmoke.ipa" >"$export_log" 2>&1 || export_rc=$?
  pbxproj="$ios_export_dir/KanamaSmoke.xcodeproj/project.pbxproj"
  if [[ "$export_rc" -ne 0 || ! -f "$pbxproj" ]]; then
    tail -n 80 "$export_log" >&2
    echo "[package_install_smoke] FAIL -- the iOS export failed (exit $export_rc, log: $export_log)" >&2
    exit 1
  fi
  if ! grep -q 'kanama_ios.debug.xcframework' "$pbxproj"; then
    echo "[package_install_smoke] FAIL -- the exported Xcode project does not link kanama_ios.debug.xcframework" >&2
    exit 1
  fi
  check_scene_properties ios

  # The app links the shim, the runtime and the project's script registry (the shim's weak reference to it).
  xcode_log="$work_dir/ios-xcodebuild.log"
  derived_dir="$work_dir/ios-derived-data"
  echo "[package_install_smoke] ios: xcodebuild -sdk iphoneos CODE_SIGNING_ALLOWED=NO"
  if ! run_bounded 1800 xcodebuild -project "$ios_export_dir/KanamaSmoke.xcodeproj" -scheme KanamaSmoke \
    -configuration Debug -sdk iphoneos -destination generic/platform=iOS -derivedDataPath "$derived_dir" \
    CODE_SIGNING_ALLOWED=NO build >"$xcode_log" 2>&1; then
    tail -n 80 "$xcode_log" >&2
    echo "[package_install_smoke] FAIL -- xcodebuild of the exported project failed (log: $xcode_log)" >&2
    exit 1
  fi
  app_binary="$derived_dir/Build/Products/Debug-iphoneos/KanamaSmoke.app/KanamaSmoke"
  if [[ ! -f "$app_binary" ]]; then
    echo "[package_install_smoke] FAIL -- xcodebuild wrote no app binary: $app_binary" >&2
    exit 1
  fi
  symbols="$(xcrun nm -gU -j "$app_binary")"
  for symbol in _kanama_entry _kanama_ios_runtime_entry _kanama_ios_project_scripts_register; do
    if ! grep -Fxq "$symbol" <<<"$symbols"; then
      echo "[package_install_smoke] FAIL -- the app does not define $symbol: $app_binary" >&2
      exit 1
    fi
  done
  echo "[package_install_smoke] ios: buildIosScripts, a headless export and an unsigned device build: $app_binary"
fi

echo "[package_install_smoke] PASS"
