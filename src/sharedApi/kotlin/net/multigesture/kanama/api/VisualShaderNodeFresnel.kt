package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeFresnel
 */
class VisualShaderNodeFresnel(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFresnel? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeFresnel? =
            if (handle.address() == 0L) null else VisualShaderNodeFresnel(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
