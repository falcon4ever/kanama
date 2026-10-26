package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Array of 2-dimensional textures, optionally compressed.
 *
 * Generated from Godot docs: CompressedTexture2DArray
 */
class CompressedTexture2DArray(handle: GodotHandle) : CompressedTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTexture2DArray? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompressedTexture2DArray? =
            if (handle.address() == 0L) null else RefCounted.owned(CompressedTexture2DArray(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompressedTexture2DArray? =
            if (handle.address() == 0L) null else CompressedTexture2DArray(GodotHandle(handle))

        /** A new CompressedTexture2DArray, GDScript's `CompressedTexture2DArray.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): CompressedTexture2DArray =
            RefCounted.owned(CompressedTexture2DArray(GodotHandle(requireConstructed(ObjectCalls.constructObject("CompressedTexture2DArray"), "CompressedTexture2DArray"))))
    }
}
