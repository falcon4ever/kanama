package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: AudioStreamPlaybackOggVorbis
 */
class AudioStreamPlaybackOggVorbis(handle: GodotHandle) : AudioStreamPlaybackResampled(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackOggVorbis? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPlaybackOggVorbis? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPlaybackOggVorbis(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPlaybackOggVorbis? =
            if (handle.address() == 0L) null else AudioStreamPlaybackOggVorbis(GodotHandle(handle))
    }
}
