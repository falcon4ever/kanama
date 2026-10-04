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
