package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeLinearSceneDepth
 */
class VisualShaderNodeLinearSceneDepth(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeLinearSceneDepth? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeLinearSceneDepth? =
            if (handle.address() == 0L) null else VisualShaderNodeLinearSceneDepth(handle)

        // No MethodBinds emitted yet.
    }
}
