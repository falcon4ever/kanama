package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeUVPolarCoord
 */
class VisualShaderNodeUVPolarCoord(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVPolarCoord? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeUVPolarCoord? =
            if (handle.address() == 0L) null else VisualShaderNodeUVPolarCoord(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
