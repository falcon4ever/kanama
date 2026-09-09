package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationServer3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer3D waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List,
//   ptrcallWithRIDAndPackedVector3ListArg, ptrcallWithRIDArgRetPackedVector3List,
//   ptrcallWithRIDArgRetRIDList, ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns all created navigation map `RID`s on the NavigationServer. This returns both 2D and 3D
 * created navigation maps as there is technically no distinction between them.
 *
 * Generated from Godot docs: NavigationServer3D.get_maps
 */
fun NavigationServer3D.getMaps(): List<RID> {
    return ObjectCalls.ptrcallNoArgsRetRIDList(getMapsBind, navigationServer3DSingleton)
}

/**
 * Returns the navigation path to reach the destination from the origin. `navigation_layers` is a
 * bitmask of all region navigation layers that are allowed to be in the path.
 *
 * Generated from Godot docs: NavigationServer3D.map_get_path
 */
fun NavigationServer3D.mapGetPath(map: RID, origin: Vector3, destination: Vector3, optimize: Boolean, navigationLayers: Long = 1L): List<Vector3> {
    return ObjectCalls.ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List(mapGetPathBind, navigationServer3DSingleton, map, origin, destination, optimize, navigationLayers)
}

/**
 * Returns all navigation link `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer3D.map_get_links
 */
fun NavigationServer3D.mapGetLinks(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetLinksBind, navigationServer3DSingleton, map)
}

/**
 * Returns all navigation regions `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer3D.map_get_regions
 */
fun NavigationServer3D.mapGetRegions(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetRegionsBind, navigationServer3DSingleton, map)
}

/**
 * Returns all navigation agents `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer3D.map_get_agents
 */
fun NavigationServer3D.mapGetAgents(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetAgentsBind, navigationServer3DSingleton, map)
}

/**
 * Returns all navigation obstacle `RID`s that are currently assigned to the requested navigation
 * `map`.
 *
 * Generated from Godot docs: NavigationServer3D.map_get_obstacles
 */
fun NavigationServer3D.mapGetObstacles(map: RID): List<RID> {
    return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetObstaclesBind, navigationServer3DSingleton, map)
}

/**
 * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
 * will be pushed in by the obstacle, else they will be pushed out.
 *
 * Generated from Godot docs: NavigationServer3D.obstacle_set_vertices
 */
fun NavigationServer3D.obstacleSetVertices(obstacle: RID, vertices: List<Vector3>) {
    ObjectCalls.ptrcallWithRIDAndPackedVector3ListArg(obstacleSetVerticesBind, navigationServer3DSingleton, obstacle, vertices)
}

/**
 * Returns the outline vertices for the specified `obstacle`.
 *
 * Generated from Godot docs: NavigationServer3D.obstacle_get_vertices
 */
fun NavigationServer3D.obstacleGetVertices(obstacle: RID): List<Vector3> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(obstacleGetVerticesBind, navigationServer3DSingleton, obstacle)
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
fun NavigationServer3D.simplifyPath(path: List<Vector3>, epsilon: Double): List<Vector3> {
    return ObjectCalls.ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List(simplifyPathBind, navigationServer3DSingleton, path, epsilon)
}

private val navigationServer3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer3D")
}

private const val GET_MAPS_HASH = 3995934104L
private val getMapsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "get_maps", GET_MAPS_HASH)
}

private const val MAP_GET_PATH_HASH = 276783190L
private val mapGetPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "map_get_path", MAP_GET_PATH_HASH)
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

private const val OBSTACLE_SET_VERTICES_HASH = 4030257846L
private val obstacleSetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
}

private const val OBSTACLE_GET_VERTICES_HASH = 808965560L
private val obstacleGetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_vertices", OBSTACLE_GET_VERTICES_HASH)
}

private const val SIMPLIFY_PATH_HASH = 2344122170L
private val simplifyPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "simplify_path", SIMPLIFY_PATH_HASH)
}
