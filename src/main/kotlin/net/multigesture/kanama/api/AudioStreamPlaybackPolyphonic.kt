package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Playback instance for `AudioStreamPolyphonic`.
 *
 * Generated from Godot docs: AudioStreamPlaybackPolyphonic
 */
class AudioStreamPlaybackPolyphonic(handle: MemorySegment) : AudioStreamPlayback(handle) {
    /**
     * Play an `AudioStream` at a given offset, volume, pitch scale, playback type, and bus. Playback
     * starts immediately. The return value is a unique integer ID that is associated to this playback
     * stream and which can be used to control it. This ID becomes invalid when the stream ends (if it
     * does not loop), when the `AudioStreamPlaybackPolyphonic` is stopped, or when `stop_stream` is
     * called. This function returns `INVALID_ID` if the amount of streams currently playing equals
     * `AudioStreamPolyphonic.polyphony`. If you need a higher amount of maximum polyphony, raise this
     * value.
     *
     * Generated from Godot docs: AudioStreamPlaybackPolyphonic.play_stream
     */
    fun playStream(stream: AudioStream?, fromOffset: Double = 0.0, volumeDb: Double = 0.0, pitchScale: Double = 1.0, playbackType: Long = 0L, bus: String = "Master"): Long {
        return ObjectCalls.ptrcallWithObjectThreeDoubleLongStringNameArgsRetLong(playStreamBind, handle, stream?.requireOpenHandle() ?: MemorySegment.NULL, fromOffset, volumeDb, pitchScale, playbackType, bus)
    }

    /**
     * Change the stream volume (in db). The `stream` argument is an integer ID returned by
     * `play_stream`.
     *
     * Generated from Godot docs: AudioStreamPlaybackPolyphonic.set_stream_volume
     */
    fun setStreamVolume(stream: Long, volumeDb: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setStreamVolumeBind, handle, stream, volumeDb)
    }

    /**
     * Change the stream pitch scale. The `stream` argument is an integer ID returned by `play_stream`.
     *
     * Generated from Godot docs: AudioStreamPlaybackPolyphonic.set_stream_pitch_scale
     */
    fun setStreamPitchScale(stream: Long, pitchScale: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setStreamPitchScaleBind, handle, stream, pitchScale)
    }

    /**
     * Returns `true` if the stream associated with the given integer ID is still playing. Check
     * `play_stream` for information on when this ID becomes invalid.
     *
     * Generated from Godot docs: AudioStreamPlaybackPolyphonic.is_stream_playing
     */
    fun isStreamPlaying(stream: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isStreamPlayingBind, handle, stream)
    }

    /**
     * Stop a stream. The `stream` argument is an integer ID returned by `play_stream`, which becomes
     * invalid after calling this function.
     *
     * Generated from Godot docs: AudioStreamPlaybackPolyphonic.stop_stream
     */
    fun stopStream(stream: Long) {
        ObjectCalls.ptrcallWithLongArg(stopStreamBind, handle, stream)
    }

    companion object {
        const val INVALID_ID: Long = -1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaybackPolyphonic? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaybackPolyphonic? =
            if (handle.address() == 0L) null else AudioStreamPlaybackPolyphonic(handle)

        private const val PLAY_STREAM_HASH = 1846744803L
        private val playStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackPolyphonic", "play_stream", PLAY_STREAM_HASH)
        }

        private const val SET_STREAM_VOLUME_HASH = 1602489585L
        private val setStreamVolumeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackPolyphonic", "set_stream_volume", SET_STREAM_VOLUME_HASH)
        }

        private const val SET_STREAM_PITCH_SCALE_HASH = 1602489585L
        private val setStreamPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackPolyphonic", "set_stream_pitch_scale", SET_STREAM_PITCH_SCALE_HASH)
        }

        private const val IS_STREAM_PLAYING_HASH = 1116898809L
        private val isStreamPlayingBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackPolyphonic", "is_stream_playing", IS_STREAM_PLAYING_HASH)
        }

        private const val STOP_STREAM_HASH = 1286410249L
        private val stopStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackPolyphonic", "stop_stream", STOP_STREAM_HASH)
        }
    }
}
