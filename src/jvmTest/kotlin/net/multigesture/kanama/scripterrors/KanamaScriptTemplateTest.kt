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
  @Test
  fun theExampleFixtureIsTheTemplateOutput() {
    val fixture = File("example_project/NewScriptTemplateProbe.kt").readText()
    val body = fixture.substring(fixture.indexOf("package "))

    assertEquals(
      KanamaScriptTemplate.source(
        className = "NewScriptTemplateProbe",
        baseClass = "Node3D",
        packageName = "net.multigesture.kanama.example",
      ),
      body,
    )
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
    // The scripts already in the directory decide (Kanama projects keep one package per folder).
    assertEquals("tps", KanamaScriptTemplate.packageFor("res://kotlin-src/Player.kt", "tps"))
    // Else the directory below kotlin-src/ (or res://).
    assertEquals(
      "com.example.enemies",
      KanamaScriptTemplate.packageFor("res://kotlin-src/com/example/enemies/Bat.kt", null),
    )
    assertEquals("scripts.ai", KanamaScriptTemplate.packageFor("res://scripts/ai/Brain.kt", null))
    assertEquals("_3d.`object`", KanamaScriptTemplate.packageFor("res://3d/object/Crate.kt", null))
    // Else a default.
    assertEquals("game", KanamaScriptTemplate.packageFor("res://Player.kt", null))
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
