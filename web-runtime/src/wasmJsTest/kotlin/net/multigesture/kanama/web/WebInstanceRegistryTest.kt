package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Two-step script creation (protocol 33): `reserve` mints the handle, `construct` runs the Kotlin
 * constructor once the proxy has wired the handle. (A test, so it lives outside the `api` package
 * the hand-code budget gate scans.)
 */
class WebInstanceRegistryTest {
  private class FakeScript(handle: Int) : KanamaWebScript(WebObjectId(handle))

  private fun passThrough(block: () -> KanamaWebScript): KanamaWebScript = block()

  private fun registry(onCreate: (Int) -> Unit = {}) = WebInstanceRegistry { _, handle ->
    onCreate(handle)
    FakeScript(handle)
  }

  @Test
  fun reservedSlotIsNotLiveButIsConstructing() {
    val registry = registry()
    val handle = registry.reserve(scriptId = 7)

    assertFalse(registry.isLive(handle))
    assertTrue(registry.isLiveOrConstructing(handle))
    assertEquals(7, registry.pendingScriptId(handle))
    val message = assertFailsWith<IllegalStateException> { registry.require(handle) }.message
    assertTrue(message!!.contains("still constructing"), message)
  }

  @Test
  fun aBrowserHandleNeverReadsAsAScriptHandle() {
    val registry = registry()
    val script = registry.reserve(scriptId = 1)
    registry.construct(script, ::passThrough)
    // The browser namespace (bit 30) with the script's own slot and generation: same low bits.
    val browserHandle = script or 0x40000000

    assertFalse(registry.isLive(browserHandle))
    assertFalse(registry.isLiveOrConstructing(browserHandle))
    assertNull(registry.pendingScriptId(browserHandle))
    assertFalse(registry.free(browserHandle))
    assertTrue(registry.isLive(script))
  }

  @Test
  fun constructMakesTheScriptLive() {
    val registry = registry()
    val handle = registry.reserve(scriptId = 1)

    assertEquals(handle, registry.construct(handle, ::passThrough))

    assertTrue(registry.isLive(handle))
    assertEquals(1, registry.require(handle).scriptId)
    assertNull(registry.pendingScriptId(handle))
  }

  @Test
  fun constructorSeesItsOwnHandleAsConstructing() {
    var duringConstruct = false
    lateinit var registry: WebInstanceRegistry
    registry = WebInstanceRegistry { _, handle ->
      duringConstruct = registry.isLiveOrConstructing(handle) && !registry.isLive(handle)
      FakeScript(handle)
    }
    val handle = registry.reserve(scriptId = 1)

    registry.construct(handle, ::passThrough)

    assertTrue(duringConstruct)
  }

  @Test
  fun throwingConstructorFreesTheSlotAndPropagates() {
    val registry = WebInstanceRegistry { _, _ -> error("initializer failed") }
    val handle = registry.reserve(scriptId = 1)

    val failure =
      assertFailsWith<IllegalStateException> { registry.construct(handle, ::passThrough) }

    assertEquals("initializer failed", failure.message)
    assertFalse(registry.isLiveOrConstructing(handle))
    // The slot is reusable exactly once: a second reserve takes it back under a new generation.
    val next = registry.reserve(scriptId = 2)
    assertTrue(next != handle)
    val other = registry.reserve(scriptId = 3)
    assertTrue(other != next)
  }

  @Test
  fun constructOfAnUnreservedHandleThrows() {
    val registry = registry()

    val failure =
      assertFailsWith<IllegalStateException> { registry.construct(0x10001, ::passThrough) }
    assertTrue(failure.message!!.contains("was not reserved"), failure.message)
  }

  @Test
  fun constructOfAnAlreadyConstructedHandleThrowsAndKeepsTheScript() {
    val registry = registry()
    val handle = registry.reserve(scriptId = 1)
    registry.construct(handle, ::passThrough)

    val failure =
      assertFailsWith<IllegalStateException> { registry.construct(handle, ::passThrough) }

    assertTrue(failure.message!!.contains("already constructed"), failure.message)
    assertTrue(registry.isLive(handle))
    assertNotNull(registry.require(handle))
  }

  @Test
  fun freeReleasesOnceAndADoubleFreeDoesNotPushTheSlotTwice() {
    val registry = registry()
    val handle = registry.reserve(scriptId = 1)
    registry.construct(handle, ::passThrough)

    assertTrue(registry.free(handle))
    assertFalse(registry.free(handle))

    // A slot pushed twice would hand the same slot to two live scripts.
    val first = registry.reserve(scriptId = 1)
    val second = registry.reserve(scriptId = 1)
    assertTrue((first and 0xffff) != (second and 0xffff))
  }
}
