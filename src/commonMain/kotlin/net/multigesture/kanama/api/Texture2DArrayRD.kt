package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Texture Array for 2D that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: Texture2DArrayRD
 */
class Texture2DArrayRD(handle: GodotHandle) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture2DArrayRD? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture2DArrayRD? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture2DArrayRD(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture2DArrayRD? =
            if (handle.address() == 0L) null else Texture2DArrayRD(GodotHandle(handle))

        /** A new Texture2DArrayRD, GDScript's `Texture2DArrayRD.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): Texture2DArrayRD =
            RefCounted.owned(Texture2DArrayRD(GodotHandle(requireConstructed(ObjectCalls.constructObject("Texture2DArrayRD"), "Texture2DArrayRD"))))
    }
}
