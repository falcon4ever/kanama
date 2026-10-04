package net.multigesture.kanama.scripterrors

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import net.multigesture.kanama.api.KanamaScope
import net.multigesture.kanama.binding.runtime.ScriptErrorReport
import net.multigesture.kanama.binding.runtime.ScriptErrors

/**
 * Task 131 item 10: an exception that escapes a `KanamaScope` coroutine is reported like any other
 * script error -- the game file:line -- instead of only reaching the default coroutine handler
 * (stderr). The scope runs on `Dispatchers.Unconfined` here, so `launch` runs inline.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class KanamaScopeErrorsTest {
  private val reports = mutableListOf<ScriptErrorReport>()

  init {
    ScriptErrors.sinkOverride = { reports += it }
  }

  @AfterTest
  fun clearSink() {
    ScriptErrors.sinkOverride = null
  }

  @Test
  fun aFailedCoroutineIsAScriptErrorAtTheGameLine() {
    val scope = KanamaScope(SupervisorJob(), Dispatchers.Unconfined)
    val throwLine = Throwable().stackTrace[0].lineNumber + 1
    scope.launch { error("coroutine boom") }

    assertEquals(1, reports.size)
    val report = reports.single()
    assertEquals("java.lang.IllegalStateException: coroutine boom", report.message)
    assertEquals("KanamaScopeErrorsTest.kt", report.file)
    assertEquals(throwLine, report.line)
  }

  @Test
  fun theFailureStaysInItsCoroutineAndNamesIt() {
    val scope = KanamaScope(SupervisorJob(), Dispatchers.Unconfined)
    scope.launch(CoroutineName("spawnWave")) { throw IllegalArgumentException() }
    var sibling = false
    scope.launch { sibling = true }

    assertTrue(sibling, "a SupervisorJob keeps the scope alive for the next coroutine")
    assertEquals(1, reports.size)
    assertEquals("java.lang.IllegalArgumentException", reports.single().message)
  }

  @Test
  fun cancellationIsNotAnError() {
    val scope = KanamaScope(SupervisorJob(), Dispatchers.Unconfined)
    scope.launch { kotlinx.coroutines.awaitCancellation() }
    scope.cancel()

    assertEquals(emptyList(), reports)
  }
}
