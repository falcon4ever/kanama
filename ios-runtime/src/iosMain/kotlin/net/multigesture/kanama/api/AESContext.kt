package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AESContext
 */
class AESContext(handle: MemorySegment) : RefCounted(handle) {
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

        fun fromHandle(handle: MemorySegment): AESContext? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AESContext? =
            if (handle.address() == 0L) null else AESContext(handle)

        private const val FINISH_HASH = 3218959716L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("AESContext", "finish", FINISH_HASH)
        }
    }
}
