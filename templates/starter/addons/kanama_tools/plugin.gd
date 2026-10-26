@tool
extends EditorPlugin

const MENU_BUILD_SYNC := "Kanama Tools: Build Scripts"
const MENU_BUILD_JAR := "Kanama Tools: Build Runtime Jar"
const MENU_OPEN_KOTLIN_SOURCES := "Kanama Tools: Open Kotlin Sources"
const MENU_SETUP_PROJECT := "Kanama Tools: Set Up Kanama Project"
const SETTING_REPO_DIR := "kanama/tools/repo_dir"
const SETTING_KOTLIN_SOURCES_DIR := "kanama/tools/kotlin_sources_dir"
# An EditorSettings entry (Editor > Editor Settings > Kanama > Build), on by default: whether to build
# is a habit of the person at the machine, not a property of the project shared through version
# control. The project setting kanama/tools/auto_build_on_save it replaces is no longer read.
const SETTING_BUILD_ON_SAVE := "kanama/build/build_on_save"
const OLD_SETTING_AUTO_BUILD_ON_SAVE := "kanama/tools/auto_build_on_save"
# Editor Settings > Kanama > Build > Build Timeout Sec: a build running longer is stopped and the light
# turns red (a Gradle waiting on a lock or the network would otherwise keep the light yellow forever).
const SETTING_BUILD_TIMEOUT_SEC := "kanama/build/build_timeout_sec"
const DEFAULT_BUILD_TIMEOUT_SEC := 1200
const SETTING_AUTO_BUILD_DEBOUNCE_MS := "kanama/tools/auto_build_debounce_ms"
const SETTING_RELOAD_SCENE_AFTER_SYNC := "kanama/tools/reload_scene_after_sync"
const SETTING_DEVELOPER_MODE := "kanama/tools/developer_mode"
const SETTING_JAVA_PREFLIGHT_ENABLED := "kanama/tools/java_preflight_enabled"
const SETTING_EXPORT_GUARD_ENABLED := "kanama/tools/export_guard_enabled"
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
# The version lock (task 143 item 1): the addon's Kanama version, and the one buildScripts built
# kanama-scripts.jar with.
const ADDON_VERSION_FILE := "res://addons/kanama/kanama.version"
const SCRIPTS_VERSION_FILE := "res://addons/kanama/kanama-scripts.version"
const SYNC_ADDON_TEXT := "Sync Addon"
# While the editor has the project open: its process id, so the Kanama Gradle plugin can warn that an
# edit it makes to project.godot needs Project > Reload Current Project (Godot leaves no such mark).
const EDITOR_PID_FILE := "res://.godot/kanama_editor.pid"
const SCRIPTS_JAR := "res://addons/kanama/kanama-scripts.jar"
# The .kt files the last successful script build compiled (sources_fingerprint), so the status light
# is right after an editor restart too. In .godot/: it describes this checkout, not the project.
const BUILD_STAMP_FILE := "kanama_build_stamp"
# The build status light: STATUS_CURRENT is green, STATUS_STALE and STATUS_BUILDING are yellow,
# STATUS_FAILED is red, STATUS_NONE (no Kotlin scripts and no scripts jar) is grey.
const STATUS_NONE := "none"
const STATUS_CURRENT := "current"
const STATUS_STALE := "stale"
const STATUS_BUILDING := "building"
const STATUS_FAILED := "failed"
# Build Release: the Gradle task that builds the scripts for a release, in the project's own build.
# None exists yet ("" hides the button); name it here when one does.
const RELEASE_BUILD_TASK := ""
# The platform builds of the toolbar's Platforms menu. Each entry runs either a task of the Kanama
# Gradle plugin in the project's own build ("project_task", for a kit or store-addon project whose
# build.gradle.kts applies net.multigesture.kanama; "" where the plugin has none yet) or the commands
# the export page documents, in the Kanama checkout the project was set up from ("checkout_tasks";
# {project} and {scripts} are the project and its Kotlin source folder). The project task wins when
# both exist. An entry with neither is not shown. "script_build" marks the checkout commands that also
# rebuild kanama-scripts.jar, so the status light and scene reload follow them.
const PLATFORM_BUILDS := [
    {
        "id": "android",
        "label": "Android",
        "project_task": "buildAndroidScripts",
        "checkout_tasks": [
            ["installAndroidPluginAar", "-PkanamaAndroidProjectDir={project}"],
            ["installAddonJar", "-PkanamaProjectDir={project}", "-PkanamaProjectScriptsDir={scripts}"],
        ],
        "os": [],
        "script_build": true,
    },
    {
        "id": "ios",
        "label": "iOS",
        "project_task": "",
        "checkout_tasks": [
            ["installIosAddon", "-PkanamaIosProjectDir={project}", "-PkanamaIosProjectScriptsDir={scripts}"],
        ],
        "os": ["macOS"],
        "script_build": true,
    },
    {
        "id": "web",
        "label": "Web",
        "project_task": "",
        "checkout_tasks": [
            [":web-runtime:exportWeb", "-PkanamaWebProjectDir={project}"],
        ],
        "os": [],
        "script_build": false,
    },
]
const SETUP_SCRIPT_SH := "addons/kanama/setup-kanama-project.sh"
const SETUP_SCRIPT_PS1 := "addons/kanama/setup-kanama-project.ps1"
const SETUP_TEMPLATE_DIR := "addons/kanama/templates/release-kit"
const KotlinSyntaxHighlighter := preload("res://addons/kanama_tools/kotlin_syntax_highlighter.gd")
const KanamaExportGuard := preload("res://addons/kanama_tools/kanama_export_guard.gd")

