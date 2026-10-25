package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * High-level multiplayer API implementation.
 *
 * Generated from Godot docs: SceneMultiplayer
 */
class SceneMultiplayer(handle: GodotHandle) : MultiplayerAPI(handle) {
    var rootPath: NodePath
        @JvmName("rootPathProperty")
        get() = getRootPath()
        @JvmName("setRootPathProperty")
        set(value) = setRootPath(value)

    val authCallback: GodotCallable?
        @JvmName("authCallbackProperty")
        get() = getAuthCallback()

    var authTimeout: Double
        @JvmName("authTimeoutProperty")
        get() = getAuthTimeout()
        @JvmName("setAuthTimeoutProperty")
        set(value) = setAuthTimeout(value)

    var allowObjectDecoding: Boolean
        @JvmName("allowObjectDecodingProperty")
        get() = isObjectDecodingAllowed()
        @JvmName("setAllowObjectDecodingProperty")
        set(value) = setAllowObjectDecoding(value)

    var refuseNewConnections: Boolean
        @JvmName("refuseNewConnectionsProperty")
        get() = isRefusingNewConnections()
        @JvmName("setRefuseNewConnectionsProperty")
        set(value) = setRefuseNewConnections(value)

    var serverRelay: Boolean
        @JvmName("serverRelayProperty")
        get() = isServerRelayEnabled()
        @JvmName("setServerRelayProperty")
        set(value) = setServerRelayEnabled(value)

    var maxSyncPacketSize: Int
        @JvmName("maxSyncPacketSizeProperty")
        get() = getMaxSyncPacketSize()
        @JvmName("setMaxSyncPacketSizeProperty")
        set(value) = setMaxSyncPacketSize(value)

    var maxDeltaPacketSize: Int
        @JvmName("maxDeltaPacketSizeProperty")
        get() = getMaxDeltaPacketSize()
        @JvmName("setMaxDeltaPacketSizeProperty")
        set(value) = setMaxDeltaPacketSize(value)

