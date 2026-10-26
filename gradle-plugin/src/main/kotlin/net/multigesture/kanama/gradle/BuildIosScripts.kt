package net.multigesture.kanama.gradle

import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.management.ManagementFactory
import java.util.zip.ZipFile
import javax.inject.Inject
import net.multigesture.kanama.buildlogic.IosSourceLines
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations

/**
 * `./gradlew buildIosScripts`: what `installIosAddon` does in a Kanama checkout, from the addon
 * alone.
 * 1. The inner build (`build/kanama/ios`, rewritten on every run, run with the project's own
 *    `gradlew`) compiles `kotlin-src` for the iPhone with the script registry KSP generates from
 *    it, against the prebuilt runtime klib `kanama-iosarm64` from `addons/kanama/maven`, and links
 *    a debug and a release static library with the runtime exported into each.
 * 2. The C shim from `kanama-ios-shim` (same repository) is compiled for each, the debug library's
 *    source-line table is generated (the checkout's `IosSourceLines`), and each library is combined
 *    with them and made into a device xcframework.
 * 3. The xcframeworks go to `addons/kanama/bin/ios/`, and the iOS lines into
 *    `addons/kanama/kanama.gdextension`. Godot's iOS export then picks them up as it does in a
 *    checkout.
 *
 * It always runs: the inner Gradle build decides what is up to date.
 */
abstract class BuildIosScripts : DefaultTask() {
  @get:Internal abstract val projectDirectory: DirectoryProperty
  @get:Internal abstract val scriptsDirectory: DirectoryProperty
  @get:Internal abstract val innerBuildDirectory: DirectoryProperty
  /** `-PkanamaXcodeDeveloperDir`, when set. */
  @get:Internal abstract val xcodeDeveloperDir: Property<String>
  /** `-PkanamaIosAllowSkips=true`: a member iOS cannot carry is left out with a warning. */
  @get:Internal abstract val allowSkips: Property<Boolean>

  @get:Inject abstract val execOperations: ExecOperations

  init {
    doNotTrackState(
      "installs into addons/kanama/bin/ios; the inner Gradle build tracks its own state"
    )
  }

