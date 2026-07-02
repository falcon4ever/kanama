package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeUVFunc
 */
class VisualShaderNodeUVFunc(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_PANNING: Long = 0L
        const val FUNC_SCALING: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeUVFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUVFunc(handle)

        private const val SET_FUNCTION_HASH = 765791915L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 3772902164L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
