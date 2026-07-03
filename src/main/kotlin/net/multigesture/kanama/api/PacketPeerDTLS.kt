package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * DTLS packet peer.
 *
 * Generated from Godot docs: PacketPeerDTLS
 */
class PacketPeerDTLS(handle: MemorySegment) : PacketPeer(handle) {
    /**
     * Poll the connection to check for incoming packets. Call this frequently to update the status and
     * keep the connection working.
     *
     * Generated from Godot docs: PacketPeerDTLS.poll
     */
    fun poll() {
        ObjectCalls.ptrcallNoArgs(pollBind, handle)
    }

    /**
     * Connects a `packet_peer` beginning the DTLS handshake using the underlying `PacketPeerUDP` which
     * must be connected (see `PacketPeerUDP.connect_to_host`). You can optionally specify the
     * `client_options` to be used while verifying the TLS connections. See `TLSOptions.client` and
     * `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: PacketPeerDTLS.connect_to_peer
     */
    fun connectToPeer(packetPeer: PacketPeerUDP?, hostname: String, clientOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(connectToPeerBind, handle, packetPeer?.requireOpenHandle() ?: MemorySegment.NULL, hostname, clientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the status of the connection.
     *
     * Generated from Godot docs: PacketPeerDTLS.get_status
     */
    fun getStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, handle)
    }

    /**
     * Disconnects this peer, terminating the DTLS session.
     *
     * Generated from Godot docs: PacketPeerDTLS.disconnect_from_peer
     */
    fun disconnectFromPeer() {
        ObjectCalls.ptrcallNoArgs(disconnectFromPeerBind, handle)
    }

    companion object {
        const val STATUS_DISCONNECTED: Long = 0L
        const val STATUS_HANDSHAKING: Long = 1L
        const val STATUS_CONNECTED: Long = 2L
        const val STATUS_ERROR: Long = 3L
        const val STATUS_ERROR_HOSTNAME_MISMATCH: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PacketPeerDTLS? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PacketPeerDTLS? =
            if (handle.address() == 0L) null else PacketPeerDTLS(handle)

        private const val POLL_HASH = 3218959716L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerDTLS", "poll", POLL_HASH)
        }

        private const val CONNECT_TO_PEER_HASH = 2880188099L
        private val connectToPeerBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerDTLS", "connect_to_peer", CONNECT_TO_PEER_HASH)
        }

        private const val GET_STATUS_HASH = 3248654679L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerDTLS", "get_status", GET_STATUS_HASH)
        }

        private const val DISCONNECT_FROM_PEER_HASH = 3218959716L
        private val disconnectFromPeerBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerDTLS", "disconnect_from_peer", DISCONNECT_FROM_PEER_HASH)
        }
    }
}
