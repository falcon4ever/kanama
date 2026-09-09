package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeCubemapParameter
 */
class VisualShaderNodeCubemapParameter(handle: MemorySegment) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeCubemapParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeCubemapParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeCubemapParameter(handle)

        // No MethodBinds emitted yet.
    }
}
