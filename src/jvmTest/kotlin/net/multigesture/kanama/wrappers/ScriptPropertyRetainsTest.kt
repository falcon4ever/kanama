package net.multigesture.kanama.wrappers

import java.lang.foreign.MemorySegment
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import net.multigesture.kanama.binding.ScriptPropertyRetains

/**
 * Task 132: the references a script-property setter takes are owned per (owner, property) by the
 * runtime, so `free` releases them even after the GC collected the Kotlin script object, and a
 * re-set releases what the property held before. The engine release is replaced by a recorder.
 */
class ScriptPropertyRetainsTest {
  private val released = mutableListOf<Long>()
  private val ownerA = MemorySegment.ofAddress(0xA000)
  private val ownerB = MemorySegment.ofAddress(0xB000)

  private fun handle(address: Long) = MemorySegment.ofAddress(address)

  // A setter's retaining read: one +1 per handle.
  private fun setter(owner: MemorySegment, property: String, vararg handles: Long) =
    ScriptPropertyRetains.capture(owner, property) {
      handles.forEach { ScriptPropertyRetains.recordHandle(handle(it)) }
      handles.size
    }

  @BeforeTest
  fun install() {
    ScriptPropertyRetains.releaseOverride = { released += (it as MemorySegment).address() }
  }

  @AfterTest
  fun uninstall() {
    ScriptPropertyRetains.releaseOwner(ownerA.address())
    ScriptPropertyRetains.releaseOwner(ownerB.address())
    ScriptPropertyRetains.releaseOverride = null
  }

  @Test
  fun freeReleasesEverySetterReference() {
    setter(ownerA, "items", 1, 2, 3)
    setter(ownerA, "item", 4)
    assertEquals(4, ScriptPropertyRetains.countFor(ownerA.address()))
    assertEquals(emptyList(), released)
    ScriptPropertyRetains.releaseOwner(ownerA.address())
    assertEquals(listOf(1L, 2L, 3L, 4L), released.sorted())
    assertEquals(0, ScriptPropertyRetains.countFor(ownerA.address()))
    ScriptPropertyRetains.releaseOwner(ownerA.address())
    assertEquals(4, released.size, "a second free releases nothing")
  }

  @Test
  fun reSettingReleasesOnlyWhatThePropertyHeldBefore() {
    setter(ownerA, "items", 1, 2)
    setter(ownerA, "other", 9)
    setter(ownerA, "items", 2, 3)
    // The new +1s were taken before the old ones are dropped: re-setting the same value is safe.
    assertEquals(listOf(1L, 2L), released.sorted())
    setter(ownerA, "items")
    assertEquals(listOf(1L, 2L, 2L, 3L), released.sorted())
    assertEquals(1, ScriptPropertyRetains.countFor(ownerA.address()), "`other` is untouched")
  }

  @Test
  fun ownersAreIndependent() {
    setter(ownerA, "items", 1)
    setter(ownerB, "items", 2)
    ScriptPropertyRetains.releaseOwner(ownerA.address())
    assertEquals(listOf(1L), released)
    assertEquals(1, ScriptPropertyRetains.countFor(ownerB.address()))
  }

  @Test
  fun nestedSettersRecordIntoTheirOwnProperty() {
    // A read that resolves a script object may run another owner's setter (a refill).
    ScriptPropertyRetains.capture(ownerA, "items") {
      ScriptPropertyRetains.recordHandle(handle(1))
      setter(ownerB, "items", 2)
      ScriptPropertyRetains.recordHandle(handle(3))
    }
    assertEquals(2, ScriptPropertyRetains.countFor(ownerA.address()))
    assertEquals(1, ScriptPropertyRetains.countFor(ownerB.address()))
    ScriptPropertyRetains.releaseOwner(ownerA.address())
    assertEquals(listOf(1L, 3L), released.sorted())
  }

  @Test
  fun aThrowingReadReleasesWhatItTookAndKeepsThePreviousEntry() {
    setter(ownerA, "items", 1)
    assertFailsWith<IllegalStateException> {
      ScriptPropertyRetains.capture(ownerA, "items") {
        ScriptPropertyRetains.recordHandle(handle(2))
        error("decode failed")
      }
    }
    assertEquals(listOf(2L), released)
    assertEquals(1, ScriptPropertyRetains.countFor(ownerA.address()))
  }

  @Test
  fun aReadOutsideASetterRecordsNothing() {
    ScriptPropertyRetains.recordHandle(handle(7))
    assertEquals(0, ScriptPropertyRetains.countFor(ownerA.address()))
  }
}
