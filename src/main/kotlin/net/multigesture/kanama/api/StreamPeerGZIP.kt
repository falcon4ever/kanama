package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A stream peer that handles GZIP and deflate compression/decompression.
 *
 * Generated from Godot docs: StreamPeerGZIP
 */
class StreamPeerGZIP(handle: MemorySegment) : StreamPeer(handle) {
    fun startCompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startCompressionBind, handle, useDeflate, bufferSize)
    }

    fun startDecompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startDecompressionBind, handle, useDeflate, bufferSize)
    }

    /**
     * Finalizes the stream, compressing any buffered chunk left. You must call it only when you are
     * compressing.
     *
     * Generated from Godot docs: StreamPeerGZIP.finish
     */
    fun finish(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(finishBind, handle)
    }

    /**
     * Clears this stream, resetting the internal state.
     *
     * Generated from Godot docs: StreamPeerGZIP.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StreamPeerGZIP? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerGZIP? =
            if (handle.address() == 0L) null else StreamPeerGZIP(handle)

        private const val START_COMPRESSION_HASH = 781582770L
        private val startCompressionBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerGZIP", "start_compression", START_COMPRESSION_HASH)
        }

        private const val START_DECOMPRESSION_HASH = 781582770L
        private val startDecompressionBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerGZIP", "start_decompression", START_DECOMPRESSION_HASH)
        }

        private const val FINISH_HASH = 166280745L
        private val finishBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerGZIP", "finish", FINISH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerGZIP", "clear", CLEAR_HASH)
        }
    }
}
