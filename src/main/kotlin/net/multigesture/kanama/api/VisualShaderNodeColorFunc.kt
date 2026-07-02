package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeColorFunc
 */
class VisualShaderNodeColorFunc(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_GRAYSCALE: Long = 0L
        const val FUNC_HSV2RGB: Long = 1L
        const val FUNC_RGB2HSV: Long = 2L
        const val FUNC_SEPIA: Long = 3L
        const val FUNC_LINEAR_TO_SRGB: Long = 4L
        const val FUNC_SRGB_TO_LINEAR: Long = 5L
        const val FUNC_MAX: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeColorFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeColorFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeColorFunc(handle)

        private const val SET_FUNCTION_HASH = 3973396138L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 554863321L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
