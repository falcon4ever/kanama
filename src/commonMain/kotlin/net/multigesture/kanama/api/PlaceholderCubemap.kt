package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A `Cubemap` without image data.
 *
 * Generated from Godot docs: PlaceholderCubemap
 */
class PlaceholderCubemap(handle: MemorySegment) : PlaceholderTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderCubemap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderCubemap? =
            if (handle.address() == 0L) null else PlaceholderCubemap(handle)

        // No MethodBinds emitted yet.
    }
}
