package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Playback class used for `AudioStreamOggVorbis`.
 *
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

        /** A new AudioStreamPlaybackOggVorbis, GDScript's `AudioStreamPlaybackOggVorbis.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioStreamPlaybackOggVorbis =
            RefCounted.owned(AudioStreamPlaybackOggVorbis(GodotHandle(requireConstructed(ObjectCalls.constructObject("AudioStreamPlaybackOggVorbis"), "AudioStreamPlaybackOggVorbis"))))
    }
}
