#!/usr/bin/env bash
# check_gradle_templates_configure.sh -- the Gradle files users receive still configure under the repo's Gradle.
#
# Every starter a user can follow is a Gradle project with its own wrapper: the source-checkout starter
# (`createStarterProject`), the desktop kit (the same copySpec, `kanamaGradleProjectSpec` in build.gradle.kts) and
# a project set up from the store addon (`setup-kanama-project.sh` copies addons/kanama/templates/release-kit). Build
# Scripts and IntelliJ run Gradle on those files. Gradle rejects a settings file whose `plugins {}` block precedes
# `pluginManagement {}`: that mistake once shipped in the release-kit template and only package.yml would have noticed,
# after the merge. And the README once told users to run `./gradlew buildScripts` in a starter that had no wrapper.
#
# This gate builds each layout in a scratch directory -- the starter with the real `createStarterProject`, the
# store-addon project by running the real setup script over the files packageStoreAddon ships -- and in each:
#   1. `gradlew`, `gradlew.bat` and the wrapper exist,
#   2. `./gradlew tasks --all` configures (no dependency resolution, no compile),
#   3. every task the getting-started and shipping pages and the README Quick Start tell the user to run in a project exists there,
#      and every task they tell the user to run in the Kanama checkout exists in the repo build.
# The templates are used as shipped (only @KANAMA_VERSION@ is substituted, as the packaging tasks do). The Kanama Gradle
# plugin comes from addons/kanama/maven as in a kit: this gate publishes it from the checkout and copies it there.
#
# 4. The version lock (task 143 item 1): settings.gradle.kts takes the plugin's version from addons/kanama/kanama.version.
#    A missing file, a version the addon's Maven repository lacks (a half-unzipped addon), and a plugin version pinned
#    by hand that disagrees with the file each stop the build with their one line.
#
# Needs network the first time (the Kotlin and KSP plugins come from the plugin portal).
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/portable.sh
source "$ROOT_DIR/scripts/portable.sh"
TAG="[check_gradle_templates_configure]"

TMP_ROOT="${TMPDIR:-/tmp}"
WORK="$(mktemp -d "${TMP_ROOT%/}/kanama_template_configure.XXXXXX")"
trap 'rm -rf "$WORK"' EXIT

fail() {
  echo "$TAG FAIL -- $1" >&2
  if [[ -n "${2:-}" && -f "$2" ]]; then
    sed 's/^/    | /' "$2" >&2
  fi
  exit 1
}

# The documented commands: `<repo|project> <task> <page>:<line>` per `./gradlew <task>` in a fenced shell block
# (```sh, ```bash title="..." and the like). A block runs in the page's default place; `cd kanama` moves it to the
# checkout, any other `cd` or the store-addon setup script to the project; `gradlew -p <dir>` always runs in the
# checkout. A task path (`:processor:test`) is checked by its path. The pages include the READMEs a new project
# ships with (the kit's and the source-checkout starter's) and the starter template's.
documented="$WORK/documented.txt"
python3 - "$ROOT_DIR" >"$documented" <<'PY'
import re
import sys
from pathlib import Path

root = Path(sys.argv[1])
PAGES = {
    "README.md": "repo",
    "docs/getting-started/index.md": "repo",
    "docs/getting-started/source-checkout.md": "repo",
    "docs/getting-started/editor-workflow.md": "repo",
    "docs/getting-started/release-kit.md": "project",
    "docs/getting-started/store-addon.md": "project",
    "docs/exporting/desktop.md": "repo",
    "docs/exporting/android.md": "repo",
    "docs/exporting/ios.md": "repo",
    "docs/exporting/web.md": "repo",
    "templates/release-kit/README.md": "project",
    "templates/starter/project-README.md": "project",
    "templates/starter/README.md": "repo",
}
SHELLS = ("sh", "bash", "shell", "zsh", "console", "powershell", "pwsh", "ps1")
GRADLEW = re.compile(r"^(?:\./|\.\\)?gradlew(?:\.bat)?\s+(.*)$")
TASK = re.compile(r"^:?[A-Za-z][A-Za-z0-9:_-]*$")
found = 0
for page, default in PAGES.items():
    fence = None
    where = default
    for number, raw in enumerate((root / page).read_text(encoding="utf-8").splitlines(), 1):
        line = raw.strip()
        if line.startswith(("$ ", "> ")):
            line = line[2:].strip()  # a console prompt
        match = GRADLEW.match(line)
        if line.startswith("```"):
            # The info string's first word is the language: ```bash title="..." is a bash block.
            info = line[3:].strip().lstrip("{.").split()
            fence = None if fence is not None else (info[0].lower().rstrip("}") if info else "")
            where = default
        elif fence not in SHELLS:
            pass  # prose, or a non-shell block: no command runs there
        elif line.startswith("cd "):
            where = "repo" if Path(line[3:].strip()).name == "kanama" else "project"
        elif "setup-kanama-project" in line:
            where = "project"
        elif match:
            args = match.group(1).replace("\\", " ").split()
            context = "repo" if "-p" in args or "--project-dir" in args else where
            for previous, task in zip([""] + args, args):
                if TASK.match(task) and previous not in ("-p", "--project-dir"):
                    print(f"{context} {task} {page}:{number}")
                    found += 1
