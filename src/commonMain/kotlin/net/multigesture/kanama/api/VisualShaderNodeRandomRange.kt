package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeRandomRange
 */
class VisualShaderNodeRandomRange(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeRandomRange? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeRandomRange? =
            if (handle.address() == 0L) null else VisualShaderNodeRandomRange(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
