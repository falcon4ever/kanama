package net.multigesture.kanama.api

import java.util.concurrent.ConcurrentLinkedQueue
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Main-thread queue for safe Godot API handoff.
 *
 * Background work can enqueue actions here; Kanama pumps this queue once per
 * engine frame from ScriptLanguage._frame.
 */
object MainThread {

    private val tasks = ConcurrentLinkedQueue<() -> Unit>()
    private val nextFrameTasks = ConcurrentLinkedQueue<() -> Unit>()
    private val nextFrameContinuations = ConcurrentLinkedQueue<CancellableContinuation<Unit>>()

    @JvmStatic
    fun runOnMainThread(action: () -> Unit) {
        tasks.add(action)
    }

    @JvmStatic
    fun post(action: () -> Unit) {
        runOnMainThread(action)
    }

    /**
     * Enqueue an action to run after at least one ScriptLanguage._frame pump.
     */
    @JvmStatic
    fun postNextFrame(action: () -> Unit) {
        nextFrameTasks.add(action)
    }

    /**
     * Enqueue an action to run after at least [frames] ScriptLanguage._frame pumps.
     */
    @JvmStatic
    fun postAfterFrames(frames: Int, action: () -> Unit) {
        if (frames <= 0) {
            post(action)
            return
        }

        postNextFrame {
            postAfterFrames(frames - 1, action)
        }
    }

    /**
     * Resume on the next frame pumped by Kanama's ScriptLanguage._frame callback.
     */
    suspend fun awaitNextFrame() {
        suspendCancellableCoroutine { continuation ->
            nextFrameContinuations.add(continuation)
            continuation.invokeOnCancellation {
                nextFrameContinuations.remove(continuation)
            }
        }
    }

    internal fun pump(maxTasks: Int = 2048) {
        while (true) {
            val task = nextFrameTasks.poll() ?: break
            tasks.add(task)
        }

        var count = 0
        while (count < maxTasks) {
            val task = tasks.poll() ?: break
            try {
                task()
            } catch (t: Throwable) {
                System.err.println("[kanama:kt] MainThread task failed: ${t::class.qualifiedName}: ${t.message}")
            }
            count += 1
        }

        while (true) {
            val continuation = nextFrameContinuations.poll() ?: break
            if (!continuation.isActive) continue
            try {
                continuation.resume(Unit)
            } catch (t: Throwable) {
                System.err.println("[kanama:kt] MainThread continuation resume failed: ${t.message}")
            }
        }
    }
}
