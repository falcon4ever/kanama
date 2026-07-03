package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Custom logger to receive messages from the internal error/warning stream.
 *
 * Generated from Godot docs: Logger
 */
class Logger(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val ERROR_TYPE_ERROR: Long = 0L
        const val ERROR_TYPE_WARNING: Long = 1L
        const val ERROR_TYPE_SCRIPT: Long = 2L
        const val ERROR_TYPE_SHADER: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Logger? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Logger? =
            if (handle.address() == 0L) null else Logger(handle)

        // No MethodBinds emitted yet.
    }
}
