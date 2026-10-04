package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeIntFunc
 */
class VisualShaderNodeIntFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeIntFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeIntFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeIntFunc.Function {
        checkOpen()
        return VisualShaderNodeIntFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val ABS: Function get() = Function(0L)
            val NEGATE: Function get() = Function(1L)
            val SIGN: Function get() = Function(2L)
            val BITWISE_NOT: Function get() = Function(3L)
            val MAX: Function get() = Function(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIntFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIntFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIntFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeIntFunc(GodotHandle(handle))

        private const val SET_FUNCTION_HASH = 424195284L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 2753496911L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
