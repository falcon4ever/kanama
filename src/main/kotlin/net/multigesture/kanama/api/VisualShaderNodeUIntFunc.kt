package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeUIntFunc
 */
class VisualShaderNodeUIntFunc(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_NEGATE: Long = 0L
        const val FUNC_BITWISE_NOT: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeUIntFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntFunc(handle)

        private const val SET_FUNCTION_HASH = 2273148961L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 4187123296L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntFunc", "get_function", GET_FUNCTION_HASH)
        }
    }
}
