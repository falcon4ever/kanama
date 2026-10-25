@tool
extends EditorPlugin
## check_editor_build_loop.gd: drives the Kanama Tools plugin (addons/kanama_tools/plugin.gd) inside a
## running editor and checks its build loop through what the user sees: the toolbar's status light
## (KanamaBuildStatus: its state and its colour), Build Scripts (KanamaBuildScripts), the build output
## dialog the light opens, and build on save. The builds are a fake gradlew (scripts/tool_smoke.sh
## writes it) that logs each run to $KANAMA_FAKE_GRADLE_CONTROL/runs, sleeps a second, writes a KSP-like
## .kt file under build/ like the real build, and fails or succeeds as $KANAMA_FAKE_GRADLE_CONTROL/mode
## says. Prints `[build_loop] PASS` and quits with 0, or prints each FAIL and quits with 1.
##
## KANAMA_BUILD_LOOP_MODE=restart runs only the check of a second editor session: the light is green
## at once for scripts the first session built (the build stamp in .godot/).
##
## Runs as an editor plugin beside Kanama Tools in a scratch project; the editor's settings must be a
## scratch copy too (tool_smoke.sh points HOME and the XDG folders at the scratch directory), because
## the check switches the Build On Save editor setting.
const TAG := "[build_loop]"
const KANAMA_PLUGIN := "res://addons/kanama_tools/plugin.gd"
const BUILD_ON_SAVE := "kanama/build/build_on_save"
const HELLO := "res://kotlin-src/HelloScript.kt"
const TIMEOUT_SEC := 150.0

var _failures := PackedStringArray()
var _control := OS.get_environment("KANAMA_FAKE_GRADLE_CONTROL")
var _kanama: EditorPlugin
var _light: Button
var _done := false


func _enter_tree() -> void:
    _run.call_deferred()


func _run() -> void:
    get_tree().create_timer(TIMEOUT_SEC).timeout.connect(func() -> void:
        if not _done:
            _failures.append("the check did not finish within %d s" % int(TIMEOUT_SEC))
            _finish())
    if _control.is_empty():
        _failures.append("KANAMA_FAKE_GRADLE_CONTROL is not set")
        _finish()
        return
    for i in 200:
        _kanama = _find_kanama_plugin()
        if _kanama != null:
            break
        await get_tree().process_frame
    if _kanama == null:
        _failures.append("the Kanama Tools plugin is not loaded")
        _finish()
        return
    _light = _find_control("KanamaBuildStatus") as Button
    var build := _find_control("KanamaBuildScripts") as Button
    if _light == null or build == null:
        _failures.append("the Kanama toolbar has no status light (KanamaBuildStatus) or no Build Scripts (KanamaBuildScripts)")
        _finish()
        return
    if OS.get_environment("KANAMA_BUILD_LOOP_MODE") == "restart":
        _expect_status("current", "a new editor session on scripts the last session built")
        _finish()
        return

    # The defaults: build on save is on; no release build of the scripts exists, and a project without
    # a Kanama checkout beside it has no platform builds, so neither button is there.
    _expect(_kanama.is_build_on_save_enabled(), "build on save is off by default")
    _expect(_find_control("KanamaBuildRelease") == null, "Build Release is shown, but no release build of the scripts exists")
    _expect(_find_control("KanamaPlatformBuilds") == null, "the Platforms menu is shown in a project without a Kanama checkout")

    # 1. A .kt file and no scripts jar: never built.
    _expect_status("stale", "a project with a .kt file and no kanama-scripts.jar")

    # 2. A failing build: yellow while it runs, then red; the light opens its output.
    _write(_control.path_join("mode"), "fail")
    build.pressed.emit()
    await get_tree().process_frame
    _expect_status("building", "right after Build Scripts was pressed")
    await _wait_while_building()
    _expect_status("failed", "after a failing build")
    _expect(_runs() == 1, "Build Scripts ran %d builds, expected 1" % _runs())
    _light.pressed.emit()
    await get_tree().process_frame
    var dialog := _find_control("KanamaBuildOutput") as AcceptDialog
    if dialog == null or not dialog.visible:
        _failures.append("pressing the status light shows no build output dialog")
    else:
        var text := (dialog.get_node("Output") as TextEdit).text
        _expect(text.contains("Unresolved reference 'oops'"), "the build output dialog lacks the compiler error: %s" % text.left(300))
        _expect(text.contains("failed (exit 1)"), "the build output dialog does not say the build failed with exit 1: %s" % text.left(300))
        dialog.hide()

    # 3. A successful build: green. The KSP-like file the build wrote under build/ is no save.
    _write(_control.path_join("mode"), "ok")
    build.pressed.emit()
    await get_tree().process_frame
    _expect_status("building", "right after Build Scripts was pressed again")
    await _wait_while_building()
    _expect_status("current", "after a successful build")
    _expect(_runs() == 2, "after two presses %d builds ran, expected 2" % _runs())
    await _wait(3.0)
    _expect(_runs() == 2, "a build started with no save after a build: the watcher took the build's own output under build/ for a save (%d builds)" % _runs())

    # 4. Build on save: two saves within the debounce start one build, and the light follows it.
    _append(HELLO, "// saved once\n")
    await _wait(0.5)
    _write(ProjectSettings.globalize_path("res://kotlin-src/Second.kt"), "class Second\n")
    await _wait_for(func() -> bool: return _runs() >= 3, 15.0)
    _expect(_runs() >= 3, "saving a .kt file with build on save on started no build")
    await _wait_while_building()
    _expect_status("current", "after the build that build on save started")
    await _wait(3.0)
    _expect(_runs() == 3, "two saves within the debounce started %d builds, expected 1" % (_runs() - 2))

    # 5. Build on save off: a save starts nothing and the light turns yellow.
    EditorInterface.get_editor_settings().set_setting(BUILD_ON_SAVE, false)
    await _wait(1.1)
    _append(HELLO, "// saved with build on save off\n")
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "stale", 5.0)
    _expect_status("stale", "after a save with build on save off")
    await _wait(3.0)
    _expect(_runs() == 3, "build on save is off, but a save started a build (%d builds)" % _runs())
    EditorInterface.get_editor_settings().set_setting(BUILD_ON_SAVE, true)

    # 6. The scripts jar disappears (deleted, or never restored by a build): the light must not stay
    # green, with no save to notice.
    build.pressed.emit()
    await get_tree().process_frame
    await _wait_while_building()
    _expect_status("current", "after the last build")
    DirAccess.remove_absolute(ProjectSettings.globalize_path("res://addons/kanama/kanama-scripts.jar"))
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "stale", 5.0)
    _expect_status("stale", "with kanama-scripts.jar deleted")
    build.pressed.emit()
    await get_tree().process_frame
    await _wait_while_building()
    _expect_status("current", "after the build that restored the jar")

    # 7. Leaving the editor mid-build ends the build: no Gradle client outlives the plugin.
    _write(_control.path_join("mode"), "hang")
    build.pressed.emit()
    await _wait_for(func() -> bool: return FileAccess.file_exists(_control.path_join("hang.pid")), 10.0)
    var pid := FileAccess.get_file_as_string(_control.path_join("hang.pid")).strip_edges()
    _expect(not pid.is_empty(), "the hanging fake build never started")
    EditorInterface.set_plugin_enabled("kanama_tools", false)
    await _wait(0.5)
    if not pid.is_empty():
        var alive := OS.execute("sh", ["-c", "kill -0 %s 2>/dev/null" % pid]) == 0
        _expect(not alive, "the build (pid %s) still runs after the plugin left" % pid)
        if alive:
            OS.kill(int(pid))
    _finish()


