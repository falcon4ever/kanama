package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 133 C2 — `Color` is a script type on every backend: an `@Export var tint: Color`, a public
 * `fun f(c: Color): Color` and a `@Signal fun changed(c: Color)` build and marshal four float32
 * channels (Godot's Color is 16 bytes in every build, independent of `real_t`).
 */
class ColorScriptTypeTest {

  private val color = TypeMapping.COLOR

  private val model =
    ScriptModel(
      simpleName = "Tinted",
      fqName = "net.multigesture.kanama.test.Tinted",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = "tint",
            godotName = "tint",
            type = color,
            isMutable = true,
            defaultLiteral = "net.multigesture.kanama.types.Color(1.0, 0.5, 0.25, 1.0)",
          ),
          ScriptPropertyModel(
            kotlinName = "solid",
            godotName = "solid",
            type = color,
            isMutable = true,
            hint = ExportHints.PROPERTY_HINT_COLOR_NO_ALPHA,
            hintString = "",
            defaultLiteral = "net.multigesture.kanama.types.Color.RED",
            explicitHint = true,
          ),
        ),
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods =
        listOf(
          MethodModel(
            kotlinName = "darker",
            godotName = "darker",
            returnType = color,
            args = listOf(ArgModel("c", color)),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "current",
            godotName = "current",
            returnType = color,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          ),
          MethodModel(
            kotlinName = "paint",
            godotName = "paint",
            returnType = null,
            args = listOf(ArgModel("c", color)),
            kind = MethodKind.REGULAR,
          ),
        ),
      signals = listOf(SignalModel("changed", listOf(ArgModel("c", color)))),
    )

  @Test
  fun colorIsAScriptTypeWithGodotsLayout() {
    assertEquals(color, KanamaProcessor.fqToTypeMapping("net.multigesture.kanama.types.Color"))
    assertEquals("COLOR", color.variantTypeEnum)
    assertEquals(16, color.ptrcallSizeBytes)
    // Float32 channels at 0/4/8/12 whatever real_t is; never GodotReal.
    val read = color.readPtrcallArg("p0")
    assertTrue(read.contains("get(JAVA_FLOAT, 12)"), read)
    assertTrue(!read.contains("GodotReal"), read)
    val write = color.writePtrcallReturn("v")
    assertTrue(write.contains("set(JAVA_FLOAT, 12, v.a.toFloat())"), write)
  }

  @Test
  fun defaultLiteralsNormalize() {
    assertEquals(
      "net.multigesture.kanama.types.Color(1.0, 0.5, 0.0)",
      normalizeScriptPropertyDefaultLiteral("Color(1, 0.5f, 0)", color),
    )
    assertEquals(
      "net.multigesture.kanama.types.Color(0.2, 0.3, 0.4, 0.5)",
      normalizeScriptPropertyDefaultLiteral("Color(0.2, 0.3, 0.4, 0.5)", color),
    )
    assertEquals(
      "net.multigesture.kanama.types.Color.WHITE_SMOKE",
      normalizeScriptPropertyDefaultLiteral("Color.WHITE_SMOKE", color),
    )
    assertEquals(null, normalizeScriptPropertyDefaultLiteral("Color.fromHsv(0.1, 1.0, 1.0)", color))
  }

  @Test
  fun colorNoAlphaHintMatchesGdscript() {
    val hint =
      ExportHints.build(
        "Tinted.solid",
        ExportHints.Use("ExportColorNoAlpha", emptyMap()),
        ExportHints.Slot.COLOR,
        "Color",
      )
    assertEquals(ExportHints.Result.Hint(21, ""), hint)
    val wrong =
      ExportHints.build(
        "Tinted.speed",
        ExportHints.Use("ExportColorNoAlpha", emptyMap()),
        ExportHints.Slot.FLOAT,
        "Double",
      )
    assertTrue(wrong is ExportHints.Result.Error && wrong.message.contains("type Color"), "$wrong")
  }

  @Test
  fun desktopRegistrarMarshalsColor() {
    val source = ScriptCodeEmitter(model, "TintedScriptRegistrar").emit()
    assertTrue(
      source.contains("ClassDB.PropertySpec(\"tint\", VariantType.COLOR, 0, \"\", 6)"),
      source,
    )
    assertTrue(source.contains("ClassDB.PropertySpec(\"solid\", VariantType.COLOR, 21, \"\", 6)"))
    assertTrue(source.contains("VariantConverters.variantToType(VariantType.COLOR)"))
    assertTrue(source.contains("Signals.Arg(VariantType.COLOR, c)"), "the signal arg is a Color")
  }

  @Test
  fun iosRegistryMarshalsColor() {
    val errors = mutableListOf<String>()
    // An iOS export skip is a build error too (task 131 item 9), so `errors` covers both.
    val emitter =
      IosScriptCodeEmitter(
        listOf(IosScriptInput(model, "res://Tinted.kt")),
        error = { errors += it },
      )
    val source = emitter.registrySource()
    assertTrue(errors.isEmpty(), errors.toString())
    assertTrue(source.contains("KanamaIosScriptProperty(\"tint\", 20, 0, \"\", 6)"), source)
    assertTrue(source.contains("KanamaIosScriptProperty(\"solid\", 20, 21, \"\", 6)"))
    assertTrue(source.contains("as net.multigesture.kanama.types.Color"), "the call arg decodes")
  }

  @Test
  fun webProxyAndRegistryMarshalColor() {
    val options = mapOf("kanamaRuntimeTarget" to "web")
    assertEquals(emptyList(), WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, options))
    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://Tinted.kt")))
    val proxy = emitter.proxySources().single { it.sourceResourcePath.isNotEmpty() }.source
    assertTrue(proxy.contains("var tint: Color = Color(1.0, 0.5, 0.25, 1.0)"), proxy)
    assertTrue(proxy.contains("var solid: Color = Color.RED"), proxy)
    assertTrue(
      proxy.contains(
        "_kanama_bridge.setColorProperty(_kanama_handle, 1, tint.r, tint.g, tint.b, tint.a)"
      ),
      proxy,
    )
    assertTrue(proxy.contains("tint = Color(_kanama_parts_1[0], _kanama_parts_1[1], "), proxy)
    assertTrue(proxy.contains("TYPE_COLOR:"), "a Color signal payload packs its four channels")
    val registry = emitter.registrySource()
    assertTrue(registry.contains("fun setColorProperty("), registry)
    assertTrue(
      registry.contains("(script as Tinted).tint = net.multigesture.kanama.types.Color(r, g, b, a)")
    )
    assertTrue(registry.contains("it.r},\${it.g},\${it.b},\${it.a}"), "pull packs four channels")
    assertEquals(
      WebDispatchStatus.TYPED,
      WebScriptCodeEmitter.signalDispatch(model.signals.single()).status,
    )
    // Web has no arm for a method taking arguments AND returning a value, whatever the types
    // (pre-existing, Vector3 alike); a Color return and Color arguments each ride an arm.
    model.methods
      .filter { it.kotlinName != "darker" }
      .forEach { method ->
        assertTrue(WebScriptCodeEmitter.methodDispatch(method).isTyped, method.kotlinName)
      }
  }
}
