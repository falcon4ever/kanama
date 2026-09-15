package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeParticleConeVelocity
 */
class VisualShaderNodeParticleConeVelocity(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleConeVelocity? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleConeVelocity? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleConeVelocity(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