  @TaskAction
  fun build() {
    if (!System.getProperty("os.name").startsWith("Mac")) {
      throw GradleException("iOS export needs a Mac with Xcode.")
    }
    val project = projectDirectory.get().asFile
    val version = KanamaVersions.KANAMA
    val maven = project.resolve("addons/kanama/maven/net/multigesture/kanama")
    val klib = maven.resolve("kanama-iosarm64/$version/kanama-iosarm64-$version.klib")
    val shimZip = maven.resolve("kanama-ios-shim/$version/kanama-ios-shim-$version.zip")
    if (!klib.isFile || !shimZip.isFile) {
      throw GradleException(
        "addons/kanama/maven has no Kanama iOS runtime (kanama-iosarm64 $version). The macOS kit " +
          "and the store addon carry it: unzip the Kanama $version macOS kit or store addon into " +
          "this project again. In a project built from a Kanama checkout, run installIosAddon in " +
          "the checkout instead."
      )
    }
    val xcode =
      try {
        Xcode.find(xcodeDeveloperDir.orNull)
      } catch (e: IllegalArgumentException) {
        throw GradleException(e.message ?: Xcode.NOT_FOUND)
      }
    val developerDir = xcode.developerDir.absolutePath
    logger.lifecycle("[kanama] Xcode: $developerDir (from ${xcode.source})")

    val scripts = scriptsDirectory.get().asFile
    if (!scripts.isDirectory) {
      throw GradleException(
        "No Kotlin scripts folder at $scripts: iOS compiles the project's kotlin-src."
      )
    }
    val inner = innerBuildDirectory.get().asFile
    writeInnerBuild(inner, project, scripts)
    stateFirstBuildCosts()

    val gradlew = project.resolve("gradlew")
    if (!gradlew.isFile) throw GradleException("No Gradle wrapper at $gradlew.")
    val started = System.nanoTime()
    val result =
      execOperations.exec { spec ->
        spec.commandLine(
          gradlew.absolutePath,
          "-p",
          inner.absolutePath,
          "linkDebugStaticIosArm64",
          "linkReleaseStaticIosArm64",
        )
        spec.environment("DEVELOPER_DIR", developerDir)
        // The JDK this Gradle runs on, which runs the inner Gradle too.
        spec.environment("JAVA_HOME", System.getProperty("java.home"))
        spec.isIgnoreExitValue = true
      }
    if (result.exitValue != 0) {
      throw GradleException(
        "The iOS scripts build failed (exit ${result.exitValue}); its output is above. " +
          "It runs in ${inner.relativeTo(project).invariantSeparatorsPath}."
      )
    }
    logger.lifecycle(
      "[kanama] iOS scripts compiled and linked in ${(System.nanoTime() - started) / 1_000_000_000} s."
    )

    val shim = inner.resolve("shim")
    unzip(shimZip, shim)
    val work = inner.resolve("build/kanama-ios")
    work.deleteRecursively()
    val registersScripts = generatedRegistryHasEntry(inner)
    val frameworks =
      BUILD_TYPES.associateWith { type ->
        val runtimeLib = inner.resolve("build/bin/iosArm64/${type}Static/libkanama_ios_runtime.a")
        if (!runtimeLib.isFile) throw GradleException("The iOS scripts build wrote no $runtimeLib.")
        val dir = work.resolve(type)
        dir.mkdirs()
        val shimObject = dir.resolve("kanama_ios_shim.o")
        compileC(
          developerDir,
          shim,
          shim.resolve("bootstrap/kanama_ios_shim.c"),
          shimObject,
          listOf("-DKANAMA_IOS_SELFTEST=0"),
        )
        checkRuntimeEntries(developerDir, shimObject, runtimeLib, registersScripts)
        val table =
          if (type == "debug") {
            val tableDir = dir.resolve("source-lines")
            val roots =
              listOf(scripts).filter { root -> root.walkTopDown().any { it.extension == "kt" } }
            val (functions, rows) =
              IosSourceLines.write(
                runtimeLib,
                roots,
                tableDir.resolve("kanama_ios_source_lines_table.inc"),
                dir.resolve("source-lines-work"),
                developerDir,
              )
            logger.info("[kanama] iOS source lines: $functions functions, $rows rows")
            listOf("-DKANAMA_IOS_SOURCE_LINES=1", "-I", tableDir.absolutePath)
          } else emptyList()
        val sourceLinesObject = dir.resolve("kanama_ios_source_lines.o")
        compileC(
          developerDir,
          shim,
          shim.resolve("bootstrap/kanama_ios_source_lines.c"),
          sourceLinesObject,
          table,
        )
        val lib = dir.resolve("libkanama_ios.a")
        run(
          developerDir,
          "xcrun",
          "--sdk",
          "iphoneos",
          "libtool",
          "-static",
          "-o",
          lib.absolutePath,
          shimObject.absolutePath,
          sourceLinesObject.absolutePath,
          runtimeLib.absolutePath,
        )
        val framework =
          dir.resolve(
            if (type == "debug") KanamaIosAddonFiles.DEBUG_XCFRAMEWORK
            else KanamaIosAddonFiles.RELEASE_XCFRAMEWORK
          )
        run(
          developerDir,
          "xcodebuild",
          "-create-xcframework",
          "-library",
          lib.absolutePath,
          "-headers",
          shim.resolve("include").absolutePath,
          "-output",
          framework.absolutePath,
        )
        framework
      }
    install(project, frameworks.values.toList())
  }

  private fun install(project: File, frameworks: List<File>) {
    val bin = project.resolve(KanamaIosAddonFiles.BIN_DIR)
    bin.deleteRecursively()
    bin.mkdirs()
    for (framework in frameworks) framework.copyRecursively(bin.resolve(framework.name))

    val descriptor = project.resolve("addons/kanama/kanama.gdextension")
    if (!descriptor.isFile) {
      throw GradleException(
        "addons/kanama/kanama.gdextension is missing. Unzip the Kanama kit or addon into this project again."
      )
    }
    KanamaIosAddonFiles.enableIosGdextensionMetadata(descriptor)
    // Godot loads the descriptor only when the project's extension list names it (the kit's does).
    val extensionList = project.resolve(".godot/extension_list.cfg")
    val entry = "res://addons/kanama/kanama.gdextension"
    val existing = if (extensionList.isFile) extensionList.readLines() else emptyList()
    if (entry !in existing) {
      extensionList.parentFile.mkdirs()
      extensionList.writeText((existing + entry).joinToString("\n", postfix = "\n"))
    }
    logger.lifecycle(
      "[kanama] Installed ${KanamaIosAddonFiles.BIN_DIR}/${KanamaIosAddonFiles.DEBUG_XCFRAMEWORK} " +
        "and ${KanamaIosAddonFiles.RELEASE_XCFRAMEWORK}. Export the Xcode project from Godot."
    )
  }

