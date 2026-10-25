package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Plays real-time audio input data.
 *
 * Generated from Godot docs: AudioStreamMicrophone
 */
class AudioStreamMicrophone(handle: GodotHandle) : AudioStream(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamMicrophone? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamMicrophone? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamMicrophone(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamMicrophone? =
            if (handle.address() == 0L) null else AudioStreamMicrophone(GodotHandle(handle))

        /** A new AudioStreamMicrophone, GDScript's `AudioStreamMicrophone.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioStreamMicrophone =
            RefCounted.owned(AudioStreamMicrophone(GodotHandle(ObjectCalls.constructObject("AudioStreamMicrophone"))))
    }
}
