@tool
extends EditorPlugin

const MENU_BUILD_SYNC := "Kanama Tools: Build Scripts"
const MENU_BUILD_JAR := "Kanama Tools: Build Runtime Jar"
const MENU_OPEN_KOTLIN_SOURCES := "Kanama Tools: Open Kotlin Sources"
const SETTING_REPO_DIR := "kanama/tools/repo_dir"
const SETTING_KOTLIN_SOURCES_DIR := "kanama/tools/kotlin_sources_dir"
const SETTING_AUTO_BUILD_ON_SAVE := "kanama/tools/auto_build_on_save"
const SETTING_AUTO_BUILD_DEBOUNCE_MS := "kanama/tools/auto_build_debounce_ms"
const SETTING_RELOAD_SCENE_AFTER_SYNC := "kanama/tools/reload_scene_after_sync"
const SETTING_DEVELOPER_MODE := "kanama/tools/developer_mode"
const SETTING_JAVA_PREFLIGHT_ENABLED := "kanama/tools/java_preflight_enabled"
const SETTING_BUILD_JDK_PATH := "kanama/build/jdk_path"
const MIN_JDK_MAJOR := 25
const JDK_HINT_FILE := "kanama_jdk_home"
const META_HINT_AT_START := "kanama_tools_runtime_hint_at_start"
const MODE_BUILD := "build"
const MODE_RUNTIME := "runtime"
const SETTING_JDWP_ENABLED := "kanama/debug/jdwp_enabled"
const SETTING_JDWP_PORT := "kanama/debug/jdwp_port"
# Extra JVM options the native bootstrap passes to the embedded JVM (task 131 item 18). Read from
# project.godot by bootstrap.c before the JVM starts; restart the game after changing it.
const SETTING_JVM_OPTIONS := "kanama/jvm/options"
# Read by the Kanama runtime at startup: log each RefCounted the GC released because its owned
# Kotlin wrapper was never closed, once per creation site (task 132).
const SETTING_LOG_GC_RELEASES := "kanama/debug/log_gc_releases"
# A Kanama test hook, off for users: run the typed-signal self-test on the first frame of a debug
# game run (task 138 item 23). The Kanama smokes switch it on.
const SETTING_SIGNAL_SELF_TEST := "kanama/debug/signal_self_test"
const DEFAULT_JDWP_PORT := 5005
const SYNC_BUTTON_IDLE_TEXT := "Build Scripts"
const SYNC_BUTTON_BUSY_TEXT := "Building..."
const JAR_BUTTON_IDLE_TEXT := "Build Runtime"
const JAR_BUTTON_BUSY_TEXT := "Building..."
const KT_SCAN_INTERVAL_SEC := 0.6
const KotlinSyntaxHighlighter := preload("res://addons/kanama_tools/kotlin_syntax_highlighter.gd")

var _toolbar_container: HBoxContainer
var _sync_button: Button
var _sources_button: Button
var _jar_button: Button
var _kotlin_syntax_highlighter: EditorSyntaxHighlighter
var _scan_accum_sec := 0.0
var _known_kt_mtimes: Dictionary = {}
var _pending_auto_sync := false
var _last_change_msec := 0
var _is_build_running := false
var _jar_menu_added := false
var _last_developer_mode_enabled := false
var _java_preflight_dialog_shown := false
var _restart_needed_message := ""


func _enter_tree() -> void:
    _ensure_project_settings()
    _ensure_editor_settings()
    _sync_runtime_jdk_hint()
    get_editor_interface().get_editor_settings().settings_changed.connect(_on_editor_settings_changed)
    call_deferred("_run_java_preflight")
    add_tool_menu_item(MENU_BUILD_SYNC, _on_build_sync_pressed)
    add_tool_menu_item(MENU_OPEN_KOTLIN_SOURCES, _on_open_kotlin_sources_pressed)
    _last_developer_mode_enabled = _is_developer_mode_enabled()
    if _last_developer_mode_enabled:
        add_tool_menu_item(MENU_BUILD_JAR, _on_build_jar_pressed)
        _jar_menu_added = true
    call_deferred("_register_kotlin_syntax_highlighter")
    _install_toolbar_buttons()
    _known_kt_mtimes = _collect_kt_mtimes()
    set_process(true)


func _exit_tree() -> void:
    set_process(false)
    var editor_settings := get_editor_interface().get_editor_settings()
    if editor_settings != null and editor_settings.settings_changed.is_connected(_on_editor_settings_changed):
        editor_settings.settings_changed.disconnect(_on_editor_settings_changed)
    remove_tool_menu_item(MENU_BUILD_SYNC)
    remove_tool_menu_item(MENU_OPEN_KOTLIN_SOURCES)
    if _jar_menu_added:
        remove_tool_menu_item(MENU_BUILD_JAR)
        _jar_menu_added = false
    _unregister_kotlin_syntax_highlighter()
    _remove_toolbar_buttons()


func _on_build_sync_pressed() -> void:
    _run_script_build()


func _on_build_jar_pressed() -> void:
    _run_gradle_task("jar")


func _on_open_kotlin_sources_pressed() -> void:
    _open_kotlin_sources()


func _process(delta: float) -> void:
    _refresh_developer_mode_controls()

    _scan_accum_sec += delta
    if _scan_accum_sec < KT_SCAN_INTERVAL_SEC:
        return
    _scan_accum_sec = 0.0

    if not _is_auto_build_on_save_enabled():
        return
    if _is_build_running:
        return

    if _detect_kt_changes():
        _pending_auto_sync = true
        _last_change_msec = Time.get_ticks_msec()

    if not _pending_auto_sync:
        return

    var debounce_ms := _auto_build_debounce_ms()
    var elapsed := Time.get_ticks_msec() - _last_change_msec
    if elapsed >= debounce_ms:
        _pending_auto_sync = false
        print("[kanama:tools] Kotlin save detected; running Build Scripts")
        _run_script_build()


