package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for OpenXRSpatialEntityExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialEntityExtension waits on: ptrcallWithObjectListObjectCallableArgsRetObject,
//   ptrcallWithRIDObjectListObjectArgsRetBool, ptrcallWithRIDObjectListObjectCallableArgsRetObject,
//   ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialEntityExtension.createSpatialContext(capabilityConfigurations: List<OpenXRSpatialCapabilityConfigurationBaseHeader>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithObjectListObjectCallableArgsRetObject(createSpatialContextBind, handle, capabilityConfigurations, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialEntityExtension.discoverSpatialEntitiesWithComponentData(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListObjectCallableArgsRetObject(discoverSpatialEntitiesWithComponentDataBind, handle, spatialContext, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialEntityExtension.updateSpatialEntities(spatialContext: RID, entities: List<RID>, componentTypes: List<Long>, next: OpenXRStructureBase?): RID {
    return ObjectCalls.ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID(updateSpatialEntitiesBind, handle, spatialContext, entities, componentTypes, next?.requireOpenHandle() ?: MemorySegment.NULL)
}

fun OpenXRSpatialEntityExtension.querySnapshot(spatialSnapshot: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?): Boolean {
    return ObjectCalls.ptrcallWithRIDObjectListObjectArgsRetBool(querySnapshotBind, handle, spatialSnapshot, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL)
}

private const val CREATE_SPATIAL_CONTEXT_HASH = 1874506473L
private val createSpatialContextBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "create_spatial_context", CREATE_SPATIAL_CONTEXT_HASH)
}

private const val DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH = 1830928590L
private val discoverSpatialEntitiesWithComponentDataBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "discover_spatial_entities_with_component_data", DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH)
}

private const val UPDATE_SPATIAL_ENTITIES_HASH = 3446086438L
private val updateSpatialEntitiesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "update_spatial_entities", UPDATE_SPATIAL_ENTITIES_HASH)
}

private const val QUERY_SNAPSHOT_HASH = 641015484L
private val querySnapshotBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "query_snapshot", QUERY_SNAPSHOT_HASH)
}
