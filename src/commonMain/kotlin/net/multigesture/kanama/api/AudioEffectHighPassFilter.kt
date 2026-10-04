package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a high-pass filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectHighPassFilter
 */
class AudioEffectHighPassFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectHighPassFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectHighPassFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectHighPassFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectHighPassFilter? =
            if (handle.address() == 0L) null else AudioEffectHighPassFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
