package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A custom visual shader graph expression written in Godot Shading Language.
 *
 * Generated from Godot docs: VisualShaderNodeExpression
 */
open class VisualShaderNodeExpression(handle: GodotHandle) : VisualShaderNodeGroupBase(handle) {
    var expression: String
        @JvmName("expressionProperty")
        get() = getExpression()
        @JvmName("setExpressionProperty")
        set(value) = setExpression(value)

    /**
     * An expression in Godot Shading Language, which will be injected at the start of the graph's
     * matching shader function (`vertex`, `fragment`, or `light`), and thus cannot be used to declare
     * functions, varyings, uniforms, or global constants.
     *
     * Generated from Godot docs: VisualShaderNodeExpression.set_expression
     */
    fun setExpression(expression: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setExpressionBind, segment, expression)
    }

    /**
     * An expression in Godot Shading Language, which will be injected at the start of the graph's
     * matching shader function (`vertex`, `fragment`, or `light`), and thus cannot be used to declare
     * functions, varyings, uniforms, or global constants.
     *
     * Generated from Godot docs: VisualShaderNodeExpression.get_expression
     */
    fun getExpression(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getExpressionBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeExpression? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeExpression? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeExpression(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeExpression? =
            if (handle.address() == 0L) null else VisualShaderNodeExpression(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_EXPRESSION_HASH = 83702148L
        @JvmField
        val setExpressionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeExpression", "set_expression", SET_EXPRESSION_HASH)

        private const val GET_EXPRESSION_HASH = 201670096L
        @JvmField
        val getExpressionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeExpression", "get_expression", GET_EXPRESSION_HASH)
    }
}
