package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for sliders.
 *
 * Generated from Godot docs: Slider
 */
open class Slider(handle: GodotHandle) : Range(handle) {
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

    var tickCount: Int
        @JvmName("tickCountProperty")
        get() = getTicks()
        @JvmName("setTickCountProperty")
        set(value) = setTicks(value)

    var ticksOnBorders: Boolean
        @JvmName("ticksOnBordersProperty")
        get() = getTicksOnBorders()
        @JvmName("setTicksOnBordersProperty")
        set(value) = setTicksOnBorders(value)

    var ticksPosition: Slider.TickPosition
        @JvmName("ticksPositionProperty")
        get() = getTicksPosition()
        @JvmName("setTicksPositionProperty")
        set(value) = setTicksPosition(value)

    /**
     * Number of ticks displayed on the slider, including border ticks. Ticks are uniformly-distributed
     * value markers.
     *
     * Generated from Godot docs: Slider.set_ticks
     */
    fun setTicks(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTicksBind, segment, count)
    }

    /**
     * Number of ticks displayed on the slider, including border ticks. Ticks are uniformly-distributed
     * value markers.
     *
     * Generated from Godot docs: Slider.get_ticks
     */
    fun getTicks(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTicksBind, segment)
    }

    /**
     * If `true`, the slider will display ticks for minimum and maximum values.
     *
     * Generated from Godot docs: Slider.get_ticks_on_borders
     */
    fun getTicksOnBorders(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getTicksOnBordersBind, segment)
    }

    /**
     * If `true`, the slider will display ticks for minimum and maximum values.
     *
     * Generated from Godot docs: Slider.set_ticks_on_borders
     */
    fun setTicksOnBorders(ticksOnBorder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTicksOnBordersBind, segment, ticksOnBorder)
    }

    /**
     * Sets the position of the ticks. See `TickPosition` for details.
     *
     * Generated from Godot docs: Slider.get_ticks_position
     */
    fun getTicksPosition(): Slider.TickPosition {
        return Slider.TickPosition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTicksPositionBind, segment))
    }

    /**
     * Sets the position of the ticks. See `TickPosition` for details.
     *
     * Generated from Godot docs: Slider.set_ticks_position
     */
    fun setTicksPosition(ticksOnBorder: Slider.TickPosition) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTicksPositionBind, segment, ticksOnBorder.value)
    }

    /**
     * If `true`, the slider can be interacted with. If `false`, the value can be changed only by code.
     *
     * Generated from Godot docs: Slider.set_editable
     */
    fun setEditable(editable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditableBind, segment, editable)
    }

    /**
     * If `true`, the slider can be interacted with. If `false`, the value can be changed only by code.
     *
     * Generated from Godot docs: Slider.is_editable
     */
    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditableBind, segment)
    }

    /**
     * If `true`, the value can be changed using the mouse wheel.
     *
     * Generated from Godot docs: Slider.set_scrollable
     */
    fun setScrollable(scrollable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setScrollableBind, segment, scrollable)
    }

    /**
     * If `true`, the value can be changed using the mouse wheel.
     *
     * Generated from Godot docs: Slider.is_scrollable
     */
    fun isScrollable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isScrollableBind, segment)
    }

    /** Signal `drag_started()`; see [TypedSignal]. */
    val dragStarted: Signal0
        @JvmName("dragStartedTypedSignal")
        get() = Signal0(this, "drag_started")

    /** Signal `drag_ended(value_changed: bool)`; see [TypedSignal]. */
    val dragEnded: Signal1<Boolean>
        @JvmName("dragEndedTypedSignal")
        get() = Signal1(this, "drag_ended", SignalArgType.BOOLEAN)

    object Signals {
        const val dragStarted: String = "drag_started"
        const val dragEnded: String = "drag_ended"
    }

    /**
     * Godot's `Slider.TickPosition` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Slider.TickPosition.<NAME>`).
     *
     * Generated from Godot docs: Slider.TickPosition
     */
    @JvmInline
    value class TickPosition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Places the ticks at the bottom of the `HSlider`, or right of the `VSlider`.
             *
             * Generated from Godot docs: Slider.TICK_POSITION_BOTTOM_RIGHT
             */
            val BOTTOM_RIGHT: TickPosition get() = TickPosition(0L)
            /**
             * Places the ticks at the top of the `HSlider`, or left of the `VSlider`.
             *
             * Generated from Godot docs: Slider.TICK_POSITION_TOP_LEFT
             */
            val TOP_LEFT: TickPosition get() = TickPosition(1L)
            /**
             * Places the ticks at the both sides of the slider.
             *
             * Generated from Godot docs: Slider.TICK_POSITION_BOTH
             */
            val BOTH: TickPosition get() = TickPosition(2L)
            /**
             * Places the ticks at the center of the slider.
             *
             * Generated from Godot docs: Slider.TICK_POSITION_CENTER
             */
            val CENTER: TickPosition get() = TickPosition(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Slider? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Slider? =
            if (handle.address() == 0L) null else Slider(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TICKS_HASH = 1286410249L
        @JvmField
        val setTicksBind =
            ObjectCalls.getMethodBind("Slider", "set_ticks", SET_TICKS_HASH)

        private const val GET_TICKS_HASH = 3905245786L
        @JvmField
        val getTicksBind =
            ObjectCalls.getMethodBind("Slider", "get_ticks", GET_TICKS_HASH)

        private const val GET_TICKS_ON_BORDERS_HASH = 36873697L
        @JvmField
        val getTicksOnBordersBind =
            ObjectCalls.getMethodBind("Slider", "get_ticks_on_borders", GET_TICKS_ON_BORDERS_HASH)

        private const val SET_TICKS_ON_BORDERS_HASH = 2586408642L
        @JvmField
        val setTicksOnBordersBind =
            ObjectCalls.getMethodBind("Slider", "set_ticks_on_borders", SET_TICKS_ON_BORDERS_HASH)

        private const val GET_TICKS_POSITION_HASH = 3567635531L
        @JvmField
        val getTicksPositionBind =
            ObjectCalls.getMethodBind("Slider", "get_ticks_position", GET_TICKS_POSITION_HASH)

        private const val SET_TICKS_POSITION_HASH = 2952822224L
        @JvmField
        val setTicksPositionBind =
            ObjectCalls.getMethodBind("Slider", "set_ticks_position", SET_TICKS_POSITION_HASH)

        private const val SET_EDITABLE_HASH = 2586408642L
        @JvmField
        val setEditableBind =
            ObjectCalls.getMethodBind("Slider", "set_editable", SET_EDITABLE_HASH)

        private const val IS_EDITABLE_HASH = 36873697L
        @JvmField
        val isEditableBind =
            ObjectCalls.getMethodBind("Slider", "is_editable", IS_EDITABLE_HASH)

        private const val SET_SCROLLABLE_HASH = 2586408642L
        @JvmField
        val setScrollableBind =
            ObjectCalls.getMethodBind("Slider", "set_scrollable", SET_SCROLLABLE_HASH)

        private const val IS_SCROLLABLE_HASH = 36873697L
        @JvmField
        val isScrollableBind =
            ObjectCalls.getMethodBind("Slider", "is_scrollable", IS_SCROLLABLE_HASH)
    }
}
