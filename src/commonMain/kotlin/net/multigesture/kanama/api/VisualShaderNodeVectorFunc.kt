package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVectorFunc
 */
class VisualShaderNodeVectorFunc(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    var function: VisualShaderNodeVectorFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeVectorFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeVectorFunc.Function {
        checkOpen()
        return VisualShaderNodeVectorFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val NORMALIZE: Function get() = Function(0L)
            val SATURATE: Function get() = Function(1L)
            val NEGATE: Function get() = Function(2L)
            val RECIPROCAL: Function get() = Function(3L)
            val ABS: Function get() = Function(4L)
            val ACOS: Function get() = Function(5L)
            val ACOSH: Function get() = Function(6L)
            val ASIN: Function get() = Function(7L)
            val ASINH: Function get() = Function(8L)
            val ATAN: Function get() = Function(9L)
            val ATANH: Function get() = Function(10L)
            val CEIL: Function get() = Function(11L)
            val COS: Function get() = Function(12L)
            val COSH: Function get() = Function(13L)
            val DEGREES: Function get() = Function(14L)
            val EXP: Function get() = Function(15L)
            val EXP2: Function get() = Function(16L)
            val FLOOR: Function get() = Function(17L)
            val FRACT: Function get() = Function(18L)
            val INVERSE_SQRT: Function get() = Function(19L)
            val LOG: Function get() = Function(20L)
            val LOG2: Function get() = Function(21L)
            val RADIANS: Function get() = Function(22L)
            val ROUND: Function get() = Function(23L)
            val ROUNDEVEN: Function get() = Function(24L)
            val SIGN: Function get() = Function(25L)
            val SIN: Function get() = Function(26L)
            val SINH: Function get() = Function(27L)
            val SQRT: Function get() = Function(28L)
            val TAN: Function get() = Function(29L)
            val TANH: Function get() = Function(30L)
            val TRUNC: Function get() = Function(31L)
            val ONEMINUS: Function get() = Function(32L)
            val MAX: Function get() = Function(33L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorFunc(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 629964457L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 4047776843L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorFunc", "get_function", GET_FUNCTION_HASH)
    }
}
