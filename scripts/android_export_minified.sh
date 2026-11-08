#!/usr/bin/env bash
set -euo pipefail

# Reproducible R8-minified Android release smoke for the Kanama plugin.
#
# Since Godot 4.8 the Android export has its own option, Gradle Build > Minification
# (`gradle_build/minification`), so this script takes the path a user takes: it turns the option on
# for the run and restores the preset afterwards. The Kanama export guard keeps R8 in compat mode.
#
# Flow:
#   - build + install the Kanama Android plugin AAR into the demo,
#   - check the Godot Android build template matches the editor, then (default) delete it, so the export is
#     the first one after a Godot upgrade: Godot installs the template during the export, after the plugins'
#     _export_begin, and the export guard must still get the R8 compat line into the template Gradle uses,
#   - turn on the preset's minification,
#   - export a *release* APK (R8 runs, consumer-rules.pro applies),
#   - install, launch, and assert Kanama starts past the PanamaPort FFI
#     bootstrap (the R8 gate this validates).

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/gate_skip.sh
source "$ROOT_DIR/scripts/gate_skip.sh"
# shellcheck source=scripts/portable.sh
source "$ROOT_DIR/scripts/portable.sh"
# shellcheck source=scripts/android_apk_id.sh
source "$ROOT_DIR/scripts/android_apk_id.sh"
ANDROID_TAG="[android_minified]"
# shellcheck source=scripts/android_common.sh
source "$ROOT_DIR/scripts/android_common.sh"

usage() {
  cat <<'EOF'
usage: scripts/android_export_minified.sh /path/to/godot /path/to/demo package.name [/path/to/output.apk]
  (default: a per-run directory under ${TMPDIR:-/tmp}, deleted when the run passes; pass a path to keep the APK)

Required environment:
  ANDROID_HOME or ANDROID_SDK_ROOT

Optional environment:
  ADB=/path/to/adb
  KANAMA_DEMO_JAVA_HOME=/path/to/jdk25
  KANAMA_ANDROID_LOG=/path/to/logcat.txt
  KANAMA_ANDROID_LAUNCH_WAIT=30
  KANAMA_ANDROID_TEMPLATE=fresh|stale|reuse
      fresh (default): delete the Android build template before the export, so Godot installs it during the
      export, as on the first export after a Godot upgrade. stale: leave a template marked as another Godot
      version's, carrying the R8 compat line as older Kanama versions left it, with Editor Settings > Export >
      Android > Build > Automatically Delete Build Directory on (without it Godot refuses the export). Godot
      then deletes all of res://android during the export, android/plugins (Kanama's plugin) too, and the
      run passes only when the export guard stops that export before Gradle. reuse: keep the installed template, as on
      every later export; the R8 compat line is taken out of it first, so only the export guard can put it back.
  KANAMA_PANAMAPORT_MAVEN_REPO=file:///path/to/.m2/repository
      One extra Maven repository for a PanamaPort fork published outside JitPack (with
      ORG_GRADLE_PROJECT_kanamaPanamaPortCore=<test coordinate>), patched into the template's build.gradle,
      so it needs KANAMA_ANDROID_TEMPLATE=reuse. Unset by default: the export then resolves PanamaPort only
      through KanamaAndroid.gdap, as a user's editor export does.
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
# The APK and logcat default to a directory of this run's own (mktemp): a fixed /tmp path was shared by concurrent runs.
# shellcheck source=scripts/run_logs.sh
source "$ROOT_DIR/scripts/run_logs.sh"
run_logs_dir kanama_android_minified
RUN_DIR="$RUN_LOGS_DIR"
APK_PATH="${4:-$RUN_DIR/kanama-android-minified.apk}"
LOG_FILE="${KANAMA_ANDROID_LOG:-$RUN_DIR/kanama_android_minified.log}"
echo "[android_export_minified] output: $RUN_DIR"
LAUNCH_WAIT="${KANAMA_ANDROID_LAUNCH_WAIT:-30}"
PANAMAPORT_MAVEN_REPO="${KANAMA_PANAMAPORT_MAVEN_REPO:-}"
TEMPLATE_MODE="${KANAMA_ANDROID_TEMPLATE:-fresh}"
case "$TEMPLATE_MODE" in
  fresh | stale | reuse) ;;
  *)
    echo "[android_minified] KANAMA_ANDROID_TEMPLATE must be fresh, stale or reuse, not '$TEMPLATE_MODE'" >&2
    exit 2
    ;;
esac
if [[ -n "$PANAMAPORT_MAVEN_REPO" && "$TEMPLATE_MODE" != reuse ]]; then
  echo "[android_minified] KANAMA_PANAMAPORT_MAVEN_REPO patches the template's build.gradle before the export: set KANAMA_ANDROID_TEMPLATE=reuse" >&2
  exit 2
