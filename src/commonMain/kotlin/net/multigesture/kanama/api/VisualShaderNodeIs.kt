package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeIs
 */
class VisualShaderNodeIs(handle: GodotHandle) : VisualShaderNode(handle) {
    var function: VisualShaderNodeIs.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    fun setFunction(func: VisualShaderNodeIs.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeIs.Function {
        checkOpen()
        return VisualShaderNodeIs.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val IS_INF: Function get() = Function(0L)
            val IS_NAN: Function get() = Function(1L)
            val MAX: Function get() = Function(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIs? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIs(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIs? =
            if (handle.address() == 0L) null else VisualShaderNodeIs(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FUNCTION_HASH = 1438374690L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 580678557L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIs", "get_function", GET_FUNCTION_HASH)
    }
}
