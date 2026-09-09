package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GDExtensionManager (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GDExtensionManager waits on: ptrcallWithStringConstGDExtensionInitializationFunctionPtrArgsRetLong
// Index: docs/contributing/ios-shape-gap.md

/**
 * Loads the extension already in address space via the given path and initialization function. The
 * `path` needs to be unique and start with `"libgodot://"`. Returns `LOAD_STATUS_OK` if
 * successful.
 *
 * Generated from Godot docs: GDExtensionManager.load_extension_from_function
 */
fun GDExtensionManager.loadExtensionFromFunction(path: String, initFunc: MemorySegment): Long {
    return ObjectCalls.ptrcallWithStringConstGDExtensionInitializationFunctionPtrArgsRetLong(loadExtensionFromFunctionBind, gDExtensionManagerSingleton, path, initFunc)
}

private val gDExtensionManagerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("GDExtensionManager")
}

private const val LOAD_EXTENSION_FROM_FUNCTION_HASH = 1565094761L
private val loadExtensionFromFunctionBind by lazy {
    ObjectCalls.getMethodBind("GDExtensionManager", "load_extension_from_function", LOAD_EXTENSION_FROM_FUNCTION_HASH)
}
