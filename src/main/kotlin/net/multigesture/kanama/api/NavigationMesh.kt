package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3

/**
 * A navigation mesh that defines traversable areas and obstacles.
 *
 * Generated from Godot docs: NavigationMesh
 */
class NavigationMesh(handle: MemorySegment) : Resource(handle) {
    var vertices: List<Vector3>
        @JvmName("verticesProperty")
        get() = getVertices()
        @JvmName("setVerticesProperty")
        set(value) = setVertices(value)

    var samplePartitionType: Long
        @JvmName("samplePartitionTypeProperty")
        get() = getSamplePartitionType()
        @JvmName("setSamplePartitionTypeProperty")
        set(value) = setSamplePartitionType(value)

    var geometryParsedGeometryType: Long
        @JvmName("geometryParsedGeometryTypeProperty")
        get() = getParsedGeometryType()
        @JvmName("setGeometryParsedGeometryTypeProperty")
        set(value) = setParsedGeometryType(value)

    var geometryCollisionMask: Long
        @JvmName("geometryCollisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setGeometryCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var geometrySourceGeometryMode: Long
        @JvmName("geometrySourceGeometryModeProperty")
        get() = getSourceGeometryMode()
        @JvmName("setGeometrySourceGeometryModeProperty")
        set(value) = setSourceGeometryMode(value)

    var geometrySourceGroupName: String
        @JvmName("geometrySourceGroupNameProperty")
        get() = getSourceGroupName()
        @JvmName("setGeometrySourceGroupNameProperty")
        set(value) = setSourceGroupName(value)

    var cellSize: Double
        @JvmName("cellSizeProperty")
        get() = getCellSize()
        @JvmName("setCellSizeProperty")
        set(value) = setCellSize(value)

    var cellHeight: Double
        @JvmName("cellHeightProperty")
        get() = getCellHeight()
        @JvmName("setCellHeightProperty")
        set(value) = setCellHeight(value)

    var borderSize: Double
        @JvmName("borderSizeProperty")
        get() = getBorderSize()
        @JvmName("setBorderSizeProperty")
        set(value) = setBorderSize(value)

    var agentHeight: Double
        @JvmName("agentHeightProperty")
        get() = getAgentHeight()
        @JvmName("setAgentHeightProperty")
        set(value) = setAgentHeight(value)

    var agentRadius: Double
        @JvmName("agentRadiusProperty")
        get() = getAgentRadius()
        @JvmName("setAgentRadiusProperty")
        set(value) = setAgentRadius(value)

    var agentMaxClimb: Double
        @JvmName("agentMaxClimbProperty")
        get() = getAgentMaxClimb()
        @JvmName("setAgentMaxClimbProperty")
        set(value) = setAgentMaxClimb(value)

    var agentMaxSlope: Double
        @JvmName("agentMaxSlopeProperty")
        get() = getAgentMaxSlope()
        @JvmName("setAgentMaxSlopeProperty")
        set(value) = setAgentMaxSlope(value)

    var regionMinSize: Double
        @JvmName("regionMinSizeProperty")
        get() = getRegionMinSize()
        @JvmName("setRegionMinSizeProperty")
        set(value) = setRegionMinSize(value)

    var regionMergeSize: Double
        @JvmName("regionMergeSizeProperty")
        get() = getRegionMergeSize()
        @JvmName("setRegionMergeSizeProperty")
        set(value) = setRegionMergeSize(value)

    var edgeMaxLength: Double
        @JvmName("edgeMaxLengthProperty")
        get() = getEdgeMaxLength()
        @JvmName("setEdgeMaxLengthProperty")
        set(value) = setEdgeMaxLength(value)

    var edgeMaxError: Double
        @JvmName("edgeMaxErrorProperty")
        get() = getEdgeMaxError()
        @JvmName("setEdgeMaxErrorProperty")
        set(value) = setEdgeMaxError(value)

    var verticesPerPolygon: Double
        @JvmName("verticesPerPolygonProperty")
        get() = getVerticesPerPolygon()
        @JvmName("setVerticesPerPolygonProperty")
        set(value) = setVerticesPerPolygon(value)

    var detailSampleDistance: Double
        @JvmName("detailSampleDistanceProperty")
        get() = getDetailSampleDistance()
        @JvmName("setDetailSampleDistanceProperty")
        set(value) = setDetailSampleDistance(value)

    var detailSampleMaxError: Double
        @JvmName("detailSampleMaxErrorProperty")
        get() = getDetailSampleMaxError()
        @JvmName("setDetailSampleMaxErrorProperty")
        set(value) = setDetailSampleMaxError(value)

    var filterLowHangingObstacles: Boolean
        @JvmName("filterLowHangingObstaclesProperty")
        get() = getFilterLowHangingObstacles()
        @JvmName("setFilterLowHangingObstaclesProperty")
        set(value) = setFilterLowHangingObstacles(value)

    var filterLedgeSpans: Boolean
        @JvmName("filterLedgeSpansProperty")
        get() = getFilterLedgeSpans()
        @JvmName("setFilterLedgeSpansProperty")
        set(value) = setFilterLedgeSpans(value)

    var filterWalkableLowHeightSpans: Boolean
        @JvmName("filterWalkableLowHeightSpansProperty")
        get() = getFilterWalkableLowHeightSpans()
        @JvmName("setFilterWalkableLowHeightSpansProperty")
        set(value) = setFilterWalkableLowHeightSpans(value)

    var filterBakingAabb: AABB
        @JvmName("filterBakingAabbProperty")
        get() = getFilterBakingAabb()
        @JvmName("setFilterBakingAabbProperty")
        set(value) = setFilterBakingAabb(value)

    var filterBakingAabbOffset: Vector3
        @JvmName("filterBakingAabbOffsetProperty")
        get() = getFilterBakingAabbOffset()
        @JvmName("setFilterBakingAabbOffsetProperty")
        set(value) = setFilterBakingAabbOffset(value)

    fun setSamplePartitionType(samplePartitionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setSamplePartitionTypeBind, handle, samplePartitionType)
    }

