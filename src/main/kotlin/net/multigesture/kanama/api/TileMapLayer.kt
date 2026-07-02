package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Node for 2D tile-based maps.
 *
 * Generated from Godot docs: TileMapLayer
 */
class TileMapLayer(handle: MemorySegment) : Node2D(handle) {
    var tileMapData: ByteArray
        @JvmName("tileMapDataProperty")
        get() = getTileMapDataAsArray()
        @JvmName("setTileMapDataProperty")
        set(value) = setTileMapDataFromArray(value)

    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var tileSet: TileSet?
        @JvmName("tileSetProperty")
        get() = getTileSet()
        @JvmName("setTileSetProperty")
        set(value) = setTileSet(value)

    var occlusionEnabled: Boolean
        @JvmName("occlusionEnabledProperty")
        get() = isOcclusionEnabled()
        @JvmName("setOcclusionEnabledProperty")
        set(value) = setOcclusionEnabled(value)

    var ySortOrigin: Int
        @JvmName("ySortOriginProperty")
        get() = getYSortOrigin()
        @JvmName("setYSortOriginProperty")
        set(value) = setYSortOrigin(value)

    var xDrawOrderReversed: Boolean
        @JvmName("xDrawOrderReversedProperty")
        get() = isXDrawOrderReversed()
        @JvmName("setXDrawOrderReversedProperty")
        set(value) = setXDrawOrderReversed(value)

    var renderingQuadrantSize: Int
        @JvmName("renderingQuadrantSizeProperty")
        get() = getRenderingQuadrantSize()
        @JvmName("setRenderingQuadrantSizeProperty")
        set(value) = setRenderingQuadrantSize(value)

    var collisionEnabled: Boolean
        @JvmName("collisionEnabledProperty")
        get() = isCollisionEnabled()
        @JvmName("setCollisionEnabledProperty")
        set(value) = setCollisionEnabled(value)

    var useKinematicBodies: Boolean
        @JvmName("useKinematicBodiesProperty")
        get() = isUsingKinematicBodies()
        @JvmName("setUseKinematicBodiesProperty")
        set(value) = setUseKinematicBodies(value)

    var collisionVisibilityMode: Long
        @JvmName("collisionVisibilityModeProperty")
        get() = getCollisionVisibilityMode()
        @JvmName("setCollisionVisibilityModeProperty")
        set(value) = setCollisionVisibilityMode(value)

    var physicsQuadrantSize: Int
        @JvmName("physicsQuadrantSizeProperty")
        get() = getPhysicsQuadrantSize()
        @JvmName("setPhysicsQuadrantSizeProperty")
        set(value) = setPhysicsQuadrantSize(value)

    var navigationEnabled: Boolean
        @JvmName("navigationEnabledProperty")
        get() = isNavigationEnabled()
        @JvmName("setNavigationEnabledProperty")
        set(value) = setNavigationEnabled(value)

    var navigationVisibilityMode: Long
        @JvmName("navigationVisibilityModeProperty")
        get() = getNavigationVisibilityMode()
        @JvmName("setNavigationVisibilityModeProperty")
        set(value) = setNavigationVisibilityMode(value)

    fun setCell(coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = 0) {
        ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(setCellBind, handle, coords, sourceId, atlasCoords, alternativeTile)
    }

    fun eraseCell(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(eraseCellBind, handle, coords)
    }

    fun fixInvalidTiles() {
        ObjectCalls.ptrcallNoArgs(fixInvalidTilesBind, handle)
    }

