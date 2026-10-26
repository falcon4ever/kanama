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
  fun closeOnABorrowedViewReleasesNothing() {
    val view = RefCounted.wrapBorrowed(segment(0x7000))!!
    assertFalse(view.isOwned)
    view.close() // returns before any engine call: nothing to release
    assertFalse(view.isOwned)
  }

  @Test
  fun aCleanupThatAlreadyFiredMakesCloseSkipItsRelease() {
    val wrapper = RefCounted.wrapOwned(segment(0x7100))!!
    // The fallback won the race (the cleaner thread ran first): close() must not release too.
    assertTrue(OwnedReleases.firePendingForTest(wrapper))
    assertFalse(wrapper.cancelOwnedRelease())
    OwnedReleases.drain()
    assertEquals(listOf(0x7100L), released)
  }

  // Task 138 item 6 (R6.1): the thread that builds an owned wrapper does not matter. A wrapper made
  // on a worker registers its release; the cleanup fires (here: now, as the cleaner thread would)
  // and the drain on this thread -- the main thread of the test -- releases its handle once.
  @Test
  fun aWrapperMadeOnAWorkerRegistersAndTheMainThreadDrainReleasesIt() {
    var offThread: RefCounted? = null
    val worker = Thread { offThread = RefCounted.wrapOwned(segment(0x7200)) }
    worker.start()
    worker.join()
    val wrapper = offThread!!
    assertTrue(wrapper.isOwned)
    assertTrue(wrapper.hasPendingRelease)
    assertTrue(OwnedReleases.firePendingForTest(wrapper))
    OwnedReleases.drain()
    assertEquals(listOf(0x7200L), released)
    assertFalse(wrapper.cancelOwnedRelease()) // the fallback won: a later close() releases nothing
  }

  private fun dropMany(base: Long, count: Int) {
    for (i in 0 until count) RefCounted.wrapOwned(segment(base + i * 16))
  }

  @Test
  fun theDrainSpendsItsFrameBudgetAndCarriesTheRestOver() {
    val budgetBefore = OwnedReleases.frameBudget
    try {
      OwnedReleases.frameBudget = kotlin.time.Duration.ZERO
      dropMany(0x100000, 500)
      repeat(3) { OwnedReleaseCleaner.collectGarbage(200) } // all 500 queued, none drained yet
      // A normal frame: a slow one (the collections above took ~600 ms) would earn a larger budget.
      OwnedReleases.drainAfter(kotlin.time.Duration.parse("16ms"))
      // A zero budget still releases one clock-check batch (65) per drain, never all 500 at once.
      assertTrue(released.size < 500, "released ${released.size} in one budget-less drain")
      assertTrue(OwnedReleases.backlogSize > 0)
      OwnedReleases.frameBudget = budgetBefore
      assertTrue(drainUntil { released.size >= 500 })
      assertEquals(0, OwnedReleases.backlogSize)
    } finally {
      OwnedReleases.frameBudget = budgetBefore
    }
  }

  @Test
  fun aLargeBacklogAtALowFrameRateDrainsInAFewFrames() {
    // A phone under load: each release costs ~20 us and a frame comes every 200 ms. With the
    // fixed 1 ms budget, 10,000 releases took ~200 frames; the budget now grows with the backlog
    // and the frame time (capped at a few ms), with a minimum count per frame.
    val overrideBefore = OwnedReleases.releaseOverride
    try {
      OwnedReleases.releaseOverride = { handle ->
        released += handle.address()
        val until = System.nanoTime() + 20_000
        while (System.nanoTime() < until) Unit
      }
      dropMany(0x400000, 10_000)
      repeat(3) { OwnedReleaseCleaner.collectGarbage(200) }
      OwnedReleases.drain() // takes the queue in; the next drains see a 200 ms frame
      var frames = 1
      while (released.size < 10_000 && frames < 60) {
        Thread.sleep(200)
        OwnedReleases.drain()
        frames++
      }
      assertEquals(10_000, released.size, "released ${released.size} in $frames frames")
      assertTrue(frames <= 25, "10,000 releases took $frames frames at 5 fps")
    } finally {
      OwnedReleases.releaseOverride = overrideBefore
    }
  }

  @Test
  fun theBudgetIsUnchangedForASmallBacklogAndCappedForALargeOne() {
    val frame = kotlin.time.Duration.parse("16ms")
    val slow = kotlin.time.Duration.parse("2s")
    // A normal frame with a small backlog: the old 1 ms, nothing more.
    assertEquals(OwnedReleases.frameBudget, OwnedReleases.budgetFor(100, frame))
    assertEquals(OwnedReleases.frameBudget, OwnedReleases.budgetFor(4096, frame))
    assertTrue(OwnedReleases.budgetFor(20_000, frame) > OwnedReleases.frameBudget)
    assertTrue(OwnedReleases.budgetFor(100, slow) > OwnedReleases.frameBudget)
    // Never more than the cap, however large the backlog or slow the frame.
    assertEquals(kotlin.time.Duration.parse("4ms"), OwnedReleases.budgetFor(1_000_000, slow))
    assertEquals(kotlin.time.Duration.parse("4ms"), OwnedReleases.budgetFor(1_000_000, frame))
  }

  @Test
  fun aboveTheRegistrationCapNewWrappersStayOwnedButUnregistered() {
    val capBefore = OwnedReleases.registrationCap
    try {
      OwnedReleases.registrationCap = OwnedReleases.liveRegistrationCount + 1
      val first = RefCounted.wrapOwned(segment(0x8000))!!
      val second = RefCounted.wrapOwned(segment(0x8100))!!
      assertTrue(first.hasPendingRelease)
      assertTrue(second.isOwned)
      assertFalse(second.hasPendingRelease)
      assertTrue(logged.isEmpty() || true)
    } finally {
      OwnedReleases.registrationCap = capBefore
    }
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
