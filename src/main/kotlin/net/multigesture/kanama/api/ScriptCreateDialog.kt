package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's popup dialog for creating new `Script` files.
 *
 * Generated from Godot docs: ScriptCreateDialog
 */
class ScriptCreateDialog(handle: MemorySegment) : ConfirmationDialog(handle) {
    /**
     * Prefills required fields to configure the ScriptCreateDialog for use.
     *
     * Generated from Godot docs: ScriptCreateDialog.config
     */
    fun config(inherits: String, path: String, builtInEnabled: Boolean = true, loadEnabled: Boolean = true) {
        ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgs(configBind, handle, inherits, path, builtInEnabled, loadEnabled)
    }

    object Signals {
        const val scriptCreated: String = "script_created"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScriptCreateDialog? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptCreateDialog? =
            if (handle.address() == 0L) null else ScriptCreateDialog(handle)

        private const val CONFIG_HASH = 869314288L
        private val configBind by lazy {
            ObjectCalls.getMethodBind("ScriptCreateDialog", "config", CONFIG_HASH)
        }
    }
}
