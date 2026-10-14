# shellcheck shell=bash
# android_common.sh -- the steps scripts/android_smoke.sh and scripts/android_export_minified.sh share
# (task 119 finding 8: ~50 lines were pasted into both and already drifting). Source it after
# gate_skip.sh and android_apk_id.sh; do not run it. The caller sets ANDROID_TAG (its "[prefix]" for
# messages) first, and DEMO_DIR, ROOT_DIR, GODOT_BIN, APK_PATH, LOG_FILE where a function needs them.
#
#   check_log <pattern> / check_log_absent <pattern>   the logcat verdicts (LOG_FILE)
#   set_gradle_property <file> <key> <value>
#   build_demo_scripts                                 the demo's scripts, against THIS checkout
#   install_desktop_addon                              the export-time editor's addon (task 116)
#   ensure_android_build_template                      sets BUILD_GRADLE (and installs the template)

check_log() {
  local pattern="$1"
  if ! rg -q "$pattern" "$LOG_FILE"; then
    echo "$ANDROID_TAG missing log pattern: $pattern" >&2
    echo "$ANDROID_TAG log tail:" >&2
    tail -n 200 "$LOG_FILE" >&2
    exit 1
  fi
}

check_log_absent() {
  local pattern="$1"
  if rg -q "$pattern" "$LOG_FILE"; then
    echo "$ANDROID_TAG unexpected log pattern: $pattern" >&2
    echo "$ANDROID_TAG log tail:" >&2
    tail -n 200 "$LOG_FILE" >&2
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

# Task 119 item 40: compile the demo's scripts in source-checkout mode, against THIS Kanama checkout's
# API and KSP processor (the same `:project-scripts:jar -PkanamaProjectScriptsDir` path the demos'
# desktop CI lane uses), never through the demo's own Gradle build, which resolves `kanama`/`processor`
# from mavenLocal (whatever main last published). The Android scripts AAR takes its registrars from the
# same checkout's processor (`:project-scripts:kspKotlin`), so a branch's API or processor change is
# what gets built and exported.
build_demo_scripts() {
  if [[ -d "$DEMO_DIR/kotlin-src" ]]; then
    echo "$ANDROID_TAG build demo scripts (against $ROOT_DIR)"
    if [[ -n "${KANAMA_DEMO_JAVA_HOME:-}" ]]; then
      JAVA_HOME="$KANAMA_DEMO_JAVA_HOME" "$ROOT_DIR/gradlew" -p "$ROOT_DIR" :project-scripts:jar \
        "-PkanamaProjectScriptsDir=$DEMO_DIR/kotlin-src"
    else
      "$ROOT_DIR/gradlew" -p "$ROOT_DIR" :project-scripts:jar \
        "-PkanamaProjectScriptsDir=$DEMO_DIR/kotlin-src"
    fi
  fi
}

# Task 116: the export runs in the DESKTOP editor, which needs the desktop Kanama addon (kanama.jar,
# the project's kanama-scripts.jar, the bootstrap dylib) to load the project's .kt scripts. Without it
# the editor prints "No loader found for resource: res://….kt" and, because Godot's export re-packs
# every scene with the properties the script instance reports, ships every scene with NO script
# properties (task 106). On a fresh clone addons/kanama/ holds only the .gdextension file. Install after
# the AAR so installAddonJar preserves the Android entries.
install_desktop_addon() {
  echo "$ANDROID_TAG install desktop addon (export-time editor)"
  local addon_args=("-PkanamaProjectDir=$DEMO_DIR")
  if [[ -d "$DEMO_DIR/kotlin-src" ]]; then
    addon_args+=("-PkanamaProjectScriptsDir=$DEMO_DIR/kotlin-src")
  fi
  "$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAddonJar "${addon_args[@]}"
}

# Installs the Godot Android build template and sets BUILD_GRADLE to the generated build.gradle.
ensure_android_build_template() {
  echo "$ANDROID_TAG install android build template"
  # In 4.7.2 this installs nothing on its own (task 116), so its exit status is not the verdict: the
  # build.gradle check below is, and installs the template by hand when this left it missing. Keep the
  # output and show it when the command failed, instead of discarding it (task 118).
  local template_log="${APK_PATH%.apk}.install-template.log"
  if ! "$GODOT_BIN" --headless \
    --path "$DEMO_DIR" \
    --install-android-build-template \
    --quit >"$template_log" 2>&1; then
    echo "$ANDROID_TAG note: --install-android-build-template exited non-zero (output below; the build.gradle check decides):" >&2
    tail -n 20 "$template_log" >&2
  fi

  BUILD_GRADLE="$DEMO_DIR/android/build/build.gradle"
  if [[ ! -f "$BUILD_GRADLE" ]]; then
    # Task 116: in 4.7.2 `--install-android-build-template --quit` installs nothing on its own (it only
    # takes effect combined with an export). Do what the editor's installer does: write
    # android/.build_version, create android/build/.gdignore, unzip android_source.zip from the export
    # templates of this exact editor version. The build.gradle patch the callers apply needs the
    # template BEFORE the export, so the combined form is not an option here.
    # The templates directory and android/.build_version must equal Godot's VERSION_FULL_CONFIG
    # ("4.7.2.stable", "4.8.stable", "4.7.2.stable.mono"); `godot --version` cannot be cut into that
    # reliably (an X.Y.0 release prints no patch, a mono build adds a segment), so read the pin the
    # repo already owns, as ios_template_preflight.sh does.
    local godot_version templates_root android_source
    godot_version="$(sed -n 's/^kanamaGodotVersion=//p' "$ROOT_DIR/gradle.properties" | tr -d '[:space:]')"
    if [[ -z "$godot_version" ]]; then
      echo "$ANDROID_TAG kanamaGodotVersion missing from $ROOT_DIR/gradle.properties" >&2
      exit 1
    fi
    templates_root="${KANAMA_GODOT_TEMPLATES_DIR:-}"
    if [[ -z "$templates_root" ]]; then
      if [[ -d "$HOME/Library/Application Support/Godot/export_templates" ]]; then
        templates_root="$HOME/Library/Application Support/Godot/export_templates"
      else
        templates_root="${XDG_DATA_HOME:-$HOME/.local/share}/godot/export_templates"
      fi
    fi
    android_source="$templates_root/$godot_version/android_source.zip"
    if [[ ! -f "$android_source" ]]; then
      echo "$ANDROID_TAG android build template not installed and $android_source is missing" >&2
      echo "$ANDROID_TAG install the $godot_version export templates (the official tpz has android_source.zip)" >&2
      exit 1
    fi
    echo "$ANDROID_TAG installing the android build template by hand from $android_source"
    mkdir -p "$DEMO_DIR/android/build"
    : >"$DEMO_DIR/android/build/.gdignore"
    printf '%s\n' "$godot_version" >"$DEMO_DIR/android/.build_version"
    unzip -q -o "$android_source" -d "$DEMO_DIR/android/build"
  fi
  if [[ ! -f "$BUILD_GRADLE" ]]; then
    echo "$ANDROID_TAG generated build.gradle not found: $BUILD_GRADLE" >&2
    echo "$ANDROID_TAG is gradle_build/use_gradle_build=true in the export preset?" >&2
    exit 1
  fi
}
