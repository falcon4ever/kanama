package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A scalar floating-point function to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeFloatFunc
 */
class VisualShaderNodeFloatFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeFloatFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeFloatFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeFloatFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A function to be applied to the scalar.
     *
     * Generated from Godot docs: VisualShaderNodeFloatFunc.get_function
     */
    fun getFunction(): VisualShaderNodeFloatFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeFloatFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeFloatFunc.Function` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeFloatFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeFloatFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Returns the sine of the parameter. Translates to `sin(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_SIN
             */
            val SIN: Function get() = Function(0L)
            /**
             * Returns the cosine of the parameter. Translates to `cos(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_COS
             */
            val COS: Function get() = Function(1L)
            /**
             * Returns the tangent of the parameter. Translates to `tan(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_TAN
             */
            val TAN: Function get() = Function(2L)
            /**
             * Returns the arc-sine of the parameter. Translates to `asin(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ASIN
             */
            val ASIN: Function get() = Function(3L)
            /**
             * Returns the arc-cosine of the parameter. Translates to `acos(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ACOS
             */
            val ACOS: Function get() = Function(4L)
            /**
             * Returns the arc-tangent of the parameter. Translates to `atan(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ATAN
             */
            val ATAN: Function get() = Function(5L)
            /**
             * Returns the hyperbolic sine of the parameter. Translates to `sinh(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_SINH
             */
            val SINH: Function get() = Function(6L)
            /**
             * Returns the hyperbolic cosine of the parameter. Translates to `cosh(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_COSH
             */
            val COSH: Function get() = Function(7L)
            /**
             * Returns the hyperbolic tangent of the parameter. Translates to `tanh(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_TANH
             */
            val TANH: Function get() = Function(8L)
            /**
             * Returns the natural logarithm of the parameter. Translates to `log(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_LOG
             */
            val LOG: Function get() = Function(9L)
            /**
             * Returns the natural exponentiation of the parameter. Translates to `exp(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_EXP
             */
            val EXP: Function get() = Function(10L)
            /**
             * Returns the square root of the parameter. Translates to `sqrt(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_SQRT
             */
            val SQRT: Function get() = Function(11L)
            /**
             * Returns the absolute value of the parameter. Translates to `abs(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ABS
             */
            val ABS: Function get() = Function(12L)
            /**
             * Extracts the sign of the parameter. Translates to `sign(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_SIGN
             */
            val SIGN: Function get() = Function(13L)
            /**
             * Finds the nearest integer less than or equal to the parameter. Translates to `floor(x)` in the
             * Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_FLOOR
             */
            val FLOOR: Function get() = Function(14L)
            /**
             * Finds the nearest integer to the parameter. Translates to `round(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ROUND
             */
            val ROUND: Function get() = Function(15L)
            /**
             * Finds the nearest integer that is greater than or equal to the parameter. Translates to
             * `ceil(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_CEIL
             */
            val CEIL: Function get() = Function(16L)
            /**
             * Computes the fractional part of the argument. Translates to `fract(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_FRACT
             */
            val FRACT: Function get() = Function(17L)
            /**
             * Clamps the value between `0.0` and `1.0` using `min(max(x, 0.0), 1.0)`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_SATURATE
             */
            val SATURATE: Function get() = Function(18L)
            /**
             * Negates the `x` using `-(x)`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_NEGATE
             */
            val NEGATE: Function get() = Function(19L)
            /**
             * Returns the arc-hyperbolic-cosine of the parameter. Translates to `acosh(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ACOSH
             */
            val ACOSH: Function get() = Function(20L)
            /**
             * Returns the arc-hyperbolic-sine of the parameter. Translates to `asinh(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ASINH
             */
            val ASINH: Function get() = Function(21L)
            /**
             * Returns the arc-hyperbolic-tangent of the parameter. Translates to `atanh(x)` in the Godot
             * Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ATANH
             */
            val ATANH: Function get() = Function(22L)
            /**
             * Convert a quantity in radians to degrees. Translates to `degrees(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_DEGREES
             */
            val DEGREES: Function get() = Function(23L)
            /**
             * Returns 2 raised by the power of the parameter. Translates to `exp2(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_EXP2
             */
            val EXP2: Function get() = Function(24L)
            /**
             * Returns the inverse of the square root of the parameter. Translates to `inversesqrt(x)` in the
             * Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_INVERSE_SQRT
             */
            val INVERSE_SQRT: Function get() = Function(25L)
            /**
             * Returns the base 2 logarithm of the parameter. Translates to `log2(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_LOG2
             */
            val LOG2: Function get() = Function(26L)
            /**
             * Convert a quantity in degrees to radians. Translates to `radians(x)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_RADIANS
             */
            val RADIANS: Function get() = Function(27L)
            /**
             * Finds reciprocal value of dividing 1 by `x` (i.e. `1 / x`).
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_RECIPROCAL
             */
            val RECIPROCAL: Function get() = Function(28L)
            /**
             * Finds the nearest even integer to the parameter. Translates to `roundEven(x)` in the Godot
             * Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ROUNDEVEN
             */
            val ROUNDEVEN: Function get() = Function(29L)
            /**
             * Returns a value equal to the nearest integer to `x` whose absolute value is not larger than the
             * absolute value of `x`. Translates to `trunc(x)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_TRUNC
             */
            val TRUNC: Function get() = Function(30L)
            /**
             * Subtracts scalar `x` from 1 (i.e. `1 - x`).
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_ONEMINUS
             */
            val ONEMINUS: Function get() = Function(31L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeFloatFunc.FUNC_MAX
             */
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

        /** A new VisualShaderNodeFloatFunc, GDScript's `VisualShaderNodeFloatFunc.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFloatFunc =
            RefCounted.owned(VisualShaderNodeFloatFunc(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeFloatFunc"), "VisualShaderNodeFloatFunc"))))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 536026177L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 2033948868L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatFunc", "get_function", GET_FUNCTION_HASH)
    }
}
