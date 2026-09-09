package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorExportPlugin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorExportPlugin waits on: ptrcallWithStringByteArrayBoolArgs,
//   ptrcallWithStringPackedStringListAndStringArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Adds a shared object or a directory containing only shared objects with the given `tags` and
 * destination `path`. Note: In case of macOS exports, those shared objects will be added to
 * `Frameworks` directory of app bundle. In case of a directory code-sign will error if you place
 * non code object in directory.
 *
 * Generated from Godot docs: EditorExportPlugin.add_shared_object
 */
fun EditorExportPlugin.addSharedObject(path: String, tags: List<String>, target: String) {
    checkOpen()
    ObjectCalls.ptrcallWithStringPackedStringListAndStringArgs(addSharedObjectBind, handle, path, tags, target)
}

/**
 * Adds a custom file to be exported. `path` is the virtual path that can be used to load the file,
 * `file` is the binary data of the file. When called inside `_export_file` and `remap` is `true`,
 * the current file will not be exported, but instead remapped to this custom file. `remap` is
 * ignored when called in other places. `file` will not be imported, so consider using
 * `_customize_resource` to remap imported resources.
 *
 * Generated from Godot docs: EditorExportPlugin.add_file
 */
fun EditorExportPlugin.addFile(path: String, file: ByteArray, remap: Boolean) {
    checkOpen()
    ObjectCalls.ptrcallWithStringByteArrayBoolArgs(addFileBind, handle, path, file, remap)
}

private const val ADD_SHARED_OBJECT_HASH = 3098291045L
private val addSharedObjectBind by lazy {
    ObjectCalls.getMethodBind("EditorExportPlugin", "add_shared_object", ADD_SHARED_OBJECT_HASH)
}

private const val ADD_FILE_HASH = 527928637L
private val addFileBind by lazy {
    ObjectCalls.getMethodBind("EditorExportPlugin", "add_file", ADD_FILE_HASH)
}
