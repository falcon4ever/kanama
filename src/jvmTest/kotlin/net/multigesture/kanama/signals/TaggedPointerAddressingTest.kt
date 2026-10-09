package net.multigesture.kanama.signals

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_LONG
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.SignalEmitFrame

/**
 * Task 138 item 21: on Android every native heap pointer is tagged (top byte `0xB4`, Android 11+
 * pointer tagging), so as a signed `long` it is negative. The typed `emit` wrote its `const Variant
 * **` table through a whole-address-space segment
 * (`MemorySegment.NULL.reinterpret(Long.MAX_VALUE)`) at the absolute address `base + 144 + i * 8`:
 * a negative offset, which every segment rejects. On the Pixel 7 every typed emit failed with the
 * exception below (squash failed only in the runs whose gameplay reached an emit: the player died
 * or squashed a mob). Desktop heap pointers are small and positive, so no desktop run could see it.
 * The host can replay the arithmetic with the device's own address.
 */
class TaggedPointerAddressingTest {
  /** The emit frame's base on the Pixel 7 (`squash-ab/main1.log`: offset - 144 for entry 0). */
  private val taggedBase = 0xB400007AC6D23850UL.toLong()

  @Test
  fun aTaggedHeapPointerIsNegative() {
    assertTrue(taggedBase < 0, "the 0xB4 tag sets the sign bit")
  }

  @Test
  fun theWholeAddressSpaceSegmentRejectsATaggedAddress() {
    // The pre-fix code path, reproduced: the same exception the device logged, before any memory
    // is touched (the bounds check fails), so the host can run it safely.
    val addressSpace = MemorySegment.NULL.reinterpret(Long.MAX_VALUE)
    val failure =
      assertFailsWith<IndexOutOfBoundsException> {
        addressSpace.set(JAVA_LONG, taggedBase + SignalEmitFrame.POINTERS_OFFSET, taggedBase)
      }
    assertTrue("-5476376619560847136" in failure.message.orEmpty(), failure.message)
  }

  @Test
  fun theEmitFramesPointerTableIsWrittenRelativeToTheFrame() {
    Arena.ofConfined().use { arena ->
      val block = arena.allocate(SignalEmitFrame.FRAME_SIZE, 16L)
      for (base in listOf(taggedBase, block.address())) {
        SignalEmitFrame.writePointerTable(block, base, count = 5)
        for (i in 0..5) {
          assertEquals(
            base + i * 24L,
            block.get(JAVA_LONG, SignalEmitFrame.POINTERS_OFFSET + i * 8L),
            "entry $i points at Variant slot $i",
          )
        }
      }
    }
  }
}