func _install_toolbar_buttons() -> void:
    _toolbar_container = HBoxContainer.new()
    _toolbar_container.name = "KanamaToolsToolbar"

    _sync_button = Button.new()
    _sync_button.text = SYNC_BUTTON_IDLE_TEXT
    _sync_button.tooltip_text = "Build and deploy Kotlin scripts into this Godot project"
    _sync_button.pressed.connect(_on_build_sync_pressed)
    _toolbar_container.add_child(_sync_button)

    _sources_button = Button.new()
    _sources_button.text = "Open Kotlin"
    _sources_button.tooltip_text = "Open the configured Kotlin source folder"
    _sources_button.pressed.connect(_on_open_kotlin_sources_pressed)
    _toolbar_container.add_child(_sources_button)

    if _is_developer_mode_enabled():
        _jar_button = Button.new()
        _jar_button.text = JAR_BUTTON_IDLE_TEXT
        _jar_button.tooltip_text = "Build the Kanama runtime jar (Kanama developers only)"
        _jar_button.pressed.connect(_on_build_jar_pressed)
        _toolbar_container.add_child(_jar_button)

    add_control_to_container(EditorPlugin.CONTAINER_TOOLBAR, _toolbar_container)


func _remove_toolbar_buttons() -> void:
    if _toolbar_container == null:
        return
    remove_control_from_container(EditorPlugin.CONTAINER_TOOLBAR, _toolbar_container)
    _toolbar_container.queue_free()
    _toolbar_container = null
    _sync_button = null
    _sources_button = null
    _jar_button = null


func _refresh_developer_mode_controls() -> void:
    var enabled := _is_developer_mode_enabled()
    if enabled == _last_developer_mode_enabled:
        return
    _last_developer_mode_enabled = enabled

    if enabled and not _jar_menu_added:
        add_tool_menu_item(MENU_BUILD_JAR, _on_build_jar_pressed)
        _jar_menu_added = true
    elif not enabled and _jar_menu_added:
        remove_tool_menu_item(MENU_BUILD_JAR)
        _jar_menu_added = false

    if _toolbar_container == null:
        return
    if enabled and _jar_button == null:
        _jar_button = Button.new()
        _jar_button.text = JAR_BUTTON_IDLE_TEXT
        _jar_button.tooltip_text = "Build the Kanama runtime jar (Kanama developers only)"
        _jar_button.pressed.connect(_on_build_jar_pressed)
        _toolbar_container.add_child(_jar_button)
    elif not enabled and _jar_button != null:
        _toolbar_container.remove_child(_jar_button)
        _jar_button.queue_free()
        _jar_button = null


func _register_kotlin_syntax_highlighter() -> void:
    if _kotlin_syntax_highlighter != null:
        return
    var script_editor := get_editor_interface().get_script_editor()
    if script_editor == null:
        call_deferred("_register_kotlin_syntax_highlighter")
        return
    _kotlin_syntax_highlighter = KotlinSyntaxHighlighter.new()
    script_editor.register_syntax_highlighter(_kotlin_syntax_highlighter)
    for script_editor_base in script_editor.get_open_script_editors():
        script_editor_base.add_syntax_highlighter(_kotlin_syntax_highlighter)


func _unregister_kotlin_syntax_highlighter() -> void:
    if _kotlin_syntax_highlighter == null:
        return
    var script_editor := get_editor_interface().get_script_editor()
    if script_editor != null:
        script_editor.unregister_syntax_highlighter(_kotlin_syntax_highlighter)
    _kotlin_syntax_highlighter = null


## 'kanama/build/jdk_path' is an EditorSettings entry (Editor > Editor Settings > Kanama > Build):
## a JDK path belongs to the machine, not to the project that is shared through version control.
func _ensure_editor_settings() -> void:
    var settings := get_editor_interface().get_editor_settings()
    if not settings.has_setting(SETTING_BUILD_JDK_PATH):
        settings.set_setting(SETTING_BUILD_JDK_PATH, "")
    settings.add_property_info({
        "name": SETTING_BUILD_JDK_PATH,
        "type": TYPE_STRING,
        "hint": PROPERTY_HINT_GLOBAL_DIR,
    })


## Hand the explicit setting to bootstrap.c (see write_runtime_jdk_hint). Only a valid explicit
## setting is written; an empty or invalid one removes the file.
func _on_editor_settings_changed() -> void:
    _sync_runtime_jdk_hint(true)


func _sync_runtime_jdk_hint(announce: bool = false) -> void:
    var home := ""
    var setting := read_jdk_path_setting()
    if not setting.is_empty():
        var resolution := resolve_runtime_jdk_from_environment()
        if bool(resolution.get("ok", false)) and String(resolution.get("source", "")).contains(SETTING_BUILD_JDK_PATH):
            home = String(resolution["home"])
    var project_dir := ProjectSettings.globalize_path("res://")
    # The runtime read the hint when the editor started, which is before this plugin loads; what the
    # file held then is what the running runtime used. Remember it for the whole editor session.
    if not Engine.has_meta(META_HINT_AT_START):
        Engine.set_meta(META_HINT_AT_START, read_runtime_jdk_hint(project_dir))
    write_runtime_jdk_hint(project_dir, home)
    var restart_before := _restart_needed_message
    _restart_needed_message = runtime_restart_message(String(Engine.get_meta(META_HINT_AT_START)), home)
    if announce and _restart_needed_message != restart_before and not _restart_needed_message.is_empty():
        _run_java_preflight(true)


