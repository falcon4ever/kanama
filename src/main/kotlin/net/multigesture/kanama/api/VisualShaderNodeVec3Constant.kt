package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: VisualShaderNodeVec3Constant
 */
class VisualShaderNodeVec3Constant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Vector3
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantBind, handle, constant)
    }

    fun getConstant(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVec3Constant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVec3Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec3Constant(handle)

        private const val SET_CONSTANT_HASH = 3460891852L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Constant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3360562783L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Constant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
