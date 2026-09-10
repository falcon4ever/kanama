package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides access to AES encryption/decryption of raw data.
 *
 * Generated from Godot docs: AESContext
 */
class AESContext(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Get the current IV state for this context (IV gets updated when calling `update`). You normally
     * don't need this function. Note: This function only makes sense when the context is started with
     * `MODE_CBC_ENCRYPT` or `MODE_CBC_DECRYPT`.
     *
     * Generated from Godot docs: AESContext.get_iv_state
     */
    fun getIvState(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(getIvStateBind, handle)
    }

    /**
     * Close this AES context so it can be started again. See `start`.
     *
     * Generated from Godot docs: AESContext.finish
     */
    fun finish() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(finishBind, handle)
    }

    companion object {
        const val MODE_ECB_ENCRYPT: Long = 0L
        const val MODE_ECB_DECRYPT: Long = 1L
        const val MODE_CBC_ENCRYPT: Long = 2L
        const val MODE_CBC_DECRYPT: Long = 3L
        const val MODE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AESContext? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AESContext? =
            if (handle.address() == 0L) null else AESContext(handle)

        private const val GET_IV_STATE_HASH = 2115431945L
        private val getIvStateBind by lazy {
            ObjectCalls.getMethodBind("AESContext", "get_iv_state", GET_IV_STATE_HASH)
        }

        private const val FINISH_HASH = 3218959716L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("AESContext", "finish", FINISH_HASH)
        }
    }
}
