package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `Cubemap` without image data.
 *
 * Generated from Godot docs: PlaceholderCubemap
 */
class PlaceholderCubemap(handle: GodotHandle) : PlaceholderTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderCubemap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderCubemap? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderCubemap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderCubemap? =
            if (handle.address() == 0L) null else PlaceholderCubemap(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
