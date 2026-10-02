package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides access to AES encryption/decryption of raw data.
 *
 * Generated from Godot docs: AESContext
 */
class AESContext(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Start the AES context in the given `mode`. A `key` of either 16 or 32 bytes must always be
     * provided, while an `iv` (initialization vector) of exactly 16 bytes, is only needed when `mode`
     * is either `MODE_CBC_ENCRYPT` or `MODE_CBC_DECRYPT`.
     *
     * Generated from Godot docs: AESContext.start
     */
    fun start(mode: AESContext.Mode, key: ByteArray, iv: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongAndTwoByteArrayArgsRetLong(startBind, segment, mode.value, key, iv))
    }

    /**
     * Run the desired operation for this AES context. Will return a `PackedByteArray` containing the
     * result of encrypting (or decrypting) the given `src`. See `start` for mode of operation. Note:
     * The size of `src` must be a multiple of 16. Apply some padding if needed.
     *
     * Generated from Godot docs: AESContext.update
     */
    fun update(src: ByteArray): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallWithByteArrayArgRetByteArray(updateBind, segment, src)
    }

    /**
     * Get the current IV state for this context (IV gets updated when calling `update`). You normally
     * don't need this function. Note: This function only makes sense when the context is started with
     * `MODE_CBC_ENCRYPT` or `MODE_CBC_DECRYPT`.
     *
     * Generated from Godot docs: AESContext.get_iv_state
     */
    fun getIvState(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(getIvStateBind, segment)
    }

    /**
     * Close this AES context so it can be started again. See `start`.
     *
     * Generated from Godot docs: AESContext.finish
     */
    fun finish() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(finishBind, segment)
    }

    @JvmInline
    value class Mode(val value: Long) {
        companion object {
            /**
             * AES electronic codebook encryption mode.
             *
             * Generated from Godot docs: AESContext.MODE_ECB_ENCRYPT
             */
            val ECB_ENCRYPT: Mode get() = Mode(0L)
            /**
             * AES electronic codebook decryption mode.
             *
             * Generated from Godot docs: AESContext.MODE_ECB_DECRYPT
             */
            val ECB_DECRYPT: Mode get() = Mode(1L)
            /**
             * AES cipher block chaining encryption mode.
             *
             * Generated from Godot docs: AESContext.MODE_CBC_ENCRYPT
             */
            val CBC_ENCRYPT: Mode get() = Mode(2L)
            /**
             * AES cipher block chaining decryption mode.
             *
             * Generated from Godot docs: AESContext.MODE_CBC_DECRYPT
             */
            val CBC_DECRYPT: Mode get() = Mode(3L)
            /**
             * Maximum value for the mode enum.
             *
             * Generated from Godot docs: AESContext.MODE_MAX
             */
            val MAX: Mode get() = Mode(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AESContext? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AESContext? =
            if (handle.address() == 0L) null else AESContext(GodotHandle(handle))

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
