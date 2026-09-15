package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for `Viewport`-based input events.
 *
 * Generated from Godot docs: InputEventFromWindow
 */
open class InputEventFromWindow(handle: GodotHandle) : InputEvent(handle) {
    var windowId: Long
        @JvmName("windowIdProperty")
        get() = getWindowId()
        @JvmName("setWindowIdProperty")
        set(value) = setWindowId(value)

    /**
     * The ID of a `Window` that received this event.
     *
     * Generated from Godot docs: InputEventFromWindow.set_window_id
     */
    fun setWindowId(id: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setWindowIdBind, segment, id)
    }

    /**
     * The ID of a `Window` that received this event.
     *
     * Generated from Godot docs: InputEventFromWindow.get_window_id
     */
    fun getWindowId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getWindowIdBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventFromWindow? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): InputEventFromWindow? =
            if (handle.address() == 0L) null else InputEventFromWindow(GodotHandle(handle))

        private const val SET_WINDOW_ID_HASH = 1286410249L
        private val setWindowIdBind by lazy {
            ObjectCalls.getMethodBind("InputEventFromWindow", "set_window_id", SET_WINDOW_ID_HASH)
        }

        private const val GET_WINDOW_ID_HASH = 3905245786L
        private val getWindowIdBind by lazy {
            ObjectCalls.getMethodBind("InputEventFromWindow", "get_window_id", GET_WINDOW_ID_HASH)
        }
    }
}
