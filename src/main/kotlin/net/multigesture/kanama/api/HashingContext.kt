package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

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
        return ObjectCalls.ptrcallWithLongArgRetLong(startBind, handle, type)
    }

    /**
     * Updates the computation with the given `chunk` of data.
     *
     * Generated from Godot docs: HashingContext.update
     */
    fun update(chunk: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(updateBind, handle, chunk)
    }

    /**
     * Closes the current context, and return the computed hash.
     *
     * Generated from Godot docs: HashingContext.finish
     */
    fun finish(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, handle)
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

        private const val UPDATE_HASH = 680677267L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("HashingContext", "update", UPDATE_HASH)
        }

        private const val FINISH_HASH = 2115431945L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("HashingContext", "finish", FINISH_HASH)
        }
    }
}
