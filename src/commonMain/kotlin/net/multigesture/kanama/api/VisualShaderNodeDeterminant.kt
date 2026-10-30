package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Calculates the determinant of a `Transform3D` within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeDeterminant
 */
class VisualShaderNodeDeterminant(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDeterminant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeDeterminant? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeDeterminant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeDeterminant? =
            if (handle.address() == 0L) null else VisualShaderNodeDeterminant(GodotHandle(handle))

        /** A new VisualShaderNodeDeterminant, GDScript's `VisualShaderNodeDeterminant.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeDeterminant =
            RefCounted.owned(VisualShaderNodeDeterminant(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeDeterminant"), "VisualShaderNodeDeterminant"))))
    }
}
