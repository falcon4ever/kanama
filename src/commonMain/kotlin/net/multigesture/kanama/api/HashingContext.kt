package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides functionality for computing cryptographic hashes chunk by chunk.
 *
 * Generated from Godot docs: HashingContext
 */
class HashingContext(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Starts a new hash computation of the given `type` (e.g. `HASH_SHA256` to start computation of an
     * SHA-256).
     *
     * Generated from Godot docs: HashingContext.start
     */
    fun start(type: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(startBind, segment, type)
    }

    /**
     * Updates the computation with the given `chunk` of data.
     *
     * Generated from Godot docs: HashingContext.update
     */
    fun update(chunk: ByteArray): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(updateBind, segment, chunk)
    }

    /**
     * Closes the current context, and return the computed hash.
     *
     * Generated from Godot docs: HashingContext.finish
     */
    fun finish(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, segment)
    }

    companion object {
        const val HASH_MD5: Long = 0L
        const val HASH_SHA1: Long = 1L
        const val HASH_SHA256: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): HashingContext? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HashingContext? =
            if (handle.address() == 0L) null else HashingContext(GodotHandle(handle))

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
