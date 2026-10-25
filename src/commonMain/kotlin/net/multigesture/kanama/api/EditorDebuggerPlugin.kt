package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        val ret = EditorDebuggerSession.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSessionBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an array of `EditorDebuggerSession` currently available to this debugger plugin. Note:
     * Sessions in the array may be inactive, check their state via `EditorDebuggerSession.is_active`.
     *
     * Generated from Godot docs: EditorDebuggerPlugin.get_sessions
     */
    fun getSessions(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getSessionsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorDebuggerPlugin? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorDebuggerPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorDebuggerPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorDebuggerPlugin? =
            if (handle.address() == 0L) null else EditorDebuggerPlugin(GodotHandle(handle))

        /** A new EditorDebuggerPlugin, GDScript's `EditorDebuggerPlugin.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorDebuggerPlugin =
            RefCounted.owned(EditorDebuggerPlugin(GodotHandle(ObjectCalls.constructObject("EditorDebuggerPlugin"))))
    }

    private object Binds {
        private const val GET_SESSION_HASH = 3061968499L
        @JvmField
        val getSessionBind =
            ObjectCalls.getMethodBind("EditorDebuggerPlugin", "get_session", GET_SESSION_HASH)

        private const val GET_SESSIONS_HASH = 2915620761L
        @JvmField
        val getSessionsBind =
            ObjectCalls.getMethodBind("EditorDebuggerPlugin", "get_sessions", GET_SESSIONS_HASH)
    }
}
