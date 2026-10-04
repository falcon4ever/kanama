package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTextureSDF
 */
class VisualShaderNodeTextureSDF(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureSDF? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTextureSDF(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
