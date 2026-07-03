package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for `Viewport`-based input events.
 *
 * Generated from Godot docs: InputEventFromWindow
 */
open class InputEventFromWindow(handle: MemorySegment) : InputEvent(handle) {
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
        ObjectCalls.ptrcallWithLongArg(setWindowIdBind, handle, id)
    }

    /**
     * The ID of a `Window` that received this event.
     *
     * Generated from Godot docs: InputEventFromWindow.get_window_id
     */
    fun getWindowId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getWindowIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventFromWindow? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventFromWindow? =
            if (handle.address() == 0L) null else InputEventFromWindow(handle)

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
