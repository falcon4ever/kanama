package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node with the ability to send HTTP(S) requests.
 *
 * Generated from Godot docs: HTTPRequest
 */
class HTTPRequest(handle: GodotHandle) : Node(handle) {
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
     * Returns `GodotError.OK` if request is successfully created. (Does not imply that the server has
     * responded), `GodotError.ERR_UNCONFIGURED` if not in the tree, `GodotError.ERR_BUSY` if still
     * processing previous request, `GodotError.ERR_INVALID_PARAMETER` if given string is not a valid
     * URL format, or `GodotError.ERR_CANT_CONNECT` if not using thread and the `HTTPClient` cannot
     * connect to host. Note: When `method` is `HTTPClient.Method.GET`, the payload sent via
     * `request_data` might be ignored by the server or even cause the server to reject the request
     * (check RFC 7231 section 4.3.1 (https://datatracker.ietf.org/doc/html/rfc7231#section-4.3.1) for
     * more details). As a workaround, you can send data as a query string in the URL (see
     * `String.uri_encode` for an example). Note: It's recommended to use transport encryption (TLS)
     * and to avoid sending sensitive information (such as login credentials) in HTTP GET URL
     * parameters. Consider using HTTP POST requests or HTTP headers for such information instead.
     *
     * Generated from Godot docs: HTTPRequest.request
     */
    fun request(url: String, customHeaders: List<String>, method: HTTPClient.Method = HTTPClient.Method.GET, requestData: String = ""): GodotError {
        return GodotError(ObjectCalls.ptrcallWithStringPackedStringListLongStringArgsRetLong(Binds.requestBind, segment, url, customHeaders, method.value, requestData))
    }

    /**
     * Creates request on the underlying `HTTPClient` using a raw array of bytes for the request body.
     * If there is no configuration errors, it tries to connect using `HTTPClient.connect_to_host` and
     * passes parameters onto `HTTPClient.request`. Returns `GodotError.OK` if request is successfully
     * created. (Does not imply that the server has responded), `GodotError.ERR_UNCONFIGURED` if not in
     * the tree, `GodotError.ERR_BUSY` if still processing previous request,
     * `GodotError.ERR_INVALID_PARAMETER` if given string is not a valid URL format, or
     * `GodotError.ERR_CANT_CONNECT` if not using thread and the `HTTPClient` cannot connect to host.
     *
     * Generated from Godot docs: HTTPRequest.request_raw
     */
    fun requestRaw(url: String, customHeaders: List<String>, method: HTTPClient.Method = HTTPClient.Method.GET, requestDataRaw: ByteArray): GodotError {
        return GodotError(ObjectCalls.ptrcallWithStringPackedStringListLongByteArrayArgsRetLong(Binds.requestRawBind, segment, url, customHeaders, method.value, requestDataRaw))
    }

    /**
     * Cancels the current request.
     *
     * Generated from Godot docs: HTTPRequest.cancel_request
     */
    fun cancelRequest() {
        ObjectCalls.ptrcallNoArgs(Binds.cancelRequestBind, segment)
    }

    /**
     * Sets the `TLSOptions` to be used when connecting to an HTTPS server. See `TLSOptions.client`.
     *
     * Generated from Godot docs: HTTPRequest.set_tls_options
     */
    fun setTlsOptions(clientOptions: TLSOptions?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTlsOptionsBind, segment, listOf(clientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Returns the current status of the underlying `HTTPClient`.
     *
     * Generated from Godot docs: HTTPRequest.get_http_client_status
     */
    fun getHttpClientStatus(): HTTPClient.Status {
        return HTTPClient.Status(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHttpClientStatusBind, segment))
    }

    /**
     * If `true`, multithreading is used to improve performance.
     *
     * Generated from Godot docs: HTTPRequest.set_use_threads
     */
    fun setUseThreads(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseThreadsBind, segment, enable)
    }

    /**
     * If `true`, multithreading is used to improve performance.
     *
     * Generated from Godot docs: HTTPRequest.is_using_threads
     */
    fun isUsingThreads(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingThreadsBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setAcceptGzipBind, segment, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAcceptingGzipBind, segment)
    }

