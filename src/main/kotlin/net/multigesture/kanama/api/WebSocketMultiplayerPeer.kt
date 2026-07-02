package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebSocketMultiplayerPeer
 */
class WebSocketMultiplayerPeer(handle: MemorySegment) : MultiplayerPeer(handle) {
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
        return ObjectCalls.ptrcallWithStringAndObjectArgRetLong(createClientBind, handle, url, tlsClientOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun createServer(port: Int, bindAddress: String = "*", tlsServerOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithIntStringObjectArgsRetLong(createServerBind, handle, port, bindAddress, tlsServerOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getPeer(peerId: Int): WebSocketPeer? {
        return WebSocketPeer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPeerBind, handle, peerId))
    }

    fun getPeerAddress(id: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getPeerAddressBind, handle, id)
    }

    fun getPeerPort(id: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getPeerPortBind, handle, id)
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

    fun getHandshakeTimeout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHandshakeTimeoutBind, handle)
    }

    fun setHandshakeTimeout(timeout: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHandshakeTimeoutBind, handle, timeout)
    }

    fun setMaxQueuedPackets(maxQueuedPackets: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxQueuedPacketsBind, handle, maxQueuedPackets)
    }

    fun getMaxQueuedPackets(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxQueuedPacketsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebSocketMultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else WebSocketMultiplayerPeer(handle)

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
