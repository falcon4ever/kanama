# shellcheck shell=bash
# android_apk_id.sh -- "the artifact this gate ran is the artifact it built" for the Android runners
# (task 118), and the one way they find the app's process. Source it (after gate_skip.sh); do not run it.
#
#   assert_apk_package_id <tag> <android-sdk-dir> <apk> <expected-package>
#
# `adb install` accepts any APK and `monkey -p <package>` launches whatever sits under <package>, so
# an export whose preset names a different `package/unique_name` than the one the script was told to
# launch installs fine and then starts an OLDER install of <package>: its logcat is judged as this
# build's (the iOS launch-mismatch of task 115, on Android). This reads the APK's own application id
# (aapt2 from the SDK build-tools, else apkanalyzer) and refuses to install on a mismatch. With
# neither tool it is a gate_skip (SKIP line; fatal under CI unless listed in KANAMA_ALLOW_SKIP).

apk_application_id() {
  local sdk="$1" apk="$2" tool
  # justified: a missing build-tools dir just leaves $tool empty and falls through to apkanalyzer, then to the SKIP below.
  tool="$(ls -d "$sdk"/build-tools/*/aapt2 2>/dev/null | sort -V | tail -n 1)"
  if [[ -n "$tool" ]]; then
    "$tool" dump packagename "$apk"
    return
  fi
  # justified: a missing cmdline-tools dir just leaves $tool empty, which returns 3 (the gate_skip path).
  tool="$(ls -d "$sdk"/cmdline-tools/*/bin/apkanalyzer 2>/dev/null | sort -V | tail -n 1)"
  if [[ -n "$tool" ]]; then
    "$tool" manifest application-id "$apk"
    return
  fi
  return 3
}

assert_apk_package_id() {
  local tag="$1" sdk="$2" apk="$3" expected="$4" actual rc=0
  actual="$(apk_application_id "$sdk" "$apk")" || rc=$?
  if [[ "$rc" -eq 3 ]]; then
    gate_skip android-apk-package-id "no aapt2 under $sdk/build-tools and no apkanalyzer under $sdk/cmdline-tools; cannot prove the APK's package is $expected" || return 1
    return 0
  fi
  if [[ "$rc" -ne 0 || -z "$actual" ]]; then
    echo "$tag cannot read the application id of $apk (exit $rc)" >&2
    return 1
  fi
  if [[ "$actual" != "$expected" ]]; then
    echo "$tag the exported APK's application id is '$actual' but this run was asked to install and launch '$expected'." >&2
    echo "$tag Launching '$expected' would start whatever older build is installed under that id; refusing. Fix package/unique_name in the demo's export preset (or the package argument)." >&2
    return 1
  fi
  echo "$tag APK application id verified: $actual"
}

# app_pid_once <adb> <package>: prints the pid of the running app, or nothing.
# `pidof -s` is toybox's; Android 9 (API 28) has a toybox pidof, but not every build has `-s` (or any
# pidof), so an empty answer falls back to `ps -A`, whose last column is the process name (= the package).
app_pid_once() {
  local adb="$1" package="$2" pid
  # justified: a failing or missing pidof prints nothing, and the `ps -A` fallback below is the second opinion.
  pid="$("$adb" shell pidof -s "$package" 2>/dev/null | tr -d '\r' || true)"
  if [[ -z "$pid" ]]; then
    # justified: an adb error leaves $pid empty; the caller retries until its deadline and then fails.
    pid="$("$adb" shell ps -A 2>/dev/null | tr -d '\r' | awk -v p="$package" '$NF == p { print $2; exit }' || true)"
  fi
  printf '%s' "$pid"
}

# wait_for_app_pid <adb> <package> <timeout-seconds>: prints the FIRST pid the app shows, polling once a
# second; returns 1 when none appears in time (the app never started or died instantly). The runners judge
# `logcat --pid <this pid>`, not "is it still alive at second 30": a demo may legitimately quit on its own
# (Bunnymark ends once its benchmark converges) while a crash is read from the log, not from liveness.
wait_for_app_pid() {
  local adb="$1" package="$2" timeout="$3" deadline pid
  deadline=$((SECONDS + timeout))
  while :; do
    pid="$(app_pid_once "$adb" "$package")"
    if [[ -n "$pid" ]]; then
      printf '%s' "$pid"
      return 0
    fi
    if (( SECONDS >= deadline )); then
      return 1
    fi
    sleep 1
  done
}
