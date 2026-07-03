package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A base class to implement debugger plugins.
 *
 * Generated from Godot docs: EditorDebuggerPlugin
 */
class EditorDebuggerPlugin(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the `EditorDebuggerSession` with the given `id`.
     *
     * Generated from Godot docs: EditorDebuggerPlugin.get_session
     */
    fun getSession(id: Int): EditorDebuggerSession? {
        return EditorDebuggerSession.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSessionBind, handle, id))
    }

    /**
     * Returns an array of `EditorDebuggerSession` currently available to this debugger plugin. Note:
     * Sessions in the array may be inactive, check their state via `EditorDebuggerSession.is_active`.
     *
     * Generated from Godot docs: EditorDebuggerPlugin.get_sessions
     */
    fun getSessions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getSessionsBind, handle)
    }

    companion object {
        @JvmStatic
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
