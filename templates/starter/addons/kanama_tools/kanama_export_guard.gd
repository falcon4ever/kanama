@tool
extends EditorExportPlugin
## Stops an export that would ship scenes the editor could not bind to their Kotlin scripts.
##
## Godot instantiates and re-packs every scene when it converts text resources to binary, and keeps
## only the properties the script instance reports. When kanama-scripts.jar has no class for a
## project `.kt` script, the script reports none and every scene-stored value is dropped; when the
## desktop addon is missing, the editor has no `.kt` loader at all, leaves the `.kt` files out of the
## export and ships the scenes that use them unconverted and unchecked. Godot reports neither as an
## export failure. GDScript projects cannot reach that state, so a Kanama project must not either.
##
## _export_begin runs when the export begins. It checks the `.kt` scripts that the scenes and
## resources this preset exports attach, and the `.kt` autoloads (a helper `.kt` that nothing
## attaches is not a script class and is not checked). A script binds when the editor loads it as a
## Script that can instantiate; a missing loader makes `load` return null, a script missing from the
## jar loads unbound (`can_instantiate()` is false).
##
## Godot 4.7 gives an export plugin no way to fail an export: an error message only marks it failed
## in the export dialog, and the platform exporters (Android, iOS, macOS, PCK/ZIP) finish and exit 0
## whatever a plugin reports. So, when a script does not bind:
## - a command-line export (`--export-release`, `--export-debug`, `--export-pack`, `--export-patch`)
##   prints the message and ends the editor process in _export_begin, so the exit status is non-zero
##   and the exporter writes nothing after that point. Most exporters run _export_begin before they
##   write any file; the Windows exporter copies the template's DLLs into the export folder first;
## - an editor (GUI) export reports the message as an export error ("Failed" in the export dialog)
##   and is otherwise left exactly as Godot produces it. An Android or iOS one-click deploy (Remote
##   Deploy), which Godot exports under a fixed name in the editor's temp folder and installs right
##   after, has that temporary export removed in _export_end, so nothing reaches the device and the
##   install step fails.
## Every message starts with the stable line `Kanama: Export stopped`.
##
## The same stop applies to an Android Gradle export whose preset sets Min SDK below 33 or leaves it empty.
## Kanama's Android plugin declares minSdk 33 (Android 13; ANDROID_MIN_SDK), so Godot's Gradle build would
## fail later with a manifest-merger error that does not say what to change. The Export dialog also shows
## the warning on the Min SDK field itself (_get_export_option_warning).
##
## Opt out with the project setting `kanama/tools/export_guard_enabled` (default true).

const SETTING_ENABLED := "kanama/tools/export_guard_enabled"
const CATEGORY := "Kanama"
const STOP_LINE := "Kanama: Export stopped"
const MAX_LISTED := 10
const RESOURCE_EXTENSIONS := ["tscn", "scn", "tres", "res"]
const TEXT_RESOURCE_EXTENSIONS := ["tscn", "tres"]
const KOTLIN_EXTENSION := "kt"
const SCRIPT_CLASS := "KanamaScript"
const COMMAND_LINE_EXPORT_ARGS := ["--export-release", "--export-debug", "--export-pack", "--export-patch"]
## The names Godot gives a one-click deploy's temporary export, relative to the editor's temp folder:
## Android `tmpexport.<unix time>.apk` (platform/android/export/export_plugin.cpp, run()), iOS
## `tmpexport.<unix time>/export.ipa` (editor/export/editor_export_platform_apple_embedded.cpp, run()).
const ONE_CLICK_EXPORT_PATTERN := "^tmpexport\\.[0-9]+(\\.apk|/export\\.ipa)$"

## Kanama's Android minimum (Android 13). Keep it equal to minSdk in android/godot-plugin/plugin/build.gradle.kts
## and android/godot-plugin/scripts/build.gradle.kts: release builds crash at startup below it (PanamaPort).
const ANDROID_MIN_SDK := 33
const ANDROID_OS_NAME := "Android"
const OPTION_GRADLE_BUILD := "gradle_build/use_gradle_build"
const OPTION_MIN_SDK := "gradle_build/min_sdk"

const REASON_NO_LOADER := "no .kt loader: the desktop addon (addons/kanama) is missing or did not load, so the export would leave the script out and could not convert or check the scenes that use it"
const REASON_NOT_BOUND := "not in kanama-scripts.jar: the scenes that use it would be exported without its property values"

var _discard_on_end := ""


func _get_name() -> String:
    return "KanamaExportGuard"


