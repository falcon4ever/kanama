package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleSphereEmitter
 */
class VisualShaderNodeParticleSphereEmitter(handle: MemorySegment) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleSphereEmitter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleSphereEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleSphereEmitter(handle)

        // No MethodBinds emitted yet.
    }
}
