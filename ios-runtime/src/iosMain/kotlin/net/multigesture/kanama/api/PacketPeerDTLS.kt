package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PacketPeerDTLS
 */
class PacketPeerDTLS(handle: MemorySegment) : PacketPeer(handle) {
    fun poll() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(pollBind, handle)
    }

    fun connectToPeer(packetPeer: PacketPeerUDP?, hostname: String, clientOptions: TLSOptions?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectStringAndObjectArgsRetLong(connectToPeerBind, handle, packetPeer?.requireOpenHandle() ?: MemorySegment.NULL, hostname, clientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getStatus(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, handle)
    }

    fun disconnectFromPeer() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(disconnectFromPeerBind, handle)
    }

    companion object {
        const val STATUS_DISCONNECTED: Long = 0L
        const val STATUS_HANDSHAKING: Long = 1L
        const val STATUS_CONNECTED: Long = 2L
        const val STATUS_ERROR: Long = 3L
        const val STATUS_ERROR_HOSTNAME_MISMATCH: Long = 4L

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
