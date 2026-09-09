package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3i

// GENERATED desktop/Android companion for GridMap (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GridMap waits on: ptrcallNoArgsRetVector3iList, ptrcallWithAABBArgRetVector3iList,
//   ptrcallWithIntArgRetVector3iList, ptrcallWithVector3iAndIntArgRetVector3iList,
//   ptrcallWithVector3iArgRetVector3iList
// Index: docs/contributing/ios-shape-gap.md

fun GridMap.getUsedCells(): List<Vector3i> {
    return ObjectCalls.ptrcallNoArgsRetVector3iList(getUsedCellsBind, handle)
}

fun GridMap.getUsedCellsByItem(item: Int): List<Vector3i> {
    return ObjectCalls.ptrcallWithIntArgRetVector3iList(getUsedCellsByItemBind, handle, item)
}

fun GridMap.getUsedOctants(): List<Vector3i> {
    return ObjectCalls.ptrcallNoArgsRetVector3iList(getUsedOctantsBind, handle)
}

fun GridMap.getUsedOctantsByItem(item: Int): List<Vector3i> {
    return ObjectCalls.ptrcallWithIntArgRetVector3iList(getUsedOctantsByItemBind, handle, item)
}

fun GridMap.getUsedCellsInOctant(octantCoords: Vector3i): List<Vector3i> {
    return ObjectCalls.ptrcallWithVector3iArgRetVector3iList(getUsedCellsInOctantBind, handle, octantCoords)
}

fun GridMap.getUsedCellsInOctantByItem(octantCoords: Vector3i, item: Int): List<Vector3i> {
    return ObjectCalls.ptrcallWithVector3iAndIntArgRetVector3iList(getUsedCellsInOctantByItemBind, handle, octantCoords, item)
}

fun GridMap.getOctantsInBounds(bounds: AABB): List<Vector3i> {
    return ObjectCalls.ptrcallWithAABBArgRetVector3iList(getOctantsInBoundsBind, handle, bounds)
}

fun GridMap.getUsedOctantsInBounds(bounds: AABB): List<Vector3i> {
    return ObjectCalls.ptrcallWithAABBArgRetVector3iList(getUsedOctantsInBoundsBind, handle, bounds)
}

private const val GET_USED_CELLS_HASH = 3995934104L
private val getUsedCellsBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_cells", GET_USED_CELLS_HASH)
}

private const val GET_USED_CELLS_BY_ITEM_HASH = 663333327L
private val getUsedCellsByItemBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_cells_by_item", GET_USED_CELLS_BY_ITEM_HASH)
}

private const val GET_USED_OCTANTS_HASH = 3995934104L
private val getUsedOctantsBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_octants", GET_USED_OCTANTS_HASH)
}

private const val GET_USED_OCTANTS_BY_ITEM_HASH = 663333327L
private val getUsedOctantsByItemBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_octants_by_item", GET_USED_OCTANTS_BY_ITEM_HASH)
}

private const val GET_USED_CELLS_IN_OCTANT_HASH = 2658725580L
private val getUsedCellsInOctantBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant", GET_USED_CELLS_IN_OCTANT_HASH)
}

private const val GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH = 2384667821L
private val getUsedCellsInOctantByItemBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant_by_item", GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH)
}

private const val GET_OCTANTS_IN_BOUNDS_HASH = 2489849902L
private val getOctantsInBoundsBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_octants_in_bounds", GET_OCTANTS_IN_BOUNDS_HASH)
}

private const val GET_USED_OCTANTS_IN_BOUNDS_HASH = 2489849902L
private val getUsedOctantsInBoundsBind by lazy {
    ObjectCalls.getMethodBind("GridMap", "get_used_octants_in_bounds", GET_USED_OCTANTS_IN_BOUNDS_HASH)
}
