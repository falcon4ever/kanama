package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for DisplayServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DisplayServer waits on: ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
private val fileDialogWithOptionsShowBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
}
