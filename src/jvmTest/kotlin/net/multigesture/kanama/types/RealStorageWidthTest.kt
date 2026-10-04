package net.multigesture.kanama.types

import java.lang.foreign.Arena
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Task 134 (A2): components are `Double` in every public signature, but a value type stores them at
 * Godot's width — `real_t` (float32 in a default build, float64 with `-PkanamaPrecision=double`)
 * for the vectors and transforms, float32 for `Color` in every build — exactly like GDScript. So
 * equality, printing and basic arithmetic match Godot's, and marshalling moves the stored bits.
 * Expected strings are GDScript's `str(v)` from Godot 4.7.2 (the runtime smoke compares them live).
 */
class RealStorageWidthTest {
  private val single = REAL_IS_SINGLE

  /** What Godot stores for [value] in a `real_t` component, widened back to Double. */
  private fun real(value: Double): Double = if (single) value.toFloat().toDouble() else value

  @Test
  fun componentsAreStoredAtGodotsWidth() {
    val v = Vector3(0.1, 0.2, 0.3)
    assertEquals(real(0.1), v.x)
    assertEquals(real(0.3), v.z)
    // GDScript's caveat, identical here: `v.x = 0.1; v.x == 0.1` is false in a float32 build.
    assertEquals(!single, v.x == 0.1)
    assertTrue(isEqualApprox(v.x, 0.1))
    // Color is float32 in every Godot build.
    assertEquals(0.1f.toDouble(), Color(0.1, 0.2, 0.3).r)
    assertEquals(1.0, Color(0.1, 0.2, 0.3).a)
    // Values exact at the storage width are kept as written.
    assertEquals(1.25, Vector2(1.25, -0.5).x)
    assertEquals(Vector3(1.0, 2.0, 3.0), Vector3(1, 2, 3))
    assertEquals(Color(1.0, 0.0, 0.0, 1.0), Color(1, 0, 0))
  }

  @Test
  fun equalityComparesTheStoredValues() {
    // A value read back from the engine is the stored value: equal to the value written.
    val written = Vector2(0.1, 0.2)
    val readBack = Vector2(written.x, written.y)
    assertEquals(written, readBack)
    assertEquals(written.hashCode(), readBack.hashCode())
    // Godot's `==`: signed zero is equal; NaN stays reflexive for the JVM contract.
    assertEquals(Vector3(0.0, 0.0, 0.0), Vector3(-0.0, 0.0, -0.0))
    assertEquals(Vector3(0.0, 0.0, 0.0).hashCode(), Vector3(-0.0, 0.0, -0.0).hashCode())
    assertEquals(Vector2(Double.NaN, 1.0), Vector2(Double.NaN, 1.0))
    assertEquals(Color(-0.0, 0.0, 0.0), Color(0.0, 0.0, 0.0))
    assertEquals(Color(-0.0, 0.0, 0.0).hashCode(), Color(0.0, 0.0, 0.0).hashCode())
    assertEquals(Plane(Vector3.UP, 0.1), Plane(Vector3.UP, real(0.1)))
    if (single) assertEquals(Vector2(0.1, 0.2), Vector2(0.1f.toDouble(), 0.2f.toDouble()))
  }

  @Test
  fun mixedIntAndDoubleArgumentsResolveToOneOverload() {
    // Compile-time proof that each mix has exactly one applicable overload (an ambiguity, or a
    // boxing `Number` fallback, would not compile or would show up in the API snapshot).
    val x = 1.5
    val y = -2.25
    assertEquals(Vector3(1.5, 0.0, -2.25), Vector3(x, 0, y))
    assertEquals(Vector3(0.0, 1.5, 3.0), Vector3(0, x, 3))
    assertEquals(Vector3(1.0, 2.0, -2.25), Vector3(1, 2, y))
    assertEquals(Vector3(1.5, -2.25, 4.0), Vector3(x, y, 4))
    assertEquals(Vector3(1.5, 0.0, 0.0), Vector3(x, 0, 0))
    assertEquals(Vector3(0.0, 0.0, 1.5), Vector3(0, 0, x))
    assertEquals(Vector3(0.0, 1.5, -2.25), Vector3(0, x, y))
    assertEquals(Vector2(1.5, 0.0), Vector2(x, 0))
    assertEquals(Vector2(0.0, -2.25), Vector2(0, y))
    assertEquals(Color(1.0, 1.0, 1.0, 0.72), Color(1, 1, 1, 0.72))
    assertEquals(Color(1.0, 1.0, 1.0, 1.0), Color(1, 1, 1))
    assertEquals(Vector2(3.0, 0.0), Vector2(1.5, 0.0) * 2L)
  }

