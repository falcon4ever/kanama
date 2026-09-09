package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlaybackOggVorbis
 */
class AudioStreamPlaybackOggVorbis(handle: MemorySegment) : AudioStreamPlaybackResampled(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaybackOggVorbis? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackOggVorbis? =
            if (handle.address() == 0L) null else AudioStreamPlaybackOggVorbis(handle)

        // No MethodBinds emitted yet.
    }
}
