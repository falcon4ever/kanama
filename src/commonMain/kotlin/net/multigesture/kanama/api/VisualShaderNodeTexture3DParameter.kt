package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTexture3DParameter
 */
class VisualShaderNodeTexture3DParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture3DParameter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTexture3DParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture3DParameter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
