package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A class that stores an expression you can execute.
 *
 * Generated from Godot docs: Expression
 */
class Expression(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Parses the expression and returns an `Error` code. You can optionally specify names of variables
     * that may appear in the expression with `input_names`, so that you can bind them when it gets
     * executed.
     *
     * Generated from Godot docs: Expression.parse
     */
    fun parse(expression: String, inputNames: List<String>): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(parseBind, handle, expression, inputNames)
    }

    /**
     * Returns `true` if `execute` has failed.
     *
     * Generated from Godot docs: Expression.has_execute_failed
     */
    fun hasExecuteFailed(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasExecuteFailedBind, handle)
    }

    /**
     * Returns the error text if `parse` or `execute` has failed.
     *
     * Generated from Godot docs: Expression.get_error_text
     */
    fun getErrorText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getErrorTextBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Expression? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Expression? =
            if (handle.address() == 0L) null else Expression(handle)

        private const val PARSE_HASH = 3069722906L
        private val parseBind by lazy {
            ObjectCalls.getMethodBind("Expression", "parse", PARSE_HASH)
        }

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
