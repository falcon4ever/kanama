package net.multigesture.kanama.gradle

import java.io.File

/**
 * The files that make Godot's Android export load Kanama: the two AARs under `android/plugins/`,
 * the `.gdap` that names them, and the Android lines of `addons/kanama/kanama.gdextension`.
 *
 * One copy for both writers: the Kanama Gradle plugin's `buildAndroidScripts` (a kit or
 * store-addon project) and the checkout's `installAndroidPluginAar` (the root buildSrc compiles
 * this directory too). Plain Kotlin, no Gradle API.
 */
object KanamaAndroidPluginFiles {
  const val PLUGINS_DIR = "android/plugins"
  const val RUNTIME_AAR = "KanamaAndroid.debug.aar"
  const val SCRIPTS_AAR = "KanamaAndroidScripts.debug.aar"
  const val GDAP = "KanamaAndroid.gdap"

  /**
   * The plugin config Godot reads. `custom_maven_repos`: the PanamaPort fork is published on
   * JitPack, which Godot's Android build template does not list; Godot passes this repository to
   * its Gradle build, so a plain editor export resolves the remote dependency.
   */
  fun gdap(panamaPortCore: String): String =
    """
    |[config]
    |
    |name="KanamaAndroid"
    |binary_type="local"
    |binary="$RUNTIME_AAR"
    |
    |[dependencies]
    |local=["$SCRIPTS_AAR"]
    |remote=["$panamaPortCore"]
    |custom_maven_repos=["https://jitpack.io"]
    |"""
      .trimMargin()

  private val androidLibraryLines =
    listOf(
      "android.debug.arm64 = \"libkanama_bootstrap.so\"",
      "android.release.arm64 = \"libkanama_bootstrap.so\"",
      "android.debug.x86_64 = \"libkanama_bootstrap.so\"",
      "android.release.x86_64 = \"libkanama_bootstrap.so\"",
    )

  /**
   * Adds `android_aar_plugin = true` to the descriptor's `[configuration]` and the Android
   * `[libraries]` entries (the bootstrap ships inside the runtime AAR). Keeps every other line and
   * adds nothing that is already there, so it can run on every build.
   */
  fun enableAndroidGdextensionMetadata(descriptor: File) {
    val lines = descriptor.readLines().toMutableList()
    if ("android_aar_plugin = true" !in lines) {
      val librariesIndex = lines.indexOf("[libraries]").takeIf { it >= 0 } ?: lines.size
      val insertIndex =
        if (librariesIndex > 0 && lines[librariesIndex - 1].isBlank()) {
          librariesIndex - 1
        } else {
          librariesIndex
        }
      lines.add(insertIndex, "android_aar_plugin = true")
    }

    val missingLibraryLines = androidLibraryLines.filter { it !in lines }
    if (missingLibraryLines.isNotEmpty()) {
      val librariesIndex =
        lines.indexOf("[libraries]").takeIf { it >= 0 }
          ?: run {
            if (lines.lastOrNull()?.isNotBlank() == true) {
              lines.add("")
            }
            lines.add("[libraries]")
            lines.lastIndex
          }
      val insertIndex =
        lines.withIndex().drop(librariesIndex + 1).firstOrNull { it.value.startsWith("[") }?.index
          ?: lines.size
      lines.addAll(insertIndex, missingLibraryLines)
    }
    descriptor.writeText(lines.joinToString(System.lineSeparator()) + System.lineSeparator())
  }
}
