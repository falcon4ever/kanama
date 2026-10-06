package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: WebRTCMultiplayerPeer
 */
class WebRTCMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    fun createServer(channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithArrayArgRetLong(Binds.createServerBind, segment, channelsConfig))
    }

    fun createClient(peerId: Int, channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndArrayArgRetLong(Binds.createClientBind, segment, peerId, channelsConfig))
    }

    fun createMesh(peerId: Int, channelsConfig: List<Any?> = emptyList()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndArrayArgRetLong(Binds.createMeshBind, segment, peerId, channelsConfig))
    }

    fun addPeer(peer: WebRTCPeerConnection?, peerId: Int, unreliableLifetime: Int = 1): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectTwoIntArgsRetLong(Binds.addPeerBind, segment, peer?.requireOpenHandle() ?: NULL_SEGMENT, peerId, unreliableLifetime))
    }

    fun removePeer(peerId: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removePeerBind, segment, peerId)
    }

    fun hasPeer(peerId: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasPeerBind, segment, peerId)
    }

    fun getPeer(peerId: Int): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDictionary(Binds.getPeerBind, segment, peerId)
    }

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
