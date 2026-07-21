package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WebScriptCodeEmitterTest {

  private fun model(simpleName: String, greeting: String = "greeting") =
    ScriptModel(
      simpleName = simpleName,
      fqName = "net.multigesture.kanama.web.$simpleName",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = greeting,
            godotName = greeting,
            type = TypeMapping.STRING,
            isMutable = true,
          )
        ),
      toolButtons = emptyList(),
      virtuals =
        listOf(
          VirtualModel("_ready", "callReady", "ready"),
          VirtualModel(
            "_process",
            "callProcess",
            "process",
            args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
          ),
        ),
      methods =
        listOf(
          MethodModel(
            kotlinName = "echo",
            godotName = "echo",
            returnType = TypeMapping.INT,
            args = listOf(ArgModel("value", TypeMapping.INT)),
            kind = MethodKind.REGULAR,
          )
        ),
      signals = listOf(SignalModel("changed", listOf(ArgModel("value", TypeMapping.INT)))),
    )

  @Test
  fun emitsDeterministicNumericRegistryAndTypedDispatch() {
    val source =
      WebScriptCodeEmitter(
          listOf(
            WebScriptInput(model("SecondScript"), "res://z/SecondScript.kt"),
            WebScriptInput(model("FirstScript"), "res://a/FirstScript.kt"),
          )
        )
        .registrySource()

    val firstDescriptor = source.indexOf("className = \"net.multigesture.kanama.web.FirstScript\"")
    val secondDescriptor =
      source.indexOf("className = \"net.multigesture.kanama.web.SecondScript\"")
    assertTrue(firstDescriptor >= 0)
    assertTrue(secondDescriptor > firstDescriptor, "resource paths must define stable script IDs")

    assertTrue(source.contains("const val PROTOCOL_VERSION: Int = 1"))
    assertTrue(source.contains("1 -> FirstScript(WebObjectId(objectId))"))
    assertTrue(source.contains("2 -> SecondScript(WebObjectId(objectId))"))
    assertTrue(source.contains("WebMemberDescriptor(1, \"greeting\")"))
    assertTrue(source.contains("(script as FirstScript).process(delta)"))
    assertTrue(source.contains("(script as FirstScript).echo(value)"))

    assertFalse(source.contains("java.lang.foreign"))
    assertFalse(source.contains("MemorySegment"))
    assertFalse(source.contains("Class.forName"))
    assertFalse(source.contains("Map<String, Any"))
  }

  @Test
  fun outputIsIndependentOfInputOrder() {
    val first = WebScriptInput(model("FirstScript"), "res://a/FirstScript.kt")
    val second = WebScriptInput(model("SecondScript"), "res://z/SecondScript.kt")

    assertEquals(
      WebScriptCodeEmitter(listOf(first, second)).registrySource(),
      WebScriptCodeEmitter(listOf(second, first)).registrySource(),
    )
  }

  @Test
  fun emitsProxyWithLifecycleBatchAndImmediatePaths() {
    val emitter =
      WebScriptCodeEmitter(listOf(WebScriptInput(model("FirstScript"), "res://FirstScript.kt")))
    val proxy = emitter.proxySources().single()

    assertEquals("res://FirstScript.kt", proxy.sourceResourcePath)
    assertEquals("res://kanama-web/generated/FirstScript.gd", proxy.proxyResourcePath)
    assertTrue(proxy.source.contains("extends Node"))
    assertTrue(proxy.source.contains("@export var greeting: String"))
    assertTrue(proxy.source.contains("JavaScriptBridge.get_interface(\"KanamaWebBridge\")"))
    assertTrue(proxy.source.contains("func _process(delta: float)"))
    assertTrue(proxy.source.contains("func _exit_tree()"))
    assertTrue(proxy.source.contains("func _kanama_apply_commands(args: Array)"))
    assertTrue(proxy.source.contains("js_buffer_to_packed_byte_array(args[0])"))
    assertTrue(proxy.source.contains("bytes.decode_s32(offset + 8)"))
    assertTrue(proxy.source.contains("callInt(_kanama_handle, 1, 47)"))
    assertTrue(proxy.source.contains("func echo(value: int) -> int"))

    assertTrue(
      emitter
        .proxyManifest()
        .contains("res://FirstScript.kt\tres://kanama-web/generated/FirstScript.gd\tFirstScript.gd")
    )
  }
}
