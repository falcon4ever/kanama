package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeFloatConstant
 */
class VisualShaderNodeFloatConstant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Double
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFloatConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatConstant(handle)

        private const val SET_CONSTANT_HASH = 373806689L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 1740695150L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
