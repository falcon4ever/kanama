package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Used to create an HMAC for a message using a key.
 *
 * Generated from Godot docs: HMACContext
 */
class HMACContext(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the resulting HMAC. If the HMAC failed, an empty `PackedByteArray` is returned.
     *
     * Generated from Godot docs: HMACContext.finish
     */
    fun finish(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(finishBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HMACContext? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HMACContext? =
            if (handle.address() == 0L) null else HMACContext(handle)

        private const val FINISH_HASH = 2115431945L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("HMACContext", "finish", FINISH_HASH)
        }
    }
}
