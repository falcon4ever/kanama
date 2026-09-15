package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A stream peer that handles GZIP and deflate compression/decompression.
 *
 * Generated from Godot docs: StreamPeerGZIP
 */
class StreamPeerGZIP(handle: GodotHandle) : StreamPeer(handle) {
    /**
     * Start the stream in compression mode with the given `buffer_size`, if `use_deflate` is `true`
     * uses deflate instead of GZIP.
     *
     * Generated from Godot docs: StreamPeerGZIP.start_compression
     */
    fun startCompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startCompressionBind, segment, useDeflate, bufferSize)
    }

    /**
     * Start the stream in decompression mode with the given `buffer_size`, if `use_deflate` is `true`
     * uses deflate instead of GZIP.
     *
     * Generated from Godot docs: StreamPeerGZIP.start_decompression
     */
    fun startDecompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startDecompressionBind, segment, useDeflate, bufferSize)
    }

    /**
     * Finalizes the stream, compressing any buffered chunk left. You must call it only when you are
     * compressing.
     *
     * Generated from Godot docs: StreamPeerGZIP.finish
     */
    fun finish(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(finishBind, segment)
    }

    /**
     * Clears this stream, resetting the internal state.
     *
     * Generated from Godot docs: StreamPeerGZIP.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerGZIP? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StreamPeerGZIP? =
            if (handle.address() == 0L) null else StreamPeerGZIP(GodotHandle(handle))

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
