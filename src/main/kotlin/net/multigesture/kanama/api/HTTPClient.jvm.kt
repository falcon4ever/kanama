package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for HTTPClient (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP HTTPClient waits on: ptrcallNoArgsRetByteArray, ptrcallNoArgsRetDictionary,
//   ptrcallWithDictionaryArgRetString, ptrcallWithLongStringPackedStringListByteArrayArgsRetLong,
//   ptrcallWithLongStringPackedStringListStringArgsRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Sends a raw HTTP request to the connected host with the given `method`. The URL parameter is
 * usually just the part after the host, so for `https://example.com/index.php`, it is
 * `/index.php`. When sending requests to an HTTP proxy server, it should be an absolute URL. For
 * `HTTPClient.METHOD_OPTIONS` requests, `*` is also allowed. For `HTTPClient.METHOD_CONNECT`
 * requests, it should be the authority component (`host:port`). `headers` are HTTP request
 * headers. Sends the body data raw, as a byte array and does not encode it in any way.
 *
 * Generated from Godot docs: HTTPClient.request_raw
 */
fun HTTPClient.requestRaw(method: Long, url: String, headers: List<String>, body: ByteArray): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithLongStringPackedStringListByteArrayArgsRetLong(requestRawBind, handle, method, url, headers, body)
}

/**
 * Sends an HTTP request to the connected host with the given `method`. The URL parameter is
 * usually just the part after the host, so for `https://example.com/index.php`, it is
 * `/index.php`. When sending requests to an HTTP proxy server, it should be an absolute URL. For
 * `HTTPClient.METHOD_OPTIONS` requests, `*` is also allowed. For `HTTPClient.METHOD_CONNECT`
 * requests, it should be the authority component (`host:port`). `headers` are HTTP request
 * headers. To create a POST request with query strings to push to the server, do:
 *
 * Generated from Godot docs: HTTPClient.request
 */
fun HTTPClient.request(method: Long, url: String, headers: List<String>, body: String = ""): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithLongStringPackedStringListStringArgsRetLong(requestBind, handle, method, url, headers, body)
}

/**
 * Returns all response headers as a `Dictionary`. Each entry is composed by the header name, and a
 * `String` containing the values separated by `"; "`. The casing is kept the same as the headers
 * were received.
 *
 * Generated from Godot docs: HTTPClient.get_response_headers_as_dictionary
 */
fun HTTPClient.getResponseHeadersAsDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getResponseHeadersAsDictionaryBind, handle)
}

/**
 * Reads one chunk from the response.
 *
 * Generated from Godot docs: HTTPClient.read_response_body_chunk
 */
fun HTTPClient.readResponseBodyChunk(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(readResponseBodyChunkBind, handle)
}

/**
 * Generates a GET/POST application/x-www-form-urlencoded style query string from a provided
 * dictionary, e.g.:
 *
 * Generated from Godot docs: HTTPClient.query_string_from_dict
 */
fun HTTPClient.queryStringFromDict(fields: Map<String, Any?>): String {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetString(queryStringFromDictBind, handle, fields)
}

private const val REQUEST_RAW_HASH = 540161961L
private val requestRawBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "request_raw", REQUEST_RAW_HASH)
}

private const val REQUEST_HASH = 3778990155L
private val requestBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "request", REQUEST_HASH)
}

private const val GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH = 2382534195L
private val getResponseHeadersAsDictionaryBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "get_response_headers_as_dictionary", GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH)
}

private const val READ_RESPONSE_BODY_CHUNK_HASH = 2115431945L
private val readResponseBodyChunkBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "read_response_body_chunk", READ_RESPONSE_BODY_CHUNK_HASH)
}

private const val QUERY_STRING_FROM_DICT_HASH = 2538086567L
private val queryStringFromDictBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "query_string_from_dict", QUERY_STRING_FROM_DICT_HASH)
}
