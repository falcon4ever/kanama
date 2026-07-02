package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides access to AES encryption/decryption of raw data.
 *
 * Generated from Godot docs: AESContext
 */
class AESContext(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Start the AES context in the given `mode`. A `key` of either 16 or 32 bytes must always be
     * provided, while an `iv` (initialization vector) of exactly 16 bytes, is only needed when `mode`
     * is either `MODE_CBC_ENCRYPT` or `MODE_CBC_DECRYPT`.
     *
     * Generated from Godot docs: AESContext.start
     */
    fun start(mode: Long, key: ByteArray, iv: ByteArray): Long {
        return ObjectCalls.ptrcallWithLongAndTwoByteArrayArgsRetLong(startBind, handle, mode, key, iv)
    }

    /**
     * Run the desired operation for this AES context. Will return a `PackedByteArray` containing the
     * result of encrypting (or decrypting) the given `src`. See `start` for mode of operation. Note:
     * The size of `src` must be a multiple of 16. Apply some padding if needed.
     *
     * Generated from Godot docs: AESContext.update
     */
    fun update(src: ByteArray): ByteArray {
        return ObjectCalls.ptrcallWithByteArrayArgRetByteArray(updateBind, handle, src)
    }

    fun getIvState(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getIvStateBind, handle)
    }

    /**
     * Close this AES context so it can be started again. See `start`.
     *
     * Generated from Godot docs: AESContext.finish
     */
    fun finish() {
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

        private const val START_HASH = 3122411423L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("AESContext", "start", START_HASH)
        }

        private const val UPDATE_HASH = 527836100L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("AESContext", "update", UPDATE_HASH)
        }

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
