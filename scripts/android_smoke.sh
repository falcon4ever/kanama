#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/android_smoke.sh /path/to/godot /path/to/demo package.name [/path/to/output.apk]

Runs the experimental Kanama Android smoke:
  - builds and installs the Kanama Android plugin AAR into the demo,
  - exports a Godot Android debug APK,
  - installs and launches it with adb,
  - checks logcat for Kanama startup signals and fatal failures,
  - captures a screenshot and verifies it is not blank.

Required environment:
  ANDROID_HOME or ANDROID_SDK_ROOT

Optional environment:
  ADB=/path/to/adb
  KANAMA_DEMO_JAVA_HOME=/path/to/jdk25
  KANAMA_ANDROID_LOG=/path/to/logcat.txt
  KANAMA_ANDROID_SCREENSHOT=/path/to/screenshot.png
  KANAMA_ANDROID_LAUNCH_WAIT=30
  KANAMA_ANDROID_RENDERER=mobile|gl_compatibility|forward_plus
      Temporarily overrides the demo's rendering_method.mobile project setting
      for this run (restored afterwards) and asserts in logcat that the
      requested renderer actually initialized — a silent GL fallback fails.
EOF
}

if [[ $# -lt 3 || $# -gt 4 ]]; then
  usage
  exit 2
fi

GODOT_BIN="$1"
DEMO_DIR="$2"
PACKAGE_NAME="$3"
APK_PATH="${4:-/tmp/kanama-android-smoke.apk}"
LOG_FILE="${KANAMA_ANDROID_LOG:-/tmp/kanama_android_smoke.log}"
SCREENSHOT="${KANAMA_ANDROID_SCREENSHOT:-/tmp/kanama_android_smoke.png}"
LAUNCH_WAIT="${KANAMA_ANDROID_LAUNCH_WAIT:-30}"
PANAMAPORT_MAVEN_REPO="${KANAMA_PANAMAPORT_MAVEN_REPO:-file://$HOME/.m2/repository}"
RENDERER_OVERRIDE="${KANAMA_ANDROID_RENDERER:-}"
PROJECT_GODOT_BACKUP=""

case "$RENDERER_OVERRIDE" in
  ""|mobile|gl_compatibility|forward_plus) ;;
  *)
    echo "[android_smoke] unsupported KANAMA_ANDROID_RENDERER: $RENDERER_OVERRIDE" >&2
    exit 2
    ;;
esac

ANDROID_SDK_DIR="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-}}"
if [[ -z "$ANDROID_SDK_DIR" ]]; then
  echo "[android_smoke] missing ANDROID_HOME or ANDROID_SDK_ROOT" >&2
  exit 2
fi

ADB_BIN="${ADB:-$ANDROID_SDK_DIR/platform-tools/adb}"

if [[ ! -x "$GODOT_BIN" ]]; then
  echo "[android_smoke] Godot binary is not executable: $GODOT_BIN" >&2
  exit 2
fi
if [[ ! -d "$DEMO_DIR" ]]; then
  echo "[android_smoke] demo directory does not exist: $DEMO_DIR" >&2
  exit 2
fi
if [[ ! -x "$ADB_BIN" ]]; then
  echo "[android_smoke] adb is not executable: $ADB_BIN" >&2
  exit 2
fi

check_log() {
  local pattern="$1"
  if ! rg -q "$pattern" "$LOG_FILE"; then
    echo "[android_smoke] missing log pattern: $pattern" >&2
    echo "[android_smoke] log tail:" >&2
    tail -n 160 "$LOG_FILE" >&2
    exit 1
  fi
}

