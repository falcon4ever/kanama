package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorVCSInterface (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorVCSInterface waits on: ptrcallWithDictionaryDictionaryListArgsRetDictionary,
//   ptrcallWithFourIntArgsRetDictionary, ptrcallWithStringTwoLongArgsRetDictionary,
//   ptrcallWithThreeStringTwoLongArgsRetDictionary, ptrcallWithTwoIntTwoStringArgsRetDictionary,
//   ptrcallWithTwoStringArgsRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Helper function to create a `Dictionary` for storing a line diff. `new_line_no` is the line
 * number in the new file (can be `-1` if the line is deleted). `old_line_no` is the line number in
 * the old file (can be `-1` if the line is added). `content` is the diff text. `status` is a
 * single character string which stores the line origin.
 *
 * Generated from Godot docs: EditorVCSInterface.create_diff_line
 */
fun EditorVCSInterface.createDiffLine(newLineNo: Int, oldLineNo: Int, content: String, status: String): Map<String, Any?> {
    return ObjectCalls.ptrcallWithTwoIntTwoStringArgsRetDictionary(createDiffLineBind, handle, newLineNo, oldLineNo, content, status)
}

/**
 * Helper function to create a `Dictionary` for storing diff hunk data. `old_start` is the starting
 * line number in old file. `new_start` is the starting line number in new file. `old_lines` is the
 * number of lines in the old file. `new_lines` is the number of lines in the new file.
 *
 * Generated from Godot docs: EditorVCSInterface.create_diff_hunk
 */
fun EditorVCSInterface.createDiffHunk(oldStart: Int, newStart: Int, oldLines: Int, newLines: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithFourIntArgsRetDictionary(createDiffHunkBind, handle, oldStart, newStart, oldLines, newLines)
}

/**
 * Helper function to create a `Dictionary` for storing old and new diff file paths.
 *
 * Generated from Godot docs: EditorVCSInterface.create_diff_file
 */
fun EditorVCSInterface.createDiffFile(newFile: String, oldFile: String): Map<String, Any?> {
    return ObjectCalls.ptrcallWithTwoStringArgsRetDictionary(createDiffFileBind, handle, newFile, oldFile)
}

/**
 * Helper function to create a commit `Dictionary` item. `msg` is the commit message of the commit.
 * `author` is a single human-readable string containing all the author's details, e.g. the email
 * and name configured in the VCS. `id` is the identifier of the commit, in whichever format your
 * VCS may provide an identifier to commits. `unix_timestamp` is the UTC Unix timestamp of when the
 * commit was created. `offset_minutes` is the timezone offset in minutes, recorded from the system
 * timezone where the commit was created.
 *
 * Generated from Godot docs: EditorVCSInterface.create_commit
 */
fun EditorVCSInterface.createCommit(msg: String, author: String, id: String, unixTimestamp: Long, offsetMinutes: Long): Map<String, Any?> {
    return ObjectCalls.ptrcallWithThreeStringTwoLongArgsRetDictionary(createCommitBind, handle, msg, author, id, unixTimestamp, offsetMinutes)
}

/**
 * Helper function to create a `Dictionary` used by editor to read the status of a file.
 *
 * Generated from Godot docs: EditorVCSInterface.create_status_file
 */
fun EditorVCSInterface.createStatusFile(filePath: String, changeType: Long, area: Long): Map<String, Any?> {
    return ObjectCalls.ptrcallWithStringTwoLongArgsRetDictionary(createStatusFileBind, handle, filePath, changeType, area)
}

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

private const val CREATE_DIFF_LINE_HASH = 2901184053L
private val createDiffLineBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "create_diff_line", CREATE_DIFF_LINE_HASH)
}

private const val CREATE_DIFF_HUNK_HASH = 3784842090L
private val createDiffHunkBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "create_diff_hunk", CREATE_DIFF_HUNK_HASH)
}

private const val CREATE_DIFF_FILE_HASH = 2723227684L
private val createDiffFileBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "create_diff_file", CREATE_DIFF_FILE_HASH)
}

private const val CREATE_COMMIT_HASH = 1075983584L
private val createCommitBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "create_commit", CREATE_COMMIT_HASH)
}

private const val CREATE_STATUS_FILE_HASH = 1083471673L
private val createStatusFileBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "create_status_file", CREATE_STATUS_FILE_HASH)
}

private const val ADD_DIFF_HUNKS_INTO_DIFF_FILE_HASH = 4015243225L
private val addDiffHunksIntoDiffFileBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "add_diff_hunks_into_diff_file", ADD_DIFF_HUNKS_INTO_DIFF_FILE_HASH)
}

private const val ADD_LINE_DIFFS_INTO_DIFF_HUNK_HASH = 4015243225L
private val addLineDiffsIntoDiffHunkBind by lazy {
    ObjectCalls.getMethodBind("EditorVCSInterface", "add_line_diffs_into_diff_hunk", ADD_LINE_DIFFS_INTO_DIFF_HUNK_HASH)
}