fi

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

cleanup() {
  local status=$?
  if [[ "${PACKAGE_LAUNCHED:-0}" == "1" ]]; then
    # justified: cleanup after the verdict; the app may already have exited.
    "$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
  fi
  if [[ -n "${PRESET_BACKUP:-}" && -f "$PRESET_BACKUP" ]]; then
    cp "$PRESET_BACKUP" "$DEMO_DIR/export_presets.cfg"
  fi
  run_logs_finish "$status" android_export_minified
}
trap cleanup EXIT

echo "[android_minified] demo: $DEMO_DIR"
# Task 119 item 40: compile the demo's scripts in source-checkout mode, against THIS Kanama checkout's
# API and KSP processor (the same `:project-scripts:jar -PkanamaProjectScriptsDir` path the demos' desktop
# CI lane uses), never through the demo's own Gradle build, which resolves `kanama`/`processor` from
# mavenLocal (whatever main last published). The Android scripts AAR below takes its registrars from the
# same checkout's processor (`:project-scripts:kspKotlin`), so a branch's API or processor change is what
# gets built and exported.
build_demo_scripts

echo "[android_minified] build + install Kanama AAR"
ANDROID_HOME="$ANDROID_SDK_DIR" ANDROID_SDK_ROOT="$ANDROID_SDK_DIR" \
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAndroidPluginAar \
  -PkanamaAndroidDemoDir="$DEMO_DIR"
# The release path keeps no API exercise rows (they ride only in android_smoke.sh's flagged debug AAR).
python3 "$ROOT_DIR/scripts/generate_api_exercise.py" --check-jar "$DEMO_DIR/android/plugins/KanamaAndroid.debug.aar"

# Task 116: installed after the AAR so installAddonJar preserves the Android .gdextension entries.
install_desktop_addon

# 1) Install/refresh the Godot Android build template (regenerates build.gradle).
ensure_android_build_template

# 2) Turn on the preset's minification for this run; cleanup restores the preset.
PRESET_BACKUP="$RUN_DIR/export_presets.cfg.orig"
cp "$DEMO_DIR/export_presets.cfg" "$PRESET_BACKUP"
python3 - "$DEMO_DIR/export_presets.cfg" <<'EOF'
import re
import sys

path = sys.argv[1]
with open(path, encoding="utf-8") as handle:
    text = handle.read()
sections = re.split(r"(?m)^(?=\[preset\.)", text)
for index, section in enumerate(sections):
    if re.match(r"\[preset\.\d+\]", section) and 'platform="Android"' in section:
        options = re.sub(r"(?m)^gradle_build/minification=.*\n", "", sections[index + 1])
        sections[index + 1] = re.sub(r"(?m)^(\[preset\.\d+\.options\]\n)", r"\1gradle_build/minification=true\n", options, count=1)
        break
else:
    raise SystemExit(f"no Android preset in {path}")
with open(path, "w", encoding="utf-8") as handle:
    handle.write("".join(sections))
EOF
echo "[android_minified] preset: gradle_build/minification=true for this run"

GP="$DEMO_DIR/android/build/gradle.properties"
# The key ends at `=`, `:`, whitespace or the end of the line, as java.util.Properties reads it.
R8_KEY_RE='^[[:space:]]*android\.enableR8\.fullMode([[:space:]=:]|$)'
if [[ "$TEMPLATE_MODE" == fresh ]]; then
  # The first export after a Godot upgrade: no template yet. Godot installs it inside the export, after the
  # plugins' _export_begin and before Gradle, so a line written at _export_begin would never reach Gradle.
  echo "[android_minified] template: deleted, Godot installs it during the export (KANAMA_ANDROID_TEMPLATE=fresh)"
  rm -rf "$DEMO_DIR/android/build" "$DEMO_DIR/android/.build_version"
elif [[ "$TEMPLATE_MODE" == stale ]]; then
  # A template from another Godot version, with the line older Kanama versions wrote: Godot deletes it during
  # the export (Automatically Delete Build Directory) and unzips a fresh one, after the plugins' _export_begin.
  echo "[android_minified] template: marked stale, Godot replaces it during the export (KANAMA_ANDROID_TEMPLATE=stale)"
  printf '%s\n' "kanama-smoke-stale" >"$DEMO_DIR/android/.build_version"
  printf '%s\n' "android.enableR8.fullMode=false" >>"$GP"
