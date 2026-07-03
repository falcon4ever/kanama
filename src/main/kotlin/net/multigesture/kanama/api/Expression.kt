package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

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
        return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(parseBind, handle, expression, inputNames)
    }

    /**
     * Executes the expression that was previously parsed by `parse` and returns the result. Before you
     * use the returned object, you should check if the method failed by calling `has_execute_failed`.
     * If you defined input variables in `parse`, you can specify their values in the inputs array, in
     * the same order.
     *
     * Generated from Godot docs: Expression.execute
     */
    fun execute(inputs: List<Any?> = emptyList(), baseInstance: GodotObject, showError: Boolean = true, constCallsOnly: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithArrayObjectTwoBoolArgsRetVariantScalar(executeBind, handle, inputs, baseInstance.handle, showError, constCallsOnly)
    }

    /**
     * Returns `true` if `execute` has failed.
     *
     * Generated from Godot docs: Expression.has_execute_failed
     */
    fun hasExecuteFailed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasExecuteFailedBind, handle)
    }

    /**
     * Returns the error text if `parse` or `execute` has failed.
     *
     * Generated from Godot docs: Expression.get_error_text
     */
    fun getErrorText(): String {
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

        private const val EXECUTE_HASH = 3712471238L
        private val executeBind by lazy {
            ObjectCalls.getMethodBind("Expression", "execute", EXECUTE_HASH)
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
