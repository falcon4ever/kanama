package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Returns the distance between two points. To be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorDistance
 */
class VisualShaderNodeVectorDistance(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorDistance? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorDistance? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorDistance(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorDistance? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorDistance(GodotHandle(handle))
    }
}