check_log_absent() {
  local pattern="$1"
  if rg -q "$pattern" "$LOG_FILE"; then
    echo "[android_smoke] unexpected log pattern: $pattern" >&2
    echo "[android_smoke] log tail:" >&2
    tail -n 160 "$LOG_FILE" >&2
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

adb_retry() {
  local attempt=1
  while true; do
    if "$ADB_BIN" "$@"; then
      return 0
    fi
    if [[ "$attempt" -ge 3 ]]; then
      echo "[android_smoke] adb command failed after $attempt attempts: $*" >&2
      return 1
    fi
    echo "[android_smoke] adb command failed, restarting daemon and retrying: $*" >&2
    "$ADB_BIN" kill-server >/dev/null 2>&1 || true
    sleep 1
    "$ADB_BIN" start-server >/dev/null 2>&1 || true
    sleep 1
    attempt=$((attempt + 1))
  done
}

cleanup() {
  if [[ "${PACKAGE_LAUNCHED:-0}" == "1" ]]; then
    "$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
  fi
  if [[ -n "$PROJECT_GODOT_BACKUP" && -f "$PROJECT_GODOT_BACKUP" ]]; then
    cp "$PROJECT_GODOT_BACKUP" "$DEMO_DIR/project.godot"
    rm -f "$PROJECT_GODOT_BACKUP"
  fi
}
trap cleanup EXIT

if [[ -n "$RENDERER_OVERRIDE" ]]; then
  PROJECT_GODOT="$DEMO_DIR/project.godot"
  if ! grep -q '^renderer/rendering_method.mobile=' "$PROJECT_GODOT"; then
    echo "[android_smoke] demo has no renderer/rendering_method.mobile setting to override: $PROJECT_GODOT" >&2
    exit 1
  fi
  PROJECT_GODOT_BACKUP="$(mktemp "${TMPDIR:-/tmp}/kanama_project_godot.XXXXXX")"
  cp "$PROJECT_GODOT" "$PROJECT_GODOT_BACKUP"
  /usr/bin/sed -i '' \
    "s|^renderer/rendering_method.mobile=.*|renderer/rendering_method.mobile=\"$RENDERER_OVERRIDE\"|" \
    "$PROJECT_GODOT"
  echo "[android_smoke] renderer override for this run: rendering_method.mobile=\"$RENDERER_OVERRIDE\""
fi

echo "[android_smoke] demo: $DEMO_DIR"
if [[ -x "$DEMO_DIR/gradlew" ]]; then
  echo "[android_smoke] build demo scripts"
  if [[ -n "${KANAMA_DEMO_JAVA_HOME:-}" ]]; then
    JAVA_HOME="$KANAMA_DEMO_JAVA_HOME" "$DEMO_DIR/gradlew" -p "$DEMO_DIR" jar
  else
    "$DEMO_DIR/gradlew" -p "$DEMO_DIR" jar
  fi
fi

ANDROID_HOME="$ANDROID_SDK_DIR" ANDROID_SDK_ROOT="$ANDROID_SDK_DIR" \
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAndroidPluginAar \
  -PkanamaAndroidDemoDir="$DEMO_DIR"

echo "[android_smoke] install android build template"
"$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --install-android-build-template \
  --quit >/dev/null 2>&1 || true

BUILD_GRADLE="$DEMO_DIR/android/build/build.gradle"
if [[ ! -f "$BUILD_GRADLE" ]]; then
  echo "[android_smoke] generated build.gradle not found: $BUILD_GRADLE" >&2
  echo "[android_smoke] is gradle_build/use_gradle_build=true in the export preset?" >&2
  exit 1
fi

set_gradle_property "$DEMO_DIR/android/build/gradle.properties" \
  "plugins_maven_repos" "$PANAMAPORT_MAVEN_REPO"

MARKER="kanama-local-maven"
if grep -q "$MARKER" "$BUILD_GRADLE"; then
  /usr/bin/sed -i '' "/=== $MARKER /,\$d" "$BUILD_GRADLE"
fi
cat >>"$BUILD_GRADLE" <<'GRADLE'

// === kanama-local-maven (injected by scripts/android_smoke.sh) ===
// Make a locally published PanamaPort fork available to Godot's generated
// Android Gradle build when the Kanama plugin .gdap points at that coordinate.
allprojects {
    repositories {
        mavenLocal()
        maven { url 'https://jitpack.io' }
    }
}
GRADLE

echo "[android_smoke] export: $APK_PATH"
"$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --export-debug Android "$APK_PATH"

echo "[android_smoke] install: $PACKAGE_NAME"
adb_retry start-server >/dev/null
if ! "$ADB_BIN" install -r "$APK_PATH" >/dev/null 2>&1; then
  # A leftover install from an older run may carry a different debug-keystore
  # signature (INSTALL_FAILED_UPDATE_INCOMPATIBLE). Smoke installs hold no user
  # data, so drop the stale package and install fresh.
  echo "[android_smoke] install -r failed; uninstalling stale $PACKAGE_NAME and retrying"
  "$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 || true
  adb_retry install "$APK_PATH" >/dev/null
fi
adb_retry logcat -c
adb_retry shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
# A dozing/locked screen fails renderer surface creation at launch (seen as
# "Failed to create vulkan window" under the Mobile renderer). Wake the device
# and dismiss a non-secure keyguard before launching; a PIN-locked keyguard
# still needs a human unlock.
adb_retry shell input keyevent KEYCODE_WAKEUP >/dev/null 2>&1 || true
adb_retry shell wm dismiss-keyguard >/dev/null 2>&1 || true
adb_retry shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null
PACKAGE_LAUNCHED=1
sleep "$LAUNCH_WAIT"
APP_PID="$("$ADB_BIN" shell pidof -s "$PACKAGE_NAME" 2>/dev/null | tr -d '\r' || true)"
if [[ -n "$APP_PID" ]]; then
  adb_retry logcat --pid "$APP_PID" -d >"$LOG_FILE"
else
  adb_retry logcat -d >"$LOG_FILE"
fi

check_log "Initializing Godot plugin KanamaAndroid"
check_log "registered KanamaScriptLanguage"
check_log "registered KanamaResourceFormatLoader for \\.kt"
check_log "ResourceFormatLoader\\._load bound kotlinClass="
# task 54 — assert a script instance actually CONSTRUCTED, not merely that the loader bound the
# class above. A regression that resolves the class but produces no live instance passes the bind
# check but fails this. (The desktop hot-reload smoke asserts `placeholder=false`; that field is
# editor-only and never appears here — the Android smoke runs the game, where every construct is a
# real, non-placeholder instance, so the construct line itself is the equivalent proof.)
check_log "KanamaScript\\.construct kotlinClass="
check_log "OnGodotMainLoopStarted"

# Renderer assertion: the override must have actually initialized on device.
# Godot logs the active driver + method at startup (e.g. "Vulkan 1.x - Forward
# Mobile - Using Device ..."); a silent fallback to another renderer fails here.
case "$RENDERER_OVERRIDE" in
  mobile)
    check_log "Vulkan"
    check_log "Forward Mobile"
    ;;
  forward_plus)
    check_log "Vulkan"
    check_log "Forward\\+"
    ;;
  gl_compatibility)
    check_log "OpenGL|Compatibility"
    ;;
