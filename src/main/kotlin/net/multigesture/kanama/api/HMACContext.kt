package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Used to create an HMAC for a message using a key.
 *
 * Generated from Godot docs: HMACContext
 */
class HMACContext(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Initializes the HMACContext. This method cannot be called again on the same HMACContext until
     * `finish` has been called.
     *
     * Generated from Godot docs: HMACContext.start
     */
    fun start(hashType: Long, key: ByteArray): Long {
        return ObjectCalls.ptrcallWithLongAndByteArrayArgRetLong(startBind, handle, hashType, key)
    }

    /**
     * Updates the message to be HMACed. This can be called multiple times before `finish` is called to
     * append `data` to the message, but cannot be called until `start` has been called.
     *
     * Generated from Godot docs: HMACContext.update
     */
    fun update(data: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(updateBind, handle, data)
    }

    /**
     * Returns the resulting HMAC. If the HMAC failed, an empty `PackedByteArray` is returned.
     *
     * Generated from Godot docs: HMACContext.finish
     */
    fun finish(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HMACContext? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HMACContext? =
            if (handle.address() == 0L) null else HMACContext(handle)

        private const val START_HASH = 3537364598L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("HMACContext", "start", START_HASH)
        }

        private const val UPDATE_HASH = 680677267L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("HMACContext", "update", UPDATE_HASH)
        }

        private const val FINISH_HASH = 2115431945L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("HMACContext", "finish", FINISH_HASH)
        }
    }
}
