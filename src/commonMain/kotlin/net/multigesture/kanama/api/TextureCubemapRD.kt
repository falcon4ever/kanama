package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Texture for Cubemap that is bound to a texture created on the `RenderingDevice`.
 *
 * Generated from Godot docs: TextureCubemapRD
 */
class TextureCubemapRD(handle: GodotHandle) : TextureLayeredRD(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureCubemapRD? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextureCubemapRD? =
            if (handle.address() == 0L) null else RefCounted.owned(TextureCubemapRD(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextureCubemapRD? =
            if (handle.address() == 0L) null else TextureCubemapRD(GodotHandle(handle))

        /** A new TextureCubemapRD, GDScript's `TextureCubemapRD.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): TextureCubemapRD =
            RefCounted.owned(TextureCubemapRD(GodotHandle(requireConstructed(ObjectCalls.constructObject("TextureCubemapRD"), "TextureCubemapRD"))))
    }
}
