package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A visual shader node that calculates the position of the pixel in world space using the depth
 * texture.
 *
 * Generated from Godot docs: VisualShaderNodeWorldPositionFromDepth
 */
class VisualShaderNodeWorldPositionFromDepth(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeWorldPositionFromDepth? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeWorldPositionFromDepth? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeWorldPositionFromDepth(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeWorldPositionFromDepth? =
            if (handle.address() == 0L) null else VisualShaderNodeWorldPositionFromDepth(GodotHandle(handle))

        /** A new VisualShaderNodeWorldPositionFromDepth, GDScript's `VisualShaderNodeWorldPositionFromDepth.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeWorldPositionFromDepth =
            RefCounted.owned(VisualShaderNodeWorldPositionFromDepth(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeWorldPositionFromDepth"), "VisualShaderNodeWorldPositionFromDepth"))))
    }
}
