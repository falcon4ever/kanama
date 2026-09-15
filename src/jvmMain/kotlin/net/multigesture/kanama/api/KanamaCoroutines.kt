package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

object KanamaDispatchers {
    val Main: CoroutineDispatcher = object : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            MainThread.post { block.run() }
        }
    }

    val Default: CoroutineDispatcher = Dispatchers.Default
}

class KanamaScope(
    private val job: Job = SupervisorJob(),
    dispatcher: CoroutineDispatcher = KanamaDispatchers.Main,
) : CoroutineScope, AutoCloseable {
    override val coroutineContext: CoroutineContext = job + dispatcher

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
