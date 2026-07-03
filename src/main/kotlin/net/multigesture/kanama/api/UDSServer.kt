package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A Unix Domain Socket (UDS) server.
 *
 * Generated from Godot docs: UDSServer
 */
class UDSServer(handle: MemorySegment) : SocketServer(handle) {
    /**
     * Listens on the socket at `path`. The socket file will be created at the specified path. Note:
     * The socket file must not already exist at the specified path. You may need to remove any
     * existing socket file before calling this method.
     *
     * Generated from Godot docs: UDSServer.listen
     */
    fun listen(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(listenBind, handle, path)
    }

    /**
     * If a connection is available, returns a StreamPeerUDS with the connection.
     *
     * Generated from Godot docs: UDSServer.take_connection
     */
    fun takeConnection(): StreamPeerUDS? {
        return StreamPeerUDS.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): UDSServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): UDSServer? =
            if (handle.address() == 0L) null else UDSServer(handle)

        private const val LISTEN_HASH = 166001499L
        private val listenBind by lazy {
            ObjectCalls.getMethodBind("UDSServer", "listen", LISTEN_HASH)
        }

        private const val TAKE_CONNECTION_HASH = 1623851112L
        private val takeConnectionBind by lazy {
            ObjectCalls.getMethodBind("UDSServer", "take_connection", TAKE_CONNECTION_HASH)
        }
    }
}
