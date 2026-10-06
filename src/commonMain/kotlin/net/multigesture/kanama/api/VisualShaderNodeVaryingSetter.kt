package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVaryingSetter
 */
class VisualShaderNodeVaryingSetter(handle: GodotHandle) : VisualShaderNodeVarying(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVaryingSetter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVaryingSetter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVaryingSetter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVaryingSetter? =
            if (handle.address() == 0L) null else VisualShaderNodeVaryingSetter(GodotHandle(handle))
    }
}
