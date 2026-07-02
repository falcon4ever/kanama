package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Internal class used by `VideoStream` to manage playback state when played from a
 * `VideoStreamPlayer`.
 *
 * Generated from Godot docs: VideoStreamPlayback
 */
class VideoStreamPlayback(handle: MemorySegment) : Resource(handle) {
    fun mixAudio(numFrames: Int, buffer: List<Float>, offset: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntPackedFloat32ListAndIntArgsRetInt(mixAudioBind, handle, numFrames, buffer, offset)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VideoStreamPlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VideoStreamPlayback? =
            if (handle.address() == 0L) null else VideoStreamPlayback(handle)

        private const val MIX_AUDIO_HASH = 93876830L
        private val mixAudioBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayback", "mix_audio", MIX_AUDIO_HASH)
        }
    }
}