## The hint file's current content (empty when absent).
static func read_runtime_jdk_hint(project_dir: String) -> String:
    var hint_path := project_dir.path_join(".godot").path_join(JDK_HINT_FILE)
    if not FileAccess.file_exists(hint_path):
        return ""
    return FileAccess.get_file_as_string(hint_path).strip_edges()


## Non-empty when the JDK the runtime would load now (`wanted_home`: the explicit setting, "" when none)
## differs from the one the running runtime loaded (`started_hint`: the hint it saw at editor start).
## The runtime reads the hint once, in kanama_entry, before this plugin can write it, so on the first
## open of a project (a fresh clone, a deleted .godot) the preflight alone would say OK.
static func runtime_restart_message(started_hint: String, wanted_home: String) -> String:
    if started_hint == wanted_home:
        return ""
    if wanted_home.is_empty():
        return "[kanama:tools] The running Kanama runtime loaded the JDK %s from an earlier '%s'; that setting is empty now. Restart the editor to go back to JAVA_HOME / the install locations." % [started_hint, SETTING_BUILD_JDK_PATH]
    return "[kanama:tools] Restart the editor to use JDK %s: '%s' is set to it, but the Kanama runtime in this editor started before the setting was applied to this project and loaded another JDK (or none)." % [wanted_home, SETTING_BUILD_JDK_PATH]


func _ensure_project_settings() -> void:
    if not ProjectSettings.has_setting(SETTING_REPO_DIR):
        ProjectSettings.set_setting(SETTING_REPO_DIR, "")
    if not ProjectSettings.has_setting(SETTING_KOTLIN_SOURCES_DIR):
        ProjectSettings.set_setting(SETTING_KOTLIN_SOURCES_DIR, "")
    if not ProjectSettings.has_setting(SETTING_AUTO_BUILD_ON_SAVE):
        ProjectSettings.set_setting(SETTING_AUTO_BUILD_ON_SAVE, false)
    if not ProjectSettings.has_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS):
        ProjectSettings.set_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS, 800)
    if not ProjectSettings.has_setting(SETTING_RELOAD_SCENE_AFTER_SYNC):
        ProjectSettings.set_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)
    if not ProjectSettings.has_setting(SETTING_DEVELOPER_MODE):
        ProjectSettings.set_setting(SETTING_DEVELOPER_MODE, false)
    if not ProjectSettings.has_setting(SETTING_JAVA_PREFLIGHT_ENABLED):
        ProjectSettings.set_setting(SETTING_JAVA_PREFLIGHT_ENABLED, true)
    if not ProjectSettings.has_setting(SETTING_JDWP_ENABLED):
        ProjectSettings.set_setting(SETTING_JDWP_ENABLED, false)
    if not ProjectSettings.has_setting(SETTING_JDWP_PORT):
        ProjectSettings.set_setting(SETTING_JDWP_PORT, DEFAULT_JDWP_PORT)
    if not ProjectSettings.has_setting(SETTING_JVM_OPTIONS):
        ProjectSettings.set_setting(SETTING_JVM_OPTIONS, "")
    if not ProjectSettings.has_setting(SETTING_LOG_GC_RELEASES):
        ProjectSettings.set_setting(SETTING_LOG_GC_RELEASES, false)
    if not ProjectSettings.has_setting(SETTING_SIGNAL_SELF_TEST):
        ProjectSettings.set_setting(SETTING_SIGNAL_SELF_TEST, false)
    ProjectSettings.set_initial_value(SETTING_SIGNAL_SELF_TEST, false)
    ProjectSettings.set_initial_value(SETTING_LOG_GC_RELEASES, false)
    ProjectSettings.set_initial_value(SETTING_JDWP_ENABLED, false)
    ProjectSettings.set_initial_value(SETTING_JDWP_PORT, DEFAULT_JDWP_PORT)
    ProjectSettings.set_initial_value(SETTING_JVM_OPTIONS, "")

    ProjectSettings.add_property_info({
        "name": SETTING_REPO_DIR,
        "type": TYPE_STRING,
        "hint": PROPERTY_HINT_GLOBAL_DIR,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_KOTLIN_SOURCES_DIR,
        "type": TYPE_STRING,
        "hint": PROPERTY_HINT_GLOBAL_DIR,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_AUTO_BUILD_ON_SAVE,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_AUTO_BUILD_DEBOUNCE_MS,
        "type": TYPE_INT,
        "hint": PROPERTY_HINT_RANGE,
        "hint_string": "100,5000,100",
    })
    ProjectSettings.add_property_info({
        "name": SETTING_RELOAD_SCENE_AFTER_SYNC,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_DEVELOPER_MODE,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_JAVA_PREFLIGHT_ENABLED,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_JDWP_ENABLED,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_LOG_GC_RELEASES,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_SIGNAL_SELF_TEST,
        "type": TYPE_BOOL,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_JVM_OPTIONS,
        "type": TYPE_STRING,
    })
    ProjectSettings.add_property_info({
        "name": SETTING_JDWP_PORT,
        "type": TYPE_INT,
        "hint": PROPERTY_HINT_RANGE,
        "hint_string": "0,65535,1",
    })


func _is_auto_build_on_save_enabled() -> bool:
    return bool(ProjectSettings.get_setting(SETTING_AUTO_BUILD_ON_SAVE, false))


