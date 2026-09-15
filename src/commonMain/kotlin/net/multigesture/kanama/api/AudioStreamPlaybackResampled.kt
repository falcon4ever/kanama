package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Playback class used for resampled `AudioStream`s.
 *
 * Generated from Godot docs: AudioStreamPlaybackResampled
 */
open class AudioStreamPlaybackResampled(handle: GodotHandle) : AudioStreamPlayback(handle) {
    /**
     * Called when an `AudioStream` is played. Clears the cubic interpolation history and starts mixing
     * by calling `_mix_resampled`.
     *
     * Generated from Godot docs: AudioStreamPlaybackResampled.begin_resample
     */
    fun beginResample() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(beginResampleBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackResampled? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlaybackResampled? =
            if (handle.address() == 0L) null else AudioStreamPlaybackResampled(GodotHandle(handle))

        private const val BEGIN_RESAMPLE_HASH = 3218959716L
        private val beginResampleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackResampled", "begin_resample", BEGIN_RESAMPLE_HASH)
        }
    }
}
