package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

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
        ObjectCalls.ptrcallWithDoubleArg(setConstantBind, segment, constant)
    }

    fun getConstant(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatConstant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatConstant(GodotHandle(handle))

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
