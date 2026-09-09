package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ENetConnection (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ENetConnection waits on: ptrcallWithIntArgRetArray, ptrcallWithIntByteArrayIntArgs,
//   ptrcallWithStringIntByteArrayArgs
// Index: docs/reference/generated/ios-shape-gap.md

fun ENetConnection.service(timeout: Int = 0): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(serviceBind, handle, timeout)
}

fun ENetConnection.broadcast(channel: Int, packet: ByteArray, flags: Int) {
    checkOpen()
    ObjectCalls.ptrcallWithIntByteArrayIntArgs(broadcastBind, handle, channel, packet, flags)
}

fun ENetConnection.socketSend(destinationAddress: String, destinationPort: Int, packet: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithStringIntByteArrayArgs(socketSendBind, handle, destinationAddress, destinationPort, packet)
}

private const val SERVICE_HASH = 2402345344L
private val serviceBind by lazy {
    ObjectCalls.getMethodBind("ENetConnection", "service", SERVICE_HASH)
}

private const val BROADCAST_HASH = 2772371345L
private val broadcastBind by lazy {
    ObjectCalls.getMethodBind("ENetConnection", "broadcast", BROADCAST_HASH)
}

private const val SOCKET_SEND_HASH = 1100646812L
private val socketSendBind by lazy {
    ObjectCalls.getMethodBind("ENetConnection", "socket_send", SOCKET_SEND_HASH)
}
