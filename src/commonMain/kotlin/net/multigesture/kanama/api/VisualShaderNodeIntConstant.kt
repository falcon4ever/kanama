package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeIntConstant
 */
class VisualShaderNodeIntConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Int
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setConstantBind, segment, constant)
    }

    fun getConstant(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIntConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIntConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIntConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIntConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeIntConstant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 1286410249L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 3905245786L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