  /**
   * The first iOS build on a Mac downloads Kotlin/Native; every release link needs a large heap.
   * Both are said before the build starts, so a long first build is not mistaken for a hang.
   */
  private fun stateFirstBuildCosts() {
    val konan =
      System.getenv("KONAN_DATA_DIR")?.takeIf { it.isNotBlank() }?.let(::File)
        ?: File(System.getProperty("user.home"), ".konan")
    val arch = if (System.getProperty("os.arch") == "aarch64") "aarch64" else "x86_64"
    val compiler = konan.resolve("kotlin-native-prebuilt-macos-$arch-${KanamaVersions.KOTLIN}")
    if (!compiler.isDirectory) {
      logger.lifecycle(
        "[kanama] First iOS build on this Mac: Kotlin/Native downloads its compiler and the iOS " +
          "toolchain, about 1 GB, into $konan. It needs a network connection and takes a few minutes."
      )
    }
    val memoryGb =
      (ManagementFactory.getOperatingSystemMXBean() as? com.sun.management.OperatingSystemMXBean)
        ?.totalMemorySize
        ?.div(1L shl 30)
    val note =
      "[kanama] The iOS release link uses up to about $RELEASE_LINK_MEMORY_GB GB of memory and " +
        "takes a few minutes."
    if (memoryGb != null && memoryGb < RECOMMENDED_MEMORY_GB) {
      logger.warn(
        "$note This Mac has $memoryGb GB; a Mac with $RECOMMENDED_MEMORY_GB GB or more is recommended."
      )
    } else {
      logger.lifecycle(note)
    }
  }

  /**
   * Every Kanama entry the shim calls must be in the library. Kotlin/Native drops a dependency's
   * `@CName` functions unless the binary exports that klib, and Xcode's link would only fail later,
   * naming one symbol. With project scripts, the generated register function must be there too: the
   * shim's reference to it is weak, so without it the game would start with no scripts at all.
   */
  private fun checkRuntimeEntries(
    developerDir: String,
    shimObject: File,
    runtimeLib: File,
    registersScripts: Boolean,
  ) {
    val needed =
      output(developerDir, "xcrun", "nm", "-u", shimObject.absolutePath)
        .lines()
        .map { it.trim() }
        .filter { it.startsWith("_kanama_ios_") && it != "_$REGISTER_SYMBOL" }
        .toSet() + if (registersScripts) setOf("_$REGISTER_SYMBOL") else emptySet()
    val defined =
      output(developerDir, "xcrun", "nm", "-gU", "-j", runtimeLib.absolutePath)
        .lines()
        .map { it.trim() }
        .toSet()
    val missing = (needed - defined).sorted()
    if (missing.isNotEmpty()) {
      throw GradleException(
        "The iOS library ${runtimeLib.name} lacks ${missing.size} Kanama entr" +
          (if (missing.size == 1) "y" else "ies") +
          " the shim calls (" +
          missing.take(5).joinToString { it.removePrefix("_") } +
          (if (missing.size > 5) ", ..." else "") +
          "): the runtime klib was not exported into it, or the shim and the runtime klib in " +
          "addons/kanama/maven are from different Kanama versions. Unzip the Kanama kit or addon " +
          "into this project again."
      )
    }
  }

  private fun generatedRegistryHasEntry(inner: File): Boolean {
    val registry =
      inner.resolve(
        "build/generated/ksp/iosArm64/iosArm64Main/kotlin/net/multigesture/kanama/ios/" +
          "KanamaIosProjectRegistry.generated.kt"
      )
    return registry.isFile && "\"$REGISTER_SYMBOL\"" in registry.readText()
  }

  private fun compileC(
    developerDir: String,
    shim: File,
    source: File,
    output: File,
    extra: List<String>,
  ) {
    val sdk = output(developerDir, "xcrun", "--sdk", "iphoneos", "--show-sdk-path").trim()
    run(
      developerDir,
      *(listOf(
          "xcrun",
          "--sdk",
          "iphoneos",
          "clang",
          "-arch",
          "arm64",
          "-isysroot",
          sdk,
          "-mios-version-min=${KanamaVersions.IOS_MIN_VERSION}",
          "-fvisibility=hidden",
          "-I",
          shim.resolve("gdextension").absolutePath,
          "-I",
          shim.resolve("include").absolutePath,
        ) + extra + listOf("-c", source.absolutePath, "-o", output.absolutePath))
        .toTypedArray(),
    )
  }

