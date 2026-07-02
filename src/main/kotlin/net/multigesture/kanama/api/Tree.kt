package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * A control used to show a set of internal `TreeItem`s in a hierarchical structure.
 *
 * Generated from Godot docs: Tree
 */
class Tree(handle: MemorySegment) : Control(handle) {
    var columns: Int
        @JvmName("columnsProperty")
        get() = getColumns()
        @JvmName("setColumnsProperty")
        set(value) = setColumns(value)

    var columnTitlesVisible: Boolean
        @JvmName("columnTitlesVisibleProperty")
        get() = areColumnTitlesVisible()
        @JvmName("setColumnTitlesVisibleProperty")
        set(value) = setColumnTitlesVisible(value)

    var allowReselect: Boolean
        @JvmName("allowReselectProperty")
        get() = getAllowReselect()
        @JvmName("setAllowReselectProperty")
        set(value) = setAllowReselect(value)

    var allowRmbSelect: Boolean
        @JvmName("allowRmbSelectProperty")
        get() = getAllowRmbSelect()
        @JvmName("setAllowRmbSelectProperty")
        set(value) = setAllowRmbSelect(value)

    var allowSearch: Boolean
        @JvmName("allowSearchProperty")
        get() = getAllowSearch()
        @JvmName("setAllowSearchProperty")
        set(value) = setAllowSearch(value)

    var hideFolding: Boolean
        @JvmName("hideFoldingProperty")
        get() = isFoldingHidden()
        @JvmName("setHideFoldingProperty")
        set(value) = setHideFolding(value)

    var enableRecursiveFolding: Boolean
        @JvmName("enableRecursiveFoldingProperty")
        get() = isRecursiveFoldingEnabled()
        @JvmName("setEnableRecursiveFoldingProperty")
        set(value) = setEnableRecursiveFolding(value)

    var enableDragUnfolding: Boolean
        @JvmName("enableDragUnfoldingProperty")
        get() = isDragUnfoldingEnabled()
        @JvmName("setEnableDragUnfoldingProperty")
        set(value) = setEnableDragUnfolding(value)

    var hideRoot: Boolean
        @JvmName("hideRootProperty")
        get() = isRootHidden()
        @JvmName("setHideRootProperty")
        set(value) = setHideRoot(value)

    var dropModeFlags: Int
        @JvmName("dropModeFlagsProperty")
        get() = getDropModeFlags()
        @JvmName("setDropModeFlagsProperty")
        set(value) = setDropModeFlags(value)

    var selectMode: Long
        @JvmName("selectModeProperty")
        get() = getSelectMode()
        @JvmName("setSelectModeProperty")
        set(value) = setSelectMode(value)

    var autoTooltip: Boolean
        @JvmName("autoTooltipProperty")
        get() = isAutoTooltipEnabled()
        @JvmName("setAutoTooltipProperty")
        set(value) = setAutoTooltip(value)

    var scrollHorizontalEnabled: Boolean
        @JvmName("scrollHorizontalEnabledProperty")
        get() = isHScrollEnabled()
        @JvmName("setScrollHorizontalEnabledProperty")
        set(value) = setHScrollEnabled(value)

    var scrollVerticalEnabled: Boolean
        @JvmName("scrollVerticalEnabledProperty")
        get() = isVScrollEnabled()
        @JvmName("setScrollVerticalEnabledProperty")
        set(value) = setVScrollEnabled(value)

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

