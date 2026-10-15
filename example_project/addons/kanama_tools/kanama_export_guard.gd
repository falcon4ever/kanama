@tool
extends EditorExportPlugin
## Stops an export that would ship scenes without their Kotlin script properties.
##
## Godot instantiates and re-packs every scene when it converts text resources to binary, and keeps
## only the properties the script instance reports. When the editor cannot bind a project `.kt`
## script (the desktop addon is missing, or kanama-scripts.jar predates the script), the script
## reports none, every scene-stored value is dropped, and the export succeeds without a word.
## GDScript projects cannot reach that state, so a Kanama project must not either.
##
## _export_begin runs before any scene is converted. It checks the `.kt` scripts the project
## actually uses (attached in a scene or resource, or listed as an autoload; a helper `.kt` that no
## scene attaches is not a script class and is not checked). A script binds when the editor loads
## it as a Script that can instantiate; a missing loader makes `load` return null, a script missing
## from the jar loads unbound (`can_instantiate()` is false). When any script fails, the export
## stops with one message (logged as an export error, so it also shows in the export dialog) that
## names them and the fix.
##
## Godot 4.7 gives an export plugin no way to cancel (push_error and add_message only log), so the
## failure is forced the one way the exporter honours: a shared object that does not exist makes the
## platform exporter return an error and the export command exit non-zero. Every file is skipped
## meanwhile, so no scene is converted and no stripped copy lands in the .godot/exported cache.
##
## Opt out with the project setting `kanama/tools/export_guard_enabled` (default true).

const SETTING_ENABLED := "kanama/tools/export_guard_enabled"
const CATEGORY := "Kanama"
const BLOCK_PATH := "res://kanama_export_stopped_see_error_above.so"
const MAX_LISTED := 10
const RESOURCE_EXTENSIONS := ["tscn", "scn", "tres", "res"]
const KOTLIN_EXTENSION := "kt"
const SCRIPT_CLASS := "KanamaScript"

const REASON_NO_LOADER := "no .kt loader: the desktop addon is not loaded"
const REASON_NOT_BOUND := "not in kanama-scripts.jar"

var _blocked := false


func _get_name() -> String:
    return "KanamaExportGuard"


func _export_begin(_features: PackedStringArray, _is_debug: bool, _path: String, _flags: int) -> void:
    _blocked = false
    if not bool(ProjectSettings.get_setting(SETTING_ENABLED, true)):
        return
    var problems := find_unbound_scripts()
    if problems.is_empty():
        return
    var message := format_message(problems)
    get_export_platform().add_message(EditorExportPlatform.EXPORT_MESSAGE_ERROR, CATEGORY, message)
    _blocked = true
    add_shared_object(BLOCK_PATH, PackedStringArray(), "")


func _export_file(_path: String, _type: String, _features: PackedStringArray) -> void:
    if _blocked:
        skip()


func _export_end() -> void:
    _blocked = false


## The `.kt` scripts the project uses that do not bind in this editor: [{"path", "reason"}].
static func find_unbound_scripts() -> Array:
    var problems := []
    for path in used_kotlin_scripts():
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


## Every `.kt` path a scene, a resource or an autoload attaches. Sorted, de-duplicated.
static func used_kotlin_scripts() -> Array:
    var found := {}
    _scan_dir("res://", found)
    for info in ProjectSettings.get_property_list():
        var name := String(info["name"])
        if name.begins_with("autoload/"):
            var target := String(ProjectSettings.get_setting(name, "")).trim_prefix("*")
            if target.get_extension() == KOTLIN_EXTENSION:
                found[target] = true
    var paths := found.keys()
    paths.sort()
    return paths


static func _scan_dir(dir: String, found: Dictionary) -> void:
    for file in DirAccess.get_files_at(dir):
        if RESOURCE_EXTENSIONS.has(file.get_extension()):
            for dependency in ResourceLoader.get_dependencies(dir.path_join(file)):
                var path := _dependency_path(dependency)
                if path.get_extension() == KOTLIN_EXTENSION:
                    found[path] = true
    for sub in DirAccess.get_directories_at(dir):
        # addons ship their own scripts; hidden folders (.godot) and .gdignore folders are not exported.
        if sub.begins_with(".") or (dir == "res://" and sub == "addons"):
            continue
        if FileAccess.file_exists(dir.path_join(sub).path_join(".gdignore")):
            continue
        _scan_dir(dir.path_join(sub), found)


## get_dependencies() yields "path" or "uid://...::Type::path"; the uid wins when the file moved.
static func _dependency_path(dependency: String) -> String:
    var parts := dependency.split("::")
    var path := parts[parts.size() - 1]
    if parts.size() > 1 and parts[0].begins_with("uid://"):
        var id := ResourceUID.text_to_id(parts[0])
        if ResourceUID.has_id(id):
            path = ResourceUID.get_id_path(id)
    return path


static func format_message(problems: Array) -> String:
    var lines := PackedStringArray()
    lines.append("Export stopped: %d Kotlin script(s) used by this project do not bind in this editor. Exporting now would ship every scene that uses them without the scripts' property values, and Godot would not report it." % problems.size())
    for i in mini(problems.size(), MAX_LISTED):
        lines.append("  %s (%s)" % [problems[i]["path"], problems[i]["reason"]])
    if problems.size() > MAX_LISTED:
        lines.append("  ... and %d more" % (problems.size() - MAX_LISTED))
    lines.append("Fix: install the desktop addon and rebuild the scripts jar, then restart the editor: ./gradlew installAddonJar -PkanamaProjectDir=<project> -PkanamaProjectScriptsDir=<project>/kotlin-src (from the Kanama checkout; for an Android project, run installAndroidPluginAar first and installAddonJar after it), or use Build Scripts in the editor when addons/kanama is already installed.")
    return "\n".join(lines)
