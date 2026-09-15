package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Custom logger to receive messages from the internal error/warning stream.
 *
 * Generated from Godot docs: Logger
 */
class Logger(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val ERROR_TYPE_ERROR: Long = 0L
        const val ERROR_TYPE_WARNING: Long = 1L
        const val ERROR_TYPE_SCRIPT: Long = 2L
        const val ERROR_TYPE_SHADER: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Logger? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Logger? =
            if (handle.address() == 0L) null else Logger(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
