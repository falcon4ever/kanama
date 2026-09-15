package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeScreenNormalWorldSpace
 */
class VisualShaderNodeScreenNormalWorldSpace(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeScreenNormalWorldSpace? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeScreenNormalWorldSpace? =
            if (handle.address() == 0L) null else VisualShaderNodeScreenNormalWorldSpace(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
