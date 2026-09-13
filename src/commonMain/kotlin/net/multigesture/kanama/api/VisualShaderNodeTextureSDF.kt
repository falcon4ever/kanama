package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTextureSDF
 */
class VisualShaderNodeTextureSDF(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureSDF? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
