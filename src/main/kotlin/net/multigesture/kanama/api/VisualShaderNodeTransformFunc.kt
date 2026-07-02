package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTransformFunc
 */
class VisualShaderNodeTransformFunc(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_INVERSE: Long = 0L
        const val FUNC_TRANSPOSE: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformFunc(handle)

        private const val SET_FUNCTION_HASH = 2900990409L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 2839926569L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
