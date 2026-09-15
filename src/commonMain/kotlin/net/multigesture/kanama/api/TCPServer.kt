package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A TCP server.
 *
 * Generated from Godot docs: TCPServer
 */
class TCPServer(handle: GodotHandle) : SocketServer(handle) {
    /**
     * Listen on the `port` binding to `bind_address`. If `bind_address` is set as `"*"` (default), the
     * server will listen on all available addresses (both IPv4 and IPv6). If `bind_address` is set as
     * `"0.0.0.0"` (for IPv4) or `"::"` (for IPv6), the server will listen on all available addresses
     * matching that IP type. If `bind_address` is set to any valid address (e.g. `"192.168.1.101"`,
     * `"::1"`, etc.), the server will only listen on the interface with that address (or fail if no
     * interface with the given address exists).
     *
     * Generated from Godot docs: TCPServer.listen
     */
    fun listen(port: Int, bindAddress: String = "*"): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetLong(listenBind, segment, port, bindAddress)
    }

    /**
     * Returns the local port this server is listening to.
     *
     * Generated from Godot docs: TCPServer.get_local_port
     */
    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, segment)
    }

    /**
     * If a connection is available, returns a StreamPeerTCP with the connection.
     *
     * Generated from Godot docs: TCPServer.take_connection
     */
    fun takeConnection(): StreamPeerTCP? {
        checkOpen()
        return StreamPeerTCP.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TCPServer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TCPServer? =
            if (handle.address() == 0L) null else TCPServer(GodotHandle(handle))

        private const val LISTEN_HASH = 3167955072L
        private val listenBind by lazy {
            ObjectCalls.getMethodBind("TCPServer", "listen", LISTEN_HASH)
        }

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        private val getLocalPortBind by lazy {
            ObjectCalls.getMethodBind("TCPServer", "get_local_port", GET_LOCAL_PORT_HASH)
        }

        private const val TAKE_CONNECTION_HASH = 30545006L
        private val takeConnectionBind by lazy {
            ObjectCalls.getMethodBind("TCPServer", "take_connection", TAKE_CONNECTION_HASH)
        }
    }
}
