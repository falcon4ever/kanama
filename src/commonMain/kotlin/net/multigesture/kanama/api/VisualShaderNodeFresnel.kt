package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeFresnel
 */
class VisualShaderNodeFresnel(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFresnel? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFresnel? =
            if (handle.address() == 0L) null else VisualShaderNodeFresnel(handle)

        // No MethodBinds emitted yet.
    }
}
