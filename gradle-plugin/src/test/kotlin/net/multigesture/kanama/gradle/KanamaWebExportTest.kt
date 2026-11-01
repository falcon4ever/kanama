package net.multigesture.kanama.gradle

import java.io.File
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.buildlogic.KanamaWebExport

/**
 * The Web export's merge and staging (`KanamaWebExport`), which exportWeb and the checkout share.
 */
class KanamaWebExportTest {
  private fun tempDir(): File = Files.createTempDirectory("kanama-web-export-test").toFile()

  private fun File.write(path: String, text: String): File =
    resolve(path).apply {
      parentFile.mkdirs()
      writeText(text)
    }

  @Test
  fun `the merge lays web overrides over kotlin-src and leaves out the excluded files`() {
    val root = tempDir()
    root.write("kotlin-src/a/Shared.kt", "shared")
    root.write("kotlin-src/a/Desktop.kt", "desktop")
    root.write("kotlin-src/Both.kt", "desktop copy")
    root.write("kotlin-src/notes.txt", "not a script")
    root.write("web/kotlin-src/Both.kt", "web copy")
    root.write("web/kotlin-src-excludes.txt", "# comment\na/Desktop.kt\n")
    val out = root.resolve("merged")
    KanamaWebExport.mergeScripts(
      root.resolve("kotlin-src"),
      root.resolve("web/kotlin-src"),
      root.resolve("web/kotlin-src-excludes.txt"),
      out,
    )
    val merged = out.resolve("kotlin-src")
    assertEquals(
      listOf("Both.kt", "a/Shared.kt"),
      merged
        .walkTopDown()
        .filter { it.isFile }
        .map { it.relativeTo(merged).invariantSeparatorsPath }
        .sorted()
        .toList(),
    )
    assertEquals("web copy", merged.resolve("Both.kt").readText())
  }

  @Test
  fun `a stale exclude fails, naming the entry`() {
    val root = tempDir()
    root.write("kotlin-src/A.kt", "a")
    root.write("web/kotlin-src-excludes.txt", "Gone.kt\n")
    val error =
      assertFailsWith<KanamaWebExport.KanamaWebExportException> {
        KanamaWebExport.mergeScripts(
          root.resolve("kotlin-src"),
          root.resolve("web/kotlin-src"),
          root.resolve("web/kotlin-src-excludes.txt"),
          root.resolve("merged"),
        )
      }
    assertTrue("'Gone.kt'" in error.message.orEmpty(), error.message)
  }