func _export_begin(_features: PackedStringArray, _is_debug: bool, path: String, _flags: int) -> void:
    _discard_on_end = ""
    if not bool(ProjectSettings.get_setting(SETTING_ENABLED, true)):
        return
    var messages := PackedStringArray()
    var platform := get_export_platform()
    if platform != null and platform.get_os_name() == ANDROID_OS_NAME:
        var sdk_problem := android_min_sdk_problem(get_option(OPTION_GRADLE_BUILD), get_option(OPTION_MIN_SDK))
        if not sdk_problem.is_empty():
            messages.append("Export stopped: " + sdk_problem)
    var problems := find_unbound_scripts(get_export_preset())
    if not problems.is_empty():
        messages.append(format_message(problems))
    if messages.is_empty():
        return
    for message in messages:
        platform.add_message(EditorExportPlatform.EXPORT_MESSAGE_ERROR, CATEGORY, message)
    if is_command_line_export():
        printerr("%s: ending the export process (the exit status is non-zero)." % STOP_LINE)
        end_process()
        return
    if is_one_click_deploy(path):
        _discard_on_end = path


## The Export dialog's warning on the Android preset's Min SDK field, before anyone exports.
func _get_export_option_warning(platform: EditorExportPlatform, option: String) -> String:
    if option != OPTION_MIN_SDK or platform == null or platform.get_os_name() != ANDROID_OS_NAME:
        return ""
    if not bool(ProjectSettings.get_setting(SETTING_ENABLED, true)):
        return ""
    return android_min_sdk_problem(get_option(OPTION_GRADLE_BUILD), get_option(OPTION_MIN_SDK))


## "" when an Android preset with these two option values can ship Kanama, else one line that says what to
## set. Only a Gradle build reads Min SDK, and only a Gradle build can include the Kanama plugin. Min SDK is
## a string option; empty means Godot's own default, which is below Android 13.
static func android_min_sdk_problem(use_gradle_build: Variant, min_sdk: Variant) -> String:
    if not (use_gradle_build is bool and use_gradle_build):
        return ""
    var value: String = "" if min_sdk == null else str(min_sdk).strip_edges()
    if value.is_valid_int() and value.to_int() >= ANDROID_MIN_SDK:
        return ""
    var current: String = "empty" if value.is_empty() else value
    return "Kanama needs Android 13 or newer, and this Android preset's Min SDK is %s. Set Min SDK to %d in the Android export preset (Gradle Build > Min SDK)." % [current, ANDROID_MIN_SDK]


func _export_end() -> void:
    if not _discard_on_end.is_empty():
        discard_export(_discard_on_end)
    _discard_on_end = ""


static func is_command_line_export() -> bool:
    for arg in OS.get_cmdline_args():
        if COMMAND_LINE_EXPORT_ARGS.has(arg):
            return true
    return false


## Ends this process at once with a non-zero status. On Unix OS.kill sends SIGKILL (status 137). On
## Windows OS.kill terminates with status 0 (TerminateProcess(..., 0)), so taskkill /F (status 1) runs
## instead, and if it returns without ending the process, OS.crash aborts it.
static func end_process() -> void:
    var pid := OS.get_process_id()
    if OS.get_name() == "Windows":
        OS.execute("taskkill", ["/F", "/PID", str(pid)])
    else:
        OS.kill(pid)
    OS.crash(STOP_LINE)


## The editor's temp folder, where Godot writes a one-click deploy's export (EditorPaths' temp dir,
## which 4.7 does not expose to scripts): editor_data/temp beside a self-contained editor, else the
## OS temp folder.
static func editor_temp_dir() -> String:
    var editor := _editor()
    if editor != null:
        var paths: EditorPaths = editor.get_editor_paths()
        if paths.is_self_contained():
            return paths.get_data_dir().path_join("temp")
    return OS.get_temp_dir()


## True only for the temporary export of an Android or iOS one-click deploy: Godot's own file name
## directly in the editor's temp folder (ONE_CLICK_EXPORT_PATTERN), never an export the user aimed at
## the temp folder (on Linux that is /tmp).
static func is_one_click_deploy(path: String) -> bool:
    var temp_dir := editor_temp_dir()
    if temp_dir.is_empty():
        return false
    var prefix := temp_dir.simplify_path().path_join("")
    var target := path.simplify_path()
    if not target.begins_with(prefix):
        return false
    return RegEx.create_from_string(ONE_CLICK_EXPORT_PATTERN).search(target.substr(prefix.length())) != null


## Removes a one-click deploy's temporary export: the Android APK and its .idsig, or the Xcode archive
## Godot installs an iOS app from. Does nothing for any other path.
static func discard_export(path: String) -> void:
    if not is_one_click_deploy(path):
        return
    for file in [path, path + ".idsig"]:
        if FileAccess.file_exists(file):
            DirAccess.remove_absolute(file)
    _remove_tree(path.get_basename() + ".xcarchive")