else
  echo "[android_minified] template: reused (KANAMA_ANDROID_TEMPLATE=reuse)"
  # Drop what earlier script versions left in the template.
  rm -f "$DEMO_DIR/android/build/kanama-minify.pro"
  # A reused template (same Godot version) keeps its gradle.properties, and earlier script versions wrote the
  # R8 compat line into it. Take every setting of the property out, so after the export only the export guard
  # can have put it back: the check below then proves the guard writes it, as it must for a user.
  if [[ ! -f "$GP" ]]; then
    echo "[android_minified] the Android build template has no gradle.properties: $GP" >&2
    exit 1
  fi
  sed_inplace "$GP" -E "/$R8_KEY_RE/d"
  if grep -qE "$R8_KEY_RE" "$GP"; then
    echo "[android_minified] could not take android.enableR8.fullMode out of $GP before the export" >&2
    exit 1
  fi

  # Strip earlier runs' blocks, this script's and android_smoke.sh's (marker -> EOF each).
  for marker in kanama-local-maven kanama-r8-minify; do
    if grep -q "=== $marker " "$BUILD_GRADLE"; then
      sed_inplace "$BUILD_GRADLE" "/=== $marker /,\$d"
    fi
  done
  # Repositories are NOT added here: PanamaPort resolves through the repository KanamaAndroid.gdap
  # declares, as in a user's editor export (KANAMA_PANAMAPORT_MAVEN_REPO adds one).
  if [[ -n "$PANAMAPORT_MAVEN_REPO" ]]; then
    cat >>"$BUILD_GRADLE" <<GRADLE

// === kanama-local-maven (injected by scripts/android_export_minified.sh, KANAMA_PANAMAPORT_MAVEN_REPO) ===
allprojects {
    repositories {
        maven { url '$PANAMAPORT_MAVEN_REPO' }
    }
}
GRADLE
  fi
fi

# 3) Export the release APK (fresh: Godot installs the template first; reuse: it keeps the patch).
rm -rf "$DEMO_DIR/.godot/exported" # task 106/116: never reuse a conversion made without the desktop addon
EXPORT_LOG="${KANAMA_ANDROID_EXPORT_LOG:-${APK_PATH%.apk}.export.log}"
echo "[android_minified] export release (R8): $APK_PATH (log: $EXPORT_LOG)"
# `|| export_status=` keeps errexit from aborting the pipeline before the status is read (pipefail is on).
export_status=0
"$GODOT_BIN" --headless \
  --path "$DEMO_DIR" \
  --export-release Android "$APK_PATH" 2>&1 | tee "$EXPORT_LOG" || export_status="${PIPESTATUS[0]}"
# The Kanama Tools export guard prints this line, and ends the export, when a .kt script the export
# uses does not bind in the export-time editor (desktop addon missing, or kanama-scripts.jar stale),
# when the Android preset's Min SDK is below 33 or empty, or when it cannot write the R8 compat line.
if [[ "$TEMPLATE_MODE" == stale ]]; then
  # Godot's automatic deletion takes res://android/plugins with the old template: the export would build an
  # APK with no Kanama in it. The guard must stop it, naming the lost plugin, before Gradle runs.
  if grep -q 'Kanama: Export stopped: Godot deleted .*KanamaAndroid.gdap.* from res://android/plugins' "$EXPORT_LOG" &&
    [[ "$export_status" -ne 0 && ! -f "$APK_PATH" ]]; then
    echo "[android_minified] PASS (stale template: Godot deleted res://android/plugins with it, and the export guard stopped the export before Gradle)"
    exit 0
  fi
  echo "[android_minified] stale template: the export guard did not stop an export that lost res://android/plugins/KanamaAndroid.gdap (exit $export_status); log: $EXPORT_LOG" >&2
  exit 1
fi
if grep -q 'Kanama: Export stopped' "$EXPORT_LOG"; then
  echo "[android_minified] the Kanama export guard stopped the export: a .kt script does not bind in the export-time editor, the preset's Min SDK is below 33, or the R8 compat line could not be written (the 'Kanama: Export stopped' message above names which); log: $EXPORT_LOG" >&2
  exit 1
fi
if [[ "$export_status" -ne 0 ]]; then
  echo "[android_minified] Godot export failed (exit $export_status); log: $EXPORT_LOG" >&2
  exit 1
fi
if grep -qE 'No loader found for resource: res://.*\.kt|ResourceFormatLoader\._load bound kotlinClass= ' "$EXPORT_LOG"; then
  echo "[android_minified] the export-time editor could not bind the project's .kt scripts; scene-stored @Export values would be missing (task 106/116)" >&2
  exit 1
fi
# The check quits on its first frame, so Godot's exit-time leak report is the project's own shutdown:
# a leak there (an autoload's playing stream, a Resource a script holds) fails the run too.
SCENE_CHECK_LOG="${APK_PATH%.apk}.scene_check.log"
scene_check_status=0
"$GODOT_BIN" --headless --path "$DEMO_DIR" --script "$ROOT_DIR/scripts/check_exported_scene_properties.gd" -- --require-converted >"$SCENE_CHECK_LOG" 2>&1 || scene_check_status=$?
cat "$SCENE_CHECK_LOG"
if [[ "$scene_check_status" -ne 0 ]]; then
  echo "[android_minified] exported scenes lost script properties (task 112 check); refusing to install." >&2
  exit 1