var _toolbar_container: HBoxContainer
var _status_button: Button
var _sync_button: Button
var _release_button: Button
var _platform_button: MenuButton
var _platform_builds: Array = []
var _sources_button: Button
var _jar_button: Button
var _output_dialog: AcceptDialog
var _kotlin_syntax_highlighter: EditorSyntaxHighlighter
var _export_guard: EditorExportPlugin
var _scan_accum_sec := 0.0
var _known_kt_mtimes: Dictionary = {}
var _pending_auto_sync := false
var _last_change_msec := 0
var _is_build_running := false
var _status := STATUS_NONE
var _last_build_failed := false
# The build in progress: its label, the Gradle steps still to run, the running step's process and
# pipes, and the output so far. _last_build keeps the finished one for the status light's dialog.
var _build_label := ""
var _build_steps: Array = []
var _build_is_script_build := false
var _build_fingerprint := ""
var _build_pid := -1
var _build_stdio: FileAccess
var _build_stderr: FileAccess
var _build_output := PackedByteArray()
var _build_started_msec := 0
# "timed out" or "cancelled" when the plugin ended the build, else "".
var _build_end_reason := ""
var _last_build: Dictionary = {}
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
    add_tool_menu_item(MENU_SETUP_PROJECT, _on_setup_project_pressed)
    _last_developer_mode_enabled = _is_developer_mode_enabled()
    if _last_developer_mode_enabled:
        add_tool_menu_item(MENU_BUILD_JAR, _on_build_jar_pressed)
        _jar_menu_added = true
    call_deferred("_register_kotlin_syntax_highlighter")
    _export_guard = KanamaExportGuard.new()
    add_export_plugin(_export_guard)
    _install_toolbar_buttons()
    var filesystem := get_editor_interface().get_resource_filesystem()
    if filesystem != null and not filesystem.filesystem_changed.is_connected(_on_filesystem_changed):
        filesystem.filesystem_changed.connect(_on_filesystem_changed)
    _warn_old_build_on_save_setting()
    _known_kt_mtimes = _collect_kt_mtimes()
    call_deferred("_check_addon_version")
    _write_editor_pid()
    _refresh_build_status()
    set_process(true)


func _exit_tree() -> void:
    set_process(false)
    _stop_running_build()
    if FileAccess.file_exists(EDITOR_PID_FILE):
        DirAccess.remove_absolute(ProjectSettings.globalize_path(EDITOR_PID_FILE))
    _finish_pending_editor_docs()
    var editor_settings := get_editor_interface().get_editor_settings()
    if editor_settings != null and editor_settings.settings_changed.is_connected(_on_editor_settings_changed):
        editor_settings.settings_changed.disconnect(_on_editor_settings_changed)
    var filesystem := get_editor_interface().get_resource_filesystem()
    if filesystem != null and filesystem.filesystem_changed.is_connected(_on_filesystem_changed):
        filesystem.filesystem_changed.disconnect(_on_filesystem_changed)
    remove_tool_menu_item(MENU_BUILD_SYNC)
    remove_tool_menu_item(MENU_OPEN_KOTLIN_SOURCES)
    remove_tool_menu_item(MENU_SETUP_PROJECT)
    if _output_dialog != null:
        _output_dialog.queue_free()
        _output_dialog = null
    if _jar_menu_added:
        remove_tool_menu_item(MENU_BUILD_JAR)
        _jar_menu_added = false
    _unregister_kotlin_syntax_highlighter()
    if _export_guard != null:
        remove_export_plugin(_export_guard)
        _export_guard = null
    _remove_toolbar_buttons()


# Godot 4.7.2 crashes (exit 134) when the editor quits while its documentation thread is still
# loading: the thread queues EditorHelp::_gen_extensions_docs, the editor frees the docs, and
# Main::cleanup then runs that call on a null DocTools as soon as an extension class (Kanama's) is
# registered. On a project without .godot/ the Kanama extension loads during the first scan, which
# restarts that thread just before a failing --export-* quits. Asking for the docs here (the plugin
# leaves while the editor still runs) waits for the thread, so its queued call runs in this frame.
func _finish_pending_editor_docs() -> void:
    var script_editor := get_editor_interface().get_script_editor()
    if script_editor != null:
        script_editor.update_docs_from_script(get_script())


func _on_build_sync_pressed() -> void:
    _run_script_build()


func _on_build_jar_pressed() -> void:
    _run_gradle_task("jar")


func _on_open_kotlin_sources_pressed() -> void:
    _open_kotlin_sources()


func _on_release_build_pressed() -> void:
    var project_gradle_dir := _project_dir_with_gradle_wrapper()
    if RELEASE_BUILD_TASK.is_empty() or project_gradle_dir.is_empty():
        return
    _start_build("Build Release", [_gradle_step(project_gradle_dir, RELEASE_BUILD_TASK, [])], true)


func _on_platform_build_selected(index: int) -> void:
    if index < 0 or index >= _platform_builds.size():
        return
    var entry: Dictionary = _platform_builds[index]
    _start_build(String(entry["label"]), entry["steps"], bool(entry["script_build"]))


func _process(delta: float) -> void:
    _refresh_developer_mode_controls()
    _poll_build()

    _scan_accum_sec += delta
    if _scan_accum_sec < KT_SCAN_INTERVAL_SEC:
        return
    _scan_accum_sec = 0.0

    # Changes are watched with build on save off too: they turn the status light yellow. The light is
    # refreshed on every scan, so a deleted kanama-scripts.jar turns it yellow without a save.
    var changed := _detect_kt_changes()
    _refresh_build_status()
    if changed:
        if is_build_on_save_enabled():
            _pending_auto_sync = true
            _last_change_msec = Time.get_ticks_msec()

    if not _pending_auto_sync or _is_build_running:
        return
    if not is_build_on_save_enabled():
        _pending_auto_sync = false
        return

    var debounce_ms := _auto_build_debounce_ms()
    var elapsed := Time.get_ticks_msec() - _last_change_msec
    if elapsed >= debounce_ms:
        _pending_auto_sync = false
        if _script_build_steps().is_empty():
            return
        print("[kanama:tools] Kotlin save detected; running Build Scripts")
        _run_script_build()


## The version lock (task 143 item 1). kanama-scripts.jar must be built for the addon Godot loads:
## buildScripts writes the Kanama version it compiled against beside the jar, and unzipping a newer
## addon moves addons/kanama/kanama.version. Returns the one line to show on a mismatch, or "" when
## the two agree or either is missing (no jar built yet; a project without the version files).
static func addon_version_mismatch(addon_version_text: String, scripts_version_text: String) -> String:
    var addon := addon_version_text.strip_edges()
    var scripts := scripts_version_text.strip_edges()
    if addon.is_empty() or scripts.is_empty() or addon == scripts:
        return ""
    return "Kanama: your scripts were built for Kanama %s, but the addon is Kanama %s. Press %s to rebuild them for the addon." % [scripts, addon, SYNC_ADDON_TEXT]


func _write_editor_pid() -> void:
    var file := FileAccess.open(EDITOR_PID_FILE, FileAccess.WRITE)
    if file != null:
        file.store_line(str(OS.get_process_id()))


