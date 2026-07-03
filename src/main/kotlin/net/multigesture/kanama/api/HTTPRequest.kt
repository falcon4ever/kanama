package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node with the ability to send HTTP(S) requests.
 *
 * Generated from Godot docs: HTTPRequest
 */
class HTTPRequest(handle: MemorySegment) : Node(handle) {
    var downloadFile: String
        @JvmName("downloadFileProperty")
        get() = getDownloadFile()
        @JvmName("setDownloadFileProperty")
        set(value) = setDownloadFile(value)

    var downloadChunkSize: Int
        @JvmName("downloadChunkSizeProperty")
        get() = getDownloadChunkSize()
        @JvmName("setDownloadChunkSizeProperty")
        set(value) = setDownloadChunkSize(value)

    var useThreads: Boolean
        @JvmName("useThreadsProperty")
        get() = isUsingThreads()
        @JvmName("setUseThreadsProperty")
        set(value) = setUseThreads(value)

    var acceptGzip: Boolean
        @JvmName("acceptGzipProperty")
        get() = isAcceptingGzip()
        @JvmName("setAcceptGzipProperty")
        set(value) = setAcceptGzip(value)

    var bodySizeLimit: Int
        @JvmName("bodySizeLimitProperty")
        get() = getBodySizeLimit()
        @JvmName("setBodySizeLimitProperty")
        set(value) = setBodySizeLimit(value)

    var maxRedirects: Int
        @JvmName("maxRedirectsProperty")
        get() = getMaxRedirects()
        @JvmName("setMaxRedirectsProperty")
        set(value) = setMaxRedirects(value)

    var timeout: Double
        @JvmName("timeoutProperty")
        get() = getTimeout()
        @JvmName("setTimeoutProperty")
        set(value) = setTimeout(value)

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
    fun request(url: String, customHeaders: List<String>, method: Long = 0L, requestData: String = ""): Long {
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
    fun requestRaw(url: String, customHeaders: List<String>, method: Long = 0L, requestDataRaw: ByteArray): Long {
        return ObjectCalls.ptrcallWithStringPackedStringListLongByteArrayArgsRetLong(requestRawBind, handle, url, customHeaders, method, requestDataRaw)
    }

    /**
     * Cancels the current request.
     *
     * Generated from Godot docs: HTTPRequest.cancel_request
     */
    fun cancelRequest() {
        ObjectCalls.ptrcallNoArgs(cancelRequestBind, handle)
    }

    /**
     * Sets the `TLSOptions` to be used when connecting to an HTTPS server. See `TLSOptions.client`.
     *
     * Generated from Godot docs: HTTPRequest.set_tls_options
     */
    fun setTlsOptions(clientOptions: TLSOptions?) {
        ObjectCalls.ptrcallWithObjectArgs(setTlsOptionsBind, handle, listOf(clientOptions?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the current status of the underlying `HTTPClient`.
     *
     * Generated from Godot docs: HTTPRequest.get_http_client_status
     */
    fun getHttpClientStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHttpClientStatusBind, handle)
    }

    /**
     * If `true`, multithreading is used to improve performance.
     *
     * Generated from Godot docs: HTTPRequest.set_use_threads
     */
    fun setUseThreads(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseThreadsBind, handle, enable)
    }

    /**
     * If `true`, multithreading is used to improve performance.
     *
     * Generated from Godot docs: HTTPRequest.is_using_threads
     */
    fun isUsingThreads(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingThreadsBind, handle)
    }

