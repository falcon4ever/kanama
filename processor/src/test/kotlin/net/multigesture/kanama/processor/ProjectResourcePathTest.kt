package net.multigesture.kanama.processor

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The `res://` path an autoload's Kotlin script is matched by (task 135 item 5). A kit project's
 * Web build compiles a merged copy of `kotlin-src` under the project's own `build/kanama/web/`, so
 * the copy's path in the project is not the script's `res://` path: the script root wins, or every
 * Kotlin-script autoload would be typed Node on the Web only.
 */
class ProjectResourcePathTest {
  private val project =
    File(System.getProperty("java.io.tmpdir"), "kanama-res-path-test").canonicalFile

  private fun path(vararg parts: String) =
    parts.fold(project) { dir, part -> dir.resolve(part) }.path

  @Test
  fun aScriptOfTheProjectIsItsPathInTheProject() {
    assertEquals(
      "res://kotlin-src/game/Settings.kt",
      projectResourcePath(path("kotlin-src", "game", "Settings.kt"), project, emptyList()),
    )
  }

  @Test
  fun aMergedCopyUnderTheProjectsBuildTakesTheScriptRootPath() {
    val merged = path("build", "kanama", "web", "merged")
    assertEquals(
      "res://kotlin-src/game/Settings.kt",
      projectResourcePath(
        path("build", "kanama", "web", "merged", "kotlin-src", "game", "Settings.kt"),
        project,
        listOf(merged),
      ),
    )
  }

  @Test
  fun aScriptsFolderOtherThanKotlinSrcKeepsItsName() {
    val merged = path("build", "kanama", "web", "merged")
    assertEquals(
      "res://scripts/Settings.kt",
      projectResourcePath(
        path("build", "kanama", "web", "merged", "scripts", "Settings.kt"),
        project,
        listOf(merged),
      ),
    )
  }

  @Test
  fun theProjectAsTheScriptRootIsTheProjectPath() {
    // iOS: a kotlin-src inside the project makes the project itself the script root.
    assertEquals(
      "res://kotlin-src/Settings.kt",
      projectResourcePath(path("kotlin-src", "Settings.kt"), project, listOf(project.path)),
    )
  }
}
