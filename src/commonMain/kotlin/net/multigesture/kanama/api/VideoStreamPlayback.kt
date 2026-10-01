package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Internal class used by `VideoStream` to manage playback state when played from a
 * `VideoStreamPlayer`.
 *
 * Generated from Godot docs: VideoStreamPlayback
 */
class VideoStreamPlayback(handle: GodotHandle) : Resource(handle) {
    /**
     * Render `num_frames` audio frames (of `_get_channels` floats each) from `buffer`, starting from
     * index `offset` in the array. Returns the number of audio frames rendered, or -1 on error.
     *
     * Generated from Godot docs: VideoStreamPlayback.mix_audio
     */
    fun mixAudio(numFrames: Int, buffer: List<Float>, offset: Int = 0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntPackedFloat32ListAndIntArgsRetInt(mixAudioBind, segment, numFrames, buffer, offset)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VideoStreamPlayback? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VideoStreamPlayback? =
            if (handle.address() == 0L) null else VideoStreamPlayback(GodotHandle(handle))

        private const val MIX_AUDIO_HASH = 93876830L
        private val mixAudioBind by lazy {
            ObjectCalls.getMethodBind("VideoStreamPlayback", "mix_audio", MIX_AUDIO_HASH)
        }
    }
}
