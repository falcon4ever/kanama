package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Manages toast notifications within the editor.
 *
 * Generated from Godot docs: EditorToaster
 */
class EditorToaster(handle: MemorySegment) : HBoxContainer(handle) {
    /**
     * Pushes a toast notification to the editor for display.
     *
     * Generated from Godot docs: EditorToaster.push_toast
     */
    fun pushToast(message: String, severity: Long = 0L, tooltip: String = "") {
        ObjectCalls.ptrcallWithStringLongStringArgs(pushToastBind, handle, message, severity, tooltip)
    }

    companion object {
        const val SEVERITY_INFO: Long = 0L
        const val SEVERITY_WARNING: Long = 1L
        const val SEVERITY_ERROR: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorToaster? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorToaster? =
            if (handle.address() == 0L) null else EditorToaster(handle)

        private const val PUSH_TOAST_HASH = 1813923476L
        private val pushToastBind by lazy {
            ObjectCalls.getMethodBind("EditorToaster", "push_toast", PUSH_TOAST_HASH)
        }
    }
}
