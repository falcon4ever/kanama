package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeVectorLen
 */
class VisualShaderNodeVectorLen(handle: MemorySegment) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVectorLen? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVectorLen? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorLen(handle)

        // No MethodBinds emitted yet.
    }
}
