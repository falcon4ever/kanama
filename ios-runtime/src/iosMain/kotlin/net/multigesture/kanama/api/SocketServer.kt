package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: SocketServer
 */
open class SocketServer(handle: MemorySegment) : RefCounted(handle) {
    fun isConnectionAvailable(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isConnectionAvailableBind, handle)
    }

    fun isListening(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isListeningBind, handle)
    }

    fun stop() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    fun takeSocketConnection(): StreamPeerSocket? {
        checkOpen()
        return StreamPeerSocket.wrap(ObjectCalls.ptrcallNoArgsRetObject(takeSocketConnectionBind, handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): SocketServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SocketServer? =
            if (handle.address() == 0L) null else SocketServer(handle)

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
