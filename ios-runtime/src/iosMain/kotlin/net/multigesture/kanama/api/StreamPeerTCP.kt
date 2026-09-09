package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StreamPeerTCP
 */
class StreamPeerTCP(handle: MemorySegment) : StreamPeerSocket(handle) {
    fun bind(port: Int, host: String = "*"): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndStringArgRetLong(bindBind, handle, port, host)
    }

    fun connectToHost(host: String, port: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(connectToHostBind, handle, host, port)
    }

    fun getConnectedHost(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getConnectedHostBind, handle)
    }

    fun getConnectedPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getConnectedPortBind, handle)
    }

    fun getLocalPort(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLocalPortBind, handle)
    }

    fun setNoDelay(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNoDelayBind, handle, enabled)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): StreamPeerTCP? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeerTCP? =
            if (handle.address() == 0L) null else StreamPeerTCP(handle)

        private const val BIND_HASH = 3167955072L
        private val bindBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "bind", BIND_HASH)
        }

        private const val CONNECT_TO_HOST_HASH = 993915709L
        private val connectToHostBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "connect_to_host", CONNECT_TO_HOST_HASH)
        }

        private const val GET_CONNECTED_HOST_HASH = 201670096L
        private val getConnectedHostBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_connected_host", GET_CONNECTED_HOST_HASH)
        }

        private const val GET_CONNECTED_PORT_HASH = 3905245786L
        private val getConnectedPortBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_connected_port", GET_CONNECTED_PORT_HASH)
        }

        private const val GET_LOCAL_PORT_HASH = 3905245786L
        private val getLocalPortBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "get_local_port", GET_LOCAL_PORT_HASH)
        }

        private const val SET_NO_DELAY_HASH = 2586408642L
        private val setNoDelayBind by lazy {
            ObjectCalls.getMethodBind("StreamPeerTCP", "set_no_delay", SET_NO_DELAY_HASH)
        }
    }
}
