package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A modal window used to display a list of options.
 *
 * Generated from Godot docs: PopupMenu
 */
class PopupMenu(handle: MemorySegment) : Popup(handle) {
    var hideOnItemSelection: Boolean
        @JvmName("hideOnItemSelectionProperty")
        get() = isHideOnItemSelection()
        @JvmName("setHideOnItemSelectionProperty")
        set(value) = setHideOnItemSelection(value)

    var hideOnCheckableItemSelection: Boolean
        @JvmName("hideOnCheckableItemSelectionProperty")
        get() = isHideOnCheckableItemSelection()
        @JvmName("setHideOnCheckableItemSelectionProperty")
        set(value) = setHideOnCheckableItemSelection(value)

    var hideOnStateItemSelection: Boolean
        @JvmName("hideOnStateItemSelectionProperty")
        get() = isHideOnStateItemSelection()
        @JvmName("setHideOnStateItemSelectionProperty")
        set(value) = setHideOnStateItemSelection(value)

    var submenuPopupDelay: Double
        @JvmName("submenuPopupDelayProperty")
        get() = getSubmenuPopupDelay()
        @JvmName("setSubmenuPopupDelayProperty")
        set(value) = setSubmenuPopupDelay(value)

    var allowSearch: Boolean
        @JvmName("allowSearchProperty")
        get() = getAllowSearch()
        @JvmName("setAllowSearchProperty")
        set(value) = setAllowSearch(value)

    var systemMenuId: Long
        @JvmName("systemMenuIdProperty")
        get() = getSystemMenu()
        @JvmName("setSystemMenuIdProperty")
        set(value) = setSystemMenu(value)

    var preferNativeMenu: Boolean
        @JvmName("preferNativeMenuProperty")
        get() = isPreferNativeMenu()
        @JvmName("setPreferNativeMenuProperty")
        set(value) = setPreferNativeMenu(value)

    var shrinkHeight: Boolean
        @JvmName("shrinkHeightProperty")
        get() = getShrinkHeight()
        @JvmName("setShrinkHeightProperty")
        set(value) = setShrinkHeight(value)

    var shrinkWidth: Boolean
        @JvmName("shrinkWidthProperty")
        get() = getShrinkWidth()
        @JvmName("setShrinkWidthProperty")
        set(value) = setShrinkWidth(value)

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

    fun activateItemByEvent(event: InputEvent?, forGlobalOnly: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetBool(activateItemByEventBind, handle, event?.requireOpenHandle() ?: MemorySegment.NULL, forGlobalOnly)
    }

