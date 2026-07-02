package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container used to provide scrollbars to a child control when needed.
 *
 * Generated from Godot docs: ScrollContainer
 */
open class ScrollContainer(handle: MemorySegment) : Container(handle) {
    var followFocus: Boolean
        @JvmName("followFocusProperty")
        get() = isFollowingFocus()
        @JvmName("setFollowFocusProperty")
        set(value) = setFollowFocus(value)

    var drawFocusBorder: Boolean
        @JvmName("drawFocusBorderProperty")
        get() = getDrawFocusBorder()
        @JvmName("setDrawFocusBorderProperty")
        set(value) = setDrawFocusBorder(value)

    var scrollHorizontal: Int
        @JvmName("scrollHorizontalProperty")
        get() = getHScroll()
        @JvmName("setScrollHorizontalProperty")
        set(value) = setHScroll(value)

    var scrollVertical: Int
        @JvmName("scrollVerticalProperty")
        get() = getVScroll()
        @JvmName("setScrollVerticalProperty")
        set(value) = setVScroll(value)

    var scrollHorizontalCustomStep: Double
        @JvmName("scrollHorizontalCustomStepProperty")
        get() = getHorizontalCustomStep()
        @JvmName("setScrollHorizontalCustomStepProperty")
        set(value) = setHorizontalCustomStep(value)

    var scrollVerticalCustomStep: Double
        @JvmName("scrollVerticalCustomStepProperty")
        get() = getVerticalCustomStep()
        @JvmName("setScrollVerticalCustomStepProperty")
        set(value) = setVerticalCustomStep(value)

    var horizontalScrollMode: Long
        @JvmName("horizontalScrollModeProperty")
        get() = getHorizontalScrollMode()
        @JvmName("setHorizontalScrollModeProperty")
        set(value) = setHorizontalScrollMode(value)

    var verticalScrollMode: Long
        @JvmName("verticalScrollModeProperty")
        get() = getVerticalScrollMode()
        @JvmName("setVerticalScrollModeProperty")
        set(value) = setVerticalScrollMode(value)

    var scrollHorizontalByDefault: Boolean
        @JvmName("scrollHorizontalByDefaultProperty")
        get() = isScrollHorizontalByDefault()
        @JvmName("setScrollHorizontalByDefaultProperty")
        set(value) = setScrollHorizontalByDefault(value)

    var scrollDeadzone: Int
        @JvmName("scrollDeadzoneProperty")
        get() = getDeadzone()
        @JvmName("setScrollDeadzoneProperty")
        set(value) = setDeadzone(value)

    var scrollHintMode: Long
        @JvmName("scrollHintModeProperty")
        get() = getScrollHintMode()
        @JvmName("setScrollHintModeProperty")
        set(value) = setScrollHintMode(value)

    var tileScrollHint: Boolean
        @JvmName("tileScrollHintProperty")
        get() = isScrollHintTiled()
        @JvmName("setTileScrollHintProperty")
        set(value) = setTileScrollHint(value)

