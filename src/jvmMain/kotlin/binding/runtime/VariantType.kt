package net.multigesture.kanama.binding.runtime

/**
 * Mirror of `GDExtensionVariantType` from `gdextension_interface.h`.
 *
 * Only the handful we currently marshal is listed; extend as new conversions are needed. The
 * integer ids MUST match the C enum because Godot's variant constructors are indexed by them.
 */
enum class VariantType(val id: Int) {
  NIL(0),
  BOOL(1),
  INT(2),
  FLOAT(3),
  STRING(4),
  VECTOR2(5),
  VECTOR2I(6),
  RECT2(7),
  RECT2I(8),
  VECTOR3(9),
  VECTOR3I(10),
  TRANSFORM2D(11),
  VECTOR4(12),
  VECTOR4I(13),
  PLANE(14),
  QUATERNION(15),
  AABB(16),
  BASIS(17),
  TRANSFORM3D(18),
  PROJECTION(19),
  COLOR(20),
  OBJECT(24),
  DICTIONARY(27),
  ARRAY(28),
  PACKED_BYTE_ARRAY(29),
  PACKED_INT32_ARRAY(30),
  PACKED_INT64_ARRAY(31),
  PACKED_FLOAT32_ARRAY(32),
  PACKED_FLOAT64_ARRAY(33),
  PACKED_STRING_ARRAY(34),
  PACKED_VECTOR2_ARRAY(35),
  PACKED_VECTOR3_ARRAY(36),
  PACKED_COLOR_ARRAY(37),
  PACKED_VECTOR4_ARRAY(38),
  STRING_NAME(21),
  NODE_PATH(22),
  RID(23),
  CALLABLE(25),
  SIGNAL(26),
}
