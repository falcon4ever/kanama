package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a band-limit filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectBandLimitFilter
 */
class AudioEffectBandLimitFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectBandLimitFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectBandLimitFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectBandLimitFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectBandLimitFilter? =
            if (handle.address() == 0L) null else AudioEffectBandLimitFilter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
