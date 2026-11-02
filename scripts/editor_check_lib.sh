# shellcheck shell=bash
# editor_check_lib.sh -- the scratch projects and the scratch editor HOME that the editor checks run in. Source it;
# do not run it. An editor check is a GDScript EditorPlugin (scripts/check_editor_*.gd) enabled beside Kanama Tools
# in a scratch Godot project; it drives the plugin the way a user does and quits the editor with its verdict.
# tool_smoke.sh, store_addon_editor_smoke.sh and editor_probe_smoke.sh use it.
#
#   editor_check_plugin PROJECT NAME CHECK_GD
#       install CHECK_GD as the editor plugin addons/NAME (plugin.gd and its plugin.cfg) in PROJECT
#   editor_check_project_godot PROJECT CONFIG_NAME PLUGINS [LINE...]
#       write PROJECT/project.godot: the application name, the editor plugins PLUGINS enables (a space-separated
#       list of addon folder names, each res://addons/<name>/plugin.cfg), then each LINE as it is (a [kanama] section)
#   editor_check_home DIR
#       make DIR the scratch HOME editor_env runs Godot under (EDITOR_HOME); the editor's settings (macOS: HOME,
#       Linux: the XDG folders) then live there, so a check may switch an editor setting such as Build On Save.
#       Gradle keeps the machine's own cache (GRADLE_USER_HOME), as a check may run a real build
#   editor_env COMMAND...
#       run COMMAND with that scratch HOME
#   run_bounded SECONDS TAG COMMAND...
#       COMMAND's exit status, or a failure once SECONDS passed (COMMAND and its children are then stopped and
#       TAG says so): the bound for an editor that never gets as far as its check's own timeout

editor_check_plugin() {
  local project="$1" name="$2" check_gd="$3"
  mkdir -p "$project/addons/$name"
  cp "$check_gd" "$project/addons/$name/plugin.gd"
  printf '%s\n' '[plugin]' '' "name=\"$name\"" 'description=""' 'author=""' 'version=""' 'script="plugin.gd"' \
    >"$project/addons/$name/plugin.cfg"
}

editor_check_project_godot() {
  local project="$1" config_name="$2" plugins="$3" plugin enabled=""
  shift 3
  for plugin in $plugins; do
    enabled="${enabled:+$enabled, }\"res://addons/$plugin/plugin.cfg\""
  done
  printf '%s\n' 'config_version=5' '' '[application]' '' "config/name=\"$config_name\"" '' '[editor_plugins]' '' \
    "enabled=PackedStringArray($enabled)" >"$project/project.godot"
  if [[ $# -gt 0 ]]; then
    printf '\n' >>"$project/project.godot"
    printf '%s\n' "$@" >>"$project/project.godot"
  fi
}

EDITOR_HOME=""
editor_check_home() {
  EDITOR_HOME="$1"
  mkdir -p "$EDITOR_HOME"
  EDITOR_GRADLE_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
}

editor_env() {
  env HOME="$EDITOR_HOME" XDG_CONFIG_HOME="$EDITOR_HOME/.config" XDG_DATA_HOME="$EDITOR_HOME/.local/share" \
    XDG_CACHE_HOME="$EDITOR_HOME/.cache" GRADLE_USER_HOME="$EDITOR_GRADLE_HOME" "$@"
}

run_bounded() {
  local limit="$1" tag="$2" pid watchdog rc=0
  shift 2
  "$@" &
  pid=$!
  (
    # justified: a sleep stopped because the command finished is no timeout.
    sleep "$limit" || exit 0
    echo "$tag timed out after ${limit}s: $*" >&2
    # justified: the command may have exited between the sleep and the kill.
    pkill -TERM -P "$pid" 2>/dev/null || true
    # justified: as above.
    kill -TERM "$pid" 2>/dev/null || true
  ) &
  watchdog=$!
  wait "$pid" || rc=$?
  # justified: the watchdog and its sleep only have to be gone; it may already have fired.
  pkill -P "$watchdog" 2>/dev/null || true
  # justified: as above.
  kill "$watchdog" 2>/dev/null || true
  # justified: reaping the watchdog; its status is not the verdict.
  wait "$watchdog" 2>/dev/null || true
  return "$rc"
}
