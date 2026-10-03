# check_editor_jdk_resolution.gd -- the Kanama Tools plugin picks the JDK Build Scripts runs Gradle with (kanama#277).
#
# Run by scripts/tool_smoke.sh (`godot --headless --path <scratch project> --script ...`) against
# a scratch project that carries a copy of addons/kanama_tools. It exercises the plugin's static
# resolution functions (res://addons/kanama_tools/plugin.gd) with fake JDK homes built under
# $KANAMA_JDK_TEST_TMP, then the real-environment entry point with JAVA_HOME removed from the
# process. Prints "[jdk_test] ok <case>" per case and "[jdk_test] PASS" last; any failure prints
# "[jdk_test] FAIL <case>" and exits 1.
extends SceneTree

var _plugin: GDScript
var _failures := 0


func _init() -> void:
    var tmp := OS.get_environment("KANAMA_JDK_TEST_TMP")
    if tmp.is_empty():
        printerr("[jdk_test] FAIL KANAMA_JDK_TEST_TMP is not set")
        quit(2)
        return
    _plugin = load("res://addons/kanama_tools/plugin.gd")
    if _plugin == null:
        printerr("[jdk_test] FAIL cannot load res://addons/kanama_tools/plugin.gd")
        quit(2)
        return
    for required in ["resolve_build_jdk", "resolve_build_jdk_from_environment", "parse_java_version", "compare_version_parts", "execute_with_java_home"]:
        if not _plugin.has_method(required):
            printerr("[jdk_test] FAIL the plugin has no %s(): Build Scripts does not resolve a JDK" % required)
            quit(1)
            return

    var jdk17 := _fake_jdk(tmp, "jdk-17", "17.0.12")
    var jdk21 := _fake_jdk(tmp, "jdk-21", "21.0.4")
    var jdk25 := _fake_jdk(tmp, "jdk-25", "25.0.4.1")
    var jdk25b := _fake_jdk(tmp, "jdk-25-newer-patch", "25.0.10")
    var jdk26 := _fake_jdk(tmp, "jdk-26", "26-ea")
    var jdk8 := _fake_jdk(tmp, "jdk-8", "1.8.0_302")
    var not_a_jdk := tmp.path_join("not-a-jdk")
    DirAccess.make_dir_recursive_absolute(not_a_jdk)

    # --- version parsing / ordering
    _expect("parse 25.0.4.1", _plugin.parse_java_version("25.0.4.1") == [25, 0, 4, 1])
    _expect("parse 26-ea", _plugin.parse_java_version("26-ea") == [26])
    _expect("parse 1.8.0_302 is JDK 8", _plugin.parse_java_version("1.8.0_302") == [8, 0, 302])
    _expect("parse empty", _plugin.parse_java_version("").is_empty())
    _expect("25.0.10 > 25.0.4.1", _plugin.compare_version_parts([25, 0, 10], [25, 0, 4, 1]) == 1)
    _expect("26 > 25.9", _plugin.compare_version_parts([26], [25, 9]) == 1)
    _expect("25 == 25.0", _plugin.compare_version_parts([25], [25, 0]) == 0)

    # --- the project setting wins over JAVA_HOME and the install locations
    var r: Dictionary = _plugin.resolve_build_jdk(jdk25, jdk26, [jdk26])
    _expect("setting wins: ok", bool(r["ok"]) and r["home"] == jdk25 and String(r["source"]).contains("kanama/build/jdk_path"))
    # --- an explicit setting that is wrong is an error naming the setting, never a silent fall-through
    r = _plugin.resolve_build_jdk(jdk17, jdk25, [jdk26])
    _expect("setting too old: error", not bool(r["ok"]) and String(r["message"]).contains("kanama/build/jdk_path") and String(r["message"]).contains("17.0.12"))
    r = _plugin.resolve_build_jdk(not_a_jdk, jdk25, [])
    _expect("setting not a JDK: error", not bool(r["ok"]) and String(r["message"]).contains("not a usable JDK"))
    r = _plugin.resolve_build_jdk(jdk8, "", [])
    _expect("setting is JDK 8 (release 1.8): error", not bool(r["ok"]) and String(r["message"]).contains("1.8.0_302"))
    # --- empty setting: JAVA_HOME
    r = _plugin.resolve_build_jdk("", jdk25, [jdk26])
    _expect("JAVA_HOME used", bool(r["ok"]) and r["home"] == jdk25 and r["source"] == "JAVA_HOME")
    # --- JAVA_HOME on an old JDK is skipped (and reported); the newest JDK 25+ install wins
    r = _plugin.resolve_build_jdk("", jdk17, [jdk21, jdk25, jdk26, jdk25b, not_a_jdk])
    _expect("old JAVA_HOME skipped, newest install wins", bool(r["ok"]) and r["home"] == jdk26 and String(r["source"]).contains("install location"))
    _expect("old JAVA_HOME reported", String(" ".join(r["notes"])).contains("17.0.12"))
    r = _plugin.resolve_build_jdk("", "", [jdk25, jdk25b, jdk21])
    _expect("newest patch of the same major", bool(r["ok"]) and r["home"] == jdk25b)
    # --- nothing usable: one clear error that names the setting and JAVA_HOME
    r = _plugin.resolve_build_jdk("", "", [jdk17, jdk21, not_a_jdk])
    var msg := String(r["message"])
    _expect("none found: error", not bool(r["ok"]) and msg.contains("kanama/build/jdk_path") and msg.contains("JAVA_HOME") and msg.contains("JDK 25"))
    print("[jdk_test] none-found message:\n" + msg)

    # --- the real entry point, JAVA_HOME removed from this process, the setting set
    OS.unset_environment("JAVA_HOME")
    ProjectSettings.set_setting("kanama/build/jdk_path", jdk25)
    r = _plugin.resolve_build_jdk_from_environment()
    _expect("env unset + setting set -> the setting", bool(r["ok"]) and r["home"] == jdk25)
    # Setting empty and JAVA_HOME unset: whatever this host has installed, or the clear error.
    ProjectSettings.set_setting("kanama/build/jdk_path", "")
    r = _plugin.resolve_build_jdk_from_environment()
    if bool(r["ok"]):
        _expect("nothing set: resolved from install locations to JDK 25+", int(r["major"]) >= 25)
        print("[jdk_test] nothing set resolved to %s (JDK %s, %s)" % [r["home"], r["version"], r["source"]])
    else:
        _expect("nothing set: clear error", String(r["message"]).contains("kanama/build/jdk_path"))
        print("[jdk_test] nothing set, no JDK 25+ in the install locations of this host; error text:\n" + String(r["message"]))

    # --- the process JAVA_HOME is set for the child and restored after
    _check_execute_restores_env(jdk25)

    if _failures > 0:
        printerr("[jdk_test] FAIL %d case(s)" % _failures)
        quit(1)
        return
    print("[jdk_test] PASS")
    quit(0)


