package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeExpression
 */
open class VisualShaderNodeExpression(handle: MemorySegment) : VisualShaderNodeGroupBase(handle) {
    var expression: String
        @JvmName("expressionProperty")
        get() = getExpression()
        @JvmName("setExpressionProperty")
        set(value) = setExpression(value)

    fun setExpression(expression: String) {
        ObjectCalls.ptrcallWithStringArg(setExpressionBind, handle, expression)
    }

    fun getExpression(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getExpressionBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeExpression? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeExpression? =
            if (handle.address() == 0L) null else VisualShaderNodeExpression(handle)

        private const val SET_EXPRESSION_HASH = 83702148L
        private val setExpressionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeExpression", "set_expression", SET_EXPRESSION_HASH)
        }

        private const val GET_EXPRESSION_HASH = 201670096L
        private val getExpressionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeExpression", "get_expression", GET_EXPRESSION_HASH)
        }
    }
}
