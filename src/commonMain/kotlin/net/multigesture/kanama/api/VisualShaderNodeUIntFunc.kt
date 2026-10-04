package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeUIntFunc
 */
class VisualShaderNodeUIntFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeUIntFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeUIntFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeUIntFunc.Function {
        checkOpen()
        return VisualShaderNodeUIntFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val NEGATE: Function get() = Function(0L)
            val BITWISE_NOT: Function get() = Function(1L)
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUIntFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUIntFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUIntFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntFunc(GodotHandle(handle))

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