func _is_developer_mode_enabled() -> bool:
    return bool(ProjectSettings.get_setting(SETTING_DEVELOPER_MODE, false))


func _auto_build_debounce_ms() -> int:
    return int(ProjectSettings.get_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS, 800))


func _detect_kt_changes() -> bool:
    var latest := _collect_kt_mtimes()
    var changed := false

    for path in latest.keys():
        if not _known_kt_mtimes.has(path):
            changed = true
            continue
        if int(_known_kt_mtimes[path]) != int(latest[path]):
            changed = true

    for old_path in _known_kt_mtimes.keys():
        if not latest.has(old_path):
            changed = true

    _known_kt_mtimes = latest
    return changed


func _collect_kt_mtimes() -> Dictionary:
    var result: Dictionary = {}
    _collect_kt_mtimes_recursive("res://", result)
    return result


func _collect_kt_mtimes_recursive(dir_path: String, sink: Dictionary) -> void:
    var dir := DirAccess.open(dir_path)
    if dir == null:
        return
    dir.list_dir_begin()
    while true:
        var name := dir.get_next()
        if name.is_empty():
            break
        if name == "." or name == "..":
            continue
        var child := dir_path.path_join(name)
        if dir.current_is_dir():
            _collect_kt_mtimes_recursive(child, sink)
        elif name.to_lower().ends_with(".kt"):
            sink[child] = FileAccess.get_modified_time(child)
    dir.list_dir_end()


func _run_script_build() -> void:
    var project_gradle_dir := _project_dir_with_gradle_wrapper()
    if not project_gradle_dir.is_empty():
        _run_gradle_task("buildScripts", [], project_gradle_dir)
        return
    _run_gradle_task("installAddonJar", _current_project_install_args())


func _run_gradle_task(task_name: String, extra_args: Array = [], repo_dir_override: String = "") -> void:
    if _is_build_running:
        push_warning("[kanama:tools] Build already running; skipping '%s'" % task_name)
        return

    var repo_dir := repo_dir_override if not repo_dir_override.is_empty() else _resolve_repo_dir()
    if repo_dir.is_empty():
        push_error("[kanama:tools] Could not find Kanama repo. Set '%s' in Project Settings." % SETTING_REPO_DIR)
        return

    var gradlew := repo_dir.path_join("gradlew")
    if OS.get_name() == "Windows":
        gradlew += ".bat"
    if not FileAccess.file_exists(gradlew):
        push_error("[kanama:tools] gradlew not found at %s" % gradlew)
        return

    # A desktop launcher does not hand Godot the shell's JAVA_HOME, so gradlew (and the CMake run
    # under it) used to fall back to whatever JDK the system defaulted to (kanama#277). Pick the
    # JDK here and refuse to start a build that cannot work.
    var jdk := resolve_build_jdk_from_environment()
    if not bool(jdk.get("ok", false)):
        push_error(String(jdk.get("message", "")))
        return
    print("[kanama:tools] Build JDK: %s (JDK %s, from %s)" % [jdk["home"], jdk["version"], jdk["source"]])

    var output: Array = []
    var args := ["-p", repo_dir, task_name]
    args.append_array(extra_args)
    print("[kanama:tools] Running: %s %s" % [gradlew, " ".join(args)])
    _set_build_buttons_busy(task_name, true)
    var code := execute_with_java_home(String(jdk["home"]), gradlew, args, output)
    _set_build_buttons_busy(task_name, false)
    if code == 0:
        print("[kanama:tools] %s succeeded." % task_name)
        if _is_script_build_task(task_name) and bool(ProjectSettings.get_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)):
            _queue_reload_edited_scene()
    else:
        push_error("[kanama:tools] %s failed (exit=%d)\n%s" % [task_name, code, "\n".join(output)])


## JDK lookup (kanama#277). Two questions share one lookup order and one ranking:
##   MODE_BUILD   which JDK Build Scripts runs Gradle with: a full JDK (bin/java and include/jni.h);
##   MODE_RUNTIME which JDK bootstrap.c will load libjvm from (the startup preflight): release says
##                25+ and lib/server/libjvm.* exists. bootstrap.c implements the same lookup in C
##                (find_jvm_lib) and scripts/check_jdk_locations_parity.py holds the two location
##                tables equal; scripts/check_jdk_lookup_parity.sh compares their picks.
## Order: the 'kanama/build/jdk_path' EditorSettings value (empty = auto; bootstrap.c sees it as
## <project>/.godot/kanama_jdk_home, written by this plugin), then JAVA_HOME from the editor's
## environment, then the best JDK in the install locations. Only a JDK >= MIN_JDK_MAJOR counts. A wrong
## explicit setting is an error, not a silent fall-through; a JAVA_HOME at an older JDK is skipped
## (it is usually set for some other project). Among install locations: GA before EA, exactly
## MIN_JDK_MAJOR before newer, then the newer version, then the smaller path.
static func resolve_build_jdk_from_environment(setting_override: Variant = null) -> Dictionary:
    return _resolve_from_environment(MODE_BUILD, setting_override)


static func resolve_runtime_jdk_from_environment(setting_override: Variant = null) -> Dictionary:
    return _resolve_from_environment(MODE_RUNTIME, setting_override)


static func _resolve_from_environment(mode: String, setting_override: Variant) -> Dictionary:
    var home_dir := _user_home_dir()
    var setting := read_jdk_path_setting() if setting_override == null else String(setting_override).strip_edges()
    if setting == "~" or setting.begins_with("~/"):
        setting = home_dir + setting.substr(1)
    return resolve_jdk(
        mode,
        setting,
        OS.get_environment("JAVA_HOME"),
        jdk_install_candidates(OS.get_name(), home_dir, OS.get_environment("ProgramFiles"), OS.get_environment("KANAMA_TEST_JDK_SEARCH_DIRS")),
        OS.get_name(),
    )


