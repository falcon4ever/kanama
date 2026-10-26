package net.multigesture.kanama.gradle

import java.io.File
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * `id("net.multigesture.kanama")`: the export tasks of a Kanama project that has no Kanama checkout
 * beside it (a desktop kit, or a project set up from the store addon).
 *
 * The project stays the `kotlin("jvm")` build IntelliJ imports; `buildScripts` stays in
 * `addons/kanama/kanama-project.gradle.kts`. Each export task writes a small plugin-owned Gradle
 * build under `build/kanama/<platform>/` and runs it with the project's own `gradlew`, so nothing
 * Android- or iOS-specific is configured, and no Kotlin/Native is downloaded, until that platform's
 * build is asked for.
 */
class KanamaPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    checkAddonVersion(project.projectDir)

    project.tasks.register("buildAndroidScripts", BuildAndroidScripts::class.java) { task ->
      task.group = "kanama"
      task.description =
        "Compile kotlin-src for Android and install the KanamaAndroid plugin into android/plugins."
      task.dependsOn("buildScripts")
      task.projectDirectory.set(project.layout.projectDirectory)
      task.scriptsDirectory.set(
        project.layout.projectDirectory.dir(
          project.providers.gradleProperty("kanamaProjectScriptsDir").orElse("kotlin-src")
        )
      )
      task.kspDirectory.set(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
      task.innerBuildDirectory.set(project.layout.buildDirectory.dir("kanama/android"))
    }

    project.tasks.register("buildIosScripts", BuildIosScripts::class.java) { task ->
      task.group = "kanama"
      task.description =
        "Compile kotlin-src for iOS and install the Kanama iOS libraries into addons/kanama/bin/ios."
      // The editor loads the desktop scripts during the export (the export guard and the scenes'
      // script properties).
      task.dependsOn("buildScripts")
      task.projectDirectory.set(project.layout.projectDirectory)
      task.scriptsDirectory.set(
        project.layout.projectDirectory.dir(
          project.providers.gradleProperty("kanamaProjectScriptsDir").orElse("kotlin-src")
        )
      )
      task.innerBuildDirectory.set(project.layout.buildDirectory.dir("kanama/ios"))
      task.xcodeDeveloperDir.set(project.providers.gradleProperty("kanamaXcodeDeveloperDir"))
      task.allowSkips.set(
        project.providers.gradleProperty("kanamaIosAllowSkips").map { it.trim() == "true" }
      )
      task.heap.set(project.providers.gradleProperty("kanamaIosHeap"))
    }
  }

  companion object {
    const val VERSION_FILE = "addons/kanama/kanama.version"

    /**
     * The plugin and the addon must be the same Kanama. The template's settings.gradle.kts resolves
     * the plugin from [VERSION_FILE], so a mismatch here means a build whose own settings lack that
     * block pinned a version by hand: removing the version alone would leave the plugin unresolved.
     */
    fun checkAddonVersion(projectDir: File) {
      val file = projectDir.resolve(VERSION_FILE)
      if (!file.isFile) {
        throw GradleException(
          "Kanama addon not found: $VERSION_FILE is missing. Unzip the Kanama addon into addons/ again."
        )
      }
      val addon = file.readText().trim()
      if (addon != KanamaVersions.KANAMA) {
        throw GradleException(
          "The Kanama Gradle plugin is ${KanamaVersions.KANAMA} but the addon is $addon " +
            "($VERSION_FILE). Merge the pluginManagement { } block of " +
            "addons/kanama/templates/release-kit/settings.gradle.kts into your settings.gradle.kts " +
            "and drop the version from id(\"net.multigesture.kanama\"): that block takes it from " +
            "$VERSION_FILE."
        )
      }
    }
  }
}
