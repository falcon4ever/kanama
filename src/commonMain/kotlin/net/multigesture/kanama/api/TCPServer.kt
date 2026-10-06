package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
    fun listen(port: Int, bindAddress: String = "*"): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndStringArgRetLong(Binds.listenBind, segment, port, bindAddress))
    }

    /**
     * Returns the local port this server is listening to.
     *
     * Generated from Godot docs: TCPServer.get_local_port
     */
    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLocalPortBind, segment)
    }

    /**
     * If a connection is available, returns a StreamPeerTCP with the connection.
     *
     * Generated from Godot docs: TCPServer.take_connection
     */
    fun takeConnection(): StreamPeerTCP? {
        checkOpen()
        return StreamPeerTCP.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.takeConnectionBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TCPServer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TCPServer? =
            if (handle.address() == 0L) null else RefCounted.owned(TCPServer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TCPServer? =
            if (handle.address() == 0L) null else TCPServer(GodotHandle(handle))
    }

    private object Binds {
        private const val LISTEN_HASH = 3167955072L
        @JvmField
        val listenBind =
            ObjectCalls.getMethodBind("TCPServer", "listen", LISTEN_HASH)

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        @JvmField
        val getLocalPortBind =
            ObjectCalls.getMethodBind("TCPServer", "get_local_port", GET_LOCAL_PORT_HASH)

        private const val TAKE_CONNECTION_HASH = 30545006L
        @JvmField
        val takeConnectionBind =
            ObjectCalls.getMethodBind("TCPServer", "take_connection", TAKE_CONNECTION_HASH)
    }
}
