package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeScreenNormalWorldSpace
 */
class VisualShaderNodeScreenNormalWorldSpace(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeScreenNormalWorldSpace? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeScreenNormalWorldSpace? =
            if (handle.address() == 0L) null else VisualShaderNodeScreenNormalWorldSpace(handle)

        // No MethodBinds emitted yet.
    }
}
