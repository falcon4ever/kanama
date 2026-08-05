package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Task 64: the default-literal source parser. The shipped bug: [extractPropertyInitializer] took
 * the FIRST `=` of the collected declaration, so a one-line annotated declaration (`@Export(hint =
 * PropertyHint.RANGE, ...) var x: Long = 2`) yielded annotation-argument garbage, normalized to
 * null, and the Web proxy hydrated the type default (fps `number_of_jumps` = 0) over the Kotlin
 * initializer.
 */
class PropertyInitializerParsingTest {

  private fun initializerOf(name: String, vararg lines: String): String? =
    extractPropertyInitializer(collectPropertyDeclaration(lines.toList(), 0), name)

  @Test
  fun oneLineAnnotatedDeclarationSkipsAnnotationArguments() {
    assertEquals(
      "2",
      initializerOf(
        "numberOfJumps",
        "@Export(hint = PropertyHint.RANGE, hintString = \"0,100,1\") var numberOfJumps: Long = 2",
      ),
    )
    assertEquals(
      "3.0",
      initializerOf(
        "lifetimeRandom",
        "@ScriptProperty(name = \"lifetime_random\") var lifetimeRandom = 3.0",
      ),
    )
    assertEquals(
      "emptyList()",
      initializerOf(
        "forceLoop",
        "@ScriptProperty(name = \"_force_loop\") var forceLoop: List<String> = emptyList()",
      ),
    )
    assertEquals(
      "null",
      initializerOf(
        "cameraBase",
        "@ScriptProperty(name = \"camera_base\") var cameraBase: Node3D? = null",
      ),
    )
  }

  @Test
  fun plainDeclarationsStillParse() {
    assertEquals("8.0", initializerOf("moveSpeed", "var moveSpeed = 8.0"))
    assertEquals("2", initializerOf("jumps", "var jumps: Long = 2"))
    assertEquals("a == b", initializerOf("flag", "val flag = a == b"))
    // Trailing line comments are stripped before collection.
    assertEquals(
      "1.0471975511965976",
      initializerOf("tiltUpperLimit", "var tiltUpperLimit = 1.0471975511965976 // Mathf.PI / 3.0"),
    )
  }

  @Test
  fun declarationsWithoutInitializersYieldNull() {
    assertEquals(null, initializerOf("node", "lateinit var node: Node3D"))
    assertEquals(null, initializerOf("other", "var mismatchedName = 1"))
  }
}
