package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileMapLayer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileMapLayer waits on: ptrcallNoArgsRetVector2iList, ptrcallWithByteArrayArg,
//   ptrcallWithIntVector2iAndIntArgsRetVector2iList, ptrcallWithVector2iArgRetVector2iList,
//   ptrcallWithVector2iListArgRetObject, ptrcallWithVector2iListTwoIntAndBoolArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a `Vector2i` array with the positions of all cells containing a tile. A cell is
 * considered empty if its source identifier equals `-1`, its atlas coordinate identifier is
 * `Vector2(-1, -1)` and its alternative identifier is `-1`.
 *
 * Generated from Godot docs: TileMapLayer.get_used_cells
 */
fun TileMapLayer.getUsedCells(): List<Vector2i> {
    return ObjectCalls.ptrcallNoArgsRetVector2iList(getUsedCellsBind, handle)
}

/**
 * Returns a `Vector2i` array with the positions of all cells containing a tile. Tiles may be
 * filtered according to their source (`source_id`), their atlas coordinates (`atlas_coords`), or
 * alternative id (`alternative_tile`). If a parameter has its value set to the default one, this
 * parameter is not used to filter a cell. Thus, if all parameters have their respective default
 * values, this method returns the same result as `get_used_cells`. A cell is considered empty if
 * its source identifier equals `-1`, its atlas coordinate identifier is `Vector2(-1, -1)` and its
 * alternative identifier is `-1`.
 *
 * Generated from Godot docs: TileMapLayer.get_used_cells_by_id
 */
fun TileMapLayer.getUsedCellsById(sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1): List<Vector2i> {
    return ObjectCalls.ptrcallWithIntVector2iAndIntArgsRetVector2iList(getUsedCellsByIdBind, handle, sourceId, atlasCoords, alternativeTile)
}

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
 * Returns the list of all neighboring cells to the one at `coords`. Any neighboring cell is one
 * that is touching edges, so for a square cell 4 cells would be returned, for a hexagon 6 cells
 * are returned.
 *
 * Generated from Godot docs: TileMapLayer.get_surrounding_cells
 */
fun TileMapLayer.getSurroundingCells(coords: Vector2i): List<Vector2i> {
    return ObjectCalls.ptrcallWithVector2iArgRetVector2iList(getSurroundingCellsBind, handle, coords)
}

/**
 * The raw tile map data as a byte array.
 *
 * Generated from Godot docs: TileMapLayer.set_tile_map_data_from_array
 */
fun TileMapLayer.setTileMapDataFromArray(tileMapLayerData: ByteArray) {
    ObjectCalls.ptrcallWithByteArrayArg(setTileMapDataFromArrayBind, handle, tileMapLayerData)
}

private const val GET_USED_CELLS_HASH = 3995934104L
private val getUsedCellsBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells", GET_USED_CELLS_HASH)
}

private const val GET_USED_CELLS_BY_ID_HASH = 4175304538L
private val getUsedCellsByIdBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells_by_id", GET_USED_CELLS_BY_ID_HASH)
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

private const val GET_SURROUNDING_CELLS_HASH = 2673526557L
private val getSurroundingCellsBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "get_surrounding_cells", GET_SURROUNDING_CELLS_HASH)
}

private const val SET_TILE_MAP_DATA_FROM_ARRAY_HASH = 2971499966L
private val setTileMapDataFromArrayBind by lazy {
    ObjectCalls.getMethodBind("TileMapLayer", "set_tile_map_data_from_array", SET_TILE_MAP_DATA_FROM_ARRAY_HASH)
}
