package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVectorLen
 */
class VisualShaderNodeVectorLen(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorLen? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorLen? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorLen(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorLen? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorLen(GodotHandle(handle))
    }
}