## The 'kanama/build/jdk_path' value. It is an EditorSettings entry (per machine), not a project
## setting, so an absolute path on one machine is not committed with the project.
static func read_jdk_path_setting() -> String:
    if not Engine.is_editor_hint():
        return ""
    var settings := EditorInterface.get_editor_settings()
    if settings == null or not settings.has_setting(SETTING_BUILD_JDK_PATH):
        return ""
    return String(settings.get_setting(SETTING_BUILD_JDK_PATH)).strip_edges()


static func resolve_build_jdk(setting_value: String, java_home_env: String, install_candidates: Array) -> Dictionary:
    return resolve_jdk(MODE_BUILD, setting_value, java_home_env, install_candidates, OS.get_name())


static func resolve_runtime_jdk(setting_value: String, java_home_env: String, install_candidates: Array) -> Dictionary:
    return resolve_jdk(MODE_RUNTIME, setting_value, java_home_env, install_candidates, OS.get_name())


## Pure resolution (no editor state), so the order and the failure texts are testable.
## Returns {"ok", "home", "version", "major", "source", "notes", "message"}.
static func resolve_jdk(mode: String, setting_value: String, java_home_env: String, install_candidates: Array, os_name: String) -> Dictionary:
    var notes: Array = []
    var purpose := "for Build Scripts" if mode == MODE_BUILD else "to run Kanama scripts in the editor and your game"
    var setting := setting_value.strip_edges()
    if not setting.is_empty():
        if setting.begins_with("res://") or setting.begins_with("user://") or not setting.is_absolute_path():
            return _jdk_failure("'%s' is '%s'. It must be an absolute path to a JDK %d+ home directory (the folder that contains bin/ and lib/); relative and res:// paths are not accepted. Clear it to auto-detect." % [SETTING_BUILD_JDK_PATH, setting, MIN_JDK_MAJOR], notes)
        var setting_home := _strip_trailing_separator(setting)
        # A macOS bundle (…/temurin-25.jdk) keeps the JDK home under Contents/Home.
        if DirAccess.dir_exists_absolute(setting_home.path_join("Contents/Home")):
            setting_home = setting_home.path_join("Contents/Home")
        var info := inspect_jdk(setting_home, mode, os_name)
        if not bool(info.get("ok", false)):
            return _jdk_failure("'%s' is set to '%s', which is not a usable JDK (%s). Set it to the home directory of a JDK %d or newer (the folder that contains bin/ and lib/), or clear it to auto-detect." % [SETTING_BUILD_JDK_PATH, setting, String(info.get("reason", "")), MIN_JDK_MAJOR], notes)
        if int(info["major"]) < MIN_JDK_MAJOR:
            return _jdk_failure("'%s' is set to '%s', which is JDK %s. Kanama needs JDK %d or newer: point the setting at one, or clear it to auto-detect." % [SETTING_BUILD_JDK_PATH, setting, String(info["version"]), MIN_JDK_MAJOR], notes)
        return _jdk_success(info, "the %s editor setting" % SETTING_BUILD_JDK_PATH, notes)

    var env_home := java_home_env.strip_edges()
    if not env_home.is_empty():
        var env_info := inspect_jdk(env_home, mode, os_name)
        if bool(env_info.get("ok", false)) and int(env_info["major"]) >= MIN_JDK_MAJOR:
            return _jdk_success(env_info, "JAVA_HOME", notes)
        if bool(env_info.get("ok", false)):
            notes.append("JAVA_HOME=%s is JDK %s (need %d+)" % [env_home, String(env_info["version"]), MIN_JDK_MAJOR])
        else:
            notes.append("JAVA_HOME=%s is not usable (%s)" % [env_home, String(env_info.get("reason", ""))])

    var best: Dictionary = {}
    for candidate in install_candidates:
        var info := inspect_jdk(String(candidate), mode, os_name)
        if not bool(info.get("ok", false)):
            if String(info.get("reason", "")) != "not a directory":
                notes.append("%s is not usable (%s)" % [String(candidate), String(info.get("reason", ""))])
            continue
        if int(info["major"]) < MIN_JDK_MAJOR:
            notes.append("%s is JDK %s (need %d+)" % [String(candidate), String(info["version"]), MIN_JDK_MAJOR])
            continue
        if best.is_empty() or _jdk_better(info, best):
            best = info
    if not best.is_empty():
        return _jdk_success(best, "the best JDK %d+ in the install locations (exactly %d preferred, GA over EA)" % [MIN_JDK_MAJOR, MIN_JDK_MAJOR], notes)

    var lines: Array = [
        "Kanama could not find a JDK %d or newer %s." % [MIN_JDK_MAJOR, purpose],
        "Godot started from a desktop launcher does not inherit your shell's JAVA_HOME, and the system default JDK is often older.",
        "Set the editor setting '%s' (Editor > Editor Settings > Kanama > Build) to a JDK %d+ home directory%s, or start Godot with JAVA_HOME set." % [SETTING_BUILD_JDK_PATH, MIN_JDK_MAJOR, " and restart the editor" if mode == MODE_RUNTIME else ""],
    ]
    if java_home_env.strip_edges().is_empty():
        lines.append("JAVA_HOME is not set in the editor's environment.")
    return _jdk_failure("\n".join(lines), notes)


