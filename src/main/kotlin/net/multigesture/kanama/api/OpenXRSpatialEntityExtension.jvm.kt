package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for OpenXRSpatialEntityExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRSpatialEntityExtension waits on: ptrcallWithObjectListObjectCallableArgsRetObject,
//   ptrcallWithRIDAndLongArgRetByteArray, ptrcallWithRIDAndLongArgRetPackedFloat32List,
//   ptrcallWithRIDAndLongArgRetPackedInt32List, ptrcallWithRIDAndLongArgRetPackedVector2List,
//   ptrcallWithRIDAndLongArgRetPackedVector3List, ptrcallWithRIDAndLongArgRetString,
//   ptrcallWithRIDObjectListObjectArgsRetBool, ptrcallWithRIDObjectListObjectCallableArgsRetObject,
//   ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject,
//   ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRSpatialEntityExtension.createSpatialContext(capabilityConfigurations: List<OpenXRSpatialCapabilityConfigurationBaseHeader>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithObjectListObjectCallableArgsRetObject(createSpatialContextBind, handle, capabilityConfigurations, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialEntityExtension.discoverSpatialEntitiesWithComponentData(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListObjectCallableArgsRetObject(discoverSpatialEntitiesWithComponentDataBind, handle, spatialContext, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialEntityExtension.discoverSpatialEntities(spatialContext: RID, componentTypes: List<Long>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
    return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject(discoverSpatialEntitiesBind, handle, spatialContext, componentTypes, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
}

fun OpenXRSpatialEntityExtension.updateSpatialEntities(spatialContext: RID, entities: List<RID>, componentTypes: List<Long>, next: OpenXRStructureBase?): RID {
    return ObjectCalls.ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID(updateSpatialEntitiesBind, handle, spatialContext, entities, componentTypes, next?.requireOpenHandle() ?: MemorySegment.NULL)
}

fun OpenXRSpatialEntityExtension.querySnapshot(spatialSnapshot: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?): Boolean {
    return ObjectCalls.ptrcallWithRIDObjectListObjectArgsRetBool(querySnapshotBind, handle, spatialSnapshot, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL)
}

fun OpenXRSpatialEntityExtension.getString(spatialSnapshot: RID, bufferId: Long): String {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetString(getStringBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getUint8Buffer(spatialSnapshot: RID, bufferId: Long): ByteArray {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetByteArray(getUint8BufferBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getUint16Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(getUint16BufferBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getUint32Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(getUint32BufferBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getFloatBuffer(spatialSnapshot: RID, bufferId: Long): List<Float> {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedFloat32List(getFloatBufferBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getVector2Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector2> {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(getVector2BufferBind, handle, spatialSnapshot, bufferId)
}

fun OpenXRSpatialEntityExtension.getVector3Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector3> {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector3List(getVector3BufferBind, handle, spatialSnapshot, bufferId)
}

private const val CREATE_SPATIAL_CONTEXT_HASH = 1874506473L
private val createSpatialContextBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "create_spatial_context", CREATE_SPATIAL_CONTEXT_HASH)
}

private const val DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH = 1830928590L
private val discoverSpatialEntitiesWithComponentDataBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "discover_spatial_entities_with_component_data", DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH)
}

private const val DISCOVER_SPATIAL_ENTITIES_HASH = 2252833536L
private val discoverSpatialEntitiesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "discover_spatial_entities", DISCOVER_SPATIAL_ENTITIES_HASH)
}

private const val UPDATE_SPATIAL_ENTITIES_HASH = 3446086438L
private val updateSpatialEntitiesBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "update_spatial_entities", UPDATE_SPATIAL_ENTITIES_HASH)
}

private const val QUERY_SNAPSHOT_HASH = 641015484L
private val querySnapshotBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "query_snapshot", QUERY_SNAPSHOT_HASH)
}

private const val GET_STRING_HASH = 1464764419L
private val getStringBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_string", GET_STRING_HASH)
}

private const val GET_UINT8_BUFFER_HASH = 3570600051L
private val getUint8BufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint8_buffer", GET_UINT8_BUFFER_HASH)
}

private const val GET_UINT16_BUFFER_HASH = 3393655756L
private val getUint16BufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint16_buffer", GET_UINT16_BUFFER_HASH)
}

private const val GET_UINT32_BUFFER_HASH = 3393655756L
private val getUint32BufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint32_buffer", GET_UINT32_BUFFER_HASH)
}

private const val GET_FLOAT_BUFFER_HASH = 2313216651L
private val getFloatBufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_float_buffer", GET_FLOAT_BUFFER_HASH)
}

private const val GET_VECTOR2_BUFFER_HASH = 110850971L
private val getVector2BufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_vector2_buffer", GET_VECTOR2_BUFFER_HASH)
}

private const val GET_VECTOR3_BUFFER_HASH = 1166453791L
private val getVector3BufferBind by lazy {
    ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_vector3_buffer", GET_VECTOR3_BUFFER_HASH)
}