func _check_addon_version() -> void:
    var message := addon_version_mismatch(
        FileAccess.get_file_as_string(ADDON_VERSION_FILE),
        FileAccess.get_file_as_string(SCRIPTS_VERSION_FILE))
    if message.is_empty():
        return
    push_error(message)
    var base_control := get_editor_interface().get_base_control()
    if base_control == null:
        return
    var dialog := ConfirmationDialog.new()
    dialog.title = "Kanama Version Mismatch"
    dialog.dialog_text = message
    dialog.ok_button_text = SYNC_ADDON_TEXT
    base_control.add_child(dialog)
    dialog.confirmed.connect(func() -> void:
        dialog.queue_free()
        _run_script_build())
    dialog.canceled.connect(dialog.queue_free)
    dialog.popup_centered()


## The old project setting is not carried over: build on save is an editor setting now. Say so once.
func _warn_old_build_on_save_setting() -> void:
    if ProjectSettings.has_setting(OLD_SETTING_AUTO_BUILD_ON_SAVE):
        push_warning("[kanama:tools] project.godot still has '%s'; it is no longer read. Build on save is the editor setting Editor > Editor Settings > Kanama > Build > Build On Save ('%s'). Delete the old line from project.godot." % [OLD_SETTING_AUTO_BUILD_ON_SAVE, SETTING_BUILD_ON_SAVE])


func _install_toolbar_buttons() -> void:
    _toolbar_container = HBoxContainer.new()
    _toolbar_container.name = "KanamaToolsToolbar"

    # The status light: green when the scripts are built and current, yellow while they build or
    # when a .kt file changed since, red when the last build failed. Pressing it shows that build's
    # output.
    _status_button = Button.new()
    _status_button.name = "KanamaBuildStatus"
    _status_button.flat = true
    _status_button.icon = _status_light_texture()
    _status_button.pressed.connect(_show_last_build_output)
    _toolbar_container.add_child(_status_button)

    _sync_button = Button.new()
    _sync_button.name = "KanamaBuildScripts"
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
    _refresh_optional_buttons()


## Build Release and Platforms depend on the project's files (its own Gradle build, the Kanama Gradle
## plugin, a checkout beside it), so they are recomputed when the editor's file system changes and after
## Set Up Kanama Project; nothing needs an editor restart.
func _refresh_optional_buttons() -> void:
    if _toolbar_container == null:
        return
    var want_release := not RELEASE_BUILD_TASK.is_empty() and not _project_dir_with_gradle_wrapper().is_empty()
    var platforms := _available_platform_builds()
    var platform_texts := platforms.map(func(entry: Dictionary) -> String: return String(entry["menu_text"]))
    var have_texts := _platform_builds.map(func(entry: Dictionary) -> String: return String(entry["menu_text"]))
    if want_release == (_release_button != null) and platform_texts == have_texts and (_platform_button != null) == not platforms.is_empty():
        return
    for old in [_release_button, _platform_button]:
        if old != null:
            _toolbar_container.remove_child(old)
            old.queue_free()
    _release_button = null
    _platform_button = null
    _platform_builds = platforms
    var index := _sync_button.get_index() + 1
    if want_release:
        _release_button = Button.new()
        _release_button.name = "KanamaBuildRelease"
        _release_button.text = "Build Release"
        _release_button.tooltip_text = "Build the Kotlin scripts for a release export"
        _release_button.pressed.connect(_on_release_build_pressed)
        _toolbar_container.add_child(_release_button)
        _toolbar_container.move_child(_release_button, index)
        index += 1
    if not platforms.is_empty():
        _platform_button = MenuButton.new()
        _platform_button.name = "KanamaPlatformBuilds"
        _platform_button.text = "Platforms"
        _platform_button.flat = false
        _platform_button.tooltip_text = "Build your scripts for another platform's export"
        var popup := _platform_button.get_popup()
        for i in platforms.size():
            popup.add_item(String(platforms[i]["menu_text"]), i)
        popup.id_pressed.connect(_on_platform_build_selected)
        _toolbar_container.add_child(_platform_button)
        _toolbar_container.move_child(_platform_button, index)
    _set_build_buttons_busy(_is_build_running)


func _on_filesystem_changed() -> void:
    _refresh_optional_buttons()


func _remove_toolbar_buttons() -> void:
    if _toolbar_container == null:
        return
    remove_control_from_container(EditorPlugin.CONTAINER_TOOLBAR, _toolbar_container)
    _toolbar_container.queue_free()
    _toolbar_container = null
    _status_button = null
    _sync_button = null
    _release_button = null
    _platform_button = null
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
    if not settings.has_setting(SETTING_BUILD_TIMEOUT_SEC):
        settings.set_setting(SETTING_BUILD_TIMEOUT_SEC, DEFAULT_BUILD_TIMEOUT_SEC)
    settings.set_initial_value(SETTING_BUILD_TIMEOUT_SEC, DEFAULT_BUILD_TIMEOUT_SEC, false)
    settings.add_property_info({
        "name": SETTING_BUILD_TIMEOUT_SEC,
        "type": TYPE_INT,
        "hint": PROPERTY_HINT_RANGE,
        "hint_string": "1,86400,1",
    })
    if not settings.has_setting(SETTING_BUILD_ON_SAVE):
        settings.set_setting(SETTING_BUILD_ON_SAVE, true)
    settings.set_initial_value(SETTING_BUILD_ON_SAVE, true, false)
    settings.add_property_info({
        "name": SETTING_BUILD_ON_SAVE,
        "type": TYPE_BOOL,
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
    if not ProjectSettings.has_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS):
        ProjectSettings.set_setting(SETTING_AUTO_BUILD_DEBOUNCE_MS, 800)
    if not ProjectSettings.has_setting(SETTING_RELOAD_SCENE_AFTER_SYNC):
        ProjectSettings.set_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)
    if not ProjectSettings.has_setting(SETTING_DEVELOPER_MODE):
        ProjectSettings.set_setting(SETTING_DEVELOPER_MODE, false)
    if not ProjectSettings.has_setting(SETTING_JAVA_PREFLIGHT_ENABLED):
        ProjectSettings.set_setting(SETTING_JAVA_PREFLIGHT_ENABLED, true)
    if not ProjectSettings.has_setting(SETTING_EXPORT_GUARD_ENABLED):
        ProjectSettings.set_setting(SETTING_EXPORT_GUARD_ENABLED, true)
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
        "name": SETTING_EXPORT_GUARD_ENABLED,
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


