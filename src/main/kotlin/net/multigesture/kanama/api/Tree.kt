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

    /**
     * Creates an item in the tree and adds it as a child of `parent`, which can be either a valid
     * `TreeItem` or `null`. If `parent` is `null`, the root item will be the parent, or the new item
     * will be the root itself if the tree is empty. The new item will be the `index`-th child of
     * parent, or it will be the last child if there are not enough siblings. Prints an error and
     * returns `null` if called during mouse selection, or if the `parent` does not belong to this
     * tree.
     *
     * Generated from Godot docs: Tree.create_item
     */
    fun createItem(parent: TreeItem, index: Int = -1): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithObjectAndIntArgRetObject(createItemBind, handle, parent.handle, index))
    }

    /**
     * Returns the tree's root item, or `null` if the tree is empty.
     *
     * Generated from Godot docs: Tree.get_root
     */
    fun getRoot(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRootBind, handle))
    }

    /**
     * Overrides the calculated minimum width of a column. It can be set to `0` to restore the default
     * behavior. Columns that have the "Expand" flag will use their "min_width" in a similar fashion to
     * `Control.size_flags_stretch_ratio`.
     *
     * Generated from Godot docs: Tree.set_column_custom_minimum_width
     */
    fun setColumnCustomMinimumWidth(column: Int, minWidth: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setColumnCustomMinimumWidthBind, handle, column, minWidth)
    }

    /**
     * If `true`, the column will have the "Expand" flag of `Control`. Columns that have the "Expand"
     * flag will use their expand ratio in a similar fashion to `Control.size_flags_stretch_ratio` (see
     * `set_column_expand_ratio`).
     *
     * Generated from Godot docs: Tree.set_column_expand
     */
    fun setColumnExpand(column: Int, expand: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setColumnExpandBind, handle, column, expand)
    }

    /**
     * Sets the relative expand ratio for a column. See `set_column_expand`.
     *
     * Generated from Godot docs: Tree.set_column_expand_ratio
     */
    fun setColumnExpandRatio(column: Int, ratio: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setColumnExpandRatioBind, handle, column, ratio)
    }

    /**
     * Allows to enable clipping for column's content, making the content size ignored.
     *
     * Generated from Godot docs: Tree.set_column_clip_content
     */
    fun setColumnClipContent(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setColumnClipContentBind, handle, column, enable)
    }

    /**
     * Returns `true` if the column has enabled expanding (see `set_column_expand`).
     *
     * Generated from Godot docs: Tree.is_column_expanding
     */
    fun isColumnExpanding(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isColumnExpandingBind, handle, column)
    }

    /**
     * Returns `true` if the column has enabled clipping (see `set_column_clip_content`).
     *
     * Generated from Godot docs: Tree.is_column_clipping_content
     */
    fun isColumnClippingContent(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isColumnClippingContentBind, handle, column)
    }

    /**
     * Returns the expand ratio assigned to the column.
     *
     * Generated from Godot docs: Tree.get_column_expand_ratio
     */
    fun getColumnExpandRatio(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColumnExpandRatioBind, handle, column)
    }

    /**
     * Returns the column's width in pixels.
     *
     * Generated from Godot docs: Tree.get_column_width
     */
    fun getColumnWidth(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getColumnWidthBind, handle, column)
    }

    /**
     * Returns the internal canvas item designated for custom drawing. See
     * `TreeItem.set_custom_draw_callback`. Note: This canvas item clears automatically on each Tree
     * draw call.
     *
     * Generated from Godot docs: Tree.get_custom_drawing_canvas_item
     */
    fun getCustomDrawingCanvasItem(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCustomDrawingCanvasItemBind, handle)
    }

    /**
     * If `true`, the tree's root is hidden.
     *
     * Generated from Godot docs: Tree.set_hide_root
     */
    fun setHideRoot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideRootBind, handle, enable)
    }

    /**
     * If `true`, the tree's root is hidden.
     *
     * Generated from Godot docs: Tree.is_root_hidden
     */
    fun isRootHidden(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRootHiddenBind, handle)
    }

    /**
     * Returns the next selected `TreeItem` after the given one, or `null` if the end is reached. If
     * `from` is `null`, this returns the first selected item.
     *
     * Generated from Godot docs: Tree.get_next_selected
     */
    fun getNextSelected(from: TreeItem): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(getNextSelectedBind, handle, from.handle))
    }

    /**
     * Returns the currently focused item, or `null` if no item is focused. In `SELECT_ROW` and
     * `SELECT_SINGLE` modes, the focused item is same as the selected item. In `SELECT_MULTI` mode,
     * the focused item is the item under the focus cursor, not necessarily selected. To get the
     * currently selected item(s), use `get_next_selected`.
     *
     * Generated from Godot docs: Tree.get_selected
     */
    fun getSelected(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSelectedBind, handle))
    }

    /**
     * Selects the specified `TreeItem` and column.
     *
     * Generated from Godot docs: Tree.set_selected
     */
    fun setSelected(item: TreeItem, column: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(setSelectedBind, handle, item.handle, column)
    }

    /**
     * Returns the currently focused column, or -1 if no column is focused. In `SELECT_SINGLE` mode,
     * the focused column is the selected column. In `SELECT_ROW` mode, the focused column is always 0
     * if any item is selected. In `SELECT_MULTI` mode, the focused column is the column under the
     * focus cursor, and there are not necessarily any column selected. To tell whether a column of an
     * item is selected, use `TreeItem.is_selected`.
     *
     * Generated from Godot docs: Tree.get_selected_column
     */
    fun getSelectedColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedColumnBind, handle)
    }

    /**
     * Returns the last pressed button's index.
     *
     * Generated from Godot docs: Tree.get_pressed_button
     */
    fun getPressedButton(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPressedButtonBind, handle)
    }

    /**
     * Allows single or multiple selection. See the `SelectMode` constants.
     *
     * Generated from Godot docs: Tree.set_select_mode
     */
    fun setSelectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSelectModeBind, handle, mode)
    }

    /**
     * Allows single or multiple selection. See the `SelectMode` constants.
     *
     * Generated from Godot docs: Tree.get_select_mode
     */
    fun getSelectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSelectModeBind, handle)
    }

    /**
     * Deselects all tree items (rows and columns). In `SELECT_MULTI` mode also removes selection
     * cursor.
     *
     * Generated from Godot docs: Tree.deselect_all
     */
    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

    /**
     * The number of columns. Prints an error and does not allow setting the columns during mouse
     * selection.
     *
     * Generated from Godot docs: Tree.set_columns
     */
    fun setColumns(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setColumnsBind, handle, amount)
    }

    /**
     * The number of columns. Prints an error and does not allow setting the columns during mouse
     * selection.
     *
     * Generated from Godot docs: Tree.get_columns
     */
    fun getColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColumnsBind, handle)
    }

    /**
     * Returns the currently edited item. Can be used with `item_edited` to get the item that was
     * modified.
     *
     * Generated from Godot docs: Tree.get_edited
     */
    fun getEdited(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedBind, handle))
    }

    /**
     * Returns the column for the currently edited item.
     *
     * Generated from Godot docs: Tree.get_edited_column
     */
    fun getEditedColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEditedColumnBind, handle)
    }

    /**
     * Edits the selected tree item as if it was clicked. Either the item must be set editable with
     * `TreeItem.set_editable` or `force_edit` must be `true`. Returns `true` if the item could be
     * edited. Fails if no item is selected.
     *
     * Generated from Godot docs: Tree.edit_selected
     */
    fun editSelected(forceEdit: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(editSelectedBind, handle, forceEdit)
    }

    /**
     * Returns the rectangle for custom popups. Helper to create custom cell controls that display a
     * popup. See `TreeItem.set_cell_mode`.
     *
     * Generated from Godot docs: Tree.get_custom_popup_rect
     */
    fun getCustomPopupRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getCustomPopupRectBind, handle)
    }

    /**
     * Returns the rectangle area for the specified `TreeItem`. If `column` is specified, only get the
     * position and size of that column, otherwise get the rectangle containing all columns. If a
     * button index is specified, the rectangle of that button will be returned.
     *
     * Generated from Godot docs: Tree.get_item_area_rect
     */
    fun getItemAreaRect(item: TreeItem, column: Int = -1, buttonIndex: Int = -1): Rect2 {
        return ObjectCalls.ptrcallWithObjectAndTwoIntArgsRetRect2(getItemAreaRectBind, handle, item.handle, column, buttonIndex)
    }

    /**
     * Returns the tree item at the specified position (relative to the tree origin position).
     *
     * Generated from Godot docs: Tree.get_item_at_position
     */
    fun getItemAtPosition(position: Vector2): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithVector2ArgRetObject(getItemAtPositionBind, handle, position))
    }

    /**
     * Returns the column index at `position`, or -1 if no item is there.
     *
     * Generated from Godot docs: Tree.get_column_at_position
     */
    fun getColumnAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getColumnAtPositionBind, handle, position)
    }

    /**
     * Returns the drop section at `position`, as permitted by enabled `DropModeFlags`. - `-1` if the
     * position is above the item. Typically used to insert as the item's previous sibling. - `0` if
     * the position is on the item. Typically used to insert as the item's last child. - `1` if the
     * position is below the item, when the item has no children. Typically used to insert as the
     * item's next sibling. If the item does have children, this section is still reachable by hovering
     * to the left of the item's collapse arrow, and below. - `2` if the position is below the item,
     * when the item has children. Typically used to insert as the item's first child. - `-100` if the
     * position is not over any item, or no `DropModeFlags` are set. See `DropModeFlags` for a
     * description of each drop region. To get the item which the returned drop section refers to, use
     * `get_item_at_position`.
     *
     * Generated from Godot docs: Tree.get_drop_section_at_position
     */
    fun getDropSectionAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getDropSectionAtPositionBind, handle, position)
    }

    /**
     * Returns the button ID at `position`, or -1 if no button is there.
     *
     * Generated from Godot docs: Tree.get_button_id_at_position
     */
    fun getButtonIdAtPosition(position: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(getButtonIdAtPositionBind, handle, position)
    }

    /**
     * Makes the currently focused cell visible. This will scroll the tree if necessary. In
     * `SELECT_ROW` mode, this will not do horizontal scrolling, as all the cells in the selected row
     * is focused logically. Note: Despite the name of this method, the focus cursor itself is only
     * visible in `SELECT_MULTI` mode.
     *
     * Generated from Godot docs: Tree.ensure_cursor_is_visible
     */
    fun ensureCursorIsVisible() {
        ObjectCalls.ptrcallNoArgs(ensureCursorIsVisibleBind, handle)
    }

    /**
     * If `true`, column titles are visible.
     *
     * Generated from Godot docs: Tree.set_column_titles_visible
     */
    fun setColumnTitlesVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setColumnTitlesVisibleBind, handle, visible)
    }

    /**
     * If `true`, column titles are visible.
     *
     * Generated from Godot docs: Tree.are_column_titles_visible
     */
    fun areColumnTitlesVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areColumnTitlesVisibleBind, handle)
    }

    /**
     * Sets the title of a column.
     *
     * Generated from Godot docs: Tree.set_column_title
     */
    fun setColumnTitle(column: Int, title: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleBind, handle, column, title)
    }

    /**
     * Returns the column's title.
     *
     * Generated from Godot docs: Tree.get_column_title
     */
    fun getColumnTitle(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleBind, handle, column)
    }

    /**
     * Sets the column title's tooltip text.
     *
     * Generated from Godot docs: Tree.set_column_title_tooltip_text
     */
    fun setColumnTitleTooltipText(column: Int, tooltipText: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleTooltipTextBind, handle, column, tooltipText)
    }

    /**
     * Returns the column title's tooltip text.
     *
     * Generated from Godot docs: Tree.get_column_title_tooltip_text
     */
    fun getColumnTitleTooltipText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleTooltipTextBind, handle, column)
    }

    /**
     * Sets the column title alignment. Note that `@GlobalScope.HORIZONTAL_ALIGNMENT_FILL` is not
     * supported for column titles.
     *
     * Generated from Godot docs: Tree.set_column_title_alignment
     */
    fun setColumnTitleAlignment(column: Int, titleAlignment: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setColumnTitleAlignmentBind, handle, column, titleAlignment)
    }

    /**
     * Returns the column title alignment.
     *
     * Generated from Godot docs: Tree.get_column_title_alignment
     */
    fun getColumnTitleAlignment(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColumnTitleAlignmentBind, handle, column)
    }

    /**
     * Sets column title base writing direction.
     *
     * Generated from Godot docs: Tree.set_column_title_direction
     */
    fun setColumnTitleDirection(column: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setColumnTitleDirectionBind, handle, column, direction)
    }

    /**
     * Returns column title base writing direction.
     *
     * Generated from Godot docs: Tree.get_column_title_direction
     */
    fun getColumnTitleDirection(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getColumnTitleDirectionBind, handle, column)
    }

    /**
     * Sets the language code of the given `column`'s title to `language`. This is used for
     * line-breaking and text shaping algorithms. If `language` is empty, the current locale is used.
     *
     * Generated from Godot docs: Tree.set_column_title_language
     */
    fun setColumnTitleLanguage(column: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setColumnTitleLanguageBind, handle, column, language)
    }

    /**
     * Returns column title language code.
     *
     * Generated from Godot docs: Tree.get_column_title_language
     */
    fun getColumnTitleLanguage(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleLanguageBind, handle, column)
    }

    /**
     * Returns the current scrolling position.
     *
     * Generated from Godot docs: Tree.get_scroll
     */
    fun getScroll(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScrollBind, handle)
    }

    /**
     * Causes the `Tree` to jump to the specified `TreeItem`.
     *
     * Generated from Godot docs: Tree.scroll_to_item
     */
    fun scrollToItem(item: TreeItem, centerOnItem: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(scrollToItemBind, handle, item.handle, centerOnItem)
    }

    /**
     * If `true`, enables horizontal scrolling.
     *
     * Generated from Godot docs: Tree.set_h_scroll_enabled
     */
    fun setHScrollEnabled(hScroll: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHScrollEnabledBind, handle, hScroll)
    }

    /**
     * If `true`, enables horizontal scrolling.
     *
     * Generated from Godot docs: Tree.is_h_scroll_enabled
     */
    fun isHScrollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHScrollEnabledBind, handle)
    }

    /**
     * If `true`, enables vertical scrolling.
     *
     * Generated from Godot docs: Tree.set_v_scroll_enabled
     */
    fun setVScrollEnabled(hScroll: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVScrollEnabledBind, handle, hScroll)
    }

    /**
     * If `true`, enables vertical scrolling.
     *
     * Generated from Godot docs: Tree.is_v_scroll_enabled
     */
    fun isVScrollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVScrollEnabledBind, handle)
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown.
     *
     * Generated from Godot docs: Tree.set_scroll_hint_mode
     */
    fun setScrollHintMode(scrollHintMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScrollHintModeBind, handle, scrollHintMode)
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown.
     *
     * Generated from Godot docs: Tree.get_scroll_hint_mode
     */
    fun getScrollHintMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScrollHintModeBind, handle)
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: Tree.set_tile_scroll_hint
     */
    fun setTileScrollHint(tileScrollHint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTileScrollHintBind, handle, tileScrollHint)
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: Tree.is_scroll_hint_tiled
     */
    fun isScrollHintTiled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHintTiledBind, handle)
    }

    /**
     * If `true`, the folding arrow is hidden.
     *
     * Generated from Godot docs: Tree.set_hide_folding
     */
    fun setHideFolding(hide: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideFoldingBind, handle, hide)
    }

    /**
     * If `true`, the folding arrow is hidden.
     *
     * Generated from Godot docs: Tree.is_folding_hidden
     */
    fun isFoldingHidden(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFoldingHiddenBind, handle)
    }

    /**
     * If `true`, recursive folding is enabled for this `Tree`. Holding down Shift while clicking the
     * fold arrow or using `ui_right`/`ui_left` shortcuts collapses or uncollapses the `TreeItem` and
     * all its descendants.
     *
     * Generated from Godot docs: Tree.set_enable_recursive_folding
     */
    fun setEnableRecursiveFolding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableRecursiveFoldingBind, handle, enable)
    }

    /**
     * If `true`, recursive folding is enabled for this `Tree`. Holding down Shift while clicking the
     * fold arrow or using `ui_right`/`ui_left` shortcuts collapses or uncollapses the `TreeItem` and
     * all its descendants.
     *
     * Generated from Godot docs: Tree.is_recursive_folding_enabled
     */
    fun isRecursiveFoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecursiveFoldingEnabledBind, handle)
    }

    /**
     * If `true`, tree items will unfold when hovered over during a drag-and-drop. The delay for when
     * this happens is dictated by `dragging_unfold_wait_msec`.
     *
     * Generated from Godot docs: Tree.set_enable_drag_unfolding
     */
    fun setEnableDragUnfolding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDragUnfoldingBind, handle, enable)
    }

    /**
     * If `true`, tree items will unfold when hovered over during a drag-and-drop. The delay for when
     * this happens is dictated by `dragging_unfold_wait_msec`.
     *
     * Generated from Godot docs: Tree.is_drag_unfolding_enabled
     */
    fun isDragUnfoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragUnfoldingEnabledBind, handle)
    }

    /**
     * The drop mode as an OR combination of flags. See `DropModeFlags` constants. Once dropping is
     * done, reverts to `DROP_MODE_DISABLED`. Setting this during `Control._can_drop_data` is
     * recommended. This controls the drop sections, i.e. the decision and drawing of possible drop
     * locations based on the mouse position.
     *
     * Generated from Godot docs: Tree.set_drop_mode_flags
     */
    fun setDropModeFlags(flags: Int) {
        ObjectCalls.ptrcallWithIntArg(setDropModeFlagsBind, handle, flags)
    }

    /**
     * The drop mode as an OR combination of flags. See `DropModeFlags` constants. Once dropping is
     * done, reverts to `DROP_MODE_DISABLED`. Setting this during `Control._can_drop_data` is
     * recommended. This controls the drop sections, i.e. the decision and drawing of possible drop
     * locations based on the mouse position.
     *
     * Generated from Godot docs: Tree.get_drop_mode_flags
     */
    fun getDropModeFlags(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDropModeFlagsBind, handle)
    }

    /**
     * If `true`, a right mouse button click can select items.
     *
     * Generated from Godot docs: Tree.set_allow_rmb_select
     */
    fun setAllowRmbSelect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowRmbSelectBind, handle, allow)
    }

    /**
     * If `true`, a right mouse button click can select items.
     *
     * Generated from Godot docs: Tree.get_allow_rmb_select
     */
    fun getAllowRmbSelect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowRmbSelectBind, handle)
    }

    /**
     * If `true`, the currently selected cell may be selected again.
     *
     * Generated from Godot docs: Tree.set_allow_reselect
     */
    fun setAllowReselect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowReselectBind, handle, allow)
    }

    /**
     * If `true`, the currently selected cell may be selected again.
     *
     * Generated from Godot docs: Tree.get_allow_reselect
     */
    fun getAllowReselect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowReselectBind, handle)
    }

    /**
     * If `true`, allows navigating the `Tree` with letter keys through incremental search.
     *
     * Generated from Godot docs: Tree.set_allow_search
     */
    fun setAllowSearch(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSearchBind, handle, allow)
    }

    /**
     * If `true`, allows navigating the `Tree` with letter keys through incremental search.
     *
     * Generated from Godot docs: Tree.get_allow_search
     */
    fun getAllowSearch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowSearchBind, handle)
    }

    /**
     * If `true`, tree items with no tooltip assigned display their text as their tooltip. See also
     * `TreeItem.get_tooltip_text` and `TreeItem.get_button_tooltip_text`.
     *
     * Generated from Godot docs: Tree.set_auto_tooltip
     */
    fun setAutoTooltip(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoTooltipBind, handle, enable)
    }

    /**
     * If `true`, tree items with no tooltip assigned display their text as their tooltip. See also
     * `TreeItem.get_tooltip_text` and `TreeItem.get_button_tooltip_text`.
     *
     * Generated from Godot docs: Tree.is_auto_tooltip_enabled
     */
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
