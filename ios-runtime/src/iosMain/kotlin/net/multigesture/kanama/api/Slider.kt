package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Slider
 */
open class Slider(handle: MemorySegment) : Range(handle) {
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

    var ticksPosition: Long
        @JvmName("ticksPositionProperty")
        get() = getTicksPosition()
        @JvmName("setTicksPositionProperty")
        set(value) = setTicksPosition(value)

    fun setTicks(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setTicksBind, handle, count)
    }

    fun getTicks(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTicksBind, handle)
    }

    fun getTicksOnBorders(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getTicksOnBordersBind, handle)
    }

    fun setTicksOnBorders(ticksOnBorder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTicksOnBordersBind, handle, ticksOnBorder)
    }

    fun getTicksPosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTicksPositionBind, handle)
    }

    fun setTicksPosition(ticksOnBorder: Long) {
        ObjectCalls.ptrcallWithLongArg(setTicksPositionBind, handle, ticksOnBorder)
    }

    fun setEditable(editable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, handle, editable)
    }

    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, handle)
    }

    fun setScrollable(scrollable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollableBind, handle, scrollable)
    }

    fun isScrollable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollableBind, handle)
    }

    object Signals {
        const val dragStarted: String = "drag_started"
        const val dragEnded: String = "drag_ended"
    }

    companion object {
        const val TICK_POSITION_BOTTOM_RIGHT: Long = 0L
        const val TICK_POSITION_TOP_LEFT: Long = 1L
        const val TICK_POSITION_BOTH: Long = 2L
        const val TICK_POSITION_CENTER: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Slider? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Slider? =
            if (handle.address() == 0L) null else Slider(handle)

        private const val SET_TICKS_HASH = 1286410249L
        private val setTicksBind by lazy {
            ObjectCalls.getMethodBind("Slider", "set_ticks", SET_TICKS_HASH)
        }

        private const val GET_TICKS_HASH = 3905245786L
        private val getTicksBind by lazy {
            ObjectCalls.getMethodBind("Slider", "get_ticks", GET_TICKS_HASH)
        }

        private const val GET_TICKS_ON_BORDERS_HASH = 36873697L
        private val getTicksOnBordersBind by lazy {
            ObjectCalls.getMethodBind("Slider", "get_ticks_on_borders", GET_TICKS_ON_BORDERS_HASH)
        }

        private const val SET_TICKS_ON_BORDERS_HASH = 2586408642L
        private val setTicksOnBordersBind by lazy {
            ObjectCalls.getMethodBind("Slider", "set_ticks_on_borders", SET_TICKS_ON_BORDERS_HASH)
        }

        private const val GET_TICKS_POSITION_HASH = 3567635531L
        private val getTicksPositionBind by lazy {
            ObjectCalls.getMethodBind("Slider", "get_ticks_position", GET_TICKS_POSITION_HASH)
        }

        private const val SET_TICKS_POSITION_HASH = 2952822224L
        private val setTicksPositionBind by lazy {
            ObjectCalls.getMethodBind("Slider", "set_ticks_position", SET_TICKS_POSITION_HASH)
        }

        private const val SET_EDITABLE_HASH = 2586408642L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("Slider", "set_editable", SET_EDITABLE_HASH)
        }

        private const val IS_EDITABLE_HASH = 36873697L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("Slider", "is_editable", IS_EDITABLE_HASH)
        }

        private const val SET_SCROLLABLE_HASH = 2586408642L
        private val setScrollableBind by lazy {
            ObjectCalls.getMethodBind("Slider", "set_scrollable", SET_SCROLLABLE_HASH)
        }

        private const val IS_SCROLLABLE_HASH = 36873697L
        private val isScrollableBind by lazy {
            ObjectCalls.getMethodBind("Slider", "is_scrollable", IS_SCROLLABLE_HASH)
        }
    }
}
