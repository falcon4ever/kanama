package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebRTCMultiplayerPeer
 */
class WebRTCMultiplayerPeer(handle: MemorySegment) : MultiplayerPeer(handle) {
    fun createServer(channelsConfig: List<Any?> = emptyList()): Long {
        return ObjectCalls.ptrcallWithArrayArgRetLong(createServerBind, handle, channelsConfig)
    }

    fun createClient(peerId: Int, channelsConfig: List<Any?> = emptyList()): Long {
        return ObjectCalls.ptrcallWithIntAndArrayArgRetLong(createClientBind, handle, peerId, channelsConfig)
    }

    fun createMesh(peerId: Int, channelsConfig: List<Any?> = emptyList()): Long {
        return ObjectCalls.ptrcallWithIntAndArrayArgRetLong(createMeshBind, handle, peerId, channelsConfig)
    }

    fun addPeer(peer: WebRTCPeerConnection?, peerId: Int, unreliableLifetime: Int = 1): Long {
        return ObjectCalls.ptrcallWithObjectTwoIntArgsRetLong(addPeerBind, handle, peer?.requireOpenHandle() ?: MemorySegment.NULL, peerId, unreliableLifetime)
    }

    fun removePeer(peerId: Int) {
        ObjectCalls.ptrcallWithIntArg(removePeerBind, handle, peerId)
    }

    fun hasPeer(peerId: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasPeerBind, handle, peerId)
    }

    fun getPeer(peerId: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getPeerBind, handle, peerId)
    }

    fun getPeers(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getPeersBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebRTCMultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebRTCMultiplayerPeer? =
            if (handle.address() == 0L) null else WebRTCMultiplayerPeer(handle)

        private const val CREATE_SERVER_HASH = 2865356025L
        private val createServerBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_server", CREATE_SERVER_HASH)
        }

        private const val CREATE_CLIENT_HASH = 2641732907L
        private val createClientBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)
        }

        private const val CREATE_MESH_HASH = 2641732907L
        private val createMeshBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "create_mesh", CREATE_MESH_HASH)
        }

        private const val ADD_PEER_HASH = 4078953270L
        private val addPeerBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "add_peer", ADD_PEER_HASH)
        }

        private const val REMOVE_PEER_HASH = 1286410249L
        private val removePeerBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "remove_peer", REMOVE_PEER_HASH)
        }

        private const val HAS_PEER_HASH = 3067735520L
        private val hasPeerBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "has_peer", HAS_PEER_HASH)
        }

        private const val GET_PEER_HASH = 3554694381L
        private val getPeerBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peer", GET_PEER_HASH)
        }

        private const val GET_PEERS_HASH = 2382534195L
        private val getPeersBind by lazy {
            ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peers", GET_PEERS_HASH)
        }
    }
}
