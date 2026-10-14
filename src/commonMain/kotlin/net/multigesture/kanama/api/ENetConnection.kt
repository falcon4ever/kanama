package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A wrapper class for an ENetHost (http://enet.bespin.org/group__host.html).
 *
 * Generated from Godot docs: ENetConnection
 */
class ENetConnection(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Creates an ENetHost bound to the given `bind_address` and `bind_port` that allows up to
     * `max_peers` connected peers, each allocating up to `max_channels` channels, optionally limiting
     * bandwidth to `in_bandwidth` and `out_bandwidth` (if greater than zero). Note: It is necessary to
     * create a host in both client and server in order to establish a connection.
     *
     * Generated from Godot docs: ENetConnection.create_host_bound
     */
    fun createHostBound(bindAddress: String, bindPort: Int, maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(Binds.createHostBoundBind, segment, bindAddress, bindPort, maxPeers, maxChannels, inBandwidth, outBandwidth))
    }

    /**
     * Creates an ENetHost that allows up to `max_peers` connected peers, each allocating up to
     * `max_channels` channels, optionally limiting bandwidth to `in_bandwidth` and `out_bandwidth` (if
     * greater than zero). This method binds a random available dynamic UDP port on the host machine at
     * the unspecified address. Use `create_host_bound` to specify the address and port. Note: It is
     * necessary to create a host in both client and server in order to establish a connection.
     *
     * Generated from Godot docs: ENetConnection.create_host
     */
    fun createHost(maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithFourIntArgsRetLong(Binds.createHostBind, segment, maxPeers, maxChannels, inBandwidth, outBandwidth))
    }

    /**
     * Destroys the host and all resources associated with it.
     *
     * Generated from Godot docs: ENetConnection.destroy
     */
    fun destroy() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.destroyBind, segment)
    }

    /**
     * Initiates a connection to a foreign `address` using the specified `port` and allocating the
     * requested `channels`. Optional `data` can be passed during connection in the form of a 32 bit
     * integer. Note: You must call either `create_host` or `create_host_bound` on both ends before
     * calling this method.
     *
     * Generated from Godot docs: ENetConnection.connect_to_host
     */
    fun connectToHost(address: String, port: Int, channels: Int = 0, data: Int = 0): ENetPacketPeer? {
        checkOpen()
        return ENetPacketPeer.wrapOwned(ObjectCalls.ptrcallWithStringAndThreeIntArgsRetObject(Binds.connectToHostBind, segment, address, port, channels, data))
    }

    /**
     * Waits for events on this connection and shuttles packets between the host and its peers, with
     * the given `timeout` (in milliseconds). The returned `Array` will have 4 elements. An
     * `EventType`, the `ENetPacketPeer` which generated the event, the event associated data (if any),
     * the event associated channel (if any). If the generated event is `EventType.RECEIVE`, the
     * received packet will be queued to the associated `ENetPacketPeer`. Call this function regularly
     * to handle connections, disconnections, and to receive new packets. Note: This method must be
     * called on both ends involved in the event (sending and receiving hosts).
     *
     * Generated from Godot docs: ENetConnection.service
     */
    fun service(timeout: Int = 0): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.serviceBind, segment, timeout)
    }

    /**
     * Sends any queued packets on the host specified to its designated peers.
     *
     * Generated from Godot docs: ENetConnection.flush
     */
    fun flush() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.flushBind, segment)
    }

    /**
     * Adjusts the bandwidth limits of a host.
     *
     * Generated from Godot docs: ENetConnection.bandwidth_limit
     */
    fun bandwidthLimit(inBandwidth: Int = 0, outBandwidth: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.bandwidthLimitBind, segment, inBandwidth, outBandwidth)
    }

    /**
     * Limits the maximum allowed channels of future incoming connections.
     *
     * Generated from Godot docs: ENetConnection.channel_limit
     */
    fun channelLimit(limit: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.channelLimitBind, segment, limit)
    }

    /**
     * Queues a `packet` to be sent to all peers associated with the host over the specified `channel`.
     * See `ENetPacketPeer` `FLAG_*` constants for available packet flags.
     *
     * Generated from Godot docs: ENetConnection.broadcast
     */
    fun broadcast(channel: Int, packet: ByteArray, flags: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntByteArrayIntArgs(Binds.broadcastBind, segment, channel, packet, flags)
    }

    /**
     * Sets the compression method used for network packets. These have different tradeoffs of
     * compression speed versus bandwidth, you may need to test which one works best for your use case
     * if you use compression at all. Note: Most games' network design involve sending many small
     * packets frequently (smaller than 4 KB each). If in doubt, it is recommended to keep the default
     * compression algorithm as it works best on these small packets. Note: The compression mode must
     * be set to the same value on both the server and all its clients. Clients will fail to connect if
     * the compression mode set on the client differs from the one set on the server.
     *
     * Generated from Godot docs: ENetConnection.compress
     */
    fun compress(mode: ENetConnection.CompressionMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.compressBind, segment, mode.value)
    }

    /**
     * Configure this ENetHost to use the custom Godot extension allowing DTLS encryption for ENet
     * servers. Call this right after `create_host_bound` to have ENet expect peers to connect using
     * DTLS. See `TLSOptions.server`.
     *
     * Generated from Godot docs: ENetConnection.dtls_server_setup
     */
    fun dtlsServerSetup(serverOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(Binds.dtlsServerSetupBind, segment, serverOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Configure this ENetHost to use the custom Godot extension allowing DTLS encryption for ENet
     * clients. Call this before `connect_to_host` to have ENet connect using DTLS validating the
     * server certificate against `hostname`. You can pass the optional `client_options` parameter to
     * customize the trusted certification authorities, or disable the common name verification. See
     * `TLSOptions.client` and `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: ENetConnection.dtls_client_setup
     */
    fun dtlsClientSetup(hostname: String, clientOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndObjectArgRetLong(Binds.dtlsClientSetupBind, segment, hostname, clientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Configures the DTLS server to automatically drop new connections. Note: This method is only
     * relevant after calling `dtls_server_setup`.
     *
     * Generated from Godot docs: ENetConnection.refuse_new_connections
     */
    fun refuseNewConnections(refuse: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.refuseNewConnectionsBind, segment, refuse)
    }

    /**
     * Returns and resets host statistics.
     *
     * Generated from Godot docs: ENetConnection.pop_statistic
     */
    fun popStatistic(statistic: ENetConnection.HostStatistic): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.popStatisticBind, segment, statistic.value)
    }

    /**
     * Returns the maximum number of channels allowed for connected peers.
     *
     * Generated from Godot docs: ENetConnection.get_max_channels
     */
    fun getMaxChannels(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxChannelsBind, segment)
    }

    /**
     * Returns the local port to which this peer is bound.
     *
     * Generated from Godot docs: ENetConnection.get_local_port
     */
    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLocalPortBind, segment)
    }

    /**
     * Returns the list of peers associated with this host. Note: This list might include some peers
     * that are not fully connected or are still being disconnected.
     *
     * Generated from Godot docs: ENetConnection.get_peers
     */
    fun getPeers(): List<ENetPacketPeer> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getPeersBind, segment, ENetPacketPeer::wrapBorrowed)
    }

    /**
     * Sends a `packet` toward a destination from the address and port currently bound by this
     * ENetConnection instance. This is useful as it serves to establish entries in NAT routing tables
     * on all devices between this bound instance and the public facing internet, allowing a
     * prospective client's connection packets to be routed backward through the NAT device(s) between
     * the public internet and this host. This requires forward knowledge of a prospective client's
     * address and communication port as seen by the public internet - after any NAT devices have
     * handled their connection request. This information can be obtained by a STUN
     * (https://en.wikipedia.org/wiki/STUN) service, and must be handed off to your host by an entity
     * that is not the prospective client. This will never work for a client behind a Symmetric NAT due
     * to the nature of the Symmetric NAT routing algorithm, as their IP and Port cannot be known
     * beforehand.
     *
     * Generated from Godot docs: ENetConnection.socket_send
     */
    fun socketSend(destinationAddress: String, destinationPort: Int, packet: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithStringIntByteArrayArgs(Binds.socketSendBind, segment, destinationAddress, destinationPort, packet)
    }

    /**
     * Godot's `ENetConnection.CompressionMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ENetConnection.CompressionMode.<NAME>`).
     *
     * Generated from Godot docs: ENetConnection.CompressionMode
     */
    @JvmInline
    value class CompressionMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No compression. This uses the most bandwidth, but has the upside of requiring the fewest CPU
             * resources. This option may also be used to make network debugging using tools like Wireshark
             * easier.
             *
             * Generated from Godot docs: ENetConnection.COMPRESS_NONE
             */
            val NONE: CompressionMode get() = CompressionMode(0L)
            /**
             * ENet's built-in range encoding. Works well on small packets, but is not the most efficient
             * algorithm on packets larger than 4 KB.
             *
             * Generated from Godot docs: ENetConnection.COMPRESS_RANGE_CODER
             */
            val RANGE_CODER: CompressionMode get() = CompressionMode(1L)
            /**
             * FastLZ (https://fastlz.org/) compression. This option uses less CPU resources compared to
             * `CompressionMode.ZLIB`, at the expense of using more bandwidth.
             *
             * Generated from Godot docs: ENetConnection.COMPRESS_FASTLZ
             */
            val FASTLZ: CompressionMode get() = CompressionMode(2L)
            /**
             * Zlib (https://www.zlib.net/) compression. This option uses less bandwidth compared to
             * `CompressionMode.FASTLZ`, at the expense of using more CPU resources.
             *
             * Generated from Godot docs: ENetConnection.COMPRESS_ZLIB
             */
            val ZLIB: CompressionMode get() = CompressionMode(3L)
            /**
             * Zstandard (https://facebook.github.io/zstd/) compression. Note that this algorithm is not very
             * efficient on packets smaller than 4 KB. Therefore, it's recommended to use other compression
             * algorithms in most cases.
             *
             * Generated from Godot docs: ENetConnection.COMPRESS_ZSTD
             */
            val ZSTD: CompressionMode get() = CompressionMode(4L)
        }
    }

    /**
     * Godot's `ENetConnection.EventType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ENetConnection.EventType.<NAME>`).
     *
     * Generated from Godot docs: ENetConnection.EventType
     */
    @JvmInline
    value class EventType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * An error occurred during `service`. You will likely need to `destroy` the host and recreate it.
             *
             * Generated from Godot docs: ENetConnection.EVENT_ERROR
             */
            val ERROR: EventType get() = EventType(-1L)
            /**
             * No event occurred within the specified time limit.
             *
             * Generated from Godot docs: ENetConnection.EVENT_NONE
             */
            val NONE: EventType get() = EventType(0L)
            /**
             * A connection request initiated by enet_host_connect has completed. The array will contain the
             * peer which successfully connected.
             *
             * Generated from Godot docs: ENetConnection.EVENT_CONNECT
             */
            val CONNECT: EventType get() = EventType(1L)
            /**
             * A peer has disconnected. This event is generated on a successful completion of a disconnect
             * initiated by `ENetPacketPeer.peer_disconnect`, if a peer has timed out, or if a connection
             * request initialized by `connect_to_host` has timed out. The array will contain the peer which
             * disconnected. The data field contains user supplied data describing the disconnection, or 0, if
             * none is available.
             *
             * Generated from Godot docs: ENetConnection.EVENT_DISCONNECT
             */
            val DISCONNECT: EventType get() = EventType(2L)
            /**
             * A packet has been received from a peer. The array will contain the peer which sent the packet
             * and the channel number upon which the packet was received. The received packet will be queued to
             * the associated `ENetPacketPeer`.
             *
             * Generated from Godot docs: ENetConnection.EVENT_RECEIVE
             */
            val RECEIVE: EventType get() = EventType(3L)
        }
    }

    /**
     * Godot's `ENetConnection.HostStatistic` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ENetConnection.HostStatistic.<NAME>`).
     *
     * Generated from Godot docs: ENetConnection.HostStatistic
     */
    @JvmInline
    value class HostStatistic(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Total data sent.
             *
             * Generated from Godot docs: ENetConnection.HOST_TOTAL_SENT_DATA
             */
            val SENT_DATA: HostStatistic get() = HostStatistic(0L)
            /**
             * Total UDP packets sent.
             *
             * Generated from Godot docs: ENetConnection.HOST_TOTAL_SENT_PACKETS
             */
            val SENT_PACKETS: HostStatistic get() = HostStatistic(1L)
            /**
             * Total data received.
             *
             * Generated from Godot docs: ENetConnection.HOST_TOTAL_RECEIVED_DATA
             */
            val RECEIVED_DATA: HostStatistic get() = HostStatistic(2L)
            /**
             * Total UDP packets received.
             *
             * Generated from Godot docs: ENetConnection.HOST_TOTAL_RECEIVED_PACKETS
             */
            val RECEIVED_PACKETS: HostStatistic get() = HostStatistic(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ENetConnection? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ENetConnection? =
            if (handle.address() == 0L) null else RefCounted.owned(ENetConnection(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ENetConnection? =
            if (handle.address() == 0L) null else ENetConnection(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_HOST_BOUND_HASH = 1515002313L
        @JvmField
        val createHostBoundBind =
            ObjectCalls.getMethodBind("ENetConnection", "create_host_bound", CREATE_HOST_BOUND_HASH)

        private const val CREATE_HOST_HASH = 117198950L
        @JvmField
        val createHostBind =
            ObjectCalls.getMethodBind("ENetConnection", "create_host", CREATE_HOST_HASH)

        private const val DESTROY_HASH = 3218959716L
        @JvmField
        val destroyBind =
            ObjectCalls.getMethodBind("ENetConnection", "destroy", DESTROY_HASH)

        private const val CONNECT_TO_HOST_HASH = 2171300490L
        @JvmField
        val connectToHostBind =
            ObjectCalls.getMethodBind("ENetConnection", "connect_to_host", CONNECT_TO_HOST_HASH)

        private const val SERVICE_HASH = 2402345344L
        @JvmField
        val serviceBind =
            ObjectCalls.getMethodBind("ENetConnection", "service", SERVICE_HASH)

        private const val FLUSH_HASH = 3218959716L
        @JvmField
        val flushBind =
            ObjectCalls.getMethodBind("ENetConnection", "flush", FLUSH_HASH)

        private const val BANDWIDTH_LIMIT_HASH = 2302169788L
        @JvmField
        val bandwidthLimitBind =
            ObjectCalls.getMethodBind("ENetConnection", "bandwidth_limit", BANDWIDTH_LIMIT_HASH)

        private const val CHANNEL_LIMIT_HASH = 1286410249L
        @JvmField
        val channelLimitBind =
            ObjectCalls.getMethodBind("ENetConnection", "channel_limit", CHANNEL_LIMIT_HASH)

        private const val BROADCAST_HASH = 2772371345L
        @JvmField
        val broadcastBind =
            ObjectCalls.getMethodBind("ENetConnection", "broadcast", BROADCAST_HASH)

        private const val COMPRESS_HASH = 2660215187L
        @JvmField
        val compressBind =
            ObjectCalls.getMethodBind("ENetConnection", "compress", COMPRESS_HASH)

        private const val DTLS_SERVER_SETUP_HASH = 1262296096L
        @JvmField
        val dtlsServerSetupBind =
            ObjectCalls.getMethodBind("ENetConnection", "dtls_server_setup", DTLS_SERVER_SETUP_HASH)

        private const val DTLS_CLIENT_SETUP_HASH = 1966198364L
        @JvmField
        val dtlsClientSetupBind =
            ObjectCalls.getMethodBind("ENetConnection", "dtls_client_setup", DTLS_CLIENT_SETUP_HASH)

        private const val REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        @JvmField
        val refuseNewConnectionsBind =
            ObjectCalls.getMethodBind("ENetConnection", "refuse_new_connections", REFUSE_NEW_CONNECTIONS_HASH)

        private const val POP_STATISTIC_HASH = 2166904170L
        @JvmField
        val popStatisticBind =
            ObjectCalls.getMethodBind("ENetConnection", "pop_statistic", POP_STATISTIC_HASH)

        private const val GET_MAX_CHANNELS_HASH = 3905245786L
        @JvmField
        val getMaxChannelsBind =
            ObjectCalls.getMethodBind("ENetConnection", "get_max_channels", GET_MAX_CHANNELS_HASH)

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        @JvmField
        val getLocalPortBind =
            ObjectCalls.getMethodBind("ENetConnection", "get_local_port", GET_LOCAL_PORT_HASH)

        private const val GET_PEERS_HASH = 2915620761L
        @JvmField
        val getPeersBind =
            ObjectCalls.getMethodBind("ENetConnection", "get_peers", GET_PEERS_HASH)

        private const val SOCKET_SEND_HASH = 1100646812L
        @JvmField
        val socketSendBind =
            ObjectCalls.getMethodBind("ENetConnection", "socket_send", SOCKET_SEND_HASH)
    }
}
