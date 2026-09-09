package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerGZIP
 */
class StreamPeerGZIP(handle: MemorySegment) : StreamPeer(handle) {
    fun startCompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startCompressionBind, handle, useDeflate, bufferSize)
    }

    fun startDecompression(useDeflate: Boolean = false, bufferSize: Int = 65535): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetLong(startDecompressionBind, handle, useDeflate, bufferSize)
    }

    fun finish(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(finishBind, handle)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
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
