package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * UDP packet peer.
 *
 * Generated from Godot docs: PacketPeerUDP
 */
class PacketPeerUDP(handle: MemorySegment) : PacketPeer(handle) {
    /**
     * Binds this `PacketPeerUDP` to the specified `port` and `bind_address` with a buffer size
     * `recv_buf_size`, allowing it to receive incoming packets. If `bind_address` is set to `"*"`
     * (default), the peer will be bound on all available addresses (both IPv4 and IPv6). If
     * `bind_address` is set to `"0.0.0.0"` (for IPv4) or `"::"` (for IPv6), the peer will be bound to
     * all available addresses matching that IP type. If `bind_address` is set to any valid address
     * (e.g. `"192.168.1.101"`, `"::1"`, etc.), the peer will only be bound to the interface with that
     * address (or fail if no interface with the given address exists).
     *
     * Generated from Godot docs: PacketPeerUDP.bind
     */
    fun bind(port: Int, bindAddress: String = "*", recvBufSize: Int = 65536): Long {
        return ObjectCalls.ptrcallWithIntStringAndIntArgsRetLong(bindBind, handle, port, bindAddress, recvBufSize)
    }

    fun closeConnection() {
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, handle)
    }

    fun waitBlocking(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(waitBlockingBind, handle)
    }

    fun isBound(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBoundBind, handle)
    }

    fun connectToHost(host: String, port: Int): Long {
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(connectToHostBind, handle, host, port)
    }

    fun isSocketConnected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSocketConnectedBind, handle)
    }

    fun getPacketIp(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPacketIpBind, handle)
    }

    fun getPacketPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketPortBind, handle)
    }

    fun getLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun setDestAddress(host: String, port: Int): Long {
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(setDestAddressBind, handle, host, port)
    }

    fun setBroadcastEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBroadcastEnabledBind, handle, enabled)
    }

    fun joinMulticastGroup(multicastAddress: String, interfaceName: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(joinMulticastGroupBind, handle, multicastAddress, interfaceName)
    }

    fun leaveMulticastGroup(multicastAddress: String, interfaceName: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(leaveMulticastGroupBind, handle, multicastAddress, interfaceName)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PacketPeerUDP? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PacketPeerUDP? =
            if (handle.address() == 0L) null else PacketPeerUDP(handle)

        private const val BIND_HASH = 4051239242L
        private val bindBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "bind", BIND_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "close", CLOSE_HASH)
        }

        private const val WAIT_HASH = 166280745L
        private val waitBlockingBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "wait", WAIT_HASH)
        }

        private const val IS_BOUND_HASH = 36873697L
        private val isBoundBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "is_bound", IS_BOUND_HASH)
        }

        private const val CONNECT_TO_HOST_HASH = 993915709L
        private val connectToHostBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "connect_to_host", CONNECT_TO_HOST_HASH)
        }

        private const val IS_SOCKET_CONNECTED_HASH = 36873697L
        private val isSocketConnectedBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "is_socket_connected", IS_SOCKET_CONNECTED_HASH)
        }

        private const val GET_PACKET_IP_HASH = 201670096L
        private val getPacketIpBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "get_packet_ip", GET_PACKET_IP_HASH)
        }

        private const val GET_PACKET_PORT_HASH = 3905245786L
        private val getPacketPortBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "get_packet_port", GET_PACKET_PORT_HASH)
        }

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        private val getLocalPortBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "get_local_port", GET_LOCAL_PORT_HASH)
        }

        private const val SET_DEST_ADDRESS_HASH = 993915709L
        private val setDestAddressBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "set_dest_address", SET_DEST_ADDRESS_HASH)
        }

        private const val SET_BROADCAST_ENABLED_HASH = 2586408642L
        private val setBroadcastEnabledBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "set_broadcast_enabled", SET_BROADCAST_ENABLED_HASH)
        }

        private const val JOIN_MULTICAST_GROUP_HASH = 852856452L
        private val joinMulticastGroupBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "join_multicast_group", JOIN_MULTICAST_GROUP_HASH)
        }

        private const val LEAVE_MULTICAST_GROUP_HASH = 852856452L
        private val leaveMulticastGroupBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerUDP", "leave_multicast_group", LEAVE_MULTICAST_GROUP_HASH)
        }
    }
}
