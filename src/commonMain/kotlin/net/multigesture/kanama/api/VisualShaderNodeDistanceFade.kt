package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeDistanceFade
 */
class VisualShaderNodeDistanceFade(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDistanceFade? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeDistanceFade? =
            if (handle.address() == 0L) null else VisualShaderNodeDistanceFade(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
