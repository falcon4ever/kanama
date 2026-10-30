@tool
extends Node

# Opened in the editor by scripts/tool_smoke.sh. `Pre` carries ToolConstructorErrorSmoke.kt, a @Tool
# script whose constructor throws, with rounds = 7 in the scene. The editor must keep the script on
# the node, on a fresh set_script too, and a repack of the node must still name the script and its
# value: before, the node lost its script and a save wrote the scene without it.
func _ready() -> void:
	if not Engine.is_editor_hint():
		return
	var pre := get_node("Pre")
	print("[kanama-smoke] constructor_error_tool scene script=%s rounds=%s" % [pre.get_script() != null, pre.get("rounds")])
	var fresh := Node.new()
	fresh.set_script(load("res://ToolConstructorErrorSmoke.kt"))
	print("[kanama-smoke] constructor_error_tool attach script=%s" % [fresh.get_script() != null])
	fresh.free()
	var packed := PackedScene.new()
	var err := packed.pack(pre)
	var path := "user://constructor_error_tool_repack.tscn"
	ResourceSaver.save(packed, path)
	var text := FileAccess.get_file_as_string(path)
	print("[kanama-smoke] constructor_error_tool saved pack=%s script=%s rounds=%s" % [
		err, text.contains("ToolConstructorErrorSmoke.kt"), text.contains("rounds = 7")])