## Deletes a folder and everything in it, hidden files included. A symbolic link is removed as a link:
## the folder it points to is never entered.
static func _remove_tree(dir: String) -> void:
    if _is_link(dir):
        DirAccess.remove_absolute(dir)
        return
    var access := DirAccess.open(dir)
    if access == null:
        return
    access.include_hidden = true
    for sub in access.get_directories():
        _remove_tree(dir.path_join(sub))
    for file in access.get_files():
        DirAccess.remove_absolute(dir.path_join(file))
    DirAccess.remove_absolute(dir)


static func _is_link(path: String) -> bool:
    var parent := DirAccess.open(path.get_base_dir())
    return parent != null and parent.is_link(path)


## The `.kt` scripts the export uses that do not bind in this editor: [{"path", "reason"}]. With a
## preset (in the editor), only the scenes and resources that preset exports count; without one, every
## scene and resource under res://.
static func find_unbound_scripts(preset: EditorExportPreset = null) -> Array:
    var problems := []
    for path in used_kotlin_scripts(preset):
        var reason := unbound_reason(path)
        if not reason.is_empty():
            problems.append({"path": path, "reason": reason})
    return problems


## "" when the script binds, else why it does not. A path that does not exist is not this guard's
## business (Godot reports it); a `.kt` the editor cannot load at all means no loader is registered.
static func unbound_reason(path: String) -> String:
    if not FileAccess.file_exists(path):
        return ""
    # Without the addon nothing registers KanamaScript; do not make Godot log "No loader found" per script.
    if not ClassDB.class_exists(SCRIPT_CLASS):
        return REASON_NO_LOADER
    var script = ResourceLoader.load(path)
    if script == null:
        return REASON_NO_LOADER
    if not (script is Script) or not script.can_instantiate():
        return REASON_NOT_BOUND
    return ""


## Every `.kt` path an exported scene or resource attaches, plus the `.kt` autoloads. Sorted, de-duplicated.
static func used_kotlin_scripts(preset: EditorExportPreset = null) -> Array:
    var found := {}
    for file in exported_resource_files(preset):
        # A text resource that never mentions ".kt" attaches no Kotlin script: skip the dependency parse.
        if TEXT_RESOURCE_EXTENSIONS.has(file.get_extension()) and not FileAccess.get_file_as_string(file).contains("." + KOTLIN_EXTENSION):
            continue
        for dependency in ResourceLoader.get_dependencies(file):
            var path := _dependency_path(dependency)
            if path.get_extension() == KOTLIN_EXTENSION:
                found[path] = true
    for target in _autoload_paths():
        if target.get_extension() == KOTLIN_EXTENSION:
            found[target] = true
    var paths := found.keys()
    paths.sort()
    return paths


## The scenes and resources (.tscn, .scn, .tres, .res) the export writes. In the editor this follows
## the preset like Godot's exporter does (export mode, selected files and their dependencies, include
## and exclude filters) over the editor's file system, which already leaves out hidden folders,
## folders with .gdignore and nested projects. Outside the editor (scripts/check_export_guard.gd) it
## walks res:// the same way and takes every scene and resource.
static func exported_resource_files(preset: EditorExportPreset = null) -> PackedStringArray:
    var all := {}
    var editor := _editor()
    if editor != null:
        _collect_editor_files(editor.get_resource_filesystem().get_filesystem(), all)
    else:
        _collect_disk_files("res://", all)
    var exported := {}
    var mode := EditorExportPreset.EXPORT_ALL_RESOURCES if preset == null else preset.get_export_filter()
    match mode:
        EditorExportPreset.EXPORT_SELECTED_SCENES, EditorExportPreset.EXPORT_SELECTED_RESOURCES:
            for file in preset.get_files_to_export():
                if mode == EditorExportPreset.EXPORT_SELECTED_SCENES and String(all.get(file, "")) != "PackedScene":
                    continue
                _add_with_dependencies(file, exported)
            for target in _autoload_paths():
                _add_with_dependencies(target, exported)
        EditorExportPreset.EXCLUDE_SELECTED_RESOURCES:
            exported = all.duplicate()
            for file in preset.get_files_to_export():
                exported.erase(file)
        EditorExportPreset.EXPORT_CUSTOMIZED:
            _collect_customized(preset, editor.get_resource_filesystem().get_filesystem(), preset.get_file_export_mode("res://", EditorExportPreset.MODE_FILE_NOT_CUSTOMIZED), exported)
        _:
            exported = all
    if preset != null:
        _apply_filter(all, preset.get_include_filter(), exported, false)
        _apply_filter(all, preset.get_exclude_filter(), exported, true)
    var files := PackedStringArray()
    for file in exported:
        if RESOURCE_EXTENSIONS.has(String(file).get_extension()):
            files.append(file)
    return files


