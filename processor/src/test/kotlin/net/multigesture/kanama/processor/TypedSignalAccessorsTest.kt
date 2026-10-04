package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 134 D4 — typed handles for `@Signal` declarations: a top-level extension property per signal
 * (`val Player.coinCollected: Signal1<Long>`) next to the `<Class>Signals` helpers, on the desktop
 * and iOS emitters. The generated source is compiled by the demos (desktop and iOS builds).
 */
class TypedSignalAccessorsTest {
  private val api = "net.multigesture.kanama.api"
  private val processMode = GodotEnumTable.forKotlin("$api.Node.ProcessMode")!!

  private val signals =
    listOf(
      SignalModel("hit", emptyList()),
      SignalModel("coin_collected", listOf(ArgModel("coins", TypeMapping.INT))),
      SignalModel(
        "moved",
        listOf(
          ArgModel("where", TypeMapping.VECTOR2),
          ArgModel("mode", TypeMapping.INT, godotEnum = processMode.ref),
          ArgModel("by", TypeMapping.OBJECT, objectWrapperFqName = "$api.Node3D"),
          ArgModel("tag", TypeMapping.STRING),
        ),
      ),
      SignalModel(
        "maybe",
        listOf(ArgModel("res", TypeMapping.OBJECT, "$api.Resource", nullable = true)),
      ),
      // No typed decode for a primitive packed array: the signal keeps only its helpers.
      SignalModel("raw", listOf(ArgModel("ints", TypeMapping.PACKED_INT32_ARRAY))),
    )

  private fun model() =
    ScriptModel(
      simpleName = "Player",
      fqName = "demo.Player",
      attachTo = "CharacterBody3D",
      isTool = false,
      isGlobalClass = false,
      properties = emptyList(),
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods = emptyList(),
      signals = signals,
    )

  private fun assertTypedAccessors(source: String) {
    fun has(fragment: String) = assertTrue(source.contains(fragment), "missing: $fragment")
    has("val demo.Player.hit: $api.Signal0\n")
    has("get() = $api.Signal0($api.GodotObject(godotObject), \"hit\")")
    has("val demo.Player.coinCollected: $api.Signal1<Long>")
    has("$api.Signal1($api.GodotObject(godotObject), \"coin_collected\", $api.SignalArgType.LONG)")
    has(
      "val demo.Player.moved: $api.Signal4<net.multigesture.kanama.types.Vector2, ${processMode.kotlinFqName}, $api.Node3D, String>"
    )
    has(
      "$api.SignalArgType.valueOf<net.multigesture.kanama.types.Vector2>(\"Vector2\", net.multigesture.kanama.types.Vector2::class)"
    )
    has(
      "$api.SignalArgType.enumOf(\"${processMode.kotlinFqName}\", { ${processMode.kotlinFqName}(it) }, { it.value })"
    )
    has("$api.SignalArgType.objectOf(\"Node3D\") { $api.Node3D(it) }")
    has("val demo.Player.maybe: $api.Signal1<$api.Resource?>")
    has("$api.SignalArgType.nullableObjectOf(\"Resource\") { $api.Resource(it) }")
    assertFalse(
      source.contains("val demo.Player.raw:"),
      "a signal without a typed decode has no accessor",
    )
    // The factory for an emitter held as a Godot object, in the Signals object (task 134 C review).
    has("    fun coinCollected(emitter: $api.GodotObject): $api.Signal1<Long> =")
    has("        $api.Signal1(emitter, \"coin_collected\", $api.SignalArgType.LONG)")
    has("    fun hit(emitter: $api.GodotObject): $api.Signal0 =")
  }

  @Test
  fun desktopEmitterAddsTypedAccessors() {
    assertTypedAccessors(ScriptCodeEmitter(model(), "PlayerRegistrar").emit())
  }

  @Test
  fun iosEmitterAddsTheSameAccessors() {
    val errors = mutableListOf<String>()
    val constants =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model(), "res://Player.kt")),
          warn = {},
          error = { errors += it },
        )
        .constantsSource()
    assertEquals(emptyList(), errors)
    assertTypedAccessors(constants)
  }

  @Test
  fun moreThanFiveArgumentsKeepOnlyTheHelpers() {
    val six = SignalModel("six", List(6) { ArgModel("a$it", TypeMapping.INT) })
    assertEquals("", typedSignalAccessors("demo.Player", listOf(six)))
  }
}
