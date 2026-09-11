package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NavigationServer3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer3D waits on: ptrcallNoArgsRetRIDList, ptrcallWithRIDArgRetRIDList
// Index: docs/reference/generated/ios-shape-gap.md

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

private val navigationServer3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer3D")
}

private const val GET_MAPS_HASH = 3995934104L
private val getMapsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "get_maps", GET_MAPS_HASH)
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
