@tool
extends EditorPlugin

const MENU_BUILD_SYNC := "Kanama Tools: Build Scripts"
const MENU_BUILD_JAR := "Kanama Tools: Build Runtime Jar"
const SETTING_REPO_DIR := "kanama/tools/repo_dir"
const SETTING_AUTO_BUILD_ON_SAVE := "kanama/tools/auto_build_on_save"
const SETTING_AUTO_BUILD_DEBOUNCE_MS := "kanama/tools/auto_build_debounce_ms"
const SETTING_RELOAD_SCENE_AFTER_SYNC := "kanama/tools/reload_scene_after_sync"
const SETTING_DEVELOPER_MODE := "kanama/tools/developer_mode"
const SETTING_JDWP_ENABLED := "kanama/debug/jdwp_enabled"
const SETTING_JDWP_PORT := "kanama/debug/jdwp_port"
const DEFAULT_JDWP_PORT := 5005
const SYNC_BUTTON_IDLE_TEXT := "Build Scripts"
const SYNC_BUTTON_BUSY_TEXT := "Building..."
const JAR_BUTTON_IDLE_TEXT := "Build Runtime"
const JAR_BUTTON_BUSY_TEXT := "Building..."
const KT_SCAN_INTERVAL_SEC := 0.6
const KotlinSyntaxHighlighter := preload("res://addons/kanama_tools/kotlin_syntax_highlighter.gd")

var _toolbar_container: HBoxContainer
var _sync_button: Button
var _jar_button: Button
var _kotlin_syntax_highlighter: EditorSyntaxHighlighter
var _scan_accum_sec := 0.0
var _known_kt_mtimes: Dictionary = {}
var _pending_auto_sync := false
var _last_change_msec := 0
var _is_build_running := false
var _jar_menu_added := false


func _enter_tree() -> void:
    _ensure_project_settings()
    add_tool_menu_item(MENU_BUILD_SYNC, _on_build_sync_pressed)
    if _is_developer_mode_enabled():
        add_tool_menu_item(MENU_BUILD_JAR, _on_build_jar_pressed)
        _jar_menu_added = true
    call_deferred("_register_kotlin_syntax_highlighter")
    _install_toolbar_buttons()
    _known_kt_mtimes = _collect_kt_mtimes()
    set_process(true)


func _exit_tree() -> void:
    set_process(false)
    remove_tool_menu_item(MENU_BUILD_SYNC)
    if _jar_menu_added:
        remove_tool_menu_item(MENU_BUILD_JAR)
        _jar_menu_added = false
    _unregister_kotlin_syntax_highlighter()
    _remove_toolbar_buttons()


func _on_build_sync_pressed() -> void:
    _run_script_build()


func _on_build_jar_pressed() -> void:
    _run_gradle_task("jar")

func _process(delta: float) -> void:
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


func _ensure_project_settings() -> void:
    if not ProjectSettings.has_setting(SETTING_REPO_DIR):
        ProjectSettings.set_setting(SETTING_REPO_DIR, "")
    if not ProjectSettings.has_setting(SETTING_AUTO_BUILD_ON_SAVE):
        ProjectSettings.set_setting(SETTING_AUTO_BUILD_ON_SAVE, false)
    if not ProjectSettings.has_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS):
        ProjectSettings.set_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS, 800)
    if not ProjectSettings.has_setting(SETTING_RELOAD_SCENE_AFTER_SYNC):
        ProjectSettings.set_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)
    if not ProjectSettings.has_setting(SETTING_DEVELOPER_MODE):
        ProjectSettings.set_setting(SETTING_DEVELOPER_MODE, false)
    if not ProjectSettings.has_setting(SETTING_JDWP_ENABLED):
        ProjectSettings.set_setting(SETTING_JDWP_ENABLED, false)
    if not ProjectSettings.has_setting(SETTING_JDWP_PORT):
        ProjectSettings.set_setting(SETTING_JDWP_PORT, DEFAULT_JDWP_PORT)
    ProjectSettings.set_initial_value(SETTING_JDWP_ENABLED, false)
    ProjectSettings.set_initial_value(SETTING_JDWP_PORT, DEFAULT_JDWP_PORT)

    ProjectSettings.add_property_info({
        "name": SETTING_REPO_DIR,
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
        "name": SETTING_JDWP_ENABLED,
        "type": TYPE_BOOL,
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

    var output: Array = []
    var args := ["-p", repo_dir, task_name]
    args.append_array(extra_args)
    print("[kanama:tools] Running: %s %s" % [gradlew, " ".join(args)])
    _set_build_buttons_busy(task_name, true)
    var code := OS.execute(gradlew, args, output, true, true)
    _set_build_buttons_busy(task_name, false)
    if code == 0:
        print("[kanama:tools] %s succeeded." % task_name)
        if _is_script_build_task(task_name) and bool(ProjectSettings.get_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)):
            _queue_reload_edited_scene()
    else:
        push_error("[kanama:tools] %s failed (exit=%d)\n%s" % [task_name, code, "\n".join(output)])


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


func _is_script_build_task(task_name: String) -> bool:
    return task_name == "buildScripts" or task_name == "installAddonJar"


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
