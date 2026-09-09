package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for WebSocketPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP WebSocketPeer waits on: ptrcallWithByteArrayAndLongArgRetLong,
//   ptrcallWithPackedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun WebSocketPeer.send(message: ByteArray, writeMode: Long = 1L): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayAndLongArgRetLong(sendBind, handle, message, writeMode)
}

fun WebSocketPeer.setSupportedProtocols(protocols: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setSupportedProtocolsBind, handle, protocols)
}

fun WebSocketPeer.setHandshakeHeaders(protocols: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setHandshakeHeadersBind, handle, protocols)
}

private const val SEND_HASH = 2780360567L
private val sendBind by lazy {
    ObjectCalls.getMethodBind("WebSocketPeer", "send", SEND_HASH)
}

private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
private val setSupportedProtocolsBind by lazy {
    ObjectCalls.getMethodBind("WebSocketPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)
}

private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
private val setHandshakeHeadersBind by lazy {
    ObjectCalls.getMethodBind("WebSocketPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)
}
