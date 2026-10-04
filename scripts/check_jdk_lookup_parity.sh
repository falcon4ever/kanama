#!/usr/bin/env bash
# check_jdk_lookup_parity.sh -- The runtime (bootstrap.c) and the editor plugin pick the same JDK from the same layout (kanama#277).
#
# On the #277 setup (only /usr/lib/jvm/jdk-26, no JAVA_HOME) the plugin's preflight said everything
# was fine while bootstrap.c, which only knew JAVA_HOME and a few fixed *-25-* paths, logged
# "libjvm not found". Both now implement one lookup (bundled runtime, the plugin-recorded
# kanama/build/jdk_path, JAVA_HOME, then the best JDK 25+ in the install locations: GA before EA,
# exactly 25 before newer, newer version, smaller path). This gate lays out fake JDKs (symlinks to
# the real JDK 25 plus a `release` file of their own, so the real libjvm boots) under a scratch
# directory, points KANAMA_TEST_JDK_SEARCH_DIRS at it, and for each scenario starts the example project
# in Godot (bootstrap.c's "[kanama] using libjvm:" line) and runs the plugin's resolution
# (scripts/print_jdk_lookup.gd), then asserts both chose the expected JDK, JAVA_HOME unset
# unless a scenario sets it. The location TABLES are compared by check_jdk_locations_parity.py.
#
# usage: scripts/check_jdk_lookup_parity.sh /absolute/path/to/godot_binary
# Needs a real JDK 25+ (KANAMA_TEST_JDK, else JAVA_HOME, else macOS java_home -v 25). Unix hosts only.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/gate_skip.sh
source "$ROOT_DIR/scripts/gate_skip.sh"
TAG="[check_jdk_lookup_parity]"
PROJECT_DIR="$ROOT_DIR/example_project"

