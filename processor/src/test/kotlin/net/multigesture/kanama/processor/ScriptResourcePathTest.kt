package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The `res://` path an iOS or Web script is registered under is its path in the Godot project
 * wherever the scripts folder is: the build passes the project as the script root, so a folder
 * called `game/kotlin` registers `res://game/kotlin/X.kt`, not `res://X.kt`.
 */
class ScriptResourcePathTest {
  @Test
  fun aProjectRootKeepsTheScriptsFolderInThePath() {
    assertEquals("res://game/kotlin/X.kt", scriptResourcePath("/p/game/kotlin/X.kt", listOf("/p")))
    assertEquals(
      "res://game/kotlin/sub/Y.kt",
      scriptResourcePath("/p/game/kotlin/sub/Y.kt", listOf("/p")),
    )
  }

  @Test
  fun aKotlinSrcRootStillRegistersUnderKotlinSrc() {
    assertEquals(
      "res://kotlin-src/X.kt",
      scriptResourcePath("/p/kotlin-src/X.kt", listOf("/p/kotlin-src")),
    )
    assertEquals("res://kotlin-src/X.kt", scriptResourcePath("/p/kotlin-src/X.kt", listOf("/p")))
  }

  @Test
  fun aScriptsFolderOutsideTheProjectKeepsItselfAsTheRoot() {
    assertEquals(
      "res://X.kt",
      scriptResourcePath("/elsewhere/game/kotlin/X.kt", listOf("/elsewhere/game/kotlin")),
    )
  }
}
