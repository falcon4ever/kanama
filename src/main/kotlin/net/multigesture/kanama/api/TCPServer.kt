package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A TCP server.
 *
 * Generated from Godot docs: TCPServer
 */
class TCPServer(handle: MemorySegment) : SocketServer(handle) {
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
        return ObjectCalls.ptrcallWithIntAndStringArgRetLong(listenBind, handle, port, bindAddress)
    }

    fun getLocalPort(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun takeConnection(): StreamPeerTCP? {
        return StreamPeerTCP.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TCPServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TCPServer? =
            if (handle.address() == 0L) null else TCPServer(handle)

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
