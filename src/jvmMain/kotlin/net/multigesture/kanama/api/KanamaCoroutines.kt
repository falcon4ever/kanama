package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext
import net.multigesture.kanama.binding.runtime.ScriptErrors

object KanamaDispatchers {
    val Main: CoroutineDispatcher = object : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            MainThread.post { block.run() }
        }
    }

    val Default: CoroutineDispatcher = Dispatchers.Default
}

/**
 * The coroutine scope of a script (see [KanamaCoroutineOwner]); cancelled when the script instance
 * is freed.
 *
 * An exception that escapes a coroutine launched here is reported as a Godot script error with the
 * game's file and line, like an exception from a script method (task 131 item 10); before, it went
 * to the default coroutine handler, which printed it to stderr only. A [SupervisorJob] keeps the
 * failure to the one coroutine. A [CoroutineExceptionHandler] in a `launch` context replaces this
 * one, as usual.
 */
class KanamaScope(
    private val job: Job = SupervisorJob(),
    dispatcher: CoroutineDispatcher = KanamaDispatchers.Main,
) : CoroutineScope, AutoCloseable {
    override val coroutineContext: CoroutineContext = job + dispatcher + scriptErrorHandler

    fun cancel() {
        coroutineContext.cancel()
    }

    override fun close() {
        cancel()
    }
}

interface KanamaCoroutineOwner {
    val kanamaScope: KanamaScope
}

/**
 * Routes an exception that escaped a [KanamaScope] coroutine to [ScriptErrors] (task 131 item 10).
 * Cancellation never reaches a handler, so every call here is a real failure. Internal so the JVM
 * tests can exercise it.
 */
internal val scriptErrorHandler: CoroutineExceptionHandler =
    CoroutineExceptionHandler { context, throwable ->
        val name = context[kotlinx.coroutines.CoroutineName]?.name
        val where = if (name.isNullOrEmpty()) "KanamaScope coroutine" else "KanamaScope coroutine $name"
        ScriptErrors.report(throwable, where)
        runCatching {
            System.err.println(
                "[kanama:kt] coroutine failed ($where): ${throwable.javaClass.name}: ${throwable.message}"
            )
            throwable.printStackTrace(System.err)
        }
    }
