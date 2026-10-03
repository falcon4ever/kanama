#!/usr/bin/env bash
# check_bootstrap_jdk_resolution.sh -- The native bootstrap's CMake configure picks the right JDK and refuses a stale jni.h on every OS.
#
# kanama#277: on Linux the editor's Build Scripts ran with the system default JDK (Java 26 on the
# reporter's box, no JAVA_HOME), `find_package(JNI)` accepted its headers without a check, and the
# build died in bootstrap.c with a bare "JNI_VERSION_21 undeclared". bootstrap/CMakeLists.txt now
# resolves one JDK for macOS/Linux/Windows (-DKANAMA_JAVA_HOME, then JAVA_HOME, then macOS
# java_home, then find_package(JNI)) and runs the same header check on all of them.
#
# This gate configures the bootstrap (configure only, no compile) against fake JDK homes built from
# the real JDK's include/ directory and asserts: a jni.h without JNI_VERSION_21 stops the configure
# with the clear message naming that JDK, whichever of -DKANAMA_JAVA_HOME / JAVA_HOME points at it;
# -DKANAMA_JAVA_HOME wins over JAVA_HOME; the real JDK configures. It runs on whatever host runs
# local_ci.sh, so the Linux CI job exercises the Linux include path (include/linux) for real.
#
# Needs cmake, a C compiler (the project is `project(... C)`) and one real JDK 25+: KANAMA_TEST_JDK,
# else JAVA_HOME, else macOS `java_home -v 25`.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TAG="[check_bootstrap_jdk_resolution]"

if ! command -v cmake >/dev/null 2>&1; then
  echo "$TAG cmake is required" >&2
  exit 2
fi

REAL_JDK="${KANAMA_TEST_JDK:-${JAVA_HOME:-}}"
if [[ -z "$REAL_JDK" && "$(uname -s)" == "Darwin" ]]; then
  REAL_JDK="$(/usr/libexec/java_home -v 25 2>/dev/null || true)"
fi
if [[ -z "$REAL_JDK" || ! -f "$REAL_JDK/include/jni.h" ]]; then
  echo "$TAG need a real JDK 25+ with include/jni.h (set KANAMA_TEST_JDK or JAVA_HOME); got '${REAL_JDK}'" >&2
  exit 2
fi
if ! grep -q "JNI_VERSION_21" "$REAL_JDK/include/jni.h"; then
  echo "$TAG the reference JDK $REAL_JDK has no JNI_VERSION_21 in jni.h; it is not a JDK 25" >&2
  exit 2
fi

TMP_ROOT="${TMPDIR:-/tmp}"
WORK="$(mktemp -d "${TMP_ROOT%/}/kanama_jdk_resolution.XXXXXX")"
trap 'rm -rf "$WORK"' EXIT

# A JDK-shaped directory: the real include/ tree (jni.h plus the per-OS jni_md.h).
fake_jdk() {
  local dir="$1"
  mkdir -p "$dir"
  cp -R "$REAL_JDK/include" "$dir/include"
}

OLD_JDK="$WORK/jdk8-like"
fake_jdk "$OLD_JDK"
# Strip every line naming JNI_VERSION_21 (and the later versions that reference it) so the header
# looks like JDK 17's.
grep -v "JNI_VERSION_21\|JNI_VERSION_24" "$REAL_JDK/include/jni.h" >"$OLD_JDK/include/jni.h"
if grep -q "JNI_VERSION_21" "$OLD_JDK/include/jni.h"; then
  echo "$TAG failed to build the stale-header fixture" >&2
  exit 1
fi

GOOD_JDK="$WORK/jdk25-like"
fake_jdk "$GOOD_JDK"

NO_MD_JDK="$WORK/jdk-no-platform-header"
fake_jdk "$NO_MD_JDK"
find "$NO_MD_JDK/include" -name jni_md.h -delete

failures=0
run_case() {
  # run_case <name> <expect: ok|fail> <expected output regex> <env JAVA_HOME or -> <cmake -D args...>
  local name="$1" expect="$2" pattern="$3" java_home="$4"
  shift 4
  local build_dir="$WORK/build-$name" log="$WORK/$name.log" rc=0
  if [[ "$java_home" == "-" ]]; then
    env -u JAVA_HOME cmake -S "$ROOT_DIR/bootstrap" -B "$build_dir" "$@" >"$log" 2>&1 || rc=$?
  else
    JAVA_HOME="$java_home" cmake -S "$ROOT_DIR/bootstrap" -B "$build_dir" "$@" >"$log" 2>&1 || rc=$?
  fi
  local verdict="ok"
  if [[ "$expect" == "ok" && $rc -ne 0 ]]; then
    verdict="expected configure to succeed, exit $rc"
  elif [[ "$expect" == "fail" && $rc -eq 0 ]]; then
    verdict="expected configure to fail, it succeeded"
  elif ! tr -s ' \n' '  ' <"$log" | grep -Eq -- "$pattern"; then
    verdict="output does not match: $pattern"
  fi
  if [[ "$verdict" == "ok" ]]; then
    echo "$TAG ok   $name"
  else
    echo "$TAG FAIL $name -- $verdict" >&2
    sed 's/^/    | /' "$log" >&2
    failures=$((failures + 1))
  fi
}

# JAVA_HOME alone, stale header: the clear message naming that JDK (the kanama#277 failure).
run_case env_stale_header fail \
  "predates JDK 21 \\(no JNI_VERSION_21\\)" "$OLD_JDK"
run_case env_stale_header_names_jdk fail \
  "jni.h under ${OLD_JDK} \\(found via JAVA_HOME\\)" "$OLD_JDK"
# -DKANAMA_JAVA_HOME (what Gradle passes) with a stale header, a good JAVA_HOME behind it: the
# explicit one is the JDK in use, so it is the one checked.
run_case gradle_stale_header fail \
  "jni.h under ${OLD_JDK} \\(found via -DKANAMA_JAVA_HOME" "$GOOD_JDK" "-DKANAMA_JAVA_HOME=$OLD_JDK"
# ... and the reverse: a stale JAVA_HOME does not matter when Gradle names a good JDK.
run_case gradle_beats_env ok \
  "Kanama bootstrap JDK: ${GOOD_JDK} \\(via -DKANAMA_JAVA_HOME" "$OLD_JDK" "-DKANAMA_JAVA_HOME=$GOOD_JDK"
# A correct JDK through JAVA_HOME configures.
run_case env_good ok \
  "Kanama bootstrap JDK: ${GOOD_JDK} \\(via JAVA_HOME\\)" "$GOOD_JDK"
# The real JDK configures too.
run_case env_real ok \
  "Kanama bootstrap JDK: ${REAL_JDK} \\(via JAVA_HOME\\)" "$REAL_JDK"
# A -DKANAMA_JAVA_HOME that is a JRE / not a JDK (no include/jni.h) is skipped, not trusted.
run_case gradle_not_a_jdk_falls_through ok \
  "Kanama bootstrap JDK: ${GOOD_JDK} \\(via JAVA_HOME\\)" "$GOOD_JDK" "-DKANAMA_JAVA_HOME=$WORK/not-a-jdk"
# A JDK without the per-OS jni_md.h fails with its own message instead of a compile error.
run_case missing_platform_header fail \
  "No jni_md.h under ${NO_MD_JDK}" "$NO_MD_JDK"

if [[ $failures -ne 0 ]]; then
  echo "$TAG FAIL -- $failures case(s)" >&2
  exit 1
fi
echo "$TAG all cases passed (host $(uname -s), reference JDK $REAL_JDK)"
