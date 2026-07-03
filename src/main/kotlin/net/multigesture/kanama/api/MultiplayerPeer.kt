package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract class for specialized `PacketPeer`s used by the `MultiplayerAPI`.
 *
 * Generated from Godot docs: MultiplayerPeer
 */
open class MultiplayerPeer(handle: MemorySegment) : PacketPeer(handle) {
    var refuseNewConnections: Boolean
        @JvmName("refuseNewConnectionsProperty")
        get() = isRefusingNewConnections()
        @JvmName("setRefuseNewConnectionsProperty")
        set(value) = setRefuseNewConnections(value)

    var transferMode: Long
        @JvmName("transferModeProperty")
        get() = getTransferMode()
        @JvmName("setTransferModeProperty")
        set(value) = setTransferMode(value)

    var transferChannel: Int
        @JvmName("transferChannelProperty")
        get() = getTransferChannel()
        @JvmName("setTransferChannelProperty")
        set(value) = setTransferChannel(value)

    /**
     * The channel to use to send packets. Many network APIs such as ENet and WebRTC allow the creation
     * of multiple independent channels which behaves, in a way, like separate connections. This means
     * that reliable data will only block delivery of other packets on that channel, and ordering will
     * only be in respect to the channel the packet is being sent on. Using different channels to send
     * different and independent state updates is a common way to optimize network usage and decrease
     * latency in fast-paced games. Note: The default channel (`0`) actually works as 3 separate
     * channels (one for each `TransferMode`) so that `TRANSFER_MODE_RELIABLE` and
     * `TRANSFER_MODE_UNRELIABLE_ORDERED` does not interact with each other by default. Refer to the
     * specific network API documentation (e.g. ENet or WebRTC) to learn how to set up channels
     * correctly.
     *
     * Generated from Godot docs: MultiplayerPeer.set_transfer_channel
     */
    fun setTransferChannel(channel: Int) {
        ObjectCalls.ptrcallWithIntArg(setTransferChannelBind, handle, channel)
    }

