package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A stream peer that handles TLS connections.
 *
 * Generated from Godot docs: StreamPeerTLS
 */
class StreamPeerTLS(handle: GodotHandle) : StreamPeer(handle) {
    /**
     * Poll the connection to check for incoming bytes. Call this right before
     * `StreamPeer.get_available_bytes` for it to work properly.
     *
     * Generated from Godot docs: StreamPeerTLS.poll
     */
    fun poll() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(pollBind, segment)
    }

    /**
     * Accepts a peer connection as a server using the given `server_options`. See `TLSOptions.server`.
     *
     * Generated from Godot docs: StreamPeerTLS.accept_stream
     */
    fun acceptStream(stream: StreamPeer?, serverOptions: TLSOptions?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoObjectArgsRetLong(acceptStreamBind, segment, stream?.requireOpenHandle() ?: NULL_SEGMENT, serverOptions?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Connects to a peer using an underlying `StreamPeer` `stream` and verifying the remote
     * certificate is correctly signed for the given `common_name`. You can pass the optional
     * `client_options` parameter to customize the trusted certification authorities, or disable the
     * common name verification. See `TLSOptions.client` and `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: StreamPeerTLS.connect_to_stream
     */
    fun connectToStream(stream: StreamPeer?, commonName: String, clientOptions: TLSOptions?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(connectToStreamBind, segment, stream?.requireOpenHandle() ?: NULL_SEGMENT, commonName, clientOptions?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the status of the connection.
     *
     * Generated from Godot docs: StreamPeerTLS.get_status
     */
    fun getStatus(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, segment)
    }

    /**
     * Returns the underlying `StreamPeer` connection, used in `accept_stream` or `connect_to_stream`.
     *
     * Generated from Godot docs: StreamPeerTLS.get_stream
     */
    fun getStream(): StreamPeer? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getStreamBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return StreamPeer.wrap(ret)
    }

    /**
     * Disconnects from host.
     *
     * Generated from Godot docs: StreamPeerTLS.disconnect_from_stream
     */
    fun disconnectFromStream() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(disconnectFromStreamBind, segment)
    }

    companion object {
        const val STATUS_DISCONNECTED: Long = 0L
        const val STATUS_HANDSHAKING: Long = 1L
        const val STATUS_CONNECTED: Long = 2L
        const val STATUS_ERROR: Long = 3L
        const val STATUS_ERROR_HOSTNAME_MISMATCH: Long = 4L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerTLS? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StreamPeerTLS? =
            if (handle.address() == 0L) null else StreamPeerTLS(GodotHandle(handle))

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
