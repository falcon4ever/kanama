package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OptionButton
 */
class OptionButton(handle: GodotHandle) : Button(handle) {
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
        ObjectCalls.ptrcallWithStringAndIntArg(Binds.addItemBind, segment, label, id)
    }

    fun addIconItem(texture: Texture2D?, label: String, id: Int = -1) {
        ObjectCalls.ptrcallWithObjectStringAndIntArgs(Binds.addIconItemBind, segment, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id)
    }

    fun setItemText(idx: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setItemTextBind, segment, idx, text)
    }

    fun setItemIcon(idx: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setItemIconBind, segment, idx, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setItemDisabled(idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setItemDisabledBind, segment, idx, disabled)
    }

    fun setItemId(idx: Int, id: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setItemIdBind, segment, idx, id)
    }

    fun setItemMetadata(idx: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setItemMetadataBind, segment, idx, metadata)
    }

    fun setItemTooltip(idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setItemTooltipBind, segment, idx, tooltip)
    }

    fun setItemAutoTranslateMode(idx: Int, mode: Node.AutoTranslateMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setItemAutoTranslateModeBind, segment, idx, mode.value)
    }

    fun setSearchBarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSearchBarEnabledBind, segment, enabled)
    }

    fun setSearchBarMinItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSearchBarMinItemCountBind, segment, count)
    }

    fun getSearchBarMinItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSearchBarMinItemCountBind, segment)
    }

    fun setSearchBarFuzzySearchEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSearchBarFuzzySearchEnabledBind, segment, enabled)
    }

    fun isSearchBarFuzzySearchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSearchBarFuzzySearchEnabledBind, segment)
    }

    fun setSearchBarFuzzySearchMaxMisses(maxMisses: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSearchBarFuzzySearchMaxMissesBind, segment, maxMisses)
    }

    fun getSearchBarFuzzySearchMaxMisses(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSearchBarFuzzySearchMaxMissesBind, segment)
    }

    fun getItemText(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getItemTextBind, segment, idx)
    }

    fun getItemIcon(idx: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getItemIconBind, segment, idx))
    }

    fun getItemId(idx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getItemIdBind, segment, idx)
    }

    fun getItemIndex(id: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getItemIndexBind, segment, id)
    }

    fun getItemMetadata(idx: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getItemMetadataBind, segment, idx)
    }

    fun getItemTooltip(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getItemTooltipBind, segment, idx)
    }

    fun getItemAutoTranslateMode(idx: Int): Node.AutoTranslateMode {
        return Node.AutoTranslateMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getItemAutoTranslateModeBind, segment, idx))
    }

    fun isItemDisabled(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isItemDisabledBind, segment, idx)
    }

    fun isItemSeparator(idx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isItemSeparatorBind, segment, idx)
    }

    fun isSearchBarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSearchBarEnabledBind, segment)
    }

    fun addSeparator(text: String = "") {
        ObjectCalls.ptrcallWithStringArg(Binds.addSeparatorBind, segment, text)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    fun select(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.selectBind, segment, idx)
    }

    fun getSelected(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectedBind, segment)
    }

    fun getSelectedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectedIdBind, segment)
    }

    fun getSelectedMetadata(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getSelectedMetadataBind, segment)
    }

    fun removeItem(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.removeItemBind, segment, idx)
    }

    fun getPopup(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPopupBind, segment))
    }

    fun showPopup() {
        ObjectCalls.ptrcallNoArgs(Binds.showPopupBind, segment)
    }

    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setItemCountBind, segment, count)
    }

    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getItemCountBind, segment)
    }

    fun hasSelectableItems(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSelectableItemsBind, segment)
    }

    fun getSelectableItem(fromLast: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(Binds.getSelectableItemBind, segment, fromLast)
    }

    fun setFitToLongestItem(fit: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFitToLongestItemBind, segment, fit)
    }

    fun isFitToLongestItem(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFitToLongestItemBind, segment)
    }

    fun setAllowReselect(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowReselectBind, segment, allow)
    }

    fun getAllowReselect(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAllowReselectBind, segment)
    }

    fun setDisableShortcuts(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisableShortcutsBind, segment, disabled)
    }

    /** Signal `item_selected(index: int)`; see [TypedSignal]. */
    val itemSelected: Signal1<Long>
        @JvmName("itemSelectedTypedSignal")
        get() = Signal1(this, "item_selected", SignalArgType.LONG)

    /** Signal `item_focused(index: int)`; see [TypedSignal]. */
    val itemFocused: Signal1<Long>
        @JvmName("itemFocusedTypedSignal")
        get() = Signal1(this, "item_focused", SignalArgType.LONG)

    object Signals {
        const val itemSelected: String = "item_selected"
        const val itemFocused: String = "item_focused"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OptionButton? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OptionButton? =
            if (handle.address() == 0L) null else OptionButton(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_ITEM_HASH = 2697778442L
        @JvmField
        val addItemBind =
            ObjectCalls.getMethodBind("OptionButton", "add_item", ADD_ITEM_HASH)

        private const val ADD_ICON_ITEM_HASH = 3781678508L
        @JvmField
        val addIconItemBind =
            ObjectCalls.getMethodBind("OptionButton", "add_icon_item", ADD_ICON_ITEM_HASH)

        private const val SET_ITEM_TEXT_HASH = 501894301L
        @JvmField
        val setItemTextBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_text", SET_ITEM_TEXT_HASH)

        private const val SET_ITEM_ICON_HASH = 666127730L
        @JvmField
        val setItemIconBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_icon", SET_ITEM_ICON_HASH)

        private const val SET_ITEM_DISABLED_HASH = 300928843L
        @JvmField
        val setItemDisabledBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_disabled", SET_ITEM_DISABLED_HASH)

        private const val SET_ITEM_ID_HASH = 3937882851L
        @JvmField
        val setItemIdBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_id", SET_ITEM_ID_HASH)

        private const val SET_ITEM_METADATA_HASH = 2152698145L
        @JvmField
        val setItemMetadataBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_metadata", SET_ITEM_METADATA_HASH)

        private const val SET_ITEM_TOOLTIP_HASH = 501894301L
        @JvmField
        val setItemTooltipBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)

        private const val SET_ITEM_AUTO_TRANSLATE_MODE_HASH = 287402019L
        @JvmField
        val setItemAutoTranslateModeBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_auto_translate_mode", SET_ITEM_AUTO_TRANSLATE_MODE_HASH)

        private const val SET_SEARCH_BAR_ENABLED_HASH = 2586408642L
        @JvmField
        val setSearchBarEnabledBind =
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_enabled", SET_SEARCH_BAR_ENABLED_HASH)

        private const val SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 1286410249L
        @JvmField
        val setSearchBarMinItemCountBind =
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_min_item_count", SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)

        private const val GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 3905245786L
        @JvmField
        val getSearchBarMinItemCountBind =
            ObjectCalls.getMethodBind("OptionButton", "get_search_bar_min_item_count", GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 2586408642L
        @JvmField
        val setSearchBarFuzzySearchEnabledBind =
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_fuzzy_search_enabled", SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)

        private const val IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 36873697L
        @JvmField
        val isSearchBarFuzzySearchEnabledBind =
            ObjectCalls.getMethodBind("OptionButton", "is_search_bar_fuzzy_search_enabled", IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 1286410249L
        @JvmField
        val setSearchBarFuzzySearchMaxMissesBind =
            ObjectCalls.getMethodBind("OptionButton", "set_search_bar_fuzzy_search_max_misses", SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)

        private const val GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 3905245786L
        @JvmField
        val getSearchBarFuzzySearchMaxMissesBind =
            ObjectCalls.getMethodBind("OptionButton", "get_search_bar_fuzzy_search_max_misses", GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)

        private const val GET_ITEM_TEXT_HASH = 844755477L
        @JvmField
        val getItemTextBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_text", GET_ITEM_TEXT_HASH)

        private const val GET_ITEM_ICON_HASH = 3536238170L
        @JvmField
        val getItemIconBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_icon", GET_ITEM_ICON_HASH)

        private const val GET_ITEM_ID_HASH = 923996154L
        @JvmField
        val getItemIdBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_id", GET_ITEM_ID_HASH)

        private const val GET_ITEM_INDEX_HASH = 923996154L
        @JvmField
        val getItemIndexBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_index", GET_ITEM_INDEX_HASH)

        private const val GET_ITEM_METADATA_HASH = 4227898402L
        @JvmField
        val getItemMetadataBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_metadata", GET_ITEM_METADATA_HASH)

        private const val GET_ITEM_TOOLTIP_HASH = 844755477L
        @JvmField
        val getItemTooltipBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)

        private const val GET_ITEM_AUTO_TRANSLATE_MODE_HASH = 906302372L
        @JvmField
        val getItemAutoTranslateModeBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_auto_translate_mode", GET_ITEM_AUTO_TRANSLATE_MODE_HASH)

        private const val IS_ITEM_DISABLED_HASH = 1116898809L
        @JvmField
        val isItemDisabledBind =
            ObjectCalls.getMethodBind("OptionButton", "is_item_disabled", IS_ITEM_DISABLED_HASH)

        private const val IS_ITEM_SEPARATOR_HASH = 1116898809L
        @JvmField
        val isItemSeparatorBind =
            ObjectCalls.getMethodBind("OptionButton", "is_item_separator", IS_ITEM_SEPARATOR_HASH)

        private const val IS_SEARCH_BAR_ENABLED_HASH = 36873697L
        @JvmField
        val isSearchBarEnabledBind =
            ObjectCalls.getMethodBind("OptionButton", "is_search_bar_enabled", IS_SEARCH_BAR_ENABLED_HASH)

        private const val ADD_SEPARATOR_HASH = 3005725572L
        @JvmField
        val addSeparatorBind =
            ObjectCalls.getMethodBind("OptionButton", "add_separator", ADD_SEPARATOR_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("OptionButton", "clear", CLEAR_HASH)

        private const val SELECT_HASH = 1286410249L
        @JvmField
        val selectBind =
            ObjectCalls.getMethodBind("OptionButton", "select", SELECT_HASH)

        private const val GET_SELECTED_HASH = 3905245786L
        @JvmField
        val getSelectedBind =
            ObjectCalls.getMethodBind("OptionButton", "get_selected", GET_SELECTED_HASH)

        private const val GET_SELECTED_ID_HASH = 3905245786L
        @JvmField
        val getSelectedIdBind =
            ObjectCalls.getMethodBind("OptionButton", "get_selected_id", GET_SELECTED_ID_HASH)

        private const val GET_SELECTED_METADATA_HASH = 1214101251L
        @JvmField
        val getSelectedMetadataBind =
            ObjectCalls.getMethodBind("OptionButton", "get_selected_metadata", GET_SELECTED_METADATA_HASH)

        private const val REMOVE_ITEM_HASH = 1286410249L
        @JvmField
        val removeItemBind =
            ObjectCalls.getMethodBind("OptionButton", "remove_item", REMOVE_ITEM_HASH)

        private const val GET_POPUP_HASH = 229722558L
        @JvmField
        val getPopupBind =
            ObjectCalls.getMethodBind("OptionButton", "get_popup", GET_POPUP_HASH)

        private const val SHOW_POPUP_HASH = 3218959716L
        @JvmField
        val showPopupBind =
            ObjectCalls.getMethodBind("OptionButton", "show_popup", SHOW_POPUP_HASH)

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        @JvmField
        val setItemCountBind =
            ObjectCalls.getMethodBind("OptionButton", "set_item_count", SET_ITEM_COUNT_HASH)

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        @JvmField
        val getItemCountBind =
            ObjectCalls.getMethodBind("OptionButton", "get_item_count", GET_ITEM_COUNT_HASH)

        private const val HAS_SELECTABLE_ITEMS_HASH = 36873697L
        @JvmField
        val hasSelectableItemsBind =
            ObjectCalls.getMethodBind("OptionButton", "has_selectable_items", HAS_SELECTABLE_ITEMS_HASH)

        private const val GET_SELECTABLE_ITEM_HASH = 894402480L
        @JvmField
        val getSelectableItemBind =
            ObjectCalls.getMethodBind("OptionButton", "get_selectable_item", GET_SELECTABLE_ITEM_HASH)

        private const val SET_FIT_TO_LONGEST_ITEM_HASH = 2586408642L
        @JvmField
        val setFitToLongestItemBind =
            ObjectCalls.getMethodBind("OptionButton", "set_fit_to_longest_item", SET_FIT_TO_LONGEST_ITEM_HASH)

        private const val IS_FIT_TO_LONGEST_ITEM_HASH = 36873697L
        @JvmField
        val isFitToLongestItemBind =
            ObjectCalls.getMethodBind("OptionButton", "is_fit_to_longest_item", IS_FIT_TO_LONGEST_ITEM_HASH)

        private const val SET_ALLOW_RESELECT_HASH = 2586408642L
        @JvmField
        val setAllowReselectBind =
            ObjectCalls.getMethodBind("OptionButton", "set_allow_reselect", SET_ALLOW_RESELECT_HASH)

        private const val GET_ALLOW_RESELECT_HASH = 36873697L
        @JvmField
        val getAllowReselectBind =
            ObjectCalls.getMethodBind("OptionButton", "get_allow_reselect", GET_ALLOW_RESELECT_HASH)

        private const val SET_DISABLE_SHORTCUTS_HASH = 2586408642L
        @JvmField
        val setDisableShortcutsBind =
            ObjectCalls.getMethodBind("OptionButton", "set_disable_shortcuts", SET_DISABLE_SHORTCUTS_HASH)
    }
}
