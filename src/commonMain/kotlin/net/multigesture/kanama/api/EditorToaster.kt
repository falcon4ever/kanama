package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
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
    fun pushToast(message: String, severity: EditorToaster.Severity = EditorToaster.Severity.INFO, tooltip: String = "") {
        ObjectCalls.ptrcallWithStringLongStringArgs(pushToastBind, segment, message, severity.value, tooltip)
    }

    @JvmInline
    value class Severity(val value: Long) {
        companion object {
            /**
             * Toast will display with an INFO severity.
             *
             * Generated from Godot docs: EditorToaster.SEVERITY_INFO
             */
            val INFO: Severity get() = Severity(0L)
            /**
             * Toast will display with a WARNING severity and have a corresponding color.
             *
             * Generated from Godot docs: EditorToaster.SEVERITY_WARNING
             */
            val WARNING: Severity get() = Severity(1L)
            /**
             * Toast will display with an ERROR severity and have a corresponding color.
             *
             * Generated from Godot docs: EditorToaster.SEVERITY_ERROR
             */
            val ERROR: Severity get() = Severity(2L)
        }
    }

    companion object {
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
