package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

        // No MethodBinds emitted yet.
    }
}
