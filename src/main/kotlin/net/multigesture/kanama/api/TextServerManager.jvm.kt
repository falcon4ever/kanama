package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TextServerManager (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextServerManager waits on: ptrcallNoArgsRetDictionaryList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns a list of available interfaces, with the index and name of each interface.
 *
 * Generated from Godot docs: TextServerManager.get_interfaces
 */
fun TextServerManager.getInterfaces(): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getInterfacesBind, textServerManagerSingleton)
}

private val textServerManagerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("TextServerManager")
}

private const val GET_INTERFACES_HASH = 3995934104L
private val getInterfacesBind by lazy {
    ObjectCalls.getMethodBind("TextServerManager", "get_interfaces", GET_INTERFACES_HASH)
}
