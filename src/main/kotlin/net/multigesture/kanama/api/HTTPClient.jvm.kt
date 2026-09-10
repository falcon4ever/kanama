package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for HTTPClient (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP HTTPClient waits on: ptrcallNoArgsRetDictionary, ptrcallWithDictionaryArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

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
 * Generates a GET/POST application/x-www-form-urlencoded style query string from a provided
 * dictionary, e.g.:
 *
 * Generated from Godot docs: HTTPClient.query_string_from_dict
 */
fun HTTPClient.queryStringFromDict(fields: Map<String, Any?>): String {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetString(queryStringFromDictBind, handle, fields)
}

private const val GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH = 2382534195L
private val getResponseHeadersAsDictionaryBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "get_response_headers_as_dictionary", GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH)
}

private const val QUERY_STRING_FROM_DICT_HASH = 2538086567L
private val queryStringFromDictBind by lazy {
    ObjectCalls.getMethodBind("HTTPClient", "query_string_from_dict", QUERY_STRING_FROM_DICT_HASH)
}
