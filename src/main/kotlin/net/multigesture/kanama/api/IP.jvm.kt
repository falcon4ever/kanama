package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for IP (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP IP waits on: ptrcallNoArgsRetDictionaryList, ptrcallWithIntArgRetArray
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns resolved addresses, or an empty array if an error happened or resolution didn't happen
 * yet (see `get_resolve_item_status`).
 *
 * Generated from Godot docs: IP.get_resolve_item_addresses
 */
fun IP.getResolveItemAddresses(id: Int): List<Any?> {
    return ObjectCalls.ptrcallWithIntArgRetArray(getResolveItemAddressesBind, iPSingleton, id)
}

/**
 * Returns all network adapters as an array. Each adapter is a dictionary of the form:
 *
 * Generated from Godot docs: IP.get_local_interfaces
 */
fun IP.getLocalInterfaces(): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getLocalInterfacesBind, iPSingleton)
}

private val iPSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("IP")
}

private const val GET_RESOLVE_ITEM_ADDRESSES_HASH = 663333327L
private val getResolveItemAddressesBind by lazy {
    ObjectCalls.getMethodBind("IP", "get_resolve_item_addresses", GET_RESOLVE_ITEM_ADDRESSES_HASH)
}

private const val GET_LOCAL_INTERFACES_HASH = 3995934104L
private val getLocalInterfacesBind by lazy {
    ObjectCalls.getMethodBind("IP", "get_local_interfaces", GET_LOCAL_INTERFACES_HASH)
}
