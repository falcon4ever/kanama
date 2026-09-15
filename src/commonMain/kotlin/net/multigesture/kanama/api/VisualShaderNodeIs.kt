package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeIs
 */
class VisualShaderNodeIs(handle: GodotHandle) : VisualShaderNode(handle) {
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
        const val FUNC_IS_INF: Long = 0L
        const val FUNC_IS_NAN: Long = 1L
        const val FUNC_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIs? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else VisualShaderNodeIs(GodotHandle(handle))

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
