package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for DisplayServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DisplayServer waits on: ptrcallNoArgsRetRect2List,
//   ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong,
//   ptrcallWithIntAndRect2iArg, ptrcallWithPackedVector2ListAndIntArgs, ptrcallWithRIDAndVariantArg,
//   ptrcallWithRect2iArgRetObject, ptrcallWithStringAndVariantArgRetInt,
//   ptrcallWithStringArgRetPackedStringList, ptrcallWithStringIntAndVariantArg,
//   ptrcallWithStringIntArgsRetCallable,
//   ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt,
//   ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong,
//   ptrcallWithTwoStringPackedStringListCallableArgsRetLong,
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
 * Returns a `PackedStringArray` of voice identifiers for the `language`. Note: This method is
 * implemented on Android, iOS, Web, Linux (X11/Wayland), macOS, and Windows.
 *
 * Generated from Godot docs: DisplayServer.tts_get_voices_for_language
 */
fun DisplayServer.ttsGetVoicesForLanguage(language: String): List<String> {
    return ObjectCalls.ptrcallWithStringArgRetPackedStringList(ttsGetVoicesForLanguageBind, displayServerSingleton, language)
}

/**
 * Returns an `Array` of `Rect2`, each of which is the bounding rectangle for a display cutout or
 * notch. These are non-functional areas on edge-to-edge screens used by cameras and sensors.
 * Returns an empty array if the device does not have cutouts. See also `get_display_safe_area`.
 * Note: Currently only implemented on Android. Other platforms will return an empty array even if
 * they do have display cutouts or notches.
 *
 * Generated from Godot docs: DisplayServer.get_display_cutouts
 */
fun DisplayServer.getDisplayCutouts(): List<Rect2> {
    return ObjectCalls.ptrcallNoArgsRetRect2List(getDisplayCutoutsBind, displayServerSingleton)
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
 * Sets a polygonal region of the window which accepts mouse events. Mouse events outside the
 * region will be passed through. Passing an empty array will disable passthrough support (all
 * mouse events will be intercepted by the window, which is the default behavior).
 *
 * Generated from Godot docs: DisplayServer.window_set_mouse_passthrough
 */
fun DisplayServer.windowSetMousePassthrough(region: List<Vector2>, windowId: Int = 0) {
    ObjectCalls.ptrcallWithPackedVector2ListAndIntArgs(windowSetMousePassthroughBind, displayServerSingleton, region, windowId)
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
 * Shows a text dialog which uses the operating system's native look-and-feel. `callback` should
 * accept a single `int` parameter which corresponds to the index of the pressed button. Note: This
 * method is implemented if the display server has the `FEATURE_NATIVE_DIALOG` feature. Supported
 * platforms include macOS, Windows, and Android.
 *
 * Generated from Godot docs: DisplayServer.dialog_show
 */
fun DisplayServer.dialogShow(title: String, description: String, buttons: List<String>, callback: GodotCallable): Long {
    return ObjectCalls.ptrcallWithTwoStringPackedStringListCallableArgsRetLong(dialogShowBind, displayServerSingleton, title, description, buttons, callback.target.handle, callback.method)
}

/**
 * Displays OS native dialog for selecting files or directories in the file system. Each filter
 * string in the `filters` array should be formatted like this: `*.png,*.jpg,*.jpeg;Image
 * Files;image/png,image/jpeg`. The description text of the filter is optional and can be omitted.
 * It is recommended to set both file extension and MIME type. See also `FileDialog.filters`.
 * Callbacks have the following arguments: `status: bool, selected_paths: PackedStringArray,
 * selected_filter_index: int`. On Android, the third callback argument (`selected_filter_index`)
 * is always `0`. Note: This method is implemented if the display server has the
 * `FEATURE_NATIVE_DIALOG_FILE` feature. Supported platforms include Linux (X11/Wayland), Windows,
 * macOS, and Android. Note: `current_directory` might be ignored. Note: Embedded file dialogs and
 * Windows file dialogs support only file extensions, while Android, Linux, and macOS file dialogs
 * also support MIME types. Note: On Android and Linux, `show_hidden` is ignored. Note: On Android
 * and macOS, native file dialogs have no title. Note: On macOS, sandboxed apps will save
 * security-scoped bookmarks to retain access to the opened folders across multiple sessions. Use
 * `OS.get_granted_permissions` to get a list of saved bookmarks. Note: On Android, this method
 * uses the Android Storage Access Framework (SAF). The file picker returns a URI instead of a
 * filesystem path. This URI can be passed directly to `FileAccess` to perform read/write
 * operations. When using `FILE_DIALOG_MODE_OPEN_DIR`, it returns a tree URI that grants full
 * access to the selected directory. File operations inside this directory can be performed by
 * passing a path on the form `treeUri#relative/path/to/file` to `FileAccess`. To avoid opening the
 * file picker again after each app restart, you can take persistable URI permission as follows:
 *
 * Generated from Godot docs: DisplayServer.file_dialog_show
 */
fun DisplayServer.fileDialogShow(title: String, currentDirectory: String, filename: String, showHidden: Boolean, mode: Long, filters: List<String>, callback: GodotCallable, parentWindowId: Int = 0): Long {
    return ObjectCalls.ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong(fileDialogShowBind, displayServerSingleton, title, currentDirectory, filename, showHidden, mode, filters, callback.target.handle, callback.method, parentWindowId)
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

private const val TTS_GET_VOICES_FOR_LANGUAGE_HASH = 4291131558L
private val ttsGetVoicesForLanguageBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "tts_get_voices_for_language", TTS_GET_VOICES_FOR_LANGUAGE_HASH)
}

private const val GET_DISPLAY_CUTOUTS_HASH = 3995934104L
private val getDisplayCutoutsBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "get_display_cutouts", GET_DISPLAY_CUTOUTS_HASH)
}

private const val SCREEN_GET_IMAGE_RECT_HASH = 2601441065L
private val screenGetImageRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "screen_get_image_rect", SCREEN_GET_IMAGE_RECT_HASH)
}

private const val WINDOW_SET_POPUP_SAFE_RECT_HASH = 3317281434L
private val windowSetPopupSafeRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "window_set_popup_safe_rect", WINDOW_SET_POPUP_SAFE_RECT_HASH)
}

private const val WINDOW_SET_MOUSE_PASSTHROUGH_HASH = 1993637420L
private val windowSetMousePassthroughBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "window_set_mouse_passthrough", WINDOW_SET_MOUSE_PASSTHROUGH_HASH)
}

private const val ACCESSIBILITY_ELEMENT_SET_META_HASH = 3175752987L
private val accessibilityElementSetMetaBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_set_meta", ACCESSIBILITY_ELEMENT_SET_META_HASH)
}

private const val DIALOG_SHOW_HASH = 4115553226L
private val dialogShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "dialog_show", DIALOG_SHOW_HASH)
}

private const val FILE_DIALOG_SHOW_HASH = 1386825884L
private val fileDialogShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "file_dialog_show", FILE_DIALOG_SHOW_HASH)
}

private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
private val fileDialogWithOptionsShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
}