  private fun run(developerDir: String, vararg command: String) {
    val result =
      execOperations.exec { spec ->
        spec.commandLine(*command)
        spec.environment("DEVELOPER_DIR", developerDir)
        spec.isIgnoreExitValue = true
      }
    if (result.exitValue != 0) {
      throw GradleException(
        "${command.take(4).joinToString(" ")} ... failed (exit ${result.exitValue})."
      )
    }
  }

  private fun output(developerDir: String, vararg command: String): String {
    val out = ByteArrayOutputStream()
    val result =
      execOperations.exec { spec ->
        spec.commandLine(*command)
        spec.environment("DEVELOPER_DIR", developerDir)
        spec.standardOutput = out
        spec.isIgnoreExitValue = true
      }
    if (result.exitValue != 0) {
      throw GradleException(
        "${command.take(4).joinToString(" ")} ... failed (exit ${result.exitValue})."
      )
    }
    return out.toString(Charsets.UTF_8.name())
  }

  private fun unzip(zip: File, into: File) {
    into.deleteRecursively()
    ZipFile(zip).use { file ->
      for (entry in file.entries()) {
        val target = into.resolve(entry.name).normalize()
        check(target.startsWith(into)) { "unexpected entry ${entry.name} in $zip" }
        if (entry.isDirectory) {
          target.mkdirs()
        } else {
          target.parentFile.mkdirs()
          file.getInputStream(entry).use { input -> target.outputStream().use { input.copyTo(it) } }
        }
      }
    }
  }

  private fun writeInnerBuild(inner: File, project: File, scripts: File) {
    val projectPath = project.canonicalFile
    val scriptsPath = scripts.canonicalFile
    // A script's `res://` path is its path in the project, wherever kotlin-src is.
    val scriptRoot = if (scriptsPath.startsWith(projectPath)) projectPath else scriptsPath
    val values =
      mapOf(
        "KANAMA_MAVEN" to project.resolve("addons/kanama/maven").absoluteFile.toURI().toString(),
        "INNER_PROJECT" to INNER_PROJECT,
        "KANAMA" to KanamaVersions.KANAMA,
        "KOTLIN" to KanamaVersions.KOTLIN,
        "KSP" to KanamaVersions.KSP,
        "SCRIPTS_DIR" to kotlinString(scriptsPath.invariantSeparatorsPath),
        "SCRIPT_ROOT" to kotlinString(scriptRoot.invariantSeparatorsPath),
        "PROJECT_DIR" to kotlinString(projectPath.invariantSeparatorsPath),
        "ALLOW_SKIPS" to allowSkips.getOrElse(false).toString(),
        "HEAP" to "${INNER_HEAP_GB}g",
        "HEAP_NOTE" to HEAP_NOTE,
      )
    for ((resource, target) in INNER_FILES) {
      val text =
        javaClass.getResourceAsStream("ios/$resource")?.use { it.readBytes().decodeToString() }
          ?: throw GradleException("The Kanama Gradle plugin jar has no ios/$resource")
      val file = inner.resolve(target)
      file.parentFile.mkdirs()
      val written = values.entries.fold(text) { acc, (key, value) -> acc.replace("@$key@", value) }
      check(!Regex("@[A-Z_]+@").containsMatchIn(written)) { "unreplaced placeholder in $resource" }
      if (!file.isFile || file.readText() != written) file.writeText(written)
    }
  }

  /** [text] as the inside of a Kotlin string literal of the inner build script. */
  private fun kotlinString(text: String): String =
    text.replace("\\", "\\\\").replace("\"", "\\\"").replace("$", "\\$")

  internal companion object {
    const val INNER_PROJECT = "kanama-ios-scripts"
    val BUILD_TYPES = listOf("debug", "release")
    val INNER_FILES =
      listOf(
        "settings.gradle.kts.in" to "settings.gradle.kts",
        "build.gradle.kts.in" to "build.gradle.kts",
        "gradle.properties.in" to "gradle.properties",
      )
    const val REGISTER_SYMBOL = "kanama_ios_project_scripts_register"

    // The inner build's Gradle heap, in which Kotlin/Native compiles and links. Measured on an M1
    // Max (Kotlin 2.4.21, the starter): the release link runs out of a 2 GB heap and links in 3 GB,
    // its heap use peaks near 4 GB when it has more, and LLVM's native memory comes on top, so the
    // daemon's process peaks at 9 to 11 GB. 6 GB leaves the heap room for a larger project.
    const val INNER_HEAP_GB = 6
    const val RELEASE_LINK_MEMORY_GB = 11
    const val RECOMMENDED_MEMORY_GB = 16
    const val HEAP_NOTE = "measured: 2 GB runs out of memory, 3 GB links"
  }
}
