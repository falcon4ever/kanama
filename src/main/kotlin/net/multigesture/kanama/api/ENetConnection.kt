package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: ENetConnection
 */
class ENetConnection(handle: MemorySegment) : RefCounted(handle) {
    fun createHostBound(bindAddress: String, bindPort: Int, maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): Long {
        return ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(createHostBoundBind, handle, bindAddress, bindPort, maxPeers, maxChannels, inBandwidth, outBandwidth)
    }

    fun createHost(maxPeers: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): Long {
        return ObjectCalls.ptrcallWithFourIntArgsRetLong(createHostBind, handle, maxPeers, maxChannels, inBandwidth, outBandwidth)
    }

    fun destroy() {
        ObjectCalls.ptrcallNoArgs(destroyBind, handle)
    }

    fun connectToHost(address: String, port: Int, channels: Int = 0, data: Int = 0): ENetPacketPeer? {
        return ENetPacketPeer.wrap(ObjectCalls.ptrcallWithStringAndThreeIntArgsRetObject(connectToHostBind, handle, address, port, channels, data))
    }

    fun service(timeout: Int = 0): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(serviceBind, handle, timeout)
    }

    fun flush() {
        ObjectCalls.ptrcallNoArgs(flushBind, handle)
    }

    fun bandwidthLimit(inBandwidth: Int = 0, outBandwidth: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(bandwidthLimitBind, handle, inBandwidth, outBandwidth)
    }

    fun channelLimit(limit: Int) {
        ObjectCalls.ptrcallWithIntArg(channelLimitBind, handle, limit)
    }

    fun broadcast(channel: Int, packet: ByteArray, flags: Int) {
        ObjectCalls.ptrcallWithIntByteArrayIntArgs(broadcastBind, handle, channel, packet, flags)
    }

    fun compress(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(compressBind, handle, mode)
    }

    fun dtlsServerSetup(serverOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(dtlsServerSetupBind, handle, serverOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun dtlsClientSetup(hostname: String, clientOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithStringAndObjectArgRetLong(dtlsClientSetupBind, handle, hostname, clientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun refuseNewConnections(refuse: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(refuseNewConnectionsBind, handle, refuse)
    }

    fun popStatistic(statistic: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(popStatisticBind, handle, statistic)
    }

    fun getMaxChannels(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxChannelsBind, handle)
    }

    fun getLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun getPeers(): List<ENetPacketPeer> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getPeersBind, handle, ENetPacketPeer::fromHandle)
    }

    fun socketSend(destinationAddress: String, destinationPort: Int, packet: ByteArray) {
        ObjectCalls.ptrcallWithStringIntByteArrayArgs(socketSendBind, handle, destinationAddress, destinationPort, packet)
    }

    companion object {
        const val COMPRESS_NONE: Long = 0L
        const val COMPRESS_RANGE_CODER: Long = 1L
        const val COMPRESS_FASTLZ: Long = 2L
        const val COMPRESS_ZLIB: Long = 3L
        const val COMPRESS_ZSTD: Long = 4L
        const val EVENT_ERROR: Long = -1L
        const val EVENT_NONE: Long = 0L
        const val EVENT_CONNECT: Long = 1L
        const val EVENT_DISCONNECT: Long = 2L
        const val EVENT_RECEIVE: Long = 3L
        const val HOST_TOTAL_SENT_DATA: Long = 0L
        const val HOST_TOTAL_SENT_PACKETS: Long = 1L
        const val HOST_TOTAL_RECEIVED_DATA: Long = 2L
        const val HOST_TOTAL_RECEIVED_PACKETS: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ENetConnection? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ENetConnection? =
            if (handle.address() == 0L) null else ENetConnection(handle)

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
