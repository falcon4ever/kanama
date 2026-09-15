package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeVectorDecompose
 */
class VisualShaderNodeVectorDecompose(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorDecompose? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVectorDecompose? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorDecompose(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
