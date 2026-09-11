package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i

// GENERATED desktop/Android companion for AStarGrid2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AStarGrid2D waits on: ptrcallWithRect2iAndBoolArg, ptrcallWithRect2iAndDoubleArg,
//   ptrcallWithRect2iArg, ptrcallWithRect2iArgRetDictionaryList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The region of grid cells available for pathfinding. If changed, `update` needs to be called
 * before finding the next path.
 *
 * Generated from Godot docs: AStarGrid2D.set_region
 */
fun AStarGrid2D.setRegion(region: Rect2i) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iArg(setRegionBind, handle, region)
}

/**
 * Fills the given `region` on the grid with the specified value for the solid flag. Note: Calling
 * `update` is not needed after the call of this function.
 *
 * Generated from Godot docs: AStarGrid2D.fill_solid_region
 */
fun AStarGrid2D.fillSolidRegion(region: Rect2i, solid: Boolean = true) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iAndBoolArg(fillSolidRegionBind, handle, region, solid)
}

/**
 * Fills the given `region` on the grid with the specified value for the weight scale. Note:
 * Calling `update` is not needed after the call of this function.
 *
 * Generated from Godot docs: AStarGrid2D.fill_weight_scale_region
 */
fun AStarGrid2D.fillWeightScaleRegion(region: Rect2i, weightScale: Double) {
    checkOpen()
    ObjectCalls.ptrcallWithRect2iAndDoubleArg(fillWeightScaleRegionBind, handle, region, weightScale)
}

/**
 * Returns an array of dictionaries with point data (`id`: `Vector2i`, `position`: `Vector2`,
 * `solid`: `bool`, `weight_scale`: `float`) within a `region`.
 *
 * Generated from Godot docs: AStarGrid2D.get_point_data_in_region
 */
fun AStarGrid2D.getPointDataInRegion(region: Rect2i): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRect2iArgRetDictionaryList(getPointDataInRegionBind, handle, region)
}

private const val SET_REGION_HASH = 1763793166L
private val setRegionBind by lazy {
    ObjectCalls.getMethodBind("AStarGrid2D", "set_region", SET_REGION_HASH)
}

private const val FILL_SOLID_REGION_HASH = 2261970063L
private val fillSolidRegionBind by lazy {
    ObjectCalls.getMethodBind("AStarGrid2D", "fill_solid_region", FILL_SOLID_REGION_HASH)
}

private const val FILL_WEIGHT_SCALE_REGION_HASH = 2793244083L
private val fillWeightScaleRegionBind by lazy {
    ObjectCalls.getMethodBind("AStarGrid2D", "fill_weight_scale_region", FILL_WEIGHT_SCALE_REGION_HASH)
}

private const val GET_POINT_DATA_IN_REGION_HASH = 3893818462L
private val getPointDataInRegionBind by lazy {
    ObjectCalls.getMethodBind("AStarGrid2D", "get_point_data_in_region", GET_POINT_DATA_IN_REGION_HASH)
}
