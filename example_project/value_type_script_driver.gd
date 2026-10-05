extends Node
# Task 133: drives ValueTypeScriptSmoke.kt and its GDScript twin the same way and prints one line
# per side and step; scripts/runtime_smoke.sh requires the kotlin and gdscript lines to be
# identical (after the side label). var_to_str prints every component exactly.

const NAMES := ["rect", "recti", "vec4", "vec4i", "plane", "box", "xform2", "xform3", "proj", "cell", "rot", "basis"]

# Values with no short decimal, extreme ints, NaN and the infinities, set through Object.set.
var set_values := {
	"rect": Rect2(0.1, -0.2, 1e-30, 3.4e38),
	"recti": Rect2i(-2147483648, 2147483647, 7, -9),
	"vec4": Vector4(1.0 / 3.0, NAN, INF, -INF),
	"vec4i": Vector4i(2147483647, -2147483648, 0, -1),
	"plane": Plane(Vector3(0.6, 0.8, 0.0), -12.75),
	"box": AABB(Vector3(-1.5, 2.25, 0.1), Vector3(1e-7, 4.0, 5.5)),
	"xform2": Transform2D(0.7, Vector2(3.0, -4.0)),
	"xform3": Transform3D(Basis(Vector3(0, 1, 0), 0.3), Vector3(0.1, 0.2, 0.3)),
	"proj": Projection.create_perspective(70.0, 1.5, 0.05, 4000.0),
	"cell": Vector3i(-7, 8, 2147483647),
	"rot": Quaternion(Vector3(1, 0, 0), 1.25),
	"basis": Basis.from_euler(Vector3(0.1, 0.2, 0.3)),
}

# Values a GDScript emit sends to the script's own typed handlers.
var emit_values := {
	"rect": Rect2(5, 6, 7, 8),
	"recti": Rect2i(1, 2, 3, 4),
	"vec4": Vector4(0.5, 0.25, 0.125, 0.0625),
	"vec4i": Vector4i(-5, 6, -7, 8),
	"plane": Plane(Vector3(0, 0, 1), 0.3),
	"box": AABB(Vector3(9, 8, 7), Vector3(0.1, 0.2, 0.3)),
	"xform2": Transform2D(-0.5, Vector2(0.1, 0.2)),
	"xform3": Transform3D(Basis.FLIP_Z, Vector3(-1, -2, -3)),
	"proj": Projection.create_orthogonal(-1, 1, -2, 2, 0.1, 100),
	"cell": Vector3i(4, 5, 6),
	"rot": Quaternion(0.5, 0.5, 0.5, 0.5),
	"basis": Basis.FLIP_X,
}

func _ready() -> void:
	report("kotlin", $Kotlin)
	report("gdscript", $GDScript)
	get_tree().quit.call_deferred()

func show(value) -> String:
	return var_to_str(value).replace("\n", "")

func line(side: String, step: String, parts: Array[String]) -> void:
	print("ValueTypeScript %s %s %s" % [side, step, " ".join(parts)])

func report(side: String, node: Node) -> void:
	var rows := {}
	for property in node.get_property_list():
		if property.name in NAMES:
			rows[property.name] = "%d/%d/%s" % [property.type, property.hint, property.hint_string]
	var parts: Array[String] = []
	for name in NAMES:
		var from_scene = node.get(name)
		var default_value = node.get_script().get_property_default_value(name)
		parts.append("%s{scene=%s type=%d row=%s default=%s}" % [
			name, show(from_scene), typeof(from_scene), rows.get(name), show(default_value)])
	line(side, "props", parts)

	parts = []
	for name in NAMES:
		node.set(name, set_values[name])
		var back = node.get(name)
		var echoed = node.call("echo_" + name, set_values[name])
		parts.append("%s{set=%s echo=%s}" % [name, show(back), show(echoed)])
	line(side, "set", parts)

	# GDScript emits on the node; the script's typed handlers store what they receive.
	node.listen()
	node.emit_signal("flats", emit_values.rect, emit_values.recti, emit_values.vec4, emit_values.vec4i)
	node.emit_signal("solids", emit_values.plane, emit_values.box, emit_values.cell, emit_values.rot)
	node.emit_signal("frames", emit_values.xform2, emit_values.xform3, emit_values.proj, emit_values.basis)
	node.unlisten()
	parts = []
	for name in NAMES:
		parts.append("%s=%s" % [name, show(node.get(name))])
	line(side, "received", parts)

	# The script emits its current values; GDScript receives them.
	var seen: Array[String] = []
	var collect := func(a, b, c, d) -> void: seen.append("%s|%s|%s|%s" % [show(a), show(b), show(c), show(d)])
	for signal_name in ["flats", "solids", "frames"]:
		node.connect(signal_name, collect)
	node.emit_all()
	for signal_name in ["flats", "solids", "frames"]:
		node.disconnect(signal_name, collect)
	line(side, "emitted", seen)
