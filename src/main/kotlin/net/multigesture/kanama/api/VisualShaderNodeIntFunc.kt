package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeIntFunc
 */
class VisualShaderNodeIntFunc(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_ABS: Long = 0L
        const val FUNC_NEGATE: Long = 1L
        const val FUNC_SIGN: Long = 2L
        const val FUNC_BITWISE_NOT: Long = 3L
        const val FUNC_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeIntFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeIntFunc(handle)

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
