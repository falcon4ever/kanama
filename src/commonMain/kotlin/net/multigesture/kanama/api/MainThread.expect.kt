package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic

/**
 * Main-thread queue for safe Godot API handoff (task 117 P4′, D26).
 *
 * Background work enqueues actions here and Kanama runs them on the engine main thread, once per
 * engine frame. Desktop/Android implement it over concurrent queues pumped from
 * `ScriptLanguage._frame` (`src/jvmMain/.../api/MainThread.kt`); iOS runs [post] /
 * [runOnMainThread] inline (Kotlin/Native scripts already run on the engine main thread) and pumps
 * the frame queues from `KanamaIosRuntime.frame()` (`src/iosMain/.../api/MainThread.kt`).
 *
 * No default arguments here (D24): the Android lane skips `*.expect.kt`, so a default declared
 * only on this file would not exist there.
 *
 * `@JvmStatic` is on these expect members AND mirrored on both actuals — D6's fallback. On the
 * expect alone K2 compiles, but the JVM actual then gets no static method (javap shows instance
 * methods only, and the compiler warns "Annotation `@JvmStatic` is missing on actual
 * declaration"), which would silently drop desktop's existing static entry points.
 */
expect object MainThread {
    /** Enqueue [action] to run on the engine main thread. */
    @JvmStatic fun runOnMainThread(action: () -> Unit)

    /** Same as [runOnMainThread]. */
    @JvmStatic fun post(action: () -> Unit)

    /** Enqueue [action] to run after at least one engine frame pump. */
    @JvmStatic fun postNextFrame(action: () -> Unit)

    /** Enqueue [action] to run after at least [frames] engine frame pumps. */
    @JvmStatic fun postAfterFrames(frames: Int, action: () -> Unit)

    /** Resume on the next engine frame pump. */
    suspend fun awaitNextFrame()
}
