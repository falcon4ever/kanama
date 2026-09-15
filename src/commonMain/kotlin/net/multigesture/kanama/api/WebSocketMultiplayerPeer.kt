package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: WebSocketMultiplayerPeer
 */
class WebSocketMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
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

    var handshakeTimeout: Double
        @JvmName("handshakeTimeoutProperty")
        get() = getHandshakeTimeout()
        @JvmName("setHandshakeTimeoutProperty")
        set(value) = setHandshakeTimeout(value)

    var maxQueuedPackets: Int
        @JvmName("maxQueuedPacketsProperty")
        get() = getMaxQueuedPackets()
        @JvmName("setMaxQueuedPacketsProperty")
        set(value) = setMaxQueuedPackets(value)

    fun createClient(url: String, tlsClientOptions: TLSOptions?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndObjectArgRetLong(createClientBind, segment, url, tlsClientOptions?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun createServer(port: Int, bindAddress: String = "*", tlsServerOptions: TLSOptions?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntStringObjectArgsRetLong(createServerBind, segment, port, bindAddress, tlsServerOptions?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun getPeer(peerId: Int): WebSocketPeer? {
        checkOpen()
        return WebSocketPeer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPeerBind, segment, peerId))
    }

    fun getPeerAddress(id: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getPeerAddressBind, segment, id)
    }

    fun getPeerPort(id: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getPeerPortBind, segment, id)
    }

    fun getSupportedProtocols(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSupportedProtocolsBind, segment)
    }

    fun setSupportedProtocols(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(setSupportedProtocolsBind, segment, protocols)
    }

    fun getHandshakeHeaders(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getHandshakeHeadersBind, segment)
    }

    fun setHandshakeHeaders(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(setHandshakeHeadersBind, segment, protocols)
    }

    fun getInboundBufferSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getInboundBufferSizeBind, segment)
    }

    fun setInboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setInboundBufferSizeBind, segment, bufferSize)
    }

    fun getOutboundBufferSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getOutboundBufferSizeBind, segment)
    }

    fun setOutboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setOutboundBufferSizeBind, segment, bufferSize)
    }

    fun getHandshakeTimeout(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getHandshakeTimeoutBind, segment)
    }

    fun setHandshakeTimeout(timeout: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setHandshakeTimeoutBind, segment, timeout)
    }

    fun setMaxQueuedPackets(maxQueuedPackets: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMaxQueuedPacketsBind, segment, maxQueuedPackets)
    }

    fun getMaxQueuedPackets(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxQueuedPacketsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebSocketMultiplayerPeer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else WebSocketMultiplayerPeer(GodotHandle(handle))

        private const val CREATE_CLIENT_HASH = 1966198364L
        private val createClientBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)
        }

        private const val CREATE_SERVER_HASH = 2400822951L
        private val createServerBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "create_server", CREATE_SERVER_HASH)
        }

        private const val GET_PEER_HASH = 1381378851L
        private val getPeerBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer", GET_PEER_HASH)
        }

        private const val GET_PEER_ADDRESS_HASH = 844755477L
        private val getPeerAddressBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer_address", GET_PEER_ADDRESS_HASH)
        }

        private const val GET_PEER_PORT_HASH = 923996154L
        private val getPeerPortBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer_port", GET_PEER_PORT_HASH)
        }

        private const val GET_SUPPORTED_PROTOCOLS_HASH = 1139954409L
        private val getSupportedProtocolsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_supported_protocols", GET_SUPPORTED_PROTOCOLS_HASH)
        }

        private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
        private val setSupportedProtocolsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)
        }

        private const val GET_HANDSHAKE_HEADERS_HASH = 1139954409L
        private val getHandshakeHeadersBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_handshake_headers", GET_HANDSHAKE_HEADERS_HASH)
        }

        private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
        private val setHandshakeHeadersBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)
        }

        private const val GET_INBOUND_BUFFER_SIZE_HASH = 3905245786L
        private val getInboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_inbound_buffer_size", GET_INBOUND_BUFFER_SIZE_HASH)
        }

        private const val SET_INBOUND_BUFFER_SIZE_HASH = 1286410249L
        private val setInboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_inbound_buffer_size", SET_INBOUND_BUFFER_SIZE_HASH)
        }

        private const val GET_OUTBOUND_BUFFER_SIZE_HASH = 3905245786L
        private val getOutboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_outbound_buffer_size", GET_OUTBOUND_BUFFER_SIZE_HASH)
        }

        private const val SET_OUTBOUND_BUFFER_SIZE_HASH = 1286410249L
        private val setOutboundBufferSizeBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_outbound_buffer_size", SET_OUTBOUND_BUFFER_SIZE_HASH)
        }

        private const val GET_HANDSHAKE_TIMEOUT_HASH = 1740695150L
        private val getHandshakeTimeoutBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_handshake_timeout", GET_HANDSHAKE_TIMEOUT_HASH)
        }

        private const val SET_HANDSHAKE_TIMEOUT_HASH = 373806689L
        private val setHandshakeTimeoutBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_handshake_timeout", SET_HANDSHAKE_TIMEOUT_HASH)
        }

        private const val SET_MAX_QUEUED_PACKETS_HASH = 1286410249L
        private val setMaxQueuedPacketsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_max_queued_packets", SET_MAX_QUEUED_PACKETS_HASH)
        }

        private const val GET_MAX_QUEUED_PACKETS_HASH = 3905245786L
        private val getMaxQueuedPacketsBind by lazy {
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_max_queued_packets", GET_MAX_QUEUED_PACKETS_HASH)
        }
    }
}
