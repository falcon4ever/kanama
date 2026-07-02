package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A class to interact with the editor debugger.
 *
 * Generated from Godot docs: EditorDebuggerSession
 */
class EditorDebuggerSession(handle: MemorySegment) : RefCounted(handle) {
    fun sendMessage(message: String, data: List<Any?> = emptyList()) {
        ObjectCalls.ptrcallWithStringAndArrayArg(sendMessageBind, handle, message, data)
    }

    fun toggleProfiler(profiler: String, enable: Boolean, data: List<Any?> = emptyList()) {
        ObjectCalls.ptrcallWithStringBoolArrayArgs(toggleProfilerBind, handle, profiler, enable, data)
    }

    fun isBreaked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBreakedBind, handle)
    }

    fun isDebuggable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebuggableBind, handle)
    }

    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    fun addSessionTab(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(addSessionTabBind, handle, listOf(control.handle))
    }

    fun removeSessionTab(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(removeSessionTabBind, handle, listOf(control.handle))
    }

    fun setBreakpoint(path: String, line: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(setBreakpointBind, handle, path, line, enabled)
    }

    object Signals {
        const val started: String = "started"
        const val stopped: String = "stopped"
        const val breaked: String = "breaked"
        const val continued: String = "continued"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorDebuggerSession? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorDebuggerSession? =
            if (handle.address() == 0L) null else EditorDebuggerSession(handle)

        private const val SEND_MESSAGE_HASH = 85656714L
        private val sendMessageBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "send_message", SEND_MESSAGE_HASH)
        }

        private const val TOGGLE_PROFILER_HASH = 1198443697L
        private val toggleProfilerBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "toggle_profiler", TOGGLE_PROFILER_HASH)
        }

        private const val IS_BREAKED_HASH = 2240911060L
        private val isBreakedBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "is_breaked", IS_BREAKED_HASH)
        }

        private const val IS_DEBUGGABLE_HASH = 2240911060L
        private val isDebuggableBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "is_debuggable", IS_DEBUGGABLE_HASH)
        }

        private const val IS_ACTIVE_HASH = 2240911060L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "is_active", IS_ACTIVE_HASH)
        }

        private const val ADD_SESSION_TAB_HASH = 1496901182L
        private val addSessionTabBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "add_session_tab", ADD_SESSION_TAB_HASH)
        }

        private const val REMOVE_SESSION_TAB_HASH = 1496901182L
        private val removeSessionTabBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "remove_session_tab", REMOVE_SESSION_TAB_HASH)
        }

        private const val SET_BREAKPOINT_HASH = 4108344793L
        private val setBreakpointBind by lazy {
            ObjectCalls.getMethodBind("EditorDebuggerSession", "set_breakpoint", SET_BREAKPOINT_HASH)
        }
    }
}
