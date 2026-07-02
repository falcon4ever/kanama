package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShaderNodeVec2Constant
 */
class VisualShaderNodeVec2Constant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Vector2
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setConstantBind, handle, constant)
    }

    fun getConstant(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVec2Constant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVec2Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec2Constant(handle)

        private const val SET_CONSTANT_HASH = 743155724L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Constant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3341600327L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Constant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
