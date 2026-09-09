package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides functionality for computing cryptographic hashes chunk by chunk.
 *
 * Generated from Godot docs: HashingContext
 */
class HashingContext(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Starts a new hash computation of the given `type` (e.g. `HASH_SHA256` to start computation of an
     * SHA-256).
     *
     * Generated from Godot docs: HashingContext.start
     */
    fun start(type: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(startBind, handle, type)
    }

    companion object {
        const val HASH_MD5: Long = 0L
        const val HASH_SHA1: Long = 1L
        const val HASH_SHA256: Long = 2L

        @JvmStatic
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
