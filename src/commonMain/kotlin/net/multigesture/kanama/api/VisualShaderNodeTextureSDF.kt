package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTextureSDF
 */
class VisualShaderNodeTextureSDF(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTextureSDF? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDF(handle)

        // No MethodBinds emitted yet.
    }
}
