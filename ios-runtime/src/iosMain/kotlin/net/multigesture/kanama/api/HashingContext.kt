package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: HashingContext
 */
class HashingContext(handle: MemorySegment) : RefCounted(handle) {
    fun start(type: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(startBind, handle, type)
    }

    companion object {
        const val HASH_MD5: Long = 0L
        const val HASH_SHA1: Long = 1L
        const val HASH_SHA256: Long = 2L

        fun fromHandle(handle: MemorySegment): HashingContext? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HashingContext? =
            if (handle.address() == 0L) null else HashingContext(handle)

        private const val START_HASH = 3940338335L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("HashingContext", "start", START_HASH)
        }
    }
}
