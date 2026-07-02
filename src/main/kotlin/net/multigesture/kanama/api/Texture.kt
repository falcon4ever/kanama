package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for all texture types.
 *
 * Generated from Godot docs: Texture
 */
open class Texture(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Texture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Texture? =
            if (handle.address() == 0L) null else Texture(handle)

        // No MethodBinds emitted yet.
    }
}
