extends Node

# Emitted by main.gd around the in-process hot reload; HelloScript connects a lambda to it.
signal probe

var calls := 0

func describe(prefix: String, count: int, enabled: bool, scale: float) -> String:
	calls += 1
	return "%s:%d:%s:%.1f:%d" % [prefix, count, str(enabled).to_lower(), scale, calls]

func add_count(left: int, right: int) -> int:
	return left + right

func negate(value: bool) -> bool:
	return not value

func object_class_name(value: Object) -> String:
	return value.get_class()

func resource_class_name(value: Resource) -> String:
	return value.get_class()

func return_object(value: Object) -> Object:
	return value

func vector2_sum(value: Vector2) -> Vector2:
	return value + Vector2(1.0, 2.0)

func vector3_sum(value: Vector3) -> Vector3:
	return value + Vector3(1.0, 2.0, 3.0)

func color_mix(value: Color) -> Color:
	return Color(value.r + 0.1, value.g + 0.2, value.b + 0.3, value.a)

func quaternion_negate(value: Quaternion) -> Quaternion:
	return Quaternion(-value.x, -value.y, -value.z, -value.w)

func vector4_negate(value: Vector4) -> Vector4:
	return Vector4(-value.x, -value.y, -value.z, -value.w)

func rect2_grow(value: Rect2) -> Rect2:
	return Rect2(value.position + Vector2(1.0, 2.0), value.size + Vector2(3.0, 4.0))

func aabb_grow(value: AABB) -> AABB:
	return AABB(value.position + Vector3(1.0, 2.0, 3.0), value.size + Vector3(4.0, 5.0, 6.0))

func plane_negate(value: Plane) -> Plane:
	return Plane(-value.normal, -value.d)

func basis_translate(value: Basis) -> Basis:
	return Basis(
		value.x + Vector3(1.0, 0.0, 0.0),
		value.y + Vector3(0.0, 1.0, 0.0),
		value.z + Vector3(0.0, 0.0, 1.0),
	)

func transform3d_translate(value: Transform3D) -> Transform3D:
	return Transform3D(value.basis, value.origin + Vector3(10.0, 20.0, 30.0))

func transform2d_translate(value: Transform2D) -> Transform2D:
	return Transform2D(value.x, value.y, value.origin + Vector2(10.0, 20.0))

func projection_negate_w(value: Projection) -> Projection:
	return Projection(value.x, value.y, value.z, -value.w)

func vector2i_negate(value: Vector2i) -> Vector2i:
	return Vector2i(-value.x, -value.y)

func vector3i_negate(value: Vector3i) -> Vector3i:
	return Vector3i(-value.x, -value.y, -value.z)

func vector4i_negate(value: Vector4i) -> Vector4i:
	return Vector4i(-value.x, -value.y, -value.z, -value.w)

func rect2i_grow(value: Rect2i) -> Rect2i:
	return Rect2i(value.position + Vector2i(1, 2), value.size + Vector2i(3, 4))

func nodepath_describe(value: NodePath) -> String:
	# Read-back: report the node-path as a String so HelloScript can see we
	# actually received the typed NodePath (not, say, a String with leading
	# coercion). String(NodePath(":foo:bar")) is ":foo:bar" — verifies the
	# Variant carried the NodePath through correctly.
	return "np:%d:%s" % [value.get_name_count(), str(value)]
