package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Base class for WebSocket server and client.
 *
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

    /**
     * Starts a new multiplayer client connecting to the given `url`. TLS certificates will be verified
     * against the hostname when connecting using the `wss://` protocol. You can pass the optional
     * `tls_client_options` parameter to customize the trusted certification authorities, or disable
     * the common name verification. See `TLSOptions.client` and `TLSOptions.client_unsafe`. Note: It
     * is recommended to specify the scheme part of the URL, i.e. the `url` should start with either
     * `ws://` or `wss://`.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.create_client
     */
    fun createClient(url: String, tlsClientOptions: TLSOptions? = null): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringAndObjectArgRetLong(Binds.createClientBind, segment, url, tlsClientOptions?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(tlsClientOptions)
        return ret
    }

    /**
     * Starts a new multiplayer server listening on the given `port`. You can optionally specify a
     * `bind_address`, and provide valid `tls_server_options` to use TLS. See `TLSOptions.server`.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.create_server
     */
    fun createServer(port: Int, bindAddress: String = "*", tlsServerOptions: TLSOptions? = null): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithIntStringObjectArgsRetLong(Binds.createServerBind, segment, port, bindAddress, tlsServerOptions?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(tlsServerOptions)
        return ret
    }

    /**
     * Returns the `WebSocketPeer` associated to the given `peer_id`.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_peer
     */
    fun getPeer(peerId: Int): WebSocketPeer? {
        checkOpen()
        val ret = WebSocketPeer.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPeerBind, segment, peerId))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the IP address of the given peer.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_peer_address
     */
    fun getPeerAddress(id: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getPeerAddressBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the remote port of the given peer.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_peer_port
     */
    fun getPeerPort(id: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetInt(Binds.getPeerPortBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The supported WebSocket sub-protocols. See `WebSocketPeer.supported_protocols` for more details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_supported_protocols
     */
    fun getSupportedProtocols(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getSupportedProtocolsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The supported WebSocket sub-protocols. See `WebSocketPeer.supported_protocols` for more details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_supported_protocols
     */
    fun setSupportedProtocols(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setSupportedProtocolsBind, segment, protocols)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The extra headers to use during handshake. See `WebSocketPeer.handshake_headers` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_handshake_headers
     */
    fun getHandshakeHeaders(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getHandshakeHeadersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The extra headers to use during handshake. See `WebSocketPeer.handshake_headers` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_handshake_headers
     */
    fun setHandshakeHeaders(protocols: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setHandshakeHeadersBind, segment, protocols)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The inbound buffer size for connected peers. See `WebSocketPeer.inbound_buffer_size` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_inbound_buffer_size
     */
    fun getInboundBufferSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getInboundBufferSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The inbound buffer size for connected peers. See `WebSocketPeer.inbound_buffer_size` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_inbound_buffer_size
     */
    fun setInboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInboundBufferSizeBind, segment, bufferSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The outbound buffer size for connected peers. See `WebSocketPeer.outbound_buffer_size` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_outbound_buffer_size
     */
    fun getOutboundBufferSize(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutboundBufferSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The outbound buffer size for connected peers. See `WebSocketPeer.outbound_buffer_size` for more
     * details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_outbound_buffer_size
     */
    fun setOutboundBufferSize(bufferSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutboundBufferSizeBind, segment, bufferSize)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum time each peer can stay in a connecting state before being dropped.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_handshake_timeout
     */
    fun getHandshakeTimeout(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHandshakeTimeoutBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The maximum time each peer can stay in a connecting state before being dropped.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_handshake_timeout
     */
    fun setHandshakeTimeout(timeout: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHandshakeTimeoutBind, segment, timeout)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum number of queued packets for connected peers. See `WebSocketPeer.max_queued_packets`
     * for more details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.set_max_queued_packets
     */
    fun setMaxQueuedPackets(maxQueuedPackets: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxQueuedPacketsBind, segment, maxQueuedPackets)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum number of queued packets for connected peers. See `WebSocketPeer.max_queued_packets`
     * for more details.
     *
     * Generated from Godot docs: WebSocketMultiplayerPeer.get_max_queued_packets
     */
    fun getMaxQueuedPackets(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxQueuedPacketsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebSocketMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(WebSocketMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebSocketMultiplayerPeer? =
            if (handle.address() == 0L) null else WebSocketMultiplayerPeer(GodotHandle(handle))

        /** A new WebSocketMultiplayerPeer, GDScript's `WebSocketMultiplayerPeer.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): WebSocketMultiplayerPeer =
            RefCounted.owned(WebSocketMultiplayerPeer(GodotHandle(requireConstructed(ObjectCalls.constructObject("WebSocketMultiplayerPeer"), "WebSocketMultiplayerPeer"))))
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
