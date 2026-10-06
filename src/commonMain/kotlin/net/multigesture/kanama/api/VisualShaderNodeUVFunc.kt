package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeUVFunc.Function {
        checkOpen()
        return VisualShaderNodeUVFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val PANNING: Function get() = Function(0L)
            val SCALING: Function get() = Function(1L)
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUVFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUVFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUVFunc(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 765791915L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 3772902164L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUVFunc", "get_function", GET_FUNCTION_HASH)
    }
}
