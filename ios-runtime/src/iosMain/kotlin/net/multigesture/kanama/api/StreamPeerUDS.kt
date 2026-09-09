package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerUDS
 */
class StreamPeerUDS(handle: MemorySegment) : StreamPeerSocket(handle) {
    fun bind(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(bindBind, handle, path)
    }

    fun connectToHost(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(connectToHostBind, handle, path)
    }

    fun getConnectedPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getConnectedPathBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): StreamPeerUDS? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerUDS? =
            if (handle.address() == 0L) null else StreamPeerUDS(handle)

        private const val BIND_HASH = 166001499L
        private val bindBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerUDS", "bind", BIND_HASH)
        }

        private const val CONNECT_TO_HOST_HASH = 166001499L
        private val connectToHostBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerUDS", "connect_to_host", CONNECT_TO_HOST_HASH)
        }

        private const val GET_CONNECTED_PATH_HASH = 201670096L
        private val getConnectedPathBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerUDS", "get_connected_path", GET_CONNECTED_PATH_HASH)
        }
    }
}
