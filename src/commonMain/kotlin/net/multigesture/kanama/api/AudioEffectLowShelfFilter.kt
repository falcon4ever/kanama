package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a low-shelf filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectLowShelfFilter
 */
class AudioEffectLowShelfFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectLowShelfFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectLowShelfFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectLowShelfFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectLowShelfFilter? =
            if (handle.address() == 0L) null else AudioEffectLowShelfFilter(GodotHandle(handle))
    }
}
