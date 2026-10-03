# check_editor_jdk_resolution.gd -- the Kanama Tools plugin picks the JDK Build Scripts runs Gradle with (kanama#277).
#
# Run by scripts/tool_smoke.sh (`godot --headless --path <scratch project> --script ...`) against
# a scratch project that carries a copy of addons/kanama_tools. It exercises the plugin's static
# resolution functions (res://addons/kanama_tools/plugin.gd) with fake JDK homes built under
# $KANAMA_JDK_TEST_TMP, then the real-environment entry points with JAVA_HOME removed from the
# process (the setting is passed as an override: EditorSettings exist only in a running editor,
# which scripts/tool_smoke.sh's headless-editor run and the end-to-end check cover). Prints "[jdk_test] ok <case>" per case and "[jdk_test] PASS" last; any failure prints
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
    for required in ["resolve_build_jdk", "resolve_build_jdk_from_environment", "parse_java_version", "compare_version_parts", "execute_with_java_home", "detect_desktop_jvm", "resolve_runtime_jdk", "resolve_runtime_jdk_from_environment", "write_runtime_jdk_hint", "jdk_install_candidates"]:
        if not _plugin.has_method(required):
            printerr("[jdk_test] FAIL the plugin has no %s()" % required)
            quit(1)
            return

    var jdk17 := _fake_jdk(tmp, "jdk-17", "17.0.12")
    var jdk21 := _fake_jdk(tmp, "jdk-21", "21.0.4")
    var jdk25 := _fake_jdk(tmp, "jdk-25", "25.0.4.1")
    var jdk25b := _fake_jdk(tmp, "jdk-25-newer-patch", "25.0.10")
    var jdk26 := _fake_jdk(tmp, "jdk-26", "26-ea")
    var jdk25_no_libjvm := _fake_jdk(tmp, "jdk-25-no-libjvm", "25.0.4.1", false)
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
    _expect("old JAVA_HOME skipped, best install wins (GA 25.0.10 over EA 26)", bool(r["ok"]) and r["home"] == jdk25b and String(r["source"]).contains("install locations"))
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
    r = _plugin.resolve_build_jdk_from_environment(jdk25)
    _expect("env unset + setting set -> the setting", bool(r["ok"]) and r["home"] == jdk25)
    # Setting empty and JAVA_HOME unset: whatever this host has installed, or the clear error.
    r = _plugin.resolve_build_jdk_from_environment("")
    if bool(r["ok"]):
        _expect("nothing set: resolved from install locations to JDK 25+", int(r["major"]) >= 25)
        print("[jdk_test] nothing set resolved to %s (JDK %s, %s)" % [r["home"], r["version"], r["source"]])
    else:
        _expect("nothing set: clear error", String(r["message"]).contains("kanama/build/jdk_path"))
        print("[jdk_test] nothing set, no JDK 25+ in the install locations of this host; error text:\n" + String(r["message"]))

    # --- ranking among install locations: GA before EA, exactly 25 before newer, then newer, then smaller path
    var jdk26ga := _fake_jdk(tmp, "jdk-26-ga", "26.0.1")
    var jdk27ea := _fake_jdk(tmp, "jdk-27-ea", "27-ea")
    var jdk25ea := _fake_jdk(tmp, "jdk-25-ea", "25-ea")
    r = _plugin.resolve_build_jdk("", "", [jdk26ga, jdk25])
    _expect("exactly 25 preferred over a newer GA", r["home"] == jdk25)
    r = _plugin.resolve_build_jdk("", "", [jdk26, jdk26ga])
    _expect("GA 26.0.1 beats EA 26-ea", r["home"] == jdk26ga)
    r = _plugin.resolve_build_jdk("", "", [jdk27ea, jdk26ga])
    _expect("GA 26 beats EA 27", r["home"] == jdk26ga)
    r = _plugin.resolve_build_jdk("", "", [jdk25ea, jdk26ga])
    _expect("GA 26 beats EA 25", r["home"] == jdk26ga)
    r = _plugin.resolve_build_jdk("", "", [jdk27ea, jdk25ea])
    _expect("only EA: exactly 25 first", r["home"] == jdk25ea)
    r = _plugin.resolve_build_jdk("", "", [jdk26ga, jdk26ga.replace("jdk-26-ga", "jdk-26-ga")])
    _expect("a duplicate candidate is harmless", r["home"] == jdk26ga)

    # --- build mode needs a full JDK (include/jni.h); runtime mode only libjvm
    var jre25 := _fake_jdk(tmp, "jre-25", "25.0.4", true, false)
    r = _plugin.resolve_build_jdk("", "", [jre25])
    _expect("build: a JRE (no include/jni.h) is not a build JDK", not bool(r["ok"]) and String(r["message"]).contains("include/jni.h"))
    r = _plugin.resolve_runtime_jdk("", "", [jre25])
    _expect("runtime: a JRE with libjvm is fine", bool(r["ok"]) and r["home"] == jre25)
    r = _plugin.resolve_build_jdk(jre25, "", [jdk25])
    _expect("build: an explicit JRE setting is an error", not bool(r["ok"]) and String(r["message"]).contains("include/jni.h"))

    # --- the setting must be an absolute path; ~ is expanded by the environment entry point
    r = _plugin.resolve_build_jdk("jdks/jdk-25", "", [jdk25])
    _expect("relative setting rejected", not bool(r["ok"]) and String(r["message"]).contains("absolute path"))
    r = _plugin.resolve_build_jdk("res://jdk", "", [jdk25])
    _expect("res:// setting rejected", not bool(r["ok"]) and String(r["message"]).contains("absolute path"))
    var real_home := OS.get_environment("HOME")
    OS.set_environment("HOME", tmp)
    r = _plugin.resolve_build_jdk_from_environment("~/jdk-25")
    OS.set_environment("HOME", real_home)
    if OS.get_name() != "Windows":
        _expect("~ in the setting expands to the home directory", bool(r["ok"]) and r["home"] == jdk25)

    # --- the message text differs by purpose; the startup text is not about Build Scripts
    r = _plugin.resolve_runtime_jdk("", "", [])
    _expect("runtime message is not about Build Scripts", not String(r["message"]).contains("Build Scripts") and String(r["message"]).contains("restart the editor"))
    r = _plugin.resolve_build_jdk("", "", [])
    _expect("build message names Build Scripts", String(r["message"]).contains("Build Scripts"))
    # --- success carries no skip noise in the message; failure lists what was skipped
    r = _plugin.resolve_build_jdk("", jdk17, [jdk25])
    _expect("success has an empty message", bool(r["ok"]) and String(r["message"]).is_empty())

    # --- KANAMA_JDK_SEARCH_DIRS replaces the location table (the parents of JDK homes)
    var cands: Array = _plugin.jdk_install_candidates("Linux", "/nonexistent-home", "", tmp)
    _expect("search dirs: children of the dir, sorted", jdk25 in cands and jdk17 in cands and cands.find(jdk17) < cands.find(jdk25))
    cands = _plugin.jdk_install_candidates("Linux", "/nonexistent-home", "", "")
    _expect("no search dirs: the table (none of the fake JDKs)", not (jdk25 in cands))

    # --- the runtime hint file bootstrap.c reads: written for a valid explicit setting, removed otherwise
    var project := tmp.path_join("hint-project")
    _plugin.write_runtime_jdk_hint(project, jdk25)
    var hint_file := project.path_join(".godot/kanama_jdk_home")
    _expect("hint file written", FileAccess.file_exists(hint_file) and FileAccess.get_file_as_string(hint_file).strip_edges() == jdk25)
    _plugin.write_runtime_jdk_hint(project, "")
    _expect("hint file removed", not FileAccess.file_exists(hint_file))

    # --- the libjvm preflight shares the resolution (no "libjvm not found" while a JDK 25+ resolves)
    var os_name := OS.get_name()
    r = _plugin.resolve_runtime_jdk("", "", [jdk17, jdk25])
    var pf: Dictionary = _plugin.detect_desktop_jvm(r, os_name)
    _expect("preflight: JAVA_HOME unset + resolvable install -> ok, no error", bool(pf["ok"]) and String(pf["path"]).begins_with(jdk25) and not pf.has("message"))
    r = _plugin.resolve_runtime_jdk("", "", [jdk17, jdk21])
    pf = _plugin.detect_desktop_jvm(r, os_name)
    _expect("preflight: nothing >= 25 resolves -> error naming the setting", not bool(pf["ok"]) and String(pf["message"]).contains("kanama/build/jdk_path"))
    r = _plugin.resolve_runtime_jdk("", "", [jdk25_no_libjvm])
    pf = _plugin.detect_desktop_jvm(r, os_name)
    _expect("preflight: JDK 25 without libjvm -> libjvm error", not bool(pf["ok"]) and String(pf["message"]).contains("libjvm") and String(pf["message"]).contains(jdk25_no_libjvm) and String(pf["message"]).contains("kanama/build/jdk_path"))
    _expect("preflight: Android/Web are skipped", bool(_plugin.detect_desktop_jvm({"ok": false, "message": "x"}, "Android")["ok"]))
    OS.unset_environment("JAVA_HOME")
    r = _plugin.resolve_runtime_jdk_from_environment(jdk25)
    pf = _plugin.detect_desktop_jvm(r, os_name)
    _expect("preflight: setting set, JAVA_HOME unset -> ok", bool(pf["ok"]))

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


