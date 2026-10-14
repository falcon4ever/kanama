package net.multigesture.kanama.binding.runtime

/**
 * Godot's names for `Variant::Type` ids, and the message of a script property that is refused a
 * Variant of the wrong type: `Expected Quaternion for property 'rot' but the Variant holds
 * Vector2`. One table for desktop, Android and iOS, so the three backends name the same mistake the
 * same way.
 */
internal object VariantTypeNames {
  /** The type's name as GDScript prints it (`int`, `float`, `Vector2`, `PackedByteArray`, ...). */
  fun label(id: Int): String =
    when (id) {
      0 -> "Nil"
      1 -> "bool"
      2 -> "int"
      3 -> "float"
      4 -> "String"
      5 -> "Vector2"
      6 -> "Vector2i"
      7 -> "Rect2"
      8 -> "Rect2i"
      9 -> "Vector3"
      10 -> "Vector3i"
      11 -> "Transform2D"
      12 -> "Vector4"
      13 -> "Vector4i"
      14 -> "Plane"
      15 -> "Quaternion"
      16 -> "AABB"
      17 -> "Basis"
      18 -> "Transform3D"
      19 -> "Projection"
      20 -> "Color"
      21 -> "StringName"
      22 -> "NodePath"
      23 -> "RID"
      24 -> "Object"
      25 -> "Callable"
      26 -> "Signal"
      27 -> "Dictionary"
      28 -> "Array"
      29 -> "PackedByteArray"
      30 -> "PackedInt32Array"
      31 -> "PackedInt64Array"
      32 -> "PackedFloat32Array"
      33 -> "PackedFloat64Array"
      34 -> "PackedStringArray"
      35 -> "PackedVector2Array"
      36 -> "PackedVector3Array"
      37 -> "PackedColorArray"
      38 -> "PackedVector4Array"
      else -> "Variant type $id"
    }

  /** [what] names the property or argument (`property 'rot'`); a Nil is `null (Nil)`. */
  fun assignmentError(expectedId: Int, actualId: Int, what: String): String =
    assignmentError(label(expectedId), actualId, what)

  /** The same message for an expected type given by name (a value type's Kotlin class name). */
  fun assignmentError(expectedName: String, actualId: Int, what: String): String =
    "Expected $expectedName for $what but the Variant holds " +
      (if (actualId == 0) "null (Nil)" else label(actualId))
}
