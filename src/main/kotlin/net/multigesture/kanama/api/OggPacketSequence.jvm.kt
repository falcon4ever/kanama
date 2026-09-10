package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OggPacketSequence (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OggPacketSequence waits on: ptrcallNoArgsRetArrayList, ptrcallWithArrayListArg,
//   ptrcallWithPackedInt64ListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun OggPacketSequence.setPacketData(packetData: List<List<Any?>>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayListArg(setPacketDataBind, handle, packetData)
}

fun OggPacketSequence.getPacketData(): List<List<Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetArrayList(getPacketDataBind, handle)
}

fun OggPacketSequence.setPacketGranulePositions(granulePositions: List<Long>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt64ListArg(setPacketGranulePositionsBind, handle, granulePositions)
}

var OggPacketSequence.packetData: List<List<Any?>>
    @JvmName("packetDataProperty")
    get() = getPacketData()
    @JvmName("setPacketDataProperty")
    set(value) = setPacketData(value)

private const val SET_PACKET_DATA_HASH = 381264803L
private val setPacketDataBind by lazy {
    ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_data", SET_PACKET_DATA_HASH)
}

private const val GET_PACKET_DATA_HASH = 3995934104L
private val getPacketDataBind by lazy {
    ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_data", GET_PACKET_DATA_HASH)
}

private const val SET_PACKET_GRANULE_POSITIONS_HASH = 3709968205L
private val setPacketGranulePositionsBind by lazy {
    ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_granule_positions", SET_PACKET_GRANULE_POSITIONS_HASH)
}
