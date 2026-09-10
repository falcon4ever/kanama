package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for FileDialog (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FileDialog waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithIntAndPackedStringListArg, ptrcallWithIntArgRetPackedStringList,
//   ptrcallWithPackedStringListArg, ptrcallWithStringPackedStringListAndIntArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The available file type filters. Each filter string in the array should be formatted like this:
 * `*.png,*.jpg,*.jpeg;Image Files;image/png,image/jpeg`. The description text of the filter is
 * optional and can be omitted. Both file extensions and MIME type should be always set. Note:
 * Embedded file dialogs and Windows file dialogs support only file extensions, while Android,
 * Linux, and macOS file dialogs also support MIME types.
 *
 * Generated from Godot docs: FileDialog.set_filters
 */
fun FileDialog.setFilters(filters: List<String>) {
    ObjectCalls.ptrcallWithPackedStringListArg(setFiltersBind, handle, filters)
}

/**
 * Returns an array of values of the `OptionButton` with index `option`.
 *
 * Generated from Godot docs: FileDialog.get_option_values
 */
fun FileDialog.getOptionValues(option: Int): List<String> {
    return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getOptionValuesBind, handle, option)
}

/**
 * Sets the option values of the `OptionButton` with index `option`.
 *
 * Generated from Godot docs: FileDialog.set_option_values
 */
fun FileDialog.setOptionValues(option: Int, values: List<String>) {
    ObjectCalls.ptrcallWithIntAndPackedStringListArg(setOptionValuesBind, handle, option, values)
}

/**
 * Adds an additional `OptionButton` to the file dialog. If `values` is empty, a `CheckBox` is
 * added instead. `default_value_index` should be an index of the value in the `values`. If
 * `values` is empty it should be either `1` (checked), or `0` (unchecked).
 *
 * Generated from Godot docs: FileDialog.add_option
 */
fun FileDialog.addOption(name: String, values: List<String>, defaultValueIndex: Int) {
    ObjectCalls.ptrcallWithStringPackedStringListAndIntArgs(addOptionBind, handle, name, values, defaultValueIndex)
}

/**
 * Returns a `Dictionary` with the selected values of the additional `OptionButton`s and/or
 * `CheckBox`es. `Dictionary` keys are names and values are selected value indices.
 *
 * Generated from Godot docs: FileDialog.get_selected_options
 */
fun FileDialog.getSelectedOptions(): Map<String, Any?> {
    return ObjectCalls.ptrcallNoArgsRetDictionary(getSelectedOptionsBind, handle)
}

/**
 * Sets the list of favorite directories, which is shared by all `FileDialog` nodes. Useful to
 * restore the list of favorites saved with `get_favorite_list`. This method can be called only
 * from the main thread. Note: `FileDialog` will update its internal `ItemList` of favorites when
 * its visibility changes. Be sure to call this method earlier if you want your changes to have
 * effect.
 *
 * Generated from Godot docs: FileDialog.set_favorite_list
 */
fun FileDialog.Companion.setFavoriteList(favorites: List<String>) {
    ObjectCalls.ptrcallWithPackedStringListArg(setFavoriteListBind, MemorySegment.NULL, favorites)
}

/**
 * Sets the list of recent directories, which is shared by all `FileDialog` nodes. Useful to
 * restore the list of recents saved with `set_recent_list`. This method can be called only from
 * the main thread. Note: `FileDialog` will update its internal `ItemList` of recent directories
 * when its visibility changes. Be sure to call this method earlier if you want your changes to
 * have effect.
 *
 * Generated from Godot docs: FileDialog.set_recent_list
 */
fun FileDialog.Companion.setRecentList(recents: List<String>) {
    ObjectCalls.ptrcallWithPackedStringListArg(setRecentListBind, MemorySegment.NULL, recents)
}

private const val SET_FILTERS_HASH = 4015028928L
private val setFiltersBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "set_filters", SET_FILTERS_HASH)
}

private const val GET_OPTION_VALUES_HASH = 647634434L
private val getOptionValuesBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "get_option_values", GET_OPTION_VALUES_HASH)
}

private const val SET_OPTION_VALUES_HASH = 3353661094L
private val setOptionValuesBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "set_option_values", SET_OPTION_VALUES_HASH)
}

private const val ADD_OPTION_HASH = 149592325L
private val addOptionBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "add_option", ADD_OPTION_HASH)
}

private const val GET_SELECTED_OPTIONS_HASH = 3102165223L
private val getSelectedOptionsBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "get_selected_options", GET_SELECTED_OPTIONS_HASH)
}

private const val SET_FAVORITE_LIST_HASH = 4015028928L
private val setFavoriteListBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "set_favorite_list", SET_FAVORITE_LIST_HASH)
}

private const val SET_RECENT_LIST_HASH = 4015028928L
private val setRecentListBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "set_recent_list", SET_RECENT_LIST_HASH)
}
