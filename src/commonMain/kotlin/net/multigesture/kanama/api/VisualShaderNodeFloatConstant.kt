package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A scalar floating-point constant to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeFloatConstant
 */
class VisualShaderNodeFloatConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Double
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A floating-point constant which represents a state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeFloatConstant.set_constant
     */
    fun setConstant(constant: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A floating-point constant which represents a state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeFloatConstant.get_constant
     */
    fun getConstant(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFloatConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFloatConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatConstant(GodotHandle(handle))

        /** A new VisualShaderNodeFloatConstant, GDScript's `VisualShaderNodeFloatConstant.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFloatConstant =
            RefCounted.owned(VisualShaderNodeFloatConstant(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeFloatConstant"), "VisualShaderNodeFloatConstant"))))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 373806689L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 1740695150L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
