package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: ENetPacketPeer
 */
class ENetPacketPeer(handle: MemorySegment) : PacketPeer(handle) {
    fun peerDisconnect(data: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(peerDisconnectBind, handle, data)
    }

    fun peerDisconnectLater(data: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(peerDisconnectLaterBind, handle, data)
    }

    fun peerDisconnectNow(data: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(peerDisconnectNowBind, handle, data)
    }

    fun ping() {
        ObjectCalls.ptrcallNoArgs(pingBind, handle)
    }

    fun pingInterval(pingInterval: Int) {
        ObjectCalls.ptrcallWithIntArg(pingIntervalBind, handle, pingInterval)
    }

    fun reset() {
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    fun send(channel: Int, packet: ByteArray, flags: Int): Long {
        return ObjectCalls.ptrcallWithIntByteArrayIntArgsRetLong(sendBind, handle, channel, packet, flags)
    }

    fun throttleConfigure(interval: Int, acceleration: Int, deceleration: Int) {
        ObjectCalls.ptrcallWithThreeIntArgs(throttleConfigureBind, handle, interval, acceleration, deceleration)
    }

    fun setTimeout(timeout: Int, timeoutMin: Int, timeoutMax: Int) {
        ObjectCalls.ptrcallWithThreeIntArgs(setTimeoutBind, handle, timeout, timeoutMin, timeoutMax)
    }

    fun getPacketFlags(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketFlagsBind, handle)
    }

    fun getRemoteAddress(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRemoteAddressBind, handle)
    }

    fun getRemotePort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRemotePortBind, handle)
    }

    fun getStatistic(statistic: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getStatisticBind, handle, statistic)
    }

    fun getState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStateBind, handle)
    }

    fun getChannels(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getChannelsBind, handle)
    }

    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    companion object {
        const val PACKET_LOSS_SCALE: Long = 65536L
        const val PACKET_THROTTLE_SCALE: Long = 32L
        const val FLAG_RELIABLE: Long = 1L
        const val FLAG_UNSEQUENCED: Long = 2L
        const val FLAG_UNRELIABLE_FRAGMENT: Long = 8L
        const val STATE_DISCONNECTED: Long = 0L
        const val STATE_CONNECTING: Long = 1L
        const val STATE_ACKNOWLEDGING_CONNECT: Long = 2L
        const val STATE_CONNECTION_PENDING: Long = 3L
        const val STATE_CONNECTION_SUCCEEDED: Long = 4L
        const val STATE_CONNECTED: Long = 5L
        const val STATE_DISCONNECT_LATER: Long = 6L
        const val STATE_DISCONNECTING: Long = 7L
        const val STATE_ACKNOWLEDGING_DISCONNECT: Long = 8L
        const val STATE_ZOMBIE: Long = 9L
        const val PEER_PACKET_LOSS: Long = 0L
        const val PEER_PACKET_LOSS_VARIANCE: Long = 1L
        const val PEER_PACKET_LOSS_EPOCH: Long = 2L
        const val PEER_ROUND_TRIP_TIME: Long = 3L
        const val PEER_ROUND_TRIP_TIME_VARIANCE: Long = 4L
        const val PEER_LAST_ROUND_TRIP_TIME: Long = 5L
        const val PEER_LAST_ROUND_TRIP_TIME_VARIANCE: Long = 6L
        const val PEER_PACKET_THROTTLE: Long = 7L
        const val PEER_PACKET_THROTTLE_LIMIT: Long = 8L
        const val PEER_PACKET_THROTTLE_COUNTER: Long = 9L
        const val PEER_PACKET_THROTTLE_EPOCH: Long = 10L
        const val PEER_PACKET_THROTTLE_ACCELERATION: Long = 11L
        const val PEER_PACKET_THROTTLE_DECELERATION: Long = 12L
        const val PEER_PACKET_THROTTLE_INTERVAL: Long = 13L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ENetPacketPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ENetPacketPeer? =
            if (handle.address() == 0L) null else ENetPacketPeer(handle)

        private const val PEER_DISCONNECT_HASH = 1995695955L
        private val peerDisconnectBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect", PEER_DISCONNECT_HASH)
        }

        private const val PEER_DISCONNECT_LATER_HASH = 1995695955L
        private val peerDisconnectLaterBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect_later", PEER_DISCONNECT_LATER_HASH)
        }

        private const val PEER_DISCONNECT_NOW_HASH = 1995695955L
        private val peerDisconnectNowBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect_now", PEER_DISCONNECT_NOW_HASH)
        }

        private const val PING_HASH = 3218959716L
        private val pingBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "ping", PING_HASH)
        }

        private const val PING_INTERVAL_HASH = 1286410249L
        private val pingIntervalBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "ping_interval", PING_INTERVAL_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "reset", RESET_HASH)
        }

        private const val SEND_HASH = 120522849L
        private val sendBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "send", SEND_HASH)
        }

        private const val THROTTLE_CONFIGURE_HASH = 1649997291L
        private val throttleConfigureBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "throttle_configure", THROTTLE_CONFIGURE_HASH)
        }

        private const val SET_TIMEOUT_HASH = 1649997291L
        private val setTimeoutBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "set_timeout", SET_TIMEOUT_HASH)
        }

        private const val GET_PACKET_FLAGS_HASH = 3905245786L
        private val getPacketFlagsBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_packet_flags", GET_PACKET_FLAGS_HASH)
        }

        private const val GET_REMOTE_ADDRESS_HASH = 201670096L
        private val getRemoteAddressBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_remote_address", GET_REMOTE_ADDRESS_HASH)
        }

        private const val GET_REMOTE_PORT_HASH = 3905245786L
        private val getRemotePortBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_remote_port", GET_REMOTE_PORT_HASH)
        }

        private const val GET_STATISTIC_HASH = 1642578323L
        private val getStatisticBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_statistic", GET_STATISTIC_HASH)
        }

        private const val GET_STATE_HASH = 711068532L
        private val getStateBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_state", GET_STATE_HASH)
        }

        private const val GET_CHANNELS_HASH = 3905245786L
        private val getChannelsBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_channels", GET_CHANNELS_HASH)
        }

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("ENetPacketPeer", "is_active", IS_ACTIVE_HASH)
        }
    }
}
