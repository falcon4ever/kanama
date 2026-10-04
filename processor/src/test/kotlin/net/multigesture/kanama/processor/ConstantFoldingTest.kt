package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/** Task 133 C — constant `@Export` defaults fold to the literal GDScript would compute. */
class ConstantFoldingTest {

  @Test
  fun doubleExpressionsFold() {
    assertEquals("1.0471975511965976", ConstantFolding.foldDoubleLiteral("Mathf.PI / 3.0"))
    assertEquals("1.0471975511965976", ConstantFolding.foldDoubleLiteral("PI / 3.0"))
    assertEquals("1.0471975511965976", ConstantFolding.foldDoubleLiteral("kotlin.math.PI / 3"))
    assertEquals(
      (-60.0 * (Math.PI / 180.0)).toString(),
      ConstantFolding.foldDoubleLiteral("GD.degToRad(-60.0)"),
    )
    assertEquals(
      Math.toRadians(45.0).toString(),
      ConstantFolding.foldDoubleLiteral("Math.toRadians(45.0)"),
    )
    assertEquals("6.283185307179586", ConstantFolding.foldDoubleLiteral("Mathf.TAU"))
    assertEquals("-0.5", ConstantFolding.foldDoubleLiteral("-(1.0 / 2)"))
    assertEquals("2.5", ConstantFolding.foldDoubleLiteral("(1 + 4) * 0.5"))
    assertEquals("1.0e-5", ConstantFolding.foldDoubleLiteral("1e-5"))
    assertEquals("0.5", ConstantFolding.foldDoubleLiteral("1f / 2f"))
  }

  @Test
  fun foldsWithKotlinsTypes() {
    // Task 133 C2: `5 / 2` is Int division (2) before the Double addition, as in Kotlin.
    assertEquals("2.5", ConstantFolding.foldDoubleLiteral("5 / 2 + 0.5"))
    assertEquals("3.0", ConstantFolding.foldDoubleLiteral("5.0 / 2 + 0.5"))
    // Float arithmetic is done in float: Kotlin's `1f / 3f`, widened exactly.
    assertEquals((1f / 3f).toDouble().toString(), ConstantFolding.foldDoubleLiteral("1f / 3f"))
    assertEquals("0.33333334f", ConstantFolding.foldFloatLiteral("1f / 3f"))
    assertEquals("0.5f", ConstantFolding.foldFloatLiteral("1 / 2f"))
    assertNull(ConstantFolding.foldFloatLiteral("1.0 / 3"), "a Double is not a Float default")
    // Float op Double is Double.
    assertEquals(
      (0.1f.toDouble() + 0.2).toString(),
      ConstantFolding.foldDoubleLiteral("0.1f + 0.2"),
    )
    assertEquals("1.0471975511965976", ConstantFolding.foldDoubleLiteral("PI / 3.0"))
    // Int wraps at 32 bits like Kotlin; a Long property's literals are Longs.
    assertEquals((Int.MAX_VALUE + 1).toString(), ConstantFolding.foldIntLiteral("2147483647 + 1"))
    // Kotlin evaluates `val x: Long = 2147483647 + 1` in Int, then widens (task 133 C3 review).
    assertEquals("-2147483648", ConstantFolding.foldLongLiteral("2147483647 + 1"))
    assertEquals("-727379968", ConstantFolding.foldLongLiteral("1000000 * 1000000"))
    assertEquals("6000000000", ConstantFolding.foldLongLiteral("3000000000 * 2"))
    assertNull(ConstantFolding.foldDoubleLiteral("1d"), "Kotlin has no d suffix")
    assertEquals("2", ConstantFolding.foldIntLiteral("5 / 2"))
    assertNull(ConstantFolding.foldIntLiteral("5 / 0"))
    assertNull(ConstantFolding.foldLongLiteral("5 / 2 + 0.5"))
  }

