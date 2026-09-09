package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * High-level multiplayer API interface.
 *
 * Generated from Godot docs: MultiplayerAPI
 */
open class MultiplayerAPI(handle: MemorySegment) : RefCounted(handle) {
    var multiplayerPeer: MultiplayerPeer?
        @JvmName("multiplayerPeerProperty")
        get() = getMultiplayerPeer()
        @JvmName("setMultiplayerPeerProperty")
        set(value) = setMultiplayerPeer(value)

    /**
     * Returns `true` if there is a `multiplayer_peer` set.
     *
     * Generated from Godot docs: MultiplayerAPI.has_multiplayer_peer
     */
    fun hasMultiplayerPeer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasMultiplayerPeerBind, handle)
    }

    /**
     * The peer object to handle the RPC system (effectively enabling networking when set). Depending
     * on the peer itself, the MultiplayerAPI will become a network server (check with `is_server`) and
     * will set root node's network mode to authority, or it will become a regular client peer. All
     * child nodes are set to inherit the network mode by default. Handling of networking-related
     * events (connection, disconnection, new clients) is done by connecting to MultiplayerAPI's
     * signals.
     *
     * Generated from Godot docs: MultiplayerAPI.get_multiplayer_peer
     */
    fun getMultiplayerPeer(): MultiplayerPeer? {
        checkOpen()
        return MultiplayerPeer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultiplayerPeerBind, handle))
    }

    /**
     * The peer object to handle the RPC system (effectively enabling networking when set). Depending
     * on the peer itself, the MultiplayerAPI will become a network server (check with `is_server`) and
     * will set root node's network mode to authority, or it will become a regular client peer. All
     * child nodes are set to inherit the network mode by default. Handling of networking-related
     * events (connection, disconnection, new clients) is done by connecting to MultiplayerAPI's
     * signals.
     *
     * Generated from Godot docs: MultiplayerAPI.set_multiplayer_peer
     */
    fun setMultiplayerPeer(peer: MultiplayerPeer?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setMultiplayerPeerBind, handle, listOf(peer?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the unique peer ID of this MultiplayerAPI's `multiplayer_peer`.
     *
     * Generated from Godot docs: MultiplayerAPI.get_unique_id
     */
    fun getUniqueId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getUniqueIdBind, handle)
    }

    /**
     * Returns `true` if this MultiplayerAPI's `multiplayer_peer` is valid and in server mode
     * (listening for connections).
     *
     * Generated from Godot docs: MultiplayerAPI.is_server
     */
    fun isServer(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isServerBind, handle)
    }

    /**
     * Returns the sender's peer ID for the RPC currently being executed. Note: This method returns `0`
     * when called outside of an RPC. As such, the original peer ID may be lost when code execution is
     * delayed (such as with GDScript's `await` keyword).
     *
     * Generated from Godot docs: MultiplayerAPI.get_remote_sender_id
     */
    fun getRemoteSenderId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getRemoteSenderIdBind, handle)
    }

    /**
     * Method used for polling the MultiplayerAPI. You only need to worry about this if you set
     * `SceneTree.multiplayer_poll` to `false`. By default, `SceneTree` will poll its MultiplayerAPI(s)
     * for you. Note: This method results in RPCs being called, so they will be executed in the same
     * context of this function (e.g. `_process`, `physics`, `Thread`).
     *
     * Generated from Godot docs: MultiplayerAPI.poll
     */
    fun poll(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    /**
     * Returns the peer IDs of all connected peers of this MultiplayerAPI's `multiplayer_peer`.
     *
     * Generated from Godot docs: MultiplayerAPI.get_peers
     */
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
        /**
         * Sets the default MultiplayerAPI implementation class. This method can be used by modules and
         * extensions to configure which implementation will be used by `SceneTree` when the engine starts.
         *
         * Generated from Godot docs: MultiplayerAPI.set_default_interface
         */
        fun setDefaultInterface(interfaceName: String) {
            ObjectCalls.ptrcallWithStringNameArg(setDefaultInterfaceBind, MemorySegment.NULL, interfaceName)
        }

        /**
         * Returns the default MultiplayerAPI implementation class name. This is usually
         * `"SceneMultiplayer"` when `SceneMultiplayer` is available. See `set_default_interface`.
         *
         * Generated from Godot docs: MultiplayerAPI.get_default_interface
         */
        fun getDefaultInterface(): String {
            return ObjectCalls.ptrcallNoArgsRetStringName(getDefaultInterfaceBind, MemorySegment.NULL)
        }

        /**
         * Returns a new instance of the default MultiplayerAPI.
         *
         * Generated from Godot docs: MultiplayerAPI.create_default_interface
         */
        fun createDefaultInterface(): MultiplayerAPI? {
            return MultiplayerAPI.wrap(ObjectCalls.ptrcallNoArgsRetObject(createDefaultInterfaceBind, MemorySegment.NULL))
        }

        const val RPC_MODE_DISABLED: Long = 0L
        const val RPC_MODE_ANY_PEER: Long = 1L
        const val RPC_MODE_AUTHORITY: Long = 2L

        @JvmStatic
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
