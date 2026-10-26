package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Decomposes a `Vector2`, `Vector3` or 4D vector (represented as a `Quaternion`) into scalars
 * within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorDecompose
 */
class VisualShaderNodeVectorDecompose(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorDecompose? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorDecompose? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorDecompose(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorDecompose? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorDecompose(GodotHandle(handle))

        /** A new VisualShaderNodeVectorDecompose, GDScript's `VisualShaderNodeVectorDecompose.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVectorDecompose =
            RefCounted.owned(VisualShaderNodeVectorDecompose(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeVectorDecompose"), "VisualShaderNodeVectorDecompose"))))
    }
}
