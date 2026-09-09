package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for HTTPRequest (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP HTTPRequest waits on: ptrcallWithStringPackedStringListLongByteArrayArgsRetLong,
//   ptrcallWithStringPackedStringListLongStringArgsRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates request on the underlying `HTTPClient`. If there is no configuration errors, it tries to
 * connect using `HTTPClient.connect_to_host` and passes parameters onto `HTTPClient.request`.
 * Returns `OK` if request is successfully created. (Does not imply that the server has responded),
 * `ERR_UNCONFIGURED` if not in the tree, `ERR_BUSY` if still processing previous request,
 * `ERR_INVALID_PARAMETER` if given string is not a valid URL format, or `ERR_CANT_CONNECT` if not
 * using thread and the `HTTPClient` cannot connect to host. Note: When `method` is
 * `HTTPClient.METHOD_GET`, the payload sent via `request_data` might be ignored by the server or
 * even cause the server to reject the request (check RFC 7231 section 4.3.1
 * (https://datatracker.ietf.org/doc/html/rfc7231#section-4.3.1) for more details). As a
 * workaround, you can send data as a query string in the URL (see `String.uri_encode` for an
 * example). Note: It's recommended to use transport encryption (TLS) and to avoid sending
 * sensitive information (such as login credentials) in HTTP GET URL parameters. Consider using
 * HTTP POST requests or HTTP headers for such information instead.
 *
 * Generated from Godot docs: HTTPRequest.request
 */
fun HTTPRequest.request(url: String, customHeaders: List<String>, method: Long = 0L, requestData: String = ""): Long {
    return ObjectCalls.ptrcallWithStringPackedStringListLongStringArgsRetLong(requestBind, handle, url, customHeaders, method, requestData)
}

/**
 * Creates request on the underlying `HTTPClient` using a raw array of bytes for the request body.
 * If there is no configuration errors, it tries to connect using `HTTPClient.connect_to_host` and
 * passes parameters onto `HTTPClient.request`. Returns `OK` if request is successfully created.
 * (Does not imply that the server has responded), `ERR_UNCONFIGURED` if not in the tree,
 * `ERR_BUSY` if still processing previous request, `ERR_INVALID_PARAMETER` if given string is not
 * a valid URL format, or `ERR_CANT_CONNECT` if not using thread and the `HTTPClient` cannot
 * connect to host.
 *
 * Generated from Godot docs: HTTPRequest.request_raw
 */
fun HTTPRequest.requestRaw(url: String, customHeaders: List<String>, method: Long = 0L, requestDataRaw: ByteArray): Long {
    return ObjectCalls.ptrcallWithStringPackedStringListLongByteArrayArgsRetLong(requestRawBind, handle, url, customHeaders, method, requestDataRaw)
}

private const val REQUEST_HASH = 3215244323L
private val requestBind by lazy {
    ObjectCalls.getMethodBind("HTTPRequest", "request", REQUEST_HASH)
}

private const val REQUEST_RAW_HASH = 2714829993L
private val requestRawBind by lazy {
    ObjectCalls.getMethodBind("HTTPRequest", "request_raw", REQUEST_RAW_HASH)
}
