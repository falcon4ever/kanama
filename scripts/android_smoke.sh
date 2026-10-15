#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/gate_skip.sh
source "$ROOT_DIR/scripts/gate_skip.sh"
# shellcheck source=scripts/portable.sh
source "$ROOT_DIR/scripts/portable.sh"
# shellcheck source=scripts/android_apk_id.sh
source "$ROOT_DIR/scripts/android_apk_id.sh"
ANDROID_TAG="[android_smoke]"
# shellcheck source=scripts/android_common.sh
source "$ROOT_DIR/scripts/android_common.sh"

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
    # justified: the daemon restart is best effort; the retried command on the next loop turn is the check
    # (after 3 attempts adb_retry returns 1).
    "$ADB_BIN" kill-server >/dev/null 2>&1 || true
    sleep 1
    # justified: best-effort daemon restart inside adb_retry; the retried command on the next loop turn is the check.
    "$ADB_BIN" start-server >/dev/null 2>&1 || true
    sleep 1
    attempt=$((attempt + 1))
  done
}

cleanup() {
  if [[ "${PACKAGE_LAUNCHED:-0}" == "1" ]]; then
    # justified: cleanup after the verdict; the app may already have exited.
    "$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
  fi
  if [[ -n "$PROJECT_GODOT_BACKUP" && -f "$PROJECT_GODOT_BACKUP" ]]; then
    cp "$PROJECT_GODOT_BACKUP" "$DEMO_DIR/project.godot"
    rm -f "$PROJECT_GODOT_BACKUP"
  fi
}
trap cleanup EXIT

# The demo's project.godot is backed up on every run and restored on exit: the typed-signal self-test
# is a project setting (an environment variable cannot reach an Android app), written below, and the
# renderer override edits the same file.
PROJECT_GODOT="$DEMO_DIR/project.godot"
if [[ ! -f "$PROJECT_GODOT" ]]; then
  echo "[android_smoke] demo has no project.godot: $PROJECT_GODOT" >&2
  exit 1
fi
PROJECT_GODOT_BACKUP="$(mktemp "${TMPDIR:-/tmp}/kanama_project_godot.XXXXXX")"
cp "$PROJECT_GODOT" "$PROJECT_GODOT_BACKUP"

# Task 138 item 23: opt the demo's debug build into the typed-signal self-test (SignalSelfTest.kt;
# off for users). The setting sits under [kanama]; an existing key is replaced, a missing section added.
python3 - "$PROJECT_GODOT" <<'PY'
import re
import sys

path = sys.argv[1]
key = "debug/signal_self_test"
line = key + "=true"
with open(path, encoding="utf-8") as fh:
    text = fh.read()
if re.search(r"^" + re.escape(key) + r"=.*$", text, re.M):
    text = re.sub(r"^" + re.escape(key) + r"=.*$", line, text, flags=re.M)
elif re.search(r"^\[kanama\]\s*$", text, re.M):
    text = re.sub(r"^(\[kanama\]\s*)$", lambda m: m.group(1) + "\n" + line + "\n", text, count=1, flags=re.M)
else:
    text = text.rstrip("\n") + "\n\n[kanama]\n\n" + line + "\n"
with open(path, "w", encoding="utf-8") as fh:
    fh.write(text)
PY
grep -q '^debug/signal_self_test=true$' "$PROJECT_GODOT" || {
  echo "[android_smoke] could not write kanama/debug/signal_self_test into $PROJECT_GODOT" >&2
  exit 1
}
echo "[android_smoke] typed-signal self-test enabled for this run (kanama/debug/signal_self_test=true)"

if [[ -n "$RENDERER_OVERRIDE" ]]; then
  if ! grep -q '^renderer/rendering_method.mobile=' "$PROJECT_GODOT"; then
    echo "[android_smoke] demo has no renderer/rendering_method.mobile setting to override: $PROJECT_GODOT" >&2
    exit 1
  fi
  sed_inplace "$PROJECT_GODOT" \
    "s|^renderer/rendering_method.mobile=.*|renderer/rendering_method.mobile=\"$RENDERER_OVERRIDE\"|"
  echo "[android_smoke] renderer override for this run: rendering_method.mobile=\"$RENDERER_OVERRIDE\""
fi

echo "[android_smoke] demo: $DEMO_DIR"
build_demo_scripts

ANDROID_HOME="$ANDROID_SDK_DIR" ANDROID_SDK_ROOT="$ANDROID_SDK_DIR" \
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAndroidPluginAar \
  -PkanamaAndroidDemoDir="$DEMO_DIR"

install_desktop_addon
ensure_android_build_template

set_gradle_property "$DEMO_DIR/android/build/gradle.properties" \
  "plugins_maven_repos" "$PANAMAPORT_MAVEN_REPO"

MARKER="kanama-local-maven"
if grep -q "$MARKER" "$BUILD_GRADLE"; then
  sed_inplace "$BUILD_GRADLE" "/=== $MARKER /,\$d"
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

# Task 106/116: Godot caches converted scenes under .godot/exported keyed by the .tscn md5 + mtime;
# a conversion made without the desktop addon (properties stripped) would be reused here.
rm -rf "$DEMO_DIR/.godot/exported"

