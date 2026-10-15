package net.multigesture.kanama.processor

/**
 * Value types on the Web (task 133; every Godot value type since task 134 D1, when the Web build
 * started compiling the shared value types): Vector3i, Vector4, Vector4i, Rect2, Rect2i, Plane,
 * Quaternion, AABB, Basis, Transform2D, Transform3D and Projection as script types (`@Export`,
 * function arguments and returns, signal payloads). Each crosses the text channels as its
 * components, comma-separated, in **Godot's memory layout** (the order of [LAYOUTS]) -- the order
 * the runtime's `WebPackedValues` reads and writes on the Kotlin side, which is also the order the
 * builtin-call frame holds. A decimal crosses as the text of its IEEE-754 bits (the int64 of the
 * double; the proxy's `_kanama_web_float_text` / `_kanama_web_float`, Kotlin's `WebPackedFloats`),
 * so every value -- NaN, ±INF, -0, denormals -- round-trips exactly in both directions (task 134 D1
 * review S1: decimal text did not; Godot's `String.to_float` is not correctly rounded).
 *
 * Vector2, Vector2i, Vector3 and Color keep their older dedicated arms for members and returns
 * ([COMPONENTS] leaves them out); the generic Variant helpers ([gdHelpers]) cover them too.
 */
internal object WebValueTypes {
  /** Basis rows: `x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z` (Godot stores the rows). */
  private fun rows(prefix: String): List<String> =
    listOf("x", "y", "z").flatMap { row -> listOf("x", "y", "z").map { "$prefix$it.$row" } }

  private fun xyzw(prefix: String, axes: String): List<String> = axes.map { "$prefix$it" }

  /** Every value type's GDScript component paths, in Godot's memory layout. */
  val LAYOUTS: Map<TypeMapping, List<String>> =
    linkedMapOf(
      TypeMapping.VECTOR2 to xyzw("", "xy"),
      TypeMapping.VECTOR2I to xyzw("", "xy"),
      TypeMapping.RECT2 to listOf("position.x", "position.y", "size.x", "size.y"),
      TypeMapping.RECT2I to listOf("position.x", "position.y", "size.x", "size.y"),
      TypeMapping.VECTOR3 to xyzw("", "xyz"),
      TypeMapping.VECTOR3I to xyzw("", "xyz"),
      TypeMapping.TRANSFORM2D to xyzw("x.", "xy") + xyzw("y.", "xy") + xyzw("origin.", "xy"),
      TypeMapping.VECTOR4 to xyzw("", "xyzw"),
      TypeMapping.VECTOR4I to xyzw("", "xyzw"),
      TypeMapping.PLANE to listOf("normal.x", "normal.y", "normal.z", "d"),
      TypeMapping.QUATERNION to xyzw("", "xyzw"),
      TypeMapping.AABB to xyzw("position.", "xyz") + xyzw("size.", "xyz"),
      TypeMapping.BASIS to rows(""),
      TypeMapping.TRANSFORM3D to rows("basis.") + xyzw("origin.", "xyz"),
      TypeMapping.PROJECTION to
        xyzw("x.", "xyzw") + xyzw("y.", "xyzw") + xyzw("z.", "xyzw") + xyzw("w.", "xyzw"),
      TypeMapping.COLOR to xyzw("", "rgba"),
    )

  /** The value types the generic packed-value arms carry (the four older ones have their own). */
  val COMPONENTS: Map<TypeMapping, List<String>> =
    LAYOUTS.filterKeys {
      it !in
        setOf(TypeMapping.VECTOR2, TypeMapping.VECTOR2I, TypeMapping.VECTOR3, TypeMapping.COLOR)
    }

  /** The types whose components are ints (written with `str`, read with `int`). */
  private val WHOLE =
    setOf(TypeMapping.VECTOR2I, TypeMapping.VECTOR3I, TypeMapping.VECTOR4I, TypeMapping.RECT2I)

  fun isWebValueType(type: TypeMapping?): Boolean = type in COMPONENTS

