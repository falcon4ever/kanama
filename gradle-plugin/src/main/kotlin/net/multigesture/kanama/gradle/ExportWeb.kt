package net.multigesture.kanama.gradle

import java.io.File
import java.lang.management.ManagementFactory
import javax.inject.Inject
import net.multigesture.kanama.buildlogic.KanamaWebExport
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations

/**
 * `./gradlew exportWeb`: what `:web-runtime:exportWeb -PkanamaWebProjectDir` does in a Kanama
 * checkout, from the addon alone.
 * 1. `kotlin-src` (with `web/kotlin-src` over it, the checkout's merge) is compiled for Kotlin/Wasm
 *    by the inner build (`build/kanama/web`, rewritten on every run, run with the project's own
 *    `gradlew`): KSP generates the script registry, its `main()` and the GDScript proxies, and the
 *    scripts link against the prebuilt runtime klib `kanama-web` from `addons/kanama/maven` into
 *    the webpack bundle.
 * 2. A copy of the project is staged under `build/kanama/web/godot-project` with the proxies
 *    attached and Kanama's Web preset (`addons/kanama/web`); the project itself is not changed.
 * 3. Godot exports the staged copy headless with its `web_nothreads_release.zip` template, and the
 *    bundle and the bridge go in beside it: the export lands in `build/web-export/`.
 *
 * It always runs: the inner Gradle build decides what is up to date.
 */
abstract class ExportWeb : DefaultTask() {
  @get:Internal abstract val projectDirectory: DirectoryProperty
  @get:Internal abstract val innerBuildDirectory: DirectoryProperty
  @get:Internal abstract val exportDirectory: DirectoryProperty
  /** `-PkanamaGodotExecutable`, when set. */
  @get:Internal abstract val godotExecutable: Property<String>
  /** `-PkanamaWebTemplateRelease`, when set. */
  @get:Internal abstract val templateRelease: Property<String>
  /** `-PkanamaWebHeap=4g`: the inner build's heap, for a project too large for the default. */
  @get:Internal abstract val heap: Property<String>

  @get:Inject abstract val execOperations: ExecOperations

  init {
    doNotTrackState("writes build/web-export; the inner Gradle build tracks its own state")
  }

  private fun <T> web(block: () -> T): T =
    try {
      block()
    } catch (e: KanamaWebExport.KanamaWebExportException) {
      throw GradleException(e.message ?: "The Web export failed", e)
    }

