package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a triggered keyboard `Shortcut`.
 *
 * Generated from Godot docs: InputEventShortcut
 */
class InputEventShortcut(handle: MemorySegment) : InputEvent(handle) {
    var shortcut: Shortcut?
        @JvmName("shortcutProperty")
        get() = getShortcut()
        @JvmName("setShortcutProperty")
        set(value) = setShortcut(value)

    /**
     * The `Shortcut` represented by this event. Its `Shortcut.matches_event` method will always return
     * `true` for this event.
     *
     * Generated from Godot docs: InputEventShortcut.set_shortcut
     */
    fun setShortcut(shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithObjectArgs(setShortcutBind, handle, listOf(shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Shortcut` represented by this event. Its `Shortcut.matches_event` method will always return
     * `true` for this event.
     *
     * Generated from Godot docs: InputEventShortcut.get_shortcut
     */
    fun getShortcut(): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutBind, handle))
    }

    companion object {
        @JvmStatic
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
