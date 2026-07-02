package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Node for 2D tile-based maps.
 *
 * Generated from Godot docs: TileMap
 */
class TileMap(handle: MemorySegment) : Node2D(handle) {
    var tileSet: TileSet?
        @JvmName("tileSetProperty")
        get() = getTileset()
        @JvmName("setTileSetProperty")
        set(value) = setTileset(value)

    var renderingQuadrantSize: Int
        @JvmName("renderingQuadrantSizeProperty")
        get() = getRenderingQuadrantSize()
        @JvmName("setRenderingQuadrantSizeProperty")
        set(value) = setRenderingQuadrantSize(value)

    var collisionAnimatable: Boolean
        @JvmName("collisionAnimatableProperty")
        get() = isCollisionAnimatable()
        @JvmName("setCollisionAnimatableProperty")
        set(value) = setCollisionAnimatable(value)

    var collisionVisibilityMode: Long
        @JvmName("collisionVisibilityModeProperty")
        get() = getCollisionVisibilityMode()
        @JvmName("setCollisionVisibilityModeProperty")
        set(value) = setCollisionVisibilityMode(value)

    var navigationVisibilityMode: Long
        @JvmName("navigationVisibilityModeProperty")
        get() = getNavigationVisibilityMode()
        @JvmName("setNavigationVisibilityModeProperty")
        set(value) = setNavigationVisibilityMode(value)

    fun setNavigationMap(layer: Int, map: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(setNavigationMapBind, handle, layer, map)
    }

