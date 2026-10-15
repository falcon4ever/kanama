package net.multigesture.kanama.variantdecode

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType
import net.multigesture.kanama.binding.runtime.VariantTypeNames
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
    assertSame(
      q,
      BuiltinTypes.decodeVariantValue(
        q,
        Quaternion::class.java,
        "property 'rot'",
        VariantType.QUATERNION.id,
      ),
    )
  }

  @Test
  fun wrongTypeNamesPropertyExpectedAndActual() {
    val e =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(
          Vector2(1.0, 2.0),
          Quaternion::class.java,
          "property 'rot'",
          VariantType.VECTOR2.id,
        )
      }
    assertEquals("Expected Quaternion for property 'rot' but the Variant holds Vector2", e.message)
  }

  @Test
  fun nilAndScalarsAreNamedToo() {
    val nil =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(
          null,
          Rect2::class.java,
          "argument 'area'",
          VariantType.NIL.id,
        )
      }
    assertTrue(
      "argument 'area'" in nil.message!! && "Rect2" in nil.message!! && "Nil" in nil.message!!
    )
    val int =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(
          3L,
          Rect2::class.java,
          "argument 'area'",
          VariantType.INT.id,
        )
      }
    assertTrue("holds int" in int.message!!)
  }

  /**
   * A container or an object is named by its Godot type, as GDScript, the property refusal and the
   * iOS runtime name it ("holds Array", "holds Object"), not by the Kotlin class it decoded to
   * (`ArrayList`, `GodotObject`).
   */
  @Test
  fun containersAndObjectsAreNamedByTheirGodotType() {
    val array =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(
          listOf(1L, 2L),
          Quaternion::class.java,
          "argument 'q'",
          VariantType.ARRAY.id,
        )
      }
    assertEquals("Expected Quaternion for argument 'q' but the Variant holds Array", array.message)
    val obj =
      assertFailsWith<IllegalStateException> {
        BuiltinTypes.decodeVariantValue(
          Any(),
          Rect2::class.java,
          "argument 'r'",
          VariantType.OBJECT.id,
        )
      }
    assertEquals("Expected Rect2 for argument 'r' but the Variant holds Object", obj.message)
  }

  /** Task 138 item 26: the refusal of a wrong-typed property value names both Godot types. */
  @Test
  fun assignmentErrorNamesPropertyAndBothGodotTypes() {
    assertEquals(
      "Expected Vector3 for property 'velocity' but the Variant holds Vector2",
      BuiltinTypes.variantAssignmentError(
        VariantType.VECTOR3,
        VariantType.VECTOR2.id,
        "property 'velocity'",
      ),
    )
    assertEquals(
      "Expected int for property 'count' but the Variant holds null (Nil)",
      BuiltinTypes.variantAssignmentError(VariantType.INT, VariantType.NIL.id, "property 'count'"),
    )
    assertEquals(
      "Expected Array for property 'names' but the Variant holds PackedInt32Array",
      BuiltinTypes.variantAssignmentError(
        VariantType.ARRAY,
        VariantType.PACKED_INT32_ARRAY.id,
        "property 'names'",
      ),
    )
  }

  @Test
  fun everyVariantTypeHasAGodotName() {
    VariantType.entries.forEach {
      assertTrue(
        !BuiltinTypes.variantTypeLabel(it.id).startsWith("Variant type"),
        "${it.name} has no label",
      )
    }
  }

  /**
   * Task 138 item 27: the iOS runtime builds its refusal of a wrong-typed value-type property from
   * the registered and the offered `Variant::Type` ids, with this one function (the C shim passes
   * the ids; there is no device here), so the message is pinned on the host.
   */
  @Test
  fun iosRefusalMessageFromVariantTypeIds() {
    assertEquals(
      "Expected Quaternion for property 'rot' but the Variant holds Vector2",
      VariantTypeNames.assignmentError(
        VariantType.QUATERNION.id,
        VariantType.VECTOR2.id,
        "property 'rot'",
      ),
    )
    // A Nil is the same refusal, spelled as the desktop one is.
    assertEquals(
      "Expected Rect2 for property 'area' but the Variant holds null (Nil)",
      VariantTypeNames.assignmentError(VariantType.RECT2.id, VariantType.NIL.id, "property 'area'"),
    )
    assertEquals(
      "Expected Basis for property 'b' but the Variant holds int",
      VariantTypeNames.assignmentError(VariantType.BASIS.id, VariantType.INT.id, "property 'b'"),
    )
    // The desktop message for the same mistake is the same text.
    assertEquals(
      VariantTypeNames.assignmentError(VariantType.QUATERNION.id, VariantType.VECTOR2.id, "p"),
      BuiltinTypes.variantAssignmentError(VariantType.QUATERNION, VariantType.VECTOR2.id, "p"),
    )
  }
}
