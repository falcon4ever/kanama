package net.multigesture.kanama.processor

/**
 * Task 133 value types on the Web: Vector3i, Rect2, Rect2i, Plane, Quaternion, Basis and
 * Transform3D as script types (`@Export`, function arguments and returns, signal payloads). Each
 * crosses the text channels as its components, comma-separated, in the order of [COMPONENTS] — the
 * order the runtime's `WebPackedValues` reads and writes on the Kotlin side. Decimals are written
 * by the proxy with `String.num_scientific` (shortest round-trip text, `nan`/`inf`/`-inf`), so no
 * component loses precision on the way in; Kotlin writes them with `WebPackedFloats`.
 *
 * Vector4, Vector4i, AABB, Transform2D and Projection are script types on desktop, Android and iOS
 * but not on Web: the Web runtime has no value type of that name yet (its value types are the
 * hand-written subset in `web-runtime/.../types/WebValueTypes.kt`).
 */
internal object WebValueTypes {
  /** The GDScript access path of each component, in channel order. */
  val COMPONENTS: Map<TypeMapping, List<String>> =
    linkedMapOf(
      TypeMapping.VECTOR3I to listOf("x", "y", "z"),
      TypeMapping.RECT2 to listOf("position.x", "position.y", "size.x", "size.y"),
      TypeMapping.RECT2I to listOf("position.x", "position.y", "size.x", "size.y"),
      TypeMapping.PLANE to listOf("normal.x", "normal.y", "normal.z", "d"),
      TypeMapping.QUATERNION to listOf("x", "y", "z", "w"),
      TypeMapping.BASIS to columns(""),
      TypeMapping.TRANSFORM3D to columns("basis.") + listOf("origin.x", "origin.y", "origin.z"),
    )

  /** The types whose components are ints (written with `str`, read with `int`). */
  private val WHOLE = setOf(TypeMapping.VECTOR3I, TypeMapping.RECT2I)

  private fun columns(prefix: String): List<String> =
    listOf("x", "y", "z").flatMap { column -> listOf("x", "y", "z").map { "$prefix$column.$it" } }

  fun isWebValueType(type: TypeMapping?): Boolean = type in COMPONENTS

  /** The GDScript type name (`Rect2`), also its `TYPE_*` suffix in upper case. */
  fun gdName(type: TypeMapping): String = type.kotlinType.substringAfterLast('.')

  /** `TYPE_RECT2`: the GDScript Variant.Type constant. */
  fun gdTypeConstant(type: TypeMapping): String = "TYPE_${type.name}"

  /** The GDScript constructor of [type] from its components (`part(i)` is component i). */
  fun gdConstruct(type: TypeMapping, part: (Int) -> String): String {
    fun n(i: Int) = if (type in WHOLE) "int(${part(i)})" else part(i)
    fun v3(i: Int) = "Vector3(${n(i)}, ${n(i + 1)}, ${n(i + 2)})"
    return when (type) {
      TypeMapping.VECTOR3I -> "Vector3i(${n(0)}, ${n(1)}, ${n(2)})"
      TypeMapping.RECT2 -> "Rect2(${n(0)}, ${n(1)}, ${n(2)}, ${n(3)})"
      TypeMapping.RECT2I -> "Rect2i(${n(0)}, ${n(1)}, ${n(2)}, ${n(3)})"
      TypeMapping.PLANE -> "Plane(${n(0)}, ${n(1)}, ${n(2)}, ${n(3)})"
      TypeMapping.QUATERNION -> "Quaternion(${n(0)}, ${n(1)}, ${n(2)}, ${n(3)})"
      // Packed as the three COLUMNS, which is Basis(x_axis, y_axis, z_axis).
      TypeMapping.BASIS -> "Basis(${v3(0)}, ${v3(3)}, ${v3(6)})"
      TypeMapping.TRANSFORM3D -> "Transform3D(Basis(${v3(0)}, ${v3(3)}, ${v3(6)}), ${v3(9)})"
      else -> error("${type.name} is not a Web value type")
    }
  }

  /** The proxy's `_kanama_web_pack_value(value)` and `_kanama_web_unpack_value(type, packed)`. */
  fun gdHelpers(): String = buildString {
    appendLine("func $PACK(value: Variant) -> String:")
    appendLine("\t# Task 133: a value type as its components; decimals round-trip exactly.")
    appendLine("\tmatch typeof(value):")
    COMPONENTS.forEach { (type, paths) ->
      appendLine("\t\t${gdTypeConstant(type)}:")
      val parts =
        paths.joinToString(", ") {
          if (type in WHOLE) "str(value.$it)" else "String.num_scientific(value.$it)"
        }
      appendLine("\t\t\treturn \",\".join(PackedStringArray([$parts]))")
    }
    appendLine("\treturn str(value)")
    appendLine()
    appendLine("func $UNPACK(type: int, packed: String) -> Variant:")
    appendLine("\tvar p := _kanama_web_floats(packed)")
    appendLine("\tmatch type:")
    COMPONENTS.keys.forEach { type ->
      appendLine("\t\t${gdTypeConstant(type)}:")
      appendLine("\t\t\treturn ${gdConstruct(type) { "p[$it]" }}")
    }
    appendLine("\treturn null")
    appendLine()
  }

  /** The proxy expression packing the GDScript value [expr]. */
  fun gdPack(expr: String): String = "$PACK($expr)"

  /** The proxy expression unpacking [packed] into [type]. */
  fun gdUnpack(type: TypeMapping, packed: String): String =
    "$UNPACK(${gdTypeConstant(type)}, $packed)"

  /** The Kotlin expression packing [access] (a value of a Web value type). */
  fun kotlinPack(access: String): String = "$RUNTIME.encode($access)"

  /** The Kotlin expression decoding [packed] into [type]. */
  fun kotlinUnpack(type: TypeMapping, packed: String): String =
    "($RUNTIME.decode($packed, ${type.kotlinType}::class) as ${type.kotlinType})"

  /** The Kotlin expression building [type] from the numeric-crossing slot expressions [slots]. */
  fun kotlinFromSlots(type: TypeMapping, slots: List<String>): String =
    "($RUNTIME.fromComponents(${type.kotlinType}::class, doubleArrayOf(${slots.joinToString(", ")})) as ${type.kotlinType})"

  const val PACK = "_kanama_web_pack_value"
  const val UNPACK = "_kanama_web_unpack_value"
  private const val RUNTIME = "net.multigesture.kanama.web.WebPackedValues"
}