  /** The GDScript type name (`Rect2`), also its `TYPE_*` suffix in upper case. */
  fun gdName(type: TypeMapping): String = type.kotlinType.substringAfterLast('.')

  /** `TYPE_RECT2`: the GDScript Variant.Type constant. */
  fun gdTypeConstant(type: TypeMapping): String = "TYPE_${type.name}"

  /** The GDScript constructor of [type] from its components (`part(i)` is component i). */
  fun gdConstruct(type: TypeMapping, part: (Int) -> String): String {
    fun n(i: Int) = if (type in WHOLE) "int(${part(i)})" else part(i)
    fun list(from: Int, count: Int) = (from until from + count).joinToString(", ") { n(it) }
    fun v2(i: Int) = "Vector2(${list(i, 2)})"
    fun v3(i: Int) = "Vector3(${list(i, 3)})"
    fun v4(i: Int) = "Vector4(${list(i, 4)})"
    // Rows r0 = (0, 1, 2), r1 = (3, 4, 5), r2 = (6, 7, 8): column x is (0, 3, 6).
    fun basis(i: Int) =
      "Basis(Vector3(${n(i)}, ${n(i + 3)}, ${n(i + 6)}), Vector3(${n(i + 1)}, ${n(i + 4)}, ${n(i + 7)}), " +
        "Vector3(${n(i + 2)}, ${n(i + 5)}, ${n(i + 8)}))"
    return when (type) {
      TypeMapping.VECTOR2 -> v2(0)
      TypeMapping.VECTOR2I -> "Vector2i(${list(0, 2)})"
      TypeMapping.RECT2 -> "Rect2(${list(0, 4)})"
      TypeMapping.RECT2I -> "Rect2i(${list(0, 4)})"
      TypeMapping.VECTOR3 -> v3(0)
      TypeMapping.VECTOR3I -> "Vector3i(${list(0, 3)})"
      TypeMapping.TRANSFORM2D -> "Transform2D(${v2(0)}, ${v2(2)}, ${v2(4)})"
      TypeMapping.VECTOR4 -> v4(0)
      TypeMapping.VECTOR4I -> "Vector4i(${list(0, 4)})"
      TypeMapping.PLANE -> "Plane(${list(0, 4)})"
      TypeMapping.QUATERNION -> "Quaternion(${list(0, 4)})"
      TypeMapping.AABB -> "AABB(${v3(0)}, ${v3(3)})"
      TypeMapping.BASIS -> basis(0)
      TypeMapping.TRANSFORM3D -> "Transform3D(${basis(0)}, ${v3(9)})"
      TypeMapping.PROJECTION -> "Projection(${v4(0)}, ${v4(4)}, ${v4(8)}, ${v4(12)})"
      TypeMapping.COLOR -> "Color(${list(0, 4)})"
      else -> error("${type.name} is not a Web value type")
    }
  }

