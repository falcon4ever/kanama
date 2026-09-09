package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeOutput
 */
open class VisualShaderNodeOutput(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeOutput? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeOutput? =
            if (handle.address() == 0L) null else VisualShaderNodeOutput(handle)

        // No MethodBinds emitted yet.
    }
}
