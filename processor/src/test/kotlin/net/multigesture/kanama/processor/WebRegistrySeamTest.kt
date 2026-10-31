package net.multigesture.kanama.processor

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * The seam between the Web runtime and a project's generated registry (task 135 item 5). A kit
 * project compiles its scripts, the registry and `main()` in a module of its own against the
 * prebuilt runtime klib (`kanama-web`), so the generated code may name only public runtime
 * declarations: the registry implements the runtime's `KanamaWebProjectScripts` (every member, and
 * nothing more), `main()` hands it to `startKanamaWeb`, and the runtime helpers are reached through
 * `KanamaWebGenerated`, never the internal objects behind it.
 */
class WebRegistrySeamTest {
  private fun script(name: String) =
    WebScriptInput(
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

  private val runtimeInterface =
    File(
        "../web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/KanamaWebProjectScripts.kt"
      )
      .readText()

  /** The member names of the runtime's `interface KanamaWebProjectScripts`. */
  private fun interfaceMembers(): Set<String> {
    val body =
      runtimeInterface.substringAfter("interface KanamaWebProjectScripts {").let { rest ->
        rest.substring(0, rest.indexOf("\n}\n"))
      }
    return Regex("(?m)^  (?:fun|val) (\\w+)").findAll(body).map { it.groupValues[1] }.toSet()
  }

  @Test
  fun theRegistryImplementsTheRuntimeInterfaceAndMainStartsTheRuntime() {
    val source = WebScriptCodeEmitter(listOf(script("Hello"), script("Other"))).registrySource()
    assertTrue(
      source.contains(
        "@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)\n"
      )
    )
    assertTrue(
      "object KanamaWebProjectRegistry : net.multigesture.kanama.web.KanamaWebProjectScripts {" in
        source
    )
    assertTrue(
      "fun main() {\n  net.multigesture.kanama.web.startKanamaWeb(KanamaWebProjectRegistry)\n}" in
        source,
      source.takeLast(400),
    )
    val objectBody =
      source.substringAfter("object KanamaWebProjectRegistry").substringBefore("\n}\n")
    val overridden =
      Regex("(?m)^  override (?:fun|val) (\\w+)")
        .findAll(objectBody)
        .map { it.groupValues[1] }
        .toSet()
    val unmarked =
      Regex("(?m)^  (?:fun|val) (\\w+)").findAll(objectBody).map { it.groupValues[1] }.toList()
    assertEquals(emptyList(), unmarked, "registry members that are not the runtime interface's")
    val expected = interfaceMembers()
    assertEquals(34, expected.size, "the interface's members: $expected")
    assertEquals(expected, overridden)
  }

  @Test
  fun aProjectWithoutScriptsStillHasARegistryAndMain() {
    val source = WebScriptCodeEmitter(emptyList()).registrySource()
    assertTrue("net.multigesture.kanama.web.startKanamaWeb(KanamaWebProjectRegistry)" in source)
    assertTrue("override val scripts: List<WebScriptDescriptor>" in source)
  }

  /**
   * The emitters write no runtime-internal name into generated code: the value and float text forms
   * and a script by handle go through `KanamaWebGenerated`, and a Godot wrapper is built with its
   * public `(WebObjectId)` constructor, not the internal backend-handle one. (The demo corpus
   * compiled as separate modules against the klib is the full proof; this keeps it from
   * regressing.)
   */
  @Test
  fun theEmittersNameNoRuntimeInternal() {
    val emitters =
      listOf("WebScriptCodeEmitter.kt", "WebValueTypes.kt").map { name ->
        name to
          File("src/main/kotlin/net/multigesture/kanama/processor/$name")
            .readLines()
            .filterNot { it.trimStart().startsWith("//") || it.trimStart().startsWith("*") }
            .joinToString("\n")
      }
    for ((name, text) in emitters) {
      for (internal in
        listOf(
          "net.multigesture.kanama.web.WebPackedFloats",
          "net.multigesture.kanama.web.WebPackedValues",
          "net.multigesture.kanama.web.webScriptInstance",
          "GodotHandle.fromBackendToken(",
        )) {
        assertFalse(internal in text, "$name emits the runtime-internal $internal")
      }
    }
  }
}
