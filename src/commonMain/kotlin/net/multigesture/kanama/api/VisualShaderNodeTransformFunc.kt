package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformFunc
 */
class VisualShaderNodeTransformFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeTransformFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeTransformFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeTransformFunc.Function {
        checkOpen()
        return VisualShaderNodeTransformFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val INVERSE: Function get() = Function(0L)
            val TRANSPOSE: Function get() = Function(1L)
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformFunc? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformFunc(GodotHandle(handle))

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
