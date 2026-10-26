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

    # The Platforms menu. Beside a Kanama checkout: the commands the export pages give, run in the
    # checkout, iOS only on macOS. In a project whose build applies the Kanama Gradle plugin (a kit or
    # store-addon project): Android is its buildAndroidScripts. Nothing with neither.
    var mac: Array = _kanama.platform_builds_for("macOS", "", "/k", "/p", "/p/kotlin-src")
    var linux: Array = _kanama.platform_builds_for("Linux", "", "/k", "/p", "/p/kotlin-src")
    _expect(_platform_ids(mac) == ["android", "ios", "web"], "platform builds on macOS beside a checkout: %s" % [_platform_ids(mac)])
    _expect(_platform_ids(linux) == ["android", "web"], "platform builds on Linux beside a checkout: %s" % [_platform_ids(linux)])
    _expect(_platform_ids(_kanama.platform_builds_for("macOS", "", "", "/p", "/p/kotlin-src")).is_empty(), "platform builds without a checkout or the Kanama Gradle plugin")
    if mac.size() == 3:
        var android: Array = mac[0]["steps"]
        _expect(android.size() == 2 and android[0]["dir"] == "/k" and android[0]["task"] == "installAndroidPluginAar"
            and android[0]["args"] == ["-PkanamaAndroidProjectDir=/p"] and android[1]["task"] == "installAddonJar"
            and android[1]["args"] == ["-PkanamaProjectDir=/p", "-PkanamaProjectScriptsDir=/p/kotlin-src"],
            "the Android platform build runs %s, not the two commands of the Android export page" % [android])
        _expect(bool(mac[0]["script_build"]), "the checkout's Android build (it ends with installAddonJar) is not a script build")
        _expect(mac[2]["steps"][0]["task"] == ":web-runtime:exportWeb" and mac[2]["steps"][0]["args"] == ["-PkanamaWebProjectDir=/p"],
            "the Web platform build runs %s" % [mac[2]["steps"]])
    var kit: Array = _kanama.platform_builds_for("Linux", "/p", "", "/p", "/p/kotlin-src")
    _expect(_platform_ids(kit) == ["android"], "platform builds of a kit project: %s" % [_platform_ids(kit)])
    if kit.size() == 1:
        _expect(kit[0]["steps"] == [{"dir": "/p", "task": "buildAndroidScripts", "args": []}],
            "a kit project's Android build runs %s, not the project's buildAndroidScripts" % [kit[0]["steps"]])
    var both: Array = _kanama.platform_builds_for("macOS", "/p", "/k", "/p", "/p/kotlin-src")
    _expect(both.size() == 3 and both[0]["steps"][0]["task"] == "buildAndroidScripts" and both[1]["steps"][0]["dir"] == "/k",
        "a project with the Kanama Gradle plugin beside a checkout: Android must be the plugin's task, iOS the checkout's: %s" % [both])
    _expect(_kanama.applies_kanama_gradle_plugin('plugins {\n    kotlin("jvm") version "2.4.21"\n    id("net.multigesture.kanama")\n}\n'),
        "the release kit's build.gradle.kts is not seen to apply the Kanama Gradle plugin")
    _expect(not _kanama.applies_kanama_gradle_plugin('plugins {\n    // id("net.multigesture.kanama")\n}\n'),
        "a commented-out plugin line counts as applying the Kanama Gradle plugin")
    # The version lock: scripts built for another Kanama than the addon are yellow, not green.
    _expect(_kanama.build_status(false, false, true, true, true, true) == "stale", "scripts built for another Kanama version than the addon do not turn the light yellow")
    _expect(_kanama.build_status(false, false, true, true, true, false) == "current", "built, current scripts are not green")

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

    # The version lock: a jar built for another Kanama than the addon turns the light yellow, and its
    # tooltip says so; agreeing versions turn it green again.
    var addon_version := ProjectSettings.globalize_path("res://addons/kanama/kanama.version")
    var scripts_version := ProjectSettings.globalize_path("res://addons/kanama/kanama-scripts.version")
    _write(addon_version, "9.9.9\n")
    _write(scripts_version, "0.0.1\n")
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "stale", 5.0)
    _expect_status("stale", "with scripts built for Kanama 0.0.1 and the addon at 9.9.9")
    _expect(_light.tooltip_text.contains("built for Kanama 0.0.1") and _light.tooltip_text.contains("9.9.9"),
        "the status light's tooltip does not name the version mismatch: %s" % _light.tooltip_text)
    _write(scripts_version, "9.9.9\n")
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "current", 5.0)
    _expect_status("current", "once the scripts version matches the addon again")
    DirAccess.remove_absolute(addon_version)
    DirAccess.remove_absolute(scripts_version)

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


func _platform_ids(builds: Array) -> Array:
    var ids := []
    for build in builds:
        ids.append(String(build["id"]))
    return ids
