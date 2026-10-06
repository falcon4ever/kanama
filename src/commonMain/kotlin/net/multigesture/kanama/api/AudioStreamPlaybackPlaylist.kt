package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: AudioStreamPlaybackPlaylist
 */
class AudioStreamPlaybackPlaylist(handle: GodotHandle) : AudioStreamPlayback(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackPlaylist? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPlaybackPlaylist? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPlaybackPlaylist(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPlaybackPlaylist? =
            if (handle.address() == 0L) null else AudioStreamPlaybackPlaylist(GodotHandle(handle))
    }
}
