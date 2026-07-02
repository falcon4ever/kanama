package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Quaternion

/**
 * Generated from Godot docs: VisualShaderNodeVec4Constant
 */
class VisualShaderNodeVec4Constant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Quaternion
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Quaternion) {
        ObjectCalls.ptrcallWithQuaternionArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVec4Constant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVec4Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec4Constant(handle)

        private const val SET_CONSTANT_HASH = 1727505552L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 1222331677L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
