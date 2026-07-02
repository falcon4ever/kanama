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

    fun addItem(text: String, icon: Texture2D?, selectable: Boolean = true): Int {
        return ObjectCalls.ptrcallWithStringObjectBoolArgsRetInt(addItemBind, handle, text, icon?.requireOpenHandle() ?: MemorySegment.NULL, selectable)
    }

    fun addIconItem(icon: Texture2D?, selectable: Boolean = true): Int {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetInt(addIconItemBind, handle, icon?.requireOpenHandle() ?: MemorySegment.NULL, selectable)
    }

    fun setItemText(idx: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTextBind, handle, idx, text)
    }

    fun getItemText(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, idx)
    }

    fun setItemIcon(idx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemIconBind, handle, idx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getItemIcon(idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemIconBind, handle, idx))
    }

    fun setItemTextDirection(idx: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemTextDirectionBind, handle, idx, direction)
    }

    fun getItemTextDirection(idx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemTextDirectionBind, handle, idx)
    }

    fun setItemLanguage(idx: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemLanguageBind, handle, idx, language)
    }

    fun getItemLanguage(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemLanguageBind, handle, idx)
    }

    fun setItemAutoTranslateMode(idx: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAutoTranslateModeBind, handle, idx, mode)
    }

    fun getItemAutoTranslateMode(idx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAutoTranslateModeBind, handle, idx)
    }

    fun setItemIconTransposed(idx: Int, transposed: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemIconTransposedBind, handle, idx, transposed)
    }

    fun isItemIconTransposed(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemIconTransposedBind, handle, idx)
    }

    fun setItemIconRegion(idx: Int, rect: Rect2) {
        ObjectCalls.ptrcallWithIntAndRect2Arg(setItemIconRegionBind, handle, idx, rect)
    }

    fun getItemIconRegion(idx: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(getItemIconRegionBind, handle, idx)
    }

    fun setItemIconModulate(idx: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemIconModulateBind, handle, idx, modulate)
    }

    fun getItemIconModulate(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemIconModulateBind, handle, idx)
    }

    fun setItemSelectable(idx: Int, selectable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemSelectableBind, handle, idx, selectable)
    }

    fun isItemSelectable(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemSelectableBind, handle, idx)
    }

    fun setItemDisabled(idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemDisabledBind, handle, idx, disabled)
    }

    fun isItemDisabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemDisabledBind, handle, idx)
    }

    fun setItemMetadata(idx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, idx, metadata)
    }

    fun getItemMetadata(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, idx)
    }

    fun setItemCustomBgColor(idx: Int, customBgColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemCustomBgColorBind, handle, idx, customBgColor)
    }

    fun getItemCustomBgColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemCustomBgColorBind, handle, idx)
    }

    fun setItemCustomFgColor(idx: Int, customFgColor: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemCustomFgColorBind, handle, idx, customFgColor)
    }

    fun getItemCustomFgColor(idx: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemCustomFgColorBind, handle, idx)
    }

    fun getItemRect(idx: Int, expand: Boolean = true): Rect2 {
        return ObjectCalls.ptrcallWithIntAndBoolArgRetRect2(getItemRectBind, handle, idx, expand)
    }

    fun setItemTooltipEnabled(idx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemTooltipEnabledBind, handle, idx, enable)
    }

    fun isItemTooltipEnabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemTooltipEnabledBind, handle, idx)
    }

    fun setItemTooltip(idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTooltipBind, handle, idx, tooltip)
    }

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

    fun deselectAll() {
        ObjectCalls.ptrcallNoArgs(deselectAllBind, handle)
    }

    fun isSelected(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSelectedBind, handle, idx)
    }

    fun getSelectedItems(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getSelectedItemsBind, handle)
    }

    fun moveItem(fromIdx: Int, toIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveItemBind, handle, fromIdx, toIdx)
    }

    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

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

    fun sortItemsByText() {
        ObjectCalls.ptrcallNoArgs(sortItemsByTextBind, handle)
    }

    fun setFixedColumnWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedColumnWidthBind, handle, width)
    }

    fun getFixedColumnWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedColumnWidthBind, handle)
    }

    fun setSameColumnWidth(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSameColumnWidthBind, handle, enable)
    }

    fun isSameColumnWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSameColumnWidthBind, handle)
    }

    fun setMaxTextLines(lines: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxTextLinesBind, handle, lines)
    }

    fun getMaxTextLines(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxTextLinesBind, handle)
    }

    fun setMaxColumns(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxColumnsBind, handle, amount)
    }

    fun getMaxColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxColumnsBind, handle)
    }

    fun setSelectMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSelectModeBind, handle, mode)
    }

    fun getSelectMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSelectModeBind, handle)
    }

    fun setIconMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconModeBind, handle, mode)
    }

    fun getIconMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIconModeBind, handle)
    }

    fun setFixedIconSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setFixedIconSizeBind, handle, size)
    }

    fun getFixedIconSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getFixedIconSizeBind, handle)
    }

    fun setIconScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setIconScaleBind, handle, scale)
    }

    fun getIconScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getIconScaleBind, handle)
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

    fun setAutoWidth(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoWidthBind, handle, enable)
    }

    fun hasAutoWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutoWidthBind, handle)
    }

    fun setAutoHeight(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoHeightBind, handle, enable)
    }

    fun hasAutoHeight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutoHeightBind, handle)
    }

    fun isAnythingSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAnythingSelectedBind, handle)
    }

    fun getItemAtPosition(position: Vector2, exact: Boolean = false): Int {
        return ObjectCalls.ptrcallWithVector2AndBoolArgRetInt(getItemAtPositionBind, handle, position, exact)
    }

    fun ensureCurrentIsVisible() {
        ObjectCalls.ptrcallNoArgs(ensureCurrentIsVisibleBind, handle)
    }

    fun centerOnCurrent(centerVerically: Boolean = true, centerHorizontally: Boolean = true) {
        ObjectCalls.ptrcallWithTwoBoolArgs(centerOnCurrentBind, handle, centerVerically, centerHorizontally)
    }

    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    fun getHScrollBar(): HScrollBar? {
        return HScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHScrollBarBind, handle))
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

    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    fun setWraparoundItems(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWraparoundItemsBind, handle, enable)
    }

    fun hasWraparoundItems(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasWraparoundItemsBind, handle)
    }

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
