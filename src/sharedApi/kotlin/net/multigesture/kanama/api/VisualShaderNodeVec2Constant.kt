package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShaderNodeVec2Constant
 */
class VisualShaderNodeVec2Constant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Vector2
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setConstantBind, segment, constant)
    }

    fun getConstant(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec2Constant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVec2Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec2Constant(GodotHandle(handle))

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
