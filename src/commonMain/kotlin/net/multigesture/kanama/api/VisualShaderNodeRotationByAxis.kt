package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeRotationByAxis
 */
class VisualShaderNodeRotationByAxis(handle: MemorySegment) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeRotationByAxis? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeRotationByAxis? =
            if (handle.address() == 0L) null else VisualShaderNodeRotationByAxis(handle)

        // No MethodBinds emitted yet.
    }
}