fi
if grep -qE 'leaked at exit|still in use at exit' "$SCENE_CHECK_LOG"; then
  echo "[android_minified] the exported-scene check leaked at exit (above; rerun it with --verbose to name the instances): $SCENE_CHECK_LOG" >&2
  exit 1
fi

if [[ ! -f "$APK_PATH" ]]; then
  echo "[android_minified] export did not produce an APK: $APK_PATH" >&2
  exit 1
fi

# Task 118: prove the APK is the package this run will launch, before anything is installed.
assert_apk_package_id "[android_minified]" "$ANDROID_SDK_DIR" "$APK_PATH" "$PACKAGE_NAME" || exit 1

# PanamaPort's R8 rules hold in compat mode only: AGP's default full mode corrupts its downcall-stub
# dispatch, and the crash shows only on the device. The export guard writes this line, as for a user: Godot
# installed this template without it (fresh), or this run took it out before the export (reuse), so it is
# there only if the guard wrote it into the template Gradle built from, and exactly once.
r8_lines="$(R8_KEY_RE="$R8_KEY_RE" awk '$0 ~ ENVIRON["R8_KEY_RE"] { n++ } END { print n + 0 }' "$GP")"
if ! grep -q '^android.enableR8.fullMode=false$' "$GP" || [[ "$r8_lines" != 1 ]]; then
  echo "[android_minified] R8 did not run in compat mode: $GP should hold exactly one android.enableR8.fullMode=false, written by the export guard during the export (it holds $r8_lines setting(s) of the property)" >&2
  exit 1
fi

echo "[android_minified] install: $PACKAGE_NAME"
"$ADB_BIN" start-server >/dev/null
INSTALL_LOG="${APK_PATH%.apk}.install.log"
if ! "$ADB_BIN" install -r "$APK_PATH" >"$INSTALL_LOG" 2>&1; then
  # A leftover install from the debug smoke carries the Godot editor debug
  # keystore's signature, which differs from this gate's release keystore
  # (INSTALL_FAILED_UPDATE_INCOMPATIBLE). Smoke installs hold no user data,
  # so drop the stale package and install fresh — same hardening as
  # android_smoke.sh.
  echo "[android_minified] install -r failed (output below); uninstalling stale $PACKAGE_NAME and retrying"
  cat "$INSTALL_LOG" >&2
  # justified: the package may simply not be installed; the plain install on the next line is the check.
  "$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 || true
  "$ADB_BIN" install "$APK_PATH" >/dev/null
fi
"$ADB_BIN" logcat -c
# justified: the app may not be running yet; the launch below and the pid check after it decide.
"$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 || true
"$ADB_BIN" shell monkey --pct-syskeys 0 -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null
PACKAGE_LAUNCHED=1
# Task 118: take the app's FIRST pid as soon as it appears and judge `logcat --pid <pid>` after the launch wait,
# instead of requiring the process to be alive at the end of it (a demo may quit on its own; a crash is read
# from the log). No pid within the wait means the app never started: fail, with the device log tail.
launch_started=$SECONDS
if ! APP_PID="$(wait_for_app_pid "$ADB_BIN" "$PACKAGE_NAME" "$LAUNCH_WAIT")"; then
  "$ADB_BIN" logcat -d >"$LOG_FILE"
  echo "[android_minified] $PACKAGE_NAME never showed a process within ${LAUNCH_WAIT}s of the launch (crashed on start or never started); logcat: $LOG_FILE" >&2
  tail -n 80 "$LOG_FILE" >&2
  exit 1
fi
launch_elapsed=$((SECONDS - launch_started))
if (( launch_elapsed < LAUNCH_WAIT )); then
  sleep $((LAUNCH_WAIT - launch_elapsed))
fi
"$ADB_BIN" logcat --pid "$APP_PID" -d >"$LOG_FILE"

check_log "Initializing Godot plugin KanamaAndroid"
check_log "registered KanamaResourceFormatLoader for \\.kt"

# Negative: the R8 failure signatures must be gone.
check_log_absent "Should not reach here"
check_log_absent "GDExtension initialization function 'kanama_entry' returned an error"
check_log_absent "Error loading extension"
check_log_absent "No loader found for resource: res://kotlin-src"
check_log_absent "FATAL EXCEPTION"
check_log_absent "Fatal signal [0-9]+"

echo "[android_minified] PASS (R8-minified release boots Kanama)"