    /**
     * If `true`, this header will be added to each request: `Accept-Encoding: gzip, deflate` telling
     * servers that it's okay to compress response bodies. Any Response body declaring a
     * `Content-Encoding` of either `gzip` or `deflate` will then be automatically decompressed, and
     * the uncompressed bytes will be delivered via `request_completed`. If the user has specified
     * their own `Accept-Encoding` header, then no header will be added regardless of `accept_gzip`. If
     * `false` no header will be added, and no decompression will be performed on response bodies. The
     * raw bytes of the response body will be returned via `request_completed`.
     *
     * Generated from Godot docs: HTTPRequest.set_accept_gzip
     */
    fun setAcceptGzip(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAcceptGzipBind, handle, enable)
    }

    /**
     * If `true`, this header will be added to each request: `Accept-Encoding: gzip, deflate` telling
     * servers that it's okay to compress response bodies. Any Response body declaring a
     * `Content-Encoding` of either `gzip` or `deflate` will then be automatically decompressed, and
     * the uncompressed bytes will be delivered via `request_completed`. If the user has specified
     * their own `Accept-Encoding` header, then no header will be added regardless of `accept_gzip`. If
     * `false` no header will be added, and no decompression will be performed on response bodies. The
     * raw bytes of the response body will be returned via `request_completed`.
     *
     * Generated from Godot docs: HTTPRequest.is_accepting_gzip
     */
    fun isAcceptingGzip(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAcceptingGzipBind, handle)
    }

    /**
     * Maximum allowed size for response bodies. If the response body is compressed, this will be used
     * as the maximum allowed size for the decompressed body.
     *
     * Generated from Godot docs: HTTPRequest.set_body_size_limit
     */
    fun setBodySizeLimit(bytes: Int) {
        ObjectCalls.ptrcallWithIntArg(setBodySizeLimitBind, handle, bytes)
    }

    /**
     * Maximum allowed size for response bodies. If the response body is compressed, this will be used
     * as the maximum allowed size for the decompressed body.
     *
     * Generated from Godot docs: HTTPRequest.get_body_size_limit
     */
    fun getBodySizeLimit(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBodySizeLimitBind, handle)
    }

    /**
     * Maximum number of allowed redirects.
     *
     * Generated from Godot docs: HTTPRequest.set_max_redirects
     */
    fun setMaxRedirects(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxRedirectsBind, handle, amount)
    }

    /**
     * Maximum number of allowed redirects.
     *
     * Generated from Godot docs: HTTPRequest.get_max_redirects
     */
    fun getMaxRedirects(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxRedirectsBind, handle)
    }

    /**
     * The file to download into. Will output any received file into it.
     *
     * Generated from Godot docs: HTTPRequest.set_download_file
     */
    fun setDownloadFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(setDownloadFileBind, handle, path)
    }

    /**
     * The file to download into. Will output any received file into it.
     *
     * Generated from Godot docs: HTTPRequest.get_download_file
     */
    fun getDownloadFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDownloadFileBind, handle)
    }

    /**
     * Returns the number of bytes this HTTPRequest downloaded.
     *
     * Generated from Godot docs: HTTPRequest.get_downloaded_bytes
     */
    fun getDownloadedBytes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDownloadedBytesBind, handle)
    }

    /**
     * Returns the response body length. Note: Some Web servers may not send a body length. In this
     * case, the value returned will be `-1`. If using chunked transfer encoding, the body length will
     * also be `-1`.
     *
     * Generated from Godot docs: HTTPRequest.get_body_size
     */
    fun getBodySize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBodySizeBind, handle)
    }

    /**
     * The duration to wait before a request times out, in seconds (independent of
     * `Engine.time_scale`). If `timeout` is set to `0.0`, the request will never time out. For simple
     * requests, such as communication with a REST API, it is recommended to set `timeout` to a value
     * suitable for the server response time (commonly between `1.0` and `10.0`). This will help
     * prevent unwanted timeouts caused by variation in response times while still allowing the
     * application to detect when a request has timed out. For larger requests such as file downloads,
     * it is recommended to set `timeout` to `0.0`, disabling the timeout functionality. This will help
     * prevent large transfers from failing due to exceeding the timeout value.
     *
     * Generated from Godot docs: HTTPRequest.set_timeout
     */
    fun setTimeout(timeout: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeoutBind, handle, timeout)
    }

    /**
     * The duration to wait before a request times out, in seconds (independent of
     * `Engine.time_scale`). If `timeout` is set to `0.0`, the request will never time out. For simple
     * requests, such as communication with a REST API, it is recommended to set `timeout` to a value
     * suitable for the server response time (commonly between `1.0` and `10.0`). This will help
     * prevent unwanted timeouts caused by variation in response times while still allowing the
     * application to detect when a request has timed out. For larger requests such as file downloads,
     * it is recommended to set `timeout` to `0.0`, disabling the timeout functionality. This will help
     * prevent large transfers from failing due to exceeding the timeout value.
     *
     * Generated from Godot docs: HTTPRequest.get_timeout
     */
    fun getTimeout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeoutBind, handle)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `HTTPClient.read_chunk_size`. Set this to a lower value (e.g. 4096 for 4 KiB) when downloading
     * small files to decrease memory usage at the cost of download speeds.
     *
     * Generated from Godot docs: HTTPRequest.set_download_chunk_size
     */
    fun setDownloadChunkSize(chunkSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setDownloadChunkSizeBind, handle, chunkSize)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `HTTPClient.read_chunk_size`. Set this to a lower value (e.g. 4096 for 4 KiB) when downloading
     * small files to decrease memory usage at the cost of download speeds.
     *
     * Generated from Godot docs: HTTPRequest.get_download_chunk_size
     */
    fun getDownloadChunkSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDownloadChunkSizeBind, handle)
    }

    /**
     * Sets the proxy server for HTTP requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPRequest.set_http_proxy
     */
    fun setHttpProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpProxyBind, handle, host, port)
    }

    /**
     * Sets the proxy server for HTTPS requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPRequest.set_https_proxy
     */
    fun setHttpsProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpsProxyBind, handle, host, port)
    }

    object Signals {
        const val requestCompleted: String = "request_completed"
    }

    companion object {
        const val RESULT_SUCCESS: Long = 0L
        const val RESULT_CHUNKED_BODY_SIZE_MISMATCH: Long = 1L
        const val RESULT_CANT_CONNECT: Long = 2L
        const val RESULT_CANT_RESOLVE: Long = 3L
        const val RESULT_CONNECTION_ERROR: Long = 4L
        const val RESULT_TLS_HANDSHAKE_ERROR: Long = 5L
        const val RESULT_NO_RESPONSE: Long = 6L
        const val RESULT_BODY_SIZE_LIMIT_EXCEEDED: Long = 7L
        const val RESULT_BODY_DECOMPRESS_FAILED: Long = 8L
        const val RESULT_REQUEST_FAILED: Long = 9L
        const val RESULT_DOWNLOAD_FILE_CANT_OPEN: Long = 10L
        const val RESULT_DOWNLOAD_FILE_WRITE_ERROR: Long = 11L
        const val RESULT_REDIRECT_LIMIT_REACHED: Long = 12L
        const val RESULT_TIMEOUT: Long = 13L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): HTTPRequest? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HTTPRequest? =
            if (handle.address() == 0L) null else HTTPRequest(handle)

        private const val REQUEST_HASH = 3215244323L
        private val requestBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "request", REQUEST_HASH)
        }

        private const val REQUEST_RAW_HASH = 2714829993L
        private val requestRawBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "request_raw", REQUEST_RAW_HASH)
        }

        private const val CANCEL_REQUEST_HASH = 3218959716L
        private val cancelRequestBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "cancel_request", CANCEL_REQUEST_HASH)
        }

        private const val SET_TLS_OPTIONS_HASH = 2210231844L
        private val setTlsOptionsBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_tls_options", SET_TLS_OPTIONS_HASH)
        }

        private const val GET_HTTP_CLIENT_STATUS_HASH = 1426656811L
        private val getHttpClientStatusBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_http_client_status", GET_HTTP_CLIENT_STATUS_HASH)
        }

        private const val SET_USE_THREADS_HASH = 2586408642L
        private val setUseThreadsBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_use_threads", SET_USE_THREADS_HASH)
        }

        private const val IS_USING_THREADS_HASH = 36873697L
        private val isUsingThreadsBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "is_using_threads", IS_USING_THREADS_HASH)
        }

        private const val SET_ACCEPT_GZIP_HASH = 2586408642L
        private val setAcceptGzipBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_accept_gzip", SET_ACCEPT_GZIP_HASH)
        }

        private const val IS_ACCEPTING_GZIP_HASH = 36873697L
        private val isAcceptingGzipBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "is_accepting_gzip", IS_ACCEPTING_GZIP_HASH)
        }

        private const val SET_BODY_SIZE_LIMIT_HASH = 1286410249L
        private val setBodySizeLimitBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_body_size_limit", SET_BODY_SIZE_LIMIT_HASH)
        }

        private const val GET_BODY_SIZE_LIMIT_HASH = 3905245786L
        private val getBodySizeLimitBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_body_size_limit", GET_BODY_SIZE_LIMIT_HASH)
        }

        private const val SET_MAX_REDIRECTS_HASH = 1286410249L
        private val setMaxRedirectsBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_max_redirects", SET_MAX_REDIRECTS_HASH)
        }

        private const val GET_MAX_REDIRECTS_HASH = 3905245786L
        private val getMaxRedirectsBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_max_redirects", GET_MAX_REDIRECTS_HASH)
        }

        private const val SET_DOWNLOAD_FILE_HASH = 83702148L
        private val setDownloadFileBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_download_file", SET_DOWNLOAD_FILE_HASH)
        }

        private const val GET_DOWNLOAD_FILE_HASH = 201670096L
        private val getDownloadFileBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_download_file", GET_DOWNLOAD_FILE_HASH)
        }

        private const val GET_DOWNLOADED_BYTES_HASH = 3905245786L
        private val getDownloadedBytesBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_downloaded_bytes", GET_DOWNLOADED_BYTES_HASH)
        }

        private const val GET_BODY_SIZE_HASH = 3905245786L
        private val getBodySizeBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_body_size", GET_BODY_SIZE_HASH)
        }

        private const val SET_TIMEOUT_HASH = 373806689L
        private val setTimeoutBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_timeout", SET_TIMEOUT_HASH)
        }

        private const val GET_TIMEOUT_HASH = 191475506L
        private val getTimeoutBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_timeout", GET_TIMEOUT_HASH)
        }

        private const val SET_DOWNLOAD_CHUNK_SIZE_HASH = 1286410249L
        private val setDownloadChunkSizeBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_download_chunk_size", SET_DOWNLOAD_CHUNK_SIZE_HASH)
        }

        private const val GET_DOWNLOAD_CHUNK_SIZE_HASH = 3905245786L
        private val getDownloadChunkSizeBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "get_download_chunk_size", GET_DOWNLOAD_CHUNK_SIZE_HASH)
        }

        private const val SET_HTTP_PROXY_HASH = 2956805083L
        private val setHttpProxyBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_http_proxy", SET_HTTP_PROXY_HASH)
        }

        private const val SET_HTTPS_PROXY_HASH = 2956805083L
        private val setHttpsProxyBind by lazy {
            ObjectCalls.getMethodBind("HTTPRequest", "set_https_proxy", SET_HTTPS_PROXY_HASH)
        }
    }
}
