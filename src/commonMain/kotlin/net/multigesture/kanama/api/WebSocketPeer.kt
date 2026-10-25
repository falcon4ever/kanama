package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A WebSocket connection.
 *
 * Generated from Godot docs: WebSocketPeer
 */
class WebSocketPeer(handle: GodotHandle) : PacketPeer(handle) {
    var supportedProtocols: List<String>
        @JvmName("supportedProtocolsProperty")
        get() = getSupportedProtocols()
        @JvmName("setSupportedProtocolsProperty")
        set(value) = setSupportedProtocols(value)

    var handshakeHeaders: List<String>
        @JvmName("handshakeHeadersProperty")
        get() = getHandshakeHeaders()
        @JvmName("setHandshakeHeadersProperty")
        set(value) = setHandshakeHeaders(value)

    var inboundBufferSize: Int
        @JvmName("inboundBufferSizeProperty")
        get() = getInboundBufferSize()
        @JvmName("setInboundBufferSizeProperty")
        set(value) = setInboundBufferSize(value)

    var outboundBufferSize: Int
        @JvmName("outboundBufferSizeProperty")
        get() = getOutboundBufferSize()
        @JvmName("setOutboundBufferSizeProperty")
        set(value) = setOutboundBufferSize(value)

    var maxQueuedPackets: Int
        @JvmName("maxQueuedPacketsProperty")
        get() = getMaxQueuedPackets()
        @JvmName("setMaxQueuedPacketsProperty")
        set(value) = setMaxQueuedPackets(value)

    var heartbeatInterval: Double
        @JvmName("heartbeatIntervalProperty")
        get() = getHeartbeatInterval()
        @JvmName("setHeartbeatIntervalProperty")
        set(value) = setHeartbeatInterval(value)

    /**
     * Connects to the given URL. TLS certificates will be verified against the hostname when
     * connecting using the `wss://` protocol. You can pass the optional `tls_client_options` parameter
     * to customize the trusted certification authorities, or disable the common name verification. See
     * `TLSOptions.client` and `TLSOptions.client_unsafe`. Note: This method is non-blocking, and will
     * return `GodotError.OK` before the connection is established as long as the provided parameters
     * are valid and the peer is not in an invalid state (e.g. already connected). Regularly call
     * `poll` (e.g. during `Node` process) and check the result of `get_ready_state` to know whether
     * the connection succeeds or fails. Note: To avoid mixed content warnings or errors in Web, you
     * may have to use a `url` that starts with `wss://` (secure) instead of `ws://`. When doing so,
     * make sure to use the fully qualified domain name that matches the one defined in the server's
     * TLS certificate. Do not connect directly via the IP address for `wss://` connections, as it
     * won't match with the TLS certificate.
     *
     * Generated from Godot docs: WebSocketPeer.connect_to_url
     */
    fun connectToUrl(url: String, tlsClientOptions: TLSOptions? = null): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringAndObjectArgRetLong(Binds.connectToUrlBind, segment, url, tlsClientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(tlsClientOptions)
        return ret
    }

    /**
     * Accepts a peer connection performing the HTTP handshake as a WebSocket server. The `stream` must
     * be a valid TCP stream retrieved via `TCPServer.take_connection`, or a TLS stream accepted via
     * `StreamPeerTLS.accept_stream`. Note: Not supported in Web exports due to browsers' restrictions.
     *
     * Generated from Godot docs: WebSocketPeer.accept_stream
     */
    fun acceptStream(stream: StreamPeer?): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(Binds.acceptStreamBind, segment, stream?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(stream)
        return ret
    }