## Editor > Editor Settings > Kanama > Build > Build On Save; on when the setting is absent.
static func is_build_on_save_enabled() -> bool:
    if not Engine.is_editor_hint():
        return false
    var settings := EditorInterface.get_editor_settings()
    if settings == null or not settings.has_setting(SETTING_BUILD_ON_SAVE):
        return true
    return bool(settings.get_setting(SETTING_BUILD_ON_SAVE))


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
    for root in _kt_scan_roots():
        _collect_kt_mtimes_recursive(root, result)
    return result


## The folders the watcher scans: the ones the script build compiles, not the whole project (the scan
## runs every KT_SCAN_INTERVAL_SEC). kotlin-src/ (or kanama/tools/kotlin_sources_dir when it names a
## folder in the project), and web/kotlin-src/ when it exists. Only a project that builds from a Kanama
## checkout with no Kotlin folder (installStarterTemplate puts HelloScript.kt at the root) is scanned
## from res://. Any other project, such as a plain one before Set Up Kanama Project, is not scanned.
func _kt_scan_roots() -> Array:
    var roots: Array = []
    var configured := String(ProjectSettings.get_setting(SETTING_KOTLIN_SOURCES_DIR, "")).strip_edges()
    if configured.begins_with("res://") and configured.trim_suffix("/") != "res:/" and DirAccess.dir_exists_absolute(configured):
        roots.append(configured.trim_suffix("/"))
    elif DirAccess.dir_exists_absolute("res://kotlin-src"):
        roots.append("res://kotlin-src")
    if DirAccess.dir_exists_absolute("res://web/kotlin-src") and not roots.has("res://web/kotlin-src"):
        roots.append("res://web/kotlin-src")
    if roots.is_empty() and _project_dir_with_gradle_wrapper().is_empty() and not _find_kanama_checkout().is_empty():
        roots.append("res://")
    return roots


# Folders the build-on-save watcher does not scan, the ones the script build does not compile:
# hidden folders at any depth (.godot, .gradle, .git, .idea), folders with a .gdignore (Godot does not
# see them either: the store addon's setup template in addons/kanama/templates holds a HelloScript.kt
# that is no script of the project), and at the project root Gradle's build/ (KSP rewrites the
# generated registrar .kt files there on every build, so watching it made each save build twice) and
# Godot's android/ build template. A build/ or android/ folder deeper in the sources is compiled, so
# it is watched.
static func _is_kt_scan_skipped_dir(parent_path: String, name: String) -> bool:
    if name.begins_with("."):
        return true
    if FileAccess.file_exists(parent_path.path_join(name).path_join(".gdignore")):
        return true
    return parent_path == "res://" and (name == "build" or name == "android")


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
            if not _is_kt_scan_skipped_dir(dir_path, name):
                _collect_kt_mtimes_recursive(child, sink)
        elif name.to_lower().ends_with(".kt"):
            sink[child] = FileAccess.get_modified_time(child)
    dir.list_dir_end()


## How Build Scripts builds this project: `buildScripts` of its own Gradle build, or `installAddonJar` of
## the Kanama checkout it was set up from. [] when it has neither (a plain project, or one with the store
## addon before Set Up Kanama Project).
func _script_build_steps() -> Array:
    var project_gradle_dir := _project_dir_with_gradle_wrapper()
    if not project_gradle_dir.is_empty():
        return [_gradle_step(project_gradle_dir, "buildScripts", [])]
    var checkout := _find_kanama_checkout()
    if not checkout.is_empty():
        return [_gradle_step(checkout, "installAddonJar", _current_project_install_args())]
    return []


## Why Build Scripts has nothing to run, for the light and the output dialog.
func _no_build_message() -> String:
    if FileAccess.file_exists("res://" + SETUP_TEMPLATE_DIR + "/build.gradle.kts"):
        return "[kanama:tools] This project has no Gradle build yet. Choose Project > Tools > %s: it adds the build, then Build Scripts builds your scripts." % MENU_SETUP_PROJECT
    return "[kanama:tools] This project has no Gradle build (no gradlew) and no Kanama checkout beside it ('%s' in Project Settings names one). Set it up as Add Kanama to an Existing Project describes." % SETTING_REPO_DIR


func _run_script_build() -> void:
    var steps := _script_build_steps()
    if steps.is_empty():
        # No build to start: the light shows why (red), and the output dialog says what to do.
        _build_label = SYNC_BUTTON_IDLE_TEXT
        _build_end_reason = ""
        _build_output = _no_build_message().to_utf8_buffer()
        _finish_build(-1)
        return
    _start_build(SYNC_BUTTON_IDLE_TEXT, steps, true)


func _run_gradle_task(task_name: String, extra_args: Array = [], repo_dir_override: String = "") -> void:
    var repo_dir := repo_dir_override if not repo_dir_override.is_empty() else _find_kanama_checkout()
    _start_build(task_name, [_gradle_step(repo_dir, task_name, extra_args)], _is_script_build_task(task_name))


static func _gradle_step(dir: String, task_name: String, args: Array) -> Dictionary:
    return {"dir": dir, "task": task_name, "args": args}


## Starts a build the toolbar shows: `label` names it on the status light and in the output dialog,
## `steps` are the Gradle runs (_gradle_step) it makes one after the other, stopping at the first that
## fails. The build runs beside the editor (the editor stays responsive and the light yellow) and
## _poll_build finishes it. `script_build` marks a build that rebuilds kanama-scripts.jar.
func _start_build(label: String, steps: Array, script_build: bool) -> void:
    if _is_build_running:
        push_warning("[kanama:tools] Build already running; skipping '%s'" % label)
        return
    _build_label = label
    _build_steps = steps.duplicate()
    _build_is_script_build = script_build
    _build_output = PackedByteArray()
    _build_fingerprint = sources_fingerprint(_collect_kt_mtimes())
    _build_started_msec = Time.get_ticks_msec()
    _build_end_reason = ""
    _set_build_buttons_busy(true)
    _refresh_build_status()
    _start_next_build_step()


func _start_next_build_step() -> void:
    var step: Dictionary = _build_steps.pop_front()
    var error := _spawn_gradle_step(String(step["dir"]), String(step["task"]), step["args"])
    if not error.is_empty():
        _append_build_output(error + "\n")
        _finish_build(-1)


