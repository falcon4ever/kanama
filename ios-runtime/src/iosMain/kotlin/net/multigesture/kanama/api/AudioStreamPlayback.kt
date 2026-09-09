package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlayback
 */
open class AudioStreamPlayback(handle: MemorySegment) : RefCounted(handle) {
    fun setSamplePlayback(playbackSample: AudioSamplePlayback?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setSamplePlaybackBind, handle, listOf(playbackSample?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSamplePlayback(): AudioSamplePlayback? {
        checkOpen()
        return AudioSamplePlayback.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSamplePlaybackBind, handle))
    }

    fun start(fromPos: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(startBind, handle, fromPos)
    }

    fun seek(time: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(seekBind, handle, time)
    }

    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun getLoopCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopCountBind, handle)
    }

    fun getPlaybackPosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPlaybackPositionBind, handle)
    }

    fun isPlaying(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPlayingBind, handle)
    }

    companion object {
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
