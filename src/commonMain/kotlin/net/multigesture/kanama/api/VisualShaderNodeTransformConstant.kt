package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * A `Transform3D` constant for use within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTransformConstant
 */
class VisualShaderNodeTransformConstant(handle: GodotHandle) : VisualShaderNodeConstant(handle) {
    var constant: Transform3D
        @JvmName("constantProperty")
        get() = getConstant()
        @JvmName("setConstantProperty")
        set(value) = setConstant(value)

    /**
     * A `Transform3D` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeTransformConstant.set_constant
     */
    fun setConstant(constant: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setConstantBind, segment, constant)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A `Transform3D` constant which represents the state of this node.
     *
     * Generated from Godot docs: VisualShaderNodeTransformConstant.get_constant
     */
    fun getConstant(): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getConstantBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformConstant(GodotHandle(handle))

        /** A new VisualShaderNodeTransformConstant, GDScript's `VisualShaderNodeTransformConstant.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTransformConstant =
            RefCounted.owned(VisualShaderNodeTransformConstant(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeTransformConstant"))))
    }

    private object Binds {
        private const val SET_CONSTANT_HASH = 2952846383L
        @JvmField
        val setConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformConstant", "set_constant", SET_CONSTANT_HASH)

        private const val GET_CONSTANT_HASH = 3229777777L
        @JvmField
        val getConstantBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformConstant", "get_constant", GET_CONSTANT_HASH)
    }
}
