package net.multigesture.kanama.gradle

import java.io.File

/**
 * Where a project's iOS libraries live and the iOS lines of `addons/kanama/kanama.gdextension`.
 *
 * One copy for both writers: the Kanama Gradle plugin's `buildIosScripts` (a kit or store-addon
 * project) and the checkout's `installIosAddon` (the root buildSrc compiles this directory too).
 * Plain Kotlin, no Gradle API.
 */
object KanamaIosAddonFiles {
  const val BIN_DIR = "addons/kanama/bin/ios"
  const val DEBUG_XCFRAMEWORK = "kanama_ios.debug.xcframework"
  const val RELEASE_XCFRAMEWORK = "kanama_ios.release.xcframework"

  private val iosLibraryLines =
    listOf(
      "ios.debug.arm64 = \"res://$BIN_DIR/$DEBUG_XCFRAMEWORK\"",
      "ios.release.arm64 = \"res://$BIN_DIR/$RELEASE_XCFRAMEWORK\"",
    )

  /**
   * Adds the two iOS `[libraries]` entries. Keeps every other line and adds nothing that is already
   * there, so it can run on every build.
   */
  fun enableIosGdextensionMetadata(descriptor: File) {
    val lines = descriptor.readLines().toMutableList()
    val missingLibraryLines = iosLibraryLines.filter { it !in lines }
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
