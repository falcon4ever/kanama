package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Calculates a dot product of two vectors within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeDotProduct
 */
class VisualShaderNodeDotProduct(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDotProduct? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeDotProduct? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeDotProduct(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeDotProduct? =
            if (handle.address() == 0L) null else VisualShaderNodeDotProduct(GodotHandle(handle))

        /** A new VisualShaderNodeDotProduct, GDScript's `VisualShaderNodeDotProduct.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeDotProduct =
            RefCounted.owned(VisualShaderNodeDotProduct(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeDotProduct"), "VisualShaderNodeDotProduct"))))
    }
}
