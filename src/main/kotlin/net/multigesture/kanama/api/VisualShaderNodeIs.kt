package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeIs
 */
class VisualShaderNodeIs(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val FUNC_IS_INF: Long = 0L
        const val FUNC_IS_NAN: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeIs? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else VisualShaderNodeIs(handle)

        private const val SET_FUNCTION_HASH = 1438374690L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 580678557L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "get_function", GET_FUNCTION_HASH)
        }
    }
}