    /**
     * The root path to use for RPCs and replication. Instead of an absolute path, a relative path will
     * be used to find the node upon which the RPC should be executed. This effectively allows to have
     * different branches of the scene tree to be managed by different MultiplayerAPI, allowing for
     * example to run both client and server in the same scene.
     *
     * Generated from Godot docs: SceneMultiplayer.set_root_path
     */
    fun setRootPath(path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRootPathBind, segment, path)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The root path to use for RPCs and replication. Instead of an absolute path, a relative path will
     * be used to find the node upon which the RPC should be executed. This effectively allows to have
     * different branches of the scene tree to be managed by different MultiplayerAPI, allowing for
     * example to run both client and server in the same scene.
     *
     * Generated from Godot docs: SceneMultiplayer.get_root_path
     */
    fun getRootPath(): NodePath {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRootPathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Clears the current SceneMultiplayer network state (you shouldn't call this unless you know what
     * you are doing).
     *
     * Generated from Godot docs: SceneMultiplayer.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Disconnects the peer identified by `id`, removing it from the list of connected peers, and
     * closing the underlying connection with it.
     *
     * Generated from Godot docs: SceneMultiplayer.disconnect_peer
     */
    fun disconnectPeer(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.disconnectPeerBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the IDs of the peers currently trying to authenticate with this `MultiplayerAPI`.
     *
     * Generated from Godot docs: SceneMultiplayer.get_authenticating_peers
     */
    fun getAuthenticatingPeers(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getAuthenticatingPeersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sends the specified `data` to the remote peer identified by `id` as part of an authentication
     * message. This can be used to authenticate peers, and control when
     * `MultiplayerAPI.peer_connected` is emitted (and the remote peer accepted as one of the connected
     * peers).
     *
     * Generated from Godot docs: SceneMultiplayer.send_auth
     */
    fun sendAuth(id: Int, data: ByteArray): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithIntAndByteArrayArgRetLong(Binds.sendAuthBind, segment, id, data))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Mark the authentication step as completed for the remote peer identified by `id`. The
     * `MultiplayerAPI.peer_connected` signal will be emitted for this peer once the remote side also
     * completes the authentication. No further authentication messages are expected to be received
     * from this peer. If a peer disconnects before completing authentication, either due to a network
     * issue, the `auth_timeout` expiring, or manually calling `disconnect_peer`, the
     * `peer_authentication_failed` signal will be emitted instead of
     * `MultiplayerAPI.peer_disconnected`.
     *
     * Generated from Godot docs: SceneMultiplayer.complete_auth
     */
    fun completeAuth(id: Int): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithIntArgRetLong(Binds.completeAuthBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The callback to execute when receiving authentication data sent via `send_auth`. If the
     * `Callable` is empty (default), peers will be automatically accepted as soon as they connect.
     *
     * Generated from Godot docs: SceneMultiplayer.set_auth_callback
     */
    fun setAuthCallback(callback: GodotCallable) {
        checkOpen()
        ObjectCalls.ptrcallWithCallableArg(Binds.setAuthCallbackBind, segment, callback.target.segment, callback.method)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(callback)
    }

    /**
     * The callback to execute when receiving authentication data sent via `send_auth`. If the
     * `Callable` is empty (default), peers will be automatically accepted as soon as they connect.
     *
     * Generated from Godot docs: SceneMultiplayer.get_auth_callback
     */
    fun getAuthCallback(): GodotCallable? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetCallable(Binds.getAuthCallbackBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set to a value greater than `0.0`, the maximum duration in seconds peers can stay in the
     * authenticating state, after which the authentication will automatically fail. See the
     * `peer_authenticating` and `peer_authentication_failed` signals.
     *
     * Generated from Godot docs: SceneMultiplayer.set_auth_timeout
     */
    fun setAuthTimeout(timeout: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAuthTimeoutBind, segment, timeout)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If set to a value greater than `0.0`, the maximum duration in seconds peers can stay in the
     * authenticating state, after which the authentication will automatically fail. See the
     * `peer_authenticating` and `peer_authentication_failed` signals.
     *
     * Generated from Godot docs: SceneMultiplayer.get_auth_timeout
     */
    fun getAuthTimeout(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAuthTimeoutBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the MultiplayerAPI's `MultiplayerAPI.multiplayer_peer` refuses new incoming
     * connections.
     *
     * Generated from Godot docs: SceneMultiplayer.set_refuse_new_connections
     */
    fun setRefuseNewConnections(refuse: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setRefuseNewConnectionsBind, segment, refuse)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the MultiplayerAPI's `MultiplayerAPI.multiplayer_peer` refuses new incoming
     * connections.
     *
     * Generated from Godot docs: SceneMultiplayer.is_refusing_new_connections
     */
    fun isRefusingNewConnections(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isRefusingNewConnectionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the MultiplayerAPI will allow encoding and decoding of object during RPCs. Warning:
     * Deserialized objects can contain code which gets executed. Do not use this option if the
     * serialized object comes from untrusted sources to avoid potential security threat such as remote
     * code execution.
     *
     * Generated from Godot docs: SceneMultiplayer.set_allow_object_decoding
     */
    fun setAllowObjectDecoding(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowObjectDecodingBind, segment, enable)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the MultiplayerAPI will allow encoding and decoding of object during RPCs. Warning:
     * Deserialized objects can contain code which gets executed. Do not use this option if the
     * serialized object comes from untrusted sources to avoid potential security threat such as remote
     * code execution.
     *
     * Generated from Godot docs: SceneMultiplayer.is_object_decoding_allowed
     */
    fun isObjectDecodingAllowed(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isObjectDecodingAllowedBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Enable or disable the server feature that notifies clients of other peers'
     * connection/disconnection, and relays messages between them. When this option is `false`, clients
     * won't be automatically notified of other peers and won't be able to send them packets through
     * the server. Note: Changing this option while other peers are connected may lead to unexpected
     * behaviors. Note: Support for this feature may depend on the current `MultiplayerPeer`
     * configuration. See `MultiplayerPeer.is_server_relay_supported`.
     *
     * Generated from Godot docs: SceneMultiplayer.set_server_relay_enabled
     */
    fun setServerRelayEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setServerRelayEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enable or disable the server feature that notifies clients of other peers'
     * connection/disconnection, and relays messages between them. When this option is `false`, clients
     * won't be automatically notified of other peers and won't be able to send them packets through
     * the server. Note: Changing this option while other peers are connected may lead to unexpected
     * behaviors. Note: Support for this feature may depend on the current `MultiplayerPeer`
     * configuration. See `MultiplayerPeer.is_server_relay_supported`.
     *
     * Generated from Godot docs: SceneMultiplayer.is_server_relay_enabled
     */
    fun isServerRelayEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isServerRelayEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sends the given raw `bytes` to a specific peer identified by `id` (see
     * `MultiplayerPeer.set_target_peer`). Default ID is `0`, i.e. broadcast to all peers.
     *
     * Generated from Godot docs: SceneMultiplayer.send_bytes
     */
    fun sendBytes(bytes: ByteArray, id: Int = 0, mode: MultiplayerPeer.TransferMode = MultiplayerPeer.TransferMode.RELIABLE, channel: Int = 0): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithByteArrayIntLongIntArgsRetLong(Binds.sendBytesBind, segment, bytes, id, mode.value, channel))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum size of each synchronization packet. Higher values increase the chance of receiving full
     * updates in a single frame, but also the chance of packet loss. See `MultiplayerSynchronizer`.
     *
     * Generated from Godot docs: SceneMultiplayer.get_max_sync_packet_size
     */
    fun getMaxSyncPacketSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxSyncPacketSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum size of each synchronization packet. Higher values increase the chance of receiving full
     * updates in a single frame, but also the chance of packet loss. See `MultiplayerSynchronizer`.
     *
     * Generated from Godot docs: SceneMultiplayer.set_max_sync_packet_size
     */
    fun setMaxSyncPacketSize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxSyncPacketSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Maximum size of each delta packet. Higher values increase the chance of receiving full updates
     * in a single frame, but also the chance of causing networking congestion (higher latency,
     * disconnections). See `MultiplayerSynchronizer`.
     *
     * Generated from Godot docs: SceneMultiplayer.get_max_delta_packet_size
     */
    fun getMaxDeltaPacketSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxDeltaPacketSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum size of each delta packet. Higher values increase the chance of receiving full updates
     * in a single frame, but also the chance of causing networking congestion (higher latency,
     * disconnections). See `MultiplayerSynchronizer`.
     *
     * Generated from Godot docs: SceneMultiplayer.set_max_delta_packet_size
     */
    fun setMaxDeltaPacketSize(size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxDeltaPacketSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /** Signal `peer_authenticating(id: int)`; see [TypedSignal]. */
    val peerAuthenticating: Signal1<Long>
        @JvmName("peerAuthenticatingTypedSignal")
        get() = Signal1(this, "peer_authenticating", SignalArgType.LONG)

    /** Signal `peer_authentication_failed(id: int)`; see [TypedSignal]. */
    val peerAuthenticationFailed: Signal1<Long>
        @JvmName("peerAuthenticationFailedTypedSignal")
        get() = Signal1(this, "peer_authentication_failed", SignalArgType.LONG)

    /** Signal `peer_packet(id: int, packet: PackedByteArray)`; see [TypedSignal]. On iOS a PackedByteArray argument is not delivered yet: a connection reports a script error. */
    val peerPacket: Signal2<Long, ByteArray>
        @JvmName("peerPacketTypedSignal")
        get() = Signal2(this, "peer_packet", SignalArgType.LONG, SignalArgType.valueOf<ByteArray>("PackedByteArray", ByteArray::class))

    object Signals {
        const val peerAuthenticating: String = "peer_authenticating"
        const val peerAuthenticationFailed: String = "peer_authentication_failed"
        const val peerPacket: String = "peer_packet"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneMultiplayer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SceneMultiplayer? =
            if (handle.address() == 0L) null else RefCounted.owned(SceneMultiplayer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SceneMultiplayer? =
            if (handle.address() == 0L) null else SceneMultiplayer(GodotHandle(handle))

        /** A new SceneMultiplayer, GDScript's `SceneMultiplayer.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): SceneMultiplayer =
            RefCounted.owned(SceneMultiplayer(GodotHandle(ObjectCalls.constructObject("SceneMultiplayer"))))

        // Downcast a MultiplayerAPI to SceneMultiplayer (null if not).
        @JvmStatic
        fun fromApi(api: MultiplayerAPI?): SceneMultiplayer? =
            api?.takeIf { it.isClass("SceneMultiplayer") }?.let { RefCounted.retained(SceneMultiplayer(it.handle), it) }
    }

    private object Binds {
        private const val SET_ROOT_PATH_HASH = 1348162250L
        @JvmField
        val setRootPathBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_root_path", SET_ROOT_PATH_HASH)

        private const val GET_ROOT_PATH_HASH = 4075236667L
        @JvmField
        val getRootPathBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_root_path", GET_ROOT_PATH_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "clear", CLEAR_HASH)

        private const val DISCONNECT_PEER_HASH = 1286410249L
        @JvmField
        val disconnectPeerBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "disconnect_peer", DISCONNECT_PEER_HASH)

        private const val GET_AUTHENTICATING_PEERS_HASH = 969006518L
        @JvmField
        val getAuthenticatingPeersBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_authenticating_peers", GET_AUTHENTICATING_PEERS_HASH)

        private const val SEND_AUTH_HASH = 506032537L
        @JvmField
        val sendAuthBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "send_auth", SEND_AUTH_HASH)

        private const val COMPLETE_AUTH_HASH = 844576869L
        @JvmField
        val completeAuthBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "complete_auth", COMPLETE_AUTH_HASH)

        private const val SET_AUTH_CALLBACK_HASH = 1611583062L
        @JvmField
        val setAuthCallbackBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_auth_callback", SET_AUTH_CALLBACK_HASH)

        private const val GET_AUTH_CALLBACK_HASH = 1307783378L
        @JvmField
        val getAuthCallbackBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_auth_callback", GET_AUTH_CALLBACK_HASH)

        private const val SET_AUTH_TIMEOUT_HASH = 373806689L
        @JvmField
        val setAuthTimeoutBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_auth_timeout", SET_AUTH_TIMEOUT_HASH)

        private const val GET_AUTH_TIMEOUT_HASH = 1740695150L
        @JvmField
        val getAuthTimeoutBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_auth_timeout", GET_AUTH_TIMEOUT_HASH)

        private const val SET_REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        @JvmField
        val setRefuseNewConnectionsBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_refuse_new_connections", SET_REFUSE_NEW_CONNECTIONS_HASH)

        private const val IS_REFUSING_NEW_CONNECTIONS_HASH = 36873697L
        @JvmField
        val isRefusingNewConnectionsBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_refusing_new_connections", IS_REFUSING_NEW_CONNECTIONS_HASH)

        private const val SET_ALLOW_OBJECT_DECODING_HASH = 2586408642L
        @JvmField
        val setAllowObjectDecodingBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_allow_object_decoding", SET_ALLOW_OBJECT_DECODING_HASH)

        private const val IS_OBJECT_DECODING_ALLOWED_HASH = 36873697L
        @JvmField
        val isObjectDecodingAllowedBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_object_decoding_allowed", IS_OBJECT_DECODING_ALLOWED_HASH)

        private const val SET_SERVER_RELAY_ENABLED_HASH = 2586408642L
        @JvmField
        val setServerRelayEnabledBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_server_relay_enabled", SET_SERVER_RELAY_ENABLED_HASH)

        private const val IS_SERVER_RELAY_ENABLED_HASH = 36873697L
        @JvmField
        val isServerRelayEnabledBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_server_relay_enabled", IS_SERVER_RELAY_ENABLED_HASH)

        private const val SEND_BYTES_HASH = 1307428718L
        @JvmField
        val sendBytesBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "send_bytes", SEND_BYTES_HASH)

        private const val GET_MAX_SYNC_PACKET_SIZE_HASH = 3905245786L
        @JvmField
        val getMaxSyncPacketSizeBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_max_sync_packet_size", GET_MAX_SYNC_PACKET_SIZE_HASH)

        private const val SET_MAX_SYNC_PACKET_SIZE_HASH = 1286410249L
        @JvmField
        val setMaxSyncPacketSizeBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_max_sync_packet_size", SET_MAX_SYNC_PACKET_SIZE_HASH)

        private const val GET_MAX_DELTA_PACKET_SIZE_HASH = 3905245786L
        @JvmField
        val getMaxDeltaPacketSizeBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_max_delta_packet_size", GET_MAX_DELTA_PACKET_SIZE_HASH)

        private const val SET_MAX_DELTA_PACKET_SIZE_HASH = 1286410249L
        @JvmField
        val setMaxDeltaPacketSizeBind =
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_max_delta_packet_size", SET_MAX_DELTA_PACKET_SIZE_HASH)
    }
}
