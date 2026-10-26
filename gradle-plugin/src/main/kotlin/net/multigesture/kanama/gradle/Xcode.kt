package net.multigesture.kanama.gradle

import java.io.File

/**
 * Finds the Xcode an iOS build uses, as a Kanama checkout and Apple's own tools do:
 * `-PkanamaXcodeDeveloperDir`, then `DEVELOPER_DIR`, then the Xcode `xcode-select -p` names, then
 * `/Applications/Xcode.app`. It must be the full Xcode app: the Command Line Tools have no iOS SDK
 * and no `xcodebuild`.
 */
internal object Xcode {
  data class Found(val developerDir: File, val source: String)

  const val DEFAULT_DEVELOPER_DIR = "/Applications/Xcode.app/Contents/Developer"

  const val NOT_FOUND =
    "Xcode not found. iOS export needs the full Xcode app, not only the Command Line Tools: " +
      "install Xcode, then run sudo xcode-select -s /Applications/Xcode.app (or set " +
      "DEVELOPER_DIR=/path/to/Xcode.app/Contents/Developer)."

  /**
   * A Developer directory of a full Xcode: it has `xcodebuild` and is not the Command Line Tools.
   */
  fun isXcode(dir: File): Boolean =
    dir.resolve("usr/bin/xcodebuild").isFile && !dir.path.contains("CommandLineTools")

  /**
   * [property] and the `DEVELOPER_DIR` of [env] are the user's own choice: one that is not an Xcode
   * fails with its name rather than falling through to another Xcode. [xcodeSelect] is the output
   * of `xcode-select -p`, or null.
   */
  fun find(
    property: String?,
    env: Map<String, String> = System.getenv(),
    xcodeSelect: () -> String? = ::xcodeSelectPath,
    defaultDir: File = File(DEFAULT_DEVELOPER_DIR),
  ): Found {
    for ((value, source) in
      listOf(property to "-PkanamaXcodeDeveloperDir", env["DEVELOPER_DIR"] to "DEVELOPER_DIR")) {
      val path = value?.trim().orEmpty()
      if (path.isEmpty()) continue
      val dir = File(path)
      if (!isXcode(dir)) {
        throw IllegalArgumentException(
          "$source=$path is not the Developer folder of a full Xcode (no usr/bin/xcodebuild there). " +
            "Point it at /path/to/Xcode.app/Contents/Developer."
        )
      }
      return Found(dir, source)
    }
    xcodeSelect()
      ?.trim()
      ?.takeIf { it.isNotEmpty() }
      ?.let(::File)
      ?.takeIf(::isXcode)
      ?.let {
        return Found(it, "xcode-select -p")
      }
    if (isXcode(defaultDir)) return Found(defaultDir, "the default Xcode location")
    throw IllegalArgumentException(NOT_FOUND)
  }

  private fun xcodeSelectPath(): String? =
    runCatching {
        // Its output (one path) fits the pipe buffer, so it is read after the bounded wait: a
        // hung xcode-select costs 10 s, never the build.
        val process = ProcessBuilder("xcode-select", "-p").redirectErrorStream(true).start()
        if (!process.waitFor(10, java.util.concurrent.TimeUnit.SECONDS)) {
          process.destroyForcibly()
          null
        } else if (process.exitValue() == 0) {
          process.inputStream.bufferedReader().readText()
        } else {
          null
        }
      }
      .getOrNull()
}
