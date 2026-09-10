package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for TileData (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileData waits on: ptrcallWithIntAndVariantArg, ptrcallWithStringAndVariantArg,
//   ptrcallWithTwoIntAndPackedVector2ListArg, ptrcallWithTwoIntArgsRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the points of the polygon at index `polygon_index` for TileSet physics layer with index
 * `layer_id`.
 *
 * Generated from Godot docs: TileData.set_collision_polygon_points
 */
fun TileData.setCollisionPolygonPoints(layerId: Int, polygonIndex: Int, polygon: List<Vector2>) {
    ObjectCalls.ptrcallWithTwoIntAndPackedVector2ListArg(setCollisionPolygonPointsBind, handle, layerId, polygonIndex, polygon)
}

/**
 * Returns the points of the polygon at index `polygon_index` for TileSet physics layer with index
 * `layer_id`.
 *
 * Generated from Godot docs: TileData.get_collision_polygon_points
 */
fun TileData.getCollisionPolygonPoints(layerId: Int, polygonIndex: Int): List<Vector2> {
    return ObjectCalls.ptrcallWithTwoIntArgsRetPackedVector2List(getCollisionPolygonPointsBind, handle, layerId, polygonIndex)
}

/**
 * Sets the tile's custom data value for the TileSet custom data layer with name `layer_name`.
 *
 * Generated from Godot docs: TileData.set_custom_data
 */
fun TileData.setCustomData(layerName: String, value: Any?) {
    ObjectCalls.ptrcallWithStringAndVariantArg(setCustomDataBind, handle, layerName, value)
}

/**
 * Sets the tile's custom data value for the TileSet custom data layer with index `layer_id`.
 *
 * Generated from Godot docs: TileData.set_custom_data_by_layer_id
 */
fun TileData.setCustomDataByLayerId(layerId: Int, value: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setCustomDataByLayerIdBind, handle, layerId, value)
}

private const val SET_COLLISION_POLYGON_POINTS_HASH = 3230546541L
private val setCollisionPolygonPointsBind by lazy {
    ObjectCalls.getMethodBind("TileData", "set_collision_polygon_points", SET_COLLISION_POLYGON_POINTS_HASH)
}

private const val GET_COLLISION_POLYGON_POINTS_HASH = 103942801L
private val getCollisionPolygonPointsBind by lazy {
    ObjectCalls.getMethodBind("TileData", "get_collision_polygon_points", GET_COLLISION_POLYGON_POINTS_HASH)
}

private const val SET_CUSTOM_DATA_HASH = 402577236L
private val setCustomDataBind by lazy {
    ObjectCalls.getMethodBind("TileData", "set_custom_data", SET_CUSTOM_DATA_HASH)
}

private const val SET_CUSTOM_DATA_BY_LAYER_ID_HASH = 2152698145L
private val setCustomDataByLayerIdBind by lazy {
    ObjectCalls.getMethodBind("TileData", "set_custom_data_by_layer_id", SET_CUSTOM_DATA_BY_LAYER_ID_HASH)
}
