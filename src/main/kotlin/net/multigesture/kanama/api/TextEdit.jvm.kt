package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TextEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextEdit waits on: ptrcallWithArrayArg, ptrcallWithBoolArgRetPackedInt32List,
//   ptrcallWithIntArgRetPackedStringList, ptrcallWithTwoBoolArgsRetVector2iList,
//   ptrcallWithTwoIntAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Set additional options for BiDi override.
 *
 * Generated from Godot docs: TextEdit.set_structured_text_bidi_override_options
 */
fun TextEdit.setStructuredTextBidiOverrideOptions(args: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
}

/**
 * Returns the carets sorted by selection beginning from lowest line and column to highest (from
 * top to bottom of text). If `include_ignored_carets` is `false`, carets from
 * `multicaret_edit_ignore_caret` will be ignored.
 *
 * Generated from Godot docs: TextEdit.get_sorted_carets
 */
fun TextEdit.getSortedCarets(includeIgnoredCarets: Boolean = false): List<Int> {
    return ObjectCalls.ptrcallWithBoolArgRetPackedInt32List(getSortedCaretsBind, handle, includeIgnoredCarets)
}

/**
 * Returns an `Array` of line ranges where `x` is the first line and `y` is the last line. All
 * lines within these ranges will have a caret on them or be part of a selection. Each line will
 * only be part of one line range, even if it has multiple carets on it. If a selection's end
 * column (`get_selection_to_column`) is at column `0`, that line will not be included. If a
 * selection begins on the line after another selection ends and `merge_adjacent` is `true`, or
 * they begin and end on the same line, one line range will include both selections.
 *
 * Generated from Godot docs: TextEdit.get_line_ranges_from_carets
 */
fun TextEdit.getLineRangesFromCarets(onlySelections: Boolean = false, mergeAdjacent: Boolean = true): List<Vector2i> {
    return ObjectCalls.ptrcallWithTwoBoolArgsRetVector2iList(getLineRangesFromCaretsBind, handle, onlySelections, mergeAdjacent)
}

/**
 * Returns an array of `String`s representing each wrapped index.
 *
 * Generated from Godot docs: TextEdit.get_line_wrapped_text
 */
fun TextEdit.getLineWrappedText(line: Int): List<String> {
    return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getLineWrappedTextBind, handle, line)
}

/**
 * Sets the metadata for `gutter` on `line` to `metadata`.
 *
 * Generated from Godot docs: TextEdit.set_line_gutter_metadata
 */
fun TextEdit.setLineGutterMetadata(line: Int, gutter: Int, metadata: Any?) {
    ObjectCalls.ptrcallWithTwoIntAndVariantArg(setLineGutterMetadataBind, handle, line, gutter, metadata)
}

private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
private val setStructuredTextBidiOverrideOptionsBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
}

private const val GET_SORTED_CARETS_HASH = 2131714034L
private val getSortedCaretsBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "get_sorted_carets", GET_SORTED_CARETS_HASH)
}

private const val GET_LINE_RANGES_FROM_CARETS_HASH = 2393089247L
private val getLineRangesFromCaretsBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "get_line_ranges_from_carets", GET_LINE_RANGES_FROM_CARETS_HASH)
}

private const val GET_LINE_WRAPPED_TEXT_HASH = 647634434L
private val getLineWrappedTextBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "get_line_wrapped_text", GET_LINE_WRAPPED_TEXT_HASH)
}

private const val SET_LINE_GUTTER_METADATA_HASH = 2060538656L
private val setLineGutterMetadataBind by lazy {
    ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_metadata", SET_LINE_GUTTER_METADATA_HASH)
}
