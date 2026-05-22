package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Generated from Godot docs: SceneMultiplayer
 */
class SceneMultiplayer(handle: MemorySegment) : MultiplayerAPI(handle) {
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

    fun setRootPath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootPathBind, handle, path)
    }

    fun getRootPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootPathBind, handle)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun disconnectPeer(id: Int) {
        ObjectCalls.ptrcallWithIntArg(disconnectPeerBind, handle, id)
    }

    fun getAuthenticatingPeers(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getAuthenticatingPeersBind, handle)
    }

    fun sendAuth(id: Int, data: ByteArray): Long {
        return ObjectCalls.ptrcallWithIntAndByteArrayArgRetLong(sendAuthBind, handle, id, data)
    }

    fun completeAuth(id: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(completeAuthBind, handle, id)
    }

    fun setAuthCallback(callback: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setAuthCallbackBind, handle, callback.target.handle, callback.method)
    }

    fun getAuthCallback(): GodotCallable? {
        return ObjectCalls.ptrcallNoArgsRetCallable(getAuthCallbackBind, handle)
    }

    fun setAuthTimeout(timeout: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAuthTimeoutBind, handle, timeout)
    }

    fun getAuthTimeout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAuthTimeoutBind, handle)
    }

    fun setRefuseNewConnections(refuse: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRefuseNewConnectionsBind, handle, refuse)
    }

    fun isRefusingNewConnections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRefusingNewConnectionsBind, handle)
    }

    fun setAllowObjectDecoding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowObjectDecodingBind, handle, enable)
    }

    fun isObjectDecodingAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isObjectDecodingAllowedBind, handle)
    }

    fun setServerRelayEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setServerRelayEnabledBind, handle, enabled)
    }

    fun isServerRelayEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isServerRelayEnabledBind, handle)
    }

    fun sendBytes(bytes: ByteArray, id: Int = 0, mode: Long = 2L, channel: Int = 0): Long {
        return ObjectCalls.ptrcallWithByteArrayIntLongIntArgsRetLong(sendBytesBind, handle, bytes, id, mode, channel)
    }

    fun getMaxSyncPacketSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxSyncPacketSizeBind, handle)
    }

    fun setMaxSyncPacketSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxSyncPacketSizeBind, handle, size)
    }

    fun getMaxDeltaPacketSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxDeltaPacketSizeBind, handle)
    }

    fun setMaxDeltaPacketSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxDeltaPacketSizeBind, handle, size)
    }

    object Signals {
        const val peerAuthenticating: String = "peer_authenticating"
        const val peerAuthenticationFailed: String = "peer_authentication_failed"
        const val peerPacket: String = "peer_packet"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SceneMultiplayer? =
            if (handle.address() == 0L) null else SceneMultiplayer(handle)

        @JvmStatic
        fun fromApi(api: MultiplayerAPI?): SceneMultiplayer? =
            api?.takeIf { it.isClass("SceneMultiplayer") }?.let { SceneMultiplayer(it.handle) }

        private const val SET_ROOT_PATH_HASH = 1348162250L
        private val setRootPathBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_root_path", SET_ROOT_PATH_HASH)
        }

        private const val GET_ROOT_PATH_HASH = 4075236667L
        private val getRootPathBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_root_path", GET_ROOT_PATH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "clear", CLEAR_HASH)
        }

        private const val DISCONNECT_PEER_HASH = 1286410249L
        private val disconnectPeerBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "disconnect_peer", DISCONNECT_PEER_HASH)
        }

        private const val GET_AUTHENTICATING_PEERS_HASH = 969006518L
        private val getAuthenticatingPeersBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_authenticating_peers", GET_AUTHENTICATING_PEERS_HASH)
        }

        private const val SEND_AUTH_HASH = 506032537L
        private val sendAuthBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "send_auth", SEND_AUTH_HASH)
        }

        private const val COMPLETE_AUTH_HASH = 844576869L
        private val completeAuthBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "complete_auth", COMPLETE_AUTH_HASH)
        }

        private const val SET_AUTH_CALLBACK_HASH = 1611583062L
        private val setAuthCallbackBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_auth_callback", SET_AUTH_CALLBACK_HASH)
        }

        private const val GET_AUTH_CALLBACK_HASH = 1307783378L
        private val getAuthCallbackBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_auth_callback", GET_AUTH_CALLBACK_HASH)
        }

        private const val SET_AUTH_TIMEOUT_HASH = 373806689L
        private val setAuthTimeoutBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_auth_timeout", SET_AUTH_TIMEOUT_HASH)
        }

        private const val GET_AUTH_TIMEOUT_HASH = 1740695150L
        private val getAuthTimeoutBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_auth_timeout", GET_AUTH_TIMEOUT_HASH)
        }

        private const val SET_REFUSE_NEW_CONNECTIONS_HASH = 2586408642L
        private val setRefuseNewConnectionsBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_refuse_new_connections", SET_REFUSE_NEW_CONNECTIONS_HASH)
        }

        private const val IS_REFUSING_NEW_CONNECTIONS_HASH = 36873697L
        private val isRefusingNewConnectionsBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_refusing_new_connections", IS_REFUSING_NEW_CONNECTIONS_HASH)
        }

        private const val SET_ALLOW_OBJECT_DECODING_HASH = 2586408642L
        private val setAllowObjectDecodingBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_allow_object_decoding", SET_ALLOW_OBJECT_DECODING_HASH)
        }

        private const val IS_OBJECT_DECODING_ALLOWED_HASH = 36873697L
        private val isObjectDecodingAllowedBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_object_decoding_allowed", IS_OBJECT_DECODING_ALLOWED_HASH)
        }

        private const val SET_SERVER_RELAY_ENABLED_HASH = 2586408642L
        private val setServerRelayEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_server_relay_enabled", SET_SERVER_RELAY_ENABLED_HASH)
        }

        private const val IS_SERVER_RELAY_ENABLED_HASH = 36873697L
        private val isServerRelayEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "is_server_relay_enabled", IS_SERVER_RELAY_ENABLED_HASH)
        }

        private const val SEND_BYTES_HASH = 1307428718L
        private val sendBytesBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "send_bytes", SEND_BYTES_HASH)
        }

        private const val GET_MAX_SYNC_PACKET_SIZE_HASH = 3905245786L
        private val getMaxSyncPacketSizeBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_max_sync_packet_size", GET_MAX_SYNC_PACKET_SIZE_HASH)
        }

        private const val SET_MAX_SYNC_PACKET_SIZE_HASH = 1286410249L
        private val setMaxSyncPacketSizeBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_max_sync_packet_size", SET_MAX_SYNC_PACKET_SIZE_HASH)
        }

        private const val GET_MAX_DELTA_PACKET_SIZE_HASH = 3905245786L
        private val getMaxDeltaPacketSizeBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "get_max_delta_packet_size", GET_MAX_DELTA_PACKET_SIZE_HASH)
        }

        private const val SET_MAX_DELTA_PACKET_SIZE_HASH = 1286410249L
        private val setMaxDeltaPacketSizeBind by lazy {
            ObjectCalls.getMethodBind("SceneMultiplayer", "set_max_delta_packet_size", SET_MAX_DELTA_PACKET_SIZE_HASH)
        }
    }
}
