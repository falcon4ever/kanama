package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParticleSphereEmitter
 */
class VisualShaderNodeParticleSphereEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleSphereEmitter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleSphereEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleSphereEmitter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
