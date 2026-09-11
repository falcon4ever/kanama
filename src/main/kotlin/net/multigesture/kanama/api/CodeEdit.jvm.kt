package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// GENERATED desktop/Android companion for CodeEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CodeEdit waits on: ptrcallWithDictionaryArg,
//   ptrcallWithLongTwoStringColorObjectVariantIntArgs
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val SET_AUTO_BRACE_COMPLETION_PAIRS_HASH = 4155329257L
private val setAutoBraceCompletionPairsBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "set_auto_brace_completion_pairs", SET_AUTO_BRACE_COMPLETION_PAIRS_HASH)
}

private const val ADD_CODE_COMPLETION_OPTION_HASH = 3944379502L
private val addCodeCompletionOptionBind by lazy {
    ObjectCalls.getMethodBind("CodeEdit", "add_code_completion_option", ADD_CODE_COMPLETION_OPTION_HASH)
}
