package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A `Vector3` constant to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVec3Constant
 */
class VisualShaderNodeVec3Constant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Vector3
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A `Vector3` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec3Constant.set_constant
     */
    fun setConstant(constant: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A `Vector3` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec3Constant.get_constant
     */
    fun getConstant(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec3Constant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVec3Constant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVec3Constant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVec3Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec3Constant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 3460891852L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Constant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 3360562783L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Constant", "get_constant", GET_CONSTANT_HASH)
    }
}
