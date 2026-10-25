package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Provides a 2D texture parameter within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTexture2DParameter
 */
class VisualShaderNodeTexture2DParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture2DParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTexture2DParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTexture2DParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTexture2DParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture2DParameter(GodotHandle(handle))

        /** A new VisualShaderNodeTexture2DParameter, GDScript's `VisualShaderNodeTexture2DParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTexture2DParameter =
            RefCounted.owned(VisualShaderNodeTexture2DParameter(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeTexture2DParameter"), "VisualShaderNodeTexture2DParameter"))))
    }
}