## EditorInterface, looked up at run time: this file also runs outside the editor (scripts/check_export_guard.gd).
static func _editor() -> Object:
    return Engine.get_singleton("EditorInterface") if Engine.is_editor_hint() else null


static func _collect_editor_files(dir: EditorFileSystemDirectory, out: Dictionary) -> void:
    for i in dir.get_subdir_count():
        _collect_editor_files(dir.get_subdir(i), out)
    for i in dir.get_file_count():
        var type := dir.get_file_type(i)
        if type != "TextFile":
            out[dir.get_file_path(i)] = type


static func _collect_disk_files(dir: String, out: Dictionary) -> void:
    for file in DirAccess.get_files_at(dir):
        if RESOURCE_EXTENSIONS.has(file.get_extension()):
            out[dir.path_join(file)] = ""
    for sub in DirAccess.get_directories_at(dir):
        var path := dir.path_join(sub)
        # Godot's file system skips hidden folders (.godot), .gdignore folders and nested projects.
        if sub.begins_with(".") or FileAccess.file_exists(path.path_join(".gdignore")) or FileAccess.file_exists(path.path_join("project.godot")):
            continue
        _collect_disk_files(path, out)


static func _collect_customized(preset: EditorExportPreset, dir: EditorFileSystemDirectory, dir_mode: EditorExportPreset.FileExportMode, out: Dictionary) -> void:
    for i in dir.get_subdir_count():
        var sub := dir.get_subdir(i)
        _collect_customized(preset, sub, preset.get_file_export_mode(sub.get_path(), dir_mode), out)
    for i in dir.get_file_count():
        if dir.get_file_type(i) == "TextFile":
            continue
        var path := dir.get_file_path(i)
        if preset.get_file_export_mode(path, dir_mode) != EditorExportPreset.MODE_FILE_REMOVE:
            out[path] = true


static func _add_with_dependencies(path: String, out: Dictionary) -> void:
    if path.is_empty() or out.has(path):
        return
    out[path] = true
    if not RESOURCE_EXTENSIONS.has(path.get_extension()):
        return
    for dependency in ResourceLoader.get_dependencies(path):
        _add_with_dependencies(_dependency_path(dependency), out)


## A preset's comma-separated wildcard filter, matched like Godot's exporter: against the full path and
## the path without res://, case-insensitively.
static func _apply_filter(all: Dictionary, filter: String, exported: Dictionary, exclude: bool) -> void:
    var patterns := PackedStringArray()
    for pattern in filter.split(","):
        if not pattern.strip_edges().is_empty():
            patterns.append(pattern.strip_edges())
    if patterns.is_empty():
        return
    for file in (exported.keys() if exclude else all.keys()):
        for pattern in patterns:
            if String(file).matchn(pattern) or String(file).trim_prefix("res://").matchn(pattern):
                if exclude:
                    exported.erase(file)
                else:
                    exported[file] = true
                break


static func _autoload_paths() -> PackedStringArray:
    var paths := PackedStringArray()
    for info in ProjectSettings.get_property_list():
        var name := String(info["name"])
        if name.begins_with("autoload/"):
            paths.append(String(ProjectSettings.get_setting(name, "")).trim_prefix("*"))
    return paths


## get_dependencies() yields "path" or "uid://...::Type::path"; the uid wins when the file moved.
static func _dependency_path(dependency: String) -> String:
    var parts := dependency.split("::")
    var path := parts[parts.size() - 1]
    if parts.size() > 1 and parts[0].begins_with("uid://"):
        var id := ResourceUID.text_to_id(parts[0])
        if ResourceUID.has_id(id):
            path = ResourceUID.get_id_path(id)
    return path


## The message (without the "Kanama: " category prefix Godot adds to export messages).
static func format_message(problems: Array) -> String:
    var lines := PackedStringArray()
    lines.append("Export stopped: %d Kotlin script(s) that this export uses do not bind in this editor." % problems.size())
    for i in mini(problems.size(), MAX_LISTED):
        lines.append("  %s (%s)" % [problems[i]["path"], problems[i]["reason"]])
    if problems.size() > MAX_LISTED:
        lines.append("  ... and %d more" % (problems.size() - MAX_LISTED))
    lines.append("Fix: install the desktop addon and rebuild the scripts jar, then restart the editor: ./gradlew installAddonJar -PkanamaProjectDir=<project> -PkanamaProjectScriptsDir=<project>/kotlin-src (from the Kanama checkout; for an Android project run installAndroidPluginAar first and installAddonJar after it; for an iOS project installIosAddon with -PkanamaProjectScriptsDir), or use Build Scripts in the editor when addons/kanama is already installed.")
    lines.append("To export anyway, with those scenes as described above, turn off the project setting %s." % SETTING_ENABLED)
    return "\n".join(lines)
