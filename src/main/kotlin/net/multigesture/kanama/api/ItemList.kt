package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * A vertical list of selectable items with one or multiple columns.
 *
 * Generated from Godot docs: ItemList
 */
class ItemList(handle: MemorySegment) : Control(handle) {
    var selectMode: Long
        @JvmName("selectModeProperty")
        get() = getSelectMode()
        @JvmName("setSelectModeProperty")
        set(value) = setSelectMode(value)

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

    var maxTextLines: Int
        @JvmName("maxTextLinesProperty")
        get() = getMaxTextLines()
        @JvmName("setMaxTextLinesProperty")
        set(value) = setMaxTextLines(value)

    var autoWidth: Boolean
        @JvmName("autoWidthProperty")
        get() = hasAutoWidth()
        @JvmName("setAutoWidthProperty")
        set(value) = setAutoWidth(value)

    var autoHeight: Boolean
        @JvmName("autoHeightProperty")
        get() = hasAutoHeight()
        @JvmName("setAutoHeightProperty")
        set(value) = setAutoHeight(value)

    var textOverrunBehavior: Long
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var wraparoundItems: Boolean
        @JvmName("wraparoundItemsProperty")
        get() = hasWraparoundItems()
        @JvmName("setWraparoundItemsProperty")
        set(value) = setWraparoundItems(value)

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

    var itemCount: Int
        @JvmName("itemCountProperty")
        get() = getItemCount()
        @JvmName("setItemCountProperty")
        set(value) = setItemCount(value)

    var maxColumns: Int
        @JvmName("maxColumnsProperty")
        get() = getMaxColumns()
        @JvmName("setMaxColumnsProperty")
        set(value) = setMaxColumns(value)

    var sameColumnWidth: Boolean
        @JvmName("sameColumnWidthProperty")
        get() = isSameColumnWidth()
        @JvmName("setSameColumnWidthProperty")
        set(value) = setSameColumnWidth(value)

    var fixedColumnWidth: Int
        @JvmName("fixedColumnWidthProperty")
        get() = getFixedColumnWidth()
        @JvmName("setFixedColumnWidthProperty")
        set(value) = setFixedColumnWidth(value)

    var iconMode: Long
        @JvmName("iconModeProperty")
        get() = getIconMode()
        @JvmName("setIconModeProperty")
        set(value) = setIconMode(value)

    var iconScale: Double
        @JvmName("iconScaleProperty")
        get() = getIconScale()
        @JvmName("setIconScaleProperty")
        set(value) = setIconScale(value)

    var fixedIconSize: Vector2i
        @JvmName("fixedIconSizeProperty")
        get() = getFixedIconSize()
        @JvmName("setFixedIconSizeProperty")
        set(value) = setFixedIconSize(value)

    /**
     * Adds an item to the item list with specified text. Returns the index of an added item. Specify
     * an `icon`, or use `null` as the `icon` for a list item with no icon. If `selectable` is `true`,
     * the list item will be selectable.
     *
     * Generated from Godot docs: ItemList.add_item
     */
    fun addItem(text: String, icon: Texture2D?, selectable: Boolean = true): Int {
        return ObjectCalls.ptrcallWithStringObjectBoolArgsRetInt(addItemBind, handle, text, icon?.requireOpenHandle() ?: MemorySegment.NULL, selectable)
    }

    /**
     * Adds an item to the item list with no text, only an icon. Returns the index of an added item.
     *
     * Generated from Godot docs: ItemList.add_icon_item
     */
    fun addIconItem(icon: Texture2D?, selectable: Boolean = true): Int {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetInt(addIconItemBind, handle, icon?.requireOpenHandle() ?: MemorySegment.NULL, selectable)
    }

