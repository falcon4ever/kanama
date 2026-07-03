package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A binary `Semaphore` for synchronization of multiple `Thread`s.
 *
 * Generated from Godot docs: Mutex
 */
class Mutex(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Locks this `Mutex`, blocks until it is unlocked by the current owner. Note: This function
     * returns without blocking if the thread already has ownership of the mutex.
     *
     * Generated from Godot docs: Mutex.lock
     */
    fun lock() {
        ObjectCalls.ptrcallNoArgs(lockBind, handle)
    }

    /**
     * Tries locking this `Mutex`, but does not block. Returns `true` on success, `false` otherwise.
     * Note: This function returns `true` if the thread already has ownership of the mutex.
     *
     * Generated from Godot docs: Mutex.try_lock
     */
    fun tryLock(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(tryLockBind, handle)
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
        ObjectCalls.ptrcallNoArgs(unlockBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Mutex? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Mutex? =
            if (handle.address() == 0L) null else Mutex(handle)

        private const val LOCK_HASH = 3218959716L
        private val lockBind by lazy {
            ObjectCalls.getMethodBind("Mutex", "lock", LOCK_HASH)
        }

        private const val TRY_LOCK_HASH = 2240911060L
        private val tryLockBind by lazy {
            ObjectCalls.getMethodBind("Mutex", "try_lock", TRY_LOCK_HASH)
        }

        private const val UNLOCK_HASH = 3218959716L
        private val unlockBind by lazy {
            ObjectCalls.getMethodBind("Mutex", "unlock", UNLOCK_HASH)
        }
    }
}
