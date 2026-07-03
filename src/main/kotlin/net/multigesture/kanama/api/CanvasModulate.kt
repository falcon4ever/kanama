package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A node that applies a color tint to a canvas.
 *
 * Generated from Godot docs: CanvasModulate
 */
class CanvasModulate(handle: MemorySegment) : Node2D(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    /**
     * The tint color to apply.
     *
     * Generated from Godot docs: CanvasModulate.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * The tint color to apply.
     *
     * Generated from Godot docs: CanvasModulate.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasModulate? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasModulate? =
            if (handle.address() == 0L) null else CanvasModulate(handle)

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("CanvasModulate", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("CanvasModulate", "get_color", GET_COLOR_HASH)
        }
    }
}
