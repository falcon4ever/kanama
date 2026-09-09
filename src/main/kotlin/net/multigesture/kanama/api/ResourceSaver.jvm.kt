package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for ResourceSaver (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ResourceSaver waits on: ptrcallWithObjectArgRetPackedStringList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the list of extensions available for saving a resource of a given type.
 *
 * Generated from Godot docs: ResourceSaver.get_recognized_extensions
 */
fun ResourceSaver.getRecognizedExtensions(type: Resource): List<String> {
    return ObjectCalls.ptrcallWithObjectArgRetPackedStringList(getRecognizedExtensionsBind, resourceSaverSingleton, type.requireOpenHandle())
}

private val resourceSaverSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("ResourceSaver")
}

private const val GET_RECOGNIZED_EXTENSIONS_HASH = 4223597960L
private val getRecognizedExtensionsBind by lazy {
    ObjectCalls.getMethodBind("ResourceSaver", "get_recognized_extensions", GET_RECOGNIZED_EXTENSIONS_HASH)
}
