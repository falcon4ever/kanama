package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A simple interface to create a peer-to-peer mesh network composed of `WebRTCPeerConnection` that
 * is compatible with the `MultiplayerAPI`.
 *
 * Generated from Godot docs: WebRTCMultiplayerPeer
 */
class WebRTCMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    /**
     * Initialize the multiplayer peer as a server (with unique ID of `1`). This mode enables
     * `MultiplayerPeer.is_server_relay_supported`, allowing the upper `MultiplayerAPI` layer to
     * perform peer exchange and packet relaying. You can optionally specify a `channels_config` array
     * of `MultiplayerPeer.TransferMode` which will be used to create extra channels (WebRTC only
     * supports one transfer mode per channel).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.create_server
     */
    fun createServer(channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithArrayArgRetLong(Binds.createServerBind, segment, channelsConfig))
    }

    /**
     * Initialize the multiplayer peer as a client with the given `peer_id` (must be between 2 and
     * 2147483647). In this mode, you should only call `add_peer` once and with `peer_id` of `1`. This
     * mode enables `MultiplayerPeer.is_server_relay_supported`, allowing the upper `MultiplayerAPI`
     * layer to perform peer exchange and packet relaying. You can optionally specify a
     * `channels_config` array of `MultiplayerPeer.TransferMode` which will be used to create extra
     * channels (WebRTC only supports one transfer mode per channel).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.create_client
     */
    fun createClient(peerId: Int, channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndArrayArgRetLong(Binds.createClientBind, segment, peerId, channelsConfig))
    }

    /**
     * Initialize the multiplayer peer as a mesh (i.e. all peers connect to each other) with the given
     * `peer_id` (must be between 1 and 2147483647).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.create_mesh
     */
    fun createMesh(peerId: Int, channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndArrayArgRetLong(Binds.createMeshBind, segment, peerId, channelsConfig))
    }

    /**
     * Add a new peer to the mesh with the given `peer_id`. The `WebRTCPeerConnection` must be in state
     * `WebRTCPeerConnection.ConnectionState.NEW`. Three channels will be created for reliable,
     * unreliable, and ordered transport. The value of `unreliable_lifetime` will be passed to the
     * `"maxPacketLifetime"` option when creating unreliable and ordered channels (see
     * `WebRTCPeerConnection.create_data_channel`).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.add_peer
     */
    fun addPeer(peer: WebRTCPeerConnection?, peerId: Int, unreliableLifetime: Int = 1): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectTwoIntArgsRetLong(Binds.addPeerBind, segment, peer?.requireOpenHandle() ?: NULL_SEGMENT, peerId, unreliableLifetime))
    }

    /**
     * Remove the peer with given `peer_id` from the mesh. If the peer was connected, and
     * `MultiplayerPeer.peer_connected` was emitted for it, then `MultiplayerPeer.peer_disconnected`
     * will be emitted.
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.remove_peer
     */
    fun removePeer(peerId: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removePeerBind, segment, peerId)
    }

    /**
     * Returns `true` if the given `peer_id` is in the peers map (it might not be connected though).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.has_peer
     */
    fun hasPeer(peerId: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasPeerBind, segment, peerId)
    }

    /**
     * Returns a dictionary representation of the peer with given `peer_id` with three keys.
     * `"connection"` containing the `WebRTCPeerConnection` to this peer, `"channels"` an array of
     * three `WebRTCDataChannel`, and `"connected"` a boolean representing if the peer connection is
     * currently connected (all three channels are open).
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.get_peer
     */
    fun getPeer(peerId: Int): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDictionary(Binds.getPeerBind, segment, peerId)
    }

    /**
     * Returns a dictionary which keys are the peer ids and values the peer representation as in
     * `get_peer`.
     *
     * Generated from Godot docs: WebRTCMultiplayerPeer.get_peers
     */
    fun getPeers(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getPeersBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebRTCMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(WebRTCMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebRTCMultiplayerPeer? =
            if (handle.address() == 0L) null else WebRTCMultiplayerPeer(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_SERVER_HASH = 2865356025L
        @JvmField
        val createServerBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_server", CREATE_SERVER_HASH)

        private const val CREATE_CLIENT_HASH = 2641732907L
        @JvmField
        val createClientBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)

        private const val CREATE_MESH_HASH = 2641732907L
        @JvmField
        val createMeshBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_mesh", CREATE_MESH_HASH)

        private const val ADD_PEER_HASH = 4078953270L
        @JvmField
        val addPeerBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "add_peer", ADD_PEER_HASH)

        private const val REMOVE_PEER_HASH = 1286410249L
        @JvmField
        val removePeerBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "remove_peer", REMOVE_PEER_HASH)

        private const val HAS_PEER_HASH = 3067735520L
        @JvmField
        val hasPeerBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "has_peer", HAS_PEER_HASH)

        private const val GET_PEER_HASH = 3554694381L
        @JvmField
        val getPeerBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peer", GET_PEER_HASH)

        private const val GET_PEERS_HASH = 2382534195L
        @JvmField
        val getPeersBind =
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peers", GET_PEERS_HASH)
    }
}
