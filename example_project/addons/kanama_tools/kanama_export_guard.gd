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
## the warning on the Min SDK field itself (_get_export_option_warning, which Godot calls only because
## _supports_platform returns true).
##
## A minified Android release export (Gradle Build > Minification) also gets R8's compat mode: the guard writes
## `android.enableR8.fullMode=false` into the build template's gradle.properties from _get_android_libraries,
## which Godot calls after it has installed or replaced the template and before it starts Gradle (see there).
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
const OPTION_MINIFICATION := "gradle_build/minification"
const OPTION_GRADLE_BUILD_DIRECTORY := "gradle_build/gradle_build_directory"
## Where Godot keeps the Android build template when the preset's Gradle Build Directory is empty.
const ANDROID_DEFAULT_GRADLE_BUILD_DIRECTORY := "res://android"

## A minified Android export must run R8 in compat mode: PanamaPort's R8 rules are written for it, and AGP's
## default full mode breaks the engine's startup on the device, after a build that looks fine.
## Where Godot reads Android plugins (.gdap and their AARs; Kanama's KanamaAndroid.gdap): always res://android/plugins.
const ANDROID_PLUGINS_DIR := "res://android/plugins"
const R8_FULL_MODE_PROPERTY := "android.enableR8.fullMode"

const REASON_NO_LOADER := "no .kt loader: the desktop addon (addons/kanama) is missing or did not load, so the export would leave the script out and could not convert or check the scenes that use it"
const REASON_NOT_BOUND := "not in kanama-scripts.jar: the scenes that use it would be exported without its property values"

var _discard_on_end := ""
## The files in ANDROID_PLUGINS_DIR when an Android Gradle export began (_export_begin), checked again in
## _get_android_libraries.
var _android_plugins_at_begin := PackedStringArray()


func _get_name() -> String:
    return "KanamaExportGuard"


## Every platform: Godot calls a plugin's per-platform hooks only when this returns true, among them
## _get_export_option_warning for the Export dialog (EditorExportPreset::_get_property_warning) and
## _get_android_libraries. The guard overrides no other per-platform hook, so the rest return Godot's neutral
## defaults (no options, no manifest contents, no Apple project step). _export_begin runs either way.
func _supports_platform(_platform: EditorExportPlatform) -> bool:
    return true


func _export_begin(_features: PackedStringArray, _is_debug: bool, path: String, _flags: int) -> void:
    _discard_on_end = ""
    _android_plugins_at_begin = PackedStringArray()
    if not bool(ProjectSettings.get_setting(SETTING_ENABLED, true)):
        return
    var messages := PackedStringArray()
    var platform := get_export_platform()
    if platform != null and platform.get_os_name() == ANDROID_OS_NAME:
        var sdk_problem := android_min_sdk_problem(get_option(OPTION_GRADLE_BUILD), get_option(OPTION_MIN_SDK))
        if not sdk_problem.is_empty():
            messages.append("Export stopped: " + sdk_problem)
        if get_option(OPTION_GRADLE_BUILD) == true and DirAccess.dir_exists_absolute(ANDROID_PLUGINS_DIR):
            _android_plugins_at_begin = DirAccess.get_files_at(ANDROID_PLUGINS_DIR)
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


## Runs after Godot set up the Android build template and before Gradle starts. Godot 4.8's Android exporter
## runs the plugins' _export_begin first, and only then sets up the template: it installs a missing one, or,
## with Editor Settings > Export > Android > Build > Automatically Delete Build Directory on, deletes one from
## another Godot version together with everything else in its parent folder (res://android, so also
## res://android/plugins) and installs a fresh one. This hook comes after that setup (export_plugin.cpp,
## export_project); Godot calls it only for a Gradle build. Two things are checked here:
## - the Android plugins that were in res://android/plugins when the export began are still there: an export
##   without KanamaAndroid.gdap builds an APK with no Kanama in it, which fails only on the device;
## - a minified release export gets R8's compat-mode line in the template Gradle builds from. A line written in
##   _export_begin could be deleted with the old template. Godot minifies only release builds, so a debug
##   export or a one-click deploy is left alone.
## The guard adds no library: it returns an empty list.
func _get_android_libraries(platform: EditorExportPlatform, debug: bool) -> PackedStringArray:
    if platform == null or platform.get_os_name() != ANDROID_OS_NAME:
        return PackedStringArray()
    if not bool(ProjectSettings.get_setting(SETTING_ENABLED, true)):
        return PackedStringArray()
    if get_option(OPTION_GRADLE_BUILD) != true:
        return PackedStringArray()
    var problem := android_plugins_problem(_android_plugins_at_begin, DirAccess.get_files_at(ANDROID_PLUGINS_DIR) if DirAccess.dir_exists_absolute(ANDROID_PLUGINS_DIR) else PackedStringArray())
    if problem.is_empty() and not debug and get_option(OPTION_MINIFICATION) == true:
        problem = keep_r8_compat_mode(android_gradle_properties_path(get_option(OPTION_GRADLE_BUILD_DIRECTORY)))
    if problem.is_empty():
        return PackedStringArray()
    # Too late to stop the export from Godot's side: a command-line export ends here, before Gradle runs; an
    # editor export reports the error ("Failed" in the export dialog).
    platform.add_message(EditorExportPlatform.EXPORT_MESSAGE_ERROR, CATEGORY, "Export stopped: " + problem)
    if is_command_line_export():
        printerr("%s: ending the export process (the exit status is non-zero)." % STOP_LINE)
        end_process()
    return PackedStringArray()


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
    return "Android 13 or newer is required, and this preset's Min SDK is %s. Set Min SDK to %d in the Android export preset (Gradle Build > Min SDK)." % [current, ANDROID_MIN_SDK]


