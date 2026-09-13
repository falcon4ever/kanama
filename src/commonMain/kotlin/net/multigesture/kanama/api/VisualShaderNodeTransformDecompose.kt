package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeTransformDecompose
 */
class VisualShaderNodeTransformDecompose(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformDecompose? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformDecompose? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformDecompose(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
