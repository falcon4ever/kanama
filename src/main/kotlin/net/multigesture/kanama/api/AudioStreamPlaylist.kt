package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: AudioStreamPlaylist
 */
class AudioStreamPlaylist(handle: MemorySegment) : AudioStream(handle) {
    var shuffle: Boolean
        @JvmName("shuffleProperty")
        get() = getShuffle()
        @JvmName("setShuffleProperty")
        set(value) = setShuffle(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = hasLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    var fadeTime: Double
        @JvmName("fadeTimeProperty")
        get() = getFadeTime()
        @JvmName("setFadeTimeProperty")
        set(value) = setFadeTime(value)

    var streamCount: Int
        @JvmName("streamCountProperty")
        get() = getStreamCount()
        @JvmName("setStreamCountProperty")
        set(value) = setStreamCount(value)

    fun setStreamCount(streamCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setStreamCountBind, handle, streamCount)
    }

    fun getStreamCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStreamCountBind, handle)
    }

    fun getBpm(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBpmBind, handle)
    }

    fun setListStream(streamIndex: Int, audioStream: AudioStream?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setListStreamBind, handle, streamIndex, audioStream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getListStream(streamIndex: Int): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getListStreamBind, handle, streamIndex))
    }

    fun setShuffle(shuffle: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShuffleBind, handle, shuffle)
    }

    fun getShuffle(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShuffleBind, handle)
    }

    fun setFadeTime(dec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFadeTimeBind, handle, dec)
    }

    fun getFadeTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadeTimeBind, handle)
    }

    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, loop)
    }

    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasLoopBind, handle)
    }

    companion object {
        const val MAX_STREAMS: Long = 64L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamPlaylist? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamPlaylist? =
            if (handle.address() == 0L) null else AudioStreamPlaylist(handle)

        private const val SET_STREAM_COUNT_HASH = 1286410249L
        private val setStreamCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "set_stream_count", SET_STREAM_COUNT_HASH)
        }

        private const val GET_STREAM_COUNT_HASH = 3905245786L
        private val getStreamCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "get_stream_count", GET_STREAM_COUNT_HASH)
        }

        private const val GET_BPM_HASH = 1740695150L
        private val getBpmBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "get_bpm", GET_BPM_HASH)
        }

        private const val SET_LIST_STREAM_HASH = 111075094L
        private val setListStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "set_list_stream", SET_LIST_STREAM_HASH)
        }

        private const val GET_LIST_STREAM_HASH = 2739380747L
        private val getListStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "get_list_stream", GET_LIST_STREAM_HASH)
        }

        private const val SET_SHUFFLE_HASH = 2586408642L
        private val setShuffleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "set_shuffle", SET_SHUFFLE_HASH)
        }

        private const val GET_SHUFFLE_HASH = 36873697L
        private val getShuffleBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "get_shuffle", GET_SHUFFLE_HASH)
        }

        private const val SET_FADE_TIME_HASH = 373806689L
        private val setFadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "set_fade_time", SET_FADE_TIME_HASH)
        }

        private const val GET_FADE_TIME_HASH = 1740695150L
        private val getFadeTimeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "get_fade_time", GET_FADE_TIME_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaylist", "has_loop", HAS_LOOP_HASH)
        }
    }
}