  @TaskAction
  fun export() {
    val project = projectDirectory.get().asFile
    val version = KanamaVersions.KANAMA
    val web = project.resolve(WEB_DIR)
    val maven = project.resolve("addons/kanama/maven/net/multigesture/kanama")
    val missing =
      listOf(
          maven.resolve("kanama-web-wasm-js/$version/kanama-web-wasm-js-$version.klib"),
          maven.resolve(
            "kanama-common-api-wasm-js/$version/kanama-common-api-wasm-js-$version.klib"
          ),
          web.resolve(KanamaWebExport.BRIDGE),
          web.resolve(KanamaWebExport.SHELL),
          web.resolve(KanamaWebExport.PRESET),
        )
        .filterNot { it.isFile }
    if (missing.isNotEmpty()) {
      throw GradleException(
        "The Kanama addon has no Web runtime (missing " +
          missing.joinToString { it.relativeTo(project).invariantSeparatorsPath } +
          "). Unzip the Kanama $version kit or store addon into this project again; in a project " +
          "built from a Kanama checkout, run :web-runtime:exportWeb -PkanamaWebProjectDir in the " +
          "checkout instead."
      )
    }
    val scripts = project.resolve("kotlin-src")
    if (!scripts.isDirectory) {
      throw GradleException(
        "No kotlin-src/ folder in $project: the Web export compiles the project's Kotlin scripts from there."
      )
    }
    web { KanamaWebExport.mainSceneProblem(project) }?.let { throw GradleException(it) }
    val godot = web {
      KanamaWebExport.findGodot(godotExecutable.orNull, System.getenv("KANAMA_GODOT_BIN"))
    }
    val template = web {
      KanamaWebExport.findTemplateRelease(
        templateRelease.orNull?.let { project.resolve(it) },
        KanamaVersions.GODOT,
      )
    }
    val heapSize = heap.getOrElse(INNER_HEAP).trim()
    if (!Regex("[1-9][0-9]*[gGmM]").matches(heapSize)) {
      throw GradleException("-PkanamaWebHeap=$heapSize is not a heap size: write it like 4g.")
    }

    val inner = innerBuildDirectory.get().asFile
    val merged = inner.resolve("merged")
    web {
      KanamaWebExport.mergeScripts(
        scripts,
        project.resolve("web/kotlin-src"),
        project.resolve("web/kotlin-src-excludes.txt"),
        merged,
      )
    }
    writeInnerBuild(inner, project, merged, heapSize)
    stateFirstBuildCosts()

    val gradlew = project.resolve(if (isWindows()) "gradlew.bat" else "gradlew")
    if (!gradlew.isFile) throw GradleException("No Gradle wrapper at $gradlew.")
    val started = System.nanoTime()
    val result =
      execOperations.exec { spec ->
        spec.commandLine(
          gradlew.absolutePath,
          "-p",
          inner.absolutePath,
          // On the command line, so it wins over an org.gradle.jvmargs in the user's
          // ~/.gradle/gradle.properties, which the inner build's own gradle.properties loses to.
          "-Dorg.gradle.jvmargs=${innerJvmArgs(heapSize)}",
          "wasmJsBrowserDistribution",
        )
        // The JDK this Gradle runs on, which runs the inner Gradle too.
        spec.environment("JAVA_HOME", System.getProperty("java.home"))
        spec.isIgnoreExitValue = true
      }
    if (result.exitValue != 0) {
      throw GradleException(
        "The Web scripts build failed (exit ${result.exitValue}); its output is above. " +
          "It runs in ${inner.relativeTo(project).invariantSeparatorsPath}."
      )
    }
    logger.lifecycle(
      "[kanama] Web scripts compiled and linked in ${(System.nanoTime() - started) / 1_000_000_000} s."
    )

    val proxies = inner.resolve(PROXIES)
    val protocolJson = proxies.resolve(KanamaWebExport.PROTOCOL_JSON)
    val bridge = web.resolve(KanamaWebExport.BRIDGE)
    val generatedProtocol = web { KanamaWebExport.protocolVersion(protocolJson) }
    val bridgeProtocol = web { KanamaWebExport.bridgeProtocolVersion(bridge) }
    if (generatedProtocol != bridgeProtocol) {
      throw GradleException(
        "$WEB_DIR/${KanamaWebExport.BRIDGE} speaks Kanama Web protocol $bridgeProtocol, but the " +
          "Kanama $version processor generated protocol $generatedProtocol: the addon is a mix of " +
          "two Kanama versions. Unzip the Kanama $version kit or store addon into this project again."
      )
    }

    val staging = inner.resolve("godot-project")
    val staged = web {
      KanamaWebExport.stageProject(
        KanamaWebExport.StageInputs(
          projectDir = project,
          stagingDir = staging,
          preset = web.resolve(KanamaWebExport.PRESET),
          assets = listOf(bridge, web.resolve(KanamaWebExport.SHELL)),
          proxies = proxies,
          mergedSources = merged.resolve("kotlin-src"),
        )
      )
    }
    if (staged.unattached.isNotEmpty()) {
      // Not an error: a @GlobalClass script, or one a scene loads by path, has no attachment.
      logger.info("[kanama] Web: no scene attaches ${staged.unattached}")
    }
    web { KanamaWebExport.setPresetTemplates(staging, template, null) }

    val exportDir = exportDirectory.get().asFile
    exportDir.deleteRecursively()
    exportDir.mkdirs()
    logger.lifecycle("[kanama] Godot: $godot; Web template: $template")
    val godotResult =
      execOperations.exec { spec ->
        spec.commandLine(
          KanamaWebExport.godotExportCommand(godot, staging, exportDir, debug = false)
        )
        spec.isIgnoreExitValue = true
      }
    if (godotResult.exitValue != 0) {
      throw GradleException(
        "Godot's Web export failed (exit ${godotResult.exitValue}); its output is above. It " +
          "exported the staged copy in ${staging.relativeTo(project).invariantSeparatorsPath}."
      )
    }
    val finished = web {
      KanamaWebExport.finishExport(
        KanamaWebExport.FinishInputs(
          exportDir = exportDir,
          distribution = inner.resolve(DISTRIBUTION),
          bridge = bridge,
          protocolJson = protocolJson,
          stagingDir = staging,
          demo = "project",
          projectId = KanamaWebExport.projectId(project),
        )
      )
    }
    logger.lifecycle(
      "[kanama] Web export of ${staged.scriptCount} script(s): " +
        "${exportDir.relativeTo(project).invariantSeparatorsPath}/index.html " +
        "(${finished.totalBytes / 1024} KB). Serve that folder over HTTP to play it."
    )
  }

