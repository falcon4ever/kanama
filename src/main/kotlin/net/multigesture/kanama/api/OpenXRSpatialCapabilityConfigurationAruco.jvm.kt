package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRSpatialCapabilityConfigurationAruco (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialCapabilityConfigurationAruco waits on: ptrcallNoArgsRetPackedInt64List
// Index: docs/contributing/ios-shape-gap.md

fun OpenXRSpatialCapabilityConfigurationAruco.getEnabledComponents(): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, handle)
}

private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
private val getEnabledComponentsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
}