if found == 0:
    sys.exit("no ./gradlew command found in the getting-started pages: the scan is vacuous")
PY
grep -q '^project ' "$documented" || fail "no project-side ./gradlew command found in the docs (scan is vacuous)"
grep -q '^repo ' "$documented" || fail "no checkout-side ./gradlew command found in the docs (scan is vacuous)"

# The Kanama Gradle plugin, published from this checkout, as each layout's addons/kanama/maven carries it.
KANAMA_VERSION="$(sed -n 's/^version = "\(.*\)"$/\1/p' "$ROOT_DIR/build.gradle.kts")"
[[ -n "$KANAMA_VERSION" ]] || fail "no version = \"...\" line in build.gradle.kts"
plugin_log="$WORK/plugin.publish.log"
if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" --no-daemon -q :gradle-plugin:publishAllPublicationsToKanamaPackageRepository \
  >"$plugin_log" 2>&1; then
  fail "publishing the Kanama Gradle plugin failed:" "$plugin_log"
fi
add_kanama_plugin() {
  local maven="$1/addons/kanama/maven/net/multigesture/kanama" artifact
  mkdir -p "$maven"
  for artifact in kanama-gradle-plugin net.multigesture.kanama.gradle.plugin; do
    cp -R "$ROOT_DIR/build/package/maven/net/multigesture/kanama/$artifact" "$maven/"
  done
}

# check_tasks <name> <dir> <context>: the layout configures and has every documented <context> task.
check_tasks() {
  local name="$1" dir="$2" context="$3" log="$WORK/$1.tasks.log"
  if ! (cd "$dir" && ./gradlew --no-daemon -q tasks --all >"$log" 2>&1); then
    fail "the $name Gradle build does not configure:" "$log"
  fi
  local missing=0 task where
  while read -r ctx task where; do
    # `tasks --all` lists `:processor:test` as `processor:test` and `:jvmTest` as `jvmTest`.
    if [[ "$ctx" == "$context" ]] && ! grep -Eq "^${task#:}( - |$)" "$log"; then
      echo "$TAG FAIL -- $where tells the user to run \`./gradlew $task\`, and the $name build has no task $task" >&2
      missing=1
    fi
  done <"$documented"
  [[ "$missing" -eq 0 ]] || exit 1
}

check_wrapper() {
  local name="$1" dir="$2" file
  for file in gradlew gradlew.bat gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.properties; do
    [[ -f "$dir/$file" ]] || fail "the $name has no $file: \`./gradlew buildScripts\` does not exist there"
  done
  [[ -x "$dir/gradlew" ]] || fail "the $name's gradlew is not executable"
}

# 1. The repo build: the checkout-side commands.
check_tasks "Kanama checkout" "$ROOT_DIR" repo

# 2. The source-checkout starter (and, through the shared copySpec, the desktop kit's project files).
starter="$WORK/starter"
starter_log="$WORK/starter.create.log"
if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" --no-daemon -q createStarterProject \
  -PkanamaStarterProjectDir="$starter" >"$starter_log" 2>&1; then
  fail "createStarterProject failed:" "$starter_log"
fi
for file in project.godot main.tscn settings.gradle.kts build.gradle.kts kotlin-src/HelloScript.kt \
  addons/kanama/kanama-project.gradle.kts addons/kanama_tools/plugin.cfg; do
  [[ -f "$starter/$file" ]] || fail "createStarterProject wrote no $file"
