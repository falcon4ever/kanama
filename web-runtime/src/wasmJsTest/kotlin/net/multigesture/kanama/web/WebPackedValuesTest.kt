package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
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
 * Task 133: the Web value types cross the text channels as their components in a fixed order (the
 * proxy's `_kanama_web_pack_value` / `_kanama_web_unpack_value`, from the processor's
 * `WebValueTypes.COMPONENTS`). The decode reads what the proxy writes (`String.num_scientific`:
 * shortest round-trip decimals, `nan` / `inf` / `-inf`), and every value survives encode + decode.
 */
class WebPackedValuesTest {
  @Test
  fun decodesWhatTheProxyWrites() {
    // What `_kanama_web_pack_value(Rect2(0.1, -0.2, 1e-30, 3.4e38))` prints (Godot 4.7.2,
    // single precision: `String.num_scientific` of each float32 component).
    assertEquals(
      Rect2(Vector2(0.1, -0.2), Vector2(1e-30, 3.4e38)),
      WebPackedValues.decode(
        "0.10000000149011612,-0.20000000298023224,1.0000000031710769e-30,3.3999999521443642e+38",
        Rect2::class,
      ),
    )
    assertEquals(
      Rect2i(Vector2i(Int.MIN_VALUE, Int.MAX_VALUE), Vector2i(7, -9)),
      WebPackedValues.decode("-2147483648,2147483647,7,-9", Rect2i::class),
    )
    assertEquals(
      Vector3i(-7, 8, 2147483647),
      WebPackedValues.decode("-7,8,2147483647", Vector3i::class),
    )
    val plane = WebPackedValues.decode("nan,inf,-inf,-12.75", Plane::class) as Plane
    assertTrue(plane.normal.x.isNaN())
    assertEquals(Double.POSITIVE_INFINITY, plane.normal.y)
    assertEquals(Double.NEGATIVE_INFINITY, plane.normal.z)
    assertEquals(-12.75, plane.d)
    assertEquals(
      Quaternion(0.0, 0.6, 0.0, 0.8),
      WebPackedValues.decode("0,0.6,0,0.8", Quaternion::class),
    )
  }

  @Test
  fun basisAndTransformAreColumnsThenOrigin() {
    val basis = Basis(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0), Vector3(7.0, 8.0, 9.0))
    // Columns x, y, z in order: GDScript's Basis(x_axis, y_axis, z_axis).
    assertEquals("1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0", WebPackedValues.encode(basis))
    val transform = Transform3D(basis, Vector3(10.0, 11.0, 12.0))
    assertEquals(
      "1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0",
      WebPackedValues.encode(transform),
    )
    assertEquals(
      transform,
      WebPackedValues.decode("1,2,3,4,5,6,7,8,9,10,11,12", Transform3D::class),
    )
    assertEquals("-7,8,9", WebPackedValues.encode(Vector3i(-7, 8, 9)))
    assertEquals("1,2,3,4", WebPackedValues.encode(Rect2i(Vector2i(1, 2), Vector2i(3, 4))))
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
