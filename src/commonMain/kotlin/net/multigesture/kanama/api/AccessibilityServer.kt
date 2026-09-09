package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D

/**
 * A server interface for screen reader support.
 *
 * Generated from Godot docs: AccessibilityServer
 */
object AccessibilityServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("AccessibilityServer")
    }

    const val ROLE_UNKNOWN: Long = 0L
    const val ROLE_DEFAULT_BUTTON: Long = 1L
    const val ROLE_AUDIO: Long = 2L
    const val ROLE_VIDEO: Long = 3L
    const val ROLE_STATIC_TEXT: Long = 4L
    const val ROLE_CONTAINER: Long = 5L
    const val ROLE_PANEL: Long = 6L
    const val ROLE_BUTTON: Long = 7L
    const val ROLE_LINK: Long = 8L
    const val ROLE_CHECK_BOX: Long = 9L
    const val ROLE_RADIO_BUTTON: Long = 10L
    const val ROLE_CHECK_BUTTON: Long = 11L
    const val ROLE_SCROLL_BAR: Long = 12L
    const val ROLE_SCROLL_VIEW: Long = 13L
    const val ROLE_SPLITTER: Long = 14L
    const val ROLE_SLIDER: Long = 15L
    const val ROLE_SPIN_BUTTON: Long = 16L
    const val ROLE_PROGRESS_INDICATOR: Long = 17L
    const val ROLE_TEXT_FIELD: Long = 18L
    const val ROLE_MULTILINE_TEXT_FIELD: Long = 19L
    const val ROLE_COLOR_PICKER: Long = 20L
    const val ROLE_TABLE: Long = 21L
    const val ROLE_CELL: Long = 22L
    const val ROLE_ROW: Long = 23L
    const val ROLE_ROW_GROUP: Long = 24L
    const val ROLE_ROW_HEADER: Long = 25L
    const val ROLE_COLUMN_HEADER: Long = 26L
    const val ROLE_TREE: Long = 27L
    const val ROLE_TREE_ITEM: Long = 28L
    const val ROLE_LIST: Long = 29L
    const val ROLE_LIST_ITEM: Long = 30L
    const val ROLE_LIST_BOX: Long = 31L
    const val ROLE_LIST_BOX_OPTION: Long = 32L
    const val ROLE_TAB_BAR: Long = 33L
    const val ROLE_TAB: Long = 34L
    const val ROLE_TAB_PANEL: Long = 35L
    const val ROLE_MENU_BAR: Long = 36L
    const val ROLE_MENU: Long = 37L
    const val ROLE_MENU_ITEM: Long = 38L
    const val ROLE_MENU_ITEM_CHECK_BOX: Long = 39L
    const val ROLE_MENU_ITEM_RADIO: Long = 40L
    const val ROLE_IMAGE: Long = 41L
    const val ROLE_WINDOW: Long = 42L
    const val ROLE_TITLE_BAR: Long = 43L
    const val ROLE_DIALOG: Long = 44L
    const val ROLE_TOOLTIP: Long = 45L
    const val ROLE_REGION: Long = 46L
    const val ROLE_TEXT_RUN: Long = 47L
    const val POPUP_MENU: Long = 0L
    const val POPUP_LIST: Long = 1L
    const val POPUP_TREE: Long = 2L
    const val POPUP_DIALOG: Long = 3L
    const val FLAG_HIDDEN: Long = 0L
    const val FLAG_MULTISELECTABLE: Long = 1L
    const val FLAG_REQUIRED: Long = 2L
    const val FLAG_VISITED: Long = 3L
    const val FLAG_BUSY: Long = 4L
    const val FLAG_MODAL: Long = 5L
    const val FLAG_TOUCH_PASSTHROUGH: Long = 6L
    const val FLAG_READONLY: Long = 7L
    const val FLAG_DISABLED: Long = 8L
    const val FLAG_CLIPS_CHILDREN: Long = 9L
    const val ACTION_CLICK: Long = 0L
    const val ACTION_FOCUS: Long = 1L
    const val ACTION_BLUR: Long = 2L
    const val ACTION_COLLAPSE: Long = 3L
    const val ACTION_EXPAND: Long = 4L
    const val ACTION_DECREMENT: Long = 5L
    const val ACTION_INCREMENT: Long = 6L
    const val ACTION_HIDE_TOOLTIP: Long = 7L
    const val ACTION_SHOW_TOOLTIP: Long = 8L
    const val ACTION_SET_TEXT_SELECTION: Long = 9L
    const val ACTION_REPLACE_SELECTED_TEXT: Long = 10L
    const val ACTION_SCROLL_BACKWARD: Long = 11L
    const val ACTION_SCROLL_DOWN: Long = 12L
    const val ACTION_SCROLL_FORWARD: Long = 13L
    const val ACTION_SCROLL_LEFT: Long = 14L
    const val ACTION_SCROLL_RIGHT: Long = 15L
    const val ACTION_SCROLL_UP: Long = 16L
    const val ACTION_SCROLL_INTO_VIEW: Long = 17L
    const val ACTION_SCROLL_TO_POINT: Long = 18L
    const val ACTION_SET_SCROLL_OFFSET: Long = 19L
    const val ACTION_SET_VALUE: Long = 20L
    const val ACTION_SHOW_CONTEXT_MENU: Long = 21L
    const val ACTION_CUSTOM: Long = 22L
    const val LIVE_OFF: Long = 0L
    const val LIVE_POLITE: Long = 1L
    const val LIVE_ASSERTIVE: Long = 2L
    const val SCROLL_UNIT_ITEM: Long = 0L
    const val SCROLL_UNIT_PAGE: Long = 1L
    const val SCROLL_HINT_TOP_LEFT: Long = 0L
    const val SCROLL_HINT_BOTTOM_RIGHT: Long = 1L
    const val SCROLL_HINT_TOP_EDGE: Long = 2L
    const val SCROLL_HINT_BOTTOM_EDGE: Long = 3L
    const val SCROLL_HINT_LEFT_EDGE: Long = 4L
    const val SCROLL_HINT_RIGHT_EDGE: Long = 5L

    /**
     * Returns `true` if screen reader is support by this implementation.
     *
     * Generated from Godot docs: AccessibilityServer.is_supported
     */
    @JvmStatic
    fun isSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSupportedBind, singleton)
    }

    /**
     * Creates a new, empty accessibility element resource. Note: An accessibility element is created
     * and freed automatically for each `Node`. In general, this function should not be called
     * manually.
     *
     * Generated from Godot docs: AccessibilityServer.create_element
     */
    @JvmStatic
    fun createElement(windowId: Int, role: Long): RID {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetRID(createElementBind, singleton, windowId, role)
    }

    /**
     * Creates a new, empty accessibility sub-element resource. Sub-elements can be used to provide
     * accessibility information for objects which are not `Node`s, such as list items, table cells, or
     * menu items. Sub-elements are freed automatically when the parent element is freed, or can be
     * freed early using the `free_element` method.
     *
     * Generated from Godot docs: AccessibilityServer.create_sub_element
     */
    @JvmStatic
    fun createSubElement(parentRid: RID, role: Long, insertPos: Int = -1): RID {
        return ObjectCalls.ptrcallWithRIDLongIntArgsRetRID(createSubElementBind, singleton, parentRid, role, insertPos)
    }

    /**
     * Creates a new, empty accessibility sub-element from the shaped text buffer. Sub-elements are
     * freed automatically when the parent element is freed, or can be freed early using the
     * `free_element` method. If `is_last_line` is `true`, no trailing newline is appended to the text
     * content. Set to `true` for the last line in multi-line text fields and for single-line text
     * fields.
     *
     * Generated from Godot docs: AccessibilityServer.create_sub_text_edit_elements
     */
    @JvmStatic
    fun createSubTextEditElements(parentRid: RID, shapedText: RID, minHeight: Double, insertPos: Int = -1, isLastLine: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoRIDDoubleIntBoolArgsRetRID(createSubTextEditElementsBind, singleton, parentRid, shapedText, minHeight, insertPos, isLastLine)
    }

    /**
     * Returns `true` if `id` is a valid accessibility element.
     *
     * Generated from Godot docs: AccessibilityServer.has_element
     */
    @JvmStatic
    fun hasElement(id: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasElementBind, singleton, id)
    }

    /**
     * Frees the accessibility element `id` created by `create_element`, `create_sub_element`, or
     * `create_sub_text_edit_elements`.
     *
     * Generated from Godot docs: AccessibilityServer.free_element
     */
    @JvmStatic
    fun freeElement(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeElementBind, singleton, id)
    }

    /**
     * Sets window outer (with decorations) and inner (without decorations) bounds for assistive apps.
     * Note: This method is implemented on Linux, macOS, and Windows. Note: Advanced users only!
     * `Window` objects call this method automatically.
     *
     * Generated from Godot docs: AccessibilityServer.set_window_rect
     */
    @JvmStatic
    fun setWindowRect(windowId: Int, rectOut: Rect2, rectIn: Rect2) {
        ObjectCalls.ptrcallWithIntRect2Rect2Args(setWindowRectBind, singleton, windowId, rectOut, rectIn)
    }

    /**
     * Sets the window focused state for assistive apps. Note: This method is implemented on Linux,
     * macOS, and Windows. Note: Advanced users only! `Window` objects call this method automatically.
     *
     * Generated from Godot docs: AccessibilityServer.set_window_focused
     */
    @JvmStatic
    fun setWindowFocused(windowId: Int, focused: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setWindowFocusedBind, singleton, windowId, focused)
    }

    /**
     * Sets currently focused element.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_focus
     */
    @JvmStatic
    fun updateSetFocus(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(updateSetFocusBind, singleton, id)
    }

    /**
     * Returns the main accessibility element of the OS native window.
     *
     * Generated from Godot docs: AccessibilityServer.get_window_root
     */
    @JvmStatic
    fun getWindowRoot(windowId: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getWindowRootBind, singleton, windowId)
    }

    /**
     * Sets element accessibility role.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_role
     */
    @JvmStatic
    fun updateSetRole(id: RID, role: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetRoleBind, singleton, id, role)
    }

    /**
     * Sets element accessibility name.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_name
     */
    @JvmStatic
    fun updateSetName(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetNameBind, singleton, id, name)
    }

    /**
     * Sets element accessibility label for Braille display.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_braille_label
     */
    @JvmStatic
    fun updateSetBrailleLabel(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetBrailleLabelBind, singleton, id, name)
    }

    /**
     * Sets element accessibility role description for Braille display.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_braille_role_description
     */
    @JvmStatic
    fun updateSetBrailleRoleDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetBrailleRoleDescriptionBind, singleton, id, description)
    }

    /**
     * Sets element accessibility extra information added to the element name.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_extra_info
     */
    @JvmStatic
    fun updateSetExtraInfo(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetExtraInfoBind, singleton, id, name)
    }

    /**
     * Sets element accessibility description.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_description
     */
    @JvmStatic
    fun updateSetDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetDescriptionBind, singleton, id, description)
    }

    /**
     * Sets element text value.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_value
     */
    @JvmStatic
    fun updateSetValue(id: RID, value: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetValueBind, singleton, id, value)
    }

    /**
     * Sets tooltip text.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_tooltip
     */
    @JvmStatic
    fun updateSetTooltip(id: RID, tooltip: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetTooltipBind, singleton, id, tooltip)
    }

    /**
     * Sets element bounding box, relative to the node position.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_bounds
     */
    @JvmStatic
    fun updateSetBounds(id: RID, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDAndRect2Arg(updateSetBoundsBind, singleton, id, rect)
    }

    /**
     * Sets element 2D transform.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_transform
     */
    @JvmStatic
    fun updateSetTransform(id: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(updateSetTransformBind, singleton, id, transform)
    }

    /**
     * Adds a child accessibility element. Note: `Node` children and sub-elements are added to the
     * child list automatically.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_child
     */
    @JvmStatic
    fun updateAddChild(id: RID, childId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddChildBind, singleton, id, childId)
    }

    /**
     * Adds an element that is controlled by this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_controls
     */
    @JvmStatic
    fun updateAddRelatedControls(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedControlsBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that details this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_details
     */
    @JvmStatic
    fun updateAddRelatedDetails(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedDetailsBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that describes this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_described_by
     */
    @JvmStatic
    fun updateAddRelatedDescribedBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedDescribedByBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that this element flow into.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_flow_to
     */
    @JvmStatic
    fun updateAddRelatedFlowTo(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedFlowToBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that labels this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_labeled_by
     */
    @JvmStatic
    fun updateAddRelatedLabeledBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedLabeledByBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that is part of the same radio group. Note: This method should be called on each
     * element of the group, using all other elements as `related_id`.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_related_radio_group
     */
    @JvmStatic
    fun updateAddRelatedRadioGroup(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateAddRelatedRadioGroupBind, singleton, id, relatedId)
    }

    /**
     * Adds an element that is an active descendant of this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_active_descendant
     */
    @JvmStatic
    fun updateSetActiveDescendant(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetActiveDescendantBind, singleton, id, otherId)
    }

    /**
     * Sets next element on the line.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_next_on_line
     */
    @JvmStatic
    fun updateSetNextOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetNextOnLineBind, singleton, id, otherId)
    }

    /**
     * Sets previous element on the line.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_previous_on_line
     */
    @JvmStatic
    fun updateSetPreviousOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetPreviousOnLineBind, singleton, id, otherId)
    }

    /**
     * Sets the element to be a member of the group.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_member_of
     */
    @JvmStatic
    fun updateSetMemberOf(id: RID, groupId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetMemberOfBind, singleton, id, groupId)
    }

    /**
     * Sets target element for the link.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_in_page_link_target
     */
    @JvmStatic
    fun updateSetInPageLinkTarget(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetInPageLinkTargetBind, singleton, id, otherId)
    }

    /**
     * Sets an element which contains an error message for this element.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_error_message
     */
    @JvmStatic
    fun updateSetErrorMessage(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(updateSetErrorMessageBind, singleton, id, otherId)
    }

    /**
     * Sets the priority of the live region updates.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_live
     */
    @JvmStatic
    fun updateSetLive(id: RID, live: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetLiveBind, singleton, id, live)
    }

    /**
     * Adds a callback for the accessibility action (action which can be performed by using a special
     * screen reader command or buttons on the Braille display), and marks this action as supported.
     * The action callback receives one `Variant` argument, which value depends on action type.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_action
     */
    @JvmStatic
    fun updateAddAction(id: RID, action: Long, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(updateAddActionBind, singleton, id, action, callable.target.handle, callable.method)
    }

    /**
     * Adds support for a custom accessibility action. `action_id` is passed as an argument to the
     * callback of `ACTION_CUSTOM` action.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_custom_action
     */
    @JvmStatic
    fun updateAddCustomAction(id: RID, actionId: Int, actionDescription: String) {
        ObjectCalls.ptrcallWithRIDIntAndStringArgs(updateAddCustomActionBind, singleton, id, actionId, actionDescription)
    }

    /**
     * Sets number of rows in the table.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_row_count
     */
    @JvmStatic
    fun updateSetTableRowCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetTableRowCountBind, singleton, id, count)
    }

    /**
     * Sets number of columns in the table.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_column_count
     */
    @JvmStatic
    fun updateSetTableColumnCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetTableColumnCountBind, singleton, id, count)
    }

    /**
     * Sets position of the row in the table.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_row_index
     */
    @JvmStatic
    fun updateSetTableRowIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetTableRowIndexBind, singleton, id, index)
    }

    /**
     * Sets position of the column.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_column_index
     */
    @JvmStatic
    fun updateSetTableColumnIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetTableColumnIndexBind, singleton, id, index)
    }

    /**
     * Sets cell position in the table.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_cell_position
     */
    @JvmStatic
    fun updateSetTableCellPosition(id: RID, rowIndex: Int, columnIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(updateSetTableCellPositionBind, singleton, id, rowIndex, columnIndex)
    }

    /**
     * Sets cell row/column span.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_table_cell_span
     */
    @JvmStatic
    fun updateSetTableCellSpan(id: RID, rowSpan: Int, columnSpan: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(updateSetTableCellSpanBind, singleton, id, rowSpan, columnSpan)
    }

    /**
     * Sets number of items in the list.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_item_count
     */
    @JvmStatic
    fun updateSetListItemCount(id: RID, size: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetListItemCountBind, singleton, id, size)
    }

    /**
     * Sets the position of the element in the list.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_item_index
     */
    @JvmStatic
    fun updateSetListItemIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetListItemIndexBind, singleton, id, index)
    }

    /**
     * Sets the hierarchical level of the element in the list.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_item_level
     */
    @JvmStatic
    fun updateSetListItemLevel(id: RID, level: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(updateSetListItemLevelBind, singleton, id, level)
    }

    /**
     * Sets list/tree item selected status.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_item_selected
     */
    @JvmStatic
    fun updateSetListItemSelected(id: RID, selected: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(updateSetListItemSelectedBind, singleton, id, selected)
    }

    /**
     * Sets list/tree item expanded status.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_item_expanded
     */
    @JvmStatic
    fun updateSetListItemExpanded(id: RID, expanded: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(updateSetListItemExpandedBind, singleton, id, expanded)
    }

    /**
     * Sets popup type for popup buttons.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_popup_type
     */
    @JvmStatic
    fun updateSetPopupType(id: RID, popup: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetPopupTypeBind, singleton, id, popup)
    }

    /**
     * Sets element checked state.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_checked
     */
    @JvmStatic
    fun updateSetChecked(id: RID, checekd: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(updateSetCheckedBind, singleton, id, checekd)
    }

    /**
     * Sets numeric value.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_num_value
     */
    @JvmStatic
    fun updateSetNumValue(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(updateSetNumValueBind, singleton, id, position)
    }

    /**
     * Sets numeric value range.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_num_range
     */
    @JvmStatic
    fun updateSetNumRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(updateSetNumRangeBind, singleton, id, min, max)
    }

    /**
     * Sets numeric value step.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_num_step
     */
    @JvmStatic
    fun updateSetNumStep(id: RID, step: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(updateSetNumStepBind, singleton, id, step)
    }

    /**
     * Sets numeric value jump.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_num_jump
     */
    @JvmStatic
    fun updateSetNumJump(id: RID, jump: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(updateSetNumJumpBind, singleton, id, jump)
    }

    /**
     * Sets scroll bar x position.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_scroll_x
     */
    @JvmStatic
    fun updateSetScrollX(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(updateSetScrollXBind, singleton, id, position)
    }

    /**
     * Sets scroll bar x range.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_scroll_x_range
     */
    @JvmStatic
    fun updateSetScrollXRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(updateSetScrollXRangeBind, singleton, id, min, max)
    }

    /**
     * Sets scroll bar y position.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_scroll_y
     */
    @JvmStatic
    fun updateSetScrollY(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(updateSetScrollYBind, singleton, id, position)
    }

    /**
     * Sets scroll bar y range.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_scroll_y_range
     */
    @JvmStatic
    fun updateSetScrollYRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(updateSetScrollYRangeBind, singleton, id, min, max)
    }

    /**
     * Sets text underline/overline/strikethrough.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_text_decorations
     */
    @JvmStatic
    fun updateSetTextDecorations(id: RID, underline: Boolean, strikethrough: Boolean, overline: Boolean, color: Color) {
        ObjectCalls.ptrcallWithRIDThreeBoolAndColorArgs(updateSetTextDecorationsBind, singleton, id, underline, strikethrough, overline, color)
    }

    /**
     * Sets element text alignment.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_text_align
     */
    @JvmStatic
    fun updateSetTextAlign(id: RID, align: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetTextAlignBind, singleton, id, align)
    }

    /**
     * Sets text selection to the text field. `text_start_id` and `text_end_id` should be elements
     * created by `create_sub_text_edit_elements`. Character offsets are relative to the corresponding
     * element.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_text_selection
     */
    @JvmStatic
    fun updateSetTextSelection(id: RID, textStartId: RID, startChar: Int, textEndId: RID, endChar: Int) {
        ObjectCalls.ptrcallWithTwoRIDIntRIDIntArgs(updateSetTextSelectionBind, singleton, id, textStartId, startChar, textEndId, endChar)
    }

    /**
     * Sets element flag.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_flag
     */
    @JvmStatic
    fun updateSetFlag(id: RID, flag: Long, value: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(updateSetFlagBind, singleton, id, flag, value)
    }

    /**
     * Sets element class name.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_classname
     */
    @JvmStatic
    fun updateSetClassname(id: RID, classname: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetClassnameBind, singleton, id, classname)
    }

    /**
     * Sets placeholder text.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_placeholder
     */
    @JvmStatic
    fun updateSetPlaceholder(id: RID, placeholder: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetPlaceholderBind, singleton, id, placeholder)
    }

    /**
     * Sets element text language.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_language
     */
    @JvmStatic
    fun updateSetLanguage(id: RID, language: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetLanguageBind, singleton, id, language)
    }

    /**
     * Sets text orientation.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_text_orientation
     */
    @JvmStatic
    fun updateSetTextOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(updateSetTextOrientationBind, singleton, id, vertical)
    }

    /**
     * Sets the orientation of the list elements.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_list_orientation
     */
    @JvmStatic
    fun updateSetListOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(updateSetListOrientationBind, singleton, id, vertical)
    }

    /**
     * Sets the list of keyboard shortcuts used by element.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_shortcut
     */
    @JvmStatic
    fun updateSetShortcut(id: RID, shortcut: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetShortcutBind, singleton, id, shortcut)
    }

    /**
     * Sets link URL.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_url
     */
    @JvmStatic
    fun updateSetUrl(id: RID, url: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetUrlBind, singleton, id, url)
    }

    /**
     * Sets element accessibility role description text.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_role_description
     */
    @JvmStatic
    fun updateSetRoleDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetRoleDescriptionBind, singleton, id, description)
    }

    /**
     * Sets human-readable description of the current checked state.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_state_description
     */
    @JvmStatic
    fun updateSetStateDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(updateSetStateDescriptionBind, singleton, id, description)
    }

    /**
     * Sets element color value.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_color_value
     */
    @JvmStatic
    fun updateSetColorValue(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(updateSetColorValueBind, singleton, id, color)
    }

    /**
     * Sets element background color.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_background_color
     */
    @JvmStatic
    fun updateSetBackgroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(updateSetBackgroundColorBind, singleton, id, color)
    }

    /**
     * Sets element foreground color.
     *
     * Generated from Godot docs: AccessibilityServer.update_set_foreground_color
     */
    @JvmStatic
    fun updateSetForegroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(updateSetForegroundColorBind, singleton, id, color)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): AccessibilityServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): AccessibilityServer? =
        if (handle.address() == 0L) null else this

    private const val IS_SUPPORTED_HASH = 36873697L
    private val isSupportedBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "is_supported", IS_SUPPORTED_HASH)
    }

    private const val CREATE_ELEMENT_HASH = 3846965249L
    private val createElementBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "create_element", CREATE_ELEMENT_HASH)
    }

    private const val CREATE_SUB_ELEMENT_HASH = 1151690429L
    private val createSubElementBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "create_sub_element", CREATE_SUB_ELEMENT_HASH)
    }

    private const val CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH = 2702009895L
    private val createSubTextEditElementsBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "create_sub_text_edit_elements", CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH)
    }

    private const val HAS_ELEMENT_HASH = 4155700596L
    private val hasElementBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "has_element", HAS_ELEMENT_HASH)
    }

    private const val FREE_ELEMENT_HASH = 2722037293L
    private val freeElementBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "free_element", FREE_ELEMENT_HASH)
    }

    private const val SET_WINDOW_RECT_HASH = 2386961724L
    private val setWindowRectBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "set_window_rect", SET_WINDOW_RECT_HASH)
    }

    private const val SET_WINDOW_FOCUSED_HASH = 300928843L
    private val setWindowFocusedBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "set_window_focused", SET_WINDOW_FOCUSED_HASH)
    }

    private const val UPDATE_SET_FOCUS_HASH = 2722037293L
    private val updateSetFocusBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_focus", UPDATE_SET_FOCUS_HASH)
    }

    private const val GET_WINDOW_ROOT_HASH = 495598643L
    private val getWindowRootBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "get_window_root", GET_WINDOW_ROOT_HASH)
    }

    private const val UPDATE_SET_ROLE_HASH = 3747886520L
    private val updateSetRoleBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_role", UPDATE_SET_ROLE_HASH)
    }

    private const val UPDATE_SET_NAME_HASH = 2726140452L
    private val updateSetNameBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_name", UPDATE_SET_NAME_HASH)
    }

    private const val UPDATE_SET_BRAILLE_LABEL_HASH = 2726140452L
    private val updateSetBrailleLabelBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_braille_label", UPDATE_SET_BRAILLE_LABEL_HASH)
    }

    private const val UPDATE_SET_BRAILLE_ROLE_DESCRIPTION_HASH = 2726140452L
    private val updateSetBrailleRoleDescriptionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_braille_role_description", UPDATE_SET_BRAILLE_ROLE_DESCRIPTION_HASH)
    }

    private const val UPDATE_SET_EXTRA_INFO_HASH = 2726140452L
    private val updateSetExtraInfoBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_extra_info", UPDATE_SET_EXTRA_INFO_HASH)
    }

    private const val UPDATE_SET_DESCRIPTION_HASH = 2726140452L
    private val updateSetDescriptionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_description", UPDATE_SET_DESCRIPTION_HASH)
    }

    private const val UPDATE_SET_VALUE_HASH = 2726140452L
    private val updateSetValueBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_value", UPDATE_SET_VALUE_HASH)
    }

    private const val UPDATE_SET_TOOLTIP_HASH = 2726140452L
    private val updateSetTooltipBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_tooltip", UPDATE_SET_TOOLTIP_HASH)
    }

    private const val UPDATE_SET_BOUNDS_HASH = 1378122625L
    private val updateSetBoundsBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_bounds", UPDATE_SET_BOUNDS_HASH)
    }

    private const val UPDATE_SET_TRANSFORM_HASH = 1246044741L
    private val updateSetTransformBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_transform", UPDATE_SET_TRANSFORM_HASH)
    }

    private const val UPDATE_ADD_CHILD_HASH = 395945892L
    private val updateAddChildBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_child", UPDATE_ADD_CHILD_HASH)
    }

    private const val UPDATE_ADD_RELATED_CONTROLS_HASH = 395945892L
    private val updateAddRelatedControlsBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_controls", UPDATE_ADD_RELATED_CONTROLS_HASH)
    }

    private const val UPDATE_ADD_RELATED_DETAILS_HASH = 395945892L
    private val updateAddRelatedDetailsBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_details", UPDATE_ADD_RELATED_DETAILS_HASH)
    }

    private const val UPDATE_ADD_RELATED_DESCRIBED_BY_HASH = 395945892L
    private val updateAddRelatedDescribedByBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_described_by", UPDATE_ADD_RELATED_DESCRIBED_BY_HASH)
    }

    private const val UPDATE_ADD_RELATED_FLOW_TO_HASH = 395945892L
    private val updateAddRelatedFlowToBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_flow_to", UPDATE_ADD_RELATED_FLOW_TO_HASH)
    }

    private const val UPDATE_ADD_RELATED_LABELED_BY_HASH = 395945892L
    private val updateAddRelatedLabeledByBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_labeled_by", UPDATE_ADD_RELATED_LABELED_BY_HASH)
    }

    private const val UPDATE_ADD_RELATED_RADIO_GROUP_HASH = 395945892L
    private val updateAddRelatedRadioGroupBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_related_radio_group", UPDATE_ADD_RELATED_RADIO_GROUP_HASH)
    }

    private const val UPDATE_SET_ACTIVE_DESCENDANT_HASH = 395945892L
    private val updateSetActiveDescendantBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_active_descendant", UPDATE_SET_ACTIVE_DESCENDANT_HASH)
    }

    private const val UPDATE_SET_NEXT_ON_LINE_HASH = 395945892L
    private val updateSetNextOnLineBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_next_on_line", UPDATE_SET_NEXT_ON_LINE_HASH)
    }

    private const val UPDATE_SET_PREVIOUS_ON_LINE_HASH = 395945892L
    private val updateSetPreviousOnLineBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_previous_on_line", UPDATE_SET_PREVIOUS_ON_LINE_HASH)
    }

    private const val UPDATE_SET_MEMBER_OF_HASH = 395945892L
    private val updateSetMemberOfBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_member_of", UPDATE_SET_MEMBER_OF_HASH)
    }

    private const val UPDATE_SET_IN_PAGE_LINK_TARGET_HASH = 395945892L
    private val updateSetInPageLinkTargetBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_in_page_link_target", UPDATE_SET_IN_PAGE_LINK_TARGET_HASH)
    }

    private const val UPDATE_SET_ERROR_MESSAGE_HASH = 395945892L
    private val updateSetErrorMessageBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_error_message", UPDATE_SET_ERROR_MESSAGE_HASH)
    }

    private const val UPDATE_SET_LIVE_HASH = 2993365237L
    private val updateSetLiveBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_live", UPDATE_SET_LIVE_HASH)
    }

    private const val UPDATE_ADD_ACTION_HASH = 3960092835L
    private val updateAddActionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_action", UPDATE_ADD_ACTION_HASH)
    }

    private const val UPDATE_ADD_CUSTOM_ACTION_HASH = 4153150897L
    private val updateAddCustomActionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_add_custom_action", UPDATE_ADD_CUSTOM_ACTION_HASH)
    }

    private const val UPDATE_SET_TABLE_ROW_COUNT_HASH = 3411492887L
    private val updateSetTableRowCountBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_row_count", UPDATE_SET_TABLE_ROW_COUNT_HASH)
    }

    private const val UPDATE_SET_TABLE_COLUMN_COUNT_HASH = 3411492887L
    private val updateSetTableColumnCountBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_column_count", UPDATE_SET_TABLE_COLUMN_COUNT_HASH)
    }

    private const val UPDATE_SET_TABLE_ROW_INDEX_HASH = 3411492887L
    private val updateSetTableRowIndexBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_row_index", UPDATE_SET_TABLE_ROW_INDEX_HASH)
    }

    private const val UPDATE_SET_TABLE_COLUMN_INDEX_HASH = 3411492887L
    private val updateSetTableColumnIndexBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_column_index", UPDATE_SET_TABLE_COLUMN_INDEX_HASH)
    }

    private const val UPDATE_SET_TABLE_CELL_POSITION_HASH = 4288446313L
    private val updateSetTableCellPositionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_cell_position", UPDATE_SET_TABLE_CELL_POSITION_HASH)
    }

    private const val UPDATE_SET_TABLE_CELL_SPAN_HASH = 4288446313L
    private val updateSetTableCellSpanBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_table_cell_span", UPDATE_SET_TABLE_CELL_SPAN_HASH)
    }

    private const val UPDATE_SET_LIST_ITEM_COUNT_HASH = 3411492887L
    private val updateSetListItemCountBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_item_count", UPDATE_SET_LIST_ITEM_COUNT_HASH)
    }

    private const val UPDATE_SET_LIST_ITEM_INDEX_HASH = 3411492887L
    private val updateSetListItemIndexBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_item_index", UPDATE_SET_LIST_ITEM_INDEX_HASH)
    }

    private const val UPDATE_SET_LIST_ITEM_LEVEL_HASH = 3411492887L
    private val updateSetListItemLevelBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_item_level", UPDATE_SET_LIST_ITEM_LEVEL_HASH)
    }

    private const val UPDATE_SET_LIST_ITEM_SELECTED_HASH = 1265174801L
    private val updateSetListItemSelectedBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_item_selected", UPDATE_SET_LIST_ITEM_SELECTED_HASH)
    }

    private const val UPDATE_SET_LIST_ITEM_EXPANDED_HASH = 1265174801L
    private val updateSetListItemExpandedBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_item_expanded", UPDATE_SET_LIST_ITEM_EXPANDED_HASH)
    }

    private const val UPDATE_SET_POPUP_TYPE_HASH = 690307634L
    private val updateSetPopupTypeBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_popup_type", UPDATE_SET_POPUP_TYPE_HASH)
    }

    private const val UPDATE_SET_CHECKED_HASH = 1265174801L
    private val updateSetCheckedBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_checked", UPDATE_SET_CHECKED_HASH)
    }

    private const val UPDATE_SET_NUM_VALUE_HASH = 1794382983L
    private val updateSetNumValueBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_num_value", UPDATE_SET_NUM_VALUE_HASH)
    }

    private const val UPDATE_SET_NUM_RANGE_HASH = 2513314492L
    private val updateSetNumRangeBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_num_range", UPDATE_SET_NUM_RANGE_HASH)
    }

    private const val UPDATE_SET_NUM_STEP_HASH = 1794382983L
    private val updateSetNumStepBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_num_step", UPDATE_SET_NUM_STEP_HASH)
    }

    private const val UPDATE_SET_NUM_JUMP_HASH = 1794382983L
    private val updateSetNumJumpBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_num_jump", UPDATE_SET_NUM_JUMP_HASH)
    }

    private const val UPDATE_SET_SCROLL_X_HASH = 1794382983L
    private val updateSetScrollXBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_scroll_x", UPDATE_SET_SCROLL_X_HASH)
    }

    private const val UPDATE_SET_SCROLL_X_RANGE_HASH = 2513314492L
    private val updateSetScrollXRangeBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_scroll_x_range", UPDATE_SET_SCROLL_X_RANGE_HASH)
    }

    private const val UPDATE_SET_SCROLL_Y_HASH = 1794382983L
    private val updateSetScrollYBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_scroll_y", UPDATE_SET_SCROLL_Y_HASH)
    }

    private const val UPDATE_SET_SCROLL_Y_RANGE_HASH = 2513314492L
    private val updateSetScrollYRangeBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_scroll_y_range", UPDATE_SET_SCROLL_Y_RANGE_HASH)
    }

    private const val UPDATE_SET_TEXT_DECORATIONS_HASH = 457503484L
    private val updateSetTextDecorationsBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_text_decorations", UPDATE_SET_TEXT_DECORATIONS_HASH)
    }

    private const val UPDATE_SET_TEXT_ALIGN_HASH = 3725995085L
    private val updateSetTextAlignBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_text_align", UPDATE_SET_TEXT_ALIGN_HASH)
    }

    private const val UPDATE_SET_TEXT_SELECTION_HASH = 3119144029L
    private val updateSetTextSelectionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_text_selection", UPDATE_SET_TEXT_SELECTION_HASH)
    }

    private const val UPDATE_SET_FLAG_HASH = 1473043386L
    private val updateSetFlagBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_flag", UPDATE_SET_FLAG_HASH)
    }

    private const val UPDATE_SET_CLASSNAME_HASH = 2726140452L
    private val updateSetClassnameBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_classname", UPDATE_SET_CLASSNAME_HASH)
    }

    private const val UPDATE_SET_PLACEHOLDER_HASH = 2726140452L
    private val updateSetPlaceholderBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_placeholder", UPDATE_SET_PLACEHOLDER_HASH)
    }

    private const val UPDATE_SET_LANGUAGE_HASH = 2726140452L
    private val updateSetLanguageBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_language", UPDATE_SET_LANGUAGE_HASH)
    }

    private const val UPDATE_SET_TEXT_ORIENTATION_HASH = 1265174801L
    private val updateSetTextOrientationBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_text_orientation", UPDATE_SET_TEXT_ORIENTATION_HASH)
    }

    private const val UPDATE_SET_LIST_ORIENTATION_HASH = 1265174801L
    private val updateSetListOrientationBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_list_orientation", UPDATE_SET_LIST_ORIENTATION_HASH)
    }

    private const val UPDATE_SET_SHORTCUT_HASH = 2726140452L
    private val updateSetShortcutBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_shortcut", UPDATE_SET_SHORTCUT_HASH)
    }

    private const val UPDATE_SET_URL_HASH = 2726140452L
    private val updateSetUrlBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_url", UPDATE_SET_URL_HASH)
    }

    private const val UPDATE_SET_ROLE_DESCRIPTION_HASH = 2726140452L
    private val updateSetRoleDescriptionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_role_description", UPDATE_SET_ROLE_DESCRIPTION_HASH)
    }

    private const val UPDATE_SET_STATE_DESCRIPTION_HASH = 2726140452L
    private val updateSetStateDescriptionBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_state_description", UPDATE_SET_STATE_DESCRIPTION_HASH)
    }

    private const val UPDATE_SET_COLOR_VALUE_HASH = 2948539648L
    private val updateSetColorValueBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_color_value", UPDATE_SET_COLOR_VALUE_HASH)
    }

    private const val UPDATE_SET_BACKGROUND_COLOR_HASH = 2948539648L
    private val updateSetBackgroundColorBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_background_color", UPDATE_SET_BACKGROUND_COLOR_HASH)
    }

    private const val UPDATE_SET_FOREGROUND_COLOR_HASH = 2948539648L
    private val updateSetForegroundColorBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "update_set_foreground_color", UPDATE_SET_FOREGROUND_COLOR_HASH)
    }
}
