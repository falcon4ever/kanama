package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for EditorImportPlugin (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP EditorImportPlugin waits on: ptrcallWithStringDictionaryStringVariantArgsRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * This function can only be called during the `_import` callback and it allows manually importing
 * resources from it. This is useful when the imported file generates external resources that
 * require importing (as example, images). Custom parameters for the ".import" file can be passed
 * via the `custom_options`. Additionally, in cases where multiple importers can handle a file, the
 * `custom_importer` can be specified to force a specific one. This function performs a resource
 * import and returns immediately with a success or error code. `generator_parameters` defines
 * optional extra metadata which will be stored as `generator_parameters` in the `remap` section of
 * the `.import` file, for example to store a md5 hash of the source data.
 *
 * Generated from Godot docs: EditorImportPlugin.append_import_external_resource
 */
fun EditorImportPlugin.appendImportExternalResource(path: String, customOptions: Map<String, Any?> = emptyMap(), customImporter: String = "", generatorParameters: Any? = null): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithStringDictionaryStringVariantArgsRetLong(appendImportExternalResourceBind, handle, path, customOptions, customImporter, generatorParameters)
}

private const val APPEND_IMPORT_EXTERNAL_RESOURCE_HASH = 320493106L
private val appendImportExternalResourceBind by lazy {
    ObjectCalls.getMethodBind("EditorImportPlugin", "append_import_external_resource", APPEND_IMPORT_EXTERNAL_RESOURCE_HASH)
}