    /**
     * Sends the given `message` using the desired `write_mode`. When sending a `String`, prefer using
     * `send_text`.
     *
     * Generated from Godot docs: WebSocketPeer.send
     */
    fun send(message: ByteArray, writeMode: WebSocketPeer.WriteMode = WebSocketPeer.WriteMode.BINARY): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithByteArrayAndLongArgRetLong(Binds.sendBind, segment, message, writeMode.value))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sends the given `message` using WebSocket text mode. Prefer this method over
     * `PacketPeer.put_packet` when interacting with third-party text-based API (e.g. when using `JSON`
     * formatted messages).
     *
     * Generated from Godot docs: WebSocketPeer.send_text
     */
    fun sendText(message: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.sendTextBind, segment, message))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the last received packet was sent as a text payload. See `WriteMode`.
     *
     * Generated from Godot docs: WebSocketPeer.was_string_packet
     */
    fun wasStringPacket(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.wasStringPacketBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Updates the connection state and receive incoming packets. Call this function regularly to keep
     * it in a clean state.
     *
     * Generated from Godot docs: WebSocketPeer.poll
     */
    fun poll() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.pollBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Closes this WebSocket connection. `code` is the status code for the closure (see RFC 6455
     * section 7.4 (https://datatracker.ietf.org/doc/html/rfc6455#section-7.4.1) for a list of valid
     * status codes). If `code` is negative, the connection will be closed immediately without
     * notifying the remote peer. `reason` is the human-readable reason for closing the connection. It
     * can be any UTF-8 string that's smaller than 123 bytes. Note: To achieve a clean closure, you
     * will need to keep polling until `State.CLOSED` is reached. Note: The Web export might not
     * support all status codes. Please refer to browser-specific documentation for more details.
     *
     * Generated from Godot docs: WebSocketPeer.close
     */
    fun closeConnection(code: Int = 1000, reason: String = "") {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.closeConnectionBind, segment, code, reason)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the IP address of the connected peer. Note: Not available in the Web export.
     *
     * Generated from Godot docs: WebSocketPeer.get_connected_host
     */
    fun getConnectedHost(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getConnectedHostBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the remote port of the connected peer. Note: Not available in the Web export.
     *
     * Generated from Godot docs: WebSocketPeer.get_connected_port
     */
    fun getConnectedPort(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getConnectedPortBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the selected WebSocket sub-protocol for this connection or an empty string if the
     * sub-protocol has not been selected yet.
     *
     * Generated from Godot docs: WebSocketPeer.get_selected_protocol
     */
    fun getSelectedProtocol(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getSelectedProtocolBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the URL requested by this peer. The URL is derived from the `url` passed to
     * `connect_to_url` or from the HTTP headers when acting as server (i.e. when using
     * `accept_stream`).
     *
     * Generated from Godot docs: WebSocketPeer.get_requested_url
     */
    fun getRequestedUrl(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getRequestedUrlBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Disable Nagle's algorithm on the underlying TCP socket (default). See
     * `StreamPeerTCP.set_no_delay` for more information. Note: Not available in the Web export.
     *
     * Generated from Godot docs: WebSocketPeer.set_no_delay
     */
    fun setNoDelay(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNoDelayBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the current amount of data in the outbound websocket buffer. Note: Web exports use
     * WebSocket.bufferedAmount, while other platforms use an internal buffer.
     *
     * Generated from Godot docs: WebSocketPeer.get_current_outbound_buffered_amount
     */
    fun getCurrentOutboundBufferedAmount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentOutboundBufferedAmountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the ready state of the connection.
     *
     * Generated from Godot docs: WebSocketPeer.get_ready_state
     */
    fun getReadyState(): WebSocketPeer.State {
        checkOpen()
        val ret = WebSocketPeer.State(ObjectCalls.ptrcallNoArgsRetLong(Binds.getReadyStateBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the received WebSocket close frame status code, or `-1` when the connection was not
     * cleanly closed. Only call this method when `get_ready_state` returns `State.CLOSED`.
     *
     * Generated from Godot docs: WebSocketPeer.get_close_code
     */
    fun getCloseCode(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCloseCodeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the received WebSocket close frame status reason string. Only call this method when
     * `get_ready_state` returns `State.CLOSED`.
     *
     * Generated from Godot docs: WebSocketPeer.get_close_reason
     */
    fun getCloseReason(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getCloseReasonBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The WebSocket sub-protocols allowed during the WebSocket handshake.
     *
     * Generated from Godot docs: WebSocketPeer.get_supported_protocols
     */
    fun getSupportedProtocols(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getSupportedProtocolsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The WebSocket sub-protocols allowed during the WebSocket handshake.
     *
     * Generated from Godot docs: WebSocketPeer.set_supported_protocols
     */
    fun setSupportedProtocols(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setSupportedProtocolsBind, segment, protocols)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The extra HTTP headers to be sent during the WebSocket handshake. Note: Not supported in Web
     * exports due to browsers' restrictions.
     *
     * Generated from Godot docs: WebSocketPeer.get_handshake_headers
     */
    fun getHandshakeHeaders(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getHandshakeHeadersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The extra HTTP headers to be sent during the WebSocket handshake. Note: Not supported in Web
     * exports due to browsers' restrictions.
     *
     * Generated from Godot docs: WebSocketPeer.set_handshake_headers
     */
    fun setHandshakeHeaders(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setHandshakeHeadersBind, segment, protocols)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The size of the input buffer in bytes (roughly the maximum amount of memory that will be
     * allocated for the inbound packets).
     *
     * Generated from Godot docs: WebSocketPeer.get_inbound_buffer_size
     */
    fun getInboundBufferSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getInboundBufferSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The size of the input buffer in bytes (roughly the maximum amount of memory that will be
     * allocated for the inbound packets).
     *
     * Generated from Godot docs: WebSocketPeer.set_inbound_buffer_size
     */
    fun setInboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInboundBufferSizeBind, segment, bufferSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The size of the input buffer in bytes (roughly the maximum amount of memory that will be
     * allocated for the outbound packets).
     *
     * Generated from Godot docs: WebSocketPeer.get_outbound_buffer_size
     */
    fun getOutboundBufferSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutboundBufferSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The size of the input buffer in bytes (roughly the maximum amount of memory that will be
     * allocated for the outbound packets).
     *
     * Generated from Godot docs: WebSocketPeer.set_outbound_buffer_size
     */
    fun setOutboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutboundBufferSizeBind, segment, bufferSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum amount of packets that will be allowed in the queues (both inbound and outbound).
     *
     * Generated from Godot docs: WebSocketPeer.set_max_queued_packets
     */
    fun setMaxQueuedPackets(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxQueuedPacketsBind, segment, bufferSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum amount of packets that will be allowed in the queues (both inbound and outbound).
     *
     * Generated from Godot docs: WebSocketPeer.get_max_queued_packets
     */
    fun getMaxQueuedPackets(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxQueuedPacketsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The interval (in seconds) at which the peer will automatically send WebSocket "ping" control
     * frames. When set to `0`, no "ping" control frames will be sent. Note: Has no effect in Web
     * exports due to browser restrictions.
     *
     * Generated from Godot docs: WebSocketPeer.set_heartbeat_interval
     */
    fun setHeartbeatInterval(interval: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeartbeatIntervalBind, segment, interval)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The interval (in seconds) at which the peer will automatically send WebSocket "ping" control
     * frames. When set to `0`, no "ping" control frames will be sent. Note: Has no effect in Web
     * exports due to browser restrictions.
     *
     * Generated from Godot docs: WebSocketPeer.get_heartbeat_interval
     */
    fun getHeartbeatInterval(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeartbeatIntervalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `WebSocketPeer.WriteMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`WebSocketPeer.WriteMode.<NAME>`).
     *
     * Generated from Godot docs: WebSocketPeer.WriteMode
     */
    @JvmInline
    value class WriteMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Specifies that WebSockets messages should be transferred as text payload (only valid UTF-8 is
             * allowed).
             *
             * Generated from Godot docs: WebSocketPeer.WRITE_MODE_TEXT
             */
            val TEXT: WriteMode get() = WriteMode(0L)
            /**
             * Specifies that WebSockets messages should be transferred as binary payload (any byte combination
             * is allowed).
             *
             * Generated from Godot docs: WebSocketPeer.WRITE_MODE_BINARY
             */
            val BINARY: WriteMode get() = WriteMode(1L)
        }
    }

    /**
     * Godot's `WebSocketPeer.State` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`WebSocketPeer.State.<NAME>`).
     *
     * Generated from Godot docs: WebSocketPeer.State
     */
    @JvmInline
    value class State(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Socket has been created. The connection is not yet open.
             *
             * Generated from Godot docs: WebSocketPeer.STATE_CONNECTING
             */
            val CONNECTING: State get() = State(0L)
            /**
             * The connection is open and ready to communicate.
             *
             * Generated from Godot docs: WebSocketPeer.STATE_OPEN
             */
            val OPEN: State get() = State(1L)
            /**
             * The connection is in the process of closing. This means a close request has been sent to the
             * remote peer but confirmation has not been received.
             *
             * Generated from Godot docs: WebSocketPeer.STATE_CLOSING
             */
            val CLOSING: State get() = State(2L)
            /**
             * The connection is closed or couldn't be opened.
             *
             * Generated from Godot docs: WebSocketPeer.STATE_CLOSED
             */
            val CLOSED: State get() = State(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebSocketPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebSocketPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(WebSocketPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebSocketPeer? =
            if (handle.address() == 0L) null else WebSocketPeer(GodotHandle(handle))

        /** A new WebSocketPeer, GDScript's `WebSocketPeer.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): WebSocketPeer =
            RefCounted.owned(WebSocketPeer(GodotHandle(requireConstructed(ObjectCalls.constructObject("WebSocketPeer"), "WebSocketPeer"))))
    }

    private object Binds {
        private const val CONNECT_TO_URL_HASH = 1966198364L
        @JvmField
        val connectToUrlBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "connect_to_url", CONNECT_TO_URL_HASH)

        private const val ACCEPT_STREAM_HASH = 255125695L
        @JvmField
        val acceptStreamBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "accept_stream", ACCEPT_STREAM_HASH)

        private const val SEND_HASH = 2780360567L
        @JvmField
        val sendBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "send", SEND_HASH)

        private const val SEND_TEXT_HASH = 166001499L
        @JvmField
        val sendTextBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "send_text", SEND_TEXT_HASH)

        private const val WAS_STRING_PACKET_HASH = 36873697L
        @JvmField
        val wasStringPacketBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "was_string_packet", WAS_STRING_PACKET_HASH)

        private const val POLL_HASH = 3218959716L
        @JvmField
        val pollBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "poll", POLL_HASH)

        private const val CLOSE_HASH = 1047156615L
        @JvmField
        val closeConnectionBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "close", CLOSE_HASH)

        private const val GET_CONNECTED_HOST_HASH = 201670096L
        @JvmField
        val getConnectedHostBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_connected_host", GET_CONNECTED_HOST_HASH)

        private const val GET_CONNECTED_PORT_HASH = 3905245786L
        @JvmField
        val getConnectedPortBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_connected_port", GET_CONNECTED_PORT_HASH)

        private const val GET_SELECTED_PROTOCOL_HASH = 201670096L
        @JvmField
        val getSelectedProtocolBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_selected_protocol", GET_SELECTED_PROTOCOL_HASH)

        private const val GET_REQUESTED_URL_HASH = 201670096L
        @JvmField
        val getRequestedUrlBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_requested_url", GET_REQUESTED_URL_HASH)

        private const val SET_NO_DELAY_HASH = 2586408642L
        @JvmField
        val setNoDelayBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_no_delay", SET_NO_DELAY_HASH)

        private const val GET_CURRENT_OUTBOUND_BUFFERED_AMOUNT_HASH = 3905245786L
        @JvmField
        val getCurrentOutboundBufferedAmountBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_current_outbound_buffered_amount", GET_CURRENT_OUTBOUND_BUFFERED_AMOUNT_HASH)

        private const val GET_READY_STATE_HASH = 346482985L
        @JvmField
        val getReadyStateBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_ready_state", GET_READY_STATE_HASH)

        private const val GET_CLOSE_CODE_HASH = 3905245786L
        @JvmField
        val getCloseCodeBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_close_code", GET_CLOSE_CODE_HASH)

        private const val GET_CLOSE_REASON_HASH = 201670096L
        @JvmField
        val getCloseReasonBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_close_reason", GET_CLOSE_REASON_HASH)

        private const val GET_SUPPORTED_PROTOCOLS_HASH = 1139954409L
        @JvmField
        val getSupportedProtocolsBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_supported_protocols", GET_SUPPORTED_PROTOCOLS_HASH)

        private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
        @JvmField
        val setSupportedProtocolsBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)

        private const val GET_HANDSHAKE_HEADERS_HASH = 1139954409L
        @JvmField
        val getHandshakeHeadersBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_handshake_headers", GET_HANDSHAKE_HEADERS_HASH)

        private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
        @JvmField
        val setHandshakeHeadersBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)

        private const val GET_INBOUND_BUFFER_SIZE_HASH = 3905245786L
        @JvmField
        val getInboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_inbound_buffer_size", GET_INBOUND_BUFFER_SIZE_HASH)

        private const val SET_INBOUND_BUFFER_SIZE_HASH = 1286410249L
        @JvmField
        val setInboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_inbound_buffer_size", SET_INBOUND_BUFFER_SIZE_HASH)

        private const val GET_OUTBOUND_BUFFER_SIZE_HASH = 3905245786L
        @JvmField
        val getOutboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_outbound_buffer_size", GET_OUTBOUND_BUFFER_SIZE_HASH)

        private const val SET_OUTBOUND_BUFFER_SIZE_HASH = 1286410249L
        @JvmField
        val setOutboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_outbound_buffer_size", SET_OUTBOUND_BUFFER_SIZE_HASH)

        private const val SET_MAX_QUEUED_PACKETS_HASH = 1286410249L
        @JvmField
        val setMaxQueuedPacketsBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_max_queued_packets", SET_MAX_QUEUED_PACKETS_HASH)

        private const val GET_MAX_QUEUED_PACKETS_HASH = 3905245786L
        @JvmField
        val getMaxQueuedPacketsBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_max_queued_packets", GET_MAX_QUEUED_PACKETS_HASH)

        private const val SET_HEARTBEAT_INTERVAL_HASH = 373806689L
        @JvmField
        val setHeartbeatIntervalBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "set_heartbeat_interval", SET_HEARTBEAT_INTERVAL_HASH)

        private const val GET_HEARTBEAT_INTERVAL_HASH = 1740695150L
        @JvmField
        val getHeartbeatIntervalBind =
            ObjectCalls.getMethodBind("WebSocketPeer", "get_heartbeat_interval", GET_HEARTBEAT_INTERVAL_HASH)
    }
}
