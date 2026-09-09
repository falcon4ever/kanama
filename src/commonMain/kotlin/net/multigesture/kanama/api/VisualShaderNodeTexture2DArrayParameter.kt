package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTexture2DArrayParameter
 */
class VisualShaderNodeTexture2DArrayParameter(handle: MemorySegment) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTexture2DArrayParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTexture2DArrayParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture2DArrayParameter(handle)

        // No MethodBinds emitted yet.
    }
}
