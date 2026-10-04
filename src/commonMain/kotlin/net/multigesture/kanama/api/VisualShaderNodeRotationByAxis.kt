package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeRotationByAxis
 */
class VisualShaderNodeRotationByAxis(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeRotationByAxis? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeRotationByAxis? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeRotationByAxis(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeRotationByAxis? =
            if (handle.address() == 0L) null else VisualShaderNodeRotationByAxis(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
