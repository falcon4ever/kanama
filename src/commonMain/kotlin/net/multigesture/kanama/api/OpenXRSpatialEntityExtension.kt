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
 * OpenXR extension that handles spatial entities.
 *
 * Generated from Godot docs: OpenXRSpatialEntityExtension
 */
class OpenXRSpatialEntityExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if this spatial entity `capability` is supported by the hardware used.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.supports_capability
     */
    fun supportsCapability(capability: OpenXRSpatialEntityExtension.Capability): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.supportsCapabilityBind, segment, capability.value)
    }

    /**
     * Returns `true` if this `capability` supports the `component_type`.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.supports_component_type
     */
    fun supportsComponentType(capability: OpenXRSpatialEntityExtension.Capability, componentType: OpenXRSpatialEntityExtension.ComponentType): Boolean {
        return ObjectCalls.ptrcallWithTwoLongArgsRetBool(Binds.supportsComponentTypeBind, segment, capability.value, componentType.value)
    }

    /**
     * Creates a new spatial context that handles entities for the provided capability configurations.
     * `capability_configurations` is an array of `OpenXRSpatialCapabilityConfigurationBaseHeader` with
     * the needed capability configuration data. `next` is an optional parameter that can contain
     * additional information for creating our spatial context. Note: This is an asynchronous method
     * and returns an `OpenXRFutureResult` object with which to track the status, discarding this
     * object will not cancel the creation process. On success `user_callback` will be called if
     * specified. The result data for this function is the `RID` for our spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.create_spatial_context
     */
    fun createSpatialContext(capabilityConfigurations: List<OpenXRSpatialCapabilityConfigurationBaseHeader>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithObjectListObjectCallableArgsRetObject(Binds.createSpatialContextBind, segment, capabilityConfigurations, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    /**
     * Returns `true` if the spatial context finished its creation and is ready to be used.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_context_ready
     */
    fun getSpatialContextReady(spatialContext: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.getSpatialContextReadyBind, segment, spatialContext)
    }

    /**
     * Frees a spatial context previously created when calling `create_spatial_context`. If the spatial
     * context creation is still ongoing, the asynchronous process is cancelled.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.free_spatial_context
     */
    fun freeSpatialContext(spatialContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeSpatialContextBind, segment, spatialContext)
    }

    /**
     * Returns the OpenXR spatial context handle for this snapshot. Note: This method is intended to be
     * used from GDExtensions that implement spatial entity capability handlers.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_context_handle
     */
    fun getSpatialContextHandle(spatialContext: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialContextHandleBind, segment, spatialContext)
    }

    /**
     * Convenience method when the caller only has an `Array` of `OpenXRSpatialComponentData` and needs
     * to discover spatial entities.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.discover_spatial_entities_with_component_data
     */
    fun discoverSpatialEntitiesWithComponentData(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListObjectCallableArgsRetObject(Binds.discoverSpatialEntitiesWithComponentDataBind, segment, spatialContext, componentData, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    /**
     * Starts a new discovery query, this will gather all objects tracked by the `spatial_context` that
     * have at least one of the component types specified in `component_types`. `next` is an optional
     * parameter that can contain additional information for executing the discovery query. Note: This
     * is an asynchronous method and returns an `OpenXRFutureResult` object with which to track the
     * status, discarding this object will not cancel the discovery process. On success `user_callback`
     * will be called if specified. The result data for this function is the `RID` for our snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.discover_spatial_entities
     */
    fun discoverSpatialEntities(spatialContext: RID, componentTypes: List<Long>, next: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDPackedInt64ListObjectCallableArgsRetObject(Binds.discoverSpatialEntitiesBind, segment, spatialContext, componentTypes, next?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    /**
     * Performs a snapshot for a limited number of entities. This is NOT an asynchronous method and
     * will return the snapshot immediately.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.update_spatial_entities
     */
    fun updateSpatialEntities(spatialContext: RID, entities: List<RID>, componentTypes: List<Long>, next: OpenXRStructureBase?): RID {
        return ObjectCalls.ptrcallWithRIDRIDListPackedInt64ListObjectArgsRetRID(Binds.updateSpatialEntitiesBind, segment, spatialContext, entities, componentTypes, next?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Frees a spatial snapshot previously created when calling `discover_spatial_entities`. If the
     * spatial snapshot creation is still ongoing, the asynchronous process is cancelled.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.free_spatial_snapshot
     */
    fun freeSpatialSnapshot(spatialSnapshot: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeSpatialSnapshotBind, segment, spatialSnapshot)
    }

    /**
     * Returns the OpenXR spatial snapshot handle for this snapshot. Note: This method is intended to
     * be used from GDExtensions that implement spatial entity capability handlers.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_snapshot_handle
     */
    fun getSpatialSnapshotHandle(spatialSnapshot: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialSnapshotHandleBind, segment, spatialSnapshot)
    }

    /**
     * Returns the spatial context related to this spatial snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_snapshot_context
     */
    fun getSpatialSnapshotContext(spatialSnapshot: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.getSpatialSnapshotContextBind, segment, spatialSnapshot)
    }

    /**
     * Queries the snapshot data. This will find all entities in the snapshot that contain all
     * requested components in `component_data`. The objects held within `component_data` will then be
     * populated with the queried data. `component_data` must always have an object of
     * `OpenXRSpatialQueryResultData` as the first entry. `next` is an optional parameter that can
     * contain additional information passed when setting our query conditions.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.query_snapshot
     */
    fun querySnapshot(spatialSnapshot: RID, componentData: List<OpenXRSpatialComponentData>, next: OpenXRStructureBase?): Boolean {
        return ObjectCalls.ptrcallWithRIDObjectListObjectArgsRetBool(Binds.querySnapshotBind, segment, spatialSnapshot, componentData, next?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns a string from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_string
     */
    fun getString(spatialSnapshot: RID, bufferId: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.getStringBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with 8 bit ints from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_uint8_buffer
     */
    fun getUint8Buffer(spatialSnapshot: RID, bufferId: Long): ByteArray {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetByteArray(Binds.getUint8BufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with 16 bit ints from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_uint16_buffer
     */
    fun getUint16Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(Binds.getUint16BufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with 32 bit ints from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_uint32_buffer
     */
    fun getUint32Buffer(spatialSnapshot: RID, bufferId: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedInt32List(Binds.getUint32BufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with floats from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_float_buffer
     */
    fun getFloatBuffer(spatialSnapshot: RID, bufferId: Long): List<Float> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedFloat32List(Binds.getFloatBufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with `Vector2` entries from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_vector2_buffer
     */
    fun getVector2Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector2> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector2List(Binds.getVector2BufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns a buffer with `Vector3` entries from a buffer that was retrieved when taking a snapshot.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_vector3_buffer
     */
    fun getVector3Buffer(spatialSnapshot: RID, bufferId: Long): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedVector3List(Binds.getVector3BufferBind, segment, spatialSnapshot, bufferId)
    }

    /**
     * Returns the `RID` for the specified spatial entity ID.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.find_spatial_entity
     */
    fun findSpatialEntity(entityId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(Binds.findSpatialEntityBind, segment, entityId)
    }

    /**
     * Registers an entity that was created directly on the OpenXR runtime.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.add_spatial_entity
     */
    fun addSpatialEntity(spatialContext: RID, entityId: Long, entity: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(Binds.addSpatialEntityBind, segment, spatialContext, entityId, entity)
    }

    /**
     * Creates a new entity for this `entity_id`. The `spatial_context` should match the context that
     * discovered the entity.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.make_spatial_entity
     */
    fun makeSpatialEntity(spatialContext: RID, entityId: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(Binds.makeSpatialEntityBind, segment, spatialContext, entityId)
    }

    /**
     * Returns the internal `XrSpatialEntityIdEXT` associated with the entity.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_entity_id
     */
    fun getSpatialEntityId(entity: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.getSpatialEntityIdBind, segment, entity)
    }

    /**
     * Returns the spatial context for this entity.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.get_spatial_entity_context
     */
    fun getSpatialEntityContext(entity: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.getSpatialEntityContextBind, segment, entity)
    }

    /**
     * Frees an entity previously created when calling `add_spatial_entity` or `make_spatial_entity`.
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.free_spatial_entity
     */
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

    /**
     * Godot's `OpenXRSpatialEntityExtension.Capability` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialEntityExtension.Capability.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.Capability
     */
    @JvmInline
    value class Capability(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Plane tracking capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_PLANE_TRACKING
             */
            val PLANE_TRACKING: Capability get() = Capability(1000741000L)
            /**
             * QR code based marker tracking capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_MARKER_TRACKING_QR_CODE
             */
            val MARKER_TRACKING_QR_CODE: Capability get() = Capability(1000743000L)
            /**
             * Micro QR code based marker tracking capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_MARKER_TRACKING_MICRO_QR_CODE
             */
            val MARKER_TRACKING_MICRO_QR_CODE: Capability get() = Capability(1000743001L)
            /**
             * Aruco marker based marker tracking capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_MARKER_TRACKING_ARUCO_MARKER
             */
            val MARKER_TRACKING_ARUCO_MARKER: Capability get() = Capability(1000743002L)
            /**
             * April tag based marker tracking capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_MARKER_TRACKING_APRIL_TAG
             */
            val MARKER_TRACKING_APRIL_TAG: Capability get() = Capability(1000743003L)
            /**
             * Anchor capability.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.CAPABILITY_ANCHOR
             */
            val ANCHOR: Capability get() = Capability(1000762000L)
        }
    }

    /**
     * Godot's `OpenXRSpatialEntityExtension.ComponentType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialEntityExtension.ComponentType.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialEntityExtension.ComponentType
     */
    @JvmInline
    value class ComponentType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Component that provides the 2D bounds for a spatial entity. The corresponding list structure is
             * `XrSpatialComponentBounded2DListEXT`; the corresponding data structure is
             * `XrSpatialBounded2DDataEXT`.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_BOUNDED_2D
             */
            val BOUNDED_2D: ComponentType get() = ComponentType(1L)
            /**
             * Component that provides the 3D bounds for a spatial entity. The corresponding list structure is
             * `XrSpatialComponentBounded3DListEXT`; the corresponding data structure is `XrBoxf`.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_BOUNDED_3D
             */
            val BOUNDED_3D: ComponentType get() = ComponentType(2L)
            /**
             * Component that provides the XrSpatialEntityIdEXT of the parent for a spatial entity. The
             * corresponding list structure is `XrSpatialComponentParentListEXT`; the corresponding data
             * structure is `XrSpatialEntityIdEXT`.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_PARENT
             */
            val PARENT: ComponentType get() = ComponentType(3L)
            /**
             * Component that provides a 3D mesh for a spatial entity. The corresponding list structure is
             * `XrSpatialComponentMesh3DListEXT`; the corresponding data structure is `XrSpatialMeshDataEXT`.
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_MESH_3D
             */
            val MESH_3D: ComponentType get() = ComponentType(4L)
            /**
             * Component that provides the plane alignment enum for a spatial entity. The corresponding list
             * structure is `XrSpatialComponentPlaneAlignmentListEXT`; the corresponding data structure is
             * `XrSpatialPlaneAlignmentEXT` (Added by the `XR_EXT_spatial_plane_tracking` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_PLANE_ALIGNMENT
             */
            val PLANE_ALIGNMENT: ComponentType get() = ComponentType(1000741000L)
            /**
             * Component that provides a 2D mesh for a spatial entity. The corresponding list structure is
             * `XrSpatialComponentMesh2DListEXT`; the corresponding data structure is `XrSpatialMeshDataEXT`
             * (Added by the `XR_EXT_spatial_plane_tracking` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_MESH_2D
             */
            val MESH_2D: ComponentType get() = ComponentType(1000741001L)
            /**
             * Component that provides a 2D boundary polygon for a spatial entity. The corresponding list
             * structure is `XrSpatialComponentPolygon2DListEXT`; the corresponding data structure is
             * `XrSpatialPolygon2DDataEXT` (Added by the `XR_EXT_spatial_plane_tracking` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_POLYGON_2D
             */
            val POLYGON_2D: ComponentType get() = ComponentType(1000741002L)
            /**
             * Component that provides a semantic label for a plane. The corresponding list structure is
             * `XrSpatialComponentPlaneSemanticLabelListEXT`; the corresponding data structure is
             * `XrSpatialPlaneSemanticLabelEXT` (Added by the `XR_EXT_spatial_plane_tracking` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_PLANE_SEMANTIC_LABEL
             */
            val PLANE_SEMANTIC_LABEL: ComponentType get() = ComponentType(1000741003L)
            /**
             * A component describing the marker type, ID and location. The corresponding list structure is
             * `XrSpatialComponentMarkerListEXT`; the corresponding data structure is `XrSpatialMarkerDataEXT`
             * (Added by the `XR_EXT_spatial_marker_tracking` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_MARKER
             */
            val MARKER: ComponentType get() = ComponentType(1000743000L)
            /**
             * Component that provides the location for an anchor. The corresponding list structure is
             * `XrSpatialComponentAnchorListEXT`; the corresponding data structure is `XrPosef` (Added by the
             * `XR_EXT_spatial_anchor` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_ANCHOR
             */
            val ANCHOR: ComponentType get() = ComponentType(1000762000L)
            /**
             * Component that provides the persisted UUID for a spatial entity. The corresponding list
             * structure is `XrSpatialComponentPersistenceListEXT; the corresponding data structure is
             * `code`XrSpatialPersistenceDataEXT` (Added by the `XR_EXT_spatial_persistence` extension).
             *
             * Generated from Godot docs: OpenXRSpatialEntityExtension.COMPONENT_TYPE_PERSISTENCE
             */
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
