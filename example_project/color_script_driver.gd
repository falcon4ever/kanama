extends Node
# Task 133 C2: drives ColorScriptSmoke.kt and its GDScript twin the same way and prints one line
# each; scripts/runtime_smoke.sh requires the two lines to be identical (after the side label).
# Channels print at full precision (str(Color) rounds to 4 decimals).

func _ready() -> void:
	report("kotlin", $Kotlin)
	report("gdscript", $GDScript)
	get_tree().quit.call_deferred()

func channels(value) -> String:
	if typeof(value) != TYPE_COLOR:
		return "not-a-color:%s" % [value]
	return "%s,%s,%s,%s" % [value.r, value.g, value.b, value.a]

func report(side: String, node: Node) -> void:
	var from_scene = node.get("tint")
	var row := {}
	for property in node.get_property_list():
		if property.name in ["tint", "solid"]:
			row[property.name] = "%d/%d/%s" % [property.type, property.hint, property.hint_string]
	var default_value = node.get_script().get_property_default_value("tint")
	node.set("tint", Color(0.5, 0.25, 0.125, 1.0 / 3.0))
	var set_back = node.get("tint")
	var mixed = node.mix_with(Color(1, 1, 1, 1))
	var returned = node.current()
	var seen: Array[String] = []
	var collect := func(color: Color) -> void: seen.append(channels(color))
	node.connect("tinted", collect)
	node.emit_tint()
	node.disconnect("tinted", collect)
	print("ColorScript %s scene=%s type=%d rows=%s/%s default=%s set=%s mixed=%s returned=%s signal=%s received=%s describe=%s" % [
		side, channels(from_scene), typeof(from_scene), row.get("tint"), row.get("solid"),
		channels(default_value), channels(set_back), channels(mixed), channels(returned), seen,
		node.received(), node.describe()])