## True when JDK `a` beats `b` among install-location candidates (the same rule as bootstrap.c).
static func _jdk_better(a: Dictionary, b: Dictionary) -> bool:
    if bool(a["ea"]) != bool(b["ea"]):
        return not bool(a["ea"])
    var a_exact := int(a["major"]) == MIN_JDK_MAJOR
    var b_exact := int(b["major"]) == MIN_JDK_MAJOR
    if a_exact != b_exact:
        return a_exact
    var cmp := compare_version_parts(a["parts"], b["parts"])
    if cmp != 0:
        return cmp > 0
    return String(a["home"]) < String(b["home"])


static func _jdk_success(info: Dictionary, source: String, notes: Array) -> Dictionary:
    return {
        "ok": true,
        "home": info["home"],
        "version": info["version"],
        "major": info["major"],
        "ea": info["ea"],
        "source": source,
        "notes": notes,
        "message": "",
    }


static func _jdk_failure(message: String, notes: Array) -> Dictionary:
    var text := message
    if not notes.is_empty():
        text += "\nSkipped:"
        for note in notes:
            text += "\n- %s" % String(note)
    return {"ok": false, "home": "", "version": "", "major": 0, "source": "", "notes": notes, "message": "[kanama:tools] " + text}


static func _strip_trailing_separator(path: String) -> String:
    var result := path
    while result.length() > 1 and (result.ends_with("/") or result.ends_with("\\")):
        result = result.left(result.length() - 1)
    return result


## Reads <home>/release (JAVA_VERSION="25.0.4.1") and checks what `mode` needs: MODE_RUNTIME the
## libjvm of this OS, MODE_BUILD bin/java and include/jni.h (a JRE cannot build the native
## bootstrap). Returns {"ok", "home", "version", "parts", "major", "ea", "reason"}; "reason" says why
## a home is not usable.
static func inspect_jdk(home_path: String, mode: String = MODE_BUILD, os_name: String = "") -> Dictionary:
    var home := _strip_trailing_separator(home_path.strip_edges())
    var host_os := os_name if not os_name.is_empty() else OS.get_name()
    if not DirAccess.dir_exists_absolute(home):
        return {"ok": false, "reason": "not a directory"}
    var release_path := home.path_join("release")
    if not FileAccess.file_exists(release_path):
        return {"ok": false, "reason": "no release file"}
    var file := FileAccess.open(release_path, FileAccess.READ)
    if file == null:
        return {"ok": false, "reason": "cannot read the release file"}
    var version := ""
    while not file.eof_reached():
        var line := file.get_line().strip_edges()
        if line.begins_with("JAVA_VERSION="):
            version = line.get_slice("=", 1).strip_edges().trim_prefix("\"").trim_suffix("\"")
            break
    file.close()
    var parts := parse_java_version(version)
    if parts.is_empty():
        return {"ok": false, "reason": "the release file has no JAVA_VERSION"}
    if mode == MODE_RUNTIME:
        var libjvm_relative := _desktop_jvm_relative_path(host_os)
        if not FileAccess.file_exists(home.path_join(libjvm_relative)):
            return {"ok": false, "reason": "no %s" % libjvm_relative}
    else:
        var java_name := "bin/java.exe" if host_os == "Windows" else "bin/java"
        if not FileAccess.file_exists(home.path_join(java_name)):
            return {"ok": false, "reason": "no %s" % java_name}
        if not FileAccess.file_exists(home.path_join("include/jni.h")):
            return {"ok": false, "reason": "no include/jni.h: a JRE, not a full JDK"}
    return {"ok": true, "home": home, "version": version, "parts": parts, "major": int(parts[0]), "ea": version.contains("-ea"), "reason": ""}


## "25.0.4.1" -> [25, 0, 4, 1]; "26-ea" -> [26]; "1.8.0_302" -> [8, 0, 302]; "" -> []. A component
## saturates at 999999999, exactly as bootstrap.c's jdk_parse_version does.
static func parse_java_version(version: String) -> Array:
    var parts: Array = []
    var digits := 0
    var have_digits := false
    for ch in version:
        if ch >= "0" and ch <= "9":
            digits = digits * 10 + int(ch) if digits < 100000000 else 999999999
            have_digits = true
            continue
        if have_digits:
            parts.append(digits)
            digits = 0
            have_digits = false
        if ch != "." and ch != "_" and ch != "+":
            break
    if have_digits:
        parts.append(digits)
    if parts.size() >= 2 and int(parts[0]) == 1:
        parts.pop_front()
    return parts


static func compare_version_parts(a: Array, b: Array) -> int:
    var count := maxi(a.size(), b.size())
    for i in count:
        var left := int(a[i]) if i < a.size() else 0
        var right := int(b[i]) if i < b.size() else 0
        if left != right:
            return 1 if left > right else -1
    return 0


