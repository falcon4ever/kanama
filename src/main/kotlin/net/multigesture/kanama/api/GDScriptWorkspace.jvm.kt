package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GDScriptWorkspace (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GDScriptWorkspace waits on: ptrcallWithDictionaryArg,
//   ptrcallWithStringArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

fun GDScriptWorkspace.generateScriptApi(path: String): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringArgRetDictionary(generateScriptApiBind, handle, path)
}

fun GDScriptWorkspace.didDeleteFiles(params: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(didDeleteFilesBind, handle, params)
}

private const val GENERATE_SCRIPT_API_HASH = 2786125124L
private val generateScriptApiBind by lazy {
    ObjectCalls.getMethodBind("GDScriptWorkspace", "generate_script_api", GENERATE_SCRIPT_API_HASH)
}

private const val DIDDELETEFILES_HASH = 4155329257L
private val didDeleteFilesBind by lazy {
    ObjectCalls.getMethodBind("GDScriptWorkspace", "didDeleteFiles", DIDDELETEFILES_HASH)
}
