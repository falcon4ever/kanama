package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeUIntConstant
 */
class VisualShaderNodeUIntConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Int
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setConstantBind, segment, constant)
    }

    fun getConstant(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntConstant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeUIntConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntConstant(GodotHandle(handle))

        private const val SET_CONSTANT_HASH = 1286410249L
        private val setConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntConstant", "set_constant", SET_CONSTANT_HASH)
        }

        private const val GET_CONSTANT_HASH = 3905245786L
        private val getConstantBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntConstant", "get_constant", GET_CONSTANT_HASH)
        }
    }
}
