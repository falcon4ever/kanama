package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeIntConstant
 */
class VisualShaderNodeIntConstant(handle: MemorySegment) : VisualShaderNodeConstant(handle) {
    var constant: Int
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Int) {
        ObjectCalls.ptrcallWithIntArg(setConstantBind, handle, constant)
    }

    fun getConstant(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getConstantBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeIntConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeIntConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeIntConstant(handle)

        private const val SET_CONSTANT_HASH = 1286410249L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3905245786L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
