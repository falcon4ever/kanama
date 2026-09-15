#!/usr/bin/env bash
set -euo pipefail

# Reproducible R8-minified Android release smoke for the Kanama plugin.
#
# Godot's stock Android Gradle template ships no minify support, and a forked
# `android_source.zip` would be version-locked to one Godot release. Instead
# this script PATCHES the generated `<demo>/android/build/build.gradle` after
# the template is installed, so it survives Godot export-template upgrades.
#
# Flow:
#   - build + install the Kanama Android plugin AAR into the demo,
#   - install/refresh the Godot Android build template,
#   - inject `minifyEnabled true` into the release buildType,
#   - export a *release* APK (R8 runs, consumer-rules.pro applies),
#   - install, launch, and assert Kanama starts past the PanamaPort FFI
#     bootstrap (the R8 gate this validates).

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/android_export_minified.sh /path/to/godot /path/to/demo package.name [/path/to/output.apk]

Required environment:
  ANDROID_HOME or ANDROID_SDK_ROOT

Optional environment:
  ADB=/path/to/adb
  KANAMA_DEMO_JAVA_HOME=/path/to/jdk25
  KANAMA_ANDROID_LOG=/path/to/logcat.txt
  KANAMA_ANDROID_LAUNCH_WAIT=30
  # Release signing (defaults to the standard Android debug keystore so the
  # minified release APK is installable without per-machine editor settings):
  KANAMA_RELEASE_KEYSTORE=$HOME/.android/debug.keystore
  KANAMA_RELEASE_KEYSTORE_USER=androiddebugkey
  KANAMA_RELEASE_KEYSTORE_PASSWORD=android
EOF
}

