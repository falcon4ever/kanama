package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileMapLayer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileMapLayer waits on: ptrcallWithByteArrayArg,
//   ptrcallWithVector2iListArgRetObject, ptrcallWithVector2iListTwoIntAndBoolArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates and returns a new `TileMapPattern` from the given array of cells. See also
 * `set_pattern`.
 *
 * Generated from Godot docs: TileMapLayer.get_pattern
 */
fun TileMapLayer.getPattern(coordsArray: List<Vector2i>): TileMapPattern? {
    return TileMapPattern.wrap(ObjectCalls.ptrcallWithVector2iListArgRetObject(getPatternBind, handle, coordsArray))
}

/**
 * Update all the cells in the `cells` coordinates array so that they use the given `terrain` for
 * the given `terrain_set`. If an updated cell has the same terrain as one of its neighboring
 * cells, this function tries to join the two. This function might update neighboring tiles if
 * needed to create correct terrain transitions. If `ignore_empty_terrains` is `true`, empty
 * terrains will be ignored when trying to find the best fitting tile for the given terrain
 * constraints. Note: To work correctly, this method requires the `TileMapLayer`'s TileSet to have
 * terrains set up with all required terrain combinations. Otherwise, it may produce unexpected
 * results.
 *
 * Generated from Godot docs: TileMapLayer.set_cells_terrain_connect
 */
fun TileMapLayer.setCellsTerrainConnect(cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
    ObjectCalls.ptrcallWithVector2iListTwoIntAndBoolArgs(setCellsTerrainConnectBind, handle, cells, terrainSet, terrain, ignoreEmptyTerrains)
}

/**
 * Update all the cells in the `path` coordinates array so that they use the given `terrain` for
 * the given `terrain_set`. The function will also connect two successive cell in the path with the
 * same terrain. This function might update neighboring tiles if needed to create correct terrain
 * transitions. If `ignore_empty_terrains` is `true`, empty terrains will be ignored when trying to
 * find the best fitting tile for the given terrain constraints. Note: To work correctly, this
 * method requires the `TileMapLayer`'s TileSet to have terrains set up with all required terrain
 * combinations. Otherwise, it may produce unexpected results.
 *
 * Generated from Godot docs: TileMapLayer.set_cells_terrain_path
 */
fun TileMapLayer.setCellsTerrainPath(path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
    ObjectCalls.ptrcallWithVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, path, terrainSet, terrain, ignoreEmptyTerrains)
}

/**
 * The raw tile map data as a byte array.
 *
 * Generated from Godot docs: TileMapLayer.set_tile_map_data_from_array
 */
fun TileMapLayer.setTileMapDataFromArray(tileMapLayerData: ByteArray) {
    ObjectCalls.ptrcallWithByteArrayArg(setTileMapDataFromArrayBind, handle, tileMapLayerData)
}

private const val GET_PATTERN_HASH = 3820813253L
private val getPatternBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "get_pattern", GET_PATTERN_HASH)
}

private const val SET_CELLS_TERRAIN_CONNECT_HASH = 748968311L
private val setCellsTerrainConnectBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "set_cells_terrain_connect", SET_CELLS_TERRAIN_CONNECT_HASH)
}

private const val SET_CELLS_TERRAIN_PATH_HASH = 748968311L
private val setCellsTerrainPathBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "set_cells_terrain_path", SET_CELLS_TERRAIN_PATH_HASH)
}

private const val SET_TILE_MAP_DATA_FROM_ARRAY_HASH = 2971499966L
private val setTileMapDataFromArrayBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "set_tile_map_data_from_array", SET_TILE_MAP_DATA_FROM_ARRAY_HASH)
}
