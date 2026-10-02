package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
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
    fun acceptStream(stream: StreamPeer?, serverOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoObjectArgsRetLong(acceptStreamBind, segment, stream?.requireOpenHandle() ?: NULL_SEGMENT, serverOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Connects to a peer using an underlying `StreamPeer` `stream` and verifying the remote
     * certificate is correctly signed for the given `common_name`. You can pass the optional
     * `client_options` parameter to customize the trusted certification authorities, or disable the
     * common name verification. See `TLSOptions.client` and `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: StreamPeerTLS.connect_to_stream
     */
    fun connectToStream(stream: StreamPeer?, commonName: String, clientOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(connectToStreamBind, segment, stream?.requireOpenHandle() ?: NULL_SEGMENT, commonName, clientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the status of the connection.
     *
     * Generated from Godot docs: StreamPeerTLS.get_status
     */
    fun getStatus(): StreamPeerTLS.Status {
        checkOpen()
        return StreamPeerTLS.Status(ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, segment))
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

    @JvmInline
    value class Status(val value: Long) {
        companion object {
            /**
             * A status representing a `StreamPeerTLS` that is disconnected.
             *
             * Generated from Godot docs: StreamPeerTLS.STATUS_DISCONNECTED
             */
            val DISCONNECTED: Status get() = Status(0L)
            /**
             * A status representing a `StreamPeerTLS` during handshaking.
             *
             * Generated from Godot docs: StreamPeerTLS.STATUS_HANDSHAKING
             */
            val HANDSHAKING: Status get() = Status(1L)
            /**
             * A status representing a `StreamPeerTLS` that is connected to a host.
             *
             * Generated from Godot docs: StreamPeerTLS.STATUS_CONNECTED
             */
            val CONNECTED: Status get() = Status(2L)
            /**
             * A status representing a `StreamPeerTLS` in error state.
             *
             * Generated from Godot docs: StreamPeerTLS.STATUS_ERROR
             */
            val ERROR: Status get() = Status(3L)
            /**
             * An error status that shows a mismatch in the TLS certificate domain presented by the host and
             * the domain requested for validation.
             *
             * Generated from Godot docs: StreamPeerTLS.STATUS_ERROR_HOSTNAME_MISMATCH
             */
            val ERROR_HOSTNAME_MISMATCH: Status get() = Status(4L)
        }
    }

    companion object {
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
