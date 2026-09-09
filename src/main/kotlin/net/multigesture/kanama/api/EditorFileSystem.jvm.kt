package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorFileSystem (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorFileSystem waits on: ptrcallWithPackedStringListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Reimports a set of files. Call this if these files or their `.import` files were directly edited
 * by script or an external program. If the file type changed or the file was newly created, use
 * `update_file` or `scan`. Note: This function blocks until the import is finished. However, the
 * main loop iteration, including timers and `Node._process`, will occur during the import process
 * due to progress bar updates. Avoid calls to `reimport_files` or `scan` while an import is in
 * progress.
 *
 * Generated from Godot docs: EditorFileSystem.reimport_files
 */
fun EditorFileSystem.reimportFiles(files: List<String>) {
    ObjectCalls.ptrcallWithPackedStringListArg(reimportFilesBind, handle, files)
}

private const val REIMPORT_FILES_HASH = 4015028928L
private val reimportFilesBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystem", "reimport_files", REIMPORT_FILES_HASH)
}
