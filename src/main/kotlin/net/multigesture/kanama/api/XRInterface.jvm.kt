package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for XRInterface (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRInterface waits on: ptrcallNoArgsRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a `Dictionary` with extra system info. Interfaces are expected to return `XRRuntimeName`
 * and `XRRuntimeVersion` providing info about the used XR runtime. Additional entries may be
 * provided specific to an interface. Note:This information may only be available after
 * `initialize` was successfully called.
 *
 * Generated from Godot docs: XRInterface.get_system_info
 */
fun XRInterface.getSystemInfo(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getSystemInfoBind, handle)
}

private const val GET_SYSTEM_INFO_HASH = 2382534195L
private val getSystemInfoBind by lazy {
    ObjectCalls.getMethodBind("XRInterface", "get_system_info", GET_SYSTEM_INFO_HASH)
}
