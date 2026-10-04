package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTextureSDFNormal
 */
class VisualShaderNodeTextureSDFNormal(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureSDFNormal? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTextureSDFNormal? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTextureSDFNormal(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTextureSDFNormal? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDFNormal(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
