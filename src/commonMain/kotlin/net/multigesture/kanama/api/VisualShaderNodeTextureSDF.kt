package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs an SDF (signed-distance field) texture lookup within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTextureSDF
 */
class VisualShaderNodeTextureSDF(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTextureSDF? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTextureSDF(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTextureSDF? =
            if (handle.address() == 0L) null else VisualShaderNodeTextureSDF(GodotHandle(handle))

        /** A new VisualShaderNodeTextureSDF, GDScript's `VisualShaderNodeTextureSDF.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTextureSDF =
            RefCounted.owned(VisualShaderNodeTextureSDF(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeTextureSDF"))))
    }
}
