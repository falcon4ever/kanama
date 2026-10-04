package net.multigesture.kanama.wrappers

import java.lang.foreign.MemorySegment
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.binding.runtime.ObjectRuntime
import net.multigesture.kanama.binding.runtime.OwnedReleaseCleaner
import net.multigesture.kanama.binding.runtime.OwnedReleases

/**
 * Task 132 without an engine: ownership is a constructor fact (D1), an owned wrapper dropped
 * without `close()` is released on the main-thread drain after the GC (D2), `close()`'s cancel
 * means it is never released twice (D3), and the D5 log names each creation site once. The real
 * `java.lang.ref.Cleaner` runs; only the release itself and the liveness question go to test seams.
 * The engine-backed versions are the runtime smoke's `owned_release_smoke.tscn` (10,000 dropped
 * Resources, red and green) and the iOS self-test row.
 */
class OwnedReleasesTest {
  private val released = mutableListOf<Long>()
  private val logged = mutableListOf<String>()
  private val freed = mutableSetOf<Long>()

  @BeforeTest
  fun installSeams() {
    ObjectRuntime.instanceIdOverride = { it.address() + 1000 }
    ObjectRuntime.isLiveOverride = { segment, _ -> segment.address() !in freed }
    OwnedReleases.releaseOverride = { released += it.address() }
    OwnedReleases.logOverride = { logged += it }
    OwnedReleases.configure("", logSetting = false)
    drainUntil { false } // nothing left over from another test
    released.clear()
  }

  @AfterTest
  fun clearSeams() {
    ObjectRuntime.instanceIdOverride = null
    ObjectRuntime.isLiveOverride = null
    OwnedReleases.releaseOverride = null
    OwnedReleases.logOverride = null
    OwnedReleases.configure("", logSetting = false)
  }

  private fun segment(address: Long) = MemorySegment.ofAddress(address)

  // Its own function, so no stack slot of the test keeps the wrapper reachable.
  private fun dropOwned(address: Long) {
    RefCounted.wrapOwned(segment(address))
  }

  private fun dropOwnedTwiceFromOneSite(first: Long, second: Long) {
    for (address in listOf(first, second)) RefCounted.wrapOwned(segment(address))
  }

  private fun dropCancelled(address: Long) {
    val wrapper = RefCounted.wrapOwned(segment(address))!!
    assertTrue(wrapper.hasPendingRelease)
    wrapper.cancelOwnedRelease() // what close() does before it unreferences
    assertFalse(wrapper.hasPendingRelease)
  }

  private fun dropBorrowed(address: Long) {
    RefCounted.wrapBorrowed(segment(address))
  }

  /** GC + drain until [done] or ~5 s pass; returns whether [done] became true. */
  private fun drainUntil(done: () -> Boolean): Boolean {
    repeat(50) {
      OwnedReleaseCleaner.collectGarbage(100)
      OwnedReleases.drain()
      if (done()) return true
    }
    return false
  }

  @Test
  fun ownershipIsAConstructorFact() {
    assertTrue(RefCounted.wrapOwned(segment(0x1000))!!.hasPendingRelease)
    assertFalse(RefCounted.wrapBorrowed(segment(0x1000))!!.hasPendingRelease)
    assertFalse(
      RefCounted.fromHandle(net.multigesture.kanama.api.GodotHandle(segment(0x1000)))!!
        .hasPendingRelease
    )
    assertEquals(null, RefCounted.wrapOwned(MemorySegment.NULL))
  }

  @Test
  fun anOwnedWrapperDroppedWithoutCloseIsReleasedOnTheDrain() {
    dropOwned(0x2000)
    assertTrue(drainUntil { 0x2000L in released }, "the GC fallback never released 0x2000")
    assertEquals(listOf(0x2000L), released)
  }

  @Test
  fun aClosedWrapperIsNeverReleasedAgainAndABorrowedOneNever() {
    dropCancelled(0x3000)
    dropBorrowed(0x3100)
    dropOwned(0x3200) // the control: proves the GC and the cleaner did run
    assertTrue(drainUntil { 0x3200L in released })
    drainUntil { false }
    assertEquals(listOf(0x3200L), released)
  }

  @Test
  fun aReleaseOfAnObjectThatIsAlreadyGoneIsSkipped() {
    freed += 0x4000
    dropOwned(0x4000)
    dropOwned(0x4100)
    assertTrue(drainUntil { 0x4100L in released })
    drainUntil { false }
    assertEquals(listOf(0x4100L), released)
  }

  @Test
  fun theFallbackCanBeTurnedOffForMeasurement() {
    assertEquals("off (KANAMA_GC_RELEASES=0)", OwnedReleases.configure("0", logSetting = false))
    assertFalse(RefCounted.wrapOwned(segment(0x5000))!!.hasPendingRelease)
    assertEquals(
      "on (java.lang.ref.Cleaner, logging GC releases)",
      OwnedReleases.configure("", logSetting = true),
    )
    assertTrue(RefCounted.wrapOwned(segment(0x5000))!!.hasPendingRelease)
  }

  @Test
  fun eachCreationSiteIsLoggedOnce() {
    OwnedReleases.configure("", logSetting = true)
    dropOwnedTwiceFromOneSite(0x6000, 0x6100)
    assertTrue(drainUntil { released.containsAll(listOf(0x6000L, 0x6100L)) })
    val lines = logged.filter { it.startsWith("released by GC: ") }
    assertEquals(1, lines.size, "one line per creation site: $lines")
    assertTrue(
      Regex("""released by GC: RefCounted \(created at OwnedReleasesTest\.kt:\d+\)""")
        .matches(lines.single()),
      lines.single(),
    )
  }
}
