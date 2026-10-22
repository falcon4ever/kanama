extends SceneTree
## check_export_guard.gd: runs the editor export guard's check (addons/kanama_tools/kanama_export_guard.gd)
## on the project it is started in, without exporting. Exit 0 = every .kt script a scene, a resource or an
## autoload uses binds in this runtime; exit 1 = at least one does not (the guard's message is printed,
## starting with the same `Kanama: Export stopped` line an export prints). Without an export preset it
## checks every scene and resource under res://. It also holds every Android preset in res://export_presets.cfg
## to the guard's Min SDK rule (ANDROID_MIN_SDK): exit 1 names the preset and what to set.
##
## Usage: godot --headless --path <project with addons/kanama + addons/kanama_tools> --script <this file>
const Guard := preload("res://addons/kanama_tools/kanama_export_guard.gd")
const TAG := "[export_guard]"


func _initialize() -> void:
    quit(_run())


func _run() -> int:
    var sdk_problems := android_preset_problems("res://export_presets.cfg")
    if not sdk_problems.is_empty():
        for line in sdk_problems:
            printerr("%s: Export stopped: %s" % [Guard.CATEGORY, line])
        printerr("%s FAIL: %d Android preset(s) set Min SDK below %d" % [TAG, sdk_problems.size(), Guard.ANDROID_MIN_SDK])
        return 1
    var used := Guard.used_kotlin_scripts()
    if used.is_empty():
        printerr("%s FAIL: no .kt script is attached anywhere in this project, so the check proves nothing" % TAG)
        return 1
    var problems := Guard.find_unbound_scripts()
    if not problems.is_empty():
        printerr("%s: %s" % [Guard.CATEGORY, Guard.format_message(problems)])
        printerr("%s FAIL: %d of %d script(s) do not bind" % [TAG, problems.size(), used.size()])
        return 1
    print("%s PASS: all %d script(s) used by this project bind" % [TAG, used.size()])
    return 0


## The guard's Min SDK verdict for each Android preset in [param path], read as a file (no editor here):
## one line per preset that fails it, naming the preset.
static func android_preset_problems(path: String) -> PackedStringArray:
    var problems := PackedStringArray()
    if not FileAccess.file_exists(path):
        return problems
    var presets := ConfigFile.new()
    if presets.load(path) != OK:
        problems.append("%s cannot be read" % path)
        return problems
    for section in presets.get_sections():
        if not section.begins_with("preset.") or section.ends_with(".options"):
            continue
        if presets.get_value(section, "platform", "") != Guard.ANDROID_OS_NAME:
            continue
        var options := section + ".options"
        var problem := Guard.android_min_sdk_problem(
            presets.get_value(options, Guard.OPTION_GRADLE_BUILD, false),
            presets.get_value(options, Guard.OPTION_MIN_SDK, ""))
        if not problem.is_empty():
            problems.append("preset \"%s\": %s" % [presets.get_value(section, "name", section), problem])
    return problems
