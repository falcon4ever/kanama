package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTexture2DArrayParameter
 */
class VisualShaderNodeTexture2DArrayParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture2DArrayParameter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeTexture2DArrayParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture2DArrayParameter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
