package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NavigationServer2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer2D waits on: ptrcallNoArgsRetRIDList, ptrcallWithRIDArgRetRIDList
// Index: docs/reference/generated/ios-shape-gap.md

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

private val navigationServer2DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer2D")
}

private const val GET_MAPS_HASH = 3995934104L
private val getMapsBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "get_maps", GET_MAPS_HASH)
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
