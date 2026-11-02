@tool
extends EditorPlugin
## check_editor_probes.gd: real-editor probes of the Kotlin edit loop. In a running editor, with the real Gradle
## build of the Kanama checkout and the Kanama runtime reloading what it builds, it edits a @ScriptClass the way a
## user does (the file changes on disk) and checks what the user then sees:
##   1. build on save runs the real build and the toolbar's status light (KanamaBuildStatus) ends green;
##   2. the class list: the script Godot loaded for the .kt file lists the new @Export property and method, and
##      the global class list still names the class;
##   3. node members: the scene's node with that script has the new property, with its default, in its
##      property list and in the inspector;
##   4. the saved scene: a value set on the property survives saving the scene and reloading it (.tscn text too);
##   5. a broken edit (a compile error) turns the light red while the last good build stays usable; fixing it
##      turns the light green and the fixed code runs.
## Each probe prints `[editor_probe] PASS probe N: ...` or `[editor_probe] FAIL probe N: ...` lines; the check
## quits with 0 after `[editor_probe] PASS`, else 1.
##
## Runs as an editor plugin beside Kanama Tools in the scratch project scripts/editor_probe_smoke.sh lays out:
## ProbeScript.kt and probe.tscn (scripts/fixtures/editor-probes) at the root, kanama/tools/repo_dir naming the
## checkout, so Build Scripts runs the checkout's installAddonJar for the project (the example_project layout).
const TAG := "[editor_probe]"
const KANAMA_PLUGIN := "res://addons/kanama_tools/plugin.gd"
const SCRIPT_PATH := "res://ProbeScript.kt"
const SCENE_PATH := "res://probe.tscn"
const MARKER := "  // probe: new members\n"
const NEW_MEMBERS := "  @Export var probeLabel: String = \"probe-default\"\n\n  fun probeAnswer(): Long = 42\n"
const BROKEN_MEMBER := "  fun probeBroken(): Long = probeNoSuchValue\n"
const FIXED_MEMBERS := "  @Export var probeLabel: String = \"probe-default\"\n\n  fun probeAnswer(): Long = 43\n"
const PROPERTY := "probe_label"
const METHOD := "probe_answer"
const SAVED_VALUE := "saved-value"
# A real Gradle build of the scripts: a warm daemon takes seconds, a cold one up to a few minutes.
const BUILD_WAIT_SEC := 300.0
const RELOAD_WAIT_SEC := 30.0
# The runtime looks at kanama-scripts.jar every 500 ms (KanamaHotReload.CHECK_INTERVAL_NANOS): after a failed build
# the probe waits a few of those before it holds the old class to being usable, so a jar the build broke is seen.
const AFTER_FAILED_BUILD_SEC := 1.6
const SCRIPTS_JAR := "res://addons/kanama/kanama-scripts.jar"
const TIMEOUT_SEC := 1200.0

var _kanama: EditorPlugin
var _light: Button
var _done := false
var _any_failed := false
var _probe := ""
var _probe_failures := PackedStringArray()


func _enter_tree() -> void:
    _run.call_deferred()


