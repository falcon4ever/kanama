extends Node
# Drives WrongTypeSetSmoke.kt and its GDScript twin the same way: every property is offered a set of
# values (right type, a conversion GDScript accepts, a value of the wrong type) through Object.set,
# and the line per side records what the property holds afterwards. A refused value leaves the
# property as it was (marked `(kept)`). scripts/runtime_smoke.sh requires the kotlin and gdscript lines to be
# identical (after the side label), and the Kotlin refusals to name the property.

const NAMES := ["flag", "count", "ratio", "label", "path", "vec2", "vec2i", "vec3", "vec3i", "tint", "vec4", "area", "rot", "xform", "plane", "names", "modes", "extras", "scores"]

func offers() -> Dictionary:
	var typed_names: Array[String] = ["a", "b"]
	var typed_modes: Array[int] = [1, 0]
	var typed_scores: Dictionary[String, int] = {"x": 3}
	var ints: Array[int] = [7]
	return {
		"flag": [true, 1, 0.5, null, "true", Vector2(1, 2), [1], {}],
		"count": [5, true, 3.7, "12", "abc", null, Vector2(1, 2), [1], {}],
		"ratio": [2.5, 3, true, "1.5", null, Vector2(1, 2), [1], {}],
		"label": ["ok", &"sn", NodePath("a/b"), null, 3, 2.5, true, Vector2(1, 2), [1], {}],
		"path": [NodePath("x/y"), "a/b", &"sn", null, 3, Vector2(1, 2), {}],
		"vec2": [Vector2(5, 6), Vector2i(3, 4), null, 1, 1.5, "x", Vector3(1, 2, 3), [1, 2], {}],
		"vec2i": [Vector2i(5, 6), Vector2(3.7, 4.2), null, 1, "x", Vector3i(1, 2, 3), [1, 2], {}],
		"vec3": [Vector3(5, 6, 7), Vector3i(3, 4, 5), null, 1, "x", Vector2(1, 2), Vector4(1, 2, 3, 4), [1, 2, 3], {}],
		"vec3i": [Vector3i(5, 6, 7), Vector3(3.7, 4.2, 1.1), null, 1, "x", Vector2i(1, 2), [1, 2, 3], {}],
		"tint": [Color(0.5, 0.25, 0.125, 1.0), "red", "kanama_not_a_color", 1, null, Vector4(1, 2, 3, 4), [1, 2, 3], {}],
		"vec4": [Vector4(1.5, 2, 3, 4), Vector4i(1, 2, 3, 4), null, Vector3(1, 2, 3), [1, 2, 3, 4]],
		"area": [Rect2(1.5, 2, 3, 4), Rect2i(1, 2, 3, 4), null, Vector2(1, 2), [1, 2]],
		"rot": [Quaternion(0, 1, 0, 0), Basis(Vector3.UP, PI / 2), null, Vector3(1, 2, 3), [1, 2, 3, 4], RefCounted.new()],
		"xform": [Transform3D(Basis.FLIP_Y, Vector3(1, 2, 3)), Quaternion(0, 1, 0, 0), Basis.FLIP_X, null, Vector3(1, 2, 3)],
		"plane": [Plane(0, 0, 1, 2), Vector3(0, 1, 0), null, Vector2(1, 2), [0, 1, 0]],
		"names": [typed_names, null, 1, "x", {}, Vector2(1, 2)],
		"modes": [typed_modes, null, 1, "x", {}, Vector2(1, 2)],
		"extras": [{"a": 1}, null, 1, "x", [1], Vector2(1, 2)],
		"scores": [typed_scores, null, 1, "x", [1], Vector2(1, 2)],
	}

# What every property holds before each offer: not a default, so a refused value (the property keeps
# this) cannot be mistaken for one that was converted to the zero value.
func markers() -> Dictionary:
	var marker_names: Array[String] = ["marker"]
	var marker_modes: Array[int] = [1, 1]
	var marker_scores: Dictionary[String, int] = {"marker": 9}
	return {
		"flag": true, "count": 41, "ratio": 4.5, "label": "marker", "path": NodePath("marker/path"),
		"vec2": Vector2(9, 9), "vec2i": Vector2i(9, 9), "vec3": Vector3(9, 9, 9), "vec3i": Vector3i(9, 9, 9),
		"tint": Color(0.9, 0.8, 0.7, 1.0), "vec4": Vector4(9, 9, 9, 9), "area": Rect2(9, 9, 9, 9),
		"rot": Quaternion(0, 0, 1, 0), "xform": Transform3D(Basis.FLIP_Z, Vector3(9, 9, 9)), "plane": Plane(0, 1, 0, 9),
		"names": marker_names, "modes": marker_modes,
		"extras": {"marker": 1}, "scores": marker_scores,
	}

func _ready() -> void:
	report("kotlin", $Kotlin)
	report("gdscript", $GDScript)
	get_tree().quit.call_deferred()

func show(value) -> String:
	# str() for a container: var_to_str spells a typed Array / Dictionary out, an untyped one not.
	if typeof(value) == TYPE_ARRAY or typeof(value) == TYPE_DICTIONARY:
		return "%d:%s" % [typeof(value), str(value)]
	return "%d:%s" % [typeof(value), var_to_str(value).replace("\n", "")]

func report(side: String, node: Node) -> void:
	var all := offers()
	var marks := markers()
	for name in NAMES:
		var parts: Array[String] = []
		for value in all[name]:
			node.set(name, marks[name])
			var before = node.get(name)
			node.set(name, value)
			var after = node.get(name)
			parts.append("%s=>%s%s" % [show(value), show(after), "(kept)" if show(before) == show(after) else ""])
		print("WrongTypeSet %s %s %s" % [side, name, " | ".join(parts)])
