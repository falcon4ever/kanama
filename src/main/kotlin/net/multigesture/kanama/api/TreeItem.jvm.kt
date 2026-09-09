package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TreeItem (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TreeItem waits on: ptrcallWithIntAndArrayArg, ptrcallWithIntAndVariantArg,
//   ptrcallWithIntArgRetArray, ptrcallWithIntArgRetCallable, ptrcallWithIntArgRetDictionary,
//   ptrcallWithIntArgRetString, ptrcallWithIntArgRetVariantScalar, ptrcallWithTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the given column's text.
 *
 * Generated from Godot docs: TreeItem.get_text
 */
fun TreeItem.getText(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getTextBind, handle, column)
}

/**
 * Returns the given column's description for assistive apps.
 *
 * Generated from Godot docs: TreeItem.get_description
 */
fun TreeItem.getDescription(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getDescriptionBind, handle, column)
}

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
 * Returns item's text language code.
 *
 * Generated from Godot docs: TreeItem.get_language
 */
fun TreeItem.getLanguage(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getLanguageBind, handle, column)
}

/**
 * Gets the suffix string shown after the column value.
 *
 * Generated from Godot docs: TreeItem.get_suffix
 */
fun TreeItem.getSuffix(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getSuffixBind, handle, column)
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
 * Returns the metadata value that was set for the given column using `set_metadata`.
 *
 * Generated from Godot docs: TreeItem.get_metadata
 */
fun TreeItem.getMetadata(column: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getMetadataBind, handle, column)
}

/**
 * Returns the custom callback of column `column`.
 *
 * Generated from Godot docs: TreeItem.get_custom_draw_callback
 */
fun TreeItem.getCustomDrawCallback(column: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithIntArgRetCallable(getCustomDrawCallbackBind, handle, column)
}

/**
 * Returns the tooltip text for the button at index `button_index` in column `column`.
 *
 * Generated from Godot docs: TreeItem.get_button_tooltip_text
 */
fun TreeItem.getButtonTooltipText(column: Int, buttonIndex: Int): String {
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getButtonTooltipTextBind, handle, column, buttonIndex)
}

/**
 * Returns the given column's tooltip text.
 *
 * Generated from Godot docs: TreeItem.get_tooltip_text
 */
fun TreeItem.getTooltipText(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getTooltipTextBind, handle, column)
}

private const val GET_TEXT_HASH = 844755477L
private val getTextBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_text", GET_TEXT_HASH)
}

private const val GET_DESCRIPTION_HASH = 844755477L
private val getDescriptionBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_description", GET_DESCRIPTION_HASH)
}

private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 537221740L
private val setStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 663333327L
private val getStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val GET_LANGUAGE_HASH = 844755477L
private val getLanguageBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_language", GET_LANGUAGE_HASH)
}

private const val GET_SUFFIX_HASH = 844755477L
private val getSuffixBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_suffix", GET_SUFFIX_HASH)
}

private const val GET_RANGE_CONFIG_HASH = 3554694381L
private val getRangeConfigBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_range_config", GET_RANGE_CONFIG_HASH)
}

private const val SET_METADATA_HASH = 2152698145L
private val setMetadataBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "set_metadata", SET_METADATA_HASH)
}

private const val GET_METADATA_HASH = 4227898402L
private val getMetadataBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_metadata", GET_METADATA_HASH)
}

private const val GET_CUSTOM_DRAW_CALLBACK_HASH = 1317077508L
private val getCustomDrawCallbackBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_custom_draw_callback", GET_CUSTOM_DRAW_CALLBACK_HASH)
}

private const val GET_BUTTON_TOOLTIP_TEXT_HASH = 1391810591L
private val getButtonTooltipTextBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_button_tooltip_text", GET_BUTTON_TOOLTIP_TEXT_HASH)
}

private const val GET_TOOLTIP_TEXT_HASH = 844755477L
private val getTooltipTextBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_tooltip_text", GET_TOOLTIP_TEXT_HASH)
}
