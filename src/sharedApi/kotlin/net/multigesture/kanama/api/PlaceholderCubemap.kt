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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PlaceholderCubemap? =
            if (handle.address() == 0L) null else PlaceholderCubemap(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