    fun setHScroll(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setHScrollBind, handle, value)
    }

    fun getHScroll(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHScrollBind, handle)
    }

    fun setVScroll(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setVScrollBind, handle, value)
    }

    fun getVScroll(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVScrollBind, handle)
    }

    fun setHorizontalCustomStep(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHorizontalCustomStepBind, handle, value)
    }

    fun getHorizontalCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHorizontalCustomStepBind, handle)
    }

    fun setVerticalCustomStep(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVerticalCustomStepBind, handle, value)
    }

    fun getVerticalCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVerticalCustomStepBind, handle)
    }

    fun setHorizontalScrollMode(enable: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalScrollModeBind, handle, enable)
    }

    fun getHorizontalScrollMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalScrollModeBind, handle)
    }

    fun setVerticalScrollMode(enable: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalScrollModeBind, handle, enable)
    }

    fun getVerticalScrollMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalScrollModeBind, handle)
    }

    fun setScrollHorizontalByDefault(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollHorizontalByDefaultBind, handle, enable)
    }

    fun isScrollHorizontalByDefault(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHorizontalByDefaultBind, handle)
    }

    fun setDeadzone(deadzone: Int) {
        ObjectCalls.ptrcallWithIntArg(setDeadzoneBind, handle, deadzone)
    }

    fun getDeadzone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDeadzoneBind, handle)
    }

    fun setScrollHintMode(scrollHintMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScrollHintModeBind, handle, scrollHintMode)
    }

    fun getScrollHintMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScrollHintModeBind, handle)
    }

    fun setTileScrollHint(tileScrollHint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTileScrollHintBind, handle, tileScrollHint)
    }

    fun isScrollHintTiled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHintTiledBind, handle)
    }

    fun setFollowFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowFocusBind, handle, enabled)
    }

    fun isFollowingFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFollowingFocusBind, handle)
    }

    fun getHScrollBar(): HScrollBar? {
        return HScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHScrollBarBind, handle))
    }

    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    fun ensureControlVisible(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(ensureControlVisibleBind, handle, listOf(control.handle))
    }

    fun setDrawFocusBorder(draw: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawFocusBorderBind, handle, draw)
    }

    fun getDrawFocusBorder(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDrawFocusBorderBind, handle)
    }

    object Signals {
        const val scrollStarted: String = "scroll_started"
        const val scrollEnded: String = "scroll_ended"
    }

    companion object {
        const val SCROLL_MODE_DISABLED: Long = 0L
        const val SCROLL_MODE_AUTO: Long = 1L
        const val SCROLL_MODE_SHOW_ALWAYS: Long = 2L
        const val SCROLL_MODE_SHOW_NEVER: Long = 3L
        const val SCROLL_MODE_RESERVE: Long = 4L
        const val SCROLL_MODE_MAXIMIZE_FIRST: Long = 5L
        const val SCROLL_HINT_MODE_DISABLED: Long = 0L
        const val SCROLL_HINT_MODE_ALL: Long = 1L
        const val SCROLL_HINT_MODE_TOP_AND_LEFT: Long = 2L
        const val SCROLL_HINT_MODE_BOTTOM_AND_RIGHT: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ScrollContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScrollContainer? =
            if (handle.address() == 0L) null else ScrollContainer(handle)

        private const val SET_H_SCROLL_HASH = 1286410249L
        private val setHScrollBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_h_scroll", SET_H_SCROLL_HASH)
        }

        private const val GET_H_SCROLL_HASH = 3905245786L
        private val getHScrollBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_h_scroll", GET_H_SCROLL_HASH)
        }

        private const val SET_V_SCROLL_HASH = 1286410249L
        private val setVScrollBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_v_scroll", SET_V_SCROLL_HASH)
        }

        private const val GET_V_SCROLL_HASH = 3905245786L
        private val getVScrollBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_v_scroll", GET_V_SCROLL_HASH)
        }

        private const val SET_HORIZONTAL_CUSTOM_STEP_HASH = 373806689L
        private val setHorizontalCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_horizontal_custom_step", SET_HORIZONTAL_CUSTOM_STEP_HASH)
        }

        private const val GET_HORIZONTAL_CUSTOM_STEP_HASH = 1740695150L
        private val getHorizontalCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_horizontal_custom_step", GET_HORIZONTAL_CUSTOM_STEP_HASH)
        }

        private const val SET_VERTICAL_CUSTOM_STEP_HASH = 373806689L
        private val setVerticalCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_vertical_custom_step", SET_VERTICAL_CUSTOM_STEP_HASH)
        }

        private const val GET_VERTICAL_CUSTOM_STEP_HASH = 1740695150L
        private val getVerticalCustomStepBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_vertical_custom_step", GET_VERTICAL_CUSTOM_STEP_HASH)
        }

        private const val SET_HORIZONTAL_SCROLL_MODE_HASH = 2750506364L
        private val setHorizontalScrollModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_horizontal_scroll_mode", SET_HORIZONTAL_SCROLL_MODE_HASH)
        }

        private const val GET_HORIZONTAL_SCROLL_MODE_HASH = 3987985145L
        private val getHorizontalScrollModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_horizontal_scroll_mode", GET_HORIZONTAL_SCROLL_MODE_HASH)
        }

        private const val SET_VERTICAL_SCROLL_MODE_HASH = 2750506364L
        private val setVerticalScrollModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_vertical_scroll_mode", SET_VERTICAL_SCROLL_MODE_HASH)
        }

        private const val GET_VERTICAL_SCROLL_MODE_HASH = 3987985145L
        private val getVerticalScrollModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_vertical_scroll_mode", GET_VERTICAL_SCROLL_MODE_HASH)
        }

        private const val SET_SCROLL_HORIZONTAL_BY_DEFAULT_HASH = 2586408642L
        private val setScrollHorizontalByDefaultBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_scroll_horizontal_by_default", SET_SCROLL_HORIZONTAL_BY_DEFAULT_HASH)
        }

        private const val IS_SCROLL_HORIZONTAL_BY_DEFAULT_HASH = 36873697L
        private val isScrollHorizontalByDefaultBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "is_scroll_horizontal_by_default", IS_SCROLL_HORIZONTAL_BY_DEFAULT_HASH)
        }

        private const val SET_DEADZONE_HASH = 1286410249L
        private val setDeadzoneBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_deadzone", SET_DEADZONE_HASH)
        }

        private const val GET_DEADZONE_HASH = 3905245786L
        private val getDeadzoneBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_deadzone", GET_DEADZONE_HASH)
        }

        private const val SET_SCROLL_HINT_MODE_HASH = 578158943L
        private val setScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_scroll_hint_mode", SET_SCROLL_HINT_MODE_HASH)
        }

        private const val GET_SCROLL_HINT_MODE_HASH = 246835423L
        private val getScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_scroll_hint_mode", GET_SCROLL_HINT_MODE_HASH)
        }

        private const val SET_TILE_SCROLL_HINT_HASH = 2586408642L
        private val setTileScrollHintBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_tile_scroll_hint", SET_TILE_SCROLL_HINT_HASH)
        }

        private const val IS_SCROLL_HINT_TILED_HASH = 2240911060L
        private val isScrollHintTiledBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "is_scroll_hint_tiled", IS_SCROLL_HINT_TILED_HASH)
        }

        private const val SET_FOLLOW_FOCUS_HASH = 2586408642L
        private val setFollowFocusBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_follow_focus", SET_FOLLOW_FOCUS_HASH)
        }

        private const val IS_FOLLOWING_FOCUS_HASH = 36873697L
        private val isFollowingFocusBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "is_following_focus", IS_FOLLOWING_FOCUS_HASH)
        }

        private const val GET_H_SCROLL_BAR_HASH = 4004517983L
        private val getHScrollBarBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_h_scroll_bar", GET_H_SCROLL_BAR_HASH)
        }

        private const val GET_V_SCROLL_BAR_HASH = 2630340773L
        private val getVScrollBarBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_v_scroll_bar", GET_V_SCROLL_BAR_HASH)
        }

        private const val ENSURE_CONTROL_VISIBLE_HASH = 1496901182L
        private val ensureControlVisibleBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "ensure_control_visible", ENSURE_CONTROL_VISIBLE_HASH)
        }

        private const val SET_DRAW_FOCUS_BORDER_HASH = 2586408642L
        private val setDrawFocusBorderBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "set_draw_focus_border", SET_DRAW_FOCUS_BORDER_HASH)
        }

        private const val GET_DRAW_FOCUS_BORDER_HASH = 2240911060L
        private val getDrawFocusBorderBind by lazy {
            ObjectCalls.getMethodBind("ScrollContainer", "get_draw_focus_border", GET_DRAW_FOCUS_BORDER_HASH)
        }
    }
}
