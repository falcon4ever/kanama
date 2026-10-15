@tool
extends EditorPlugin
## check_export_guard_discard.gd: tests, inside a running editor, the export guard's one-click deploy
## clean-up (addons/kanama_tools/kanama_export_guard.gd: is_one_click_deploy, discard_export) on files it
## makes in the editor's real temp folder. Godot's own one-click names (Android tmpexport.<n>.apk with
## its .idsig, iOS tmpexport.<n>/export.ipa with its export.xcarchive, hidden files and a symbolic link
## inside) must be recognised and deleted, without following the link; a user's export in the temp
## folder and names Godot does not use must be left alone. Prints `[export_guard_discard] PASS` and quits
## with 0, or prints each FAIL and quits with 1.
##
## Runs as an editor plugin (EditorPaths and EditorInterface exist only in the editor). Usage, from
## scripts/tool_smoke.sh: a scratch project with addons/kanama_tools and this file enabled as a plugin,
## `godot --headless --editor --path <project> --quit-after <frames>`.
const Guard := preload("res://addons/kanama_tools/kanama_export_guard.gd")
const TAG := "[export_guard_discard]"

var _failures := PackedStringArray()


func _enter_tree() -> void:
    _run.call_deferred()


func _run() -> void:
    var temp := OS.get_temp_dir()
    var paths := EditorInterface.get_editor_paths()
    if paths.is_self_contained():
        temp = paths.get_data_dir().path_join("temp")
    # Digits only, unique to this run: Godot's names use the Unix time.
    var id := "%d%d" % [int(Time.get_unix_time_from_system()), OS.get_process_id()]
    var outside := temp.path_join("kanama_guard_outside_%s" % id)
    var made: Array[String] = [outside]

    # Android one-click: <temp>/tmpexport.<n>.apk and the .idsig apksigner writes beside it.
    var apk := temp.path_join("tmpexport.%s.apk" % id)
    _write(apk)
    _write(apk + ".idsig")
    made.append_array([apk, apk + ".idsig"])
    _expect(Guard.is_one_click_deploy(apk), "Godot's one-click APK %s is not recognised" % apk)
    Guard.discard_export(apk)
    _expect(not FileAccess.file_exists(apk), "the one-click APK %s was not deleted" % apk)
    _expect(not FileAccess.file_exists(apk + ".idsig"), "the one-click APK's .idsig was not deleted")

    # iOS one-click: <temp>/tmpexport.<n>/export.ipa; the app is installed from export.xcarchive beside it.
    var ios_dir := temp.path_join("tmpexport.%s1" % id)
    var ipa := ios_dir.path_join("export.ipa")
    var archive := ios_dir.path_join("export.xcarchive")
    var app := archive.path_join("Products/Applications/export.app")
    made.append(ios_dir)
    _write(app.path_join("export.pck"))
    _write(app.path_join(".hidden"))
    _write(archive.path_join(".DS_Store"))
    _write(outside.path_join("keep.txt"))
    var linked := DirAccess.open(archive).create_link(outside, archive.path_join("linked")) == OK
    if not linked:
        print("%s note: this system cannot create a symbolic link; the link case is not checked" % TAG)
    _expect(Guard.is_one_click_deploy(ipa), "Godot's one-click iOS export %s is not recognised" % ipa)
    Guard.discard_export(ipa)
    _expect(not DirAccess.dir_exists_absolute(archive), "the one-click Xcode archive %s was not deleted (hidden files left?)" % archive)
    _expect(FileAccess.file_exists(outside.path_join("keep.txt")), "discard_export followed a symbolic link out of the archive and deleted %s" % outside.path_join("keep.txt"))

    # A user's own exports in the temp folder (on Linux, /tmp/game.apk) and names Godot does not use.
    var keep: Array[String] = [
        temp.path_join("kanama_guard_user_%s.apk" % id),
        temp.path_join("kanama_guard_user_%s.ipa" % id),
        temp.path_join("tmpexport.%sx.apk" % id),
        temp.path_join("tmpexport.%s2/game.ipa" % id),
        temp.path_join("kanama_guard_sub_%s/tmpexport.%s.apk" % [id, id]),
        ProjectSettings.globalize_path("res://tmpexport.%s.apk" % id),
    ]
    _write(temp.path_join("kanama_guard_user_%s.xcarchive/keep.txt" % id))
    made.append_array([temp.path_join("kanama_guard_user_%s.xcarchive" % id), temp.path_join("tmpexport.%s2" % id), temp.path_join("kanama_guard_sub_%s" % id)])
    for file in keep:
        _write(file)
        _write(file + ".idsig")
        made.append_array([file, file + ".idsig"])
        _expect(not Guard.is_one_click_deploy(file), "%s is taken for a one-click export" % file)
        Guard.discard_export(file)
        _expect(FileAccess.file_exists(file) and FileAccess.file_exists(file + ".idsig"), "discard_export deleted %s or its .idsig" % file)
    _expect(FileAccess.file_exists(temp.path_join("kanama_guard_user_%s.xcarchive/keep.txt" % id)), "discard_export deleted the Xcode archive beside a user's .ipa")

    for path in made:
        _clean(path)
    # Called by name, so a guard without the helper still runs the cases above and reports them.
    var guard_script: Object = Guard
    if guard_script.get_script_method_list().any(func(method): return method["name"] == "editor_temp_dir"):
        var guard_temp = guard_script.call("editor_temp_dir")
        _expect(guard_temp == temp, "the guard's temp folder is %s, the editor's is %s" % [guard_temp, temp])
    else:
        _failures.append("the guard has no editor_temp_dir()")
    if _failures.is_empty():
        print("%s PASS: one-click APK, .idsig and Xcode archive deleted; %d other files left alone" % [TAG, keep.size() * 2 + 1])
        get_tree().quit(0)
    else:
        for failure in _failures:
            printerr("%s FAIL: %s" % [TAG, failure])
        get_tree().quit(1)


func _expect(ok: bool, failure: String) -> void:
    if not ok:
        _failures.append(failure)


func _write(path: String) -> void:
    DirAccess.make_dir_recursive_absolute(path.get_base_dir())
    var file := FileAccess.open(path, FileAccess.WRITE)
    if file == null:
        _failures.append("cannot write the scratch file %s" % path)
        return
    file.store_string("kanama export guard test\n")


## Test clean-up, independent of the guard's own deletion: links are removed as links.
func _clean(path: String) -> void:
    var parent := DirAccess.open(path.get_base_dir())
    if parent == null:
        return
    if parent.is_link(path) or FileAccess.file_exists(path):
        DirAccess.remove_absolute(path)
        return
    var access := DirAccess.open(path)
    if access == null:
        return
    access.include_hidden = true
    for sub in access.get_directories():
        _clean(path.path_join(sub))
    for file in access.get_files():
        DirAccess.remove_absolute(path.path_join(file))
    DirAccess.remove_absolute(path)
