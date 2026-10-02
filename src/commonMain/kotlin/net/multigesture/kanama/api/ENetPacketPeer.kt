package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ENetPacketPeer
 */
class ENetPacketPeer(handle: GodotHandle) : PacketPeer(handle) {
    fun peerDisconnect(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(peerDisconnectBind, segment, data)
    }

    fun peerDisconnectLater(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(peerDisconnectLaterBind, segment, data)
    }

    fun peerDisconnectNow(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(peerDisconnectNowBind, segment, data)
    }

    fun ping() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(pingBind, segment)
    }

    fun pingInterval(pingInterval: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(pingIntervalBind, segment, pingInterval)
    }

    fun reset() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(resetBind, segment)
    }

    fun send(channel: Int, packet: ByteArray, flags: Int): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntByteArrayIntArgsRetLong(sendBind, segment, channel, packet, flags))
    }

    fun throttleConfigure(interval: Int, acceleration: Int, deceleration: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntArgs(throttleConfigureBind, segment, interval, acceleration, deceleration)
    }

    fun setTimeout(timeout: Int, timeoutMin: Int, timeoutMax: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntArgs(setTimeoutBind, segment, timeout, timeoutMin, timeoutMax)
    }

    fun getPacketFlags(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPacketFlagsBind, segment)
    }

    fun getRemoteAddress(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getRemoteAddressBind, segment)
    }

    fun getRemotePort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getRemotePortBind, segment)
    }

    fun getStatistic(statistic: ENetPacketPeer.PeerStatistic): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getStatisticBind, segment, statistic.value)
    }

    fun getState(): ENetPacketPeer.PeerState {
        checkOpen()
        return ENetPacketPeer.PeerState(ObjectCalls.ptrcallNoArgsRetLong(getStateBind, segment))
    }

    fun getChannels(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getChannelsBind, segment)
    }

    fun isActive(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, segment)
    }

    @JvmInline
    value class PeerState(override val value: Long) : GodotEnumValue {
        companion object {
            val DISCONNECTED: PeerState get() = PeerState(0L)
            val CONNECTING: PeerState get() = PeerState(1L)
            val ACKNOWLEDGING_CONNECT: PeerState get() = PeerState(2L)
            val CONNECTION_PENDING: PeerState get() = PeerState(3L)
            val CONNECTION_SUCCEEDED: PeerState get() = PeerState(4L)
            val CONNECTED: PeerState get() = PeerState(5L)
            val DISCONNECT_LATER: PeerState get() = PeerState(6L)
            val DISCONNECTING: PeerState get() = PeerState(7L)
            val ACKNOWLEDGING_DISCONNECT: PeerState get() = PeerState(8L)
            val ZOMBIE: PeerState get() = PeerState(9L)
        }
    }

    @JvmInline
    value class PeerStatistic(override val value: Long) : GodotEnumValue {
        companion object {
            val PACKET_LOSS: PeerStatistic get() = PeerStatistic(0L)
            val PACKET_LOSS_VARIANCE: PeerStatistic get() = PeerStatistic(1L)
            val PACKET_LOSS_EPOCH: PeerStatistic get() = PeerStatistic(2L)
            val ROUND_TRIP_TIME: PeerStatistic get() = PeerStatistic(3L)
            val ROUND_TRIP_TIME_VARIANCE: PeerStatistic get() = PeerStatistic(4L)
            val LAST_ROUND_TRIP_TIME: PeerStatistic get() = PeerStatistic(5L)
            val LAST_ROUND_TRIP_TIME_VARIANCE: PeerStatistic get() = PeerStatistic(6L)
            val PACKET_THROTTLE: PeerStatistic get() = PeerStatistic(7L)
            val PACKET_THROTTLE_LIMIT: PeerStatistic get() = PeerStatistic(8L)
            val PACKET_THROTTLE_COUNTER: PeerStatistic get() = PeerStatistic(9L)
            val PACKET_THROTTLE_EPOCH: PeerStatistic get() = PeerStatistic(10L)
            val PACKET_THROTTLE_ACCELERATION: PeerStatistic get() = PeerStatistic(11L)
            val PACKET_THROTTLE_DECELERATION: PeerStatistic get() = PeerStatistic(12L)
            val PACKET_THROTTLE_INTERVAL: PeerStatistic get() = PeerStatistic(13L)
        }
    }

    companion object {
        const val PACKET_LOSS_SCALE: Long = 65536L
        const val PACKET_THROTTLE_SCALE: Long = 32L
        const val FLAG_RELIABLE: Long = 1L
        const val FLAG_UNSEQUENCED: Long = 2L
        const val FLAG_UNRELIABLE_FRAGMENT: Long = 8L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): ENetPacketPeer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ENetPacketPeer? =
            if (handle.address() == 0L) null else ENetPacketPeer(GodotHandle(handle))

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
