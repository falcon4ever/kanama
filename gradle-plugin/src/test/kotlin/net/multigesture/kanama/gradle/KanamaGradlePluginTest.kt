package net.multigesture.kanama.gradle

import java.io.File
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.gradle.api.GradleException

class KanamaGradlePluginTest {
  private fun tempDir(): File = Files.createTempDirectory("kanama-gradle-plugin-test").toFile()

  @Test
  fun `the ETC2 ASTC import is added to an existing rendering section, once`() {
    val text =
      "config_version=5\n\n[application]\n\nconfig/name=\"A\"\n\n[rendering]\n\nrenderer/x=1\n"
    val once = ProjectGodot.setSetting(text, ProjectGodot.ETC2_ASTC_SETTING, "true")
    assertEquals(
      "config_version=5\n\n[application]\n\nconfig/name=\"A\"\n\n[rendering]\n\nrenderer/x=1\n" +
        "textures/vram_compression/import_etc2_astc=true\n",
      once,
    )
    assertEquals(once, ProjectGodot.setSetting(once, ProjectGodot.ETC2_ASTC_SETTING, "true"))
  }

  @Test
  fun `the ETC2 ASTC import replaces false and appends a missing section`() {
    val off = "[rendering]\n\ntextures/vram_compression/import_etc2_astc=false\n\n[other]\n\na=1\n"
    assertEquals(
      "[rendering]\n\ntextures/vram_compression/import_etc2_astc=true\n\n[other]\n\na=1\n",
      ProjectGodot.setSetting(off, ProjectGodot.ETC2_ASTC_SETTING, "true"),
    )
    assertEquals(
      "config_version=5\n\n[rendering]\n\ntextures/vram_compression/import_etc2_astc=true\n",
      ProjectGodot.setSetting("config_version=5\n", ProjectGodot.ETC2_ASTC_SETTING, "true"),
    )
  }

  @Test
  fun `the Android SDK path is read from Godot's editor settings`() {
    val settings =
      "[gd_resource type=\"EditorSettings\" format=3]\n\n[resource]\n" +
        "export/android/android_sdk_path = \"C:\\\\Android\\\\Sdk\"\n"
    assertEquals("C:\\Android\\Sdk", AndroidSdk.editorSettingsSdkPath(settings))
    assertNull(AndroidSdk.editorSettingsSdkPath("export/android/android_sdk_path = \"\"\n"))
  }

  @Test
  fun `ANDROID_HOME wins, then Godot's editor setting`() {
    val home = tempDir()
    val fromEnv = home.resolve("env-sdk").apply { mkdirs() }
    val fromGodot = home.resolve("godot-sdk").apply { mkdirs() }
    val config = home.resolve(".config/godot").apply { mkdirs() }
    config
      .resolve("editor_settings-4.7.tres")
      .writeText("export/android/android_sdk_path = \"${fromGodot.path}\"\n")
    val env = mapOf("ANDROID_HOME" to fromEnv.path)
    assertEquals(fromEnv, AndroidSdk.find(env, "Linux", home, "4.7.2.stable")?.dir)
    assertEquals(fromGodot, AndroidSdk.find(emptyMap(), "Linux", home, "4.7.2.stable")?.dir)
    assertNull(AndroidSdk.find(emptyMap(), "Linux", home, "4.8.stable"))
  }

  @Test
  fun `the descriptor gets the Android lines once`() {
    val descriptor = tempDir().resolve("kanama.gdextension")
    descriptor.writeText(
      "[configuration]\n\nentry_symbol = \"kanama_entry\"\n\n[libraries]\n\nmacos.debug = \"x\"\n"
    )
    KanamaAndroidPluginFiles.enableAndroidGdextensionMetadata(descriptor)
    val once = descriptor.readText()
    KanamaAndroidPluginFiles.enableAndroidGdextensionMetadata(descriptor)
    assertEquals(once, descriptor.readText())
    assertTrue("android_aar_plugin = true" in once)
    assertTrue("android.release.arm64 = \"libkanama_bootstrap.so\"" in once)
  }

  @Test
  fun `the plugin refuses an addon of another Kanama version`() {
    val project = tempDir()
    val missing = assertFailsWith<GradleException> { KanamaPlugin.checkAddonVersion(project) }
    assertTrue("kanama.version is missing" in missing.message.orEmpty())
    project.resolve("addons/kanama").mkdirs()
    project.resolve(KanamaPlugin.VERSION_FILE).writeText("${KanamaVersions.KANAMA}\n")
    KanamaPlugin.checkAddonVersion(project)
    project.resolve(KanamaPlugin.VERSION_FILE).writeText("9.9.9\n")
    val other = assertFailsWith<GradleException> { KanamaPlugin.checkAddonVersion(project) }
    assertTrue("but the addon is 9.9.9" in other.message.orEmpty())
  }

  @Test
  fun `the gdap names both AARs, PanamaPort and JitPack`() {
    val gdap = KanamaAndroidPluginFiles.gdap("com.example:Core:1")
    assertTrue("binary=\"KanamaAndroid.debug.aar\"" in gdap)
    assertTrue("local=[\"KanamaAndroidScripts.debug.aar\"]" in gdap)
    assertTrue("remote=[\"com.example:Core:1\"]" in gdap)
    assertTrue("custom_maven_repos=[\"https://jitpack.io\"]" in gdap)
  }
}
