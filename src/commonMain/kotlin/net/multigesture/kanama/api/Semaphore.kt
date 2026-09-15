package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A synchronization mechanism used to control access to a shared resource by `Thread`s.
 *
 * Generated from Godot docs: Semaphore
 */
class Semaphore(handle: GodotHandle) : RefCounted(handle) {
    fun waitBlocking() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(waitBlockingBind, segment)
    }

    /**
     * Like `wait`, but won't block, so if the value is zero, fails immediately and returns `false`. If
     * non-zero, it returns `true` to report success.
     *
     * Generated from Godot docs: Semaphore.try_wait
     */
    fun tryWait(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(tryWaitBind, segment)
    }

    /**
     * Lowers the `Semaphore`, allowing one thread in, or more if `count` is specified.
     *
     * Generated from Godot docs: Semaphore.post
     */
    fun post(count: Int = 1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(postBind, segment, count)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Semaphore? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Semaphore? =
            if (handle.address() == 0L) null else Semaphore(GodotHandle(handle))

        private const val WAIT_HASH = 3218959716L
        private val waitBlockingBind by lazy {
            ObjectCalls.getMethodBind("Semaphore", "wait", WAIT_HASH)
        }

        private const val TRY_WAIT_HASH = 2240911060L
        private val tryWaitBind by lazy {
            ObjectCalls.getMethodBind("Semaphore", "try_wait", TRY_WAIT_HASH)
        }

        private const val POST_HASH = 1667783136L
        private val postBind by lazy {
            ObjectCalls.getMethodBind("Semaphore", "post", POST_HASH)
        }
    }
}
