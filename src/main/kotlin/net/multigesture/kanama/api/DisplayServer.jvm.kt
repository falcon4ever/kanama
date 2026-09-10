package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for DisplayServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DisplayServer waits on: ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong,
//   ptrcallWithIntAndRect2iArg, ptrcallWithRect2iArgRetObject, ptrcallWithStringIntArgsRetCallable
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val GLOBAL_MENU_GET_ITEM_CALLBACK_HASH = 748666903L
private val globalMenuGetItemCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_callback", GLOBAL_MENU_GET_ITEM_CALLBACK_HASH)
}

private const val GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH = 748666903L
private val globalMenuGetItemKeyCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_key_callback", GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH)
}

private const val SCREEN_GET_IMAGE_RECT_HASH = 2601441065L
private val screenGetImageRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "screen_get_image_rect", SCREEN_GET_IMAGE_RECT_HASH)
}

private const val WINDOW_SET_POPUP_SAFE_RECT_HASH = 3317281434L
private val windowSetPopupSafeRectBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "window_set_popup_safe_rect", WINDOW_SET_POPUP_SAFE_RECT_HASH)
}

private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
private val fileDialogWithOptionsShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
}
