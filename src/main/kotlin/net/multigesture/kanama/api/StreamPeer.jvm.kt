package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for StreamPeer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP StreamPeer waits on: ptrcallWithBoolArgRetVariantScalar,
//   ptrcallWithByteArrayArgRetArray, ptrcallWithByteArrayArgRetLong, ptrcallWithIntArgRetArray,
//   ptrcallWithIntArgRetString, ptrcallWithVariantAndBoolArg
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
 * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
 * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
 * bytes are available, the function will block until the desired amount is received.
 *
 * Generated from Godot docs: StreamPeer.get_data
 */
fun StreamPeer.getData(bytes: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getDataBind, handle, bytes)
}

/**
 * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
 * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
 * bytes are available, the function will return how many were actually received.
 *
 * Generated from Godot docs: StreamPeer.get_partial_data
 */
fun StreamPeer.getPartialData(bytes: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getPartialDataBind, handle, bytes)
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

/**
 * Gets an ASCII string with byte-length `bytes` from the stream. If `bytes` is negative (default)
 * the length will be read from the stream using the reverse process of `put_string`.
 *
 * Generated from Godot docs: StreamPeer.get_string
 */
fun StreamPeer.getString(bytes: Int = -1): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getStringBind, handle, bytes)
}

/**
 * Gets a UTF-8 string with byte-length `bytes` from the stream (this decodes the string sent as
 * UTF-8). If `bytes` is negative (default) the length will be read from the stream using the
 * reverse process of `put_utf8_string`.
 *
 * Generated from Godot docs: StreamPeer.get_utf8_string
 */
fun StreamPeer.getUtf8String(bytes: Int = -1): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getUtf8StringBind, handle, bytes)
}

/**
 * Gets a Variant from the stream. If `allow_objects` is `true`, decoding objects is allowed.
 * Internally, this uses the same decoding mechanism as the `@GlobalScope.bytes_to_var` method.
 * Warning: Deserialized objects can contain code which gets executed. Do not use this option if
 * the serialized object comes from untrusted sources to avoid potential security threats such as
 * remote code execution.
 *
 * Generated from Godot docs: StreamPeer.get_var
 */
fun StreamPeer.getVar(allowObjects: Boolean = false): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, handle, allowObjects)
}

private const val PUT_DATA_HASH = 680677267L
private val putDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_data", PUT_DATA_HASH)
}

private const val PUT_PARTIAL_DATA_HASH = 2934048347L
private val putPartialDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_partial_data", PUT_PARTIAL_DATA_HASH)
}

private const val GET_DATA_HASH = 1171824711L
private val getDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "get_data", GET_DATA_HASH)
}

private const val GET_PARTIAL_DATA_HASH = 1171824711L
private val getPartialDataBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "get_partial_data", GET_PARTIAL_DATA_HASH)
}

private const val PUT_VAR_HASH = 738511890L
private val putVarBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "put_var", PUT_VAR_HASH)
}

private const val GET_STRING_HASH = 2309358862L
private val getStringBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "get_string", GET_STRING_HASH)
}

private const val GET_UTF8_STRING_HASH = 2309358862L
private val getUtf8StringBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "get_utf8_string", GET_UTF8_STRING_HASH)
}

private const val GET_VAR_HASH = 3442865206L
private val getVarBind by lazy {
    ObjectCalls.getMethodBind("StreamPeer", "get_var", GET_VAR_HASH)
}
