extends Node
# Task 133 C2: the GDScript twin of ColorScriptSmoke.kt (same members, same tscn values).

signal tinted(color: Color)

@export var tint: Color = Color(0.1, 0.2, 0.3, 0.4)
@export_color_no_alpha var solid: Color = Color.RED

var _received = null

func mix_with(other: Color) -> Color:
	return tint.lerp(other, 0.25)

func current() -> Color:
	return tint

func describe() -> String:
	return "%s|%s" % [tint, solid]

func emit_tint() -> void:
	var on_tinted := func(color: Color) -> void: _received = color
	tinted.connect(on_tinted)
	tinted.emit(tint)
	tinted.disconnect(on_tinted)

func received() -> String:
	return str(_received)
