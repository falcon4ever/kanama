package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformCompose
 */
class VisualShaderNodeTransformCompose(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformCompose? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTransformCompose? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformCompose(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