  private fun stageFixture(): Pair<File, KanamaWebExport.StageInputs> {
    val root = tempDir()
    val project = root.resolve("project")
    project.write(
      "project.godot",
      "config_version=5\n\n[application]\n\nrun/main_scene=\"res://main.tscn\"\n" +
        "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")\n",
    )
    project.write(
      "main.tscn",
      "[ext_resource type=\"Script\" uid=\"uid://abc\" path=\"res://kotlin-src/Hello.kt\" id=\"1\"]\n",
    )
    project.write("kotlin-src/Hello.kt", "class Hello")
    project.write("kotlin-src/notes.txt", "kotlin-src goes whole")
    project.write("art/sprite.png", "png")
    project.write("game.iml", "iml")
    project.write("export_presets.cfg", "the user's own")
    project.write("build.gradle.kts", "plugins {}")
    project.write("build/kanama/web/x.kt", "build output")
    project.write("addons/kanama/kanama.jar", "jar")
    project.write("addons/kanama_tools/plugin.cfg", "tools")
    project.write("web/kotlin-src/Hello.kt", "override")
    val proxies = root.resolve("proxies")
    proxies.write("Hello.gd", "extends Node")
    proxies.write(
      KanamaWebExport.PROXY_MANIFEST,
      "# kanama-web-protocol=39\nres://kotlin-src/Hello.kt\tres://kanama-web/generated/Hello.gd\tHello.gd\n",
    )
    proxies.write(
      KanamaWebExport.PROTOCOL_JSON,
      "{\n  \"protocolVersion\": 39,\n  \"scripts\": [\n    {\"resourcePath\": \"res://kotlin-src/Hello.kt\"}\n  ]\n}\n",
    )
    val merged = root.resolve("merged/kotlin-src")
    merged.write("Hello.kt", "class Hello")
    val assets = root.resolve("assets")
    val bridge = assets.write(KanamaWebExport.BRIDGE, "  const KANAMA_WEB_PROTOCOL_VERSION = 39;\n")
    val shell = assets.write(KanamaWebExport.SHELL, "<html></html>")
    val preset = assets.write(KanamaWebExport.PRESET, "custom_template/release=\"\"\n")
    return root to
      KanamaWebExport.StageInputs(
        projectDir = project,
        stagingDir = root.resolve("staging"),
        preset = preset,
        assets = listOf(bridge, shell),
        proxies = proxies,
        mergedSources = merged,
      )
  }

  @Test
  fun `the staged copy has the proxies attached, Kanama's preset and none of the build files`() {
    val (_, inputs) = stageFixture()
    val result = KanamaWebExport.stageProject(inputs)
    assertEquals(1, result.scriptCount)
    val staging = inputs.stagingDir
    val staged =
      staging
        .walkTopDown()
        .filter { it.isFile }
        .map { it.relativeTo(staging).invariantSeparatorsPath }
        .toSet()
    assertTrue("art/sprite.png" in staged)
    assertTrue("addons/kanama_tools/plugin.cfg" in staged)
    assertTrue("kanama-web/generated/Hello.gd" in staged)
    assertTrue("kanama-web/${KanamaWebExport.BRIDGE}" in staged)
    for (absent in
      listOf(
        "kotlin-src/Hello.kt",
        "kotlin-src/notes.txt",
        "web/kotlin-src/Hello.kt",
        "game.iml",
        "build.gradle.kts",
        "build/kanama/web/x.kt",
        "addons/kanama/kanama.jar",
      )) {
      assertFalse(absent in staged, "$absent was staged")
    }
    assertEquals("custom_template/release=\"\"\n", staging.resolve("export_presets.cfg").readText())
    assertEquals(
      "[ext_resource type=\"Script\" path=\"res://kanama-web/generated/Hello.gd\" id=\"1\"]\n",
      staging.resolve("main.tscn").readText(),
    )
    val projectGodot = staging.resolve("project.godot").readText()
    assertTrue("\"GL Compatibility\"" in projectGodot, projectGodot)
    assertTrue("renderer/rendering_method=\"gl_compatibility\"" in projectGodot, projectGodot)
    // The project itself is unchanged.
    assertTrue("res://kotlin-src/Hello.kt" in inputs.projectDir.resolve("main.tscn").readText())
  }

  @Test
  fun `a script with no proxy fails the staging`() {
    val (_, inputs) = stageFixture()
    inputs.proxies.resolve(KanamaWebExport.PROXY_MANIFEST).writeText("# kanama-web-protocol=39\n")
    val error =
      assertFailsWith<KanamaWebExport.KanamaWebExportException> {
        KanamaWebExport.stageProject(inputs)
      }
    assertTrue("no proxy for [res://kotlin-src/Hello.kt]" in error.message.orEmpty(), error.message)
  }

  @Test
  fun `another scripts folder is merged, left out and attached by its own res path`() {
    val (root, fixture) = stageFixture()
    val project = fixture.projectDir
    project.resolve("kotlin-src").deleteRecursively()
    project.write("game/scripts/Hello.kt", "class Hello")
    project.write(
      "main.tscn",
      "[ext_resource type=\"Script\" path=\"res://game/scripts/Hello.kt\" id=\"1\"]\n",
    )
    val scriptsPath = KanamaWebExport.scriptsPathIn(project, project.resolve("game/scripts"))
    assertEquals("game/scripts", scriptsPath)
    val out = root.resolve("merged-game")
    KanamaWebExport.mergeScripts(project.resolve("game/scripts"), null, null, out, scriptsPath)
    assertTrue(out.resolve("game/scripts/Hello.kt").isFile)
    for (file in listOf(KanamaWebExport.PROXY_MANIFEST, KanamaWebExport.PROTOCOL_JSON)) {
      val generated = fixture.proxies.resolve(file)
      generated.writeText(generated.readText().replace("res://kotlin-src/", "res://game/scripts/"))
    }
    val inputs =
      KanamaWebExport.StageInputs(
        projectDir = project,
        stagingDir = fixture.stagingDir,
        preset = fixture.preset,
        assets = fixture.assets,
        proxies = fixture.proxies,
        mergedSources = out.resolve(scriptsPath),
        scriptsPath = scriptsPath,
      )
    assertEquals(1, KanamaWebExport.stageProject(inputs).scriptCount)
    assertFalse(inputs.stagingDir.resolve("game/scripts/Hello.kt").exists())
    assertEquals(
      "[ext_resource type=\"Script\" path=\"res://kanama-web/generated/Hello.gd\" id=\"1\"]\n",
      inputs.stagingDir.resolve("main.tscn").readText(),
    )
  }

  /** A scripts folder that holds the main scene and an asset beside its Kotlin script. */
  private fun mixedFolderFixture(): KanamaWebExport.StageInputs {
    val (root, fixture) = stageFixture()
    val project = fixture.projectDir
    project.resolve("kotlin-src").deleteRecursively()
    project.resolve("main.tscn").delete()
    project.write("game/Hello.kt", "class Hello")
    project.write("game/Hello.kt.uid", "uid://hello")
    project.write("game/art/ship.png", "png")
    project.write(
      "game/main.tscn",
      "[ext_resource type=\"Script\" path=\"res://game/Hello.kt\" id=\"1\"]\n" +
        "[ext_resource type=\"Texture2D\" path=\"res://game/art/ship.png\" id=\"2\"]\n",
    )
    val projectGodot = project.resolve("project.godot")
    projectGodot.writeText(
      projectGodot.readText().replace("res://main.tscn", "res://game/main.tscn")
    )
    val out = root.resolve("merged-mixed")
    KanamaWebExport.mergeScripts(project.resolve("game"), null, null, out, "game")
    for (file in listOf(KanamaWebExport.PROXY_MANIFEST, KanamaWebExport.PROTOCOL_JSON)) {
      val generated = fixture.proxies.resolve(file)
      generated.writeText(generated.readText().replace("res://kotlin-src/", "res://game/"))
    }
    return KanamaWebExport.StageInputs(
      projectDir = project,
      stagingDir = fixture.stagingDir,
      preset = fixture.preset,
      assets = fixture.assets,
      proxies = fixture.proxies,
      mergedSources = out.resolve("game"),
      scriptsPath = "game",
    )
  }

  @Test
  fun `a scripts folder with scenes and assets keeps them and leaves out only its Kotlin`() {
    val inputs = mixedFolderFixture()
    assertEquals(1, KanamaWebExport.stageProject(inputs).scriptCount)
    val staging = inputs.stagingDir
    assertTrue(staging.resolve("game/art/ship.png").isFile)
    assertFalse(staging.resolve("game/Hello.kt").exists())
    assertFalse(staging.resolve("game/Hello.kt.uid").exists())
    assertEquals(
      "[ext_resource type=\"Script\" path=\"res://kanama-web/generated/Hello.gd\" id=\"1\"]\n" +
        "[ext_resource type=\"Texture2D\" path=\"res://game/art/ship.png\" id=\"2\"]\n",
      staging.resolve("game/main.tscn").readText(),
    )
    assertTrue("res://game/main.tscn" in staging.resolve("project.godot").readText())
  }

  @Test
  fun `a Kotlin script of the scripts folder that has no proxy still fails the staging`() {
    val inputs = mixedFolderFixture()
    inputs.projectDir.write(
      "game/other.tscn",
      "[ext_resource type=\"Script\" path=\"res://game/Desktop.kt\" id=\"1\"]\n",
    )
    val error =
      assertFailsWith<KanamaWebExport.KanamaWebExportException> {
        KanamaWebExport.stageProject(inputs)
      }
    assertTrue("Unmapped Kotlin attachment" in error.message.orEmpty(), error.message)
  }

  @Test
  fun `a scripts folder outside the project, or the project itself, fails`() {
    val root = tempDir()
    val project = root.resolve("project").apply { mkdirs() }
    for (folder in listOf(root.resolve("elsewhere"), project)) {
      val error =
        assertFailsWith<KanamaWebExport.KanamaWebExportException> {
          KanamaWebExport.scriptsPathIn(project, folder)
        }
      assertTrue("kanamaProjectScriptsDir" in error.message.orEmpty(), error.message)
    }
  }

  @Test
  fun `the protocol of the bridge and of the generated descriptor are read`() {
    val (_, inputs) = stageFixture()
    assertEquals(39, KanamaWebExport.bridgeProtocolVersion(inputs.assets.first()))
    assertEquals(
      39,
      KanamaWebExport.protocolVersion(inputs.proxies.resolve(KanamaWebExport.PROTOCOL_JSON)),
    )
  }
}
