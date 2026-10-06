package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeProximityFade
 */
class VisualShaderNodeProximityFade(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeProximityFade? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeProximityFade? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeProximityFade(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeProximityFade? =
            if (handle.address() == 0L) null else VisualShaderNodeProximityFade(GodotHandle(handle))
    }
}
