package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Outputs a 3D vector based on the result of a floating-point comparison within the visual shader
 * graph.
 *
 * Generated from Godot docs: VisualShaderNodeIf
 */
class VisualShaderNodeIf(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIf? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIf? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIf(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIf? =
            if (handle.address() == 0L) null else VisualShaderNodeIf(GodotHandle(handle))
    }
}
