#!/usr/bin/env bash
# check_gradle_templates_configure.sh -- the Gradle files users receive still configure under the repo's Gradle.
#
# templates/release-kit/{settings,build}.gradle.kts and templates/consumer-gradle/kanama-project.gradle.kts
# are copied into every desktop kit and store-add-on project (setup-kanama-project.sh/.ps1), where
# Build Scripts runs Gradle on them. Gradle rejects a settings file whose `plugins {}` block precedes
# `pluginManagement {}`: that mistake once shipped in the release-kit template and only package.yml
# would have noticed, after the merge. This gate assembles the same layout in a scratch directory
# with the repo's own Gradle wrapper and runs `help` (configuration only: no dependency resolution, no
# compile), so a template that does not configure fails every PR. The templates are used as shipped
# (only @KANAMA_VERSION@ is substituted, as the packaging task does).
#
# Needs network the first time (the Kotlin and KSP plugins come from the plugin portal).
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TAG="[check_gradle_templates_configure]"

TMP_ROOT="${TMPDIR:-/tmp}"
WORK="$(mktemp -d "${TMP_ROOT%/}/kanama_template_configure.XXXXXX")"
trap 'rm -rf "$WORK"' EXIT

kit="$WORK/kit"
mkdir -p "$kit/addons/kanama" "$kit/kotlin-src" "$kit/gradle"
cp -R "$ROOT_DIR/gradle/wrapper" "$kit/gradle/wrapper"
cp "$ROOT_DIR/gradlew" "$kit/gradlew"
chmod +x "$kit/gradlew"
for file in settings.gradle.kts build.gradle.kts; do
  sed 's/@KANAMA_VERSION@/0.0.0/g' "$ROOT_DIR/templates/release-kit/$file" >"$kit/$file"
done
sed 's/@KANAMA_VERSION@/0.0.0/g' "$ROOT_DIR/templates/consumer-gradle/kanama-project.gradle.kts" \
  >"$kit/addons/kanama/kanama-project.gradle.kts"
cp "$ROOT_DIR/templates/starter/HelloScript.kt" "$kit/kotlin-src/HelloScript.kt"

log="$WORK/help.log"
if ! (cd "$kit" && ./gradlew --no-daemon -q help >"$log" 2>&1); then
  echo "$TAG FAIL -- the release-kit Gradle template does not configure:" >&2
  sed 's/^/    | /' "$log" >&2
  exit 1
fi
echo "$TAG PASS release-kit settings/build + consumer-gradle script configure (gradlew help)"
