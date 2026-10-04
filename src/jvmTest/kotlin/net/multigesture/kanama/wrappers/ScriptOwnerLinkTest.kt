package net.multigesture.kanama.wrappers

import java.lang.foreign.MemorySegment
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.KanamaScriptInstance
import net.multigesture.kanama.binding.ScriptOwnerLink

/**
 * Task 132 round 2: the owner link's strong/weak switch under concurrent refcount callbacks. Godot
 * calls refcount_incremented / refcount_decremented from whichever thread takes or drops a
 * reference; each callback re-reads the count under the link's lock, so once the threads are done
 * the link is WEAK exactly when only the script instance's own +1 is left (count 1).
 */
class ScriptOwnerLinkTest {
  // What a KanamaScript's anchor does in the runtime: keeps the instance reachable while the test
  // holds it, so the weak link cannot lose it mid-test.
  private val anchors = mutableListOf<KanamaScriptInstance>()

  private fun link(kotlinObject: Any, anchor: Boolean = true): ScriptOwnerLink {
    val owner = MemorySegment.ofAddress(0x9000)
    val si = KanamaScriptInstance(kotlinObject = kotlinObject, ownerObject = owner)
    if (anchor) anchors += si
    return ScriptOwnerLink(si, owner, -1L, null, anchored = true).also {
      it.markOwnerRefHeldForTest()
    }
  }

  private fun hammer(link: ScriptOwnerLink, count: AtomicInteger, extraIncrements: Int) {
    val barrier = CyclicBarrier(2)
    val workers =
      (0 until 2).map { index ->
        thread {
          barrier.await()
          repeat(200_000) {
            count.incrementAndGet()
            link.onIncremented { count.get() }
            count.decrementAndGet()
            link.onDecremented { count.get() }
          }
          if (index == 0) {
            repeat(extraIncrements) {
              count.incrementAndGet()
              link.onIncremented { count.get() }
            }
          }
        }
      }
    workers.forEach { it.join() }
  }

  @Test
  fun twoThreadsLeaveTheLinkWeakAtCountOne() {
    val kotlinObject = Any()
    val link = link(kotlinObject)
    val count = AtomicInteger(1) // the instance's own +1
    hammer(link, count, extraIncrements = 0)
    assertEquals(1, count.get())
    assertTrue(link.isWeak, "count 1 must leave the link weak")
    assertTrue(link.instance() != null) // kotlinObject is still reachable here
    assertEquals(kotlinObject, link.instance()!!.kotlinObject)
  }

  @Test
  fun twoThreadsLeaveTheLinkStrongAboveOne() {
    val link = link(Any())
    val count = AtomicInteger(1)
    hammer(link, count, extraIncrements = 1)
    assertEquals(2, count.get())
    assertFalse(link.isWeak, "count 2 must leave the link strong")
  }

  @Test
  fun aCollectedInstanceAsksForARebuildWhenStrongIsDue() {
    val link = link(Any(), anchor = false)
    link.onDecremented { 1 } // weak: only the script object would keep the instance now
    assertTrue(link.isWeak)
    // The instance's KanamaScriptInstance is only weakly held; drop it.
    repeat(20) {
      System.gc()
      if (link.instance() == null) return@repeat
      Thread.sleep(10)
    }
    if (link.instance() == null) {
      assertFalse(link.onIncremented { 2 }, "a collected instance must be rebuilt")
    }
  }
}
