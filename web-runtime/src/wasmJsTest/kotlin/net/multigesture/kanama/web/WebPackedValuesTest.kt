package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
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

/**
 * Task 133 / 134 D1: the value types cross the text channels as their components in Godot's memory
 * layout (the proxy's `_kanama_web_pack_value` / `_kanama_web_unpack_value`, from the processor's
 * `WebValueTypes.LAYOUTS`), and a Variant as `<Variant.Type>:<payload>`. A decimal is the text of
 * its IEEE-754 bits (task 134 D1 review S1), and every value survives encode + decode.
 */
class WebPackedValuesTest {
  /** Decimals as the proxy writes them: the text of their IEEE-754 bits (task 134 D1 review S1). */
  private fun b(vararg values: Double) = values.joinToString(",") { WebPackedFloats.encode(it) }

  @Test
  fun decodesWhatTheProxyWrites() {
    // `_kanama_web_pack_value(Rect2(0.1, -0.2, 1e-30, 3.4e38))`: each float32 component widened
    // to a double, written as its bits.
    val f = floatArrayOf(0.1f, -0.2f, 1e-30f, 3.4e38f).map { it.toDouble() }
    assertEquals(
      Rect2(Vector2(0.1, -0.2), Vector2(1e-30, 3.4e38)),
      WebPackedValues.decode(b(f[0], f[1], f[2], f[3]), Rect2::class),
    )
    // Int components are their decimals.
    assertEquals(
      Rect2i(Vector2i(Int.MIN_VALUE, Int.MAX_VALUE), Vector2i(7, -9)),
      WebPackedValues.decode("-2147483648,2147483647,7,-9", Rect2i::class),
    )
    assertEquals(
      Vector3i(-7, 8, 2147483647),
      WebPackedValues.decode("-7,8,2147483647", Vector3i::class),
    )
    val plane =
      WebPackedValues.decode(
        b(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, -12.75),
        Plane::class,
      ) as Plane
    assertTrue(plane.normal.x.isNaN())
    assertEquals(Double.POSITIVE_INFINITY, plane.normal.y)
    assertEquals(Double.NEGATIVE_INFINITY, plane.normal.z)
    assertEquals(-12.75, plane.d)
  }

  @Test
  fun basisAndTransformAreRowsThenOrigin() {
    val basis = Basis(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0), Vector3(7.0, 8.0, 9.0))
    // Task 134 D1: Godot's memory layout, the rows (x.x, y.x, z.x, …), as the builtin frame holds
    // them.
    assertEquals(b(1.0, 4.0, 7.0, 2.0, 5.0, 8.0, 3.0, 6.0, 9.0), WebPackedValues.encode(basis))
    val transform = Transform3D(basis, Vector3(10.0, 11.0, 12.0))
    assertEquals(
      b(1.0, 4.0, 7.0, 2.0, 5.0, 8.0, 3.0, 6.0, 9.0, 10.0, 11.0, 12.0),
      WebPackedValues.encode(transform),
    )
    assertEquals("-7,8,9", WebPackedValues.encode(Vector3i(-7, 8, 9)))
    assertEquals("1,2,3,4", WebPackedValues.encode(Rect2i(Vector2i(1, 2), Vector2i(3, 4))))
    assertEquals(
      b(1.0, 2.0, 3.0, 4.0, 5.0, 6.0),
      WebPackedValues.encode(Transform2D(Vector2(1.0, 2.0), Vector2(3.0, 4.0), Vector2(5.0, 6.0))),
    )
  }

  @Test
  fun variantsCarryTheirType() {
    assertEquals("2:-9007199254740993", WebPackedValues.encodeVariant(-9007199254740993L))
    assertEquals("3:${b(Double.NaN)}", WebPackedValues.encodeVariant(Double.NaN))
    assertEquals("3:-9223372036854775808", WebPackedValues.encodeVariant(-0.0))
    assertEquals("1:1", WebPackedValues.encodeVariant(true))
    assertEquals("4:a%1Fb%25", WebPackedValues.encodeVariant("a\u001Fb%"))
    assertEquals(
      "12:${b(1.0, 2.0, 3.0, 4.0)}",
      WebPackedValues.encodeVariant(Vector4(1.0, 2.0, 3.0, 4.0)),
    )
    assertEquals(-9007199254740993L, WebPackedValues.decodeVariant("2:-9007199254740993"))
    assertEquals("a\u001Fb%", WebPackedValues.decodeVariant("4:a%1Fb%25"))
    assertEquals("n", WebPackedValues.decodeVariant("21:n"))
    assertEquals(null, WebPackedValues.decodeVariant("0:"))
    assertEquals(Vector4i(1, -2, 3, -4), WebPackedValues.decodeVariant("13:1,-2,3,-4"))
  }

  @Test
  fun everyValueRoundTrips() {
    val values =
      listOf(
        Vector3i(Int.MIN_VALUE, 0, Int.MAX_VALUE),
        Rect2(Vector2(1.0 / 3.0, -0.0), Vector2(1e-30, 3.4e38)),
        Rect2i(Vector2i(-1, 2), Vector2i(30, 40)),
        Plane(Vector3(0.6, 0.8, 0.0), Double.NEGATIVE_INFINITY),
        Quaternion(0.1, 0.2, 0.3, 0.9),
        Basis(Vector3(0.1, 0.2, 0.3), Vector3(0.4, 0.5, 0.6), Vector3(0.7, 0.8, 0.9)),
        Transform3D(Basis.IDENTITY, Vector3(1e-7, -2.5, 1e20)),
        Vector2(0.1, -0.0),
        Vector2i(Int.MIN_VALUE, 7),
        Vector3(1.0 / 3.0, 2.0, 3.0),
        Color(0.25, 0.5, 1.5, -1.0),
        Vector4(0.1, 0.2, 0.3, Double.POSITIVE_INFINITY),
        Vector4i(1, -2, Int.MAX_VALUE, Int.MIN_VALUE),
        AABB(Vector3(-1.0, 2.0, 0.5), Vector3(3.0, 4.0, 1e-30)),
        Transform2D(Vector2(0.6, 0.8), Vector2(-0.8, 0.6), Vector2(10.0, -20.0)),
        Projection(
          Vector4(1.0, 2.0, 3.0, 4.0),
          Vector4(5.0, 6.0, 7.0, 8.0),
          Vector4(9.0, 10.0, 11.0, 12.0),
          Vector4(13.0, 14.0, 15.0, 0.1),
        ),
      )
    for (value in values) {
      assertEquals(
        value,
        WebPackedValues.decode(WebPackedValues.encode(value), value::class),
        "$value",
      )
    }
  }
}
