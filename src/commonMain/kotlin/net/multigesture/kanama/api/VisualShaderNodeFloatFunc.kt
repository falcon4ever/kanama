package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeFloatFunc
 */
class VisualShaderNodeFloatFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeFloatFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeFloatFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeFloatFunc.Function {
        checkOpen()
        return VisualShaderNodeFloatFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val SIN: Function get() = Function(0L)
            val COS: Function get() = Function(1L)
            val TAN: Function get() = Function(2L)
            val ASIN: Function get() = Function(3L)
            val ACOS: Function get() = Function(4L)
            val ATAN: Function get() = Function(5L)
            val SINH: Function get() = Function(6L)
            val COSH: Function get() = Function(7L)
            val TANH: Function get() = Function(8L)
            val LOG: Function get() = Function(9L)
            val EXP: Function get() = Function(10L)
            val SQRT: Function get() = Function(11L)
            val ABS: Function get() = Function(12L)
            val SIGN: Function get() = Function(13L)
            val FLOOR: Function get() = Function(14L)
            val ROUND: Function get() = Function(15L)
            val CEIL: Function get() = Function(16L)
            val FRACT: Function get() = Function(17L)
            val SATURATE: Function get() = Function(18L)
            val NEGATE: Function get() = Function(19L)
            val ACOSH: Function get() = Function(20L)
            val ASINH: Function get() = Function(21L)
            val ATANH: Function get() = Function(22L)
            val DEGREES: Function get() = Function(23L)
            val EXP2: Function get() = Function(24L)
            val INVERSE_SQRT: Function get() = Function(25L)
            val LOG2: Function get() = Function(26L)
            val RADIANS: Function get() = Function(27L)
            val RECIPROCAL: Function get() = Function(28L)
            val ROUNDEVEN: Function get() = Function(29L)
            val TRUNC: Function get() = Function(30L)
            val ONEMINUS: Function get() = Function(31L)
            val MAX: Function get() = Function(32L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFloatFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFloatFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFloatFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatFunc(GodotHandle(handle))

        private const val SET_FUNCTION_HASH = 536026177L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 2033948868L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
