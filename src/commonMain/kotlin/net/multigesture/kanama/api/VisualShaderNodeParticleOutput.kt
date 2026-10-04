package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParticleOutput
 */
class VisualShaderNodeParticleOutput(handle: GodotHandle) : VisualShaderNodeOutput(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleOutput? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleOutput? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleOutput(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleOutput? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleOutput(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
