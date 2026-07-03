package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A stream peer that handles UNIX Domain Socket (UDS) connections.
 *
 * Generated from Godot docs: StreamPeerUDS
 */
class StreamPeerUDS(handle: MemorySegment) : StreamPeerSocket(handle) {
    /**
     * Opens the UDS socket, and binds it to the specified socket path. This method is generally not
     * needed, and only used to force the subsequent call to `connect_to_host` to use the specified
     * `path` as the source address.
     *
     * Generated from Godot docs: StreamPeerUDS.bind
     */
    fun bind(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(bindBind, handle, path)
    }

    /**
     * Connects to the specified UNIX Domain Socket path. Returns `OK` on success.
     *
     * Generated from Godot docs: StreamPeerUDS.connect_to_host
     */
    fun connectToHost(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(connectToHostBind, handle, path)
    }

    /**
     * Returns the socket path of this peer.
     *
     * Generated from Godot docs: StreamPeerUDS.get_connected_path
     */
    fun getConnectedPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConnectedPathBind, handle)
    }

    companion object {
        @JvmStatic
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
