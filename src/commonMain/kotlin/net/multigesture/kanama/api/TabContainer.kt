package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A container that creates a tab for each child control, displaying only the active tab's control.
 *
 * Generated from Godot docs: TabContainer
 */
class TabContainer(handle: GodotHandle) : Container(handle) {
    var tabAlignment: TabBar.AlignmentMode
        @JvmName("tabAlignmentProperty")
        get() = getTabAlignment()
        @JvmName("setTabAlignmentProperty")
        set(value) = setTabAlignment(value)

    var currentTab: Int
        @JvmName("currentTabProperty")
        get() = getCurrentTab()
        @JvmName("setCurrentTabProperty")
        set(value) = setCurrentTab(value)

    var tabsPosition: TabContainer.TabPosition
        @JvmName("tabsPositionProperty")
        get() = getTabsPosition()
        @JvmName("setTabsPositionProperty")
        set(value) = setTabsPosition(value)

    var clipTabs: Boolean
        @JvmName("clipTabsProperty")
        get() = getClipTabs()
        @JvmName("setClipTabsProperty")
        set(value) = setClipTabs(value)

    var tabsVisible: Boolean
        @JvmName("tabsVisibleProperty")
        get() = areTabsVisible()
        @JvmName("setTabsVisibleProperty")
        set(value) = setTabsVisible(value)

    var allTabsInFront: Boolean
        @JvmName("allTabsInFrontProperty")
        get() = isAllTabsInFront()
        @JvmName("setAllTabsInFrontProperty")
        set(value) = setAllTabsInFront(value)

    var switchOnDragHover: Boolean
        @JvmName("switchOnDragHoverProperty")
        get() = getSwitchOnDragHover()
        @JvmName("setSwitchOnDragHoverProperty")
        set(value) = setSwitchOnDragHover(value)

    var dragToRearrangeEnabled: Boolean
        @JvmName("dragToRearrangeEnabledProperty")
        get() = getDragToRearrangeEnabled()
        @JvmName("setDragToRearrangeEnabledProperty")
        set(value) = setDragToRearrangeEnabled(value)

    var tabsRearrangeGroup: Int
        @JvmName("tabsRearrangeGroupProperty")
        get() = getTabsRearrangeGroup()
        @JvmName("setTabsRearrangeGroupProperty")
        set(value) = setTabsRearrangeGroup(value)

    var useHiddenTabsForMinSize: Boolean
        @JvmName("useHiddenTabsForMinSizeProperty")
        get() = getUseHiddenTabsForMinSize()
        @JvmName("setUseHiddenTabsForMinSizeProperty")
        set(value) = setUseHiddenTabsForMinSize(value)

    var tabFocusMode: Control.FocusMode
        @JvmName("tabFocusModeProperty")
        get() = getTabFocusMode()
        @JvmName("setTabFocusModeProperty")
        set(value) = setTabFocusMode(value)

    var deselectEnabled: Boolean
        @JvmName("deselectEnabledProperty")
        get() = getDeselectEnabled()
        @JvmName("setDeselectEnabledProperty")
        set(value) = setDeselectEnabled(value)

