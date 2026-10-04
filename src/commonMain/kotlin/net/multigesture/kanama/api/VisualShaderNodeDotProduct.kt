package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeDotProduct
 */
class VisualShaderNodeDotProduct(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDotProduct? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeDotProduct? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeDotProduct(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeDotProduct? =
            if (handle.address() == 0L) null else VisualShaderNodeDotProduct(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
