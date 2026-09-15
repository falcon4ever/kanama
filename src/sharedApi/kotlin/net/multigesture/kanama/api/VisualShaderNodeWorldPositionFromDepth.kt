package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeWorldPositionFromDepth
 */
class VisualShaderNodeWorldPositionFromDepth(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeWorldPositionFromDepth? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeWorldPositionFromDepth? =
            if (handle.address() == 0L) null else VisualShaderNodeWorldPositionFromDepth(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
