package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTextureParameterTriplanar
 */
class VisualShaderNodeTextureParameterTriplanar(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureParameterTriplanar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTextureParameterTriplanar? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureParameterTriplanar(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
