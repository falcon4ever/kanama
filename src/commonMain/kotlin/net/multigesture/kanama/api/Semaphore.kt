package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A synchronization mechanism used to control access to a shared resource by `Thread`s.
 *
 * Generated from Godot docs: Semaphore
 */
class Semaphore(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Waits for the `Semaphore`, if its value is zero, blocks until non-zero.
     *
     * Generated from Godot docs: Semaphore.wait
     */
    fun waitBlocking() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.waitBlockingBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Like `wait`, but won't block, so if the value is zero, fails immediately and returns `false`. If
     * non-zero, it returns `true` to report success.
     *
     * Generated from Godot docs: Semaphore.try_wait
     */
    fun tryWait(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.tryWaitBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Lowers the `Semaphore`, allowing one thread in, or more if `count` is specified.
     *
     * Generated from Godot docs: Semaphore.post
     */
    fun post(count: Int = 1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.postBind, segment, count)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Semaphore? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Semaphore? =
            if (handle.address() == 0L) null else RefCounted.owned(Semaphore(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Semaphore? =
            if (handle.address() == 0L) null else Semaphore(GodotHandle(handle))

        /** A new Semaphore, GDScript's `Semaphore.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): Semaphore =
            RefCounted.owned(Semaphore(GodotHandle(requireConstructed(ObjectCalls.constructObject("Semaphore"), "Semaphore"))))
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
