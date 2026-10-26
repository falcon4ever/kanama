package net.multigesture.kanama.gradle

import java.io.File
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations

/**
 * `./gradlew buildAndroidScripts`: what `installAndroidPluginAar` does in a Kanama checkout, from
 * the addon alone.
 * 1. The project's `kotlin-src` and the registrars `buildScripts` generated (KSP) are audited,
 *    remapped for PanamaPort (the checkout's `KanamaAndroidRemap`) and written to the inner build.
 * 2. The inner build (`build/kanama/android`, rewritten on every run) compiles them into an Android
 *    library against the prebuilt runtime AAR `kanama-android` from `addons/kanama/maven`. It runs
 *    with the project's own `gradlew`.
 * 3. Both AARs and `KanamaAndroid.gdap` go to `android/plugins/`, the Android lines into
 *    `addons/kanama/kanama.gdextension`, and `project.godot` gets the ETC2/ASTC texture import
 *    Godot's Android export requires.
 *
 * It always runs: the inner Gradle build decides what is up to date.
 */
abstract class BuildAndroidScripts : DefaultTask() {
  @get:Internal abstract val projectDirectory: DirectoryProperty
  @get:Internal abstract val scriptsDirectory: DirectoryProperty
  @get:Internal abstract val kspDirectory: DirectoryProperty
  @get:Internal abstract val innerBuildDirectory: DirectoryProperty

  @get:Inject abstract val execOperations: ExecOperations

  init {
    doNotTrackState("installs into android/plugins; the inner Gradle build tracks its own state")
  }

  @TaskAction
  fun build() {
    val project = projectDirectory.get().asFile
    val version = KanamaVersions.KANAMA
    val runtimeAar =
      project.resolve(
        "addons/kanama/maven/net/multigesture/kanama/kanama-android/$version/kanama-android-$version.aar"
      )
    if (!runtimeAar.isFile) {
      throw GradleException(
        "addons/kanama/maven has no Kanama Android runtime (kanama-android $version). " +
          "Unzip the Kanama $version kit or addon into this project again; in a project built " +
          "from a Kanama checkout, run installAndroidPluginAar in the checkout instead."
      )
    }
    val sdk = AndroidSdk.find() ?: throw GradleException(AndroidSdk.NOT_FOUND)
    logger.lifecycle("[kanama] Android SDK: ${sdk.dir} (from ${sdk.source})")

    val scripts = scriptsDirectory.get().asFile
    val ksp = kspDirectory.get().asFile
    if (!ksp.isDirectory) {
      throw GradleException(
        "No generated script registrars in $ksp: run ./gradlew buildScripts first."
      )
    }
    val inner = innerBuildDirectory.get().asFile
    val sources = inner.resolve("src/main/kotlin")
    KanamaAndroidRemap.auditOriginalDemoSources(scripts)
    sources.deleteRecursively()
    copyRemapped(scripts, sources)
    copyRemapped(ksp, sources)
    KanamaAndroidRemap.auditGeneratedSources(sources, "scripts")
    writeInnerBuild(inner, project)

    val gradlew = project.resolve(if (isWindows()) "gradlew.bat" else "gradlew")
    if (!gradlew.isFile) throw GradleException("No Gradle wrapper at $gradlew.")
    val result =
      execOperations.exec { spec ->
        spec.commandLine(gradlew.absolutePath, "-p", inner.absolutePath, "assembleDebug")
        spec.environment("ANDROID_HOME", sdk.dir.absolutePath)
        spec.environment("ANDROID_SDK_ROOT", sdk.dir.absolutePath)
        // The JDK this Gradle runs on, which runs the inner Gradle too.
        spec.environment("JAVA_HOME", System.getProperty("java.home"))
        spec.isIgnoreExitValue = true
      }
    if (result.exitValue != 0) {
      throw GradleException(
        "The Android scripts build failed (exit ${result.exitValue}); its output is above. " +
          "It runs in ${inner.relativeTo(project).invariantSeparatorsPath}."
      )
    }
    val scriptsAar = inner.resolve("build/outputs/aar/$INNER_PROJECT-debug.aar")
    if (!scriptsAar.isFile) throw GradleException("The Android scripts build wrote no $scriptsAar.")

    install(project, runtimeAar, scriptsAar)
  }

