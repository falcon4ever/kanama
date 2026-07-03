package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A synchronization mechanism used to control access to a shared resource by `Thread`s.
 *
 * Generated from Godot docs: Semaphore
 */
class Semaphore(handle: MemorySegment) : RefCounted(handle) {
    fun waitBlocking() {
        ObjectCalls.ptrcallNoArgs(waitBlockingBind, handle)
    }

    /**
     * Like `wait`, but won't block, so if the value is zero, fails immediately and returns `false`. If
     * non-zero, it returns `true` to report success.
     *
     * Generated from Godot docs: Semaphore.try_wait
     */
    fun tryWait(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(tryWaitBind, handle)
    }

    /**
     * Lowers the `Semaphore`, allowing one thread in, or more if `count` is specified.
     *
     * Generated from Godot docs: Semaphore.post
     */
    fun post(count: Int = 1) {
        ObjectCalls.ptrcallWithIntArg(postBind, handle, count)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Semaphore? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Semaphore? =
            if (handle.address() == 0L) null else Semaphore(handle)

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
