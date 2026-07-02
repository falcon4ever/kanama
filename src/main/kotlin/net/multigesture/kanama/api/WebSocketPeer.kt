package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebSocketPeer
 */
class WebSocketPeer(handle: MemorySegment) : PacketPeer(handle) {
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

    fun connectToUrl(url: String, tlsClientOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithStringAndObjectArgRetLong(connectToUrlBind, handle, url, tlsClientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun acceptStream(stream: StreamPeer?): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(acceptStreamBind, handle, stream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun send(message: ByteArray, writeMode: Long = 1L): Long {
        return ObjectCalls.ptrcallWithByteArrayAndLongArgRetLong(sendBind, handle, message, writeMode)
    }

    fun sendText(message: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(sendTextBind, handle, message)
    }

    fun wasStringPacket(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(wasStringPacketBind, handle)
    }

    fun poll() {
        ObjectCalls.ptrcallNoArgs(pollBind, handle)
    }

    fun closeConnection(code: Int = 1000, reason: String = "") {
        ObjectCalls.ptrcallWithIntAndStringArg(closeConnectionBind, handle, code, reason)
    }

    fun getConnectedHost(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConnectedHostBind, handle)
    }

    fun getConnectedPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectedPortBind, handle)
    }

    fun getSelectedProtocol(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelectedProtocolBind, handle)
    }

    fun getRequestedUrl(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getRequestedUrlBind, handle)
    }

    fun setNoDelay(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNoDelayBind, handle, enabled)
    }

    fun getCurrentOutboundBufferedAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentOutboundBufferedAmountBind, handle)
    }

    fun getReadyState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getReadyStateBind, handle)
    }

    fun getCloseCode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCloseCodeBind, handle)
    }

    fun getCloseReason(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCloseReasonBind, handle)
    }

    fun getSupportedProtocols(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSupportedProtocolsBind, handle)
    }

    fun setSupportedProtocols(protocols: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setSupportedProtocolsBind, handle, protocols)
    }

    fun getHandshakeHeaders(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getHandshakeHeadersBind, handle)
    }

    fun setHandshakeHeaders(protocols: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setHandshakeHeadersBind, handle, protocols)
    }

    fun getInboundBufferSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInboundBufferSizeBind, handle)
    }

    fun setInboundBufferSize(bufferSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setInboundBufferSizeBind, handle, bufferSize)
    }

    fun getOutboundBufferSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutboundBufferSizeBind, handle)
    }

    fun setOutboundBufferSize(bufferSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutboundBufferSizeBind, handle, bufferSize)
    }

    fun setMaxQueuedPackets(bufferSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxQueuedPacketsBind, handle, bufferSize)
    }

    fun getMaxQueuedPackets(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxQueuedPacketsBind, handle)
    }

    fun setHeartbeatInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeartbeatIntervalBind, handle, interval)
    }

    fun getHeartbeatInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeartbeatIntervalBind, handle)
    }

    companion object {
        const val WRITE_MODE_TEXT: Long = 0L
        const val WRITE_MODE_BINARY: Long = 1L
        const val STATE_CONNECTING: Long = 0L
        const val STATE_OPEN: Long = 1L
        const val STATE_CLOSING: Long = 2L
        const val STATE_CLOSED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebSocketPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebSocketPeer? =
            if (handle.address() == 0L) null else WebSocketPeer(handle)

        private const val CONNECT_TO_URL_HASH = 1966198364L
        private val connectToUrlBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "connect_to_url", CONNECT_TO_URL_HASH)
        }

        private const val ACCEPT_STREAM_HASH = 255125695L
        private val acceptStreamBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "accept_stream", ACCEPT_STREAM_HASH)
        }

        private const val SEND_HASH = 2780360567L
        private val sendBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "send", SEND_HASH)
        }

        private const val SEND_TEXT_HASH = 166001499L
        private val sendTextBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "send_text", SEND_TEXT_HASH)
        }

        private const val WAS_STRING_PACKET_HASH = 36873697L
        private val wasStringPacketBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "was_string_packet", WAS_STRING_PACKET_HASH)
        }

        private const val POLL_HASH = 3218959716L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "poll", POLL_HASH)
        }

        private const val CLOSE_HASH = 1047156615L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "close", CLOSE_HASH)
        }

        private const val GET_CONNECTED_HOST_HASH = 201670096L
        private val getConnectedHostBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_connected_host", GET_CONNECTED_HOST_HASH)
        }

        private const val GET_CONNECTED_PORT_HASH = 3905245786L
        private val getConnectedPortBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_connected_port", GET_CONNECTED_PORT_HASH)
        }

        private const val GET_SELECTED_PROTOCOL_HASH = 201670096L
        private val getSelectedProtocolBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_selected_protocol", GET_SELECTED_PROTOCOL_HASH)
        }

        private const val GET_REQUESTED_URL_HASH = 201670096L
        private val getRequestedUrlBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_requested_url", GET_REQUESTED_URL_HASH)
        }

        private const val SET_NO_DELAY_HASH = 2586408642L
        private val setNoDelayBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_no_delay", SET_NO_DELAY_HASH)
        }

        private const val GET_CURRENT_OUTBOUND_BUFFERED_AMOUNT_HASH = 3905245786L
        private val getCurrentOutboundBufferedAmountBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_current_outbound_buffered_amount", GET_CURRENT_OUTBOUND_BUFFERED_AMOUNT_HASH)
        }

        private const val GET_READY_STATE_HASH = 346482985L
        private val getReadyStateBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_ready_state", GET_READY_STATE_HASH)
        }

        private const val GET_CLOSE_CODE_HASH = 3905245786L
        private val getCloseCodeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_close_code", GET_CLOSE_CODE_HASH)
        }

        private const val GET_CLOSE_REASON_HASH = 201670096L
        private val getCloseReasonBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_close_reason", GET_CLOSE_REASON_HASH)
        }

        private const val GET_SUPPORTED_PROTOCOLS_HASH = 1139954409L
        private val getSupportedProtocolsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_supported_protocols", GET_SUPPORTED_PROTOCOLS_HASH)
        }

        private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
        private val setSupportedProtocolsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)
        }

        private const val GET_HANDSHAKE_HEADERS_HASH = 1139954409L
        private val getHandshakeHeadersBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_handshake_headers", GET_HANDSHAKE_HEADERS_HASH)
        }

        private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
        private val setHandshakeHeadersBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)
        }

        private const val GET_INBOUND_BUFFER_SIZE_HASH = 3905245786L
        private val getInboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_inbound_buffer_size", GET_INBOUND_BUFFER_SIZE_HASH)
        }

        private const val SET_INBOUND_BUFFER_SIZE_HASH = 1286410249L
        private val setInboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_inbound_buffer_size", SET_INBOUND_BUFFER_SIZE_HASH)
        }

        private const val GET_OUTBOUND_BUFFER_SIZE_HASH = 3905245786L
        private val getOutboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_outbound_buffer_size", GET_OUTBOUND_BUFFER_SIZE_HASH)
        }

        private const val SET_OUTBOUND_BUFFER_SIZE_HASH = 1286410249L
        private val setOutboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_outbound_buffer_size", SET_OUTBOUND_BUFFER_SIZE_HASH)
        }

        private const val SET_MAX_QUEUED_PACKETS_HASH = 1286410249L
        private val setMaxQueuedPacketsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_max_queued_packets", SET_MAX_QUEUED_PACKETS_HASH)
        }

        private const val GET_MAX_QUEUED_PACKETS_HASH = 3905245786L
        private val getMaxQueuedPacketsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_max_queued_packets", GET_MAX_QUEUED_PACKETS_HASH)
        }

        private const val SET_HEARTBEAT_INTERVAL_HASH = 373806689L
        private val setHeartbeatIntervalBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "set_heartbeat_interval", SET_HEARTBEAT_INTERVAL_HASH)
        }

        private const val GET_HEARTBEAT_INTERVAL_HASH = 1740695150L
        private val getHeartbeatIntervalBind by lazy {
            ObjectCalls.getMethodBind("WebSocketPeer", "get_heartbeat_interval", GET_HEARTBEAT_INTERVAL_HASH)
        }
    }
}
