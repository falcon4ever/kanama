package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationServer2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer2D waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithPackedVector2ListAndDoubleArgRetPackedVector2List,
//   ptrcallWithRIDAndPackedVector2ListArg, ptrcallWithRIDArgRetPackedVector2List,
//   ptrcallWithRIDArgRetRIDList, ptrcallWithRIDTwoVector2BoolUInt32ArgsRetPackedVector2List
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns all created navigation map `RID`s on the NavigationServer. This returns both 2D and 3D
 * created navigation maps as there is technically no distinction between them.
 *
 * Generated from Godot docs: NavigationServer2D.get_maps
 */
fun NavigationServer2D.getMaps(): List<RID> {
    return ObjectCalls.ptrcallNoArgsRetRIDList(getMapsBind, navigationServer2DSingleton)
}

/**
 * Returns the navigation path to reach the destination from the origin. `navigation_layers` is a
 * bitmask of all region navigation layers that are allowed to be in the path.
 *
 * Generated from Godot docs: NavigationServer2D.map_get_path
 */
fun NavigationServer2D.mapGetPath(map: RID, origin: Vector2, destination: Vector2, optimize: Boolean, navigationLayers: Long = 1L): List<Vector2> {
    return ObjectCalls.ptrcallWithRIDTwoVector2BoolUInt32ArgsRetPackedVector2List(mapGetPathBind, navigationServer2DSingleton, map, origin, destination, optimize, navigationLayers)
}

/**
 * Returns all navigation link `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer2D.map_get_links
 */
fun NavigationServer2D.mapGetLinks(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetLinksBind, navigationServer2DSingleton, map)
}

/**
 * Returns all navigation regions `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer2D.map_get_regions
 */
fun NavigationServer2D.mapGetRegions(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetRegionsBind, navigationServer2DSingleton, map)
}

/**
 * Returns all navigation agents `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer2D.map_get_agents
 */
fun NavigationServer2D.mapGetAgents(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetAgentsBind, navigationServer2DSingleton, map)
}

/**
 * Returns all navigation obstacle `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer2D.map_get_obstacles
 */
fun NavigationServer2D.mapGetObstacles(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetObstaclesBind, navigationServer2DSingleton, map)
}

/**
 * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
 * will be pushed in by the obstacle, else they will be pushed out.
 *
 * Generated from Godot docs: NavigationServer2D.obstacle_set_vertices
 */
fun NavigationServer2D.obstacleSetVertices(obstacle: RID, vertices: List<Vector2>) {
    ObjectCalls.ptrcallWithRIDAndPackedVector2ListArg(obstacleSetVerticesBind, navigationServer2DSingleton, obstacle, vertices)
}

/**
 * Returns the outline vertices for the specified `obstacle`.
 *
 * Generated from Godot docs: NavigationServer2D.obstacle_get_vertices
 */
fun NavigationServer2D.obstacleGetVertices(obstacle: RID): List<Vector2> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedVector2List(obstacleGetVerticesBind, navigationServer2DSingleton, obstacle)
}

/**
 * Returns a simplified version of `path` with less critical path points removed. The
 * simplification amount is in worlds units and controlled by `epsilon`. The simplification uses a
 * variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
 * be helpful to mitigate various path following issues that can arise with certain agent types and
 * script behaviors. E.g. "steering" agents or avoidance in "open fields".
 *
 * Generated from Godot docs: NavigationServer2D.simplify_path
 */
fun NavigationServer2D.simplifyPath(path: List<Vector2>, epsilon: Double): List<Vector2> {
    return ObjectCalls.ptrcallWithPackedVector2ListAndDoubleArgRetPackedVector2List(simplifyPathBind, navigationServer2DSingleton, path, epsilon)
}

private val navigationServer2DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer2D")
}

private const val GET_MAPS_HASH = 3995934104L
private val getMapsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "get_maps", GET_MAPS_HASH)
}

private const val MAP_GET_PATH_HASH = 1279824844L
private val mapGetPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "map_get_path", MAP_GET_PATH_HASH)
}

private const val MAP_GET_LINKS_HASH = 2684255073L
private val mapGetLinksBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "map_get_links", MAP_GET_LINKS_HASH)
}

private const val MAP_GET_REGIONS_HASH = 2684255073L
private val mapGetRegionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "map_get_regions", MAP_GET_REGIONS_HASH)
}

private const val MAP_GET_AGENTS_HASH = 2684255073L
private val mapGetAgentsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "map_get_agents", MAP_GET_AGENTS_HASH)
}

private const val MAP_GET_OBSTACLES_HASH = 2684255073L
private val mapGetObstaclesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "map_get_obstacles", MAP_GET_OBSTACLES_HASH)
}

private const val OBSTACLE_SET_VERTICES_HASH = 29476483L
private val obstacleSetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
}

private const val OBSTACLE_GET_VERTICES_HASH = 2222557395L
private val obstacleGetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "obstacle_get_vertices", OBSTACLE_GET_VERTICES_HASH)
}

private const val SIMPLIFY_PATH_HASH = 2457191505L
private val simplifyPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "simplify_path", SIMPLIFY_PATH_HASH)
}
