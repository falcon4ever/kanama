extends Node

# Attaches ConstructorErrorSmoke.kt, whose constructor throws, twice: once to a node in the tree and
# once to a node that never enters it. Each attach must leave the node usable, with its script and an
# inert placeholder instance (no `probe` method), and the process must reach the last line. The
# GDScript twin's constructor fails too, but GDScript keeps its half-built live instance (a run-time
# error does not abort construction); a Kotlin constructor that threw leaves no object to keep.
func _ready() -> void:
	var script: Script = load("res://ConstructorErrorSmoke.kt")
	var in_tree := Node.new()
	add_child(in_tree)
	in_tree.set_script(script)
	print("[kanama-smoke] constructor_error attach in_tree script=%s instance=%s name=%s" % [
		in_tree.get_script() != null, in_tree.has_method("probe"), in_tree.name])
	var detached := Node.new()
	detached.set_script(script)
	print("[kanama-smoke] constructor_error attach detached script=%s instance=%s" % [
		detached.get_script() != null, detached.has_method("probe")])
	detached.free()
	var twin := Node.new()
	twin.set_script(load("res://constructor_error_twin.gd"))
	print("[kanama-smoke] constructor_error gdscript_twin script=%s instance=%s" % [
		twin.get_script() != null, twin.has_method("probe")])
	twin.free()
	in_tree.queue_free()
	await get_tree().process_frame
	print("[kanama-smoke] constructor_error done")
	get_tree().quit()
