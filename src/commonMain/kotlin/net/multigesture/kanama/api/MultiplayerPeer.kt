package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract class for specialized `PacketPeer`s used by the `MultiplayerAPI`.
 *
 * Generated from Godot docs: MultiplayerPeer
 */
open class MultiplayerPeer(handle: GodotHandle) : PacketPeer(handle) {
    var refuseNewConnections: Boolean
        @JvmName("refuseNewConnectionsProperty")
        get() = isRefusingNewConnections()
        @JvmName("setRefuseNewConnectionsProperty")
        set(value) = setRefuseNewConnections(value)

    var transferMode: MultiplayerPeer.TransferMode
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
     * channels (one for each `TransferMode`) so that `TransferMode.RELIABLE` and
     * `TransferMode.UNRELIABLE_ORDERED` does not interact with each other by default. Refer to the
     * specific network API documentation (e.g. ENet or WebRTC) to learn how to set up channels
     * correctly.
     *
     * Generated from Godot docs: MultiplayerPeer.set_transfer_channel
     */
    fun setTransferChannel(channel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setTransferChannelBind, segment, channel)
    }

    /**
     * The channel to use to send packets. Many network APIs such as ENet and WebRTC allow the creation
     * of multiple independent channels which behaves, in a way, like separate connections. This means
     * that reliable data will only block delivery of other packets on that channel, and ordering will
     * only be in respect to the channel the packet is being sent on. Using different channels to send
     * different and independent state updates is a common way to optimize network usage and decrease
     * latency in fast-paced games. Note: The default channel (`0`) actually works as 3 separate
     * channels (one for each `TransferMode`) so that `TransferMode.RELIABLE` and
     * `TransferMode.UNRELIABLE_ORDERED` does not interact with each other by default. Refer to the
     * specific network API documentation (e.g. ENet or WebRTC) to learn how to set up channels
     * correctly.
     *
     * Generated from Godot docs: MultiplayerPeer.get_transfer_channel
     */
    fun getTransferChannel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTransferChannelBind, segment)
    }

    /**
     * The manner in which to send packets to the target peer. See the `set_target_peer` method.
     *
     * Generated from Godot docs: MultiplayerPeer.set_transfer_mode
     */
    fun setTransferMode(mode: MultiplayerPeer.TransferMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTransferModeBind, segment, mode.value)
    }

    /**
     * The manner in which to send packets to the target peer. See the `set_target_peer` method.
     *
     * Generated from Godot docs: MultiplayerPeer.get_transfer_mode
     */
    fun getTransferMode(): MultiplayerPeer.TransferMode {
        checkOpen()
        return MultiplayerPeer.TransferMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTransferModeBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setTargetPeerBind, segment, id)
    }

    /**
     * Returns the ID of the `MultiplayerPeer` who sent the next available packet. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_peer
     */
    fun getPacketPeer(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPacketPeerBind, segment)
    }

    /**
     * Returns the channel over which the next available packet was received. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_channel
     */
    fun getPacketChannel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPacketChannelBind, segment)
    }

    /**
     * Returns the transfer mode the remote peer used to send the next available packet. See
     * `PacketPeer.get_available_packet_count`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_packet_mode
     */
    fun getPacketMode(): MultiplayerPeer.TransferMode {
        checkOpen()
        return MultiplayerPeer.TransferMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPacketModeBind, segment))
    }

    /**
     * Waits up to 1 second to receive a new network event.
     *
     * Generated from Godot docs: MultiplayerPeer.poll
     */
    fun poll() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.pollBind, segment)
    }

    fun closeConnection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.closeConnectionBind, segment)
    }

    /**
     * Disconnects the given `peer` from this host. If `force` is `true` the `peer_disconnected` signal
     * will not be emitted for this peer.
     *
     * Generated from Godot docs: MultiplayerPeer.disconnect_peer
     */
    fun disconnectPeer(peer: Int, force: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.disconnectPeerBind, segment, peer, force)
    }

    /**
     * Returns the current state of the connection.
     *
     * Generated from Godot docs: MultiplayerPeer.get_connection_status
     */
    fun getConnectionStatus(): MultiplayerPeer.ConnectionStatus {
        checkOpen()
        return MultiplayerPeer.ConnectionStatus(ObjectCalls.ptrcallNoArgsRetLong(Binds.getConnectionStatusBind, segment))
    }

    /**
     * Returns the ID of this `MultiplayerPeer`.
     *
     * Generated from Godot docs: MultiplayerPeer.get_unique_id
     */
    fun getUniqueId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getUniqueIdBind, segment)
    }

    /**
     * Returns a randomly generated integer that can be used as a network unique ID.
     *
     * Generated from Godot docs: MultiplayerPeer.generate_unique_id
     */
    fun generateUniqueId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.generateUniqueIdBind, segment)
    }

    /**
     * If `true`, this `MultiplayerPeer` refuses new connections.
     *
     * Generated from Godot docs: MultiplayerPeer.set_refuse_new_connections
     */
    fun setRefuseNewConnections(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setRefuseNewConnectionsBind, segment, enable)
    }

    /**
     * If `true`, this `MultiplayerPeer` refuses new connections.
     *
     * Generated from Godot docs: MultiplayerPeer.is_refusing_new_connections
     */
    fun isRefusingNewConnections(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRefusingNewConnectionsBind, segment)
    }

    /**
     * Returns `true` if the server can act as a relay in the current configuration. That is, if the
     * higher level `MultiplayerAPI` should notify connected clients of other peers, and implement a
     * relay protocol to allow communication between them.
     *
     * Generated from Godot docs: MultiplayerPeer.is_server_relay_supported
     */
    fun isServerRelaySupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isServerRelaySupportedBind, segment)
    }

    /** Signal `peer_connected(id: int)`; see [TypedSignal]. */
    val peerConnected: Signal1<Long>
        @JvmName("peerConnectedTypedSignal")
        get() = Signal1(this, "peer_connected", SignalArgType.LONG)

    /** Signal `peer_disconnected(id: int)`; see [TypedSignal]. */
    val peerDisconnected: Signal1<Long>
        @JvmName("peerDisconnectedTypedSignal")
        get() = Signal1(this, "peer_disconnected", SignalArgType.LONG)

    object Signals {
        const val peerConnected: String = "peer_connected"
        const val peerDisconnected: String = "peer_disconnected"
    }

    /**
     * Godot's `MultiplayerPeer.ConnectionStatus` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`MultiplayerPeer.ConnectionStatus.<NAME>`).
     *
     * Generated from Godot docs: MultiplayerPeer.ConnectionStatus
     */
    @JvmInline
    value class ConnectionStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The MultiplayerPeer is disconnected.
             *
             * Generated from Godot docs: MultiplayerPeer.CONNECTION_DISCONNECTED
             */
            val DISCONNECTED: ConnectionStatus get() = ConnectionStatus(0L)
            /**
             * The MultiplayerPeer is currently connecting to a server.
             *
             * Generated from Godot docs: MultiplayerPeer.CONNECTION_CONNECTING
             */
            val CONNECTING: ConnectionStatus get() = ConnectionStatus(1L)
            /**
             * This MultiplayerPeer is connected.
             *
             * Generated from Godot docs: MultiplayerPeer.CONNECTION_CONNECTED
             */
            val CONNECTED: ConnectionStatus get() = ConnectionStatus(2L)
        }
    }

    /**
     * Godot's `MultiplayerPeer.TransferMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`MultiplayerPeer.TransferMode.<NAME>`).
     *
     * Generated from Godot docs: MultiplayerPeer.TransferMode
     */
    @JvmInline
    value class TransferMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Packets are not acknowledged, no resend attempts are made for lost packets. Packets may arrive
             * in any order. Potentially faster than `TransferMode.UNRELIABLE_ORDERED`. Use for non-critical
             * data, and always consider whether the order matters.
             *
             * Generated from Godot docs: MultiplayerPeer.TRANSFER_MODE_UNRELIABLE
             */
            val UNRELIABLE: TransferMode get() = TransferMode(0L)
            /**
             * Packets are not acknowledged, no resend attempts are made for lost packets. Packets are received
             * in the order they were sent in. Potentially faster than `TransferMode.RELIABLE`. Use for
             * non-critical data or data that would be outdated if received late due to resend attempt(s)
             * anyway, for example movement and positional data.
             *
             * Generated from Godot docs: MultiplayerPeer.TRANSFER_MODE_UNRELIABLE_ORDERED
             */
            val UNRELIABLE_ORDERED: TransferMode get() = TransferMode(1L)
            /**
             * Packets must be received and resend attempts should be made until the packets are acknowledged.
             * Packets must be received in the order they were sent in. Most reliable transfer mode, but
             * potentially the slowest due to the overhead. Use for critical data that must be transmitted and
             * arrive in order, for example an ability being triggered or a chat message. Consider carefully if
             * the information really is critical, and use sparingly.
             *
             * Generated from Godot docs: MultiplayerPeer.TRANSFER_MODE_RELIABLE
             */
            val RELIABLE: TransferMode get() = TransferMode(2L)
        }
    }

    companion object {
        const val TARGET_PEER_BROADCAST: Long = 0L
        const val TARGET_PEER_SERVER: Long = 1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): MultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(MultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): MultiplayerPeer? =
            if (handle.address() == 0L) null else MultiplayerPeer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TRANSFER_CHANNEL_HASH = 1286410249L
        @JvmField
        val setTransferChannelBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_transfer_channel", SET_TRANSFER_CHANNEL_HASH)

        private const val GET_TRANSFER_CHANNEL_HASH = 3905245786L
        @JvmField
        val getTransferChannelBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_transfer_channel", GET_TRANSFER_CHANNEL_HASH)

        private const val SET_TRANSFER_MODE_HASH = 950411049L
        @JvmField
        val setTransferModeBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_transfer_mode", SET_TRANSFER_MODE_HASH)

        private const val GET_TRANSFER_MODE_HASH = 3369852622L
        @JvmField
        val getTransferModeBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_transfer_mode", GET_TRANSFER_MODE_HASH)

        private const val SET_TARGET_PEER_HASH = 1286410249L
        @JvmField
        val setTargetPeerBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_target_peer", SET_TARGET_PEER_HASH)

        private const val GET_PACKET_PEER_HASH = 3905245786L
        @JvmField
        val getPacketPeerBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_peer", GET_PACKET_PEER_HASH)

        private const val GET_PACKET_CHANNEL_HASH = 3905245786L
        @JvmField
        val getPacketChannelBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_channel", GET_PACKET_CHANNEL_HASH)

        private const val GET_PACKET_MODE_HASH = 3369852622L
        @JvmField
        val getPacketModeBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_packet_mode", GET_PACKET_MODE_HASH)

        private const val POLL_HASH = 3218959716L
        @JvmField
        val pollBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "poll", POLL_HASH)

        private const val CLOSE_HASH = 3218959716L
        @JvmField
        val closeConnectionBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "close", CLOSE_HASH)

        private const val DISCONNECT_PEER_HASH = 4023243586L
        @JvmField
        val disconnectPeerBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "disconnect_peer", DISCONNECT_PEER_HASH)

        private const val GET_CONNECTION_STATUS_HASH = 2147374275L
        @JvmField
        val getConnectionStatusBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_connection_status", GET_CONNECTION_STATUS_HASH)

        private const val GET_UNIQUE_ID_HASH = 3905245786L
        @JvmField
        val getUniqueIdBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "get_unique_id", GET_UNIQUE_ID_HASH)

        private const val GENERATE_UNIQUE_ID_HASH = 3905245786L
        @JvmField
        val generateUniqueIdBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "generate_unique_id", GENERATE_UNIQUE_ID_HASH)

        private const val SET_REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        @JvmField
        val setRefuseNewConnectionsBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "set_refuse_new_connections", SET_REFUSE_NEW_CONNECTIONS_HASH)

        private const val IS_REFUSING_NEW_CONNECTIONS_HASH = 36873697L
        @JvmField
        val isRefusingNewConnectionsBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "is_refusing_new_connections", IS_REFUSING_NEW_CONNECTIONS_HASH)

        private const val IS_SERVER_RELAY_SUPPORTED_HASH = 36873697L
        @JvmField
        val isServerRelaySupportedBind =
            ObjectCalls.getMethodBind("MultiplayerPeer", "is_server_relay_supported", IS_SERVER_RELAY_SUPPORTED_HASH)
    }
}