    fun getSamplePartitionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSamplePartitionTypeBind, handle)
    }

    fun setParsedGeometryType(geometryType: Long) {
        ObjectCalls.ptrcallWithLongArg(setParsedGeometryTypeBind, handle, geometryType)
    }

    fun getParsedGeometryType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getParsedGeometryTypeBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    fun setSourceGeometryMode(mask: Long) {
        ObjectCalls.ptrcallWithLongArg(setSourceGeometryModeBind, handle, mask)
    }

    fun getSourceGeometryMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceGeometryModeBind, handle)
    }

    fun setSourceGroupName(mask: String) {
        ObjectCalls.ptrcallWithStringNameArg(setSourceGroupNameBind, handle, mask)
    }

    fun getSourceGroupName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getSourceGroupNameBind, handle)
    }

    fun setCellSize(cellSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellSizeBind, handle, cellSize)
    }

    fun getCellSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellSizeBind, handle)
    }

    fun setCellHeight(cellHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellHeightBind, handle, cellHeight)
    }

    fun getCellHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellHeightBind, handle)
    }

    fun setBorderSize(borderSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBorderSizeBind, handle, borderSize)
    }

    fun getBorderSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBorderSizeBind, handle)
    }

    fun setAgentHeight(agentHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentHeightBind, handle, agentHeight)
    }

    fun getAgentHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentHeightBind, handle)
    }

    fun setAgentRadius(agentRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentRadiusBind, handle, agentRadius)
    }

    fun getAgentRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentRadiusBind, handle)
    }

    fun setAgentMaxClimb(agentMaxClimb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentMaxClimbBind, handle, agentMaxClimb)
    }

    fun getAgentMaxClimb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentMaxClimbBind, handle)
    }

    fun setAgentMaxSlope(agentMaxSlope: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentMaxSlopeBind, handle, agentMaxSlope)
    }

    fun getAgentMaxSlope(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentMaxSlopeBind, handle)
    }

    fun setRegionMinSize(regionMinSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRegionMinSizeBind, handle, regionMinSize)
    }

    fun getRegionMinSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRegionMinSizeBind, handle)
    }

    fun setRegionMergeSize(regionMergeSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRegionMergeSizeBind, handle, regionMergeSize)
    }

    fun getRegionMergeSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRegionMergeSizeBind, handle)
    }

    fun setEdgeMaxLength(edgeMaxLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEdgeMaxLengthBind, handle, edgeMaxLength)
    }

    fun getEdgeMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEdgeMaxLengthBind, handle)
    }

    fun setEdgeMaxError(edgeMaxError: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEdgeMaxErrorBind, handle, edgeMaxError)
    }

    fun getEdgeMaxError(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEdgeMaxErrorBind, handle)
    }

    fun setVerticesPerPolygon(verticesPerPolygon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVerticesPerPolygonBind, handle, verticesPerPolygon)
    }

    fun getVerticesPerPolygon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVerticesPerPolygonBind, handle)
    }

    fun setDetailSampleDistance(detailSampleDist: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDetailSampleDistanceBind, handle, detailSampleDist)
    }

    fun getDetailSampleDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDetailSampleDistanceBind, handle)
    }

    fun setDetailSampleMaxError(detailSampleMaxError: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDetailSampleMaxErrorBind, handle, detailSampleMaxError)
    }

    fun getDetailSampleMaxError(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDetailSampleMaxErrorBind, handle)
    }

    fun setFilterLowHangingObstacles(filterLowHangingObstacles: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterLowHangingObstaclesBind, handle, filterLowHangingObstacles)
    }

    fun getFilterLowHangingObstacles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterLowHangingObstaclesBind, handle)
    }

    fun setFilterLedgeSpans(filterLedgeSpans: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterLedgeSpansBind, handle, filterLedgeSpans)
    }

    fun getFilterLedgeSpans(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterLedgeSpansBind, handle)
    }

    fun setFilterWalkableLowHeightSpans(filterWalkableLowHeightSpans: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterWalkableLowHeightSpansBind, handle, filterWalkableLowHeightSpans)
    }

    fun getFilterWalkableLowHeightSpans(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterWalkableLowHeightSpansBind, handle)
    }

    fun setFilterBakingAabb(bakingAabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setFilterBakingAabbBind, handle, bakingAabb)
    }

    fun getFilterBakingAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getFilterBakingAabbBind, handle)
    }

    fun setFilterBakingAabbOffset(bakingAabbOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setFilterBakingAabbOffsetBind, handle, bakingAabbOffset)
    }

    fun getFilterBakingAabbOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getFilterBakingAabbOffsetBind, handle)
    }

    fun setVertices(vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
    }

    fun getVertices(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
    }

    fun addPolygon(polygon: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(addPolygonBind, handle, polygon)
    }

    fun getPolygonCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPolygonCountBind, handle)
    }

    fun getPolygon(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getPolygonBind, handle, idx)
    }

    fun clearPolygons() {
        ObjectCalls.ptrcallNoArgs(clearPolygonsBind, handle)
    }

    fun createFromMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(createFromMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Clears the internal arrays for vertices and polygon indices.
     *
     * Generated from Godot docs: NavigationMesh.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
        const val SAMPLE_PARTITION_WATERSHED: Long = 0L
        const val SAMPLE_PARTITION_MONOTONE: Long = 1L
        const val SAMPLE_PARTITION_LAYERS: Long = 2L
        const val SAMPLE_PARTITION_MAX: Long = 3L
        const val PARSED_GEOMETRY_MESH_INSTANCES: Long = 0L
        const val PARSED_GEOMETRY_STATIC_COLLIDERS: Long = 1L
        const val PARSED_GEOMETRY_BOTH: Long = 2L
        const val PARSED_GEOMETRY_MAX: Long = 3L
        const val SOURCE_GEOMETRY_ROOT_NODE_CHILDREN: Long = 0L
        const val SOURCE_GEOMETRY_GROUPS_WITH_CHILDREN: Long = 1L
        const val SOURCE_GEOMETRY_GROUPS_EXPLICIT: Long = 2L
        const val SOURCE_GEOMETRY_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationMesh? =
            if (handle.address() == 0L) null else NavigationMesh(handle)

        private const val SET_SAMPLE_PARTITION_TYPE_HASH = 2472437533L
        private val setSamplePartitionTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_sample_partition_type", SET_SAMPLE_PARTITION_TYPE_HASH)
        }

        private const val GET_SAMPLE_PARTITION_TYPE_HASH = 833513918L
        private val getSamplePartitionTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_sample_partition_type", GET_SAMPLE_PARTITION_TYPE_HASH)
        }

        private const val SET_PARSED_GEOMETRY_TYPE_HASH = 3064713163L
        private val setParsedGeometryTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_parsed_geometry_type", SET_PARSED_GEOMETRY_TYPE_HASH)
        }

        private const val GET_PARSED_GEOMETRY_TYPE_HASH = 3928011953L
        private val getParsedGeometryTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_parsed_geometry_type", GET_PARSED_GEOMETRY_TYPE_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_SOURCE_GEOMETRY_MODE_HASH = 2700825194L
        private val setSourceGeometryModeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_source_geometry_mode", SET_SOURCE_GEOMETRY_MODE_HASH)
        }

        private const val GET_SOURCE_GEOMETRY_MODE_HASH = 2770484141L
        private val getSourceGeometryModeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_source_geometry_mode", GET_SOURCE_GEOMETRY_MODE_HASH)
        }

        private const val SET_SOURCE_GROUP_NAME_HASH = 3304788590L
        private val setSourceGroupNameBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_source_group_name", SET_SOURCE_GROUP_NAME_HASH)
        }

        private const val GET_SOURCE_GROUP_NAME_HASH = 2002593661L
        private val getSourceGroupNameBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_source_group_name", GET_SOURCE_GROUP_NAME_HASH)
        }

        private const val SET_CELL_SIZE_HASH = 373806689L
        private val setCellSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_cell_size", SET_CELL_SIZE_HASH)
        }

        private const val GET_CELL_SIZE_HASH = 1740695150L
        private val getCellSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_cell_size", GET_CELL_SIZE_HASH)
        }

        private const val SET_CELL_HEIGHT_HASH = 373806689L
        private val setCellHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_cell_height", SET_CELL_HEIGHT_HASH)
        }

        private const val GET_CELL_HEIGHT_HASH = 1740695150L
        private val getCellHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_cell_height", GET_CELL_HEIGHT_HASH)
        }

        private const val SET_BORDER_SIZE_HASH = 373806689L
        private val setBorderSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_border_size", SET_BORDER_SIZE_HASH)
        }

        private const val GET_BORDER_SIZE_HASH = 1740695150L
        private val getBorderSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_border_size", GET_BORDER_SIZE_HASH)
        }

        private const val SET_AGENT_HEIGHT_HASH = 373806689L
        private val setAgentHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_agent_height", SET_AGENT_HEIGHT_HASH)
        }

        private const val GET_AGENT_HEIGHT_HASH = 1740695150L
        private val getAgentHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_agent_height", GET_AGENT_HEIGHT_HASH)
        }

        private const val SET_AGENT_RADIUS_HASH = 373806689L
        private val setAgentRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_agent_radius", SET_AGENT_RADIUS_HASH)
        }

        private const val GET_AGENT_RADIUS_HASH = 191475506L
        private val getAgentRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_agent_radius", GET_AGENT_RADIUS_HASH)
        }

        private const val SET_AGENT_MAX_CLIMB_HASH = 373806689L
        private val setAgentMaxClimbBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_agent_max_climb", SET_AGENT_MAX_CLIMB_HASH)
        }

        private const val GET_AGENT_MAX_CLIMB_HASH = 1740695150L
        private val getAgentMaxClimbBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_agent_max_climb", GET_AGENT_MAX_CLIMB_HASH)
        }

        private const val SET_AGENT_MAX_SLOPE_HASH = 373806689L
        private val setAgentMaxSlopeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_agent_max_slope", SET_AGENT_MAX_SLOPE_HASH)
        }

        private const val GET_AGENT_MAX_SLOPE_HASH = 1740695150L
        private val getAgentMaxSlopeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_agent_max_slope", GET_AGENT_MAX_SLOPE_HASH)
        }

        private const val SET_REGION_MIN_SIZE_HASH = 373806689L
        private val setRegionMinSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_region_min_size", SET_REGION_MIN_SIZE_HASH)
        }

        private const val GET_REGION_MIN_SIZE_HASH = 1740695150L
        private val getRegionMinSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_region_min_size", GET_REGION_MIN_SIZE_HASH)
        }

        private const val SET_REGION_MERGE_SIZE_HASH = 373806689L
        private val setRegionMergeSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_region_merge_size", SET_REGION_MERGE_SIZE_HASH)
        }

        private const val GET_REGION_MERGE_SIZE_HASH = 1740695150L
        private val getRegionMergeSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_region_merge_size", GET_REGION_MERGE_SIZE_HASH)
        }

        private const val SET_EDGE_MAX_LENGTH_HASH = 373806689L
        private val setEdgeMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_edge_max_length", SET_EDGE_MAX_LENGTH_HASH)
        }

        private const val GET_EDGE_MAX_LENGTH_HASH = 1740695150L
        private val getEdgeMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_edge_max_length", GET_EDGE_MAX_LENGTH_HASH)
        }

        private const val SET_EDGE_MAX_ERROR_HASH = 373806689L
        private val setEdgeMaxErrorBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_edge_max_error", SET_EDGE_MAX_ERROR_HASH)
        }

        private const val GET_EDGE_MAX_ERROR_HASH = 1740695150L
        private val getEdgeMaxErrorBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_edge_max_error", GET_EDGE_MAX_ERROR_HASH)
        }

        private const val SET_VERTICES_PER_POLYGON_HASH = 373806689L
        private val setVerticesPerPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_vertices_per_polygon", SET_VERTICES_PER_POLYGON_HASH)
        }

        private const val GET_VERTICES_PER_POLYGON_HASH = 1740695150L
        private val getVerticesPerPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_vertices_per_polygon", GET_VERTICES_PER_POLYGON_HASH)
        }

        private const val SET_DETAIL_SAMPLE_DISTANCE_HASH = 373806689L
        private val setDetailSampleDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_detail_sample_distance", SET_DETAIL_SAMPLE_DISTANCE_HASH)
        }

        private const val GET_DETAIL_SAMPLE_DISTANCE_HASH = 1740695150L
        private val getDetailSampleDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_detail_sample_distance", GET_DETAIL_SAMPLE_DISTANCE_HASH)
        }

        private const val SET_DETAIL_SAMPLE_MAX_ERROR_HASH = 373806689L
        private val setDetailSampleMaxErrorBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_detail_sample_max_error", SET_DETAIL_SAMPLE_MAX_ERROR_HASH)
        }

        private const val GET_DETAIL_SAMPLE_MAX_ERROR_HASH = 1740695150L
        private val getDetailSampleMaxErrorBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_detail_sample_max_error", GET_DETAIL_SAMPLE_MAX_ERROR_HASH)
        }

        private const val SET_FILTER_LOW_HANGING_OBSTACLES_HASH = 2586408642L
        private val setFilterLowHangingObstaclesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_filter_low_hanging_obstacles", SET_FILTER_LOW_HANGING_OBSTACLES_HASH)
        }

        private const val GET_FILTER_LOW_HANGING_OBSTACLES_HASH = 36873697L
        private val getFilterLowHangingObstaclesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_filter_low_hanging_obstacles", GET_FILTER_LOW_HANGING_OBSTACLES_HASH)
        }

        private const val SET_FILTER_LEDGE_SPANS_HASH = 2586408642L
        private val setFilterLedgeSpansBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_filter_ledge_spans", SET_FILTER_LEDGE_SPANS_HASH)
        }

        private const val GET_FILTER_LEDGE_SPANS_HASH = 36873697L
        private val getFilterLedgeSpansBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_filter_ledge_spans", GET_FILTER_LEDGE_SPANS_HASH)
        }

        private const val SET_FILTER_WALKABLE_LOW_HEIGHT_SPANS_HASH = 2586408642L
        private val setFilterWalkableLowHeightSpansBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_filter_walkable_low_height_spans", SET_FILTER_WALKABLE_LOW_HEIGHT_SPANS_HASH)
        }

        private const val GET_FILTER_WALKABLE_LOW_HEIGHT_SPANS_HASH = 36873697L
        private val getFilterWalkableLowHeightSpansBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_filter_walkable_low_height_spans", GET_FILTER_WALKABLE_LOW_HEIGHT_SPANS_HASH)
        }

        private const val SET_FILTER_BAKING_AABB_HASH = 259215842L
        private val setFilterBakingAabbBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_filter_baking_aabb", SET_FILTER_BAKING_AABB_HASH)
        }

        private const val GET_FILTER_BAKING_AABB_HASH = 1068685055L
        private val getFilterBakingAabbBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_filter_baking_aabb", GET_FILTER_BAKING_AABB_HASH)
        }

        private const val SET_FILTER_BAKING_AABB_OFFSET_HASH = 3460891852L
        private val setFilterBakingAabbOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_filter_baking_aabb_offset", SET_FILTER_BAKING_AABB_OFFSET_HASH)
        }

        private const val GET_FILTER_BAKING_AABB_OFFSET_HASH = 3360562783L
        private val getFilterBakingAabbOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_filter_baking_aabb_offset", GET_FILTER_BAKING_AABB_OFFSET_HASH)
        }

        private const val SET_VERTICES_HASH = 334873810L
        private val setVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "set_vertices", SET_VERTICES_HASH)
        }

        private const val GET_VERTICES_HASH = 497664490L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_vertices", GET_VERTICES_HASH)
        }

        private const val ADD_POLYGON_HASH = 3614634198L
        private val addPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "add_polygon", ADD_POLYGON_HASH)
        }

        private const val GET_POLYGON_COUNT_HASH = 3905245786L
        private val getPolygonCountBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_polygon_count", GET_POLYGON_COUNT_HASH)
        }

        private const val GET_POLYGON_HASH = 3668444399L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "get_polygon", GET_POLYGON_HASH)
        }

        private const val CLEAR_POLYGONS_HASH = 3218959716L
        private val clearPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "clear_polygons", CLEAR_POLYGONS_HASH)
        }

        private const val CREATE_FROM_MESH_HASH = 194775623L
        private val createFromMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "create_from_mesh", CREATE_FROM_MESH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("NavigationMesh", "clear", CLEAR_HASH)
        }
    }
}
