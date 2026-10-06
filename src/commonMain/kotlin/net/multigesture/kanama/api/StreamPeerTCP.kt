package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A stream peer that handles TCP connections.
 *
 * Generated from Godot docs: StreamPeerTCP
 */
class StreamPeerTCP(handle: GodotHandle) : StreamPeerSocket(handle) {
    /**
     * Opens the TCP socket, and binds it to the specified local address. This method is generally not
     * needed, and only used to force the subsequent call to `connect_to_host` to use the specified
     * `host` and `port` as source address. This can be desired in some NAT punchthrough techniques, or
     * when forcing the source network interface.
     *
     * Generated from Godot docs: StreamPeerTCP.bind
     */
    fun bind(port: Int, host: String = "*"): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndStringArgRetLong(Binds.bindBind, segment, port, host))
    }

    /**
     * Connects to the specified `host:port` pair. A hostname will be resolved if valid. Returns
     * `GodotError.OK` on success.
     *
     * Generated from Godot docs: StreamPeerTCP.connect_to_host
     */
    fun connectToHost(host: String, port: Int): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndIntArgRetLong(Binds.connectToHostBind, segment, host, port))
    }

    /**
     * Returns the IP of this peer.
     *
     * Generated from Godot docs: StreamPeerTCP.get_connected_host
     */
    fun getConnectedHost(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getConnectedHostBind, segment)
    }

    /**
     * Returns the port of this peer.
     *
     * Generated from Godot docs: StreamPeerTCP.get_connected_port
     */
    fun getConnectedPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getConnectedPortBind, segment)
    }

    /**
     * Returns the local port to which this peer is bound.
     *
     * Generated from Godot docs: StreamPeerTCP.get_local_port
     */
    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLocalPortBind, segment)
    }

    /**
     * If `enabled` is `true`, packets will be sent immediately. If `enabled` is `false` (the default),
     * packet transfers will be delayed and combined using Nagle's algorithm
     * (https://en.wikipedia.org/wiki/Nagle%27s_algorithm). Note: It's recommended to leave this
     * disabled for applications that send large packets or need to transfer a lot of data, as enabling
     * this can decrease the total available bandwidth.
     *
     * Generated from Godot docs: StreamPeerTCP.set_no_delay
     */
    fun setNoDelay(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setNoDelayBind, segment, enabled)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeerTCP? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StreamPeerTCP? =
            if (handle.address() == 0L) null else RefCounted.owned(StreamPeerTCP(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StreamPeerTCP? =
            if (handle.address() == 0L) null else StreamPeerTCP(GodotHandle(handle))
    }

    private object Binds {
        private const val BIND_HASH = 3167955072L
        @JvmField
        val bindBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "bind", BIND_HASH)

        private const val CONNECT_TO_HOST_HASH = 993915709L
        @JvmField
        val connectToHostBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "connect_to_host", CONNECT_TO_HOST_HASH)

        private const val GET_CONNECTED_HOST_HASH = 201670096L
        @JvmField
        val getConnectedHostBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_connected_host", GET_CONNECTED_HOST_HASH)

        private const val GET_CONNECTED_PORT_HASH = 3905245786L
        @JvmField
        val getConnectedPortBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_connected_port", GET_CONNECTED_PORT_HASH)

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        @JvmField
        val getLocalPortBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_local_port", GET_LOCAL_PORT_HASH)

        private const val SET_NO_DELAY_HASH = 2586408642L
        @JvmField
        val setNoDelayBind =
            ObjectCalls.getMethodBind("StreamPeerTCP", "set_no_delay", SET_NO_DELAY_HASH)
    }
}
