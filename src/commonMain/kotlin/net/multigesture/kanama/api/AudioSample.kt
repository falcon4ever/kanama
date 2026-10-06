package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for audio samples.
 *
 * Generated from Godot docs: AudioSample
 */
class AudioSample(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioSample? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioSample? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioSample(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioSample? =
            if (handle.address() == 0L) null else AudioSample(GodotHandle(handle))
    }
}
