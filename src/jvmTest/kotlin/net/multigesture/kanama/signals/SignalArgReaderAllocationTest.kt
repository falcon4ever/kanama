package net.multigesture.kanama.signals

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.management.ManagementFactory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.JvmSignalArgReader
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * A signal lambda's `call_func` ([SignalCallables.call]) reads Godot's `const Variant **` array in
 * place and allocates nothing per call (task 134 D4). Task 138 item 21 moved the array reads off
 * absolute addresses (Android heap pointers are tagged and negative); the segment that replaced
 * them must stay local so the JIT removes it -- stored in a field it cost 40 bytes per call.
 */
class SignalArgReaderAllocationTest {
  private val registered = mutableListOf<Long>()

  @AfterTest
  fun cleanUp() {
    registered.forEach(SignalCallbackRegistry::unregister)
  }

  @Test
  fun aSignalLambdaCallReadsItsArgumentsWithoutAllocating() {
    val threads = ManagementFactory.getThreadMXBean() as com.sun.management.ThreadMXBean
    var sum = 0L
    val id =
      SignalCallbackRegistry.register(3, {}) { reader ->
          val jvm = reader as JvmSignalArgReader
          sum += jvm.variant(0) + jvm.variant(1) + jvm.variant(2)
        }
        .also { registered += it }
    Arena.ofConfined().use { arena ->
      // The Variant pointers are only read, never dereferenced: no engine needed.
      val args = arena.allocate(3 * 8L, 8L)
      for (i in 0 until 3) args.set(JAVA_LONG, i * 8L, 0x1000L * (i + 1))
      val error = arena.allocate(12L, 4L)
      val ret = arena.allocate(24L, 8L)
      val userdata = MemorySegment.ofAddress(id)
      fun calls(n: Int) {
        repeat(n) { SignalCallables.call(userdata, args, 3, ret, error) }
      }

      calls(1)
      assertEquals(0x6000L, sum, "the three argument pointers, read relative to the array")
      calls(WARMUP) // let C2 compile the path, so escape analysis has run
      val before = threads.currentThreadAllocatedBytes
      calls(MEASURED)
      val perCall = (threads.currentThreadAllocatedBytes - before).toDouble() / MEASURED
      println("[signal-arg-reader] ${"%.3f".format(perCall)} B/call over $MEASURED calls")
      assertTrue(perCall < 1.0, "signal lambda call allocates $perCall B/call (task 134 D4: 0)")
    }
  }

  private companion object {
    const val WARMUP = 300_000
    const val MEASURED = 1_000_000
  }
}
