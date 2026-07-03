package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A modified version of `FileDialog` used by the editor.
 *
 * Generated from Godot docs: EditorFileDialog
 */
class EditorFileDialog(handle: MemorySegment) : FileDialog(handle) {
    var disableOverwriteWarning: Boolean
        @JvmName("disableOverwriteWarningProperty")
        get() = isOverwriteWarningDisabled()
        @JvmName("setDisableOverwriteWarningProperty")
        set(value) = setDisableOverwriteWarning(value)

    /**
     * This method is kept for compatibility and does nothing. As an alternative, you can display
     * another dialog after showing the file dialog.
     *
     * Generated from Godot docs: EditorFileDialog.add_side_menu
     */
    fun addSideMenu(menu: Control, title: String = "") {
        ObjectCalls.ptrcallWithObjectAndStringArg(addSideMenuBind, handle, menu.handle, title)
    }

    /**
     * If `true`, the `EditorFileDialog` will not warn the user before overwriting files.
     *
     * Generated from Godot docs: EditorFileDialog.set_disable_overwrite_warning
     */
    fun setDisableOverwriteWarning(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableOverwriteWarningBind, handle, disable)
    }

    /**
     * If `true`, the `EditorFileDialog` will not warn the user before overwriting files.
     *
     * Generated from Godot docs: EditorFileDialog.is_overwrite_warning_disabled
     */
    fun isOverwriteWarningDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOverwriteWarningDisabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorFileDialog? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorFileDialog? =
            if (handle.address() == 0L) null else EditorFileDialog(handle)

        private const val ADD_SIDE_MENU_HASH = 402368861L
        private val addSideMenuBind by lazy {
            ObjectCalls.getMethodBind("EditorFileDialog", "add_side_menu", ADD_SIDE_MENU_HASH)
        }

        private const val SET_DISABLE_OVERWRITE_WARNING_HASH = 2586408642L
        private val setDisableOverwriteWarningBind by lazy {
            ObjectCalls.getMethodBind("EditorFileDialog", "set_disable_overwrite_warning", SET_DISABLE_OVERWRITE_WARNING_HASH)
        }

        private const val IS_OVERWRITE_WARNING_DISABLED_HASH = 36873697L
        private val isOverwriteWarningDisabledBind by lazy {
            ObjectCalls.getMethodBind("EditorFileDialog", "is_overwrite_warning_disabled", IS_OVERWRITE_WARNING_DISABLED_HASH)
        }
    }
}
