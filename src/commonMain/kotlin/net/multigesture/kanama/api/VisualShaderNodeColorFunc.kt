package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeColorFunc
 */
class VisualShaderNodeColorFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeColorFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeColorFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeColorFunc.Function {
        checkOpen()
        return VisualShaderNodeColorFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val GRAYSCALE: Function get() = Function(0L)
            val HSV2RGB: Function get() = Function(1L)
            val RGB2HSV: Function get() = Function(2L)
            val SEPIA: Function get() = Function(3L)
            val LINEAR_TO_SRGB: Function get() = Function(4L)
            val SRGB_TO_LINEAR: Function get() = Function(5L)
            val MAX: Function get() = Function(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeColorFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeColorFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeColorFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeColorFunc(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 3973396138L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 554863321L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "get_function", GET_FUNCTION_HASH)
    }
}
