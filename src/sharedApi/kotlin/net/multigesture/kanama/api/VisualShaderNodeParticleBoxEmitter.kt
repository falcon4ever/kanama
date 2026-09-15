package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleBoxEmitter
 */
class VisualShaderNodeParticleBoxEmitter(handle: GodotHandle) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleBoxEmitter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleBoxEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleBoxEmitter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