func _check_execute_restores_env(jdk_home: String) -> void:
    var output: Array = []
    var code: int
    var windows := OS.get_name() == "Windows"
    var exe := "cmd" if windows else "/bin/sh"
    var args: Array = ["/c", "echo %JAVA_HOME%"] if windows else ["-c", "echo \"$JAVA_HOME\""]
    OS.unset_environment("JAVA_HOME")
    code = _plugin.execute_with_java_home(jdk_home, exe, args, output)
    var seen := String("".join(output)).strip_edges()
    _expect("child sees JAVA_HOME", code == 0 and seen.replace("\\", "/") == jdk_home.replace("\\", "/"))
    _expect("JAVA_HOME restored to unset", not OS.has_environment("JAVA_HOME"))
    OS.set_environment("JAVA_HOME", "/previous/value")
    output.clear()
    _plugin.execute_with_java_home(jdk_home, exe, args, output)
    _expect("JAVA_HOME restored to previous value", OS.get_environment("JAVA_HOME") == "/previous/value")
    OS.unset_environment("JAVA_HOME")


# A directory shaped like a JDK home: bin/java(.exe), and a release file with JAVA_VERSION.
func _fake_jdk(root: String, name: String, version: String) -> String:
    var home := root.path_join(name)
    DirAccess.make_dir_recursive_absolute(home.path_join("bin"))
    var java_name := "java.exe" if OS.get_name() == "Windows" else "java"
    var java := FileAccess.open(home.path_join("bin").path_join(java_name), FileAccess.WRITE)
    java.store_string("#!/bin/sh\n")
    java.close()
    var release := FileAccess.open(home.path_join("release"), FileAccess.WRITE)
    release.store_string("IMPLEMENTOR=\"Kanama test\"\nJAVA_VERSION=\"%s\"\n" % version)
    release.close()
    return home


func _expect(name: String, condition: bool) -> void:
    if condition:
        print("[jdk_test] ok %s" % name)
    else:
        printerr("[jdk_test] FAIL %s" % name)
        _failures += 1
