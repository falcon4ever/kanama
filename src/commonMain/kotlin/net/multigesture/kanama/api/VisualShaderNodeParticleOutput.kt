package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleOutput
 */
class VisualShaderNodeParticleOutput(handle: GodotHandle) : VisualShaderNodeOutput(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleOutput? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleOutput? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleOutput(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
