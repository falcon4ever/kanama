package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A synchronization mechanism used to control access to a shared resource by `Thread`s.
 *
 * Generated from Godot docs: Semaphore
 */
class Semaphore(handle: GodotHandle) : RefCounted(handle) {
    fun waitBlocking() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.waitBlockingBind, segment)
    }

    /**
     * Like `wait`, but won't block, so if the value is zero, fails immediately and returns `false`. If
     * non-zero, it returns `true` to report success.
     *
     * Generated from Godot docs: Semaphore.try_wait
     */
    fun tryWait(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.tryWaitBind, segment)
    }

    /**
     * Lowers the `Semaphore`, allowing one thread in, or more if `count` is specified.
     *
     * Generated from Godot docs: Semaphore.post
     */
    fun post(count: Int = 1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.postBind, segment, count)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Semaphore? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Semaphore? =
            if (handle.address() == 0L) null else RefCounted.owned(Semaphore(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Semaphore? =
            if (handle.address() == 0L) null else Semaphore(GodotHandle(handle))
    }

    private object Binds {
        private const val WAIT_HASH = 3218959716L
        @JvmField
        val waitBlockingBind =
            ObjectCalls.getMethodBind("Semaphore", "wait", WAIT_HASH)

        private const val TRY_WAIT_HASH = 2240911060L
        @JvmField
        val tryWaitBind =
            ObjectCalls.getMethodBind("Semaphore", "try_wait", TRY_WAIT_HASH)

        private const val POST_HASH = 1667783136L
        @JvmField
        val postBind =
            ObjectCalls.getMethodBind("Semaphore", "post", POST_HASH)
    }
}
