package net.multigesture.kanama.variantdecode

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Task 138 item 9: a script property or argument whose Variant holds another type used to decode to
 * the zero value (`as? T ?: ZERO`). It is now an error that names the property or argument, the
 * expected type and the actual type.
 */
class VariantValueDecodeTest {
  @Test
  fun matchingTypeIsReturnedUnchanged() {
    val q = Quaternion(0.0, 1.0, 0.0, 0.0)
    assertSame(q, BuiltinTypes.decodeVariantValue(q, Quaternion::class.java, "property 'rot'"))
  }

  @Test
  fun wrongTypeNamesPropertyExpectedAndActual() {
    val e =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(Vector2(1.0, 2.0), Quaternion::class.java, "property 'rot'")
      }
    assertEquals("Expected Quaternion for property 'rot' but the Variant holds Vector2", e.message)
  }

  @Test
  fun nilAndScalarsAreNamedToo() {
    val nil =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(null, Rect2::class.java, "argument 'area'")
      }
    assertTrue(
      "argument 'area'" in nil.message!! && "Rect2" in nil.message!! && "Nil" in nil.message!!
    )
    val int =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(3L, Rect2::class.java, "argument 'area'")
      }
    assertTrue("holds int" in int.message!!)
  }
}
