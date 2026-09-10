package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AudioStreamMP3 (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamMP3 waits on: ptrcallWithByteArrayArg, ptrcallWithByteArrayArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun AudioStreamMP3.Companion.loadFromBuffer(streamData: ByteArray): AudioStreamMP3? {
    return AudioStreamMP3.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
}

fun AudioStreamMP3.setData(data: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayArg(setDataBind, handle, data)
}

private const val LOAD_FROM_BUFFER_HASH = 1674970313L
private val loadFromBufferBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamMP3", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
}

private const val SET_DATA_HASH = 2971499966L
private val setDataBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamMP3", "set_data", SET_DATA_HASH)
}
