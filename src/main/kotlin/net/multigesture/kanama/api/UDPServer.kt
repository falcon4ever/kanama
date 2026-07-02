package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Helper class to implement a UDP server.
 *
 * Generated from Godot docs: UDPServer
 */
class UDPServer(handle: MemorySegment) : RefCounted(handle) {
    var maxPendingConnections: Int
        @JvmName("maxPendingConnectionsProperty")
        get() = getMaxPendingConnections()
        @JvmName("setMaxPendingConnectionsProperty")
        set(value) = setMaxPendingConnections(value)

    /**
     * Starts the server by opening a UDP socket listening on the given `port`. You can optionally
     * specify a `bind_address` to only listen for packets sent to that address. See also
     * `PacketPeerUDP.bind`.
     *
     * Generated from Godot docs: UDPServer.listen
     */
    fun listen(port: Int, bindAddress: String = "*"): Long {
        return ObjectCalls.ptrcallWithIntAndStringArgRetLong(listenBind, handle, port, bindAddress)
    }

    /**
     * Call this method at regular intervals (e.g. inside `Node._process`) to process new packets. Any
     * packet from a known address/port pair will be delivered to the appropriate `PacketPeerUDP`,
     * while any packet received from an unknown address/port pair will be added as a pending
     * connection (see `is_connection_available` and `take_connection`). The maximum number of pending
     * connections is defined via `max_pending_connections`.
     *
     * Generated from Godot docs: UDPServer.poll
     */
    fun poll(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun isConnectionAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isConnectionAvailableBind, handle)
    }

    fun getLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun isListening(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isListeningBind, handle)
    }

    fun takeConnection(): PacketPeerUDP? {
        return PacketPeerUDP.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, handle))
    }

    /**
     * Stops the server, closing the UDP socket if open. Will close all connected `PacketPeerUDP`
     * accepted via `take_connection` (remote peers will not be notified).
     *
     * Generated from Godot docs: UDPServer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun setMaxPendingConnections(maxPendingConnections: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxPendingConnectionsBind, handle, maxPendingConnections)
    }

    fun getMaxPendingConnections(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPendingConnectionsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): UDPServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UDPServer? =
            if (handle.address() == 0L) null else UDPServer(handle)

        private const val LISTEN_HASH = 3167955072L
        private val listenBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "listen", LISTEN_HASH)
        }

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "poll", POLL_HASH)
        }

        private const val IS_CONNECTION_AVAILABLE_HASH = 36873697L
        private val isConnectionAvailableBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "is_connection_available", IS_CONNECTION_AVAILABLE_HASH)
        }

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        private val getLocalPortBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "get_local_port", GET_LOCAL_PORT_HASH)
        }

        private const val IS_LISTENING_HASH = 36873697L
        private val isListeningBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "is_listening", IS_LISTENING_HASH)
        }

        private const val TAKE_CONNECTION_HASH = 808734560L
        private val takeConnectionBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "take_connection", TAKE_CONNECTION_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "stop", STOP_HASH)
        }

        private const val SET_MAX_PENDING_CONNECTIONS_HASH = 1286410249L
        private val setMaxPendingConnectionsBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "set_max_pending_connections", SET_MAX_PENDING_CONNECTIONS_HASH)
        }

        private const val GET_MAX_PENDING_CONNECTIONS_HASH = 3905245786L
        private val getMaxPendingConnectionsBind by lazy {
            ObjectCalls.getMethodBind("UDPServer", "get_max_pending_connections", GET_MAX_PENDING_CONNECTIONS_HASH)
        }
    }
}
