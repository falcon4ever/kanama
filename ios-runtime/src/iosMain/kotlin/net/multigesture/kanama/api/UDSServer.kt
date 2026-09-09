package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: UDSServer
 */
class UDSServer(handle: MemorySegment) : SocketServer(handle) {
    fun listen(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(listenBind, handle, path)
    }

    fun takeConnection(): StreamPeerUDS? {
        checkOpen()
        return StreamPeerUDS.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeConnectionBind, handle))
    }

    companion object {
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
