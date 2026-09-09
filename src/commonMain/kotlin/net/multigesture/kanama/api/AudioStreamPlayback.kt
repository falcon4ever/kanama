package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setSamplePlaybackBind, handle, listOf(playbackSample?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the `AudioSamplePlayback` associated with this `AudioStreamPlayback` for playing back
     * the audio sample of this stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_sample_playback
     */
    fun getSamplePlayback(): AudioSamplePlayback? {
        checkOpen()
        return AudioSamplePlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSamplePlaybackBind, handle))
    }

    /**
     * Starts the stream from the given `from_pos`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.start
     */
    fun start(fromPos: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(startBind, handle, fromPos)
    }

    /**
     * Seeks the stream at the given `time`, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.seek
     */
    fun seek(time: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(seekBind, handle, time)
    }

    /**
     * Stops the stream.
     *
     * Generated from Godot docs: AudioStreamPlayback.stop
     */
    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    /**
     * Returns the number of times the stream has looped.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_loop_count
     */
    fun getLoopCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopCountBind, handle)
    }

    /**
     * Returns the current position in the stream, in seconds.
     *
     * Generated from Godot docs: AudioStreamPlayback.get_playback_position
     */
    fun getPlaybackPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, handle)
    }

    /**
     * Returns `true` if the stream is playing.
     *
     * Generated from Godot docs: AudioStreamPlayback.is_playing
     */
    fun isPlaying(): Boolean {
        checkOpen()
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
