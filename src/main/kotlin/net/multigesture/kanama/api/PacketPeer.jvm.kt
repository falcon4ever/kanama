package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PacketPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PacketPeer waits on: ptrcallWithByteArrayArgRetLong,
//   ptrcallWithVariantAndBoolArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sends a `Variant` as a packet. If `full_objects` is `true`, encoding objects is allowed (and can
 * potentially include code). Internally, this uses the same encoding mechanism as the
 * `@GlobalScope.var_to_bytes` method.
 *
 * Generated from Godot docs: PacketPeer.put_var
 */
fun PacketPeer.putVar(varValue: Any?, fullObjects: Boolean = false): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithVariantAndBoolArgRetLong(putVarBind, handle, varValue, fullObjects)
}

/**
 * Sends a raw packet.
 *
 * Generated from Godot docs: PacketPeer.put_packet
 */
fun PacketPeer.putPacket(buffer: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(putPacketBind, handle, buffer)
}

private const val PUT_VAR_HASH = 2436251611L
private val putVarBind by lazy {
    ObjectCalls.getMethodBind("PacketPeer", "put_var", PUT_VAR_HASH)
}

private const val PUT_PACKET_HASH = 680677267L
private val putPacketBind by lazy {
    ObjectCalls.getMethodBind("PacketPeer", "put_packet", PUT_PACKET_HASH)
}
