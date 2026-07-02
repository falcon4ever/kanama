package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's command palette.
 *
 * Generated from Godot docs: EditorCommandPalette
 */
class EditorCommandPalette(handle: MemorySegment) : ConfirmationDialog(handle) {
    fun addCommand(commandName: String, keyName: String, bindedCallable: GodotCallable, shortcutText: String = "None") {
        ObjectCalls.ptrcallWithTwoStringCallableStringArgs(addCommandBind, handle, commandName, keyName, bindedCallable.target.handle, bindedCallable.method, shortcutText)
    }

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
