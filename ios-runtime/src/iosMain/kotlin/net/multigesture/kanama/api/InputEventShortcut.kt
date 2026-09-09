package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: InputEventShortcut
 */
class InputEventShortcut(handle: MemorySegment) : InputEvent(handle) {
    var shortcut: Shortcut?
        @JvmName("shortcutProperty")
        get() = getShortcut()
        @JvmName("setShortcutProperty")
        set(value) = setShortcut(value)

    fun setShortcut(shortcut: Shortcut?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShortcutBind, handle, listOf(shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShortcut(): Shortcut? {
        checkOpen()
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutBind, handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): InputEventShortcut? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventShortcut? =
            if (handle.address() == 0L) null else InputEventShortcut(handle)

        private const val SET_SHORTCUT_HASH = 857163497L
        private val setShortcutBind by lazy {
            ObjectCalls.getMethodBind("InputEventShortcut", "set_shortcut", SET_SHORTCUT_HASH)
        }

        private const val GET_SHORTCUT_HASH = 3766804753L
        private val getShortcutBind by lazy {
            ObjectCalls.getMethodBind("InputEventShortcut", "get_shortcut", GET_SHORTCUT_HASH)
        }
    }
}