  /**
   * The proxy's value and Variant helpers: `_kanama_web_pack_value(value)` /
   * `_kanama_web_unpack_value(type, packed)` (a value type as its components) and
   * `_kanama_web_pack_variant(value, result)` / `_kanama_web_unpack_variant(text)` (any deliverable
   * Variant as `<Variant.Type>:<payload>`, the runtime's `WebPackedValues.encodeVariant` format).
   */
  fun gdHelpers(): String = buildString {
    appendLine("func $PACK(value: Variant) -> String:")
    appendLine("\t# A value type as its components in Godot's memory layout, each decimal as its")
    appendLine("\t# IEEE-754 bits (exact both ways).")
    appendLine("\tmatch typeof(value):")
    LAYOUTS.forEach { (type, paths) ->
      appendLine("\t\t${gdTypeConstant(type)}:")
      val parts =
        paths.joinToString(", ") {
          if (type in WHOLE) "str(value.$it)" else "_kanama_web_float_text(value.$it)"
        }
      appendLine("\t\t\treturn \",\".join(PackedStringArray([$parts]))")
    }
    appendLine("\treturn str(value)")
    appendLine()
    appendLine("func $UNPACK(type: int, packed: String) -> Variant:")
    appendLine("\tmatch type:")
    LAYOUTS.keys.forEach { type ->
      appendLine("\t\t${gdTypeConstant(type)}:")
      // Ints as written, decimals as their IEEE-754 bits.
      if (type in WHOLE) appendLine("\t\t\tvar p := packed.split(\",\")")
      else appendLine("\t\t\tvar p := _kanama_web_floats(packed)")
      appendLine("\t\t\treturn ${gdConstruct(type) { "p[$it]" }}")
    }
    appendLine("\treturn null")
    appendLine()
    appendLine("func $PACK_VARIANT(value: Variant, result := false) -> String:")
    appendLine("\t# Task 134 D1: one Variant as `<Variant.Type>:<payload>` (a signal argument, a")
    appendLine("\t# builtin call's result); an object crosses as its bridge handle id. A call's")
    appendLine("\t# [result] keeps the handles of the objects in it (see $PACK_HELD_OBJECT).")
    appendLine("\tvar type := typeof(value)")
    appendLine("\tmatch type:")
    appendLine("\t\tTYPE_NIL:")
    appendLine("\t\t\treturn \"0:\"")
    appendLine("\t\tTYPE_BOOL:")
    appendLine("\t\t\treturn \"1:1\" if value else \"1:0\"")
    appendLine("\t\tTYPE_INT:")
    appendLine("\t\t\treturn \"2:\" + str(value)")
    appendLine("\t\tTYPE_FLOAT:")
    appendLine("\t\t\treturn \"3:\" + _kanama_web_float_text(value)")
    appendLine("\t\tTYPE_STRING, TYPE_STRING_NAME, TYPE_NODE_PATH:")
    appendLine("\t\t\treturn \"%d:%s\" % [type, _kanama_web_pack_text(String(value))]")
    appendLine("\t\tTYPE_RID:")
    appendLine("\t\t\treturn \"23:%d\" % value.get_id()")
    appendLine("\t\tTYPE_OBJECT:")
    appendLine("\t\t\tif result:")
    appendLine("\t\t\t\treturn \"24:%d\" % $PACK_HELD_OBJECT(value, true)")
    appendLine("\t\t\treturn \"24:%d\" % _kanama_web_pack_object(value)")
    // Task 134 D2: the packed returns of the String / PackedByteArray builtin methods.
    appendLine("\t\tTYPE_PACKED_BYTE_ARRAY:")
    appendLine("\t\t\treturn \"29:\" + value.hex_encode()")
    appendLine("\t\tTYPE_PACKED_FLOAT64_ARRAY:")
    appendLine("\t\t\tvar floats := PackedStringArray()")
    appendLine("\t\t\tfor f in value:")
    appendLine("\t\t\t\tfloats.append(_kanama_web_float_text(f))")
    appendLine("\t\t\treturn \"33:\" + \",\".join(floats)")
    appendLine("\t\tTYPE_PACKED_STRING_ARRAY:")
    appendLine("\t\t\tvar texts := \"34:%d\" % value.size()")
    appendLine("\t\t\tfor text in value:")
    appendLine("\t\t\t\ttexts += \"\\u001f\" + _kanama_web_pack_text(text)")
    appendLine("\t\t\treturn texts")
    // Task 138 item 2: Arrays, Dictionaries, Callables and Signals (a builtin, utility or generic
    // call's result, a signal argument): each element an encoded Variant, nested (`%`, `,` and the
    // unit separator escaped) so a container rides one comma-separated payload.
    appendLine("\t\tTYPE_ARRAY:")
    appendLine("\t\t\tvar items := PackedStringArray([str(value.size())])")
    appendLine("\t\t\tfor item in value:")
    appendLine("\t\t\t\titems.append($NEST($PACK_VARIANT(item, result)))")
    appendLine("\t\t\treturn \"28:\" + \",\".join(items)")
    appendLine("\t\tTYPE_DICTIONARY:")
    appendLine("\t\t\tvar entries := PackedStringArray([str(value.size())])")
    appendLine("\t\t\tfor key in value:")
    appendLine("\t\t\t\tentries.append($NEST($PACK_VARIANT(key, result)))")
    appendLine("\t\t\t\tentries.append($NEST($PACK_VARIANT(value[key], result)))")
    appendLine("\t\t\treturn \"27:\" + \",\".join(entries)")
    appendLine("\t\tTYPE_CALLABLE:")
    appendLine(
      "\t\t\treturn \"25:%d,%s\" % [$PACK_HELD_OBJECT(value.get_object(), result), $NEST(String(value.get_method()))]"
    )
    appendLine("\t\tTYPE_SIGNAL:")
    appendLine(
      "\t\t\treturn \"26:%d,%s\" % [$PACK_HELD_OBJECT(value.get_object(), result), $NEST(String(value.get_name()))]"
    )
    appendLine("\t\tTYPE_PACKED_INT64_ARRAY:")
    appendLine("\t\t\tvar ints := PackedStringArray()")
    appendLine("\t\t\tfor i in value:")
    appendLine("\t\t\t\tints.append(str(i))")
    appendLine("\t\t\treturn \"31:\" + \",\".join(ints)")
    appendLine("\tif type >= TYPE_VECTOR2 and type <= TYPE_COLOR:")
    appendLine("\t\treturn \"%d:%s\" % [type, $PACK(value)]")
    appendLine("\t# A type Web does not carry: the type alone, so Kotlin reports it by name.")
    appendLine("\treturn \"%d:\" % type")
    appendLine()
    appendLine("func $UNPACK_VARIANT(text: String) -> Variant:")
    appendLine("\tvar split := text.find(\":\")")
    appendLine("\tvar type := int(text.substr(0, split))")
    appendLine("\tvar payload := text.substr(split + 1)")
    appendLine("\tmatch type:")
    appendLine("\t\tTYPE_NIL:")
    appendLine("\t\t\treturn null")
    appendLine("\t\tTYPE_BOOL:")
    appendLine("\t\t\treturn payload == \"1\"")
    appendLine("\t\tTYPE_INT:")
    appendLine("\t\t\treturn int(payload)")
    appendLine("\t\tTYPE_FLOAT:")
    appendLine("\t\t\treturn _kanama_web_float(payload)")
    appendLine("\t\tTYPE_STRING:")
    appendLine("\t\t\treturn _kanama_web_unpack_text(payload)")
    appendLine("\t\tTYPE_STRING_NAME:")
    appendLine("\t\t\treturn StringName(_kanama_web_unpack_text(payload))")
    appendLine("\t\tTYPE_NODE_PATH:")
    appendLine("\t\t\treturn NodePath(_kanama_web_unpack_text(payload))")
    appendLine("\t\tTYPE_RID:")
    appendLine("\t\t\treturn rid_from_int64(int(payload))")
    appendLine("\t\tTYPE_PACKED_BYTE_ARRAY:")
    appendLine("\t\t\treturn payload.hex_decode()")
    appendLine("\t\tTYPE_OBJECT:")
    appendLine("\t\t\treturn _kanama_live_object(int(payload))")
    appendLine("\t\tTYPE_ARRAY:")
    appendLine("\t\t\tvar items := payload.split(\",\")")
    appendLine("\t\t\tvar array := []")
    appendLine("\t\t\tfor index in range(1, int(items[0]) + 1):")
    appendLine("\t\t\t\tarray.append($UNPACK_VARIANT($UNNEST(items[index])))")
    appendLine("\t\t\treturn array")
    appendLine("\t\tTYPE_DICTIONARY:")
    appendLine("\t\t\tvar entries := payload.split(\",\")")
    appendLine("\t\t\tvar dictionary := {}")
    appendLine("\t\t\tfor index in range(int(entries[0])):")
    appendLine(
      "\t\t\t\tdictionary[$UNPACK_VARIANT($UNNEST(entries[2 * index + 1]))] = $UNPACK_VARIANT($UNNEST(entries[2 * index + 2]))"
    )
    appendLine("\t\t\treturn dictionary")
    appendLine("\t\tTYPE_CALLABLE:")
    appendLine("\t\t\tvar callable_parts := payload.split(\",\")")
    appendLine("\t\t\tvar callable_target: Object = _kanama_live_object(int(callable_parts[0]))")
    appendLine("\t\t\treturn Callable(callable_target, StringName($UNNEST(callable_parts[1])))")
    appendLine("\treturn $UNPACK(type, payload)")
    appendLine()
    appendLine("func $NEST(text: String) -> String:")
    appendLine(
      "\treturn text.replace(\"%\", \"%25\").replace(\",\", \"%2C\").replace(\"\\u001f\", \"%1F\")"
    )
    appendLine()
    appendLine("func $UNNEST(text: String) -> String:")
    appendLine(
      "\treturn text.replace(\"%1F\", \"\\u001f\").replace(\"%2C\", \",\").replace(\"%25\", \"%\")"
    )
    appendLine()
    appendLine("func $PACK_HELD_OBJECT(value: Object, result := false) -> int:")
    appendLine(
      "\t# Any object in a Variant: its script handle, else the handle already tracking it, so a"
    )
    appendLine(
      "\t# repeated query reuses one handle and keeps the object's identity. A signal argument is"
    )
    appendLine(
      "\t# lent like a direct one (task 138 item 32, _kanama_lend); a call's result (task 129 E"
    )
    appendLine(
      "\t# review B2) gets a handle of its kind, as a generic call's object return does: a Node or"
    )
    appendLine(
      "\t# plain Object one lives as long as the object, a RefCounted one while Kotlin wraps it."
    )
    appendLine("\tif value == null:")
    appendLine("\t\treturn 0")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tvar script_handle := int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\t\tif script_handle != 0:")
    appendLine("\t\t\treturn script_handle")
    appendLine("\tif not result:")
    appendLine("\t\treturn _kanama_web_pack_object(value)")
    appendLine("\tvar existing := _kanama_tracked_handle(value)")
    appendLine("\tif existing != 0:")
    appendLine("\t\treturn existing")
    appendLine("\tvar kind := \"object\"")
    appendLine("\tif value is Node:")
    appendLine("\t\tkind = \"node\"")
    appendLine("\telif value is Resource:")
    appendLine("\t\tkind = \"resource\"")
    appendLine("\telif value is Tween or value is Tweener:")
    appendLine("\t\tkind = \"tween\"")
    appendLine("\tvar minted := int(_kanama_bridge.mintGenericHandle(_kanama_handle, kind))")
    appendLine(
      "\t# A lambda connection's guard (getConnections) is held by its connection alone: a weak"
    )
    appendLine(
      "\t# entry ([WeakRef], unwrapped by _kanama_live_object), so the answer does not keep it,"
    )
    appendLine("\t# and its Kotlin callback, alive.")
    appendLine("\tvar guard := \"router_handle\" in value and \"callback_id\" in value")
    appendLine("\t_kanama_track(minted, [weakref(value)] if guard else value)")
    appendLine("\treturn minted")
    appendLine()
    appendLine("func _kanama_web_unpack_text(value: String) -> String:")
    appendLine("\treturn value.replace(\"%1F\", \"\\u001f\").replace(\"%25\", \"%\")")
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
  const val PACK_VARIANT = "_kanama_web_pack_variant"
  const val UNPACK_VARIANT = "_kanama_web_unpack_variant"
  const val NEST = "_kanama_web_nest"
  const val UNNEST = "_kanama_web_unnest"
  const val PACK_HELD_OBJECT = "_kanama_web_pack_held_object"
  private const val RUNTIME = "net.multigesture.kanama.web.WebPackedValues"
}
