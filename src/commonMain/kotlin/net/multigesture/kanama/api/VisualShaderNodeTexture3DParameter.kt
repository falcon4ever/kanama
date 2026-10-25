package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Provides a 3D texture parameter within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTexture3DParameter
 */
class VisualShaderNodeTexture3DParameter(handle: GodotHandle) : VisualShaderNodeTextureParameter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTexture3DParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTexture3DParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTexture3DParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTexture3DParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTexture3DParameter(GodotHandle(handle))

        /** A new VisualShaderNodeTexture3DParameter, GDScript's `VisualShaderNodeTexture3DParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTexture3DParameter =
            RefCounted.owned(VisualShaderNodeTexture3DParameter(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeTexture3DParameter"), "VisualShaderNodeTexture3DParameter"))))
    }
}
