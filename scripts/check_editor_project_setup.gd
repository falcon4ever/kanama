@tool
extends EditorPlugin
## check_editor_project_setup.gd: checks, inside a running editor, the Kanama Tools menu action
## Project > Tools > Kanama Tools: Set Up Kanama Project (addons/kanama_tools/plugin.gd,
## _on_setup_project_pressed) on a plain Godot project with the store addon unzipped into addons/ and
## no Kanama files. The first run must add exactly the missing files, keep the user's own
## settings.gradle.kts byte for byte, and list what it added; a second run must add nothing and
## change no file. Prints `[project_setup] PASS` and quits with 0, or each FAIL and quits with 1.
##
## Runs as an editor plugin beside Kanama Tools in the scratch project scripts/tool_smoke.sh lays out
## (the store addon's addons/kanama shape: templates/release-kit, the setup scripts, the runtime).
const TAG := "[project_setup]"
const KANAMA_PLUGIN := "res://addons/kanama_tools/plugin.gd"
const USER_SETTINGS := "// the user's own settings.gradle.kts\nrootProject.name = \"mine\"\n"
# A file of the project's own that the template also has: the setup must keep it byte for byte.
const USER_FILE := "gradlew.bat"
const USER_FILE_TEXT := "@rem the user's own gradlew.bat\n"
# What the store addon's setup adds to a project that has its own settings.gradle.kts (no
# pluginManagement block) and gradlew.bat: the settings file is merged, never replaced.
const EXPECTED := ["build.gradle.kts", "gradlew", "gradle", "kotlin-src/", "kotlin-src/HelloScript.kt", "build/.gdignore"]
const MERGED_LINE := "settings.gradle.kts (added the Kanama pluginManagement block)"
const TIMEOUT_SEC := 420.0

var _failures := PackedStringArray()
var _done := false


func _enter_tree() -> void:
    _run.call_deferred()


func _run() -> void:
    get_tree().create_timer(TIMEOUT_SEC).timeout.connect(func() -> void:
        if not _done:
            _failures.append("the check did not finish within %d s" % int(TIMEOUT_SEC))
            _finish())
    var kanama: EditorPlugin = null
    for i in 200:
        kanama = _find_kanama_plugin()
        if kanama != null:
            break
        await get_tree().process_frame
    if kanama == null:
        _failures.append("the Kanama Tools plugin is not loaded")
        _finish()
        return
    print("%s the Kanama Tools plugin is loaded; checking the project before the setup" % TAG)
    var project := ProjectSettings.globalize_path("res://").trim_suffix("/")
    _write(project.path_join(USER_FILE), USER_FILE_TEXT)
    _expect(FileAccess.get_file_as_string(project.path_join("settings.gradle.kts")) == USER_SETTINGS, "the scratch project does not start with the user's settings.gradle.kts")
    _expect(kanama.get_build_status() == "none", "the status light of a project with no Kotlin scripts is %s, expected none" % kanama.get_build_status())
    _expect(_find_control("KanamaPlatformBuilds") == null, "the Platforms menu is shown before the project has a Gradle build")

    # Build Scripts before the setup: nothing to build with; the light turns red and says what to do.
    (_find_control("KanamaBuildScripts") as Button).pressed.emit()
    await get_tree().process_frame
    _expect(kanama.get_build_status() == "failed", "Build Scripts before the setup left the light %s, expected failed" % kanama.get_build_status())
    _expect(String(kanama._last_build.get("output", "")).contains("Set Up Kanama Project"), "Build Scripts before the setup does not point at Set Up Kanama Project: %s" % kanama._last_build.get("output", ""))

    # First run, through the menu action's handler: only the missing files.
    var before := _snapshot(project)
    print("%s running Set Up Kanama Project" % TAG)
    kanama._on_setup_project_pressed()
    print("%s Set Up Kanama Project returned" % TAG)
    await get_tree().process_frame
    var dialog := _find_control("KanamaSetupResult") as AcceptDialog
    _expect(dialog != null and dialog.visible, "Set Up Kanama Project shows no result dialog")
    var listed := dialog.dialog_text if dialog != null else ""
    var listed_lines := PackedStringArray()
    for line in listed.split("\n"):
        listed_lines.append(line.strip_edges())
    for path in EXPECTED:
        _expect(listed_lines.has(path), "the setup result does not list %s:\n%s" % [path, listed])
        _expect(FileAccess.file_exists(project.path_join(path)) or DirAccess.dir_exists_absolute(project.path_join(path)), "the setup did not add %s" % path)
    _expect(listed_lines.has(MERGED_LINE), "the setup result does not list the merged settings.gradle.kts:\n%s" % listed)
    _expect(not listed_lines.has(USER_FILE), "the setup lists %s, which the project already had:\n%s" % [USER_FILE, listed])
    _expect(FileAccess.get_file_as_string(project.path_join(USER_FILE)) == USER_FILE_TEXT, "the setup overwrote the project's own %s" % USER_FILE)
    var settings := FileAccess.get_file_as_string(project.path_join("settings.gradle.kts"))
    _expect(settings.begins_with("pluginManagement {") and settings.ends_with(USER_SETTINGS) and settings.contains("addons/kanama/kanama.version"),
        "settings.gradle.kts is not the Kanama pluginManagement block followed by the user's lines:\n%s" % settings)
    var after := _snapshot(project)
    for path in before.keys():
        if path != "settings.gradle.kts":
            _expect(after.has(path) and after[path] == before[path], "the setup changed the existing file %s" % path)
    if dialog != null:
        dialog.queue_free()
    # The added HelloScript.kt is not a save: no build starts by itself. The toolbar follows the new
    # Gradle build without a restart: it applies the Kanama Gradle plugin, so Platforms has Android.
    await get_tree().create_timer(2.0).timeout
    _expect(kanama.get_build_status() == "stale", "after the setup the status light is %s, expected stale (scripts not built yet)" % kanama.get_build_status())
    var platforms := _find_control("KanamaPlatformBuilds") as MenuButton
    _expect(platforms != null and platforms.get_popup().item_count == 1 and platforms.get_popup().get_item_text(0).contains("buildAndroidScripts"),
        "after the setup the Platforms menu does not offer the project's buildAndroidScripts")

    # The project builds: the merged settings resolve the Kanama Gradle plugin from the addon.
    print("%s pressing Build Scripts" % TAG)
    (_find_control("KanamaBuildScripts") as Button).pressed.emit()
    await get_tree().process_frame
    var deadline := Time.get_ticks_msec() + 360000
    while kanama.get_build_status() == "building" and Time.get_ticks_msec() < deadline:
        await get_tree().process_frame
    print("%s Build Scripts ended: %s" % [TAG, kanama.get_build_status()])
    _expect(kanama.get_build_status() == "current", "Build Scripts after the setup ended %s, expected current:\n%s" % [kanama.get_build_status(), String(kanama._last_build.get("output", "")).right(3000)])
    var after_build := _snapshot(project)

    # Second run: nothing to add, nothing changed.
    var second: Dictionary = kanama.setup_kanama_project(project, OS.get_name())
    _expect(bool(second["ok"]), "the second setup run failed: %s" % second["message"])
    _expect((second["created"] as Array).is_empty(), "the second setup run added %s" % [second["created"]])
    var again := _snapshot(project)
    for path in after_build.keys():
        _expect(again.has(path) and again[path] == after_build[path], "the second setup run changed %s" % path)
    _expect(again.size() == after_build.size(), "the second setup run added files (%d before, %d after)" % [after_build.size(), again.size()])
    _finish()


