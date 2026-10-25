# check_addon_version_lock.gd -- the Kanama Tools plugin notices scripts built for another Kanama (task 143 item 1).
#
# Run by scripts/tool_smoke.sh (`godot --headless --path <scratch project> --script ...`) against a scratch
# project that carries a copy of addons/kanama_tools. buildScripts stamps kanama-scripts.jar with the Kanama
# version it compiled against (addons/kanama/kanama-scripts.version); unzipping a newer addon moves
# addons/kanama/kanama.version. The plugin's addon_version_mismatch() must name both versions and the Sync
# Addon action on a mismatch, and stay quiet when they agree or either file is missing. Prints
# "[version_lock] ok <case>" per case and "[version_lock] PASS" last; any failure prints
# "[version_lock] FAIL <case>" and exits 1.
extends SceneTree

var _failures := 0


func _init() -> void:
    var plugin: GDScript = load("res://addons/kanama_tools/plugin.gd")
    if plugin == null or not plugin.has_method("addon_version_mismatch"):
        printerr("[version_lock] FAIL the plugin has no addon_version_mismatch()")
        quit(1)
        return

    var mismatch := String(plugin.addon_version_mismatch("0.5.1\n", "0.5.0\n"))
    _expect("mismatch names the scripts' version", mismatch.contains("built for Kanama 0.5.0"), mismatch)
    _expect("mismatch names the addon's version", mismatch.contains("the addon is Kanama 0.5.1"), mismatch)
    _expect("mismatch offers Sync Addon", mismatch.contains("Sync Addon"), mismatch)
    _expect("mismatch is one line", not mismatch.contains("\n"), mismatch)
    _expect("same version is quiet", String(plugin.addon_version_mismatch("0.5.0\n", "0.5.0")).is_empty(), "")
    _expect("no scripts jar yet is quiet", String(plugin.addon_version_mismatch("0.5.0\n", "")).is_empty(), "")
    _expect("no addon version file is quiet", String(plugin.addon_version_mismatch("", "0.5.0\n")).is_empty(), "")

    if _failures > 0:
        quit(1)
        return
    print("[version_lock] PASS")
    quit(0)


func _expect(name: String, ok: bool, detail: String) -> void:
    if ok:
        print("[version_lock] ok %s" % name)
    else:
        printerr("[version_lock] FAIL %s: %s" % [name, detail])
        _failures += 1
