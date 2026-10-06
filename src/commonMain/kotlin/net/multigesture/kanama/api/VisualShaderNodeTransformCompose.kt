package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformCompose
 */
class VisualShaderNodeTransformCompose(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformCompose? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformCompose? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformCompose(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformCompose? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformCompose(GodotHandle(handle))
    }
}