func _run() -> void:
    get_tree().create_timer(TIMEOUT_SEC).timeout.connect(func() -> void:
        if not _done:
            printerr("%s FAIL: the probes did not finish within %d s (in %s)" % [TAG, int(TIMEOUT_SEC), _probe])
            _any_failed = true
            _finish())
    for i in 200:
        _kanama = _find_kanama_plugin()
        if _kanama != null:
            break
        await get_tree().process_frame
    _light = _find_control("KanamaBuildStatus") as Button
    if _kanama == null or _light == null:
        printerr("%s FAIL: the Kanama Tools plugin or its status light (KanamaBuildStatus) is not loaded" % TAG)
        _any_failed = true
        _finish()
        return
    if not _kanama.is_build_on_save_enabled():
        printerr("%s FAIL: build on save is off in a fresh editor" % TAG)
        _any_failed = true
        _finish()
        return
    # The project starts built (editor_probe_smoke.sh ran installAddonJar): green, and the script loads.
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "current", 10.0)
    if _kanama.get_build_status() != "current" or not _has_property(_script(), "speed"):
        printerr("%s FAIL: the probe project does not start built: light %s, ProbeScript properties %s" % [TAG, _kanama.get_build_status(), _property_names(_script())])
        _any_failed = true
        _finish()
        return
    EditorInterface.open_scene_from_path(SCENE_PATH)
    await _wait_for(func() -> bool: return _probe_node() != null, 10.0)
    print("%s the probe scene is open; light %s" % [TAG, _kanama.get_build_status()])

    # 1. Edit the .kt (a new @Export property and a new method) and save: build on save runs the real build.
    _begin("probe 1: a saved .kt edit is built by build on save and the light ends green")
    await _edit_source(MARKER, NEW_MEMBERS + MARKER)
    var build_ok := await _expect_build("current", "the edit")
    _end()

    # 2. The class list: the reloaded build's members are on the script the editor loaded for the .kt file.
    _begin("probe 2: the class list has the new @Export property and method")
    if build_ok:
        await _wait_for(func() -> bool: return _has_property(_script(), PROPERTY), RELOAD_WAIT_SEC)
    var script := _script()
    _expect(_has_property(script, PROPERTY), "ProbeScript's script property list lacks %s after the build: %s" % [PROPERTY, _property_names(script)])
    _expect(_has_method(script, METHOD), "ProbeScript's script method list lacks %s after the build: %s" % [METHOD, _method_names(script)])
    _expect(int(Engine.get_meta(_loaded_jar_meta(), -2)) == FileAccess.get_modified_time(ProjectSettings.globalize_path(SCRIPTS_JAR)),
        "the runtime's Engine metadata does not name the jar it loaded (the plugin waits on it before it reloads the scene): %s" % _reload_state())
    _expect(_global_class_path("ProbeScript") == SCRIPT_PATH, "the global class list does not name ProbeScript at %s: %s" % [SCRIPT_PATH, _global_class_path("ProbeScript")])
    _end()

    # 3. Node members: the scene's node shows the new property, with its default, as the inspector lists it.
    _begin("probe 3: the node in the open scene shows the new @Export property with its default")
    # The new code runs once the runtime has loaded the jar and the plugin has reloaded the scene: a bounded poll,
    # not one read (a slow host builds for 20 s and reloads after the light turned green).
    await _wait_for(func() -> bool: return _probe_node() != null and _node_has_editor_property(_probe_node(), PROPERTY) and _answer(_probe_node()) == 42, RELOAD_WAIT_SEC)
    var node := _probe_node()
    _expect(node != null, "the open scene has no Probe node")
    if node != null:
        _expect(_node_has_editor_property(node, PROPERTY), "the Probe node's property list has no editor-visible %s: %s" % [PROPERTY, _node_script_properties(node)])
        _expect(node.get(PROPERTY) == "probe-default", "the Probe node's %s is %s, expected the default probe-default" % [PROPERTY, node.get(PROPERTY)])
        _expect(script != null and script.get_property_default_value(PROPERTY) == "probe-default", "ProbeScript's default for %s is %s" % [PROPERTY, script.get_property_default_value(PROPERTY) if script != null else null])
        _expect(_answer(node) == 42, "the Probe node's %s() returns %s, expected 42 (the new build's code); %s" % [METHOD, _answer(node), _reload_state()])
        EditorInterface.edit_node(node)
        await _wait_for(func() -> bool: return _inspector_shows(PROPERTY), 5.0)
        _expect(_inspector_shows(PROPERTY), "the inspector shows no %s for the Probe node" % PROPERTY)
    _end()

    # 4. The saved scene: set the property, save, reload; the value comes back from the .tscn.
    _begin("probe 4: a value set on the new property round-trips through the saved scene")
    node = _probe_node()
    if node != null:
        node.set(PROPERTY, SAVED_VALUE)
        EditorInterface.mark_scene_as_unsaved()
        var saved := EditorInterface.save_scene()
        _expect(saved == OK, "saving the probe scene returned %s" % error_string(saved))
        var text := FileAccess.get_file_as_string(SCENE_PATH)
        _expect(text.contains('%s = "%s"' % [PROPERTY, SAVED_VALUE]), "the saved probe.tscn does not hold %s = \"%s\":\n%s" % [PROPERTY, SAVED_VALUE, text])
        EditorInterface.reload_scene_from_path(SCENE_PATH)
        await _frames(5)
        await _wait_for(func() -> bool: return _probe_node() != null, 10.0)
        var reloaded := _probe_node()
        _expect(reloaded != null and reloaded.get(PROPERTY) == SAVED_VALUE, "after reloading the scene the Probe node's %s is %s, expected %s" % [PROPERTY, reloaded.get(PROPERTY) if reloaded != null else null, SAVED_VALUE])
        var fresh := _instantiate_fresh()
        _expect(fresh != null and fresh.get(PROPERTY) == SAVED_VALUE, "a fresh instance of the saved scene has %s = %s, expected %s" % [PROPERTY, fresh.get(PROPERTY) if fresh != null else null, SAVED_VALUE])
    else:
        _expect(false, "the open scene has no Probe node")
    _end()

    # 5. A broken edit: red, the last good build stays usable; the fix: green, and the fixed code runs.
    _begin("probe 5: a compile error turns the light red and keeps the old class usable; the fix turns it green")
    var jar_before := _jar_state()
    await _edit_source(MARKER, BROKEN_MEMBER + MARKER)
    await _expect_build("failed", "the broken edit")
    var output := String(_kanama._last_build.get("output", ""))
    _expect(output.contains("probeNoSuchValue"), "the failed build's output does not name the compile error: %s" % output.right(1500))
    await get_tree().create_timer(AFTER_FAILED_BUILD_SEC).timeout
    var jar_after := _jar_state()
    _expect(jar_after == jar_before, "the failed build changed kanama-scripts.jar (modified time, size, MD5): %s before, %s after" % [jar_before, jar_after])
    script = _script()
    _expect(_has_property(script, PROPERTY) and _has_method(script, METHOD), "after the failed build ProbeScript lost its members: %s %s" % [_property_names(script), _method_names(script)])
    node = _probe_node()
    _expect(node != null and _answer(node) == 42, "after the failed build the Probe node's %s() returns %s, expected 42 (the last good build)" % [METHOD, _answer(node) if node != null else null])
    var old_fresh := _instantiate_fresh()
    _expect(old_fresh != null and old_fresh.get(PROPERTY) == SAVED_VALUE and _answer(old_fresh) == 42,
        "after the failed build a fresh instance of the scene has %s = %s and %s() = %s" % [PROPERTY, old_fresh.get(PROPERTY) if old_fresh != null else null, METHOD, _answer(old_fresh) if old_fresh != null else null])
    await _write_source(_read_source().replace(BROKEN_MEMBER, "").replace(NEW_MEMBERS, FIXED_MEMBERS))
    if await _expect_build("current", "the fix"):
        await _wait_for(func() -> bool: return _probe_node() != null and _answer(_probe_node()) == 43, RELOAD_WAIT_SEC)
    node = _probe_node()
    _expect(node != null and _answer(node) == 43, "after the fix the Probe node's %s() returns %s, expected 43 (the fixed build); %s; a fresh instance returns %s" % [METHOD, _answer(node) if node != null else null, _reload_state(), _answer(_instantiate_fresh())])
    _expect(node != null and node.get(PROPERTY) == SAVED_VALUE, "after the fix the Probe node's %s is %s, expected the saved %s" % [PROPERTY, node.get(PROPERTY) if node != null else null, SAVED_VALUE])
    _end()
    _finish()


