package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a band-pass filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectBandPassFilter
 */
class AudioEffectBandPassFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectBandPassFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectBandPassFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectBandPassFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectBandPassFilter? =
            if (handle.address() == 0L) null else AudioEffectBandPassFilter(GodotHandle(handle))
    }
}
