package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: WebRTCMultiplayerPeer
 */
class WebRTCMultiplayerPeer(handle: MemorySegment) : MultiplayerPeer(handle) {
    fun addPeer(peer: WebRTCPeerConnection?, peerId: Int, unreliableLifetime: Int = 1): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectTwoIntArgsRetLong(addPeerBind, handle, peer?.requireOpenHandle() ?: MemorySegment.NULL, peerId, unreliableLifetime)
    }

    fun removePeer(peerId: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removePeerBind, handle, peerId)
    }

    fun hasPeer(peerId: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(hasPeerBind, handle, peerId)
    }

    fun getPeer(peerId: Int): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getPeerBind, handle, peerId)
    }

    fun getPeers(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getPeersBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebRTCMultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebRTCMultiplayerPeer? =
            if (handle.address() == 0L) null else WebRTCMultiplayerPeer(handle)

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
