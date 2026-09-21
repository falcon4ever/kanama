package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * A control that provides a horizontal bar with tabs.
 *
 * Generated from Godot docs: TabBar
 */
class TabBar(handle: GodotHandle) : Control(handle) {
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

    /**
     * The number of tabs currently in the bar.
     *
     * Generated from Godot docs: TabBar.set_tab_count
     */
    fun setTabCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabCountBind, segment, count)
    }

    /**
     * The number of tabs currently in the bar.
     *
     * Generated from Godot docs: TabBar.get_tab_count
     */
    fun getTabCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabCountBind, segment)
    }

    /**
     * The index of the current selected tab. A value of `-1` means that no tab is selected and can
     * only be set when `deselect_enabled` is `true` or if all tabs are hidden or disabled.
     *
     * Generated from Godot docs: TabBar.set_current_tab
     */
    fun setCurrentTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setCurrentTabBind, segment, tabIdx)
    }

    /**
     * The index of the current selected tab. A value of `-1` means that no tab is selected and can
     * only be set when `deselect_enabled` is `true` or if all tabs are hidden or disabled.
     *
     * Generated from Godot docs: TabBar.get_current_tab
     */
    fun getCurrentTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentTabBind, segment)
    }

    /**
     * Returns the previously active tab index.
     *
     * Generated from Godot docs: TabBar.get_previous_tab
     */
    fun getPreviousTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPreviousTabBind, segment)
    }

    /**
     * Selects the first available tab with lower index than the currently selected. Returns `true` if
     * tab selection changed.
     *
     * Generated from Godot docs: TabBar.select_previous_available
     */
    fun selectPreviousAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectPreviousAvailableBind, segment)
    }

    /**
     * Selects the first available tab with greater index than the currently selected. Returns `true`
     * if tab selection changed.
     *
     * Generated from Godot docs: TabBar.select_next_available
     */
    fun selectNextAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectNextAvailableBind, segment)
    }

    /**
     * Sets a `title` for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.set_tab_title
     */
    fun setTabTitle(tabIdx: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTitleBind, segment, tabIdx, title)
    }

    /**
     * Returns the title of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_title
     */
    fun getTabTitle(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTitleBind, segment, tabIdx)
    }

    /**
     * Sets a `tooltip` for tab at index `tab_idx`. Note: By default, if the `tooltip` is empty and the
     * tab text is truncated (not all characters fit into the tab), the title will be displayed as a
     * tooltip. To hide the tooltip, assign `" "` as the `tooltip` text.
     *
     * Generated from Godot docs: TabBar.set_tab_tooltip
     */
    fun setTabTooltip(tabIdx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTooltipBind, segment, tabIdx, tooltip)
    }

    /**
     * Returns the tooltip text of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_tooltip
     */
    fun getTabTooltip(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTooltipBind, segment, tabIdx)
    }

    /**
     * Sets tab title base writing direction.
     *
     * Generated from Godot docs: TabBar.set_tab_text_direction
     */
    fun setTabTextDirection(tabIdx: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTabTextDirectionBind, segment, tabIdx, direction)
    }

    /**
     * Returns tab title text base writing direction.
     *
     * Generated from Godot docs: TabBar.get_tab_text_direction
     */
    fun getTabTextDirection(tabIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTabTextDirectionBind, segment, tabIdx)
    }

    /**
     * Sets the language code of the title for the tab at index `tab_idx` to `language`. This is used
     * for line-breaking and text shaping algorithms. If `language` is empty, the current locale is
     * used.
     *
     * Generated from Godot docs: TabBar.set_tab_language
     */
    fun setTabLanguage(tabIdx: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabLanguageBind, segment, tabIdx, language)
    }

    /**
     * Returns tab title language code.
     *
     * Generated from Godot docs: TabBar.get_tab_language
     */
    fun getTabLanguage(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabLanguageBind, segment, tabIdx)
    }

    /**
     * Sets an `icon` for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.set_tab_icon
     */
    fun setTabIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the icon for the tab at index `tab_idx` or `null` if the tab has no icon.
     *
     * Generated from Godot docs: TabBar.get_tab_icon
     */
    fun getTabIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabIconBind, segment, tabIdx))
    }

    /**
     * Sets the maximum allowed width of the icon for the tab at index `tab_idx`. This limit is applied
     * on top of the default size of the icon and on top of `icon_max_width`. The height is adjusted
     * according to the icon's ratio.
     *
     * Generated from Godot docs: TabBar.set_tab_icon_max_width
     */
    fun setTabIconMaxWidth(tabIdx: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTabIconMaxWidthBind, segment, tabIdx, width)
    }

    /**
     * Returns the maximum allowed width of the icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_icon_max_width
     */
    fun getTabIconMaxWidth(tabIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTabIconMaxWidthBind, segment, tabIdx)
    }

    /**
     * Sets an `icon` for the button of the tab at index `tab_idx` (located to the right, before the
     * close button), making it visible and clickable (See `tab_button_pressed`). Giving it a `null`
     * value will hide the button.
     *
     * Generated from Godot docs: TabBar.set_tab_button_icon
     */
    fun setTabButtonIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabButtonIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the icon for the right button of the tab at index `tab_idx` or `null` if the right
     * button has no icon.
     *
     * Generated from Godot docs: TabBar.get_tab_button_icon
     */
    fun getTabButtonIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabButtonIconBind, segment, tabIdx))
    }

    /**
     * If `disabled` is `true`, disables the tab at index `tab_idx`, making it non-interactable.
     *
     * Generated from Godot docs: TabBar.set_tab_disabled
     */
    fun setTabDisabled(tabIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabDisabledBind, segment, tabIdx, disabled)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is disabled.
     *
     * Generated from Godot docs: TabBar.is_tab_disabled
     */
    fun isTabDisabled(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabDisabledBind, segment, tabIdx)
    }

    /**
     * If `hidden` is `true`, hides the tab at index `tab_idx`, making it disappear from the tab area.
     *
     * Generated from Godot docs: TabBar.set_tab_hidden
     */
    fun setTabHidden(tabIdx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabHiddenBind, segment, tabIdx, hidden)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is hidden.
     *
     * Generated from Godot docs: TabBar.is_tab_hidden
     */
    fun isTabHidden(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabHiddenBind, segment, tabIdx)
    }

    /**
     * Sets the metadata value for the tab at index `tab_idx`, which can be retrieved later using
     * `get_tab_metadata`.
     *
     * Generated from Godot docs: TabBar.set_tab_metadata
     */
    fun setTabMetadata(tabIdx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setTabMetadataBind, segment, tabIdx, metadata)
    }

    /**
     * Returns the metadata value set to the tab at index `tab_idx` using `set_tab_metadata`. If no
     * metadata was previously set, returns `null` by default.
     *
     * Generated from Godot docs: TabBar.get_tab_metadata
     */
    fun getTabMetadata(tabIdx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getTabMetadataBind, segment, tabIdx)
    }

    /**
     * Removes the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.remove_tab
     */
    fun removeTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTabBind, segment, tabIdx)
    }

    /**
     * Adds a new tab.
     *
     * Generated from Godot docs: TabBar.add_tab
     */
    fun addTab(title: String = "", icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(addTabBind, segment, title, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the index of the tab at local coordinates `point`. Returns `-1` if the point is outside
     * the control boundaries or if there's no tab at the queried position.
     *
     * Generated from Godot docs: TabBar.get_tab_idx_at_point
     */
    fun getTabIdxAtPoint(point: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getTabIdxAtPointBind, segment, point)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabBar.set_tab_alignment
     */
    fun setTabAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabAlignmentBind, segment, alignment)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabBar.get_tab_alignment
     */
    fun getTabAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabAlignmentBind, segment)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabBar.set_clip_tabs
     */
    fun setClipTabs(clipTabs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTabsBind, segment, clipTabs)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabBar.get_clip_tabs
     */
    fun getClipTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getClipTabsBind, segment)
    }

    /**
     * Returns the number of hidden tabs offsetted to the left.
     *
     * Generated from Godot docs: TabBar.get_tab_offset
     */
    fun getTabOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabOffsetBind, segment)
    }

    /**
     * Returns `true` if the offset buttons (the ones that appear when there's not enough space for all
     * tabs) are visible.
     *
     * Generated from Godot docs: TabBar.get_offset_buttons_visible
     */
    fun getOffsetButtonsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOffsetButtonsVisibleBind, segment)
    }

    /**
     * Moves the scroll view to make the tab visible.
     *
     * Generated from Godot docs: TabBar.ensure_tab_visible
     */
    fun ensureTabVisible(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(ensureTabVisibleBind, segment, idx)
    }

    /**
     * Returns tab `Rect2` with local position and size.
     *
     * Generated from Godot docs: TabBar.get_tab_rect
     */
    fun getTabRect(tabIdx: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(getTabRectBind, segment, tabIdx)
    }

    /**
     * Moves a tab from `from` to `to`.
     *
     * Generated from Godot docs: TabBar.move_tab
     */
    fun moveTab(from: Int, to: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveTabBind, segment, from, to)
    }

    /**
     * If `true`, middle-clicking on a tab will emit the `tab_close_pressed` signal.
     *
     * Generated from Godot docs: TabBar.set_close_with_middle_mouse
     */
    fun setCloseWithMiddleMouse(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCloseWithMiddleMouseBind, segment, enabled)
    }

    /**
     * If `true`, middle-clicking on a tab will emit the `tab_close_pressed` signal.
     *
     * Generated from Godot docs: TabBar.get_close_with_middle_mouse
     */
    fun getCloseWithMiddleMouse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCloseWithMiddleMouseBind, segment)
    }

    /**
     * When the close button will appear on the tabs.
     *
     * Generated from Godot docs: TabBar.set_tab_close_display_policy
     */
    fun setTabCloseDisplayPolicy(policy: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabCloseDisplayPolicyBind, segment, policy)
    }

    /**
     * When the close button will appear on the tabs.
     *
     * Generated from Godot docs: TabBar.get_tab_close_display_policy
     */
    fun getTabCloseDisplayPolicy(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabCloseDisplayPolicyBind, segment)
    }

    /**
     * Sets the maximum width which all tabs should be limited to. Unlimited if set to `0`.
     *
     * Generated from Godot docs: TabBar.set_max_tab_width
     */
    fun setMaxTabWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTabWidthBind, segment, width)
    }

    /**
     * Sets the maximum width which all tabs should be limited to. Unlimited if set to `0`.
     *
     * Generated from Godot docs: TabBar.get_max_tab_width
     */
    fun getMaxTabWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTabWidthBind, segment)
    }

    /**
     * if `true`, the mouse's scroll wheel can be used to navigate the scroll view.
     *
     * Generated from Godot docs: TabBar.set_scrolling_enabled
     */
    fun setScrollingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollingEnabledBind, segment, enabled)
    }

    /**
     * if `true`, the mouse's scroll wheel can be used to navigate the scroll view.
     *
     * Generated from Godot docs: TabBar.get_scrolling_enabled
     */
    fun getScrollingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getScrollingEnabledBind, segment)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabBar.set_drag_to_rearrange_enabled
     */
    fun setDragToRearrangeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragToRearrangeEnabledBind, segment, enabled)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabBar.get_drag_to_rearrange_enabled
     */
    fun getDragToRearrangeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDragToRearrangeEnabledBind, segment)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange. The delay for when this happens is dictated by
     * `hover_switch_wait_msec`.
     *
     * Generated from Godot docs: TabBar.set_switch_on_drag_hover
     */
    fun setSwitchOnDragHover(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSwitchOnDragHoverBind, segment, enabled)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange. The delay for when this happens is dictated by
     * `hover_switch_wait_msec`.
     *
     * Generated from Godot docs: TabBar.get_switch_on_drag_hover
     */
    fun getSwitchOnDragHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSwitchOnDragHoverBind, segment)
    }

    /**
     * `TabBar`s with the same rearrange group ID will allow dragging the tabs between them. Enable
     * drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging between
     * `TabBar`s.
     *
     * Generated from Godot docs: TabBar.set_tabs_rearrange_group
     */
    fun setTabsRearrangeGroup(groupId: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabsRearrangeGroupBind, segment, groupId)
    }

    /**
     * `TabBar`s with the same rearrange group ID will allow dragging the tabs between them. Enable
     * drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging between
     * `TabBar`s.
     *
     * Generated from Godot docs: TabBar.get_tabs_rearrange_group
     */
    fun getTabsRearrangeGroup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabsRearrangeGroupBind, segment)
    }

    /**
     * If `true`, the tab offset will be changed to keep the currently selected tab visible.
     *
     * Generated from Godot docs: TabBar.set_scroll_to_selected
     */
    fun setScrollToSelected(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollToSelectedBind, segment, enabled)
    }

    /**
     * If `true`, the tab offset will be changed to keep the currently selected tab visible.
     *
     * Generated from Godot docs: TabBar.get_scroll_to_selected
     */
    fun getScrollToSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getScrollToSelectedBind, segment)
    }

    /**
     * If `true`, enables selecting a tab with the right mouse button.
     *
     * Generated from Godot docs: TabBar.set_select_with_rmb
     */
    fun setSelectWithRmb(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectWithRmbBind, segment, enabled)
    }

    /**
     * If `true`, enables selecting a tab with the right mouse button.
     *
     * Generated from Godot docs: TabBar.get_select_with_rmb
     */
    fun getSelectWithRmb(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSelectWithRmbBind, segment)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the current tab to
     * deselect it.
     *
     * Generated from Godot docs: TabBar.set_deselect_enabled
     */
    fun setDeselectEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectEnabledBind, segment, enabled)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the current tab to
     * deselect it.
     *
     * Generated from Godot docs: TabBar.get_deselect_enabled
     */
    fun getDeselectEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDeselectEnabledBind, segment)
    }

    /**
     * Clears all tabs.
     *
     * Generated from Godot docs: TabBar.clear_tabs
     */
    fun clearTabs() {
        ObjectCalls.ptrcallNoArgs(clearTabsBind, segment)
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
        fun fromHandle(handle: GodotHandle): TabBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TabBar? =
            if (handle.address() == 0L) null else TabBar(GodotHandle(handle))

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

        private const val SET_TAB_METADATA_HASH = 2152698145L
        private val setTabMetadataBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "set_tab_metadata", SET_TAB_METADATA_HASH)
        }

        private const val GET_TAB_METADATA_HASH = 4227898402L
        private val getTabMetadataBind by lazy {
            ObjectCalls.getMethodBind("TabBar", "get_tab_metadata", GET_TAB_METADATA_HASH)
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
