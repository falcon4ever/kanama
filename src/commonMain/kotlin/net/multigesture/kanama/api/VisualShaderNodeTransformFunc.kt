package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeTransformFunc.Function {
        checkOpen()
        return VisualShaderNodeTransformFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformFunc(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 2900990409L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 2839926569L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformFunc", "get_function", GET_FUNCTION_HASH)
    }
}