## "" when every file of [param before] (res://android/plugins when the export began) is still in
## [param now], else what happened and what to do.
static func android_plugins_problem(before: PackedStringArray, now: PackedStringArray) -> String:
    var lost := PackedStringArray()
    for file in before:
        if not now.has(file):
            lost.append(file)
    if lost.is_empty():
        return ""
    return "Godot deleted %s from %s while it replaced the Android build template (Editor Settings > Export > Android > Build > Automatically Delete Build Directory deletes everything in res://android), so this export would not contain Kanama. Put the Android plugin back (./gradlew buildAndroidScripts, or installAndroidPluginAar from a Kanama checkout) and export again. On the next Godot upgrade, delete res://android/build yourself instead." % [", ".join(lost), ANDROID_PLUGINS_DIR]


## The gradle.properties of the Android build template a Gradle export builds. Godot builds in
## <Gradle Build Directory>/build, and in res://android/build when that preset option is empty
## (ExportTemplateManager::get_android_build_directory in Godot 4.8).
static func android_gradle_properties_path(gradle_build_directory: Variant) -> String:
    var dir: String = "" if gradle_build_directory == null else str(gradle_build_directory)
    if dir.is_empty():
        dir = ANDROID_DEFAULT_GRADLE_BUILD_DIRECTORY
    return dir.path_join("build").path_join("gradle.properties")


## Writes the compat-mode line into the Android build template's gradle.properties at [param path];
## "" when it is there or was written, else one line that says what is wrong. It runs after Godot set up
## the template (_get_android_libraries), so a missing folder or file means the template is broken.
static func keep_r8_compat_mode(path: String) -> String:
    var build_dir := path.get_base_dir()
    if not DirAccess.dir_exists_absolute(build_dir):
        return "a minified build needs %s=false in the Android build template, and its folder %s does not exist although Godot set the template up for this export. Reinstall the Android build template (Project > Install Android Build Template)." % [R8_FULL_MODE_PROPERTY, build_dir]
    var reader := FileAccess.open(path, FileAccess.READ)
    if reader == null:
        return "a minified build needs %s=false in %s, and that file %s. Reinstall the Android build template (Project > Install Android Build Template)." % [R8_FULL_MODE_PROPERTY, path, "is missing" if not FileAccess.file_exists(path) else "cannot be read"]
    var text := reader.get_as_text()
    reader.close()
    var updated := with_r8_compat_mode(text)
    if updated == text:
        return ""
    var file := FileAccess.open(path, FileAccess.WRITE)
    if file == null:
        return "cannot write %s, and a minified build needs %s=false in it." % [path, R8_FULL_MODE_PROPERTY]
    file.store_string(updated)
    return ""


## [param text], a gradle.properties, with R8's full mode switched off: unchanged when it already sets
## exactly that once, else every other setting of the property dropped and the line added at the end.
## Keeps the file's other lines and its line endings (CRLF stays CRLF).
static func with_r8_compat_mode(text: String) -> String:
    var line := "%s=false" % R8_FULL_MODE_PROPERTY
    var eol := "\r\n" if text.contains("\r\n") else "\n"
    var kept := PackedStringArray()
    var settings := PackedStringArray()
    for existing in text.split("\n"):
        var bare := existing.trim_suffix("\r")
        if _property_key(bare) == R8_FULL_MODE_PROPERTY:
            settings.append(bare.replace(" ", "").replace("\t", ""))
        else:
            kept.append(bare)
    if settings.size() == 1 and settings[0] == line:
        return text
    var body := eol.join(kept).strip_edges(false, true)
    return ("" if body.is_empty() else body + eol) + line + eol


## The key of one gradle.properties line ("" for a comment or a blank line): the text before the first
## `=`, `:` or whitespace, as java.util.Properties reads it.
static func _property_key(line: String) -> String:
    var bare := line.strip_edges()
    if bare.is_empty() or bare.begins_with("#") or bare.begins_with("!"):
        return ""
    for i in bare.length():
        if bare[i] in ["=", ":", " ", "\t"]:
            return bare.substr(0, i)
    return bare


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