  private fun install(project: File, runtimeAar: File, scriptsAar: File) {
    val plugins = project.resolve(KanamaAndroidPluginFiles.PLUGINS_DIR)
    plugins.mkdirs()
    runtimeAar.copyTo(plugins.resolve(KanamaAndroidPluginFiles.RUNTIME_AAR), overwrite = true)
    scriptsAar.copyTo(plugins.resolve(KanamaAndroidPluginFiles.SCRIPTS_AAR), overwrite = true)
    plugins
      .resolve(KanamaAndroidPluginFiles.GDAP)
      .writeText(KanamaAndroidPluginFiles.gdap(KanamaVersions.PANAMAPORT_CORE))

    val descriptor = project.resolve("addons/kanama/kanama.gdextension")
    if (!descriptor.isFile) {
      throw GradleException(
        "addons/kanama/kanama.gdextension is missing. Unzip the Kanama kit or addon into this project again."
      )
    }
    KanamaAndroidPluginFiles.enableAndroidGdextensionMetadata(descriptor)

    ProjectGodot.enableEtc2AstcImportFor(project, "Android", logger)
    logger.lifecycle(
      "[kanama] Installed ${KanamaAndroidPluginFiles.PLUGINS_DIR}/${KanamaAndroidPluginFiles.GDAP} " +
        "and both AARs. Turn on the KanamaAndroid plugin in the Android export preset."
    )
  }

  private fun copyRemapped(from: File, into: File) {
    if (!from.isDirectory) return
    from
      .walkTopDown()
      .filter { it.isFile && (it.extension == "kt" || it.extension == "java") }
      .forEach { file ->
        val target = into.resolve(file.relativeTo(from).path)
        target.parentFile.mkdirs()
        target.writeText(
          file.readLines().joinToString("\n", postfix = "\n") { KanamaAndroidRemap.remapLine(it) }
        )
      }
  }

  private fun writeInnerBuild(inner: File, project: File) {
    val values =
      mapOf(
        "KANAMA_MAVEN" to project.resolve("addons/kanama/maven").absoluteFile.toURI().toString(),
        "INNER_PROJECT" to INNER_PROJECT,
        "KANAMA" to KanamaVersions.KANAMA,
        "ANDROID_GRADLE_PLUGIN" to KanamaVersions.ANDROID_GRADLE_PLUGIN,
        "ANDROID_KOTLIN" to KanamaVersions.ANDROID_KOTLIN,
        "ANDROID_KOTLIN_API" to KanamaVersions.ANDROID_KOTLIN_API,
        "ANDROID_COMPILE_SDK" to KanamaVersions.ANDROID_COMPILE_SDK,
        "ANDROID_MIN_SDK" to KanamaVersions.ANDROID_MIN_SDK,
        "ANDROID_COROUTINES" to KanamaVersions.ANDROID_COROUTINES,
        "PANAMAPORT_CORE" to KanamaVersions.PANAMAPORT_CORE,
      )
    for ((resource, target) in INNER_FILES) {
      val text =
        javaClass.getResourceAsStream("android/$resource")?.use { it.readBytes().decodeToString() }
          ?: throw GradleException("The Kanama Gradle plugin jar has no android/$resource")
      val file = inner.resolve(target)
      file.parentFile.mkdirs()
      val written = values.entries.fold(text) { acc, (key, value) -> acc.replace("@$key@", value) }
      check(!Regex("@[A-Z_]+@").containsMatchIn(written)) { "unreplaced placeholder in $resource" }
      if (!file.isFile || file.readText() != written) file.writeText(written)
    }
  }

  private fun isWindows() = System.getProperty("os.name").startsWith("Windows")

  private companion object {
    const val INNER_PROJECT = "kanama-android-scripts"
    val INNER_FILES =
      listOf(
        "settings.gradle.kts.in" to "settings.gradle.kts",
        "build.gradle.kts.in" to "build.gradle.kts",
        "gradle.properties.in" to "gradle.properties",
        "AndroidManifest.xml.in" to "src/main/AndroidManifest.xml",
      )
  }
}
