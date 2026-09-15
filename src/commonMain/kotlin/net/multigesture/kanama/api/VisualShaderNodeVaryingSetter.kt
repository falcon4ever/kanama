package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeVaryingSetter
 */
class VisualShaderNodeVaryingSetter(handle: GodotHandle) : VisualShaderNodeVarying(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVaryingSetter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVaryingSetter? =
            if (handle.address() == 0L) null else VisualShaderNodeVaryingSetter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
