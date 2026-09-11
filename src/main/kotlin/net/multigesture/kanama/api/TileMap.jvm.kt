package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileMap (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileMap waits on: ptrcallWithIntAndVector2iListArgsRetObject,
//   ptrcallWithIntVector2iListTwoIntAndBoolArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a new `TileMapPattern` from the given layer and set of cells. If `layer` is negative,
 * the layers are accessed from the last one.
 *
 * Generated from Godot docs: TileMap.get_pattern
 */
fun TileMap.getPattern(layer: Int, coordsArray: List<Vector2i>): TileMapPattern? {
    return TileMapPattern.wrap(ObjectCalls.ptrcallWithIntAndVector2iListArgsRetObject(getPatternBind, handle, layer, coordsArray))
}

/**
 * Update all the cells in the `cells` coordinates array so that they use the given `terrain` for
 * the given `terrain_set`. If an updated cell has the same terrain as one of its neighboring
 * cells, this function tries to join the two. This function might update neighboring tiles if
 * needed to create correct terrain transitions. If `ignore_empty_terrains` is `true`, empty
 * terrains will be ignored when trying to find the best fitting tile for the given terrain
 * constraints. If `layer` is negative, the layers are accessed from the last one. Note: To work
 * correctly, this method requires the TileMap's TileSet to have terrains set up with all required
 * terrain combinations. Otherwise, it may produce unexpected results.
 *
 * Generated from Godot docs: TileMap.set_cells_terrain_connect
 */
fun TileMap.setCellsTerrainConnect(layer: Int, cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
    ObjectCalls.ptrcallWithIntVector2iListTwoIntAndBoolArgs(setCellsTerrainConnectBind, handle, layer, cells, terrainSet, terrain, ignoreEmptyTerrains)
}

/**
 * Update all the cells in the `path` coordinates array so that they use the given `terrain` for
 * the given `terrain_set`. The function will also connect two successive cell in the path with the
 * same terrain. This function might update neighboring tiles if needed to create correct terrain
 * transitions. If `ignore_empty_terrains` is `true`, empty terrains will be ignored when trying to
 * find the best fitting tile for the given terrain constraints. If `layer` is negative, the layers
 * are accessed from the last one. Note: To work correctly, this method requires the TileMap's
 * TileSet to have terrains set up with all required terrain combinations. Otherwise, it may
 * produce unexpected results.
 *
 * Generated from Godot docs: TileMap.set_cells_terrain_path
 */
fun TileMap.setCellsTerrainPath(layer: Int, path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
    ObjectCalls.ptrcallWithIntVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, layer, path, terrainSet, terrain, ignoreEmptyTerrains)
}

private const val GET_PATTERN_HASH = 2833570986L
private val getPatternBind by lazy {
    ObjectCalls.getMethodBind("TileMap", "get_pattern", GET_PATTERN_HASH)
}

private const val SET_CELLS_TERRAIN_CONNECT_HASH = 3578627656L
private val setCellsTerrainConnectBind by lazy {
    ObjectCalls.getMethodBind("TileMap", "set_cells_terrain_connect", SET_CELLS_TERRAIN_CONNECT_HASH)
}

private const val SET_CELLS_TERRAIN_PATH_HASH = 3578627656L
private val setCellsTerrainPathBind by lazy {
    ObjectCalls.getMethodBind("TileMap", "set_cells_terrain_path", SET_CELLS_TERRAIN_PATH_HASH)
}
