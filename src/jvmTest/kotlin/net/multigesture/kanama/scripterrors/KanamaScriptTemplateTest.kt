package net.multigesture.kanama.scripterrors

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.KanamaScriptTemplate

/**
 * Task 131 item 5 (F15): the editor's New Script template has the documented shape (a package, a
 * `KanamaScript<Base>` subclass, an `@OnReady` stub, `@GlobalClass` only when asked).
 *
 * The template output is compiled through the KSP processor by the example build:
 * `example_project/NewScriptTemplateProbe.kt` is [KanamaScriptTemplate.source]'s exact output for
 * that script (below its leading comment), and [theExampleFixtureIsTheTemplateOutput] keeps the two
 * identical.
 */
class KanamaScriptTemplateTest {
  /** `example_project/<file>` below its leading comment. */
  private fun fixture(file: String): String =
    File("example_project/$file").readText().let { it.substring(it.indexOf("package ")) }

  @Test
  fun theExampleFixturesAreTheTemplateOutput() {
    // One per construction path: `::Wrapper` with the @OnReady stub (Node3D), `::Wrapper` without
    // it (Resource), GodotObject for Godot's Object, and the fromHandle factory (RefCounted).
    val cases =
      listOf(
        Triple("NewScriptTemplateProbe", "Node3D", true),
        Triple("NewScriptTemplateResourceProbe", "Resource", false),
        Triple("NewScriptTemplateObjectProbe", "Object", false),
        Triple("NewScriptTemplateRefCountedProbe", "RefCounted", false),
      )
    for ((name, base, nodeDerived) in cases) {
      assertEquals(
        KanamaScriptTemplate.source(
          className = name,
          baseClass = base,
          packageName = "net.multigesture.kanama.example",
          nodeDerived = nodeDerived,
        ),
        fixture("$name.kt"),
        name,
      )
    }
  }

  @Test
  fun everyBaseGetsItsConstructionPathAndTheStubOnlyForNodes() {
    val node = KanamaScriptTemplate.source("A", "Node", "game", nodeDerived = true)
    assertTrue(node.contains("KanamaScript<Node>(godotObject, ::Node) {\n  @OnReady\n"))
    val node3d = KanamaScriptTemplate.source("A", "Node3D", "game", nodeDerived = true)
    assertTrue(node3d.contains("KanamaScript<Node3D>(godotObject, ::Node3D) {\n  @OnReady\n"))
    val resource = KanamaScriptTemplate.source("A", "Resource", "game", nodeDerived = false)
    assertTrue(resource.contains("KanamaScript<Resource>(godotObject, ::Resource)\n"))
    val refCounted = KanamaScriptTemplate.source("A", "RefCounted", "game", nodeDerived = false)
    assertTrue(
      refCounted.contains(
        "KanamaScript<RefCounted>(godotObject, { RefCounted.fromHandle(it)!! })\n"
      )
    )
    val obj = KanamaScriptTemplate.source("A", "Object", "game", nodeDerived = false)
    assertTrue(obj.contains("@ScriptClass(attachTo = \"Object\")\n"))
    assertTrue(obj.contains("KanamaScript<GodotObject>(godotObject, ::GodotObject)\n"))
    for (source in listOf(resource, refCounted, obj)) {
      assertFalse(source.contains("OnReady"), source)
      assertFalse(source.contains("{\n"), source)
    }
  }

  @Test
  fun theTemplateHasTheDocumentedShape() {
    val source = KanamaScriptTemplate.source("Player", "Area3D")

    assertTrue(source.startsWith("package ${KanamaScriptTemplate.PACKAGE_PLACEHOLDER}\n"))
    assertTrue(source.contains("@ScriptClass(attachTo = \"Area3D\")\n"))
    assertTrue(
      source.contains(
        "class Player(godotObject: GodotHandle) : KanamaScript<Area3D>(godotObject, ::Area3D) {\n"
      )
    )
    assertTrue(source.contains("import net.multigesture.kanama.api.Area3D\n"))
    assertTrue(source.contains("  @OnReady\n  fun ready() {\n"))
    assertFalse(source.contains("GlobalClass"))
    // A header past ktfmt's 100 columns is wrapped the way ktfmt wraps it.
    assertTrue(
      KanamaScriptTemplate.source("PlayerController", "CharacterBody3D")
        .contains(
          "class PlayerController(godotObject: GodotHandle) :\n" +
            "  KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {\n"
        )
    )
  }