## Starts one Gradle run; returns "" or why it could not start.
func _spawn_gradle_step(repo_dir: String, task_name: String, extra_args: Array) -> String:
    if repo_dir.is_empty():
        return "[kanama:tools] Could not find Kanama repo. Set '%s' in Project Settings." % SETTING_REPO_DIR

    var windows := OS.get_name() == "Windows"
    var gradlew := repo_dir.path_join("gradlew.bat" if windows else "gradlew")
    if not FileAccess.file_exists(gradlew):
        return "[kanama:tools] gradlew not found at %s" % gradlew

    # A desktop launcher does not hand Godot the shell's JAVA_HOME, so gradlew (and the CMake run
    # under it) used to fall back to whatever JDK the system defaulted to (kanama#277). Pick the
    # JDK here and refuse to start a build that cannot work.
    var jdk := resolve_build_jdk_from_environment()
    if not bool(jdk.get("ok", false)):
        return String(jdk.get("message", ""))
    print("[kanama:tools] Build JDK: %s (JDK %s, from %s)" % [jdk["home"], jdk["version"], jdk["source"]])

    var args := ["-p", repo_dir, task_name]
    args.append_array(extra_args)
    var program := gradlew
    if windows:
        # gradlew.bat would run under cmd.exe, which expands %...% in the arguments (a project path with
        # a % in it) and whose death leaves the Gradle java client running. What gradlew.bat runs is the
        # wrapper's main class on the JDK: start that directly, so the process is the client itself.
        program = String(jdk["home"]).path_join("bin/java.exe")
        var wrapper_jar := repo_dir.path_join("gradle/wrapper/gradle-wrapper.jar")
        if not FileAccess.file_exists(wrapper_jar):
            return "[kanama:tools] the Gradle wrapper is incomplete: %s is missing" % wrapper_jar
        var java_args := ["-Xmx64m", "-Xms64m", "-classpath", wrapper_jar, "org.gradle.wrapper.GradleWrapperMain"]
        java_args.append_array(args)
        args = java_args
    var command := "%s %s" % [program, " ".join(args)]
    print("[kanama:tools] Running: %s" % command)
    _append_build_output("$ %s\n" % command)
    var process := spawn_with_java_home(String(jdk["home"]), program, args)
    if process.is_empty():
        return "[kanama:tools] Could not start %s" % program
    _build_pid = int(process["pid"])
    _build_stdio = process["stdio"]
    _build_stderr = process["stderr"]
    return ""


## Called every frame: reads the running step's output (a full pipe would stall Gradle) and, once
## the step has exited, starts the next one or finishes the build.
func _poll_build() -> void:
    if _build_pid < 0:
        return
    _drain_build_pipes()
    if OS.is_process_running(_build_pid):
        var limit_sec := build_timeout_sec()
        if Time.get_ticks_msec() - _build_started_msec > limit_sec * 1000:
            _end_build("timed out", "[kanama:tools] %s timed out after %d s (Editor Settings > Kanama > Build > Build Timeout Sec) and was stopped." % [_build_label, limit_sec])
        return
    _drain_build_pipes()
    var code := OS.get_process_exit_code(_build_pid)
    _close_build_pipes()
    _build_pid = -1
    if code == 0 and not _build_steps.is_empty():
        _start_next_build_step()
        return
    _finish_build(code)


func _drain_build_pipes() -> void:
    for pipe in [_build_stdio, _build_stderr]:
        if pipe == null:
            continue
        while true:
            var chunk: PackedByteArray = pipe.get_buffer(65536)
            if chunk.is_empty():
                break
            _build_output.append_array(chunk)


func _close_build_pipes() -> void:
    if _build_stdio != null:
        _build_stdio.close()
    if _build_stderr != null:
        _build_stderr.close()
    _build_stdio = null
    _build_stderr = null


func _append_build_output(text: String) -> void:
    _build_output.append_array(text.to_utf8_buffer())


## `code` is the last step's exit status, -1 when a step could not start.
func _finish_build(code: int) -> void:
    _build_steps.clear()
    var output := _build_output.get_string_from_utf8()
    _last_build = {
        "label": _build_label,
        "ok": code == 0,
        "exit": code,
        "output": output,
        "finished": Time.get_datetime_string_from_system(false, true),
    }
    _last_build["reason"] = _build_end_reason
    _set_build_buttons_busy(false)
    if _build_end_reason == "cancelled":
        # A cancelled build says nothing about the scripts: the light goes back to what the files say.
        print("[kanama:tools] %s cancelled." % _build_label)
    elif code == 0:
        _last_build_failed = false
        print("[kanama:tools] %s succeeded." % _build_label)
        if _build_is_script_build:
            write_build_stamp(ProjectSettings.globalize_path("res://"), _build_fingerprint)
            _check_addon_version()
            if bool(ProjectSettings.get_setting(SETTING_RELOAD_SCENE_AFTER_SYNC, true)):
                _queue_reload_edited_scene()
    else:
        _last_build_failed = true
        push_error("[kanama:tools] %s failed (exit=%d)\n%s" % [_build_label, code, output])
    _refresh_build_status()


## Ends a running build when the plugin leaves (the editor quits or the plugin is turned off), so no
## Gradle client outlives the editor.
func _stop_running_build() -> void:
    if _build_pid >= 0 and OS.is_process_running(_build_pid):
        OS.kill(_build_pid)
    _close_build_pipes()
    _build_pid = -1
    _build_steps.clear()
    _is_build_running = false


## Stops the running build (the output dialog's Cancel Build, or the timeout) and finishes it with
## `reason`. On Windows the process is the Gradle client itself (see _spawn_gradle_step), so killing it
## orphans nothing; elsewhere gradlew execs java, so its pid is the client too.
func _end_build(reason: String, message: String) -> void:
    if _build_pid < 0:
        return
    _drain_build_pipes()
    OS.kill(_build_pid)
    _close_build_pipes()
    _build_pid = -1
    _build_steps.clear()
    _build_end_reason = reason
    _append_build_output("\n" + message + "\n")
    _finish_build(-1)


func _on_cancel_build_pressed() -> void:
    _end_build("cancelled", "[kanama:tools] %s was cancelled." % _build_label)
    _show_last_build_output()


