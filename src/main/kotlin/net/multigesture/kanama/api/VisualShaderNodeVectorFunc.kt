package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeVectorFunc
 */
class VisualShaderNodeVectorFunc(handle: MemorySegment) : VisualShaderNodeVectorBase(handle) {
    var function: Long
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: Long) {
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, handle, func)
    }

    fun getFunction(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, handle)
    }

    companion object {
        const val FUNC_NORMALIZE: Long = 0L
        const val FUNC_SATURATE: Long = 1L
        const val FUNC_NEGATE: Long = 2L
        const val FUNC_RECIPROCAL: Long = 3L
        const val FUNC_ABS: Long = 4L
        const val FUNC_ACOS: Long = 5L
        const val FUNC_ACOSH: Long = 6L
        const val FUNC_ASIN: Long = 7L
        const val FUNC_ASINH: Long = 8L
        const val FUNC_ATAN: Long = 9L
        const val FUNC_ATANH: Long = 10L
        const val FUNC_CEIL: Long = 11L
        const val FUNC_COS: Long = 12L
        const val FUNC_COSH: Long = 13L
        const val FUNC_DEGREES: Long = 14L
        const val FUNC_EXP: Long = 15L
        const val FUNC_EXP2: Long = 16L
        const val FUNC_FLOOR: Long = 17L
        const val FUNC_FRACT: Long = 18L
        const val FUNC_INVERSE_SQRT: Long = 19L
        const val FUNC_LOG: Long = 20L
        const val FUNC_LOG2: Long = 21L
        const val FUNC_RADIANS: Long = 22L
        const val FUNC_ROUND: Long = 23L
        const val FUNC_ROUNDEVEN: Long = 24L
        const val FUNC_SIGN: Long = 25L
        const val FUNC_SIN: Long = 26L
        const val FUNC_SINH: Long = 27L
        const val FUNC_SQRT: Long = 28L
        const val FUNC_TAN: Long = 29L
        const val FUNC_TANH: Long = 30L
        const val FUNC_TRUNC: Long = 31L
        const val FUNC_ONEMINUS: Long = 32L
        const val FUNC_MAX: Long = 33L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVectorFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVectorFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorFunc(handle)

        private const val SET_FUNCTION_HASH = 629964457L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 4047776843L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
