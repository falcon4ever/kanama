package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a high-shelf filter to an audio bus.
 *
 * Generated from Godot docs: AudioEffectHighShelfFilter
 */
class AudioEffectHighShelfFilter(handle: GodotHandle) : AudioEffectFilter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectHighShelfFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectHighShelfFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectHighShelfFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectHighShelfFilter? =
            if (handle.address() == 0L) null else AudioEffectHighShelfFilter(GodotHandle(handle))
    }
}
