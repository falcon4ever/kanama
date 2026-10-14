package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 119 finding 31: a registered function returning `GodotObject?` kept `GodotObject` as its
 * Kotlin type, so the generated registrar passed a nullable to a non-null parameter and the game's
 * module failed to compile. The return keeps its `?`; the glue writes null as nil.
 */
class NullableObjectReturnTest {
  private val godotObject = "net.multigesture.kanama.api.GodotObject"

  private fun model(nullable: Boolean) =
    ScriptModel(
      simpleName = "ObjProbe",
      fqName = "net.multigesture.kanama.test.ObjProbe",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties = emptyList(),
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods =
        listOf(
          MethodModel(
            "pick",
            "pick",
            TypeMapping.OBJECT,
            emptyList(),
            MethodKind.REGULAR,
            returnNullable = nullable,
          )
        ),
      signals = emptyList(),
    )

  @Test
  fun theReturnTypeKeepsItsQuestionMark() {
    assertEquals("$godotObject?", model(nullable = true).methods.single().returnKotlinType)
    assertEquals(godotObject, model(nullable = false).methods.single().returnKotlinType)
  }

  @Test
  fun theMethodHelperReturnsTheNullableType() {
    val source = ScriptCodeEmitter(model(nullable = true), "ObjProbeScriptRegistrar").emit()
    assertTrue(source.contains("fun pick(instance: ObjProbe): $godotObject? ="), source)
    assertFalse(source.contains("fun pick(instance: ObjProbe): $godotObject ="), source)
  }
}