# A directory shaped like a JDK home: bin/java(.exe), a release file with JAVA_VERSION, and libjvm.
func _fake_jdk(root: String, name: String, version: String, with_libjvm: bool = true, with_headers: bool = true) -> String:
    var home := root.path_join(name)
    DirAccess.make_dir_recursive_absolute(home.path_join("bin"))
    var java_name := "java.exe" if OS.get_name() == "Windows" else "java"
    var java := FileAccess.open(home.path_join("bin").path_join(java_name), FileAccess.WRITE)
    java.store_string("#!/bin/sh\n")
    java.close()
    var release := FileAccess.open(home.path_join("release"), FileAccess.WRITE)
    release.store_string("IMPLEMENTOR=\"Kanama test\"\nJAVA_VERSION=\"%s\"\n" % version)
    release.close()
    if with_headers:
        DirAccess.make_dir_recursive_absolute(home.path_join("include"))
        var jni := FileAccess.open(home.path_join("include/jni.h"), FileAccess.WRITE)
        jni.store_string("#define JNI_VERSION_21 0x00150000\n")
        jni.close()
    if with_libjvm:
        var libjvm_rel := "bin/server/jvm.dll" if OS.get_name() == "Windows" else ("lib/server/libjvm.dylib" if OS.get_name() == "macOS" else "lib/server/libjvm.so")
        DirAccess.make_dir_recursive_absolute(home.path_join(libjvm_rel).get_base_dir())
        var libjvm := FileAccess.open(home.path_join(libjvm_rel), FileAccess.WRITE)
        libjvm.store_string("x")
        libjvm.close()
    return home


func _expect(name: String, condition: bool) -> void:
    if condition:
        print("[jdk_test] ok %s" % name)
    else:
        printerr("[jdk_test] FAIL %s" % name)
        _failures += 1
