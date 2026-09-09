package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeGlobalExpression
 */
class VisualShaderNodeGlobalExpression(handle: MemorySegment) : VisualShaderNodeExpression(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeGlobalExpression? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeGlobalExpression? =
            if (handle.address() == 0L) null else VisualShaderNodeGlobalExpression(handle)

        // No MethodBinds emitted yet.
    }
}