    /**
     * Sets text of the item associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_text
     */
    fun setItemText(idx: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTextBind, handle, idx, text)
    }

    /**
     * Returns the text associated with the specified index.
     *
     * Generated from Godot docs: ItemList.get_item_text
     */
    fun getItemText(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, idx)
    }

    /**
     * Sets (or replaces) the icon's `Texture2D` associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_icon
     */
    fun setItemIcon(idx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemIconBind, handle, idx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the icon associated with the specified index.
     *
     * Generated from Godot docs: ItemList.get_item_icon
     */
    fun getItemIcon(idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemIconBind, handle, idx))
    }

    /**
     * Sets item's text base writing direction.
     *
     * Generated from Godot docs: ItemList.set_item_text_direction
     */
    fun setItemTextDirection(idx: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemTextDirectionBind, handle, idx, direction)
    }

    /**
     * Returns item's text base writing direction.
     *
     * Generated from Godot docs: ItemList.get_item_text_direction
     */
    fun getItemTextDirection(idx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemTextDirectionBind, handle, idx)
    }

    /**
     * Sets the language code of the text for the item at the given index to `language`. This is used
     * for line-breaking and text shaping algorithms. If `language` is empty, the current locale is
     * used.
     *
     * Generated from Godot docs: ItemList.set_item_language
     */
    fun setItemLanguage(idx: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemLanguageBind, handle, idx, language)
    }

    /**
     * Returns item's text language code.
     *
     * Generated from Godot docs: ItemList.get_item_language
     */
    fun getItemLanguage(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemLanguageBind, handle, idx)
    }

    /**
     * Sets the auto translate mode of the item associated with the specified index. Items use
     * `Node.AUTO_TRANSLATE_MODE_INHERIT` by default, which uses the same auto translate mode as the
     * `ItemList` itself.
     *
     * Generated from Godot docs: ItemList.set_item_auto_translate_mode
     */
    fun setItemAutoTranslateMode(idx: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAutoTranslateModeBind, handle, idx, mode)
    }

    /**
     * Returns item's auto translate mode.
     *
     * Generated from Godot docs: ItemList.get_item_auto_translate_mode
     */
    fun getItemAutoTranslateMode(idx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAutoTranslateModeBind, handle, idx)
    }

    /**
     * Sets whether the item icon will be drawn transposed.
     *
     * Generated from Godot docs: ItemList.set_item_icon_transposed
     */
    fun setItemIconTransposed(idx: Int, transposed: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemIconTransposedBind, handle, idx, transposed)
    }

    /**
     * Returns `true` if the item icon will be drawn transposed, i.e. the X and Y axes are swapped.
     *
     * Generated from Godot docs: ItemList.is_item_icon_transposed
     */
    fun isItemIconTransposed(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemIconTransposedBind, handle, idx)
    }

    /**
     * Sets the region of item's icon used. The whole icon will be used if the region has no area.
     *
     * Generated from Godot docs: ItemList.set_item_icon_region
     */
    fun setItemIconRegion(idx: Int, rect: Rect2) {
        ObjectCalls.ptrcallWithIntAndRect2Arg(setItemIconRegionBind, handle, idx, rect)
    }

    /**
     * Returns the region of item's icon used. The whole icon will be used if the region has no area.
     *
     * Generated from Godot docs: ItemList.get_item_icon_region
     */
    fun getItemIconRegion(idx: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(getItemIconRegionBind, handle, idx)
    }

    /**
     * Sets a modulating `Color` of the item associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_icon_modulate
     */
    fun setItemIconModulate(idx: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemIconModulateBind, handle, idx, modulate)
    }

    /**
     * Returns a `Color` modulating item's icon at the specified index.
     *
     * Generated from Godot docs: ItemList.get_item_icon_modulate
     */
    fun getItemIconModulate(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemIconModulateBind, handle, idx)
    }

    /**
     * Allows or disallows selection of the item associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_selectable
     */
    fun setItemSelectable(idx: Int, selectable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemSelectableBind, handle, idx, selectable)
    }

    /**
     * Returns `true` if the item at the specified index is selectable.
     *
     * Generated from Godot docs: ItemList.is_item_selectable
     */
    fun isItemSelectable(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemSelectableBind, handle, idx)
    }

    /**
     * Disables (or enables) the item at the specified index. Disabled items cannot be selected and do
     * not trigger activation signals (when double-clicking or pressing Enter).
     *
     * Generated from Godot docs: ItemList.set_item_disabled
     */
    fun setItemDisabled(idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemDisabledBind, handle, idx, disabled)
    }

    /**
     * Returns `true` if the item at the specified index is disabled.
     *
     * Generated from Godot docs: ItemList.is_item_disabled
     */
    fun isItemDisabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemDisabledBind, handle, idx)
    }

    /**
     * Sets a value (of any type) to be stored with the item associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_metadata
     */
    fun setItemMetadata(idx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, idx, metadata)
    }

    /**
     * Returns the metadata value of the specified index.
     *
     * Generated from Godot docs: ItemList.get_item_metadata
     */
    fun getItemMetadata(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, idx)
    }

    /**
     * Sets the background color of the item specified by `idx` index to the specified `Color`.
     *
     * Generated from Godot docs: ItemList.set_item_custom_bg_color
     */
    fun setItemCustomBgColor(idx: Int, customBgColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemCustomBgColorBind, handle, idx, customBgColor)
    }

    /**
     * Returns the custom background color of the item specified by `idx` index.
     *
     * Generated from Godot docs: ItemList.get_item_custom_bg_color
     */
    fun getItemCustomBgColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemCustomBgColorBind, handle, idx)
    }

    /**
     * Sets the foreground color of the item specified by `idx` index to the specified `Color`.
     *
     * Generated from Godot docs: ItemList.set_item_custom_fg_color
     */
    fun setItemCustomFgColor(idx: Int, customFgColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemCustomFgColorBind, handle, idx, customFgColor)
    }

    /**
     * Returns the custom foreground color of the item specified by `idx` index.
     *
     * Generated from Godot docs: ItemList.get_item_custom_fg_color
     */
    fun getItemCustomFgColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemCustomFgColorBind, handle, idx)
    }

    /**
     * Returns the position and size of the item with the specified index, in the coordinate system of
     * the `ItemList` node. If `expand` is `true` the last column expands to fill the rest of the row.
     * Note: The returned value is unreliable if called right after modifying the `ItemList`, before it
     * redraws in the next frame.
     *
     * Generated from Godot docs: ItemList.get_item_rect
     */
    fun getItemRect(idx: Int, expand: Boolean = true): Rect2 {
        return ObjectCalls.ptrcallWithIntAndBoolArgRetRect2(getItemRectBind, handle, idx, expand)
    }

    /**
     * Sets whether the tooltip hint is enabled for specified item index.
     *
     * Generated from Godot docs: ItemList.set_item_tooltip_enabled
     */
    fun setItemTooltipEnabled(idx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemTooltipEnabledBind, handle, idx, enable)
    }

    /**
     * Returns `true` if the tooltip is enabled for specified item index.
     *
     * Generated from Godot docs: ItemList.is_item_tooltip_enabled
     */
    fun isItemTooltipEnabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemTooltipEnabledBind, handle, idx)
    }

    /**
     * Sets the tooltip hint for the item associated with the specified index.
     *
     * Generated from Godot docs: ItemList.set_item_tooltip
     */
    fun setItemTooltip(idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTooltipBind, handle, idx, tooltip)
    }

    /**
     * Returns the tooltip hint associated with the specified index.
     *
     * Generated from Godot docs: ItemList.get_item_tooltip
     */
    fun getItemTooltip(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTooltipBind, handle, idx)
    }

    /**
     * Selects the item at the specified index. Note: This method does not trigger the item selection
     * signal.
     *
     * Generated from Godot docs: ItemList.select
     */
    fun select(idx: Int, single: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(selectBind, handle, idx, single)
    }

    /**
     * Ensures the item associated with the specified index is not selected.
     *
     * Generated from Godot docs: ItemList.deselect
     */
    fun deselect(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(deselectBind, handle, idx)
    }

    /**
     * Ensures there are no items selected.
     *
     * Generated from Godot docs: ItemList.deselect_all
     */
    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

    /**
     * Returns `true` if the item at the specified index is currently selected.
     *
     * Generated from Godot docs: ItemList.is_selected
     */
    fun isSelected(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSelectedBind, handle, idx)
    }

    /**
     * Returns an array with the indexes of the selected items.
     *
     * Generated from Godot docs: ItemList.get_selected_items
     */
    fun getSelectedItems(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSelectedItemsBind, handle)
    }

    /**
     * Moves item from index `from_idx` to `to_idx`.
     *
     * Generated from Godot docs: ItemList.move_item
     */
    fun moveItem(fromIdx: Int, toIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveItemBind, handle, fromIdx, toIdx)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: ItemList.set_item_count
     */
    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    /**
     * The number of items currently in the list.
     *
     * Generated from Godot docs: ItemList.get_item_count
     */
    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    /**
     * Removes the item specified by `idx` index from the list.
     *
     * Generated from Godot docs: ItemList.remove_item
     */
    fun removeItem(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, idx)
    }

    /**
     * Removes all items from the list.
     *
     * Generated from Godot docs: ItemList.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Sorts items in the list by their text.
     *
     * Generated from Godot docs: ItemList.sort_items_by_text
     */
    fun sortItemsByText() {
        ObjectCalls.ptrcallNoArgs(sortItemsByTextBind, handle)
    }

    /**
     * The width all columns will be adjusted to. A value of zero disables the adjustment, each item
     * will have a width equal to the width of its content and the columns will have an uneven width.
     *
     * Generated from Godot docs: ItemList.set_fixed_column_width
     */
    fun setFixedColumnWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedColumnWidthBind, handle, width)
    }

    /**
     * The width all columns will be adjusted to. A value of zero disables the adjustment, each item
     * will have a width equal to the width of its content and the columns will have an uneven width.
     *
     * Generated from Godot docs: ItemList.get_fixed_column_width
     */
    fun getFixedColumnWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedColumnWidthBind, handle)
    }

    /**
     * Whether all columns will have the same width. If `true`, the width is equal to the largest
     * column width of all columns.
     *
     * Generated from Godot docs: ItemList.set_same_column_width
     */
    fun setSameColumnWidth(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSameColumnWidthBind, handle, enable)
    }

    /**
     * Whether all columns will have the same width. If `true`, the width is equal to the largest
     * column width of all columns.
     *
     * Generated from Godot docs: ItemList.is_same_column_width
     */
    fun isSameColumnWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSameColumnWidthBind, handle)
    }

    /**
     * Maximum lines of text allowed in each item. Space will be reserved even when there is not enough
     * lines of text to display. Note: This property takes effect only when `icon_mode` is
     * `ICON_MODE_TOP`. To make the text wrap, `fixed_column_width` should be greater than zero.
     *
     * Generated from Godot docs: ItemList.set_max_text_lines
     */
    fun setMaxTextLines(lines: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTextLinesBind, handle, lines)
    }

    /**
     * Maximum lines of text allowed in each item. Space will be reserved even when there is not enough
     * lines of text to display. Note: This property takes effect only when `icon_mode` is
     * `ICON_MODE_TOP`. To make the text wrap, `fixed_column_width` should be greater than zero.
     *
     * Generated from Godot docs: ItemList.get_max_text_lines
     */
    fun getMaxTextLines(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTextLinesBind, handle)
    }

    /**
     * Maximum columns the list will have. If greater than zero, the content will be split among the
     * specified columns. A value of zero means unlimited columns, i.e. all items will be put in the
     * same row.
     *
     * Generated from Godot docs: ItemList.set_max_columns
     */
    fun setMaxColumns(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxColumnsBind, handle, amount)
    }

    /**
     * Maximum columns the list will have. If greater than zero, the content will be split among the
     * specified columns. A value of zero means unlimited columns, i.e. all items will be put in the
     * same row.
     *
     * Generated from Godot docs: ItemList.get_max_columns
     */
    fun getMaxColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxColumnsBind, handle)
    }

    /**
     * Allows single or multiple item selection. See the `SelectMode` constants.
     *
     * Generated from Godot docs: ItemList.set_select_mode
     */
    fun setSelectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSelectModeBind, handle, mode)
    }

    /**
     * Allows single or multiple item selection. See the `SelectMode` constants.
     *
     * Generated from Godot docs: ItemList.get_select_mode
     */
    fun getSelectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSelectModeBind, handle)
    }

    /**
     * The icon position, whether above or to the left of the text. See the `IconMode` constants.
     *
     * Generated from Godot docs: ItemList.set_icon_mode
     */
    fun setIconMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconModeBind, handle, mode)
    }

    /**
     * The icon position, whether above or to the left of the text. See the `IconMode` constants.
     *
     * Generated from Godot docs: ItemList.get_icon_mode
     */
    fun getIconMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIconModeBind, handle)
    }

    /**
     * The size all icons will be adjusted to. If either X or Y component is not greater than zero,
     * icon size won't be affected.
     *
     * Generated from Godot docs: ItemList.set_fixed_icon_size
     */
    fun setFixedIconSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setFixedIconSizeBind, handle, size)
    }

    /**
     * The size all icons will be adjusted to. If either X or Y component is not greater than zero,
     * icon size won't be affected.
     *
     * Generated from Godot docs: ItemList.get_fixed_icon_size
     */
    fun getFixedIconSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getFixedIconSizeBind, handle)
    }

    /**
     * The scale of icon applied after `fixed_icon_size` and transposing takes effect.
     *
     * Generated from Godot docs: ItemList.set_icon_scale
     */
    fun setIconScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setIconScaleBind, handle, scale)
    }

    /**
     * The scale of icon applied after `fixed_icon_size` and transposing takes effect.
     *
     * Generated from Godot docs: ItemList.get_icon_scale
     */
    fun getIconScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getIconScaleBind, handle)
    }

    /**
     * If `true`, right mouse button click can select items.
     *
     * Generated from Godot docs: ItemList.set_allow_rmb_select
     */
    fun setAllowRmbSelect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowRmbSelectBind, handle, allow)
    }

    /**
     * If `true`, right mouse button click can select items.
     *
     * Generated from Godot docs: ItemList.get_allow_rmb_select
     */
    fun getAllowRmbSelect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowRmbSelectBind, handle)
    }

    /**
     * If `true`, the currently selected item can be selected again.
     *
     * Generated from Godot docs: ItemList.set_allow_reselect
     */
    fun setAllowReselect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowReselectBind, handle, allow)
    }

    /**
     * If `true`, the currently selected item can be selected again.
     *
     * Generated from Godot docs: ItemList.get_allow_reselect
     */
    fun getAllowReselect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowReselectBind, handle)
    }

    /**
     * If `true`, allows navigating the `ItemList` with letter keys through incremental search.
     *
     * Generated from Godot docs: ItemList.set_allow_search
     */
    fun setAllowSearch(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSearchBind, handle, allow)
    }

    /**
     * If `true`, allows navigating the `ItemList` with letter keys through incremental search.
     *
     * Generated from Godot docs: ItemList.get_allow_search
     */
    fun getAllowSearch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowSearchBind, handle)
    }

    /**
     * If `true`, the control will automatically resize the width to fit its content.
     *
     * Generated from Godot docs: ItemList.set_auto_width
     */
    fun setAutoWidth(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoWidthBind, handle, enable)
    }

    /**
     * If `true`, the control will automatically resize the width to fit its content.
     *
     * Generated from Godot docs: ItemList.has_auto_width
     */
    fun hasAutoWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutoWidthBind, handle)
    }

    /**
     * If `true`, the control will automatically resize the height to fit its content.
     *
     * Generated from Godot docs: ItemList.set_auto_height
     */
    fun setAutoHeight(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoHeightBind, handle, enable)
    }

    /**
     * If `true`, the control will automatically resize the height to fit its content.
     *
     * Generated from Godot docs: ItemList.has_auto_height
     */
    fun hasAutoHeight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutoHeightBind, handle)
    }

    /**
     * Returns `true` if one or more items are selected.
     *
     * Generated from Godot docs: ItemList.is_anything_selected
     */
    fun isAnythingSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAnythingSelectedBind, handle)
    }

    /**
     * Returns the item index at the given `position`. When there is no item at that point, -1 will be
     * returned if `exact` is `true`, and the closest item index will be returned otherwise. Note: The
     * returned value is unreliable if called right after modifying the `ItemList`, before it redraws
     * in the next frame.
     *
     * Generated from Godot docs: ItemList.get_item_at_position
     */
    fun getItemAtPosition(position: Vector2, exact: Boolean = false): Int {
        return ObjectCalls.ptrcallWithVector2AndBoolArgRetInt(getItemAtPositionBind, handle, position, exact)
    }

    /**
     * Ensures the currently selected item (the first selected item if multiple selection is enabled)
     * is visible, adjusting the scroll position as necessary. See also `center_on_current`.
     *
     * Generated from Godot docs: ItemList.ensure_current_is_visible
     */
    fun ensureCurrentIsVisible() {
        ObjectCalls.ptrcallNoArgs(ensureCurrentIsVisibleBind, handle)
    }

    /**
     * Ensures the currently selected item (the first selected item if multiple selection is enabled)
     * is visible, adjusting the scroll position as necessary to place the item at the center of the
     * list if possible. See also `ensure_current_is_visible`. Fails and prints an error if both
     * arguments are `false`.
     *
     * Generated from Godot docs: ItemList.center_on_current
     */
    fun centerOnCurrent(centerVerically: Boolean = true, centerHorizontally: Boolean = true) {
        ObjectCalls.ptrcallWithTwoBoolArgs(centerOnCurrentBind, handle, centerVerically, centerHorizontally)
    }

    /**
     * Returns the vertical scrollbar. Warning: This is a required internal node, removing and freeing
     * it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: ItemList.get_v_scroll_bar
     */
    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    /**
     * Returns the horizontal scrollbar. Warning: This is a required internal node, removing and
     * freeing it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: ItemList.get_h_scroll_bar
     */
    fun getHScrollBar(): HScrollBar? {
        return HScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHScrollBarBind, handle))
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown.
     *
     * Generated from Godot docs: ItemList.set_scroll_hint_mode
     */
    fun setScrollHintMode(scrollHintMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setScrollHintModeBind, handle, scrollHintMode)
    }

    /**
     * The way which scroll hints (indicators that show that the content can still be scrolled in a
     * certain direction) will be shown.
     *
     * Generated from Godot docs: ItemList.get_scroll_hint_mode
     */
    fun getScrollHintMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getScrollHintModeBind, handle)
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: ItemList.set_tile_scroll_hint
     */
    fun setTileScrollHint(tileScrollHint: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTileScrollHintBind, handle, tileScrollHint)
    }

    /**
     * If `true`, the scroll hint texture will be tiled instead of stretched. See `scroll_hint_mode`.
     *
     * Generated from Godot docs: ItemList.is_scroll_hint_tiled
     */
    fun isScrollHintTiled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollHintTiledBind, handle)
    }

    /**
     * The clipping behavior when the text exceeds an item's bounding rectangle.
     *
     * Generated from Godot docs: ItemList.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    /**
     * The clipping behavior when the text exceeds an item's bounding rectangle.
     *
     * Generated from Godot docs: ItemList.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    /**
     * If `true`, the control will automatically move items into a new row to fit its content. See also
     * `HFlowContainer` for this behavior. If `false`, the control will add a horizontal scrollbar to
     * make all items visible.
     *
     * Generated from Godot docs: ItemList.set_wraparound_items
     */
    fun setWraparoundItems(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWraparoundItemsBind, handle, enable)
    }

    /**
     * If `true`, the control will automatically move items into a new row to fit its content. See also
     * `HFlowContainer` for this behavior. If `false`, the control will add a horizontal scrollbar to
     * make all items visible.
     *
     * Generated from Godot docs: ItemList.has_wraparound_items
     */
    fun hasWraparoundItems(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasWraparoundItemsBind, handle)
    }

    /**
     * Forces an update to the list size based on its items. This happens automatically whenever size
     * of the items, or other relevant settings like `auto_height`, change. The method can be used to
     * trigger the update ahead of next drawing pass.
     *
     * Generated from Godot docs: ItemList.force_update_list_size
     */
    fun forceUpdateListSize() {
        ObjectCalls.ptrcallNoArgs(forceUpdateListSizeBind, handle)
    }

    object Signals {
        const val itemSelected: String = "item_selected"
        const val emptyClicked: String = "empty_clicked"
        const val itemClicked: String = "item_clicked"
        const val multiSelected: String = "multi_selected"
        const val itemActivated: String = "item_activated"
    }

    companion object {
        const val ICON_MODE_TOP: Long = 0L
        const val ICON_MODE_LEFT: Long = 1L
        const val SELECT_SINGLE: Long = 0L
        const val SELECT_MULTI: Long = 1L
        const val SELECT_TOGGLE: Long = 2L
        const val SCROLL_HINT_MODE_DISABLED: Long = 0L
        const val SCROLL_HINT_MODE_BOTH: Long = 1L
        const val SCROLL_HINT_MODE_TOP: Long = 2L
        const val SCROLL_HINT_MODE_BOTTOM: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ItemList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ItemList? =
            if (handle.address() == 0L) null else ItemList(handle)

        private const val ADD_ITEM_HASH = 359861678L
        private val addItemBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "add_item", ADD_ITEM_HASH)
        }

        private const val ADD_ICON_ITEM_HASH = 4256579627L
        private val addIconItemBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "add_icon_item", ADD_ICON_ITEM_HASH)
        }

        private const val SET_ITEM_TEXT_HASH = 501894301L
        private val setItemTextBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_text", SET_ITEM_TEXT_HASH)
        }

        private const val GET_ITEM_TEXT_HASH = 844755477L
        private val getItemTextBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_text", GET_ITEM_TEXT_HASH)
        }

        private const val SET_ITEM_ICON_HASH = 666127730L
        private val setItemIconBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_icon", SET_ITEM_ICON_HASH)
        }

        private const val GET_ITEM_ICON_HASH = 3536238170L
        private val getItemIconBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_icon", GET_ITEM_ICON_HASH)
        }

        private const val SET_ITEM_TEXT_DIRECTION_HASH = 1707680378L
        private val setItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_text_direction", SET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val GET_ITEM_TEXT_DIRECTION_HASH = 4235602388L
        private val getItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_text_direction", GET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val SET_ITEM_LANGUAGE_HASH = 501894301L
        private val setItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_language", SET_ITEM_LANGUAGE_HASH)
        }

        private const val GET_ITEM_LANGUAGE_HASH = 844755477L
        private val getItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_language", GET_ITEM_LANGUAGE_HASH)
        }

        private const val SET_ITEM_AUTO_TRANSLATE_MODE_HASH = 287402019L
        private val setItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_auto_translate_mode", SET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_ITEM_AUTO_TRANSLATE_MODE_HASH = 906302372L
        private val getItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_auto_translate_mode", GET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_ITEM_ICON_TRANSPOSED_HASH = 300928843L
        private val setItemIconTransposedBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_icon_transposed", SET_ITEM_ICON_TRANSPOSED_HASH)
        }

        private const val IS_ITEM_ICON_TRANSPOSED_HASH = 1116898809L
        private val isItemIconTransposedBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_item_icon_transposed", IS_ITEM_ICON_TRANSPOSED_HASH)
        }

        private const val SET_ITEM_ICON_REGION_HASH = 1356297692L
        private val setItemIconRegionBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_icon_region", SET_ITEM_ICON_REGION_HASH)
        }

        private const val GET_ITEM_ICON_REGION_HASH = 3327874267L
        private val getItemIconRegionBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_icon_region", GET_ITEM_ICON_REGION_HASH)
        }

        private const val SET_ITEM_ICON_MODULATE_HASH = 2878471219L
        private val setItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_icon_modulate", SET_ITEM_ICON_MODULATE_HASH)
        }

        private const val GET_ITEM_ICON_MODULATE_HASH = 3457211756L
        private val getItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_icon_modulate", GET_ITEM_ICON_MODULATE_HASH)
        }

        private const val SET_ITEM_SELECTABLE_HASH = 300928843L
        private val setItemSelectableBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_selectable", SET_ITEM_SELECTABLE_HASH)
        }

        private const val IS_ITEM_SELECTABLE_HASH = 1116898809L
        private val isItemSelectableBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_item_selectable", IS_ITEM_SELECTABLE_HASH)
        }

        private const val SET_ITEM_DISABLED_HASH = 300928843L
        private val setItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_disabled", SET_ITEM_DISABLED_HASH)
        }

        private const val IS_ITEM_DISABLED_HASH = 1116898809L
        private val isItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_item_disabled", IS_ITEM_DISABLED_HASH)
        }

        private const val SET_ITEM_METADATA_HASH = 2152698145L
        private val setItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_metadata", SET_ITEM_METADATA_HASH)
        }

        private const val GET_ITEM_METADATA_HASH = 4227898402L
        private val getItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_metadata", GET_ITEM_METADATA_HASH)
        }

        private const val SET_ITEM_CUSTOM_BG_COLOR_HASH = 2878471219L
        private val setItemCustomBgColorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_custom_bg_color", SET_ITEM_CUSTOM_BG_COLOR_HASH)
        }

        private const val GET_ITEM_CUSTOM_BG_COLOR_HASH = 3457211756L
        private val getItemCustomBgColorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_custom_bg_color", GET_ITEM_CUSTOM_BG_COLOR_HASH)
        }

        private const val SET_ITEM_CUSTOM_FG_COLOR_HASH = 2878471219L
        private val setItemCustomFgColorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_custom_fg_color", SET_ITEM_CUSTOM_FG_COLOR_HASH)
        }

        private const val GET_ITEM_CUSTOM_FG_COLOR_HASH = 3457211756L
        private val getItemCustomFgColorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_custom_fg_color", GET_ITEM_CUSTOM_FG_COLOR_HASH)
        }

        private const val GET_ITEM_RECT_HASH = 159227807L
        private val getItemRectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_rect", GET_ITEM_RECT_HASH)
        }

        private const val SET_ITEM_TOOLTIP_ENABLED_HASH = 300928843L
        private val setItemTooltipEnabledBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_tooltip_enabled", SET_ITEM_TOOLTIP_ENABLED_HASH)
        }

        private const val IS_ITEM_TOOLTIP_ENABLED_HASH = 1116898809L
        private val isItemTooltipEnabledBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_item_tooltip_enabled", IS_ITEM_TOOLTIP_ENABLED_HASH)
        }

        private const val SET_ITEM_TOOLTIP_HASH = 501894301L
        private val setItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)
        }

        private const val GET_ITEM_TOOLTIP_HASH = 844755477L
        private val getItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
        }

        private const val SELECT_HASH = 972357352L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "select", SELECT_HASH)
        }

        private const val DESELECT_HASH = 1286410249L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "deselect", DESELECT_HASH)
        }

        private const val DESELECT_ALL_HASH = 3218959716L
        private val deselectAllBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "deselect_all", DESELECT_ALL_HASH)
        }

        private const val IS_SELECTED_HASH = 1116898809L
        private val isSelectedBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_selected", IS_SELECTED_HASH)
        }

        private const val GET_SELECTED_ITEMS_HASH = 969006518L
        private val getSelectedItemsBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_selected_items", GET_SELECTED_ITEMS_HASH)
        }

        private const val MOVE_ITEM_HASH = 3937882851L
        private val moveItemBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "move_item", MOVE_ITEM_HASH)
        }

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        private val setItemCountBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_item_count", SET_ITEM_COUNT_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_count", GET_ITEM_COUNT_HASH)
        }

        private const val REMOVE_ITEM_HASH = 1286410249L
        private val removeItemBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "remove_item", REMOVE_ITEM_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "clear", CLEAR_HASH)
        }

        private const val SORT_ITEMS_BY_TEXT_HASH = 3218959716L
        private val sortItemsByTextBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "sort_items_by_text", SORT_ITEMS_BY_TEXT_HASH)
        }

        private const val SET_FIXED_COLUMN_WIDTH_HASH = 1286410249L
        private val setFixedColumnWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_fixed_column_width", SET_FIXED_COLUMN_WIDTH_HASH)
        }

        private const val GET_FIXED_COLUMN_WIDTH_HASH = 3905245786L
        private val getFixedColumnWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_fixed_column_width", GET_FIXED_COLUMN_WIDTH_HASH)
        }

        private const val SET_SAME_COLUMN_WIDTH_HASH = 2586408642L
        private val setSameColumnWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_same_column_width", SET_SAME_COLUMN_WIDTH_HASH)
        }

        private const val IS_SAME_COLUMN_WIDTH_HASH = 36873697L
        private val isSameColumnWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_same_column_width", IS_SAME_COLUMN_WIDTH_HASH)
        }

        private const val SET_MAX_TEXT_LINES_HASH = 1286410249L
        private val setMaxTextLinesBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_max_text_lines", SET_MAX_TEXT_LINES_HASH)
        }

        private const val GET_MAX_TEXT_LINES_HASH = 3905245786L
        private val getMaxTextLinesBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_max_text_lines", GET_MAX_TEXT_LINES_HASH)
        }

        private const val SET_MAX_COLUMNS_HASH = 1286410249L
        private val setMaxColumnsBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_max_columns", SET_MAX_COLUMNS_HASH)
        }

        private const val GET_MAX_COLUMNS_HASH = 3905245786L
        private val getMaxColumnsBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_max_columns", GET_MAX_COLUMNS_HASH)
        }

        private const val SET_SELECT_MODE_HASH = 928267388L
        private val setSelectModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_select_mode", SET_SELECT_MODE_HASH)
        }

        private const val GET_SELECT_MODE_HASH = 1191945842L
        private val getSelectModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_select_mode", GET_SELECT_MODE_HASH)
        }

        private const val SET_ICON_MODE_HASH = 2025053633L
        private val setIconModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_icon_mode", SET_ICON_MODE_HASH)
        }

        private const val GET_ICON_MODE_HASH = 3353929232L
        private val getIconModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_icon_mode", GET_ICON_MODE_HASH)
        }

        private const val SET_FIXED_ICON_SIZE_HASH = 1130785943L
        private val setFixedIconSizeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_fixed_icon_size", SET_FIXED_ICON_SIZE_HASH)
        }

        private const val GET_FIXED_ICON_SIZE_HASH = 3690982128L
        private val getFixedIconSizeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_fixed_icon_size", GET_FIXED_ICON_SIZE_HASH)
        }

        private const val SET_ICON_SCALE_HASH = 373806689L
        private val setIconScaleBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_icon_scale", SET_ICON_SCALE_HASH)
        }

        private const val GET_ICON_SCALE_HASH = 1740695150L
        private val getIconScaleBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_icon_scale", GET_ICON_SCALE_HASH)
        }

        private const val SET_ALLOW_RMB_SELECT_HASH = 2586408642L
        private val setAllowRmbSelectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_allow_rmb_select", SET_ALLOW_RMB_SELECT_HASH)
        }

        private const val GET_ALLOW_RMB_SELECT_HASH = 36873697L
        private val getAllowRmbSelectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_allow_rmb_select", GET_ALLOW_RMB_SELECT_HASH)
        }

        private const val SET_ALLOW_RESELECT_HASH = 2586408642L
        private val setAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_allow_reselect", SET_ALLOW_RESELECT_HASH)
        }

        private const val GET_ALLOW_RESELECT_HASH = 36873697L
        private val getAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_allow_reselect", GET_ALLOW_RESELECT_HASH)
        }

        private const val SET_ALLOW_SEARCH_HASH = 2586408642L
        private val setAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_allow_search", SET_ALLOW_SEARCH_HASH)
        }

        private const val GET_ALLOW_SEARCH_HASH = 36873697L
        private val getAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_allow_search", GET_ALLOW_SEARCH_HASH)
        }

        private const val SET_AUTO_WIDTH_HASH = 2586408642L
        private val setAutoWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_auto_width", SET_AUTO_WIDTH_HASH)
        }

        private const val HAS_AUTO_WIDTH_HASH = 36873697L
        private val hasAutoWidthBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "has_auto_width", HAS_AUTO_WIDTH_HASH)
        }

        private const val SET_AUTO_HEIGHT_HASH = 2586408642L
        private val setAutoHeightBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_auto_height", SET_AUTO_HEIGHT_HASH)
        }

        private const val HAS_AUTO_HEIGHT_HASH = 36873697L
        private val hasAutoHeightBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "has_auto_height", HAS_AUTO_HEIGHT_HASH)
        }

        private const val IS_ANYTHING_SELECTED_HASH = 2240911060L
        private val isAnythingSelectedBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_anything_selected", IS_ANYTHING_SELECTED_HASH)
        }

        private const val GET_ITEM_AT_POSITION_HASH = 2300324924L
        private val getItemAtPositionBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_item_at_position", GET_ITEM_AT_POSITION_HASH)
        }

        private const val ENSURE_CURRENT_IS_VISIBLE_HASH = 3218959716L
        private val ensureCurrentIsVisibleBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "ensure_current_is_visible", ENSURE_CURRENT_IS_VISIBLE_HASH)
        }

        private const val CENTER_ON_CURRENT_HASH = 3058350285L
        private val centerOnCurrentBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "center_on_current", CENTER_ON_CURRENT_HASH)
        }

        private const val GET_V_SCROLL_BAR_HASH = 2630340773L
        private val getVScrollBarBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_v_scroll_bar", GET_V_SCROLL_BAR_HASH)
        }

        private const val GET_H_SCROLL_BAR_HASH = 4004517983L
        private val getHScrollBarBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_h_scroll_bar", GET_H_SCROLL_BAR_HASH)
        }

        private const val SET_SCROLL_HINT_MODE_HASH = 2917787337L
        private val setScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_scroll_hint_mode", SET_SCROLL_HINT_MODE_HASH)
        }

        private const val GET_SCROLL_HINT_MODE_HASH = 2522227939L
        private val getScrollHintModeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_scroll_hint_mode", GET_SCROLL_HINT_MODE_HASH)
        }

        private const val SET_TILE_SCROLL_HINT_HASH = 2586408642L
        private val setTileScrollHintBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_tile_scroll_hint", SET_TILE_SCROLL_HINT_HASH)
        }

        private const val IS_SCROLL_HINT_TILED_HASH = 2240911060L
        private val isScrollHintTiledBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "is_scroll_hint_tiled", IS_SCROLL_HINT_TILED_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_WRAPAROUND_ITEMS_HASH = 2586408642L
        private val setWraparoundItemsBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "set_wraparound_items", SET_WRAPAROUND_ITEMS_HASH)
        }

        private const val HAS_WRAPAROUND_ITEMS_HASH = 36873697L
        private val hasWraparoundItemsBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "has_wraparound_items", HAS_WRAPAROUND_ITEMS_HASH)
        }

        private const val FORCE_UPDATE_LIST_SIZE_HASH = 3218959716L
        private val forceUpdateListSizeBind by lazy {
            ObjectCalls.getMethodBind("ItemList", "force_update_list_size", FORCE_UPDATE_LIST_SIZE_HASH)
        }
    }
}
