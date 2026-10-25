package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs an SDF (signed-distance field) normal texture lookup within the visual shader graph.
 *
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

        /** A new VisualShaderNodeTextureSDFNormal, GDScript's `VisualShaderNodeTextureSDFNormal.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTextureSDFNormal =
            RefCounted.owned(VisualShaderNodeTextureSDFNormal(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeTextureSDFNormal"))))
    }
}
