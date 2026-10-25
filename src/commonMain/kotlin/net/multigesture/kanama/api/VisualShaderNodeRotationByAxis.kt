package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that modifies the rotation of the object using a rotation matrix.
 *
 * Generated from Godot docs: VisualShaderNodeRotationByAxis
 */
class VisualShaderNodeRotationByAxis(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeRotationByAxis? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeRotationByAxis? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeRotationByAxis(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeRotationByAxis? =
            if (handle.address() == 0L) null else VisualShaderNodeRotationByAxis(GodotHandle(handle))

        /** A new VisualShaderNodeRotationByAxis, GDScript's `VisualShaderNodeRotationByAxis.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeRotationByAxis =
            RefCounted.owned(VisualShaderNodeRotationByAxis(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeRotationByAxis"))))
    }
}
