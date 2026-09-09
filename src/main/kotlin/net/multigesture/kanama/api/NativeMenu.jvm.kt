package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NativeMenu (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NativeMenu waits on: ptrcallWithLongArgRetString, ptrcallWithRIDAndIntArgRetString,
//   ptrcallWithRIDAndIntArgRetVariantScalar, ptrcallWithRIDAndVariantArgRetInt,
//   ptrcallWithRIDArgRetCallable, ptrcallWithRIDIntAndVariantArgs, ptrcallWithRIDIntArgsRetCallable,
//   ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt,
//   ptrcallWithRIDStringRIDVariantIntArgsRetInt,
//   ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt,
//   ptrcallWithRIDStringTwoIntTwoCallableVariantLongIntArgsRetInt
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns readable name of a special system menu. Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_system_menu_name
 */
fun NativeMenu.getSystemMenuName(menuId: Long): String {
    return ObjectCalls.ptrcallWithLongArgRetString(getSystemMenuNameBind, nativeMenuSingleton, menuId)
}

/**
 * Returns the text of the system menu item. Note: This method is implemented on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_system_menu_text
 */
fun NativeMenu.getSystemMenuText(menuId: Long): String {
    return ObjectCalls.ptrcallWithLongArgRetString(getSystemMenuTextBind, nativeMenuSingleton, menuId)
}

/**
 * Returns global menu open callback. Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_popup_open_callback
 */
fun NativeMenu.getPopupOpenCallback(rid: RID): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupOpenCallbackBind, nativeMenuSingleton, rid)
}

/**
 * Returns global menu close callback. Note: This method is implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.get_popup_close_callback
 */
fun NativeMenu.getPopupCloseCallback(rid: RID): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupCloseCallbackBind, nativeMenuSingleton, rid)
}

/**
 * Adds an item that will act as a submenu of the global menu `rid`. The `submenu_rid` argument is
 * the RID of the global menu that will be shown when the item is clicked. Returns index of the
 * inserted item, it's not guaranteed to be the same as `index` value. Note: This method is
 * implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.add_submenu_item
 */
fun NativeMenu.addSubmenuItem(rid: RID, label: String, submenuRid: RID, tag: Any? = null, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDStringRIDVariantIntArgsRetInt(addSubmenuItemBind, nativeMenuSingleton, rid, label, submenuRid, tag, index)
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
fun NativeMenu.addItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addItemBind, nativeMenuSingleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addCheckItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addCheckItemBind, nativeMenuSingleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addIconItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconItemBind, nativeMenuSingleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addIconCheckItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconCheckItemBind, nativeMenuSingleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addRadioCheckItem(rid: RID, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDStringTwoCallableVariantLongIntArgsRetInt(addRadioCheckItemBind, nativeMenuSingleton, rid, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addIconRadioCheckItem(rid: RID, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDObjectStringTwoCallableVariantLongIntArgsRetInt(addIconRadioCheckItemBind, nativeMenuSingleton, rid, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
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
fun NativeMenu.addMultistateItem(rid: RID, label: String, maxStates: Int, defaultState: Int, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithRIDStringTwoIntTwoCallableVariantLongIntArgsRetInt(addMultistateItemBind, nativeMenuSingleton, rid, label, maxStates, defaultState, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Returns the index of the item with the specified `tag`. Indices are automatically assigned to
 * each item by the engine. Note: This method is implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.find_item_index_with_tag
 */
fun NativeMenu.findItemIndexWithTag(rid: RID, tag: Any?): Int {
    return ObjectCalls.ptrcallWithRIDAndVariantArgRetInt(findItemIndexWithTagBind, nativeMenuSingleton, rid, tag)
}

/**
 * Returns the callback of the item at index `idx`. Note: This method is implemented on macOS and
 * Windows.
 *
 * Generated from Godot docs: NativeMenu.get_item_callback
 */
fun NativeMenu.getItemCallback(rid: RID, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemCallbackBind, nativeMenuSingleton, rid, idx)
}

/**
 * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
 * only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_item_key_callback
 */
fun NativeMenu.getItemKeyCallback(rid: RID, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemKeyCallbackBind, nativeMenuSingleton, rid, idx)
}

/**
 * Returns the metadata of the specified item, which might be of any type. You can set it with
 * `set_item_tag`, which provides a simple way of assigning context data to items. Note: This
 * method is implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.get_item_tag
 */
fun NativeMenu.getItemTag(rid: RID, idx: Int): Any? {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetVariantScalar(getItemTagBind, nativeMenuSingleton, rid, idx)
}

/**
 * Returns the text of the item at index `idx`. Note: This method is implemented on macOS and
 * Windows.
 *
 * Generated from Godot docs: NativeMenu.get_item_text
 */
fun NativeMenu.getItemText(rid: RID, idx: Int): String {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetString(getItemTextBind, nativeMenuSingleton, rid, idx)
}

/**
 * Returns the tooltip associated with the specified index `idx`. Note: This method is implemented
 * only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_item_tooltip
 */
fun NativeMenu.getItemTooltip(rid: RID, idx: Int): String {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetString(getItemTooltipBind, nativeMenuSingleton, rid, idx)
}

/**
 * Sets the metadata of an item, which may be of any type. You can later get it with
 * `get_item_tag`, which provides a simple way of assigning context data to items. Note: This
 * method is implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.set_item_tag
 */
fun NativeMenu.setItemTag(rid: RID, idx: Int, tag: Any?) {
    ObjectCalls.ptrcallWithRIDIntAndVariantArgs(setItemTagBind, nativeMenuSingleton, rid, idx, tag)
}

private val nativeMenuSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NativeMenu")
}

private const val GET_SYSTEM_MENU_NAME_HASH = 1281499290L
private val getSystemMenuNameBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_system_menu_name", GET_SYSTEM_MENU_NAME_HASH)
}

private const val GET_SYSTEM_MENU_TEXT_HASH = 1281499290L
private val getSystemMenuTextBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_system_menu_text", GET_SYSTEM_MENU_TEXT_HASH)
}

private const val GET_POPUP_OPEN_CALLBACK_HASH = 3170603026L
private val getPopupOpenCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_popup_open_callback", GET_POPUP_OPEN_CALLBACK_HASH)
}

private const val GET_POPUP_CLOSE_CALLBACK_HASH = 3170603026L
private val getPopupCloseCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_popup_close_callback", GET_POPUP_CLOSE_CALLBACK_HASH)
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

private const val FIND_ITEM_INDEX_WITH_TAG_HASH = 1260085030L
private val findItemIndexWithTagBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "find_item_index_with_tag", FIND_ITEM_INDEX_WITH_TAG_HASH)
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

private const val GET_ITEM_TOOLTIP_HASH = 1464764419L
private val getItemTooltipBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
}

private const val SET_ITEM_TAG_HASH = 2706844827L
private val setItemTagBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "set_item_tag", SET_ITEM_TAG_HASH)
}
