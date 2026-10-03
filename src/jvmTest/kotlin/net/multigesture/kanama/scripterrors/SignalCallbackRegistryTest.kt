package net.multigesture.kanama.scripterrors

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_INT
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import net.multigesture.kanama.binding.runtime.ScriptErrorReport
import net.multigesture.kanama.binding.runtime.ScriptErrors
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 131 item 3 (F9): a lambda connection's closure lives until Godot drops the connection's
 * custom Callable. These drive the Callable's two upcall targets the way Godot does -- `call_func`
 * ([SignalCallables.call]) and `free_func` ([SignalCallables.free]) -- without an engine. WHY Godot
 * drops a Callable (receiver freed, emitter freed, one-shot fired, disconnected) is engine
 * behaviour, proved per case by `signal_leak_smoke.tscn` in the runtime smoke; every case ends in
 * the same `free_func`. The registry is process-wide, so assertions are deltas.
 */
class SignalCallbackRegistryTest {
  private val registered = mutableListOf<Long>()
  private val reports = mutableListOf<ScriptErrorReport>()

  private fun register(argumentCount: Int, callback: (List<Any?>) -> Unit): Long =
    SignalCallbackRegistry.register(argumentCount, callback).also { registered += it }

  @AfterTest
  fun cleanUp() {
    registered.forEach(SignalCallbackRegistry::unregister)
    ScriptErrors.sinkOverride = null
  }

  /** Calls [SignalCallables.call] like Godot's CallableCustomExtension::call; returns the error. */
  private fun godotCall(id: Long, argCount: Long = 0): Triple<Int, Int, Int> =
    Arena.ofConfined().use { arena ->
      val error = arena.allocate(12L, 4L)
      error.set(JAVA_INT, 0, 0x7777) // Godot does not initialise r_error: call_func must
      val ret = arena.allocate(24L, 8L)
      SignalCallables.call(MemorySegment.ofAddress(id), MemorySegment.NULL, argCount, ret, error)
      Triple(error.get(JAVA_INT, 0), error.get(JAVA_INT, 4), error.get(JAVA_INT, 8))
    }

  @Test
  fun theCallableFreeFuncReleasesTheClosure() {
    val before = SignalCallbackRegistry.size
    var fired = 0
    val id = register(0) { fired++ }
    assertEquals(before + 1, SignalCallbackRegistry.size)

    assertEquals(Triple(0, 0, 0), godotCall(id))
    assertEquals(1, fired)

    // Godot dropped the connection: receiver freed, emitter freed, one-shot fired, disconnected.
    SignalCallables.free(MemorySegment.ofAddress(id))

    assertEquals(before, SignalCallbackRegistry.size)
    assertEquals(Triple(0, 0, 0), godotCall(id)) // a late call is a harmless no-op
    assertEquals(1, fired)
  }

  @Test
  fun releaseIsIdempotentAcrossCloseAndFreeFunc() {
    val before = SignalCallbackRegistry.size
    val id = register(0) {}

    SignalCallbackRegistry.unregister(id) // SignalConnection.close()
    SignalCallables.free(MemorySegment.ofAddress(id)) // the original Callable's free_func
    SignalCallables.free(MemorySegment.ofAddress(id)) // the disconnect temporary's free_func

    assertEquals(before, SignalCallbackRegistry.size)
  }

  @Test
  fun tooFewEmittedArgumentsIsACallErrorNotACrash() {
    var fired = 0
    val id = register(2) { fired++ }

    // CALL_ERROR_TOO_FEW_ARGUMENTS = 4, expected = 2
    assertEquals(Triple(4, 0, 2), godotCall(id, argCount = 1))
    assertEquals(0, fired)
  }

  @Test
  fun aThrowingLambdaIsContainedReportedAndTheCallSucceeds() {
    ScriptErrors.sinkOverride = { reports += it }
    val id = register(0) { error("lambda failure") }

    assertEquals(Triple(0, 0, 0), godotCall(id)) // CALL_OK, as a GDScript lambda's runtime error

    assertEquals("java.lang.IllegalStateException: lambda failure", reports.single().message)
    assertEquals("SignalCallbackRegistryTest.kt", reports.single().file)
  }
}
