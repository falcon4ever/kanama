package net.multigesture.kanama.scriptaccess

import java.lang.foreign.MemorySegment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.awaitCancellation
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread

/**
 * Task 133 item 5 (F10): the coroutine scope every `KanamaScript` carries. It runs on the
 * main-thread pump, survives `cancelCoroutines()`, and ends on the free path (`disposeScriptScope`,
 * called by `ScriptBridge.siFree` and the iOS runtime). `wait(seconds)` needs a running `SceneTree`
 * and is proved by the runtime smoke.
 */
class KanamaScriptScopeTest {
  // No engine call is made: Self is a plain String, and the handle is never dereferenced.
  private class Probe :
    KanamaScript<String>(GodotHandle(MemorySegment.ofAddress(0x1000)), { "self" })

  @Test
  fun launchRunsOnTheMainThreadPump() {
    val script = Probe()
    var ran = false
    script.launch { ran = true }
    assertFalse(ran, "launch dispatches to the main-thread queue")
    MainThread.pump()
    assertTrue(ran)
  }

  @Test
  fun nextFrameResumesOnTheFollowingPump() {
    val script = Probe()
    val steps = mutableListOf<String>()
    script.launch {
      steps += "before"
      script.nextFrame()
      steps += "after"
    }
    MainThread.pump() // starts the coroutine; it parks on the next frame
    assertEquals(listOf("before"), steps)
    MainThread.pump() // the frame resumes it (dispatched to the queue)
    MainThread.pump()
    assertEquals(listOf("before", "after"), steps)
  }

  @Test
  fun theFreePathCancelsEveryCoroutineAndLaterLaunchesDoNotRun() {
    val script = Probe()
    val parked = script.launch { awaitCancellation() }
    MainThread.pump()
    assertTrue(parked.isActive)

    script.disposeScriptScope()
    assertTrue(parked.isCancelled)

    var ran = false
    val late = script.launch { ran = true }
    MainThread.pump()
    assertFalse(ran, "a coroutine launched after the free path must not run")
    assertTrue(late.isCancelled)
  }

  @Test
  fun cancelCoroutinesKeepsTheScopeUsable() {
    val script = Probe()
    val parked = script.launch { awaitCancellation() }
    MainThread.pump()
    script.cancelCoroutines()
    assertTrue(parked.isCancelled)

    var ran = false
    script.launch { ran = true }
    MainThread.pump()
    assertTrue(ran, "launch after cancelCoroutines() runs")
  }

  @Test
  fun scopeIsCreatedOnFirstUseAndKept() {
    val script = Probe()
    assertTrue(script.scriptScope === script.scriptScope)
  }
}