    /**
     * Maximum allowed size for response bodies. If the response body is compressed, this will be used
     * as the maximum allowed size for the decompressed body.
     *
     * Generated from Godot docs: HTTPRequest.set_body_size_limit
     */
    fun setBodySizeLimit(bytes: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setBodySizeLimitBind, segment, bytes)
    }

    /**
     * Maximum allowed size for response bodies. If the response body is compressed, this will be used
     * as the maximum allowed size for the decompressed body.
     *
     * Generated from Godot docs: HTTPRequest.get_body_size_limit
     */
    fun getBodySizeLimit(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBodySizeLimitBind, segment)
    }

    /**
     * Maximum number of allowed redirects.
     *
     * Generated from Godot docs: HTTPRequest.set_max_redirects
     */
    fun setMaxRedirects(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxRedirectsBind, segment, amount)
    }

    /**
     * Maximum number of allowed redirects.
     *
     * Generated from Godot docs: HTTPRequest.get_max_redirects
     */
    fun getMaxRedirects(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxRedirectsBind, segment)
    }

    /**
     * The file to download into. Will output any received file into it.
     *
     * Generated from Godot docs: HTTPRequest.set_download_file
     */
    fun setDownloadFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setDownloadFileBind, segment, path)
    }

    /**
     * The file to download into. Will output any received file into it.
     *
     * Generated from Godot docs: HTTPRequest.get_download_file
     */
    fun getDownloadFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getDownloadFileBind, segment)
    }

    /**
     * Returns the number of bytes this HTTPRequest downloaded.
     *
     * Generated from Godot docs: HTTPRequest.get_downloaded_bytes
     */
    fun getDownloadedBytes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDownloadedBytesBind, segment)
    }

    /**
     * Returns the response body length. Note: Some Web servers may not send a body length. In this
     * case, the value returned will be `-1`. If using chunked transfer encoding, the body length will
     * also be `-1`.
     *
     * Generated from Godot docs: HTTPRequest.get_body_size
     */
    fun getBodySize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBodySizeBind, segment)
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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTimeoutBind, segment, timeout)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTimeoutBind, segment)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `HTTPClient.read_chunk_size`. Set this to a lower value (e.g. 4096 for 4 KiB) when downloading
     * small files to decrease memory usage at the cost of download speeds.
     *
     * Generated from Godot docs: HTTPRequest.set_download_chunk_size
     */
    fun setDownloadChunkSize(chunkSize: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setDownloadChunkSizeBind, segment, chunkSize)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `HTTPClient.read_chunk_size`. Set this to a lower value (e.g. 4096 for 4 KiB) when downloading
     * small files to decrease memory usage at the cost of download speeds.
     *
     * Generated from Godot docs: HTTPRequest.get_download_chunk_size
     */
    fun getDownloadChunkSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDownloadChunkSizeBind, segment)
    }

    /**
     * Sets the proxy server for HTTP requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPRequest.set_http_proxy
     */
    fun setHttpProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(Binds.setHttpProxyBind, segment, host, port)
    }

    /**
     * Sets the proxy server for HTTPS requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPRequest.set_https_proxy
     */
    fun setHttpsProxy(host: String, port: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(Binds.setHttpsProxyBind, segment, host, port)
    }

    /** Signal `request_completed(result: int, response_code: int, headers: PackedStringArray, body: PackedByteArray)`; see [TypedSignal]. On iOS a PackedByteArray/PackedStringArray argument is not delivered yet: a connection reports a script error. */
    val requestCompleted: Signal4<Long, Long, List<String>, ByteArray>
        @JvmName("requestCompletedTypedSignal")
        get() = Signal4(this, "request_completed", SignalArgType.LONG, SignalArgType.LONG, SignalArgType.valueOf<List<String>>("PackedStringArray", List::class), SignalArgType.valueOf<ByteArray>("PackedByteArray", ByteArray::class))

    object Signals {
        const val requestCompleted: String = "request_completed"
    }

    /**
     * Godot's `HTTPRequest.Result` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`HTTPRequest.Result.<NAME>`).
     *
     * Generated from Godot docs: HTTPRequest.Result
     */
    @JvmInline
    value class Result(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Request successful.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_SUCCESS
             */
            val SUCCESS: Result get() = Result(0L)
            /**
             * Request failed due to a mismatch between the expected and actual chunked body size during
             * transfer. Possible causes include network errors, server misconfiguration, or issues with
             * chunked encoding.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_CHUNKED_BODY_SIZE_MISMATCH
             */
            val CHUNKED_BODY_SIZE_MISMATCH: Result get() = Result(1L)
            /**
             * Request failed while connecting.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_CANT_CONNECT
             */
            val CANT_CONNECT: Result get() = Result(2L)
            /**
             * Request failed while resolving.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_CANT_RESOLVE
             */
            val CANT_RESOLVE: Result get() = Result(3L)
            /**
             * Request failed due to connection (read/write) error.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_CONNECTION_ERROR
             */
            val CONNECTION_ERROR: Result get() = Result(4L)
            /**
             * Request failed on TLS handshake.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_TLS_HANDSHAKE_ERROR
             */
            val TLS_HANDSHAKE_ERROR: Result get() = Result(5L)
            /**
             * Request does not have a response (yet).
             *
             * Generated from Godot docs: HTTPRequest.RESULT_NO_RESPONSE
             */
            val NO_RESPONSE: Result get() = Result(6L)
            /**
             * Request exceeded its maximum size limit, see `body_size_limit`.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_BODY_SIZE_LIMIT_EXCEEDED
             */
            val BODY_SIZE_LIMIT_EXCEEDED: Result get() = Result(7L)
            /**
             * Request failed due to an error while decompressing the response body. Possible causes include
             * unsupported or incorrect compression format, corrupted data, or incomplete transfer.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_BODY_DECOMPRESS_FAILED
             */
            val BODY_DECOMPRESS_FAILED: Result get() = Result(8L)
            /**
             * Request failed (currently unused).
             *
             * Generated from Godot docs: HTTPRequest.RESULT_REQUEST_FAILED
             */
            val REQUEST_FAILED: Result get() = Result(9L)
            /**
             * HTTPRequest couldn't open the download file.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_DOWNLOAD_FILE_CANT_OPEN
             */
            val DOWNLOAD_FILE_CANT_OPEN: Result get() = Result(10L)
            /**
             * HTTPRequest couldn't write to the download file.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_DOWNLOAD_FILE_WRITE_ERROR
             */
            val DOWNLOAD_FILE_WRITE_ERROR: Result get() = Result(11L)
            /**
             * Request reached its maximum redirect limit, see `max_redirects`.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_REDIRECT_LIMIT_REACHED
             */
            val REDIRECT_LIMIT_REACHED: Result get() = Result(12L)
            /**
             * Request failed due to a timeout. If you expect requests to take a long time, try increasing the
             * value of `timeout` or setting it to `0.0` to remove the timeout completely.
             *
             * Generated from Godot docs: HTTPRequest.RESULT_TIMEOUT
             */
            val TIMEOUT: Result get() = Result(13L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HTTPRequest? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HTTPRequest? =
            if (handle.address() == 0L) null else HTTPRequest(GodotHandle(handle))
    }

    private object Binds {
        private const val REQUEST_HASH = 3215244323L
        @JvmField
        val requestBind =
            ObjectCalls.getMethodBind("HTTPRequest", "request", REQUEST_HASH)

        private const val REQUEST_RAW_HASH = 2714829993L
        @JvmField
        val requestRawBind =
            ObjectCalls.getMethodBind("HTTPRequest", "request_raw", REQUEST_RAW_HASH)

        private const val CANCEL_REQUEST_HASH = 3218959716L
        @JvmField
        val cancelRequestBind =
            ObjectCalls.getMethodBind("HTTPRequest", "cancel_request", CANCEL_REQUEST_HASH)

        private const val SET_TLS_OPTIONS_HASH = 2210231844L
        @JvmField
        val setTlsOptionsBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_tls_options", SET_TLS_OPTIONS_HASH)

        private const val GET_HTTP_CLIENT_STATUS_HASH = 1426656811L
        @JvmField
        val getHttpClientStatusBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_http_client_status", GET_HTTP_CLIENT_STATUS_HASH)

        private const val SET_USE_THREADS_HASH = 2586408642L
        @JvmField
        val setUseThreadsBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_use_threads", SET_USE_THREADS_HASH)

        private const val IS_USING_THREADS_HASH = 36873697L
        @JvmField
        val isUsingThreadsBind =
            ObjectCalls.getMethodBind("HTTPRequest", "is_using_threads", IS_USING_THREADS_HASH)

        private const val SET_ACCEPT_GZIP_HASH = 2586408642L
        @JvmField
        val setAcceptGzipBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_accept_gzip", SET_ACCEPT_GZIP_HASH)

        private const val IS_ACCEPTING_GZIP_HASH = 36873697L
        @JvmField
        val isAcceptingGzipBind =
            ObjectCalls.getMethodBind("HTTPRequest", "is_accepting_gzip", IS_ACCEPTING_GZIP_HASH)

        private const val SET_BODY_SIZE_LIMIT_HASH = 1286410249L
        @JvmField
        val setBodySizeLimitBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_body_size_limit", SET_BODY_SIZE_LIMIT_HASH)

        private const val GET_BODY_SIZE_LIMIT_HASH = 3905245786L
        @JvmField
        val getBodySizeLimitBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_body_size_limit", GET_BODY_SIZE_LIMIT_HASH)

        private const val SET_MAX_REDIRECTS_HASH = 1286410249L
        @JvmField
        val setMaxRedirectsBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_max_redirects", SET_MAX_REDIRECTS_HASH)

        private const val GET_MAX_REDIRECTS_HASH = 3905245786L
        @JvmField
        val getMaxRedirectsBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_max_redirects", GET_MAX_REDIRECTS_HASH)

        private const val SET_DOWNLOAD_FILE_HASH = 83702148L
        @JvmField
        val setDownloadFileBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_download_file", SET_DOWNLOAD_FILE_HASH)

        private const val GET_DOWNLOAD_FILE_HASH = 201670096L
        @JvmField
        val getDownloadFileBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_download_file", GET_DOWNLOAD_FILE_HASH)

        private const val GET_DOWNLOADED_BYTES_HASH = 3905245786L
        @JvmField
        val getDownloadedBytesBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_downloaded_bytes", GET_DOWNLOADED_BYTES_HASH)

        private const val GET_BODY_SIZE_HASH = 3905245786L
        @JvmField
        val getBodySizeBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_body_size", GET_BODY_SIZE_HASH)

        private const val SET_TIMEOUT_HASH = 373806689L
        @JvmField
        val setTimeoutBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_timeout", SET_TIMEOUT_HASH)

        private const val GET_TIMEOUT_HASH = 191475506L
        @JvmField
        val getTimeoutBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_timeout", GET_TIMEOUT_HASH)

        private const val SET_DOWNLOAD_CHUNK_SIZE_HASH = 1286410249L
        @JvmField
        val setDownloadChunkSizeBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_download_chunk_size", SET_DOWNLOAD_CHUNK_SIZE_HASH)

        private const val GET_DOWNLOAD_CHUNK_SIZE_HASH = 3905245786L
        @JvmField
        val getDownloadChunkSizeBind =
            ObjectCalls.getMethodBind("HTTPRequest", "get_download_chunk_size", GET_DOWNLOAD_CHUNK_SIZE_HASH)

        private const val SET_HTTP_PROXY_HASH = 2956805083L
        @JvmField
        val setHttpProxyBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_http_proxy", SET_HTTP_PROXY_HASH)

        private const val SET_HTTPS_PROXY_HASH = 2956805083L
        @JvmField
        val setHttpsProxyBind =
            ObjectCalls.getMethodBind("HTTPRequest", "set_https_proxy", SET_HTTPS_PROXY_HASH)
    }
}
