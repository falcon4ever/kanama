package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeFaceForward
 */
class VisualShaderNodeFaceForward(handle: MemorySegment) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFaceForward? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFaceForward? =
            if (handle.address() == 0L) null else VisualShaderNodeFaceForward(handle)

        // No MethodBinds emitted yet.
    }
}
