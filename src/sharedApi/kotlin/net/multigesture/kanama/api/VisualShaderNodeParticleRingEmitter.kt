package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleRingEmitter
 */
class VisualShaderNodeParticleRingEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleRingEmitter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleRingEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleRingEmitter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
