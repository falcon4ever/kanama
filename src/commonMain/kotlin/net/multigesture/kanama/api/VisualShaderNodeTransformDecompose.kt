package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformDecompose
 */
class VisualShaderNodeTransformDecompose(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformDecompose? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformDecompose? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformDecompose(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformDecompose? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformDecompose(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
