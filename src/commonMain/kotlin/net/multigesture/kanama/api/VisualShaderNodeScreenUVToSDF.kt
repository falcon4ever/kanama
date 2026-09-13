package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeScreenUVToSDF
 */
class VisualShaderNodeScreenUVToSDF(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeScreenUVToSDF? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeScreenUVToSDF? =
            if (handle.address() == 0L) null else VisualShaderNodeScreenUVToSDF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
