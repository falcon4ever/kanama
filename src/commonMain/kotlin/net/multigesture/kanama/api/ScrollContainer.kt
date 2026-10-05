package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container used to provide scrollbars to a child control when needed.
 *
 * Generated from Godot docs: ScrollContainer
 */
open class ScrollContainer(handle: GodotHandle) : Container(handle) {
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

    var horizontalScrollMode: ScrollContainer.ScrollMode
        @JvmName("horizontalScrollModeProperty")
        get() = getHorizontalScrollMode()
        @JvmName("setHorizontalScrollModeProperty")
        set(value) = setHorizontalScrollMode(value)

    var verticalScrollMode: ScrollContainer.ScrollMode
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

    var scrollHintMode: ScrollContainer.ScrollHintMode
        @JvmName("scrollHintModeProperty")
        get() = getScrollHintMode()
        @JvmName("setScrollHintModeProperty")
        set(value) = setScrollHintMode(value)

    var tileScrollHint: Boolean
        @JvmName("tileScrollHintProperty")
        get() = isScrollHintTiled()
        @JvmName("setTileScrollHintProperty")
        set(value) = setTileScrollHint(value)

    /**
     * The current horizontal scroll value. Note: If you are setting this value in the `Node._ready`
     * function or earlier, it needs to be wrapped with `Object.set_deferred`, since scroll bar's
     * `Range.max_value` is not initialized yet.
     *
     * Generated from Godot docs: ScrollContainer.set_h_scroll
     */
    fun setHScroll(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setHScrollBind, segment, value)
    }

    /**
     * The current horizontal scroll value. Note: If you are setting this value in the `Node._ready`
     * function or earlier, it needs to be wrapped with `Object.set_deferred`, since scroll bar's
     * `Range.max_value` is not initialized yet.
     *
     * Generated from Godot docs: ScrollContainer.get_h_scroll
     */
    fun getHScroll(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHScrollBind, segment)
    }

    /**
     * The current vertical scroll value. Note: Setting it early needs to be deferred, just like in
     * `scroll_horizontal`.
     *
     * Generated from Godot docs: ScrollContainer.set_v_scroll
     */
    fun setVScroll(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setVScrollBind, segment, value)
    }

    /**
     * The current vertical scroll value. Note: Setting it early needs to be deferred, just like in
     * `scroll_horizontal`.
     *
     * Generated from Godot docs: ScrollContainer.get_v_scroll
     */
    fun getVScroll(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVScrollBind, segment)
    }

    /**
     * Overrides the `ScrollBar.custom_step` used when clicking the internal scroll bar's horizontal
     * increment and decrement buttons or when using arrow keys when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollContainer.set_horizontal_custom_step
     */
    fun setHorizontalCustomStep(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHorizontalCustomStepBind, segment, value)
    }

    /**
     * Overrides the `ScrollBar.custom_step` used when clicking the internal scroll bar's horizontal
     * increment and decrement buttons or when using arrow keys when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollContainer.get_horizontal_custom_step
     */
    fun getHorizontalCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHorizontalCustomStepBind, segment)
    }

    /**
     * Overrides the `ScrollBar.custom_step` used when clicking the internal scroll bar's vertical
     * increment and decrement buttons or when using arrow keys when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollContainer.set_vertical_custom_step
     */
    fun setVerticalCustomStep(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVerticalCustomStepBind, segment, value)
    }

    /**
     * Overrides the `ScrollBar.custom_step` used when clicking the internal scroll bar's vertical
     * increment and decrement buttons or when using arrow keys when the `ScrollBar` is focused.
     *
     * Generated from Godot docs: ScrollContainer.get_vertical_custom_step
     */
    fun getVerticalCustomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVerticalCustomStepBind, segment)
    }

    /**
     * Controls whether horizontal scrollbar can be used and when it should be visible.
     *
     * Generated from Godot docs: ScrollContainer.set_horizontal_scroll_mode
     */
    fun setHorizontalScrollMode(enable: ScrollContainer.ScrollMode) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalScrollModeBind, segment, enable.value)
    }

    /**
     * Controls whether horizontal scrollbar can be used and when it should be visible.
     *
     * Generated from Godot docs: ScrollContainer.get_horizontal_scroll_mode
     */
    fun getHorizontalScrollMode(): ScrollContainer.ScrollMode {
        return ScrollContainer.ScrollMode(ObjectCalls.ptrcallNoArgsRetLong(getHorizontalScrollModeBind, segment))
    }

    /**
     * Controls whether vertical scrollbar can be used and when it should be visible.
     *
     * Generated from Godot docs: ScrollContainer.set_vertical_scroll_mode
     */
    fun setVerticalScrollMode(enable: ScrollContainer.ScrollMode) {
        ObjectCalls.ptrcallWithLongArg(setVerticalScrollModeBind, segment, enable.value)
    }

    /**
     * Controls whether vertical scrollbar can be used and when it should be visible.
     *
     * Generated from Godot docs: ScrollContainer.get_vertical_scroll_mode
     */
    fun getVerticalScrollMode(): ScrollContainer.ScrollMode {
        return ScrollContainer.ScrollMode(ObjectCalls.ptrcallNoArgsRetLong(getVerticalScrollModeBind, segment))
    }

    /**
     * If `true`, the mouse wheel scrolls the view horizontally, and holding Shift scrolls vertically.
     * If `false` (default), the mouse wheel scrolls the view vertically, and holding Shift scrolls
     * horizontally.
     *
     * Generated from Godot docs: ScrollContainer.set_scroll_horizontal_by_default
     */
    fun setScrollHorizontalByDefault(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollHorizontalByDefaultBind, segment, enable)
    }

    /**
     * If `true`, the mouse wheel scrolls the view horizontally, and holding Shift scrolls vertically.
     * If `false` (default), the mouse wheel scrolls the view vertically, and holding Shift scrolls
     * horizontally.
     *
     * Generated from Godot docs: ScrollContainer.is_scroll_horizontal_by_default
     */
    fun isScrollHorizontalByDefault(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHorizontalByDefaultBind, segment)
    }

    /**
     * Deadzone for touch scrolling. Lower deadzone makes the scrolling more sensitive.
     *
     * Generated from Godot docs: ScrollContainer.set_deadzone
     */
    fun setDeadzone(deadzone: Int) {
        ObjectCalls.ptrcallWithIntArg(setDeadzoneBind, segment, deadzone)
    }

    /**
     * Deadzone for touch scrolling. Lower deadzone makes the scrolling more sensitive.
     *
     * Generated from Godot docs: ScrollContainer.get_deadzone
     */
    fun getDeadzone(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDeadzoneBind, segment)
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown. Note: Hints won't be shown if the content can be scrolled both
     * vertically and horizontally.
     *
     * Generated from Godot docs: ScrollContainer.set_scroll_hint_mode
     */
    fun setScrollHintMode(scrollHintMode: ScrollContainer.ScrollHintMode) {
        ObjectCalls.ptrcallWithLongArg(setScrollHintModeBind, segment, scrollHintMode.value)
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown. Note: Hints won't be shown if the content can be scrolled both
     * vertically and horizontally.
     *
     * Generated from Godot docs: ScrollContainer.get_scroll_hint_mode
     */
    fun getScrollHintMode(): ScrollContainer.ScrollHintMode {
        return ScrollContainer.ScrollHintMode(ObjectCalls.ptrcallNoArgsRetLong(getScrollHintModeBind, segment))
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: ScrollContainer.set_tile_scroll_hint
     */
    fun setTileScrollHint(tileScrollHint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTileScrollHintBind, segment, tileScrollHint)
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: ScrollContainer.is_scroll_hint_tiled
     */
    fun isScrollHintTiled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHintTiledBind, segment)
    }

    /**
     * If `true`, the ScrollContainer will automatically scroll to focused children (including indirect
     * children) to make sure they are fully visible.
     *
     * Generated from Godot docs: ScrollContainer.set_follow_focus
     */
    fun setFollowFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowFocusBind, segment, enabled)
    }

    /**
     * If `true`, the ScrollContainer will automatically scroll to focused children (including indirect
     * children) to make sure they are fully visible.
     *
     * Generated from Godot docs: ScrollContainer.is_following_focus
     */
    fun isFollowingFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFollowingFocusBind, segment)
    }

    /**
     * Returns the horizontal scrollbar `HScrollBar` of this `ScrollContainer`. Warning: This is a
     * required internal node, removing and freeing it may cause a crash. If you wish to disable or
     * hide a scrollbar, you can use `horizontal_scroll_mode`.
     *
     * Generated from Godot docs: ScrollContainer.get_h_scroll_bar
     */
    fun getHScrollBar(): HScrollBar? {
        return HScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHScrollBarBind, segment))
    }

    /**
     * Returns the vertical scrollbar `VScrollBar` of this `ScrollContainer`. Warning: This is a
     * required internal node, removing and freeing it may cause a crash. If you wish to disable or
     * hide a scrollbar, you can use `vertical_scroll_mode`.
     *
     * Generated from Godot docs: ScrollContainer.get_v_scroll_bar
     */
    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, segment))
    }

    /**
     * Ensures the given `control` is visible (must be a direct or indirect child of the
     * ScrollContainer). Used by `follow_focus`. Note: This will not work on a node that was just added
     * during the same frame.
     *
     * Generated from Godot docs: ScrollContainer.ensure_control_visible
     */
    fun ensureControlVisible(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(ensureControlVisibleBind, segment, listOf(control.segment))
    }

    /**
     * If `true`, `focus` is drawn when the ScrollContainer or one of its descendant nodes is focused.
     *
     * Generated from Godot docs: ScrollContainer.set_draw_focus_border
     */
    fun setDrawFocusBorder(draw: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawFocusBorderBind, segment, draw)
    }

    /**
     * If `true`, `focus` is drawn when the ScrollContainer or one of its descendant nodes is focused.
     *
     * Generated from Godot docs: ScrollContainer.get_draw_focus_border
     */
    fun getDrawFocusBorder(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDrawFocusBorderBind, segment)
    }

    /** Signal `scroll_started()`; see [TypedSignal]. */
    val scrollStarted: Signal0
        @JvmName("scrollStartedTypedSignal")
        get() = Signal0(this, "scroll_started")

    /** Signal `scroll_ended()`; see [TypedSignal]. */
    val scrollEnded: Signal0
        @JvmName("scrollEndedTypedSignal")
        get() = Signal0(this, "scroll_ended")

    object Signals {
        const val scrollStarted: String = "scroll_started"
        const val scrollEnded: String = "scroll_ended"
    }

    /**
     * Godot's `ScrollContainer.ScrollMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ScrollContainer.ScrollMode.<NAME>`).
     *
     * Generated from Godot docs: ScrollContainer.ScrollMode
     */
    @JvmInline
    value class ScrollMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Scrolling disabled, scrollbar will be invisible.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_DISABLED
             */
            val DISABLED: ScrollMode get() = ScrollMode(0L)
            /**
             * Scrolling enabled, scrollbar will be visible only if necessary, i.e. container's content is
             * bigger than the container.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_AUTO
             */
            val AUTO: ScrollMode get() = ScrollMode(1L)
            /**
             * Scrolling enabled, scrollbar will be always visible.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_SHOW_ALWAYS
             */
            val SHOW_ALWAYS: ScrollMode get() = ScrollMode(2L)
            /**
             * Scrolling enabled, scrollbar will be hidden.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_SHOW_NEVER
             */
            val SHOW_NEVER: ScrollMode get() = ScrollMode(3L)
            /**
             * Combines `ScrollMode.AUTO` and `ScrollMode.SHOW_ALWAYS`. The scrollbar is only visible if
             * necessary, but the content size is adjusted as if it was always visible. It's useful for
             * ensuring that content size stays the same regardless if the scrollbar is visible.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_RESERVE
             */
            val RESERVE: ScrollMode get() = ScrollMode(4L)
            /**
             * Behaves like `ScrollMode.AUTO`, but makes the `ScrollContainer` report a minimum size based on
             * its content (limited by `Control.custom_maximum_size` when set on the corresponding axis). This
             * allows it to grow first and only start scrolling once constrained.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_MODE_MAXIMIZE_FIRST
             */
            val MAXIMIZE_FIRST: ScrollMode get() = ScrollMode(5L)
        }
    }

    /**
     * Godot's `ScrollContainer.ScrollHintMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ScrollContainer.ScrollHintMode.<NAME>`).
     *
     * Generated from Godot docs: ScrollContainer.ScrollHintMode
     */
    @JvmInline
    value class ScrollHintMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Scroll hints will never be shown.
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_HINT_MODE_DISABLED
             */
            val DISABLED: ScrollHintMode get() = ScrollHintMode(0L)
            /**
             * Scroll hints will be shown at the top and bottom (if vertical), or left and right (if
             * horizontal).
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_HINT_MODE_ALL
             */
            val ALL: ScrollHintMode get() = ScrollHintMode(1L)
            /**
             * Scroll hints will be shown at the top (if vertical), or the left (if horizontal).
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_HINT_MODE_TOP_AND_LEFT
             */
            val TOP_AND_LEFT: ScrollHintMode get() = ScrollHintMode(2L)
            /**
             * Scroll hints will be shown at the bottom (if horizontal), or the right (if horizontal).
             *
             * Generated from Godot docs: ScrollContainer.SCROLL_HINT_MODE_BOTTOM_AND_RIGHT
             */
            val BOTTOM_AND_RIGHT: ScrollHintMode get() = ScrollHintMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScrollContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScrollContainer? =
            if (handle.address() == 0L) null else ScrollContainer(GodotHandle(handle))

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
