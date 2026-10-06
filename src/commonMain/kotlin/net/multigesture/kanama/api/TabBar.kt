package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
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

    var tabAlignment: TabBar.AlignmentMode
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

    var tabCloseDisplayPolicy: TabBar.CloseButtonDisplayPolicy
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
        ObjectCalls.ptrcallWithIntArg(Binds.setTabCountBind, segment, count)
    }

    /**
     * The number of tabs currently in the bar.
     *
     * Generated from Godot docs: TabBar.get_tab_count
     */
    fun getTabCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTabCountBind, segment)
    }

    /**
     * The index of the current selected tab. A value of `-1` means that no tab is selected and can
     * only be set when `deselect_enabled` is `true` or if all tabs are hidden or disabled.
     *
     * Generated from Godot docs: TabBar.set_current_tab
     */
    fun setCurrentTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setCurrentTabBind, segment, tabIdx)
    }

    /**
     * The index of the current selected tab. A value of `-1` means that no tab is selected and can
     * only be set when `deselect_enabled` is `true` or if all tabs are hidden or disabled.
     *
     * Generated from Godot docs: TabBar.get_current_tab
     */
    fun getCurrentTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentTabBind, segment)
    }

    /**
     * Returns the previously active tab index.
     *
     * Generated from Godot docs: TabBar.get_previous_tab
     */
    fun getPreviousTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPreviousTabBind, segment)
    }

    /**
     * Selects the first available tab with lower index than the currently selected. Returns `true` if
     * tab selection changed.
     *
     * Generated from Godot docs: TabBar.select_previous_available
     */
    fun selectPreviousAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.selectPreviousAvailableBind, segment)
    }

    /**
     * Selects the first available tab with greater index than the currently selected. Returns `true`
     * if tab selection changed.
     *
     * Generated from Godot docs: TabBar.select_next_available
     */
    fun selectNextAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.selectNextAvailableBind, segment)
    }

    /**
     * Sets a `title` for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.set_tab_title
     */
    fun setTabTitle(tabIdx: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTabTitleBind, segment, tabIdx, title)
    }

    /**
     * Returns the title of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_title
     */
    fun getTabTitle(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTabTitleBind, segment, tabIdx)
    }

    /**
     * Sets a `tooltip` for tab at index `tab_idx`. Note: By default, if the `tooltip` is empty and the
     * tab text is truncated (not all characters fit into the tab), the title will be displayed as a
     * tooltip. To hide the tooltip, assign `" "` as the `tooltip` text.
     *
     * Generated from Godot docs: TabBar.set_tab_tooltip
     */
    fun setTabTooltip(tabIdx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTabTooltipBind, segment, tabIdx, tooltip)
    }

    /**
     * Returns the tooltip text of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_tooltip
     */
    fun getTabTooltip(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTabTooltipBind, segment, tabIdx)
    }

    /**
     * Sets tab title base writing direction.
     *
     * Generated from Godot docs: TabBar.set_tab_text_direction
     */
    fun setTabTextDirection(tabIdx: Int, direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTabTextDirectionBind, segment, tabIdx, direction.value)
    }

    /**
     * Returns tab title text base writing direction.
     *
     * Generated from Godot docs: TabBar.get_tab_text_direction
     */
    fun getTabTextDirection(tabIdx: Int): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTabTextDirectionBind, segment, tabIdx))
    }

    /**
     * Sets the language code of the title for the tab at index `tab_idx` to `language`. This is used
     * for line-breaking and text shaping algorithms. If `language` is empty, the current locale is
     * used.
     *
     * Generated from Godot docs: TabBar.set_tab_language
     */
    fun setTabLanguage(tabIdx: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTabLanguageBind, segment, tabIdx, language)
    }

    /**
     * Returns tab title language code.
     *
     * Generated from Godot docs: TabBar.get_tab_language
     */
    fun getTabLanguage(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTabLanguageBind, segment, tabIdx)
    }

    /**
     * Sets an `icon` for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.set_tab_icon
     */
    fun setTabIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setTabIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the icon for the tab at index `tab_idx` or `null` if the tab has no icon.
     *
     * Generated from Godot docs: TabBar.get_tab_icon
     */
    fun getTabIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTabIconBind, segment, tabIdx))
    }

    /**
     * Sets the maximum allowed width of the icon for the tab at index `tab_idx`. This limit is applied
     * on top of the default size of the icon and on top of `icon_max_width`. The height is adjusted
     * according to the icon's ratio.
     *
     * Generated from Godot docs: TabBar.set_tab_icon_max_width
     */
    fun setTabIconMaxWidth(tabIdx: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setTabIconMaxWidthBind, segment, tabIdx, width)
    }

    /**
     * Returns the maximum allowed width of the icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.get_tab_icon_max_width
     */
    fun getTabIconMaxWidth(tabIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getTabIconMaxWidthBind, segment, tabIdx)
    }

    /**
     * Sets an `icon` for the button of the tab at index `tab_idx` (located to the right, before the
     * close button), making it visible and clickable (See `tab_button_pressed`). Giving it a `null`
     * value will hide the button.
     *
     * Generated from Godot docs: TabBar.set_tab_button_icon
     */
    fun setTabButtonIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setTabButtonIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the icon for the right button of the tab at index `tab_idx` or `null` if the right
     * button has no icon.
     *
     * Generated from Godot docs: TabBar.get_tab_button_icon
     */
    fun getTabButtonIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTabButtonIconBind, segment, tabIdx))
    }

    /**
     * If `disabled` is `true`, disables the tab at index `tab_idx`, making it non-interactable.
     *
     * Generated from Godot docs: TabBar.set_tab_disabled
     */
    fun setTabDisabled(tabIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setTabDisabledBind, segment, tabIdx, disabled)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is disabled.
     *
     * Generated from Godot docs: TabBar.is_tab_disabled
     */
    fun isTabDisabled(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isTabDisabledBind, segment, tabIdx)
    }

    /**
     * If `hidden` is `true`, hides the tab at index `tab_idx`, making it disappear from the tab area.
     *
     * Generated from Godot docs: TabBar.set_tab_hidden
     */
    fun setTabHidden(tabIdx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setTabHiddenBind, segment, tabIdx, hidden)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is hidden.
     *
     * Generated from Godot docs: TabBar.is_tab_hidden
     */
    fun isTabHidden(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isTabHiddenBind, segment, tabIdx)
    }

    /**
     * Sets the metadata value for the tab at index `tab_idx`, which can be retrieved later using
     * `get_tab_metadata`.
     *
     * Generated from Godot docs: TabBar.set_tab_metadata
     */
    fun setTabMetadata(tabIdx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setTabMetadataBind, segment, tabIdx, metadata)
    }

    /**
     * Returns the metadata value set to the tab at index `tab_idx` using `set_tab_metadata`. If no
     * metadata was previously set, returns `null` by default.
     *
     * Generated from Godot docs: TabBar.get_tab_metadata
     */
    fun getTabMetadata(tabIdx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getTabMetadataBind, segment, tabIdx)
    }

    /**
     * Removes the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabBar.remove_tab
     */
    fun removeTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.removeTabBind, segment, tabIdx)
    }

    /**
     * Adds a new tab.
     *
     * Generated from Godot docs: TabBar.add_tab
     */
    fun addTab(title: String = "", icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringAndObjectArg(Binds.addTabBind, segment, title, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the index of the tab at local coordinates `point`. Returns `-1` if the point is outside
     * the control boundaries or if there's no tab at the queried position.
     *
     * Generated from Godot docs: TabBar.get_tab_idx_at_point
     */
    fun getTabIdxAtPoint(point: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(Binds.getTabIdxAtPointBind, segment, point)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabBar.set_tab_alignment
     */
    fun setTabAlignment(alignment: TabBar.AlignmentMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTabAlignmentBind, segment, alignment.value)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabBar.get_tab_alignment
     */
    fun getTabAlignment(): TabBar.AlignmentMode {
        return TabBar.AlignmentMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTabAlignmentBind, segment))
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabBar.set_clip_tabs
     */
    fun setClipTabs(clipTabs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setClipTabsBind, segment, clipTabs)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabBar.get_clip_tabs
     */
    fun getClipTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getClipTabsBind, segment)
    }

    /**
     * Returns the number of hidden tabs offsetted to the left.
     *
     * Generated from Godot docs: TabBar.get_tab_offset
     */
    fun getTabOffset(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTabOffsetBind, segment)
    }

    /**
     * Returns `true` if the offset buttons (the ones that appear when there's not enough space for all
     * tabs) are visible.
     *
     * Generated from Godot docs: TabBar.get_offset_buttons_visible
     */
    fun getOffsetButtonsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getOffsetButtonsVisibleBind, segment)
    }

    /**
     * Moves the scroll view to make the tab visible.
     *
     * Generated from Godot docs: TabBar.ensure_tab_visible
     */
    fun ensureTabVisible(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.ensureTabVisibleBind, segment, idx)
    }

    /**
     * Returns tab `Rect2` with local position and size.
     *
     * Generated from Godot docs: TabBar.get_tab_rect
     */
    fun getTabRect(tabIdx: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(Binds.getTabRectBind, segment, tabIdx)
    }

    /**
     * Moves a tab from `from` to `to`.
     *
     * Generated from Godot docs: TabBar.move_tab
     */
    fun moveTab(from: Int, to: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveTabBind, segment, from, to)
    }

    /**
     * If `true`, middle-clicking on a tab will emit the `tab_close_pressed` signal.
     *
     * Generated from Godot docs: TabBar.set_close_with_middle_mouse
     */
    fun setCloseWithMiddleMouse(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCloseWithMiddleMouseBind, segment, enabled)
    }

    /**
     * If `true`, middle-clicking on a tab will emit the `tab_close_pressed` signal.
     *
     * Generated from Godot docs: TabBar.get_close_with_middle_mouse
     */
    fun getCloseWithMiddleMouse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCloseWithMiddleMouseBind, segment)
    }

    /**
     * When the close button will appear on the tabs.
     *
     * Generated from Godot docs: TabBar.set_tab_close_display_policy
     */
    fun setTabCloseDisplayPolicy(policy: TabBar.CloseButtonDisplayPolicy) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTabCloseDisplayPolicyBind, segment, policy.value)
    }

    /**
     * When the close button will appear on the tabs.
     *
     * Generated from Godot docs: TabBar.get_tab_close_display_policy
     */
    fun getTabCloseDisplayPolicy(): TabBar.CloseButtonDisplayPolicy {
        return TabBar.CloseButtonDisplayPolicy(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTabCloseDisplayPolicyBind, segment))
    }

    /**
     * Sets the maximum width which all tabs should be limited to. Unlimited if set to `0`.
     *
     * Generated from Godot docs: TabBar.set_max_tab_width
     */
    fun setMaxTabWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxTabWidthBind, segment, width)
    }

    /**
     * Sets the maximum width which all tabs should be limited to. Unlimited if set to `0`.
     *
     * Generated from Godot docs: TabBar.get_max_tab_width
     */
    fun getMaxTabWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxTabWidthBind, segment)
    }

    /**
     * if `true`, the mouse's scroll wheel can be used to navigate the scroll view.
     *
     * Generated from Godot docs: TabBar.set_scrolling_enabled
     */
    fun setScrollingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setScrollingEnabledBind, segment, enabled)
    }

    /**
     * if `true`, the mouse's scroll wheel can be used to navigate the scroll view.
     *
     * Generated from Godot docs: TabBar.get_scrolling_enabled
     */
    fun getScrollingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getScrollingEnabledBind, segment)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabBar.set_drag_to_rearrange_enabled
     */
    fun setDragToRearrangeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDragToRearrangeEnabledBind, segment, enabled)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabBar.get_drag_to_rearrange_enabled
     */
    fun getDragToRearrangeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDragToRearrangeEnabledBind, segment)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange. The delay for when this happens is dictated by
     * `hover_switch_wait_msec`.
     *
     * Generated from Godot docs: TabBar.set_switch_on_drag_hover
     */
    fun setSwitchOnDragHover(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSwitchOnDragHoverBind, segment, enabled)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange. The delay for when this happens is dictated by
     * `hover_switch_wait_msec`.
     *
     * Generated from Godot docs: TabBar.get_switch_on_drag_hover
     */
    fun getSwitchOnDragHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSwitchOnDragHoverBind, segment)
    }

    /**
     * `TabBar`s with the same rearrange group ID will allow dragging the tabs between them. Enable
     * drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging between
     * `TabBar`s.
     *
     * Generated from Godot docs: TabBar.set_tabs_rearrange_group
     */
    fun setTabsRearrangeGroup(groupId: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTabsRearrangeGroupBind, segment, groupId)
    }

    /**
     * `TabBar`s with the same rearrange group ID will allow dragging the tabs between them. Enable
     * drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging between
     * `TabBar`s.
     *
     * Generated from Godot docs: TabBar.get_tabs_rearrange_group
     */
    fun getTabsRearrangeGroup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTabsRearrangeGroupBind, segment)
    }

    /**
     * If `true`, the tab offset will be changed to keep the currently selected tab visible.
     *
     * Generated from Godot docs: TabBar.set_scroll_to_selected
     */
    fun setScrollToSelected(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setScrollToSelectedBind, segment, enabled)
    }

    /**
     * If `true`, the tab offset will be changed to keep the currently selected tab visible.
     *
     * Generated from Godot docs: TabBar.get_scroll_to_selected
     */
    fun getScrollToSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getScrollToSelectedBind, segment)
    }

    /**
     * If `true`, enables selecting a tab with the right mouse button.
     *
     * Generated from Godot docs: TabBar.set_select_with_rmb
     */
    fun setSelectWithRmb(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSelectWithRmbBind, segment, enabled)
    }

    /**
     * If `true`, enables selecting a tab with the right mouse button.
     *
     * Generated from Godot docs: TabBar.get_select_with_rmb
     */
    fun getSelectWithRmb(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSelectWithRmbBind, segment)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the current tab to
     * deselect it.
     *
     * Generated from Godot docs: TabBar.set_deselect_enabled
     */
    fun setDeselectEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeselectEnabledBind, segment, enabled)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the current tab to
     * deselect it.
     *
     * Generated from Godot docs: TabBar.get_deselect_enabled
     */
    fun getDeselectEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDeselectEnabledBind, segment)
    }

    /**
     * Clears all tabs.
     *
     * Generated from Godot docs: TabBar.clear_tabs
     */
    fun clearTabs() {
        ObjectCalls.ptrcallNoArgs(Binds.clearTabsBind, segment)
    }

    /** Signal `tab_selected(tab: int)`; see [TypedSignal]. */
    val tabSelected: Signal1<Long>
        @JvmName("tabSelectedTypedSignal")
        get() = Signal1(this, "tab_selected", SignalArgType.LONG)

    /** Signal `tab_changed(tab: int)`; see [TypedSignal]. */
    val tabChanged: Signal1<Long>
        @JvmName("tabChangedTypedSignal")
        get() = Signal1(this, "tab_changed", SignalArgType.LONG)

    /** Signal `tab_clicked(tab: int)`; see [TypedSignal]. */
    val tabClicked: Signal1<Long>
        @JvmName("tabClickedTypedSignal")
        get() = Signal1(this, "tab_clicked", SignalArgType.LONG)

    /** Signal `tab_rmb_clicked(tab: int)`; see [TypedSignal]. */
    val tabRmbClicked: Signal1<Long>
        @JvmName("tabRmbClickedTypedSignal")
        get() = Signal1(this, "tab_rmb_clicked", SignalArgType.LONG)

    /** Signal `tab_close_pressed(tab: int)`; see [TypedSignal]. */
    val tabClosePressed: Signal1<Long>
        @JvmName("tabClosePressedTypedSignal")
        get() = Signal1(this, "tab_close_pressed", SignalArgType.LONG)

    /** Signal `tab_button_pressed(tab: int)`; see [TypedSignal]. */
    val tabButtonPressed: Signal1<Long>
        @JvmName("tabButtonPressedTypedSignal")
        get() = Signal1(this, "tab_button_pressed", SignalArgType.LONG)

    /** Signal `tab_hovered(tab: int)`; see [TypedSignal]. */
    val tabHovered: Signal1<Long>
        @JvmName("tabHoveredTypedSignal")
        get() = Signal1(this, "tab_hovered", SignalArgType.LONG)

    /** Signal `active_tab_rearranged(idx_to: int)`; see [TypedSignal]. */
    val activeTabRearranged: Signal1<Long>
        @JvmName("activeTabRearrangedTypedSignal")
        get() = Signal1(this, "active_tab_rearranged", SignalArgType.LONG)

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

    /**
     * Godot's `TabBar.AlignmentMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TabBar.AlignmentMode.<NAME>`).
     *
     * Generated from Godot docs: TabBar.AlignmentMode
     */
    @JvmInline
    value class AlignmentMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Aligns tabs to the left.
             *
             * Generated from Godot docs: TabBar.ALIGNMENT_LEFT
             */
            val LEFT: AlignmentMode get() = AlignmentMode(0L)
            /**
             * Aligns tabs in the middle.
             *
             * Generated from Godot docs: TabBar.ALIGNMENT_CENTER
             */
            val CENTER: AlignmentMode get() = AlignmentMode(1L)
            /**
             * Aligns tabs to the right.
             *
             * Generated from Godot docs: TabBar.ALIGNMENT_RIGHT
             */
            val RIGHT: AlignmentMode get() = AlignmentMode(2L)
            /**
             * Represents the size of the `AlignmentMode` enum.
             *
             * Generated from Godot docs: TabBar.ALIGNMENT_MAX
             */
            val MAX: AlignmentMode get() = AlignmentMode(3L)
        }
    }

    /**
     * Godot's `TabBar.CloseButtonDisplayPolicy` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`TabBar.CloseButtonDisplayPolicy.<NAME>`).
     *
     * Generated from Godot docs: TabBar.CloseButtonDisplayPolicy
     */
    @JvmInline
    value class CloseButtonDisplayPolicy(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Never show the close buttons.
             *
             * Generated from Godot docs: TabBar.CLOSE_BUTTON_SHOW_NEVER
             */
            val SHOW_NEVER: CloseButtonDisplayPolicy get() = CloseButtonDisplayPolicy(0L)
            /**
             * Only show the close button on the currently active tab.
             *
             * Generated from Godot docs: TabBar.CLOSE_BUTTON_SHOW_ACTIVE_ONLY
             */
            val SHOW_ACTIVE_ONLY: CloseButtonDisplayPolicy get() = CloseButtonDisplayPolicy(1L)
            /**
             * Show the close button on all tabs.
             *
             * Generated from Godot docs: TabBar.CLOSE_BUTTON_SHOW_ALWAYS
             */
            val SHOW_ALWAYS: CloseButtonDisplayPolicy get() = CloseButtonDisplayPolicy(2L)
            /**
             * Represents the size of the `CloseButtonDisplayPolicy` enum.
             *
             * Generated from Godot docs: TabBar.CLOSE_BUTTON_MAX
             */
            val MAX: CloseButtonDisplayPolicy get() = CloseButtonDisplayPolicy(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TabBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TabBar? =
            if (handle.address() == 0L) null else TabBar(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TAB_COUNT_HASH = 1286410249L
        @JvmField
        val setTabCountBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_count", SET_TAB_COUNT_HASH)

        private const val GET_TAB_COUNT_HASH = 3905245786L
        @JvmField
        val getTabCountBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_count", GET_TAB_COUNT_HASH)

        private const val SET_CURRENT_TAB_HASH = 1286410249L
        @JvmField
        val setCurrentTabBind =
            ObjectCalls.getMethodBind("TabBar", "set_current_tab", SET_CURRENT_TAB_HASH)

        private const val GET_CURRENT_TAB_HASH = 3905245786L
        @JvmField
        val getCurrentTabBind =
            ObjectCalls.getMethodBind("TabBar", "get_current_tab", GET_CURRENT_TAB_HASH)

        private const val GET_PREVIOUS_TAB_HASH = 3905245786L
        @JvmField
        val getPreviousTabBind =
            ObjectCalls.getMethodBind("TabBar", "get_previous_tab", GET_PREVIOUS_TAB_HASH)

        private const val SELECT_PREVIOUS_AVAILABLE_HASH = 2240911060L
        @JvmField
        val selectPreviousAvailableBind =
            ObjectCalls.getMethodBind("TabBar", "select_previous_available", SELECT_PREVIOUS_AVAILABLE_HASH)

        private const val SELECT_NEXT_AVAILABLE_HASH = 2240911060L
        @JvmField
        val selectNextAvailableBind =
            ObjectCalls.getMethodBind("TabBar", "select_next_available", SELECT_NEXT_AVAILABLE_HASH)

        private const val SET_TAB_TITLE_HASH = 501894301L
        @JvmField
        val setTabTitleBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_title", SET_TAB_TITLE_HASH)

        private const val GET_TAB_TITLE_HASH = 844755477L
        @JvmField
        val getTabTitleBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_title", GET_TAB_TITLE_HASH)

        private const val SET_TAB_TOOLTIP_HASH = 501894301L
        @JvmField
        val setTabTooltipBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_tooltip", SET_TAB_TOOLTIP_HASH)

        private const val GET_TAB_TOOLTIP_HASH = 844755477L
        @JvmField
        val getTabTooltipBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_tooltip", GET_TAB_TOOLTIP_HASH)

        private const val SET_TAB_TEXT_DIRECTION_HASH = 1707680378L
        @JvmField
        val setTabTextDirectionBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_text_direction", SET_TAB_TEXT_DIRECTION_HASH)

        private const val GET_TAB_TEXT_DIRECTION_HASH = 4235602388L
        @JvmField
        val getTabTextDirectionBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_text_direction", GET_TAB_TEXT_DIRECTION_HASH)

        private const val SET_TAB_LANGUAGE_HASH = 501894301L
        @JvmField
        val setTabLanguageBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_language", SET_TAB_LANGUAGE_HASH)

        private const val GET_TAB_LANGUAGE_HASH = 844755477L
        @JvmField
        val getTabLanguageBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_language", GET_TAB_LANGUAGE_HASH)

        private const val SET_TAB_ICON_HASH = 666127730L
        @JvmField
        val setTabIconBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_icon", SET_TAB_ICON_HASH)

        private const val GET_TAB_ICON_HASH = 3536238170L
        @JvmField
        val getTabIconBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_icon", GET_TAB_ICON_HASH)

        private const val SET_TAB_ICON_MAX_WIDTH_HASH = 3937882851L
        @JvmField
        val setTabIconMaxWidthBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_icon_max_width", SET_TAB_ICON_MAX_WIDTH_HASH)

        private const val GET_TAB_ICON_MAX_WIDTH_HASH = 923996154L
        @JvmField
        val getTabIconMaxWidthBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_icon_max_width", GET_TAB_ICON_MAX_WIDTH_HASH)

        private const val SET_TAB_BUTTON_ICON_HASH = 666127730L
        @JvmField
        val setTabButtonIconBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_button_icon", SET_TAB_BUTTON_ICON_HASH)

        private const val GET_TAB_BUTTON_ICON_HASH = 3536238170L
        @JvmField
        val getTabButtonIconBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_button_icon", GET_TAB_BUTTON_ICON_HASH)

        private const val SET_TAB_DISABLED_HASH = 300928843L
        @JvmField
        val setTabDisabledBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_disabled", SET_TAB_DISABLED_HASH)

        private const val IS_TAB_DISABLED_HASH = 1116898809L
        @JvmField
        val isTabDisabledBind =
            ObjectCalls.getMethodBind("TabBar", "is_tab_disabled", IS_TAB_DISABLED_HASH)

        private const val SET_TAB_HIDDEN_HASH = 300928843L
        @JvmField
        val setTabHiddenBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_hidden", SET_TAB_HIDDEN_HASH)

        private const val IS_TAB_HIDDEN_HASH = 1116898809L
        @JvmField
        val isTabHiddenBind =
            ObjectCalls.getMethodBind("TabBar", "is_tab_hidden", IS_TAB_HIDDEN_HASH)

        private const val SET_TAB_METADATA_HASH = 2152698145L
        @JvmField
        val setTabMetadataBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_metadata", SET_TAB_METADATA_HASH)

        private const val GET_TAB_METADATA_HASH = 4227898402L
        @JvmField
        val getTabMetadataBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_metadata", GET_TAB_METADATA_HASH)

        private const val REMOVE_TAB_HASH = 1286410249L
        @JvmField
        val removeTabBind =
            ObjectCalls.getMethodBind("TabBar", "remove_tab", REMOVE_TAB_HASH)

        private const val ADD_TAB_HASH = 1465444425L
        @JvmField
        val addTabBind =
            ObjectCalls.getMethodBind("TabBar", "add_tab", ADD_TAB_HASH)

        private const val GET_TAB_IDX_AT_POINT_HASH = 3820158470L
        @JvmField
        val getTabIdxAtPointBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_idx_at_point", GET_TAB_IDX_AT_POINT_HASH)

        private const val SET_TAB_ALIGNMENT_HASH = 2413632353L
        @JvmField
        val setTabAlignmentBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_alignment", SET_TAB_ALIGNMENT_HASH)

        private const val GET_TAB_ALIGNMENT_HASH = 2178122193L
        @JvmField
        val getTabAlignmentBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_alignment", GET_TAB_ALIGNMENT_HASH)

        private const val SET_CLIP_TABS_HASH = 2586408642L
        @JvmField
        val setClipTabsBind =
            ObjectCalls.getMethodBind("TabBar", "set_clip_tabs", SET_CLIP_TABS_HASH)

        private const val GET_CLIP_TABS_HASH = 36873697L
        @JvmField
        val getClipTabsBind =
            ObjectCalls.getMethodBind("TabBar", "get_clip_tabs", GET_CLIP_TABS_HASH)

        private const val GET_TAB_OFFSET_HASH = 3905245786L
        @JvmField
        val getTabOffsetBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_offset", GET_TAB_OFFSET_HASH)

        private const val GET_OFFSET_BUTTONS_VISIBLE_HASH = 36873697L
        @JvmField
        val getOffsetButtonsVisibleBind =
            ObjectCalls.getMethodBind("TabBar", "get_offset_buttons_visible", GET_OFFSET_BUTTONS_VISIBLE_HASH)

        private const val ENSURE_TAB_VISIBLE_HASH = 1286410249L
        @JvmField
        val ensureTabVisibleBind =
            ObjectCalls.getMethodBind("TabBar", "ensure_tab_visible", ENSURE_TAB_VISIBLE_HASH)

        private const val GET_TAB_RECT_HASH = 3327874267L
        @JvmField
        val getTabRectBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_rect", GET_TAB_RECT_HASH)

        private const val MOVE_TAB_HASH = 3937882851L
        @JvmField
        val moveTabBind =
            ObjectCalls.getMethodBind("TabBar", "move_tab", MOVE_TAB_HASH)

        private const val SET_CLOSE_WITH_MIDDLE_MOUSE_HASH = 2586408642L
        @JvmField
        val setCloseWithMiddleMouseBind =
            ObjectCalls.getMethodBind("TabBar", "set_close_with_middle_mouse", SET_CLOSE_WITH_MIDDLE_MOUSE_HASH)

        private const val GET_CLOSE_WITH_MIDDLE_MOUSE_HASH = 36873697L
        @JvmField
        val getCloseWithMiddleMouseBind =
            ObjectCalls.getMethodBind("TabBar", "get_close_with_middle_mouse", GET_CLOSE_WITH_MIDDLE_MOUSE_HASH)

        private const val SET_TAB_CLOSE_DISPLAY_POLICY_HASH = 2212906737L
        @JvmField
        val setTabCloseDisplayPolicyBind =
            ObjectCalls.getMethodBind("TabBar", "set_tab_close_display_policy", SET_TAB_CLOSE_DISPLAY_POLICY_HASH)

        private const val GET_TAB_CLOSE_DISPLAY_POLICY_HASH = 2956568028L
        @JvmField
        val getTabCloseDisplayPolicyBind =
            ObjectCalls.getMethodBind("TabBar", "get_tab_close_display_policy", GET_TAB_CLOSE_DISPLAY_POLICY_HASH)

        private const val SET_MAX_TAB_WIDTH_HASH = 1286410249L
        @JvmField
        val setMaxTabWidthBind =
            ObjectCalls.getMethodBind("TabBar", "set_max_tab_width", SET_MAX_TAB_WIDTH_HASH)

        private const val GET_MAX_TAB_WIDTH_HASH = 3905245786L
        @JvmField
        val getMaxTabWidthBind =
            ObjectCalls.getMethodBind("TabBar", "get_max_tab_width", GET_MAX_TAB_WIDTH_HASH)

        private const val SET_SCROLLING_ENABLED_HASH = 2586408642L
        @JvmField
        val setScrollingEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "set_scrolling_enabled", SET_SCROLLING_ENABLED_HASH)

        private const val GET_SCROLLING_ENABLED_HASH = 36873697L
        @JvmField
        val getScrollingEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "get_scrolling_enabled", GET_SCROLLING_ENABLED_HASH)

        private const val SET_DRAG_TO_REARRANGE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDragToRearrangeEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "set_drag_to_rearrange_enabled", SET_DRAG_TO_REARRANGE_ENABLED_HASH)

        private const val GET_DRAG_TO_REARRANGE_ENABLED_HASH = 36873697L
        @JvmField
        val getDragToRearrangeEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "get_drag_to_rearrange_enabled", GET_DRAG_TO_REARRANGE_ENABLED_HASH)

        private const val SET_SWITCH_ON_DRAG_HOVER_HASH = 2586408642L
        @JvmField
        val setSwitchOnDragHoverBind =
            ObjectCalls.getMethodBind("TabBar", "set_switch_on_drag_hover", SET_SWITCH_ON_DRAG_HOVER_HASH)

        private const val GET_SWITCH_ON_DRAG_HOVER_HASH = 36873697L
        @JvmField
        val getSwitchOnDragHoverBind =
            ObjectCalls.getMethodBind("TabBar", "get_switch_on_drag_hover", GET_SWITCH_ON_DRAG_HOVER_HASH)

        private const val SET_TABS_REARRANGE_GROUP_HASH = 1286410249L
        @JvmField
        val setTabsRearrangeGroupBind =
            ObjectCalls.getMethodBind("TabBar", "set_tabs_rearrange_group", SET_TABS_REARRANGE_GROUP_HASH)

        private const val GET_TABS_REARRANGE_GROUP_HASH = 3905245786L
        @JvmField
        val getTabsRearrangeGroupBind =
            ObjectCalls.getMethodBind("TabBar", "get_tabs_rearrange_group", GET_TABS_REARRANGE_GROUP_HASH)

        private const val SET_SCROLL_TO_SELECTED_HASH = 2586408642L
        @JvmField
        val setScrollToSelectedBind =
            ObjectCalls.getMethodBind("TabBar", "set_scroll_to_selected", SET_SCROLL_TO_SELECTED_HASH)

        private const val GET_SCROLL_TO_SELECTED_HASH = 36873697L
        @JvmField
        val getScrollToSelectedBind =
            ObjectCalls.getMethodBind("TabBar", "get_scroll_to_selected", GET_SCROLL_TO_SELECTED_HASH)

        private const val SET_SELECT_WITH_RMB_HASH = 2586408642L
        @JvmField
        val setSelectWithRmbBind =
            ObjectCalls.getMethodBind("TabBar", "set_select_with_rmb", SET_SELECT_WITH_RMB_HASH)

        private const val GET_SELECT_WITH_RMB_HASH = 36873697L
        @JvmField
        val getSelectWithRmbBind =
            ObjectCalls.getMethodBind("TabBar", "get_select_with_rmb", GET_SELECT_WITH_RMB_HASH)

        private const val SET_DESELECT_ENABLED_HASH = 2586408642L
        @JvmField
        val setDeselectEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "set_deselect_enabled", SET_DESELECT_ENABLED_HASH)

        private const val GET_DESELECT_ENABLED_HASH = 36873697L
        @JvmField
        val getDeselectEnabledBind =
            ObjectCalls.getMethodBind("TabBar", "get_deselect_enabled", GET_DESELECT_ENABLED_HASH)

        private const val CLEAR_TABS_HASH = 3218959716L
        @JvmField
        val clearTabsBind =
            ObjectCalls.getMethodBind("TabBar", "clear_tabs", CLEAR_TABS_HASH)
    }
}
