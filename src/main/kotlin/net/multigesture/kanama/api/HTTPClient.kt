package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Low-level hyper-text transfer protocol client.
 *
 * Generated from Godot docs: HTTPClient
 */
class HTTPClient(handle: MemorySegment) : RefCounted(handle) {
    var blockingModeEnabled: Boolean
        @JvmName("blockingModeEnabledProperty")
        get() = isBlockingModeEnabled()
        @JvmName("setBlockingModeEnabledProperty")
        set(value) = setBlockingMode(value)

    var connection: StreamPeer?
        @JvmName("connectionProperty")
        get() = getConnection()
        @JvmName("setConnectionProperty")
        set(value) = setConnection(value)

    var readChunkSize: Int
        @JvmName("readChunkSizeProperty")
        get() = getReadChunkSize()
        @JvmName("setReadChunkSizeProperty")
        set(value) = setReadChunkSize(value)

    fun connectToHost(host: String, port: Int = -1, tlsOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithStringIntObjectArgsRetLong(connectToHostBind, handle, host, port, tlsOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setConnection(connection: StreamPeer?) {
        ObjectCalls.ptrcallWithObjectArgs(setConnectionBind, handle, listOf(connection?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getConnection(): StreamPeer? {
        return StreamPeer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getConnectionBind, handle))
    }

    fun requestRaw(method: Long, url: String, headers: List<String>, body: ByteArray): Long {
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
    fun request(method: Long, url: String, headers: List<String>, body: String = ""): Long {
        return ObjectCalls.ptrcallWithLongStringPackedStringListStringArgsRetLong(requestBind, handle, method, url, headers, body)
    }

    fun closeConnection() {
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, handle)
    }

    fun hasResponse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasResponseBind, handle)
    }

    fun isResponseChunked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isResponseChunkedBind, handle)
    }

    fun getResponseCode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getResponseCodeBind, handle)
    }

    fun getResponseHeaders(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getResponseHeadersBind, handle)
    }

    fun getResponseHeadersAsDictionary(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getResponseHeadersAsDictionaryBind, handle)
    }

    fun getResponseBodyLength(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getResponseBodyLengthBind, handle)
    }

    fun readResponseBodyChunk(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(readResponseBodyChunkBind, handle)
    }

    fun setReadChunkSize(bytes: Int) {
        ObjectCalls.ptrcallWithIntArg(setReadChunkSizeBind, handle, bytes)
    }

    fun getReadChunkSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getReadChunkSizeBind, handle)
    }

    fun setBlockingMode(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBlockingModeBind, handle, enabled)
    }

    fun isBlockingModeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBlockingModeEnabledBind, handle)
    }

    fun getStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, handle)
    }

    /**
     * This needs to be called in order to have any request processed. Check results with `get_status`.
     *
     * Generated from Godot docs: HTTPClient.poll
     */
    fun poll(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun setHttpProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpProxyBind, handle, host, port)
    }

    fun setHttpsProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpsProxyBind, handle, host, port)
    }

    fun queryStringFromDict(fields: Map<String, Any?>): String {
        return ObjectCalls.ptrcallWithDictionaryArgRetString(queryStringFromDictBind, handle, fields)
    }

    companion object {
        const val METHOD_GET: Long = 0L
        const val METHOD_HEAD: Long = 1L
        const val METHOD_POST: Long = 2L
        const val METHOD_PUT: Long = 3L
        const val METHOD_DELETE: Long = 4L
        const val METHOD_OPTIONS: Long = 5L
        const val METHOD_TRACE: Long = 6L
        const val METHOD_CONNECT: Long = 7L
        const val METHOD_PATCH: Long = 8L
        const val METHOD_MAX: Long = 9L
        const val STATUS_DISCONNECTED: Long = 0L
        const val STATUS_RESOLVING: Long = 1L
        const val STATUS_CANT_RESOLVE: Long = 2L
        const val STATUS_CONNECTING: Long = 3L
        const val STATUS_CANT_CONNECT: Long = 4L
        const val STATUS_CONNECTED: Long = 5L
        const val STATUS_REQUESTING: Long = 6L
        const val STATUS_BODY: Long = 7L
        const val STATUS_CONNECTION_ERROR: Long = 8L
        const val STATUS_TLS_HANDSHAKE_ERROR: Long = 9L
        const val RESPONSE_CONTINUE: Long = 100L
        const val RESPONSE_SWITCHING_PROTOCOLS: Long = 101L
        const val RESPONSE_PROCESSING: Long = 102L
        const val RESPONSE_OK: Long = 200L
        const val RESPONSE_CREATED: Long = 201L
        const val RESPONSE_ACCEPTED: Long = 202L
        const val RESPONSE_NON_AUTHORITATIVE_INFORMATION: Long = 203L
        const val RESPONSE_NO_CONTENT: Long = 204L
        const val RESPONSE_RESET_CONTENT: Long = 205L
        const val RESPONSE_PARTIAL_CONTENT: Long = 206L
        const val RESPONSE_MULTI_STATUS: Long = 207L
        const val RESPONSE_ALREADY_REPORTED: Long = 208L
        const val RESPONSE_IM_USED: Long = 226L
        const val RESPONSE_MULTIPLE_CHOICES: Long = 300L
        const val RESPONSE_MOVED_PERMANENTLY: Long = 301L
        const val RESPONSE_FOUND: Long = 302L
        const val RESPONSE_SEE_OTHER: Long = 303L
        const val RESPONSE_NOT_MODIFIED: Long = 304L
        const val RESPONSE_USE_PROXY: Long = 305L
        const val RESPONSE_SWITCH_PROXY: Long = 306L
        const val RESPONSE_TEMPORARY_REDIRECT: Long = 307L
        const val RESPONSE_PERMANENT_REDIRECT: Long = 308L
        const val RESPONSE_BAD_REQUEST: Long = 400L
        const val RESPONSE_UNAUTHORIZED: Long = 401L
        const val RESPONSE_PAYMENT_REQUIRED: Long = 402L
        const val RESPONSE_FORBIDDEN: Long = 403L
        const val RESPONSE_NOT_FOUND: Long = 404L
        const val RESPONSE_METHOD_NOT_ALLOWED: Long = 405L
        const val RESPONSE_NOT_ACCEPTABLE: Long = 406L
        const val RESPONSE_PROXY_AUTHENTICATION_REQUIRED: Long = 407L
        const val RESPONSE_REQUEST_TIMEOUT: Long = 408L
        const val RESPONSE_CONFLICT: Long = 409L
        const val RESPONSE_GONE: Long = 410L
        const val RESPONSE_LENGTH_REQUIRED: Long = 411L
        const val RESPONSE_PRECONDITION_FAILED: Long = 412L
        const val RESPONSE_REQUEST_ENTITY_TOO_LARGE: Long = 413L
        const val RESPONSE_REQUEST_URI_TOO_LONG: Long = 414L
        const val RESPONSE_UNSUPPORTED_MEDIA_TYPE: Long = 415L
        const val RESPONSE_REQUESTED_RANGE_NOT_SATISFIABLE: Long = 416L
        const val RESPONSE_EXPECTATION_FAILED: Long = 417L
        const val RESPONSE_IM_A_TEAPOT: Long = 418L
        const val RESPONSE_MISDIRECTED_REQUEST: Long = 421L
        const val RESPONSE_UNPROCESSABLE_ENTITY: Long = 422L
        const val RESPONSE_LOCKED: Long = 423L
        const val RESPONSE_FAILED_DEPENDENCY: Long = 424L
        const val RESPONSE_UPGRADE_REQUIRED: Long = 426L
        const val RESPONSE_PRECONDITION_REQUIRED: Long = 428L
        const val RESPONSE_TOO_MANY_REQUESTS: Long = 429L
        const val RESPONSE_REQUEST_HEADER_FIELDS_TOO_LARGE: Long = 431L
        const val RESPONSE_UNAVAILABLE_FOR_LEGAL_REASONS: Long = 451L
        const val RESPONSE_INTERNAL_SERVER_ERROR: Long = 500L
        const val RESPONSE_NOT_IMPLEMENTED: Long = 501L
        const val RESPONSE_BAD_GATEWAY: Long = 502L
        const val RESPONSE_SERVICE_UNAVAILABLE: Long = 503L
        const val RESPONSE_GATEWAY_TIMEOUT: Long = 504L
        const val RESPONSE_HTTP_VERSION_NOT_SUPPORTED: Long = 505L
        const val RESPONSE_VARIANT_ALSO_NEGOTIATES: Long = 506L
        const val RESPONSE_INSUFFICIENT_STORAGE: Long = 507L
        const val RESPONSE_LOOP_DETECTED: Long = 508L
        const val RESPONSE_NOT_EXTENDED: Long = 510L
        const val RESPONSE_NETWORK_AUTH_REQUIRED: Long = 511L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): HTTPClient? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HTTPClient? =
            if (handle.address() == 0L) null else HTTPClient(handle)

        private const val CONNECT_TO_HOST_HASH = 504540374L
        private val connectToHostBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "connect_to_host", CONNECT_TO_HOST_HASH)
        }

        private const val SET_CONNECTION_HASH = 3281897016L
        private val setConnectionBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "set_connection", SET_CONNECTION_HASH)
        }

        private const val GET_CONNECTION_HASH = 2741655269L
        private val getConnectionBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_connection", GET_CONNECTION_HASH)
        }

        private const val REQUEST_RAW_HASH = 540161961L
        private val requestRawBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "request_raw", REQUEST_RAW_HASH)
        }

        private const val REQUEST_HASH = 3778990155L
        private val requestBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "request", REQUEST_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "close", CLOSE_HASH)
        }

        private const val HAS_RESPONSE_HASH = 36873697L
        private val hasResponseBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "has_response", HAS_RESPONSE_HASH)
        }

        private const val IS_RESPONSE_CHUNKED_HASH = 36873697L
        private val isResponseChunkedBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "is_response_chunked", IS_RESPONSE_CHUNKED_HASH)
        }

        private const val GET_RESPONSE_CODE_HASH = 3905245786L
        private val getResponseCodeBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_response_code", GET_RESPONSE_CODE_HASH)
        }

        private const val GET_RESPONSE_HEADERS_HASH = 2981934095L
        private val getResponseHeadersBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_response_headers", GET_RESPONSE_HEADERS_HASH)
        }

        private const val GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH = 2382534195L
        private val getResponseHeadersAsDictionaryBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_response_headers_as_dictionary", GET_RESPONSE_HEADERS_AS_DICTIONARY_HASH)
        }

        private const val GET_RESPONSE_BODY_LENGTH_HASH = 3905245786L
        private val getResponseBodyLengthBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_response_body_length", GET_RESPONSE_BODY_LENGTH_HASH)
        }

        private const val READ_RESPONSE_BODY_CHUNK_HASH = 2115431945L
        private val readResponseBodyChunkBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "read_response_body_chunk", READ_RESPONSE_BODY_CHUNK_HASH)
        }

        private const val SET_READ_CHUNK_SIZE_HASH = 1286410249L
        private val setReadChunkSizeBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "set_read_chunk_size", SET_READ_CHUNK_SIZE_HASH)
        }

        private const val GET_READ_CHUNK_SIZE_HASH = 3905245786L
        private val getReadChunkSizeBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_read_chunk_size", GET_READ_CHUNK_SIZE_HASH)
        }

        private const val SET_BLOCKING_MODE_HASH = 2586408642L
        private val setBlockingModeBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "set_blocking_mode", SET_BLOCKING_MODE_HASH)
        }

        private const val IS_BLOCKING_MODE_ENABLED_HASH = 36873697L
        private val isBlockingModeEnabledBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "is_blocking_mode_enabled", IS_BLOCKING_MODE_ENABLED_HASH)
        }

        private const val GET_STATUS_HASH = 1426656811L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "get_status", GET_STATUS_HASH)
        }

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "poll", POLL_HASH)
        }

        private const val SET_HTTP_PROXY_HASH = 2956805083L
        private val setHttpProxyBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "set_http_proxy", SET_HTTP_PROXY_HASH)
        }

        private const val SET_HTTPS_PROXY_HASH = 2956805083L
        private val setHttpsProxyBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "set_https_proxy", SET_HTTPS_PROXY_HASH)
        }

        private const val QUERY_STRING_FROM_DICT_HASH = 2538086567L
        private val queryStringFromDictBind by lazy {
            ObjectCalls.getMethodBind("HTTPClient", "query_string_from_dict", QUERY_STRING_FROM_DICT_HASH)
        }
    }
}
