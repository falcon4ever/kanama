package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerSocket
 */
open class StreamPeerSocket(handle: MemorySegment) : StreamPeer(handle) {
    fun poll(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun getStatus(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, handle)
    }

    fun disconnectFromHost() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(disconnectFromHostBind, handle)
    }

    companion object {
        const val STATUS_NONE: Long = 0L
        const val STATUS_CONNECTING: Long = 1L
        const val STATUS_CONNECTED: Long = 2L
        const val STATUS_ERROR: Long = 3L

        fun fromHandle(handle: MemorySegment): StreamPeerSocket? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerSocket? =
            if (handle.address() == 0L) null else StreamPeerSocket(handle)

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "poll", POLL_HASH)
        }

        private const val GET_STATUS_HASH = 1156122502L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "get_status", GET_STATUS_HASH)
        }

        private const val DISCONNECT_FROM_HOST_HASH = 3218959716L
        private val disconnectFromHostBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "disconnect_from_host", DISCONNECT_FROM_HOST_HASH)
        }
    }
}
