extends Node2D
# Task 134 A2: the GDScript half of value_type_storage_smoke.tscn. Prints the same three lines as
# ValueTypeStorageSmoke.kt; scripts/runtime_smoke.sh requires the two to be identical.


func _ready() -> void:
	var v := Vector2(0.1, 0.2)
	position = v
	var back := position
	report("roundtrip_eq=%s str=%s x_eq_literal=%s" % [back == v, back, back.x == 0.1])

	var q := Quaternion(0.1, 0.2, 0.3, 0.9)
	var values := [
		Vector2(0.1, 0.2),
		Vector3(1, 2, 3),
		Vector2(12345.678, -0.000001),
		Vector4(0.1, 0.2, 0.3, 0.4),
		q,
		Plane(Vector3(0, 1, 0), 2.5),
		Color(0.123456, 1.0 / 3.0, 2.5, -0.00001),
		Rect2(Vector2(0.1, 0.2), Vector2(3, 4)),
		AABB(Vector3(1, 2, 3), Vector3(0.5, 0.25, 0.125)),
		Basis(q.normalized()),
		Transform3D(Basis.IDENTITY, Vector3(0.1, 0.2, 0.3)),
		Transform2D(Vector2(1, 0), Vector2(0, 1), Vector2(0.5, 0.25)),
		Projection.IDENTITY,
	]
	var strs := []
	for value in values:
		strs.append(str(value))
	report("str=%s" % "|".join(strs))

	var a := Vector2(0.1, 0.2)
	var b := Vector2(0.7, 0.3)
	var c := Vector3(0.1, 0.2, 0.3)
	var d := Vector3(0.7, 0.11, 0.13)
	var q2 := Quaternion(0.5, 0.1, 0.2, 0.8)
	var basis := Basis(q.normalized())
	var ops := [
		bits([(a + b).x, (a + b).y]),
		bits([(a - b).x, (a - b).y]),
		bits([(a * 0.1).x, (a * 0.1).y]),
		bits([(a / 0.3).x, (a / 0.3).y]),
		bits([(-a).x, (-a).y]),
		bits([a.dot(b)]),
		bits([a.length()]),
		bits([a.length_squared()]),
		bits3(c.cross(d)),
		bits3(c.normalized()),
		bits([c.length()]),
		bits4(q * q2),
		bits4(q.normalized()),
		bits3(basis.x) + "/" + bits3(basis.y) + "/" + bits3(basis.z),
		bits3(basis * c),
		bits([a.rotated(0.3).x, a.rotated(0.3).y]),
	]
	report("bits=%s" % "|".join(ops))


func report(line: String) -> void:
	printerr("[kanama:kt] ValueTypeStorage gdscript ", line)


func bits(values: Array) -> String:
	var out := []
	for f in values:
		out.append(PackedFloat32Array([f]).to_byte_array().hex_encode())
	return ",".join(out)


func bits3(v: Vector3) -> String:
	return bits([v.x, v.y, v.z])


func bits4(q: Quaternion) -> String:
	return bits([q.x, q.y, q.z, q.w])
