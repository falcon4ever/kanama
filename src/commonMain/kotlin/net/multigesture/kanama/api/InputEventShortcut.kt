package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a triggered keyboard `Shortcut`.
 *
 * Generated from Godot docs: InputEventShortcut
 */
class InputEventShortcut(handle: GodotHandle) : InputEvent(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setShortcutBind, segment, listOf(shortcut?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Shortcut` represented by this event. Its `Shortcut.matches_event` method will always return
     * `true` for this event.
     *
     * Generated from Godot docs: InputEventShortcut.get_shortcut
     */
    fun getShortcut(): Shortcut? {
        checkOpen()
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventShortcut? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): InputEventShortcut? =
            if (handle.address() == 0L) null else InputEventShortcut(GodotHandle(handle))

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
