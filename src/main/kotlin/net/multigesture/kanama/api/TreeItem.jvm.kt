package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TreeItem (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TreeItem waits on: ptrcallWithIntAndArrayArg, ptrcallWithIntAndVariantArg,
//   ptrcallWithIntArgRetArray, ptrcallWithIntArgRetCallable, ptrcallWithIntArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Set additional options for BiDi override. Has effect for cells that display text.
 *
 * Generated from Godot docs: TreeItem.set_structured_text_bidi_override_options
 */
fun TreeItem.setStructuredTextBidiOverrideOptions(column: Int, args: List<Any?>) {
    ObjectCalls.ptrcallWithIntAndArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, column, args)
}

/**
 * Returns the additional BiDi options set for this cell.
 *
 * Generated from Godot docs: TreeItem.get_structured_text_bidi_override_options
 */
fun TreeItem.getStructuredTextBidiOverrideOptions(column: Int): List<Any?> {
    return ObjectCalls.ptrcallWithIntArgRetArray(getStructuredTextBidiOverrideOptionsBind, handle, column)
}

/**
 * Returns a dictionary containing the range parameters for a given column. The keys are "min",
 * "max", "step", and "expr".
 *
 * Generated from Godot docs: TreeItem.get_range_config
 */
fun TreeItem.getRangeConfig(column: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getRangeConfigBind, handle, column)
}

/**
 * Sets the metadata value for the given column, which can be retrieved later using `get_metadata`.
 * This can be used, for example, to store a reference to the original data.
 *
 * Generated from Godot docs: TreeItem.set_metadata
 */
fun TreeItem.setMetadata(column: Int, meta: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setMetadataBind, handle, column, meta)
}

/**
 * Returns the custom callback of column `column`.
 *
 * Generated from Godot docs: TreeItem.get_custom_draw_callback
 */
fun TreeItem.getCustomDrawCallback(column: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithIntArgRetCallable(getCustomDrawCallbackBind, handle, column)
}

private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 537221740L
private val setStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 663333327L
private val getStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val GET_RANGE_CONFIG_HASH = 3554694381L
private val getRangeConfigBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_range_config", GET_RANGE_CONFIG_HASH)
}

private const val SET_METADATA_HASH = 2152698145L
private val setMetadataBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "set_metadata", SET_METADATA_HASH)
}

private const val GET_CUSTOM_DRAW_CALLBACK_HASH = 1317077508L
private val getCustomDrawCallbackBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_custom_draw_callback", GET_CUSTOM_DRAW_CALLBACK_HASH)
}
