extends SceneTree
## check_export_guard.gd: runs the editor export guard's check (addons/kanama_tools/kanama_export_guard.gd)
## on the project it is started in, without exporting. Exit 0 = every .kt script a scene, a resource or an
## autoload uses binds in this runtime; exit 1 = at least one does not (the guard's message is printed,
## starting with the same `Kanama: Export stopped` line an export prints). Without an export preset it
## checks every scene and resource under res://. It also holds every Android preset in res://export_presets.cfg
## to the guard's Min SDK rule (ANDROID_MIN_SDK): exit 1 names the preset and what to set. First it runs the
## unit cases of the guard's R8 compat-mode writer (with_r8_compat_mode, keep_r8_compat_mode and the
## preset's Gradle Build Directory), which a minified Android export relies on: exit 1 names each failed case.
##
## Usage: godot --headless --path <project with addons/kanama + addons/kanama_tools> --script <this file>
const Guard := preload("res://addons/kanama_tools/kanama_export_guard.gd")
const TAG := "[export_guard]"


func _initialize() -> void:
    quit(_run())


func _run() -> int:
    var r8_failures := r8_compat_failures()
    if not r8_failures.is_empty():
        for line in r8_failures:
            printerr("%s FAIL r8 compat: %s" % [TAG, line])
        printerr("%s FAIL: %d R8 compat-mode case(s) failed" % [TAG, r8_failures.size()])
        return 1
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


## The R8 compat-mode cases: one line per case that fails. A minified Android export reads its
## gradle.properties from the preset's Gradle Build Directory, and must end with exactly one
## `android.enableR8.fullMode=false` whatever the template held before.
static func r8_compat_failures() -> PackedStringArray:
    var failures := PackedStringArray()
    var line := "%s=false" % Guard.R8_FULL_MODE_PROPERTY
    var cases := [
        ["file absent (empty text)", "", line + "\n"],
        ["user lines preserved", "org.gradle.jvmargs=-Xmx4536m\n# a comment\nandroid.useAndroidX=true\n",
            "org.gradle.jvmargs=-Xmx4536m\n# a comment\nandroid.useAndroidX=true\n" + line + "\n"],
        ["already present (idempotent)", "a=1\n" + line + "\nb=2\n", "a=1\n" + line + "\nb=2\n"],
        ["=true becomes false, not duplicated", "a=1\nandroid.enableR8.fullMode=true\nb=2\n", "a=1\nb=2\n" + line + "\n"],
        ["duplicates collapse to one false", line + "\na=1\n" + line + "\n", "a=1\n" + line + "\n"],
        ["CRLF line endings kept", "a=1\r\nandroid.enableR8.fullMode = true\r\n", "a=1\r\n" + line + "\r\n"],
        ["CRLF already present (idempotent)", "a=1\r\n" + line + "\r\n", "a=1\r\n" + line + "\r\n"],
        ["commented-out setting kept", "#android.enableR8.fullMode=true\n", "#android.enableR8.fullMode=true\n" + line + "\n"],
        ["similar key kept", "android.enableR8.fullModeExtra=true\n", "android.enableR8.fullModeExtra=true\n" + line + "\n"],
    ]
    for c in cases:
        var got := Guard.with_r8_compat_mode(c[1])
        if got != c[2]:
            failures.append("with_r8_compat_mode, %s: got %s, expected %s" % [c[0], got.c_escape(), String(c[2]).c_escape()])
        elif Guard.with_r8_compat_mode(got) != got:
            failures.append("with_r8_compat_mode, %s: a second pass changed %s" % [c[0], got.c_escape()])

    # The preset's Gradle Build Directory: empty means res://android; Godot builds in <dir>/build.
    var paths := [
        [null, "res://android/build/gradle.properties"],
        ["", "res://android/build/gradle.properties"],
        ["res://android", "res://android/build/gradle.properties"],
        ["res://platform/android_custom", "res://platform/android_custom/build/gradle.properties"],
    ]
    for p in paths:
        var got := Guard.android_gradle_properties_path(p[0])
        if got != p[1]:
            failures.append("android_gradle_properties_path(%s): got %s, expected %s" % [str(p[0]), got, p[1]])

    # keep_r8_compat_mode on real files: a custom build directory gets the line, the default one is left
    # alone, and a missing folder or file stops with a message that names it.
    var root := OS.get_temp_dir().path_join("kanama_guard_r8_%d" % OS.get_process_id())
    var custom := root.path_join("custom_android")
    var custom_props := Guard.android_gradle_properties_path(custom)
    var default_props := root.path_join("android/build/gradle.properties")
    DirAccess.make_dir_recursive_absolute(custom_props.get_base_dir())
    DirAccess.make_dir_recursive_absolute(default_props.get_base_dir())
    _write(custom_props, "android.useAndroidX=true\r\nandroid.enableR8.fullMode=true\r\n")
    _write(default_props, "android.useAndroidX=true\n")
    var problem := Guard.keep_r8_compat_mode(custom_props)
    if not problem.is_empty():
        failures.append("keep_r8_compat_mode, custom build directory: reported \"%s\"" % problem)
    elif FileAccess.get_file_as_string(custom_props) != "android.useAndroidX=true\r\n" + line + "\r\n":
        failures.append("keep_r8_compat_mode, custom build directory: %s holds %s" % [custom_props, FileAccess.get_file_as_string(custom_props).c_escape()])
    if FileAccess.get_file_as_string(default_props) != "android.useAndroidX=true\n":
        failures.append("keep_r8_compat_mode, custom build directory: the default template %s was changed" % default_props)
    var missing_dir := Guard.android_gradle_properties_path(root.path_join("nowhere"))
    problem = Guard.keep_r8_compat_mode(missing_dir)
    if not problem.contains(missing_dir.get_base_dir()) or not problem.contains("does not exist"):
        failures.append("keep_r8_compat_mode, missing build folder: reported \"%s\", expected it to name %s as missing" % [problem, missing_dir.get_base_dir()])
    if FileAccess.file_exists(missing_dir):
        failures.append("keep_r8_compat_mode, missing build folder: wrote %s" % missing_dir)
    DirAccess.remove_absolute(default_props)
    problem = Guard.keep_r8_compat_mode(default_props)
    if not problem.contains(default_props) or not problem.contains("is missing"):
        failures.append("keep_r8_compat_mode, missing gradle.properties: reported \"%s\", expected it to name %s as missing" % [problem, default_props])
    if FileAccess.file_exists(default_props):
        failures.append("keep_r8_compat_mode, missing gradle.properties: created %s (a template without it is incomplete)" % default_props)
    for file in [custom_props, default_props]:
        DirAccess.remove_absolute(file)
    for dir in [custom_props.get_base_dir(), custom, default_props.get_base_dir(), root.path_join("android"), root]:
        DirAccess.remove_absolute(dir)
    return failures


static func _write(path: String, text: String) -> void:
    var file := FileAccess.open(path, FileAccess.WRITE)
    if file != null:
        file.store_string(text)
