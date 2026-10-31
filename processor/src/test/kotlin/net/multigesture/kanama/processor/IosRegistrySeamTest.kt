package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * The seam between the iOS runtime and a project's generated script registry (task 135 item 5). The
 * registry is a `@CName("kanama_ios_project_scripts_register")` function the C shim calls, never an
 * `actual` of a runtime `expect` (which cannot cross a module: a kit project compiles its scripts
 * in a module of its own against the prebuilt runtime klib). It opts in to the runtime declarations
 * it names, and a build without scripts defines no such function (the shim's weak default stands
 * in), so the runtime klib a kit carries never collides with the project's own strong definition.
 */
class IosRegistrySeamTest {
  private fun script(name: String) =
    IosScriptInput(
      ScriptModel(
        simpleName = name,
        fqName = "net.multigesture.kanama.test.$name",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = emptyList(),
        signals = emptyList(),
      ),
      "res://kotlin-src/$name.kt",
    )

  @Test
  fun theRegistryIsACNameFunctionThatOptsInToTheSeam() {
    val source = IosScriptCodeEmitter(listOf(script("Hello"))).registrySource()
    assertTrue(
      source.startsWith(
        "@file:OptIn(net.multigesture.kanama.ios.InternalKanamaIosApi::class, " +
          "kotlin.experimental.ExperimentalNativeApi::class)\n"
      ),
      source.lines().first(),
    )
    assertTrue(
      "@kotlin.native.CName(\"kanama_ios_project_scripts_register\")\n" +
        "fun kanamaIosProjectScriptsRegister() {" in source
    )
    assertTrue("KanamaIosProjectRegistry.register(" in source)
    assertFalse("actual fun" in source)
    assertFalse("internal actual" in source)
    assertEquals(REGISTER_SYMBOL, "kanama_ios_project_scripts_register")
  }

  @Test
  fun noScriptsDefinesNoRegisterFunction() {
    val source = IosScriptCodeEmitter(emptyList()).registrySource()
    assertFalse("CName" in source, source)
    assertFalse("fun kanamaIosProjectScriptsRegister" in source, source)
  }
}
