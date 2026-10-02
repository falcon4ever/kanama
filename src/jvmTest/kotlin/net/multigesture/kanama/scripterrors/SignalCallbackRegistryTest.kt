package net.multigesture.kanama.scripterrors

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 131 item 3 (F9): a lambda connection's closure is released when its receiver is freed and
 * when a one-shot connection fires, not only by `SignalConnection.close()`. The registry is a
 * process-wide singleton, so every assertion is a delta against the size before the test.
 */
class SignalCallbackRegistryTest {
  private val registered = mutableListOf<Long>()

  private fun register(target: Long, oneShot: Boolean, callback: (List<Any?>) -> Unit): Long =
    SignalCallbackRegistry.register(target, oneShot, callback).also { registered += it }

  @AfterTest
  fun releaseLeftovers() {
    registered.forEach(SignalCallbackRegistry::unregister)
  }

  @Test
  fun freeingTheReceiverReleasesAllOfItsEntries() {
    val before = SignalCallbackRegistry.size
    var fired = 0
    val first = register(RECEIVER, false) { fired++ }
    register(RECEIVER, false) { fired++ }
    val other = register(OTHER_RECEIVER, false) { fired++ }
    assertEquals(before + 3, SignalCallbackRegistry.size)

    assertEquals(2, SignalCallbackRegistry.unregisterTarget(RECEIVER))

    assertEquals(before + 1, SignalCallbackRegistry.size)
    SignalCallbackRegistry.invoke(first, emptyList()) // released: a late dispatch is a no-op
    assertEquals(0, fired)
    SignalCallbackRegistry.invoke(other, emptyList()) // another receiver's entry survives
    assertEquals(1, fired)
    assertEquals(0, SignalCallbackRegistry.unregisterTarget(RECEIVER))
  }

  @Test
  fun aOneShotEntryIsReleasedWhenItFires() {
    val before = SignalCallbackRegistry.size
    val seen = mutableListOf<List<Any?>>()
    val id = register(RECEIVER, true) { seen += it }
    assertEquals(before + 1, SignalCallbackRegistry.size)

    SignalCallbackRegistry.invoke(id, listOf(7L))
    SignalCallbackRegistry.invoke(id, listOf(8L))

    assertEquals(listOf(listOf<Any?>(7L)), seen)
    assertEquals(before, SignalCallbackRegistry.size)
    assertEquals(0, SignalCallbackRegistry.unregisterTarget(RECEIVER)) // target index cleaned too
  }

  @Test
  fun aOneShotEntryThatThrowsIsStillReleased() {
    val before = SignalCallbackRegistry.size
    val id = register(RECEIVER, true) { error("callback failure") }

    assertFailsWith<IllegalStateException> { SignalCallbackRegistry.invoke(id, emptyList()) }

    assertEquals(before, SignalCallbackRegistry.size)
  }

  @Test
  fun aRegularEntryStaysUntilClosedAndCloseForgetsItsReceiver() {
    val before = SignalCallbackRegistry.size
    var fired = 0
    val id = register(RECEIVER, false) { fired++ }

    SignalCallbackRegistry.invoke(id, emptyList())
    SignalCallbackRegistry.invoke(id, emptyList())
    assertEquals(2, fired)
    assertEquals(before + 1, SignalCallbackRegistry.size)

    SignalCallbackRegistry.unregister(id) // SignalConnection.close()
    SignalCallbackRegistry.unregister(id) // idempotent
    assertEquals(before, SignalCallbackRegistry.size)
    assertEquals(0, SignalCallbackRegistry.unregisterTarget(RECEIVER))
  }

  private companion object {
    const val RECEIVER = 0x5151_0001L
    const val OTHER_RECEIVER = 0x5151_0002L
  }
}
