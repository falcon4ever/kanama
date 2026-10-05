extends Node
# Task 133 review: drives RegisterClassValueTypeProbe (a @RegisterClass) with every value type over
# Object.call (the Variant call path) and a statically typed call (ptrcall), and prints one line.

func _ready() -> void:
	var values := {
		"v2": Vector2(0.1, -2.5),
		"v2i": Vector2i(3, -4),
		"v3": Vector3(1e-30, 2.0, -3.0),
		"v3i": Vector3i(-7, 8, 2147483647),
		"v4": Vector4(1.0 / 3.0, NAN, INF, -INF),
		"v4i": Vector4i(-2147483648, 2147483647, 0, -1),
		"rect": Rect2(0.5, 1.5, 2.25, 3.125),
		"recti": Rect2i(-1, 2, 30, 40),
		"plane": Plane(Vector3(0.6, 0.8, 0.0), -12.75),
		"box": AABB(Vector3(1, 2, 3), Vector3(4, 5, 6)),
		"rot": Quaternion(0.0, 0.6, 0.0, 0.8),
		"basis": Basis(Vector3(1, 2, 3), Vector3(4, 5, 6), Vector3(7, 8, 9)),
		"xform2": Transform2D(Vector2(1, 2), Vector2(3, 4), Vector2(5, 6)),
		"xform3": Transform3D(Basis(Vector3(1, 2, 3), Vector3(4, 5, 6), Vector3(7, 8, 9)), Vector3(10, 11, 12)),
		"proj": Projection(Vector4(1, 2, 3, 4), Vector4(5, 6, 7, 8), Vector4(9, 10, 11, 12), Vector4(13, 14, 15, 16)),
		"color": Color(0.25, 0.5, 2.0, 1.0),
	}
	var probe: RegisterClassValueTypeProbe = ClassDB.instantiate("RegisterClassValueTypeProbe")
	var call_ok := 0
	var typed_ok := 0
	var bad: Array[String] = []
	for name in values:
		var value = values[name]
		var back = probe.call("echo_" + name, value)
		if var_to_str(back) == var_to_str(value) and typeof(back) == typeof(value):
			call_ok += 1
		else:
			bad.append("call:%s=%s" % [name, var_to_str(back)])
	# Statically typed calls: GDScript validates the arguments and uses the ptrcall upcall.
	var typed := [
		["v2", probe.echo_v2(values.v2)], ["v2i", probe.echo_v2i(values.v2i)],
		["v3", probe.echo_v3(values.v3)], ["v3i", probe.echo_v3i(values.v3i)],
		["v4", probe.echo_v4(values.v4)], ["v4i", probe.echo_v4i(values.v4i)],
		["rect", probe.echo_rect(values.rect)], ["recti", probe.echo_recti(values.recti)],
		["plane", probe.echo_plane(values.plane)], ["box", probe.echo_box(values.box)],
		["rot", probe.echo_rot(values.rot)], ["basis", probe.echo_basis(values.basis)],
		["xform2", probe.echo_xform2(values.xform2)], ["xform3", probe.echo_xform3(values.xform3)],
		["proj", probe.echo_proj(values.proj)], ["color", probe.echo_color(values.color)],
	]
	for pair in typed:
		if var_to_str(pair[1]) == var_to_str(values[pair[0]]):
			typed_ok += 1
		else:
			bad.append("typed:%s=%s" % [pair[0], var_to_str(pair[1])])
	print("RegisterClassValueTypes call=%d/16 typed=%d/16 describe=%s bad=%s" % [
		call_ok, typed_ok, probe.describe_xform3(values.xform3), bad])
	probe.free()
	get_tree().quit.call_deferred()