done
[[ -f "$starter/addons/kanama/kanama.version" ]] || fail "createStarterProject wrote no addons/kanama/kanama.version"
add_kanama_plugin "$starter"
check_wrapper "source-checkout starter" "$starter"
check_tasks "source-checkout starter" "$starter" project

# 3. A project set up from the store addon: the files packageStoreAddon ships under addons/kanama, then the setup
#    script the docs tell the user to run.
store="$WORK/store"
template="$store/addons/kanama/templates/release-kit"
mkdir -p "$template/kotlin-src" "$template/gradle"
for file in settings.gradle.kts build.gradle.kts README.md; do
  sed "s/@KANAMA_VERSION@/$KANAMA_VERSION/g" "$ROOT_DIR/templates/release-kit/$file" >"$template/$file"
done
cp "$ROOT_DIR/templates/starter/HelloScript.kt" "$template/kotlin-src/HelloScript.kt"
cp "$ROOT_DIR/gradlew" "$ROOT_DIR/gradlew.bat" "$template/"
chmod +x "$template/gradlew"
cp -R "$ROOT_DIR/gradle/wrapper" "$template/gradle/wrapper"
cp "$ROOT_DIR/templates/consumer-gradle/kanama-project.gradle.kts" "$store/addons/kanama/kanama-project.gradle.kts"
sed "s/@KANAMA_VERSION@/$KANAMA_VERSION/g" "$ROOT_DIR/templates/consumer-gradle/kanama.version" \
  >"$store/addons/kanama/kanama.version"
add_kanama_plugin "$store"
cp "$ROOT_DIR/templates/store-addon/setup-kanama-project.sh" "$store/addons/kanama/setup-kanama-project.sh"
printf '%s\n' 'config_version=5' '' '[application]' '' 'config/name="Existing"' >"$store/project.godot"
setup_log="$WORK/store.setup.log"
if ! (cd "$store" && sh addons/kanama/setup-kanama-project.sh >"$setup_log" 2>&1); then
  fail "setup-kanama-project.sh failed:" "$setup_log"
fi
check_wrapper "store-addon project" "$store"
check_tasks "store-addon project" "$store" project

# 4. The version lock, on copies of the starter: each broken state stops the build with its one line.
# expect_lock_failure <case> <dir> <text>: configuring <dir> fails and prints <text>.
expect_lock_failure() {
  local name="$1" dir="$2" text="$3" log="$WORK/lock.$1.log"
  if (cd "$dir" && ./gradlew --no-daemon -q tasks >"$log" 2>&1); then
    fail "version lock: $name configured; it must stop the build" "$log"
  fi
  grep -Fq -- "$text" "$log" || fail "version lock: $name did not fail with the line \"$text\":" "$log"
}
lock="$WORK/lock-missing"
cp -R "$starter" "$lock"
rm "$lock/addons/kanama/kanama.version"
expect_lock_failure missing "$lock" "Kanama addon not found: addons/kanama/kanama.version is missing."
lock="$WORK/lock-unpublished"
cp -R "$starter" "$lock"
printf '9.9.9\n' >"$lock/addons/kanama/kanama.version"
expect_lock_failure unpublished "$lock" "Kanama addon is incomplete: addons/kanama/maven has no Kanama 9.9.9."
# An existing build whose own settings.gradle.kts pins the plugin by hand: the plugin itself refuses an addon of
# another version.
lock="$WORK/lock-pinned"
cp -R "$starter" "$lock"
printf '%s\n' 'pluginManagement { repositories { maven { url = uri("addons/kanama/maven") }; gradlePluginPortal() } }' \
  'rootProject.name = "pinned"' >"$lock/settings.gradle.kts"
sed_inplace "$lock/build.gradle.kts" "s/id(\"net.multigesture.kanama\")/id(\"net.multigesture.kanama\") version \"$KANAMA_VERSION\"/"
grep -Fq "version \"$KANAMA_VERSION\"" "$lock/build.gradle.kts" || fail "version lock: could not pin the plugin in the copy's build.gradle.kts"
printf '9.9.9\n' >"$lock/addons/kanama/kanama.version"
expect_lock_failure pinned "$lock" "The Kanama Gradle plugin is $KANAMA_VERSION but the addon is 9.9.9"

echo "$TAG PASS starter, store-addon project and repo build configure; the version lock stops 3 broken states; $(wc -l <"$documented" | tr -d ' ') documented ./gradlew commands exist where the docs run them"