EXPORT_LOG="${KANAMA_ANDROID_EXPORT_LOG:-${APK_PATH%.apk}.export.log}"
echo "[android_smoke] export: $APK_PATH (log: $EXPORT_LOG)"
# `|| export_status=` keeps errexit from aborting the pipeline before the status is read (pipefail is on).
export_status=0
"$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --export-debug Android "$APK_PATH" 2>&1 | tee "$EXPORT_LOG" || export_status="${PIPESTATUS[0]}"
# The Kanama Tools export guard prints this line, and ends the export, when a .kt script the export
# uses does not bind in the export-time editor (desktop addon missing, or kanama-scripts.jar stale).
if grep -q 'Kanama: Export stopped' "$EXPORT_LOG"; then
  echo "[android_smoke] the Kanama export guard stopped the export: the export-time editor cannot bind the project's .kt scripts (see the 'Kanama: Export stopped' message above); log: $EXPORT_LOG" >&2
  exit 1
fi
if [[ "$export_status" -ne 0 ]]; then
  echo "[android_smoke] Godot export failed (exit $export_status); log: $EXPORT_LOG" >&2
  exit 1
fi
# The desktop runtime logs every .kt it loads with the class it bound; an empty class or a missing
# loader means the exported scenes carry none of that script's @Export values (task 106).
if grep -qE 'No loader found for resource: res://.*\.kt|ResourceFormatLoader\._load bound kotlinClass= ' "$EXPORT_LOG"; then
  echo "[android_smoke] the export-time editor could not bind the project's .kt scripts:" >&2
  # justified: diagnostics only; the next line exits 1 whatever this prints.
  grep -E 'No loader found for resource: res://.*\.kt|_load path=' "$EXPORT_LOG" | head -5 >&2 || true
  echo "[android_smoke] scene-stored @Export values would be missing from this APK; refusing to install it." >&2
  exit 1
fi
# Task 112: every converted scene keeps its script-declared properties.
SCENE_PARITY_CHECK="$ROOT_DIR/scripts/check_exported_scene_properties.gd"
if ! "$GODOT_BIN" --headless --path "$DEMO_DIR" --script "$SCENE_PARITY_CHECK" -- --require-converted; then
  echo "[android_smoke] exported scenes lost script properties (see [check_exported_scenes] lines above); refusing to install." >&2
  exit 1
fi

# Task 118: prove the APK is the package this run will launch, before anything is installed.
assert_apk_package_id "[android_smoke]" "$ANDROID_SDK_DIR" "$APK_PATH" "$PACKAGE_NAME" || exit 1

echo "[android_smoke] install: $PACKAGE_NAME"
adb_retry start-server >/dev/null
INSTALL_LOG="${APK_PATH%.apk}.install.log"
if ! "$ADB_BIN" install -r "$APK_PATH" >"$INSTALL_LOG" 2>&1; then
  # A leftover install from an older run may carry a different debug-keystore
  # signature (INSTALL_FAILED_UPDATE_INCOMPATIBLE). Smoke installs hold no user
  # data, so drop the stale package and install fresh.
  echo "[android_smoke] install -r failed (output below); uninstalling stale $PACKAGE_NAME and retrying"
  cat "$INSTALL_LOG" >&2
  # justified: the package may simply not be installed; the retried install on the next line is the check.
  "$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 || true
  adb_retry install "$APK_PATH" >/dev/null
fi
adb_retry logcat -c
# justified: the app may not be running yet; the launch below and the pid check after it decide.
adb_retry shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
# A dozing/locked screen fails renderer surface creation at launch (seen as
# "Failed to create vulkan window" under the Mobile renderer). Wake the device
# and dismiss a non-secure keyguard before launching; a PIN-locked keyguard
# still needs a human unlock.
# justified: best effort; a screen that stayed locked fails the launch/renderer/screenshot checks below.
adb_retry shell input keyevent KEYCODE_WAKEUP >/dev/null 2>&1 || true
# justified: best effort; a screen that stayed locked fails the launch/renderer/screenshot checks below.
adb_retry shell wm dismiss-keyguard >/dev/null 2>&1 || true
adb_retry shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null
PACKAGE_LAUNCHED=1
# Task 118: take the app's FIRST pid as soon as it appears and judge `logcat --pid <pid>` after the launch wait,
# instead of requiring the process to be alive at the end of it (a demo may quit on its own; a crash is read
# from the log). No pid within the wait means the app never started: fail, with the device log tail.
launch_started=$SECONDS
if ! APP_PID="$(wait_for_app_pid "$ADB_BIN" "$PACKAGE_NAME" "$LAUNCH_WAIT")"; then
  adb_retry logcat -d >"$LOG_FILE"
  echo "[android_smoke] $PACKAGE_NAME never showed a process within ${LAUNCH_WAIT}s of the launch (crashed on start or never started); logcat: $LOG_FILE" >&2
  tail -n 80 "$LOG_FILE" >&2
  exit 1
fi
launch_elapsed=$((SECONDS - launch_started))
if (( launch_elapsed < LAUNCH_WAIT )); then
  sleep $((LAUNCH_WAIT - launch_elapsed))
fi
adb_retry logcat --pid "$APP_PID" -d >"$LOG_FILE"

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
# task 138 item 23 -- the typed-signal self-test (SignalSelfTest.kt, shared desktop/Android code),
# switched on above through the project setting, runs on the first frame of the debug build and prints
# its verdict. An Android-only typed-emit
# failure (tagged heap pointers, task 138 item 21) lived five days because no smoke required a
# typed signal to fire; now a demo smoke fails if signals do not work on the device.
check_log "\\[kanama\\] SIGNAL SELFTEST: [1-9][0-9]* passed, 0 failed"
check_log_absent "SIGNAL SELFTEST FAIL"
check_log_absent "SIGNAL SELFTEST: [0-9]+ passed, [1-9][0-9]* failed"

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
check_log_absent "Fatal signal [0-9]+"
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
