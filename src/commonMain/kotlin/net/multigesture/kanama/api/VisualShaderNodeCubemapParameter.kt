package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeCubemapParameter
 */
class VisualShaderNodeCubemapParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeCubemapParameter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeCubemapParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeCubemapParameter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
