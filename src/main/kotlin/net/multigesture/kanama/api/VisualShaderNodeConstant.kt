package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeConstant
 */
open class VisualShaderNodeConstant(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeConstant? =
            if (handle.address() == 0L) null else VisualShaderNodeConstant(handle)

        // No MethodBinds emitted yet.
    }
}