if [[ $# -lt 1 ]]; then
  echo "usage: $0 /absolute/path/to/godot_binary" >&2
  exit 2
fi
GODOT_BIN="$1"

case "$(uname -s)" in
  MINGW* | MSYS* | CYGWIN*)
    # No CI lane runs this on Windows; if one ever does it must opt out by name (KANAMA_ALLOW_SKIP).
    gate_skip jdk-lookup-parity-windows "symlink fixture needs a Unix host; the location table is still checked by check_jdk_locations_parity.py" || exit 1
    exit 0
    ;;
esac

REAL_JDK="${KANAMA_TEST_JDK:-${JAVA_HOME:-}}"
if [[ -z "$REAL_JDK" && "$(uname -s)" == "Darwin" ]]; then
  # justified: no JDK 25 leaves REAL_JDK empty, which the "need a real JDK 25+" check just below turns into exit 2.
  REAL_JDK="$(/usr/libexec/java_home -v 25 2>/dev/null || true)"
fi
if [[ -z "$REAL_JDK" || ! -d "$REAL_JDK/lib/server" ]]; then
  echo "$TAG need a real JDK 25+ (set KANAMA_TEST_JDK or JAVA_HOME); got '${REAL_JDK}'" >&2
  exit 2
fi

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null

TMP_ROOT="${TMPDIR:-/tmp}"
WORK="$(mktemp -d "${TMP_ROOT%/}/kanama_jdk_lookup.XXXXXX")"
HINT_FILE="$PROJECT_DIR/.godot/kanama_jdk_home"
HINT_BACKUP=""
if [[ -f "$HINT_FILE" ]]; then
  HINT_BACKUP="$WORK/hint.backup"
  cp "$HINT_FILE" "$HINT_BACKUP"
fi
cleanup() {
  rm -f "$HINT_FILE"
  if [[ -n "$HINT_BACKUP" && -f "$HINT_BACKUP" ]]; then
    cp "$HINT_BACKUP" "$HINT_FILE"
  fi
  rm -rf "$WORK"
}
trap cleanup EXIT

# The plugin side runs in a scratch project that only carries the plugin.
PLUGIN_PROJECT="$WORK/plugin_project"
mkdir -p "$PLUGIN_PROJECT/addons"
cp -R "$ROOT_DIR/templates/starter/addons/kanama_tools" "$PLUGIN_PROJECT/addons/"
cp "$ROOT_DIR/scripts/print_jdk_lookup.gd" "$PLUGIN_PROJECT/"
printf 'config_version=5\n\n[application]\nconfig/name="jdk-lookup-parity"\n' >"$PLUGIN_PROJECT/project.godot"

# fake_jdk <dir> <JAVA_VERSION> [nolibjvm]: symlinks to the real JDK, own release file.
fake_jdk() {
  local dir="$1" version="$2" flavour="${3:-}"
  mkdir -p "$dir"
  ln -s "$REAL_JDK/bin" "$dir/bin"
  ln -s "$REAL_JDK/include" "$dir/include"
  # justified: conf/ and legal/ are optional JDK parts; a JDK without them is still a valid fixture, and the
  # scenario's libjvm lookup (the thing under test) decides.
  ln -s "$REAL_JDK/conf" "$dir/conf" 2>/dev/null || true
  ln -s "$REAL_JDK/legal" "$dir/legal" 2>/dev/null || true
  if [[ "$flavour" == "nolibjvm" ]]; then
    mkdir -p "$dir/lib"
  else
    ln -s "$REAL_JDK/lib" "$dir/lib"
  fi
  printf 'JAVA_VERSION="%s"\n' "$version" >"$dir/release"
}

# Godot aborts when the runtime finds no JVM (the NONE scenarios); keep the shell's "Abort trap" notice out of the output.
run_logged() {
  local log="$1"
  shift
  # justified: a scenario whose JVM is not found makes Godot abort on purpose; the verdict is the libjvm line read
  # from "$log" afterwards, never this exit status.
  ( "$@" >"$log" 2>&1 ) 2>/dev/null || true
}

failures=0
scenario_n=0
# table_mode=1: the scenario uses the REAL location table: no search-dir override, HOME points at
# <root>/home, and the fake JDKs sit at paths relative to it (so ~ and the per-OS suffix expansion run).
table_mode=0
# scenario <name> <expected dir name or NONE> <java_home dir name or -> <hint dir name or -> <jdk dirs...>
# A dir name is relative to the scenario's fake root; each scenario gets its own root.
scenario() {
  local name="$1" expected="$2" java_home_name="$3" hint_name="$4"
  shift 4
  scenario_n=$((scenario_n + 1))
  local root="$WORK/s$scenario_n"
  mkdir -p "$root"
  local jdk_base="$root"
  if [[ $table_mode -eq 1 ]]; then
    jdk_base="$root/home"
    mkdir -p "$jdk_base"
  fi
  local spec
  for spec in "$@"; do
    # spec = dirname=version[=nolibjvm]
    IFS='=' read -r dir_name version flavour <<<"$spec"
    fake_jdk "$jdk_base/$dir_name" "$version" "${flavour:-}"
  done
  local -a env_args=("KANAMA_TEST_JDK_SEARCH_DIRS=$root")
  if [[ $table_mode -eq 1 ]]; then
    env_args=("HOME=$jdk_base")
  fi
  local java_home_value=""
  if [[ "$java_home_name" != "-" ]]; then
    java_home_value="$jdk_base/$java_home_name"
  fi
  local setting=""
  rm -f "$HINT_FILE"
  if [[ "$hint_name" != "-" ]]; then
    setting="$jdk_base/$hint_name"
    mkdir -p "$PROJECT_DIR/.godot"
    printf '%s\n' "$setting" >"$HINT_FILE"
  fi

  local boot_log="$WORK/s$scenario_n.boot.log" plugin_log="$WORK/s$scenario_n.plugin.log"
  if [[ -n "$java_home_value" ]]; then
    run_logged "$boot_log" env -u JAVA_HOME JAVA_HOME="$java_home_value" "${env_args[@]}" "$GODOT_BIN" --headless --path "$PROJECT_DIR" --quit
    run_logged "$plugin_log" env -u JAVA_HOME JAVA_HOME="$java_home_value" "${env_args[@]}" KANAMA_PARITY_SETTING="$setting" "$GODOT_BIN" --headless --path "$PLUGIN_PROJECT" --script res://print_jdk_lookup.gd
  else
    run_logged "$boot_log" env -u JAVA_HOME "${env_args[@]}" "$GODOT_BIN" --headless --path "$PROJECT_DIR" --quit
    run_logged "$plugin_log" env -u JAVA_HOME "${env_args[@]}" KANAMA_PARITY_SETTING="$setting" "$GODOT_BIN" --headless --path "$PLUGIN_PROJECT" --script res://print_jdk_lookup.gd
  fi

  local boot_pick plugin_pick want
  boot_pick="$(sed -n 's/^\[kanama\] using libjvm: //p' "$boot_log" | head -n 1)"
  if [[ -z "$boot_pick" ]] && grep -q "libjvm not found" "$boot_log"; then
    boot_pick="NONE"
  fi
  plugin_pick="$(sed -n 's/^\[parity\] libjvm=//p' "$plugin_log" | head -n 1)"
  if [[ "$expected" == "NONE" ]]; then
    want="NONE"
  else
    want="$jdk_base/$expected/lib/server/libjvm.$([[ "$(uname -s)" == "Darwin" ]] && echo dylib || echo so)"
  fi
  if [[ "$boot_pick" == "$want" && "$plugin_pick" == "$want" ]]; then
    echo "$TAG ok   $name -> ${expected}"
  else
    echo "$TAG FAIL $name: expected $want" >&2
    echo "$TAG   bootstrap.c picked: '${boot_pick}'" >&2
    echo "$TAG   plugin picked:      '${plugin_pick}'" >&2
    echo "$TAG   --- bootstrap log tail" >&2
    # justified: diagnostics on a scenario already counted as a failure above.
    grep -E "^\[kanama\]" "$boot_log" | tail -n 12 | sed 's/^/    | /' >&2 || true
    failures=$((failures + 1))
  fi
}

# No JAVA_HOME, only a JDK 26 (the kanama#277 box): both find it.
scenario only_jdk26 jdk-26 - - jdk-26=26.0.1
# Exactly 25 beats a newer GA.
scenario exact25_over_newer jdk-25 - - jdk-25=25.0.4.1 jdk-26=26.0.1
# Among 25s the newer patch wins, numerically (25.0.10 > 25.0.4).
scenario newer_patch jdk-25-10 - - jdk-25-4=25.0.4 jdk-25-10=25.0.10
# GA over EA, whatever the major.
scenario ga_over_ea jdk-27 - - jdk-26-ea=26-ea jdk-27=27.0.1
scenario ga_over_exact25_ea jdk-26 - - jdk-25-ea=25-ea jdk-26=26.0.1
# Only EA left: exactly 25 first.
scenario only_ea_exact25 jdk-25-ea - - jdk-25-ea=25-ea jdk-27-ea=27-ea
# Too old, and a JDK 25 whose libjvm is missing: neither counts.
scenario too_old NONE - - jdk-17=17.0.12 jdk-21=21.0.4
scenario no_libjvm_skipped jdk-26 - - jdk-30=30.0.1=nolibjvm jdk-26=26.0.1
# JAVA_HOME beats the scan; an old JAVA_HOME is skipped.
scenario java_home_wins jdk-26 jdk-26 - jdk-25=25.0.4.1 jdk-26=26.0.1
scenario old_java_home_skipped jdk-25 jdk-17 - jdk-25=25.0.4.1 jdk-17=17.0.12
# The explicit setting (bootstrap.c reads it as <project>/.godot/kanama_jdk_home) beats JAVA_HOME and the scan.
scenario setting_wins jdk-26 jdk-25 jdk-26 jdk-25=25.0.4.1 jdk-26=26.0.1

# The real table (no override): a fake JDK 25.99.0 under the fake HOME beats the machine's own JDK 25
# (newer patch of exactly 25), so both sides must have expanded the table's "~" rows. On macOS the
# per-OS suffix (~/Library/Java/JavaVirtualMachines/<x>.jdk/Contents/Home) is exercised too.
table_mode=1
scenario table_home_dot_jdks .jdks/jdk-25-fake - - .jdks/jdk-25-fake=25.99.0
if [[ "$(uname -s)" == "Darwin" ]]; then
  scenario table_home_macos_suffix Library/Java/JavaVirtualMachines/fake.jdk/Contents/Home - - Library/Java/JavaVirtualMachines/fake.jdk/Contents/Home=25.99.0
else
  scenario table_home_sdkman .sdkman/candidates/java/25.99.0-fake - - .sdkman/candidates/java/25.99.0-fake=25.99.0
fi
table_mode=0

if [[ $failures -ne 0 ]]; then
  echo "$TAG FAIL -- $failures scenario(s)" >&2
  exit 1
fi
echo "$TAG all $scenario_n scenarios passed (reference JDK $REAL_JDK)"
