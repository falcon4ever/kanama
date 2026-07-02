package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: VisualShaderNodeColorConstant
 */
class VisualShaderNodeColorConstant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Color
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Color) {
        ObjectCalls.ptrcallWithColorArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeColorConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeColorConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeColorConstant(handle)

        private const val SET_CONSTANT_HASH = 2920490490L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3444240500L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeColorConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
