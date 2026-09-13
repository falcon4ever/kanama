package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeUVPolarCoord
 */
class VisualShaderNodeUVPolarCoord(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUVPolarCoord? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUVPolarCoord? =
            if (handle.address() == 0L) null else VisualShaderNodeUVPolarCoord(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
