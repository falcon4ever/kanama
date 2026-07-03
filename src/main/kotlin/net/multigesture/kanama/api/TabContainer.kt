package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A container that creates a tab for each child control, displaying only the active tab's control.
 *
 * Generated from Godot docs: TabContainer
 */
class TabContainer(handle: MemorySegment) : Container(handle) {
    var tabAlignment: Long
        @JvmName("tabAlignmentProperty")
        get() = getTabAlignment()
        @JvmName("setTabAlignmentProperty")
        set(value) = setTabAlignment(value)

    var currentTab: Int
        @JvmName("currentTabProperty")
        get() = getCurrentTab()
        @JvmName("setCurrentTabProperty")
        set(value) = setCurrentTab(value)

    var tabsPosition: Long
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

    var tabFocusMode: Long
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
        return ObjectCalls.ptrcallNoArgsRetInt(getTabCountBind, handle)
    }

    /**
     * The current tab index. When set, this index's `Control` node's `visible` property is set to
     * `true` and all others are set to `false`. A value of `-1` means that no tab is selected.
     *
     * Generated from Godot docs: TabContainer.set_current_tab
     */
    fun setCurrentTab(tabIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(setCurrentTabBind, handle, tabIdx)
    }

    /**
     * The current tab index. When set, this index's `Control` node's `visible` property is set to
     * `true` and all others are set to `false`. A value of `-1` means that no tab is selected.
     *
     * Generated from Godot docs: TabContainer.get_current_tab
     */
    fun getCurrentTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentTabBind, handle)
    }

    /**
     * Returns the previously active tab index.
     *
     * Generated from Godot docs: TabContainer.get_previous_tab
     */
    fun getPreviousTab(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPreviousTabBind, handle)
    }

    /**
     * Selects the first available tab with lower index than the currently selected. Returns `true` if
     * tab selection changed.
     *
     * Generated from Godot docs: TabContainer.select_previous_available
     */
    fun selectPreviousAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectPreviousAvailableBind, handle)
    }

    /**
     * Selects the first available tab with greater index than the currently selected. Returns `true`
     * if tab selection changed.
     *
     * Generated from Godot docs: TabContainer.select_next_available
     */
    fun selectNextAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(selectNextAvailableBind, handle)
    }

    /**
     * Returns the child `Control` node located at the active tab index.
     *
     * Generated from Godot docs: TabContainer.get_current_tab_control
     */
    fun getCurrentTabControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentTabControlBind, handle))
    }

    /**
     * Returns the `TabBar` contained in this container. Warning: This is a required internal node,
     * removing and freeing it or editing its tabs may cause a crash. If you wish to edit the tabs, use
     * the methods provided in `TabContainer`.
     *
     * Generated from Godot docs: TabContainer.get_tab_bar
     */
    fun getTabBar(): TabBar? {
        return TabBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTabBarBind, handle))
    }

    /**
     * Returns the `Control` node from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_control
     */
    fun getTabControl(tabIdx: Int): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabControlBind, handle, tabIdx))
    }

    /**
     * The position at which tabs will be placed.
     *
     * Generated from Godot docs: TabContainer.set_tab_alignment
     */
    fun setTabAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabAlignmentBind, handle, alignment)
    }

    /**
     * The position at which tabs will be placed.
     *
     * Generated from Godot docs: TabContainer.get_tab_alignment
     */
    fun getTabAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabAlignmentBind, handle)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabContainer.set_tabs_position
     */
    fun setTabsPosition(tabsPosition: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabsPositionBind, handle, tabsPosition)
    }

    /**
     * The horizontal alignment of the tabs.
     *
     * Generated from Godot docs: TabContainer.get_tabs_position
     */
    fun getTabsPosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabsPositionBind, handle)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabContainer.set_clip_tabs
     */
    fun setClipTabs(clipTabs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTabsBind, handle, clipTabs)
    }

    /**
     * If `true`, tabs overflowing this node's width will be hidden, displaying two navigation buttons
     * instead. Otherwise, this node's minimum size is updated so that all tabs are visible.
     *
     * Generated from Godot docs: TabContainer.get_clip_tabs
     */
    fun getClipTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getClipTabsBind, handle)
    }

    /**
     * If `true`, tabs are visible. If `false`, tabs' content and titles are hidden.
     *
     * Generated from Godot docs: TabContainer.set_tabs_visible
     */
    fun setTabsVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTabsVisibleBind, handle, visible)
    }

    /**
     * If `true`, tabs are visible. If `false`, tabs' content and titles are hidden.
     *
     * Generated from Godot docs: TabContainer.are_tabs_visible
     */
    fun areTabsVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areTabsVisibleBind, handle)
    }

    /**
     * This doesn't do anything.
     *
     * Generated from Godot docs: TabContainer.set_all_tabs_in_front
     */
    fun setAllTabsInFront(isFront: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllTabsInFrontBind, handle, isFront)
    }

    /**
     * This doesn't do anything.
     *
     * Generated from Godot docs: TabContainer.is_all_tabs_in_front
     */
    fun isAllTabsInFront(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllTabsInFrontBind, handle)
    }

    /**
     * Sets a custom title for the tab at index `tab_idx` (tab titles default to the name of the
     * indexed child node). Set it back to the child's name to make the tab default to it again.
     *
     * Generated from Godot docs: TabContainer.set_tab_title
     */
    fun setTabTitle(tabIdx: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTitleBind, handle, tabIdx, title)
    }

    /**
     * Returns the title of the tab at index `tab_idx`. Tab titles default to the name of the indexed
     * child node, but this can be overridden with `set_tab_title`.
     *
     * Generated from Godot docs: TabContainer.get_tab_title
     */
    fun getTabTitle(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTitleBind, handle, tabIdx)
    }

    /**
     * Sets a custom tooltip text for tab at index `tab_idx`. Note: By default, if the `tooltip` is
     * empty and the tab text is truncated (not all characters fit into the tab), the title will be
     * displayed as a tooltip. To hide the tooltip, assign `" "` as the `tooltip` text.
     *
     * Generated from Godot docs: TabContainer.set_tab_tooltip
     */
    fun setTabTooltip(tabIdx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTabTooltipBind, handle, tabIdx, tooltip)
    }

    /**
     * Returns the tooltip text of the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_tooltip
     */
    fun getTabTooltip(tabIdx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTabTooltipBind, handle, tabIdx)
    }

    /**
     * Sets an icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.set_tab_icon
     */
    fun setTabIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabIconBind, handle, tabIdx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `Texture2D` for the tab at index `tab_idx` or `null` if the tab has no `Texture2D`.
     *
     * Generated from Godot docs: TabContainer.get_tab_icon
     */
    fun getTabIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabIconBind, handle, tabIdx))
    }

    /**
     * Sets the maximum allowed width of the icon for the tab at index `tab_idx`. This limit is applied
     * on top of the default size of the icon and on top of `icon_max_width`. The height is adjusted
     * according to the icon's ratio.
     *
     * Generated from Godot docs: TabContainer.set_tab_icon_max_width
     */
    fun setTabIconMaxWidth(tabIdx: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTabIconMaxWidthBind, handle, tabIdx, width)
    }

    /**
     * Returns the maximum allowed width of the icon for the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_icon_max_width
     */
    fun getTabIconMaxWidth(tabIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTabIconMaxWidthBind, handle, tabIdx)
    }

    /**
     * If `disabled` is `true`, disables the tab at index `tab_idx`, making it non-interactable.
     *
     * Generated from Godot docs: TabContainer.set_tab_disabled
     */
    fun setTabDisabled(tabIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabDisabledBind, handle, tabIdx, disabled)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is disabled.
     *
     * Generated from Godot docs: TabContainer.is_tab_disabled
     */
    fun isTabDisabled(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabDisabledBind, handle, tabIdx)
    }

    /**
     * If `hidden` is `true`, hides the tab at index `tab_idx`, making it disappear from the tab area.
     *
     * Generated from Godot docs: TabContainer.set_tab_hidden
     */
    fun setTabHidden(tabIdx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTabHiddenBind, handle, tabIdx, hidden)
    }

    /**
     * Returns `true` if the tab at index `tab_idx` is hidden.
     *
     * Generated from Godot docs: TabContainer.is_tab_hidden
     */
    fun isTabHidden(tabIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTabHiddenBind, handle, tabIdx)
    }

    /**
     * Sets the metadata value for the tab at index `tab_idx`, which can be retrieved later using
     * `get_tab_metadata`.
     *
     * Generated from Godot docs: TabContainer.set_tab_metadata
     */
    fun setTabMetadata(tabIdx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setTabMetadataBind, handle, tabIdx, metadata)
    }

    /**
     * Returns the metadata value set to the tab at index `tab_idx` using `set_tab_metadata`. If no
     * metadata was previously set, returns `null` by default.
     *
     * Generated from Godot docs: TabContainer.get_tab_metadata
     */
    fun getTabMetadata(tabIdx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getTabMetadataBind, handle, tabIdx)
    }

    /**
     * Sets the button icon from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.set_tab_button_icon
     */
    fun setTabButtonIcon(tabIdx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setTabButtonIconBind, handle, tabIdx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the button icon from the tab at index `tab_idx`.
     *
     * Generated from Godot docs: TabContainer.get_tab_button_icon
     */
    fun getTabButtonIcon(tabIdx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getTabButtonIconBind, handle, tabIdx))
    }

    /**
     * Returns the index of the tab at local coordinates `point`. Returns `-1` if the point is outside
     * the control boundaries or if there's no tab at the queried position.
     *
     * Generated from Godot docs: TabContainer.get_tab_idx_at_point
     */
    fun getTabIdxAtPoint(point: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getTabIdxAtPointBind, handle, point)
    }

    /**
     * Returns the index of the tab tied to the given `control`. The control must be a child of the
     * `TabContainer`.
     *
     * Generated from Godot docs: TabContainer.get_tab_idx_from_control
     */
    fun getTabIdxFromControl(control: Control): Int {
        return ObjectCalls.ptrcallWithObjectArgRetInt(getTabIdxFromControlBind, handle, control.handle)
    }

    /**
     * If set on a `Popup` node instance, a popup menu icon appears in the top-right corner of the
     * `TabContainer` (setting it to `null` will make it go away). Clicking it will expand the `Popup`
     * node.
     *
     * Generated from Godot docs: TabContainer.set_popup
     */
    fun setPopup(popup: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setPopupBind, handle, listOf(popup.handle))
    }

    /**
     * Returns the `Popup` node instance if one has been set already with `set_popup`. Warning: This is
     * a required internal node, removing and freeing it may cause a crash. If you wish to hide it or
     * any of its children, use their `Window.visible` property.
     *
     * Generated from Godot docs: TabContainer.get_popup
     */
    fun getPopup(): Popup? {
        return Popup.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPopupBind, handle))
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange.
     *
     * Generated from Godot docs: TabContainer.set_switch_on_drag_hover
     */
    fun setSwitchOnDragHover(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSwitchOnDragHoverBind, handle, enabled)
    }

    /**
     * If `true`, hovering over a tab while dragging something will switch to that tab. Does not have
     * effect when hovering another tab to rearrange.
     *
     * Generated from Godot docs: TabContainer.get_switch_on_drag_hover
     */
    fun getSwitchOnDragHover(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSwitchOnDragHoverBind, handle)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabContainer.set_drag_to_rearrange_enabled
     */
    fun setDragToRearrangeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragToRearrangeEnabledBind, handle, enabled)
    }

    /**
     * If `true`, tabs can be rearranged with mouse drag.
     *
     * Generated from Godot docs: TabContainer.get_drag_to_rearrange_enabled
     */
    fun getDragToRearrangeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDragToRearrangeEnabledBind, handle)
    }

    /**
     * `TabContainer`s with the same rearrange group ID will allow dragging the tabs between them.
     * Enable drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging
     * between `TabContainer`s.
     *
     * Generated from Godot docs: TabContainer.set_tabs_rearrange_group
     */
    fun setTabsRearrangeGroup(groupId: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabsRearrangeGroupBind, handle, groupId)
    }

    /**
     * `TabContainer`s with the same rearrange group ID will allow dragging the tabs between them.
     * Enable drag with `drag_to_rearrange_enabled`. Setting this to `-1` will disable rearranging
     * between `TabContainer`s.
     *
     * Generated from Godot docs: TabContainer.get_tabs_rearrange_group
     */
    fun getTabsRearrangeGroup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabsRearrangeGroupBind, handle)
    }

    /**
     * If `true`, child `Control` nodes that are hidden have their minimum size take into account in
     * the total, instead of only the currently visible one.
     *
     * Generated from Godot docs: TabContainer.set_use_hidden_tabs_for_min_size
     */
    fun setUseHiddenTabsForMinSize(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseHiddenTabsForMinSizeBind, handle, enabled)
    }

    /**
     * If `true`, child `Control` nodes that are hidden have their minimum size take into account in
     * the total, instead of only the currently visible one.
     *
     * Generated from Godot docs: TabContainer.get_use_hidden_tabs_for_min_size
     */
    fun getUseHiddenTabsForMinSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseHiddenTabsForMinSizeBind, handle)
    }

    /**
     * The focus access mode for the internal `TabBar` node.
     *
     * Generated from Godot docs: TabContainer.set_tab_focus_mode
     */
    fun setTabFocusMode(focusMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTabFocusModeBind, handle, focusMode)
    }

    /**
     * The focus access mode for the internal `TabBar` node.
     *
     * Generated from Godot docs: TabContainer.get_tab_focus_mode
     */
    fun getTabFocusMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTabFocusModeBind, handle)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the `current_tab` to
     * deselect it. Only the tab header will be shown if no tabs are selected.
     *
     * Generated from Godot docs: TabContainer.set_deselect_enabled
     */
    fun setDeselectEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectEnabledBind, handle, enabled)
    }

    /**
     * If `true`, all tabs can be deselected so that no tab is selected. Click on the `current_tab` to
     * deselect it. Only the tab header will be shown if no tabs are selected.
     *
     * Generated from Godot docs: TabContainer.get_deselect_enabled
     */
    fun getDeselectEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDeselectEnabledBind, handle)
    }

    object Signals {
        const val activeTabRearranged: String = "active_tab_rearranged"
        const val tabChanged: String = "tab_changed"
        const val tabClicked: String = "tab_clicked"
        const val tabHovered: String = "tab_hovered"
        const val tabSelected: String = "tab_selected"
        const val tabButtonPressed: String = "tab_button_pressed"
        const val prePopupPressed: String = "pre_popup_pressed"
    }

    companion object {
        const val POSITION_TOP: Long = 0L
        const val POSITION_BOTTOM: Long = 1L
        const val POSITION_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TabContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TabContainer? =
            if (handle.address() == 0L) null else TabContainer(handle)

        private const val GET_TAB_COUNT_HASH = 3905245786L
        private val getTabCountBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_count", GET_TAB_COUNT_HASH)
        }

        private const val SET_CURRENT_TAB_HASH = 1286410249L
        private val setCurrentTabBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_current_tab", SET_CURRENT_TAB_HASH)
        }

        private const val GET_CURRENT_TAB_HASH = 3905245786L
        private val getCurrentTabBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_current_tab", GET_CURRENT_TAB_HASH)
        }

        private const val GET_PREVIOUS_TAB_HASH = 3905245786L
        private val getPreviousTabBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_previous_tab", GET_PREVIOUS_TAB_HASH)
        }

        private const val SELECT_PREVIOUS_AVAILABLE_HASH = 2240911060L
        private val selectPreviousAvailableBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "select_previous_available", SELECT_PREVIOUS_AVAILABLE_HASH)
        }

        private const val SELECT_NEXT_AVAILABLE_HASH = 2240911060L
        private val selectNextAvailableBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "select_next_available", SELECT_NEXT_AVAILABLE_HASH)
        }

        private const val GET_CURRENT_TAB_CONTROL_HASH = 2783021301L
        private val getCurrentTabControlBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_current_tab_control", GET_CURRENT_TAB_CONTROL_HASH)
        }

        private const val GET_TAB_BAR_HASH = 1865451809L
        private val getTabBarBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_bar", GET_TAB_BAR_HASH)
        }

        private const val GET_TAB_CONTROL_HASH = 1065994134L
        private val getTabControlBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_control", GET_TAB_CONTROL_HASH)
        }

        private const val SET_TAB_ALIGNMENT_HASH = 2413632353L
        private val setTabAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_alignment", SET_TAB_ALIGNMENT_HASH)
        }

        private const val GET_TAB_ALIGNMENT_HASH = 2178122193L
        private val getTabAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_alignment", GET_TAB_ALIGNMENT_HASH)
        }

        private const val SET_TABS_POSITION_HASH = 256673370L
        private val setTabsPositionBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_position", SET_TABS_POSITION_HASH)
        }

        private const val GET_TABS_POSITION_HASH = 919937023L
        private val getTabsPositionBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tabs_position", GET_TABS_POSITION_HASH)
        }

        private const val SET_CLIP_TABS_HASH = 2586408642L
        private val setClipTabsBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_clip_tabs", SET_CLIP_TABS_HASH)
        }

        private const val GET_CLIP_TABS_HASH = 36873697L
        private val getClipTabsBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_clip_tabs", GET_CLIP_TABS_HASH)
        }

        private const val SET_TABS_VISIBLE_HASH = 2586408642L
        private val setTabsVisibleBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_visible", SET_TABS_VISIBLE_HASH)
        }

        private const val ARE_TABS_VISIBLE_HASH = 36873697L
        private val areTabsVisibleBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "are_tabs_visible", ARE_TABS_VISIBLE_HASH)
        }

        private const val SET_ALL_TABS_IN_FRONT_HASH = 2586408642L
        private val setAllTabsInFrontBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_all_tabs_in_front", SET_ALL_TABS_IN_FRONT_HASH)
        }

        private const val IS_ALL_TABS_IN_FRONT_HASH = 36873697L
        private val isAllTabsInFrontBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "is_all_tabs_in_front", IS_ALL_TABS_IN_FRONT_HASH)
        }

        private const val SET_TAB_TITLE_HASH = 501894301L
        private val setTabTitleBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_title", SET_TAB_TITLE_HASH)
        }

        private const val GET_TAB_TITLE_HASH = 844755477L
        private val getTabTitleBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_title", GET_TAB_TITLE_HASH)
        }

        private const val SET_TAB_TOOLTIP_HASH = 501894301L
        private val setTabTooltipBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_tooltip", SET_TAB_TOOLTIP_HASH)
        }

        private const val GET_TAB_TOOLTIP_HASH = 844755477L
        private val getTabTooltipBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_tooltip", GET_TAB_TOOLTIP_HASH)
        }

        private const val SET_TAB_ICON_HASH = 666127730L
        private val setTabIconBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_icon", SET_TAB_ICON_HASH)
        }

        private const val GET_TAB_ICON_HASH = 3536238170L
        private val getTabIconBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_icon", GET_TAB_ICON_HASH)
        }

        private const val SET_TAB_ICON_MAX_WIDTH_HASH = 3937882851L
        private val setTabIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_icon_max_width", SET_TAB_ICON_MAX_WIDTH_HASH)
        }

        private const val GET_TAB_ICON_MAX_WIDTH_HASH = 923996154L
        private val getTabIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_icon_max_width", GET_TAB_ICON_MAX_WIDTH_HASH)
        }

        private const val SET_TAB_DISABLED_HASH = 300928843L
        private val setTabDisabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_disabled", SET_TAB_DISABLED_HASH)
        }

        private const val IS_TAB_DISABLED_HASH = 1116898809L
        private val isTabDisabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "is_tab_disabled", IS_TAB_DISABLED_HASH)
        }

        private const val SET_TAB_HIDDEN_HASH = 300928843L
        private val setTabHiddenBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_hidden", SET_TAB_HIDDEN_HASH)
        }

        private const val IS_TAB_HIDDEN_HASH = 1116898809L
        private val isTabHiddenBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "is_tab_hidden", IS_TAB_HIDDEN_HASH)
        }

        private const val SET_TAB_METADATA_HASH = 2152698145L
        private val setTabMetadataBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_metadata", SET_TAB_METADATA_HASH)
        }

        private const val GET_TAB_METADATA_HASH = 4227898402L
        private val getTabMetadataBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_metadata", GET_TAB_METADATA_HASH)
        }

        private const val SET_TAB_BUTTON_ICON_HASH = 666127730L
        private val setTabButtonIconBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_button_icon", SET_TAB_BUTTON_ICON_HASH)
        }

        private const val GET_TAB_BUTTON_ICON_HASH = 3536238170L
        private val getTabButtonIconBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_button_icon", GET_TAB_BUTTON_ICON_HASH)
        }

        private const val GET_TAB_IDX_AT_POINT_HASH = 3820158470L
        private val getTabIdxAtPointBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_idx_at_point", GET_TAB_IDX_AT_POINT_HASH)
        }

        private const val GET_TAB_IDX_FROM_CONTROL_HASH = 2787397975L
        private val getTabIdxFromControlBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_idx_from_control", GET_TAB_IDX_FROM_CONTROL_HASH)
        }

        private const val SET_POPUP_HASH = 1078189570L
        private val setPopupBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_popup", SET_POPUP_HASH)
        }

        private const val GET_POPUP_HASH = 111095082L
        private val getPopupBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_popup", GET_POPUP_HASH)
        }

        private const val SET_SWITCH_ON_DRAG_HOVER_HASH = 2586408642L
        private val setSwitchOnDragHoverBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_switch_on_drag_hover", SET_SWITCH_ON_DRAG_HOVER_HASH)
        }

        private const val GET_SWITCH_ON_DRAG_HOVER_HASH = 36873697L
        private val getSwitchOnDragHoverBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_switch_on_drag_hover", GET_SWITCH_ON_DRAG_HOVER_HASH)
        }

        private const val SET_DRAG_TO_REARRANGE_ENABLED_HASH = 2586408642L
        private val setDragToRearrangeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_drag_to_rearrange_enabled", SET_DRAG_TO_REARRANGE_ENABLED_HASH)
        }

        private const val GET_DRAG_TO_REARRANGE_ENABLED_HASH = 36873697L
        private val getDragToRearrangeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_drag_to_rearrange_enabled", GET_DRAG_TO_REARRANGE_ENABLED_HASH)
        }

        private const val SET_TABS_REARRANGE_GROUP_HASH = 1286410249L
        private val setTabsRearrangeGroupBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tabs_rearrange_group", SET_TABS_REARRANGE_GROUP_HASH)
        }

        private const val GET_TABS_REARRANGE_GROUP_HASH = 3905245786L
        private val getTabsRearrangeGroupBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tabs_rearrange_group", GET_TABS_REARRANGE_GROUP_HASH)
        }

        private const val SET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH = 2586408642L
        private val setUseHiddenTabsForMinSizeBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_use_hidden_tabs_for_min_size", SET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH)
        }

        private const val GET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH = 36873697L
        private val getUseHiddenTabsForMinSizeBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_use_hidden_tabs_for_min_size", GET_USE_HIDDEN_TABS_FOR_MIN_SIZE_HASH)
        }

        private const val SET_TAB_FOCUS_MODE_HASH = 3232914922L
        private val setTabFocusModeBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_tab_focus_mode", SET_TAB_FOCUS_MODE_HASH)
        }

        private const val GET_TAB_FOCUS_MODE_HASH = 2132829277L
        private val getTabFocusModeBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_tab_focus_mode", GET_TAB_FOCUS_MODE_HASH)
        }

        private const val SET_DESELECT_ENABLED_HASH = 2586408642L
        private val setDeselectEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "set_deselect_enabled", SET_DESELECT_ENABLED_HASH)
        }

        private const val GET_DESELECT_ENABLED_HASH = 36873697L
        private val getDeselectEnabledBind by lazy {
            ObjectCalls.getMethodBind("TabContainer", "get_deselect_enabled", GET_DESELECT_ENABLED_HASH)
        }
    }
}
