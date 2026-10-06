package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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

    fun createClient(url: String, tlsClientOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndObjectArgRetLong(Binds.createClientBind, segment, url, tlsClientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun createServer(port: Int, bindAddress: String = "*", tlsServerOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntStringObjectArgsRetLong(Binds.createServerBind, segment, port, bindAddress, tlsServerOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getPeer(peerId: Int): WebSocketPeer? {
        checkOpen()
        return WebSocketPeer.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPeerBind, segment, peerId))
    }

    fun getPeerAddress(id: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getPeerAddressBind, segment, id)
    }

    fun getPeerPort(id: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getPeerPortBind, segment, id)
    }

    fun getSupportedProtocols(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getSupportedProtocolsBind, segment)
    }

    fun setSupportedProtocols(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setSupportedProtocolsBind, segment, protocols)
    }

    fun getHandshakeHeaders(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getHandshakeHeadersBind, segment)
    }

    fun setHandshakeHeaders(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setHandshakeHeadersBind, segment, protocols)
    }

    fun getInboundBufferSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInboundBufferSizeBind, segment)
    }

    fun setInboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInboundBufferSizeBind, segment, bufferSize)
    }

    fun getOutboundBufferSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutboundBufferSizeBind, segment)
    }

    fun setOutboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutboundBufferSizeBind, segment, bufferSize)
    }

    fun getHandshakeTimeout(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHandshakeTimeoutBind, segment)
    }

    fun setHandshakeTimeout(timeout: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHandshakeTimeoutBind, segment, timeout)
    }

    fun setMaxQueuedPackets(maxQueuedPackets: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxQueuedPacketsBind, segment, maxQueuedPackets)
    }

    fun getMaxQueuedPackets(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxQueuedPacketsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebSocketMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(WebSocketMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else WebSocketMultiplayerPeer(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_CLIENT_HASH = 1966198364L
        @JvmField
        val createClientBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)

        private const val CREATE_SERVER_HASH = 2400822951L
        @JvmField
        val createServerBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "create_server", CREATE_SERVER_HASH)

        private const val GET_PEER_HASH = 1381378851L
        @JvmField
        val getPeerBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer", GET_PEER_HASH)

        private const val GET_PEER_ADDRESS_HASH = 844755477L
        @JvmField
        val getPeerAddressBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer_address", GET_PEER_ADDRESS_HASH)

        private const val GET_PEER_PORT_HASH = 923996154L
        @JvmField
        val getPeerPortBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_peer_port", GET_PEER_PORT_HASH)

        private const val GET_SUPPORTED_PROTOCOLS_HASH = 1139954409L
        @JvmField
        val getSupportedProtocolsBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_supported_protocols", GET_SUPPORTED_PROTOCOLS_HASH)

        private const val SET_SUPPORTED_PROTOCOLS_HASH = 4015028928L
        @JvmField
        val setSupportedProtocolsBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_supported_protocols", SET_SUPPORTED_PROTOCOLS_HASH)

        private const val GET_HANDSHAKE_HEADERS_HASH = 1139954409L
        @JvmField
        val getHandshakeHeadersBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_handshake_headers", GET_HANDSHAKE_HEADERS_HASH)

        private const val SET_HANDSHAKE_HEADERS_HASH = 4015028928L
        @JvmField
        val setHandshakeHeadersBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_handshake_headers", SET_HANDSHAKE_HEADERS_HASH)

        private const val GET_INBOUND_BUFFER_SIZE_HASH = 3905245786L
        @JvmField
        val getInboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_inbound_buffer_size", GET_INBOUND_BUFFER_SIZE_HASH)

        private const val SET_INBOUND_BUFFER_SIZE_HASH = 1286410249L
        @JvmField
        val setInboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_inbound_buffer_size", SET_INBOUND_BUFFER_SIZE_HASH)

        private const val GET_OUTBOUND_BUFFER_SIZE_HASH = 3905245786L
        @JvmField
        val getOutboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_outbound_buffer_size", GET_OUTBOUND_BUFFER_SIZE_HASH)

        private const val SET_OUTBOUND_BUFFER_SIZE_HASH = 1286410249L
        @JvmField
        val setOutboundBufferSizeBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_outbound_buffer_size", SET_OUTBOUND_BUFFER_SIZE_HASH)

        private const val GET_HANDSHAKE_TIMEOUT_HASH = 1740695150L
        @JvmField
        val getHandshakeTimeoutBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_handshake_timeout", GET_HANDSHAKE_TIMEOUT_HASH)

        private const val SET_HANDSHAKE_TIMEOUT_HASH = 373806689L
        @JvmField
        val setHandshakeTimeoutBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_handshake_timeout", SET_HANDSHAKE_TIMEOUT_HASH)

        private const val SET_MAX_QUEUED_PACKETS_HASH = 1286410249L
        @JvmField
        val setMaxQueuedPacketsBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "set_max_queued_packets", SET_MAX_QUEUED_PACKETS_HASH)

        private const val GET_MAX_QUEUED_PACKETS_HASH = 3905245786L
        @JvmField
        val getMaxQueuedPacketsBind =
            ObjectCalls.getMethodBind("WebSocketMultiplayerPeer", "get_max_queued_packets", GET_MAX_QUEUED_PACKETS_HASH)
    }
}
