package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Mutex
 */
class Mutex(handle: MemorySegment) : RefCounted(handle) {
    fun lock() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(lockBind, handle)
    }

    fun tryLock(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(tryLockBind, handle)
    }

    fun unlock() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(unlockBind, handle)
    }

    companion object {
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
