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
 * Android-specific is configured until an Android build is asked for.
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
  }

  companion object {
    const val VERSION_FILE = "addons/kanama/kanama.version"

    /**
     * The plugin and the addon must be the same Kanama. The template's settings.gradle.kts resolves
     * the plugin from [VERSION_FILE], so a mismatch here means a version was pinned by hand.
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
            "($VERSION_FILE). Remove the version from id(\"net.multigesture.kanama\") in " +
            "build.gradle.kts: settings.gradle.kts takes it from $VERSION_FILE."
        )
      }
    }
  }
}
