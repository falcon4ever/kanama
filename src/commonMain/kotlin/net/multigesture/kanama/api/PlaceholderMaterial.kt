package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Placeholder class for a material.
 *
 * Generated from Godot docs: PlaceholderMaterial
 */
class PlaceholderMaterial(handle: GodotHandle) : Material(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderMaterial? =
            if (handle.address() == 0L) null else PlaceholderMaterial(GodotHandle(handle))

        /** A new PlaceholderMaterial, GDScript's `PlaceholderMaterial.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PlaceholderMaterial =
            RefCounted.owned(PlaceholderMaterial(GodotHandle(ObjectCalls.constructObject("PlaceholderMaterial"))))
    }
}
