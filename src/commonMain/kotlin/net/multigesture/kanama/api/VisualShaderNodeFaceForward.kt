package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeFaceForward
 */
class VisualShaderNodeFaceForward(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFaceForward? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFaceForward? =
            if (handle.address() == 0L) null else VisualShaderNodeFaceForward(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