  /**
   * The first Web build downloads Node.js, Yarn and the npm packages webpack needs; the Kotlin/Wasm
   * link of Kanama's runtime takes memory. Both are said before the build starts, so a long first
   * build is not mistaken for a hang.
   */
  private fun stateFirstBuildCosts() {
    val gradleHome =
      System.getenv("GRADLE_USER_HOME")?.takeIf { it.isNotBlank() }?.let(::File)
        ?: File(System.getProperty("user.home"), ".gradle")
    if (!gradleHome.resolve("nodejs").isDirectory) {
      logger.lifecycle(
        "[kanama] First Web build with this Gradle: it downloads Node.js, Yarn and Binaryen " +
          "(about $FIRST_DOWNLOAD_MB MB, into $gradleHome) and needs a network connection."
      )
    }
    val memoryGb =
      (ManagementFactory.getOperatingSystemMXBean() as? com.sun.management.OperatingSystemMXBean)
        ?.totalMemorySize
        ?.div(1L shl 30)
    if (memoryGb != null && memoryGb < RECOMMENDED_MEMORY_GB) {
      logger.warn(
        "[kanama] This computer has $memoryGb GB of memory; the Web build's compiler can use up " +
          "to $INNER_HEAP of it, and $RECOMMENDED_MEMORY_GB GB or more is recommended."
      )
    }
  }

  private fun writeInnerBuild(inner: File, project: File, merged: File, heapSize: String) {
    val values =
      mapOf(
        "KANAMA_MAVEN" to project.resolve("addons/kanama/maven").absoluteFile.toURI().toString(),
        "INNER_PROJECT" to INNER_PROJECT,
        "KANAMA" to KanamaVersions.KANAMA,
        "KOTLIN" to KanamaVersions.KOTLIN,
        "KSP" to KanamaVersions.KSP,
        "SCRIPT_ROOT" to kotlinString(merged.canonicalFile.invariantSeparatorsPath),
        "PROJECT_DIR" to kotlinString(project.canonicalFile.invariantSeparatorsPath),
        "JVM_ARGS" to innerJvmArgs(heapSize),
      )
    for ((resource, target) in INNER_FILES) {
      val text =
        javaClass.getResourceAsStream("web/$resource")?.use { it.readBytes().decodeToString() }
          ?: throw GradleException("The Kanama Gradle plugin jar has no web/$resource")
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

  private fun innerJvmArgs(heapSize: String) =
    "-Xmx$heapSize -XX:MaxMetaspaceSize=1g -Dfile.encoding=UTF-8"

  private fun isWindows() = System.getProperty("os.name").startsWith("Windows")

  internal companion object {
    const val WEB_DIR = "addons/kanama/web"
    // The bundle's global name, which the Kanama Web shell starts (globalThis["web-runtime"]).
    const val INNER_PROJECT = "web-runtime"
    const val PROXIES =
      "build/generated/ksp/wasmJs/wasmJsMain/resources/net/multigesture/kanama/web/generated/proxies"
    const val DISTRIBUTION = "build/dist/wasmJs/productionExecutable"
    val INNER_FILES =
      listOf(
        "settings.gradle.kts.in" to "settings.gradle.kts",
        "build.gradle.kts.in" to "build.gradle.kts",
        "gradle.properties.in" to "gradle.properties",
      )
    // The inner build's Gradle heap, in which the Kotlin/Wasm compile and link run (in-process).
    // Measured on an M1 Max (Kotlin 2.4.21, the starter): the inner daemon peaks near 1.3 GB.
    const val INNER_HEAP = "3g"
    const val RECOMMENDED_MEMORY_GB = 8
    // Node.js 54 MB, Binaryen 7 MB, Yarn 1 MB (Kotlin 2.4.21's packages, measured).
    const val FIRST_DOWNLOAD_MB = 70
  }
}