    fun getNavigationMap(layer: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getNavigationMapBind, handle, layer)
    }

    fun forceUpdate(layer: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(forceUpdateBind, handle, layer)
    }

    fun setTileset(tileset: TileSet?) {
        ObjectCalls.ptrcallWithObjectArgs(setTilesetBind, handle, listOf(tileset?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTileset(): TileSet? {
        return TileSet.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTilesetBind, handle))
    }

    fun setRenderingQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderingQuadrantSizeBind, handle, size)
    }

    fun getRenderingQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderingQuadrantSizeBind, handle)
    }

    fun getLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayersCountBind, handle)
    }

    fun addLayer(toPosition: Int) {
        ObjectCalls.ptrcallWithIntArg(addLayerBind, handle, toPosition)
    }

    fun moveLayer(layer: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveLayerBind, handle, layer, toPosition)
    }

    fun removeLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(removeLayerBind, handle, layer)
    }

    fun setLayerName(layer: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setLayerNameBind, handle, layer, name)
    }

    fun getLayerName(layer: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getLayerNameBind, handle, layer)
    }

    fun setLayerEnabled(layer: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerEnabledBind, handle, layer, enabled)
    }

    fun isLayerEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerEnabledBind, handle, layer)
    }

    fun setLayerModulate(layer: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setLayerModulateBind, handle, layer, modulate)
    }

    fun getLayerModulate(layer: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getLayerModulateBind, handle, layer)
    }

    fun setLayerYSortEnabled(layer: Int, ySortEnabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerYSortEnabledBind, handle, layer, ySortEnabled)
    }

    fun isLayerYSortEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerYSortEnabledBind, handle, layer)
    }

    fun setLayerYSortOrigin(layer: Int, ySortOrigin: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLayerYSortOriginBind, handle, layer, ySortOrigin)
    }

    fun getLayerYSortOrigin(layer: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLayerYSortOriginBind, handle, layer)
    }

    fun setLayerZIndex(layer: Int, zIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLayerZIndexBind, handle, layer, zIndex)
    }

    fun getLayerZIndex(layer: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLayerZIndexBind, handle, layer)
    }

    fun setLayerNavigationEnabled(layer: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerNavigationEnabledBind, handle, layer, enabled)
    }

    fun isLayerNavigationEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerNavigationEnabledBind, handle, layer)
    }

    fun setLayerNavigationMap(layer: Int, map: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(setLayerNavigationMapBind, handle, layer, map)
    }

    fun getLayerNavigationMap(layer: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getLayerNavigationMapBind, handle, layer)
    }

    fun setCollisionAnimatable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionAnimatableBind, handle, enabled)
    }

    fun isCollisionAnimatable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionAnimatableBind, handle)
    }

    fun setCollisionVisibilityMode(collisionVisibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionVisibilityModeBind, handle, collisionVisibilityMode)
    }

    fun getCollisionVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionVisibilityModeBind, handle)
    }

    fun setNavigationVisibilityMode(navigationVisibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setNavigationVisibilityModeBind, handle, navigationVisibilityMode)
    }

    fun getNavigationVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNavigationVisibilityModeBind, handle)
    }

    fun setCell(layer: Int, coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = 0) {
        ObjectCalls.ptrcallWithIntVector2iIntVector2iIntArgs(setCellBind, handle, layer, coords, sourceId, atlasCoords, alternativeTile)
    }

    fun eraseCell(layer: Int, coords: Vector2i) {
        ObjectCalls.ptrcallWithIntAndVector2iArg(eraseCellBind, handle, layer, coords)
    }

    fun getCellSourceId(layer: Int, coords: Vector2i, useProxies: Boolean = false): Int {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetInt(getCellSourceIdBind, handle, layer, coords, useProxies)
    }

    fun getCellAtlasCoords(layer: Int, coords: Vector2i, useProxies: Boolean = false): Vector2i {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetVector2i(getCellAtlasCoordsBind, handle, layer, coords, useProxies)
    }

    fun getCellAlternativeTile(layer: Int, coords: Vector2i, useProxies: Boolean = false): Int {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetInt(getCellAlternativeTileBind, handle, layer, coords, useProxies)
    }

    fun getCellTileData(layer: Int, coords: Vector2i, useProxies: Boolean = false): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetObject(getCellTileDataBind, handle, layer, coords, useProxies))
    }

    fun isCellFlippedH(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellFlippedHBind, handle, layer, coords, useProxies)
    }

    fun isCellFlippedV(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellFlippedVBind, handle, layer, coords, useProxies)
    }

    fun isCellTransposed(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellTransposedBind, handle, layer, coords, useProxies)
    }

    fun getCoordsForBodyRid(body: RID): Vector2i {
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(getCoordsForBodyRidBind, handle, body)
    }

    fun getLayerForBodyRid(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(getLayerForBodyRidBind, handle, body)
    }

    fun getPattern(layer: Int, coordsArray: List<Vector2i>): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithIntAndVector2iListArgsRetObject(getPatternBind, handle, layer, coordsArray))
    }

    fun mapPattern(positionInTilemap: Vector2i, coordsInPattern: Vector2i, pattern: TileMapPattern?): Vector2i {
        return ObjectCalls.ptrcallWithTwoVector2iAndObjectArgRetVector2i(mapPatternBind, handle, positionInTilemap, coordsInPattern, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setPattern(layer: Int, position: Vector2i, pattern: TileMapPattern?) {
        ObjectCalls.ptrcallWithIntVector2iAndObjectArg(setPatternBind, handle, layer, position, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setCellsTerrainConnect(layer: Int, cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithIntVector2iListTwoIntAndBoolArgs(setCellsTerrainConnectBind, handle, layer, cells, terrainSet, terrain, ignoreEmptyTerrains)
    }

    fun setCellsTerrainPath(layer: Int, path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithIntVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, layer, path, terrainSet, terrain, ignoreEmptyTerrains)
    }

    fun fixInvalidTiles() {
        ObjectCalls.ptrcallNoArgs(fixInvalidTilesBind, handle)
    }

    fun clearLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(clearLayerBind, handle, layer)
    }

    /**
     * Clears all cells.
     *
     * Generated from Godot docs: TileMap.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun updateInternals() {
        ObjectCalls.ptrcallNoArgs(updateInternalsBind, handle)
    }

    fun notifyRuntimeTileDataUpdate(layer: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(notifyRuntimeTileDataUpdateBind, handle, layer)
    }

    fun getSurroundingCells(coords: Vector2i): List<Vector2i> {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2iList(getSurroundingCellsBind, handle, coords)
    }

    fun getUsedCells(layer: Int): List<Vector2i> {
        return ObjectCalls.ptrcallWithIntArgRetVector2iList(getUsedCellsBind, handle, layer)
    }

    fun getUsedCellsById(layer: Int, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1): List<Vector2i> {
        return ObjectCalls.ptrcallWithTwoIntVector2iAndIntArgsRetVector2iList(getUsedCellsByIdBind, handle, layer, sourceId, atlasCoords, alternativeTile)
    }

    fun getUsedRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    fun mapToLocal(mapPosition: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(mapToLocalBind, handle, mapPosition)
    }

    fun localToMap(localPosition: Vector2): Vector2i {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2i(localToMapBind, handle, localPosition)
    }

    fun getNeighborCell(coords: Vector2i, neighbor: Long): Vector2i {
        return ObjectCalls.ptrcallWithVector2iAndLongArgRetVector2i(getNeighborCellBind, handle, coords, neighbor)
    }

    object Signals {
        const val changed: String = "changed"
    }

    companion object {
        const val VISIBILITY_MODE_DEFAULT: Long = 0L
        const val VISIBILITY_MODE_FORCE_HIDE: Long = 2L
        const val VISIBILITY_MODE_FORCE_SHOW: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileMap? =
            if (handle.address() == 0L) null else TileMap(handle)

        private const val SET_NAVIGATION_MAP_HASH = 4040184819L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 495598643L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val FORCE_UPDATE_HASH = 1025054187L
        private val forceUpdateBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "force_update", FORCE_UPDATE_HASH)
        }

        private const val SET_TILESET_HASH = 774531446L
        private val setTilesetBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_tileset", SET_TILESET_HASH)
        }

        private const val GET_TILESET_HASH = 2678226422L
        private val getTilesetBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_tileset", GET_TILESET_HASH)
        }

        private const val SET_RENDERING_QUADRANT_SIZE_HASH = 1286410249L
        private val setRenderingQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_rendering_quadrant_size", SET_RENDERING_QUADRANT_SIZE_HASH)
        }

        private const val GET_RENDERING_QUADRANT_SIZE_HASH = 3905245786L
        private val getRenderingQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_rendering_quadrant_size", GET_RENDERING_QUADRANT_SIZE_HASH)
        }

        private const val GET_LAYERS_COUNT_HASH = 3905245786L
        private val getLayersCountBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layers_count", GET_LAYERS_COUNT_HASH)
        }

        private const val ADD_LAYER_HASH = 1286410249L
        private val addLayerBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "add_layer", ADD_LAYER_HASH)
        }

        private const val MOVE_LAYER_HASH = 3937882851L
        private val moveLayerBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "move_layer", MOVE_LAYER_HASH)
        }

        private const val REMOVE_LAYER_HASH = 1286410249L
        private val removeLayerBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "remove_layer", REMOVE_LAYER_HASH)
        }

        private const val SET_LAYER_NAME_HASH = 501894301L
        private val setLayerNameBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_name", SET_LAYER_NAME_HASH)
        }

        private const val GET_LAYER_NAME_HASH = 844755477L
        private val getLayerNameBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_name", GET_LAYER_NAME_HASH)
        }

        private const val SET_LAYER_ENABLED_HASH = 300928843L
        private val setLayerEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_enabled", SET_LAYER_ENABLED_HASH)
        }

        private const val IS_LAYER_ENABLED_HASH = 1116898809L
        private val isLayerEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_layer_enabled", IS_LAYER_ENABLED_HASH)
        }

        private const val SET_LAYER_MODULATE_HASH = 2878471219L
        private val setLayerModulateBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_modulate", SET_LAYER_MODULATE_HASH)
        }

        private const val GET_LAYER_MODULATE_HASH = 3457211756L
        private val getLayerModulateBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_modulate", GET_LAYER_MODULATE_HASH)
        }

        private const val SET_LAYER_Y_SORT_ENABLED_HASH = 300928843L
        private val setLayerYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_y_sort_enabled", SET_LAYER_Y_SORT_ENABLED_HASH)
        }

        private const val IS_LAYER_Y_SORT_ENABLED_HASH = 1116898809L
        private val isLayerYSortEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_layer_y_sort_enabled", IS_LAYER_Y_SORT_ENABLED_HASH)
        }

        private const val SET_LAYER_Y_SORT_ORIGIN_HASH = 3937882851L
        private val setLayerYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_y_sort_origin", SET_LAYER_Y_SORT_ORIGIN_HASH)
        }

        private const val GET_LAYER_Y_SORT_ORIGIN_HASH = 923996154L
        private val getLayerYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_y_sort_origin", GET_LAYER_Y_SORT_ORIGIN_HASH)
        }

        private const val SET_LAYER_Z_INDEX_HASH = 3937882851L
        private val setLayerZIndexBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_z_index", SET_LAYER_Z_INDEX_HASH)
        }

        private const val GET_LAYER_Z_INDEX_HASH = 923996154L
        private val getLayerZIndexBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_z_index", GET_LAYER_Z_INDEX_HASH)
        }

        private const val SET_LAYER_NAVIGATION_ENABLED_HASH = 300928843L
        private val setLayerNavigationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_navigation_enabled", SET_LAYER_NAVIGATION_ENABLED_HASH)
        }

        private const val IS_LAYER_NAVIGATION_ENABLED_HASH = 1116898809L
        private val isLayerNavigationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_layer_navigation_enabled", IS_LAYER_NAVIGATION_ENABLED_HASH)
        }

        private const val SET_LAYER_NAVIGATION_MAP_HASH = 4040184819L
        private val setLayerNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_layer_navigation_map", SET_LAYER_NAVIGATION_MAP_HASH)
        }

        private const val GET_LAYER_NAVIGATION_MAP_HASH = 495598643L
        private val getLayerNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_navigation_map", GET_LAYER_NAVIGATION_MAP_HASH)
        }

        private const val SET_COLLISION_ANIMATABLE_HASH = 2586408642L
        private val setCollisionAnimatableBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_collision_animatable", SET_COLLISION_ANIMATABLE_HASH)
        }

        private const val IS_COLLISION_ANIMATABLE_HASH = 36873697L
        private val isCollisionAnimatableBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_collision_animatable", IS_COLLISION_ANIMATABLE_HASH)
        }

        private const val SET_COLLISION_VISIBILITY_MODE_HASH = 3193440636L
        private val setCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_collision_visibility_mode", SET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val GET_COLLISION_VISIBILITY_MODE_HASH = 1697018252L
        private val getCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_collision_visibility_mode", GET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val SET_NAVIGATION_VISIBILITY_MODE_HASH = 3193440636L
        private val setNavigationVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_navigation_visibility_mode", SET_NAVIGATION_VISIBILITY_MODE_HASH)
        }

        private const val GET_NAVIGATION_VISIBILITY_MODE_HASH = 1697018252L
        private val getNavigationVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_navigation_visibility_mode", GET_NAVIGATION_VISIBILITY_MODE_HASH)
        }

        private const val SET_CELL_HASH = 966713560L
        private val setCellBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_cell", SET_CELL_HASH)
        }

        private const val ERASE_CELL_HASH = 2311374912L
        private val eraseCellBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "erase_cell", ERASE_CELL_HASH)
        }

        private const val GET_CELL_SOURCE_ID_HASH = 551761942L
        private val getCellSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_cell_source_id", GET_CELL_SOURCE_ID_HASH)
        }

        private const val GET_CELL_ATLAS_COORDS_HASH = 1869815066L
        private val getCellAtlasCoordsBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_cell_atlas_coords", GET_CELL_ATLAS_COORDS_HASH)
        }

        private const val GET_CELL_ALTERNATIVE_TILE_HASH = 551761942L
        private val getCellAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_cell_alternative_tile", GET_CELL_ALTERNATIVE_TILE_HASH)
        }

        private const val GET_CELL_TILE_DATA_HASH = 2849631287L
        private val getCellTileDataBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_cell_tile_data", GET_CELL_TILE_DATA_HASH)
        }

        private const val IS_CELL_FLIPPED_H_HASH = 2908343862L
        private val isCellFlippedHBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_cell_flipped_h", IS_CELL_FLIPPED_H_HASH)
        }

        private const val IS_CELL_FLIPPED_V_HASH = 2908343862L
        private val isCellFlippedVBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_cell_flipped_v", IS_CELL_FLIPPED_V_HASH)
        }

        private const val IS_CELL_TRANSPOSED_HASH = 2908343862L
        private val isCellTransposedBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "is_cell_transposed", IS_CELL_TRANSPOSED_HASH)
        }

        private const val GET_COORDS_FOR_BODY_RID_HASH = 291584212L
        private val getCoordsForBodyRidBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_coords_for_body_rid", GET_COORDS_FOR_BODY_RID_HASH)
        }

        private const val GET_LAYER_FOR_BODY_RID_HASH = 3917799429L
        private val getLayerForBodyRidBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_layer_for_body_rid", GET_LAYER_FOR_BODY_RID_HASH)
        }

        private const val GET_PATTERN_HASH = 2833570986L
        private val getPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_pattern", GET_PATTERN_HASH)
        }

        private const val MAP_PATTERN_HASH = 1864516957L
        private val mapPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "map_pattern", MAP_PATTERN_HASH)
        }

        private const val SET_PATTERN_HASH = 1195853946L
        private val setPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_pattern", SET_PATTERN_HASH)
        }

        private const val SET_CELLS_TERRAIN_CONNECT_HASH = 3578627656L
        private val setCellsTerrainConnectBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_cells_terrain_connect", SET_CELLS_TERRAIN_CONNECT_HASH)
        }

        private const val SET_CELLS_TERRAIN_PATH_HASH = 3578627656L
        private val setCellsTerrainPathBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "set_cells_terrain_path", SET_CELLS_TERRAIN_PATH_HASH)
        }

        private const val FIX_INVALID_TILES_HASH = 3218959716L
        private val fixInvalidTilesBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "fix_invalid_tiles", FIX_INVALID_TILES_HASH)
        }

        private const val CLEAR_LAYER_HASH = 1286410249L
        private val clearLayerBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "clear_layer", CLEAR_LAYER_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "clear", CLEAR_HASH)
        }

        private const val UPDATE_INTERNALS_HASH = 3218959716L
        private val updateInternalsBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "update_internals", UPDATE_INTERNALS_HASH)
        }

        private const val NOTIFY_RUNTIME_TILE_DATA_UPDATE_HASH = 1025054187L
        private val notifyRuntimeTileDataUpdateBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "notify_runtime_tile_data_update", NOTIFY_RUNTIME_TILE_DATA_UPDATE_HASH)
        }

        private const val GET_SURROUNDING_CELLS_HASH = 2673526557L
        private val getSurroundingCellsBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_surrounding_cells", GET_SURROUNDING_CELLS_HASH)
        }

        private const val GET_USED_CELLS_HASH = 663333327L
        private val getUsedCellsBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_used_cells", GET_USED_CELLS_HASH)
        }

        private const val GET_USED_CELLS_BY_ID_HASH = 2931012785L
        private val getUsedCellsByIdBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_used_cells_by_id", GET_USED_CELLS_BY_ID_HASH)
        }

        private const val GET_USED_RECT_HASH = 410525958L
        private val getUsedRectBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_used_rect", GET_USED_RECT_HASH)
        }

        private const val MAP_TO_LOCAL_HASH = 108438297L
        private val mapToLocalBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "map_to_local", MAP_TO_LOCAL_HASH)
        }

        private const val LOCAL_TO_MAP_HASH = 837806996L
        private val localToMapBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "local_to_map", LOCAL_TO_MAP_HASH)
        }

        private const val GET_NEIGHBOR_CELL_HASH = 986575103L
        private val getNeighborCellBind by lazy {
            ObjectCalls.getMethodBind("TileMap", "get_neighbor_cell", GET_NEIGHBOR_CELL_HASH)
        }
    }
}
