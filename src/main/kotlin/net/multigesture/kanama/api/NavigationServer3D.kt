package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A server interface for low-level 3D navigation access.
 *
 * Generated from Godot docs: NavigationServer3D
 */
object NavigationServer3D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("NavigationServer3D")
    }

    const val INFO_ACTIVE_MAPS: Long = 0L
    const val INFO_REGION_COUNT: Long = 1L
    const val INFO_AGENT_COUNT: Long = 2L
    const val INFO_LINK_COUNT: Long = 3L
    const val INFO_POLYGON_COUNT: Long = 4L
    const val INFO_EDGE_COUNT: Long = 5L
    const val INFO_EDGE_MERGE_COUNT: Long = 6L
    const val INFO_EDGE_CONNECTION_COUNT: Long = 7L
    const val INFO_EDGE_FREE_COUNT: Long = 8L
    const val INFO_OBSTACLE_COUNT: Long = 9L

    /**
     * Returns all created navigation map `RID`s on the NavigationServer. This returns both 2D and 3D
     * created navigation maps as there is technically no distinction between them.
     *
     * Generated from Godot docs: NavigationServer3D.get_maps
     */
    @JvmStatic
    fun getMaps(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getMapsBind, singleton)
    }

    /**
     * Create a new map.
     *
     * Generated from Godot docs: NavigationServer3D.map_create
     */
    @JvmStatic
    fun mapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(mapCreateBind, singleton)
    }

    /**
     * Sets the map active.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_active
     */
    @JvmStatic
    fun mapSetActive(map: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetActiveBind, singleton, map, active)
    }

    /**
     * Returns `true` if the map is active.
     *
     * Generated from Godot docs: NavigationServer3D.map_is_active
     */
    @JvmStatic
    fun mapIsActive(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapIsActiveBind, singleton, map)
    }

    /**
     * Sets the map up direction.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_up
     */
    @JvmStatic
    fun mapSetUp(map: RID, up: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(mapSetUpBind, singleton, map, up)
    }

    /**
     * Returns the map's up direction.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_up
     */
    @JvmStatic
    fun mapGetUp(map: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(mapGetUpBind, singleton, map)
    }

    /**
     * Sets the map cell size used to rasterize the navigation mesh vertices on the XZ plane. Must
     * match with the cell size of the used navigation meshes.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_cell_size
     */
    @JvmStatic
    fun mapSetCellSize(map: RID, cellSize: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetCellSizeBind, singleton, map, cellSize)
    }

    /**
     * Returns the map cell size used to rasterize the navigation mesh vertices on the XZ plane.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_cell_size
     */
    @JvmStatic
    fun mapGetCellSize(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetCellSizeBind, singleton, map)
    }

    /**
     * Sets the map cell height used to rasterize the navigation mesh vertices on the Y axis. Must
     * match with the cell height of the used navigation meshes.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_cell_height
     */
    @JvmStatic
    fun mapSetCellHeight(map: RID, cellHeight: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetCellHeightBind, singleton, map, cellHeight)
    }

    /**
     * Returns the map cell height used to rasterize the navigation mesh vertices on the Y axis.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_cell_height
     */
    @JvmStatic
    fun mapGetCellHeight(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetCellHeightBind, singleton, map)
    }

    /**
     * Set the map's internal merge rasterizer cell scale used to control merging sensitivity.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_merge_rasterizer_cell_scale
     */
    @JvmStatic
    fun mapSetMergeRasterizerCellScale(map: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetMergeRasterizerCellScaleBind, singleton, map, scale)
    }

    /**
     * Returns map's internal merge rasterizer cell scale.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_merge_rasterizer_cell_scale
     */
    @JvmStatic
    fun mapGetMergeRasterizerCellScale(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetMergeRasterizerCellScaleBind, singleton, map)
    }

    /**
     * Set the navigation `map` edge connection use. If `enabled` is `true`, the navigation map allows
     * navigation regions to use edge connections to connect with other navigation regions within
     * proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_use_edge_connections
     */
    @JvmStatic
    fun mapSetUseEdgeConnections(map: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetUseEdgeConnectionsBind, singleton, map, enabled)
    }

    /**
     * Returns `true` if the navigation `map` allows navigation regions to use edge connections to
     * connect with other navigation regions within proximity of the navigation map edge connection
     * margin.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_use_edge_connections
     */
    @JvmStatic
    fun mapGetUseEdgeConnections(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapGetUseEdgeConnectionsBind, singleton, map)
    }

    /**
     * Set the map edge connection margin used to weld the compatible region edges.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_edge_connection_margin
     */
    @JvmStatic
    fun mapSetEdgeConnectionMargin(map: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetEdgeConnectionMarginBind, singleton, map, margin)
    }

    /**
     * Returns the edge connection margin of the map. This distance is the minimum vertex distance
     * needed to connect two edges from different regions.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_edge_connection_margin
     */
    @JvmStatic
    fun mapGetEdgeConnectionMargin(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetEdgeConnectionMarginBind, singleton, map)
    }

    /**
     * Set the map's link connection radius used to connect links to navigation polygons.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_link_connection_radius
     */
    @JvmStatic
    fun mapSetLinkConnectionRadius(map: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetLinkConnectionRadiusBind, singleton, map, radius)
    }

    /**
     * Returns the link connection radius of the map. This distance is the maximum range any link will
     * search for navigation mesh polygons to connect to.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_link_connection_radius
     */
    @JvmStatic
    fun mapGetLinkConnectionRadius(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetLinkConnectionRadiusBind, singleton, map)
    }

    /**
     * Returns the navigation path to reach the destination from the origin. `navigation_layers` is a
     * bitmask of all region navigation layers that are allowed to be in the path.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_path
     */
    @JvmStatic
    fun mapGetPath(map: RID, origin: Vector3, destination: Vector3, optimize: Boolean, navigationLayers: Long = 1L): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List(mapGetPathBind, singleton, map, origin, destination, optimize, navigationLayers)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `start` and `end` segment on
     * the navigation `map`. If `use_collision` is `true`, a closest point test is only done when the
     * segment intersects with the navigation mesh surface.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point_to_segment
     */
    @JvmStatic
    fun mapGetClosestPointToSegment(map: RID, start: Vector3, end: Vector3, useCollision: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(mapGetClosestPointToSegmentBind, singleton, map, start, end, useCollision)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `to_point` on the navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point
     */
    @JvmStatic
    fun mapGetClosestPoint(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(mapGetClosestPointBind, singleton, map, toPoint)
    }

    /**
     * Returns the navigation mesh surface normal closest to the provided `to_point` on the navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point_normal
     */
    @JvmStatic
    fun mapGetClosestPointNormal(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(mapGetClosestPointNormalBind, singleton, map, toPoint)
    }

    /**
     * Returns the owner region RID for the navigation mesh surface point closest to the provided
     * `to_point` on the navigation `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point_owner
     */
    @JvmStatic
    fun mapGetClosestPointOwner(map: RID, toPoint: Vector3): RID {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetRID(mapGetClosestPointOwnerBind, singleton, map, toPoint)
    }

    /**
     * Returns all navigation link `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_links
     */
    @JvmStatic
    fun mapGetLinks(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetLinksBind, singleton, map)
    }

    /**
     * Returns all navigation regions `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_regions
     */
    @JvmStatic
    fun mapGetRegions(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetRegionsBind, singleton, map)
    }

    /**
     * Returns all navigation agents `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_agents
     */
    @JvmStatic
    fun mapGetAgents(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetAgentsBind, singleton, map)
    }

    /**
     * Returns all navigation obstacle `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_obstacles
     */
    @JvmStatic
    fun mapGetObstacles(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetObstaclesBind, singleton, map)
    }

    /**
     * This function immediately forces synchronization of the specified navigation `map` `RID`. By
     * default navigation maps are only synchronized at the end of each physics frame. This function
     * can be used to immediately (re)calculate all the navigation meshes and region connections of the
     * navigation map. This makes it possible to query a navigation path for a changed map immediately
     * and in the same frame (multiple times if needed). Due to technical restrictions the current
     * NavigationServer command queue will be flushed. This means all already queued update commands
     * for this physics frame will be executed, even those intended for other maps, regions and agents
     * not part of the specified map. The expensive computation of the navigation meshes and region
     * connections of a map will only be done for the specified map. Other maps will receive the normal
     * synchronization at the end of the physics frame. Should the specified map receive changes after
     * the forced update it will update again as well when the other maps receive their update.
     * Avoidance processing and dispatch of the `safe_velocity` signals is unaffected by this function
     * and continues to happen for all maps and agents at the end of the physics frame. Note: With
     * great power comes great responsibility. This function should only be used by users that really
     * know what they are doing and have a good reason for it. Forcing an immediate update of a
     * navigation map requires locking the NavigationServer and flushing the entire NavigationServer
     * command queue. Not only can this severely impact the performance of a game but it can also
     * introduce bugs if used inappropriately without much foresight.
     *
     * Generated from Godot docs: NavigationServer3D.map_force_update
     */
    @JvmStatic
    fun mapForceUpdate(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(mapForceUpdateBind, singleton, map)
    }

    /**
     * Returns the current iteration id of the navigation map. Every time the navigation map changes
     * and synchronizes the iteration id increases. An iteration id of 0 means the navigation map has
     * never synchronized. Note: The iteration id will wrap back to 1 after reaching its range limit.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_iteration_id
     */
    @JvmStatic
    fun mapGetIterationId(map: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(mapGetIterationIdBind, singleton, map)
    }

    /**
     * If `enabled` is `true` the `map` synchronization uses an async process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_use_async_iterations
     */
    @JvmStatic
    fun mapSetUseAsyncIterations(map: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetUseAsyncIterationsBind, singleton, map, enabled)
    }

    /**
     * Returns `true` if the `map` synchronization uses an async process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_use_async_iterations
     */
    @JvmStatic
    fun mapGetUseAsyncIterations(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapGetUseAsyncIterationsBind, singleton, map)
    }

    /**
     * Returns a random position picked from all map region polygons with matching `navigation_layers`.
     * If `uniformly` is `true`, all map regions, polygons, and faces are weighted by their surface
     * area (slower). If `uniformly` is `false`, just a random region and a random polygon are picked
     * (faster).
     *
     * Generated from Godot docs: NavigationServer3D.map_get_random_point
     */
    @JvmStatic
    fun mapGetRandomPoint(map: RID, navigationLayers: Long, uniformly: Boolean): Vector3 {
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(mapGetRandomPointBind, singleton, map, navigationLayers, uniformly)
    }

    /**
     * Queries a path in a given navigation map. Start and target position and other parameters are
     * defined through `NavigationPathQueryParameters3D`. Updates the provided
     * `NavigationPathQueryResult3D` result object with the path among other results requested by the
     * query. After the process is finished the optional `callback` will be called.
     *
     * Generated from Godot docs: NavigationServer3D.query_path
     */
    @JvmStatic
    fun queryPath(parameters: NavigationPathQueryParameters3D?, result: NavigationPathQueryResult3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(queryPathBind, singleton, parameters?.requireOpenHandle() ?: MemorySegment.NULL, result?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    /**
     * Creates a new region.
     *
     * Generated from Godot docs: NavigationServer3D.region_create
     */
    @JvmStatic
    fun regionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(regionCreateBind, singleton)
    }

    /**
     * Returns the current iteration ID of the navigation region. Every time the navigation region
     * changes and synchronizes, the iteration ID increases. An iteration ID of `0` means the
     * navigation region has never synchronized. Note: The iteration ID will wrap around to `1` after
     * reaching its range limit.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_iteration_id
     */
    @JvmStatic
    fun regionGetIterationId(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(regionGetIterationIdBind, singleton, region)
    }

    /**
     * If `enabled` is `true` the `region` uses an async synchronization process that runs on a
     * background thread.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_use_async_iterations
     */
    @JvmStatic
    fun regionSetUseAsyncIterations(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetUseAsyncIterationsBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the `region` uses an async synchronization process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_use_async_iterations
     */
    @JvmStatic
    fun regionGetUseAsyncIterations(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetUseAsyncIterationsBind, singleton, region)
    }

    /**
     * If `enabled` is `true`, the specified `region` will contribute to its current navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_enabled
     */
    @JvmStatic
    fun regionSetEnabled(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetEnabledBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the specified `region` is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_enabled
     */
    @JvmStatic
    fun regionGetEnabled(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetEnabledBind, singleton, region)
    }

    /**
     * If `enabled` is `true`, the navigation `region` will use edge connections to connect with other
     * navigation regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_use_edge_connections
     */
    @JvmStatic
    fun regionSetUseEdgeConnections(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetUseEdgeConnectionsBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the navigation `region` is set to use edge connections to connect with other
     * navigation regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_use_edge_connections
     */
    @JvmStatic
    fun regionGetUseEdgeConnections(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetUseEdgeConnectionsBind, singleton, region)
    }

    /**
     * Sets the `enter_cost` for this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_enter_cost
     */
    @JvmStatic
    fun regionSetEnterCost(region: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(regionSetEnterCostBind, singleton, region, enterCost)
    }

    /**
     * Returns the enter cost of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_enter_cost
     */
    @JvmStatic
    fun regionGetEnterCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(regionGetEnterCostBind, singleton, region)
    }

    /**
     * Sets the `travel_cost` for this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_travel_cost
     */
    @JvmStatic
    fun regionSetTravelCost(region: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(regionSetTravelCostBind, singleton, region, travelCost)
    }

    /**
     * Returns the travel cost of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_travel_cost
     */
    @JvmStatic
    fun regionGetTravelCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(regionGetTravelCostBind, singleton, region)
    }

    /**
     * Set the `ObjectID` of the object which manages this region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_owner_id
     */
    @JvmStatic
    fun regionSetOwnerId(region: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(regionSetOwnerIdBind, singleton, region, ownerId)
    }

    /**
     * Returns the `ObjectID` of the object which manages this region.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_owner_id
     */
    @JvmStatic
    fun regionGetOwnerId(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(regionGetOwnerIdBind, singleton, region)
    }

    /**
     * Returns `true` if the provided `point` in world space is currently owned by the provided
     * navigation `region`. Owned in this context means that one of the region's navigation mesh
     * polygon faces has a possible position at the closest distance to this point compared to all
     * other navigation meshes from other navigation regions that are also registered on the navigation
     * map of the provided region. If multiple navigation meshes have positions at equal distance the
     * navigation region whose polygons are processed first wins the ownership. Polygons are processed
     * in the same order that navigation regions were registered on the NavigationServer. Note: If
     * navigation meshes from different navigation regions overlap (which should be avoided in general)
     * the result might not be what is expected.
     *
     * Generated from Godot docs: NavigationServer3D.region_owns_point
     */
    @JvmStatic
    fun regionOwnsPoint(region: RID, point: Vector3): Boolean {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetBool(regionOwnsPointBind, singleton, region, point)
    }

    /**
     * Sets the map for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_map
     */
    @JvmStatic
    fun regionSetMap(region: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(regionSetMapBind, singleton, region, map)
    }

    /**
     * Returns the navigation map `RID` the requested `region` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_map
     */
    @JvmStatic
    fun regionGetMap(region: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(regionGetMapBind, singleton, region)
    }

    /**
     * Set the region's navigation layers. This allows selecting regions from a path request (when
     * using `NavigationServer3D.map_get_path`).
     *
     * Generated from Godot docs: NavigationServer3D.region_set_navigation_layers
     */
    @JvmStatic
    fun regionSetNavigationLayers(region: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(regionSetNavigationLayersBind, singleton, region, navigationLayers)
    }

    /**
     * Returns the region's navigation layers.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_navigation_layers
     */
    @JvmStatic
    fun regionGetNavigationLayers(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(regionGetNavigationLayersBind, singleton, region)
    }

    /**
     * Sets the global transformation for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_transform
     */
    @JvmStatic
    fun regionSetTransform(region: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(regionSetTransformBind, singleton, region, transform)
    }

    /**
     * Returns the global transformation of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_transform
     */
    @JvmStatic
    fun regionGetTransform(region: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(regionGetTransformBind, singleton, region)
    }

    /**
     * Sets the navigation mesh for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_navigation_mesh
     */
    @JvmStatic
    fun regionSetNavigationMesh(region: RID, navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithRIDAndObjectArg(regionSetNavigationMeshBind, singleton, region, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Bakes the `navigation_mesh` with bake source geometry collected starting from the `root_node`.
     *
     * Generated from Godot docs: NavigationServer3D.region_bake_navigation_mesh
     */
    @JvmStatic
    fun regionBakeNavigationMesh(navigationMesh: NavigationMesh?, rootNode: Node) {
        ObjectCalls.ptrcallWithTwoObjectArgs(regionBakeNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle)
    }

    /**
     * Returns how many connections this `region` has with other regions in the map.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connections_count
     */
    @JvmStatic
    fun regionGetConnectionsCount(region: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(regionGetConnectionsCountBind, singleton, region)
    }

    /**
     * Returns the starting point of a connection door. `connection` is an index between 0 and the
     * return value of `region_get_connections_count`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connection_pathway_start
     */
    @JvmStatic
    fun regionGetConnectionPathwayStart(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(regionGetConnectionPathwayStartBind, singleton, region, connection)
    }

    /**
     * Returns the ending point of a connection door. `connection` is an index between 0 and the return
     * value of `region_get_connections_count`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connection_pathway_end
     */
    @JvmStatic
    fun regionGetConnectionPathwayEnd(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(regionGetConnectionPathwayEndBind, singleton, region, connection)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `start` and `end` segment on
     * the navigation `region`. If `use_collision` is `true`, a closest point test is only done when
     * the segment intersects with the navigation mesh surface.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_closest_point_to_segment
     */
    @JvmStatic
    fun regionGetClosestPointToSegment(region: RID, start: Vector3, end: Vector3, useCollision: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(regionGetClosestPointToSegmentBind, singleton, region, start, end, useCollision)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `to_point` on the navigation
     * `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_closest_point
     */
    @JvmStatic
    fun regionGetClosestPoint(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(regionGetClosestPointBind, singleton, region, toPoint)
    }

    /**
     * Returns the navigation mesh surface normal closest to the provided `to_point` on the navigation
     * `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_closest_point_normal
     */
    @JvmStatic
    fun regionGetClosestPointNormal(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(regionGetClosestPointNormalBind, singleton, region, toPoint)
    }

    /**
     * Returns a random position picked from all region polygons with matching `navigation_layers`. If
     * `uniformly` is `true`, all region polygons and faces are weighted by their surface area
     * (slower). If `uniformly` is `false`, just a random polygon and face is picked (faster).
     *
     * Generated from Godot docs: NavigationServer3D.region_get_random_point
     */
    @JvmStatic
    fun regionGetRandomPoint(region: RID, navigationLayers: Long, uniformly: Boolean): Vector3 {
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(regionGetRandomPointBind, singleton, region, navigationLayers, uniformly)
    }

    /**
     * Returns the axis-aligned bounding box for the `region`'s transformed navigation mesh.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_bounds
     */
    @JvmStatic
    fun regionGetBounds(region: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(regionGetBoundsBind, singleton, region)
    }

    /**
     * Create a new link between two positions on a map.
     *
     * Generated from Godot docs: NavigationServer3D.link_create
     */
    @JvmStatic
    fun linkCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(linkCreateBind, singleton)
    }

    /**
     * Returns the current iteration ID of the navigation link. Every time the navigation link changes
     * and synchronizes, the iteration ID increases. An iteration ID of `0` means the navigation link
     * has never synchronized. Note: The iteration ID will wrap around to `1` after reaching its range
     * limit.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_iteration_id
     */
    @JvmStatic
    fun linkGetIterationId(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(linkGetIterationIdBind, singleton, link)
    }

    /**
     * Sets the navigation map `RID` for the link.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_map
     */
    @JvmStatic
    fun linkSetMap(link: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(linkSetMapBind, singleton, link, map)
    }

    /**
     * Returns the navigation map `RID` the requested `link` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_map
     */
    @JvmStatic
    fun linkGetMap(link: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(linkGetMapBind, singleton, link)
    }

    /**
     * If `enabled` is `true`, the specified `link` will contribute to its current navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_enabled
     */
    @JvmStatic
    fun linkSetEnabled(link: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(linkSetEnabledBind, singleton, link, enabled)
    }

    /**
     * Returns `true` if the specified `link` is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_enabled
     */
    @JvmStatic
    fun linkGetEnabled(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(linkGetEnabledBind, singleton, link)
    }

    /**
     * Sets whether this `link` can be travelled in both directions.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_bidirectional
     */
    @JvmStatic
    fun linkSetBidirectional(link: RID, bidirectional: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(linkSetBidirectionalBind, singleton, link, bidirectional)
    }

    /**
     * Returns whether this `link` can be travelled in both directions.
     *
     * Generated from Godot docs: NavigationServer3D.link_is_bidirectional
     */
    @JvmStatic
    fun linkIsBidirectional(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(linkIsBidirectionalBind, singleton, link)
    }

    /**
     * Set the links's navigation layers. This allows selecting links from a path request (when using
     * `NavigationServer3D.map_get_path`).
     *
     * Generated from Godot docs: NavigationServer3D.link_set_navigation_layers
     */
    @JvmStatic
    fun linkSetNavigationLayers(link: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(linkSetNavigationLayersBind, singleton, link, navigationLayers)
    }

    /**
     * Returns the navigation layers for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_navigation_layers
     */
    @JvmStatic
    fun linkGetNavigationLayers(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(linkGetNavigationLayersBind, singleton, link)
    }

    /**
     * Sets the entry position for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_start_position
     */
    @JvmStatic
    fun linkSetStartPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(linkSetStartPositionBind, singleton, link, position)
    }

    /**
     * Returns the starting position of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_start_position
     */
    @JvmStatic
    fun linkGetStartPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(linkGetStartPositionBind, singleton, link)
    }

    /**
     * Sets the exit position for the `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_end_position
     */
    @JvmStatic
    fun linkSetEndPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(linkSetEndPositionBind, singleton, link, position)
    }

    /**
     * Returns the ending position of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_end_position
     */
    @JvmStatic
    fun linkGetEndPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(linkGetEndPositionBind, singleton, link)
    }

    /**
     * Sets the `enter_cost` for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_enter_cost
     */
    @JvmStatic
    fun linkSetEnterCost(link: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(linkSetEnterCostBind, singleton, link, enterCost)
    }

    /**
     * Returns the enter cost of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_enter_cost
     */
    @JvmStatic
    fun linkGetEnterCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(linkGetEnterCostBind, singleton, link)
    }

    /**
     * Sets the `travel_cost` for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_travel_cost
     */
    @JvmStatic
    fun linkSetTravelCost(link: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(linkSetTravelCostBind, singleton, link, travelCost)
    }

    /**
     * Returns the travel cost of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_travel_cost
     */
    @JvmStatic
    fun linkGetTravelCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(linkGetTravelCostBind, singleton, link)
    }

    /**
     * Set the `ObjectID` of the object which manages this link.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_owner_id
     */
    @JvmStatic
    fun linkSetOwnerId(link: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(linkSetOwnerIdBind, singleton, link, ownerId)
    }

    /**
     * Returns the `ObjectID` of the object which manages this link.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_owner_id
     */
    @JvmStatic
    fun linkGetOwnerId(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(linkGetOwnerIdBind, singleton, link)
    }

    /**
     * Creates the agent.
     *
     * Generated from Godot docs: NavigationServer3D.agent_create
     */
    @JvmStatic
    fun agentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(agentCreateBind, singleton)
    }

    /**
     * If `enabled` is `true`, the provided `agent` calculates avoidance.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_enabled
     */
    @JvmStatic
    fun agentSetAvoidanceEnabled(agent: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetAvoidanceEnabledBind, singleton, agent, enabled)
    }

    /**
     * Returns `true` if the provided `agent` has avoidance enabled.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_enabled
     */
    @JvmStatic
    fun agentGetAvoidanceEnabled(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetAvoidanceEnabledBind, singleton, agent)
    }

    /**
     * Sets if the agent uses the 2D avoidance or the 3D avoidance while avoidance is enabled. If
     * `true` the agent calculates avoidance velocities in 3D for the xyz-axis, e.g. for games that
     * take place in air, underwater or space. The 3D using agent only avoids other 3D avoidance using
     * agent's. The 3D using agent only reacts to radius based avoidance obstacles. The 3D using agent
     * ignores any vertices based obstacles. The 3D using agent only avoids other 3D using agent's. If
     * `false` the agent calculates avoidance velocities in 2D along the xz-axis ignoring the y-axis.
     * The 2D using agent only avoids other 2D avoidance using agent's. The 2D using agent reacts to
     * radius avoidance obstacles. The 2D using agent reacts to vertices based avoidance obstacles. The
     * 2D using agent only avoids other 2D using agent's. 2D using agents will ignore other 2D using
     * agents or obstacles that are below their current position or above their current position
     * including the agents height in 2D avoidance.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_use_3d_avoidance
     */
    @JvmStatic
    fun agentSetUse3dAvoidance(agent: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetUse3dAvoidanceBind, singleton, agent, enabled)
    }

    /**
     * Returns `true` if the provided `agent` uses avoidance in 3D space Vector3(x,y,z) instead of
     * horizontal 2D Vector2(x,y) / Vector3(x,0.0,z).
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_use_3d_avoidance
     */
    @JvmStatic
    fun agentGetUse3dAvoidance(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetUse3dAvoidanceBind, singleton, agent)
    }

    /**
     * Puts the agent in the map.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_map
     */
    @JvmStatic
    fun agentSetMap(agent: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(agentSetMapBind, singleton, agent, map)
    }

    /**
     * Returns the navigation map `RID` the requested `agent` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_map
     */
    @JvmStatic
    fun agentGetMap(agent: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(agentGetMapBind, singleton, agent)
    }

    /**
     * If `paused` is `true` the specified `agent` will not be processed. For example, it will not
     * calculate avoidance velocities or receive avoidance callbacks.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_paused
     */
    @JvmStatic
    fun agentSetPaused(agent: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetPausedBind, singleton, agent, paused)
    }

    /**
     * Returns `true` if the specified `agent` is paused.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_paused
     */
    @JvmStatic
    fun agentGetPaused(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetPausedBind, singleton, agent)
    }

    /**
     * Sets the maximum distance to other agents this agent takes into account in the navigation. The
     * larger this number, the longer the running time of the simulation. If the number is too low, the
     * simulation will not be safe.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_neighbor_distance
     */
    @JvmStatic
    fun agentSetNeighborDistance(agent: RID, distance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetNeighborDistanceBind, singleton, agent, distance)
    }

    /**
     * Returns the maximum distance to other agents the specified `agent` takes into account in the
     * navigation.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_neighbor_distance
     */
    @JvmStatic
    fun agentGetNeighborDistance(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetNeighborDistanceBind, singleton, agent)
    }

    /**
     * Sets the maximum number of other agents the agent takes into account in the navigation. The
     * larger this number, the longer the running time of the simulation. If the number is too low, the
     * simulation will not be safe.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_max_neighbors
     */
    @JvmStatic
    fun agentSetMaxNeighbors(agent: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(agentSetMaxNeighborsBind, singleton, agent, count)
    }

    /**
     * Returns the maximum number of other agents the specified `agent` takes into account in the
     * navigation.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_max_neighbors
     */
    @JvmStatic
    fun agentGetMaxNeighbors(agent: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(agentGetMaxNeighborsBind, singleton, agent)
    }

    /**
     * The minimal amount of time for which the agent's velocities that are computed by the simulation
     * are safe with respect to other agents. The larger this number, the sooner this agent will
     * respond to the presence of other agents, but the less freedom this agent has in choosing its
     * velocities. A too high value will slow down agents movement considerably. Must be positive.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_time_horizon_agents
     */
    @JvmStatic
    fun agentSetTimeHorizonAgents(agent: RID, timeHorizon: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetTimeHorizonAgentsBind, singleton, agent, timeHorizon)
    }

    /**
     * Returns the minimal amount of time for which the specified `agent`'s velocities that are
     * computed by the simulation are safe with respect to other agents.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_time_horizon_agents
     */
    @JvmStatic
    fun agentGetTimeHorizonAgents(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetTimeHorizonAgentsBind, singleton, agent)
    }

    /**
     * The minimal amount of time for which the agent's velocities that are computed by the simulation
     * are safe with respect to static avoidance obstacles. The larger this number, the sooner this
     * agent will respond to the presence of static avoidance obstacles, but the less freedom this
     * agent has in choosing its velocities. A too high value will slow down agents movement
     * considerably. Must be positive.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_time_horizon_obstacles
     */
    @JvmStatic
    fun agentSetTimeHorizonObstacles(agent: RID, timeHorizon: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetTimeHorizonObstaclesBind, singleton, agent, timeHorizon)
    }

    /**
     * Returns the minimal amount of time for which the specified `agent`'s velocities that are
     * computed by the simulation are safe with respect to static avoidance obstacles.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_time_horizon_obstacles
     */
    @JvmStatic
    fun agentGetTimeHorizonObstacles(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetTimeHorizonObstaclesBind, singleton, agent)
    }

    /**
     * Sets the radius of the agent.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_radius
     */
    @JvmStatic
    fun agentSetRadius(agent: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetRadiusBind, singleton, agent, radius)
    }

    /**
     * Returns the radius of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_radius
     */
    @JvmStatic
    fun agentGetRadius(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetRadiusBind, singleton, agent)
    }

    /**
     * Updates the provided `agent` `height`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_height
     */
    @JvmStatic
    fun agentSetHeight(agent: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetHeightBind, singleton, agent, height)
    }

    /**
     * Returns the `height` of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_height
     */
    @JvmStatic
    fun agentGetHeight(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetHeightBind, singleton, agent)
    }

    /**
     * Sets the maximum speed of the agent. Must be positive.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_max_speed
     */
    @JvmStatic
    fun agentSetMaxSpeed(agent: RID, maxSpeed: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetMaxSpeedBind, singleton, agent, maxSpeed)
    }

    /**
     * Returns the maximum speed of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_max_speed
     */
    @JvmStatic
    fun agentGetMaxSpeed(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetMaxSpeedBind, singleton, agent)
    }

    /**
     * Replaces the internal velocity in the collision avoidance simulation with `velocity` for the
     * specified `agent`. When an agent is teleported to a new position this function should be used in
     * the same frame. If called frequently this function can get agents stuck.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_velocity_forced
     */
    @JvmStatic
    fun agentSetVelocityForced(agent: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetVelocityForcedBind, singleton, agent, velocity)
    }

    /**
     * Sets `velocity` as the new wanted velocity for the specified `agent`. The avoidance simulation
     * will try to fulfill this velocity if possible but will modify it to avoid collision with other
     * agent's and obstacles. When an agent is teleported to a new position use
     * `agent_set_velocity_forced` as well to reset the internal simulation velocity.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_velocity
     */
    @JvmStatic
    fun agentSetVelocity(agent: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetVelocityBind, singleton, agent, velocity)
    }

    /**
     * Returns the velocity of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_velocity
     */
    @JvmStatic
    fun agentGetVelocity(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(agentGetVelocityBind, singleton, agent)
    }

    /**
     * Sets the position of the agent in world space.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_position
     */
    @JvmStatic
    fun agentSetPosition(agent: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetPositionBind, singleton, agent, position)
    }

    /**
     * Returns the position of the specified `agent` in world space.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_position
     */
    @JvmStatic
    fun agentGetPosition(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(agentGetPositionBind, singleton, agent)
    }

    /**
     * Returns `true` if the map got changed the previous frame.
     *
     * Generated from Godot docs: NavigationServer3D.agent_is_map_changed
     */
    @JvmStatic
    fun agentIsMapChanged(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentIsMapChangedBind, singleton, agent)
    }

    /**
     * Sets the callback `Callable` that gets called after each avoidance processing step for the
     * `agent`. The calculated `safe_velocity` will be dispatched with a signal to the object just
     * before the physics calculations. Note: Created callbacks are always processed independently of
     * the SceneTree state as long as the agent is on a navigation map and not freed. To disable the
     * dispatch of a callback from an agent use `agent_set_avoidance_callback` again with an empty
     * `Callable`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_callback
     */
    @JvmStatic
    fun agentSetAvoidanceCallback(agent: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(agentSetAvoidanceCallbackBind, singleton, agent, callback.target.handle, callback.method)
    }

    /**
     * Return `true` if the specified `agent` has an avoidance callback.
     *
     * Generated from Godot docs: NavigationServer3D.agent_has_avoidance_callback
     */
    @JvmStatic
    fun agentHasAvoidanceCallback(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentHasAvoidanceCallbackBind, singleton, agent)
    }

    /**
     * Set the agent's `avoidance_layers` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_layers
     */
    @JvmStatic
    fun agentSetAvoidanceLayers(agent: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(agentSetAvoidanceLayersBind, singleton, agent, layers)
    }

    /**
     * Returns the `avoidance_layers` bitmask of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_layers
     */
    @JvmStatic
    fun agentGetAvoidanceLayers(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(agentGetAvoidanceLayersBind, singleton, agent)
    }

    /**
     * Set the agent's `avoidance_mask` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_mask
     */
    @JvmStatic
    fun agentSetAvoidanceMask(agent: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(agentSetAvoidanceMaskBind, singleton, agent, mask)
    }

    /**
     * Returns the `avoidance_mask` bitmask of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_mask
     */
    @JvmStatic
    fun agentGetAvoidanceMask(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(agentGetAvoidanceMaskBind, singleton, agent)
    }

    /**
     * Set the agent's `avoidance_priority` with a `priority` between 0.0 (lowest priority) to 1.0
     * (highest priority). The specified `agent` does not adjust the velocity for other agents that
     * would match the `avoidance_mask` but have a lower `avoidance_priority`. This in turn makes the
     * other agents with lower priority adjust their velocities even more to avoid collision with this
     * agent.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_priority
     */
    @JvmStatic
    fun agentSetAvoidancePriority(agent: RID, priority: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetAvoidancePriorityBind, singleton, agent, priority)
    }

    /**
     * Returns the `avoidance_priority` of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_priority
     */
    @JvmStatic
    fun agentGetAvoidancePriority(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetAvoidancePriorityBind, singleton, agent)
    }

    /**
     * Creates a new obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_create
     */
    @JvmStatic
    fun obstacleCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(obstacleCreateBind, singleton)
    }

    /**
     * If `enabled` is `true`, the provided `obstacle` affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_avoidance_enabled
     */
    @JvmStatic
    fun obstacleSetAvoidanceEnabled(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetAvoidanceEnabledBind, singleton, obstacle, enabled)
    }

    /**
     * Returns `true` if the provided `obstacle` has avoidance enabled.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_avoidance_enabled
     */
    @JvmStatic
    fun obstacleGetAvoidanceEnabled(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetAvoidanceEnabledBind, singleton, obstacle)
    }

    /**
     * Sets if the `obstacle` uses the 2D avoidance or the 3D avoidance while avoidance is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_use_3d_avoidance
     */
    @JvmStatic
    fun obstacleSetUse3dAvoidance(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetUse3dAvoidanceBind, singleton, obstacle, enabled)
    }

    /**
     * Returns `true` if the provided `obstacle` uses avoidance in 3D space Vector3(x,y,z) instead of
     * horizontal 2D Vector2(x,y) / Vector3(x,0.0,z).
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_use_3d_avoidance
     */
    @JvmStatic
    fun obstacleGetUse3dAvoidance(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetUse3dAvoidanceBind, singleton, obstacle)
    }

    /**
     * Assigns the `obstacle` to a navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_map
     */
    @JvmStatic
    fun obstacleSetMap(obstacle: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(obstacleSetMapBind, singleton, obstacle, map)
    }

    /**
     * Returns the navigation map `RID` the requested `obstacle` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_map
     */
    @JvmStatic
    fun obstacleGetMap(obstacle: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(obstacleGetMapBind, singleton, obstacle)
    }

    /**
     * If `paused` is `true` the specified `obstacle` will not be processed. For example, it will no
     * longer affect avoidance velocities.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_paused
     */
    @JvmStatic
    fun obstacleSetPaused(obstacle: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetPausedBind, singleton, obstacle, paused)
    }

    /**
     * Returns `true` if the specified `obstacle` is paused.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_paused
     */
    @JvmStatic
    fun obstacleGetPaused(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetPausedBind, singleton, obstacle)
    }

    /**
     * Sets the radius of the dynamic obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_radius
     */
    @JvmStatic
    fun obstacleSetRadius(obstacle: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(obstacleSetRadiusBind, singleton, obstacle, radius)
    }

    /**
     * Returns the radius of the specified dynamic `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_radius
     */
    @JvmStatic
    fun obstacleGetRadius(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(obstacleGetRadiusBind, singleton, obstacle)
    }

    /**
     * Sets the `height` for the `obstacle`. In 3D agents will ignore obstacles that are above or below
     * them while using 2D avoidance.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_height
     */
    @JvmStatic
    fun obstacleSetHeight(obstacle: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(obstacleSetHeightBind, singleton, obstacle, height)
    }

    /**
     * Returns the `height` of the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_height
     */
    @JvmStatic
    fun obstacleGetHeight(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(obstacleGetHeightBind, singleton, obstacle)
    }

    /**
     * Sets `velocity` of the dynamic `obstacle`. Allows other agents to better predict the movement of
     * the dynamic obstacle. Only works in combination with the radius of the obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_velocity
     */
    @JvmStatic
    fun obstacleSetVelocity(obstacle: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(obstacleSetVelocityBind, singleton, obstacle, velocity)
    }

    /**
     * Returns the velocity of the specified dynamic `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_velocity
     */
    @JvmStatic
    fun obstacleGetVelocity(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(obstacleGetVelocityBind, singleton, obstacle)
    }

    /**
     * Updates the `position` in world space for the `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_position
     */
    @JvmStatic
    fun obstacleSetPosition(obstacle: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(obstacleSetPositionBind, singleton, obstacle, position)
    }

    /**
     * Returns the position of the specified `obstacle` in world space.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_position
     */
    @JvmStatic
    fun obstacleGetPosition(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(obstacleGetPositionBind, singleton, obstacle)
    }

    /**
     * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
     * will be pushed in by the obstacle, else they will be pushed out.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_vertices
     */
    @JvmStatic
    fun obstacleSetVertices(obstacle: RID, vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithRIDAndPackedVector3ListArg(obstacleSetVerticesBind, singleton, obstacle, vertices)
    }

    /**
     * Returns the outline vertices for the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_vertices
     */
    @JvmStatic
    fun obstacleGetVertices(obstacle: RID): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(obstacleGetVerticesBind, singleton, obstacle)
    }

    /**
     * Set the obstacles's `avoidance_layers` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_avoidance_layers
     */
    @JvmStatic
    fun obstacleSetAvoidanceLayers(obstacle: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(obstacleSetAvoidanceLayersBind, singleton, obstacle, layers)
    }

    /**
     * Returns the `avoidance_layers` bitmask of the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_avoidance_layers
     */
    @JvmStatic
    fun obstacleGetAvoidanceLayers(obstacle: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(obstacleGetAvoidanceLayersBind, singleton, obstacle)
    }

    /**
     * Parses the `SceneTree` for source geometry according to the properties of `navigation_mesh`.
     * Updates the provided `source_geometry_data` resource with the resulting data. The resource can
     * then be used to bake a navigation mesh with `bake_from_source_geometry_data`. After the process
     * is finished the optional `callback` will be called. Note: This function needs to run on the main
     * thread or with a deferred call as the SceneTree is not thread-safe. Performance: While
     * convenient, reading data arrays from `Mesh` resources can affect the frame rate negatively. The
     * data needs to be received from the GPU, stalling the `RenderingServer` in the process. For
     * performance prefer the use of e.g. collision shapes or creating the data arrays entirely in
     * code.
     *
     * Generated from Godot docs: NavigationServer3D.parse_source_geometry_data
     */
    @JvmStatic
    fun parseSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, rootNode: Node, callback: GodotCallable) {
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle, callback.target.handle, callback.method)
    }

    /**
     * Bakes the provided `navigation_mesh` with the data from the provided `source_geometry_data`.
     * After the process is finished the optional `callback` will be called.
     *
     * Generated from Godot docs: NavigationServer3D.bake_from_source_geometry_data
     */
    @JvmStatic
    fun bakeFromSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(bakeFromSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    /**
     * Bakes the provided `navigation_mesh` with the data from the provided `source_geometry_data` as
     * an async task running on a background thread. After the process is finished the optional
     * `callback` will be called.
     *
     * Generated from Godot docs: NavigationServer3D.bake_from_source_geometry_data_async
     */
    @JvmStatic
    fun bakeFromSourceGeometryDataAsync(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(bakeFromSourceGeometryDataAsyncBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    /**
     * Returns `true` when the provided navigation mesh is being baked on a background thread.
     *
     * Generated from Godot docs: NavigationServer3D.is_baking_navigation_mesh
     */
    @JvmStatic
    fun isBakingNavigationMesh(navigationMesh: NavigationMesh?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isBakingNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Creates a new source geometry parser. If a `Callable` is set for the parser with
     * `source_geometry_parser_set_callback` the callback will be called for every single node that
     * gets parsed whenever `parse_source_geometry_data` is used.
     *
     * Generated from Godot docs: NavigationServer3D.source_geometry_parser_create
     */
    @JvmStatic
    fun sourceGeometryParserCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(sourceGeometryParserCreateBind, singleton)
    }

    /**
     * Sets the `callback` `Callable` for the specific source geometry `parser`. The `Callable` will
     * receive a call with the following parameters: - `navigation_mesh` - The `NavigationMesh`
     * reference used to define the parse settings. Do NOT edit or add directly to the navigation mesh.
     * - `source_geometry_data` - The `NavigationMeshSourceGeometryData3D` reference. Add custom source
     * geometry for navigation mesh baking to this object. - `node` - The `Node` that is parsed.
     *
     * Generated from Godot docs: NavigationServer3D.source_geometry_parser_set_callback
     */
    @JvmStatic
    fun sourceGeometryParserSetCallback(parser: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(sourceGeometryParserSetCallbackBind, singleton, parser, callback.target.handle, callback.method)
    }

    /**
     * Returns a simplified version of `path` with less critical path points removed. The
     * simplification amount is in worlds units and controlled by `epsilon`. The simplification uses a
     * variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
     * be helpful to mitigate various path following issues that can arise with certain agent types and
     * script behaviors. E.g. "steering" agents or avoidance in "open fields".
     *
     * Generated from Godot docs: NavigationServer3D.simplify_path
     */
    @JvmStatic
    fun simplifyPath(path: List<Vector3>, epsilon: Double): List<Vector3> {
        return ObjectCalls.ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List(simplifyPathBind, singleton, path, epsilon)
    }

    /**
     * Destroys the given RID.
     *
     * Generated from Godot docs: NavigationServer3D.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    /**
     * Control activation of this server.
     *
     * Generated from Godot docs: NavigationServer3D.set_active
     */
    @JvmStatic
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, singleton, active)
    }

    /**
     * If `true` enables debug mode on the NavigationServer.
     *
     * Generated from Godot docs: NavigationServer3D.set_debug_enabled
     */
    @JvmStatic
    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugEnabledBind, singleton, enabled)
    }

    /**
     * Returns `true` when the NavigationServer has debug enabled.
     *
     * Generated from Godot docs: NavigationServer3D.get_debug_enabled
     */
    @JvmStatic
    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugEnabledBind, singleton)
    }

    /**
     * Returns information about the current state of the NavigationServer.
     *
     * Generated from Godot docs: NavigationServer3D.get_process_info
     */
    @JvmStatic
    fun getProcessInfo(processInfo: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getProcessInfoBind, singleton, processInfo)
    }

    object Signals {
        const val mapChanged: String = "map_changed"
        const val navigationDebugChanged: String = "navigation_debug_changed"
        const val avoidanceDebugChanged: String = "avoidance_debug_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): NavigationServer3D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): NavigationServer3D? =
        if (handle.address() == 0L) null else this

    private const val GET_MAPS_HASH = 3995934104L
    private val getMapsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_maps", GET_MAPS_HASH)
    }

    private const val MAP_CREATE_HASH = 529393457L
    private val mapCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_create", MAP_CREATE_HASH)
    }

    private const val MAP_SET_ACTIVE_HASH = 1265174801L
    private val mapSetActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_active", MAP_SET_ACTIVE_HASH)
    }

    private const val MAP_IS_ACTIVE_HASH = 4155700596L
    private val mapIsActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_is_active", MAP_IS_ACTIVE_HASH)
    }

    private const val MAP_SET_UP_HASH = 3227306858L
    private val mapSetUpBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_up", MAP_SET_UP_HASH)
    }

    private const val MAP_GET_UP_HASH = 531438156L
    private val mapGetUpBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_up", MAP_GET_UP_HASH)
    }

    private const val MAP_SET_CELL_SIZE_HASH = 1794382983L
    private val mapSetCellSizeBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_size", MAP_SET_CELL_SIZE_HASH)
    }

    private const val MAP_GET_CELL_SIZE_HASH = 866169185L
    private val mapGetCellSizeBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_size", MAP_GET_CELL_SIZE_HASH)
    }

    private const val MAP_SET_CELL_HEIGHT_HASH = 1794382983L
    private val mapSetCellHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_height", MAP_SET_CELL_HEIGHT_HASH)
    }

    private const val MAP_GET_CELL_HEIGHT_HASH = 866169185L
    private val mapGetCellHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_height", MAP_GET_CELL_HEIGHT_HASH)
    }

    private const val MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH = 1794382983L
    private val mapSetMergeRasterizerCellScaleBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_merge_rasterizer_cell_scale", MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH)
    }

    private const val MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH = 866169185L
    private val mapGetMergeRasterizerCellScaleBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_merge_rasterizer_cell_scale", MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH)
    }

    private const val MAP_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
    private val mapSetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_edge_connections", MAP_SET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val MAP_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
    private val mapGetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_edge_connections", MAP_GET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val MAP_SET_EDGE_CONNECTION_MARGIN_HASH = 1794382983L
    private val mapSetEdgeConnectionMarginBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_edge_connection_margin", MAP_SET_EDGE_CONNECTION_MARGIN_HASH)
    }

    private const val MAP_GET_EDGE_CONNECTION_MARGIN_HASH = 866169185L
    private val mapGetEdgeConnectionMarginBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_edge_connection_margin", MAP_GET_EDGE_CONNECTION_MARGIN_HASH)
    }

    private const val MAP_SET_LINK_CONNECTION_RADIUS_HASH = 1794382983L
    private val mapSetLinkConnectionRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_link_connection_radius", MAP_SET_LINK_CONNECTION_RADIUS_HASH)
    }

    private const val MAP_GET_LINK_CONNECTION_RADIUS_HASH = 866169185L
    private val mapGetLinkConnectionRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_link_connection_radius", MAP_GET_LINK_CONNECTION_RADIUS_HASH)
    }

    private const val MAP_GET_PATH_HASH = 276783190L
    private val mapGetPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_path", MAP_GET_PATH_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
    private val mapGetClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_to_segment", MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_HASH = 2056183332L
    private val mapGetClosestPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point", MAP_GET_CLOSEST_POINT_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
    private val mapGetClosestPointNormalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_normal", MAP_GET_CLOSEST_POINT_NORMAL_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_OWNER_HASH = 553364610L
    private val mapGetClosestPointOwnerBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_owner", MAP_GET_CLOSEST_POINT_OWNER_HASH)
    }

    private const val MAP_GET_LINKS_HASH = 2684255073L
    private val mapGetLinksBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_links", MAP_GET_LINKS_HASH)
    }

    private const val MAP_GET_REGIONS_HASH = 2684255073L
    private val mapGetRegionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_regions", MAP_GET_REGIONS_HASH)
    }

    private const val MAP_GET_AGENTS_HASH = 2684255073L
    private val mapGetAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_agents", MAP_GET_AGENTS_HASH)
    }

    private const val MAP_GET_OBSTACLES_HASH = 2684255073L
    private val mapGetObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_obstacles", MAP_GET_OBSTACLES_HASH)
    }

    private const val MAP_FORCE_UPDATE_HASH = 2722037293L
    private val mapForceUpdateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_force_update", MAP_FORCE_UPDATE_HASH)
    }

    private const val MAP_GET_ITERATION_ID_HASH = 2198884583L
    private val mapGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_iteration_id", MAP_GET_ITERATION_ID_HASH)
    }

    private const val MAP_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
    private val mapSetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_async_iterations", MAP_SET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val MAP_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
    private val mapGetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_async_iterations", MAP_GET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val MAP_GET_RANDOM_POINT_HASH = 722801526L
    private val mapGetRandomPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_random_point", MAP_GET_RANDOM_POINT_HASH)
    }

    private const val QUERY_PATH_HASH = 2146930868L
    private val queryPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "query_path", QUERY_PATH_HASH)
    }

    private const val REGION_CREATE_HASH = 529393457L
    private val regionCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_create", REGION_CREATE_HASH)
    }

    private const val REGION_GET_ITERATION_ID_HASH = 2198884583L
    private val regionGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_iteration_id", REGION_GET_ITERATION_ID_HASH)
    }

    private const val REGION_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
    private val regionSetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_async_iterations", REGION_SET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val REGION_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
    private val regionGetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_async_iterations", REGION_GET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val REGION_SET_ENABLED_HASH = 1265174801L
    private val regionSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enabled", REGION_SET_ENABLED_HASH)
    }

    private const val REGION_GET_ENABLED_HASH = 4155700596L
    private val regionGetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enabled", REGION_GET_ENABLED_HASH)
    }

    private const val REGION_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
    private val regionSetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_edge_connections", REGION_SET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val REGION_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
    private val regionGetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_edge_connections", REGION_GET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val REGION_SET_ENTER_COST_HASH = 1794382983L
    private val regionSetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enter_cost", REGION_SET_ENTER_COST_HASH)
    }

    private const val REGION_GET_ENTER_COST_HASH = 866169185L
    private val regionGetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enter_cost", REGION_GET_ENTER_COST_HASH)
    }

    private const val REGION_SET_TRAVEL_COST_HASH = 1794382983L
    private val regionSetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_travel_cost", REGION_SET_TRAVEL_COST_HASH)
    }

    private const val REGION_GET_TRAVEL_COST_HASH = 866169185L
    private val regionGetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_travel_cost", REGION_GET_TRAVEL_COST_HASH)
    }

    private const val REGION_SET_OWNER_ID_HASH = 3411492887L
    private val regionSetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_owner_id", REGION_SET_OWNER_ID_HASH)
    }

    private const val REGION_GET_OWNER_ID_HASH = 2198884583L
    private val regionGetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_owner_id", REGION_GET_OWNER_ID_HASH)
    }

    private const val REGION_OWNS_POINT_HASH = 2360011153L
    private val regionOwnsPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_owns_point", REGION_OWNS_POINT_HASH)
    }

    private const val REGION_SET_MAP_HASH = 395945892L
    private val regionSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_map", REGION_SET_MAP_HASH)
    }

    private const val REGION_GET_MAP_HASH = 3814569979L
    private val regionGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_map", REGION_GET_MAP_HASH)
    }

    private const val REGION_SET_NAVIGATION_LAYERS_HASH = 3411492887L
    private val regionSetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_layers", REGION_SET_NAVIGATION_LAYERS_HASH)
    }

    private const val REGION_GET_NAVIGATION_LAYERS_HASH = 2198884583L
    private val regionGetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_navigation_layers", REGION_GET_NAVIGATION_LAYERS_HASH)
    }

    private const val REGION_SET_TRANSFORM_HASH = 3935195649L
    private val regionSetTransformBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_transform", REGION_SET_TRANSFORM_HASH)
    }

    private const val REGION_GET_TRANSFORM_HASH = 1128465797L
    private val regionGetTransformBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_transform", REGION_GET_TRANSFORM_HASH)
    }

    private const val REGION_SET_NAVIGATION_MESH_HASH = 2764952978L
    private val regionSetNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_mesh", REGION_SET_NAVIGATION_MESH_HASH)
    }

    private const val REGION_BAKE_NAVIGATION_MESH_HASH = 1401173477L
    private val regionBakeNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_bake_navigation_mesh", REGION_BAKE_NAVIGATION_MESH_HASH)
    }

    private const val REGION_GET_CONNECTIONS_COUNT_HASH = 2198884583L
    private val regionGetConnectionsCountBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connections_count", REGION_GET_CONNECTIONS_COUNT_HASH)
    }

    private const val REGION_GET_CONNECTION_PATHWAY_START_HASH = 3440143363L
    private val regionGetConnectionPathwayStartBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_start", REGION_GET_CONNECTION_PATHWAY_START_HASH)
    }

    private const val REGION_GET_CONNECTION_PATHWAY_END_HASH = 3440143363L
    private val regionGetConnectionPathwayEndBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_end", REGION_GET_CONNECTION_PATHWAY_END_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
    private val regionGetClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_to_segment", REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_HASH = 2056183332L
    private val regionGetClosestPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point", REGION_GET_CLOSEST_POINT_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
    private val regionGetClosestPointNormalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_normal", REGION_GET_CLOSEST_POINT_NORMAL_HASH)
    }

    private const val REGION_GET_RANDOM_POINT_HASH = 722801526L
    private val regionGetRandomPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_random_point", REGION_GET_RANDOM_POINT_HASH)
    }

    private const val REGION_GET_BOUNDS_HASH = 974181306L
    private val regionGetBoundsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_bounds", REGION_GET_BOUNDS_HASH)
    }

    private const val LINK_CREATE_HASH = 529393457L
    private val linkCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_create", LINK_CREATE_HASH)
    }

    private const val LINK_GET_ITERATION_ID_HASH = 2198884583L
    private val linkGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_iteration_id", LINK_GET_ITERATION_ID_HASH)
    }

    private const val LINK_SET_MAP_HASH = 395945892L
    private val linkSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_map", LINK_SET_MAP_HASH)
    }

    private const val LINK_GET_MAP_HASH = 3814569979L
    private val linkGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_map", LINK_GET_MAP_HASH)
    }

    private const val LINK_SET_ENABLED_HASH = 1265174801L
    private val linkSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enabled", LINK_SET_ENABLED_HASH)
    }

    private const val LINK_GET_ENABLED_HASH = 4155700596L
    private val linkGetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enabled", LINK_GET_ENABLED_HASH)
    }

    private const val LINK_SET_BIDIRECTIONAL_HASH = 1265174801L
    private val linkSetBidirectionalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_bidirectional", LINK_SET_BIDIRECTIONAL_HASH)
    }

    private const val LINK_IS_BIDIRECTIONAL_HASH = 4155700596L
    private val linkIsBidirectionalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_is_bidirectional", LINK_IS_BIDIRECTIONAL_HASH)
    }

    private const val LINK_SET_NAVIGATION_LAYERS_HASH = 3411492887L
    private val linkSetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_navigation_layers", LINK_SET_NAVIGATION_LAYERS_HASH)
    }

    private const val LINK_GET_NAVIGATION_LAYERS_HASH = 2198884583L
    private val linkGetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_navigation_layers", LINK_GET_NAVIGATION_LAYERS_HASH)
    }

    private const val LINK_SET_START_POSITION_HASH = 3227306858L
    private val linkSetStartPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_start_position", LINK_SET_START_POSITION_HASH)
    }

    private const val LINK_GET_START_POSITION_HASH = 531438156L
    private val linkGetStartPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_start_position", LINK_GET_START_POSITION_HASH)
    }

    private const val LINK_SET_END_POSITION_HASH = 3227306858L
    private val linkSetEndPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_end_position", LINK_SET_END_POSITION_HASH)
    }

    private const val LINK_GET_END_POSITION_HASH = 531438156L
    private val linkGetEndPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_end_position", LINK_GET_END_POSITION_HASH)
    }

    private const val LINK_SET_ENTER_COST_HASH = 1794382983L
    private val linkSetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enter_cost", LINK_SET_ENTER_COST_HASH)
    }

    private const val LINK_GET_ENTER_COST_HASH = 866169185L
    private val linkGetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enter_cost", LINK_GET_ENTER_COST_HASH)
    }

    private const val LINK_SET_TRAVEL_COST_HASH = 1794382983L
    private val linkSetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_travel_cost", LINK_SET_TRAVEL_COST_HASH)
    }

    private const val LINK_GET_TRAVEL_COST_HASH = 866169185L
    private val linkGetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_travel_cost", LINK_GET_TRAVEL_COST_HASH)
    }

    private const val LINK_SET_OWNER_ID_HASH = 3411492887L
    private val linkSetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_owner_id", LINK_SET_OWNER_ID_HASH)
    }

    private const val LINK_GET_OWNER_ID_HASH = 2198884583L
    private val linkGetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_owner_id", LINK_GET_OWNER_ID_HASH)
    }

    private const val AGENT_CREATE_HASH = 529393457L
    private val agentCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_create", AGENT_CREATE_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
    private val agentSetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_enabled", AGENT_SET_AVOIDANCE_ENABLED_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
    private val agentGetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_enabled", AGENT_GET_AVOIDANCE_ENABLED_HASH)
    }

    private const val AGENT_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
    private val agentSetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_use_3d_avoidance", AGENT_SET_USE_3D_AVOIDANCE_HASH)
    }

    private const val AGENT_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
    private val agentGetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_use_3d_avoidance", AGENT_GET_USE_3D_AVOIDANCE_HASH)
    }

    private const val AGENT_SET_MAP_HASH = 395945892L
    private val agentSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_map", AGENT_SET_MAP_HASH)
    }

    private const val AGENT_GET_MAP_HASH = 3814569979L
    private val agentGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_map", AGENT_GET_MAP_HASH)
    }

    private const val AGENT_SET_PAUSED_HASH = 1265174801L
    private val agentSetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_paused", AGENT_SET_PAUSED_HASH)
    }

    private const val AGENT_GET_PAUSED_HASH = 4155700596L
    private val agentGetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_paused", AGENT_GET_PAUSED_HASH)
    }

    private const val AGENT_SET_NEIGHBOR_DISTANCE_HASH = 1794382983L
    private val agentSetNeighborDistanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_neighbor_distance", AGENT_SET_NEIGHBOR_DISTANCE_HASH)
    }

    private const val AGENT_GET_NEIGHBOR_DISTANCE_HASH = 866169185L
    private val agentGetNeighborDistanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_neighbor_distance", AGENT_GET_NEIGHBOR_DISTANCE_HASH)
    }

    private const val AGENT_SET_MAX_NEIGHBORS_HASH = 3411492887L
    private val agentSetMaxNeighborsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_neighbors", AGENT_SET_MAX_NEIGHBORS_HASH)
    }

    private const val AGENT_GET_MAX_NEIGHBORS_HASH = 2198884583L
    private val agentGetMaxNeighborsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_neighbors", AGENT_GET_MAX_NEIGHBORS_HASH)
    }

    private const val AGENT_SET_TIME_HORIZON_AGENTS_HASH = 1794382983L
    private val agentSetTimeHorizonAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_agents", AGENT_SET_TIME_HORIZON_AGENTS_HASH)
    }

    private const val AGENT_GET_TIME_HORIZON_AGENTS_HASH = 866169185L
    private val agentGetTimeHorizonAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_agents", AGENT_GET_TIME_HORIZON_AGENTS_HASH)
    }

    private const val AGENT_SET_TIME_HORIZON_OBSTACLES_HASH = 1794382983L
    private val agentSetTimeHorizonObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_obstacles", AGENT_SET_TIME_HORIZON_OBSTACLES_HASH)
    }

    private const val AGENT_GET_TIME_HORIZON_OBSTACLES_HASH = 866169185L
    private val agentGetTimeHorizonObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_obstacles", AGENT_GET_TIME_HORIZON_OBSTACLES_HASH)
    }

    private const val AGENT_SET_RADIUS_HASH = 1794382983L
    private val agentSetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_radius", AGENT_SET_RADIUS_HASH)
    }

    private const val AGENT_GET_RADIUS_HASH = 866169185L
    private val agentGetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_radius", AGENT_GET_RADIUS_HASH)
    }

    private const val AGENT_SET_HEIGHT_HASH = 1794382983L
    private val agentSetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_height", AGENT_SET_HEIGHT_HASH)
    }

    private const val AGENT_GET_HEIGHT_HASH = 866169185L
    private val agentGetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_height", AGENT_GET_HEIGHT_HASH)
    }

    private const val AGENT_SET_MAX_SPEED_HASH = 1794382983L
    private val agentSetMaxSpeedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_speed", AGENT_SET_MAX_SPEED_HASH)
    }

    private const val AGENT_GET_MAX_SPEED_HASH = 866169185L
    private val agentGetMaxSpeedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_speed", AGENT_GET_MAX_SPEED_HASH)
    }

    private const val AGENT_SET_VELOCITY_FORCED_HASH = 3227306858L
    private val agentSetVelocityForcedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity_forced", AGENT_SET_VELOCITY_FORCED_HASH)
    }

    private const val AGENT_SET_VELOCITY_HASH = 3227306858L
    private val agentSetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity", AGENT_SET_VELOCITY_HASH)
    }

    private const val AGENT_GET_VELOCITY_HASH = 531438156L
    private val agentGetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_velocity", AGENT_GET_VELOCITY_HASH)
    }

    private const val AGENT_SET_POSITION_HASH = 3227306858L
    private val agentSetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_position", AGENT_SET_POSITION_HASH)
    }

    private const val AGENT_GET_POSITION_HASH = 531438156L
    private val agentGetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_position", AGENT_GET_POSITION_HASH)
    }

    private const val AGENT_IS_MAP_CHANGED_HASH = 4155700596L
    private val agentIsMapChangedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_is_map_changed", AGENT_IS_MAP_CHANGED_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_CALLBACK_HASH = 3379118538L
    private val agentSetAvoidanceCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_callback", AGENT_SET_AVOIDANCE_CALLBACK_HASH)
    }

    private const val AGENT_HAS_AVOIDANCE_CALLBACK_HASH = 4155700596L
    private val agentHasAvoidanceCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_has_avoidance_callback", AGENT_HAS_AVOIDANCE_CALLBACK_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
    private val agentSetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_layers", AGENT_SET_AVOIDANCE_LAYERS_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
    private val agentGetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_layers", AGENT_GET_AVOIDANCE_LAYERS_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_MASK_HASH = 3411492887L
    private val agentSetAvoidanceMaskBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_mask", AGENT_SET_AVOIDANCE_MASK_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_MASK_HASH = 2198884583L
    private val agentGetAvoidanceMaskBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_mask", AGENT_GET_AVOIDANCE_MASK_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_PRIORITY_HASH = 1794382983L
    private val agentSetAvoidancePriorityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_priority", AGENT_SET_AVOIDANCE_PRIORITY_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_PRIORITY_HASH = 866169185L
    private val agentGetAvoidancePriorityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_priority", AGENT_GET_AVOIDANCE_PRIORITY_HASH)
    }

    private const val OBSTACLE_CREATE_HASH = 529393457L
    private val obstacleCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_create", OBSTACLE_CREATE_HASH)
    }

    private const val OBSTACLE_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
    private val obstacleSetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_enabled", OBSTACLE_SET_AVOIDANCE_ENABLED_HASH)
    }

    private const val OBSTACLE_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
    private val obstacleGetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_enabled", OBSTACLE_GET_AVOIDANCE_ENABLED_HASH)
    }

    private const val OBSTACLE_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
    private val obstacleSetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_use_3d_avoidance", OBSTACLE_SET_USE_3D_AVOIDANCE_HASH)
    }

    private const val OBSTACLE_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
    private val obstacleGetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_use_3d_avoidance", OBSTACLE_GET_USE_3D_AVOIDANCE_HASH)
    }

    private const val OBSTACLE_SET_MAP_HASH = 395945892L
    private val obstacleSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_map", OBSTACLE_SET_MAP_HASH)
    }

    private const val OBSTACLE_GET_MAP_HASH = 3814569979L
    private val obstacleGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_map", OBSTACLE_GET_MAP_HASH)
    }

    private const val OBSTACLE_SET_PAUSED_HASH = 1265174801L
    private val obstacleSetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_paused", OBSTACLE_SET_PAUSED_HASH)
    }

    private const val OBSTACLE_GET_PAUSED_HASH = 4155700596L
    private val obstacleGetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_paused", OBSTACLE_GET_PAUSED_HASH)
    }

    private const val OBSTACLE_SET_RADIUS_HASH = 1794382983L
    private val obstacleSetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_radius", OBSTACLE_SET_RADIUS_HASH)
    }

    private const val OBSTACLE_GET_RADIUS_HASH = 866169185L
    private val obstacleGetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_radius", OBSTACLE_GET_RADIUS_HASH)
    }

    private const val OBSTACLE_SET_HEIGHT_HASH = 1794382983L
    private val obstacleSetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_height", OBSTACLE_SET_HEIGHT_HASH)
    }

    private const val OBSTACLE_GET_HEIGHT_HASH = 866169185L
    private val obstacleGetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_height", OBSTACLE_GET_HEIGHT_HASH)
    }

    private const val OBSTACLE_SET_VELOCITY_HASH = 3227306858L
    private val obstacleSetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_velocity", OBSTACLE_SET_VELOCITY_HASH)
    }

    private const val OBSTACLE_GET_VELOCITY_HASH = 531438156L
    private val obstacleGetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_velocity", OBSTACLE_GET_VELOCITY_HASH)
    }

    private const val OBSTACLE_SET_POSITION_HASH = 3227306858L
    private val obstacleSetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_position", OBSTACLE_SET_POSITION_HASH)
    }

    private const val OBSTACLE_GET_POSITION_HASH = 531438156L
    private val obstacleGetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_position", OBSTACLE_GET_POSITION_HASH)
    }

    private const val OBSTACLE_SET_VERTICES_HASH = 4030257846L
    private val obstacleSetVerticesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
    }

    private const val OBSTACLE_GET_VERTICES_HASH = 808965560L
    private val obstacleGetVerticesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_vertices", OBSTACLE_GET_VERTICES_HASH)
    }

    private const val OBSTACLE_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
    private val obstacleSetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_layers", OBSTACLE_SET_AVOIDANCE_LAYERS_HASH)
    }

    private const val OBSTACLE_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
    private val obstacleGetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_layers", OBSTACLE_GET_AVOIDANCE_LAYERS_HASH)
    }

    private const val PARSE_SOURCE_GEOMETRY_DATA_HASH = 3172802542L
    private val parseSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "parse_source_geometry_data", PARSE_SOURCE_GEOMETRY_DATA_HASH)
    }

    private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH = 1286748856L
    private val bakeFromSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data", BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH)
    }

    private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH = 1286748856L
    private val bakeFromSourceGeometryDataAsyncBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data_async", BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH)
    }

    private const val IS_BAKING_NAVIGATION_MESH_HASH = 3142026141L
    private val isBakingNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "is_baking_navigation_mesh", IS_BAKING_NAVIGATION_MESH_HASH)
    }

    private const val SOURCE_GEOMETRY_PARSER_CREATE_HASH = 529393457L
    private val sourceGeometryParserCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_create", SOURCE_GEOMETRY_PARSER_CREATE_HASH)
    }

    private const val SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH = 3379118538L
    private val sourceGeometryParserSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_set_callback", SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH)
    }

    private const val SIMPLIFY_PATH_HASH = 2344122170L
    private val simplifyPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "simplify_path", SIMPLIFY_PATH_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "free_rid", FREE_RID_HASH)
    }

    private const val SET_ACTIVE_HASH = 2586408642L
    private val setActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "set_active", SET_ACTIVE_HASH)
    }

    private const val SET_DEBUG_ENABLED_HASH = 2586408642L
    private val setDebugEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "set_debug_enabled", SET_DEBUG_ENABLED_HASH)
    }

    private const val GET_DEBUG_ENABLED_HASH = 36873697L
    private val getDebugEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_debug_enabled", GET_DEBUG_ENABLED_HASH)
    }

    private const val GET_PROCESS_INFO_HASH = 1938440894L
    private val getProcessInfoBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_process_info", GET_PROCESS_INFO_HASH)
    }
}
