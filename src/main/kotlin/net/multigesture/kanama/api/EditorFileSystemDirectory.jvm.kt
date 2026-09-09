package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorFileSystemDirectory (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorFileSystemDirectory waits on: ptrcallWithIntArgRetString,
//   ptrcallWithIntArgRetStringName
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the name of the file at index `idx`.
 *
 * Generated from Godot docs: EditorFileSystemDirectory.get_file
 */
fun EditorFileSystemDirectory.getFile(idx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getFileBind, handle, idx)
}

/**
 * Returns the path to the file at index `idx`.
 *
 * Generated from Godot docs: EditorFileSystemDirectory.get_file_path
 */
fun EditorFileSystemDirectory.getFilePath(idx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getFilePathBind, handle, idx)
}

/**
 * Returns the resource type of the file at index `idx`. This returns a string such as `"Resource"`
 * or `"GDScript"`, not a file extension such as `".gd"`.
 *
 * Generated from Godot docs: EditorFileSystemDirectory.get_file_type
 */
fun EditorFileSystemDirectory.getFileType(idx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetStringName(getFileTypeBind, handle, idx)
}

/**
 * Returns the name of the script class defined in the file at index `idx`. If the file doesn't
 * define a script class using the `class_name` syntax, this will return an empty string.
 *
 * Generated from Godot docs: EditorFileSystemDirectory.get_file_script_class_name
 */
fun EditorFileSystemDirectory.getFileScriptClassName(idx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getFileScriptClassNameBind, handle, idx)
}

/**
 * Returns the base class of the script class defined in the file at index `idx`. If the file
 * doesn't define a script class using the `class_name` syntax, this will return an empty string.
 *
 * Generated from Godot docs: EditorFileSystemDirectory.get_file_script_class_extends
 */
fun EditorFileSystemDirectory.getFileScriptClassExtends(idx: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getFileScriptClassExtendsBind, handle, idx)
}

private const val GET_FILE_HASH = 844755477L
private val getFileBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file", GET_FILE_HASH)
}

private const val GET_FILE_PATH_HASH = 844755477L
private val getFilePathBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_path", GET_FILE_PATH_HASH)
}

private const val GET_FILE_TYPE_HASH = 659327637L
private val getFileTypeBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_type", GET_FILE_TYPE_HASH)
}

private const val GET_FILE_SCRIPT_CLASS_NAME_HASH = 844755477L
private val getFileScriptClassNameBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_name", GET_FILE_SCRIPT_CLASS_NAME_HASH)
}

private const val GET_FILE_SCRIPT_CLASS_EXTENDS_HASH = 844755477L
private val getFileScriptClassExtendsBind by lazy {
    ObjectCalls.getMethodBind("EditorFileSystemDirectory", "get_file_script_class_extends", GET_FILE_SCRIPT_CLASS_EXTENDS_HASH)
}
