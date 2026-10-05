package net.multigesture.kanama.web

import kotlin.reflect.KClass
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
 * Task 133 value types on the Web text channels: a value is its components, comma-separated, in one
 * fixed order per type — the order the proxy's `_kanama_web_pack_value` writes and
 * `_kanama_web_unpack_value` reads (the processor's `WebValueTypes.COMPONENTS`):
 * - Vector3i `x,y,z`; Quaternion `x,y,z,w`
 * - Rect2 / Rect2i `position.x,position.y,size.x,size.y`; Plane `normal.x,normal.y,normal.z,d`
 * - Basis its three COLUMNS `x.xyz,y.xyz,z.xyz`; Transform3D `basis` (as Basis) then `origin.xyz`
 *
 * Vector4, Vector4i, AABB, Transform2D and Projection have no Web value type yet, so they are not
 * Web script types.
 *
 * Decimals use [WebPackedFloats] (Kotlin's shortest round-trip text, `nan`/`inf`/`-inf`); the proxy
 * writes them with `String.num_scientific`, which is round-trip exact too.
 */
internal object WebPackedValues {
  /** [value]'s components in the channel order, or null when it is not one of these types. */
  fun components(value: Any): DoubleArray? =
    when (value) {
      is Vector3i -> doubles(value.x, value.y, value.z)
      is Rect2 -> doubleArrayOf(value.position.x, value.position.y, value.size.x, value.size.y)
      is Rect2i -> doubles(value.position.x, value.position.y, value.size.x, value.size.y)
      is Plane -> doubleArrayOf(value.normal.x, value.normal.y, value.normal.z, value.d)
      is Quaternion -> doubleArrayOf(value.x, value.y, value.z, value.w)
      is Basis -> columns(value)
      is Transform3D ->
        columns(value.basis) + doubleArrayOf(value.origin.x, value.origin.y, value.origin.z)
      else -> null
    }

  /**
   * The value of [type] built from its channel-order components [c]; the mirror of [components].
   */
  fun fromComponents(type: KClass<*>, c: DoubleArray): Any {
    fun n(i: Int) = c[i].toInt()
    fun v2(i: Int) = Vector2(c[i], c[i + 1])
    fun v3(i: Int) = Vector3(c[i], c[i + 1], c[i + 2])
    return when (type) {
      Vector3i::class -> Vector3i(n(0), n(1), n(2))
      Rect2::class -> Rect2(v2(0), v2(2))
      Rect2i::class -> Rect2i(Vector2i(n(0), n(1)), Vector2i(n(2), n(3)))
      Plane::class -> Plane(v3(0), c[3])
      Quaternion::class -> Quaternion(c[0], c[1], c[2], c[3])
      Basis::class -> Basis(v3(0), v3(3), v3(6))
      Transform3D::class -> Transform3D(Basis(v3(0), v3(3), v3(6)), v3(9))
      else -> error("no Web packed layout for ${type.simpleName}")
    }
  }

  /** [value] as its packed text (decimals through [WebPackedFloats.encode], ints as written). */
  fun encode(value: Any): String {
    val c = components(value) ?: error("no Web packed layout for ${value::class.simpleName}")
    val whole = value is Vector3i || value is Rect2i
    return c.joinToString(",") { if (whole) it.toInt().toString() else WebPackedFloats.encode(it) }
  }

  /** The [type] value packed as [packed] (what the proxy or [encode] wrote). */
  fun decode(packed: String, type: KClass<*>): Any =
    fromComponents(type, packed.split(',').map(WebPackedFloats::decode).toDoubleArray())

  private fun doubles(vararg values: Int): DoubleArray =
    DoubleArray(values.size) { values[it].toDouble() }

  private fun columns(b: Basis): DoubleArray =
    doubleArrayOf(b.x.x, b.x.y, b.x.z, b.y.x, b.y.y, b.y.z, b.z.x, b.z.y, b.z.z)
}
