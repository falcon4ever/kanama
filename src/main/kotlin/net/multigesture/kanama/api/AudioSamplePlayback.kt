package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Meta class for playing back audio samples.
 *
 * Generated from Godot docs: AudioSamplePlayback
 */
class AudioSamplePlayback(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioSamplePlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioSamplePlayback? =
            if (handle.address() == 0L) null else AudioSamplePlayback(handle)

        // No MethodBinds emitted yet.
    }
}
