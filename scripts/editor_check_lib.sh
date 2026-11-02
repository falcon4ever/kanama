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
#       TAG says so): the bound for an editor that never gets as far as its check's own timeout. COMMAND runs in
#       the background (run it from this shell, not a subshell, so the traps below can reach it)
#   run_bounded_stop
#       stop the command run_bounded is running, if any, with its children (an editor's Gradle client): TERM, then
#       KILL after RUN_BOUNDED_GRACE seconds (default 10). A script that uses run_bounded calls it from its EXIT
#       trap and calls run_bounded_signal_traps: a background job ignores the terminal's Ctrl-C, and a script that
#       is stopped does not stop its children, so without them an interrupted run left a headless editor running
#   run_bounded_signal_traps
#       turn INT and TERM into an exit (130, 143), so the script's EXIT trap runs

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

RUN_BOUNDED_PID=""
RUN_BOUNDED_WATCHDOG=""

# _run_bounded_end PID: TERM to PID and its children, then KILL to those still alive after RUN_BOUNDED_GRACE
# seconds. The children are listed first: once PID is gone they belong to init and pgrep -P no longer finds them.
_run_bounded_end() {
  local pid="$1" waited=0 targets
  # justified: no children (or no pgrep, on Git Bash) leaves the list empty; PID itself is still stopped.
  targets="$(pgrep -P "$pid" 2>/dev/null || true) $pid"
  # justified: a target may have exited since it was listed.
  kill -TERM $targets 2>/dev/null || true
  while [[ "$waited" -lt "${RUN_BOUNDED_GRACE:-10}" ]] && _run_bounded_any_alive $targets; do
    sleep 1
    waited=$((waited + 1))
  done
  # justified: as above; KILL only reaches what TERM did not stop.
  kill -KILL $targets 2>/dev/null || true
}

_run_bounded_any_alive() {
  local target
  for target in "$@"; do
    if kill -0 "$target" 2>/dev/null; then
      return 0
    fi
  done
  return 1
}

run_bounded() {
  local limit="$1" tag="$2" rc=0
  shift 2
  "$@" &
  RUN_BOUNDED_PID=$!
  (
    # justified: a sleep stopped because the command finished is no timeout.
    sleep "$limit" || exit 0
    echo "$tag timed out after ${limit}s: $*" >&2
    _run_bounded_end "$RUN_BOUNDED_PID"
  ) &
  RUN_BOUNDED_WATCHDOG=$!
  wait "$RUN_BOUNDED_PID" || rc=$?
  RUN_BOUNDED_PID=""
  _run_bounded_stop_watchdog
  return "$rc"
}

_run_bounded_stop_watchdog() {
  local watchdog="$RUN_BOUNDED_WATCHDOG"
  if [[ -z "$watchdog" ]]; then
    return 0
  fi
  RUN_BOUNDED_WATCHDOG=""
  # justified: the watchdog and its sleep only have to be gone; it may already have fired.
  pkill -P "$watchdog" 2>/dev/null || true
  # justified: as above.
  kill "$watchdog" 2>/dev/null || true
  # justified: reaping the watchdog; its status is not the verdict.
  wait "$watchdog" 2>/dev/null || true
}

run_bounded_stop() {
  local pid="$RUN_BOUNDED_PID"
  if [[ -n "$pid" ]]; then
    RUN_BOUNDED_PID=""
    _run_bounded_end "$pid"
    # justified: reaping the stopped command; the script is already on its way out with its own status.
    wait "$pid" 2>/dev/null || true
  fi
  _run_bounded_stop_watchdog
}

run_bounded_signal_traps() {
  trap 'exit 130' INT
  trap 'exit 143' TERM
}
