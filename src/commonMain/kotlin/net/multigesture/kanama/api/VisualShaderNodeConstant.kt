package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeConstant
 */
open class VisualShaderNodeConstant(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeConstant? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeConstant(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
