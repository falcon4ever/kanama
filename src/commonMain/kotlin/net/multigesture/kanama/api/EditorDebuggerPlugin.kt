package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A base class to implement debugger plugins.
 *
 * Generated from Godot docs: EditorDebuggerPlugin
 */
class EditorDebuggerPlugin(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the `EditorDebuggerSession` with the given `id`.
     *
     * Generated from Godot docs: EditorDebuggerPlugin.get_session
     */
    fun getSession(id: Int): EditorDebuggerSession? {
        checkOpen()
        return EditorDebuggerSession.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSessionBind, segment, id))
    }

    /**
     * Returns an array of `EditorDebuggerSession` currently available to this debugger plugin. Note:
     * Sessions in the array may be inactive, check their state via `EditorDebuggerSession.is_active`.
     *
     * Generated from Godot docs: EditorDebuggerPlugin.get_sessions
     */
    fun getSessions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getSessionsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorDebuggerPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): EditorDebuggerPlugin? =
            if (handle.address() == 0L) null else EditorDebuggerPlugin(GodotHandle(handle))

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