if [[ $# -lt 3 || $# -gt 4 ]]; then
  usage
  exit 2
fi

GODOT_BIN="$1"
DEMO_DIR="$2"
PACKAGE_NAME="$3"
APK_PATH="${4:-/tmp/kanama-android-minified.apk}"
LOG_FILE="${KANAMA_ANDROID_LOG:-/tmp/kanama_android_minified.log}"
LAUNCH_WAIT="${KANAMA_ANDROID_LAUNCH_WAIT:-30}"
PANAMAPORT_MAVEN_REPO="${KANAMA_PANAMAPORT_MAVEN_REPO:-file://$HOME/.m2/repository}"

ANDROID_SDK_DIR="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-}}"
if [[ -z "$ANDROID_SDK_DIR" ]]; then
  echo "[android_minified] missing ANDROID_HOME or ANDROID_SDK_ROOT" >&2
  exit 2
fi
ADB_BIN="${ADB:-$ANDROID_SDK_DIR/platform-tools/adb}"

if [[ ! -x "$GODOT_BIN" ]]; then
  echo "[android_minified] Godot binary is not executable: $GODOT_BIN" >&2
  exit 2
fi
if [[ ! -d "$DEMO_DIR" ]]; then
  echo "[android_minified] demo directory does not exist: $DEMO_DIR" >&2
  exit 2
fi
if [[ ! -x "$ADB_BIN" ]]; then
  echo "[android_minified] adb is not executable: $ADB_BIN" >&2
  exit 2
fi

# --- Release signing: default to the standard Android debug keystore. --------
KEYSTORE="${KANAMA_RELEASE_KEYSTORE:-$HOME/.android/debug.keystore}"
KEYSTORE_USER="${KANAMA_RELEASE_KEYSTORE_USER:-androiddebugkey}"
KEYSTORE_PASSWORD="${KANAMA_RELEASE_KEYSTORE_PASSWORD:-android}"
if [[ ! -f "$KEYSTORE" && "$KEYSTORE" == "$HOME/.android/debug.keystore" ]]; then
  echo "[android_minified] generating Android debug keystore at $KEYSTORE"
  mkdir -p "$(dirname "$KEYSTORE")"
  keytool -genkeypair -v -keystore "$KEYSTORE" -storepass android -keypass android \
    -alias androiddebugkey -keyalg RSA -keysize 2048 -validity 10000 \
    -dname "CN=Android Debug,O=Android,C=US"
fi
if [[ ! -f "$KEYSTORE" ]]; then
  echo "[android_minified] release keystore not found: $KEYSTORE" >&2
  exit 2
fi
# Godot reads these env vars for headless release signing.
export GODOT_ANDROID_KEYSTORE_RELEASE_PATH="$KEYSTORE"
export GODOT_ANDROID_KEYSTORE_RELEASE_USER="$KEYSTORE_USER"
export GODOT_ANDROID_KEYSTORE_RELEASE_PASSWORD="$KEYSTORE_PASSWORD"

check_log() {
  local pattern="$1"
  if ! rg -q "$pattern" "$LOG_FILE"; then
    echo "[android_minified] missing log pattern: $pattern" >&2
    echo "[android_minified] log tail:" >&2
    tail -n 200 "$LOG_FILE" >&2
    exit 1
  fi
}

check_log_absent() {
  local pattern="$1"
  if rg -q "$pattern" "$LOG_FILE"; then
    echo "[android_minified] unexpected log pattern: $pattern" >&2
    echo "[android_minified] log tail:" >&2
    tail -n 200 "$LOG_FILE" >&2
    exit 1
  fi
}

set_gradle_property() {
  local file="$1"
  local key="$2"
  local value="$3"
  if [[ -f "$file" ]] && grep -q "^$key=" "$file"; then
    /usr/bin/sed -i '' "s|^$key=.*|$key=$value|" "$file"
  else
    printf '\n%s=%s\n' "$key" "$value" >>"$file"
  fi
}

cleanup() {
  if [[ "${PACKAGE_LAUNCHED:-0}" == "1" ]]; then
    "$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
  fi
}
trap cleanup EXIT

echo "[android_minified] demo: $DEMO_DIR"
if [[ -x "$DEMO_DIR/gradlew" ]]; then
  echo "[android_minified] build demo scripts"
  if [[ -n "${KANAMA_DEMO_JAVA_HOME:-}" ]]; then
    JAVA_HOME="$KANAMA_DEMO_JAVA_HOME" "$DEMO_DIR/gradlew" -p "$DEMO_DIR" jar
  else
    "$DEMO_DIR/gradlew" -p "$DEMO_DIR" jar
  fi
fi

echo "[android_minified] build + install Kanama AAR"
ANDROID_HOME="$ANDROID_SDK_DIR" ANDROID_SDK_ROOT="$ANDROID_SDK_DIR" \
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAndroidPluginAar \
  -PkanamaAndroidDemoDir="$DEMO_DIR"

# Task 116: the export runs in the DESKTOP editor, which needs the desktop Kanama addon to load the
# project's .kt scripts; without it every exported scene ships with no script properties (task 106).
# Installed after the AAR so installAddonJar preserves the Android .gdextension entries.
echo "[android_minified] install desktop addon (export-time editor)"
addon_args=("-PkanamaProjectDir=$DEMO_DIR")
if [[ -d "$DEMO_DIR/kotlin-src" ]]; then
  addon_args+=("-PkanamaProjectScriptsDir=$DEMO_DIR/kotlin-src")
fi
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAddonJar "${addon_args[@]}"

# 1) Install/refresh the Godot Android build template (regenerates build.gradle).
echo "[android_minified] install android build template"
"$GODOT_BIN" --headless --path "$DEMO_DIR" --install-android-build-template --quit >/dev/null 2>&1 || true

BUILD_GRADLE="$DEMO_DIR/android/build/build.gradle"
if [[ ! -f "$BUILD_GRADLE" ]]; then
  # Task 116: in 4.7.2 that command installs nothing on its own (it only takes effect combined with
  # an export), and the R8 patch below needs the template BEFORE the export. Do what the editor's
  # installer does: android/.build_version, android/build/.gdignore, unzip android_source.zip.
  # The templates directory and android/.build_version must equal Godot's VERSION_FULL_CONFIG
  # ("4.7.2.stable", "4.8.stable", "4.7.2.stable.mono"); `godot --version` cannot be cut into that
  # reliably (an X.Y.0 release prints no patch, a mono build adds a segment), so read the pin the
  # repo already owns, as ios_template_preflight.sh does.
  godot_version="$(sed -n 's/^kanamaGodotVersion=//p' "$ROOT_DIR/gradle.properties" | tr -d '[:space:]')"
  if [[ -z "$godot_version" ]]; then
    echo "[android_minified] kanamaGodotVersion missing from $ROOT_DIR/gradle.properties" >&2
    exit 1
  fi
  templates_root="${KANAMA_GODOT_TEMPLATES_DIR:-}"
  if [[ -z "$templates_root" ]]; then
    if [[ -d "$HOME/Library/Application Support/Godot/export_templates" ]]; then
      templates_root="$HOME/Library/Application Support/Godot/export_templates"
    else
      templates_root="${XDG_DATA_HOME:-$HOME/.local/share}/godot/export_templates"
    fi
  fi
  android_source="$templates_root/$godot_version/android_source.zip"
  if [[ ! -f "$android_source" ]]; then
    echo "[android_minified] android build template not installed and $android_source is missing" >&2
    exit 1
  fi
  echo "[android_minified] installing the android build template by hand from $android_source"
  mkdir -p "$DEMO_DIR/android/build"
  : >"$DEMO_DIR/android/build/.gdignore"
  printf '%s\n' "$godot_version" >"$DEMO_DIR/android/.build_version"
  unzip -q -o "$android_source" -d "$DEMO_DIR/android/build"
fi
if [[ ! -f "$BUILD_GRADLE" ]]; then
  echo "[android_minified] generated build.gradle not found: $BUILD_GRADLE" >&2
  echo "[android_minified] is gradle_build/use_gradle_build=true in the export preset?" >&2
  exit 1
fi

# 2) Inject minify into the generated build.gradle (idempotent, marker-guarded).
#    Re-opens the existing release buildType, so we don't depend on the template's
#    internal block layout. Survives Godot template upgrades.
# PanamaPort's annotation-driven R8 rules (R8Annotations) are written against
# R8 compat mode. AGP 8+ defaults to R8 full mode, which is more aggressive and
# corrupts PanamaPort's LLVM downcall-stub type dispatch -> shouldNotReachHere at
# nativeLinker().downcallHandle(). Force compat mode for the minified build.
GP="$DEMO_DIR/android/build/gradle.properties"
set_gradle_property "$GP" "android.enableR8.fullMode" "false"
set_gradle_property "$GP" "plugins_maven_repos" "$PANAMAPORT_MAVEN_REPO"

# Drop any stale rules file from earlier script versions.
rm -f "$DEMO_DIR/android/build/kanama-minify.pro"

# Re-apply the patch fresh every run. --install-android-build-template does not
# always regenerate build.gradle, so a skip-if-present guard would reuse a stale
# patch; strip our previous block (marker -> EOF) and re-append.
MARKER="kanama-r8-minify"
if grep -q "$MARKER" "$BUILD_GRADLE"; then
  /usr/bin/sed -i '' "/=== $MARKER /,\$d" "$BUILD_GRADLE"
fi
echo "[android_minified] patching $BUILD_GRADLE (enable R8 on release)"
cat >>"$BUILD_GRADLE" <<'GRADLE'

// === kanama-r8-minify (injected by scripts/android_export_minified.sh) ===
// Godot's stock Android template ships no minify support. Enable R8 on the
// release build here, on the generated output, so this survives Godot
// export-template upgrades instead of forking android_source.zip. The Kanama
// AAR's consumer-rules.pro is applied automatically by R8. R8 compat mode is
// forced via gradle.properties (android.enableR8.fullMode=false) so PanamaPort's
// annotation-driven rules behave as designed.
allprojects {
    repositories {
        mavenLocal()
        maven { url 'https://jitpack.io' }
    }
}

android {
    buildTypes {
        release {
            minifyEnabled true
            shrinkResources false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt')
        }
    }
}
GRADLE

# 3) Export the release APK WITHOUT reinstalling the template (keeps the patch).
rm -rf "$DEMO_DIR/.godot/exported" # task 106/116: never reuse a conversion made without the desktop addon
EXPORT_LOG="${KANAMA_ANDROID_EXPORT_LOG:-${APK_PATH%.apk}.export.log}"
echo "[android_minified] export release (R8): $APK_PATH (log: $EXPORT_LOG)"
# `if !` keeps errexit from aborting the pipeline before the status is read (pipefail is on).
if ! "$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --export-release Android "$APK_PATH" 2>&1 | tee "$EXPORT_LOG"; then
  echo "[android_minified] Godot export failed (exit ${PIPESTATUS[0]}); log: $EXPORT_LOG" >&2
  exit 1
fi
if grep -qE 'No loader found for resource: res://.*\.kt|ResourceFormatLoader\._load bound kotlinClass= ' "$EXPORT_LOG"; then
  echo "[android_minified] the export-time editor could not bind the project's .kt scripts; scene-stored @ScriptProperty values would be missing (task 106/116)" >&2
  exit 1
fi
if ! "$GODOT_BIN" --headless --path "$DEMO_DIR" --script "$ROOT_DIR/scripts/check_exported_scene_properties.gd"; then
  echo "[android_minified] exported scenes lost script properties (task 112 check); refusing to install." >&2
  exit 1
fi

if [[ ! -f "$APK_PATH" ]]; then
  echo "[android_minified] export did not produce an APK: $APK_PATH" >&2
  exit 1
fi

echo "[android_minified] install: $PACKAGE_NAME"
"$ADB_BIN" start-server >/dev/null
if ! "$ADB_BIN" install -r "$APK_PATH" >/dev/null 2>&1; then
  # A leftover install from the debug smoke carries the Godot editor debug
  # keystore's signature, which differs from this gate's release keystore
  # (INSTALL_FAILED_UPDATE_INCOMPATIBLE). Smoke installs hold no user data,
  # so drop the stale package and install fresh — same hardening as
  # android_smoke.sh.
  echo "[android_minified] install -r failed; uninstalling stale $PACKAGE_NAME and retrying"
  "$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 || true
  "$ADB_BIN" install "$APK_PATH" >/dev/null
fi
"$ADB_BIN" logcat -c
"$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
"$ADB_BIN" shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null
PACKAGE_LAUNCHED=1
sleep "$LAUNCH_WAIT"
APP_PID="$("$ADB_BIN" shell pidof -s "$PACKAGE_NAME" 2>/dev/null | tr -d '\r' || true)"
if [[ -n "$APP_PID" ]]; then
  "$ADB_BIN" logcat --pid "$APP_PID" -d >"$LOG_FILE"
else
  "$ADB_BIN" logcat -d >"$LOG_FILE"
fi

# Positive: Kanama started past the PanamaPort FFI bootstrap and registered.
check_log "Initializing Godot plugin KanamaAndroid"
check_log "registered KanamaResourceFormatLoader for \\.kt"

# Negative: the R8 failure signatures must be gone.
check_log_absent "Should not reach here"
check_log_absent "GDExtension initialization function 'kanama_entry' returned an error"
check_log_absent "Error loading extension"
check_log_absent "No loader found for resource: res://kotlin-src"
check_log_absent "FATAL EXCEPTION"

echo "[android_minified] PASS (R8-minified release boots Kanama)"
