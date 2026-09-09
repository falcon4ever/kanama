package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorScenePostImportPlugin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorScenePostImportPlugin waits on: ptrcallWithLongStringVariantLongStringIntArgs,
//   ptrcallWithStringAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Add a specific import option (name and default value only). This function can only be called
 * from `_get_import_options` and `_get_internal_import_options`.
 *
 * Generated from Godot docs: EditorScenePostImportPlugin.add_import_option
 */
fun EditorScenePostImportPlugin.addImportOption(name: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringAndVariantArg(addImportOptionBind, handle, name, value)
}

/**
 * Add a specific import option. This function can only be called from `_get_import_options` and
 * `_get_internal_import_options`.
 *
 * Generated from Godot docs: EditorScenePostImportPlugin.add_import_option_advanced
 */
fun EditorScenePostImportPlugin.addImportOptionAdvanced(type: Long, name: String, defaultValue: Any?, hint: Long = 0L, hintString: String = "", usageFlags: Int = 6) {
    checkOpen()
    ObjectCalls.ptrcallWithLongStringVariantLongStringIntArgs(addImportOptionAdvancedBind, handle, type, name, defaultValue, hint, hintString, usageFlags)
}

private const val ADD_IMPORT_OPTION_HASH = 402577236L
private val addImportOptionBind by lazy {
    ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option", ADD_IMPORT_OPTION_HASH)
}

private const val ADD_IMPORT_OPTION_ADVANCED_HASH = 3674075649L
private val addImportOptionAdvancedBind by lazy {
    ObjectCalls.getMethodBind("EditorScenePostImportPlugin", "add_import_option_advanced", ADD_IMPORT_OPTION_ADVANCED_HASH)
}
