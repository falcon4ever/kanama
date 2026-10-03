# print_jdk_lookup.gd -- prints which JDK the editor plugin's startup preflight would use (kanama#277).
#
# Helper of scripts/check_jdk_lookup_parity.sh, run as
#   godot --headless --path <project with addons/kanama_tools> --script res://print_jdk_lookup.gd
# with JAVA_HOME / KANAMA_TEST_JDK_SEARCH_DIRS as the scenario sets them and KANAMA_PARITY_SETTING as the
# explicit 'kanama/build/jdk_path'. Prints "[parity] libjvm=<path>" or "[parity] libjvm=NONE".
extends SceneTree


func _init() -> void:
    var plugin: GDScript = load("res://addons/kanama_tools/plugin.gd")
    var resolution: Dictionary = plugin.resolve_runtime_jdk_from_environment(OS.get_environment("KANAMA_PARITY_SETTING"))
    var result: Dictionary = plugin.detect_desktop_jvm(resolution, OS.get_name())
    print("[parity] libjvm=%s" % (String(result["path"]) if bool(result["ok"]) else "NONE"))
    quit(0)
