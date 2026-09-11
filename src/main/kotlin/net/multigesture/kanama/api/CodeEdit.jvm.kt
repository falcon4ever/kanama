package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CodeEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CodeEdit waits on: ptrcallWithTypedIntListArg, ptrcallWithTypedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Prefixes to trigger an automatic indent. Used when `indent_automatic` is set to `true`.
 *
 * Generated from Godot docs: CodeEdit.set_auto_indent_prefixes
 */
fun CodeEdit.setAutoIndentPrefixes(prefixes: List<String>) {
    ObjectCalls.ptrcallWithTypedStringListArg(setAutoIndentPrefixesBind, handle, prefixes)
}

/**
 * Sets the string delimiters. All existing string delimiters will be removed.
 *
 * Generated from Godot docs: CodeEdit.set_string_delimiters
 */
fun CodeEdit.setStringDelimiters(stringDelimiters: List<String>) {
    ObjectCalls.ptrcallWithTypedStringListArg(setStringDelimitersBind, handle, stringDelimiters)
}

/**
 * Sets the comment delimiters. All existing comment delimiters will be removed.
 *
 * Generated from Godot docs: CodeEdit.set_comment_delimiters
 */
fun CodeEdit.setCommentDelimiters(commentDelimiters: List<String>) {
    ObjectCalls.ptrcallWithTypedStringListArg(setCommentDelimitersBind, handle, commentDelimiters)
}

/**
 * Sets prefixes that will trigger code completion.
 *
 * Generated from Godot docs: CodeEdit.set_code_completion_prefixes
 */
fun CodeEdit.setCodeCompletionPrefixes(prefixes: List<String>) {
    ObjectCalls.ptrcallWithTypedStringListArg(setCodeCompletionPrefixesBind, handle, prefixes)
}

/**
 * Draws vertical lines at the provided columns. The first entry is considered a main hard
 * guideline and is drawn more prominently.
 *
 * Generated from Godot docs: CodeEdit.set_line_length_guidelines
 */
fun CodeEdit.setLineLengthGuidelines(guidelineColumns: List<Long>) {
    ObjectCalls.ptrcallWithTypedIntListArg(setLineLengthGuidelinesBind, handle, guidelineColumns)
}

private const val SET_AUTO_INDENT_PREFIXES_HASH = 381264803L
private val setAutoIndentPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_auto_indent_prefixes", SET_AUTO_INDENT_PREFIXES_HASH)
}

private const val SET_STRING_DELIMITERS_HASH = 381264803L
private val setStringDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_string_delimiters", SET_STRING_DELIMITERS_HASH)
}

private const val SET_COMMENT_DELIMITERS_HASH = 381264803L
private val setCommentDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_comment_delimiters", SET_COMMENT_DELIMITERS_HASH)
}

private const val SET_CODE_COMPLETION_PREFIXES_HASH = 381264803L
private val setCodeCompletionPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_code_completion_prefixes", SET_CODE_COMPLETION_PREFIXES_HASH)
}

private const val SET_LINE_LENGTH_GUIDELINES_HASH = 381264803L
private val setLineLengthGuidelinesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_line_length_guidelines", SET_LINE_LENGTH_GUIDELINES_HASH)
}
