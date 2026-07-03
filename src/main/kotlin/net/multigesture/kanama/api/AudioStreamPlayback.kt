package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Meta class for playing back audio.
 *
 * Generated from Godot docs: AudioStreamPlayback
 */
open class AudioStreamPlayback(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Associates `AudioSamplePlayback` to this `AudioStreamPlayback` for playing back the audio sample
     * of this stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.set_sample_playback
     */
    fun setSamplePlayback(playbackSample: AudioSamplePlayback?) {
        ObjectCalls.ptrcallWithObjectArgs(setSamplePlaybackBind, handle, listOf(playbackSample?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the `AudioSamplePlayback` associated with this `AudioStreamPlayback` for playing back
     * the audio sample of this stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_sample_playback
     */
    fun getSamplePlayback(): AudioSamplePlayback? {
        return AudioSamplePlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSamplePlaybackBind, handle))
    }

    /**
     * Mixes up to `frames` of audio from the stream from the current position, at a rate of
     * `rate_scale`, advancing the stream. Returns a `PackedVector2Array` where each element holds the
     * left and right channel volume levels of each frame. Note: Can return fewer frames than
     * requested, make sure to use the size of the return value.
     *
     * Generated from Godot docs: AudioStreamPlayback.mix_audio
     */
    fun mixAudio(rateScale: Double, frames: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithDoubleAndIntArgsRetPackedVector2List(mixAudioBind, handle, rateScale, frames)
    }

    /**
     * Starts the stream from the given `from_pos`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.start
     */
    fun start(fromPos: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(startBind, handle, fromPos)
    }

    /**
     * Seeks the stream at the given `time`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.seek
     */
    fun seek(time: Double = 0.0) {
        ObjectCalls.ptrcallWithDoubleArg(seekBind, handle, time)
    }

    /**
     * Stops the stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    /**
     * Returns the number of times the stream has looped.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_loop_count
     */
    fun getLoopCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopCountBind, handle)
    }

    /**
     * Returns the current position in the stream, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_playback_position
     */
    fun getPlaybackPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, handle)
    }

    /**
     * Returns `true` if the stream is playing.
     *
     * Generated from Godot docs: AudioStreamPlayback.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlayback? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlayback? =
            if (handle.address() == 0L) null else AudioStreamPlayback(handle)

        private const val SET_SAMPLE_PLAYBACK_HASH = 3195455091L
        private val setSamplePlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "set_sample_playback", SET_SAMPLE_PLAYBACK_HASH)
        }

        private const val GET_SAMPLE_PLAYBACK_HASH = 3482738536L
        private val getSamplePlaybackBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_sample_playback", GET_SAMPLE_PLAYBACK_HASH)
        }

        private const val MIX_AUDIO_HASH = 3341291446L
        private val mixAudioBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "mix_audio", MIX_AUDIO_HASH)
        }

        private const val START_HASH = 1958160172L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "start", START_HASH)
        }

        private const val SEEK_HASH = 1958160172L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "seek", SEEK_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "stop", STOP_HASH)
        }

        private const val GET_LOOP_COUNT_HASH = 3905245786L
        private val getLoopCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_loop_count", GET_LOOP_COUNT_HASH)
        }

        private const val GET_PLAYBACK_POSITION_HASH = 1740695150L
        private val getPlaybackPositionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_playback_position", GET_PLAYBACK_POSITION_HASH)
        }

        private const val IS_PLAYING_HASH = 36873697L
        private val isPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlayback", "is_playing", IS_PLAYING_HASH)
        }
    }
}
