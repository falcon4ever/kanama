package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVectorCompose
 */
class VisualShaderNodeVectorCompose(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorCompose? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVectorCompose? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorCompose(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
