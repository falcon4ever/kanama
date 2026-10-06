package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: OpenXRSpatialEntityExtension
 */
class OpenXRSpatialEntityExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun supportsCapability(capability: OpenXRSpatialEntityExtension.Capability): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.supportsCapabilityBind, segment, capability.value)
    }

    fun supportsComponentType(capability: OpenXRSpatialEntityExtension.Capability, componentType: OpenXRSpatialEntityExtension.ComponentType): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(Binds.supportsComponentTypeBind, segment, capability.value, componentType.value)
    }

    fun createSpatialContext(capabilityConfigurations: List<OpenXRSpatialCapabilityConfigurationBaseHeader>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectListObjectCallableArgsRetObject(Binds.createSpatialContextBind, segment, capabilityConfigurations, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    fun getSpatialContextReady(spatialContext: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.getSpatialContextReadyBind, segment, spatialContext)
    }

    fun freeSpatialContext(spatialContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeSpatialContextBind, segment, spatialContext)
    }

    fun getSpatialContextHandle(spatialContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialContextHandleBind, segment, spatialContext)
    }

    fun discoverSpatialEntitiesWithComponentData(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListObjectCallableArgsRetObject(Binds.discoverSpatialEntitiesWithComponentDataBind, segment, spatialContext, componentData, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    fun discoverSpatialEntities(spatialContext: RID, componentTypes: List<Long>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject(Binds.discoverSpatialEntitiesBind, segment, spatialContext, componentTypes, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    fun updateSpatialEntities(spatialContext: RID, entities: List<RID>, componentTypes: List<Long>, next: OpenXRStructureBase?): RID {
        return ObjectCalls.ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID(Binds.updateSpatialEntitiesBind, segment, spatialContext, entities, componentTypes, next?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun freeSpatialSnapshot(spatialSnapshot: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeSpatialSnapshotBind, segment, spatialSnapshot)
    }

    fun getSpatialSnapshotHandle(spatialSnapshot: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialSnapshotHandleBind, segment, spatialSnapshot)
    }

    fun getSpatialSnapshotContext(spatialSnapshot: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.getSpatialSnapshotContextBind, segment, spatialSnapshot)
    }

    fun querySnapshot(spatialSnapshot: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?): Boolean {
        return ObjectCalls.ptrcallWithRIDObjectListObjectArgsRetBool(Binds.querySnapshotBind, segment, spatialSnapshot, componentData, next?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    fun getString(spatialSnapshot: RID, bufferId: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.getStringBind, segment, spatialSnapshot, bufferId)
    }

    fun getUint8Buffer(spatialSnapshot: RID, bufferId: Long): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetByteArray(Binds.getUint8BufferBind, segment, spatialSnapshot, bufferId)
    }

    fun getUint16Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(Binds.getUint16BufferBind, segment, spatialSnapshot, bufferId)
    }

    fun getUint32Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(Binds.getUint32BufferBind, segment, spatialSnapshot, bufferId)
    }

    fun getFloatBuffer(spatialSnapshot: RID, bufferId: Long): List<Float> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedFloat32List(Binds.getFloatBufferBind, segment, spatialSnapshot, bufferId)
    }

    fun getVector2Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector2> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(Binds.getVector2BufferBind, segment, spatialSnapshot, bufferId)
    }

    fun getVector3Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector3List(Binds.getVector3BufferBind, segment, spatialSnapshot, bufferId)
    }

    fun findSpatialEntity(entityId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(Binds.findSpatialEntityBind, segment, entityId)
    }

    fun addSpatialEntity(spatialContext: RID, entityId: Long, entity: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(Binds.addSpatialEntityBind, segment, spatialContext, entityId, entity)
    }

    fun makeSpatialEntity(spatialContext: RID, entityId: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(Binds.makeSpatialEntityBind, segment, spatialContext, entityId)
    }

    fun getSpatialEntityId(entity: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialEntityIdBind, segment, entity)
    }

    fun getSpatialEntityContext(entity: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.getSpatialEntityContextBind, segment, entity)
    }

    fun freeSpatialEntity(entity: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeSpatialEntityBind, segment, entity)
    }

    /** Signal `spatial_discovery_recommended(spatial_context: RID)`; see [TypedSignal]. */
    val spatialDiscoveryRecommended: Signal1<RID>
        @JvmName("spatialDiscoveryRecommendedTypedSignal")
        get() = Signal1(this, "spatial_discovery_recommended", SignalArgType.valueOf<RID>("RID", RID::class))

    object Signals {
        const val spatialDiscoveryRecommended: String = "spatial_discovery_recommended"
    }

    @JvmInline
    value class Capability(override val value: Long) : GodotEnumValue {
        companion object {
            val PLANE_TRACKING: Capability get() = Capability(1000741000L)
            val MARKER_TRACKING_QR_CODE: Capability get() = Capability(1000743000L)
            val MARKER_TRACKING_MICRO_QR_CODE: Capability get() = Capability(1000743001L)
            val MARKER_TRACKING_ARUCO_MARKER: Capability get() = Capability(1000743002L)
            val MARKER_TRACKING_APRIL_TAG: Capability get() = Capability(1000743003L)
            val ANCHOR: Capability get() = Capability(1000762000L)
        }
    }

    @JvmInline
    value class ComponentType(override val value: Long) : GodotEnumValue {
        companion object {
            val BOUNDED_2D: ComponentType get() = ComponentType(1L)
            val BOUNDED_3D: ComponentType get() = ComponentType(2L)
            val PARENT: ComponentType get() = ComponentType(3L)
            val MESH_3D: ComponentType get() = ComponentType(4L)
            val PLANE_ALIGNMENT: ComponentType get() = ComponentType(1000741000L)
            val MESH_2D: ComponentType get() = ComponentType(1000741001L)
            val POLYGON_2D: ComponentType get() = ComponentType(1000741002L)
            val PLANE_SEMANTIC_LABEL: ComponentType get() = ComponentType(1000741003L)
            val MARKER: ComponentType get() = ComponentType(1000743000L)
            val ANCHOR: ComponentType get() = ComponentType(1000762000L)
            val PERSISTENCE: ComponentType get() = ComponentType(1000763000L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialEntityExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialEntityExtension? =
            if (handle.address() == 0L) null else OpenXRSpatialEntityExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val SUPPORTS_CAPABILITY_HASH = 1940837202L
        @JvmField
        val supportsCapabilityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "supports_capability", SUPPORTS_CAPABILITY_HASH)

        private const val SUPPORTS_COMPONENT_TYPE_HASH = 26842779L
        @JvmField
        val supportsComponentTypeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "supports_component_type", SUPPORTS_COMPONENT_TYPE_HASH)

        private const val CREATE_SPATIAL_CONTEXT_HASH = 1874506473L
        @JvmField
        val createSpatialContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "create_spatial_context", CREATE_SPATIAL_CONTEXT_HASH)

        private const val GET_SPATIAL_CONTEXT_READY_HASH = 4155700596L
        @JvmField
        val getSpatialContextReadyBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_context_ready", GET_SPATIAL_CONTEXT_READY_HASH)

        private const val FREE_SPATIAL_CONTEXT_HASH = 2722037293L
        @JvmField
        val freeSpatialContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_context", FREE_SPATIAL_CONTEXT_HASH)

        private const val GET_SPATIAL_CONTEXT_HANDLE_HASH = 2198884583L
        @JvmField
        val getSpatialContextHandleBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_context_handle", GET_SPATIAL_CONTEXT_HANDLE_HASH)

        private const val DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH = 1830928590L
        @JvmField
        val discoverSpatialEntitiesWithComponentDataBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "discover_spatial_entities_with_component_data", DISCOVER_SPATIAL_ENTITIES_WITH_COMPONENT_DATA_HASH)

        private const val DISCOVER_SPATIAL_ENTITIES_HASH = 2252833536L
        @JvmField
        val discoverSpatialEntitiesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "discover_spatial_entities", DISCOVER_SPATIAL_ENTITIES_HASH)

        private const val UPDATE_SPATIAL_ENTITIES_HASH = 3446086438L
        @JvmField
        val updateSpatialEntitiesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "update_spatial_entities", UPDATE_SPATIAL_ENTITIES_HASH)

        private const val FREE_SPATIAL_SNAPSHOT_HASH = 2722037293L
        @JvmField
        val freeSpatialSnapshotBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_snapshot", FREE_SPATIAL_SNAPSHOT_HASH)

        private const val GET_SPATIAL_SNAPSHOT_HANDLE_HASH = 2198884583L
        @JvmField
        val getSpatialSnapshotHandleBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_snapshot_handle", GET_SPATIAL_SNAPSHOT_HANDLE_HASH)

        private const val GET_SPATIAL_SNAPSHOT_CONTEXT_HASH = 3814569979L
        @JvmField
        val getSpatialSnapshotContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_snapshot_context", GET_SPATIAL_SNAPSHOT_CONTEXT_HASH)

        private const val QUERY_SNAPSHOT_HASH = 641015484L
        @JvmField
        val querySnapshotBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "query_snapshot", QUERY_SNAPSHOT_HASH)

        private const val GET_STRING_HASH = 1464764419L
        @JvmField
        val getStringBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_string", GET_STRING_HASH)

        private const val GET_UINT8_BUFFER_HASH = 3570600051L
        @JvmField
        val getUint8BufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint8_buffer", GET_UINT8_BUFFER_HASH)

        private const val GET_UINT16_BUFFER_HASH = 3393655756L
        @JvmField
        val getUint16BufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint16_buffer", GET_UINT16_BUFFER_HASH)

        private const val GET_UINT32_BUFFER_HASH = 3393655756L
        @JvmField
        val getUint32BufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_uint32_buffer", GET_UINT32_BUFFER_HASH)

        private const val GET_FLOAT_BUFFER_HASH = 2313216651L
        @JvmField
        val getFloatBufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_float_buffer", GET_FLOAT_BUFFER_HASH)

        private const val GET_VECTOR2_BUFFER_HASH = 110850971L
        @JvmField
        val getVector2BufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_vector2_buffer", GET_VECTOR2_BUFFER_HASH)

        private const val GET_VECTOR3_BUFFER_HASH = 1166453791L
        @JvmField
        val getVector3BufferBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_vector3_buffer", GET_VECTOR3_BUFFER_HASH)

        private const val FIND_SPATIAL_ENTITY_HASH = 937000113L
        @JvmField
        val findSpatialEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "find_spatial_entity", FIND_SPATIAL_ENTITY_HASH)

        private const val ADD_SPATIAL_ENTITY_HASH = 2256026069L
        @JvmField
        val addSpatialEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "add_spatial_entity", ADD_SPATIAL_ENTITY_HASH)

        private const val MAKE_SPATIAL_ENTITY_HASH = 2233757277L
        @JvmField
        val makeSpatialEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "make_spatial_entity", MAKE_SPATIAL_ENTITY_HASH)

        private const val GET_SPATIAL_ENTITY_ID_HASH = 2198884583L
        @JvmField
        val getSpatialEntityIdBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_entity_id", GET_SPATIAL_ENTITY_ID_HASH)

        private const val GET_SPATIAL_ENTITY_CONTEXT_HASH = 3814569979L
        @JvmField
        val getSpatialEntityContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "get_spatial_entity_context", GET_SPATIAL_ENTITY_CONTEXT_HASH)

        private const val FREE_SPATIAL_ENTITY_HASH = 2722037293L
        @JvmField
        val freeSpatialEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityExtension", "free_spatial_entity", FREE_SPATIAL_ENTITY_HASH)
    }
}
