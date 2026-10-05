package net.multigesture.kanama.web

import kotlin.reflect.KClass
import net.multigesture.kanama.binding.runtime.BuiltinFrame
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4
import net.multigesture.kanama.types.Vector4i
import net.multigesture.kanama.types.put
import net.multigesture.kanama.types.retAABB
import net.multigesture.kanama.types.retBasis
import net.multigesture.kanama.types.retColor
import net.multigesture.kanama.types.retPlane
import net.multigesture.kanama.types.retProjection
import net.multigesture.kanama.types.retQuaternion
import net.multigesture.kanama.types.retRect2
import net.multigesture.kanama.types.retRect2i
import net.multigesture.kanama.types.retTransform2D
import net.multigesture.kanama.types.retTransform3D
import net.multigesture.kanama.types.retVector2
import net.multigesture.kanama.types.retVector2i
import net.multigesture.kanama.types.retVector3
import net.multigesture.kanama.types.retVector3i
import net.multigesture.kanama.types.retVector4
import net.multigesture.kanama.types.retVector4i

/**
 * The value types on the Web text channels (task 133, every value type since task 134 D1): a value
 * is its components, comma-separated, in **Godot's memory layout** -- the order the builtin-call
 * frame holds them (`BuiltinFrame.put`), so one layout serves script members, signal arguments and
 * builtin calls, and the proxy's `_kanama_web_pack_value` / `_kanama_web_unpack_value` (the
 * processor's `WebValueTypes.COMPONENTS`) read and write the same order:
 * - Vector2/3/4 (and `i`) `x,y[,z[,w]]`; Quaternion `x,y,z,w`; Color `r,g,b,a`
 * - Rect2 / Rect2i `position.x,position.y,size.x,size.y`; AABB `position.xyz,size.xyz`
 * - Plane `normal.x,normal.y,normal.z,d`
 * - Basis its three ROWS (`x.x,y.x,z.x, x.y,y.y,z.y, x.z,y.z,z.z`); Transform3D the basis rows then
 *   `origin.xyz`; Transform2D `x.xy,y.xy,origin.xy`; Projection its columns `x.xyzw … w.xyzw`
 *
 * A decimal is the text of its IEEE-754 bits ([WebPackedFloats], the proxy's
 * `_kanama_web_float_text` / `_kanama_web_float`), exact both ways (task 134 D1 review S1); an int
 * component is its decimal.
 *
 * A **Variant** crosses as `<Variant.Type>:<payload>` ([encodeVariant] / [decodeVariant], the
 * proxy's `_kanama_web_pack_variant` / `_kanama_web_unpack_variant`): bool `1`/`0`, an int as its
 * decimal, a float as its IEEE-754 bits, String/StringName/NodePath %-escaped text, an object as
 * its bridge handle id, a value type as above, a RID as its id.
 */
internal object WebPackedValues {
  const val TYPE_NIL = 0
  const val TYPE_BOOL = 1
  const val TYPE_INT = 2
  const val TYPE_FLOAT = 3
  const val TYPE_STRING = 4
  const val TYPE_STRING_NAME = 21
  const val TYPE_NODE_PATH = 22
  const val TYPE_RID = 23
  const val TYPE_OBJECT = 24

  /** Component count of each value type, by `Variant.Type` (5 = Vector2 … 20 = Color). */
  private val COMPONENT_COUNT = intArrayOf(2, 2, 4, 4, 3, 3, 6, 4, 4, 4, 4, 6, 9, 12, 16, 4)

  /** The value types whose components are int32. */
  private fun isWhole(type: Int): Boolean = type == 6 || type == 8 || type == 10 || type == 13

  fun isValueType(type: Int): Boolean = type in 5..20

  fun componentCount(type: Int): Int = COMPONENT_COUNT[type - 5]

  private val scratch = BuiltinFrame()

  /** The `Variant.Type` of a value type, or 0 when [type] is not one. */
  fun variantTypeOf(type: KClass<*>): Int =
    when (type) {
      Vector2::class -> 5
      Vector2i::class -> 6
      Rect2::class -> 7
      Rect2i::class -> 8
      Vector3::class -> 9
      Vector3i::class -> 10
      Transform2D::class -> 11
      Vector4::class -> 12
      Vector4i::class -> 13
      Plane::class -> 14
      Quaternion::class -> 15
      AABB::class -> 16
      Basis::class -> 17
      Transform3D::class -> 18
      Projection::class -> 19
      Color::class -> 20
      else -> 0
    }

  /** [value]'s components in the channel order, or null when it is not a value type. */
  fun components(value: Any): DoubleArray? {
    val type = variantTypeOf(value::class)
    if (type == 0) return null
    when (value) {
      is Vector2 -> scratch.put(0, value)
      is Vector2i -> scratch.put(0, value)
      is Rect2 -> scratch.put(0, value)
      is Rect2i -> scratch.put(0, value)
      is Vector3 -> scratch.put(0, value)
      is Vector3i -> scratch.put(0, value)
      is Transform2D -> scratch.put(0, value)
      is Vector4 -> scratch.put(0, value)
      is Vector4i -> scratch.put(0, value)
      is Plane -> scratch.put(0, value)
      is Quaternion -> scratch.put(0, value)
      is AABB -> scratch.put(0, value)
      is Basis -> scratch.put(0, value)
      is Transform3D -> scratch.put(0, value)
      is Projection -> scratch.put(0, value)
      is Color -> scratch.put(0, value)
    }
    return scratch.slots[0].copyOf(componentCount(type))
  }

