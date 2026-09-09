package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for XRServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRServer waits on: ptrcallNoArgsRetDictionaryList, ptrcallWithIntArgRetDictionary
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns a list of available interfaces the ID and name of each interface.
 *
 * Generated from Godot docs: XRServer.get_interfaces
 */
fun XRServer.getInterfaces(): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallNoArgsRetDictionaryList(getInterfacesBind, xRServerSingleton)
}

/**
 * Returns a dictionary of trackers for `tracker_types`.
 *
 * Generated from Godot docs: XRServer.get_trackers
 */
fun XRServer.getTrackers(trackerTypes: Int): Map<String, Any?> {
    return ObjectCalls.ptrcallWithIntArgRetDictionary(getTrackersBind, xRServerSingleton, trackerTypes)
}

private val xRServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("XRServer")
}

private const val GET_INTERFACES_HASH = 3995934104L
private val getInterfacesBind by lazy {
    ObjectCalls.getMethodBind("XRServer", "get_interfaces", GET_INTERFACES_HASH)
}

private const val GET_TRACKERS_HASH = 3554694381L
private val getTrackersBind by lazy {
    ObjectCalls.getMethodBind("XRServer", "get_trackers", GET_TRACKERS_HASH)
}
