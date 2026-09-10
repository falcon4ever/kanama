package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for DisplayServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DisplayServer waits on: ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong,
//   ptrcallWithIntAndRect2iArg, ptrcallWithRIDAndVariantArg, ptrcallWithRect2iArgRetObject,
//   ptrcallWithStringAndVariantArgRetInt, ptrcallWithStringIntAndVariantArg,
//   ptrcallWithStringIntArgsRetCallable,
//   ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt,
//   ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt,
//   ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds a new item with text `label` to the global menu with ID `menu_root`. Returns index of the
 * inserted item, it's not guaranteed to be the same as `index` value. An `accelerator` can
 * optionally be defined, which is a keyboard shortcut that can be pressed to trigger the menu
 * button even if it's not currently open. The `accelerator` is generally a combination of
 * `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A` (Ctrl + A). Note:
 * The `callback` and `key_callback` Callables need to accept exactly one Variant parameter, the
 * parameter passed to the Callables will be the value passed to `tag`. Note: This method is
 * implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_item
 */
fun DisplayServer.globalMenuAddItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddItemBind, displayServerSingleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new checkable item with text `label` to the global menu with ID `menu_root`. Returns
 * index of the inserted item, it's not guaranteed to be the same as `index` value. An
 * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
 * trigger the menu button even if it's not currently open. The `accelerator` is generally a
 * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
 * (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
 * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
 * method is implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_check_item
 */
fun DisplayServer.globalMenuAddCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddCheckItemBind, displayServerSingleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new item with text `label` and icon `icon` to the global menu with ID `menu_root`.
 * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. An
 * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
 * trigger the menu button even if it's not currently open. The `accelerator` is generally a
 * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
 * (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly one Variant
 * parameter, the parameter passed to the Callables will be the value passed to `tag`. Note: This
 * method is implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_icon_item
 */
fun DisplayServer.globalMenuAddIconItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconItemBind, displayServerSingleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new checkable item with text `label` and icon `icon` to the global menu with ID
 * `menu_root`. Returns index of the inserted item, it's not guaranteed to be the same as `index`
 * value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can be
 * pressed to trigger the menu button even if it's not currently open. The `accelerator` is
 * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
 * | KEY_A` (Ctrl + A). Note: The `callback` and `key_callback` Callables need to accept exactly
 * one Variant parameter, the parameter passed to the Callables will be the value passed to `tag`.
 * Note: This method is implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_icon_check_item
 */
fun DisplayServer.globalMenuAddIconCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconCheckItemBind, displayServerSingleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new radio-checkable item with text `label` to the global menu with ID `menu_root`.
 * Returns index of the inserted item, it's not guaranteed to be the same as `index` value. An
 * `accelerator` can optionally be defined, which is a keyboard shortcut that can be pressed to
 * trigger the menu button even if it's not currently open. The `accelerator` is generally a
 * combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL | KEY_A`
 * (Ctrl + A). Note: Radio-checkable items just display a checkmark, but don't have any built-in
 * checking behavior and must be checked/unchecked manually. See `global_menu_set_item_checked` for
 * more info on how to control it. Note: The `callback` and `key_callback` Callables need to accept
 * exactly one Variant parameter, the parameter passed to the Callables will be the value passed to
 * `tag`. Note: This method is implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_radio_check_item
 */
fun DisplayServer.globalMenuAddRadioCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddRadioCheckItemBind, displayServerSingleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new radio-checkable item with text `label` and icon `icon` to the global menu with ID
 * `menu_root`. Returns index of the inserted item, it's not guaranteed to be the same as `index`
 * value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can be
 * pressed to trigger the menu button even if it's not currently open. The `accelerator` is
 * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
 * | KEY_A` (Ctrl + A). Note: Radio-checkable items just display a checkmark, but don't have any
 * built-in checking behavior and must be checked/unchecked manually. See
 * `global_menu_set_item_checked` for more info on how to control it. Note: The `callback` and
 * `key_callback` Callables need to accept exactly one Variant parameter, the parameter passed to
 * the Callables will be the value passed to `tag`. Note: This method is implemented only on macOS.
 * Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_icon_radio_check_item
 */
fun DisplayServer.globalMenuAddIconRadioCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconRadioCheckItemBind, displayServerSingleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Adds a new item with text `label` to the global menu with ID `menu_root`. Contrarily to normal
 * binary items, multistate items can have more than two states, as defined by `max_states`. Each
 * press or activate of the item will increase the state by one. The default value is defined by
 * `default_state`. Returns index of the inserted item, it's not guaranteed to be the same as
 * `index` value. An `accelerator` can optionally be defined, which is a keyboard shortcut that can
 * be pressed to trigger the menu button even if it's not currently open. The `accelerator` is
 * generally a combination of `KeyModifierMask`s and `Key`s using bitwise OR such as `KEY_MASK_CTRL
 * | KEY_A` (Ctrl + A). Note: By default, there's no indication of the current item state, it
 * should be changed manually. Note: The `callback` and `key_callback` Callables need to accept
 * exactly one Variant parameter, the parameter passed to the Callables will be the value passed to
 * `tag`. Note: This method is implemented only on macOS. Supported system menu IDs:
 *
 * Generated from Godot docs: DisplayServer.global_menu_add_multistate_item
 */
fun DisplayServer.globalMenuAddMultistateItem(menuRoot: String, label: String, maxStates: Int, defaultState: Int, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
    return ObjectCalls.ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt(globalMenuAddMultistateItemBind, displayServerSingleton, menuRoot, label, maxStates, defaultState, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
}

/**
 * Returns the index of the item with the specified `tag`. Indices are automatically assigned to
 * each item by the engine, and cannot be set manually. Note: This method is implemented only on
 * macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_get_item_index_from_tag
 */
fun DisplayServer.globalMenuGetItemIndexFromTag(menuRoot: String, tag: Any?): Int {
    return ObjectCalls.ptrcallWithStringAndVariantArgRetInt(globalMenuGetItemIndexFromTagBind, displayServerSingleton, menuRoot, tag)
}

/**
 * Returns the callback of the item at index `idx`. Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_get_item_callback
 */
fun DisplayServer.globalMenuGetItemCallback(menuRoot: String, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemCallbackBind, displayServerSingleton, menuRoot, idx)
}

/**
 * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
 * only on macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_get_item_key_callback
 */
fun DisplayServer.globalMenuGetItemKeyCallback(menuRoot: String, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemKeyCallbackBind, displayServerSingleton, menuRoot, idx)
}

/**
 * Sets the metadata of an item, which may be of any type. You can later get it with
 * `global_menu_get_item_tag`, which provides a simple way of assigning context data to items.
 * Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_set_item_tag
 */
fun DisplayServer.globalMenuSetItemTag(menuRoot: String, idx: Int, tag: Any?) {
    ObjectCalls.ptrcallWithStringIntAndVariantArg(globalMenuSetItemTagBind, displayServerSingleton, menuRoot, idx, tag)
}

/**
 * Returns a screenshot of the screen region defined by `rect`. Returns `null` if `rect` is outside
 * screen bounds or the `DisplayServer` fails to capture screenshot. Note: This method is
 * implemented on macOS and Windows. On other platforms, this method always returns `null`. Note:
 * On macOS, this method requires the "Screen Recording" permission. If permission is not granted,
 * this method returns a screenshot that will not include other application windows or OS elements
 * not related to the application.
 *
 * Generated from Godot docs: DisplayServer.screen_get_image_rect
 */
fun DisplayServer.screenGetImageRect(rect: Rect2i): Image? {
    return Image.wrap(ObjectCalls.ptrcallWithRect2iArgRetObject(screenGetImageRectBind, displayServerSingleton, rect))
}

/**
 * Sets the bounding box of control, or menu item that was used to open the popup window, in the
 * screen coordinate system. Clicking this area will not auto-close this popup.
 *
 * Generated from Godot docs: DisplayServer.window_set_popup_safe_rect
 */
fun DisplayServer.windowSetPopupSafeRect(window: Int, rect: Rect2i) {
    ObjectCalls.ptrcallWithIntAndRect2iArg(windowSetPopupSafeRectBind, displayServerSingleton, window, rect)
}

/**
 * Sets the metadata of the accessibility element `id` to `meta`.
 *
 * Generated from Godot docs: DisplayServer.accessibility_element_set_meta
 */
fun DisplayServer.accessibilityElementSetMeta(id: RID, meta: Any?) {
    ObjectCalls.ptrcallWithRIDAndVariantArg(accessibilityElementSetMetaBind, displayServerSingleton, id, meta)
}

/**
 * Displays OS native dialog for selecting files or directories in the file system with additional
 * user selectable options. Each filter string in the `filters` array should be formatted like
 * this: `*.png,*.jpg,*.jpeg;Image Files;image/png,image/jpeg`. The description text of the filter
 * is optional and can be omitted. It is recommended to set both file extension and MIME type. See
 * also `FileDialog.filters`. `options` is array of `Dictionary`s with the following keys: -
 * `"name"` - option's name `String`. - `"values"` - `PackedStringArray` of values. If empty,
 * boolean option (check box) is used. - `"default"` - default selected option index (`int`) or
 * default boolean value (`bool`). Callbacks have the following arguments: `status: bool,
 * selected_paths: PackedStringArray, selected_filter_index: int, selected_option: Dictionary`.
 * Note: This method is implemented if the display server has the
 * `FEATURE_NATIVE_DIALOG_FILE_EXTRA` feature. Supported platforms include Linux (X11/Wayland),
 * Windows, and macOS. Note: `current_directory` might be ignored. Note: Embedded file dialogs and
 * Windows file dialogs support only file extensions, while Android, Linux, and macOS file dialogs
 * also support MIME types. Note: On Linux (X11), `show_hidden` is ignored. Note: On macOS, native
 * file dialogs have no title. Note: On macOS, sandboxed apps will save security-scoped bookmarks
 * to retain access to the opened folders across multiple sessions. Use
 * `OS.get_granted_permissions` to get a list of saved bookmarks.
 *
 * Generated from Godot docs: DisplayServer.file_dialog_with_options_show
 */
fun DisplayServer.fileDialogWithOptionsShow(title: String, currentDirectory: String, root: String, filename: String, showHidden: Boolean, mode: Long, filters: List<String>, options: List<Map<String, Any?>>, callback: GodotCallable, parentWindowId: Int = 0): Long {
    return ObjectCalls.ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong(fileDialogWithOptionsShowBind, displayServerSingleton, title, currentDirectory, root, filename, showHidden, mode, filters, options, callback.target.handle, callback.method, parentWindowId)
}

private val displayServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("DisplayServer")
}

private const val GLOBAL_MENU_ADD_ITEM_HASH = 3616842746L
private val globalMenuAddItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_item", GLOBAL_MENU_ADD_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_CHECK_ITEM_HASH = 3616842746L
private val globalMenuAddCheckItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_check_item", GLOBAL_MENU_ADD_CHECK_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_ICON_ITEM_HASH = 3867083847L
private val globalMenuAddIconItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_item", GLOBAL_MENU_ADD_ICON_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH = 3867083847L
private val globalMenuAddIconCheckItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_check_item", GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH = 3616842746L
private val globalMenuAddRadioCheckItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_radio_check_item", GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH = 3867083847L
private val globalMenuAddIconRadioCheckItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_radio_check_item", GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH)
}

private const val GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH = 3297554655L
private val globalMenuAddMultistateItemBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_multistate_item", GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH)
}

private const val GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH = 2941063483L
private val globalMenuGetItemIndexFromTagBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_index_from_tag", GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH)
}

private const val GLOBAL_MENU_GET_ITEM_CALLBACK_HASH = 748666903L
private val globalMenuGetItemCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_callback", GLOBAL_MENU_GET_ITEM_CALLBACK_HASH)
}

private const val GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH = 748666903L
private val globalMenuGetItemKeyCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_key_callback", GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH)
}

private const val GLOBAL_MENU_SET_ITEM_TAG_HASH = 453659863L
private val globalMenuSetItemTagBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_tag", GLOBAL_MENU_SET_ITEM_TAG_HASH)
}

private const val SCREEN_GET_IMAGE_RECT_HASH = 2601441065L
private val screenGetImageRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "screen_get_image_rect", SCREEN_GET_IMAGE_RECT_HASH)
}

private const val WINDOW_SET_POPUP_SAFE_RECT_HASH = 3317281434L
private val windowSetPopupSafeRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "window_set_popup_safe_rect", WINDOW_SET_POPUP_SAFE_RECT_HASH)
}

private const val ACCESSIBILITY_ELEMENT_SET_META_HASH = 3175752987L
private val accessibilityElementSetMetaBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_set_meta", ACCESSIBILITY_ELEMENT_SET_META_HASH)
}

private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
private val fileDialogWithOptionsShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
}
