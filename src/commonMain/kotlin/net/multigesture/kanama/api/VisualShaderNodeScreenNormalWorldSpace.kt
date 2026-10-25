package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that unpacks the screen normal texture in World Space.
 *
 * Generated from Godot docs: VisualShaderNodeScreenNormalWorldSpace
 */
class VisualShaderNodeScreenNormalWorldSpace(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeScreenNormalWorldSpace? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeScreenNormalWorldSpace? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeScreenNormalWorldSpace(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeScreenNormalWorldSpace? =
            if (handle.address() == 0L) null else VisualShaderNodeScreenNormalWorldSpace(GodotHandle(handle))

        /** A new VisualShaderNodeScreenNormalWorldSpace, GDScript's `VisualShaderNodeScreenNormalWorldSpace.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeScreenNormalWorldSpace =
            RefCounted.owned(VisualShaderNodeScreenNormalWorldSpace(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeScreenNormalWorldSpace"))))
    }
}