## The install locations, one table for every OS: [os, parent dir, suffix below each child, child
## name prefix]. "~" is the user's home, "$ProgramFiles" is %ProgramFiles%. bootstrap.c carries the
## same table (k_jdk_locations); scripts/check_jdk_locations_parity.py fails when they differ.
# KANAMA_JDK_LOCATIONS_BEGIN (same table, same order, in bootstrap.c)
const JDK_LOCATIONS := [
    ["linux", "/usr/lib/jvm", "", ""],
    ["linux", "/usr/lib64/jvm", "", ""],
    ["linux", "/usr/java", "", ""],
    ["linux", "/usr/local/java", "", ""],
    ["linux", "/opt/java", "", ""],
    ["linux", "/opt/jdk", "", ""],
    ["macos", "/Library/Java/JavaVirtualMachines", "Contents/Home", ""],
    ["macos", "~/Library/Java/JavaVirtualMachines", "Contents/Home", ""],
    ["macos", "/opt/homebrew/opt", "libexec/openjdk.jdk/Contents/Home", "openjdk"],
    ["macos", "/usr/local/opt", "libexec/openjdk.jdk/Contents/Home", "openjdk"],
    ["windows", "$ProgramFiles/Java", "", ""],
    ["windows", "$ProgramFiles/Eclipse Adoptium", "", ""],
    ["windows", "$ProgramFiles/Microsoft", "", ""],
    ["windows", "$ProgramFiles/Zulu", "", ""],
    ["windows", "$ProgramFiles/BellSoft", "", ""],
    ["windows", "$ProgramFiles/Amazon Corretto", "", ""],
    ["windows", "$ProgramFiles/Semeru", "", ""],
    ["windows", "$ProgramFiles/Temurin", "", ""],
    ["windows", "~/scoop/apps", "current", ""],
    ["all", "~/.jdks", "", ""],
    ["all", "~/.sdkman/candidates/java", "", ""],
]
# KANAMA_JDK_LOCATIONS_END


## JDK home candidates from the table: the children of each parent (names sorted). A non-empty
## `search_dirs` (KANAMA_TEST_JDK_SEARCH_DIRS, a path list whose entries are parents of JDK homes)
## replaces the table; tests use it to lay out fake JDKs for bootstrap.c and the plugin alike.
static func jdk_install_candidates(os_name: String, home_dir: String, program_files: String, search_dirs: String = "") -> Array:
    var entries: Array = []  # [parent, suffix, prefix]
    if not search_dirs.strip_edges().is_empty():
        for dir in search_dirs.split(";" if os_name == "Windows" else ":", false):
            entries.append([dir, "", ""])
    else:
        var host := "windows" if os_name == "Windows" else ("macos" if os_name == "macOS" else "linux")
        var files := program_files.strip_edges() if not program_files.strip_edges().is_empty() else "C:/Program Files"
        for location in JDK_LOCATIONS:
            if location[0] != "all" and location[0] != host:
                continue
            var parent := String(location[1])
            if parent == "~" or parent.begins_with("~/"):
                if home_dir.is_empty():
                    continue
                parent = home_dir + parent.substr(1)
            elif parent.begins_with("$ProgramFiles"):
                parent = files + parent.substr(13)
            entries.append([parent, location[2], location[3]])
    var result: Array = []
    for entry in entries:
        var dir := DirAccess.open(String(entry[0]))
        if dir == null:
            continue
        var names: Array = []
        dir.list_dir_begin()
        while true:
            var name := dir.get_next()
            if name.is_empty():
                break
            if name == "." or name == "..":
                continue
            if not String(entry[2]).is_empty() and not name.begins_with(String(entry[2])):
                continue
            if dir.current_is_dir():
                names.append(name)
        dir.list_dir_end()
        names.sort()
        for name in names:
            var path := String(entry[0]).path_join(String(name))
            if not String(entry[1]).is_empty():
                path = path.path_join(String(entry[1]))
            result.append(path)
    return result


static func _user_home_dir() -> String:
    var home := OS.get_environment("USERPROFILE" if OS.get_name() == "Windows" else "HOME")
    return home.replace("\\", "/")


## bootstrap.c cannot read EditorSettings, so the explicit 'kanama/build/jdk_path' reaches it as
## <project>/.godot/kanama_jdk_home (read before JAVA_HOME). An empty `home` removes the file.
static func write_runtime_jdk_hint(project_dir: String, home: String) -> void:
    var godot_dir := project_dir.path_join(".godot")
    var hint_path := godot_dir.path_join(JDK_HINT_FILE)
    if home.is_empty():
        if FileAccess.file_exists(hint_path):
            DirAccess.remove_absolute(hint_path)
        return
    DirAccess.make_dir_recursive_absolute(godot_dir)
    var file := FileAccess.open(hint_path, FileAccess.WRITE)
    if file == null:
        push_warning("[kanama:tools] Could not write %s" % hint_path)
        return
    file.store_string(home + "\n")
    file.close()


## Godot's OS.execute has no environment parameter. OS.create_process would not give the exit code
## and output Build Scripts reports, so JAVA_HOME is set on the editor process around the blocking
## call and the previous value (or its absence) is restored right after. The editor does nothing
## else while OS.execute blocks, so the temporary change is not observable elsewhere.
static func execute_with_java_home(java_home: String, path: String, args: Array, output: Array) -> int:
    var had_previous := OS.has_environment("JAVA_HOME")
    var previous := OS.get_environment("JAVA_HOME")
    OS.set_environment("JAVA_HOME", java_home)
    var code := OS.execute(path, args, output, true, true)
    if had_previous:
        OS.set_environment("JAVA_HOME", previous)
    else:
        OS.unset_environment("JAVA_HOME")
    return code


func _set_build_buttons_busy(task_name: String, busy: bool) -> void:
    _is_build_running = busy
    if _sync_button != null:
        _sync_button.disabled = busy
        _sync_button.text = SYNC_BUTTON_BUSY_TEXT if busy and _is_script_build_task(task_name) else SYNC_BUTTON_IDLE_TEXT
    if _jar_button != null:
        _jar_button.disabled = busy
        _jar_button.text = JAR_BUTTON_BUSY_TEXT if busy and task_name == "jar" else JAR_BUTTON_IDLE_TEXT


