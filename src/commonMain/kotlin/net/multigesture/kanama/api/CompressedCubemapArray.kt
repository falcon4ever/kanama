package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An optionally compressed `CubemapArray`.
 *
 * Generated from Godot docs: CompressedCubemapArray
 */
class CompressedCubemapArray(handle: GodotHandle) : CompressedTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedCubemapArray? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompressedCubemapArray? =
            if (handle.address() == 0L) null else RefCounted.owned(CompressedCubemapArray(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompressedCubemapArray? =
            if (handle.address() == 0L) null else CompressedCubemapArray(GodotHandle(handle))
    }
}
