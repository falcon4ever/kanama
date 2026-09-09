package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for WebRTCMultiplayerPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP WebRTCMultiplayerPeer waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithArrayArgRetLong, ptrcallWithIntAndArrayArgRetLong, ptrcallWithIntArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

fun WebRTCMultiplayerPeer.createServer(channelsConfig: List<Any?> = emptyList()): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithArrayArgRetLong(createServerBind, handle, channelsConfig)
}

fun WebRTCMultiplayerPeer.createClient(peerId: Int, channelsConfig: List<Any?> = emptyList()): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndArrayArgRetLong(createClientBind, handle, peerId, channelsConfig)
}

fun WebRTCMultiplayerPeer.createMesh(peerId: Int, channelsConfig: List<Any?> = emptyList()): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithIntAndArrayArgRetLong(createMeshBind, handle, peerId, channelsConfig)
}

fun WebRTCMultiplayerPeer.getPeer(peerId: Int): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getPeerBind, handle, peerId)
}

fun WebRTCMultiplayerPeer.getPeers(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getPeersBind, handle)
}

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

private const val GET_PEER_HASH = 3554694381L
private val getPeerBind by lazy {
    ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peer", GET_PEER_HASH)
}

private const val GET_PEERS_HASH = 2382534195L
private val getPeersBind by lazy {
    ObjectCalls.getMethodBind("WebRTCMultiplayerPeer", "get_peers", GET_PEERS_HASH)
}