  /** The value of `Variant.Type` [type] built from its channel-order components [c]. */
  fun fromComponents(type: Int, c: DoubleArray): Any {
    require(isValueType(type)) { "no Web packed layout for Variant type $type" }
    c.copyInto(scratch.ret, 0, 0, componentCount(type))
    return when (type) {
      5 -> scratch.retVector2()
      6 -> scratch.retVector2i()
      7 -> scratch.retRect2()
      8 -> scratch.retRect2i()
      9 -> scratch.retVector3()
      10 -> scratch.retVector3i()
      11 -> scratch.retTransform2D()
      12 -> scratch.retVector4()
      13 -> scratch.retVector4i()
      14 -> scratch.retPlane()
      15 -> scratch.retQuaternion()
      16 -> scratch.retAABB()
      17 -> scratch.retBasis()
      18 -> scratch.retTransform3D()
      19 -> scratch.retProjection()
      else -> scratch.retColor()
    }
  }

  /**
   * The value of [type] built from its channel-order components [c]; the mirror of [components].
   */
  fun fromComponents(type: KClass<*>, c: DoubleArray): Any =
    fromComponents(
      variantTypeOf(type).takeIf { it != 0 }
        ?: error("no Web packed layout for ${type.simpleName}"),
      c,
    )

  /**
   * [value] as its packed text (decimals as their bits through [WebPackedFloats], ints as written).
   */
  fun encode(value: Any): String {
    val c = components(value) ?: error("no Web packed layout for ${value::class.simpleName}")
    return encodeComponents(variantTypeOf(value::class), c, c.size)
  }

  internal fun encodeComponents(type: Int, c: DoubleArray, count: Int): String = buildString {
    for (i in 0 until count) {
      if (i > 0) append(',')
      append(if (isWhole(type)) c[i].toLong().toString() else WebPackedFloats.encode(c[i]))
    }
  }

  /** The [type] value packed as [packed] (what the proxy or [encode] wrote). */
  fun decode(packed: String, type: KClass<*>): Any {
    val variantType =
      variantTypeOf(type).takeIf { it != 0 } ?: error("no Web packed layout for ${type.simpleName}")
    return fromComponents(variantType, decodeComponents(variantType, packed))
  }

  /**
   * The components of a packed value of `Variant.Type` [type]: ints as written, decimals as bits.
   */
  internal fun decodeComponents(type: Int, packed: String): DoubleArray {
    val parts = packed.split(',')
    return if (isWhole(type)) DoubleArray(parts.size) { parts[it].trim().toLong().toDouble() }
    else DoubleArray(parts.size) { WebPackedFloats.decode(parts[it]) }
  }

  /**
   * `%`-escapes text so it cannot split a unit-separated list (the proxy's
   * `_kanama_web_pack_text`).
   */
  fun escapeText(value: String): String = value.replace("%", "%25").replace("\u001F", "%1F")

  fun unescapeText(value: String): String = value.replace("%1F", "\u001F").replace("%25", "%")

  /** One `<Variant.Type>:<payload>` Variant (see the class doc); objects are not encoded here. */
  fun encodeVariant(value: Any?): String =
    when (value) {
      null -> "0:"
      is Boolean -> if (value) "1:1" else "1:0"
      is Int -> "2:$value"
      is Long -> "2:$value"
      is Double -> "3:${WebPackedFloats.encode(value)}"
      is Float -> "3:${WebPackedFloats.encode(value.toDouble())}"
      is String -> "4:${escapeText(value)}"
      is NodePath -> "22:${escapeText(value.path)}"
      is RID -> "23:${value.value}"
      is net.multigesture.kanama.api.GodotEnumValue -> "2:${value.value}"
      else -> {
        val type = variantTypeOf(value::class)
        require(type != 0) { "Kanama Web cannot encode a ${value::class.simpleName} Variant" }
        "$type:${encode(value)}"
      }
    }

  /** The `Variant.Type` of an encoded Variant. */
  fun variantType(encoded: String): Int = encoded.substringBefore(':').toInt()

  /** The payload of an encoded Variant. */
  fun variantPayload(encoded: String): String = encoded.substringAfter(':')

  /**
   * An encoded Variant as Kotlin: null, Boolean, Long, Double, String (String/StringName),
   * NodePath, RID or a value type. An object (type 24) is the caller's to resolve (its payload is a
   * handle id).
   */
  fun decodeVariant(encoded: String): Any? {
    val type = variantType(encoded)
    val payload = variantPayload(encoded)
    return when (type) {
      TYPE_NIL -> null
      TYPE_BOOL -> payload == "1"
      TYPE_INT -> payload.toLong()
      TYPE_FLOAT -> WebPackedFloats.decode(payload)
      TYPE_STRING,
      TYPE_STRING_NAME -> unescapeText(payload)
      TYPE_NODE_PATH -> NodePath(unescapeText(payload))
      TYPE_RID -> RID(payload.toLong())
      in 5..20 -> fromComponents(type, decodeComponents(type, payload))
      else -> error("Kanama Web does not deliver a Variant of type $type here")
    }
  }
}
