package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A boolean constant to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeBooleanConstant
 */
class VisualShaderNodeBooleanConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Boolean
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A boolean constant which represents a state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanConstant.set_constant
     */
    fun setConstant(constant: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A boolean constant which represents a state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanConstant.get_constant
     */
    fun getConstant(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeBooleanConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeBooleanConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeBooleanConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeBooleanConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeBooleanConstant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 2586408642L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 36873697L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