esac

check_log_absent "No loader found for resource: res://kotlin-src"
check_log_absent "ClassNotFoundException"
check_log_absent "NoClassDefFoundError"
check_log_absent "UnsatisfiedLinkError"
check_log_absent "FATAL EXCEPTION"
check_log_absent "Error loading GDExtension"
check_log_absent "No \"arm64\" library found for GDExtension"

adb_retry shell screencap -p /sdcard/kanama_android_smoke.png >/dev/null
adb_retry pull /sdcard/kanama_android_smoke.png "$SCREENSHOT" >/dev/null

python3 - "$SCREENSHOT" <<'PY'
import struct
import sys
import zlib

path = sys.argv[1]
with open(path, "rb") as fh:
    data = fh.read()
if not data.startswith(b"\x89PNG\r\n\x1a\n"):
    raise SystemExit("[android_smoke] screenshot is not a PNG")

pos = 8
width = height = None
raw_parts = []
while pos < len(data):
    length = struct.unpack(">I", data[pos:pos + 4])[0]
    chunk_type = data[pos + 4:pos + 8]
    chunk_data = data[pos + 8:pos + 8 + length]
    pos += 12 + length
    if chunk_type == b"IHDR":
        width, height, bit_depth, color_type, compression, filter_method, interlace = struct.unpack(">IIBBBBB", chunk_data)
        if bit_depth != 8 or color_type not in (2, 6) or compression != 0 or filter_method != 0 or interlace != 0:
            raise SystemExit("[android_smoke] unsupported screenshot PNG format")
    elif chunk_type == b"IDAT":
        raw_parts.append(chunk_data)
    elif chunk_type == b"IEND":
        break

if not width or not height or not raw_parts:
    raise SystemExit("[android_smoke] screenshot PNG is incomplete")

channels = 4 if color_type == 6 else 3
stride = width * channels
raw = zlib.decompress(b"".join(raw_parts))
if len(raw) < (stride + 1) * height:
    raise SystemExit("[android_smoke] screenshot PNG payload is truncated")

rows = []
prev = bytearray(stride)
offset = 0
for _ in range(height):
    filter_type = raw[offset]
    offset += 1
    row = bytearray(raw[offset:offset + stride])
    offset += stride
    for i in range(stride):
        left = row[i - channels] if i >= channels else 0
        up = prev[i]
        up_left = prev[i - channels] if i >= channels else 0
        if filter_type == 1:
            row[i] = (row[i] + left) & 0xff
        elif filter_type == 2:
            row[i] = (row[i] + up) & 0xff
        elif filter_type == 3:
            row[i] = (row[i] + ((left + up) // 2)) & 0xff
        elif filter_type == 4:
            p = left + up - up_left
            pa = abs(p - left)
            pb = abs(p - up)
            pc = abs(p - up_left)
            predictor = left if pa <= pb and pa <= pc else up if pb <= pc else up_left
            row[i] = (row[i] + predictor) & 0xff
        elif filter_type != 0:
            raise SystemExit("[android_smoke] unsupported PNG filter")
    rows.append(bytes(row))
    prev = row

sample_step = max(1, len(rows) // 64)
pixels = set()
for row in rows[::sample_step]:
    for i in range(0, len(row), channels * max(1, width // 64)):
        pixels.add(tuple(row[i:i + 3]))
        if len(pixels) > 8:
            print("[android_smoke] screenshot nonblank")
            raise SystemExit(0)

raise SystemExit("[android_smoke] screenshot appears blank")
PY

echo "[android_smoke] PASS"
