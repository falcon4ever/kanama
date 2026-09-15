package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeVaryingGetter
 */
class VisualShaderNodeVaryingGetter(handle: GodotHandle) : VisualShaderNodeVarying(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVaryingGetter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVaryingGetter? =
            if (handle.address() == 0L) null else VisualShaderNodeVaryingGetter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
