package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
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

    /**
     * Godot's `Logger.ErrorType` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Logger.ErrorType.<NAME>`).
     *
     * Generated from Godot docs: Logger.ErrorType
     */
    @JvmInline
    value class ErrorType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The message received is an error.
             *
             * Generated from Godot docs: Logger.ERROR_TYPE_ERROR
             */
            val ERROR: ErrorType get() = ErrorType(0L)
            /**
             * The message received is a warning.
             *
             * Generated from Godot docs: Logger.ERROR_TYPE_WARNING
             */
            val WARNING: ErrorType get() = ErrorType(1L)
            /**
             * The message received is a script error.
             *
             * Generated from Godot docs: Logger.ERROR_TYPE_SCRIPT
             */
            val SCRIPT: ErrorType get() = ErrorType(2L)
            /**
             * The message received is a shader error.
             *
             * Generated from Godot docs: Logger.ERROR_TYPE_SHADER
             */
            val SHADER: ErrorType get() = ErrorType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Logger? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Logger? =
            if (handle.address() == 0L) null else RefCounted.owned(Logger(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Logger? =
            if (handle.address() == 0L) null else Logger(GodotHandle(handle))
    }
}
