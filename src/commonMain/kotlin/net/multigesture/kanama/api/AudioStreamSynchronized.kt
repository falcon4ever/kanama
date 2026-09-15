package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamSynchronized
 */
class AudioStreamSynchronized(handle: GodotHandle) : AudioStream(handle) {
    var streamCount: Int
        @JvmName("streamCountProperty")
        get() = getStreamCount()
        @JvmName("setStreamCountProperty")
        set(value) = setStreamCount(value)

    fun setStreamCount(streamCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setStreamCountBind, segment, streamCount)
    }

    fun getStreamCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getStreamCountBind, segment)
    }

    fun setSyncStream(streamIndex: Int, audioStream: AudioStream?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setSyncStreamBind, segment, streamIndex, audioStream?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun getSyncStream(streamIndex: Int): AudioStream? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(getSyncStreamBind, segment, streamIndex)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AudioStream.wrap(ret)
    }

    fun setSyncStreamVolume(streamIndex: Int, volumeDb: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setSyncStreamVolumeBind, segment, streamIndex, volumeDb)
    }

    fun getSyncStreamVolume(streamIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getSyncStreamVolumeBind, segment, streamIndex)
    }

    companion object {
        const val MAX_STREAMS: Long = 32L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamSynchronized? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamSynchronized? =
            if (handle.address() == 0L) null else AudioStreamSynchronized(GodotHandle(handle))

        private const val SET_STREAM_COUNT_HASH = 1286410249L
        private val setStreamCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "set_stream_count", SET_STREAM_COUNT_HASH)
        }

        private const val GET_STREAM_COUNT_HASH = 3905245786L
        private val getStreamCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "get_stream_count", GET_STREAM_COUNT_HASH)
        }

        private const val SET_SYNC_STREAM_HASH = 111075094L
        private val setSyncStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "set_sync_stream", SET_SYNC_STREAM_HASH)
        }

        private const val GET_SYNC_STREAM_HASH = 2739380747L
        private val getSyncStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "get_sync_stream", GET_SYNC_STREAM_HASH)
        }

        private const val SET_SYNC_STREAM_VOLUME_HASH = 1602489585L
        private val setSyncStreamVolumeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "set_sync_stream_volume", SET_SYNC_STREAM_VOLUME_HASH)
        }

        private const val GET_SYNC_STREAM_VOLUME_HASH = 2339986948L
        private val getSyncStreamVolumeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamSynchronized", "get_sync_stream_volume", GET_SYNC_STREAM_VOLUME_HASH)
        }
    }
}
