package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for interacting with socket streams.
 *
 * Generated from Godot docs: StreamPeerSocket
 */
open class StreamPeerSocket(handle: GodotHandle) : StreamPeer(handle) {
    /**
     * Polls the socket, updating its state. See `get_status`.
     *
     * Generated from Godot docs: StreamPeerSocket.poll
     */
    fun poll(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(pollBind, segment))
    }

    /**
     * Returns the status of the connection.
     *
     * Generated from Godot docs: StreamPeerSocket.get_status
     */
    fun getStatus(): StreamPeerSocket.Status {
        checkOpen()
        return StreamPeerSocket.Status(ObjectCalls.ptrcallNoArgsRetLong(getStatusBind, segment))
    }

    /**
     * Disconnects from host.
     *
     * Generated from Godot docs: StreamPeerSocket.disconnect_from_host
     */
    fun disconnectFromHost() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(disconnectFromHostBind, segment)
    }

    /**
     * Godot's `StreamPeerSocket.Status` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`StreamPeerSocket.Status.<NAME>`).
     *
     * Generated from Godot docs: StreamPeerSocket.Status
     */
    @JvmInline
    value class Status(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The initial status of the `StreamPeerSocket`. This is also the status after disconnecting.
             *
             * Generated from Godot docs: StreamPeerSocket.STATUS_NONE
             */
            val NONE: Status get() = Status(0L)
            /**
             * A status representing a `StreamPeerSocket` that is connecting to a host.
             *
             * Generated from Godot docs: StreamPeerSocket.STATUS_CONNECTING
             */
            val CONNECTING: Status get() = Status(1L)
            /**
             * A status representing a `StreamPeerSocket` that is connected to a host.
             *
             * Generated from Godot docs: StreamPeerSocket.STATUS_CONNECTED
             */
            val CONNECTED: Status get() = Status(2L)
            /**
             * A status representing a `StreamPeerSocket` in error state.
             *
             * Generated from Godot docs: StreamPeerSocket.STATUS_ERROR
             */
            val ERROR: Status get() = Status(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerSocket? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StreamPeerSocket? =
            if (handle.address() == 0L) null else StreamPeerSocket(GodotHandle(handle))

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "poll", POLL_HASH)
        }

        private const val GET_STATUS_HASH = 1156122502L
        private val getStatusBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "get_status", GET_STATUS_HASH)
        }

        private const val DISCONNECT_FROM_HOST_HASH = 3218959716L
        private val disconnectFromHostBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerSocket", "disconnect_from_host", DISCONNECT_FROM_HOST_HASH)
        }
    }
}
