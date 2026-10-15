package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 138 item 6: a `@RegisterClass` method returning an object hands Godot a reference of its
 * own. The ptrcall upcall writes through `BuiltinTypes.objectReturnSegment` (a `+1` for Godot's
 * `Ref<T>` slot when the object is RefCounted, the wrapper kept reachable until it is taken), and
 * the Variant upcall through `BuiltinTypes.initObjectValueVariant` (the fenced Variant encoder),
 * never the raw pointer of a wrapper the collector may already have dropped.
 */
class RegisterClassObjectReturnTest {
  private val runtime = "net.multigesture.kanama.binding.runtime.BuiltinTypes"

  private val source =
    CodeEmitter(
        ClassModel(
          simpleName = "Maker",
          fqName = "net.multigesture.kanama.test.Maker",
          parentClassName = "Node",
          isTool = false,
          methods =
            listOf(
              MethodModel("make", "make", TypeMapping.OBJECT, emptyList(), MethodKind.REGULAR)
            ),
          properties = emptyList(),
          virtuals = emptyList(),
          signals = emptyList(),
        ),
        "MakerRegistrar",
      )
      .emit()

  @Test
  fun thePtrcallUpcallHandsGodotItsOwnReference() {
    assertTrue(source.contains("set(ADDRESS, 0, $runtime.objectReturnSegment(result))"), source)
    assertFalse(source.contains("objectValueSegment(result)"), source)
  }

  @Test
  fun theVariantUpcallGoesThroughTheFencedEncoder() {
    assertTrue(source.contains("$runtime.initObjectValueVariant(rReturn, result)"), source)
    assertFalse(source.contains("VariantType.OBJECT).invoke(rReturn"), source)
  }
}
