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
const MIN_BUILD_JDK_MAJOR := 25
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


func _enter_tree() -> void:
    _ensure_project_settings()
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
    if not ProjectSettings.has_setting(SETTING_BUILD_JDK_PATH):
        ProjectSettings.set_setting(SETTING_BUILD_JDK_PATH, "")
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
        "name": SETTING_BUILD_JDK_PATH,
        "type": TYPE_STRING,
        "hint": PROPERTY_HINT_GLOBAL_DIR,
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

    _run_java_preflight(true)

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
    for note in jdk.get("notes", []):
        print("[kanama:tools]   skipped: %s" % String(note))

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


## Which JDK Build Scripts runs Gradle with. Order: the 'kanama/build/jdk_path' project setting
## (empty = auto), then JAVA_HOME from the editor's environment, then the newest JDK found in the
## common install locations of this OS. Only a JDK >= MIN_BUILD_JDK_MAJOR counts. A wrong explicit
## setting is an error, not a silent fall-through; a JAVA_HOME that points at an older JDK is skipped
## (it is usually set for some other project) and reported.
static func resolve_build_jdk_from_environment() -> Dictionary:
    var setting := String(ProjectSettings.get_setting(SETTING_BUILD_JDK_PATH, "")).strip_edges()
    if setting.begins_with("res://") or setting.begins_with("user://"):
        setting = ProjectSettings.globalize_path(setting)
    return resolve_build_jdk(
        setting,
        OS.get_environment("JAVA_HOME"),
        jdk_install_candidates(OS.get_name(), _user_home_dir(), OS.get_environment("ProgramFiles")),
    )


## Pure resolution (no editor state), so the order and the failure texts are testable.
## Returns {"ok", "home", "version", "major", "source", "notes", "message"}.
static func resolve_build_jdk(setting_value: String, java_home_env: String, install_candidates: Array) -> Dictionary:
    var notes: Array = []
    var setting := setting_value.strip_edges()
    if not setting.is_empty():
        var info := inspect_jdk(setting)
        if not bool(info.get("ok", false)):
            return _jdk_failure("'%s' is set to '%s', which is not a usable JDK (%s). Set it to the home directory of a JDK %d or newer (the folder that contains bin/ and lib/), or clear it to auto-detect." % [SETTING_BUILD_JDK_PATH, setting, String(info.get("reason", "")), MIN_BUILD_JDK_MAJOR], notes)
        if int(info["major"]) < MIN_BUILD_JDK_MAJOR:
            return _jdk_failure("'%s' is set to '%s', which is JDK %s. Kanama needs JDK %d or newer: point the setting at one, or clear it to auto-detect." % [SETTING_BUILD_JDK_PATH, setting, String(info["version"]), MIN_BUILD_JDK_MAJOR], notes)
        return _jdk_success(info, "the %s project setting" % SETTING_BUILD_JDK_PATH, notes)

    var env_home := java_home_env.strip_edges()
    if not env_home.is_empty():
        var env_info := inspect_jdk(env_home)
        if bool(env_info.get("ok", false)) and int(env_info["major"]) >= MIN_BUILD_JDK_MAJOR:
            return _jdk_success(env_info, "JAVA_HOME", notes)
        if bool(env_info.get("ok", false)):
            notes.append("JAVA_HOME=%s is JDK %s (need %d+)" % [env_home, String(env_info["version"]), MIN_BUILD_JDK_MAJOR])
        else:
            notes.append("JAVA_HOME=%s is not a usable JDK (%s)" % [env_home, String(env_info.get("reason", ""))])

    var best: Dictionary = {}
    for candidate in install_candidates:
        var info := inspect_jdk(String(candidate))
        if not bool(info.get("ok", false)):
            continue
        if int(info["major"]) < MIN_BUILD_JDK_MAJOR:
            notes.append("%s is JDK %s (need %d+)" % [String(candidate), String(info["version"]), MIN_BUILD_JDK_MAJOR])
            continue
        if best.is_empty() or compare_version_parts(info["parts"], best["parts"]) > 0:
            best = info
    if not best.is_empty():
        return _jdk_success(best, "an install location (newest JDK %d+ found)" % MIN_BUILD_JDK_MAJOR, notes)

    var lines: Array = [
        "Kanama could not find a JDK %d or newer for Build Scripts." % MIN_BUILD_JDK_MAJOR,
        "Godot started from a desktop launcher does not inherit your shell's JAVA_HOME, and the system default JDK is often older.",
        "Set the project setting '%s' (Project Settings > Kanama > Build, with Advanced Settings on) to a JDK %d+ home directory, or start Godot with JAVA_HOME set." % [SETTING_BUILD_JDK_PATH, MIN_BUILD_JDK_MAJOR],
    ]
    if java_home_env.strip_edges().is_empty():
        lines.append("JAVA_HOME is not set in the editor's environment.")
    return _jdk_failure("\n".join(lines), notes)


