package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for OpenXRSpatialComponentMarkerList (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialComponentMarkerList waits on: ptrcallWithRIDAndLongArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialComponentMarkerList.getMarkerData(snapshot: RID, index: Long): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(getMarkerDataBind, handle, snapshot, index)
}

private const val GET_MARKER_DATA_HASH = 4069510997L
private val getMarkerDataBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialComponentMarkerList", "get_marker_data", GET_MARKER_DATA_HASH)
}