    fun setPreferNativeMenu(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreferNativeMenuBind, handle, enabled)
    }

    fun isPreferNativeMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPreferNativeMenuBind, handle)
    }

    fun isNativeMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNativeMenuBind, handle)
    }

    fun addItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addItemBind, handle, label, id, accel)
    }

    fun addIconItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    fun addCheckItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addCheckItemBind, handle, label, id, accel)
    }

    fun addIconCheckItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconCheckItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    fun addRadioCheckItem(label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(addRadioCheckItemBind, handle, label, id, accel)
    }

    fun addIconRadioCheckItem(texture: Texture2D?, label: String, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithObjectStringIntLongArgs(addIconRadioCheckItemBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, label, id, accel)
    }

    fun addMultistateItem(label: String, maxStates: Int, defaultState: Int = 0, id: Int = -1, accel: Long = 0L) {
        ObjectCalls.ptrcallWithStringThreeIntLongArgs(addMultistateItemBind, handle, label, maxStates, defaultState, id, accel)
    }

    fun addShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false, allowEcho: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntTwoBoolArgs(addShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global, allowEcho)
    }

    fun addIconShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false, allowEcho: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntTwoBoolArgs(addIconShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global, allowEcho)
    }

    fun addCheckShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntBoolArgs(addCheckShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    fun addIconCheckShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntBoolArgs(addIconCheckShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    fun addRadioCheckShortcut(shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectIntBoolArgs(addRadioCheckShortcutBind, handle, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    fun addIconRadioCheckShortcut(texture: Texture2D?, shortcut: Shortcut?, id: Int = -1, global: Boolean = false) {
        ObjectCalls.ptrcallWithObjectObjectIntBoolArgs(addIconRadioCheckShortcutBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, id, global)
    }

    fun addSubmenuItem(label: String, submenu: String, id: Int = -1) {
        ObjectCalls.ptrcallWithTwoStringAndIntArgs(addSubmenuItemBind, handle, label, submenu, id)
    }

    fun addSubmenuNodeItem(label: String, submenu: PopupMenu, id: Int = -1) {
        ObjectCalls.ptrcallWithStringObjectIntArgs(addSubmenuNodeItemBind, handle, label, submenu.handle, id)
    }

    fun setItemText(index: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTextBind, handle, index, text)
    }

    fun setItemTextDirection(index: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemTextDirectionBind, handle, index, direction)
    }

    fun setItemLanguage(index: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemLanguageBind, handle, index, language)
    }

    fun setItemAutoTranslateMode(index: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAutoTranslateModeBind, handle, index, mode)
    }

    fun setItemIcon(index: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemIconBind, handle, index, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setItemIconMaxWidth(index: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIconMaxWidthBind, handle, index, width)
    }

    fun setItemIconModulate(index: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setItemIconModulateBind, handle, index, modulate)
    }

    fun setItemChecked(index: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemCheckedBind, handle, index, checked)
    }

    fun setItemId(index: Int, id: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIdBind, handle, index, id)
    }

    fun setItemAccelerator(index: Int, accel: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemAcceleratorBind, handle, index, accel)
    }

    fun setItemMetadata(index: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, index, metadata)
    }

    fun setItemDisabled(index: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemDisabledBind, handle, index, disabled)
    }

    fun setItemSubmenu(index: Int, submenu: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemSubmenuBind, handle, index, submenu)
    }

    fun setItemSubmenuNode(index: Int, submenu: PopupMenu) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemSubmenuNodeBind, handle, index, submenu.handle)
    }

    fun setItemAsSeparator(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsSeparatorBind, handle, index, enable)
    }

    fun setItemAsCheckable(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsCheckableBind, handle, index, enable)
    }

    fun setItemAsRadioCheckable(index: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemAsRadioCheckableBind, handle, index, enable)
    }

    fun setItemTooltip(index: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemTooltipBind, handle, index, tooltip)
    }

    fun setItemShortcut(index: Int, shortcut: Shortcut?, global: Boolean = false) {
        ObjectCalls.ptrcallWithIntObjectBoolArgs(setItemShortcutBind, handle, index, shortcut?.requireOpenHandle() ?: MemorySegment.NULL, global)
    }

    fun setItemIndent(index: Int, indent: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIndentBind, handle, index, indent)
    }

    fun setItemMultistate(index: Int, state: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemMultistateBind, handle, index, state)
    }

    fun setItemMultistateMax(index: Int, maxStates: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemMultistateMaxBind, handle, index, maxStates)
    }

    fun setItemShortcutDisabled(index: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setItemShortcutDisabledBind, handle, index, disabled)
    }

    fun setItemIndex(index: Int, targetIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setItemIndexBind, handle, index, targetIndex)
    }

    fun toggleItemChecked(index: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleItemCheckedBind, handle, index)
    }

    fun toggleItemMultistate(index: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleItemMultistateBind, handle, index)
    }

    fun getItemText(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, index)
    }

    fun getItemTextDirection(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemTextDirectionBind, handle, index)
    }

    fun getItemLanguage(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemLanguageBind, handle, index)
    }

    fun getItemAutoTranslateMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAutoTranslateModeBind, handle, index)
    }

    fun getItemIcon(index: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemIconBind, handle, index))
    }

    fun getItemIconMaxWidth(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIconMaxWidthBind, handle, index)
    }

    fun getItemIconModulate(index: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getItemIconModulateBind, handle, index)
    }

    fun isItemChecked(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemCheckedBind, handle, index)
    }

    fun getItemId(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIdBind, handle, index)
    }

    fun getItemIndex(id: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIndexBind, handle, id)
    }

    fun getItemAccelerator(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemAcceleratorBind, handle, index)
    }

    fun getItemMetadata(index: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, index)
    }

    fun isItemDisabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemDisabledBind, handle, index)
    }

    fun getItemSubmenu(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemSubmenuBind, handle, index)
    }

    fun getItemSubmenuNode(index: Int): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemSubmenuNodeBind, handle, index))
    }

    fun isItemSeparator(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemSeparatorBind, handle, index)
    }

    fun isItemCheckable(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemCheckableBind, handle, index)
    }

    fun isItemRadioCheckable(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemRadioCheckableBind, handle, index)
    }

    fun isItemShortcutDisabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isItemShortcutDisabledBind, handle, index)
    }

    fun getItemTooltip(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemTooltipBind, handle, index)
    }

    fun getItemShortcut(index: Int): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemShortcutBind, handle, index))
    }

    fun getItemIndent(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemIndentBind, handle, index)
    }

    fun getItemMultistateMax(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemMultistateMaxBind, handle, index)
    }

    fun getItemMultistate(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getItemMultistateBind, handle, index)
    }

    fun setFocusedItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setFocusedItemBind, handle, index)
    }

    fun getFocusedItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFocusedItemBind, handle)
    }

    fun setItemCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCountBind, handle, count)
    }

    fun getItemCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    fun scrollToItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToItemBind, handle, index)
    }

    fun removeItem(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, index)
    }

    fun addSeparator(label: String = "", id: Int = -1) {
        ObjectCalls.ptrcallWithStringAndIntArg(addSeparatorBind, handle, label, id)
    }

    /**
     * Removes all items from the `PopupMenu`. If `free_submenus` is `true`, the submenu nodes are
     * automatically freed.
     *
     * Generated from Godot docs: PopupMenu.clear
     */
    fun clear(freeSubmenus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(clearBind, handle, freeSubmenus)
    }

    fun setHideOnItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnItemSelectionBind, handle, enable)
    }

    fun isHideOnItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnItemSelectionBind, handle)
    }

    fun setHideOnCheckableItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnCheckableItemSelectionBind, handle, enable)
    }

    fun isHideOnCheckableItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnCheckableItemSelectionBind, handle)
    }

    fun setHideOnStateItemSelection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHideOnStateItemSelectionBind, handle, enable)
    }

    fun isHideOnStateItemSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHideOnStateItemSelectionBind, handle)
    }

    fun setSubmenuPopupDelay(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSubmenuPopupDelayBind, handle, seconds)
    }

    fun getSubmenuPopupDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubmenuPopupDelayBind, handle)
    }

    fun setAllowSearch(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowSearchBind, handle, allow)
    }

    fun getAllowSearch(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAllowSearchBind, handle)
    }

    fun isSystemMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSystemMenuBind, handle)
    }

    fun setSystemMenu(systemMenuId: Long) {
        ObjectCalls.ptrcallWithLongArg(setSystemMenuBind, handle, systemMenuId)
    }

    fun getSystemMenu(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSystemMenuBind, handle)
    }

    fun setSearchBarEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSearchBarEnabledBind, handle, enabled)
    }

    fun isSearchBarEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSearchBarEnabledBind, handle)
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

    fun setShrinkHeight(shrink: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShrinkHeightBind, handle, shrink)
    }

    fun getShrinkHeight(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShrinkHeightBind, handle)
    }

    fun setShrinkWidth(shrink: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShrinkWidthBind, handle, shrink)
    }

    fun getShrinkWidth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getShrinkWidthBind, handle)
    }

    object Signals {
        const val idPressed: String = "id_pressed"
        const val idFocused: String = "id_focused"
        const val indexPressed: String = "index_pressed"
        const val menuChanged: String = "menu_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PopupMenu? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PopupMenu? =
            if (handle.address() == 0L) null else PopupMenu(handle)

        private const val ACTIVATE_ITEM_BY_EVENT_HASH = 3716412023L
        private val activateItemByEventBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "activate_item_by_event", ACTIVATE_ITEM_BY_EVENT_HASH)
        }

        private const val SET_PREFER_NATIVE_MENU_HASH = 2586408642L
        private val setPreferNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_prefer_native_menu", SET_PREFER_NATIVE_MENU_HASH)
        }

        private const val IS_PREFER_NATIVE_MENU_HASH = 36873697L
        private val isPreferNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_prefer_native_menu", IS_PREFER_NATIVE_MENU_HASH)
        }

        private const val IS_NATIVE_MENU_HASH = 36873697L
        private val isNativeMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_native_menu", IS_NATIVE_MENU_HASH)
        }

        private const val ADD_ITEM_HASH = 3674230041L
        private val addItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_item", ADD_ITEM_HASH)
        }

        private const val ADD_ICON_ITEM_HASH = 1086190128L
        private val addIconItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_item", ADD_ICON_ITEM_HASH)
        }

        private const val ADD_CHECK_ITEM_HASH = 3674230041L
        private val addCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_check_item", ADD_CHECK_ITEM_HASH)
        }

        private const val ADD_ICON_CHECK_ITEM_HASH = 1086190128L
        private val addIconCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_check_item", ADD_ICON_CHECK_ITEM_HASH)
        }

        private const val ADD_RADIO_CHECK_ITEM_HASH = 3674230041L
        private val addRadioCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_radio_check_item", ADD_RADIO_CHECK_ITEM_HASH)
        }

        private const val ADD_ICON_RADIO_CHECK_ITEM_HASH = 1086190128L
        private val addIconRadioCheckItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_radio_check_item", ADD_ICON_RADIO_CHECK_ITEM_HASH)
        }

        private const val ADD_MULTISTATE_ITEM_HASH = 150780458L
        private val addMultistateItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_multistate_item", ADD_MULTISTATE_ITEM_HASH)
        }

        private const val ADD_SHORTCUT_HASH = 3451850107L
        private val addShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_shortcut", ADD_SHORTCUT_HASH)
        }

        private const val ADD_ICON_SHORTCUT_HASH = 2997871092L
        private val addIconShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_shortcut", ADD_ICON_SHORTCUT_HASH)
        }

        private const val ADD_CHECK_SHORTCUT_HASH = 1642193386L
        private val addCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_check_shortcut", ADD_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_ICON_CHECK_SHORTCUT_HASH = 3856247530L
        private val addIconCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_check_shortcut", ADD_ICON_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_RADIO_CHECK_SHORTCUT_HASH = 1642193386L
        private val addRadioCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_radio_check_shortcut", ADD_RADIO_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_ICON_RADIO_CHECK_SHORTCUT_HASH = 3856247530L
        private val addIconRadioCheckShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_icon_radio_check_shortcut", ADD_ICON_RADIO_CHECK_SHORTCUT_HASH)
        }

        private const val ADD_SUBMENU_ITEM_HASH = 2979222410L
        private val addSubmenuItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_submenu_item", ADD_SUBMENU_ITEM_HASH)
        }

        private const val ADD_SUBMENU_NODE_ITEM_HASH = 1325455216L
        private val addSubmenuNodeItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_submenu_node_item", ADD_SUBMENU_NODE_ITEM_HASH)
        }

        private const val SET_ITEM_TEXT_HASH = 501894301L
        private val setItemTextBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_text", SET_ITEM_TEXT_HASH)
        }

        private const val SET_ITEM_TEXT_DIRECTION_HASH = 1707680378L
        private val setItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_text_direction", SET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val SET_ITEM_LANGUAGE_HASH = 501894301L
        private val setItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_language", SET_ITEM_LANGUAGE_HASH)
        }

        private const val SET_ITEM_AUTO_TRANSLATE_MODE_HASH = 287402019L
        private val setItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_auto_translate_mode", SET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_ITEM_ICON_HASH = 666127730L
        private val setItemIconBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon", SET_ITEM_ICON_HASH)
        }

        private const val SET_ITEM_ICON_MAX_WIDTH_HASH = 3937882851L
        private val setItemIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon_max_width", SET_ITEM_ICON_MAX_WIDTH_HASH)
        }

        private const val SET_ITEM_ICON_MODULATE_HASH = 2878471219L
        private val setItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_icon_modulate", SET_ITEM_ICON_MODULATE_HASH)
        }

        private const val SET_ITEM_CHECKED_HASH = 300928843L
        private val setItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_checked", SET_ITEM_CHECKED_HASH)
        }

        private const val SET_ITEM_ID_HASH = 3937882851L
        private val setItemIdBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_id", SET_ITEM_ID_HASH)
        }

        private const val SET_ITEM_ACCELERATOR_HASH = 2992817551L
        private val setItemAcceleratorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_accelerator", SET_ITEM_ACCELERATOR_HASH)
        }

        private const val SET_ITEM_METADATA_HASH = 2152698145L
        private val setItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_metadata", SET_ITEM_METADATA_HASH)
        }

        private const val SET_ITEM_DISABLED_HASH = 300928843L
        private val setItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_disabled", SET_ITEM_DISABLED_HASH)
        }

        private const val SET_ITEM_SUBMENU_HASH = 501894301L
        private val setItemSubmenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_submenu", SET_ITEM_SUBMENU_HASH)
        }

        private const val SET_ITEM_SUBMENU_NODE_HASH = 1068370740L
        private val setItemSubmenuNodeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_submenu_node", SET_ITEM_SUBMENU_NODE_HASH)
        }

        private const val SET_ITEM_AS_SEPARATOR_HASH = 300928843L
        private val setItemAsSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_separator", SET_ITEM_AS_SEPARATOR_HASH)
        }

        private const val SET_ITEM_AS_CHECKABLE_HASH = 300928843L
        private val setItemAsCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_checkable", SET_ITEM_AS_CHECKABLE_HASH)
        }

        private const val SET_ITEM_AS_RADIO_CHECKABLE_HASH = 300928843L
        private val setItemAsRadioCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_as_radio_checkable", SET_ITEM_AS_RADIO_CHECKABLE_HASH)
        }

        private const val SET_ITEM_TOOLTIP_HASH = 501894301L
        private val setItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)
        }

        private const val SET_ITEM_SHORTCUT_HASH = 825127832L
        private val setItemShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_shortcut", SET_ITEM_SHORTCUT_HASH)
        }

        private const val SET_ITEM_INDENT_HASH = 3937882851L
        private val setItemIndentBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_indent", SET_ITEM_INDENT_HASH)
        }

        private const val SET_ITEM_MULTISTATE_HASH = 3937882851L
        private val setItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_multistate", SET_ITEM_MULTISTATE_HASH)
        }

        private const val SET_ITEM_MULTISTATE_MAX_HASH = 3937882851L
        private val setItemMultistateMaxBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_multistate_max", SET_ITEM_MULTISTATE_MAX_HASH)
        }

        private const val SET_ITEM_SHORTCUT_DISABLED_HASH = 300928843L
        private val setItemShortcutDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_shortcut_disabled", SET_ITEM_SHORTCUT_DISABLED_HASH)
        }

        private const val SET_ITEM_INDEX_HASH = 3937882851L
        private val setItemIndexBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_index", SET_ITEM_INDEX_HASH)
        }

        private const val TOGGLE_ITEM_CHECKED_HASH = 1286410249L
        private val toggleItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "toggle_item_checked", TOGGLE_ITEM_CHECKED_HASH)
        }

        private const val TOGGLE_ITEM_MULTISTATE_HASH = 1286410249L
        private val toggleItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "toggle_item_multistate", TOGGLE_ITEM_MULTISTATE_HASH)
        }

        private const val GET_ITEM_TEXT_HASH = 844755477L
        private val getItemTextBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_text", GET_ITEM_TEXT_HASH)
        }

        private const val GET_ITEM_TEXT_DIRECTION_HASH = 4235602388L
        private val getItemTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_text_direction", GET_ITEM_TEXT_DIRECTION_HASH)
        }

        private const val GET_ITEM_LANGUAGE_HASH = 844755477L
        private val getItemLanguageBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_language", GET_ITEM_LANGUAGE_HASH)
        }

        private const val GET_ITEM_AUTO_TRANSLATE_MODE_HASH = 906302372L
        private val getItemAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_auto_translate_mode", GET_ITEM_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_ITEM_ICON_HASH = 3536238170L
        private val getItemIconBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon", GET_ITEM_ICON_HASH)
        }

        private const val GET_ITEM_ICON_MAX_WIDTH_HASH = 923996154L
        private val getItemIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon_max_width", GET_ITEM_ICON_MAX_WIDTH_HASH)
        }

        private const val GET_ITEM_ICON_MODULATE_HASH = 3457211756L
        private val getItemIconModulateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_icon_modulate", GET_ITEM_ICON_MODULATE_HASH)
        }

        private const val IS_ITEM_CHECKED_HASH = 1116898809L
        private val isItemCheckedBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_checked", IS_ITEM_CHECKED_HASH)
        }

        private const val GET_ITEM_ID_HASH = 923996154L
        private val getItemIdBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_id", GET_ITEM_ID_HASH)
        }

        private const val GET_ITEM_INDEX_HASH = 923996154L
        private val getItemIndexBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_index", GET_ITEM_INDEX_HASH)
        }

        private const val GET_ITEM_ACCELERATOR_HASH = 253789942L
        private val getItemAcceleratorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_accelerator", GET_ITEM_ACCELERATOR_HASH)
        }

        private const val GET_ITEM_METADATA_HASH = 4227898402L
        private val getItemMetadataBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_metadata", GET_ITEM_METADATA_HASH)
        }

        private const val IS_ITEM_DISABLED_HASH = 1116898809L
        private val isItemDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_disabled", IS_ITEM_DISABLED_HASH)
        }

        private const val GET_ITEM_SUBMENU_HASH = 844755477L
        private val getItemSubmenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_submenu", GET_ITEM_SUBMENU_HASH)
        }

        private const val GET_ITEM_SUBMENU_NODE_HASH = 2100501353L
        private val getItemSubmenuNodeBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_submenu_node", GET_ITEM_SUBMENU_NODE_HASH)
        }

        private const val IS_ITEM_SEPARATOR_HASH = 1116898809L
        private val isItemSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_separator", IS_ITEM_SEPARATOR_HASH)
        }

        private const val IS_ITEM_CHECKABLE_HASH = 1116898809L
        private val isItemCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_checkable", IS_ITEM_CHECKABLE_HASH)
        }

        private const val IS_ITEM_RADIO_CHECKABLE_HASH = 1116898809L
        private val isItemRadioCheckableBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_radio_checkable", IS_ITEM_RADIO_CHECKABLE_HASH)
        }

        private const val IS_ITEM_SHORTCUT_DISABLED_HASH = 1116898809L
        private val isItemShortcutDisabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_item_shortcut_disabled", IS_ITEM_SHORTCUT_DISABLED_HASH)
        }

        private const val GET_ITEM_TOOLTIP_HASH = 844755477L
        private val getItemTooltipBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
        }

        private const val GET_ITEM_SHORTCUT_HASH = 1449483325L
        private val getItemShortcutBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_shortcut", GET_ITEM_SHORTCUT_HASH)
        }

        private const val GET_ITEM_INDENT_HASH = 923996154L
        private val getItemIndentBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_indent", GET_ITEM_INDENT_HASH)
        }

        private const val GET_ITEM_MULTISTATE_MAX_HASH = 923996154L
        private val getItemMultistateMaxBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_multistate_max", GET_ITEM_MULTISTATE_MAX_HASH)
        }

        private const val GET_ITEM_MULTISTATE_HASH = 923996154L
        private val getItemMultistateBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_multistate", GET_ITEM_MULTISTATE_HASH)
        }

        private const val SET_FOCUSED_ITEM_HASH = 1286410249L
        private val setFocusedItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_focused_item", SET_FOCUSED_ITEM_HASH)
        }

        private const val GET_FOCUSED_ITEM_HASH = 3905245786L
        private val getFocusedItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_focused_item", GET_FOCUSED_ITEM_HASH)
        }

        private const val SET_ITEM_COUNT_HASH = 1286410249L
        private val setItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_item_count", SET_ITEM_COUNT_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_item_count", GET_ITEM_COUNT_HASH)
        }

        private const val SCROLL_TO_ITEM_HASH = 1286410249L
        private val scrollToItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "scroll_to_item", SCROLL_TO_ITEM_HASH)
        }

        private const val REMOVE_ITEM_HASH = 1286410249L
        private val removeItemBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "remove_item", REMOVE_ITEM_HASH)
        }

        private const val ADD_SEPARATOR_HASH = 2266703459L
        private val addSeparatorBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "add_separator", ADD_SEPARATOR_HASH)
        }

        private const val CLEAR_HASH = 107499316L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "clear", CLEAR_HASH)
        }

        private const val SET_HIDE_ON_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_item_selection", SET_HIDE_ON_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_item_selection", IS_HIDE_ON_ITEM_SELECTION_HASH)
        }

        private const val SET_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnCheckableItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_checkable_item_selection", SET_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnCheckableItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_checkable_item_selection", IS_HIDE_ON_CHECKABLE_ITEM_SELECTION_HASH)
        }

        private const val SET_HIDE_ON_STATE_ITEM_SELECTION_HASH = 2586408642L
        private val setHideOnStateItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_hide_on_state_item_selection", SET_HIDE_ON_STATE_ITEM_SELECTION_HASH)
        }

        private const val IS_HIDE_ON_STATE_ITEM_SELECTION_HASH = 36873697L
        private val isHideOnStateItemSelectionBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_hide_on_state_item_selection", IS_HIDE_ON_STATE_ITEM_SELECTION_HASH)
        }

        private const val SET_SUBMENU_POPUP_DELAY_HASH = 373806689L
        private val setSubmenuPopupDelayBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_submenu_popup_delay", SET_SUBMENU_POPUP_DELAY_HASH)
        }

        private const val GET_SUBMENU_POPUP_DELAY_HASH = 1740695150L
        private val getSubmenuPopupDelayBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_submenu_popup_delay", GET_SUBMENU_POPUP_DELAY_HASH)
        }

        private const val SET_ALLOW_SEARCH_HASH = 2586408642L
        private val setAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_allow_search", SET_ALLOW_SEARCH_HASH)
        }

        private const val GET_ALLOW_SEARCH_HASH = 36873697L
        private val getAllowSearchBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_allow_search", GET_ALLOW_SEARCH_HASH)
        }

        private const val IS_SYSTEM_MENU_HASH = 36873697L
        private val isSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_system_menu", IS_SYSTEM_MENU_HASH)
        }

        private const val SET_SYSTEM_MENU_HASH = 600639674L
        private val setSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_system_menu", SET_SYSTEM_MENU_HASH)
        }

        private const val GET_SYSTEM_MENU_HASH = 1222557358L
        private val getSystemMenuBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_system_menu", GET_SYSTEM_MENU_HASH)
        }

        private const val SET_SEARCH_BAR_ENABLED_HASH = 2586408642L
        private val setSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_enabled", SET_SEARCH_BAR_ENABLED_HASH)
        }

        private const val IS_SEARCH_BAR_ENABLED_HASH = 36873697L
        private val isSearchBarEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_search_bar_enabled", IS_SEARCH_BAR_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 1286410249L
        private val setSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_min_item_count", SET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH = 3905245786L
        private val getSearchBarMinItemCountBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_search_bar_min_item_count", GET_SEARCH_BAR_MIN_ITEM_COUNT_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 2586408642L
        private val setSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_fuzzy_search_enabled", SET_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH = 36873697L
        private val isSearchBarFuzzySearchEnabledBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "is_search_bar_fuzzy_search_enabled", IS_SEARCH_BAR_FUZZY_SEARCH_ENABLED_HASH)
        }

        private const val SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 1286410249L
        private val setSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_search_bar_fuzzy_search_max_misses", SET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH = 3905245786L
        private val getSearchBarFuzzySearchMaxMissesBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_search_bar_fuzzy_search_max_misses", GET_SEARCH_BAR_FUZZY_SEARCH_MAX_MISSES_HASH)
        }

        private const val SET_SHRINK_HEIGHT_HASH = 2586408642L
        private val setShrinkHeightBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_shrink_height", SET_SHRINK_HEIGHT_HASH)
        }

        private const val GET_SHRINK_HEIGHT_HASH = 36873697L
        private val getShrinkHeightBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_shrink_height", GET_SHRINK_HEIGHT_HASH)
        }

        private const val SET_SHRINK_WIDTH_HASH = 2586408642L
        private val setShrinkWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "set_shrink_width", SET_SHRINK_WIDTH_HASH)
        }

        private const val GET_SHRINK_WIDTH_HASH = 36873697L
        private val getShrinkWidthBind by lazy {
            ObjectCalls.getMethodBind("PopupMenu", "get_shrink_width", GET_SHRINK_WIDTH_HASH)
        }
    }
}
