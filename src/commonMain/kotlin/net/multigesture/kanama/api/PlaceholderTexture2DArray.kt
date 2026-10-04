package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Placeholder class for a 2-dimensional texture array.
 *
 * Generated from Godot docs: PlaceholderTexture2DArray
 */
class PlaceholderTexture2DArray(handle: GodotHandle) : PlaceholderTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderTexture2DArray? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderTexture2DArray? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderTexture2DArray(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderTexture2DArray? =
            if (handle.address() == 0L) null else PlaceholderTexture2DArray(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
