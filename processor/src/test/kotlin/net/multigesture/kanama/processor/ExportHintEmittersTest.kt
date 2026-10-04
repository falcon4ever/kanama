package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Task 133 C — a typed hint annotation's hint, hint string and usage reach all three emitters
 * unchanged: the desktop/Android registrar's property spec and dictionary, the iOS registry, and
 * the Web proxy (`@export_custom`, `@export_storage`). And a script whose members come from a
 * superclass (`ScriptInheritance`) is emitted like any other: the registrars call the inherited
 * Kotlin members on the subclass instance.
 */
class ExportHintEmittersTest {

  private val model =
    ScriptModel(
      simpleName = "HintFixture",
      fqName = "net.multigesture.kanama.test.HintFixture",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = "health",
            godotName = "health",
            type = TypeMapping.INT,
            isMutable = true,
            hint = 1,
            hintString = "0.0,100.0,1.0",
            defaultLiteral = "100",
            explicitHint = true,
          ),
          ScriptPropertyModel(
            kotlinName = "icons",
            godotName = "icons",
            type = TypeMapping.ARRAY,
            isMutable = true,
            hint = 23,
            hintString = "4/13:*.png",
            defaultLiteral = "emptyList()",
            arrayElementString = true,
            explicitHint = true,
          ),
          ScriptPropertyModel(
            kotlinName = "saved",
            godotName = "saved",
            type = TypeMapping.INT,
            isMutable = true,
            defaultLiteral = "3",
            usage = 2,
          ),
          ScriptPropertyModel(
            kotlinName = "fov",
            godotName = "fov",
            type = TypeMapping.FLOAT,
            isMutable = true,
            hint = 1,
            hintString = "0.0,360.0,0.1,radians_as_degrees",
            // `Mathf.PI / 3.0`, folded by ConstantFolding.
            defaultLiteral = "1.0471975511965976",
            explicitHint = true,
          ),
        ),
      toolButtons = emptyList(),
      // `ready` declared on a superclass, wired as `_ready` (an inherited lifecycle handler).
      virtuals = listOf(VirtualModel("_ready", "ready", "ready")),
      methods =
        listOf(
          MethodModel(
            kotlinName = "describe",
            godotName = "describe",
            returnType = TypeMapping.STRING,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          )
        ),
      signals = emptyList(),
    )

  @Test
  fun desktopRegistrarReportsTheHintVerbatim() {
    val source = ScriptCodeEmitter(model, "HintFixtureScriptRegistrar").emit()
    assertTrue(
      source.contains("ClassDB.PropertySpec(\"health\", VariantType.INT, 1, \"0.0,100.0,1.0\", 6)"),
      source,
    )
    assertTrue(
      source.contains("ClassDB.PropertySpec(\"icons\", VariantType.ARRAY, 23, \"4/13:*.png\", 6)")
    )
    assertTrue(source.contains("ClassDB.PropertySpec(\"saved\", VariantType.INT, 0, \"\", 2)"))
    assertTrue(source.contains("1.0471975511965976"), "the folded default is reported")
    assertTrue(source.contains(".ready()"), "the inherited handler is called on the instance")
  }

  @Test
  fun iosRegistryReportsTheHintVerbatim() {
    val errors = mutableListOf<String>()
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model, "res://HintFixture.kt")),
          error = { errors += it },
        )
        .registrySource()
    assertTrue(errors.isEmpty(), errors.toString())
    assertTrue(
      source.contains("KanamaIosScriptProperty(\"health\", 2, 1, \"0.0,100.0,1.0\", 6)"),
      source,
    )
    assertTrue(source.contains("KanamaIosScriptProperty(\"saved\", 2, 0, \"\", 2)"))
  }

  @Test
  fun webProxyDeclaresTheHintVerbatim() {
    val options = mapOf("kanamaRuntimeTarget" to "web")
    assertTrue(WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, options).isEmpty())
    val proxy =
      WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://HintFixture.kt")))
        .proxySources()
        .single { it.sourceResourcePath.isNotEmpty() }
        .source
    assertTrue(proxy.contains("@export_custom(1, \"0.0,100.0,1.0\") var health: int = 100"), proxy)
    assertTrue(proxy.contains("@export_custom(23, \"4/13:*.png\") var icons: Array[String]"))
    assertTrue(proxy.contains("@export_storage var saved: int = 3"))
    assertTrue(
      proxy.contains(
        "@export_custom(1, \"0.0,360.0,0.1,radians_as_degrees\") var fov: float = 1.0471975511965976"
      )
    )
  }
}
