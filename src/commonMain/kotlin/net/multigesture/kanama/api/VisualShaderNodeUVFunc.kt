package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeUVFunc
 */
class VisualShaderNodeUVFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: Long
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func)
    }

    fun getFunction(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment)
    }

    companion object {
        const val FUNC_PANNING: Long = 0L
        const val FUNC_SCALING: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVFunc? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUVFunc(GodotHandle(handle))

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
