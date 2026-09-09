package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: TCPServer
 */
class TCPServer(handle: MemorySegment) : SocketServer(handle) {
    fun listen(port: Int, bindAddress: String = "*"): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetLong(listenBind, handle, port, bindAddress)
    }

    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun takeConnection(): StreamPeerTCP? {
        checkOpen()
        return StreamPeerTCP.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, handle))
    }

    companion object {
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