## Waits for the build a save starts and for its end; true when the light ends `expected`.
func _expect_build(expected: String, what: String) -> bool:
    await _wait_for(func() -> bool: return _kanama.get_build_status() == "building", 20.0)
    if _kanama.get_build_status() != "building":
        _expect(false, "saving %s started no build (light %s)" % [what, _kanama.get_build_status()])
        return false
    var started := Time.get_ticks_msec()
    await _wait_for(func() -> bool: return _kanama.get_build_status() != "building", BUILD_WAIT_SEC)
    var status: String = _kanama.get_build_status()
    print("%s the build after %s took %.1f s and ended %s" % [TAG, what, (Time.get_ticks_msec() - started) / 1000.0, status])
    var output := String(_kanama._last_build.get("output", ""))
    _expect(output.contains("installAddonJar"), "the build after %s was not the checkout's installAddonJar: %s" % [what, output.left(500)])
    _expect(status == expected, "the status light after the build of %s is %s, expected %s:\n%s" % [what, status, expected, output.right(2000)])
    var shown := String(_light.get_meta("kanama_status", ""))
    _expect(shown == status, "the status light widget shows %s, the plugin says %s" % [shown, status])
    _expect((_light.get_theme_color("icon_normal_color") as Color).is_equal_approx(_kanama.status_color(expected)), "the status light after the build of %s is not coloured for %s" % [what, expected])
    return status == expected


func _begin(probe: String) -> void:
    _probe = probe
    _probe_failures = PackedStringArray()


func _end() -> void:
    if _probe_failures.is_empty():
        print("%s PASS %s" % [TAG, _probe])
        return
    _any_failed = true
    for failure in _probe_failures:
        printerr("%s FAIL %s: %s" % [TAG, _probe.get_slice(":", 0), failure])


func _expect(ok: bool, failure: String) -> void:
    if not ok:
        _probe_failures.append(failure)


func _script() -> Script:
    return load(SCRIPT_PATH) as Script


func _has_property(script: Script, name: String) -> bool:
    return script != null and _property_names(script).has(name)


func _has_method(script: Script, name: String) -> bool:
    return script != null and _method_names(script).has(name)


func _property_names(script: Script) -> PackedStringArray:
    var names := PackedStringArray()
    if script != null:
        for property in script.get_script_property_list():
            names.append(String(property["name"]))
    return names


func _method_names(script: Script) -> PackedStringArray:
    var names := PackedStringArray()
    if script != null:
        for method in script.get_script_method_list():
            names.append(String(method["name"]))
    return names


