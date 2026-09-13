package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 104 step 1: `GodotHandle` replaced `java.lang.foreign.MemorySegment` in every public wrapper
 * and script signature. A script left on the old type must fail the build with the migration, not
 * with an argument-type mismatch deep inside the generated registrar.
 */
class LegacyHandleConstructorTest {

  @Test
  fun onlyTheFfmHandleTypeIsRejected() {
    assertTrue(isLegacyHandleParameterType("java.lang.foreign.MemorySegment"))
    assertFalse(isLegacyHandleParameterType("net.multigesture.kanama.api.GodotHandle"))
    assertFalse(isLegacyHandleParameterType("com.v7878.foreign.MemorySegment"))
    assertFalse(isLegacyHandleParameterType(null))
  }

  @Test
  fun theMessageNamesTheMigration() {
    val message = legacyHandleConstructorMessage("Bullet", "godotObject")
    assertContains(message, "Bullet")
    assertContains(message, "java.lang.foreign.MemorySegment")
    assertContains(message, "class Bullet(godotObject: GodotHandle) : KanamaScript<...>")
    assertContains(message, "import net.multigesture.kanama.api.GodotHandle")
    assertContains(message, "SomeWrapper(other.handle)")
  }

  @Test
  fun theMessageUsesTheScriptsOwnParameterName() {
    assertContains(
      legacyHandleConstructorMessage("Player", "owner"),
      "class Player(owner: GodotHandle)",
    )
  }
}
