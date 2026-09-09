package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRMarkerTracker (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRMarkerTracker waits on: ptrcallWithVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRMarkerTracker.setMarkerData(markerData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(setMarkerDataBind, handle, markerData)
}

private const val SET_MARKER_DATA_HASH = 1114965689L
private val setMarkerDataBind by lazy {
    ObjectCalls.getMethodBind("OpenXRMarkerTracker", "set_marker_data", SET_MARKER_DATA_HASH)
}
