package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's command palette.
 *
 * Generated from Godot docs: EditorCommandPalette
 */
class EditorCommandPalette(handle: MemorySegment) : ConfirmationDialog(handle) {
    /**
     * Adds a custom command to EditorCommandPalette. - `command_name`: `String` (Name of the Command.
     * This is displayed to the user.) - `key_name`: `String` (Name of the key for a particular
     * Command. This is used to uniquely identify the Command.) - `binded_callable`: `Callable`
     * (Callable of the Command. This will be executed when the Command is selected.) -
     * `shortcut_text`: `String` (Shortcut text of the Command if available.)
     *
     * Generated from Godot docs: EditorCommandPalette.add_command
     */
    fun addCommand(commandName: String, keyName: String, bindedCallable: GodotCallable, shortcutText: String = "None") {
        ObjectCalls.ptrcallWithTwoStringCallableStringArgs(addCommandBind, handle, commandName, keyName, bindedCallable.target.handle, bindedCallable.method, shortcutText)
    }

    /**
     * Removes the custom command from EditorCommandPalette. - `key_name`: `String` (Name of the key
     * for a particular Command.)
     *
     * Generated from Godot docs: EditorCommandPalette.remove_command
     */
    fun removeCommand(keyName: String) {
        ObjectCalls.ptrcallWithStringArg(removeCommandBind, handle, keyName)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorCommandPalette? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorCommandPalette? =
            if (handle.address() == 0L) null else EditorCommandPalette(handle)

        private const val ADD_COMMAND_HASH = 864043298L
        private val addCommandBind by lazy {
            ObjectCalls.getMethodBind("EditorCommandPalette", "add_command", ADD_COMMAND_HASH)
        }

        private const val REMOVE_COMMAND_HASH = 83702148L
        private val removeCommandBind by lazy {
            ObjectCalls.getMethodBind("EditorCommandPalette", "remove_command", REMOVE_COMMAND_HASH)
        }
    }
}
