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

    /**
     * Returns whether this `PacketPeerUDP` is bound to an address and can receive packets.
     *
     * Generated from Godot docs: PacketPeerUDP.is_bound
     */
    fun isBound(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBoundBind, handle)
    }

    /**
     * Calling this method connects this UDP peer to the given `host`/`port` pair. UDP is in reality
     * connectionless, so this option only means that incoming packets from different addresses are
     * automatically discarded, and that outgoing packets are always sent to the connected address
     * (future calls to `set_dest_address` are not allowed). This method does not send any data to the
     * remote peer, to do that, use `PacketPeer.put_var` or `PacketPeer.put_packet` as usual. See also
     * `UDPServer`. Note: Connecting to the remote peer does not help to protect from malicious attacks
     * like IP spoofing, etc. Think about using an encryption technique like TLS or DTLS if you feel
     * like your application is transferring sensitive information.
     *
     * Generated from Godot docs: PacketPeerUDP.connect_to_host
     */
    fun connectToHost(host: String, port: Int): Long {
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(connectToHostBind, handle, host, port)
    }

    /**
     * Returns `true` if the UDP socket is open and has been connected to a remote address. See
     * `connect_to_host`.
     *
     * Generated from Godot docs: PacketPeerUDP.is_socket_connected
     */
    fun isSocketConnected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSocketConnectedBind, handle)
    }

    /**
     * Returns the IP of the remote peer that sent the last packet(that was received with
     * `PacketPeer.get_packet` or `PacketPeer.get_var`).
     *
     * Generated from Godot docs: PacketPeerUDP.get_packet_ip
     */
    fun getPacketIp(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPacketIpBind, handle)
    }

    /**
     * Returns the port of the remote peer that sent the last packet(that was received with
     * `PacketPeer.get_packet` or `PacketPeer.get_var`).
     *
     * Generated from Godot docs: PacketPeerUDP.get_packet_port
     */
    fun getPacketPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketPortBind, handle)
    }

    /**
     * Returns the local port to which this peer is bound.
     *
     * Generated from Godot docs: PacketPeerUDP.get_local_port
     */
    fun getLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    /**
     * Sets the destination address and port for sending packets and variables. A hostname will be
     * resolved using DNS if needed. Note: `set_broadcast_enabled` must be enabled before sending
     * packets to a broadcast address (e.g. `255.255.255.255`).
     *
     * Generated from Godot docs: PacketPeerUDP.set_dest_address
     */
    fun setDestAddress(host: String, port: Int): Long {
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(setDestAddressBind, handle, host, port)
    }

    /**
     * Enable or disable sending of broadcast packets (e.g. `set_dest_address("255.255.255.255",
     * 4343)`. This option is disabled by default. Note: Some Android devices might require the
     * `CHANGE_WIFI_MULTICAST_STATE` permission and this option to be enabled to receive broadcast
     * packets too.
     *
     * Generated from Godot docs: PacketPeerUDP.set_broadcast_enabled
     */
    fun setBroadcastEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBroadcastEnabledBind, handle, enabled)
    }

    /**
     * Joins the multicast group specified by `multicast_address` using the interface identified by
     * `interface_name`. You can join the same multicast group with multiple interfaces. Use
     * `IP.get_local_interfaces` to know which are available. Note: Some Android devices might require
     * the `CHANGE_WIFI_MULTICAST_STATE` permission for multicast to work.
     *
     * Generated from Godot docs: PacketPeerUDP.join_multicast_group
     */
    fun joinMulticastGroup(multicastAddress: String, interfaceName: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(joinMulticastGroupBind, handle, multicastAddress, interfaceName)
    }

    /**
     * Removes the interface identified by `interface_name` from the multicast group specified by
     * `multicast_address`.
     *
     * Generated from Godot docs: PacketPeerUDP.leave_multicast_group
     */
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
