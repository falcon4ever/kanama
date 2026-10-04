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
	report("parity=%s" % parity())


# The randomized parity row (see ValueTypeStorageSmoke.kt): the same xorshift32 input stream and
# per-operation FNV-1a hashes of the float32 result bits.
const PARITY_INPUTS := 256
const SCALES := [0.001, 0.01, 0.1, 1.0, 10.0, 100.0, 1000.0]
var rng_state := 2463534242
var hashes := {}
var hash_order := []


func next_random() -> int:
	rng_state = rng_state ^ ((rng_state << 13) & 0xFFFFFFFF)
	rng_state = rng_state ^ (rng_state >> 17)
	rng_state = rng_state ^ ((rng_state << 5) & 0xFFFFFFFF)
	return rng_state


func next_value() -> float:
	var r := next_random()
	return ((r % 200001) - 100000) / 10000.0 * SCALES[(r >> 24) % 7]


func mix(name: String, value) -> void:
	if not hashes.has(name):
		hashes[name] = 2166136261
		hash_order.append(name)
	if value == null:
		mix(name, -1.0e30)
		return
	if value is Vector2:
		for c in [value.x, value.y]: mix(name, c)
		return
	if value is Vector3:
		for c in [value.x, value.y, value.z]: mix(name, c)
		return
	if value is Vector4 or value is Quaternion:
		for c in [value.x, value.y, value.z, value.w]: mix(name, c)
		return
	var bits := PackedFloat32Array([value]).to_byte_array().decode_u32(0)
	hashes[name] = ((hashes[name] ^ bits) * 16777619) & 0xFFFFFFFF


func parity() -> String:
	for i in PARITY_INPUTS:
		var p := []
		for k in 18: p.append(next_value())
		var a := Vector2(p[0], p[1])
		var b := Vector2(p[2], p[3])
		var c := Vector3(p[4], p[5], p[6])
		var d := Vector3(p[7], p[8], p[9])
		var e := Vector4(p[10], p[11], p[12], p[13])
		var f := Vector4(p[14], p[15], p[16], p[17])
		var q := Quaternion(p[0], p[4], p[8], p[12])
		var q2 := Quaternion(p[1], p[5], p[9], p[13])
		var s: float = p[16]
		mix("v2_add", a + b)
		mix("v2_sub", a - b)
		mix("v2_mul", a * s)
		mix("v2_div", a / s)
		mix("v2_neg", -a)
		mix("v2_dot", a.dot(b))
		mix("v2_len", a.length())
		mix("v2_len2", a.length_squared())
		mix("v2_norm", a.normalized())
		mix("v2_dist", a.distance_to(b))
		mix("v3_add", c + d)
		mix("v3_sub", c - d)
		mix("v3_mul", c * s)
		mix("v3_div", c / s)
		mix("v3_neg", -c)
		mix("v3_dot", c.dot(d))
		mix("v3_len", c.length())
		mix("v3_len2", c.length_squared())
		mix("v3_norm", c.normalized())
		mix("v3_cross", c.cross(d))
		mix("v3_dist", c.distance_to(d))
		mix("v4_add", e + f)
		mix("v4_sub", e - f)
		mix("v4_mul", e * s)
		mix("v4_neg", -e)
		mix("v4_dot", e.dot(f))
		mix("v4_len", e.length())
		mix("q_mul", q * q2)
		mix("q_norm", q.normalized())
		mix("q_dot", q.dot(q2))
		mix("q_len", q.length())
		var basis := Basis(q.normalized())
		mix("basis_q", basis.x)
		mix("basis_q", basis.y)
		mix("basis_q", basis.z)
		mix("basis_xform", basis * c)
		mix("t3d_xform", Transform3D(basis, d) * c)
		var plane := Plane(d.normalized(), s)
		mix("plane_dist", plane.distance_to(c))
		mix("plane_ray", plane.intersects_ray(c, Vector3(p[0], p[1], p[2])))
		mix("rect_area", Rect2(Vector2.ZERO, a).get_area())
		mix("aabb_volume", AABB(Vector3.ZERO, c).get_volume())
	var parts := []
	for name in hash_order:
		parts.append("%s=%x" % [name, hashes[name]])
	return "n=%d %s" % [PARITY_INPUTS, " ".join(parts)]


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
