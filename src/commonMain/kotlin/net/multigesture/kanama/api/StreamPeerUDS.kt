package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A stream peer that handles UNIX Domain Socket (UDS) connections.
 *
 * Generated from Godot docs: StreamPeerUDS
 */
class StreamPeerUDS(handle: GodotHandle) : StreamPeerSocket(handle) {
    /**
     * Opens the UDS socket, and binds it to the specified socket path. This method is generally not
     * needed, and only used to force the subsequent call to `connect_to_host` to use the specified
     * `path` as the source address.
     *
     * Generated from Godot docs: StreamPeerUDS.bind
     */
    fun bind(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.bindBind, segment, path))
    }

    /**
     * Connects to the specified UNIX Domain Socket path. Returns `GodotError.OK` on success.
     *
     * Generated from Godot docs: StreamPeerUDS.connect_to_host
     */
    fun connectToHost(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.connectToHostBind, segment, path))
    }

    /**
     * Returns the socket path of this peer.
     *
     * Generated from Godot docs: StreamPeerUDS.get_connected_path
     */
    fun getConnectedPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getConnectedPathBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerUDS? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StreamPeerUDS? =
            if (handle.address() == 0L) null else RefCounted.owned(StreamPeerUDS(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StreamPeerUDS? =
            if (handle.address() == 0L) null else StreamPeerUDS(GodotHandle(handle))
    }

    private object Binds {
        private const val BIND_HASH = 166001499L
        @JvmField
        val bindBind =
            ObjectCalls.getMethodBind("StreamPeerUDS", "bind", BIND_HASH)

        private const val CONNECT_TO_HOST_HASH = 166001499L
        @JvmField
        val connectToHostBind =
            ObjectCalls.getMethodBind("StreamPeerUDS", "connect_to_host", CONNECT_TO_HOST_HASH)

        private const val GET_CONNECTED_PATH_HASH = 201670096L
        @JvmField
        val getConnectedPathBind =
            ObjectCalls.getMethodBind("StreamPeerUDS", "get_connected_path", GET_CONNECTED_PATH_HASH)
    }
}
