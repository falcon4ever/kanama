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
# The templates are used as shipped (only @KANAMA_VERSION@ is substituted, as the packaging tasks do).
#
# Needs network the first time (the Kotlin and KSP plugins come from the plugin portal).
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
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
check_wrapper "source-checkout starter" "$starter"
check_tasks "source-checkout starter" "$starter" project

# 3. A project set up from the store addon: the files packageStoreAddon ships under addons/kanama, then the setup
#    script the docs tell the user to run.
store="$WORK/store"
template="$store/addons/kanama/templates/release-kit"
mkdir -p "$template/kotlin-src" "$template/gradle"
for file in settings.gradle.kts build.gradle.kts README.md; do
  sed 's/@KANAMA_VERSION@/0.0.0/g' "$ROOT_DIR/templates/release-kit/$file" >"$template/$file"
done
cp "$ROOT_DIR/templates/starter/HelloScript.kt" "$template/kotlin-src/HelloScript.kt"
cp "$ROOT_DIR/gradlew" "$ROOT_DIR/gradlew.bat" "$template/"
chmod +x "$template/gradlew"
cp -R "$ROOT_DIR/gradle/wrapper" "$template/gradle/wrapper"
sed 's/@KANAMA_VERSION@/0.0.0/g' "$ROOT_DIR/templates/consumer-gradle/kanama-project.gradle.kts" \
  >"$store/addons/kanama/kanama-project.gradle.kts"
cp "$ROOT_DIR/templates/store-addon/setup-kanama-project.sh" "$store/addons/kanama/setup-kanama-project.sh"
printf '%s\n' 'config_version=5' '' '[application]' '' 'config/name="Existing"' >"$store/project.godot"
setup_log="$WORK/store.setup.log"
if ! (cd "$store" && sh addons/kanama/setup-kanama-project.sh >"$setup_log" 2>&1); then
  fail "setup-kanama-project.sh failed:" "$setup_log"
fi
check_wrapper "store-addon project" "$store"
check_tasks "store-addon project" "$store" project

echo "$TAG PASS starter, store-addon project and repo build configure; $(wc -l <"$documented" | tr -d ' ') documented ./gradlew commands exist where the docs run them"
