package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: AudioStreamPlaybackSynchronized
 */
class AudioStreamPlaybackSynchronized(handle: GodotHandle) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackSynchronized? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPlaybackSynchronized? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPlaybackSynchronized(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPlaybackSynchronized? =
            if (handle.address() == 0L) null else AudioStreamPlaybackSynchronized(GodotHandle(handle))
    }
}
