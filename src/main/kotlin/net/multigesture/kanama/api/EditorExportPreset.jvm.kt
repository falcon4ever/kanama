package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorExportPreset (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorExportPreset waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithStringNameAndBoolArgRetString, ptrcallWithStringNameAndStringArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a dictionary of files selected in the "Resources" tab of the export dialog. The
 * dictionary's keys are file paths, and its values are the corresponding export modes: `"strip"`,
 * `"keep"`, or `"remove"`. See also `get_file_export_mode`.
 *
 * Generated from Godot docs: EditorExportPreset.get_customized_files
 */
fun EditorExportPreset.getCustomizedFiles(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getCustomizedFilesBind, handle)
}

/**
 * Returns export option value or value of environment variable if it is set.
 *
 * Generated from Godot docs: EditorExportPreset.get_or_env
 */
fun EditorExportPreset.getOrEnv(name: String, envVar: String): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithStringNameAndStringArgRetVariantScalar(getOrEnvBind, handle, name, envVar)
}

/**
 * Returns the preset's version number, or fall back to the
 * `ProjectSettings.application/config/version` project setting if set to an empty string. If
 * `windows_version` is `true`, formats the returned version number to be compatible with Windows
 * executable metadata.
 *
 * Generated from Godot docs: EditorExportPreset.get_version
 */
fun EditorExportPreset.getVersion(name: String, windowsVersion: Boolean): String {
    checkOpen()
    return ObjectCalls.ptrcallWithStringNameAndBoolArgRetString(getVersionBind, handle, name, windowsVersion)
}

private const val GET_CUSTOMIZED_FILES_HASH = 3102165223L
private val getCustomizedFilesBind by lazy {
    ObjectCalls.getMethodBind("EditorExportPreset", "get_customized_files", GET_CUSTOMIZED_FILES_HASH)
}

private const val GET_OR_ENV_HASH = 389838787L
private val getOrEnvBind by lazy {
    ObjectCalls.getMethodBind("EditorExportPreset", "get_or_env", GET_OR_ENV_HASH)
}

private const val GET_VERSION_HASH = 1132184663L
private val getVersionBind by lazy {
    ObjectCalls.getMethodBind("EditorExportPreset", "get_version", GET_VERSION_HASH)
}
