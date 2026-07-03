package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * A server interface for OS native menus.
 *
 * Generated from Godot docs: NativeMenu
 */
object NativeMenu {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("NativeMenu")
    }

    const val FEATURE_GLOBAL_MENU: Long = 0L
    const val FEATURE_POPUP_MENU: Long = 1L
    const val FEATURE_OPEN_CLOSE_CALLBACK: Long = 2L
    const val FEATURE_HOVER_CALLBACK: Long = 3L
    const val FEATURE_KEY_CALLBACK: Long = 4L
    const val INVALID_MENU_ID: Long = 0L
    const val MAIN_MENU_ID: Long = 1L
    const val APPLICATION_MENU_ID: Long = 2L
    const val WINDOW_MENU_ID: Long = 3L
    const val HELP_MENU_ID: Long = 4L
    const val DOCK_MENU_ID: Long = 5L

    /**
     * Returns `true` if the specified `feature` is supported by the current `NativeMenu`, `false`
     * otherwise. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.has_feature
     */
    @JvmStatic
    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature)
    }

    /**
     * Returns `true` if a special system menu is supported. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: NativeMenu.has_system_menu
     */
    @JvmStatic
    fun hasSystemMenu(menuId: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasSystemMenuBind, singleton, menuId)
    }

    /**
     * Returns RID of a special system menu. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_system_menu
     */
    @JvmStatic
    fun getSystemMenu(menuId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(getSystemMenuBind, singleton, menuId)
    }

    /**
     * Returns readable name of a special system menu. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_system_menu_name
     */
    @JvmStatic
    fun getSystemMenuName(menuId: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getSystemMenuNameBind, singleton, menuId)
    }

    /**
     * Returns the text of the system menu item. Note: This method is implemented on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_system_menu_text
     */
    @JvmStatic
    fun getSystemMenuText(menuId: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getSystemMenuTextBind, singleton, menuId)
    }

    /**
     * Sets the text of the system menu item. Note: This method is implemented on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_system_menu_text
     */
    @JvmStatic
    fun setSystemMenuText(menuId: Long, name: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setSystemMenuTextBind, singleton, menuId, name)
    }

    /**
     * Creates a new global menu object. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.create_menu
     */
    @JvmStatic
    fun createMenu(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(createMenuBind, singleton)
    }

    /**
     * Returns `true` if `rid` is valid global menu. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.has_menu
     */
    @JvmStatic
    fun hasMenu(rid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasMenuBind, singleton, rid)
    }

    /**
     * Frees a global menu object created by this `NativeMenu`. Note: This method is implemented on
     * macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.free_menu
     */
    @JvmStatic
    fun freeMenu(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeMenuBind, singleton, rid)
    }

    /**
     * Returns global menu size. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_size
     */
    @JvmStatic
    fun getSize(rid: RID): Vector2 {
        return ObjectCalls.ptrcallWithRIDArgRetVector2(getSizeBind, singleton, rid)
    }

    @JvmStatic
    /**
     * Shows the global menu at `position` in the screen coordinates. Note: This method is implemented
     * on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.popup
     */
    fun popup(rid: RID, position: Vector2i) {
        ObjectCalls.ptrcallWithRIDAndVector2iArg(popupBind, singleton, rid, position)
    }

    /**
     * Sets the menu text layout direction from right-to-left if `is_rtl` is `true`. Note: This method
     * is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_interface_direction
     */
    @JvmStatic
    fun setInterfaceDirection(rid: RID, isRtl: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(setInterfaceDirectionBind, singleton, rid, isRtl)
    }

    /**
     * Registers callable to emit after the menu is closed. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: NativeMenu.set_popup_open_callback
     */
    @JvmStatic
    fun setPopupOpenCallback(rid: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(setPopupOpenCallbackBind, singleton, rid, callback.target.handle, callback.method)
    }

    /**
     * Returns global menu open callback. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_popup_open_callback
     */
    @JvmStatic
    fun getPopupOpenCallback(rid: RID): GodotCallable? {
        return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupOpenCallbackBind, singleton, rid)
    }

    /**
     * Registers callable to emit when the menu is about to show. Note: The OS can simulate menu
     * opening to track menu item changes and global shortcuts, in which case the corresponding close
     * callback is not triggered. Use `is_opened` to check if the menu is currently opened. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_popup_close_callback
     */
    @JvmStatic
    fun setPopupCloseCallback(rid: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(setPopupCloseCallbackBind, singleton, rid, callback.target.handle, callback.method)
    }

    /**
     * Returns global menu close callback. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_popup_close_callback
     */
    @JvmStatic
    fun getPopupCloseCallback(rid: RID): GodotCallable? {
        return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupCloseCallbackBind, singleton, rid)
    }

    /**
     * Sets the minimum width of the global menu. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_minimum_width
     */
    @JvmStatic
    fun setMinimumWidth(rid: RID, width: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(setMinimumWidthBind, singleton, rid, width)
    }

    /**
     * Returns global menu minimum width. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_minimum_width
     */
    @JvmStatic
    fun getMinimumWidth(rid: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(getMinimumWidthBind, singleton, rid)
    }

    /**
     * Returns `true` if the menu is currently opened. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.is_opened
     */
    @JvmStatic
    fun isOpened(rid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(isOpenedBind, singleton, rid)
    }

    /**
     * Adds an item that will act as a submenu of the global menu `rid`. The `submenu_rid` argument is
     * the RID of the global menu that will be shown when the item is clicked. Returns index of the
     * inserted item, it's not guaranteed to be the same as `index` value. Note: This method is
     * implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.add_submenu_item
     */
    @JvmStatic
    fun addSubmenuItem(rid: RID, label: String, submenuRid: RID, tag: Any? = null, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDStringRIDVariantIntArgsRetInt(addSubmenuItemBind, singleton, rid, label, submenuRid, tag, index)
    }

    /**
     * Adds a new item with text `label` to the global menu `rid`. Returns index of the inserted item,
     * it's not guaranteed to be the same as `index` value. An `accelerator` can optionally be defined,
     * which is a keyboard shortcut that can be pressed to trigger the menu button even if it's not
     * currently open. The `accelerator` is generally a combination of `KeyModifierMask`s and `Key`s
     * using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note: The `callback` and
     * `key_callback` Callables need to accept exactly one Variant parameter, the parameter passed to
     * the Callables will be the value passed to `tag`. Note: This method is implemented on macOS and
     * Windows. Note: On Windows, `accelerator` and `key_callback` are ignored.
     *
     * Generated from Godot docs: NativeMenu.add_item
     */
    @JvmStatic
    fun addItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addItemBind, singleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new checkable item with text `label` to the global menu `rid`. Returns index of the
     * inserted item, it's not guaranteed to be the same as `index` value. An `accelerator` can
     * optionally be defined, which is a keyboard shortcut that can be pressed to trigger the menu
     * button even if it's not currently open. The `accelerator` is generally a combination of
     * `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note:
     * The `callback` and `key_callback` Callables need to accept exactly one Variant parameter, the
     * parameter passed to the Callables will be the value passed to `tag`. Note: This method is
     * implemented on macOS and Windows. Note: On Windows, `accelerator` and `key_callback` are
     * ignored.
     *
     * Generated from Godot docs: NativeMenu.add_check_item
     */
    @JvmStatic
    fun addCheckItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addCheckItemBind, singleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new item with text `label` and icon `icon` to the global menu `rid`. Returns index of the
     * inserted item, it's not guaranteed to be the same as `index` value. An `accelerator` can
     * optionally be defined, which is a keyboard shortcut that can be pressed to trigger the menu
     * button even if it's not currently open. The `accelerator` is generally a combination of
     * `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note:
     * The `callback` and `key_callback` Callables need to accept exactly one Variant parameter, the
     * parameter passed to the Callables will be the value passed to `tag`. Note: This method is
     * implemented on macOS and Windows. Note: On Windows, `accelerator` and `key_callback` are
     * ignored.
     *
     * Generated from Godot docs: NativeMenu.add_icon_item
     */
    @JvmStatic
    fun addIconItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconItemBind, singleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new checkable item with text `label` and icon `icon` to the global menu `rid`. Returns
     * index of the inserted item, it's not guaranteed to be the same as `index` value. An
     * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
     * trigger the menu button even if it's not currently open. The `accelerator` is generally a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
     * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
     * method is implemented on macOS and Windows. Note: On Windows, `accelerator` and `key_callback`
     * are ignored.
     *
     * Generated from Godot docs: NativeMenu.add_icon_check_item
     */
    @JvmStatic
    fun addIconCheckItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconCheckItemBind, singleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new radio-checkable item with text `label` to the global menu `rid`. Returns index of the
     * inserted item, it's not guaranteed to be the same as `index` value. An `accelerator` can
     * optionally be defined, which is a keyboard shortcut that can be pressed to trigger the menu
     * button even if it's not currently open. The `accelerator` is generally a combination of
     * `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note:
     * Radio-checkable items just display a checkmark, but don't have any built-in checking behavior
     * and must be checked/unchecked manually. See `set_item_checked` for more info on how to control
     * it. Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
     * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
     * method is implemented on macOS and Windows. Note: On Windows, `accelerator` and `key_callback`
     * are ignored.
     *
     * Generated from Godot docs: NativeMenu.add_radio_check_item
     */
    @JvmStatic
    fun addRadioCheckItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addRadioCheckItemBind, singleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new radio-checkable item with text `label` and icon `icon` to the global menu `rid`.
     * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. An
     * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
     * trigger the menu button even if it's not currently open. The `accelerator` is generally a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: Radio-checkable items just display a checkmark, but don't have any built-in
     * checking behavior and must be checked/unchecked manually. See `set_item_checked` for more info
     * on how to control it. Note: The `callback` and `key_callback` Callables need to accept exactly
     * one Variant parameter, the parameter passed to the Callables will be the value passed to `tag`.
     * Note: This method is implemented on macOS and Windows. Note: On Windows, `accelerator` and
     * `key_callback` are ignored.
     *
     * Generated from Godot docs: NativeMenu.add_icon_radio_check_item
     */
    @JvmStatic
    fun addIconRadioCheckItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconRadioCheckItemBind, singleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a new item with text `label` to the global menu `rid`. Contrarily to normal binary items,
     * multistate items can have more than two states, as defined by `max_states`. Each press or
     * activate of the item will increase the state by one. The default value is defined by
     * `default_state`. Returns index of the inserted item, it's not guaranteed to be the same as
     * `index` value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can
     * be pressed to trigger the menu button even if it's not currently open. The `accelerator` is
     * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
     * | KEY_A` (Ctrl + A). Note: By default, there's no indication of the current item state, it
     * should be changed manually. Note: The `callback` and `key_callback` Callables need to accept
     * exactly one Variant parameter, the parameter passed to the Callables will be the value passed to
     * `tag`. Note: This method is implemented on macOS and Windows. Note: On Windows, `accelerator`
     * and `key_callback` are ignored.
     *
     * Generated from Godot docs: NativeMenu.add_multistate_item
     */
    @JvmStatic
    fun addMultistateItem(rid: RID, label: String, maxStates: Int, defaultState: Int, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDStringTwoIntTwoCallableVariantLongIntArgsRetInt(addMultistateItemBind, singleton, rid, label, maxStates, defaultState, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    /**
     * Adds a separator between items to the global menu `rid`. Separators also occupy an index.
     * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. Note:
     * This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.add_separator
     */
    @JvmStatic
    fun addSeparator(rid: RID, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetInt(addSeparatorBind, singleton, rid, index)
    }

    /**
     * Returns the index of the item with the specified `text`. Indices are automatically assigned to
     * each item by the engine. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.find_item_index_with_text
     */
    @JvmStatic
    fun findItemIndexWithText(rid: RID, text: String): Int {
        return ObjectCalls.ptrcallWithRIDAndStringArgRetInt(findItemIndexWithTextBind, singleton, rid, text)
    }

    /**
     * Returns the index of the item with the specified `tag`. Indices are automatically assigned to
     * each item by the engine. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.find_item_index_with_tag
     */
    @JvmStatic
    fun findItemIndexWithTag(rid: RID, tag: Any?): Int {
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetInt(findItemIndexWithTagBind, singleton, rid, tag)
    }

    /**
     * Returns the index of the item with the submenu specified by `submenu_rid`. Indices are
     * automatically assigned to each item by the engine. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.find_item_index_with_submenu
     */
    @JvmStatic
    fun findItemIndexWithSubmenu(rid: RID, submenuRid: RID): Int {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetInt(findItemIndexWithSubmenuBind, singleton, rid, submenuRid)
    }

    /**
     * Returns `true` if the item at index `idx` is checked. Note: This method is implemented on macOS
     * and Windows.
     *
     * Generated from Godot docs: NativeMenu.is_item_checked
     */
    @JvmStatic
    fun isItemChecked(rid: RID, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(isItemCheckedBind, singleton, rid, idx)
    }

    /**
     * Returns `true` if the item at index `idx` is checkable in some way, i.e. if it has a checkbox or
     * radio button. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.is_item_checkable
     */
    @JvmStatic
    fun isItemCheckable(rid: RID, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(isItemCheckableBind, singleton, rid, idx)
    }

    /**
     * Returns `true` if the item at index `idx` has radio button-style checkability. Note: This is
     * purely cosmetic; you must add the logic for checking/unchecking items in radio groups. Note:
     * This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.is_item_radio_checkable
     */
    @JvmStatic
    fun isItemRadioCheckable(rid: RID, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(isItemRadioCheckableBind, singleton, rid, idx)
    }

    /**
     * Returns the callback of the item at index `idx`. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_callback
     */
    @JvmStatic
    fun getItemCallback(rid: RID, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemCallbackBind, singleton, rid, idx)
    }

    /**
     * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_item_key_callback
     */
    @JvmStatic
    fun getItemKeyCallback(rid: RID, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemKeyCallbackBind, singleton, rid, idx)
    }

    /**
     * Returns the metadata of the specified item, which might be of any type. You can set it with
     * `set_item_tag`, which provides a simple way of assigning context data to items. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_tag
     */
    @JvmStatic
    fun getItemTag(rid: RID, idx: Int): Any? {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVariantScalar(getItemTagBind, singleton, rid, idx)
    }

    /**
     * Returns the text of the item at index `idx`. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_text
     */
    @JvmStatic
    fun getItemText(rid: RID, idx: Int): String {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetString(getItemTextBind, singleton, rid, idx)
    }

    /**
     * Returns the submenu ID of the item at index `idx`. See `add_submenu_item` for more info on how
     * to add a submenu. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_submenu
     */
    @JvmStatic
    fun getItemSubmenu(rid: RID, idx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(getItemSubmenuBind, singleton, rid, idx)
    }

    /**
     * Returns the accelerator of the item at index `idx`. Accelerators are special combinations of
     * keys that activate the item, no matter which control is focused. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_item_accelerator
     */
    @JvmStatic
    fun getItemAccelerator(rid: RID, idx: Int): Long {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetLong(getItemAcceleratorBind, singleton, rid, idx)
    }

    /**
     * Returns `true` if the item at index `idx` is disabled. When it is disabled it can't be selected,
     * or its action invoked. See `set_item_disabled` for more info on how to disable an item. Note:
     * This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.is_item_disabled
     */
    @JvmStatic
    fun isItemDisabled(rid: RID, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(isItemDisabledBind, singleton, rid, idx)
    }

    /**
     * Returns `true` if the item at index `idx` is hidden. See `set_item_hidden` for more info on how
     * to hide an item. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.is_item_hidden
     */
    @JvmStatic
    fun isItemHidden(rid: RID, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(isItemHiddenBind, singleton, rid, idx)
    }

    /**
     * Returns the tooltip associated with the specified index `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_item_tooltip
     */
    @JvmStatic
    fun getItemTooltip(rid: RID, idx: Int): String {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetString(getItemTooltipBind, singleton, rid, idx)
    }

    /**
     * Returns the state of a multistate item. See `add_multistate_item` for details. Note: This method
     * is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_state
     */
    @JvmStatic
    fun getItemState(rid: RID, idx: Int): Int {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetInt(getItemStateBind, singleton, rid, idx)
    }

    /**
     * Returns number of states of a multistate item. See `add_multistate_item` for details. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_max_states
     */
    @JvmStatic
    fun getItemMaxStates(rid: RID, idx: Int): Int {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetInt(getItemMaxStatesBind, singleton, rid, idx)
    }

    /**
     * Returns the icon of the item at index `idx`. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_icon
     */
    @JvmStatic
    fun getItemIcon(rid: RID, idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithRIDAndIntArgRetObject(getItemIconBind, singleton, rid, idx))
    }

    /**
     * Returns the horizontal offset of the item at the given `idx`. Note: This method is implemented
     * only on macOS.
     *
     * Generated from Godot docs: NativeMenu.get_item_indentation_level
     */
    @JvmStatic
    fun getItemIndentationLevel(rid: RID, idx: Int): Int {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetInt(getItemIndentationLevelBind, singleton, rid, idx)
    }

    /**
     * Sets the checkstate status of the item at index `idx`. Note: This method is implemented on macOS
     * and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_checked
     */
    @JvmStatic
    fun setItemChecked(rid: RID, idx: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(setItemCheckedBind, singleton, rid, idx, checked)
    }

    /**
     * Sets whether the item at index `idx` has a checkbox. If `false`, sets the type of the item to
     * plain text. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_checkable
     */
    @JvmStatic
    fun setItemCheckable(rid: RID, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(setItemCheckableBind, singleton, rid, idx, checkable)
    }

    /**
     * Sets the type of the item at the specified index `idx` to radio button. If `false`, sets the
     * type of the item to plain text. Note: This is purely cosmetic; you must add the logic for
     * checking/unchecking items in radio groups. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_radio_checkable
     */
    @JvmStatic
    fun setItemRadioCheckable(rid: RID, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(setItemRadioCheckableBind, singleton, rid, idx, checkable)
    }

    /**
     * Sets the callback of the item at index `idx`. Callback is emitted when an item is pressed. Note:
     * The `callback` Callable needs to accept exactly one Variant parameter, the parameter passed to
     * the Callable will be the value passed to the `tag` parameter when the menu item was created.
     * Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_callback
     */
    @JvmStatic
    fun setItemCallback(rid: RID, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDIntCallableArgs(setItemCallbackBind, singleton, rid, idx, callback.target.handle, callback.method)
    }

    /**
     * Sets the callback of the item at index `idx`. The callback is emitted when an item is hovered.
     * Note: The `callback` Callable needs to accept exactly one Variant parameter, the parameter
     * passed to the Callable will be the value passed to the `tag` parameter when the menu item was
     * created. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_hover_callbacks
     */
    @JvmStatic
    fun setItemHoverCallbacks(rid: RID, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDIntCallableArgs(setItemHoverCallbacksBind, singleton, rid, idx, callback.target.handle, callback.method)
    }

    /**
     * Sets the callback of the item at index `idx`. Callback is emitted when its accelerator is
     * activated. Note: The `key_callback` Callable needs to accept exactly one Variant parameter, the
     * parameter passed to the Callable will be the value passed to the `tag` parameter when the menu
     * item was created. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_key_callback
     */
    @JvmStatic
    fun setItemKeyCallback(rid: RID, idx: Int, keyCallback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDIntCallableArgs(setItemKeyCallbackBind, singleton, rid, idx, keyCallback.target.handle, keyCallback.method)
    }

    /**
     * Sets the metadata of an item, which may be of any type. You can later get it with
     * `get_item_tag`, which provides a simple way of assigning context data to items. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_tag
     */
    @JvmStatic
    fun setItemTag(rid: RID, idx: Int, tag: Any?) {
        ObjectCalls.ptrcallWithRIDIntAndVariantArgs(setItemTagBind, singleton, rid, idx, tag)
    }

    /**
     * Sets the text of the item at index `idx`. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_text
     */
    @JvmStatic
    fun setItemText(rid: RID, idx: Int, text: String) {
        ObjectCalls.ptrcallWithRIDIntAndStringArgs(setItemTextBind, singleton, rid, idx, text)
    }

    /**
     * Sets the submenu RID of the item at index `idx`. The submenu is a global menu that would be
     * shown when the item is clicked. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_submenu
     */
    @JvmStatic
    fun setItemSubmenu(rid: RID, idx: Int, submenuRid: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(setItemSubmenuBind, singleton, rid, idx, submenuRid)
    }

    /**
     * Sets the accelerator of the item at index `idx`. `keycode` can be a single `Key`, or a
     * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
     * (Ctrl + A). Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_accelerator
     */
    @JvmStatic
    fun setItemAccelerator(rid: RID, idx: Int, keycode: Long) {
        ObjectCalls.ptrcallWithRIDIntLongArgs(setItemAcceleratorBind, singleton, rid, idx, keycode)
    }

    /**
     * Enables/disables the item at index `idx`. When it is disabled, it can't be selected and its
     * action can't be invoked. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_disabled
     */
    @JvmStatic
    fun setItemDisabled(rid: RID, idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(setItemDisabledBind, singleton, rid, idx, disabled)
    }

    /**
     * Hides/shows the item at index `idx`. When it is hidden, an item does not appear in a menu and
     * its action cannot be invoked. Note: This method is implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_hidden
     */
    @JvmStatic
    fun setItemHidden(rid: RID, idx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(setItemHiddenBind, singleton, rid, idx, hidden)
    }

    /**
     * Sets the `String` tooltip of the item at the specified index `idx`. Note: This method is
     * implemented only on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_tooltip
     */
    @JvmStatic
    fun setItemTooltip(rid: RID, idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithRIDIntAndStringArgs(setItemTooltipBind, singleton, rid, idx, tooltip)
    }

    /**
     * Sets the state of a multistate item. See `add_multistate_item` for details. Note: This method is
     * implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_state
     */
    @JvmStatic
    fun setItemState(rid: RID, idx: Int, state: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(setItemStateBind, singleton, rid, idx, state)
    }

    /**
     * Sets number of state of a multistate item. See `add_multistate_item` for details. Note: This
     * method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_max_states
     */
    @JvmStatic
    fun setItemMaxStates(rid: RID, idx: Int, maxStates: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(setItemMaxStatesBind, singleton, rid, idx, maxStates)
    }

    /**
     * Replaces the `Texture2D` icon of the specified `idx`. Note: This method is implemented on macOS
     * and Windows. Note: This method is not supported by macOS Dock menu items.
     *
     * Generated from Godot docs: NativeMenu.set_item_icon
     */
    @JvmStatic
    fun setItemIcon(rid: RID, idx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithRIDIntAndObjectArg(setItemIconBind, singleton, rid, idx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets the horizontal offset of the item at the given `idx`. Note: This method is implemented only
     * on macOS.
     *
     * Generated from Godot docs: NativeMenu.set_item_indentation_level
     */
    @JvmStatic
    fun setItemIndentationLevel(rid: RID, idx: Int, level: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(setItemIndentationLevelBind, singleton, rid, idx, level)
    }

    /**
     * Changes the index of the item at index `idx` to be at index `target_idx`. This can be used to
     * move an item above other items. Returns the new index of the moved item, it's not guaranteed to
     * be the same as `target_idx`. Note: The indices of any items between index `idx` and index
     * `target_idx` will be shifted by one. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.set_item_index
     */
    @JvmStatic
    fun setItemIndex(rid: RID, idx: Int, targetIdx: Int): Int {
        return ObjectCalls.ptrcallWithRIDAndTwoIntArgsRetInt(setItemIndexBind, singleton, rid, idx, targetIdx)
    }

    /**
     * Returns number of items in the global menu `rid`. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.get_item_count
     */
    @JvmStatic
    fun getItemCount(rid: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(getItemCountBind, singleton, rid)
    }

    /**
     * Return `true` is global menu is a special system menu. Note: This method is implemented only on
     * macOS.
     *
     * Generated from Godot docs: NativeMenu.is_system_menu
     */
    @JvmStatic
    fun isSystemMenu(rid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(isSystemMenuBind, singleton, rid)
    }

    /**
     * Removes the item at index `idx` from the global menu `rid`. Note: The indices of items after the
     * removed item will be shifted by one. Note: This method is implemented on macOS and Windows.
     *
     * Generated from Godot docs: NativeMenu.remove_item
     */
    @JvmStatic
    fun removeItem(rid: RID, idx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(removeItemBind, singleton, rid, idx)
    }

    @JvmStatic
    /**
     * Removes all items from the global menu `rid`. Note: This method is implemented on macOS and
     * Windows.
     *
     * Generated from Godot docs: NativeMenu.clear
     */
    fun clear(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(clearBind, singleton, rid)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): NativeMenu? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): NativeMenu? =
        if (handle.address() == 0L) null else this

    private const val HAS_FEATURE_HASH = 1708975490L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "has_feature", HAS_FEATURE_HASH)
    }

    private const val HAS_SYSTEM_MENU_HASH = 718213027L
    private val hasSystemMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "has_system_menu", HAS_SYSTEM_MENU_HASH)
    }

    private const val GET_SYSTEM_MENU_HASH = 469707506L
    private val getSystemMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_system_menu", GET_SYSTEM_MENU_HASH)
    }

    private const val GET_SYSTEM_MENU_NAME_HASH = 1281499290L
    private val getSystemMenuNameBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_system_menu_name", GET_SYSTEM_MENU_NAME_HASH)
    }

    private const val GET_SYSTEM_MENU_TEXT_HASH = 1281499290L
    private val getSystemMenuTextBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_system_menu_text", GET_SYSTEM_MENU_TEXT_HASH)
    }

    private const val SET_SYSTEM_MENU_TEXT_HASH = 3925225603L
    private val setSystemMenuTextBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_system_menu_text", SET_SYSTEM_MENU_TEXT_HASH)
    }

    private const val CREATE_MENU_HASH = 529393457L
    private val createMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "create_menu", CREATE_MENU_HASH)
    }

    private const val HAS_MENU_HASH = 4155700596L
    private val hasMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "has_menu", HAS_MENU_HASH)
    }

    private const val FREE_MENU_HASH = 2722037293L
    private val freeMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "free_menu", FREE_MENU_HASH)
    }

    private const val GET_SIZE_HASH = 2440833711L
    private val getSizeBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_size", GET_SIZE_HASH)
    }

    private const val POPUP_HASH = 2450610377L
    private val popupBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "popup", POPUP_HASH)
    }

    private const val SET_INTERFACE_DIRECTION_HASH = 1265174801L
    private val setInterfaceDirectionBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_interface_direction", SET_INTERFACE_DIRECTION_HASH)
    }

    private const val SET_POPUP_OPEN_CALLBACK_HASH = 3379118538L
    private val setPopupOpenCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_popup_open_callback", SET_POPUP_OPEN_CALLBACK_HASH)
    }

    private const val GET_POPUP_OPEN_CALLBACK_HASH = 3170603026L
    private val getPopupOpenCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_popup_open_callback", GET_POPUP_OPEN_CALLBACK_HASH)
    }

    private const val SET_POPUP_CLOSE_CALLBACK_HASH = 3379118538L
    private val setPopupCloseCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_popup_close_callback", SET_POPUP_CLOSE_CALLBACK_HASH)
    }

    private const val GET_POPUP_CLOSE_CALLBACK_HASH = 3170603026L
    private val getPopupCloseCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_popup_close_callback", GET_POPUP_CLOSE_CALLBACK_HASH)
    }

    private const val SET_MINIMUM_WIDTH_HASH = 1794382983L
    private val setMinimumWidthBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_minimum_width", SET_MINIMUM_WIDTH_HASH)
    }

    private const val GET_MINIMUM_WIDTH_HASH = 866169185L
    private val getMinimumWidthBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_minimum_width", GET_MINIMUM_WIDTH_HASH)
    }

    private const val IS_OPENED_HASH = 4155700596L
    private val isOpenedBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_opened", IS_OPENED_HASH)
    }

    private const val ADD_SUBMENU_ITEM_HASH = 1002030223L
    private val addSubmenuItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_submenu_item", ADD_SUBMENU_ITEM_HASH)
    }

    private const val ADD_ITEM_HASH = 980552939L
    private val addItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_item", ADD_ITEM_HASH)
    }

    private const val ADD_CHECK_ITEM_HASH = 980552939L
    private val addCheckItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_check_item", ADD_CHECK_ITEM_HASH)
    }

    private const val ADD_ICON_ITEM_HASH = 1372188274L
    private val addIconItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_icon_item", ADD_ICON_ITEM_HASH)
    }

    private const val ADD_ICON_CHECK_ITEM_HASH = 1372188274L
    private val addIconCheckItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_icon_check_item", ADD_ICON_CHECK_ITEM_HASH)
    }

    private const val ADD_RADIO_CHECK_ITEM_HASH = 980552939L
    private val addRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_radio_check_item", ADD_RADIO_CHECK_ITEM_HASH)
    }

    private const val ADD_ICON_RADIO_CHECK_ITEM_HASH = 1372188274L
    private val addIconRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_icon_radio_check_item", ADD_ICON_RADIO_CHECK_ITEM_HASH)
    }

    private const val ADD_MULTISTATE_ITEM_HASH = 2674635658L
    private val addMultistateItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_multistate_item", ADD_MULTISTATE_ITEM_HASH)
    }

    private const val ADD_SEPARATOR_HASH = 448810126L
    private val addSeparatorBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "add_separator", ADD_SEPARATOR_HASH)
    }

    private const val FIND_ITEM_INDEX_WITH_TEXT_HASH = 1362438794L
    private val findItemIndexWithTextBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "find_item_index_with_text", FIND_ITEM_INDEX_WITH_TEXT_HASH)
    }

    private const val FIND_ITEM_INDEX_WITH_TAG_HASH = 1260085030L
    private val findItemIndexWithTagBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "find_item_index_with_tag", FIND_ITEM_INDEX_WITH_TAG_HASH)
    }

    private const val FIND_ITEM_INDEX_WITH_SUBMENU_HASH = 893635918L
    private val findItemIndexWithSubmenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "find_item_index_with_submenu", FIND_ITEM_INDEX_WITH_SUBMENU_HASH)
    }

    private const val IS_ITEM_CHECKED_HASH = 3120086654L
    private val isItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_item_checked", IS_ITEM_CHECKED_HASH)
    }

    private const val IS_ITEM_CHECKABLE_HASH = 3120086654L
    private val isItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_item_checkable", IS_ITEM_CHECKABLE_HASH)
    }

    private const val IS_ITEM_RADIO_CHECKABLE_HASH = 3120086654L
    private val isItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_item_radio_checkable", IS_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val GET_ITEM_CALLBACK_HASH = 1639989698L
    private val getItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_callback", GET_ITEM_CALLBACK_HASH)
    }

    private const val GET_ITEM_KEY_CALLBACK_HASH = 1639989698L
    private val getItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_key_callback", GET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val GET_ITEM_TAG_HASH = 4069510997L
    private val getItemTagBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_tag", GET_ITEM_TAG_HASH)
    }

    private const val GET_ITEM_TEXT_HASH = 1464764419L
    private val getItemTextBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_text", GET_ITEM_TEXT_HASH)
    }

    private const val GET_ITEM_SUBMENU_HASH = 1066463050L
    private val getItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_submenu", GET_ITEM_SUBMENU_HASH)
    }

    private const val GET_ITEM_ACCELERATOR_HASH = 316800700L
    private val getItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_accelerator", GET_ITEM_ACCELERATOR_HASH)
    }

    private const val IS_ITEM_DISABLED_HASH = 3120086654L
    private val isItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_item_disabled", IS_ITEM_DISABLED_HASH)
    }

    private const val IS_ITEM_HIDDEN_HASH = 3120086654L
    private val isItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_item_hidden", IS_ITEM_HIDDEN_HASH)
    }

    private const val GET_ITEM_TOOLTIP_HASH = 1464764419L
    private val getItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
    }

    private const val GET_ITEM_STATE_HASH = 1120910005L
    private val getItemStateBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_state", GET_ITEM_STATE_HASH)
    }

    private const val GET_ITEM_MAX_STATES_HASH = 1120910005L
    private val getItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_max_states", GET_ITEM_MAX_STATES_HASH)
    }

    private const val GET_ITEM_ICON_HASH = 3391850701L
    private val getItemIconBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_icon", GET_ITEM_ICON_HASH)
    }

    private const val GET_ITEM_INDENTATION_LEVEL_HASH = 1120910005L
    private val getItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_indentation_level", GET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val SET_ITEM_CHECKED_HASH = 2658558584L
    private val setItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_checked", SET_ITEM_CHECKED_HASH)
    }

    private const val SET_ITEM_CHECKABLE_HASH = 2658558584L
    private val setItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_checkable", SET_ITEM_CHECKABLE_HASH)
    }

    private const val SET_ITEM_RADIO_CHECKABLE_HASH = 2658558584L
    private val setItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_radio_checkable", SET_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val SET_ITEM_CALLBACK_HASH = 2779810226L
    private val setItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_callback", SET_ITEM_CALLBACK_HASH)
    }

    private const val SET_ITEM_HOVER_CALLBACKS_HASH = 2779810226L
    private val setItemHoverCallbacksBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_hover_callbacks", SET_ITEM_HOVER_CALLBACKS_HASH)
    }

    private const val SET_ITEM_KEY_CALLBACK_HASH = 2779810226L
    private val setItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_key_callback", SET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val SET_ITEM_TAG_HASH = 2706844827L
    private val setItemTagBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_tag", SET_ITEM_TAG_HASH)
    }

    private const val SET_ITEM_TEXT_HASH = 4153150897L
    private val setItemTextBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_text", SET_ITEM_TEXT_HASH)
    }

    private const val SET_ITEM_SUBMENU_HASH = 2310537182L
    private val setItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_submenu", SET_ITEM_SUBMENU_HASH)
    }

    private const val SET_ITEM_ACCELERATOR_HASH = 786300043L
    private val setItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_accelerator", SET_ITEM_ACCELERATOR_HASH)
    }

    private const val SET_ITEM_DISABLED_HASH = 2658558584L
    private val setItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_disabled", SET_ITEM_DISABLED_HASH)
    }

    private const val SET_ITEM_HIDDEN_HASH = 2658558584L
    private val setItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_hidden", SET_ITEM_HIDDEN_HASH)
    }

    private const val SET_ITEM_TOOLTIP_HASH = 4153150897L
    private val setItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_tooltip", SET_ITEM_TOOLTIP_HASH)
    }

    private const val SET_ITEM_STATE_HASH = 4288446313L
    private val setItemStateBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_state", SET_ITEM_STATE_HASH)
    }

    private const val SET_ITEM_MAX_STATES_HASH = 4288446313L
    private val setItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_max_states", SET_ITEM_MAX_STATES_HASH)
    }

    private const val SET_ITEM_ICON_HASH = 1388763257L
    private val setItemIconBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_icon", SET_ITEM_ICON_HASH)
    }

    private const val SET_ITEM_INDENTATION_LEVEL_HASH = 4288446313L
    private val setItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_indentation_level", SET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val SET_ITEM_INDEX_HASH = 23951185L
    private val setItemIndexBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "set_item_index", SET_ITEM_INDEX_HASH)
    }

    private const val GET_ITEM_COUNT_HASH = 2198884583L
    private val getItemCountBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "get_item_count", GET_ITEM_COUNT_HASH)
    }

    private const val IS_SYSTEM_MENU_HASH = 4155700596L
    private val isSystemMenuBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "is_system_menu", IS_SYSTEM_MENU_HASH)
    }

    private const val REMOVE_ITEM_HASH = 3411492887L
    private val removeItemBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "remove_item", REMOVE_ITEM_HASH)
    }

    private const val CLEAR_HASH = 2722037293L
    private val clearBind by lazy {
        ObjectCalls.getMethodBind("NativeMenu", "clear", CLEAR_HASH)
    }
}
