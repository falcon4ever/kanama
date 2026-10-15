extends SceneTree
## check_export_guard.gd: runs the editor export guard's check (addons/kanama_tools/kanama_export_guard.gd)
## on the project it is started in, without exporting. Exit 0 = every .kt script a scene, a resource or an
## autoload uses binds in this runtime; exit 1 = at least one does not (the guard's message is printed).
##
## Usage: godot --headless --path <project with addons/kanama + addons/kanama_tools> --script <this file>
const Guard := preload("res://addons/kanama_tools/kanama_export_guard.gd")
const TAG := "[export_guard]"


func _initialize() -> void:
    quit(_run())


func _run() -> int:
    var used := Guard.used_kotlin_scripts()
    if used.is_empty():
        printerr("%s FAIL: no .kt script is attached anywhere in this project, so the check proves nothing" % TAG)
        return 1
    var problems := Guard.find_unbound_scripts()
    if not problems.is_empty():
        printerr(Guard.format_message(problems))
        printerr("%s FAIL: %d of %d script(s) do not bind" % [TAG, problems.size(), used.size()])
        return 1
    print("%s PASS: all %d script(s) used by this project bind" % [TAG, used.size()])
    return 0