  @Test
  fun copyAndDestructuringKeepTheDataClassShape() {
    val v = Vector3(1, 2, 3)
    val (x, y, z) = v
    assertEquals(listOf(1.0, 2.0, 3.0), listOf(x, y, z))
    assertEquals(Vector3(1, 5, 3), v.copy(y = 5.0))
    assertEquals(Color(1.0, 0.5, 0.0, 0.25), Color(1.0, 0.5, 0.0).copy(a = 0.25))
    assertEquals(Quaternion(0, 0, 1, 0), Quaternion.IDENTITY.copy(z = 1.0, w = 0.0))
  }

  @Test
  fun toStringIsGodotsStr() {
    if (!single) return // the strings below are the float32 build's
    assertEquals("(0.1, 0.2)", Vector2(0.1, 0.2).toString())
    assertEquals("(1.0, 2.0, 3.0)", Vector3(1, 2, 3).toString())
    assertEquals("(0.0, 0.5, 0.0)", Vector3(-0.0, 0.5, 1e-7).toString())
    assertEquals("(12345.68, -0.000001)", Vector2(12345.678, -0.000001).toString())
    assertEquals("(100000002004087734272.0, 123456792.0)", Vector2(1e20, 123456789.0).toString())
    assertEquals("(16777216.0, 0.333333)", Vector2(16777216.5, 0.3333333333).toString())
    assertEquals("(0.1, 0.2, 0.3, 0.4)", Vector4(0.1, 0.2, 0.3, 0.4).toString())
    assertEquals("(nan, inf)", Vector2(Double.NaN, Double.POSITIVE_INFINITY).toString())
    assertEquals("(-inf, 9.999999)", Vector2(Double.NEGATIVE_INFINITY, 9.999999).toString())
    assertEquals("(100.0, 1000.0)", Vector2(99.99999, 999.99994).toString())
    assertEquals("(0, 0, 0, 1)", Quaternion.IDENTITY.toString())
    assertEquals("(0.1, 0.2, 0.3, 0.9)", Quaternion(0.1, 0.2, 0.3, 0.9).toString())
    assertEquals("[N: (0.0, 1.0, 0.0), D: 0]", Plane(Vector3.UP, 0).toString())
    assertEquals("[N: (0.0, 1.0, 0.0), D: 2.5]", Plane(Vector3.UP, 2.5).toString())
    assertEquals("(1.0, 0.5, 0.0, 1.0)", Color(1.0, 0.5, 0.0).toString())
    assertEquals("(0.1, 0.2, 0.3, 0.4)", Color(0.1, 0.2, 0.3, 0.4).toString())
    assertEquals(
      "(0.1235, 0.3333, 2.5, -0.0)",
      Color(0.123456, 1.0 / 3.0, 2.5, -0.00001).toString(),
    )
    assertEquals(
      "[P: (0.1, 0.2), S: (3.0, 4.0)]",
      Rect2(Vector2(0.1, 0.2), Vector2(3, 4)).toString(),
    )
    assertEquals(
      "[P: (1.0, 2.0, 3.0), S: (0.5, 0.25, 0.125)]",
      AABB(Vector3(1, 2, 3), Vector3(0.5, 0.25, 0.125)).toString(),
    )
    assertEquals(
      "[X: (1.0, 0.0, 0.0), Y: (0.0, 1.0, 0.0), Z: (0.0, 0.0, 1.0)]",
      Basis.IDENTITY.toString(),
    )
    assertEquals("[X: (1.0, 0.0), Y: (0.0, 1.0), O: (0.0, 0.0)]", Transform2D.IDENTITY.toString())
    assertEquals(
      "[X: (1.0, 0.0, 0.0), Y: (0.0, 1.0, 0.0), Z: (0.0, 0.0, 1.0), O: (0.0, 0.0, 0.0)]",
      Transform3D.IDENTITY.toString(),
    )
    assertEquals(
      "[X: (1.0, 0.0, 0.0, 0.0), Y: (0.0, 1.0, 0.0, 0.0), Z: (0.0, 0.0, 1.0, 0.0), " +
        "W: (0.0, 0.0, 0.0, 1.0)]",
      Projection.IDENTITY.toString(),
    )
  }

