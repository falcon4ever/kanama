package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for WebSocketMultiplayerPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP WebSocketMultiplayerPeer waits on: ptrcallWithIntArgRetString,
//   ptrcallWithPackedStringListArg
// Index: docs/contributing/ios-shape-gap.md

fun WebSocketMultiplayerPeer.getPeerAddress(id: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getPeerAddressBind, handle, id)
}

fun WebSocketMultiplayerPeer.setSupportedProtocols(protocols: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setSupportedProtocolsBind, handle, protocols)
}

fun WebSocketMultiplayerPeer.setHandshakeHeaders(protocols: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setHandshakeHeadersBind, handle, protocols)
}

private const val GET_PEER_ADDRESS_HASH = 844755477L
private val getPeerAddressBind by lazy {
    ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer_address", GET_PEER_ADDRESS_HASH)
}

private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
private val setSupportedProtocolsBind by lazy {
    ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)
}

private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
private val setHandshakeHeadersBind by lazy {
    ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)
}
