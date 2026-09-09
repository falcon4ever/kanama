package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorDebuggerPlugin
 */
class EditorDebuggerPlugin(handle: MemorySegment) : RefCounted(handle) {
    fun getSession(id: Int): EditorDebuggerSession? {
        checkOpen()
        return EditorDebuggerSession.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSessionBind, handle, id))
    }

    fun getSessions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getSessionsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): EditorDebuggerPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorDebuggerPlugin? =
            if (handle.address() == 0L) null else EditorDebuggerPlugin(handle)

        private const val GET_SESSION_HASH = 3061968499L
        private val getSessionBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerPlugin", "get_session", GET_SESSION_HASH)
        }

        private const val GET_SESSIONS_HASH = 2915620761L
        private val getSessionsBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerPlugin", "get_sessions", GET_SESSIONS_HASH)
        }
    }
}
