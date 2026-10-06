package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Meta class for playing back audio.
 *
 * Generated from Godot docs: AudioStreamPlayback
 */
open class AudioStreamPlayback(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Associates `AudioSamplePlayback` to this `AudioStreamPlayback` for playing back the audio sample
     * of this stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.set_sample_playback
     */
    fun setSamplePlayback(playbackSample: AudioSamplePlayback?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setSamplePlaybackBind, segment, listOf(playbackSample?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the `AudioSamplePlayback` associated with this `AudioStreamPlayback` for playing back
     * the audio sample of this stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_sample_playback
     */
    fun getSamplePlayback(): AudioSamplePlayback? {
        checkOpen()
        return AudioSamplePlayback.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSamplePlaybackBind, segment))
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
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleAndIntArgsRetPackedVector2List(Binds.mixAudioBind, segment, rateScale, frames)
    }

    /**
     * Starts the stream from the given `from_pos`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.start
     */
    fun start(fromPos: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.startBind, segment, fromPos)
    }

    /**
     * Seeks the stream at the given `time`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.seek
     */
    fun seek(time: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.seekBind, segment, time)
    }

    /**
     * Stops the stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.stop
     */
    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.stopBind, segment)
    }

    /**
     * Returns the number of times the stream has looped.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_loop_count
     */
    fun getLoopCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLoopCountBind, segment)
    }

    /**
     * Returns the current position in the stream, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_playback_position
     */
    fun getPlaybackPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPlaybackPositionBind, segment)
    }

    /**
     * Returns `true` if the stream is playing.
     *
     * Generated from Godot docs: AudioStreamPlayback.is_playing
     */
    fun isPlaying(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlayback? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPlayback? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPlayback(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPlayback? =
            if (handle.address() == 0L) null else AudioStreamPlayback(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SAMPLE_PLAYBACK_HASH = 3195455091L
        @JvmField
        val setSamplePlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "set_sample_playback", SET_SAMPLE_PLAYBACK_HASH)

        private const val GET_SAMPLE_PLAYBACK_HASH = 3482738536L
        @JvmField
        val getSamplePlaybackBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_sample_playback", GET_SAMPLE_PLAYBACK_HASH)

        private const val MIX_AUDIO_HASH = 3341291446L
        @JvmField
        val mixAudioBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "mix_audio", MIX_AUDIO_HASH)

        private const val START_HASH = 1958160172L
        @JvmField
        val startBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "start", START_HASH)

        private const val SEEK_HASH = 1958160172L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "seek", SEEK_HASH)

        private const val STOP_HASH = 3218959716L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "stop", STOP_HASH)

        private const val GET_LOOP_COUNT_HASH = 3905245786L
        @JvmField
        val getLoopCountBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_loop_count", GET_LOOP_COUNT_HASH)

        private const val GET_PLAYBACK_POSITION_HASH = 1740695150L
        @JvmField
        val getPlaybackPositionBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "get_playback_position", GET_PLAYBACK_POSITION_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("AudioStreamPlayback", "is_playing", IS_PLAYING_HASH)
    }
}
