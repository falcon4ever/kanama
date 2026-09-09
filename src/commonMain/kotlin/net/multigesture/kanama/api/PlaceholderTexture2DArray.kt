package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Placeholder class for a 2-dimensional texture array.
 *
 * Generated from Godot docs: PlaceholderTexture2DArray
 */
class PlaceholderTexture2DArray(handle: MemorySegment) : PlaceholderTextureLayered(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderTexture2DArray? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderTexture2DArray? =
            if (handle.address() == 0L) null else PlaceholderTexture2DArray(handle)

        // No MethodBinds emitted yet.
    }
}
