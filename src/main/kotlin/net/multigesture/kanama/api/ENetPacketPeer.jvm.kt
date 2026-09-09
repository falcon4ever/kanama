package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ENetPacketPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ENetPacketPeer waits on: ptrcallWithIntByteArrayIntArgsRetLong
// Index: docs/contributing/ios-shape-gap.md

fun ENetPacketPeer.send(channel: Int, packet: ByteArray, flags: Int): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithIntByteArrayIntArgsRetLong(sendBind, handle, channel, packet, flags)
}

private const val SEND_HASH = 120522849L
private val sendBind by lazy {
    ObjectCalls.getMethodBind("ENetPacketPeer", "send", SEND_HASH)
}
