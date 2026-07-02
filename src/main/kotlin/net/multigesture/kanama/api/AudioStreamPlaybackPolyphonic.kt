package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Playback instance for `AudioStreamPolyphonic`.
 *
 * Generated from Godot docs: AudioStreamPlaybackPolyphonic
 */
class AudioStreamPlaybackPolyphonic(handle: MemorySegment) : AudioStreamPlayback(handle) {
    fun playStream(stream: AudioStream?, fromOffset: Double = 0.0, volumeDb: Double = 0.0, pitchScale: Double = 1.0, playbackType: Long = 0L, bus: String = "Master"): Long {
        return ObjectCalls.ptrcallWithObjectThreeDoubleLongStringNameArgsRetLong(playStreamBind, handle, stream?.requireOpenHandle() ?: MemorySegment.NULL, fromOffset, volumeDb, pitchScale, playbackType, bus)
    }

    fun setStreamVolume(stream: Long, volumeDb: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setStreamVolumeBind, handle, stream, volumeDb)
    }

    fun setStreamPitchScale(stream: Long, pitchScale: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setStreamPitchScaleBind, handle, stream, pitchScale)
    }

    fun isStreamPlaying(stream: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isStreamPlayingBind, handle, stream)
    }

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
