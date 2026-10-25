package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A function to convert an SDF (signed-distance field) to screen UV, to be used within the visual
 * shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeSDFToScreenUV
 */
class VisualShaderNodeSDFToScreenUV(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSDFToScreenUV? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSDFToScreenUV? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSDFToScreenUV(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSDFToScreenUV? =
            if (handle.address() == 0L) null else VisualShaderNodeSDFToScreenUV(GodotHandle(handle))

        /** A new VisualShaderNodeSDFToScreenUV, GDScript's `VisualShaderNodeSDFToScreenUV.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeSDFToScreenUV =
            RefCounted.owned(VisualShaderNodeSDFToScreenUV(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeSDFToScreenUV"))))
    }
}
