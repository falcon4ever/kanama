package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Manages toast notifications within the editor.
 *
 * Generated from Godot docs: EditorToaster
 */
class EditorToaster(handle: GodotHandle) : HBoxContainer(handle) {
    /**
     * Pushes a toast notification to the editor for display.
     *
     * Generated from Godot docs: EditorToaster.push_toast
     */
    fun pushToast(message: String, severity: Long = 0L, tooltip: String = "") {
        ObjectCalls.ptrcallWithStringLongStringArgs(pushToastBind, segment, message, severity, tooltip)
    }

    companion object {
        const val SEVERITY_INFO: Long = 0L
        const val SEVERITY_WARNING: Long = 1L
        const val SEVERITY_ERROR: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorToaster? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorToaster? =
            if (handle.address() == 0L) null else EditorToaster(GodotHandle(handle))

        private const val PUSH_TOAST_HASH = 1813923476L
        private val pushToastBind by lazy {
            ObjectCalls.getMethodBind("EditorToaster", "push_toast", PUSH_TOAST_HASH)
        }
    }
}