    /**
     * Clears all cells.
     *
     * Generated from Godot docs: TileMapLayer.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getCellSourceId(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellSourceIdBind, handle, coords)
    }

    fun getCellAtlasCoords(coords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getCellAtlasCoordsBind, handle, coords)
    }

    fun getCellAlternativeTile(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellAlternativeTileBind, handle, coords)
    }

    fun getCellTileData(coords: Vector2i): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithVector2iArgRetObject(getCellTileDataBind, handle, coords))
    }

    fun isCellFlippedH(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellFlippedHBind, handle, coords)
    }

    fun isCellFlippedV(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellFlippedVBind, handle, coords)
    }

    fun isCellTransposed(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellTransposedBind, handle, coords)
    }

    fun getUsedCells(): List<Vector2i> {
        return ObjectCalls.ptrcallNoArgsRetVector2iList(getUsedCellsBind, handle)
    }

    fun getUsedCellsById(sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1): List<Vector2i> {
        return ObjectCalls.ptrcallWithIntVector2iAndIntArgsRetVector2iList(getUsedCellsByIdBind, handle, sourceId, atlasCoords, alternativeTile)
    }

    fun getUsedRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    fun getPattern(coordsArray: List<Vector2i>): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithVector2iListArgRetObject(getPatternBind, handle, coordsArray))
    }

    fun setPattern(position: Vector2i, pattern: TileMapPattern?) {
        ObjectCalls.ptrcallWithVector2iAndObjectArg(setPatternBind, handle, position, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setCellsTerrainConnect(cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithVector2iListTwoIntAndBoolArgs(setCellsTerrainConnectBind, handle, cells, terrainSet, terrain, ignoreEmptyTerrains)
    }

    fun setCellsTerrainPath(path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, path, terrainSet, terrain, ignoreEmptyTerrains)
    }

    fun hasBodyRid(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasBodyRidBind, handle, body)
    }

    fun getCoordsForBodyRid(body: RID): Vector2i {
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(getCoordsForBodyRidBind, handle, body)
    }

    fun updateInternals() {
        ObjectCalls.ptrcallNoArgs(updateInternalsBind, handle)
    }

    fun notifyRuntimeTileDataUpdate() {
        ObjectCalls.ptrcallNoArgs(notifyRuntimeTileDataUpdateBind, handle)
    }

    fun mapPattern(positionInTilemap: Vector2i, coordsInPattern: Vector2i, pattern: TileMapPattern?): Vector2i {
        return ObjectCalls.ptrcallWithTwoVector2iAndObjectArgRetVector2i(mapPatternBind, handle, positionInTilemap, coordsInPattern, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSurroundingCells(coords: Vector2i): List<Vector2i> {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2iList(getSurroundingCellsBind, handle, coords)
    }

    fun getNeighborCell(coords: Vector2i, neighbor: Long): Vector2i {
        return ObjectCalls.ptrcallWithVector2iAndLongArgRetVector2i(getNeighborCellBind, handle, coords, neighbor)
    }

    fun mapToLocal(mapPosition: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(mapToLocalBind, handle, mapPosition)
    }

    fun localToMap(localPosition: Vector2): Vector2i {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2i(localToMapBind, handle, localPosition)
    }

    fun setTileMapDataFromArray(tileMapLayerData: ByteArray) {
        ObjectCalls.ptrcallWithByteArrayArg(setTileMapDataFromArrayBind, handle, tileMapLayerData)
    }

    fun getTileMapDataAsArray(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getTileMapDataAsArrayBind, handle)
    }

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setTileSet(tileSet: TileSet?) {
        ObjectCalls.ptrcallWithObjectArgs(setTileSetBind, handle, listOf(tileSet?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTileSet(): TileSet? {
        return TileSet.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTileSetBind, handle))
    }

    fun setYSortOrigin(ySortOrigin: Int) {
        ObjectCalls.ptrcallWithIntArg(setYSortOriginBind, handle, ySortOrigin)
    }

    fun getYSortOrigin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getYSortOriginBind, handle)
    }

    fun setXDrawOrderReversed(xDrawOrderReversed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setXDrawOrderReversedBind, handle, xDrawOrderReversed)
    }

    fun isXDrawOrderReversed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isXDrawOrderReversedBind, handle)
    }

    fun setRenderingQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderingQuadrantSizeBind, handle, size)
    }

    fun getRenderingQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderingQuadrantSizeBind, handle)
    }

    fun setCollisionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionEnabledBind, handle, enabled)
    }

    fun isCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionEnabledBind, handle)
    }

    fun setUseKinematicBodies(useKinematicBodies: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseKinematicBodiesBind, handle, useKinematicBodies)
    }

    fun isUsingKinematicBodies(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingKinematicBodiesBind, handle)
    }

    fun setCollisionVisibilityMode(visibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionVisibilityModeBind, handle, visibilityMode)
    }

    fun getCollisionVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionVisibilityModeBind, handle)
    }

    fun setPhysicsQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsQuadrantSizeBind, handle, size)
    }

    fun getPhysicsQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsQuadrantSizeBind, handle)
    }

    fun setOcclusionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOcclusionEnabledBind, handle, enabled)
    }

    fun isOcclusionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOcclusionEnabledBind, handle)
    }

    fun setNavigationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNavigationEnabledBind, handle, enabled)
    }

    fun isNavigationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNavigationEnabledBind, handle)
    }

    fun setNavigationMap(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, map)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    fun setNavigationVisibilityMode(showNavigation: Long) {
        ObjectCalls.ptrcallWithLongArg(setNavigationVisibilityModeBind, handle, showNavigation)
    }

    fun getNavigationVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNavigationVisibilityModeBind, handle)
    }

    object Signals {
        const val changed: String = "changed"
    }

    companion object {
        const val DEBUG_VISIBILITY_MODE_DEFAULT: Long = 0L
        const val DEBUG_VISIBILITY_MODE_FORCE_HIDE: Long = 2L
        const val DEBUG_VISIBILITY_MODE_FORCE_SHOW: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileMapLayer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileMapLayer? =
            if (handle.address() == 0L) null else TileMapLayer(handle)

        private const val SET_CELL_HASH = 2428518503L
        private val setCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_cell", SET_CELL_HASH)
        }

        private const val ERASE_CELL_HASH = 1130785943L
        private val eraseCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "erase_cell", ERASE_CELL_HASH)
        }

        private const val FIX_INVALID_TILES_HASH = 3218959716L
        private val fixInvalidTilesBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "fix_invalid_tiles", FIX_INVALID_TILES_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "clear", CLEAR_HASH)
        }

        private const val GET_CELL_SOURCE_ID_HASH = 2485466453L
        private val getCellSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_cell_source_id", GET_CELL_SOURCE_ID_HASH)
        }

        private const val GET_CELL_ATLAS_COORDS_HASH = 3050897911L
        private val getCellAtlasCoordsBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_cell_atlas_coords", GET_CELL_ATLAS_COORDS_HASH)
        }

        private const val GET_CELL_ALTERNATIVE_TILE_HASH = 2485466453L
        private val getCellAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_cell_alternative_tile", GET_CELL_ALTERNATIVE_TILE_HASH)
        }

        private const val GET_CELL_TILE_DATA_HASH = 205084707L
        private val getCellTileDataBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_cell_tile_data", GET_CELL_TILE_DATA_HASH)
        }

        private const val IS_CELL_FLIPPED_H_HASH = 3900751641L
        private val isCellFlippedHBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_cell_flipped_h", IS_CELL_FLIPPED_H_HASH)
        }

        private const val IS_CELL_FLIPPED_V_HASH = 3900751641L
        private val isCellFlippedVBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_cell_flipped_v", IS_CELL_FLIPPED_V_HASH)
        }

        private const val IS_CELL_TRANSPOSED_HASH = 3900751641L
        private val isCellTransposedBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_cell_transposed", IS_CELL_TRANSPOSED_HASH)
        }

        private const val GET_USED_CELLS_HASH = 3995934104L
        private val getUsedCellsBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells", GET_USED_CELLS_HASH)
        }

        private const val GET_USED_CELLS_BY_ID_HASH = 4175304538L
        private val getUsedCellsByIdBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells_by_id", GET_USED_CELLS_BY_ID_HASH)
        }

        private const val GET_USED_RECT_HASH = 410525958L
        private val getUsedRectBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_used_rect", GET_USED_RECT_HASH)
        }

        private const val GET_PATTERN_HASH = 3820813253L
        private val getPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_pattern", GET_PATTERN_HASH)
        }

        private const val SET_PATTERN_HASH = 1491151770L
        private val setPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_pattern", SET_PATTERN_HASH)
        }

        private const val SET_CELLS_TERRAIN_CONNECT_HASH = 748968311L
        private val setCellsTerrainConnectBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_cells_terrain_connect", SET_CELLS_TERRAIN_CONNECT_HASH)
        }

        private const val SET_CELLS_TERRAIN_PATH_HASH = 748968311L
        private val setCellsTerrainPathBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_cells_terrain_path", SET_CELLS_TERRAIN_PATH_HASH)
        }

        private const val HAS_BODY_RID_HASH = 4155700596L
        private val hasBodyRidBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "has_body_rid", HAS_BODY_RID_HASH)
        }

        private const val GET_COORDS_FOR_BODY_RID_HASH = 733700038L
        private val getCoordsForBodyRidBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_coords_for_body_rid", GET_COORDS_FOR_BODY_RID_HASH)
        }

        private const val UPDATE_INTERNALS_HASH = 3218959716L
        private val updateInternalsBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "update_internals", UPDATE_INTERNALS_HASH)
        }

        private const val NOTIFY_RUNTIME_TILE_DATA_UPDATE_HASH = 3218959716L
        private val notifyRuntimeTileDataUpdateBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "notify_runtime_tile_data_update", NOTIFY_RUNTIME_TILE_DATA_UPDATE_HASH)
        }

        private const val MAP_PATTERN_HASH = 1864516957L
        private val mapPatternBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "map_pattern", MAP_PATTERN_HASH)
        }

        private const val GET_SURROUNDING_CELLS_HASH = 2673526557L
        private val getSurroundingCellsBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_surrounding_cells", GET_SURROUNDING_CELLS_HASH)
        }

        private const val GET_NEIGHBOR_CELL_HASH = 986575103L
        private val getNeighborCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_neighbor_cell", GET_NEIGHBOR_CELL_HASH)
        }

        private const val MAP_TO_LOCAL_HASH = 108438297L
        private val mapToLocalBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "map_to_local", MAP_TO_LOCAL_HASH)
        }

        private const val LOCAL_TO_MAP_HASH = 837806996L
        private val localToMapBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "local_to_map", LOCAL_TO_MAP_HASH)
        }

        private const val SET_TILE_MAP_DATA_FROM_ARRAY_HASH = 2971499966L
        private val setTileMapDataFromArrayBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_tile_map_data_from_array", SET_TILE_MAP_DATA_FROM_ARRAY_HASH)
        }

        private const val GET_TILE_MAP_DATA_AS_ARRAY_HASH = 2362200018L
        private val getTileMapDataAsArrayBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_tile_map_data_as_array", GET_TILE_MAP_DATA_AS_ARRAY_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_TILE_SET_HASH = 774531446L
        private val setTileSetBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_tile_set", SET_TILE_SET_HASH)
        }

        private const val GET_TILE_SET_HASH = 2678226422L
        private val getTileSetBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_tile_set", GET_TILE_SET_HASH)
        }

        private const val SET_Y_SORT_ORIGIN_HASH = 1286410249L
        private val setYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_y_sort_origin", SET_Y_SORT_ORIGIN_HASH)
        }

        private const val GET_Y_SORT_ORIGIN_HASH = 3905245786L
        private val getYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_y_sort_origin", GET_Y_SORT_ORIGIN_HASH)
        }

        private const val SET_X_DRAW_ORDER_REVERSED_HASH = 2586408642L
        private val setXDrawOrderReversedBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_x_draw_order_reversed", SET_X_DRAW_ORDER_REVERSED_HASH)
        }

        private const val IS_X_DRAW_ORDER_REVERSED_HASH = 36873697L
        private val isXDrawOrderReversedBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_x_draw_order_reversed", IS_X_DRAW_ORDER_REVERSED_HASH)
        }

        private const val SET_RENDERING_QUADRANT_SIZE_HASH = 1286410249L
        private val setRenderingQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_rendering_quadrant_size", SET_RENDERING_QUADRANT_SIZE_HASH)
        }

        private const val GET_RENDERING_QUADRANT_SIZE_HASH = 3905245786L
        private val getRenderingQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_rendering_quadrant_size", GET_RENDERING_QUADRANT_SIZE_HASH)
        }

        private const val SET_COLLISION_ENABLED_HASH = 2586408642L
        private val setCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_collision_enabled", SET_COLLISION_ENABLED_HASH)
        }

        private const val IS_COLLISION_ENABLED_HASH = 36873697L
        private val isCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_collision_enabled", IS_COLLISION_ENABLED_HASH)
        }

        private const val SET_USE_KINEMATIC_BODIES_HASH = 2586408642L
        private val setUseKinematicBodiesBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_use_kinematic_bodies", SET_USE_KINEMATIC_BODIES_HASH)
        }

        private const val IS_USING_KINEMATIC_BODIES_HASH = 36873697L
        private val isUsingKinematicBodiesBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_using_kinematic_bodies", IS_USING_KINEMATIC_BODIES_HASH)
        }

        private const val SET_COLLISION_VISIBILITY_MODE_HASH = 3508099847L
        private val setCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_collision_visibility_mode", SET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val GET_COLLISION_VISIBILITY_MODE_HASH = 338220793L
        private val getCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_collision_visibility_mode", GET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val SET_PHYSICS_QUADRANT_SIZE_HASH = 1286410249L
        private val setPhysicsQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_physics_quadrant_size", SET_PHYSICS_QUADRANT_SIZE_HASH)
        }

        private const val GET_PHYSICS_QUADRANT_SIZE_HASH = 3905245786L
        private val getPhysicsQuadrantSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_physics_quadrant_size", GET_PHYSICS_QUADRANT_SIZE_HASH)
        }

        private const val SET_OCCLUSION_ENABLED_HASH = 2586408642L
        private val setOcclusionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_occlusion_enabled", SET_OCCLUSION_ENABLED_HASH)
        }

        private const val IS_OCCLUSION_ENABLED_HASH = 36873697L
        private val isOcclusionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_occlusion_enabled", IS_OCCLUSION_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_ENABLED_HASH = 2586408642L
        private val setNavigationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_navigation_enabled", SET_NAVIGATION_ENABLED_HASH)
        }

        private const val IS_NAVIGATION_ENABLED_HASH = 36873697L
        private val isNavigationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "is_navigation_enabled", IS_NAVIGATION_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_NAVIGATION_VISIBILITY_MODE_HASH = 3508099847L
        private val setNavigationVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "set_navigation_visibility_mode", SET_NAVIGATION_VISIBILITY_MODE_HASH)
        }

        private const val GET_NAVIGATION_VISIBILITY_MODE_HASH = 338220793L
        private val getNavigationVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TileMapLayer", "get_navigation_visibility_mode", GET_NAVIGATION_VISIBILITY_MODE_HASH)
        }
    }
}
