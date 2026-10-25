package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Calculates an outer product of two vectors within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeOuterProduct
 */
class VisualShaderNodeOuterProduct(handle: GodotHandle) : VisualShaderNode(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeOuterProduct? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeOuterProduct? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeOuterProduct(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeOuterProduct? =
            if (handle.address() == 0L) null else VisualShaderNodeOuterProduct(GodotHandle(handle))

        /** A new VisualShaderNodeOuterProduct, GDScript's `VisualShaderNodeOuterProduct.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeOuterProduct =
            RefCounted.owned(VisualShaderNodeOuterProduct(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeOuterProduct"), "VisualShaderNodeOuterProduct"))))
    }
}
