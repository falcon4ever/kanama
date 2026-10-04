package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeLinearSceneDepth
 */
class VisualShaderNodeLinearSceneDepth(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeLinearSceneDepth? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeLinearSceneDepth? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeLinearSceneDepth(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeLinearSceneDepth? =
            if (handle.address() == 0L) null else VisualShaderNodeLinearSceneDepth(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
