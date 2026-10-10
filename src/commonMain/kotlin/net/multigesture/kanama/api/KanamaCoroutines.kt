package net.multigesture.kanama.api

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

/** The dispatchers a script's coroutines run on: [Main] is the engine main thread. */
object KanamaDispatchers {
  val Main: CoroutineDispatcher = ScriptRuntime.mainDispatcher()

  val Default: CoroutineDispatcher = Dispatchers.Default
}

/**
 * The coroutine scope behind `KanamaScript.scriptScope` (task 133): main-thread dispatch and a
 * [SupervisorJob]; the free path cancels it. Internal since task 133 removed
 * `KanamaCoroutineOwner`; a script launches with `KanamaScript.launch`. Common since task 129 D:
 * the dispatcher and the error report are the platform's (`ScriptRuntime`).
 *
 * An exception that escapes a coroutine launched here is reported as a Godot script error with the
 * game's file and line, like an exception from a script method (task 131 item 10). A
 * [SupervisorJob] keeps the failure to the one coroutine. A [CoroutineExceptionHandler] in a
 * `launch` context replaces this one, as usual. Cancellation never reaches a handler, so every
 * report is a real failure.
 */
internal class KanamaScope(
  private val job: Job = SupervisorJob(),
  dispatcher: CoroutineDispatcher = KanamaDispatchers.Main,
) : CoroutineScope, AutoCloseable {
  override val coroutineContext: CoroutineContext =
    job +
      dispatcher +
      CoroutineExceptionHandler { context, throwable ->
        val name = context[CoroutineName]?.name
        ScriptRuntime.reportCoroutineFailure(
          throwable,
          if (name.isNullOrEmpty()) "KanamaScope coroutine" else "KanamaScope coroutine $name",
        )
      }

  fun cancel() {
    job.cancel()
  }

  override fun close() {
    cancel()
  }
}
