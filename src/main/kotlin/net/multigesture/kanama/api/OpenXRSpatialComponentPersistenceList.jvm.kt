package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRSpatialComponentPersistenceList (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialComponentPersistenceList waits on: ptrcallWithLongArgRetString
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialComponentPersistenceList.getPersistentUuid(index: Long): String {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetString(getPersistentUuidBind, handle, index)
}

private const val GET_PERSISTENT_UUID_HASH = 844755477L
private val getPersistentUuidBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_uuid", GET_PERSISTENT_UUID_HASH)
}
