package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeFloatConstant
 */
class VisualShaderNodeFloatConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Double
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConstantBind, segment, constant)
    }

    fun getConstant(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFloatConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatConstant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 373806689L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 1740695150L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
