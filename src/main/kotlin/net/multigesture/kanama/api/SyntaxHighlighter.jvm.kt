package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SyntaxHighlighter (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SyntaxHighlighter waits on: ptrcallWithIntArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the syntax highlighting data for the line at index `line`. If the line is not cached,
 * calls `_get_line_syntax_highlighting` first to calculate the data. Each entry is a column number
 * containing a nested `Dictionary`. The column number denotes the start of a region, the region
 * will end if another region is found, or at the end of the line. The nested `Dictionary` contains
 * the data for that region. Currently only the key `"color"` is supported.
 *
 * Generated from Godot docs: SyntaxHighlighter.get_line_syntax_highlighting
 */
fun SyntaxHighlighter.getLineSyntaxHighlighting(line: Int): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getLineSyntaxHighlightingBind, handle, line)
}

private const val GET_LINE_SYNTAX_HIGHLIGHTING_HASH = 3554694381L
private val getLineSyntaxHighlightingBind by lazy {
    ObjectCalls.getMethodBind("SyntaxHighlighter", "get_line_syntax_highlighting", GET_LINE_SYNTAX_HIGHLIGHTING_HASH)
}
