package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: VisualShaderNodeColorConstant
 */
class VisualShaderNodeColorConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Color
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setConstantBind, segment, constant)
    }

    fun getConstant(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeColorConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeColorConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeColorConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeColorConstant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 2920490490L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 3444240500L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
