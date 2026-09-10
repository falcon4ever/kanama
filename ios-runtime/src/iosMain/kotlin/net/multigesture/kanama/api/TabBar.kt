package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: TabBar
 */
class TabBar(handle: MemorySegment) : Control(handle) {
    var currentTab: Int
        @JvmName("currentTabProperty")
        get() = getCurrentTab()
        @JvmName("setCurrentTabProperty")
        set(value) = setCurrentTab(value)

    var tabAlignment: Long
        @JvmName("tabAlignmentProperty")
        get() = getTabAlignment()
        @JvmName("setTabAlignmentProperty")
        set(value) = setTabAlignment(value)

    var clipTabs: Boolean
        @JvmName("clipTabsProperty")
        get() = getClipTabs()
        @JvmName("setClipTabsProperty")
        set(value) = setClipTabs(value)

    var closeWithMiddleMouse: Boolean
        @JvmName("closeWithMiddleMouseProperty")
        get() = getCloseWithMiddleMouse()
        @JvmName("setCloseWithMiddleMouseProperty")
        set(value) = setCloseWithMiddleMouse(value)

    var tabCloseDisplayPolicy: Long
        @JvmName("tabCloseDisplayPolicyProperty")
        get() = getTabCloseDisplayPolicy()
        @JvmName("setTabCloseDisplayPolicyProperty")
        set(value) = setTabCloseDisplayPolicy(value)

    var maxTabWidth: Int
        @JvmName("maxTabWidthProperty")
        get() = getMaxTabWidth()
        @JvmName("setMaxTabWidthProperty")
        set(value) = setMaxTabWidth(value)

    var scrollingEnabled: Boolean
        @JvmName("scrollingEnabledProperty")
        get() = getScrollingEnabled()
        @JvmName("setScrollingEnabledProperty")
        set(value) = setScrollingEnabled(value)

    var dragToRearrangeEnabled: Boolean
        @JvmName("dragToRearrangeEnabledProperty")
        get() = getDragToRearrangeEnabled()
        @JvmName("setDragToRearrangeEnabledProperty")
        set(value) = setDragToRearrangeEnabled(value)

    var switchOnDragHover: Boolean
        @JvmName("switchOnDragHoverProperty")
        get() = getSwitchOnDragHover()
        @JvmName("setSwitchOnDragHoverProperty")
        set(value) = setSwitchOnDragHover(value)

    var tabsRearrangeGroup: Int
        @JvmName("tabsRearrangeGroupProperty")
        get() = getTabsRearrangeGroup()
        @JvmName("setTabsRearrangeGroupProperty")
        set(value) = setTabsRearrangeGroup(value)

    var scrollToSelected: Boolean
        @JvmName("scrollToSelectedProperty")
        get() = getScrollToSelected()
        @JvmName("setScrollToSelectedProperty")
        set(value) = setScrollToSelected(value)

    var selectWithRmb: Boolean
        @JvmName("selectWithRmbProperty")
        get() = getSelectWithRmb()
        @JvmName("setSelectWithRmbProperty")
        set(value) = setSelectWithRmb(value)

    var deselectEnabled: Boolean
        @JvmName("deselectEnabledProperty")
        get() = getDeselectEnabled()
        @JvmName("setDeselectEnabledProperty")
        set(value) = setDeselectEnabled(value)

    var tabCount: Int
        @JvmName("tabCountProperty")
        get() = getTabCount()
        @JvmName("setTabCountProperty")
        set(value) = setTabCount(value)

