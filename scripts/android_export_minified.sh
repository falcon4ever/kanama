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

# 1) Install/refresh the Godot Android build template (regenerates build.gradle).
echo "[android_minified] install android build template"
"$GODOT_BIN" --headless --path "$DEMO_DIR" --install-android-build-template --quit >/dev/null 2>&1 || true

BUILD_GRADLE="$DEMO_DIR/android/build/build.gradle"
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
if [[ -f "$GP" ]] && grep -q "android.enableR8.fullMode" "$GP"; then
  /usr/bin/sed -i '' 's/^android.enableR8.fullMode=.*/android.enableR8.fullMode=false/' "$GP"
else
  printf '\nandroid.enableR8.fullMode=false\n' >>"$GP"
fi

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
echo "[android_minified] export release (R8): $APK_PATH"
"$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --export-release Android "$APK_PATH"

if [[ ! -f "$APK_PATH" ]]; then
  echo "[android_minified] export did not produce an APK: $APK_PATH" >&2
  exit 1
fi

echo "[android_minified] install: $PACKAGE_NAME"
"$ADB_BIN" start-server >/dev/null
"$ADB_BIN" install -r "$APK_PATH" >/dev/null
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