    /**
     * Returns the number of tabs.
     *
     * Generated from Godot docs: TabContainer.get_tab_count
     */
    fun getTabCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTabCountBind, segment)
    }

    /**
     * The current tab index. When set, this index's `Control` node's `visible` property is set to
     * `true` and all others are set to `false`. A value of `-1` means that no tab is selected.
     *
     * Generated from Godot docs: TabContainer.set_current_tab
     */
    fun setCurrentTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setCurrentTabBind, segment, tabIdx)
    }

    /**
     * The current tab index. When set, this index's `Control` node's `visible` property is set to
     * `true` and all others are set to `false`. A value of `-1` means that no tab is selected.
     *
     * Generated from Godot docs: TabContainer.get_current_tab
     */
    fun getCurrentTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentTabBind, segment)
    }

    /**
     * Returns the previously active tab index.
     *
     * Generated from Godot docs: TabContainer.get_previous_tab
     */
    fun getPreviousTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPreviousTabBind, segment)
    }

    /**
     * Selects the first available tab with lower index than the currently selected. Returns `true` if
     * tab selection changed.
     *
     * Generated from Godot docs: TabContainer.select_previous_available
     */
    fun selectPreviousAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.selectPreviousAvailableBind, segment)
    }

    /**
     * Selects the first available tab with greater index than the currently selected. Returns `true`
     * if tab selection changed.
     *
     * Generated from Godot docs: TabContainer.select_next_available
     */
    fun selectNextAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.selectNextAvailableBind, segment)
    }

    /**
     * Returns the child `Control` node located at the active tab index.
     *
     * Generated from Godot docs: TabContainer.get_current_tab_control
     */
    fun getCurrentTabControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurrentTabControlBind, segment))
    }

    /**
     * Returns the `TabBar` contained in this container. Warning: This is a required internal node,
     * removing and freeing it or editing its tabs may cause a crash. If you wish to edit the tabs, use
     * the methods provided in `TabContainer`.
     *
     * Generated from Godot docs: TabContainer.get_tab_bar
     */
    fun getTabBar(): TabBar? {
        return TabBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTabBarBind, segment))
    }

    /**
     * Returns the `Control` node from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_control
     */
    fun getTabControl(tabIdx: Int): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTabControlBind, segment, tabIdx))
    }

    /**
     * The position at which tabs will be placed.
     *
     * Generated from Godot docs: TabContainer.set_tab_alignment
     */
    fun setTabAlignment(alignment: TabBar.AlignmentMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTabAlignmentBind, segment, alignment.value)
    }

    /**
     * The position at which tabs will be placed.
     *
     * Generated from Godot docs: TabContainer.get_tab_alignment
     */
    fun getTabAlignment(): TabBar.AlignmentMode {
        return TabBar.AlignmentMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTabAlignmentBind, segment))
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabContainer.set_tabs_position
     */
    fun setTabsPosition(tabsPosition: TabContainer.TabPosition) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTabsPositionBind, segment, tabsPosition.value)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabContainer.get_tabs_position
     */
    fun getTabsPosition(): TabContainer.TabPosition {
        return TabContainer.TabPosition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTabsPositionBind, segment))
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabContainer.set_clip_tabs
     */
    fun setClipTabs(clipTabs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setClipTabsBind, segment, clipTabs)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabContainer.get_clip_tabs
     */
    fun getClipTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getClipTabsBind, segment)
    }

    /**
     * If `true`, tabs are visible. If `false`, tabs' content and titles are hidden.
     *
     * Generated from Godot docs: TabContainer.set_tabs_visible
     */
    fun setTabsVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTabsVisibleBind, segment, visible)
    }

    /**
     * If `true`, tabs are visible. If `false`, tabs' content and titles are hidden.
     *
     * Generated from Godot docs: TabContainer.are_tabs_visible
     */
    fun areTabsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areTabsVisibleBind, segment)
    }

    /**
     * This doesn't do anything.
     *
     * Generated from Godot docs: TabContainer.set_all_tabs_in_front
     */
    fun setAllTabsInFront(isFront: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllTabsInFrontBind, segment, isFront)
    }

    /**
     * This doesn't do anything.
     *
     * Generated from Godot docs: TabContainer.is_all_tabs_in_front
     */
    fun isAllTabsInFront(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAllTabsInFrontBind, segment)
    }

    /**
     * Sets a custom title for the tab at index `tab_idx` (tab titles default to the name of the
     * indexed child node). Set it back to the child's name to make the tab default to it again.
     *
     * Generated from Godot docs: TabContainer.set_tab_title
     */
    fun setTabTitle(tabIdx: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTabTitleBind, segment, tabIdx, title)
    }

    /**
     * Returns the title of the tab at index `tab_idx`. Tab titles default to the name of the indexed
     * child node, but this can be overridden with `set_tab_title`.
     *
     * Generated from Godot docs: TabContainer.get_tab_title
     */
    fun getTabTitle(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTabTitleBind, segment, tabIdx)
    }

    /**
     * Sets a custom tooltip text for tab at index `tab_idx`. Note: By default, if the `tooltip` is
     * empty and the tab text is truncated (not all characters fit into the tab), the title will be
     * displayed as a tooltip. To hide the tooltip, assign `" "` as the `tooltip` text.
     *
     * Generated from Godot docs: TabContainer.set_tab_tooltip
     */
    fun setTabTooltip(tabIdx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTabTooltipBind, segment, tabIdx, tooltip)
    }

    /**
     * Returns the tooltip text of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_tooltip
     */
    fun getTabTooltip(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTabTooltipBind, segment, tabIdx)
    }

    /**
     * Sets an icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.set_tab_icon
     */
    fun setTabIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setTabIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the `Texture2D` for the tab at index `tab_idx` or `null` if the tab has no `Texture2D`.
     *
     * Generated from Godot docs: TabContainer.get_tab_icon
     */
    fun getTabIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTabIconBind, segment, tabIdx))
    }

    /**
     * Sets the maximum allowed width of the icon for the tab at index `tab_idx`. This limit is applied
     * on top of the default size of the icon and on top of `icon_max_width`. The height is adjusted
     * according to the icon's ratio.
     *
     * Generated from Godot docs: TabContainer.set_tab_icon_max_width
     */
    fun setTabIconMaxWidth(tabIdx: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setTabIconMaxWidthBind, segment, tabIdx, width)
    }

    /**
     * Returns the maximum allowed width of the icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_icon_max_width
     */
    fun getTabIconMaxWidth(tabIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getTabIconMaxWidthBind, segment, tabIdx)
    }

    /**
     * If `disabled` is `true`, disables the tab at index `tab_idx`, making it non-interactable.
     *
     * Generated from Godot docs: TabContainer.set_tab_disabled
     */
    fun setTabDisabled(tabIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setTabDisabledBind, segment, tabIdx, disabled)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is disabled.
     *
     * Generated from Godot docs: TabContainer.is_tab_disabled
     */
    fun isTabDisabled(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isTabDisabledBind, segment, tabIdx)
    }

    /**
     * If `hidden` is `true`, hides the tab at index `tab_idx`, making it disappear from the tab area.
     *
     * Generated from Godot docs: TabContainer.set_tab_hidden
     */
    fun setTabHidden(tabIdx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setTabHiddenBind, segment, tabIdx, hidden)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is hidden.
     *
     * Generated from Godot docs: TabContainer.is_tab_hidden
     */
    fun isTabHidden(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isTabHiddenBind, segment, tabIdx)
    }

    /**
     * Sets the metadata value for the tab at index `tab_idx`, which can be retrieved later using
     * `get_tab_metadata`.
     *
     * Generated from Godot docs: TabContainer.set_tab_metadata
     */
    fun setTabMetadata(tabIdx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setTabMetadataBind, segment, tabIdx, metadata)
    }

    /**
     * Returns the metadata value set to the tab at index `tab_idx` using `set_tab_metadata`. If no
     * metadata was previously set, returns `null` by default.
     *
     * Generated from Godot docs: TabContainer.get_tab_metadata
     */
    fun getTabMetadata(tabIdx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getTabMetadataBind, segment, tabIdx)
    }

    /**
     * Sets the button icon from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.set_tab_button_icon
     */
    fun setTabButtonIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setTabButtonIconBind, segment, tabIdx, icon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the button icon from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_button_icon
     */
    fun getTabButtonIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getTabButtonIconBind, segment, tabIdx))
    }

    /**
     * Returns the index of the tab at local coordinates `point`. Returns `-1` if the point is outside
     * the control boundaries or if there's no tab at the queried position.
     *
     * Generated from Godot docs: TabContainer.get_tab_idx_at_point
     */
    fun getTabIdxAtPoint(point: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(Binds.getTabIdxAtPointBind, segment, point)
    }

    /**
     * Returns the index of the tab tied to the given `control`. The control must be a child of the
     * `TabContainer`.
     *
     * Generated from Godot docs: TabContainer.get_tab_idx_from_control
     */
    fun getTabIdxFromControl(control: Control): Int {
        return ObjectCalls.ptrcallWithObjectArgRetInt(Binds.getTabIdxFromControlBind, segment, control.segment)
    }

    /**
     * If set on a `Popup` node instance, a popup menu icon appears in the top-right corner of the
     * `TabContainer` (setting it to `null` will make it go away). Clicking it will expand the `Popup`
     * node.
     *
     * Generated from Godot docs: TabContainer.set_popup
     */
    fun setPopup(popup: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPopupBind, segment, listOf(popup.segment))
    }

    /**
     * Returns the `Popup` node instance if one has been set already with `set_popup`. Warning: This is
     * a required internal node, removing and freeing it may cause a crash. If you wish to hide it or
     * any of its children, use their `Window.visible` property.
     *
     * Generated from Godot docs: TabContainer.get_popup
     */
    fun getPopup(): Popup? {
        return Popup.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPopupBind, segment))
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange.
     *
     * Generated from Godot docs: TabContainer.set_switch_on_drag_hover
     */
    fun setSwitchOnDragHover(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSwitchOnDragHoverBind, segment, enabled)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange.
     *
     * Generated from Godot docs: TabContainer.get_switch_on_drag_hover
     */
    fun getSwitchOnDragHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSwitchOnDragHoverBind, segment)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabContainer.set_drag_to_rearrange_enabled
     */
    fun setDragToRearrangeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDragToRearrangeEnabledBind, segment, enabled)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabContainer.get_drag_to_rearrange_enabled
     */
    fun getDragToRearrangeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDragToRearrangeEnabledBind, segment)
    }

    /**
     * `TabContainer`s with the same rearrange group ID will allow dragging the tabs between them.
     * Enable drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging
     * between `TabContainer`s.
     *
     * Generated from Godot docs: TabContainer.set_tabs_rearrange_group
     */
    fun setTabsRearrangeGroup(groupId: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTabsRearrangeGroupBind, segment, groupId)
    }

    /**
     * `TabContainer`s with the same rearrange group ID will allow dragging the tabs between them.
     * Enable drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging
     * between `TabContainer`s.
     *
     * Generated from Godot docs: TabContainer.get_tabs_rearrange_group
     */
    fun getTabsRearrangeGroup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTabsRearrangeGroupBind, segment)
    }

    /**
     * If `true`, child `Control` nodes that are hidden have their minimum size take into account in
     * the total, instead of only the currently visible one.
     *
     * Generated from Godot docs: TabContainer.set_use_hidden_tabs_for_min_size
     */
    fun setUseHiddenTabsForMinSize(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseHiddenTabsForMinSizeBind, segment, enabled)
    }

    /**
     * If `true`, child `Control` nodes that are hidden have their minimum size take into account in
     * the total, instead of only the currently visible one.
     *
     * Generated from Godot docs: TabContainer.get_use_hidden_tabs_for_min_size
     */
    fun getUseHiddenTabsForMinSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseHiddenTabsForMinSizeBind, segment)
    }

    /**
     * The focus access mode for the internal `TabBar` node.
     *
     * Generated from Godot docs: TabContainer.set_tab_focus_mode
     */
    fun setTabFocusMode(focusMode: Control.FocusMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTabFocusModeBind, segment, focusMode.value)
    }

    /**
     * The focus access mode for the internal `TabBar` node.
     *
     * Generated from Godot docs: TabContainer.get_tab_focus_mode
     */
    fun getTabFocusMode(): Control.FocusMode {
        return Control.FocusMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTabFocusModeBind, segment))
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the `current_tab` to
     * deselect it. Only the tab header will be shown if no tabs are selected.
     *
     * Generated from Godot docs: TabContainer.set_deselect_enabled
     */
    fun setDeselectEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeselectEnabledBind, segment, enabled)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the `current_tab` to
     * deselect it. Only the tab header will be shown if no tabs are selected.
     *
     * Generated from Godot docs: TabContainer.get_deselect_enabled
     */
    fun getDeselectEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDeselectEnabledBind, segment)
    }

    /** Signal `active_tab_rearranged(idx_to: int)`; see [TypedSignal]. */
    val activeTabRearranged: Signal1<Long>
        @JvmName("activeTabRearrangedTypedSignal")
        get() = Signal1(this, "active_tab_rearranged", SignalArgType.LONG)

    /** Signal `tab_changed(tab: int)`; see [TypedSignal]. */
    val tabChanged: Signal1<Long>
        @JvmName("tabChangedTypedSignal")
        get() = Signal1(this, "tab_changed", SignalArgType.LONG)

    /** Signal `tab_clicked(tab: int)`; see [TypedSignal]. */
    val tabClicked: Signal1<Long>
        @JvmName("tabClickedTypedSignal")
        get() = Signal1(this, "tab_clicked", SignalArgType.LONG)

    /** Signal `tab_hovered(tab: int)`; see [TypedSignal]. */
    val tabHovered: Signal1<Long>
        @JvmName("tabHoveredTypedSignal")
        get() = Signal1(this, "tab_hovered", SignalArgType.LONG)

    /** Signal `tab_selected(tab: int)`; see [TypedSignal]. */
    val tabSelected: Signal1<Long>
        @JvmName("tabSelectedTypedSignal")
        get() = Signal1(this, "tab_selected", SignalArgType.LONG)

    /** Signal `tab_button_pressed(tab: int)`; see [TypedSignal]. */
    val tabButtonPressed: Signal1<Long>
        @JvmName("tabButtonPressedTypedSignal")
        get() = Signal1(this, "tab_button_pressed", SignalArgType.LONG)

    /** Signal `pre_popup_pressed()`; see [TypedSignal]. */
    val prePopupPressed: Signal0
        @JvmName("prePopupPressedTypedSignal")
        get() = Signal0(this, "pre_popup_pressed")

    object Signals {
        const val activeTabRearranged: String = "active_tab_rearranged"
        const val tabChanged: String = "tab_changed"
        const val tabClicked: String = "tab_clicked"
        const val tabHovered: String = "tab_hovered"
        const val tabSelected: String = "tab_selected"
        const val tabButtonPressed: String = "tab_button_pressed"
        const val prePopupPressed: String = "pre_popup_pressed"
    }

    /**
     * Godot's `TabContainer.TabPosition` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TabContainer.TabPosition.<NAME>`).
     *
     * Generated from Godot docs: TabContainer.TabPosition
     */
    @JvmInline
    value class TabPosition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Places the tab bar at the top.
             *
             * Generated from Godot docs: TabContainer.POSITION_TOP
             */
            val TOP: TabPosition get() = TabPosition(0L)
            /**
             * Places the tab bar at the bottom. The tab bar's `StyleBox` will be flipped vertically.
             *
             * Generated from Godot docs: TabContainer.POSITION_BOTTOM
             */
            val BOTTOM: TabPosition get() = TabPosition(1L)
            /**
             * Represents the size of the `TabPosition` enum.
             *
             * Generated from Godot docs: TabContainer.POSITION_MAX
             */
            val MAX: TabPosition get() = TabPosition(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TabContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TabContainer? =
            if (handle.address() == 0L) null else TabContainer(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TAB_COUNT_HASH = 3905245786L
        @JvmField
        val getTabCountBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_count", GET_TAB_COUNT_HASH)

        private const val SET_CURRENT_TAB_HASH = 1286410249L
        @JvmField
        val setCurrentTabBind =
            ObjectCalls.getMethodBind("TabContainer", "set_current_tab", SET_CURRENT_TAB_HASH)

        private const val GET_CURRENT_TAB_HASH = 3905245786L
        @JvmField
        val getCurrentTabBind =
            ObjectCalls.getMethodBind("TabContainer", "get_current_tab", GET_CURRENT_TAB_HASH)

        private const val GET_PREVIOUS_TAB_HASH = 3905245786L
        @JvmField
        val getPreviousTabBind =
            ObjectCalls.getMethodBind("TabContainer", "get_previous_tab", GET_PREVIOUS_TAB_HASH)

        private const val SELECT_PREVIOUS_AVAILABLE_HASH = 2240911060L
        @JvmField
        val selectPreviousAvailableBind =
            ObjectCalls.getMethodBind("TabContainer", "select_previous_available", SELECT_PREVIOUS_AVAILABLE_HASH)

        private const val SELECT_NEXT_AVAILABLE_HASH = 2240911060L
        @JvmField
        val selectNextAvailableBind =
            ObjectCalls.getMethodBind("TabContainer", "select_next_available", SELECT_NEXT_AVAILABLE_HASH)

        private const val GET_CURRENT_TAB_CONTROL_HASH = 2783021301L
        @JvmField
        val getCurrentTabControlBind =
            ObjectCalls.getMethodBind("TabContainer", "get_current_tab_control", GET_CURRENT_TAB_CONTROL_HASH)

        private const val GET_TAB_BAR_HASH = 1865451809L
        @JvmField
        val getTabBarBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_bar", GET_TAB_BAR_HASH)

        private const val GET_TAB_CONTROL_HASH = 1065994134L
        @JvmField
        val getTabControlBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_control", GET_TAB_CONTROL_HASH)

        private const val SET_TAB_ALIGNMENT_HASH = 2413632353L
        @JvmField
        val setTabAlignmentBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_alignment", SET_TAB_ALIGNMENT_HASH)

        private const val GET_TAB_ALIGNMENT_HASH = 2178122193L
        @JvmField
        val getTabAlignmentBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_alignment", GET_TAB_ALIGNMENT_HASH)

        private const val SET_TABS_POSITION_HASH = 256673370L
        @JvmField
        val setTabsPositionBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_position", SET_TABS_POSITION_HASH)

        private const val GET_TABS_POSITION_HASH = 919937023L
        @JvmField
        val getTabsPositionBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tabs_position", GET_TABS_POSITION_HASH)

        private const val SET_CLIP_TABS_HASH = 2586408642L
        @JvmField
        val setClipTabsBind =
            ObjectCalls.getMethodBind("TabContainer", "set_clip_tabs", SET_CLIP_TABS_HASH)

        private const val GET_CLIP_TABS_HASH = 36873697L
        @JvmField
        val getClipTabsBind =
            ObjectCalls.getMethodBind("TabContainer", "get_clip_tabs", GET_CLIP_TABS_HASH)

        private const val SET_TABS_VISIBLE_HASH = 2586408642L
        @JvmField
        val setTabsVisibleBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_visible", SET_TABS_VISIBLE_HASH)

        private const val ARE_TABS_VISIBLE_HASH = 36873697L
        @JvmField
        val areTabsVisibleBind =
            ObjectCalls.getMethodBind("TabContainer", "are_tabs_visible", ARE_TABS_VISIBLE_HASH)

        private const val SET_ALL_TABS_IN_FRONT_HASH = 2586408642L
        @JvmField
        val setAllTabsInFrontBind =
            ObjectCalls.getMethodBind("TabContainer", "set_all_tabs_in_front", SET_ALL_TABS_IN_FRONT_HASH)

        private const val IS_ALL_TABS_IN_FRONT_HASH = 36873697L
        @JvmField
        val isAllTabsInFrontBind =
            ObjectCalls.getMethodBind("TabContainer", "is_all_tabs_in_front", IS_ALL_TABS_IN_FRONT_HASH)

        private const val SET_TAB_TITLE_HASH = 501894301L
        @JvmField
        val setTabTitleBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_title", SET_TAB_TITLE_HASH)

        private const val GET_TAB_TITLE_HASH = 844755477L
        @JvmField
        val getTabTitleBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_title", GET_TAB_TITLE_HASH)

        private const val SET_TAB_TOOLTIP_HASH = 501894301L
        @JvmField
        val setTabTooltipBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_tooltip", SET_TAB_TOOLTIP_HASH)

        private const val GET_TAB_TOOLTIP_HASH = 844755477L
        @JvmField
        val getTabTooltipBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_tooltip", GET_TAB_TOOLTIP_HASH)

        private const val SET_TAB_ICON_HASH = 666127730L
        @JvmField
        val setTabIconBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_icon", SET_TAB_ICON_HASH)

        private const val GET_TAB_ICON_HASH = 3536238170L
        @JvmField
        val getTabIconBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_icon", GET_TAB_ICON_HASH)

        private const val SET_TAB_ICON_MAX_WIDTH_HASH = 3937882851L
        @JvmField
        val setTabIconMaxWidthBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_icon_max_width", SET_TAB_ICON_MAX_WIDTH_HASH)

        private const val GET_TAB_ICON_MAX_WIDTH_HASH = 923996154L
        @JvmField
        val getTabIconMaxWidthBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_icon_max_width", GET_TAB_ICON_MAX_WIDTH_HASH)

        private const val SET_TAB_DISABLED_HASH = 300928843L
        @JvmField
        val setTabDisabledBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_disabled", SET_TAB_DISABLED_HASH)

        private const val IS_TAB_DISABLED_HASH = 1116898809L
        @JvmField
        val isTabDisabledBind =
            ObjectCalls.getMethodBind("TabContainer", "is_tab_disabled", IS_TAB_DISABLED_HASH)

        private const val SET_TAB_HIDDEN_HASH = 300928843L
        @JvmField
        val setTabHiddenBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_hidden", SET_TAB_HIDDEN_HASH)

        private const val IS_TAB_HIDDEN_HASH = 1116898809L
        @JvmField
        val isTabHiddenBind =
            ObjectCalls.getMethodBind("TabContainer", "is_tab_hidden", IS_TAB_HIDDEN_HASH)

        private const val SET_TAB_METADATA_HASH = 2152698145L
        @JvmField
        val setTabMetadataBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_metadata", SET_TAB_METADATA_HASH)

        private const val GET_TAB_METADATA_HASH = 4227898402L
        @JvmField
        val getTabMetadataBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_metadata", GET_TAB_METADATA_HASH)

        private const val SET_TAB_BUTTON_ICON_HASH = 666127730L
        @JvmField
        val setTabButtonIconBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_button_icon", SET_TAB_BUTTON_ICON_HASH)

        private const val GET_TAB_BUTTON_ICON_HASH = 3536238170L
        @JvmField
        val getTabButtonIconBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_button_icon", GET_TAB_BUTTON_ICON_HASH)

        private const val GET_TAB_IDX_AT_POINT_HASH = 3820158470L
        @JvmField
        val getTabIdxAtPointBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_idx_at_point", GET_TAB_IDX_AT_POINT_HASH)

        private const val GET_TAB_IDX_FROM_CONTROL_HASH = 2787397975L
        @JvmField
        val getTabIdxFromControlBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_idx_from_control", GET_TAB_IDX_FROM_CONTROL_HASH)

        private const val SET_POPUP_HASH = 1078189570L
        @JvmField
        val setPopupBind =
            ObjectCalls.getMethodBind("TabContainer", "set_popup", SET_POPUP_HASH)

        private const val GET_POPUP_HASH = 111095082L
        @JvmField
        val getPopupBind =
            ObjectCalls.getMethodBind("TabContainer", "get_popup", GET_POPUP_HASH)

        private const val SET_SWITCH_ON_DRAG_HOVER_HASH = 2586408642L
        @JvmField
        val setSwitchOnDragHoverBind =
            ObjectCalls.getMethodBind("TabContainer", "set_switch_on_drag_hover", SET_SWITCH_ON_DRAG_HOVER_HASH)

        private const val GET_SWITCH_ON_DRAG_HOVER_HASH = 36873697L
        @JvmField
        val getSwitchOnDragHoverBind =
            ObjectCalls.getMethodBind("TabContainer", "get_switch_on_drag_hover", GET_SWITCH_ON_DRAG_HOVER_HASH)

        private const val SET_DRAG_TO_REARRANGE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDragToRearrangeEnabledBind =
            ObjectCalls.getMethodBind("TabContainer", "set_drag_to_rearrange_enabled", SET_DRAG_TO_REARRANGE_ENABLED_HASH)

        private const val GET_DRAG_TO_REARRANGE_ENABLED_HASH = 36873697L
        @JvmField
        val getDragToRearrangeEnabledBind =
            ObjectCalls.getMethodBind("TabContainer", "get_drag_to_rearrange_enabled", GET_DRAG_TO_REARRANGE_ENABLED_HASH)

        private const val SET_TABS_REARRANGE_GROUP_HASH = 1286410249L
        @JvmField
        val setTabsRearrangeGroupBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_rearrange_group", SET_TABS_REARRANGE_GROUP_HASH)

        private const val GET_TABS_REARRANGE_GROUP_HASH = 3905245786L
        @JvmField
        val getTabsRearrangeGroupBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tabs_rearrange_group", GET_TABS_REARRANGE_GROUP_HASH)

        private const val SET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH = 2586408642L
        @JvmField
        val setUseHiddenTabsForMinSizeBind =
            ObjectCalls.getMethodBind("TabContainer", "set_use_hidden_tabs_for_min_size", SET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH)

        private const val GET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH = 36873697L
        @JvmField
        val getUseHiddenTabsForMinSizeBind =
            ObjectCalls.getMethodBind("TabContainer", "get_use_hidden_tabs_for_min_size", GET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH)

        private const val SET_TAB_FOCUS_MODE_HASH = 3232914922L
        @JvmField
        val setTabFocusModeBind =
            ObjectCalls.getMethodBind("TabContainer", "set_tab_focus_mode", SET_TAB_FOCUS_MODE_HASH)

        private const val GET_TAB_FOCUS_MODE_HASH = 2132829277L
        @JvmField
        val getTabFocusModeBind =
            ObjectCalls.getMethodBind("TabContainer", "get_tab_focus_mode", GET_TAB_FOCUS_MODE_HASH)

        private const val SET_DESELECT_ENABLED_HASH = 2586408642L
        @JvmField
        val setDeselectEnabledBind =
            ObjectCalls.getMethodBind("TabContainer", "set_deselect_enabled", SET_DESELECT_ENABLED_HASH)

        private const val GET_DESELECT_ENABLED_HASH = 36873697L
        @JvmField
        val getDeselectEnabledBind =
            ObjectCalls.getMethodBind("TabContainer", "get_deselect_enabled", GET_DESELECT_ENABLED_HASH)
    }
}