    /**
     * Clears the tree. This removes all items. Prints an error and does not allow clearing the tree if
     * called during mouse selection.
     *
     * Generated from Godot docs: Tree.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun createItem(parent: TreeItem, index: Int = -1): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithObjectAndIntArgRetObject(createItemBind, handle, parent.handle, index))
    }

    fun getRoot(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRootBind, handle))
    }

    fun setColumnCustomMinimumWidth(column: Int, minWidth: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setColumnCustomMinimumWidthBind, handle, column, minWidth)
    }

    fun setColumnExpand(column: Int, expand: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setColumnExpandBind, handle, column, expand)
    }

    fun setColumnExpandRatio(column: Int, ratio: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setColumnExpandRatioBind, handle, column, ratio)
    }

    fun setColumnClipContent(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setColumnClipContentBind, handle, column, enable)
    }

    fun isColumnExpanding(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isColumnExpandingBind, handle, column)
    }

    fun isColumnClippingContent(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isColumnClippingContentBind, handle, column)
    }

    fun getColumnExpandRatio(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColumnExpandRatioBind, handle, column)
    }

    fun getColumnWidth(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColumnWidthBind, handle, column)
    }

    fun getCustomDrawingCanvasItem(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCustomDrawingCanvasItemBind, handle)
    }

    fun setHideRoot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideRootBind, handle, enable)
    }

    fun isRootHidden(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRootHiddenBind, handle)
    }

    fun getNextSelected(from: TreeItem): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(getNextSelectedBind, handle, from.handle))
    }

    fun getSelected(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSelectedBind, handle))
    }

    fun setSelected(item: TreeItem, column: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(setSelectedBind, handle, item.handle, column)
    }

    fun getSelectedColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedColumnBind, handle)
    }

    fun getPressedButton(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPressedButtonBind, handle)
    }

    fun setSelectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSelectModeBind, handle, mode)
    }

    fun getSelectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSelectModeBind, handle)
    }

    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

    fun setColumns(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setColumnsBind, handle, amount)
    }

    fun getColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColumnsBind, handle)
    }

    fun getEdited(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedBind, handle))
    }

    fun getEditedColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEditedColumnBind, handle)
    }

    fun editSelected(forceEdit: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(editSelectedBind, handle, forceEdit)
    }

    fun getCustomPopupRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getCustomPopupRectBind, handle)
    }

    fun getItemAreaRect(item: TreeItem, column: Int = -1, buttonIndex: Int = -1): Rect2 {
        return ObjectCalls.ptrcallWithObjectAndTwoIntArgsRetRect2(getItemAreaRectBind, handle, item.handle, column, buttonIndex)
    }

    fun getItemAtPosition(position: Vector2): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithVector2ArgRetObject(getItemAtPositionBind, handle, position))
    }

    fun getColumnAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getColumnAtPositionBind, handle, position)
    }

    fun getDropSectionAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getDropSectionAtPositionBind, handle, position)
    }

    fun getButtonIdAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getButtonIdAtPositionBind, handle, position)
    }

    fun ensureCursorIsVisible() {
        ObjectCalls.ptrcallNoArgs(ensureCursorIsVisibleBind, handle)
    }

    fun setColumnTitlesVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setColumnTitlesVisibleBind, handle, visible)
    }

    fun areColumnTitlesVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areColumnTitlesVisibleBind, handle)
    }

    fun setColumnTitle(column: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleBind, handle, column, title)
    }

    fun getColumnTitle(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleBind, handle, column)
    }

    fun setColumnTitleTooltipText(column: Int, tooltipText: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleTooltipTextBind, handle, column, tooltipText)
    }

    fun getColumnTitleTooltipText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleTooltipTextBind, handle, column)
    }

    fun setColumnTitleAlignment(column: Int, titleAlignment: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setColumnTitleAlignmentBind, handle, column, titleAlignment)
    }

    fun getColumnTitleAlignment(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColumnTitleAlignmentBind, handle, column)
    }

    fun setColumnTitleDirection(column: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setColumnTitleDirectionBind, handle, column, direction)
    }

    fun getColumnTitleDirection(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColumnTitleDirectionBind, handle, column)
    }

    fun setColumnTitleLanguage(column: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleLanguageBind, handle, column, language)
    }

    fun getColumnTitleLanguage(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleLanguageBind, handle, column)
    }

    fun getScroll(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollBind, handle)
    }

    fun scrollToItem(item: TreeItem, centerOnItem: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(scrollToItemBind, handle, item.handle, centerOnItem)
    }

    fun setHScrollEnabled(hScroll: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHScrollEnabledBind, handle, hScroll)
    }

    fun isHScrollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHScrollEnabledBind, handle)
    }

    fun setVScrollEnabled(hScroll: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVScrollEnabledBind, handle, hScroll)
    }

    fun isVScrollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVScrollEnabledBind, handle)
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

    fun setHideFolding(hide: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideFoldingBind, handle, hide)
    }

    fun isFoldingHidden(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFoldingHiddenBind, handle)
    }

    fun setEnableRecursiveFolding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableRecursiveFoldingBind, handle, enable)
    }

    fun isRecursiveFoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecursiveFoldingEnabledBind, handle)
    }

    fun setEnableDragUnfolding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDragUnfoldingBind, handle, enable)
    }

    fun isDragUnfoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragUnfoldingEnabledBind, handle)
    }

    fun setDropModeFlags(flags: Int) {
        ObjectCalls.ptrcallWithIntArg(setDropModeFlagsBind, handle, flags)
    }

    fun getDropModeFlags(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDropModeFlagsBind, handle)
    }

    fun setAllowRmbSelect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowRmbSelectBind, handle, allow)
    }

    fun getAllowRmbSelect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowRmbSelectBind, handle)
    }

    fun setAllowReselect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowReselectBind, handle, allow)
    }

    fun getAllowReselect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowReselectBind, handle)
    }

    fun setAllowSearch(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSearchBind, handle, allow)
    }

    fun getAllowSearch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowSearchBind, handle)
    }

    fun setAutoTooltip(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoTooltipBind, handle, enable)
    }

    fun isAutoTooltipEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoTooltipEnabledBind, handle)
    }

    object Signals {
        const val itemSelected: String = "item_selected"
        const val cellSelected: String = "cell_selected"
        const val multiSelected: String = "multi_selected"
        const val itemMouseSelected: String = "item_mouse_selected"
        const val emptyClicked: String = "empty_clicked"
        const val itemEdited: String = "item_edited"
        const val customItemClicked: String = "custom_item_clicked"
        const val itemIconDoubleClicked: String = "item_icon_double_clicked"
        const val itemCollapsed: String = "item_collapsed"
        const val checkPropagatedToItem: String = "check_propagated_to_item"
        const val buttonClicked: String = "button_clicked"
        const val customPopupEdited: String = "custom_popup_edited"
        const val itemActivated: String = "item_activated"
        const val columnTitleClicked: String = "column_title_clicked"
        const val nothingSelected: String = "nothing_selected"
    }

    companion object {
        const val SELECT_SINGLE: Long = 0L
        const val SELECT_ROW: Long = 1L
        const val SELECT_MULTI: Long = 2L
        const val DROP_MODE_DISABLED: Long = 0L
        const val DROP_MODE_ON_ITEM: Long = 1L
        const val DROP_MODE_INBETWEEN: Long = 2L
        const val SCROLL_HINT_MODE_DISABLED: Long = 0L
        const val SCROLL_HINT_MODE_BOTH: Long = 1L
        const val SCROLL_HINT_MODE_TOP: Long = 2L
        const val SCROLL_HINT_MODE_BOTTOM: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Tree? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Tree? =
            if (handle.address() == 0L) null else Tree(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("Tree", "clear", CLEAR_HASH)
        }

        private const val CREATE_ITEM_HASH = 528467046L
        private val createItemBind by lazy {
            ObjectCalls.getMethodBind("Tree", "create_item", CREATE_ITEM_HASH)
        }

        private const val GET_ROOT_HASH = 1514277247L
        private val getRootBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_root", GET_ROOT_HASH)
        }

        private const val SET_COLUMN_CUSTOM_MINIMUM_WIDTH_HASH = 3937882851L
        private val setColumnCustomMinimumWidthBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_custom_minimum_width", SET_COLUMN_CUSTOM_MINIMUM_WIDTH_HASH)
        }

        private const val SET_COLUMN_EXPAND_HASH = 300928843L
        private val setColumnExpandBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_expand", SET_COLUMN_EXPAND_HASH)
        }

        private const val SET_COLUMN_EXPAND_RATIO_HASH = 3937882851L
        private val setColumnExpandRatioBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_expand_ratio", SET_COLUMN_EXPAND_RATIO_HASH)
        }

        private const val SET_COLUMN_CLIP_CONTENT_HASH = 300928843L
        private val setColumnClipContentBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_clip_content", SET_COLUMN_CLIP_CONTENT_HASH)
        }

        private const val IS_COLUMN_EXPANDING_HASH = 1116898809L
        private val isColumnExpandingBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_column_expanding", IS_COLUMN_EXPANDING_HASH)
        }

        private const val IS_COLUMN_CLIPPING_CONTENT_HASH = 1116898809L
        private val isColumnClippingContentBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_column_clipping_content", IS_COLUMN_CLIPPING_CONTENT_HASH)
        }

        private const val GET_COLUMN_EXPAND_RATIO_HASH = 923996154L
        private val getColumnExpandRatioBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_expand_ratio", GET_COLUMN_EXPAND_RATIO_HASH)
        }

        private const val GET_COLUMN_WIDTH_HASH = 923996154L
        private val getColumnWidthBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_width", GET_COLUMN_WIDTH_HASH)
        }

        private const val GET_CUSTOM_DRAWING_CANVAS_ITEM_HASH = 2944877500L
        private val getCustomDrawingCanvasItemBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_custom_drawing_canvas_item", GET_CUSTOM_DRAWING_CANVAS_ITEM_HASH)
        }

        private const val SET_HIDE_ROOT_HASH = 2586408642L
        private val setHideRootBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_hide_root", SET_HIDE_ROOT_HASH)
        }

        private const val IS_ROOT_HIDDEN_HASH = 36873697L
        private val isRootHiddenBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_root_hidden", IS_ROOT_HIDDEN_HASH)
        }

        private const val GET_NEXT_SELECTED_HASH = 873446299L
        private val getNextSelectedBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_next_selected", GET_NEXT_SELECTED_HASH)
        }

        private const val GET_SELECTED_HASH = 1514277247L
        private val getSelectedBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_selected", GET_SELECTED_HASH)
        }

        private const val SET_SELECTED_HASH = 2662547442L
        private val setSelectedBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_selected", SET_SELECTED_HASH)
        }

        private const val GET_SELECTED_COLUMN_HASH = 3905245786L
        private val getSelectedColumnBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_selected_column", GET_SELECTED_COLUMN_HASH)
        }

        private const val GET_PRESSED_BUTTON_HASH = 3905245786L
        private val getPressedButtonBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_pressed_button", GET_PRESSED_BUTTON_HASH)
        }

        private const val SET_SELECT_MODE_HASH = 3223887270L
        private val setSelectModeBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_select_mode", SET_SELECT_MODE_HASH)
        }

        private const val GET_SELECT_MODE_HASH = 100748571L
        private val getSelectModeBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_select_mode", GET_SELECT_MODE_HASH)
        }

        private const val DESELECT_ALL_HASH = 3218959716L
        private val deselectAllBind by lazy {
            ObjectCalls.getMethodBind("Tree", "deselect_all", DESELECT_ALL_HASH)
        }

        private const val SET_COLUMNS_HASH = 1286410249L
        private val setColumnsBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_columns", SET_COLUMNS_HASH)
        }

        private const val GET_COLUMNS_HASH = 3905245786L
        private val getColumnsBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_columns", GET_COLUMNS_HASH)
        }

        private const val GET_EDITED_HASH = 1514277247L
        private val getEditedBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_edited", GET_EDITED_HASH)
        }

        private const val GET_EDITED_COLUMN_HASH = 3905245786L
        private val getEditedColumnBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_edited_column", GET_EDITED_COLUMN_HASH)
        }

        private const val EDIT_SELECTED_HASH = 2595650253L
        private val editSelectedBind by lazy {
            ObjectCalls.getMethodBind("Tree", "edit_selected", EDIT_SELECTED_HASH)
        }

        private const val GET_CUSTOM_POPUP_RECT_HASH = 1639390495L
        private val getCustomPopupRectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_custom_popup_rect", GET_CUSTOM_POPUP_RECT_HASH)
        }

        private const val GET_ITEM_AREA_RECT_HASH = 47968679L
        private val getItemAreaRectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_item_area_rect", GET_ITEM_AREA_RECT_HASH)
        }

        private const val GET_ITEM_AT_POSITION_HASH = 4193340126L
        private val getItemAtPositionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_item_at_position", GET_ITEM_AT_POSITION_HASH)
        }

        private const val GET_COLUMN_AT_POSITION_HASH = 3820158470L
        private val getColumnAtPositionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_at_position", GET_COLUMN_AT_POSITION_HASH)
        }

        private const val GET_DROP_SECTION_AT_POSITION_HASH = 3820158470L
        private val getDropSectionAtPositionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_drop_section_at_position", GET_DROP_SECTION_AT_POSITION_HASH)
        }

        private const val GET_BUTTON_ID_AT_POSITION_HASH = 3820158470L
        private val getButtonIdAtPositionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_button_id_at_position", GET_BUTTON_ID_AT_POSITION_HASH)
        }

        private const val ENSURE_CURSOR_IS_VISIBLE_HASH = 3218959716L
        private val ensureCursorIsVisibleBind by lazy {
            ObjectCalls.getMethodBind("Tree", "ensure_cursor_is_visible", ENSURE_CURSOR_IS_VISIBLE_HASH)
        }

        private const val SET_COLUMN_TITLES_VISIBLE_HASH = 2586408642L
        private val setColumnTitlesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_titles_visible", SET_COLUMN_TITLES_VISIBLE_HASH)
        }

        private const val ARE_COLUMN_TITLES_VISIBLE_HASH = 36873697L
        private val areColumnTitlesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Tree", "are_column_titles_visible", ARE_COLUMN_TITLES_VISIBLE_HASH)
        }

        private const val SET_COLUMN_TITLE_HASH = 501894301L
        private val setColumnTitleBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_title", SET_COLUMN_TITLE_HASH)
        }

        private const val GET_COLUMN_TITLE_HASH = 844755477L
        private val getColumnTitleBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_title", GET_COLUMN_TITLE_HASH)
        }

        private const val SET_COLUMN_TITLE_TOOLTIP_TEXT_HASH = 501894301L
        private val setColumnTitleTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_title_tooltip_text", SET_COLUMN_TITLE_TOOLTIP_TEXT_HASH)
        }

        private const val GET_COLUMN_TITLE_TOOLTIP_TEXT_HASH = 844755477L
        private val getColumnTitleTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_title_tooltip_text", GET_COLUMN_TITLE_TOOLTIP_TEXT_HASH)
        }

        private const val SET_COLUMN_TITLE_ALIGNMENT_HASH = 3276431499L
        private val setColumnTitleAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_title_alignment", SET_COLUMN_TITLE_ALIGNMENT_HASH)
        }

        private const val GET_COLUMN_TITLE_ALIGNMENT_HASH = 4171562184L
        private val getColumnTitleAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_title_alignment", GET_COLUMN_TITLE_ALIGNMENT_HASH)
        }

        private const val SET_COLUMN_TITLE_DIRECTION_HASH = 1707680378L
        private val setColumnTitleDirectionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_title_direction", SET_COLUMN_TITLE_DIRECTION_HASH)
        }

        private const val GET_COLUMN_TITLE_DIRECTION_HASH = 4235602388L
        private val getColumnTitleDirectionBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_title_direction", GET_COLUMN_TITLE_DIRECTION_HASH)
        }

        private const val SET_COLUMN_TITLE_LANGUAGE_HASH = 501894301L
        private val setColumnTitleLanguageBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_column_title_language", SET_COLUMN_TITLE_LANGUAGE_HASH)
        }

        private const val GET_COLUMN_TITLE_LANGUAGE_HASH = 844755477L
        private val getColumnTitleLanguageBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_column_title_language", GET_COLUMN_TITLE_LANGUAGE_HASH)
        }

        private const val GET_SCROLL_HASH = 3341600327L
        private val getScrollBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_scroll", GET_SCROLL_HASH)
        }

        private const val SCROLL_TO_ITEM_HASH = 1314737213L
        private val scrollToItemBind by lazy {
            ObjectCalls.getMethodBind("Tree", "scroll_to_item", SCROLL_TO_ITEM_HASH)
        }

        private const val SET_H_SCROLL_ENABLED_HASH = 2586408642L
        private val setHScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_h_scroll_enabled", SET_H_SCROLL_ENABLED_HASH)
        }

        private const val IS_H_SCROLL_ENABLED_HASH = 36873697L
        private val isHScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_h_scroll_enabled", IS_H_SCROLL_ENABLED_HASH)
        }

        private const val SET_V_SCROLL_ENABLED_HASH = 2586408642L
        private val setVScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_v_scroll_enabled", SET_V_SCROLL_ENABLED_HASH)
        }

        private const val IS_V_SCROLL_ENABLED_HASH = 36873697L
        private val isVScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_v_scroll_enabled", IS_V_SCROLL_ENABLED_HASH)
        }

        private const val SET_SCROLL_HINT_MODE_HASH = 415911924L
        private val setScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_scroll_hint_mode", SET_SCROLL_HINT_MODE_HASH)
        }

        private const val GET_SCROLL_HINT_MODE_HASH = 553087187L
        private val getScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_scroll_hint_mode", GET_SCROLL_HINT_MODE_HASH)
        }

        private const val SET_TILE_SCROLL_HINT_HASH = 2586408642L
        private val setTileScrollHintBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_tile_scroll_hint", SET_TILE_SCROLL_HINT_HASH)
        }

        private const val IS_SCROLL_HINT_TILED_HASH = 2240911060L
        private val isScrollHintTiledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_scroll_hint_tiled", IS_SCROLL_HINT_TILED_HASH)
        }

        private const val SET_HIDE_FOLDING_HASH = 2586408642L
        private val setHideFoldingBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_hide_folding", SET_HIDE_FOLDING_HASH)
        }

        private const val IS_FOLDING_HIDDEN_HASH = 36873697L
        private val isFoldingHiddenBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_folding_hidden", IS_FOLDING_HIDDEN_HASH)
        }

        private const val SET_ENABLE_RECURSIVE_FOLDING_HASH = 2586408642L
        private val setEnableRecursiveFoldingBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_enable_recursive_folding", SET_ENABLE_RECURSIVE_FOLDING_HASH)
        }

        private const val IS_RECURSIVE_FOLDING_ENABLED_HASH = 36873697L
        private val isRecursiveFoldingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_recursive_folding_enabled", IS_RECURSIVE_FOLDING_ENABLED_HASH)
        }

        private const val SET_ENABLE_DRAG_UNFOLDING_HASH = 2586408642L
        private val setEnableDragUnfoldingBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_enable_drag_unfolding", SET_ENABLE_DRAG_UNFOLDING_HASH)
        }

        private const val IS_DRAG_UNFOLDING_ENABLED_HASH = 36873697L
        private val isDragUnfoldingEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_drag_unfolding_enabled", IS_DRAG_UNFOLDING_ENABLED_HASH)
        }

        private const val SET_DROP_MODE_FLAGS_HASH = 1286410249L
        private val setDropModeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_drop_mode_flags", SET_DROP_MODE_FLAGS_HASH)
        }

        private const val GET_DROP_MODE_FLAGS_HASH = 3905245786L
        private val getDropModeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_drop_mode_flags", GET_DROP_MODE_FLAGS_HASH)
        }

        private const val SET_ALLOW_RMB_SELECT_HASH = 2586408642L
        private val setAllowRmbSelectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_allow_rmb_select", SET_ALLOW_RMB_SELECT_HASH)
        }

        private const val GET_ALLOW_RMB_SELECT_HASH = 36873697L
        private val getAllowRmbSelectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_allow_rmb_select", GET_ALLOW_RMB_SELECT_HASH)
        }

        private const val SET_ALLOW_RESELECT_HASH = 2586408642L
        private val setAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_allow_reselect", SET_ALLOW_RESELECT_HASH)
        }

        private const val GET_ALLOW_RESELECT_HASH = 36873697L
        private val getAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_allow_reselect", GET_ALLOW_RESELECT_HASH)
        }

        private const val SET_ALLOW_SEARCH_HASH = 2586408642L
        private val setAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_allow_search", SET_ALLOW_SEARCH_HASH)
        }

        private const val GET_ALLOW_SEARCH_HASH = 36873697L
        private val getAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("Tree", "get_allow_search", GET_ALLOW_SEARCH_HASH)
        }

        private const val SET_AUTO_TOOLTIP_HASH = 2586408642L
        private val setAutoTooltipBind by lazy {
            ObjectCalls.getMethodBind("Tree", "set_auto_tooltip", SET_AUTO_TOOLTIP_HASH)
        }

        private const val IS_AUTO_TOOLTIP_ENABLED_HASH = 36873697L
        private val isAutoTooltipEnabledBind by lazy {
            ObjectCalls.getMethodBind("Tree", "is_auto_tooltip_enabled", IS_AUTO_TOOLTIP_ENABLED_HASH)
        }
    }
}
