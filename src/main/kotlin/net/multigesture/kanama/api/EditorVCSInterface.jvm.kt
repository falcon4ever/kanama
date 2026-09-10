package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorVCSInterface (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorVCSInterface waits on: ptrcallWithDictionaryDictionaryListArgsRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Helper function to add an array of `diff_hunks` into a `diff_file`.
 *
 * Generated from Godot docs: EditorVCSInterface.add_diff_hunks_into_diff_file
 */
fun EditorVCSInterface.addDiffHunksIntoDiffFile(diffFile: Map<String, Any?>, diffHunks: List<Map<String, Any?>>): Map<String, Any?> {
    return ObjectCalls.ptrcallWithDictionaryDictionaryListArgsRetDictionary(addDiffHunksIntoDiffFileBind, handle, diffFile, diffHunks)
}

/**
 * Helper function to add an array of `line_diffs` into a `diff_hunk`.
 *
 * Generated from Godot docs: EditorVCSInterface.add_line_diffs_into_diff_hunk
 */
fun EditorVCSInterface.addLineDiffsIntoDiffHunk(diffHunk: Map<String, Any?>, lineDiffs: List<Map<String, Any?>>): Map<String, Any?> {
    return ObjectCalls.ptrcallWithDictionaryDictionaryListArgsRetDictionary(addLineDiffsIntoDiffHunkBind, handle, diffHunk, lineDiffs)
}

private const val ADD_DIFF_HUNKS_INTO_DIFF_FILE_HASH = 4015243225L
private val addDiffHunksIntoDiffFileBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "add_diff_hunks_into_diff_file", ADD_DIFF_HUNKS_INTO_DIFF_FILE_HASH)
}

private const val ADD_LINE_DIFFS_INTO_DIFF_HUNK_HASH = 4015243225L
private val addLineDiffsIntoDiffHunkBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "add_line_diffs_into_diff_hunk", ADD_LINE_DIFFS_INTO_DIFF_HUNK_HASH)
}
