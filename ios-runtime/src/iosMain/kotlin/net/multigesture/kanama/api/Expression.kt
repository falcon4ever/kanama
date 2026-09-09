package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Expression
 */
class Expression(handle: MemorySegment) : RefCounted(handle) {
    fun hasExecuteFailed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasExecuteFailedBind, handle)
    }

    fun getErrorText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getErrorTextBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Expression? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Expression? =
            if (handle.address() == 0L) null else Expression(handle)

        private const val HAS_EXECUTE_FAILED_HASH = 36873697L
        private val hasExecuteFailedBind by lazy {
            ObjectCalls.getMethodBind("Expression", "has_execute_failed", HAS_EXECUTE_FAILED_HASH)
        }

        private const val GET_ERROR_TEXT_HASH = 201670096L
        private val getErrorTextBind by lazy {
            ObjectCalls.getMethodBind("Expression", "get_error_text", GET_ERROR_TEXT_HASH)
        }
    }
}
