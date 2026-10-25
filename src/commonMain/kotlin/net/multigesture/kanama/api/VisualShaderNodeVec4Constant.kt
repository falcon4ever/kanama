package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Quaternion

/**
 * A 4D vector constant to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVec4Constant
 */
class VisualShaderNodeVec4Constant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Quaternion
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A 4D vector (represented as a `Quaternion`) constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec4Constant.set_constant
     */
    fun setConstant(constant: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A 4D vector (represented as a `Quaternion`) constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec4Constant.get_constant
     */
    fun getConstant(): Quaternion {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec4Constant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVec4Constant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVec4Constant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVec4Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec4Constant(GodotHandle(handle))

        /** A new VisualShaderNodeVec4Constant, GDScript's `VisualShaderNodeVec4Constant.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVec4Constant =
            RefCounted.owned(VisualShaderNodeVec4Constant(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeVec4Constant"))))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 1727505552L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 1222331677L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Constant", "get_constant", GET_CONSTANT_HASH)
    }
}
