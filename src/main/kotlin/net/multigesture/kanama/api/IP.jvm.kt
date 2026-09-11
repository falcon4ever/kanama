package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for IP (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP IP waits on: ptrcallWithStringAndLongArgRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Resolves a given hostname in a blocking way. Addresses are returned as an `Array` of IPv4 or
 * IPv6 addresses depending on `ip_type`.
 *
 * Generated from Godot docs: IP.resolve_hostname_addresses
 */
fun IP.resolveHostnameAddresses(host: String, ipType: Long = 3L): List<String> {
    return ObjectCalls.ptrcallWithStringAndLongArgRetPackedStringList(resolveHostnameAddressesBind, iPSingleton, host, ipType)
}

private val iPSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("IP")
}

private const val RESOLVE_HOSTNAME_ADDRESSES_HASH = 773767525L
private val resolveHostnameAddressesBind by lazy {
    ObjectCalls.getMethodBind("IP", "resolve_hostname_addresses", RESOLVE_HOSTNAME_ADDRESSES_HASH)
}
