package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Returns the vector that points in the same direction as a reference vector within the visual
 * shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeFaceForward
 */
class VisualShaderNodeFaceForward(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFaceForward? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFaceForward? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFaceForward(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFaceForward? =
            if (handle.address() == 0L) null else VisualShaderNodeFaceForward(GodotHandle(handle))

        /** A new VisualShaderNodeFaceForward, GDScript's `VisualShaderNodeFaceForward.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFaceForward =
            RefCounted.owned(VisualShaderNodeFaceForward(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeFaceForward"))))
    }
}