static func build_timeout_sec() -> int:
    if not Engine.is_editor_hint():
        return DEFAULT_BUILD_TIMEOUT_SEC
    var settings := EditorInterface.get_editor_settings()
    if settings == null or not settings.has_setting(SETTING_BUILD_TIMEOUT_SEC):
        return DEFAULT_BUILD_TIMEOUT_SEC
    return maxi(1, int(settings.get_setting(SETTING_BUILD_TIMEOUT_SEC)))


## The status light's state (pure, so the smoke can hold every case): see the STATUS_* constants.
## `sources_built` says the last successful script build compiled the .kt files as they are now.
## `addon_mismatch`: kanama-scripts.jar was built for another Kanama version than the addon (the
## version lock): yellow until a build for the addon, the same as a changed .kt file.
static func build_status(building: bool, last_build_failed: bool, has_sources: bool, jar_exists: bool, sources_built: bool, addon_mismatch: bool = false) -> String:
    if building:
        return STATUS_BUILDING
    if last_build_failed:
        return STATUS_FAILED
    if not has_sources and not jar_exists:
        return STATUS_NONE
    if jar_exists and sources_built and not addon_mismatch:
        return STATUS_CURRENT
    return STATUS_STALE


## One value for the set of .kt files and their modification times.
static func sources_fingerprint(kt_mtimes: Dictionary) -> String:
    var paths := kt_mtimes.keys()
    paths.sort()
    var lines := PackedStringArray()
    for path in paths:
        lines.append("%s %d" % [path, int(kt_mtimes[path])])
    return "\n".join(lines).md5_text()


static func read_build_stamp(project_dir: String) -> String:
    var stamp_path := project_dir.path_join(".godot").path_join(BUILD_STAMP_FILE)
    if not FileAccess.file_exists(stamp_path):
        return ""
    return FileAccess.get_file_as_string(stamp_path).strip_edges()


static func write_build_stamp(project_dir: String, fingerprint: String) -> void:
    var godot_dir := project_dir.path_join(".godot")
    DirAccess.make_dir_recursive_absolute(godot_dir)
    var file := FileAccess.open(godot_dir.path_join(BUILD_STAMP_FILE), FileAccess.WRITE)
    if file == null:
        push_warning("[kanama:tools] Could not write %s" % godot_dir.path_join(BUILD_STAMP_FILE))
        return
    file.store_string(fingerprint + "\n")
    file.close()


## Whether kanama-scripts.jar holds the .kt files as they are now: the stamp of the last successful
## build in this checkout says so; without one (scripts built in a terminal, or before this plugin
## kept stamps) the jar must be newer than every .kt file.
##
## The stamp counts only while the jar is not newer than it: a `./gradlew buildScripts` in a terminal or
## IntelliJ installs a new jar without a stamp, and then the jar's time decides.
func _sources_built() -> bool:
    if not FileAccess.file_exists(SCRIPTS_JAR):
        return false
    var jar_time := FileAccess.get_modified_time(SCRIPTS_JAR)
    var project_dir := ProjectSettings.globalize_path("res://")
    var stamp := read_build_stamp(project_dir)
    var stamp_path := project_dir.path_join(".godot").path_join(BUILD_STAMP_FILE)
    if not stamp.is_empty() and jar_time <= FileAccess.get_modified_time(stamp_path):
        return stamp == sources_fingerprint(_known_kt_mtimes)
    for path in _known_kt_mtimes.keys():
        if int(_known_kt_mtimes[path]) > jar_time:
            return false
    return true


func _addon_mismatch_message() -> String:
    if not FileAccess.file_exists(ADDON_VERSION_FILE) or not FileAccess.file_exists(SCRIPTS_VERSION_FILE):
        return ""
    return addon_version_mismatch(FileAccess.get_file_as_string(ADDON_VERSION_FILE), FileAccess.get_file_as_string(SCRIPTS_VERSION_FILE))


func _refresh_build_status() -> void:
    _status = build_status(_is_build_running, _last_build_failed, not _known_kt_mtimes.is_empty(), FileAccess.file_exists(SCRIPTS_JAR), _sources_built(), not _addon_mismatch_message().is_empty())
    if _status_button == null:
        return
    _status_button.set_meta("kanama_status", _status)
    _status_button.add_theme_color_override("icon_normal_color", status_color(_status))
    _status_button.add_theme_color_override("icon_hover_color", status_color(_status))
    _status_button.add_theme_color_override("icon_pressed_color", status_color(_status))
    _status_button.add_theme_color_override("icon_focus_color", status_color(_status))
    _status_button.tooltip_text = _status_tooltip(_status)


## The state of the status light, for tests and tools.
func get_build_status() -> String:
    return _status


static func status_color(status: String) -> Color:
    var theme: Theme = EditorInterface.get_editor_theme() if Engine.is_editor_hint() else null
    var name := ""
    var fallback := Color(0.6, 0.6, 0.6)
    match status:
        STATUS_CURRENT:
            name = "success_color"
            fallback = Color(0.45, 0.95, 0.5)
        STATUS_STALE, STATUS_BUILDING:
            name = "warning_color"
            fallback = Color(1.0, 0.87, 0.4)
        STATUS_FAILED:
            name = "error_color"
            fallback = Color(1.0, 0.47, 0.42)
    if name.is_empty() or theme == null or not theme.has_color(name, "Editor"):
        return fallback
    return theme.get_color(name, "Editor")


func _status_tooltip(status: String) -> String:
    match status:
        STATUS_CURRENT:
            return "Kotlin scripts are built and current. Click for the last build's output."
        STATUS_BUILDING:
            return "Building: %s. Click for the output so far." % _build_label
        STATUS_FAILED:
            if String(_last_build.get("reason", "")) == "timed out":
                return "The last build timed out and was stopped. Click for its output."
            return "The last build failed. Click for its output."
        STATUS_STALE:
            var mismatch := _addon_mismatch_message()
            if not mismatch.is_empty():
                return mismatch.trim_prefix("Kanama: ") + " Build Scripts does the same."
            if _script_build_steps().is_empty():
                return _no_build_message().trim_prefix("[kanama:tools] ")
            if is_build_on_save_enabled():
                return "Kotlin scripts changed since the last build. Build on save builds them; Build Scripts builds them now."
            return "Kotlin scripts changed since the last build. Press Build Scripts."
    if FileAccess.file_exists("res://" + SETUP_TEMPLATE_DIR + "/build.gradle.kts") and _project_dir_with_gradle_wrapper().is_empty():
        return "No Kotlin scripts yet. Project > Tools > %s adds a Gradle build and a starter script." % MENU_SETUP_PROJECT
    return "No Kotlin scripts yet."


