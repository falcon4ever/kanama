package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeBooleanConstant
 */
class VisualShaderNodeBooleanConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Boolean
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    fun setConstant(constant: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setConstantBind, segment, constant)
    }

    fun getConstant(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getConstantBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeBooleanConstant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeBooleanConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeBooleanConstant(GodotHandle(handle))

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
