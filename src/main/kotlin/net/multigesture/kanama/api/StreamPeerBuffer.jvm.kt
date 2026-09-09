package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for StreamPeerBuffer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP StreamPeerBuffer waits on: ptrcallNoArgsRetByteArray, ptrcallWithByteArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The underlying data buffer. Setting this value resets the cursor.
 *
 * Generated from Godot docs: StreamPeerBuffer.set_data_array
 */
fun StreamPeerBuffer.setDataArray(data: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayArg(setDataArrayBind, handle, data)
}

/**
 * The underlying data buffer. Setting this value resets the cursor.
 *
 * Generated from Godot docs: StreamPeerBuffer.get_data_array
 */
fun StreamPeerBuffer.getDataArray(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(getDataArrayBind, handle)
}

var StreamPeerBuffer.dataArray: ByteArray
    @JvmName("dataArrayProperty")
    get() = getDataArray()
    @JvmName("setDataArrayProperty")
    set(value) = setDataArray(value)

private const val SET_DATA_ARRAY_HASH = 2971499966L
private val setDataArrayBind by lazy {
    ObjectCalls.getMethodBind("StreamPeerBuffer", "set_data_array", SET_DATA_ARRAY_HASH)
}

private const val GET_DATA_ARRAY_HASH = 2362200018L
private val getDataArrayBind by lazy {
    ObjectCalls.getMethodBind("StreamPeerBuffer", "get_data_array", GET_DATA_ARRAY_HASH)
}
