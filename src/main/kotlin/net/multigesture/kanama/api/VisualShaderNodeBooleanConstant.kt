package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeBooleanConstant
 */
class VisualShaderNodeBooleanConstant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Boolean
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeBooleanConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeBooleanConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeBooleanConstant(handle)

        private const val SET_CONSTANT_HASH = 2586408642L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 36873697L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