## path relative to the project -> md5 of its content, for every file outside .godot/, addons/, Gradle's
## folders and build/'s subfolders.
func _snapshot(project: String) -> Dictionary:
    var result := {}
    _snapshot_dir(project, "", result)
    return result


func _snapshot_dir(root: String, relative: String, sink: Dictionary) -> void:
    var dir := DirAccess.open(root.path_join(relative))
    if dir == null:
        return
    dir.include_hidden = true
    for sub in dir.get_directories():
        # Gradle's own folders and the build's outputs are not the setup's business; build/.gdignore is.
        if relative.is_empty() and sub in [".godot", "addons", ".gradle", ".kotlin"]:
            continue
        if relative == "build":
            continue
        _snapshot_dir(root, relative.path_join(sub), sink)
    for file in dir.get_files():
        var path := relative.path_join(file)
        sink[path] = FileAccess.get_md5(root.path_join(path))


func _write(path: String, text: String) -> void:
    var file := FileAccess.open(path, FileAccess.WRITE)
    if file == null:
        _failures.append("cannot write %s" % path)
        return
    file.store_string(text)
    file.close()


func _find_kanama_plugin() -> EditorPlugin:
    for node in get_parent().get_children():
        var script: Script = node.get_script()
        if node is EditorPlugin and script != null and script.resource_path == KANAMA_PLUGIN:
            return node
    return null


func _find_control(node_name: String) -> Node:
    return EditorInterface.get_base_control().get_tree().root.find_child(node_name, true, false)


func _expect(ok: bool, failure: String) -> void:
    if not ok:
        _failures.append(failure)


func _finish() -> void:
    if _done:
        return
    _done = true
    if _failures.is_empty():
        print("%s PASS" % TAG)
        get_tree().quit(0)
        return
    for failure in _failures:
        printerr("%s FAIL: %s" % [TAG, failure])
    get_tree().quit(1)