  @Test
  fun formatFixedIsCsPrintf() {
    assertEquals("0.12", formatFixed(0.125, 2)) // exact tie: half to even, as printf
    assertEquals("0.38", formatFixed(0.375, 2))
    assertEquals("1.000000", formatFixed(1.0, 6))
    assertEquals("-0.000000", formatFixed(-1e-9, 6))
    assertEquals("0.1000000000000000055511151231257827", formatFixed(0.1, 34))
    assertEquals("123456789012345677877719597056.0", formatFixed(1.2345678901234568e29, 1))
    assertEquals("0.00000000000000000000000000000000", formatFixed(Float.MIN_VALUE.toDouble(), 32))
    assertEquals("-0.0", godotNum(-0.0001, 3)) // printf keeps the sign of a value that rounds to 0
  }

  @Test
  fun basicArithmeticIsGodotsRealTArithmetic() {
    if (!single) return
    // One float32 operation on float32 operands equals the Double operation rounded to float32,
    // and a scalar operand is narrowed to real_t first, as `Vector2 * float` does in Godot.
    val a = Vector2(0.1, 0.2)
    val b = Vector2(0.7, 0.3)
    assertEquals((0.1f + 0.7f).toDouble(), (a + b).x)
    assertEquals((0.2f - 0.3f).toDouble(), (a - b).y)
    assertEquals((0.1f * 0.1f).toDouble(), (a * 0.1).x)
    assertEquals((0.2f / 0.3f).toDouble(), (a / 0.3).y)
    assertEquals("(0.01, 0.02)", (a * 0.1).toString())
    assertEquals("(0.8, 0.5)", (a + b).toString())
    val cross = Vector3(0.1, 0.2, 0.3).cross(Vector3(0.7, 0.11, 0.13))
    assertEquals(0.2f * 0.13f - 0.3f * 0.11f, cross.x.toFloat())
    assertEquals("(-0.007, 0.197, -0.129)", cross.toString())
    assertEquals((0.1f * 0.1f + 0.2f * 0.2f).toDouble(), a.lengthSquared())
    assertEquals(kotlin.math.sqrt(0.1f * 0.1f + 0.2f * 0.2f).toDouble(), a.length())
    assertEquals(0.22360680997372, a.length(), 5e-15) // GDScript prints 0.22360680997372
    assertEquals(0.17000000178814, a.dot(Vector2(0.3, 0.7)), 5e-15)
  }

  @Test
  fun marshallingMovesTheStoredBits() {
    Arena.ofConfined().use { arena ->
      val buf = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
      val v = Vector3(0.1, -2.5, 1.0e10)
      GodotRealSegment.writeRaw(buf, 0, v.rawX)
      GodotRealSegment.writeRaw(buf, 1, v.rawY)
      GodotRealSegment.writeRaw(buf, 2, v.rawZ)
      val back =
        Vector3.raw(
          GodotRealSegment.readRaw(buf, 0),
          GodotRealSegment.readRaw(buf, 1),
          GodotRealSegment.readRaw(buf, 2),
        )
      assertEquals(v, back)
      // The Double pair (what generated script registrars use) agrees with the raw pair.
      assertEquals(v.x, GodotRealSegment.readIndex(buf, 0))
      GodotRealSegment.writeIndex(buf, 0, 0.1)
      assertEquals(v.rawX, GodotRealSegment.readRaw(buf, 0))
    }
    val roundTripped = GodotReal.fromC(GodotReal.toC(0.1))
    if (single) assertNotEquals(0.1, roundTripped) else assertEquals(0.1, roundTripped)
    assertFalse(Vector2(0.1, 0.2) == Vector2(0.1, 0.25))
  }
}
