package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Returns the vector that points in the direction of refraction. For use within the visual shader
 * graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorRefract
 */
class VisualShaderNodeVectorRefract(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorRefract? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorRefract? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorRefract(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorRefract? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorRefract(GodotHandle(handle))

        /** A new VisualShaderNodeVectorRefract, GDScript's `VisualShaderNodeVectorRefract.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVectorRefract =
            RefCounted.owned(VisualShaderNodeVectorRefract(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeVectorRefract"), "VisualShaderNodeVectorRefract"))))
    }
}
