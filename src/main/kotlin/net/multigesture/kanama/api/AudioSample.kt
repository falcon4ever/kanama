package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for audio samples.
 *
 * Generated from Godot docs: AudioSample
 */
class AudioSample(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioSample? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioSample? =
            if (handle.address() == 0L) null else AudioSample(handle)

        // No MethodBinds emitted yet.
    }
}
