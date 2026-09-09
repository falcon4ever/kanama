package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTransformDecompose
 */
class VisualShaderNodeTransformDecompose(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformDecompose? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformDecompose? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformDecompose(handle)

        // No MethodBinds emitted yet.
    }
}
