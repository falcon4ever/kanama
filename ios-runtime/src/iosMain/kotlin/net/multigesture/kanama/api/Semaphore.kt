package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Semaphore
 */
class Semaphore(handle: MemorySegment) : RefCounted(handle) {
    fun waitBlocking() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(waitBlockingBind, handle)
    }

    fun tryWait(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(tryWaitBind, handle)
    }

    fun post(count: Int = 1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(postBind, handle, count)
    }

    companion object {
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
