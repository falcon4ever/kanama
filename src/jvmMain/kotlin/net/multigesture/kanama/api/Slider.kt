package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Abstract base class for sliders.
 *
 * Generated from Godot docs: Slider
 */
open class Slider(handle: GodotHandle) : Range(handle) {
    var ticks: Int
        @JvmName("ticksProperty")
        get() = getTicks()
        @JvmName("setTicksProperty")
        set(value) = setTicks(value)

    var ticksOnBorders: Boolean
        @JvmName("ticksOnBordersProperty")
        get() = getTicksOnBorders()
        @JvmName("setTicksOnBordersProperty")
        set(value) = setTicksOnBorders(value)

    var ticksPosition: Long
        @JvmName("ticksPositionProperty")
        get() = getTicksPosition()
        @JvmName("setTicksPositionProperty")
        set(value) = setTicksPosition(value)

    var editable: Boolean
        @JvmName("editableProperty")
        get() = isEditable()
        @JvmName("setEditableProperty")
        set(value) = setEditable(value)

    var scrollable: Boolean
        @JvmName("scrollableProperty")
        get() = isScrollable()
        @JvmName("setScrollableProperty")
        set(value) = setScrollable(value)

    /**
     * Number of ticks displayed on the slider, including border ticks. Ticks are uniformly-distributed
     * value markers.
     *
     * Generated from Godot docs: Slider.set_ticks
     */
    fun setTicks(count: Int) { ObjectCalls.ptrcallWithIntArg(setTicksBind, segment, count) }
    /**
     * Number of ticks displayed on the slider, including border ticks. Ticks are uniformly-distributed
     * value markers.
     *
     * Generated from Godot docs: Slider.get_ticks
     */
    fun getTicks(): Int = ObjectCalls.ptrcallNoArgsRetInt(getTicksBind, segment)
    /**
     * If `true`, the slider will display ticks for minimum and maximum values.
     *
     * Generated from Godot docs: Slider.get_ticks_on_borders
     */
    fun getTicksOnBorders(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(getTicksOnBordersBind, segment)
    /**
     * If `true`, the slider will display ticks for minimum and maximum values.
     *
     * Generated from Godot docs: Slider.set_ticks_on_borders
     */
    fun setTicksOnBorders(ticksOnBorder: Boolean) { ObjectCalls.ptrcallWithBoolArg(setTicksOnBordersBind, segment, ticksOnBorder) }
    /**
     * Sets the position of the ticks. See `TickPosition` for details.
     *
     * Generated from Godot docs: Slider.get_ticks_position
     */
    fun getTicksPosition(): Long = ObjectCalls.ptrcallNoArgsRetLong(getTicksPositionBind, segment)
    /**
     * Sets the position of the ticks. See `TickPosition` for details.
     *
     * Generated from Godot docs: Slider.set_ticks_position
     */
    fun setTicksPosition(ticksOnBorder: Long) { ObjectCalls.ptrcallWithLongArg(setTicksPositionBind, segment, ticksOnBorder) }
    /**
     * If `true`, the slider can be interacted with. If `false`, the value can be changed only by code.
     *
     * Generated from Godot docs: Slider.set_editable
     */
    fun setEditable(editable: Boolean) { ObjectCalls.ptrcallWithBoolArg(setEditableBind, segment, editable) }
    /**
     * If `true`, the slider can be interacted with. If `false`, the value can be changed only by code.
     *
     * Generated from Godot docs: Slider.is_editable
     */
    fun isEditable(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, segment)
    /**
     * If `true`, the value can be changed using the mouse wheel.
     *
     * Generated from Godot docs: Slider.set_scrollable
     */
    fun setScrollable(scrollable: Boolean) { ObjectCalls.ptrcallWithBoolArg(setScrollableBind, segment, scrollable) }
    /**
     * If `true`, the value can be changed using the mouse wheel.
     *
     * Generated from Godot docs: Slider.is_scrollable
     */
    fun isScrollable(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isScrollableBind, segment)

    companion object {
        private const val INT_VOID_HASH = 1286410249L
        private const val NOARGS_INT_HASH = 3905245786L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val GET_TICKS_POSITION_HASH = 3567635531L
        private const val SET_TICKS_POSITION_HASH = 2952822224L

        private val setTicksBind by lazy { ObjectCalls.getMethodBind("Slider", "set_ticks", INT_VOID_HASH) }
        private val getTicksBind by lazy { ObjectCalls.getMethodBind("Slider", "get_ticks", NOARGS_INT_HASH) }
        private val getTicksOnBordersBind by lazy { ObjectCalls.getMethodBind("Slider", "get_ticks_on_borders", NOARGS_BOOL_HASH) }
        private val setTicksOnBordersBind by lazy { ObjectCalls.getMethodBind("Slider", "set_ticks_on_borders", BOOL_VOID_HASH) }
        private val getTicksPositionBind by lazy { ObjectCalls.getMethodBind("Slider", "get_ticks_position", GET_TICKS_POSITION_HASH) }
        private val setTicksPositionBind by lazy { ObjectCalls.getMethodBind("Slider", "set_ticks_position", SET_TICKS_POSITION_HASH) }
        private val setEditableBind by lazy { ObjectCalls.getMethodBind("Slider", "set_editable", BOOL_VOID_HASH) }
        private val isEditableBind by lazy { ObjectCalls.getMethodBind("Slider", "is_editable", NOARGS_BOOL_HASH) }
        private val setScrollableBind by lazy { ObjectCalls.getMethodBind("Slider", "set_scrollable", BOOL_VOID_HASH) }
        private val isScrollableBind by lazy { ObjectCalls.getMethodBind("Slider", "is_scrollable", NOARGS_BOOL_HASH) }
    }
}