    /**
     * The channel to use to send packets. Many network APIs such as ENet and WebRTC allow the creation
     * of multiple independent channels which behaves, in a way, like separate connections. This means
     * that reliable data will only block delivery of other packets on that channel, and ordering will
     * only be in respect to the channel the packet is being sent on. Using different channels to send
     * different and independent state updates is a common way to optimize network usage and decrease
     * latency in fast-paced games. Note: The default channel (`0`) actually works as 3 separate
     * channels (one for each `TransferMode`) so that `TRANSFER_MODE_RELIABLE` and
     * `TRANSFER_MODE_UNRELIABLE_ORDERED` does not interact with each other by default. Refer to the
     * specific network API documentation (e.g. ENet or WebRTC) to learn how to set up channels
     * correctly.
     *
     * Generated from Godot docs: MultiplayerPeer.get_transfer_channel
     */
    fun getTransferChannel(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTransferChannelBind, handle)
    }

    /**
     * The manner in which to send packets to the target peer. See the `set_target_peer` method.
     *
     * Generated from Godot docs: MultiplayerPeer.set_transfer_mode
     */
    fun setTransferMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransferModeBind, handle, mode)
    }

    /**
     * The manner in which to send packets to the target peer. See the `set_target_peer` method.
     *
     * Generated from Godot docs: MultiplayerPeer.get_transfer_mode
     */
    fun getTransferMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransferModeBind, handle)
    }

    /**
     * Sets the peer to which packets will be sent. The `id` can be one of: `TARGET_PEER_BROADCAST` to
     * send to all connected peers, `TARGET_PEER_SERVER` to send to the peer acting as server, a valid
     * peer ID to send to that specific peer, a negative peer ID to send to all peers except that one.
     * By default, the target peer is `TARGET_PEER_BROADCAST`.
     *
     * Generated from Godot docs: MultiplayerPeer.set_target_peer
     */
    fun setTargetPeer(id: Int) {
        ObjectCalls.ptrcallWithIntArg(setTargetPeerBind, handle, id)
    }

    /**
     * Returns the ID of the `MultiplayerPeer` who sent the next available packet. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_peer
     */
    fun getPacketPeer(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketPeerBind, handle)
    }

    /**
     * Returns the channel over which the next available packet was received. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_channel
     */
    fun getPacketChannel(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketChannelBind, handle)
    }

    /**
     * Returns the transfer mode the remote peer used to send the next available packet. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_mode
     */
    fun getPacketMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPacketModeBind, handle)
    }

    /**
     * Waits up to 1 second to receive a new network event.
     *
     * Generated from Godot docs: MultiplayerPeer.poll
     */
    fun poll() {
        ObjectCalls.ptrcallNoArgs(pollBind, handle)
    }

    fun closeConnection() {
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, handle)
    }

    /**
     * Disconnects the given `peer` from this host. If `force` is `true` the `peer_disconnected` signal
     * will not be emitted for this peer.
     *
     * Generated from Godot docs: MultiplayerPeer.disconnect_peer
     */
    fun disconnectPeer(peer: Int, force: Boolean = false) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(disconnectPeerBind, handle, peer, force)
    }

    /**
     * Returns the current state of the connection.
     *
     * Generated from Godot docs: MultiplayerPeer.get_connection_status
     */
    fun getConnectionStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getConnectionStatusBind, handle)
    }

    /**
     * Returns the ID of this `MultiplayerPeer`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_unique_id
     */
    fun getUniqueId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getUniqueIdBind, handle)
    }

    /**
     * Returns a randomly generated integer that can be used as a network unique ID.
     *
     * Generated from Godot docs: MultiplayerPeer.generate_unique_id
     */
    fun generateUniqueId(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(generateUniqueIdBind, handle)
    }

    /**
     * If `true`, this `MultiplayerPeer` refuses new connections.
     *
     * Generated from Godot docs: MultiplayerPeer.set_refuse_new_connections
     */
    fun setRefuseNewConnections(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRefuseNewConnectionsBind, handle, enable)
    }

    /**
     * If `true`, this `MultiplayerPeer` refuses new connections.
     *
     * Generated from Godot docs: MultiplayerPeer.is_refusing_new_connections
     */
    fun isRefusingNewConnections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRefusingNewConnectionsBind, handle)
    }

    /**
     * Returns `true` if the server can act as a relay in the current configuration. That is, if the
     * higher level `MultiplayerAPI` should notify connected clients of other peers, and implement a
     * relay protocol to allow communication between them.
     *
     * Generated from Godot docs: MultiplayerPeer.is_server_relay_supported
     */
    fun isServerRelaySupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isServerRelaySupportedBind, handle)
    }

    object Signals {
        const val peerConnected: String = "peer_connected"
        const val peerDisconnected: String = "peer_disconnected"
    }

    companion object {
        const val TARGET_PEER_BROADCAST: Long = 0L
        const val TARGET_PEER_SERVER: Long = 1L
        const val CONNECTION_DISCONNECTED: Long = 0L
        const val CONNECTION_CONNECTING: Long = 1L
        const val CONNECTION_CONNECTED: Long = 2L
        const val TRANSFER_MODE_UNRELIABLE: Long = 0L
        const val TRANSFER_MODE_UNRELIABLE_ORDERED: Long = 1L
        const val TRANSFER_MODE_RELIABLE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiplayerPeer? =
            if (handle.address() == 0L) null else MultiplayerPeer(handle)

        private const val SET_TRANSFER_CHANNEL_HASH = 1286410249L
        private val setTransferChannelBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_transfer_channel", SET_TRANSFER_CHANNEL_HASH)
        }

        private const val GET_TRANSFER_CHANNEL_HASH = 3905245786L
        private val getTransferChannelBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_transfer_channel", GET_TRANSFER_CHANNEL_HASH)
        }

        private const val SET_TRANSFER_MODE_HASH = 950411049L
        private val setTransferModeBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_transfer_mode", SET_TRANSFER_MODE_HASH)
        }

        private const val GET_TRANSFER_MODE_HASH = 3369852622L
        private val getTransferModeBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_transfer_mode", GET_TRANSFER_MODE_HASH)
        }

        private const val SET_TARGET_PEER_HASH = 1286410249L
        private val setTargetPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_target_peer", SET_TARGET_PEER_HASH)
        }

        private const val GET_PACKET_PEER_HASH = 3905245786L
        private val getPacketPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_peer", GET_PACKET_PEER_HASH)
        }

        private const val GET_PACKET_CHANNEL_HASH = 3905245786L
        private val getPacketChannelBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_channel", GET_PACKET_CHANNEL_HASH)
        }

        private const val GET_PACKET_MODE_HASH = 3369852622L
        private val getPacketModeBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_mode", GET_PACKET_MODE_HASH)
        }

        private const val POLL_HASH = 3218959716L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "poll", POLL_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "close", CLOSE_HASH)
        }

        private const val DISCONNECT_PEER_HASH = 4023243586L
        private val disconnectPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "disconnect_peer", DISCONNECT_PEER_HASH)
        }

        private const val GET_CONNECTION_STATUS_HASH = 2147374275L
        private val getConnectionStatusBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_connection_status", GET_CONNECTION_STATUS_HASH)
        }

        private const val GET_UNIQUE_ID_HASH = 3905245786L
        private val getUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_unique_id", GET_UNIQUE_ID_HASH)
        }

        private const val GENERATE_UNIQUE_ID_HASH = 3905245786L
        private val generateUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "generate_unique_id", GENERATE_UNIQUE_ID_HASH)
        }

        private const val SET_REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        private val setRefuseNewConnectionsBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_refuse_new_connections", SET_REFUSE_NEW_CONNECTIONS_HASH)
        }

        private const val IS_REFUSING_NEW_CONNECTIONS_HASH = 36873697L
        private val isRefusingNewConnectionsBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "is_refusing_new_connections", IS_REFUSING_NEW_CONNECTIONS_HASH)
        }

        private const val IS_SERVER_RELAY_SUPPORTED_HASH = 36873697L
        private val isServerRelaySupportedBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerPeer", "is_server_relay_supported", IS_SERVER_RELAY_SUPPORTED_HASH)
        }
    }
}
