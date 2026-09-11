package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// GENERATED desktop/Android companion for CodeEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CodeEdit waits on: ptrcallNoArgsRetTypedStringList, ptrcallWithDictionaryArg,
//   ptrcallWithLongTwoStringColorObjectVariantIntArgs, ptrcallWithTypedIntListArg,
//   ptrcallWithTypedStringListArg
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
 * Prefixes to trigger an automatic indent. Used when `indent_automatic` is set to `true`.
 *
 * Generated from Godot docs: CodeEdit.get_auto_indent_prefixes
 */
fun CodeEdit.getAutoIndentPrefixes(): List<String> {
    return ObjectCalls.ptrcallNoArgsRetTypedStringList(getAutoIndentPrefixesBind, handle)
}

/**
 * Sets the brace pairs to be autocompleted. For each entry in the dictionary, the key is the
 * opening brace and the value is the closing brace that matches it. A brace is a `String` made of
 * symbols. See `auto_brace_completion_enabled` and `auto_brace_completion_highlight_matching`.
 *
 * Generated from Godot docs: CodeEdit.set_auto_brace_completion_pairs
 */
fun CodeEdit.setAutoBraceCompletionPairs(pairs: Map<String, Any?>) {
    ObjectCalls.ptrcallWithDictionaryArg(setAutoBraceCompletionPairsBind, handle, pairs)
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
 * Sets the string delimiters. All existing string delimiters will be removed.
 *
 * Generated from Godot docs: CodeEdit.get_string_delimiters
 */
fun CodeEdit.getStringDelimiters(): List<String> {
    return ObjectCalls.ptrcallNoArgsRetTypedStringList(getStringDelimitersBind, handle)
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
 * Sets the comment delimiters. All existing comment delimiters will be removed.
 *
 * Generated from Godot docs: CodeEdit.get_comment_delimiters
 */
fun CodeEdit.getCommentDelimiters(): List<String> {
    return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCommentDelimitersBind, handle)
}

/**
 * Submits an item to the queue of potential candidates for the autocomplete menu. Call
 * `update_code_completion_options` to update the list. `location` indicates location of the option
 * relative to the location of the code completion query. See `CodeEdit.CodeCompletionLocation` for
 * how to set this value. Note: This list will replace all current candidates.
 *
 * Generated from Godot docs: CodeEdit.add_code_completion_option
 */
fun CodeEdit.addCodeCompletionOption(type: Long, displayText: String, insertText: String, textColor: Color, icon: Resource?, value: Any? = null, location: Int = 1024) {
    ObjectCalls.ptrcallWithLongTwoStringColorObjectVariantIntArgs(addCodeCompletionOptionBind, handle, type, displayText, insertText, textColor, icon?.requireOpenHandle() ?: MemorySegment.NULL, value, location)
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
 * Sets prefixes that will trigger code completion.
 *
 * Generated from Godot docs: CodeEdit.get_code_completion_prefixes
 */
fun CodeEdit.getCodeCompletionPrefixes(): List<String> {
    return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCodeCompletionPrefixesBind, handle)
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

var CodeEdit.delimiterStrings: List<String>
    @JvmName("delimiterStringsProperty")
    get() = getStringDelimiters()
    @JvmName("setDelimiterStringsProperty")
    set(value) = setStringDelimiters(value)

var CodeEdit.delimiterComments: List<String>
    @JvmName("delimiterCommentsProperty")
    get() = getCommentDelimiters()
    @JvmName("setDelimiterCommentsProperty")
    set(value) = setCommentDelimiters(value)

var CodeEdit.codeCompletionPrefixes: List<String>
    @JvmName("codeCompletionPrefixesProperty")
    get() = getCodeCompletionPrefixes()
    @JvmName("setCodeCompletionPrefixesProperty")
    set(value) = setCodeCompletionPrefixes(value)

var CodeEdit.indentAutomaticPrefixes: List<String>
    @JvmName("indentAutomaticPrefixesProperty")
    get() = getAutoIndentPrefixes()
    @JvmName("setIndentAutomaticPrefixesProperty")
    set(value) = setAutoIndentPrefixes(value)

private const val SET_AUTO_INDENT_PREFIXES_HASH = 381264803L
private val setAutoIndentPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_auto_indent_prefixes", SET_AUTO_INDENT_PREFIXES_HASH)
}

private const val GET_AUTO_INDENT_PREFIXES_HASH = 3995934104L
private val getAutoIndentPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "get_auto_indent_prefixes", GET_AUTO_INDENT_PREFIXES_HASH)
}

private const val SET_AUTO_BRACE_COMPLETION_PAIRS_HASH = 4155329257L
private val setAutoBraceCompletionPairsBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_auto_brace_completion_pairs", SET_AUTO_BRACE_COMPLETION_PAIRS_HASH)
}

private const val SET_STRING_DELIMITERS_HASH = 381264803L
private val setStringDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_string_delimiters", SET_STRING_DELIMITERS_HASH)
}

private const val GET_STRING_DELIMITERS_HASH = 3995934104L
private val getStringDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "get_string_delimiters", GET_STRING_DELIMITERS_HASH)
}

private const val SET_COMMENT_DELIMITERS_HASH = 381264803L
private val setCommentDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_comment_delimiters", SET_COMMENT_DELIMITERS_HASH)
}

private const val GET_COMMENT_DELIMITERS_HASH = 3995934104L
private val getCommentDelimitersBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "get_comment_delimiters", GET_COMMENT_DELIMITERS_HASH)
}

private const val ADD_CODE_COMPLETION_OPTION_HASH = 3944379502L
private val addCodeCompletionOptionBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "add_code_completion_option", ADD_CODE_COMPLETION_OPTION_HASH)
}

private const val SET_CODE_COMPLETION_PREFIXES_HASH = 381264803L
private val setCodeCompletionPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_code_completion_prefixes", SET_CODE_COMPLETION_PREFIXES_HASH)
}

private const val GET_CODE_COMPLETION_PREFIXES_HASH = 3995934104L
private val getCodeCompletionPrefixesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "get_code_completion_prefixes", GET_CODE_COMPLETION_PREFIXES_HASH)
}

private const val SET_LINE_LENGTH_GUIDELINES_HASH = 381264803L
private val setLineLengthGuidelinesBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_line_length_guidelines", SET_LINE_LENGTH_GUIDELINES_HASH)
}
