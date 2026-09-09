package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTextureParameterTriplanar
 */
class VisualShaderNodeTextureParameterTriplanar(handle: MemorySegment) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTextureParameterTriplanar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTextureParameterTriplanar? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureParameterTriplanar(handle)

        // No MethodBinds emitted yet.
    }
}