## A white disc the status button tints with status_color.
static func _status_light_texture() -> Texture2D:
    var size := 16
    var image := Image.create(size, size, false, Image.FORMAT_RGBA8)
    var center := Vector2(size / 2.0, size / 2.0)
    var radius := 5.5
    for y in size:
        for x in size:
            var distance := Vector2(x + 0.5, y + 0.5).distance_to(center)
            var alpha := clampf(radius + 0.5 - distance, 0.0, 1.0)
            image.set_pixel(x, y, Color(1, 1, 1, alpha))
    return ImageTexture.create_from_image(image)


func _show_last_build_output() -> void:
    var text := ""
    if _is_build_running:
        text = "%s is running.\n\n%s" % [_build_label, _build_output.get_string_from_utf8()]
    elif _last_build.is_empty():
        text = "No build has run since the editor started.\n\n%s" % _status_tooltip(_status)
    else:
        var result := "succeeded" if bool(_last_build["ok"]) else "failed (exit %d)" % int(_last_build["exit"])
        if not String(_last_build.get("reason", "")).is_empty():
            result = String(_last_build["reason"])
        text = "%s %s at %s.\n\n%s" % [_last_build["label"], result, _last_build["finished"], _last_build["output"]]
    if _output_dialog == null:
        var base_control := get_editor_interface().get_base_control()
        if base_control == null:
            return
        _output_dialog = AcceptDialog.new()
        _output_dialog.name = "KanamaBuildOutput"
        _output_dialog.title = "Kanama Build Output"
        _output_dialog.min_size = Vector2i(720, 420)
        var output_text := TextEdit.new()
        output_text.name = "Output"
        output_text.editable = false
        output_text.size_flags_vertical = Control.SIZE_EXPAND_FILL
        var editor_theme := EditorInterface.get_editor_theme()
        if editor_theme != null and editor_theme.has_font("source", "EditorFonts"):
            output_text.add_theme_font_override("font", editor_theme.get_font("source", "EditorFonts"))
        _output_dialog.add_child(output_text)
        # Ends a build that hangs (Gradle waiting on a lock or the network); the timeout does the same.
        var cancel := _output_dialog.add_button("Cancel Build", true, "cancel_build")
        cancel.name = "CancelBuild"
        _output_dialog.custom_action.connect(func(action: StringName) -> void:
            if action == &"cancel_build":
                _on_cancel_build_pressed())
        base_control.add_child(_output_dialog)
    var text_edit := _output_dialog.get_node("Output") as TextEdit
    var cancel_button := _output_dialog.find_child("CancelBuild", true, false) as Button
    if cancel_button != null:
        cancel_button.visible = _is_build_running
    text_edit.text = text
    text_edit.scroll_vertical = text_edit.get_line_count()
    _output_dialog.popup_centered()


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


## Godot starts a process with no environment parameter, so JAVA_HOME is set on the editor process
## around the start of the build (the child takes its environment then) and the previous value, or
## its absence, is restored right after. Nothing else runs between the two. Returns
## OS.execute_with_pipe's {"stdio", "stderr", "pid"}, with pipes that never block, or {} when the
## process could not start. The build runs beside the editor, so the editor stays responsive and the
## status light can show it.
static func spawn_with_java_home(java_home: String, path: String, args: Array) -> Dictionary:
    var had_previous := OS.has_environment("JAVA_HOME")
    var previous := OS.get_environment("JAVA_HOME")
    OS.set_environment("JAVA_HOME", java_home)
    var process := OS.execute_with_pipe(path, PackedStringArray(args), false)
    if had_previous:
        OS.set_environment("JAVA_HOME", previous)
    else:
        OS.unset_environment("JAVA_HOME")
    return process


func _set_build_buttons_busy(busy: bool) -> void:
    _is_build_running = busy
    if _sync_button != null:
        _sync_button.disabled = busy
        _sync_button.text = SYNC_BUTTON_BUSY_TEXT if busy and _build_label == SYNC_BUTTON_IDLE_TEXT else SYNC_BUTTON_IDLE_TEXT
    if _release_button != null:
        _release_button.disabled = busy
    if _platform_button != null:
        _platform_button.disabled = busy
    if _jar_button != null:
        _jar_button.disabled = busy
        _jar_button.text = JAR_BUTTON_BUSY_TEXT if busy and _build_label == "jar" else JAR_BUTTON_IDLE_TEXT


func _project_dir_with_gradle_wrapper() -> String:
    var project_dir := ProjectSettings.globalize_path("res://")
    var gradlew := project_dir.path_join("gradlew")
    if OS.get_name() == "Windows":
        gradlew += ".bat"
    if FileAccess.file_exists(gradlew):
        return project_dir
    return ""


## The Kanama checkout the project was set up from (kanama/tools/repo_dir, else ../, ../kanama or
## ../../kanama), or "". Only a real Kanama checkout counts (is_kanama_checkout): a project inside some
## other Gradle repository must never run installAddonJar there, and the platform commands exist only in
## a checkout.
func _find_kanama_checkout() -> String:
    var candidates: Array = []
    var configured := String(ProjectSettings.get_setting(SETTING_REPO_DIR, "")).strip_edges()
    if not configured.is_empty():
        candidates.append(configured)
    var project_dir := ProjectSettings.globalize_path("res://")
    candidates.append_array([project_dir.path_join(".."), project_dir.path_join("../kanama"), project_dir.path_join("../../kanama")])
    for candidate in candidates:
        if is_kanama_checkout(String(candidate)):
            return String(candidate).simplify_path()
    return ""


static func is_kanama_checkout(dir: String) -> bool:
    return FileAccess.file_exists(dir.path_join("gradlew")) \
        and DirAccess.dir_exists_absolute(dir.path_join("bootstrap")) \
        and DirAccess.dir_exists_absolute(dir.path_join("web-runtime")) \
        and DirAccess.dir_exists_absolute(dir.path_join("templates/starter"))