static func _jdk_success(info: Dictionary, source: String, notes: Array) -> Dictionary:
    return {
        "ok": true,
        "home": info["home"],
        "version": info["version"],
        "major": info["major"],
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


## Reads <home>/release (JAVA_VERSION="25.0.4.1") and checks bin/java. Returns
## {"ok", "home", "version", "parts", "major", "reason"}; "reason" says why a home is not a JDK.
static func inspect_jdk(home_path: String) -> Dictionary:
    var home := home_path.strip_edges()
    if home.length() > 1 and (home.ends_with("/") or home.ends_with("\\")):
        home = home.left(home.length() - 1)
    # A macOS bundle (…/temurin-25.jdk) keeps the JDK home under Contents/Home.
    if DirAccess.dir_exists_absolute(home.path_join("Contents/Home")):
        home = home.path_join("Contents/Home")
    if not DirAccess.dir_exists_absolute(home):
        return {"ok": false, "reason": "not a directory"}
    var java_name := "bin/java.exe" if OS.get_name() == "Windows" else "bin/java"
    if not FileAccess.file_exists(home.path_join(java_name)):
        return {"ok": false, "reason": "no %s" % java_name}
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
    return {"ok": true, "home": home, "version": version, "parts": parts, "major": int(parts[0]), "reason": ""}


## "25.0.4.1" -> [25, 0, 4, 1]; "26-ea" -> [26]; "1.8.0_302" -> [8, 0, 302]; "" -> [].
static func parse_java_version(version: String) -> Array:
    var parts: Array = []
    var digits := ""
    for ch in version:
        if ch >= "0" and ch <= "9":
            digits += ch
            continue
        if not digits.is_empty():
            parts.append(int(digits))
            digits = ""
        if ch != "." and ch != "_" and ch != "+":
            break
    if not digits.is_empty():
        parts.append(int(digits))
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


## JDK home directories to look at when neither the setting nor JAVA_HOME helps: the immediate
## children of the usual install parents, in a stable order.
static func jdk_install_candidates(os_name: String, home_dir: String, program_files: String) -> Array:
    var parents: Array = []  # [parent_dir, suffix_below_each_child]
    match os_name:
        "Windows":
            var pf := program_files.strip_edges() if not program_files.strip_edges().is_empty() else "C:/Program Files"
            for vendor in ["Java", "Eclipse Adoptium", "Microsoft", "Zulu", "BellSoft", "Amazon Corretto", "Semeru", "Temurin"]:
                parents.append([pf.path_join(vendor), ""])
        "macOS":
            parents.append(["/Library/Java/JavaVirtualMachines", "Contents/Home"])
            if not home_dir.is_empty():
                parents.append([home_dir.path_join("Library/Java/JavaVirtualMachines"), "Contents/Home"])
        _:
            parents.append(["/usr/lib/jvm", ""])
            parents.append(["/usr/java", ""])
            parents.append(["/opt/java", ""])
            parents.append(["/opt/jdk", ""])
    if not home_dir.is_empty():
        parents.append([home_dir.path_join(".jdks"), ""])
        parents.append([home_dir.path_join(".sdkman/candidates/java"), ""])
    var result: Array = []
    for entry in parents:
        var dir := DirAccess.open(String(entry[0]))
        if dir == null:
            continue
        var names: Array = []
        dir.list_dir_begin()
        while true:
            var name := dir.get_next()
            if name.is_empty():
                break
            if name == "." or name == ".." or name == "current":
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
    if bool(result.get("ok", false)):
        print("[kanama:tools] Java runtime preflight ok: %s" % String(result.get("path", "")))
        return true

    var message := _java_preflight_message(result)
    push_warning(message)
    if force_dialog or not _java_preflight_dialog_shown:
        _show_java_preflight_dialog(message)
        _java_preflight_dialog_shown = true
    return false


func _detect_desktop_jvm() -> Dictionary:
    var os_name := OS.get_name()
    if os_name == "Android" or os_name == "Web":
        return {"ok": true, "path": ""}

    var checked_paths: Array[String] = []
    var java_home := OS.get_environment("JAVA_HOME").strip_edges()
    var relative_path := _desktop_jvm_relative_path()
    if not java_home.is_empty():
        var java_home_candidate := java_home.path_join(relative_path)
        checked_paths.append(java_home_candidate)
        if FileAccess.file_exists(java_home_candidate):
            return {"ok": true, "path": java_home_candidate}

    for candidate in _desktop_jvm_fallback_paths():
        checked_paths.append(candidate)
        if FileAccess.file_exists(candidate):
            return {"ok": true, "path": candidate}

    return {
        "ok": false,
        "java_home": java_home,
        "relative_path": relative_path,
        "checked_paths": checked_paths,
    }


func _desktop_jvm_relative_path() -> String:
    match OS.get_name():
        "Windows":
            return "bin/server/jvm.dll"
        "macOS":
            return "lib/server/libjvm.dylib"
        _:
            return "lib/server/libjvm.so"


func _desktop_jvm_fallback_paths() -> Array[String]:
    match OS.get_name():
        "Windows":
            return [
                "C:/Program Files/Eclipse Adoptium/jdk-25/bin/server/jvm.dll",
            ]
        "macOS":
            return [
                "/Library/Java/JavaVirtualMachines/temurin-25.jdk/Contents/Home/lib/server/libjvm.dylib",
            ]
        _:
            return [
                "/usr/lib/jvm/temurin-25-jdk-arm64/lib/server/libjvm.so",
                "/usr/lib/jvm/temurin-25-jdk-amd64/lib/server/libjvm.so",
                "/usr/lib/jvm/temurin-25-jdk/lib/server/libjvm.so",
                "/usr/lib/jvm/java-25-openjdk-arm64/lib/server/libjvm.so",
                "/usr/lib/jvm/java-25-openjdk-amd64/lib/server/libjvm.so",
                "/usr/lib/jvm/java-25-openjdk/lib/server/libjvm.so",
            ]


func _java_preflight_message(result: Dictionary) -> String:
    var java_home := String(result.get("java_home", ""))
    var relative_path := String(result.get("relative_path", ""))
    var lines: Array[String] = [
        "Kanama could not find libjvm for the desktop JVM.",
        "Install a JDK 25+ distribution that includes libjvm, then set JAVA_HOME to the JDK home directory.",
        "Expected relative path: %s" % relative_path,
    ]
    if java_home.is_empty():
        lines.append("JAVA_HOME is not set.")
    else:
        lines.append("JAVA_HOME is set to: %s" % java_home)
    var checked_paths: Array = result.get("checked_paths", [])
    if not checked_paths.is_empty():
        lines.append("Checked paths:")
        for path in checked_paths:
            lines.append("- %s" % String(path))
    return "\n".join(lines)


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
