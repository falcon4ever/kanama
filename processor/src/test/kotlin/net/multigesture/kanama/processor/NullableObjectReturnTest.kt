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
    assertTrue(source.contains("fun pick(instance: ThisClass): $godotObject? ="), source)
    assertFalse(source.contains("fun pick(instance: ThisClass): $godotObject ="), source)
  }

  @Test
  fun theTargetHelperDoesNotDoubleTheQuestionMarkOnDesktop() {
    for (nullable in listOf(true, false)) {
      val source = ScriptCodeEmitter(model(nullable), "ObjProbeScriptRegistrar").emit()
      assertFalse(source.contains("??"), source)
      assertTrue(source.contains("fun pick(target: $godotObject): $godotObject? ="), source)
    }
  }

  @Test
  fun theWebMethodHelpersDoNotDoubleTheQuestionMark() {
    for (nullable in listOf(true, false)) {
      val emitter =
        WebScriptCodeEmitter(listOf(WebScriptInput(model(nullable), "res://ObjProbe.kt")))
      val constants = emitter.constantsSource()
      assertFalse(constants.contains("??"), constants)
      assertTrue(constants.contains("fun pick(target: $godotObject): $godotObject? ="), constants)
      assertEquals(
        nullable,
        constants.contains(
          "fun pick(instance: net.multigesture.kanama.test.ObjProbe): $godotObject? ="
        ),
        constants,
      )
      val registry = emitter.registrySource()
      assertFalse(registry.contains("??"), registry)
      emitter.proxySources().forEach { assertFalse(it.source.contains("??"), it.source) }
    }
  }
}