## The Engine metadata key the Kanama Tools plugin waits on (editor_probe_smoke.sh holds it equal to the runtime's).
func _loaded_jar_meta() -> String:
    return String(_kanama.get_script().get_script_constant_map().get("LOADED_JAR_META", "<the plugin has no LOADED_JAR_META>"))


## What the runtime says it loaded against the jar on disk, for a failure message.
func _reload_state() -> String:
    return "the runtime loaded the jar of %s, the jar on disk is of %s" % [Engine.get_meta(_loaded_jar_meta(), "<no metadata>"), FileAccess.get_modified_time(ProjectSettings.globalize_path(SCRIPTS_JAR))]


## The node's probe_answer(), or null when it has no such method (no error printed).
func _answer(node: Node) -> Variant:
    if node == null or not node.has_method(METHOD):
        return null
    return node.call(METHOD)


func _global_class_path(name: String) -> String:
    for entry in ProjectSettings.get_global_class_list():
        if String(entry["class"]) == name:
            return String(entry["path"])
    return ""


func _probe_node() -> Node:
    var root := EditorInterface.get_edited_scene_root()
    if root == null or root.scene_file_path != SCENE_PATH:
        return null
    return root.get_node_or_null("Probe")


func _node_has_editor_property(node: Node, name: String) -> bool:
    for property in node.get_property_list():
        if String(property["name"]) == name and int(property["usage"]) & PROPERTY_USAGE_EDITOR:
            return true
    return false


func _node_script_properties(node: Node) -> PackedStringArray:
    var names := PackedStringArray()
    var in_script := false
    for property in node.get_property_list():
        if int(property["usage"]) & PROPERTY_USAGE_CATEGORY:
            in_script = String(property["hint_string"]).ends_with(".kt") or String(property["name"]).ends_with(".kt")
        elif in_script:
            names.append(String(property["name"]))
    return names


func _inspector_shows(name: String) -> bool:
    var inspector := EditorInterface.get_inspector()
    return inspector != null and _find_editor_property(inspector, name)


func _find_editor_property(parent: Node, name: String) -> bool:
    for child in parent.get_children():
        if child is EditorProperty and String((child as EditorProperty).get_edited_property()) == name:
            return true
        if _find_editor_property(child, name):
            return true
    return false


func _instantiate_fresh() -> Node:
    var packed := ResourceLoader.load(SCENE_PATH, "PackedScene", ResourceLoader.CACHE_MODE_IGNORE) as PackedScene
    if packed == null:
        return null
    var root := packed.instantiate()
    if root == null:
        return null
    var probe := root.get_node_or_null("Probe")
    root.queue_free()
    return probe


## The scripts jar's modified time, size and MD5 (empty values when it is missing).
func _jar_state() -> Array:
    var path := ProjectSettings.globalize_path(SCRIPTS_JAR)
    if not FileAccess.file_exists(path):
        return [0, -1, ""]
    var file := FileAccess.open(path, FileAccess.READ)
    var size := file.get_length() if file != null else -1
    if file != null:
        file.close()
    return [FileAccess.get_modified_time(path), size, FileAccess.get_md5(path)]


func _read_source() -> String:
    return FileAccess.get_file_as_string(ProjectSettings.globalize_path(SCRIPT_PATH))


func _edit_source(old: String, new: String) -> void:
    var text := _read_source()
    if not text.contains(old):
        _expect(false, "ProbeScript.kt does not contain %s" % old.strip_edges())
        return
    await _write_source(text.replace(old, new))


## A save as an IDE does it: the file changes on disk. A second later than the last write, so the watcher's
## modification time (seconds) changes.
func _write_source(text: String) -> void:
    await get_tree().create_timer(1.1).timeout
    var file := FileAccess.open(ProjectSettings.globalize_path(SCRIPT_PATH), FileAccess.WRITE)
    if file == null:
        _expect(false, "cannot write %s" % SCRIPT_PATH)
        return
    file.store_string(text)
    file.close()


func _find_kanama_plugin() -> EditorPlugin:
    for node in get_parent().get_children():
        var plugin_script: Script = node.get_script()
        if node is EditorPlugin and plugin_script != null and plugin_script.resource_path == KANAMA_PLUGIN:
            return node
    return null


func _find_control(node_name: String) -> Node:
    return EditorInterface.get_base_control().get_tree().root.find_child(node_name, true, false)


func _frames(count: int) -> void:
    for i in count:
        await get_tree().process_frame


func _wait_for(condition: Callable, seconds: float) -> void:
    var deadline := Time.get_ticks_msec() + int(seconds * 1000.0)
    while not condition.call() and Time.get_ticks_msec() < deadline:
        await get_tree().process_frame


func _finish() -> void:
    if _done:
        return
    _done = true
    if not _any_failed:
        print("%s PASS" % TAG)
        get_tree().quit(0)
        return
    printerr("%s FAIL" % TAG)
    get_tree().quit(1)
