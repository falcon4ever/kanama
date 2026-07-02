package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A button that brings up a dropdown with selectable options when pressed.
 *
 * Generated from Godot docs: OptionButton
 */
class OptionButton(handle: MemorySegment) : Button(handle) {
    val selected: Int
        @JvmName("selectedProperty")
        get() = getSelected()

    var fitToLongestItem: Boolean
        @JvmName("fitToLongestItemProperty")
        get() = isFitToLongestItem()
        @JvmName("setFitToLongestItemProperty")
        set(value) = setFitToLongestItem(value)

    var allowReselect: Boolean
        @JvmName("allowReselectProperty")
        get() = getAllowReselect()
        @JvmName("setAllowReselectProperty")
        set(value) = setAllowReselect(value)

    var searchBarEnabled: Boolean
        @JvmName("searchBarEnabledProperty")
        get() = isSearchBarEnabled()
        @JvmName("setSearchBarEnabledProperty")
        set(value) = setSearchBarEnabled(value)

    var searchBarMinItemCount: Int
        @JvmName("searchBarMinItemCountProperty")
        get() = getSearchBarMinItemCount()
        @JvmName("setSearchBarMinItemCountProperty")
        set(value) = setSearchBarMinItemCount(value)

    var searchBarFuzzySearchEnabled: Boolean
        @JvmName("searchBarFuzzySearchEnabledProperty")
        get() = isSearchBarFuzzySearchEnabled()
        @JvmName("setSearchBarFuzzySearchEnabledProperty")
        set(value) = setSearchBarFuzzySearchEnabled(value)

    var searchBarFuzzySearchMaxMisses: Int
        @JvmName("searchBarFuzzySearchMaxMissesProperty")
        get() = getSearchBarFuzzySearchMaxMisses()
        @JvmName("setSearchBarFuzzySearchMaxMissesProperty")
        set(value) = setSearchBarFuzzySearchMaxMisses(value)

    var itemCount: Int
        @JvmName("itemCountProperty")
        get() = getItemCount()
        @JvmName("setItemCountProperty")
        set(value) = setItemCount(value)

    fun addItem(label: String, id: Int = -1) {
        ObjectCalls.ptrcallWithStringAndIntArg(addItemBind, handle, label, id)
    }

    fun addIconItem(texture: Texture2D?, label: String, id: Int = -1) {
        ObjectCalls.ptrcallWithObjectStringAndIntArgs(addIconItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id)
    }

