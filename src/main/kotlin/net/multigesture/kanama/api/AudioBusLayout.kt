package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Stores information about the audio buses.
 *
 * Generated from Godot docs: AudioBusLayout
 */
class AudioBusLayout(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioBusLayout? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioBusLayout? =
            if (handle.address() == 0L) null else AudioBusLayout(handle)

        // No MethodBinds emitted yet.
    }
}