## The PLATFORM_BUILDS entries this project can run, each with its "menu_text" and Gradle "steps".
func _available_platform_builds() -> Array:
    var project_dir := ProjectSettings.globalize_path("res://")
    var scripts_dir := project_dir.path_join("kotlin-src") if DirAccess.dir_exists_absolute(project_dir.path_join("kotlin-src")) else project_dir
    var plugin_dir := _project_dir_with_gradle_wrapper()
    if not plugin_dir.is_empty() and not applies_kanama_gradle_plugin(FileAccess.get_file_as_string(plugin_dir.path_join("build.gradle.kts"))):
        plugin_dir = ""
    return platform_builds_for(OS.get_name(), plugin_dir, _find_kanama_checkout(), project_dir.trim_suffix("/"), scripts_dir.trim_suffix("/"))


## Whether a build.gradle.kts applies the Kanama Gradle plugin (as the release kit's and the store
## addon's template do), whose tasks the Platforms menu's "project_task" entries run.
static func applies_kanama_gradle_plugin(build_script: String) -> bool:
    for raw in build_script.split("\n"):
        var line := raw.strip_edges()
        if line.begins_with("//"):
            continue
        if line.contains("id(\"net.multigesture.kanama\")"):
            return true
    return false


## Pure: which platform builds `os_name` can run, from the Kanama Gradle plugin in the project's own
## build (`project_gradle_dir`, "" when the project has no build that applies it) or a Kanama checkout
## (`checkout_dir`, "" when none).
static func platform_builds_for(os_name: String, project_gradle_dir: String, checkout_dir: String, project_dir: String, scripts_dir: String) -> Array:
    var result: Array = []
    for entry in PLATFORM_BUILDS:
        var only_on: Array = entry["os"]
        if not only_on.is_empty() and not only_on.has(os_name):
            continue
        var steps: Array = []
        var from := ""
        var script_build := false
        if not String(entry["project_task"]).is_empty() and not project_gradle_dir.is_empty():
            steps.append(_gradle_step(project_gradle_dir, String(entry["project_task"]), []))
            from = "project"
        elif not checkout_dir.is_empty():
            script_build = bool(entry["script_build"])
            for command in entry["checkout_tasks"]:
                var args: Array = []
                for i in range(1, command.size()):
                    args.append(String(command[i]).replace("{project}", project_dir).replace("{scripts}", scripts_dir))
                steps.append(_gradle_step(checkout_dir, String(command[0]), args))
            from = "checkout"
        if steps.is_empty():
            continue
        var tasks := PackedStringArray()
        for step in steps:
            tasks.append(String(step["task"]))
        result.append({
            "id": entry["id"],
            "label": "%s build" % entry["label"],
            "menu_text": "%s (%s)" % [entry["label"], ", ".join(tasks)],
            "steps": steps,
            "from": from,
            "script_build": script_build,
        })
    return result


func _on_setup_project_pressed() -> void:
    var result := setup_kanama_project(ProjectSettings.globalize_path("res://"), OS.get_name())
    var created: Array = result["created"]
    var lines := PackedStringArray()
    if not bool(result["ok"]):
        lines.append(String(result["message"]))
        push_error("[kanama:tools] %s" % String(result["message"]))
    elif created.is_empty():
        lines.append("Nothing to add: this project already has every Kanama file. No file was changed.")
    else:
        # The build that had nothing to run before the setup has something now.
        _last_build_failed = false
        lines.append("Added to this project:")
        for path in created:
            lines.append("  " + String(path))
        lines.append("")
        lines.append("Existing files were kept. Press Build Scripts to build your Kotlin scripts.")
    var message := "\n".join(lines)
    print("[kanama:tools] Set Up Kanama Project: %s" % message.replace("\n", " "))
    var filesystem := get_editor_interface().get_resource_filesystem()
    if filesystem != null:
        filesystem.scan()
    # The files the setup added are not a save to build: Build Scripts does that when asked.
    _known_kt_mtimes = _collect_kt_mtimes()
    _refresh_build_status()
    _refresh_optional_buttons()
    _show_setup_result(message)


func _show_setup_result(message: String) -> void:
    var base_control := get_editor_interface().get_base_control()
    if base_control == null:
        return
    var dialog := AcceptDialog.new()
    dialog.name = "KanamaSetupResult"
    dialog.title = "Set Up Kanama Project"
    dialog.dialog_text = message
    base_control.add_child(dialog)
    dialog.confirmed.connect(dialog.queue_free)
    dialog.close_requested.connect(dialog.queue_free)
    dialog.popup_centered()


## "Make this a Kanama project": runs the store addon's setup script (addons/kanama/
## setup-kanama-project.sh, or .ps1 on Windows; the one copy of the setup logic) without --force, so
## it adds only what is missing. Returns {"ok", "created": the project paths it added, "message",
## "output"}.
static func setup_kanama_project(project_dir: String, os_name: String) -> Dictionary:
    var dir := project_dir.trim_suffix("/")
    if not DirAccess.dir_exists_absolute(dir.path_join(SETUP_TEMPLATE_DIR)):
        var message := "This needs the Kanama store addon: %s is missing. Unzip the store addon into this project and try again." % SETUP_TEMPLATE_DIR
        if FileAccess.file_exists(dir.path_join("gradlew")) and FileAccess.file_exists(dir.path_join("build.gradle.kts")):
            message = "This project already has a Gradle build, and its addon carries no setup template (a release kit or starter project is set up already)."
        return {"ok": false, "created": [], "message": message, "output": ""}
    var exe := "sh"
    var args := PackedStringArray([dir.path_join(SETUP_SCRIPT_SH), "--project-dir", dir])
    if os_name == "Windows":
        exe = "powershell.exe"
        args = PackedStringArray(["-NoProfile", "-ExecutionPolicy", "Bypass", "-File", dir.path_join(SETUP_SCRIPT_PS1), "-ProjectDir", dir])
    var output: Array = []
    var code := OS.execute(exe, args, output, true)
    var text := "".join(output)
    if code != 0:
        return {"ok": false, "created": [], "message": "The setup script failed (exit %d):\n%s" % [code, text.strip_edges()], "output": text}
    return {"ok": true, "created": parse_setup_output(text), "message": "", "output": text}


## The paths the setup script reports adding: its `[kanama] wrote <path>`, `[kanama] created <path>`,
## `[kanama] merged <path>` and `[kanama] registered <path>` lines.
static func parse_setup_output(text: String) -> Array:
    var created: Array = []
    for raw in text.split("\n"):
        var line := raw.strip_edges()
        for prefix in ["[kanama] wrote ", "[kanama] created ", "[kanama] registered ", "[kanama] merged "]:
            if line.begins_with(prefix):
                created.append(line.substr(prefix.length()).strip_edges())
    return created


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
