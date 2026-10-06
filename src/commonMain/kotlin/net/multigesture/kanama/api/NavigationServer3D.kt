package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Returns all created navigation map `RID`s on the NavigationServer. This returns both 2D and 3D
     * created navigation maps as there is technically no distinction between them.
     *
     * Generated from Godot docs: NavigationServer3D.get_maps
     */
    @JvmStatic
    fun getMaps(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.getMapsBind, singleton)
    }

    /**
     * Create a new map.
     *
     * Generated from Godot docs: NavigationServer3D.map_create
     */
    @JvmStatic
    fun mapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.mapCreateBind, singleton)
    }

    /**
     * Sets the map active.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_active
     */
    @JvmStatic
    fun mapSetActive(map: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.mapSetActiveBind, singleton, map, active)
    }

    /**
     * Returns `true` if the map is active.
     *
     * Generated from Godot docs: NavigationServer3D.map_is_active
     */
    @JvmStatic
    fun mapIsActive(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.mapIsActiveBind, singleton, map)
    }

    /**
     * Sets the map up direction.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_up
     */
    @JvmStatic
    fun mapSetUp(map: RID, up: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.mapSetUpBind, singleton, map, up)
    }

    /**
     * Returns the map's up direction.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_up
     */
    @JvmStatic
    fun mapGetUp(map: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.mapGetUpBind, singleton, map)
    }

    /**
     * Sets the map cell size used to rasterize the navigation mesh vertices on the XZ plane. Must
     * match with the cell size of the used navigation meshes.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_cell_size
     */
    @JvmStatic
    fun mapSetCellSize(map: RID, cellSize: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.mapSetCellSizeBind, singleton, map, cellSize)
    }

    /**
     * Returns the map cell size used to rasterize the navigation mesh vertices on the XZ plane.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_cell_size
     */
    @JvmStatic
    fun mapGetCellSize(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.mapGetCellSizeBind, singleton, map)
    }

    /**
     * Sets the map cell height used to rasterize the navigation mesh vertices on the Y axis. Must
     * match with the cell height of the used navigation meshes.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_cell_height
     */
    @JvmStatic
    fun mapSetCellHeight(map: RID, cellHeight: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.mapSetCellHeightBind, singleton, map, cellHeight)
    }

    /**
     * Returns the map cell height used to rasterize the navigation mesh vertices on the Y axis.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_cell_height
     */
    @JvmStatic
    fun mapGetCellHeight(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.mapGetCellHeightBind, singleton, map)
    }

    /**
     * Set the map's internal merge rasterizer cell scale used to control merging sensitivity.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_merge_rasterizer_cell_scale
     */
    @JvmStatic
    fun mapSetMergeRasterizerCellScale(map: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.mapSetMergeRasterizerCellScaleBind, singleton, map, scale)
    }

    /**
     * Returns map's internal merge rasterizer cell scale.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_merge_rasterizer_cell_scale
     */
    @JvmStatic
    fun mapGetMergeRasterizerCellScale(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.mapGetMergeRasterizerCellScaleBind, singleton, map)
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
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.mapSetUseEdgeConnectionsBind, singleton, map, enabled)
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
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.mapGetUseEdgeConnectionsBind, singleton, map)
    }

    /**
     * Set the map edge connection margin used to weld the compatible region edges.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_edge_connection_margin
     */
    @JvmStatic
    fun mapSetEdgeConnectionMargin(map: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.mapSetEdgeConnectionMarginBind, singleton, map, margin)
    }

    /**
     * Returns the edge connection margin of the map. This distance is the minimum vertex distance
     * needed to connect two edges from different regions.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_edge_connection_margin
     */
    @JvmStatic
    fun mapGetEdgeConnectionMargin(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.mapGetEdgeConnectionMarginBind, singleton, map)
    }

    /**
     * Set the map's link connection radius used to connect links to navigation polygons.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_link_connection_radius
     */
    @JvmStatic
    fun mapSetLinkConnectionRadius(map: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.mapSetLinkConnectionRadiusBind, singleton, map, radius)
    }

    /**
     * Returns the link connection radius of the map. This distance is the maximum range any link will
     * search for navigation mesh polygons to connect to.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_link_connection_radius
     */
    @JvmStatic
    fun mapGetLinkConnectionRadius(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.mapGetLinkConnectionRadiusBind, singleton, map)
    }

    /**
     * Returns the navigation path to reach the destination from the origin. `navigation_layers` is a
     * bitmask of all region navigation layers that are allowed to be in the path.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_path
     */
    @JvmStatic
    fun mapGetPath(map: RID, origin: Vector3, destination: Vector3, optimize: Boolean, navigationLayers: Long = 1L): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List(Binds.mapGetPathBind, singleton, map, origin, destination, optimize, navigationLayers)
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
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(Binds.mapGetClosestPointToSegmentBind, singleton, map, start, end, useCollision)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `to_point` on the navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point
     */
    @JvmStatic
    fun mapGetClosestPoint(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(Binds.mapGetClosestPointBind, singleton, map, toPoint)
    }

    /**
     * Returns the navigation mesh surface normal closest to the provided `to_point` on the navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point_normal
     */
    @JvmStatic
    fun mapGetClosestPointNormal(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(Binds.mapGetClosestPointNormalBind, singleton, map, toPoint)
    }

    /**
     * Returns the owner region RID for the navigation mesh surface point closest to the provided
     * `to_point` on the navigation `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_closest_point_owner
     */
    @JvmStatic
    fun mapGetClosestPointOwner(map: RID, toPoint: Vector3): RID {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetRID(Binds.mapGetClosestPointOwnerBind, singleton, map, toPoint)
    }

    /**
     * Returns all navigation link `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_links
     */
    @JvmStatic
    fun mapGetLinks(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(Binds.mapGetLinksBind, singleton, map)
    }

    /**
     * Returns all navigation regions `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_regions
     */
    @JvmStatic
    fun mapGetRegions(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(Binds.mapGetRegionsBind, singleton, map)
    }

    /**
     * Returns all navigation agents `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_agents
     */
    @JvmStatic
    fun mapGetAgents(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(Binds.mapGetAgentsBind, singleton, map)
    }

    /**
     * Returns all navigation obstacle `RID`s that are currently assigned to the requested navigation
     * `map`.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_obstacles
     */
    @JvmStatic
    fun mapGetObstacles(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(Binds.mapGetObstaclesBind, singleton, map)
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
        ObjectCalls.ptrcallWithRIDArg(Binds.mapForceUpdateBind, singleton, map)
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
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.mapGetIterationIdBind, singleton, map)
    }

    /**
     * If `enabled` is `true` the `map` synchronization uses an async process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.map_set_use_async_iterations
     */
    @JvmStatic
    fun mapSetUseAsyncIterations(map: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.mapSetUseAsyncIterationsBind, singleton, map, enabled)
    }

    /**
     * Returns `true` if the `map` synchronization uses an async process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.map_get_use_async_iterations
     */
    @JvmStatic
    fun mapGetUseAsyncIterations(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.mapGetUseAsyncIterationsBind, singleton, map)
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
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(Binds.mapGetRandomPointBind, singleton, map, navigationLayers, uniformly)
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
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(Binds.queryPathBind, singleton, parameters?.requireOpenHandle() ?: NULL_SEGMENT, result?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
    }

    /**
     * Creates a new region.
     *
     * Generated from Godot docs: NavigationServer3D.region_create
     */
    @JvmStatic
    fun regionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.regionCreateBind, singleton)
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
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.regionGetIterationIdBind, singleton, region)
    }

    /**
     * If `enabled` is `true` the `region` uses an async synchronization process that runs on a
     * background thread.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_use_async_iterations
     */
    @JvmStatic
    fun regionSetUseAsyncIterations(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.regionSetUseAsyncIterationsBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the `region` uses an async synchronization process that runs on a background
     * thread.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_use_async_iterations
     */
    @JvmStatic
    fun regionGetUseAsyncIterations(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.regionGetUseAsyncIterationsBind, singleton, region)
    }

    /**
     * If `enabled` is `true`, the specified `region` will contribute to its current navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_enabled
     */
    @JvmStatic
    fun regionSetEnabled(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.regionSetEnabledBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the specified `region` is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_enabled
     */
    @JvmStatic
    fun regionGetEnabled(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.regionGetEnabledBind, singleton, region)
    }

    /**
     * If `enabled` is `true`, the navigation `region` will use edge connections to connect with other
     * navigation regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_use_edge_connections
     */
    @JvmStatic
    fun regionSetUseEdgeConnections(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.regionSetUseEdgeConnectionsBind, singleton, region, enabled)
    }

    /**
     * Returns `true` if the navigation `region` is set to use edge connections to connect with other
     * navigation regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_use_edge_connections
     */
    @JvmStatic
    fun regionGetUseEdgeConnections(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.regionGetUseEdgeConnectionsBind, singleton, region)
    }

    /**
     * Sets the `enter_cost` for this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_enter_cost
     */
    @JvmStatic
    fun regionSetEnterCost(region: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.regionSetEnterCostBind, singleton, region, enterCost)
    }

    /**
     * Returns the enter cost of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_enter_cost
     */
    @JvmStatic
    fun regionGetEnterCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.regionGetEnterCostBind, singleton, region)
    }

    /**
     * Sets the `travel_cost` for this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_travel_cost
     */
    @JvmStatic
    fun regionSetTravelCost(region: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.regionSetTravelCostBind, singleton, region, travelCost)
    }

    /**
     * Returns the travel cost of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_travel_cost
     */
    @JvmStatic
    fun regionGetTravelCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.regionGetTravelCostBind, singleton, region)
    }

    /**
     * Set the `ObjectID` of the object which manages this region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_owner_id
     */
    @JvmStatic
    fun regionSetOwnerId(region: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.regionSetOwnerIdBind, singleton, region, ownerId)
    }

    /**
     * Returns the `ObjectID` of the object which manages this region.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_owner_id
     */
    @JvmStatic
    fun regionGetOwnerId(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.regionGetOwnerIdBind, singleton, region)
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
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetBool(Binds.regionOwnsPointBind, singleton, region, point)
    }

    /**
     * Sets the map for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_map
     */
    @JvmStatic
    fun regionSetMap(region: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(Binds.regionSetMapBind, singleton, region, map)
    }

    /**
     * Returns the navigation map `RID` the requested `region` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_map
     */
    @JvmStatic
    fun regionGetMap(region: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.regionGetMapBind, singleton, region)
    }

    /**
     * Set the region's navigation layers. This allows selecting regions from a path request (when
     * using `NavigationServer3D.map_get_path`).
     *
     * Generated from Godot docs: NavigationServer3D.region_set_navigation_layers
     */
    @JvmStatic
    fun regionSetNavigationLayers(region: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(Binds.regionSetNavigationLayersBind, singleton, region, navigationLayers)
    }

    /**
     * Returns the region's navigation layers.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_navigation_layers
     */
    @JvmStatic
    fun regionGetNavigationLayers(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.regionGetNavigationLayersBind, singleton, region)
    }

    /**
     * Sets the global transformation for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_transform
     */
    @JvmStatic
    fun regionSetTransform(region: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(Binds.regionSetTransformBind, singleton, region, transform)
    }

    /**
     * Returns the global transformation of this `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_transform
     */
    @JvmStatic
    fun regionGetTransform(region: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(Binds.regionGetTransformBind, singleton, region)
    }

    /**
     * Sets the navigation mesh for the region.
     *
     * Generated from Godot docs: NavigationServer3D.region_set_navigation_mesh
     */
    @JvmStatic
    fun regionSetNavigationMesh(region: RID, navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithRIDAndObjectArg(Binds.regionSetNavigationMeshBind, singleton, region, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Bakes the `navigation_mesh` with bake source geometry collected starting from the `root_node`.
     *
     * Generated from Godot docs: NavigationServer3D.region_bake_navigation_mesh
     */
    @JvmStatic
    fun regionBakeNavigationMesh(navigationMesh: NavigationMesh?, rootNode: Node) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.regionBakeNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, rootNode.segment)
    }

    /**
     * Returns how many connections this `region` has with other regions in the map.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connections_count
     */
    @JvmStatic
    fun regionGetConnectionsCount(region: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(Binds.regionGetConnectionsCountBind, singleton, region)
    }

    /**
     * Returns the starting point of a connection door. `connection` is an index between 0 and the
     * return value of `region_get_connections_count`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connection_pathway_start
     */
    @JvmStatic
    fun regionGetConnectionPathwayStart(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(Binds.regionGetConnectionPathwayStartBind, singleton, region, connection)
    }

    /**
     * Returns the ending point of a connection door. `connection` is an index between 0 and the return
     * value of `region_get_connections_count`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_connection_pathway_end
     */
    @JvmStatic
    fun regionGetConnectionPathwayEnd(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(Binds.regionGetConnectionPathwayEndBind, singleton, region, connection)
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
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(Binds.regionGetClosestPointToSegmentBind, singleton, region, start, end, useCollision)
    }

    /**
     * Returns the navigation mesh surface point closest to the provided `to_point` on the navigation
     * `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_closest_point
     */
    @JvmStatic
    fun regionGetClosestPoint(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(Binds.regionGetClosestPointBind, singleton, region, toPoint)
    }

    /**
     * Returns the navigation mesh surface normal closest to the provided `to_point` on the navigation
     * `region`.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_closest_point_normal
     */
    @JvmStatic
    fun regionGetClosestPointNormal(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(Binds.regionGetClosestPointNormalBind, singleton, region, toPoint)
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
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(Binds.regionGetRandomPointBind, singleton, region, navigationLayers, uniformly)
    }

    /**
     * Returns the axis-aligned bounding box for the `region`'s transformed navigation mesh.
     *
     * Generated from Godot docs: NavigationServer3D.region_get_bounds
     */
    @JvmStatic
    fun regionGetBounds(region: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(Binds.regionGetBoundsBind, singleton, region)
    }

    /**
     * Create a new link between two positions on a map.
     *
     * Generated from Godot docs: NavigationServer3D.link_create
     */
    @JvmStatic
    fun linkCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.linkCreateBind, singleton)
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
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.linkGetIterationIdBind, singleton, link)
    }

    /**
     * Sets the navigation map `RID` for the link.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_map
     */
    @JvmStatic
    fun linkSetMap(link: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(Binds.linkSetMapBind, singleton, link, map)
    }

    /**
     * Returns the navigation map `RID` the requested `link` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_map
     */
    @JvmStatic
    fun linkGetMap(link: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.linkGetMapBind, singleton, link)
    }

    /**
     * If `enabled` is `true`, the specified `link` will contribute to its current navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_enabled
     */
    @JvmStatic
    fun linkSetEnabled(link: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.linkSetEnabledBind, singleton, link, enabled)
    }

    /**
     * Returns `true` if the specified `link` is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_enabled
     */
    @JvmStatic
    fun linkGetEnabled(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.linkGetEnabledBind, singleton, link)
    }

    /**
     * Sets whether this `link` can be travelled in both directions.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_bidirectional
     */
    @JvmStatic
    fun linkSetBidirectional(link: RID, bidirectional: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.linkSetBidirectionalBind, singleton, link, bidirectional)
    }

    /**
     * Returns whether this `link` can be travelled in both directions.
     *
     * Generated from Godot docs: NavigationServer3D.link_is_bidirectional
     */
    @JvmStatic
    fun linkIsBidirectional(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.linkIsBidirectionalBind, singleton, link)
    }

    /**
     * Set the links's navigation layers. This allows selecting links from a path request (when using
     * `NavigationServer3D.map_get_path`).
     *
     * Generated from Godot docs: NavigationServer3D.link_set_navigation_layers
     */
    @JvmStatic
    fun linkSetNavigationLayers(link: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(Binds.linkSetNavigationLayersBind, singleton, link, navigationLayers)
    }

    /**
     * Returns the navigation layers for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_navigation_layers
     */
    @JvmStatic
    fun linkGetNavigationLayers(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.linkGetNavigationLayersBind, singleton, link)
    }

    /**
     * Sets the entry position for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_start_position
     */
    @JvmStatic
    fun linkSetStartPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.linkSetStartPositionBind, singleton, link, position)
    }

    /**
     * Returns the starting position of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_start_position
     */
    @JvmStatic
    fun linkGetStartPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.linkGetStartPositionBind, singleton, link)
    }

    /**
     * Sets the exit position for the `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_end_position
     */
    @JvmStatic
    fun linkSetEndPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.linkSetEndPositionBind, singleton, link, position)
    }

    /**
     * Returns the ending position of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_end_position
     */
    @JvmStatic
    fun linkGetEndPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.linkGetEndPositionBind, singleton, link)
    }

    /**
     * Sets the `enter_cost` for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_enter_cost
     */
    @JvmStatic
    fun linkSetEnterCost(link: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.linkSetEnterCostBind, singleton, link, enterCost)
    }

    /**
     * Returns the enter cost of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_enter_cost
     */
    @JvmStatic
    fun linkGetEnterCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.linkGetEnterCostBind, singleton, link)
    }

    /**
     * Sets the `travel_cost` for this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_travel_cost
     */
    @JvmStatic
    fun linkSetTravelCost(link: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.linkSetTravelCostBind, singleton, link, travelCost)
    }

    /**
     * Returns the travel cost of this `link`.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_travel_cost
     */
    @JvmStatic
    fun linkGetTravelCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.linkGetTravelCostBind, singleton, link)
    }

    /**
     * Set the `ObjectID` of the object which manages this link.
     *
     * Generated from Godot docs: NavigationServer3D.link_set_owner_id
     */
    @JvmStatic
    fun linkSetOwnerId(link: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.linkSetOwnerIdBind, singleton, link, ownerId)
    }

    /**
     * Returns the `ObjectID` of the object which manages this link.
     *
     * Generated from Godot docs: NavigationServer3D.link_get_owner_id
     */
    @JvmStatic
    fun linkGetOwnerId(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.linkGetOwnerIdBind, singleton, link)
    }

    /**
     * Creates the agent.
     *
     * Generated from Godot docs: NavigationServer3D.agent_create
     */
    @JvmStatic
    fun agentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.agentCreateBind, singleton)
    }

    /**
     * If `enabled` is `true`, the provided `agent` calculates avoidance.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_enabled
     */
    @JvmStatic
    fun agentSetAvoidanceEnabled(agent: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.agentSetAvoidanceEnabledBind, singleton, agent, enabled)
    }

    /**
     * Returns `true` if the provided `agent` has avoidance enabled.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_enabled
     */
    @JvmStatic
    fun agentGetAvoidanceEnabled(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.agentGetAvoidanceEnabledBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.agentSetUse3dAvoidanceBind, singleton, agent, enabled)
    }

    /**
     * Returns `true` if the provided `agent` uses avoidance in 3D space Vector3(x,y,z) instead of
     * horizontal 2D Vector2(x,y) / Vector3(x,0.0,z).
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_use_3d_avoidance
     */
    @JvmStatic
    fun agentGetUse3dAvoidance(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.agentGetUse3dAvoidanceBind, singleton, agent)
    }

    /**
     * Puts the agent in the map.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_map
     */
    @JvmStatic
    fun agentSetMap(agent: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(Binds.agentSetMapBind, singleton, agent, map)
    }

    /**
     * Returns the navigation map `RID` the requested `agent` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_map
     */
    @JvmStatic
    fun agentGetMap(agent: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.agentGetMapBind, singleton, agent)
    }

    /**
     * If `paused` is `true` the specified `agent` will not be processed. For example, it will not
     * calculate avoidance velocities or receive avoidance callbacks.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_paused
     */
    @JvmStatic
    fun agentSetPaused(agent: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.agentSetPausedBind, singleton, agent, paused)
    }

    /**
     * Returns `true` if the specified `agent` is paused.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_paused
     */
    @JvmStatic
    fun agentGetPaused(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.agentGetPausedBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetNeighborDistanceBind, singleton, agent, distance)
    }

    /**
     * Returns the maximum distance to other agents the specified `agent` takes into account in the
     * navigation.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_neighbor_distance
     */
    @JvmStatic
    fun agentGetNeighborDistance(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetNeighborDistanceBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndIntArg(Binds.agentSetMaxNeighborsBind, singleton, agent, count)
    }

    /**
     * Returns the maximum number of other agents the specified `agent` takes into account in the
     * navigation.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_max_neighbors
     */
    @JvmStatic
    fun agentGetMaxNeighbors(agent: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(Binds.agentGetMaxNeighborsBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetTimeHorizonAgentsBind, singleton, agent, timeHorizon)
    }

    /**
     * Returns the minimal amount of time for which the specified `agent`'s velocities that are
     * computed by the simulation are safe with respect to other agents.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_time_horizon_agents
     */
    @JvmStatic
    fun agentGetTimeHorizonAgents(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetTimeHorizonAgentsBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetTimeHorizonObstaclesBind, singleton, agent, timeHorizon)
    }

    /**
     * Returns the minimal amount of time for which the specified `agent`'s velocities that are
     * computed by the simulation are safe with respect to static avoidance obstacles.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_time_horizon_obstacles
     */
    @JvmStatic
    fun agentGetTimeHorizonObstacles(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetTimeHorizonObstaclesBind, singleton, agent)
    }

    /**
     * Sets the radius of the agent.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_radius
     */
    @JvmStatic
    fun agentSetRadius(agent: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetRadiusBind, singleton, agent, radius)
    }

    /**
     * Returns the radius of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_radius
     */
    @JvmStatic
    fun agentGetRadius(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetRadiusBind, singleton, agent)
    }

    /**
     * Updates the provided `agent` `height`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_height
     */
    @JvmStatic
    fun agentSetHeight(agent: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetHeightBind, singleton, agent, height)
    }

    /**
     * Returns the `height` of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_height
     */
    @JvmStatic
    fun agentGetHeight(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetHeightBind, singleton, agent)
    }

    /**
     * Sets the maximum speed of the agent. Must be positive.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_max_speed
     */
    @JvmStatic
    fun agentSetMaxSpeed(agent: RID, maxSpeed: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetMaxSpeedBind, singleton, agent, maxSpeed)
    }

    /**
     * Returns the maximum speed of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_max_speed
     */
    @JvmStatic
    fun agentGetMaxSpeed(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetMaxSpeedBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.agentSetVelocityForcedBind, singleton, agent, velocity)
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
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.agentSetVelocityBind, singleton, agent, velocity)
    }

    /**
     * Returns the velocity of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_velocity
     */
    @JvmStatic
    fun agentGetVelocity(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.agentGetVelocityBind, singleton, agent)
    }

    /**
     * Sets the position of the agent in world space.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_position
     */
    @JvmStatic
    fun agentSetPosition(agent: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.agentSetPositionBind, singleton, agent, position)
    }

    /**
     * Returns the position of the specified `agent` in world space.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_position
     */
    @JvmStatic
    fun agentGetPosition(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.agentGetPositionBind, singleton, agent)
    }

    /**
     * Returns `true` if the map got changed the previous frame.
     *
     * Generated from Godot docs: NavigationServer3D.agent_is_map_changed
     */
    @JvmStatic
    fun agentIsMapChanged(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.agentIsMapChangedBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDCallableArgs(Binds.agentSetAvoidanceCallbackBind, singleton, agent, callback.target.segment, callback.method)
    }

    /**
     * Return `true` if the specified `agent` has an avoidance callback.
     *
     * Generated from Godot docs: NavigationServer3D.agent_has_avoidance_callback
     */
    @JvmStatic
    fun agentHasAvoidanceCallback(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.agentHasAvoidanceCallbackBind, singleton, agent)
    }

    /**
     * Set the agent's `avoidance_layers` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_layers
     */
    @JvmStatic
    fun agentSetAvoidanceLayers(agent: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(Binds.agentSetAvoidanceLayersBind, singleton, agent, layers)
    }

    /**
     * Returns the `avoidance_layers` bitmask of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_layers
     */
    @JvmStatic
    fun agentGetAvoidanceLayers(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.agentGetAvoidanceLayersBind, singleton, agent)
    }

    /**
     * Set the agent's `avoidance_mask` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.agent_set_avoidance_mask
     */
    @JvmStatic
    fun agentSetAvoidanceMask(agent: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(Binds.agentSetAvoidanceMaskBind, singleton, agent, mask)
    }

    /**
     * Returns the `avoidance_mask` bitmask of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_mask
     */
    @JvmStatic
    fun agentGetAvoidanceMask(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.agentGetAvoidanceMaskBind, singleton, agent)
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
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.agentSetAvoidancePriorityBind, singleton, agent, priority)
    }

    /**
     * Returns the `avoidance_priority` of the specified `agent`.
     *
     * Generated from Godot docs: NavigationServer3D.agent_get_avoidance_priority
     */
    @JvmStatic
    fun agentGetAvoidancePriority(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.agentGetAvoidancePriorityBind, singleton, agent)
    }

    /**
     * Creates a new obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_create
     */
    @JvmStatic
    fun obstacleCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.obstacleCreateBind, singleton)
    }

    /**
     * If `enabled` is `true`, the provided `obstacle` affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_avoidance_enabled
     */
    @JvmStatic
    fun obstacleSetAvoidanceEnabled(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.obstacleSetAvoidanceEnabledBind, singleton, obstacle, enabled)
    }

    /**
     * Returns `true` if the provided `obstacle` has avoidance enabled.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_avoidance_enabled
     */
    @JvmStatic
    fun obstacleGetAvoidanceEnabled(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.obstacleGetAvoidanceEnabledBind, singleton, obstacle)
    }

    /**
     * Sets if the `obstacle` uses the 2D avoidance or the 3D avoidance while avoidance is enabled.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_use_3d_avoidance
     */
    @JvmStatic
    fun obstacleSetUse3dAvoidance(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.obstacleSetUse3dAvoidanceBind, singleton, obstacle, enabled)
    }

    /**
     * Returns `true` if the provided `obstacle` uses avoidance in 3D space Vector3(x,y,z) instead of
     * horizontal 2D Vector2(x,y) / Vector3(x,0.0,z).
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_use_3d_avoidance
     */
    @JvmStatic
    fun obstacleGetUse3dAvoidance(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.obstacleGetUse3dAvoidanceBind, singleton, obstacle)
    }

    /**
     * Assigns the `obstacle` to a navigation map.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_map
     */
    @JvmStatic
    fun obstacleSetMap(obstacle: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(Binds.obstacleSetMapBind, singleton, obstacle, map)
    }

    /**
     * Returns the navigation map `RID` the requested `obstacle` is currently assigned to.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_map
     */
    @JvmStatic
    fun obstacleGetMap(obstacle: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.obstacleGetMapBind, singleton, obstacle)
    }

    /**
     * If `paused` is `true` the specified `obstacle` will not be processed. For example, it will no
     * longer affect avoidance velocities.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_paused
     */
    @JvmStatic
    fun obstacleSetPaused(obstacle: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.obstacleSetPausedBind, singleton, obstacle, paused)
    }

    /**
     * Returns `true` if the specified `obstacle` is paused.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_paused
     */
    @JvmStatic
    fun obstacleGetPaused(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.obstacleGetPausedBind, singleton, obstacle)
    }

    /**
     * Sets the radius of the dynamic obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_radius
     */
    @JvmStatic
    fun obstacleSetRadius(obstacle: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.obstacleSetRadiusBind, singleton, obstacle, radius)
    }

    /**
     * Returns the radius of the specified dynamic `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_radius
     */
    @JvmStatic
    fun obstacleGetRadius(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.obstacleGetRadiusBind, singleton, obstacle)
    }

    /**
     * Sets the `height` for the `obstacle`. In 3D agents will ignore obstacles that are above or below
     * them while using 2D avoidance.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_height
     */
    @JvmStatic
    fun obstacleSetHeight(obstacle: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.obstacleSetHeightBind, singleton, obstacle, height)
    }

    /**
     * Returns the `height` of the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_height
     */
    @JvmStatic
    fun obstacleGetHeight(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.obstacleGetHeightBind, singleton, obstacle)
    }

    /**
     * Sets `velocity` of the dynamic `obstacle`. Allows other agents to better predict the movement of
     * the dynamic obstacle. Only works in combination with the radius of the obstacle.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_velocity
     */
    @JvmStatic
    fun obstacleSetVelocity(obstacle: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.obstacleSetVelocityBind, singleton, obstacle, velocity)
    }

    /**
     * Returns the velocity of the specified dynamic `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_velocity
     */
    @JvmStatic
    fun obstacleGetVelocity(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.obstacleGetVelocityBind, singleton, obstacle)
    }

    /**
     * Updates the `position` in world space for the `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_position
     */
    @JvmStatic
    fun obstacleSetPosition(obstacle: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(Binds.obstacleSetPositionBind, singleton, obstacle, position)
    }

    /**
     * Returns the position of the specified `obstacle` in world space.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_position
     */
    @JvmStatic
    fun obstacleGetPosition(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(Binds.obstacleGetPositionBind, singleton, obstacle)
    }

    /**
     * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
     * will be pushed in by the obstacle, else they will be pushed out.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_vertices
     */
    @JvmStatic
    fun obstacleSetVertices(obstacle: RID, vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithRIDAndPackedVector3ListArg(Binds.obstacleSetVerticesBind, singleton, obstacle, vertices)
    }

    /**
     * Returns the outline vertices for the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_vertices
     */
    @JvmStatic
    fun obstacleGetVertices(obstacle: RID): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(Binds.obstacleGetVerticesBind, singleton, obstacle)
    }

    /**
     * Set the obstacles's `avoidance_layers` bitmask.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_set_avoidance_layers
     */
    @JvmStatic
    fun obstacleSetAvoidanceLayers(obstacle: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(Binds.obstacleSetAvoidanceLayersBind, singleton, obstacle, layers)
    }

    /**
     * Returns the `avoidance_layers` bitmask of the specified `obstacle`.
     *
     * Generated from Godot docs: NavigationServer3D.obstacle_get_avoidance_layers
     */
    @JvmStatic
    fun obstacleGetAvoidanceLayers(obstacle: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.obstacleGetAvoidanceLayersBind, singleton, obstacle)
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
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(Binds.parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, sourceGeometryData?.requireOpenHandle() ?: NULL_SEGMENT, rootNode.segment, callback.target.segment, callback.method)
    }

    /**
     * Bakes the provided `navigation_mesh` with the data from the provided `source_geometry_data`.
     * After the process is finished the optional `callback` will be called.
     *
     * Generated from Godot docs: NavigationServer3D.bake_from_source_geometry_data
     */
    @JvmStatic
    fun bakeFromSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(Binds.bakeFromSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, sourceGeometryData?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
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
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(Binds.bakeFromSourceGeometryDataAsyncBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, sourceGeometryData?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
    }

    /**
     * Returns `true` when the provided navigation mesh is being baked on a background thread.
     *
     * Generated from Godot docs: NavigationServer3D.is_baking_navigation_mesh
     */
    @JvmStatic
    fun isBakingNavigationMesh(navigationMesh: NavigationMesh?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.isBakingNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT)
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
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.sourceGeometryParserCreateBind, singleton)
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
        ObjectCalls.ptrcallWithRIDCallableArgs(Binds.sourceGeometryParserSetCallbackBind, singleton, parser, callback.target.segment, callback.method)
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
        return ObjectCalls.ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List(Binds.simplifyPathBind, singleton, path, epsilon)
    }

    /**
     * Destroys the given RID.
     *
     * Generated from Godot docs: NavigationServer3D.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.freeRidBind, singleton, rid)
    }

    /**
     * Control activation of this server.
     *
     * Generated from Godot docs: NavigationServer3D.set_active
     */
    @JvmStatic
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setActiveBind, singleton, active)
    }

    /**
     * If `true` enables debug mode on the NavigationServer.
     *
     * Generated from Godot docs: NavigationServer3D.set_debug_enabled
     */
    @JvmStatic
    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDebugEnabledBind, singleton, enabled)
    }

    /**
     * Returns `true` when the NavigationServer has debug enabled.
     *
     * Generated from Godot docs: NavigationServer3D.get_debug_enabled
     */
    @JvmStatic
    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDebugEnabledBind, singleton)
    }

    /**
     * Returns information about the current state of the NavigationServer.
     *
     * Generated from Godot docs: NavigationServer3D.get_process_info
     */
    @JvmStatic
    fun getProcessInfo(processInfo: NavigationServer3D.ProcessInfo): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getProcessInfoBind, singleton, processInfo.value)
    }

    /** Signal `map_changed(map: RID)`; see [TypedSignal]. */
    val mapChanged: Signal1<RID>
        @JvmName("mapChangedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "map_changed", SignalArgType.valueOf<RID>("RID", RID::class))

    /** Signal `navigation_debug_changed()`; see [TypedSignal]. */
    val navigationDebugChanged: Signal0
        @JvmName("navigationDebugChangedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "navigation_debug_changed")

    /** Signal `avoidance_debug_changed()`; see [TypedSignal]. */
    val avoidanceDebugChanged: Signal0
        @JvmName("avoidanceDebugChangedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "avoidance_debug_changed")

    object Signals {
        const val mapChanged: String = "map_changed"
        const val navigationDebugChanged: String = "navigation_debug_changed"
        const val avoidanceDebugChanged: String = "avoidance_debug_changed"
    }

    /**
     * Godot's `NavigationServer3D.ProcessInfo` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`NavigationServer3D.ProcessInfo.<NAME>`).
     *
     * Generated from Godot docs: NavigationServer3D.ProcessInfo
     */
    @JvmInline
    value class ProcessInfo(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Constant to get the number of active navigation maps.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_ACTIVE_MAPS
             */
            val ACTIVE_MAPS: ProcessInfo get() = ProcessInfo(0L)
            /**
             * Constant to get the number of active navigation regions.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_REGION_COUNT
             */
            val REGION_COUNT: ProcessInfo get() = ProcessInfo(1L)
            /**
             * Constant to get the number of active navigation agents processing avoidance.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_AGENT_COUNT
             */
            val AGENT_COUNT: ProcessInfo get() = ProcessInfo(2L)
            /**
             * Constant to get the number of active navigation links.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_LINK_COUNT
             */
            val LINK_COUNT: ProcessInfo get() = ProcessInfo(3L)
            /**
             * Constant to get the number of navigation mesh polygons.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_POLYGON_COUNT
             */
            val POLYGON_COUNT: ProcessInfo get() = ProcessInfo(4L)
            /**
             * Constant to get the number of navigation mesh polygon edges.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_EDGE_COUNT
             */
            val EDGE_COUNT: ProcessInfo get() = ProcessInfo(5L)
            /**
             * Constant to get the number of navigation mesh polygon edges that were merged due to edge key
             * overlap.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_EDGE_MERGE_COUNT
             */
            val EDGE_MERGE_COUNT: ProcessInfo get() = ProcessInfo(6L)
            /**
             * Constant to get the number of navigation mesh polygon edges that are considered connected by
             * edge proximity.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_EDGE_CONNECTION_COUNT
             */
            val EDGE_CONNECTION_COUNT: ProcessInfo get() = ProcessInfo(7L)
            /**
             * Constant to get the number of navigation mesh polygon edges that could not be merged but may be
             * still connected by edge proximity or with links.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_EDGE_FREE_COUNT
             */
            val EDGE_FREE_COUNT: ProcessInfo get() = ProcessInfo(8L)
            /**
             * Constant to get the number of active navigation obstacles.
             *
             * Generated from Godot docs: NavigationServer3D.INFO_OBSTACLE_COUNT
             */
            val OBSTACLE_COUNT: ProcessInfo get() = ProcessInfo(9L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): NavigationServer3D? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): NavigationServer3D? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("NavigationServer3D")

        private const val GET_MAPS_HASH = 3995934104L
        @JvmField
        val getMapsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "get_maps", GET_MAPS_HASH)

        private const val MAP_CREATE_HASH = 529393457L
        @JvmField
        val mapCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_create", MAP_CREATE_HASH)

        private const val MAP_SET_ACTIVE_HASH = 1265174801L
        @JvmField
        val mapSetActiveBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_active", MAP_SET_ACTIVE_HASH)

        private const val MAP_IS_ACTIVE_HASH = 4155700596L
        @JvmField
        val mapIsActiveBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_is_active", MAP_IS_ACTIVE_HASH)

        private const val MAP_SET_UP_HASH = 3227306858L
        @JvmField
        val mapSetUpBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_up", MAP_SET_UP_HASH)

        private const val MAP_GET_UP_HASH = 531438156L
        @JvmField
        val mapGetUpBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_up", MAP_GET_UP_HASH)

        private const val MAP_SET_CELL_SIZE_HASH = 1794382983L
        @JvmField
        val mapSetCellSizeBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_size", MAP_SET_CELL_SIZE_HASH)

        private const val MAP_GET_CELL_SIZE_HASH = 866169185L
        @JvmField
        val mapGetCellSizeBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_size", MAP_GET_CELL_SIZE_HASH)

        private const val MAP_SET_CELL_HEIGHT_HASH = 1794382983L
        @JvmField
        val mapSetCellHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_height", MAP_SET_CELL_HEIGHT_HASH)

        private const val MAP_GET_CELL_HEIGHT_HASH = 866169185L
        @JvmField
        val mapGetCellHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_height", MAP_GET_CELL_HEIGHT_HASH)

        private const val MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH = 1794382983L
        @JvmField
        val mapSetMergeRasterizerCellScaleBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_merge_rasterizer_cell_scale", MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH)

        private const val MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH = 866169185L
        @JvmField
        val mapGetMergeRasterizerCellScaleBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_merge_rasterizer_cell_scale", MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH)

        private const val MAP_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
        @JvmField
        val mapSetUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_edge_connections", MAP_SET_USE_EDGE_CONNECTIONS_HASH)

        private const val MAP_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
        @JvmField
        val mapGetUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_edge_connections", MAP_GET_USE_EDGE_CONNECTIONS_HASH)

        private const val MAP_SET_EDGE_CONNECTION_MARGIN_HASH = 1794382983L
        @JvmField
        val mapSetEdgeConnectionMarginBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_edge_connection_margin", MAP_SET_EDGE_CONNECTION_MARGIN_HASH)

        private const val MAP_GET_EDGE_CONNECTION_MARGIN_HASH = 866169185L
        @JvmField
        val mapGetEdgeConnectionMarginBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_edge_connection_margin", MAP_GET_EDGE_CONNECTION_MARGIN_HASH)

        private const val MAP_SET_LINK_CONNECTION_RADIUS_HASH = 1794382983L
        @JvmField
        val mapSetLinkConnectionRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_link_connection_radius", MAP_SET_LINK_CONNECTION_RADIUS_HASH)

        private const val MAP_GET_LINK_CONNECTION_RADIUS_HASH = 866169185L
        @JvmField
        val mapGetLinkConnectionRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_link_connection_radius", MAP_GET_LINK_CONNECTION_RADIUS_HASH)

        private const val MAP_GET_PATH_HASH = 276783190L
        @JvmField
        val mapGetPathBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_path", MAP_GET_PATH_HASH)

        private const val MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
        @JvmField
        val mapGetClosestPointToSegmentBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_to_segment", MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH)

        private const val MAP_GET_CLOSEST_POINT_HASH = 2056183332L
        @JvmField
        val mapGetClosestPointBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point", MAP_GET_CLOSEST_POINT_HASH)

        private const val MAP_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
        @JvmField
        val mapGetClosestPointNormalBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_normal", MAP_GET_CLOSEST_POINT_NORMAL_HASH)

        private const val MAP_GET_CLOSEST_POINT_OWNER_HASH = 553364610L
        @JvmField
        val mapGetClosestPointOwnerBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_owner", MAP_GET_CLOSEST_POINT_OWNER_HASH)

        private const val MAP_GET_LINKS_HASH = 2684255073L
        @JvmField
        val mapGetLinksBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_links", MAP_GET_LINKS_HASH)

        private const val MAP_GET_REGIONS_HASH = 2684255073L
        @JvmField
        val mapGetRegionsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_regions", MAP_GET_REGIONS_HASH)

        private const val MAP_GET_AGENTS_HASH = 2684255073L
        @JvmField
        val mapGetAgentsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_agents", MAP_GET_AGENTS_HASH)

        private const val MAP_GET_OBSTACLES_HASH = 2684255073L
        @JvmField
        val mapGetObstaclesBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_obstacles", MAP_GET_OBSTACLES_HASH)

        private const val MAP_FORCE_UPDATE_HASH = 2722037293L
        @JvmField
        val mapForceUpdateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_force_update", MAP_FORCE_UPDATE_HASH)

        private const val MAP_GET_ITERATION_ID_HASH = 2198884583L
        @JvmField
        val mapGetIterationIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_iteration_id", MAP_GET_ITERATION_ID_HASH)

        private const val MAP_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
        @JvmField
        val mapSetUseAsyncIterationsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_async_iterations", MAP_SET_USE_ASYNC_ITERATIONS_HASH)

        private const val MAP_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
        @JvmField
        val mapGetUseAsyncIterationsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_async_iterations", MAP_GET_USE_ASYNC_ITERATIONS_HASH)

        private const val MAP_GET_RANDOM_POINT_HASH = 722801526L
        @JvmField
        val mapGetRandomPointBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "map_get_random_point", MAP_GET_RANDOM_POINT_HASH)

        private const val QUERY_PATH_HASH = 2146930868L
        @JvmField
        val queryPathBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "query_path", QUERY_PATH_HASH)

        private const val REGION_CREATE_HASH = 529393457L
        @JvmField
        val regionCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_create", REGION_CREATE_HASH)

        private const val REGION_GET_ITERATION_ID_HASH = 2198884583L
        @JvmField
        val regionGetIterationIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_iteration_id", REGION_GET_ITERATION_ID_HASH)

        private const val REGION_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
        @JvmField
        val regionSetUseAsyncIterationsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_async_iterations", REGION_SET_USE_ASYNC_ITERATIONS_HASH)

        private const val REGION_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
        @JvmField
        val regionGetUseAsyncIterationsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_async_iterations", REGION_GET_USE_ASYNC_ITERATIONS_HASH)

        private const val REGION_SET_ENABLED_HASH = 1265174801L
        @JvmField
        val regionSetEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enabled", REGION_SET_ENABLED_HASH)

        private const val REGION_GET_ENABLED_HASH = 4155700596L
        @JvmField
        val regionGetEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enabled", REGION_GET_ENABLED_HASH)

        private const val REGION_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
        @JvmField
        val regionSetUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_edge_connections", REGION_SET_USE_EDGE_CONNECTIONS_HASH)

        private const val REGION_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
        @JvmField
        val regionGetUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_edge_connections", REGION_GET_USE_EDGE_CONNECTIONS_HASH)

        private const val REGION_SET_ENTER_COST_HASH = 1794382983L
        @JvmField
        val regionSetEnterCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enter_cost", REGION_SET_ENTER_COST_HASH)

        private const val REGION_GET_ENTER_COST_HASH = 866169185L
        @JvmField
        val regionGetEnterCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enter_cost", REGION_GET_ENTER_COST_HASH)

        private const val REGION_SET_TRAVEL_COST_HASH = 1794382983L
        @JvmField
        val regionSetTravelCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_travel_cost", REGION_SET_TRAVEL_COST_HASH)

        private const val REGION_GET_TRAVEL_COST_HASH = 866169185L
        @JvmField
        val regionGetTravelCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_travel_cost", REGION_GET_TRAVEL_COST_HASH)

        private const val REGION_SET_OWNER_ID_HASH = 3411492887L
        @JvmField
        val regionSetOwnerIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_owner_id", REGION_SET_OWNER_ID_HASH)

        private const val REGION_GET_OWNER_ID_HASH = 2198884583L
        @JvmField
        val regionGetOwnerIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_owner_id", REGION_GET_OWNER_ID_HASH)

        private const val REGION_OWNS_POINT_HASH = 2360011153L
        @JvmField
        val regionOwnsPointBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_owns_point", REGION_OWNS_POINT_HASH)

        private const val REGION_SET_MAP_HASH = 395945892L
        @JvmField
        val regionSetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_map", REGION_SET_MAP_HASH)

        private const val REGION_GET_MAP_HASH = 3814569979L
        @JvmField
        val regionGetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_map", REGION_GET_MAP_HASH)

        private const val REGION_SET_NAVIGATION_LAYERS_HASH = 3411492887L
        @JvmField
        val regionSetNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_layers", REGION_SET_NAVIGATION_LAYERS_HASH)

        private const val REGION_GET_NAVIGATION_LAYERS_HASH = 2198884583L
        @JvmField
        val regionGetNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_navigation_layers", REGION_GET_NAVIGATION_LAYERS_HASH)

        private const val REGION_SET_TRANSFORM_HASH = 3935195649L
        @JvmField
        val regionSetTransformBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_transform", REGION_SET_TRANSFORM_HASH)

        private const val REGION_GET_TRANSFORM_HASH = 1128465797L
        @JvmField
        val regionGetTransformBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_transform", REGION_GET_TRANSFORM_HASH)

        private const val REGION_SET_NAVIGATION_MESH_HASH = 2764952978L
        @JvmField
        val regionSetNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_mesh", REGION_SET_NAVIGATION_MESH_HASH)

        private const val REGION_BAKE_NAVIGATION_MESH_HASH = 1401173477L
        @JvmField
        val regionBakeNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_bake_navigation_mesh", REGION_BAKE_NAVIGATION_MESH_HASH)

        private const val REGION_GET_CONNECTIONS_COUNT_HASH = 2198884583L
        @JvmField
        val regionGetConnectionsCountBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connections_count", REGION_GET_CONNECTIONS_COUNT_HASH)

        private const val REGION_GET_CONNECTION_PATHWAY_START_HASH = 3440143363L
        @JvmField
        val regionGetConnectionPathwayStartBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_start", REGION_GET_CONNECTION_PATHWAY_START_HASH)

        private const val REGION_GET_CONNECTION_PATHWAY_END_HASH = 3440143363L
        @JvmField
        val regionGetConnectionPathwayEndBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_end", REGION_GET_CONNECTION_PATHWAY_END_HASH)

        private const val REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
        @JvmField
        val regionGetClosestPointToSegmentBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_to_segment", REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH)

        private const val REGION_GET_CLOSEST_POINT_HASH = 2056183332L
        @JvmField
        val regionGetClosestPointBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point", REGION_GET_CLOSEST_POINT_HASH)

        private const val REGION_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
        @JvmField
        val regionGetClosestPointNormalBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_normal", REGION_GET_CLOSEST_POINT_NORMAL_HASH)

        private const val REGION_GET_RANDOM_POINT_HASH = 722801526L
        @JvmField
        val regionGetRandomPointBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_random_point", REGION_GET_RANDOM_POINT_HASH)

        private const val REGION_GET_BOUNDS_HASH = 974181306L
        @JvmField
        val regionGetBoundsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "region_get_bounds", REGION_GET_BOUNDS_HASH)

        private const val LINK_CREATE_HASH = 529393457L
        @JvmField
        val linkCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_create", LINK_CREATE_HASH)

        private const val LINK_GET_ITERATION_ID_HASH = 2198884583L
        @JvmField
        val linkGetIterationIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_iteration_id", LINK_GET_ITERATION_ID_HASH)

        private const val LINK_SET_MAP_HASH = 395945892L
        @JvmField
        val linkSetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_map", LINK_SET_MAP_HASH)

        private const val LINK_GET_MAP_HASH = 3814569979L
        @JvmField
        val linkGetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_map", LINK_GET_MAP_HASH)

        private const val LINK_SET_ENABLED_HASH = 1265174801L
        @JvmField
        val linkSetEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enabled", LINK_SET_ENABLED_HASH)

        private const val LINK_GET_ENABLED_HASH = 4155700596L
        @JvmField
        val linkGetEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enabled", LINK_GET_ENABLED_HASH)

        private const val LINK_SET_BIDIRECTIONAL_HASH = 1265174801L
        @JvmField
        val linkSetBidirectionalBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_bidirectional", LINK_SET_BIDIRECTIONAL_HASH)

        private const val LINK_IS_BIDIRECTIONAL_HASH = 4155700596L
        @JvmField
        val linkIsBidirectionalBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_is_bidirectional", LINK_IS_BIDIRECTIONAL_HASH)

        private const val LINK_SET_NAVIGATION_LAYERS_HASH = 3411492887L
        @JvmField
        val linkSetNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_navigation_layers", LINK_SET_NAVIGATION_LAYERS_HASH)

        private const val LINK_GET_NAVIGATION_LAYERS_HASH = 2198884583L
        @JvmField
        val linkGetNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_navigation_layers", LINK_GET_NAVIGATION_LAYERS_HASH)

        private const val LINK_SET_START_POSITION_HASH = 3227306858L
        @JvmField
        val linkSetStartPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_start_position", LINK_SET_START_POSITION_HASH)

        private const val LINK_GET_START_POSITION_HASH = 531438156L
        @JvmField
        val linkGetStartPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_start_position", LINK_GET_START_POSITION_HASH)

        private const val LINK_SET_END_POSITION_HASH = 3227306858L
        @JvmField
        val linkSetEndPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_end_position", LINK_SET_END_POSITION_HASH)

        private const val LINK_GET_END_POSITION_HASH = 531438156L
        @JvmField
        val linkGetEndPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_end_position", LINK_GET_END_POSITION_HASH)

        private const val LINK_SET_ENTER_COST_HASH = 1794382983L
        @JvmField
        val linkSetEnterCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enter_cost", LINK_SET_ENTER_COST_HASH)

        private const val LINK_GET_ENTER_COST_HASH = 866169185L
        @JvmField
        val linkGetEnterCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enter_cost", LINK_GET_ENTER_COST_HASH)

        private const val LINK_SET_TRAVEL_COST_HASH = 1794382983L
        @JvmField
        val linkSetTravelCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_travel_cost", LINK_SET_TRAVEL_COST_HASH)

        private const val LINK_GET_TRAVEL_COST_HASH = 866169185L
        @JvmField
        val linkGetTravelCostBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_travel_cost", LINK_GET_TRAVEL_COST_HASH)

        private const val LINK_SET_OWNER_ID_HASH = 3411492887L
        @JvmField
        val linkSetOwnerIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_set_owner_id", LINK_SET_OWNER_ID_HASH)

        private const val LINK_GET_OWNER_ID_HASH = 2198884583L
        @JvmField
        val linkGetOwnerIdBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "link_get_owner_id", LINK_GET_OWNER_ID_HASH)

        private const val AGENT_CREATE_HASH = 529393457L
        @JvmField
        val agentCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_create", AGENT_CREATE_HASH)

        private const val AGENT_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
        @JvmField
        val agentSetAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_enabled", AGENT_SET_AVOIDANCE_ENABLED_HASH)

        private const val AGENT_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
        @JvmField
        val agentGetAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_enabled", AGENT_GET_AVOIDANCE_ENABLED_HASH)

        private const val AGENT_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
        @JvmField
        val agentSetUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_use_3d_avoidance", AGENT_SET_USE_3D_AVOIDANCE_HASH)

        private const val AGENT_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
        @JvmField
        val agentGetUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_use_3d_avoidance", AGENT_GET_USE_3D_AVOIDANCE_HASH)

        private const val AGENT_SET_MAP_HASH = 395945892L
        @JvmField
        val agentSetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_map", AGENT_SET_MAP_HASH)

        private const val AGENT_GET_MAP_HASH = 3814569979L
        @JvmField
        val agentGetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_map", AGENT_GET_MAP_HASH)

        private const val AGENT_SET_PAUSED_HASH = 1265174801L
        @JvmField
        val agentSetPausedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_paused", AGENT_SET_PAUSED_HASH)

        private const val AGENT_GET_PAUSED_HASH = 4155700596L
        @JvmField
        val agentGetPausedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_paused", AGENT_GET_PAUSED_HASH)

        private const val AGENT_SET_NEIGHBOR_DISTANCE_HASH = 1794382983L
        @JvmField
        val agentSetNeighborDistanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_neighbor_distance", AGENT_SET_NEIGHBOR_DISTANCE_HASH)

        private const val AGENT_GET_NEIGHBOR_DISTANCE_HASH = 866169185L
        @JvmField
        val agentGetNeighborDistanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_neighbor_distance", AGENT_GET_NEIGHBOR_DISTANCE_HASH)

        private const val AGENT_SET_MAX_NEIGHBORS_HASH = 3411492887L
        @JvmField
        val agentSetMaxNeighborsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_neighbors", AGENT_SET_MAX_NEIGHBORS_HASH)

        private const val AGENT_GET_MAX_NEIGHBORS_HASH = 2198884583L
        @JvmField
        val agentGetMaxNeighborsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_neighbors", AGENT_GET_MAX_NEIGHBORS_HASH)

        private const val AGENT_SET_TIME_HORIZON_AGENTS_HASH = 1794382983L
        @JvmField
        val agentSetTimeHorizonAgentsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_agents", AGENT_SET_TIME_HORIZON_AGENTS_HASH)

        private const val AGENT_GET_TIME_HORIZON_AGENTS_HASH = 866169185L
        @JvmField
        val agentGetTimeHorizonAgentsBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_agents", AGENT_GET_TIME_HORIZON_AGENTS_HASH)

        private const val AGENT_SET_TIME_HORIZON_OBSTACLES_HASH = 1794382983L
        @JvmField
        val agentSetTimeHorizonObstaclesBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_obstacles", AGENT_SET_TIME_HORIZON_OBSTACLES_HASH)

        private const val AGENT_GET_TIME_HORIZON_OBSTACLES_HASH = 866169185L
        @JvmField
        val agentGetTimeHorizonObstaclesBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_obstacles", AGENT_GET_TIME_HORIZON_OBSTACLES_HASH)

        private const val AGENT_SET_RADIUS_HASH = 1794382983L
        @JvmField
        val agentSetRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_radius", AGENT_SET_RADIUS_HASH)

        private const val AGENT_GET_RADIUS_HASH = 866169185L
        @JvmField
        val agentGetRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_radius", AGENT_GET_RADIUS_HASH)

        private const val AGENT_SET_HEIGHT_HASH = 1794382983L
        @JvmField
        val agentSetHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_height", AGENT_SET_HEIGHT_HASH)

        private const val AGENT_GET_HEIGHT_HASH = 866169185L
        @JvmField
        val agentGetHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_height", AGENT_GET_HEIGHT_HASH)

        private const val AGENT_SET_MAX_SPEED_HASH = 1794382983L
        @JvmField
        val agentSetMaxSpeedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_speed", AGENT_SET_MAX_SPEED_HASH)

        private const val AGENT_GET_MAX_SPEED_HASH = 866169185L
        @JvmField
        val agentGetMaxSpeedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_speed", AGENT_GET_MAX_SPEED_HASH)

        private const val AGENT_SET_VELOCITY_FORCED_HASH = 3227306858L
        @JvmField
        val agentSetVelocityForcedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity_forced", AGENT_SET_VELOCITY_FORCED_HASH)

        private const val AGENT_SET_VELOCITY_HASH = 3227306858L
        @JvmField
        val agentSetVelocityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity", AGENT_SET_VELOCITY_HASH)

        private const val AGENT_GET_VELOCITY_HASH = 531438156L
        @JvmField
        val agentGetVelocityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_velocity", AGENT_GET_VELOCITY_HASH)

        private const val AGENT_SET_POSITION_HASH = 3227306858L
        @JvmField
        val agentSetPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_position", AGENT_SET_POSITION_HASH)

        private const val AGENT_GET_POSITION_HASH = 531438156L
        @JvmField
        val agentGetPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_position", AGENT_GET_POSITION_HASH)

        private const val AGENT_IS_MAP_CHANGED_HASH = 4155700596L
        @JvmField
        val agentIsMapChangedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_is_map_changed", AGENT_IS_MAP_CHANGED_HASH)

        private const val AGENT_SET_AVOIDANCE_CALLBACK_HASH = 3379118538L
        @JvmField
        val agentSetAvoidanceCallbackBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_callback", AGENT_SET_AVOIDANCE_CALLBACK_HASH)

        private const val AGENT_HAS_AVOIDANCE_CALLBACK_HASH = 4155700596L
        @JvmField
        val agentHasAvoidanceCallbackBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_has_avoidance_callback", AGENT_HAS_AVOIDANCE_CALLBACK_HASH)

        private const val AGENT_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
        @JvmField
        val agentSetAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_layers", AGENT_SET_AVOIDANCE_LAYERS_HASH)

        private const val AGENT_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
        @JvmField
        val agentGetAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_layers", AGENT_GET_AVOIDANCE_LAYERS_HASH)

        private const val AGENT_SET_AVOIDANCE_MASK_HASH = 3411492887L
        @JvmField
        val agentSetAvoidanceMaskBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_mask", AGENT_SET_AVOIDANCE_MASK_HASH)

        private const val AGENT_GET_AVOIDANCE_MASK_HASH = 2198884583L
        @JvmField
        val agentGetAvoidanceMaskBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_mask", AGENT_GET_AVOIDANCE_MASK_HASH)

        private const val AGENT_SET_AVOIDANCE_PRIORITY_HASH = 1794382983L
        @JvmField
        val agentSetAvoidancePriorityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_priority", AGENT_SET_AVOIDANCE_PRIORITY_HASH)

        private const val AGENT_GET_AVOIDANCE_PRIORITY_HASH = 866169185L
        @JvmField
        val agentGetAvoidancePriorityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_priority", AGENT_GET_AVOIDANCE_PRIORITY_HASH)

        private const val OBSTACLE_CREATE_HASH = 529393457L
        @JvmField
        val obstacleCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_create", OBSTACLE_CREATE_HASH)

        private const val OBSTACLE_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
        @JvmField
        val obstacleSetAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_enabled", OBSTACLE_SET_AVOIDANCE_ENABLED_HASH)

        private const val OBSTACLE_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
        @JvmField
        val obstacleGetAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_enabled", OBSTACLE_GET_AVOIDANCE_ENABLED_HASH)

        private const val OBSTACLE_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
        @JvmField
        val obstacleSetUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_use_3d_avoidance", OBSTACLE_SET_USE_3D_AVOIDANCE_HASH)

        private const val OBSTACLE_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
        @JvmField
        val obstacleGetUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_use_3d_avoidance", OBSTACLE_GET_USE_3D_AVOIDANCE_HASH)

        private const val OBSTACLE_SET_MAP_HASH = 395945892L
        @JvmField
        val obstacleSetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_map", OBSTACLE_SET_MAP_HASH)

        private const val OBSTACLE_GET_MAP_HASH = 3814569979L
        @JvmField
        val obstacleGetMapBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_map", OBSTACLE_GET_MAP_HASH)

        private const val OBSTACLE_SET_PAUSED_HASH = 1265174801L
        @JvmField
        val obstacleSetPausedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_paused", OBSTACLE_SET_PAUSED_HASH)

        private const val OBSTACLE_GET_PAUSED_HASH = 4155700596L
        @JvmField
        val obstacleGetPausedBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_paused", OBSTACLE_GET_PAUSED_HASH)

        private const val OBSTACLE_SET_RADIUS_HASH = 1794382983L
        @JvmField
        val obstacleSetRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_radius", OBSTACLE_SET_RADIUS_HASH)

        private const val OBSTACLE_GET_RADIUS_HASH = 866169185L
        @JvmField
        val obstacleGetRadiusBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_radius", OBSTACLE_GET_RADIUS_HASH)

        private const val OBSTACLE_SET_HEIGHT_HASH = 1794382983L
        @JvmField
        val obstacleSetHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_height", OBSTACLE_SET_HEIGHT_HASH)

        private const val OBSTACLE_GET_HEIGHT_HASH = 866169185L
        @JvmField
        val obstacleGetHeightBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_height", OBSTACLE_GET_HEIGHT_HASH)

        private const val OBSTACLE_SET_VELOCITY_HASH = 3227306858L
        @JvmField
        val obstacleSetVelocityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_velocity", OBSTACLE_SET_VELOCITY_HASH)

        private const val OBSTACLE_GET_VELOCITY_HASH = 531438156L
        @JvmField
        val obstacleGetVelocityBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_velocity", OBSTACLE_GET_VELOCITY_HASH)

        private const val OBSTACLE_SET_POSITION_HASH = 3227306858L
        @JvmField
        val obstacleSetPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_position", OBSTACLE_SET_POSITION_HASH)

        private const val OBSTACLE_GET_POSITION_HASH = 531438156L
        @JvmField
        val obstacleGetPositionBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_position", OBSTACLE_GET_POSITION_HASH)

        private const val OBSTACLE_SET_VERTICES_HASH = 4030257846L
        @JvmField
        val obstacleSetVerticesBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)

        private const val OBSTACLE_GET_VERTICES_HASH = 808965560L
        @JvmField
        val obstacleGetVerticesBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_vertices", OBSTACLE_GET_VERTICES_HASH)

        private const val OBSTACLE_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
        @JvmField
        val obstacleSetAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_layers", OBSTACLE_SET_AVOIDANCE_LAYERS_HASH)

        private const val OBSTACLE_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
        @JvmField
        val obstacleGetAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_layers", OBSTACLE_GET_AVOIDANCE_LAYERS_HASH)

        private const val PARSE_SOURCE_GEOMETRY_DATA_HASH = 3172802542L
        @JvmField
        val parseSourceGeometryDataBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "parse_source_geometry_data", PARSE_SOURCE_GEOMETRY_DATA_HASH)

        private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH = 1286748856L
        @JvmField
        val bakeFromSourceGeometryDataBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data", BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH)

        private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH = 1286748856L
        @JvmField
        val bakeFromSourceGeometryDataAsyncBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data_async", BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH)

        private const val IS_BAKING_NAVIGATION_MESH_HASH = 3142026141L
        @JvmField
        val isBakingNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "is_baking_navigation_mesh", IS_BAKING_NAVIGATION_MESH_HASH)

        private const val SOURCE_GEOMETRY_PARSER_CREATE_HASH = 529393457L
        @JvmField
        val sourceGeometryParserCreateBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_create", SOURCE_GEOMETRY_PARSER_CREATE_HASH)

        private const val SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH = 3379118538L
        @JvmField
        val sourceGeometryParserSetCallbackBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_set_callback", SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH)

        private const val SIMPLIFY_PATH_HASH = 2344122170L
        @JvmField
        val simplifyPathBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "simplify_path", SIMPLIFY_PATH_HASH)

        private const val FREE_RID_HASH = 2722037293L
        @JvmField
        val freeRidBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "free_rid", FREE_RID_HASH)

        private const val SET_ACTIVE_HASH = 2586408642L
        @JvmField
        val setActiveBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "set_active", SET_ACTIVE_HASH)

        private const val SET_DEBUG_ENABLED_HASH = 2586408642L
        @JvmField
        val setDebugEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "set_debug_enabled", SET_DEBUG_ENABLED_HASH)

        private const val GET_DEBUG_ENABLED_HASH = 36873697L
        @JvmField
        val getDebugEnabledBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "get_debug_enabled", GET_DEBUG_ENABLED_HASH)

        private const val GET_PROCESS_INFO_HASH = 1938440894L
        @JvmField
        val getProcessInfoBind =
            ObjectCalls.getMethodBind("NavigationServer3D", "get_process_info", GET_PROCESS_INFO_HASH)
    }
}
