package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A wrapper class for an ENetPeer (http://enet.bespin.org/group__peer.html).
 *
 * Generated from Godot docs: ENetPacketPeer
 */
class ENetPacketPeer(handle: GodotHandle) : PacketPeer(handle) {
    /**
     * Request a disconnection from a peer. An `ENetConnection.EventType.DISCONNECT` will be generated
     * during `ENetConnection.service` once the disconnection is complete.
     *
     * Generated from Godot docs: ENetPacketPeer.peer_disconnect
     */
    fun peerDisconnect(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.peerDisconnectBind, segment, data)
    }

    /**
     * Request a disconnection from a peer, but only after all queued outgoing packets are sent. An
     * `ENetConnection.EventType.DISCONNECT` will be generated during `ENetConnection.service` once the
     * disconnection is complete.
     *
     * Generated from Godot docs: ENetPacketPeer.peer_disconnect_later
     */
    fun peerDisconnectLater(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.peerDisconnectLaterBind, segment, data)
    }

    /**
     * Force an immediate disconnection from a peer. No `ENetConnection.EventType.DISCONNECT` will be
     * generated. The foreign peer is not guaranteed to receive the disconnect notification, and is
     * reset immediately upon return from this function.
     *
     * Generated from Godot docs: ENetPacketPeer.peer_disconnect_now
     */
    fun peerDisconnectNow(data: Int = 0) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.peerDisconnectNowBind, segment, data)
    }

    /**
     * Sends a ping request to a peer. ENet automatically pings all connected peers at regular
     * intervals, however, this function may be called to ensure more frequent ping requests.
     *
     * Generated from Godot docs: ENetPacketPeer.ping
     */
    fun ping() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.pingBind, segment)
    }

    /**
     * Sets the `ping_interval` in milliseconds at which pings will be sent to a peer. Pings are used
     * both to monitor the liveness of the connection and also to dynamically adjust the throttle
     * during periods of low traffic so that the throttle has reasonable responsiveness during traffic
     * spikes. The default ping interval is `500` milliseconds.
     *
     * Generated from Godot docs: ENetPacketPeer.ping_interval
     */
    fun pingInterval(pingInterval: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.pingIntervalBind, segment, pingInterval)
    }

    /**
     * Forcefully disconnects a peer. The foreign host represented by the peer is not notified of the
     * disconnection and will timeout on its connection to the local host.
     *
     * Generated from Godot docs: ENetPacketPeer.reset
     */
    fun reset() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.resetBind, segment)
    }

    /**
     * Queues a `packet` to be sent over the specified `channel`. See `FLAG_*` constants for available
     * packet flags.
     *
     * Generated from Godot docs: ENetPacketPeer.send
     */
    fun send(channel: Int, packet: ByteArray, flags: Int): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntByteArrayIntArgsRetLong(Binds.sendBind, segment, channel, packet, flags))
    }

    /**
     * Configures throttle parameter for a peer. Unreliable packets are dropped by ENet in response to
     * the varying conditions of the Internet connection to the peer. The throttle represents a
     * probability that an unreliable packet should not be dropped and thus sent by ENet to the peer.
     * By measuring fluctuations in round trip times of reliable packets over the specified `interval`,
     * ENet will either increase the probability by the amount specified in the `acceleration`
     * parameter, or decrease it by the amount specified in the `deceleration` parameter (both are
     * ratios to `PACKET_THROTTLE_SCALE`). When the throttle has a value of `PACKET_THROTTLE_SCALE`, no
     * unreliable packets are dropped by ENet, and so 100% of all unreliable packets will be sent. When
     * the throttle has a value of `0`, all unreliable packets are dropped by ENet, and so 0% of all
     * unreliable packets will be sent. Intermediate values for the throttle represent intermediate
     * probabilities between 0% and 100% of unreliable packets being sent. The bandwidth limits of the
     * local and foreign hosts are taken into account to determine a sensible limit for the throttle
     * probability above which it should not raise even in the best of conditions.
     *
     * Generated from Godot docs: ENetPacketPeer.throttle_configure
     */
    fun throttleConfigure(interval: Int, acceleration: Int, deceleration: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntArgs(Binds.throttleConfigureBind, segment, interval, acceleration, deceleration)
    }

    /**
     * Sets the timeout parameters for a peer. The timeout parameters control how and when a peer will
     * timeout from a failure to acknowledge reliable traffic. Timeout values are expressed in
     * milliseconds. The `timeout` is a factor that, multiplied by a value based on the average round
     * trip time, will determine the timeout limit for a reliable packet. When that limit is reached,
     * the timeout will be doubled, and the peer will be disconnected if that limit has reached
     * `timeout_min`. The `timeout_max` parameter, on the other hand, defines a fixed timeout for which
     * any packet must be acknowledged or the peer will be dropped.
     *
     * Generated from Godot docs: ENetPacketPeer.set_timeout
     */
    fun setTimeout(timeout: Int, timeoutMin: Int, timeoutMax: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntArgs(Binds.setTimeoutBind, segment, timeout, timeoutMin, timeoutMax)
    }

    /**
     * Returns the ENet flags of the next packet in the received queue. See `FLAG_*` constants for
     * available packet flags. Note that not all flags are replicated from the sending peer to the
     * receiving peer.
     *
     * Generated from Godot docs: ENetPacketPeer.get_packet_flags
     */
    fun getPacketFlags(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPacketFlagsBind, segment)
    }

    /**
     * Returns the IP address of this peer.
     *
     * Generated from Godot docs: ENetPacketPeer.get_remote_address
     */
    fun getRemoteAddress(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getRemoteAddressBind, segment)
    }

    /**
     * Returns the remote port of this peer.
     *
     * Generated from Godot docs: ENetPacketPeer.get_remote_port
     */
    fun getRemotePort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRemotePortBind, segment)
    }

    /**
     * Returns the requested `statistic` for this peer.
     *
     * Generated from Godot docs: ENetPacketPeer.get_statistic
     */
    fun getStatistic(statistic: ENetPacketPeer.PeerStatistic): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getStatisticBind, segment, statistic.value)
    }

    /**
     * Returns the current peer state.
     *
     * Generated from Godot docs: ENetPacketPeer.get_state
     */
    fun getState(): ENetPacketPeer.PeerState {
        checkOpen()
        return ENetPacketPeer.PeerState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStateBind, segment))
    }

    /**
     * Returns the number of channels allocated for communication with peer.
     *
     * Generated from Godot docs: ENetPacketPeer.get_channels
     */
    fun getChannels(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getChannelsBind, segment)
    }

    /**
     * Returns `true` if the peer is currently active (i.e. the associated `ENetConnection` is still
     * valid).
     *
     * Generated from Godot docs: ENetPacketPeer.is_active
     */
    fun isActive(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
    }

    /**
     * Godot's `ENetPacketPeer.PeerState` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ENetPacketPeer.PeerState.<NAME>`).
     *
     * Generated from Godot docs: ENetPacketPeer.PeerState
     */
    @JvmInline
    value class PeerState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The peer is disconnected.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_DISCONNECTED
             */
            val DISCONNECTED: PeerState get() = PeerState(0L)
            /**
             * The peer is currently attempting to connect.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_CONNECTING
             */
            val CONNECTING: PeerState get() = PeerState(1L)
            /**
             * The peer has acknowledged the connection request.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_ACKNOWLEDGING_CONNECT
             */
            val ACKNOWLEDGING_CONNECT: PeerState get() = PeerState(2L)
            /**
             * The peer is currently connecting.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_CONNECTION_PENDING
             */
            val CONNECTION_PENDING: PeerState get() = PeerState(3L)
            /**
             * The peer has successfully connected, but is not ready to communicate with yet
             * (`PeerState.CONNECTED`).
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_CONNECTION_SUCCEEDED
             */
            val CONNECTION_SUCCEEDED: PeerState get() = PeerState(4L)
            /**
             * The peer is currently connected and ready to communicate with.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_CONNECTED
             */
            val CONNECTED: PeerState get() = PeerState(5L)
            /**
             * The peer is expected to disconnect after it has no more outgoing packets to send.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_DISCONNECT_LATER
             */
            val DISCONNECT_LATER: PeerState get() = PeerState(6L)
            /**
             * The peer is currently disconnecting.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_DISCONNECTING
             */
            val DISCONNECTING: PeerState get() = PeerState(7L)
            /**
             * The peer has acknowledged the disconnection request.
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_ACKNOWLEDGING_DISCONNECT
             */
            val ACKNOWLEDGING_DISCONNECT: PeerState get() = PeerState(8L)
            /**
             * The peer has lost connection, but is not considered truly disconnected (as the peer didn't
             * acknowledge the disconnection request).
             *
             * Generated from Godot docs: ENetPacketPeer.STATE_ZOMBIE
             */
            val ZOMBIE: PeerState get() = PeerState(9L)
        }
    }

    /**
     * Godot's `ENetPacketPeer.PeerStatistic` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ENetPacketPeer.PeerStatistic.<NAME>`).
     *
     * Generated from Godot docs: ENetPacketPeer.PeerStatistic
     */
    @JvmInline
    value class PeerStatistic(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Mean packet loss of reliable packets as a ratio with respect to the `PACKET_LOSS_SCALE`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_LOSS
             */
            val PACKET_LOSS: PeerStatistic get() = PeerStatistic(0L)
            /**
             * Packet loss variance.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_LOSS_VARIANCE
             */
            val PACKET_LOSS_VARIANCE: PeerStatistic get() = PeerStatistic(1L)
            /**
             * The time at which packet loss statistics were last updated (in milliseconds since the connection
             * started). The interval for packet loss statistics updates is 10 seconds, and at least one packet
             * must have been sent since the last statistics update.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_LOSS_EPOCH
             */
            val PACKET_LOSS_EPOCH: PeerStatistic get() = PeerStatistic(2L)
            /**
             * Mean packet round trip time for reliable packets.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_ROUND_TRIP_TIME
             */
            val ROUND_TRIP_TIME: PeerStatistic get() = PeerStatistic(3L)
            /**
             * Variance of the mean round trip time.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_ROUND_TRIP_TIME_VARIANCE
             */
            val ROUND_TRIP_TIME_VARIANCE: PeerStatistic get() = PeerStatistic(4L)
            /**
             * Last recorded round trip time for a reliable packet.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_LAST_ROUND_TRIP_TIME
             */
            val LAST_ROUND_TRIP_TIME: PeerStatistic get() = PeerStatistic(5L)
            /**
             * Variance of the last trip time recorded.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_LAST_ROUND_TRIP_TIME_VARIANCE
             */
            val LAST_ROUND_TRIP_TIME_VARIANCE: PeerStatistic get() = PeerStatistic(6L)
            /**
             * The peer's current throttle status.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE
             */
            val PACKET_THROTTLE: PeerStatistic get() = PeerStatistic(7L)
            /**
             * The maximum number of unreliable packets that should not be dropped. This value is always
             * greater than or equal to `1`. The initial value is equal to `PACKET_THROTTLE_SCALE`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_LIMIT
             */
            val PACKET_THROTTLE_LIMIT: PeerStatistic get() = PeerStatistic(8L)
            /**
             * Internal value used to increment the packet throttle counter. The value is hardcoded to `7` and
             * cannot be changed. You probably want to look at `PeerStatistic.PACKET_THROTTLE_ACCELERATION`
             * instead.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_COUNTER
             */
            val PACKET_THROTTLE_COUNTER: PeerStatistic get() = PeerStatistic(9L)
            /**
             * The time at which throttle statistics were last updated (in milliseconds since the connection
             * started). The interval for throttle statistics updates is
             * `PeerStatistic.PACKET_THROTTLE_INTERVAL`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_EPOCH
             */
            val PACKET_THROTTLE_EPOCH: PeerStatistic get() = PeerStatistic(10L)
            /**
             * The throttle's acceleration factor. Higher values will make ENet adapt to fluctuating network
             * conditions faster, causing unrelaible packets to be sent more often. The default value is `2`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_ACCELERATION
             */
            val PACKET_THROTTLE_ACCELERATION: PeerStatistic get() = PeerStatistic(11L)
            /**
             * The throttle's deceleration factor. Higher values will make ENet adapt to fluctuating network
             * conditions faster, causing unrelaible packets to be sent less often. The default value is `2`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_DECELERATION
             */
            val PACKET_THROTTLE_DECELERATION: PeerStatistic get() = PeerStatistic(12L)
            /**
             * The interval over which the lowest mean round trip time should be measured for use by the
             * throttle mechanism (in milliseconds). The default value is `5000`.
             *
             * Generated from Godot docs: ENetPacketPeer.PEER_PACKET_THROTTLE_INTERVAL
             */
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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ENetPacketPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(ENetPacketPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ENetPacketPeer? =
            if (handle.address() == 0L) null else ENetPacketPeer(GodotHandle(handle))
    }

    private object Binds {
        private const val PEER_DISCONNECT_HASH = 1995695955L
        @JvmField
        val peerDisconnectBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect", PEER_DISCONNECT_HASH)

        private const val PEER_DISCONNECT_LATER_HASH = 1995695955L
        @JvmField
        val peerDisconnectLaterBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect_later", PEER_DISCONNECT_LATER_HASH)

        private const val PEER_DISCONNECT_NOW_HASH = 1995695955L
        @JvmField
        val peerDisconnectNowBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "peer_disconnect_now", PEER_DISCONNECT_NOW_HASH)

        private const val PING_HASH = 3218959716L
        @JvmField
        val pingBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "ping", PING_HASH)

        private const val PING_INTERVAL_HASH = 1286410249L
        @JvmField
        val pingIntervalBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "ping_interval", PING_INTERVAL_HASH)

        private const val RESET_HASH = 3218959716L
        @JvmField
        val resetBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "reset", RESET_HASH)

        private const val SEND_HASH = 120522849L
        @JvmField
        val sendBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "send", SEND_HASH)

        private const val THROTTLE_CONFIGURE_HASH = 1649997291L
        @JvmField
        val throttleConfigureBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "throttle_configure", THROTTLE_CONFIGURE_HASH)

        private const val SET_TIMEOUT_HASH = 1649997291L
        @JvmField
        val setTimeoutBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "set_timeout", SET_TIMEOUT_HASH)

        private const val GET_PACKET_FLAGS_HASH = 3905245786L
        @JvmField
        val getPacketFlagsBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_packet_flags", GET_PACKET_FLAGS_HASH)

        private const val GET_REMOTE_ADDRESS_HASH = 201670096L
        @JvmField
        val getRemoteAddressBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_remote_address", GET_REMOTE_ADDRESS_HASH)

        private const val GET_REMOTE_PORT_HASH = 3905245786L
        @JvmField
        val getRemotePortBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_remote_port", GET_REMOTE_PORT_HASH)

        private const val GET_STATISTIC_HASH = 1642578323L
        @JvmField
        val getStatisticBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_statistic", GET_STATISTIC_HASH)

        private const val GET_STATE_HASH = 711068532L
        @JvmField
        val getStateBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_state", GET_STATE_HASH)

        private const val GET_CHANNELS_HASH = 3905245786L
        @JvmField
        val getChannelsBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "get_channels", GET_CHANNELS_HASH)

        private const val IS_ACTIVE_HASH = 36873697L
        @JvmField
        val isActiveBind =
            ObjectCalls.getMethodBind("ENetPacketPeer", "is_active", IS_ACTIVE_HASH)
    }
}
