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

    /**
     * Partitioning algorithm for creating the navigation mesh polys.
     *
     * Generated from Godot docs: NavigationMesh.set_sample_partition_type
     */
    fun setSamplePartitionType(samplePartitionType: Long) {
        ObjectCalls.ptrcallWithLongArg(setSamplePartitionTypeBind, handle, samplePartitionType)
    }

    /**
     * Partitioning algorithm for creating the navigation mesh polys.
     *
     * Generated from Godot docs: NavigationMesh.get_sample_partition_type
     */
    fun getSamplePartitionType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSamplePartitionTypeBind, handle)
    }

    /**
     * Determines which type of nodes will be parsed as geometry.
     *
     * Generated from Godot docs: NavigationMesh.set_parsed_geometry_type
     */
    fun setParsedGeometryType(geometryType: Long) {
        ObjectCalls.ptrcallWithLongArg(setParsedGeometryTypeBind, handle, geometryType)
    }

    /**
     * Determines which type of nodes will be parsed as geometry.
     *
     * Generated from Godot docs: NavigationMesh.get_parsed_geometry_type
     */
    fun getParsedGeometryType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getParsedGeometryTypeBind, handle)
    }

    /**
     * The physics layers to scan for static colliders. Only used when `geometry_parsed_geometry_type`
     * is `PARSED_GEOMETRY_STATIC_COLLIDERS` or `PARSED_GEOMETRY_BOTH`.
     *
     * Generated from Godot docs: NavigationMesh.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    /**
     * The physics layers to scan for static colliders. Only used when `geometry_parsed_geometry_type`
     * is `PARSED_GEOMETRY_STATIC_COLLIDERS` or `PARSED_GEOMETRY_BOTH`.
     *
     * Generated from Godot docs: NavigationMesh.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `geometry_collision_mask`,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationMesh.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `geometry_collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationMesh.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    /**
     * The source of the geometry used when baking.
     *
     * Generated from Godot docs: NavigationMesh.set_source_geometry_mode
     */
    fun setSourceGeometryMode(mask: Long) {
        ObjectCalls.ptrcallWithLongArg(setSourceGeometryModeBind, handle, mask)
    }

    /**
     * The source of the geometry used when baking.
     *
     * Generated from Godot docs: NavigationMesh.get_source_geometry_mode
     */
    fun getSourceGeometryMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceGeometryModeBind, handle)
    }

    /**
     * The name of the group to scan for geometry. Only used when `geometry_source_geometry_mode` is
     * `SOURCE_GEOMETRY_GROUPS_WITH_CHILDREN` or `SOURCE_GEOMETRY_GROUPS_EXPLICIT`.
     *
     * Generated from Godot docs: NavigationMesh.set_source_group_name
     */
    fun setSourceGroupName(mask: String) {
        ObjectCalls.ptrcallWithStringNameArg(setSourceGroupNameBind, handle, mask)
    }

    /**
     * The name of the group to scan for geometry. Only used when `geometry_source_geometry_mode` is
     * `SOURCE_GEOMETRY_GROUPS_WITH_CHILDREN` or `SOURCE_GEOMETRY_GROUPS_EXPLICIT`.
     *
     * Generated from Godot docs: NavigationMesh.get_source_group_name
     */
    fun getSourceGroupName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getSourceGroupNameBind, handle)
    }

    /**
     * The cell size used to rasterize the navigation mesh vertices on the XZ plane. Must match with
     * the cell size on the navigation map.
     *
     * Generated from Godot docs: NavigationMesh.set_cell_size
     */
    fun setCellSize(cellSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellSizeBind, handle, cellSize)
    }

    /**
     * The cell size used to rasterize the navigation mesh vertices on the XZ plane. Must match with
     * the cell size on the navigation map.
     *
     * Generated from Godot docs: NavigationMesh.get_cell_size
     */
    fun getCellSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellSizeBind, handle)
    }

    /**
     * The cell height used to rasterize the navigation mesh vertices on the Y axis. Must match with
     * the cell height on the navigation map.
     *
     * Generated from Godot docs: NavigationMesh.set_cell_height
     */
    fun setCellHeight(cellHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellHeightBind, handle, cellHeight)
    }

    /**
     * The cell height used to rasterize the navigation mesh vertices on the Y axis. Must match with
     * the cell height on the navigation map.
     *
     * Generated from Godot docs: NavigationMesh.get_cell_height
     */
    fun getCellHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellHeightBind, handle)
    }

    /**
     * The size of the non-navigable border around the bake bounding area. In conjunction with the
     * `filter_baking_aabb` and a `edge_max_error` value at `1.0` or below the border size can be used
     * to bake tile aligned navigation meshes without the tile edges being shrunk by `agent_radius`.
     * Note: If this value is not `0.0`, it will be rounded up to the nearest multiple of `cell_size`
     * during baking.
     *
     * Generated from Godot docs: NavigationMesh.set_border_size
     */
    fun setBorderSize(borderSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBorderSizeBind, handle, borderSize)
    }

    /**
     * The size of the non-navigable border around the bake bounding area. In conjunction with the
     * `filter_baking_aabb` and a `edge_max_error` value at `1.0` or below the border size can be used
     * to bake tile aligned navigation meshes without the tile edges being shrunk by `agent_radius`.
     * Note: If this value is not `0.0`, it will be rounded up to the nearest multiple of `cell_size`
     * during baking.
     *
     * Generated from Godot docs: NavigationMesh.get_border_size
     */
    fun getBorderSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBorderSizeBind, handle)
    }

    /**
     * The minimum floor to ceiling height that will still allow the floor area to be considered
     * walkable. Note: While baking, this value will be rounded up to the nearest multiple of
     * `cell_height`.
     *
     * Generated from Godot docs: NavigationMesh.set_agent_height
     */
    fun setAgentHeight(agentHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentHeightBind, handle, agentHeight)
    }

    /**
     * The minimum floor to ceiling height that will still allow the floor area to be considered
     * walkable. Note: While baking, this value will be rounded up to the nearest multiple of
     * `cell_height`.
     *
     * Generated from Godot docs: NavigationMesh.get_agent_height
     */
    fun getAgentHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentHeightBind, handle)
    }

    /**
     * The distance to erode/shrink the walkable area of the heightfield away from obstructions. Note:
     * While baking, this value will be rounded up to the nearest multiple of `cell_size`. Note: The
     * radius must be equal or higher than `0.0`. If the radius is `0.0`, it won't be possible to fix
     * invalid outline overlaps and other precision errors during the baking process. As a result, some
     * obstacles may be excluded incorrectly from the final navigation mesh, or may delete the
     * navigation mesh's polygons.
     *
     * Generated from Godot docs: NavigationMesh.set_agent_radius
     */
    fun setAgentRadius(agentRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentRadiusBind, handle, agentRadius)
    }

    /**
     * The distance to erode/shrink the walkable area of the heightfield away from obstructions. Note:
     * While baking, this value will be rounded up to the nearest multiple of `cell_size`. Note: The
     * radius must be equal or higher than `0.0`. If the radius is `0.0`, it won't be possible to fix
     * invalid outline overlaps and other precision errors during the baking process. As a result, some
     * obstacles may be excluded incorrectly from the final navigation mesh, or may delete the
     * navigation mesh's polygons.
     *
     * Generated from Godot docs: NavigationMesh.get_agent_radius
     */
    fun getAgentRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentRadiusBind, handle)
    }

    /**
     * The minimum ledge height that is considered to still be traversable. Note: While baking, this
     * value will be rounded down to the nearest multiple of `cell_height`.
     *
     * Generated from Godot docs: NavigationMesh.set_agent_max_climb
     */
    fun setAgentMaxClimb(agentMaxClimb: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentMaxClimbBind, handle, agentMaxClimb)
    }

    /**
     * The minimum ledge height that is considered to still be traversable. Note: While baking, this
     * value will be rounded down to the nearest multiple of `cell_height`.
     *
     * Generated from Godot docs: NavigationMesh.get_agent_max_climb
     */
    fun getAgentMaxClimb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentMaxClimbBind, handle)
    }

    /**
     * The maximum slope that is considered walkable, in degrees.
     *
     * Generated from Godot docs: NavigationMesh.set_agent_max_slope
     */
    fun setAgentMaxSlope(agentMaxSlope: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAgentMaxSlopeBind, handle, agentMaxSlope)
    }

    /**
     * The maximum slope that is considered walkable, in degrees.
     *
     * Generated from Godot docs: NavigationMesh.get_agent_max_slope
     */
    fun getAgentMaxSlope(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentMaxSlopeBind, handle)
    }

    /**
     * The minimum size of a region for it to be created. Note: This value will be squared to calculate
     * the minimum number of cells allowed to form isolated island areas. For example, a value of 8
     * will set the number of cells to 64.
     *
     * Generated from Godot docs: NavigationMesh.set_region_min_size
     */
    fun setRegionMinSize(regionMinSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRegionMinSizeBind, handle, regionMinSize)
    }

    /**
     * The minimum size of a region for it to be created. Note: This value will be squared to calculate
     * the minimum number of cells allowed to form isolated island areas. For example, a value of 8
     * will set the number of cells to 64.
     *
     * Generated from Godot docs: NavigationMesh.get_region_min_size
     */
    fun getRegionMinSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRegionMinSizeBind, handle)
    }

    /**
     * Any regions with a size smaller than this will be merged with larger regions if possible. Note:
     * This value will be squared to calculate the number of cells. For example, a value of 20 will set
     * the number of cells to 400.
     *
     * Generated from Godot docs: NavigationMesh.set_region_merge_size
     */
    fun setRegionMergeSize(regionMergeSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRegionMergeSizeBind, handle, regionMergeSize)
    }

    /**
     * Any regions with a size smaller than this will be merged with larger regions if possible. Note:
     * This value will be squared to calculate the number of cells. For example, a value of 20 will set
     * the number of cells to 400.
     *
     * Generated from Godot docs: NavigationMesh.get_region_merge_size
     */
    fun getRegionMergeSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRegionMergeSizeBind, handle)
    }

    /**
     * The maximum allowed length for contour edges along the border of the mesh. A value of `0.0`
     * disables this feature. Note: While baking, this value will be rounded up to the nearest multiple
     * of `cell_size`.
     *
     * Generated from Godot docs: NavigationMesh.set_edge_max_length
     */
    fun setEdgeMaxLength(edgeMaxLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEdgeMaxLengthBind, handle, edgeMaxLength)
    }

    /**
     * The maximum allowed length for contour edges along the border of the mesh. A value of `0.0`
     * disables this feature. Note: While baking, this value will be rounded up to the nearest multiple
     * of `cell_size`.
     *
     * Generated from Godot docs: NavigationMesh.get_edge_max_length
     */
    fun getEdgeMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEdgeMaxLengthBind, handle)
    }

    /**
     * The maximum distance a simplified contour's border edges should deviate the original raw
     * contour.
     *
     * Generated from Godot docs: NavigationMesh.set_edge_max_error
     */
    fun setEdgeMaxError(edgeMaxError: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEdgeMaxErrorBind, handle, edgeMaxError)
    }

    /**
     * The maximum distance a simplified contour's border edges should deviate the original raw
     * contour.
     *
     * Generated from Godot docs: NavigationMesh.get_edge_max_error
     */
    fun getEdgeMaxError(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEdgeMaxErrorBind, handle)
    }

    /**
     * The maximum number of vertices allowed for polygons generated during the contour to polygon
     * conversion process.
     *
     * Generated from Godot docs: NavigationMesh.set_vertices_per_polygon
     */
    fun setVerticesPerPolygon(verticesPerPolygon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVerticesPerPolygonBind, handle, verticesPerPolygon)
    }

    /**
     * The maximum number of vertices allowed for polygons generated during the contour to polygon
     * conversion process.
     *
     * Generated from Godot docs: NavigationMesh.get_vertices_per_polygon
     */
    fun getVerticesPerPolygon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVerticesPerPolygonBind, handle)
    }

    /**
     * The sampling distance to use when generating the detail mesh, in cell unit.
     *
     * Generated from Godot docs: NavigationMesh.set_detail_sample_distance
     */
    fun setDetailSampleDistance(detailSampleDist: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDetailSampleDistanceBind, handle, detailSampleDist)
    }

    /**
     * The sampling distance to use when generating the detail mesh, in cell unit.
     *
     * Generated from Godot docs: NavigationMesh.get_detail_sample_distance
     */
    fun getDetailSampleDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDetailSampleDistanceBind, handle)
    }

    /**
     * The maximum distance the detail mesh surface should deviate from heightfield, in cell unit.
     *
     * Generated from Godot docs: NavigationMesh.set_detail_sample_max_error
     */
    fun setDetailSampleMaxError(detailSampleMaxError: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDetailSampleMaxErrorBind, handle, detailSampleMaxError)
    }

    /**
     * The maximum distance the detail mesh surface should deviate from heightfield, in cell unit.
     *
     * Generated from Godot docs: NavigationMesh.get_detail_sample_max_error
     */
    fun getDetailSampleMaxError(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDetailSampleMaxErrorBind, handle)
    }

    /**
     * If `true`, marks non-walkable spans as walkable if their maximum is within `agent_max_climb` of
     * a walkable neighbor.
     *
     * Generated from Godot docs: NavigationMesh.set_filter_low_hanging_obstacles
     */
    fun setFilterLowHangingObstacles(filterLowHangingObstacles: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterLowHangingObstaclesBind, handle, filterLowHangingObstacles)
    }

    /**
     * If `true`, marks non-walkable spans as walkable if their maximum is within `agent_max_climb` of
     * a walkable neighbor.
     *
     * Generated from Godot docs: NavigationMesh.get_filter_low_hanging_obstacles
     */
    fun getFilterLowHangingObstacles(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterLowHangingObstaclesBind, handle)
    }

    /**
     * If `true`, marks spans that are ledges as non-walkable.
     *
     * Generated from Godot docs: NavigationMesh.set_filter_ledge_spans
     */
    fun setFilterLedgeSpans(filterLedgeSpans: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterLedgeSpansBind, handle, filterLedgeSpans)
    }

    /**
     * If `true`, marks spans that are ledges as non-walkable.
     *
     * Generated from Godot docs: NavigationMesh.get_filter_ledge_spans
     */
    fun getFilterLedgeSpans(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterLedgeSpansBind, handle)
    }

    /**
     * If `true`, marks walkable spans as not walkable if the clearance above the span is less than
     * `agent_height`.
     *
     * Generated from Godot docs: NavigationMesh.set_filter_walkable_low_height_spans
     */
    fun setFilterWalkableLowHeightSpans(filterWalkableLowHeightSpans: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterWalkableLowHeightSpansBind, handle, filterWalkableLowHeightSpans)
    }

    /**
     * If `true`, marks walkable spans as not walkable if the clearance above the span is less than
     * `agent_height`.
     *
     * Generated from Godot docs: NavigationMesh.get_filter_walkable_low_height_spans
     */
    fun getFilterWalkableLowHeightSpans(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFilterWalkableLowHeightSpansBind, handle)
    }

    /**
     * If the baking `AABB` has a volume the navigation mesh baking will be restricted to its enclosing
     * area.
     *
     * Generated from Godot docs: NavigationMesh.set_filter_baking_aabb
     */
    fun setFilterBakingAabb(bakingAabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setFilterBakingAabbBind, handle, bakingAabb)
    }

    /**
     * If the baking `AABB` has a volume the navigation mesh baking will be restricted to its enclosing
     * area.
     *
     * Generated from Godot docs: NavigationMesh.get_filter_baking_aabb
     */
    fun getFilterBakingAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getFilterBakingAabbBind, handle)
    }

    /**
     * The position offset applied to the `filter_baking_aabb` `AABB`.
     *
     * Generated from Godot docs: NavigationMesh.set_filter_baking_aabb_offset
     */
    fun setFilterBakingAabbOffset(bakingAabbOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setFilterBakingAabbOffsetBind, handle, bakingAabbOffset)
    }

    /**
     * The position offset applied to the `filter_baking_aabb` `AABB`.
     *
     * Generated from Godot docs: NavigationMesh.get_filter_baking_aabb_offset
     */
    fun getFilterBakingAabbOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getFilterBakingAabbOffsetBind, handle)
    }

    /**
     * Sets the vertices that can be then indexed to create polygons with the `add_polygon` method.
     *
     * Generated from Godot docs: NavigationMesh.set_vertices
     */
    fun setVertices(vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
    }

    /**
     * Returns a `PackedVector3Array` containing all the vertices being used to create the polygons.
     *
     * Generated from Godot docs: NavigationMesh.get_vertices
     */
    fun getVertices(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
    }

    /**
     * Adds a polygon using the indices of the vertices you get when calling `get_vertices`.
     *
     * Generated from Godot docs: NavigationMesh.add_polygon
     */
    fun addPolygon(polygon: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(addPolygonBind, handle, polygon)
    }

    /**
     * Returns the number of polygons in the navigation mesh.
     *
     * Generated from Godot docs: NavigationMesh.get_polygon_count
     */
    fun getPolygonCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPolygonCountBind, handle)
    }

    /**
     * Returns a `PackedInt32Array` containing the indices of the vertices of a created polygon.
     *
     * Generated from Godot docs: NavigationMesh.get_polygon
     */
    fun getPolygon(idx: Int): List<Int> {
        return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getPolygonBind, handle, idx)
    }

    /**
     * Clears the array of polygons, but it doesn't clear the array of vertices.
     *
     * Generated from Godot docs: NavigationMesh.clear_polygons
     */
    fun clearPolygons() {
        ObjectCalls.ptrcallNoArgs(clearPolygonsBind, handle)
    }

    /**
     * Initializes the navigation mesh by setting the vertices and indices according to a `Mesh`. Note:
     * The given `mesh` must be of type `Mesh.PRIMITIVE_TRIANGLES` and have an index array.
     *
     * Generated from Godot docs: NavigationMesh.create_from_mesh
     */
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
