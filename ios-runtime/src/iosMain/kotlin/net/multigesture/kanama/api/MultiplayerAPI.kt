package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: MultiplayerAPI
 */
open class MultiplayerAPI(handle: MemorySegment) : RefCounted(handle) {
    var multiplayerPeer: MultiplayerPeer?
        @JvmName("multiplayerPeerProperty")
        get() = getMultiplayerPeer()
        @JvmName("setMultiplayerPeerProperty")
        set(value) = setMultiplayerPeer(value)

    fun hasMultiplayerPeer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasMultiplayerPeerBind, handle)
    }

    fun getMultiplayerPeer(): MultiplayerPeer? {
        checkOpen()
        return MultiplayerPeer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultiplayerPeerBind, handle))
    }

    fun setMultiplayerPeer(peer: MultiplayerPeer?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMultiplayerPeerBind, handle, listOf(peer?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getUniqueId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getUniqueIdBind, handle)
    }

    fun isServer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isServerBind, handle)
    }

    fun getRemoteSenderId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getRemoteSenderIdBind, handle)
    }

    fun poll(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun getPeers(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPeersBind, handle)
    }

    object Signals {
        const val peerConnected: String = "peer_connected"
        const val peerDisconnected: String = "peer_disconnected"
        const val connectedToServer: String = "connected_to_server"
        const val connectionFailed: String = "connection_failed"
        const val serverDisconnected: String = "server_disconnected"
    }

    companion object {
        fun setDefaultInterface(interfaceName: String) {
            ObjectCalls.ptrcallWithStringNameArg(setDefaultInterfaceBind, MemorySegment.NULL, interfaceName)
        }

        fun getDefaultInterface(): String {
            return ObjectCalls.ptrcallNoArgsRetStringName(getDefaultInterfaceBind, MemorySegment.NULL)
        }

        fun createDefaultInterface(): MultiplayerAPI? {
            return MultiplayerAPI.wrap(ObjectCalls.ptrcallNoArgsRetObject(createDefaultInterfaceBind, MemorySegment.NULL))
        }

        const val RPC_MODE_DISABLED: Long = 0L
        const val RPC_MODE_ANY_PEER: Long = 1L
        const val RPC_MODE_AUTHORITY: Long = 2L

        fun fromHandle(handle: MemorySegment): MultiplayerAPI? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiplayerAPI? =
            if (handle.address() == 0L) null else MultiplayerAPI(handle)

        private const val HAS_MULTIPLAYER_PEER_HASH = 2240911060L
        private val hasMultiplayerPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "has_multiplayer_peer", HAS_MULTIPLAYER_PEER_HASH)
        }

        private const val GET_MULTIPLAYER_PEER_HASH = 3223692825L
        private val getMultiplayerPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "get_multiplayer_peer", GET_MULTIPLAYER_PEER_HASH)
        }

        private const val SET_MULTIPLAYER_PEER_HASH = 3694835298L
        private val setMultiplayerPeerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "set_multiplayer_peer", SET_MULTIPLAYER_PEER_HASH)
        }

        private const val GET_UNIQUE_ID_HASH = 2455072627L
        private val getUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "get_unique_id", GET_UNIQUE_ID_HASH)
        }

        private const val IS_SERVER_HASH = 2240911060L
        private val isServerBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "is_server", IS_SERVER_HASH)
        }

        private const val GET_REMOTE_SENDER_ID_HASH = 2455072627L
        private val getRemoteSenderIdBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "get_remote_sender_id", GET_REMOTE_SENDER_ID_HASH)
        }

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "poll", POLL_HASH)
        }

        private const val GET_PEERS_HASH = 969006518L
        private val getPeersBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "get_peers", GET_PEERS_HASH)
        }

        private const val SET_DEFAULT_INTERFACE_HASH = 3304788590L
        private val setDefaultInterfaceBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "set_default_interface", SET_DEFAULT_INTERFACE_HASH)
        }

        private const val GET_DEFAULT_INTERFACE_HASH = 2737447660L
        private val getDefaultInterfaceBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "get_default_interface", GET_DEFAULT_INTERFACE_HASH)
        }

        private const val CREATE_DEFAULT_INTERFACE_HASH = 3294156723L
        private val createDefaultInterfaceBind by lazy {
            ObjectCalls.getMethodBind("MultiplayerAPI", "create_default_interface", CREATE_DEFAULT_INTERFACE_HASH)
        }
    }
}
