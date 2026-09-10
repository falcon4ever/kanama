package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for FileDialog (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FileDialog waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithIntArgRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns an array of values of the `OptionButton` with index `option`.
 *
 * Generated from Godot docs: FileDialog.get_option_values
 */
fun FileDialog.getOptionValues(option: Int): List<String> {
    return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getOptionValuesBind, handle, option)
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

private const val GET_OPTION_VALUES_HASH = 647634434L
private val getOptionValuesBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "get_option_values", GET_OPTION_VALUES_HASH)
}

private const val GET_SELECTED_OPTIONS_HASH = 3102165223L
private val getSelectedOptionsBind by lazy {
    ObjectCalls.getMethodBind("FileDialog", "get_selected_options", GET_SELECTED_OPTIONS_HASH)
}
