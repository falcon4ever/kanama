package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Stores information about the audio buses.
 *
 * Generated from Godot docs: AudioBusLayout
 */
class AudioBusLayout(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioBusLayout? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioBusLayout? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioBusLayout(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioBusLayout? =
            if (handle.address() == 0L) null else AudioBusLayout(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
