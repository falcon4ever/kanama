package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
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
     * Starts a new hash computation of the given `type` (e.g. `HashType.SHA256` to start computation
     * of an SHA-256).
     *
     * Generated from Godot docs: HashingContext.start
     */
    fun start(type: HashingContext.HashType): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongArgRetLong(Binds.startBind, segment, type.value))
    }

    /**
     * Updates the computation with the given `chunk` of data.
     *
     * Generated from Godot docs: HashingContext.update
     */
    fun update(chunk: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(Binds.updateBind, segment, chunk))
    }

    /**
     * Closes the current context, and return the computed hash.
     *
     * Generated from Godot docs: HashingContext.finish
     */
    fun finish(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.finishBind, segment)
    }

    /**
     * Godot's `HashingContext.HashType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`HashingContext.HashType.<NAME>`).
     *
     * Generated from Godot docs: HashingContext.HashType
     */
    @JvmInline
    value class HashType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Hashing algorithm: MD5.
             *
             * Generated from Godot docs: HashingContext.HASH_MD5
             */
            val MD5: HashType get() = HashType(0L)
            /**
             * Hashing algorithm: SHA-1.
             *
             * Generated from Godot docs: HashingContext.HASH_SHA1
             */
            val SHA1: HashType get() = HashType(1L)
            /**
             * Hashing algorithm: SHA-256.
             *
             * Generated from Godot docs: HashingContext.HASH_SHA256
             */
            val SHA256: HashType get() = HashType(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HashingContext? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): HashingContext? =
            if (handle.address() == 0L) null else RefCounted.owned(HashingContext(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): HashingContext? =
            if (handle.address() == 0L) null else HashingContext(GodotHandle(handle))
    }

    private object Binds {
        private const val START_HASH = 3940338335L
        @JvmField
        val startBind =
            ObjectCalls.getMethodBind("HashingContext", "start", START_HASH)

        private const val UPDATE_HASH = 680677267L
        @JvmField
        val updateBind =
            ObjectCalls.getMethodBind("HashingContext", "update", UPDATE_HASH)

        private const val FINISH_HASH = 2115431945L
        @JvmField
        val finishBind =
            ObjectCalls.getMethodBind("HashingContext", "finish", FINISH_HASH)
    }
}