  @Test
  fun integerExpressionsFold() {
    assertEquals("300", ConstantFolding.foldLongLiteral("60 * 5"))
    assertEquals("3", ConstantFolding.foldLongLiteral("7 / 2"))
    assertEquals("-4", ConstantFolding.foldLongLiteral("-(2 + 2)"))
    assertNull(ConstantFolding.foldLongLiteral("1.5 * 2"))
    assertNull(ConstantFolding.foldLongLiteral("PI"))
  }

  @Test
  fun nonConstantsDoNotFold() {
    assertNull(ConstantFolding.foldDoubleLiteral("speed * 2.0"))
    assertNull(ConstantFolding.foldDoubleLiteral("Foo.PI / 3.0"))
    assertNull(ConstantFolding.foldDoubleLiteral("max(1.0, 2.0)"))
    assertNull(ConstantFolding.foldDoubleLiteral("1.0 / 0.0"))
    assertNull(ConstantFolding.foldDoubleLiteral("1.0 +"))
    assertNull(ConstantFolding.foldDoubleLiteral("listOf(1.0)"))
  }

  /** The reviewer's probe (task 133 C3): each fold equals the value Kotlin computes. */
  @Suppress("INTEGER_OVERFLOW", "DIVISION_BY_ZERO")
  @Test
  fun matchesKotlinOnTheReviewProbe() {
    val long1: Long = 2147483647 + 1
    val long2: Long = 1000000 * 1000000
    val long3: Long = 3000000000 * 2
    val rows =
      listOf(
        ConstantFolding.foldIntLiteral("2147483647 + 1") to (2147483647 + 1).toString(),
        ConstantFolding.foldLongLiteral("2147483647 + 1") to long1.toString(),
        ConstantFolding.foldLongLiteral("1000000 * 1000000") to long2.toString(),
        ConstantFolding.foldLongLiteral("3000000000 * 2") to long3.toString(),
        ConstantFolding.foldDoubleLiteral("5 / 2 + 0.5") to (5 / 2 + 0.5).toString(),
        ConstantFolding.foldDoubleLiteral("-(7 / 2) * 1.5") to (-(7 / 2) * 1.5).toString(),
        ConstantFolding.foldDoubleLiteral("2147483647 + 1 + 0.5") to
          (2147483647 + 1 + 0.5).toString(),
        ConstantFolding.foldDoubleLiteral("1f / 3 + 0.0") to (1f / 3 + 0.0).toString(),
        ConstantFolding.foldDoubleLiteral("-1 / 2.0") to (-1 / 2.0).toString(),
        ConstantFolding.foldDoubleLiteral("-2147483648 / 2 * 1.0") to
          (-2147483648 / 2 * 1.0).toString(),
        ConstantFolding.foldFloatLiteral("1f / 3f") to "${1f / 3f}f",
        ConstantFolding.foldFloatLiteral("-1f / 3 * 2") to "${-1f / 3 * 2}f",
        ConstantFolding.foldFloatLiteral("0.1f + 0.2f") to "${0.1f + 0.2f}f",
        ConstantFolding.foldFloatLiteral("sqrt(2f)") to "${kotlin.math.sqrt(2f)}f",
        ConstantFolding.foldIntLiteral("-7 / 2") to (-7 / 2).toString(),
        ConstantFolding.foldIntLiteral("-(-2147483647 - 1)") to (-(-2147483647 - 1)).toString(),
        ConstantFolding.foldIntLiteral("46341 * 46341") to (46341 * 46341).toString(),
        ConstantFolding.foldLongLiteral("-9223372036854775807L - 1") to
          (-9223372036854775807L - 1).toString(),
        ConstantFolding.foldDoubleLiteral("Mathf.PI / 3.0") to (Math.PI / 3.0).toString(),
      )
    rows.forEachIndexed { i, (folded, kotlin) ->
      assertEquals(kotlin.replace("E", "e"), folded, "row $i")
    }
  }
}