    fun setTabCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabCountBind, handle, count)
    }

    fun getTabCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabCountBind, handle)
    }

    fun setCurrentTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setCurrentTabBind, handle, tabIdx)
    }

    fun getCurrentTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentTabBind, handle)
    }

    fun getPreviousTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPreviousTabBind, handle)
    }

    fun selectPreviousAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectPreviousAvailableBind, handle)
    }

    fun selectNextAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectNextAvailableBind, handle)
    }

    fun setTabTitle(tabIdx: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTitleBind, handle, tabIdx, title)
    }

    fun getTabTitle(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTitleBind, handle, tabIdx)
    }

    fun setTabTooltip(tabIdx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTooltipBind, handle, tabIdx, tooltip)
    }

    fun getTabTooltip(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTooltipBind, handle, tabIdx)
    }

    fun setTabTextDirection(tabIdx: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTabTextDirectionBind, handle, tabIdx, direction)
    }

    fun getTabTextDirection(tabIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTabTextDirectionBind, handle, tabIdx)
    }

    fun setTabLanguage(tabIdx: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabLanguageBind, handle, tabIdx, language)
    }

    fun getTabLanguage(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabLanguageBind, handle, tabIdx)
    }

    fun setTabIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabIconBind, handle, tabIdx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTabIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabIconBind, handle, tabIdx))
    }

    fun setTabIconMaxWidth(tabIdx: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTabIconMaxWidthBind, handle, tabIdx, width)
    }

    fun getTabIconMaxWidth(tabIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTabIconMaxWidthBind, handle, tabIdx)
    }

    fun setTabButtonIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabButtonIconBind, handle, tabIdx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTabButtonIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabButtonIconBind, handle, tabIdx))
    }

    fun setTabDisabled(tabIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabDisabledBind, handle, tabIdx, disabled)
    }

    fun isTabDisabled(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabDisabledBind, handle, tabIdx)
    }

    fun setTabHidden(tabIdx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabHiddenBind, handle, tabIdx, hidden)
    }

    fun isTabHidden(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabHiddenBind, handle, tabIdx)
    }

    fun removeTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTabBind, handle, tabIdx)
    }

    fun addTab(title: String = "", icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addTabBind, handle, title, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getTabIdxAtPoint(point: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getTabIdxAtPointBind, handle, point)
    }

    fun setTabAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabAlignmentBind, handle, alignment)
    }

    fun getTabAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabAlignmentBind, handle)
    }

    fun setClipTabs(clipTabs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTabsBind, handle, clipTabs)
    }

    fun getClipTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getClipTabsBind, handle)
    }

    fun getTabOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabOffsetBind, handle)
    }

    fun getOffsetButtonsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOffsetButtonsVisibleBind, handle)
    }

    fun ensureTabVisible(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(ensureTabVisibleBind, handle, idx)
    }

    fun getTabRect(tabIdx: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(getTabRectBind, handle, tabIdx)
    }

    fun moveTab(from: Int, to: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveTabBind, handle, from, to)
    }

    fun setCloseWithMiddleMouse(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCloseWithMiddleMouseBind, handle, enabled)
    }

    fun getCloseWithMiddleMouse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCloseWithMiddleMouseBind, handle)
    }

    fun setTabCloseDisplayPolicy(policy: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabCloseDisplayPolicyBind, handle, policy)
    }

    fun getTabCloseDisplayPolicy(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabCloseDisplayPolicyBind, handle)
    }

    fun setMaxTabWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTabWidthBind, handle, width)
    }

    fun getMaxTabWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTabWidthBind, handle)
    }

    fun setScrollingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollingEnabledBind, handle, enabled)
    }

    fun getScrollingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getScrollingEnabledBind, handle)
    }

    fun setDragToRearrangeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragToRearrangeEnabledBind, handle, enabled)
    }

    fun getDragToRearrangeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDragToRearrangeEnabledBind, handle)
    }

    fun setSwitchOnDragHover(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSwitchOnDragHoverBind, handle, enabled)
    }

    fun getSwitchOnDragHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSwitchOnDragHoverBind, handle)
    }

    fun setTabsRearrangeGroup(groupId: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabsRearrangeGroupBind, handle, groupId)
    }

    fun getTabsRearrangeGroup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabsRearrangeGroupBind, handle)
    }

    fun setScrollToSelected(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollToSelectedBind, handle, enabled)
    }

    fun getScrollToSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getScrollToSelectedBind, handle)
    }

    fun setSelectWithRmb(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectWithRmbBind, handle, enabled)
    }

    fun getSelectWithRmb(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSelectWithRmbBind, handle)
    }

    fun setDeselectEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectEnabledBind, handle, enabled)
    }

    fun getDeselectEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDeselectEnabledBind, handle)
    }

    fun clearTabs() {
        ObjectCalls.ptrcallNoArgs(clearTabsBind, handle)
    }

    object Signals {
        const val tabSelected: String = "tab_selected"
        const val tabChanged: String = "tab_changed"
        const val tabClicked: String = "tab_clicked"
        const val tabRmbClicked: String = "tab_rmb_clicked"
        const val tabClosePressed: String = "tab_close_pressed"
        const val tabButtonPressed: String = "tab_button_pressed"
        const val tabHovered: String = "tab_hovered"
        const val activeTabRearranged: String = "active_tab_rearranged"
    }

    companion object {
        const val ALIGNMENT_LEFT: Long = 0L
        const val ALIGNMENT_CENTER: Long = 1L
        const val ALIGNMENT_RIGHT: Long = 2L
        const val ALIGNMENT_MAX: Long = 3L
        const val CLOSE_BUTTON_SHOW_NEVER: Long = 0L
        const val CLOSE_BUTTON_SHOW_ACTIVE_ONLY: Long = 1L
        const val CLOSE_BUTTON_SHOW_ALWAYS: Long = 2L
        const val CLOSE_BUTTON_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TabBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TabBar? =
            if (handle.address() == 0L) null else TabBar(handle)

        private const val SET_TAB_COUNT_HASH = 1286410249L
        private val setTabCountBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_count", SET_TAB_COUNT_HASH)
        }

        private const val GET_TAB_COUNT_HASH = 3905245786L
        private val getTabCountBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_count", GET_TAB_COUNT_HASH)
        }

        private const val SET_CURRENT_TAB_HASH = 1286410249L
        private val setCurrentTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_current_tab", SET_CURRENT_TAB_HASH)
        }

        private const val GET_CURRENT_TAB_HASH = 3905245786L
        private val getCurrentTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_current_tab", GET_CURRENT_TAB_HASH)
        }

        private const val GET_PREVIOUS_TAB_HASH = 3905245786L
        private val getPreviousTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_previous_tab", GET_PREVIOUS_TAB_HASH)
        }

        private const val SELECT_PREVIOUS_AVAILABLE_HASH = 2240911060L
        private val selectPreviousAvailableBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "select_previous_available", SELECT_PREVIOUS_AVAILABLE_HASH)
        }

        private const val SELECT_NEXT_AVAILABLE_HASH = 2240911060L
        private val selectNextAvailableBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "select_next_available", SELECT_NEXT_AVAILABLE_HASH)
        }

        private const val SET_TAB_TITLE_HASH = 501894301L
        private val setTabTitleBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_title", SET_TAB_TITLE_HASH)
        }

        private const val GET_TAB_TITLE_HASH = 844755477L
        private val getTabTitleBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_title", GET_TAB_TITLE_HASH)
        }

        private const val SET_TAB_TOOLTIP_HASH = 501894301L
        private val setTabTooltipBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_tooltip", SET_TAB_TOOLTIP_HASH)
        }

        private const val GET_TAB_TOOLTIP_HASH = 844755477L
        private val getTabTooltipBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_tooltip", GET_TAB_TOOLTIP_HASH)
        }

        private const val SET_TAB_TEXT_DIRECTION_HASH = 1707680378L
        private val setTabTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_text_direction", SET_TAB_TEXT_DIRECTION_HASH)
        }

        private const val GET_TAB_TEXT_DIRECTION_HASH = 4235602388L
        private val getTabTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_text_direction", GET_TAB_TEXT_DIRECTION_HASH)
        }

        private const val SET_TAB_LANGUAGE_HASH = 501894301L
        private val setTabLanguageBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_language", SET_TAB_LANGUAGE_HASH)
        }

        private const val GET_TAB_LANGUAGE_HASH = 844755477L
        private val getTabLanguageBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_language", GET_TAB_LANGUAGE_HASH)
        }

        private const val SET_TAB_ICON_HASH = 666127730L
        private val setTabIconBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_icon", SET_TAB_ICON_HASH)
        }

        private const val GET_TAB_ICON_HASH = 3536238170L
        private val getTabIconBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_icon", GET_TAB_ICON_HASH)
        }

        private const val SET_TAB_ICON_MAX_WIDTH_HASH = 3937882851L
        private val setTabIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_icon_max_width", SET_TAB_ICON_MAX_WIDTH_HASH)
        }

        private const val GET_TAB_ICON_MAX_WIDTH_HASH = 923996154L
        private val getTabIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_icon_max_width", GET_TAB_ICON_MAX_WIDTH_HASH)
        }

        private const val SET_TAB_BUTTON_ICON_HASH = 666127730L
        private val setTabButtonIconBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_button_icon", SET_TAB_BUTTON_ICON_HASH)
        }

        private const val GET_TAB_BUTTON_ICON_HASH = 3536238170L
        private val getTabButtonIconBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_button_icon", GET_TAB_BUTTON_ICON_HASH)
        }

        private const val SET_TAB_DISABLED_HASH = 300928843L
        private val setTabDisabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_disabled", SET_TAB_DISABLED_HASH)
        }

        private const val IS_TAB_DISABLED_HASH = 1116898809L
        private val isTabDisabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "is_tab_disabled", IS_TAB_DISABLED_HASH)
        }

        private const val SET_TAB_HIDDEN_HASH = 300928843L
        private val setTabHiddenBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_hidden", SET_TAB_HIDDEN_HASH)
        }

        private const val IS_TAB_HIDDEN_HASH = 1116898809L
        private val isTabHiddenBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "is_tab_hidden", IS_TAB_HIDDEN_HASH)
        }

        private const val REMOVE_TAB_HASH = 1286410249L
        private val removeTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "remove_tab", REMOVE_TAB_HASH)
        }

        private const val ADD_TAB_HASH = 1465444425L
        private val addTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "add_tab", ADD_TAB_HASH)
        }

        private const val GET_TAB_IDX_AT_POINT_HASH = 3820158470L
        private val getTabIdxAtPointBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_idx_at_point", GET_TAB_IDX_AT_POINT_HASH)
        }

        private const val SET_TAB_ALIGNMENT_HASH = 2413632353L
        private val setTabAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_alignment", SET_TAB_ALIGNMENT_HASH)
        }

        private const val GET_TAB_ALIGNMENT_HASH = 2178122193L
        private val getTabAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_alignment", GET_TAB_ALIGNMENT_HASH)
        }

        private const val SET_CLIP_TABS_HASH = 2586408642L
        private val setClipTabsBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_clip_tabs", SET_CLIP_TABS_HASH)
        }

        private const val GET_CLIP_TABS_HASH = 36873697L
        private val getClipTabsBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_clip_tabs", GET_CLIP_TABS_HASH)
        }

        private const val GET_TAB_OFFSET_HASH = 3905245786L
        private val getTabOffsetBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_offset", GET_TAB_OFFSET_HASH)
        }

        private const val GET_OFFSET_BUTTONS_VISIBLE_HASH = 36873697L
        private val getOffsetButtonsVisibleBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_offset_buttons_visible", GET_OFFSET_BUTTONS_VISIBLE_HASH)
        }

        private const val ENSURE_TAB_VISIBLE_HASH = 1286410249L
        private val ensureTabVisibleBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "ensure_tab_visible", ENSURE_TAB_VISIBLE_HASH)
        }

        private const val GET_TAB_RECT_HASH = 3327874267L
        private val getTabRectBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_rect", GET_TAB_RECT_HASH)
        }

        private const val MOVE_TAB_HASH = 3937882851L
        private val moveTabBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "move_tab", MOVE_TAB_HASH)
        }

        private const val SET_CLOSE_WITH_MIDDLE_MOUSE_HASH = 2586408642L
        private val setCloseWithMiddleMouseBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_close_with_middle_mouse", SET_CLOSE_WITH_MIDDLE_MOUSE_HASH)
        }

        private const val GET_CLOSE_WITH_MIDDLE_MOUSE_HASH = 36873697L
        private val getCloseWithMiddleMouseBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_close_with_middle_mouse", GET_CLOSE_WITH_MIDDLE_MOUSE_HASH)
        }

        private const val SET_TAB_CLOSE_DISPLAY_POLICY_HASH = 2212906737L
        private val setTabCloseDisplayPolicyBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_close_display_policy", SET_TAB_CLOSE_DISPLAY_POLICY_HASH)
        }

        private const val GET_TAB_CLOSE_DISPLAY_POLICY_HASH = 2956568028L
        private val getTabCloseDisplayPolicyBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_close_display_policy", GET_TAB_CLOSE_DISPLAY_POLICY_HASH)
        }

        private const val SET_MAX_TAB_WIDTH_HASH = 1286410249L
        private val setMaxTabWidthBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_max_tab_width", SET_MAX_TAB_WIDTH_HASH)
        }

        private const val GET_MAX_TAB_WIDTH_HASH = 3905245786L
        private val getMaxTabWidthBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_max_tab_width", GET_MAX_TAB_WIDTH_HASH)
        }

        private const val SET_SCROLLING_ENABLED_HASH = 2586408642L
        private val setScrollingEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_scrolling_enabled", SET_SCROLLING_ENABLED_HASH)
        }

        private const val GET_SCROLLING_ENABLED_HASH = 36873697L
        private val getScrollingEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_scrolling_enabled", GET_SCROLLING_ENABLED_HASH)
        }

        private const val SET_DRAG_TO_REARRANGE_ENABLED_HASH = 2586408642L
        private val setDragToRearrangeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_drag_to_rearrange_enabled", SET_DRAG_TO_REARRANGE_ENABLED_HASH)
        }

        private const val GET_DRAG_TO_REARRANGE_ENABLED_HASH = 36873697L
        private val getDragToRearrangeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_drag_to_rearrange_enabled", GET_DRAG_TO_REARRANGE_ENABLED_HASH)
        }

        private const val SET_SWITCH_ON_DRAG_HOVER_HASH = 2586408642L
        private val setSwitchOnDragHoverBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_switch_on_drag_hover", SET_SWITCH_ON_DRAG_HOVER_HASH)
        }

        private const val GET_SWITCH_ON_DRAG_HOVER_HASH = 36873697L
        private val getSwitchOnDragHoverBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_switch_on_drag_hover", GET_SWITCH_ON_DRAG_HOVER_HASH)
        }

        private const val SET_TABS_REARRANGE_GROUP_HASH = 1286410249L
        private val setTabsRearrangeGroupBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tabs_rearrange_group", SET_TABS_REARRANGE_GROUP_HASH)
        }

        private const val GET_TABS_REARRANGE_GROUP_HASH = 3905245786L
        private val getTabsRearrangeGroupBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tabs_rearrange_group", GET_TABS_REARRANGE_GROUP_HASH)
        }

        private const val SET_SCROLL_TO_SELECTED_HASH = 2586408642L
        private val setScrollToSelectedBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_scroll_to_selected", SET_SCROLL_TO_SELECTED_HASH)
        }

        private const val GET_SCROLL_TO_SELECTED_HASH = 36873697L
        private val getScrollToSelectedBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_scroll_to_selected", GET_SCROLL_TO_SELECTED_HASH)
        }

        private const val SET_SELECT_WITH_RMB_HASH = 2586408642L
        private val setSelectWithRmbBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_select_with_rmb", SET_SELECT_WITH_RMB_HASH)
        }

        private const val GET_SELECT_WITH_RMB_HASH = 36873697L
        private val getSelectWithRmbBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_select_with_rmb", GET_SELECT_WITH_RMB_HASH)
        }

        private const val SET_DESELECT_ENABLED_HASH = 2586408642L
        private val setDeselectEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_deselect_enabled", SET_DESELECT_ENABLED_HASH)
        }

        private const val GET_DESELECT_ENABLED_HASH = 36873697L
        private val getDeselectEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_deselect_enabled", GET_DESELECT_ENABLED_HASH)
        }

        private const val CLEAR_TABS_HASH = 3218959716L
        private val clearTabsBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "clear_tabs", CLEAR_TABS_HASH)
        }
    }
}
