package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ENetConnection
 */
class ENetConnection(handle: GodotHandle) : RefCounted(handle) {
    fun createHostBound(bindAddress: String, bindPort: Int, maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(createHostBoundBind, segment, bindAddress, bindPort, maxPeers, maxChannels, inBandwidth, outBandwidth))
    }

    fun createHost(maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithFourIntArgsRetLong(createHostBind, segment, maxPeers, maxChannels, inBandwidth, outBandwidth))
    }

    fun destroy() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(destroyBind, segment)
    }

    fun connectToHost(address: String, port: Int, channels: Int = 0, data: Int = 0): ENetPacketPeer? {
        checkOpen()
        return ENetPacketPeer.wrap(ObjectCalls.ptrcallWithStringAndThreeIntArgsRetObject(connectToHostBind, segment, address, port, channels, data))
    }

    fun service(timeout: Int = 0): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(serviceBind, segment, timeout)
    }

    fun flush() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(flushBind, segment)
    }

    fun bandwidthLimit(inBandwidth: Int = 0, outBandwidth: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(bandwidthLimitBind, segment, inBandwidth, outBandwidth)
    }

    fun channelLimit(limit: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(channelLimitBind, segment, limit)
    }

    fun broadcast(channel: Int, packet: ByteArray, flags: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntByteArrayIntArgs(broadcastBind, segment, channel, packet, flags)
    }

    fun compress(mode: ENetConnection.CompressionMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(compressBind, segment, mode.value)
    }

    fun dtlsServerSetup(serverOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(dtlsServerSetupBind, segment, serverOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun dtlsClientSetup(hostname: String, clientOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndObjectArgRetLong(dtlsClientSetupBind, segment, hostname, clientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun refuseNewConnections(refuse: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(refuseNewConnectionsBind, segment, refuse)
    }

    fun popStatistic(statistic: ENetConnection.HostStatistic): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(popStatisticBind, segment, statistic.value)
    }

    fun getMaxChannels(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxChannelsBind, segment)
    }

    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, segment)
    }

    fun getPeers(): List<ENetPacketPeer> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getPeersBind, segment, ENetPacketPeer::wrap)
    }

    fun socketSend(destinationAddress: String, destinationPort: Int, packet: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithStringIntByteArrayArgs(socketSendBind, segment, destinationAddress, destinationPort, packet)
    }

    @JvmInline
    value class CompressionMode(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: CompressionMode get() = CompressionMode(0L)
            val RANGE_CODER: CompressionMode get() = CompressionMode(1L)
            val FASTLZ: CompressionMode get() = CompressionMode(2L)
            val ZLIB: CompressionMode get() = CompressionMode(3L)
            val ZSTD: CompressionMode get() = CompressionMode(4L)
        }
    }

    @JvmInline
    value class EventType(override val value: Long) : GodotEnumValue {
        companion object {
            val ERROR: EventType get() = EventType(-1L)
            val NONE: EventType get() = EventType(0L)
            val CONNECT: EventType get() = EventType(1L)
            val DISCONNECT: EventType get() = EventType(2L)
            val RECEIVE: EventType get() = EventType(3L)
        }
    }

    @JvmInline
    value class HostStatistic(override val value: Long) : GodotEnumValue {
        companion object {
            val SENT_DATA: HostStatistic get() = HostStatistic(0L)
            val SENT_PACKETS: HostStatistic get() = HostStatistic(1L)
            val RECEIVED_DATA: HostStatistic get() = HostStatistic(2L)
            val RECEIVED_PACKETS: HostStatistic get() = HostStatistic(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ENetConnection? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ENetConnection? =
            if (handle.address() == 0L) null else ENetConnection(GodotHandle(handle))

        private const val CREATE_HOST_BOUND_HASH = 1515002313L
        private val createHostBoundBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "create_host_bound", CREATE_HOST_BOUND_HASH)
        }

        private const val CREATE_HOST_HASH = 117198950L
        private val createHostBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "create_host", CREATE_HOST_HASH)
        }

        private const val DESTROY_HASH = 3218959716L
        private val destroyBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "destroy", DESTROY_HASH)
        }

        private const val CONNECT_TO_HOST_HASH = 2171300490L
        private val connectToHostBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "connect_to_host", CONNECT_TO_HOST_HASH)
        }

        private const val SERVICE_HASH = 2402345344L
        private val serviceBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "service", SERVICE_HASH)
        }

        private const val FLUSH_HASH = 3218959716L
        private val flushBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "flush", FLUSH_HASH)
        }

        private const val BANDWIDTH_LIMIT_HASH = 2302169788L
        private val bandwidthLimitBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "bandwidth_limit", BANDWIDTH_LIMIT_HASH)
        }

        private const val CHANNEL_LIMIT_HASH = 1286410249L
        private val channelLimitBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "channel_limit", CHANNEL_LIMIT_HASH)
        }

        private const val BROADCAST_HASH = 2772371345L
        private val broadcastBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "broadcast", BROADCAST_HASH)
        }

        private const val COMPRESS_HASH = 2660215187L
        private val compressBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "compress", COMPRESS_HASH)
        }

        private const val DTLS_SERVER_SETUP_HASH = 1262296096L
        private val dtlsServerSetupBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "dtls_server_setup", DTLS_SERVER_SETUP_HASH)
        }

        private const val DTLS_CLIENT_SETUP_HASH = 1966198364L
        private val dtlsClientSetupBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "dtls_client_setup", DTLS_CLIENT_SETUP_HASH)
        }

        private const val REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        private val refuseNewConnectionsBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "refuse_new_connections", REFUSE_NEW_CONNECTIONS_HASH)
        }

        private const val POP_STATISTIC_HASH = 2166904170L
        private val popStatisticBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "pop_statistic", POP_STATISTIC_HASH)
        }

        private const val GET_MAX_CHANNELS_HASH = 3905245786L
        private val getMaxChannelsBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "get_max_channels", GET_MAX_CHANNELS_HASH)
        }

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        private val getLocalPortBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "get_local_port", GET_LOCAL_PORT_HASH)
        }

        private const val GET_PEERS_HASH = 2915620761L
        private val getPeersBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "get_peers", GET_PEERS_HASH)
        }

        private const val SOCKET_SEND_HASH = 1100646812L
        private val socketSendBind by lazy {
            ObjectCalls.getMethodBind("ENetConnection", "socket_send", SOCKET_SEND_HASH)
        }
    }
}
