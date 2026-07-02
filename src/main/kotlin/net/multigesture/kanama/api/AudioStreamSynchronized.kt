package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: AudioStreamSynchronized
 */
class AudioStreamSynchronized(handle: MemorySegment) : AudioStream(handle) {
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

    fun setSyncStream(streamIndex: Int, audioStream: AudioStream?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSyncStreamBind, handle, streamIndex, audioStream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSyncStream(streamIndex: Int): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSyncStreamBind, handle, streamIndex))
    }

    fun setSyncStreamVolume(streamIndex: Int, volumeDb: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setSyncStreamVolumeBind, handle, streamIndex, volumeDb)
    }

    fun getSyncStreamVolume(streamIndex: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getSyncStreamVolumeBind, handle, streamIndex)
    }

    companion object {
        const val MAX_STREAMS: Long = 32L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamSynchronized? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamSynchronized? =
            if (handle.address() == 0L) null else AudioStreamSynchronized(handle)

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