func _resolve_repo_dir() -> String:
    if ProjectSettings.has_setting(SETTING_REPO_DIR):
        var configured := String(ProjectSettings.get_setting(SETTING_REPO_DIR, "")).strip_edges()
        if not configured.is_empty():
            return configured

    var project_dir := ProjectSettings.globalize_path("res://")
    var repo_candidates := [
        project_dir.path_join(".."),
        project_dir.path_join("../kanama"),
        project_dir.path_join("../../kanama"),
    ]
    for repo_candidate in repo_candidates:
        if FileAccess.file_exists(repo_candidate.path_join("gradlew")):
            return repo_candidate
    if FileAccess.file_exists(project_dir.path_join("gradlew")):
        return project_dir
    return ""


func _project_dir_with_gradle_wrapper() -> String:
    var project_dir := ProjectSettings.globalize_path("res://")
    var gradlew := project_dir.path_join("gradlew")
    if OS.get_name() == "Windows":
        gradlew += ".bat"
    if FileAccess.file_exists(gradlew):
        return project_dir
    return ""


func _open_kotlin_sources() -> void:
    var dir := _resolve_kotlin_sources_dir()
    if dir.is_empty():
        push_error("[kanama:tools] Could not resolve Kotlin source directory.")
        return
    if not DirAccess.dir_exists_absolute(dir):
        push_error("[kanama:tools] Kotlin source directory does not exist: %s" % dir)
        return
    var error := OS.shell_open(dir)
    if error != OK:
        push_error("[kanama:tools] Could not open Kotlin source directory: %s (error=%d)" % [dir, error])


func _resolve_kotlin_sources_dir() -> String:
    if ProjectSettings.has_setting(SETTING_KOTLIN_SOURCES_DIR):
        var configured := String(ProjectSettings.get_setting(SETTING_KOTLIN_SOURCES_DIR, "")).strip_edges()
        if not configured.is_empty():
            if configured.begins_with("res://"):
                return ProjectSettings.globalize_path(configured)
            return configured
    return ProjectSettings.globalize_path("res://")


func _is_script_build_task(task_name: String) -> bool:
    return task_name == "buildScripts" or task_name == "installAddonJar"


func _run_java_preflight(force_dialog: bool = false) -> bool:
    if not bool(ProjectSettings.get_setting(SETTING_JAVA_PREFLIGHT_ENABLED, true)):
        return true
    var result := _detect_desktop_jvm()
    if bool(result.get("ok", false)) and not _restart_needed_message.is_empty():
        result = {"ok": false, "message": _restart_needed_message}
    if bool(result.get("ok", false)):
        print("[kanama:tools] Java runtime preflight ok: %s" % String(result.get("path", "")))
        return true

    var message := String(result.get("message", ""))
    push_warning(message)
    if force_dialog or not _java_preflight_dialog_shown:
        _show_java_preflight_dialog(message)
        _java_preflight_dialog_shown = true
    return false


func _detect_desktop_jvm() -> Dictionary:
    return detect_desktop_jvm(resolve_runtime_jdk_from_environment(), OS.get_name())


## The startup preflight mirrors what bootstrap.c will do: `resolution` is a MODE_RUNTIME
## resolve_jdk() result (the setting, then JAVA_HOME, then the install locations, each needing
## a JDK 25+ with its libjvm), so there is no "libjvm not found" while the runtime would find
## one, and none the other way round. Returns {"ok", "path"} or {"ok": false, "message"}.
static func detect_desktop_jvm(resolution: Dictionary, os_name: String) -> Dictionary:
    if os_name == "Android" or os_name == "Web":
        return {"ok": true, "path": ""}
    if not bool(resolution.get("ok", false)):
        return {"ok": false, "message": String(resolution.get("message", ""))}
    return {"ok": true, "path": String(resolution["home"]).path_join(_desktop_jvm_relative_path(os_name))}


static func _desktop_jvm_relative_path(os_name: String) -> String:
    match os_name:
        "Windows":
            return "bin/server/jvm.dll"
        "macOS":
            return "lib/server/libjvm.dylib"
        _:
            return "lib/server/libjvm.so"


func _show_java_preflight_dialog(message: String) -> void:
    var editor := get_editor_interface()
    if editor == null:
        return
    var base_control := editor.get_base_control()
    if base_control == null:
        return
    var dialog := AcceptDialog.new()
    dialog.title = "Kanama Java Runtime Not Found"
    dialog.dialog_text = message
    dialog.min_size = Vector2i(560, 220)
    base_control.add_child(dialog)
    dialog.confirmed.connect(dialog.queue_free)
    dialog.close_requested.connect(dialog.queue_free)
    dialog.popup_centered()


func _current_project_install_args() -> Array:
    var project_dir := ProjectSettings.globalize_path("res://")
    return [
        "-PkanamaProjectDir=%s" % project_dir,
        "-PkanamaProjectScriptsDir=%s" % project_dir,
    ]


func _queue_reload_edited_scene() -> void:
    call_deferred("_reload_edited_scene_async")


func _reload_edited_scene_async() -> void:
    # Let script hot-reload settle before replacing edited scene instances.
    await get_tree().process_frame
    await get_tree().process_frame

    var editor := get_editor_interface()
    if editor == null:
        return

    var root := editor.get_edited_scene_root()
    if root == null:
        return

    var scene_path := root.scene_file_path
    if scene_path.is_empty():
        return

    if editor.has_method("reload_scene_from_path"):
        editor.reload_scene_from_path(scene_path)
        print("[kanama:tools] Reloaded scene after sync: %s" % scene_path)
    else:
        editor.open_scene_from_path(scene_path)
        print("[kanama:tools] Reopened scene after sync: %s" % scene_path)
