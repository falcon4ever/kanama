package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for OpenXRSpatialMarkerTrackingCapability (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialMarkerTrackingCapability waits on: ptrcallWithRIDObjectListTwoObjectArgs,
//   ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialMarkerTrackingCapability.startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(startEntityDiscoveryBind, handle, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: MemorySegment.NULL, nextSnapshotQuery?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialMarkerTrackingCapability.doEntityUpdate(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?) {
    ObjectCalls.ptrcallWithRIDObjectListTwoObjectArgs(doEntityUpdateBind, handle, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: MemorySegment.NULL, nextSnapshotQuery?.requireOpenHandle() ?: MemorySegment.NULL)
}

private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
private val startEntityDiscoveryBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)
}

private const val DO_ENTITY_UPDATE_HASH = 3138044275L
private val doEntityUpdateBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "do_entity_update", DO_ENTITY_UPDATE_HASH)
}
