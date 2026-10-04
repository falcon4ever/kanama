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
    assertEquals("2147483648", ConstantFolding.foldLongLiteral("2147483647 + 1"))
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
}
