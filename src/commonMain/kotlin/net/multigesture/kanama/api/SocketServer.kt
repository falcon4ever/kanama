package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * An abstract class for servers based on sockets.
 *
 * Generated from Godot docs: SocketServer
 */
open class SocketServer(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns `true` if a connection is available for taking.
     *
     * Generated from Godot docs: SocketServer.is_connection_available
     */
    fun isConnectionAvailable(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isConnectionAvailableBind, segment)
    }

    /**
     * Returns `true` if the server is currently listening for connections.
     *
     * Generated from Godot docs: SocketServer.is_listening
     */
    fun isListening(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isListeningBind, segment)
    }

    /**
     * Stops listening.
     *
     * Generated from Godot docs: SocketServer.stop
     */
    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopBind, segment)
    }

    /**
     * If a connection is available, returns a StreamPeerSocket with the connection.
     *
     * Generated from Godot docs: SocketServer.take_socket_connection
     */
    fun takeSocketConnection(): StreamPeerSocket? {
        checkOpen()
        return StreamPeerSocket.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeSocketConnectionBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SocketServer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SocketServer? =
            if (handle.address() == 0L) null else SocketServer(GodotHandle(handle))

        private const val IS_CONNECTION_AVAILABLE_HASH = 36873697L
        private val isConnectionAvailableBind by lazy {
            ObjectCalls.getMethodBind("SocketServer", "is_connection_available", IS_CONNECTION_AVAILABLE_HASH)
        }

        private const val IS_LISTENING_HASH = 36873697L
        private val isListeningBind by lazy {
            ObjectCalls.getMethodBind("SocketServer", "is_listening", IS_LISTENING_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("SocketServer", "stop", STOP_HASH)
        }

        private const val TAKE_SOCKET_CONNECTION_HASH = 1883962599L
        private val takeSocketConnectionBind by lazy {
            ObjectCalls.getMethodBind("SocketServer", "take_socket_connection", TAKE_SOCKET_CONNECTION_HASH)
        }
    }
}
