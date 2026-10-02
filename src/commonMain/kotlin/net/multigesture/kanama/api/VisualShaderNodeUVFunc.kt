package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeUVFunc
 */
class VisualShaderNodeUVFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeUVFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeUVFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeUVFunc.Function {
        checkOpen()
        return VisualShaderNodeUVFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    @JvmInline
    value class Function(val value: Long) {
        companion object {
            val PANNING: Function get() = Function(0L)
            val SCALING: Function get() = Function(1L)
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVFunc? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeUVFunc? =
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
