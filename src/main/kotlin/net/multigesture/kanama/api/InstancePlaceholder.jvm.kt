package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for InstancePlaceholder (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP InstancePlaceholder waits on: ptrcallWithBoolArgRetDictionary
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the list of properties that will be applied to the node when `create_instance` is
 * called. If `with_order` is `true`, a key named `.order` (note the leading period) is added to
 * the dictionary. This `.order` key is an `Array` of `String` property names specifying the order
 * in which properties will be applied (with index 0 being the first).
 *
 * Generated from Godot docs: InstancePlaceholder.get_stored_values
 */
fun InstancePlaceholder.getStoredValues(withOrder: Boolean = false): Map<String, Any?> {
    return ObjectCalls.ptrcallWithBoolArgRetDictionary(getStoredValuesBind, handle, withOrder)
}

private const val GET_STORED_VALUES_HASH = 2230153369L
private val getStoredValuesBind by lazy {
    ObjectCalls.getMethodBind("InstancePlaceholder", "get_stored_values", GET_STORED_VALUES_HASH)
}