    fun setItemText(idx: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTextBind, handle, idx, text)
    }

    fun setItemIcon(idx: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemIconBind, handle, idx, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setItemDisabled(idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemDisabledBind, handle, idx, disabled)
    }

    fun setItemId(idx: Int, id: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIdBind, handle, idx, id)
    }

    fun setItemMetadata(idx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, idx, metadata)
    }

    fun setItemTooltip(idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTooltipBind, handle, idx, tooltip)
    }

    fun setItemAutoTranslateMode(idx: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAutoTranslateModeBind, handle, idx, mode)
    }

    fun setSearchBarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSearchBarEnabledBind, handle, enabled)
    }

    fun setSearchBarMinItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setSearchBarMinItemCountBind, handle, count)
    }

    fun getSearchBarMinItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSearchBarMinItemCountBind, handle)
    }

    fun setSearchBarFuzzySearchEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSearchBarFuzzySearchEnabledBind, handle, enabled)
    }

    fun isSearchBarFuzzySearchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSearchBarFuzzySearchEnabledBind, handle)
    }

    fun setSearchBarFuzzySearchMaxMisses(maxMisses: Int) {
        ObjectCalls.ptrcallWithIntArg(setSearchBarFuzzySearchMaxMissesBind, handle, maxMisses)
    }

    fun getSearchBarFuzzySearchMaxMisses(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSearchBarFuzzySearchMaxMissesBind, handle)
    }

    fun getItemText(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, idx)
    }

    fun getItemIcon(idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemIconBind, handle, idx))
    }

    fun getItemId(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIdBind, handle, idx)
    }

    fun getItemIndex(id: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIndexBind, handle, id)
    }

    fun getItemMetadata(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, idx)
    }

    fun getItemTooltip(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTooltipBind, handle, idx)
    }

    fun getItemAutoTranslateMode(idx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAutoTranslateModeBind, handle, idx)
    }

    fun isItemDisabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemDisabledBind, handle, idx)
    }

    fun isItemSeparator(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemSeparatorBind, handle, idx)
    }

    fun isSearchBarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSearchBarEnabledBind, handle)
    }

    fun addSeparator(text: String = "") {
        ObjectCalls.ptrcallWithStringArg(addSeparatorBind, handle, text)
    }

    /**
     * Clears all the items in the `OptionButton`.
     *
     * Generated from Godot docs: OptionButton.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Selects an item by index and makes it the current item. This will work even if the item is
     * disabled. Passing `-1` as the index deselects any currently selected item.
     *
     * Generated from Godot docs: OptionButton.select
     */
    fun select(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(selectBind, handle, idx)
    }

    fun getSelected(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedBind, handle)
    }

    fun getSelectedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedIdBind, handle)
    }

    fun getSelectedMetadata(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getSelectedMetadataBind, handle)
    }

    fun removeItem(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, idx)
    }

    fun getPopup(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPopupBind, handle))
    }

    fun showPopup() {
        ObjectCalls.ptrcallNoArgs(showPopupBind, handle)
    }

    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    fun hasSelectableItems(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSelectableItemsBind, handle)
    }

    fun getSelectableItem(fromLast: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(getSelectableItemBind, handle, fromLast)
    }

    fun setFitToLongestItem(fit: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFitToLongestItemBind, handle, fit)
    }

    fun isFitToLongestItem(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFitToLongestItemBind, handle)
    }

    fun setAllowReselect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowReselectBind, handle, allow)
    }

    fun getAllowReselect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowReselectBind, handle)
    }

    fun setDisableShortcuts(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableShortcutsBind, handle, disabled)
    }

    object Signals {
        const val itemSelected: String = "item_selected"
        const val itemFocused: String = "item_focused"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OptionButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OptionButton? =
            if (handle.address() == 0L) null else OptionButton(handle)

        private const val ADD_ITEM_HASH = 2697778442L
        private val addItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "add_item", ADD_ITEM_HASH)
        }

        private const val ADD_ICON_ITEM_HASH = 3781678508L
        private val addIconItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "add_icon_item", ADD_ICON_ITEM_HASH)
        }

        private const val SET_ITEM_TEXT_HASH = 501894301L
        private val setItemTextBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_text", SET_ITEM_TEXT_HASH)
        }

        private const val SET_ITEM_ICON_HASH = 666127730L
        private val setItemIconBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_icon", SET_ITEM_ICON_HASH)
        }

        private const val SET_ITEM_DISABLED_HASH = 300928843L
        private val setItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_disabled", SET_ITEM_DISABLED_HASH)
        }

        private const val SET_ITEM_ID_HASH = 3937882851L
        private val setItemIdBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_id", SET_ITEM_ID_HASH)
        }

        private const val SET_ITEM_METADATA_HASH = 2152698145L
        private val setItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_metadata", SET_ITEM_METADATA_HASH)
        }

        private const val SET_ITEM_TOOLTIP_HASH = 501894301L
        private val setItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)
        }

        private const val SET_ITEM_AUTO_TRANSLATE_MODE_HASH = 287402019L
        private val setItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_auto_translate_mode", SET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_SEARCH_BAR_ENABLED_HASH = 2586408642L
        private val setSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_enabled", SET_SEARCH_BAR_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 1286410249L
        private val setSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_min_item_count", SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 3905245786L
        private val getSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_search_bar_min_item_count", GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 2586408642L
        private val setSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_fuzzy_search_enabled", SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 36873697L
        private val isSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "is_search_bar_fuzzy_search_enabled", IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 1286410249L
        private val setSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_fuzzy_search_max_misses", SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 3905245786L
        private val getSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_search_bar_fuzzy_search_max_misses", GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val GET_ITEM_TEXT_HASH = 844755477L
        private val getItemTextBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_text", GET_ITEM_TEXT_HASH)
        }

        private const val GET_ITEM_ICON_HASH = 3536238170L
        private val getItemIconBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_icon", GET_ITEM_ICON_HASH)
        }

        private const val GET_ITEM_ID_HASH = 923996154L
        private val getItemIdBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_id", GET_ITEM_ID_HASH)
        }

        private const val GET_ITEM_INDEX_HASH = 923996154L
        private val getItemIndexBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_index", GET_ITEM_INDEX_HASH)
        }

        private const val GET_ITEM_METADATA_HASH = 4227898402L
        private val getItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_metadata", GET_ITEM_METADATA_HASH)
        }

        private const val GET_ITEM_TOOLTIP_HASH = 844755477L
        private val getItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
        }

        private const val GET_ITEM_AUTO_TRANSLATE_MODE_HASH = 906302372L
        private val getItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_auto_translate_mode", GET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val IS_ITEM_DISABLED_HASH = 1116898809L
        private val isItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "is_item_disabled", IS_ITEM_DISABLED_HASH)
        }

        private const val IS_ITEM_SEPARATOR_HASH = 1116898809L
        private val isItemSeparatorBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "is_item_separator", IS_ITEM_SEPARATOR_HASH)
        }

        private const val IS_SEARCH_BAR_ENABLED_HASH = 36873697L
        private val isSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "is_search_bar_enabled", IS_SEARCH_BAR_ENABLED_HASH)
        }

        private const val ADD_SEPARATOR_HASH = 3005725572L
        private val addSeparatorBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "add_separator", ADD_SEPARATOR_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "clear", CLEAR_HASH)
        }

        private const val SELECT_HASH = 1286410249L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "select", SELECT_HASH)
        }

        private const val GET_SELECTED_HASH = 3905245786L
        private val getSelectedBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_selected", GET_SELECTED_HASH)
        }

        private const val GET_SELECTED_ID_HASH = 3905245786L
        private val getSelectedIdBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_selected_id", GET_SELECTED_ID_HASH)
        }

        private const val GET_SELECTED_METADATA_HASH = 1214101251L
        private val getSelectedMetadataBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_selected_metadata", GET_SELECTED_METADATA_HASH)
        }

        private const val REMOVE_ITEM_HASH = 1286410249L
        private val removeItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "remove_item", REMOVE_ITEM_HASH)
        }

        private const val GET_POPUP_HASH = 229722558L
        private val getPopupBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_popup", GET_POPUP_HASH)
        }

        private const val SHOW_POPUP_HASH = 3218959716L
        private val showPopupBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "show_popup", SHOW_POPUP_HASH)
        }

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        private val setItemCountBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_item_count", SET_ITEM_COUNT_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_item_count", GET_ITEM_COUNT_HASH)
        }

        private const val HAS_SELECTABLE_ITEMS_HASH = 36873697L
        private val hasSelectableItemsBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "has_selectable_items", HAS_SELECTABLE_ITEMS_HASH)
        }

        private const val GET_SELECTABLE_ITEM_HASH = 894402480L
        private val getSelectableItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_selectable_item", GET_SELECTABLE_ITEM_HASH)
        }

        private const val SET_FIT_TO_LONGEST_ITEM_HASH = 2586408642L
        private val setFitToLongestItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_fit_to_longest_item", SET_FIT_TO_LONGEST_ITEM_HASH)
        }

        private const val IS_FIT_TO_LONGEST_ITEM_HASH = 36873697L
        private val isFitToLongestItemBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "is_fit_to_longest_item", IS_FIT_TO_LONGEST_ITEM_HASH)
        }

        private const val SET_ALLOW_RESELECT_HASH = 2586408642L
        private val setAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_allow_reselect", SET_ALLOW_RESELECT_HASH)
        }

        private const val GET_ALLOW_RESELECT_HASH = 36873697L
        private val getAllowReselectBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "get_allow_reselect", GET_ALLOW_RESELECT_HASH)
        }

        private const val SET_DISABLE_SHORTCUTS_HASH = 2586408642L
        private val setDisableShortcutsBind by lazy {
            ObjectCalls.getMethodBind("OptionButton", "set_disable_shortcuts", SET_DISABLE_SHORTCUTS_HASH)
        }
    }
}
