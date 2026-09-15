package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `CubemapArray` without image data.
 *
 * Generated from Godot docs: PlaceholderCubemapArray
 */
class PlaceholderCubemapArray(handle: GodotHandle) : PlaceholderTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderCubemapArray? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PlaceholderCubemapArray? =
            if (handle.address() == 0L) null else PlaceholderCubemapArray(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