func _find_kanama_plugin() -> EditorPlugin:
    for node in get_parent().get_children():
        var script: Script = node.get_script()
        if node is EditorPlugin and script != null and script.resource_path == KANAMA_PLUGIN:
            return node
    return null


func _find_control(node_name: String) -> Node:
    return EditorInterface.get_base_control().get_tree().root.find_child(node_name, true, false)


func _expect_status(expected: String, when: String) -> void:
    var status: String = _kanama.get_build_status()
    _expect(status == expected, "status light %s: expected %s, got %s" % [when, expected, status])
    var shown := String(_light.get_meta("kanama_status", ""))
    _expect(shown == expected, "the status light widget %s shows %s, expected %s" % [when, shown, expected])
    var colour: Color = _light.get_theme_color("icon_normal_color")
    var want: Color = _kanama.status_color(expected)
    _expect(colour.is_equal_approx(want), "the status light %s is coloured %s, expected %s for %s" % [when, colour, want, expected])
    print("%s %s: %s" % [TAG, when, status])


func _expect(ok: bool, failure: String) -> void:
    if not ok:
        _failures.append(failure)


func _runs() -> int:
    var path := _control.path_join("runs")
    if not FileAccess.file_exists(path):
        return 0
    return FileAccess.get_file_as_string(path).strip_edges().split("\n", false).size()


func _wait(seconds: float) -> void:
    await get_tree().create_timer(seconds).timeout


func _wait_for(condition: Callable, seconds: float) -> void:
    var deadline := Time.get_ticks_msec() + int(seconds * 1000.0)
    while not condition.call() and Time.get_ticks_msec() < deadline:
        await get_tree().process_frame


func _wait_while_building() -> void:
    await _wait_for(func() -> bool: return _kanama.get_build_status() != "building", 20.0)


func _write(path: String, text: String) -> void:
    var file := FileAccess.open(path, FileAccess.WRITE)
    if file == null:
        _failures.append("cannot write %s" % path)
        return
    file.store_string(text)
    file.close()


func _append(res_path: String, text: String) -> void:
    var path := ProjectSettings.globalize_path(res_path)
    _write(path, FileAccess.get_file_as_string(path) + text)


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
