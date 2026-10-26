package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A binary `Semaphore` for synchronization of multiple `Thread`s.
 *
 * Generated from Godot docs: Mutex
 */
class Mutex(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Locks this `Mutex`, blocks until it is unlocked by the current owner. Note: This function
     * returns without blocking if the thread already has ownership of the mutex.
     *
     * Generated from Godot docs: Mutex.lock
     */
    fun lock() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.lockBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Tries locking this `Mutex`, but does not block. Returns `true` on success, `false` otherwise.
     * Note: This function returns `true` if the thread already has ownership of the mutex.
     *
     * Generated from Godot docs: Mutex.try_lock
     */
    fun tryLock(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.tryLockBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Unlocks this `Mutex`, leaving it to other threads. Note: If a thread called `lock` or `try_lock`
     * multiple times while already having ownership of the mutex, it must also call `unlock` the same
     * number of times in order to unlock it correctly. Warning: Calling `unlock` more times than
     * `lock` on a given thread, thus ending up trying to unlock a non-locked mutex, is wrong and may
     * causes crashes or deadlocks.
     *
     * Generated from Godot docs: Mutex.unlock
     */
    fun unlock() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.unlockBind, segment)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Mutex? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Mutex? =
            if (handle.address() == 0L) null else RefCounted.owned(Mutex(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Mutex? =
            if (handle.address() == 0L) null else Mutex(GodotHandle(handle))

        /** A new Mutex, GDScript's `Mutex.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): Mutex =
            RefCounted.owned(Mutex(GodotHandle(requireConstructed(ObjectCalls.constructObject("Mutex"), "Mutex"))))
    }

    private object Binds {
        private const val LOCK_HASH = 3218959716L
        @JvmField
        val lockBind =
            ObjectCalls.getMethodBind("Mutex", "lock", LOCK_HASH)

        private const val TRY_LOCK_HASH = 2240911060L
        @JvmField
        val tryLockBind =
            ObjectCalls.getMethodBind("Mutex", "try_lock", TRY_LOCK_HASH)

        private const val UNLOCK_HASH = 3218959716L
        @JvmField
        val unlockBind =
            ObjectCalls.getMethodBind("Mutex", "unlock", UNLOCK_HASH)
    }
}
