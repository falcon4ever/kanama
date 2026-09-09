package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleBoxEmitter
 */
class VisualShaderNodeParticleBoxEmitter(handle: MemorySegment) : VisualShaderNodeParticleEmitter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleBoxEmitter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleBoxEmitter? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleBoxEmitter(handle)

        // No MethodBinds emitted yet.
    }
}
