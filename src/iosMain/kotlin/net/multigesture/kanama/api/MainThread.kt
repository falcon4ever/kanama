package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine

// KANAMA-IOS-HANDWRITTEN: [platform] MainThread.post/runOnMainThread run inline (Kotlin/Native scripts already run on the engine main thread); the frame queues are pumped by KanamaIosRuntime.frame(), not a JVM executor.
/**
 * Main-thread queue — the iOS actual of the common `expect object MainThread`
 * (`src/commonMain/.../api/MainThread.expect.kt`, task 117 P4′ D26). It lived inside
 * `IosGodotApi.kt` until the expect landed.
 *
 * [runOnMainThread] and [post] are the same enqueue, as on desktop (where `post` delegates to
 * `runOnMainThread`); on iOS that enqueue runs the action at once, because every Kotlin/Native
 * script call already happens on the engine main thread.
 */
actual object MainThread {
    // Continuations parked by [awaitNextFrame], resumed once per engine frame by
    // KanamaIosRuntime.frame() via [pumpNextFrame]. Accessed only on the engine main thread
    // (awaitNextFrame runs under Dispatchers.Main; frame() runs on the engine main thread).
    private val nextFrameContinuations = mutableListOf<CancellableContinuation<Unit>>()
    private val nextFrameTasks = mutableListOf<() -> Unit>()

    @JvmStatic
    actual fun runOnMainThread(action: () -> Unit) {
        action()
    }

    @JvmStatic
    actual fun post(action: () -> Unit) {
        runOnMainThread(action)
    }

    // Run [action] after at least one engine frame pump (mirrors desktop MainThread.postNextFrame).
    @JvmStatic
    actual fun postNextFrame(action: () -> Unit) {
        nextFrameTasks.add(action)
    }

    // Run [action] after at least [frames] engine frame pumps (mirrors desktop postAfterFrames).
    @JvmStatic
    actual fun postAfterFrames(frames: Int, action: () -> Unit) {
        if (frames <= 0) {
            post(action)
            return
        }
        postNextFrame { postAfterFrames(frames - 1, action) }
    }

    // Suspend until the next engine frame is pumped (mirrors desktop MainThread.awaitNextFrame).
    // Frame-based waiting is robust to device frame rate, unlike a wall-clock delay.
    actual suspend fun awaitNextFrame() {
        suspendCancellableCoroutine { continuation ->
            nextFrameContinuations.add(continuation)
            continuation.invokeOnCancellation { nextFrameContinuations.remove(continuation) }
        }
    }

    // Once per engine frame: run parked tasks, then resume parked continuations. Both are
    // snapshot-then-cleared so work re-queued by a resumed coroutine/task waits for the next frame
    // (matching desktop's one-step-per-frame semantics).
    internal fun pumpNextFrame() {
        if (nextFrameTasks.isNotEmpty()) {
            val tasks = nextFrameTasks.toList()
            nextFrameTasks.clear()
            for (task in tasks) task()
        }
        if (nextFrameContinuations.isEmpty()) return
        val pending = nextFrameContinuations.toList()
        nextFrameContinuations.clear()
        for (continuation in pending) {
            if (continuation.isActive) continuation.resume(Unit)
        }
    }
}
