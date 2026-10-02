package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTextureSDFNormal
 */
class VisualShaderNodeTextureSDFNormal(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureSDFNormal? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTextureSDFNormal? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDFNormal(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
