package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: OpenXRSpatialEntityExtension
 */
class OpenXRSpatialEntityExtension(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun supportsCapability(capability: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(supportsCapabilityBind, handle, capability)
    }

    fun supportsComponentType(capability: Long, componentType: Long): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(supportsComponentTypeBind, handle, capability, componentType)
    }

    fun createSpatialContext(capabilityConfigurations: List<OpenXRSpatialCapabilityConfigurationBaseHeader>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithObjectListObjectCallableArgsRetObject(createSpatialContextBind, handle, capabilityConfigurations, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
    }

    fun getSpatialContextReady(spatialContext: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(getSpatialContextReadyBind, handle, spatialContext)
    }

    fun freeSpatialContext(spatialContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeSpatialContextBind, handle, spatialContext)
    }

    fun getSpatialContextHandle(spatialContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(getSpatialContextHandleBind, handle, spatialContext)
    }

    fun discoverSpatialEntitiesWithComponentData(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListObjectCallableArgsRetObject(discoverSpatialEntitiesWithComponentDataBind, handle, spatialContext, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
    }

    fun discoverSpatialEntities(spatialContext: RID, componentTypes: List<Long>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject(discoverSpatialEntitiesBind, handle, spatialContext, componentTypes, next?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
    }

    fun updateSpatialEntities(spatialContext: RID, entities: List<RID>, componentTypes: List<Long>, next: OpenXRStructureBase?): RID {
        return ObjectCalls.ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID(updateSpatialEntitiesBind, handle, spatialContext, entities, componentTypes, next?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun freeSpatialSnapshot(spatialSnapshot: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeSpatialSnapshotBind, handle, spatialSnapshot)
    }

    fun getSpatialSnapshotHandle(spatialSnapshot: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(getSpatialSnapshotHandleBind, handle, spatialSnapshot)
    }

    fun getSpatialSnapshotContext(spatialSnapshot: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(getSpatialSnapshotContextBind, handle, spatialSnapshot)
    }

    fun querySnapshot(spatialSnapshot: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?): Boolean {
        return ObjectCalls.ptrcallWithRIDObjectListObjectArgsRetBool(querySnapshotBind, handle, spatialSnapshot, componentData, next?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getString(spatialSnapshot: RID, bufferId: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(getStringBind, handle, spatialSnapshot, bufferId)
    }

    fun getUint8Buffer(spatialSnapshot: RID, bufferId: Long): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetByteArray(getUint8BufferBind, handle, spatialSnapshot, bufferId)
    }

    fun getUint16Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(getUint16BufferBind, handle, spatialSnapshot, bufferId)
    }

    fun getUint32Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(getUint32BufferBind, handle, spatialSnapshot, bufferId)
    }

    fun getFloatBuffer(spatialSnapshot: RID, bufferId: Long): List<Float> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedFloat32List(getFloatBufferBind, handle, spatialSnapshot, bufferId)
    }

    fun getVector2Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector2> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(getVector2BufferBind, handle, spatialSnapshot, bufferId)
    }

    fun getVector3Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector3List(getVector3BufferBind, handle, spatialSnapshot, bufferId)
    }

    fun findSpatialEntity(entityId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(findSpatialEntityBind, handle, entityId)
    }

    fun addSpatialEntity(spatialContext: RID, entityId: Long, entity: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(addSpatialEntityBind, handle, spatialContext, entityId, entity)
    }

    fun makeSpatialEntity(spatialContext: RID, entityId: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(makeSpatialEntityBind, handle, spatialContext, entityId)
    }

    fun getSpatialEntityId(entity: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(getSpatialEntityIdBind, handle, entity)
    }

    fun getSpatialEntityContext(entity: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(getSpatialEntityContextBind, handle, entity)
    }

    fun freeSpatialEntity(entity: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeSpatialEntityBind, handle, entity)
    }

    object Signals {
        const val spatialDiscoveryRecommended: String = "spatial_discovery_recommended"
    }

    companion object {
        const val CAPABILITY_PLANE_TRACKING: Long = 1000741000L
        const val CAPABILITY_MARKER_TRACKING_QR_CODE: Long = 1000743000L
        const val CAPABILITY_MARKER_TRACKING_MICRO_QR_CODE: Long = 1000743001L
        const val CAPABILITY_MARKER_TRACKING_ARUCO_MARKER: Long = 1000743002L
        const val CAPABILITY_MARKER_TRACKING_APRIL_TAG: Long = 1000743003L
        const val CAPABILITY_ANCHOR: Long = 1000762000L
        const val COMPONENT_TYPE_BOUNDED_2D: Long = 1L
        const val COMPONENT_TYPE_BOUNDED_3D: Long = 2L
        const val COMPONENT_TYPE_PARENT: Long = 3L
        const val COMPONENT_TYPE_MESH_3D: Long = 4L
        const val COMPONENT_TYPE_PLANE_ALIGNMENT: Long = 1000741000L
        const val COMPONENT_TYPE_MESH_2D: Long = 1000741001L
        const val COMPONENT_TYPE_POLYGON_2D: Long = 1000741002L
        const val COMPONENT_TYPE_PLANE_SEMANTIC_LABEL: Long = 1000741003L
        const val COMPONENT_TYPE_MARKER: Long = 1000743000L
        const val COMPONENT_TYPE_ANCHOR: Long = 1000762000L
        const val COMPONENT_TYPE_PERSISTENCE: Long = 1000763000L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialEntityExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialEntityExtension? =
            if (handle.address() == 0L) null else OpenXRSpatialEntityExtension(handle)

        private const val SUPPORTS_CAPABILITY_HASH = 1940837202L
        private val supportsCapabilityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "supports_capability", SUPPORTS_CAPABILITY_HASH)
        }

        private const val SUPPORTS_COMPONENT_TYPE_HASH = 26842779L
        private val supportsComponentTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "supports_component_type", SUPPORTS_COMPONENT_TYPE_HASH)
        }

        private const val CREATE_SPATIAL_CONTEXT_HASH = 1874506473L
        private val createSpatialContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "create_spatial_context", CREATE_SPATIAL_CONTEXT_HASH)
        }

        private const val GET_SPATIAL_CONTEXT_READY_HASH = 4155700596L
        private val getSpatialContextReadyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_context_ready", GET_SPATIAL_CONTEXT_READY_HASH)
        }

        private const val FREE_SPATIAL_CONTEXT_HASH = 2722037293L
        private val freeSpatialContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_context", FREE_SPATIAL_CONTEXT_HASH)
        }

        private const val GET_SPATIAL_CONTEXT_HANDLE_HASH = 2198884583L
        private val getSpatialContextHandleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_context_handle", GET_SPATIAL_CONTEXT_HANDLE_HASH)
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

        private const val FREE_SPATIAL_SNAPSHOT_HASH = 2722037293L
        private val freeSpatialSnapshotBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_snapshot", FREE_SPATIAL_SNAPSHOT_HASH)
        }

        private const val GET_SPATIAL_SNAPSHOT_HANDLE_HASH = 2198884583L
        private val getSpatialSnapshotHandleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_snapshot_handle", GET_SPATIAL_SNAPSHOT_HANDLE_HASH)
        }

        private const val GET_SPATIAL_SNAPSHOT_CONTEXT_HASH = 3814569979L
        private val getSpatialSnapshotContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_snapshot_context", GET_SPATIAL_SNAPSHOT_CONTEXT_HASH)
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

        private const val FIND_SPATIAL_ENTITY_HASH = 937000113L
        private val findSpatialEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "find_spatial_entity", FIND_SPATIAL_ENTITY_HASH)
        }

        private const val ADD_SPATIAL_ENTITY_HASH = 2256026069L
        private val addSpatialEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "add_spatial_entity", ADD_SPATIAL_ENTITY_HASH)
        }

        private const val MAKE_SPATIAL_ENTITY_HASH = 2233757277L
        private val makeSpatialEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "make_spatial_entity", MAKE_SPATIAL_ENTITY_HASH)
        }

        private const val GET_SPATIAL_ENTITY_ID_HASH = 2198884583L
        private val getSpatialEntityIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_entity_id", GET_SPATIAL_ENTITY_ID_HASH)
        }

        private const val GET_SPATIAL_ENTITY_CONTEXT_HASH = 3814569979L
        private val getSpatialEntityContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_entity_context", GET_SPATIAL_ENTITY_CONTEXT_HASH)
        }

        private const val FREE_SPATIAL_ENTITY_HASH = 2722037293L
        private val freeSpatialEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_entity", FREE_SPATIAL_ENTITY_HASH)
        }
    }
}
