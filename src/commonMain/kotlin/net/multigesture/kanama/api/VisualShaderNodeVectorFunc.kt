package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A vector function to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorFunc
 */
class VisualShaderNodeVectorFunc(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    var function: VisualShaderNodeVectorFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * The function to be performed.
     *
     * Generated from Godot docs: VisualShaderNodeVectorFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeVectorFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The function to be performed.
     *
     * Generated from Godot docs: VisualShaderNodeVectorFunc.get_function
     */
    fun getFunction(): VisualShaderNodeVectorFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeVectorFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeVectorFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeVectorFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeVectorFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Normalizes the vector so that it has a length of `1` but points in the same direction.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_NORMALIZE
             */
            val NORMALIZE: Function get() = Function(0L)
            /**
             * Clamps the value between `0.0` and `1.0`.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_SATURATE
             */
            val SATURATE: Function get() = Function(1L)
            /**
             * Returns the opposite value of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_NEGATE
             */
            val NEGATE: Function get() = Function(2L)
            /**
             * Returns `1/vector`.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_RECIPROCAL
             */
            val RECIPROCAL: Function get() = Function(3L)
            /**
             * Returns the absolute value of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ABS
             */
            val ABS: Function get() = Function(4L)
            /**
             * Returns the arc-cosine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ACOS
             */
            val ACOS: Function get() = Function(5L)
            /**
             * Returns the inverse hyperbolic cosine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ACOSH
             */
            val ACOSH: Function get() = Function(6L)
            /**
             * Returns the arc-sine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ASIN
             */
            val ASIN: Function get() = Function(7L)
            /**
             * Returns the inverse hyperbolic sine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ASINH
             */
            val ASINH: Function get() = Function(8L)
            /**
             * Returns the arc-tangent of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ATAN
             */
            val ATAN: Function get() = Function(9L)
            /**
             * Returns the inverse hyperbolic tangent of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ATANH
             */
            val ATANH: Function get() = Function(10L)
            /**
             * Finds the nearest integer that is greater than or equal to the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_CEIL
             */
            val CEIL: Function get() = Function(11L)
            /**
             * Returns the cosine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_COS
             */
            val COS: Function get() = Function(12L)
            /**
             * Returns the hyperbolic cosine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_COSH
             */
            val COSH: Function get() = Function(13L)
            /**
             * Converts a quantity in radians to degrees.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_DEGREES
             */
            val DEGREES: Function get() = Function(14L)
            /**
             * Base-e Exponential.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_EXP
             */
            val EXP: Function get() = Function(15L)
            /**
             * Base-2 Exponential.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_EXP2
             */
            val EXP2: Function get() = Function(16L)
            /**
             * Finds the nearest integer less than or equal to the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_FLOOR
             */
            val FLOOR: Function get() = Function(17L)
            /**
             * Computes the fractional part of the argument.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_FRACT
             */
            val FRACT: Function get() = Function(18L)
            /**
             * Returns the inverse of the square root of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_INVERSE_SQRT
             */
            val INVERSE_SQRT: Function get() = Function(19L)
            /**
             * Natural logarithm.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_LOG
             */
            val LOG: Function get() = Function(20L)
            /**
             * Base-2 logarithm.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_LOG2
             */
            val LOG2: Function get() = Function(21L)
            /**
             * Converts a quantity in degrees to radians.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_RADIANS
             */
            val RADIANS: Function get() = Function(22L)
            /**
             * Finds the nearest integer to the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ROUND
             */
            val ROUND: Function get() = Function(23L)
            /**
             * Finds the nearest even integer to the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ROUNDEVEN
             */
            val ROUNDEVEN: Function get() = Function(24L)
            /**
             * Extracts the sign of the parameter, i.e. returns `-1` if the parameter is negative, `1` if it's
             * positive and `0` otherwise.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_SIGN
             */
            val SIGN: Function get() = Function(25L)
            /**
             * Returns the sine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_SIN
             */
            val SIN: Function get() = Function(26L)
            /**
             * Returns the hyperbolic sine of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_SINH
             */
            val SINH: Function get() = Function(27L)
            /**
             * Returns the square root of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_SQRT
             */
            val SQRT: Function get() = Function(28L)
            /**
             * Returns the tangent of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_TAN
             */
            val TAN: Function get() = Function(29L)
            /**
             * Returns the hyperbolic tangent of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_TANH
             */
            val TANH: Function get() = Function(30L)
            /**
             * Returns a value equal to the nearest integer to the parameter whose absolute value is not larger
             * than the absolute value of the parameter.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_TRUNC
             */
            val TRUNC: Function get() = Function(31L)
            /**
             * Returns `1.0 - vector`.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_ONEMINUS
             */
            val ONEMINUS: Function get() = Function(32L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeVectorFunc.FUNC_MAX
             */
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
