package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A stream peer that handles TLS connections.
 *
 * Generated from Godot docs: StreamPeerTLS
 */
class StreamPeerTLS(handle: MemorySegment) : StreamPeer(handle) {
    /**
     * Poll the connection to check for incoming bytes. Call this right before
     * `StreamPeer.get_available_bytes` for it to work properly.
     *
     * Generated from Godot docs: StreamPeerTLS.poll
     */
    fun poll() {
        ObjectCalls.ptrcallNoArgs(pollBind, handle)
    }

    fun acceptStream(stream: StreamPeer?, serverOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithTwoObjectArgsRetLong(acceptStreamBind, handle, stream?.requireOpenHandle() ?: MemorySegment.NULL, serverOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun connectToStream(stream: StreamPeer?, commonName: String, clientOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(connectToStreamBind, handle, stream?.requireOpenHandle() ?: MemorySegment.NULL, commonName, clientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, handle)
    }

    fun getStream(): StreamPeer? {
        return StreamPeer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, handle))
    }

    fun disconnectFromStream() {
        ObjectCalls.ptrcallNoArgs(disconnectFromStreamBind, handle)
    }

    companion object {
        const val STATUS_DISCONNECTED: Long = 0L
        const val STATUS_HANDSHAKING: Long = 1L
        const val STATUS_CONNECTED: Long = 2L
        const val STATUS_ERROR: Long = 3L
        const val STATUS_ERROR_HOSTNAME_MISMATCH: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): StreamPeerTLS? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerTLS? =
            if (handle.address() == 0L) null else StreamPeerTLS(handle)

        private const val POLL_HASH = 3218959716L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "poll", POLL_HASH)
        }

        private const val ACCEPT_STREAM_HASH = 4292689651L
        private val acceptStreamBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "accept_stream", ACCEPT_STREAM_HASH)
        }

        private const val CONNECT_TO_STREAM_HASH = 57169517L
        private val connectToStreamBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "connect_to_stream", CONNECT_TO_STREAM_HASH)
        }

        private const val GET_STATUS_HASH = 1128380576L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "get_status", GET_STATUS_HASH)
        }

        private const val GET_STREAM_HASH = 2741655269L
        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "get_stream", GET_STREAM_HASH)
        }

        private const val DISCONNECT_FROM_STREAM_HASH = 3218959716L
        private val disconnectFromStreamBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTLS", "disconnect_from_stream", DISCONNECT_FROM_STREAM_HASH)
        }
    }
}
