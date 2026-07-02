package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2

/**
 * A rectangular region of 2D space that detects whether it is visible on screen.
 *
 * Generated from Godot docs: VisibleOnScreenNotifier2D
 */
open class VisibleOnScreenNotifier2D(handle: MemorySegment) : Node2D(handle) {
    var rect: Rect2
        @JvmName("rectProperty")
        get() = getRect()
        @JvmName("setRectProperty")
        set(value) = setRect(value)

    var showRect: Boolean
        @JvmName("showRectProperty")
        get() = isShowingRect()
        @JvmName("setShowRectProperty")
        set(value) = setShowRect(value)

    fun setRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRectBind, handle, rect)
    }

    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    fun setShowRect(showRect: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowRectBind, handle, showRect)
    }

    fun isShowingRect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShowingRectBind, handle)
    }

    fun isOnScreen(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnScreenBind, handle)
    }

    object Signals {
        const val screenEntered: String = "screen_entered"
        const val screenExited: String = "screen_exited"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisibleOnScreenNotifier2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisibleOnScreenNotifier2D? =
            if (handle.address() == 0L) null else VisibleOnScreenNotifier2D(handle)

        private const val SET_RECT_HASH = 2046264180L
        private val setRectBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier2D", "set_rect", SET_RECT_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier2D", "get_rect", GET_RECT_HASH)
        }

        private const val SET_SHOW_RECT_HASH = 2586408642L
        private val setShowRectBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier2D", "set_show_rect", SET_SHOW_RECT_HASH)
        }

        private const val IS_SHOWING_RECT_HASH = 36873697L
        private val isShowingRectBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier2D", "is_showing_rect", IS_SHOWING_RECT_HASH)
        }

        private const val IS_ON_SCREEN_HASH = 36873697L
        private val isOnScreenBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenNotifier2D", "is_on_screen", IS_ON_SCREEN_HASH)
        }
    }
}
