package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for StreamPeerBuffer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP StreamPeerBuffer waits on: ptrcallWithByteArrayArg
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

private const val SET_DATA_ARRAY_HASH = 2971499966L
private val setDataArrayBind by lazy {
    ObjectCalls.getMethodBind("StreamPeerBuffer", "set_data_array", SET_DATA_ARRAY_HASH)
}
