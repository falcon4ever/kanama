package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2

/**
 * A `Vector2` constant to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVec2Constant
 */
class VisualShaderNodeVec2Constant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Vector2
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A `Vector2` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec2Constant.set_constant
     */
    fun setConstant(constant: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A `Vector2` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeVec2Constant.get_constant
     */
    fun getConstant(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec2Constant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVec2Constant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVec2Constant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVec2Constant? =
            if (handle.address() == 0L) null else VisualShaderNodeVec2Constant(GodotHandle(handle))

        /** A new VisualShaderNodeVec2Constant, GDScript's `VisualShaderNodeVec2Constant.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVec2Constant =
            RefCounted.owned(VisualShaderNodeVec2Constant(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeVec2Constant"), "VisualShaderNodeVec2Constant"))))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 743155724L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Constant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 3341600327L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Constant", "get_constant", GET_CONSTANT_HASH)
    }
}
