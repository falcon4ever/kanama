package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for audio effect resources.
 *
 * Generated from Godot docs: AudioEffect
 */
open class AudioEffect(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffect? =
            if (handle.address() == 0L) null else AudioEffect(handle)

        // No MethodBinds emitted yet.
    }
}
