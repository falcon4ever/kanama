package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Playback class used for resampled `AudioStream`s.
 *
 * Generated from Godot docs: AudioStreamPlaybackResampled
 */
open class AudioStreamPlaybackResampled(handle: MemorySegment) : AudioStreamPlayback(handle) {
    fun beginResample() {
        ObjectCalls.ptrcallNoArgs(beginResampleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaybackResampled? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackResampled? =
            if (handle.address() == 0L) null else AudioStreamPlaybackResampled(handle)

        private const val BEGIN_RESAMPLE_HASH = 3218959716L
        private val beginResampleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackResampled", "begin_resample", BEGIN_RESAMPLE_HASH)
        }
    }
}
