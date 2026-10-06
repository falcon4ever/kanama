package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Used to create an HMAC for a message using a key.
 *
 * Generated from Godot docs: HMACContext
 */
class HMACContext(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Initializes the HMACContext. This method cannot be called again on the same HMACContext until
     * `finish` has been called.
     *
     * Generated from Godot docs: HMACContext.start
     */
    fun start(hashType: HashingContext.HashType, key: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongAndByteArrayArgRetLong(Binds.startBind, segment, hashType.value, key))
    }

    /**
     * Updates the message to be HMACed. This can be called multiple times before `finish` is called to
     * append `data` to the message, but cannot be called until `start` has been called.
     *
     * Generated from Godot docs: HMACContext.update
     */
    fun update(data: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(Binds.updateBind, segment, data))
    }

    /**
     * Returns the resulting HMAC. If the HMAC failed, an empty `PackedByteArray` is returned.
     *
     * Generated from Godot docs: HMACContext.finish
     */
    fun finish(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.finishBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HMACContext? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): HMACContext? =
            if (handle.address() == 0L) null else RefCounted.owned(HMACContext(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): HMACContext? =
            if (handle.address() == 0L) null else HMACContext(GodotHandle(handle))
    }

    private object Binds {
        private const val START_HASH = 3537364598L
        @JvmField
        val startBind =
            ObjectCalls.getMethodBind("HMACContext", "start", START_HASH)

        private const val UPDATE_HASH = 680677267L
        @JvmField
        val updateBind =
            ObjectCalls.getMethodBind("HMACContext", "update", UPDATE_HASH)

        private const val FINISH_HASH = 2115431945L
        @JvmField
        val finishBind =
            ObjectCalls.getMethodBind("HMACContext", "finish", FINISH_HASH)
    }
}
