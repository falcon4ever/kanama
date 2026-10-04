package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Low-level hyper-text transfer protocol client.
 *
 * Generated from Godot docs: HTTPClient
 */
class HTTPClient(handle: GodotHandle) : RefCounted(handle) {
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

    /**
     * Connects to a host. This needs to be done before any requests are sent. If no `port` is
     * specified (or `-1` is used), it is automatically set to 80 for HTTP and 443 for HTTPS. You can
     * pass the optional `tls_options` parameter to customize the trusted certification authorities, or
     * the common name verification when using HTTPS. See `TLSOptions.client` and
     * `TLSOptions.client_unsafe`.
     *
     * Generated from Godot docs: HTTPClient.connect_to_host
     */
    fun connectToHost(host: String, port: Int = -1, tlsOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringIntObjectArgsRetLong(connectToHostBind, segment, host, port, tlsOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The connection to use for this client.
     *
     * Generated from Godot docs: HTTPClient.set_connection
     */
    fun setConnection(connection: StreamPeer?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setConnectionBind, segment, listOf(connection?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The connection to use for this client.
     *
     * Generated from Godot docs: HTTPClient.get_connection
     */
    fun getConnection(): StreamPeer? {
        checkOpen()
        return StreamPeer.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getConnectionBind, segment))
    }

    /**
     * Sends a raw HTTP request to the connected host with the given `method`. The URL parameter is
     * usually just the part after the host, so for `https://example.com/index.php`, it is
     * `/index.php`. When sending requests to an HTTP proxy server, it should be an absolute URL. For
     * `HTTPClient.Method.OPTIONS` requests, `*` is also allowed. For `HTTPClient.Method.CONNECT`
     * requests, it should be the authority component (`host:port`). `headers` are HTTP request
     * headers. Sends the body data raw, as a byte array and does not encode it in any way.
     *
     * Generated from Godot docs: HTTPClient.request_raw
     */
    fun requestRaw(method: HTTPClient.Method, url: String, headers: List<String>, body: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongStringPackedStringListByteArrayArgsRetLong(requestRawBind, segment, method.value, url, headers, body))
    }

    /**
     * Sends an HTTP request to the connected host with the given `method`. The URL parameter is
     * usually just the part after the host, so for `https://example.com/index.php`, it is
     * `/index.php`. When sending requests to an HTTP proxy server, it should be an absolute URL. For
     * `HTTPClient.Method.OPTIONS` requests, `*` is also allowed. For `HTTPClient.Method.CONNECT`
     * requests, it should be the authority component (`host:port`). `headers` are HTTP request
     * headers. To create a POST request with query strings to push to the server, do:
     *
     * Generated from Godot docs: HTTPClient.request
     */
    fun request(method: HTTPClient.Method, url: String, headers: List<String>, body: String = ""): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithLongStringPackedStringListStringArgsRetLong(requestBind, segment, method.value, url, headers, body))
    }

    fun closeConnection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, segment)
    }

    /**
     * If `true`, this `HTTPClient` has a response available.
     *
     * Generated from Godot docs: HTTPClient.has_response
     */
    fun hasResponse(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasResponseBind, segment)
    }

    /**
     * If `true`, this `HTTPClient` has a response that is chunked.
     *
     * Generated from Godot docs: HTTPClient.is_response_chunked
     */
    fun isResponseChunked(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isResponseChunkedBind, segment)
    }

    /**
     * Returns the response's HTTP status code.
     *
     * Generated from Godot docs: HTTPClient.get_response_code
     */
    fun getResponseCode(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getResponseCodeBind, segment)
    }

    /**
     * Returns the response headers.
     *
     * Generated from Godot docs: HTTPClient.get_response_headers
     */
    fun getResponseHeaders(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getResponseHeadersBind, segment)
    }

    /**
     * Returns all response headers as a `Dictionary`. Each entry is composed by the header name, and a
     * `String` containing the values separated by `"; "`. The casing is kept the same as the headers
     * were received.
     *
     * Generated from Godot docs: HTTPClient.get_response_headers_as_dictionary
     */
    fun getResponseHeadersAsDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getResponseHeadersAsDictionaryBind, segment)
    }

    /**
     * Returns the response's body length. Note: Some Web servers may not send a body length. In this
     * case, the value returned will be `-1`. If using chunked transfer encoding, the body length will
     * also be `-1`. Note: This function always returns `-1` on the Web platform due to browsers
     * limitations.
     *
     * Generated from Godot docs: HTTPClient.get_response_body_length
     */
    fun getResponseBodyLength(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getResponseBodyLengthBind, segment)
    }

    /**
     * Reads one chunk from the response.
     *
     * Generated from Godot docs: HTTPClient.read_response_body_chunk
     */
    fun readResponseBodyChunk(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(readResponseBodyChunkBind, segment)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `read_response_body_chunk`.
     *
     * Generated from Godot docs: HTTPClient.set_read_chunk_size
     */
    fun setReadChunkSize(bytes: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setReadChunkSizeBind, segment, bytes)
    }

    /**
     * The size of the buffer used and maximum bytes to read per iteration. See
     * `read_response_body_chunk`.
     *
     * Generated from Godot docs: HTTPClient.get_read_chunk_size
     */
    fun getReadChunkSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getReadChunkSizeBind, segment)
    }

    /**
     * If `true`, execution will block until all data is read from the response.
     *
     * Generated from Godot docs: HTTPClient.set_blocking_mode
     */
    fun setBlockingMode(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setBlockingModeBind, segment, enabled)
    }

    /**
     * If `true`, execution will block until all data is read from the response.
     *
     * Generated from Godot docs: HTTPClient.is_blocking_mode_enabled
     */
    fun isBlockingModeEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isBlockingModeEnabledBind, segment)
    }

    /**
     * Returns a `Status` constant. Need to call `poll` in order to get status updates.
     *
     * Generated from Godot docs: HTTPClient.get_status
     */
    fun getStatus(): HTTPClient.Status {
        checkOpen()
        return HTTPClient.Status(ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, segment))
    }

    /**
     * This needs to be called in order to have any request processed. Check results with `get_status`.
     *
     * Generated from Godot docs: HTTPClient.poll
     */
    fun poll(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(pollBind, segment))
    }

    /**
     * Sets the proxy server for HTTP requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPClient.set_http_proxy
     */
    fun setHttpProxy(host: String, port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpProxyBind, segment, host, port)
    }

    /**
     * Sets the proxy server for HTTPS requests. The proxy server is unset if `host` is empty or `port`
     * is -1.
     *
     * Generated from Godot docs: HTTPClient.set_https_proxy
     */
    fun setHttpsProxy(host: String, port: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndIntArg(setHttpsProxyBind, segment, host, port)
    }

    /**
     * Generates a GET/POST application/x-www-form-urlencoded style query string from a provided
     * dictionary, e.g.:
     *
     * Generated from Godot docs: HTTPClient.query_string_from_dict
     */
    fun queryStringFromDict(fields: Map<String, Any?>): String {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetString(queryStringFromDictBind, segment, fields)
    }

    /**
     * Godot's `HTTPClient.Method` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`HTTPClient.Method.<NAME>`).
     *
     * Generated from Godot docs: HTTPClient.Method
     */
    @JvmInline
    value class Method(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * HTTP GET method. The GET method requests a representation of the specified resource. Requests
             * using GET should only retrieve data.
             *
             * Generated from Godot docs: HTTPClient.METHOD_GET
             */
            val GET: Method get() = Method(0L)
            /**
             * HTTP HEAD method. The HEAD method asks for a response identical to that of a GET request, but
             * without the response body. This is useful to request metadata like HTTP headers or to check if a
             * resource exists.
             *
             * Generated from Godot docs: HTTPClient.METHOD_HEAD
             */
            val HEAD: Method get() = Method(1L)
            /**
             * HTTP POST method. The POST method is used to submit an entity to the specified resource, often
             * causing a change in state or side effects on the server. This is often used for forms and
             * submitting data or uploading files.
             *
             * Generated from Godot docs: HTTPClient.METHOD_POST
             */
            val POST: Method get() = Method(2L)
            /**
             * HTTP PUT method. The PUT method asks to replace all current representations of the target
             * resource with the request payload. (You can think of POST as "create or update" and PUT as
             * "update", although many services tend to not make a clear distinction or change their meaning).
             *
             * Generated from Godot docs: HTTPClient.METHOD_PUT
             */
            val PUT: Method get() = Method(3L)
            /**
             * HTTP DELETE method. The DELETE method requests to delete the specified resource.
             *
             * Generated from Godot docs: HTTPClient.METHOD_DELETE
             */
            val DELETE: Method get() = Method(4L)
            /**
             * HTTP OPTIONS method. The OPTIONS method asks for a description of the communication options for
             * the target resource. Rarely used.
             *
             * Generated from Godot docs: HTTPClient.METHOD_OPTIONS
             */
            val OPTIONS: Method get() = Method(5L)
            /**
             * HTTP TRACE method. The TRACE method performs a message loop-back test along the path to the
             * target resource. Returns the entire HTTP request received in the response body. Rarely used.
             *
             * Generated from Godot docs: HTTPClient.METHOD_TRACE
             */
            val TRACE: Method get() = Method(6L)
            /**
             * HTTP CONNECT method. The CONNECT method establishes a tunnel to the server identified by the
             * target resource. Rarely used.
             *
             * Generated from Godot docs: HTTPClient.METHOD_CONNECT
             */
            val CONNECT: Method get() = Method(7L)
            /**
             * HTTP PATCH method. The PATCH method is used to apply partial modifications to a resource.
             *
             * Generated from Godot docs: HTTPClient.METHOD_PATCH
             */
            val PATCH: Method get() = Method(8L)
            /**
             * Represents the size of the `Method` enum.
             *
             * Generated from Godot docs: HTTPClient.METHOD_MAX
             */
            val MAX: Method get() = Method(9L)
        }
    }

    /**
     * Godot's `HTTPClient.Status` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`HTTPClient.Status.<NAME>`).
     *
     * Generated from Godot docs: HTTPClient.Status
     */
    @JvmInline
    value class Status(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Status: Disconnected from the server.
             *
             * Generated from Godot docs: HTTPClient.STATUS_DISCONNECTED
             */
            val DISCONNECTED: Status get() = Status(0L)
            /**
             * Status: Currently resolving the hostname for the given URL into an IP.
             *
             * Generated from Godot docs: HTTPClient.STATUS_RESOLVING
             */
            val RESOLVING: Status get() = Status(1L)
            /**
             * Status: DNS failure: Can't resolve the hostname for the given URL.
             *
             * Generated from Godot docs: HTTPClient.STATUS_CANT_RESOLVE
             */
            val CANT_RESOLVE: Status get() = Status(2L)
            /**
             * Status: Currently connecting to server.
             *
             * Generated from Godot docs: HTTPClient.STATUS_CONNECTING
             */
            val CONNECTING: Status get() = Status(3L)
            /**
             * Status: Can't connect to the server.
             *
             * Generated from Godot docs: HTTPClient.STATUS_CANT_CONNECT
             */
            val CANT_CONNECT: Status get() = Status(4L)
            /**
             * Status: Connection established.
             *
             * Generated from Godot docs: HTTPClient.STATUS_CONNECTED
             */
            val CONNECTED: Status get() = Status(5L)
            /**
             * Status: Currently sending request.
             *
             * Generated from Godot docs: HTTPClient.STATUS_REQUESTING
             */
            val REQUESTING: Status get() = Status(6L)
            /**
             * Status: HTTP body received.
             *
             * Generated from Godot docs: HTTPClient.STATUS_BODY
             */
            val BODY: Status get() = Status(7L)
            /**
             * Status: Error in HTTP connection.
             *
             * Generated from Godot docs: HTTPClient.STATUS_CONNECTION_ERROR
             */
            val CONNECTION_ERROR: Status get() = Status(8L)
            /**
             * Status: Error in TLS handshake.
             *
             * Generated from Godot docs: HTTPClient.STATUS_TLS_HANDSHAKE_ERROR
             */
            val TLS_HANDSHAKE_ERROR: Status get() = Status(9L)
        }
    }

    /**
     * Godot's `HTTPClient.ResponseCode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`HTTPClient.ResponseCode.<NAME>`).
     *
     * Generated from Godot docs: HTTPClient.ResponseCode
     */
    @JvmInline
    value class ResponseCode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * HTTP status code `100 Continue`. Interim response that indicates everything so far is OK and
             * that the client should continue with the request (or ignore this status if already finished).
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_CONTINUE
             */
            val CONTINUE: ResponseCode get() = ResponseCode(100L)
            /**
             * HTTP status code `101 Switching Protocol`. Sent in response to an `Upgrade` request header by
             * the client. Indicates the protocol the server is switching to.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_SWITCHING_PROTOCOLS
             */
            val SWITCHING_PROTOCOLS: ResponseCode get() = ResponseCode(101L)
            /**
             * HTTP status code `102 Processing` (WebDAV). Indicates that the server has received and is
             * processing the request, but no response is available yet.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PROCESSING
             */
            val PROCESSING: ResponseCode get() = ResponseCode(102L)
            /**
             * HTTP status code `200 OK`. The request has succeeded. Default response for successful requests.
             * Meaning varies depending on the request: - `Method.GET`: The resource has been fetched and is
             * transmitted in the message body. - `Method.HEAD`: The entity headers are in the message body. -
             * `Method.POST`: The resource describing the result of the action is transmitted in the message
             * body. - `Method.TRACE`: The message body contains the request message as received by the server.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_OK
             */
            val OK: ResponseCode get() = ResponseCode(200L)
            /**
             * HTTP status code `201 Created`. The request has succeeded and a new resource has been created as
             * a result of it. This is typically the response sent after a PUT request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_CREATED
             */
            val CREATED: ResponseCode get() = ResponseCode(201L)
            /**
             * HTTP status code `202 Accepted`. The request has been received but not yet acted upon. It is
             * non-committal, meaning that there is no way in HTTP to later send an asynchronous response
             * indicating the outcome of processing the request. It is intended for cases where another process
             * or server handles the request, or for batch processing.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_ACCEPTED
             */
            val ACCEPTED: ResponseCode get() = ResponseCode(202L)
            /**
             * HTTP status code `203 Non-Authoritative Information`. This response code means returned
             * meta-information set is not exact set as available from the origin server, but collected from a
             * local or a third party copy. Except this condition, 200 OK response should be preferred instead
             * of this response.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NON_AUTHORITATIVE_INFORMATION
             */
            val NON_AUTHORITATIVE_INFORMATION: ResponseCode get() = ResponseCode(203L)
            /**
             * HTTP status code `204 No Content`. There is no content to send for this request, but the headers
             * may be useful. The user-agent may update its cached headers for this resource with the new ones.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NO_CONTENT
             */
            val NO_CONTENT: ResponseCode get() = ResponseCode(204L)
            /**
             * HTTP status code `205 Reset Content`. The server has fulfilled the request and desires that the
             * client resets the "document view" that caused the request to be sent to its original state as
             * received from the origin server.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_RESET_CONTENT
             */
            val RESET_CONTENT: ResponseCode get() = ResponseCode(205L)
            /**
             * HTTP status code `206 Partial Content`. This response code is used because of a range header
             * sent by the client to separate download into multiple streams.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PARTIAL_CONTENT
             */
            val PARTIAL_CONTENT: ResponseCode get() = ResponseCode(206L)
            /**
             * HTTP status code `207 Multi-Status` (WebDAV). A Multi-Status response conveys information about
             * multiple resources in situations where multiple status codes might be appropriate.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_MULTI_STATUS
             */
            val MULTI_STATUS: ResponseCode get() = ResponseCode(207L)
            /**
             * HTTP status code `208 Already Reported` (WebDAV). Used inside a DAV: propstat response element
             * to avoid enumerating the internal members of multiple bindings to the same collection
             * repeatedly.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_ALREADY_REPORTED
             */
            val ALREADY_REPORTED: ResponseCode get() = ResponseCode(208L)
            /**
             * HTTP status code `226 IM Used` (WebDAV). The server has fulfilled a GET request for the
             * resource, and the response is a representation of the result of one or more
             * instance-manipulations applied to the current instance.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_IM_USED
             */
            val IM_USED: ResponseCode get() = ResponseCode(226L)
            /**
             * HTTP status code `300 Multiple Choice`. The request has more than one possible responses and
             * there is no standardized way to choose one of the responses. User-agent or user should choose
             * one of them.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_MULTIPLE_CHOICES
             */
            val MULTIPLE_CHOICES: ResponseCode get() = ResponseCode(300L)
            /**
             * HTTP status code `301 Moved Permanently`. Redirection. This response code means the URI of
             * requested resource has been changed. The new URI is usually included in the response.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_MOVED_PERMANENTLY
             */
            val MOVED_PERMANENTLY: ResponseCode get() = ResponseCode(301L)
            /**
             * HTTP status code `302 Found`. Temporary redirection. This response code means the URI of
             * requested resource has been changed temporarily. New changes in the URI might be made in the
             * future. Therefore, this same URI should be used by the client in future requests.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_FOUND
             */
            val FOUND: ResponseCode get() = ResponseCode(302L)
            /**
             * HTTP status code `303 See Other`. The server is redirecting the user agent to a different
             * resource, as indicated by a URI in the Location header field, which is intended to provide an
             * indirect response to the original request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_SEE_OTHER
             */
            val SEE_OTHER: ResponseCode get() = ResponseCode(303L)
            /**
             * HTTP status code `304 Not Modified`. A conditional GET or HEAD request has been received and
             * would have resulted in a 200 OK response if it were not for the fact that the condition
             * evaluated to `false`.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NOT_MODIFIED
             */
            val NOT_MODIFIED: ResponseCode get() = ResponseCode(304L)
            /**
             * HTTP status code `305 Use Proxy`.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_USE_PROXY
             */
            val USE_PROXY: ResponseCode get() = ResponseCode(305L)
            /**
             * HTTP status code `306 Switch Proxy`.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_SWITCH_PROXY
             */
            val SWITCH_PROXY: ResponseCode get() = ResponseCode(306L)
            /**
             * HTTP status code `307 Temporary Redirect`. The target resource resides temporarily under a
             * different URI and the user agent MUST NOT change the request method if it performs an automatic
             * redirection to that URI.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_TEMPORARY_REDIRECT
             */
            val TEMPORARY_REDIRECT: ResponseCode get() = ResponseCode(307L)
            /**
             * HTTP status code `308 Permanent Redirect`. The target resource has been assigned a new permanent
             * URI and any future references to this resource ought to use one of the enclosed URIs.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PERMANENT_REDIRECT
             */
            val PERMANENT_REDIRECT: ResponseCode get() = ResponseCode(308L)
            /**
             * HTTP status code `400 Bad Request`. The request was invalid. The server cannot or will not
             * process the request due to something that is perceived to be a client error (e.g., malformed
             * request syntax, invalid request message framing, invalid request contents, or deceptive request
             * routing).
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_BAD_REQUEST
             */
            val BAD_REQUEST: ResponseCode get() = ResponseCode(400L)
            /**
             * HTTP status code `401 Unauthorized`. Credentials required. The request has not been applied
             * because it lacks valid authentication credentials for the target resource.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_UNAUTHORIZED
             */
            val UNAUTHORIZED: ResponseCode get() = ResponseCode(401L)
            /**
             * HTTP status code `402 Payment Required`. This response code is reserved for future use. Initial
             * aim for creating this code was using it for digital payment systems, however this is not
             * currently used.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PAYMENT_REQUIRED
             */
            val PAYMENT_REQUIRED: ResponseCode get() = ResponseCode(402L)
            /**
             * HTTP status code `403 Forbidden`. The client does not have access rights to the content, i.e.
             * they are unauthorized, so server is rejecting to give proper response. Unlike `401`, the
             * client's identity is known to the server.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_FORBIDDEN
             */
            val FORBIDDEN: ResponseCode get() = ResponseCode(403L)
            /**
             * HTTP status code `404 Not Found`. The server can not find requested resource. Either the URL is
             * not recognized or the endpoint is valid but the resource itself does not exist. May also be sent
             * instead of 403 to hide existence of a resource if the client is not authorized.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NOT_FOUND
             */
            val NOT_FOUND: ResponseCode get() = ResponseCode(404L)
            /**
             * HTTP status code `405 Method Not Allowed`. The request's HTTP method is known by the server but
             * has been disabled and cannot be used. For example, an API may forbid DELETE-ing a resource. The
             * two mandatory methods, GET and HEAD, must never be disabled and should not return this error
             * code.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_METHOD_NOT_ALLOWED
             */
            val METHOD_NOT_ALLOWED: ResponseCode get() = ResponseCode(405L)
            /**
             * HTTP status code `406 Not Acceptable`. The target resource does not have a current
             * representation that would be acceptable to the user agent, according to the proactive
             * negotiation header fields received in the request. Used when negotiation content.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NOT_ACCEPTABLE
             */
            val NOT_ACCEPTABLE: ResponseCode get() = ResponseCode(406L)
            /**
             * HTTP status code `407 Proxy Authentication Required`. Similar to 401 Unauthorized, but it
             * indicates that the client needs to authenticate itself in order to use a proxy.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PROXY_AUTHENTICATION_REQUIRED
             */
            val PROXY_AUTHENTICATION_REQUIRED: ResponseCode get() = ResponseCode(407L)
            /**
             * HTTP status code `408 Request Timeout`. The server did not receive a complete request message
             * within the time that it was prepared to wait.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_REQUEST_TIMEOUT
             */
            val REQUEST_TIMEOUT: ResponseCode get() = ResponseCode(408L)
            /**
             * HTTP status code `409 Conflict`. The request could not be completed due to a conflict with the
             * current state of the target resource. This code is used in situations where the user might be
             * able to resolve the conflict and resubmit the request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_CONFLICT
             */
            val CONFLICT: ResponseCode get() = ResponseCode(409L)
            /**
             * HTTP status code `410 Gone`. The target resource is no longer available at the origin server and
             * this condition is likely permanent.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_GONE
             */
            val GONE: ResponseCode get() = ResponseCode(410L)
            /**
             * HTTP status code `411 Length Required`. The server refuses to accept the request without a
             * defined Content-Length header.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_LENGTH_REQUIRED
             */
            val LENGTH_REQUIRED: ResponseCode get() = ResponseCode(411L)
            /**
             * HTTP status code `412 Precondition Failed`. One or more conditions given in the request header
             * fields evaluated to `false` when tested on the server.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PRECONDITION_FAILED
             */
            val PRECONDITION_FAILED: ResponseCode get() = ResponseCode(412L)
            /**
             * HTTP status code `413 Entity Too Large`. The server is refusing to process a request because the
             * request payload is larger than the server is willing or able to process.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_REQUEST_ENTITY_TOO_LARGE
             */
            val REQUEST_ENTITY_TOO_LARGE: ResponseCode get() = ResponseCode(413L)
            /**
             * HTTP status code `414 Request-URI Too Long`. The server is refusing to service the request
             * because the request-target is longer than the server is willing to interpret.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_REQUEST_URI_TOO_LONG
             */
            val REQUEST_URI_TOO_LONG: ResponseCode get() = ResponseCode(414L)
            /**
             * HTTP status code `415 Unsupported Media Type`. The origin server is refusing to service the
             * request because the payload is in a format not supported by this method on the target resource.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_UNSUPPORTED_MEDIA_TYPE
             */
            val UNSUPPORTED_MEDIA_TYPE: ResponseCode get() = ResponseCode(415L)
            /**
             * HTTP status code `416 Requested Range Not Satisfiable`. None of the ranges in the request's
             * Range header field overlap the current extent of the selected resource or the set of ranges
             * requested has been rejected due to invalid ranges or an excessive request of small or
             * overlapping ranges.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_REQUESTED_RANGE_NOT_SATISFIABLE
             */
            val REQUESTED_RANGE_NOT_SATISFIABLE: ResponseCode get() = ResponseCode(416L)
            /**
             * HTTP status code `417 Expectation Failed`. The expectation given in the request's Expect header
             * field could not be met by at least one of the inbound servers.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_EXPECTATION_FAILED
             */
            val EXPECTATION_FAILED: ResponseCode get() = ResponseCode(417L)
            /**
             * HTTP status code `418 I'm A Teapot`. Any attempt to brew coffee with a teapot should result in
             * the error code "418 I'm a teapot". The resulting entity body MAY be short and stout.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_IM_A_TEAPOT
             */
            val IM_A_TEAPOT: ResponseCode get() = ResponseCode(418L)
            /**
             * HTTP status code `421 Misdirected Request`. The request was directed at a server that is not
             * able to produce a response. This can be sent by a server that is not configured to produce
             * responses for the combination of scheme and authority that are included in the request URI.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_MISDIRECTED_REQUEST
             */
            val MISDIRECTED_REQUEST: ResponseCode get() = ResponseCode(421L)
            /**
             * HTTP status code `422 Unprocessable Entity` (WebDAV). The server understands the content type of
             * the request entity (hence a 415 Unsupported Media Type status code is inappropriate), and the
             * syntax of the request entity is correct (thus a 400 Bad Request status code is inappropriate)
             * but was unable to process the contained instructions.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_UNPROCESSABLE_ENTITY
             */
            val UNPROCESSABLE_ENTITY: ResponseCode get() = ResponseCode(422L)
            /**
             * HTTP status code `423 Locked` (WebDAV). The source or destination resource of a method is
             * locked.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_LOCKED
             */
            val LOCKED: ResponseCode get() = ResponseCode(423L)
            /**
             * HTTP status code `424 Failed Dependency` (WebDAV). The method could not be performed on the
             * resource because the requested action depended on another action and that action failed.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_FAILED_DEPENDENCY
             */
            val FAILED_DEPENDENCY: ResponseCode get() = ResponseCode(424L)
            /**
             * HTTP status code `426 Upgrade Required`. The server refuses to perform the request using the
             * current protocol but might be willing to do so after the client upgrades to a different
             * protocol.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_UPGRADE_REQUIRED
             */
            val UPGRADE_REQUIRED: ResponseCode get() = ResponseCode(426L)
            /**
             * HTTP status code `428 Precondition Required`. The origin server requires the request to be
             * conditional.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_PRECONDITION_REQUIRED
             */
            val PRECONDITION_REQUIRED: ResponseCode get() = ResponseCode(428L)
            /**
             * HTTP status code `429 Too Many Requests`. The user has sent too many requests in a given amount
             * of time (see "rate limiting"). Back off and increase time between requests or try again later.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_TOO_MANY_REQUESTS
             */
            val TOO_MANY_REQUESTS: ResponseCode get() = ResponseCode(429L)
            /**
             * HTTP status code `431 Request Header Fields Too Large`. The server is unwilling to process the
             * request because its header fields are too large. The request MAY be resubmitted after reducing
             * the size of the request header fields.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_REQUEST_HEADER_FIELDS_TOO_LARGE
             */
            val REQUEST_HEADER_FIELDS_TOO_LARGE: ResponseCode get() = ResponseCode(431L)
            /**
             * HTTP status code `451 Response Unavailable For Legal Reasons`. The server is denying access to
             * the resource as a consequence of a legal demand.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_UNAVAILABLE_FOR_LEGAL_REASONS
             */
            val UNAVAILABLE_FOR_LEGAL_REASONS: ResponseCode get() = ResponseCode(451L)
            /**
             * HTTP status code `500 Internal Server Error`. The server encountered an unexpected condition
             * that prevented it from fulfilling the request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_INTERNAL_SERVER_ERROR
             */
            val INTERNAL_SERVER_ERROR: ResponseCode get() = ResponseCode(500L)
            /**
             * HTTP status code `501 Not Implemented`. The server does not support the functionality required
             * to fulfill the request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NOT_IMPLEMENTED
             */
            val NOT_IMPLEMENTED: ResponseCode get() = ResponseCode(501L)
            /**
             * HTTP status code `502 Bad Gateway`. The server, while acting as a gateway or proxy, received an
             * invalid response from an inbound server it accessed while attempting to fulfill the request.
             * Usually returned by load balancers or proxies.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_BAD_GATEWAY
             */
            val BAD_GATEWAY: ResponseCode get() = ResponseCode(502L)
            /**
             * HTTP status code `503 Service Unavailable`. The server is currently unable to handle the request
             * due to a temporary overload or scheduled maintenance, which will likely be alleviated after some
             * delay. Try again later.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_SERVICE_UNAVAILABLE
             */
            val SERVICE_UNAVAILABLE: ResponseCode get() = ResponseCode(503L)
            /**
             * HTTP status code `504 Gateway Timeout`. The server, while acting as a gateway or proxy, did not
             * receive a timely response from an upstream server it needed to access in order to complete the
             * request. Usually returned by load balancers or proxies.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_GATEWAY_TIMEOUT
             */
            val GATEWAY_TIMEOUT: ResponseCode get() = ResponseCode(504L)
            /**
             * HTTP status code `505 HTTP Version Not Supported`. The server does not support, or refuses to
             * support, the major version of HTTP that was used in the request message.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_HTTP_VERSION_NOT_SUPPORTED
             */
            val HTTP_VERSION_NOT_SUPPORTED: ResponseCode get() = ResponseCode(505L)
            /**
             * HTTP status code `506 Variant Also Negotiates`. The server has an internal configuration error:
             * the chosen variant resource is configured to engage in transparent content negotiation itself,
             * and is therefore not a proper end point in the negotiation process.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_VARIANT_ALSO_NEGOTIATES
             */
            val VARIANT_ALSO_NEGOTIATES: ResponseCode get() = ResponseCode(506L)
            /**
             * HTTP status code `507 Insufficient Storage`. The method could not be performed on the resource
             * because the server is unable to store the representation needed to successfully complete the
             * request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_INSUFFICIENT_STORAGE
             */
            val INSUFFICIENT_STORAGE: ResponseCode get() = ResponseCode(507L)
            /**
             * HTTP status code `508 Loop Detected`. The server terminated an operation because it encountered
             * an infinite loop while processing a request with "Depth: infinity". This status indicates that
             * the entire operation failed.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_LOOP_DETECTED
             */
            val LOOP_DETECTED: ResponseCode get() = ResponseCode(508L)
            /**
             * HTTP status code `510 Not Extended`. The policy for accessing the resource has not been met in
             * the request. The server should send back all the information necessary for the client to issue
             * an extended request.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NOT_EXTENDED
             */
            val NOT_EXTENDED: ResponseCode get() = ResponseCode(510L)
            /**
             * HTTP status code `511 Network Authentication Required`. The client needs to authenticate to gain
             * network access.
             *
             * Generated from Godot docs: HTTPClient.RESPONSE_NETWORK_AUTH_REQUIRED
             */
            val NETWORK_AUTH_REQUIRED: ResponseCode get() = ResponseCode(511L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HTTPClient? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): HTTPClient? =
            if (handle.address() == 0L) null else RefCounted.owned(HTTPClient(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): HTTPClient? =
            if (handle.address() == 0L) null else HTTPClient(GodotHandle(handle))

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
