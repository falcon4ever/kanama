extends Node
## Task 134 D4 parity probe for typed_signal_smoke.tscn: a GDScript lambda connected to the same
## signal the Kotlin script emits through its typed handle records what it received, and GDScript
## emits the signal back (once well-typed, once with a wrong type) for the Kotlin callback.

var seen := ""


func attach(emitter: Object) -> void:
	emitter.connect("kanama_typed", func(v, mode, s): seen = "%s;%s;%s;%d,%d,%d" % [v, mode, s, typeof(v), typeof(mode), typeof(s)])


func emit_from_gdscript(emitter: Object) -> void:
	emitter.emit_signal("kanama_typed", Vector2(0.5, 4.0), 1, &"from_gd")


func emit_wrong_type(emitter: Object) -> void:
	emitter.emit_signal("kanama_typed", 5, 1, "x")


func get_seen() -> String:
	return seen