  @Test
  fun globalClassOnlyWhenTheTemplateAsks() {
    assertFalse(KanamaScriptTemplate.wantsGlobalClass(""))
    assertTrue(KanamaScriptTemplate.wantsGlobalClass("@GlobalClass\n@ScriptClass"))
    val source = KanamaScriptTemplate.source("Item", "Resource", "game", globalClass = true)
    assertTrue(source.contains("import net.multigesture.kanama.annotations.GlobalClass\n"))
    assertTrue(source.contains("@ScriptClass(attachTo = \"Resource\")\n@GlobalClass\nclass Item("))
  }

  @Test
  fun objectAndCustomBasesMapToKanamaWrappers() {
    assertTrue(
      KanamaScriptTemplate.source("Thing", "Object")
        .contains("KanamaScript<GodotObject>(godotObject, ::GodotObject)")
    )
    // A custom base arrives as a quoted script path: fall back to Node.
    assertTrue(
      KanamaScriptTemplate.source("Thing", "\"res://base.gd\"")
        .contains("@ScriptClass(attachTo = \"Node\")")
    )
  }

  @Test
  fun thePackageComesFromTheDirectory() {
    // The package most scripts in the directory declare (Kanama projects keep one per folder).
    assertEquals(
      "tps",
      KanamaScriptTemplate.packageFor("res://kotlin-src/Player.kt", listOf("tps")),
    )
    assertEquals(
      "b.game",
      KanamaScriptTemplate.packageFor("res://kotlin-src/P.kt", listOf("a.x", "b.game", "b.game")),
    )
    // A tie goes to the alphabetically first.
    assertEquals(
      "a.game",
      KanamaScriptTemplate.packageFor("res://kotlin-src/P.kt", listOf("b.game", "a.game")),
    )
    // Else the directory below kotlin-src/ (or res://).
    assertEquals(
      "com.example.enemies",
      KanamaScriptTemplate.packageFor("res://kotlin-src/com/example/enemies/Bat.kt", emptyList()),
    )
    assertEquals(
      "scripts.ai",
      KanamaScriptTemplate.packageFor("res://scripts/ai/Brain.kt", emptyList()),
    )
    assertEquals(
      "_3d.`object`",
      KanamaScriptTemplate.packageFor("res://3d/object/Crate.kt", emptyList()),
    )
    // Else a default.
    assertEquals("game", KanamaScriptTemplate.packageFor("res://Player.kt", emptyList()))
  }

  @Test
  fun reservedKotlinAndJavaPackagesAreNeverChosen() {
    assertEquals(
      "game",
      KanamaScriptTemplate.packageFor("res://kotlin-src/P.kt", listOf("kotlin.collections")),
    )
    assertEquals(
      "tps",
      KanamaScriptTemplate.packageFor("res://kotlin-src/P.kt", listOf("java.util", "tps")),
    )
    assertEquals("game", KanamaScriptTemplate.packageFor("res://kotlin/Foo.kt", emptyList()))
    assertEquals("game", KanamaScriptTemplate.packageFor("res://a/java/Foo.kt", emptyList()))
  }

  @Test
  fun saveFillsThePlaceholderOnce() {
    val template = KanamaScriptTemplate.source("Player", "Node")
    val filled = KanamaScriptTemplate.withPackage(template, "com.example")

    assertTrue(filled.startsWith("package com.example\n\nimport "))
    assertFalse(KanamaScriptTemplate.hasPackagePlaceholder(filled))
    assertEquals(filled, KanamaScriptTemplate.withPackage(filled, "other"))
    assertEquals("com.example", KanamaScriptTemplate.declaredPackage(filled))
  }
}
