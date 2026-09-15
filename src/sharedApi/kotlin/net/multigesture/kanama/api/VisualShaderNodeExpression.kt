package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeExpression
 */
open class VisualShaderNodeExpression(handle: GodotHandle) : VisualShaderNodeGroupBase(handle) {
    var expression: String
        @JvmName("expressionProperty")
        get() = getExpression()
        @JvmName("setExpressionProperty")
        set(value) = setExpression(value)

    fun setExpression(expression: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setExpressionBind, segment, expression)
    }

    fun getExpression(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getExpressionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeExpression? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeExpression? =
            if (handle.address() == 0L) null else VisualShaderNodeExpression(GodotHandle(handle))

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
