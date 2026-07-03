package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A `StyleBox` that displays a single line of a given color and thickness.
 *
 * Generated from Godot docs: StyleBoxLine
 */
class StyleBoxLine(handle: MemorySegment) : StyleBox(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var growBegin: Double
        @JvmName("growBeginProperty")
        get() = getGrowBegin()
        @JvmName("setGrowBeginProperty")
        set(value) = setGrowBegin(value)

    var growEnd: Double
        @JvmName("growEndProperty")
        get() = getGrowEnd()
        @JvmName("setGrowEndProperty")
        set(value) = setGrowEnd(value)

    var thickness: Int
        @JvmName("thicknessProperty")
        get() = getThickness()
        @JvmName("setThicknessProperty")
        set(value) = setThickness(value)

    var vertical: Boolean
        @JvmName("verticalProperty")
        get() = isVertical()
        @JvmName("setVerticalProperty")
        set(value) = setVertical(value)

    /**
     * The line's color.
     *
     * Generated from Godot docs: StyleBoxLine.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * The line's color.
     *
     * Generated from Godot docs: StyleBoxLine.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    /**
     * The line's thickness in pixels.
     *
     * Generated from Godot docs: StyleBoxLine.set_thickness
     */
    fun setThickness(thickness: Int) {
        ObjectCalls.ptrcallWithIntArg(setThicknessBind, handle, thickness)
    }

    /**
     * The line's thickness in pixels.
     *
     * Generated from Godot docs: StyleBoxLine.get_thickness
     */
    fun getThickness(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getThicknessBind, handle)
    }

    /**
     * The number of pixels the line will extend before the `StyleBoxLine`'s bounds. If set to a
     * negative value, the line will begin inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.set_grow_begin
     */
    fun setGrowBegin(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGrowBeginBind, handle, offset)
    }

    /**
     * The number of pixels the line will extend before the `StyleBoxLine`'s bounds. If set to a
     * negative value, the line will begin inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.get_grow_begin
     */
    fun getGrowBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGrowBeginBind, handle)
    }

    /**
     * The number of pixels the line will extend past the `StyleBoxLine`'s bounds. If set to a negative
     * value, the line will end inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.set_grow_end
     */
    fun setGrowEnd(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGrowEndBind, handle, offset)
    }

    /**
     * The number of pixels the line will extend past the `StyleBoxLine`'s bounds. If set to a negative
     * value, the line will end inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.get_grow_end
     */
    fun getGrowEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGrowEndBind, handle)
    }

    /**
     * If `true`, the line will be vertical. If `false`, the line will be horizontal.
     *
     * Generated from Godot docs: StyleBoxLine.set_vertical
     */
    fun setVertical(vertical: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVerticalBind, handle, vertical)
    }

    /**
     * If `true`, the line will be vertical. If `false`, the line will be horizontal.
     *
     * Generated from Godot docs: StyleBoxLine.is_vertical
     */
    fun isVertical(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVerticalBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StyleBoxLine? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StyleBoxLine? =
            if (handle.address() == 0L) null else StyleBoxLine(handle)

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "get_color", GET_COLOR_HASH)
        }

        private const val SET_THICKNESS_HASH = 1286410249L
        private val setThicknessBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "set_thickness", SET_THICKNESS_HASH)
        }

        private const val GET_THICKNESS_HASH = 3905245786L
        private val getThicknessBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "get_thickness", GET_THICKNESS_HASH)
        }

        private const val SET_GROW_BEGIN_HASH = 373806689L
        private val setGrowBeginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "set_grow_begin", SET_GROW_BEGIN_HASH)
        }

        private const val GET_GROW_BEGIN_HASH = 1740695150L
        private val getGrowBeginBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "get_grow_begin", GET_GROW_BEGIN_HASH)
        }

        private const val SET_GROW_END_HASH = 373806689L
        private val setGrowEndBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "set_grow_end", SET_GROW_END_HASH)
        }

        private const val GET_GROW_END_HASH = 1740695150L
        private val getGrowEndBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "get_grow_end", GET_GROW_END_HASH)
        }

        private const val SET_VERTICAL_HASH = 2586408642L
        private val setVerticalBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "set_vertical", SET_VERTICAL_HASH)
        }

        private const val IS_VERTICAL_HASH = 36873697L
        private val isVerticalBind by lazy {
            ObjectCalls.getMethodBind("StyleBoxLine", "is_vertical", IS_VERTICAL_HASH)
        }
    }
}
