package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A control that displays a solid color rectangle.
 *
 * Generated from Godot docs: ColorRect
 */
class ColorRect(handle: MemorySegment) : Control(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    /**
     * The fill color of the rectangle.
     *
     * Generated from Godot docs: ColorRect.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * The fill color of the rectangle.
     *
     * Generated from Godot docs: ColorRect.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ColorRect? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ColorRect? =
            if (handle.address() == 0L) null else ColorRect(handle)

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("ColorRect", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("ColorRect", "get_color", GET_COLOR_HASH)
        }
    }
}
