package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("AccessibilityServer")
    }

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
    fun createElement(windowId: Int, role: AccessibilityServer.AccessibilityRole): RID {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetRID(createElementBind, singleton, windowId, role.value)
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
    fun createSubElement(parentRid: RID, role: AccessibilityServer.AccessibilityRole, insertPos: Int = -1): RID {
        return ObjectCalls.ptrcallWithRIDLongIntArgsRetRID(createSubElementBind, singleton, parentRid, role.value, insertPos)
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
     * Sets the metadata of the accessibility element `id` to `meta`.
     *
     * Generated from Godot docs: AccessibilityServer.element_set_meta
     */
    @JvmStatic
    fun elementSetMeta(id: RID, meta: Any?) {
        ObjectCalls.ptrcallWithRIDAndVariantArg(elementSetMetaBind, singleton, id, meta)
    }

    /**
     * Returns the metadata of the accessibility element `id`.
     *
     * Generated from Godot docs: AccessibilityServer.element_get_meta
     */
    @JvmStatic
    fun elementGetMeta(id: RID): Any? {
        return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(elementGetMetaBind, singleton, id)
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
    fun updateSetRole(id: RID, role: AccessibilityServer.AccessibilityRole) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetRoleBind, singleton, id, role.value)
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
    fun updateSetLive(id: RID, live: AccessibilityServer.AccessibilityLiveMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetLiveBind, singleton, id, live.value)
    }

    /**
     * Adds a callback for the accessibility action (action which can be performed by using a special
     * screen reader command or buttons on the Braille display), and marks this action as supported.
     * The action callback receives one `Variant` argument, which value depends on action type.
     *
     * Generated from Godot docs: AccessibilityServer.update_add_action
     */
    @JvmStatic
    fun updateAddAction(id: RID, action: AccessibilityServer.AccessibilityAction, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(updateAddActionBind, singleton, id, action.value, callable.target.segment, callable.method)
    }

    /**
     * Adds support for a custom accessibility action. `action_id` is passed as an argument to the
     * callback of `AccessibilityAction.CUSTOM` action.
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
    fun updateSetPopupType(id: RID, popup: AccessibilityServer.AccessibilityPopupType) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetPopupTypeBind, singleton, id, popup.value)
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
    fun updateSetTextAlign(id: RID, align: HorizontalAlignment) {
        ObjectCalls.ptrcallWithRIDAndLongArg(updateSetTextAlignBind, singleton, id, align.value)
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
    fun updateSetFlag(id: RID, flag: AccessibilityServer.AccessibilityFlags, value: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(updateSetFlagBind, singleton, id, flag.value, value)
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

    /**
     * Godot's `AccessibilityServer.AccessibilityRole` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityRole.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityRole
     */
    @JvmInline
    value class AccessibilityRole(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Unknown or custom role.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_UNKNOWN
             */
            val UNKNOWN: AccessibilityRole get() = AccessibilityRole(0L)
            /**
             * Default dialog button element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_DEFAULT_BUTTON
             */
            val DEFAULT_BUTTON: AccessibilityRole get() = AccessibilityRole(1L)
            /**
             * Audio player element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_AUDIO
             */
            val AUDIO: AccessibilityRole get() = AccessibilityRole(2L)
            /**
             * Video player element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_VIDEO
             */
            val VIDEO: AccessibilityRole get() = AccessibilityRole(3L)
            /**
             * Non-editable text label.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_STATIC_TEXT
             */
            val STATIC_TEXT: AccessibilityRole get() = AccessibilityRole(4L)
            /**
             * Container element. Elements with this role are used for internal structure and ignored by screen
             * readers.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_CONTAINER
             */
            val CONTAINER: AccessibilityRole get() = AccessibilityRole(5L)
            /**
             * Panel container element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_PANEL
             */
            val PANEL: AccessibilityRole get() = AccessibilityRole(6L)
            /**
             * Button element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_BUTTON
             */
            val BUTTON: AccessibilityRole get() = AccessibilityRole(7L)
            /**
             * Link element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_LINK
             */
            val LINK: AccessibilityRole get() = AccessibilityRole(8L)
            /**
             * Check box element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_CHECK_BOX
             */
            val CHECK_BOX: AccessibilityRole get() = AccessibilityRole(9L)
            /**
             * Radio button element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_RADIO_BUTTON
             */
            val RADIO_BUTTON: AccessibilityRole get() = AccessibilityRole(10L)
            /**
             * Check button element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_CHECK_BUTTON
             */
            val CHECK_BUTTON: AccessibilityRole get() = AccessibilityRole(11L)
            /**
             * Scroll bar element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_SCROLL_BAR
             */
            val SCROLL_BAR: AccessibilityRole get() = AccessibilityRole(12L)
            /**
             * Scroll container element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_SCROLL_VIEW
             */
            val SCROLL_VIEW: AccessibilityRole get() = AccessibilityRole(13L)
            /**
             * Container splitter handle element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_SPLITTER
             */
            val SPLITTER: AccessibilityRole get() = AccessibilityRole(14L)
            /**
             * Slider element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_SLIDER
             */
            val SLIDER: AccessibilityRole get() = AccessibilityRole(15L)
            /**
             * Spin box element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_SPIN_BUTTON
             */
            val SPIN_BUTTON: AccessibilityRole get() = AccessibilityRole(16L)
            /**
             * Progress indicator element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_PROGRESS_INDICATOR
             */
            val PROGRESS_INDICATOR: AccessibilityRole get() = AccessibilityRole(17L)
            /**
             * Editable text field element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TEXT_FIELD
             */
            val TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(18L)
            /**
             * Multiline editable text field element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MULTILINE_TEXT_FIELD
             */
            val MULTILINE_TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(19L)
            /**
             * Color picker element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_COLOR_PICKER
             */
            val COLOR_PICKER: AccessibilityRole get() = AccessibilityRole(20L)
            /**
             * Table element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TABLE
             */
            val TABLE: AccessibilityRole get() = AccessibilityRole(21L)
            /**
             * Table/tree cell element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_CELL
             */
            val CELL: AccessibilityRole get() = AccessibilityRole(22L)
            /**
             * Table/tree row element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_ROW
             */
            val ROW: AccessibilityRole get() = AccessibilityRole(23L)
            /**
             * Table/tree row group element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_ROW_GROUP
             */
            val ROW_GROUP: AccessibilityRole get() = AccessibilityRole(24L)
            /**
             * Table/tree row header element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_ROW_HEADER
             */
            val ROW_HEADER: AccessibilityRole get() = AccessibilityRole(25L)
            /**
             * Table/tree column header element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_COLUMN_HEADER
             */
            val COLUMN_HEADER: AccessibilityRole get() = AccessibilityRole(26L)
            /**
             * Tree view element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TREE
             */
            val TREE: AccessibilityRole get() = AccessibilityRole(27L)
            /**
             * Tree view item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TREE_ITEM
             */
            val TREE_ITEM: AccessibilityRole get() = AccessibilityRole(28L)
            /**
             * List element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_LIST
             */
            val LIST: AccessibilityRole get() = AccessibilityRole(29L)
            /**
             * List item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_LIST_ITEM
             */
            val LIST_ITEM: AccessibilityRole get() = AccessibilityRole(30L)
            /**
             * List view element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_LIST_BOX
             */
            val LIST_BOX: AccessibilityRole get() = AccessibilityRole(31L)
            /**
             * List view item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_LIST_BOX_OPTION
             */
            val LIST_BOX_OPTION: AccessibilityRole get() = AccessibilityRole(32L)
            /**
             * Tab bar element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TAB_BAR
             */
            val TAB_BAR: AccessibilityRole get() = AccessibilityRole(33L)
            /**
             * Tab bar item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TAB
             */
            val TAB: AccessibilityRole get() = AccessibilityRole(34L)
            /**
             * Tab panel element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TAB_PANEL
             */
            val TAB_PANEL: AccessibilityRole get() = AccessibilityRole(35L)
            /**
             * Menu bar element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MENU_BAR
             */
            val MENU_BAR: AccessibilityRole get() = AccessibilityRole(36L)
            /**
             * Popup menu element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MENU
             */
            val MENU: AccessibilityRole get() = AccessibilityRole(37L)
            /**
             * Popup menu item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MENU_ITEM
             */
            val MENU_ITEM: AccessibilityRole get() = AccessibilityRole(38L)
            /**
             * Popup menu check button item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MENU_ITEM_CHECK_BOX
             */
            val MENU_ITEM_CHECK_BOX: AccessibilityRole get() = AccessibilityRole(39L)
            /**
             * Popup menu radio button item element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_MENU_ITEM_RADIO
             */
            val MENU_ITEM_RADIO: AccessibilityRole get() = AccessibilityRole(40L)
            /**
             * Image element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_IMAGE
             */
            val IMAGE: AccessibilityRole get() = AccessibilityRole(41L)
            /**
             * Window element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_WINDOW
             */
            val WINDOW: AccessibilityRole get() = AccessibilityRole(42L)
            /**
             * Embedded window title bar element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TITLE_BAR
             */
            val TITLE_BAR: AccessibilityRole get() = AccessibilityRole(43L)
            /**
             * Dialog window element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_DIALOG
             */
            val DIALOG: AccessibilityRole get() = AccessibilityRole(44L)
            /**
             * Tooltip element.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TOOLTIP
             */
            val TOOLTIP: AccessibilityRole get() = AccessibilityRole(45L)
            /**
             * Region/landmark element. Screen readers can navigate between regions using landmark navigation.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_REGION
             */
            val REGION: AccessibilityRole get() = AccessibilityRole(46L)
            /**
             * Unifor text run. Note: This role is used for internal text elements, and should not be assigned
             * to nodes.
             *
             * Generated from Godot docs: AccessibilityServer.ROLE_TEXT_RUN
             */
            val TEXT_RUN: AccessibilityRole get() = AccessibilityRole(47L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityPopupType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityPopupType.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityPopupType
     */
    @JvmInline
    value class AccessibilityPopupType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Popup menu.
             *
             * Generated from Godot docs: AccessibilityServer.POPUP_MENU
             */
            val MENU: AccessibilityPopupType get() = AccessibilityPopupType(0L)
            /**
             * Popup list.
             *
             * Generated from Godot docs: AccessibilityServer.POPUP_LIST
             */
            val LIST: AccessibilityPopupType get() = AccessibilityPopupType(1L)
            /**
             * Popup tree view.
             *
             * Generated from Godot docs: AccessibilityServer.POPUP_TREE
             */
            val TREE: AccessibilityPopupType get() = AccessibilityPopupType(2L)
            /**
             * Popup dialog.
             *
             * Generated from Godot docs: AccessibilityServer.POPUP_DIALOG
             */
            val DIALOG: AccessibilityPopupType get() = AccessibilityPopupType(3L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityFlags` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityFlags.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityFlags
     */
    @JvmInline
    value class AccessibilityFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Element is hidden for accessibility tools.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_HIDDEN
             */
            val HIDDEN: AccessibilityFlags get() = AccessibilityFlags(0L)
            /**
             * Element supports multiple item selection.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_MULTISELECTABLE
             */
            val MULTISELECTABLE: AccessibilityFlags get() = AccessibilityFlags(1L)
            /**
             * Element require user input.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_REQUIRED
             */
            val REQUIRED: AccessibilityFlags get() = AccessibilityFlags(2L)
            /**
             * Element is a visited link.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_VISITED
             */
            val VISITED: AccessibilityFlags get() = AccessibilityFlags(3L)
            /**
             * Element content is not ready (e.g. loading).
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_BUSY
             */
            val BUSY: AccessibilityFlags get() = AccessibilityFlags(4L)
            /**
             * Element is modal window.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_MODAL
             */
            val MODAL: AccessibilityFlags get() = AccessibilityFlags(5L)
            /**
             * Element allows touches to be passed through when a screen reader is in touch exploration mode.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_TOUCH_PASSTHROUGH
             */
            val TOUCH_PASSTHROUGH: AccessibilityFlags get() = AccessibilityFlags(6L)
            /**
             * Element is text field with selectable but read-only text.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_READONLY
             */
            val READONLY: AccessibilityFlags get() = AccessibilityFlags(7L)
            /**
             * Element is disabled.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_DISABLED
             */
            val DISABLED: AccessibilityFlags get() = AccessibilityFlags(8L)
            /**
             * Element clips children.
             *
             * Generated from Godot docs: AccessibilityServer.FLAG_CLIPS_CHILDREN
             */
            val CLIPS_CHILDREN: AccessibilityFlags get() = AccessibilityFlags(9L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityAction` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityAction.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityAction
     */
    @JvmInline
    value class AccessibilityAction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Single click action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_CLICK
             */
            val CLICK: AccessibilityAction get() = AccessibilityAction(0L)
            /**
             * Focus action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_FOCUS
             */
            val FOCUS: AccessibilityAction get() = AccessibilityAction(1L)
            /**
             * Blur action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_BLUR
             */
            val BLUR: AccessibilityAction get() = AccessibilityAction(2L)
            /**
             * Collapse action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_COLLAPSE
             */
            val COLLAPSE: AccessibilityAction get() = AccessibilityAction(3L)
            /**
             * Expand action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_EXPAND
             */
            val EXPAND: AccessibilityAction get() = AccessibilityAction(4L)
            /**
             * Decrement action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_DECREMENT
             */
            val DECREMENT: AccessibilityAction get() = AccessibilityAction(5L)
            /**
             * Increment action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_INCREMENT
             */
            val INCREMENT: AccessibilityAction get() = AccessibilityAction(6L)
            /**
             * Hide tooltip action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_HIDE_TOOLTIP
             */
            val HIDE_TOOLTIP: AccessibilityAction get() = AccessibilityAction(7L)
            /**
             * Show tooltip action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SHOW_TOOLTIP
             */
            val SHOW_TOOLTIP: AccessibilityAction get() = AccessibilityAction(8L)
            /**
             * Set text selection action, callback argument is set to `Dictionary` with the following keys: -
             * `"start_element"` accessibility element of the selection start. - `"start_char"` character
             * offset relative to the accessibility element of the selection start. - `"end_element"`
             * accessibility element of the selection end. - `"end_char"` character offset relative to the
             * accessibility element of the selection end.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SET_TEXT_SELECTION
             */
            val SET_TEXT_SELECTION: AccessibilityAction get() = AccessibilityAction(9L)
            /**
             * Replace text action, callback argument is set to `String` with the replacement text.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_REPLACE_SELECTED_TEXT
             */
            val REPLACE_SELECTED_TEXT: AccessibilityAction get() = AccessibilityAction(10L)
            /**
             * Scroll backward action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_BACKWARD
             */
            val SCROLL_BACKWARD: AccessibilityAction get() = AccessibilityAction(11L)
            /**
             * Scroll down action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_DOWN
             */
            val SCROLL_DOWN: AccessibilityAction get() = AccessibilityAction(12L)
            /**
             * Scroll forward action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_FORWARD
             */
            val SCROLL_FORWARD: AccessibilityAction get() = AccessibilityAction(13L)
            /**
             * Scroll left action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_LEFT
             */
            val SCROLL_LEFT: AccessibilityAction get() = AccessibilityAction(14L)
            /**
             * Scroll right action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_RIGHT
             */
            val SCROLL_RIGHT: AccessibilityAction get() = AccessibilityAction(15L)
            /**
             * Scroll up action, callback argument is set to `AccessibilityScrollUnit`.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_UP
             */
            val SCROLL_UP: AccessibilityAction get() = AccessibilityAction(16L)
            /**
             * Scroll into view action, callback argument is set to `AccessibilityScrollHint`.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_INTO_VIEW
             */
            val SCROLL_INTO_VIEW: AccessibilityAction get() = AccessibilityAction(17L)
            /**
             * Scroll to point action, callback argument is set to `Vector2` with the relative point
             * coordinates.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SCROLL_TO_POINT
             */
            val SCROLL_TO_POINT: AccessibilityAction get() = AccessibilityAction(18L)
            /**
             * Set scroll offset action, callback argument is set to `Vector2` with the scroll offset.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SET_SCROLL_OFFSET
             */
            val SET_SCROLL_OFFSET: AccessibilityAction get() = AccessibilityAction(19L)
            /**
             * Set value action, callback argument is set to `String` or number with the new value.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SET_VALUE
             */
            val SET_VALUE: AccessibilityAction get() = AccessibilityAction(20L)
            /**
             * Show context menu action, callback argument is not set.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_SHOW_CONTEXT_MENU
             */
            val SHOW_CONTEXT_MENU: AccessibilityAction get() = AccessibilityAction(21L)
            /**
             * Custom action, callback argument is set to the integer action ID.
             *
             * Generated from Godot docs: AccessibilityServer.ACTION_CUSTOM
             */
            val CUSTOM: AccessibilityAction get() = AccessibilityAction(22L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityLiveMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityLiveMode.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityLiveMode
     */
    @JvmInline
    value class AccessibilityLiveMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Indicates that updates to the live region should not be presented.
             *
             * Generated from Godot docs: AccessibilityServer.LIVE_OFF
             */
            val OFF: AccessibilityLiveMode get() = AccessibilityLiveMode(0L)
            /**
             * Indicates that updates to the live region should be presented at the next opportunity (for
             * example at the end of speaking the current sentence).
             *
             * Generated from Godot docs: AccessibilityServer.LIVE_POLITE
             */
            val POLITE: AccessibilityLiveMode get() = AccessibilityLiveMode(1L)
            /**
             * Indicates that updates to the live region have the highest priority and should be presented
             * immediately.
             *
             * Generated from Godot docs: AccessibilityServer.LIVE_ASSERTIVE
             */
            val ASSERTIVE: AccessibilityLiveMode get() = AccessibilityLiveMode(2L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityScrollUnit` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityScrollUnit.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityScrollUnit
     */
    @JvmInline
    value class AccessibilityScrollUnit(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The amount by which to scroll. A single item of a list, line of text.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_UNIT_ITEM
             */
            val ITEM: AccessibilityScrollUnit get() = AccessibilityScrollUnit(0L)
            /**
             * The amount by which to scroll. A single page.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_UNIT_PAGE
             */
            val PAGE: AccessibilityScrollUnit get() = AccessibilityScrollUnit(1L)
        }
    }

    /**
     * Godot's `AccessibilityServer.AccessibilityScrollHint` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AccessibilityServer.AccessibilityScrollHint.<NAME>`).
     *
     * Generated from Godot docs: AccessibilityServer.AccessibilityScrollHint
     */
    @JvmInline
    value class AccessibilityScrollHint(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A preferred position for the node scrolled into view. Top-left edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_TOP_LEFT
             */
            val TOP_LEFT: AccessibilityScrollHint get() = AccessibilityScrollHint(0L)
            /**
             * A preferred position for the node scrolled into view. Bottom-right edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_BOTTOM_RIGHT
             */
            val BOTTOM_RIGHT: AccessibilityScrollHint get() = AccessibilityScrollHint(1L)
            /**
             * A preferred position for the node scrolled into view. Top edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_TOP_EDGE
             */
            val TOP_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(2L)
            /**
             * A preferred position for the node scrolled into view. Bottom edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_BOTTOM_EDGE
             */
            val BOTTOM_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(3L)
            /**
             * A preferred position for the node scrolled into view. Left edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_LEFT_EDGE
             */
            val LEFT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(4L)
            /**
             * A preferred position for the node scrolled into view. Right edge of the scroll container.
             *
             * Generated from Godot docs: AccessibilityServer.SCROLL_HINT_RIGHT_EDGE
             */
            val RIGHT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(5L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): AccessibilityServer? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): AccessibilityServer? =
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

    private const val ELEMENT_SET_META_HASH = 3175752987L
    private val elementSetMetaBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "element_set_meta", ELEMENT_SET_META_HASH)
    }

    private const val ELEMENT_GET_META_HASH = 4171304767L
    private val elementGetMetaBind by lazy {
        ObjectCalls.getMethodBind("AccessibilityServer", "element_get_meta", ELEMENT_GET_META_HASH)
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
