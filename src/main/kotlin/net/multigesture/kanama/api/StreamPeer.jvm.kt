package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for StreamPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP StreamPeer waits on: ptrcallWithByteArrayArgRetArray,
//   ptrcallWithByteArrayArgRetLong, ptrcallWithVariantAndBoolArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sends a chunk of data through the connection, blocking if necessary until the data is done
 * sending. This function returns an `Error` code.
 *
 * Generated from Godot docs: StreamPeer.put_data
 */
fun StreamPeer.putData(data: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetLong(putDataBind, handle, data)
}

/**
 * Sends a chunk of data through the connection. If all the data could not be sent at once, only
 * part of it will. This function returns two values, an `Error` code and an integer, describing
 * how much data was actually sent.
 *
 * Generated from Godot docs: StreamPeer.put_partial_data
 */
fun StreamPeer.putPartialData(data: ByteArray): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithByteArrayArgRetArray(putPartialDataBind, handle, data)
}

/**
 * Puts a Variant into the stream. If `full_objects` is `true` encoding objects is allowed (and can
 * potentially include code). Internally, this uses the same encoding mechanism as the
 * `@GlobalScope.var_to_bytes` method.
 *
 * Generated from Godot docs: StreamPeer.put_var
 */
fun StreamPeer.putVar(value: Any?, fullObjects: Boolean = false) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantAndBoolArg(putVarBind, handle, value, fullObjects)
}

private const val PUT_DATA_HASH = 680677267L
private val putDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_data", PUT_DATA_HASH)
}

private const val PUT_PARTIAL_DATA_HASH = 2934048347L
private val putPartialDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_partial_data", PUT_PARTIAL_DATA_HASH)
}

private const val PUT_VAR_HASH = 738511890L
private val putVarBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_var", PUT_VAR_HASH)
}
