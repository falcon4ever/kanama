package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Version Control System (VCS) interface, which reads and writes to the local VCS in use.
 *
 * Generated from Godot docs: EditorVCSInterface
 */
class EditorVCSInterface(handle: MemorySegment) : GodotObject(handle) {
    fun createDiffLine(newLineNo: Int, oldLineNo: Int, content: String, status: String): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoIntTwoStringArgsRetDictionary(createDiffLineBind, handle, newLineNo, oldLineNo, content, status)
    }

    fun createDiffHunk(oldStart: Int, newStart: Int, oldLines: Int, newLines: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithFourIntArgsRetDictionary(createDiffHunkBind, handle, oldStart, newStart, oldLines, newLines)
    }

    fun createDiffFile(newFile: String, oldFile: String): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoStringArgsRetDictionary(createDiffFileBind, handle, newFile, oldFile)
    }

    fun createCommit(msg: String, author: String, id: String, unixTimestamp: Long, offsetMinutes: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithThreeStringTwoLongArgsRetDictionary(createCommitBind, handle, msg, author, id, unixTimestamp, offsetMinutes)
    }

    fun createStatusFile(filePath: String, changeType: Long, area: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringTwoLongArgsRetDictionary(createStatusFileBind, handle, filePath, changeType, area)
    }

    fun addDiffHunksIntoDiffFile(diffFile: Map<String, Any?>, diffHunks: List<Map<String, Any?>>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithDictionaryDictionaryListArgsRetDictionary(addDiffHunksIntoDiffFileBind, handle, diffFile, diffHunks)
    }

    fun addLineDiffsIntoDiffHunk(diffHunk: Map<String, Any?>, lineDiffs: List<Map<String, Any?>>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithDictionaryDictionaryListArgsRetDictionary(addLineDiffsIntoDiffHunkBind, handle, diffHunk, lineDiffs)
    }

    fun popupError(msg: String) {
        ObjectCalls.ptrcallWithStringArg(popupErrorBind, handle, msg)
    }

    companion object {
        const val CHANGE_TYPE_NEW: Long = 0L
        const val CHANGE_TYPE_MODIFIED: Long = 1L
        const val CHANGE_TYPE_RENAMED: Long = 2L
        const val CHANGE_TYPE_DELETED: Long = 3L
        const val CHANGE_TYPE_TYPECHANGE: Long = 4L
        const val CHANGE_TYPE_UNMERGED: Long = 5L
        const val TREE_AREA_COMMIT: Long = 0L
        const val TREE_AREA_STAGED: Long = 1L
        const val TREE_AREA_UNSTAGED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorVCSInterface? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorVCSInterface? =
            if (handle.address() == 0L) null else EditorVCSInterface(handle)

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

        private const val POPUP_ERROR_HASH = 83702148L
        private val popupErrorBind by lazy {
            ObjectCalls.getMethodBind("EditorVCSInterface", "popup_error", POPUP_ERROR_HASH)
        }
    }
}
