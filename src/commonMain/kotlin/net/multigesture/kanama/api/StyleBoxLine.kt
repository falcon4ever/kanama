package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A `StyleBox` that displays a single line of a given color and thickness.
 *
 * Generated from Godot docs: StyleBoxLine
 */
class StyleBoxLine(handle: GodotHandle) : StyleBox(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    /**
     * The line's color.
     *
     * Generated from Godot docs: StyleBoxLine.get_color
     */
    fun getColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    /**
     * The line's thickness in pixels.
     *
     * Generated from Godot docs: StyleBoxLine.set_thickness
     */
    fun setThickness(thickness: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setThicknessBind, segment, thickness)
    }

    /**
     * The line's thickness in pixels.
     *
     * Generated from Godot docs: StyleBoxLine.get_thickness
     */
    fun getThickness(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getThicknessBind, segment)
    }

    /**
     * The number of pixels the line will extend before the `StyleBoxLine`'s bounds. If set to a
     * negative value, the line will begin inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.set_grow_begin
     */
    fun setGrowBegin(offset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGrowBeginBind, segment, offset)
    }

    /**
     * The number of pixels the line will extend before the `StyleBoxLine`'s bounds. If set to a
     * negative value, the line will begin inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.get_grow_begin
     */
    fun getGrowBegin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGrowBeginBind, segment)
    }

    /**
     * The number of pixels the line will extend past the `StyleBoxLine`'s bounds. If set to a negative
     * value, the line will end inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.set_grow_end
     */
    fun setGrowEnd(offset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGrowEndBind, segment, offset)
    }

    /**
     * The number of pixels the line will extend past the `StyleBoxLine`'s bounds. If set to a negative
     * value, the line will end inside the `StyleBoxLine`'s bounds.
     *
     * Generated from Godot docs: StyleBoxLine.get_grow_end
     */
    fun getGrowEnd(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGrowEndBind, segment)
    }

    /**
     * If `true`, the line will be vertical. If `false`, the line will be horizontal.
     *
     * Generated from Godot docs: StyleBoxLine.set_vertical
     */
    fun setVertical(vertical: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVerticalBind, segment, vertical)
    }

    /**
     * If `true`, the line will be vertical. If `false`, the line will be horizontal.
     *
     * Generated from Godot docs: StyleBoxLine.is_vertical
     */
    fun isVertical(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVerticalBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StyleBoxLine? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StyleBoxLine? =
            if (handle.address() == 0L) null else RefCounted.owned(StyleBoxLine(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StyleBoxLine? =
            if (handle.address() == 0L) null else StyleBoxLine(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "set_color", SET_COLOR_HASH)

        private const val GET_COLOR_HASH = 3444240500L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "get_color", GET_COLOR_HASH)

        private const val SET_THICKNESS_HASH = 1286410249L
        @JvmField
        val setThicknessBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "set_thickness", SET_THICKNESS_HASH)

        private const val GET_THICKNESS_HASH = 3905245786L
        @JvmField
        val getThicknessBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "get_thickness", GET_THICKNESS_HASH)

        private const val SET_GROW_BEGIN_HASH = 373806689L
        @JvmField
        val setGrowBeginBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "set_grow_begin", SET_GROW_BEGIN_HASH)

        private const val GET_GROW_BEGIN_HASH = 1740695150L
        @JvmField
        val getGrowBeginBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "get_grow_begin", GET_GROW_BEGIN_HASH)

        private const val SET_GROW_END_HASH = 373806689L
        @JvmField
        val setGrowEndBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "set_grow_end", SET_GROW_END_HASH)

        private const val GET_GROW_END_HASH = 1740695150L
        @JvmField
        val getGrowEndBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "get_grow_end", GET_GROW_END_HASH)

        private const val SET_VERTICAL_HASH = 2586408642L
        @JvmField
        val setVerticalBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "set_vertical", SET_VERTICAL_HASH)

        private const val IS_VERTICAL_HASH = 36873697L
        @JvmField
        val isVerticalBind =
            ObjectCalls.getMethodBind("StyleBoxLine", "is_vertical", IS_VERTICAL_HASH)
    }
}
