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
      SignalCallbackRegistry.register(3, {}, null) { reader ->
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
      // The property is the steady state: once C2 has compiled the path and escape analysis has
      // removed the segment, a call allocates 0 B. When C2 gets there depends on the machine (a
      // loaded CI runner was still above 1 B/call after a single 300k-call warmup), so measure up
      // to
      // ROUNDS rounds, each after its own warmup, and pass on the first one below the bound. A
      // reader that allocates on every call never gets there, however long it runs.
      val rounds = mutableListOf<Double>()
      while (rounds.size < ROUNDS) {
        calls(WARMUP) // let C2 compile the path, so escape analysis has run
        val before = threads.currentThreadAllocatedBytes
        calls(MEASURED)
        val perCall = (threads.currentThreadAllocatedBytes - before).toDouble() / MEASURED
        rounds += perCall
        println(
          "[signal-arg-reader] round ${rounds.size}: ${"%.3f".format(perCall)} B/call over $MEASURED calls"
        )
        if (perCall < 1.0) break
      }
      assertTrue(
        rounds.last() < 1.0,
        "signal lambda call allocates in every round (task 134 D4: 0 B/call once compiled): " +
          rounds.joinToString(", ") { "%.3f".format(it) } +
          " B/call over $ROUNDS rounds of $MEASURED calls",
      )
    }
  }

  private companion object {
    const val ROUNDS = 5
    const val WARMUP = 300_000
    const val MEASURED = 1_000_000
  }
}
