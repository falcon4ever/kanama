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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlaybackOggVorbis? =
            if (handle.address() == 0L) null else AudioStreamPlaybackOggVorbis(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
