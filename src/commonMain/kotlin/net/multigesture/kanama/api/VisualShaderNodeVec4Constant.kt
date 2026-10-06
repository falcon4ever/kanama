package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Quaternion

/**
 * Generated from Godot docs: VisualShaderNodeVec4Constant
 */
class VisualShaderNodeVec4Constant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Quaternion
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setConstantBind, segment, constant)
    }

    fun getConstant(): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec4Constant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVec4Constant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVec4Constant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVec4Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec4Constant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 1727505552L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 1222331677L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "get_constant", GET_CONSTANT_HASH)
    }
}
