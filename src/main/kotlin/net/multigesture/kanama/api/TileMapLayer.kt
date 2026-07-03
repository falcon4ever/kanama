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

    /**
     * Sets the tile identifiers for the cell at coordinates `coords`. Each tile of the `TileSet` is
     * identified using three parts: - The source identifier `source_id` identifies a `TileSetSource`
     * identifier. See `TileSet.set_source_id`, - The atlas coordinate identifier `atlas_coords`
     * identifies a tile coordinates in the atlas (if the source is a `TileSetAtlasSource`). For
     * `TileSetScenesCollectionSource` it should always be `Vector2i(0, 0)`, - The alternative tile
     * identifier `alternative_tile` identifies a tile alternative in the atlas (if the source is a
     * `TileSetAtlasSource`), and the scene for a `TileSetScenesCollectionSource`. If `source_id` is
     * set to `-1`, `atlas_coords` to `Vector2i(-1, -1)`, or `alternative_tile` to `-1`, the cell will
     * be erased. An erased cell gets all its identifiers automatically set to their respective invalid
     * values, namely `-1`, `Vector2i(-1, -1)` and `-1`.
     *
     * Generated from Godot docs: TileMapLayer.set_cell
     */
    fun setCell(coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = 0) {
        ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(setCellBind, handle, coords, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Erases the cell at coordinates `coords`.
     *
     * Generated from Godot docs: TileMapLayer.erase_cell
     */
    fun eraseCell(coords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(eraseCellBind, handle, coords)
    }

    /**
     * Clears cells containing tiles that do not exist in the `tile_set`.
     *
     * Generated from Godot docs: TileMapLayer.fix_invalid_tiles
     */
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

    /**
     * Returns the tile source ID of the cell at coordinates `coords`. Returns `-1` if the cell does
     * not exist.
     *
     * Generated from Godot docs: TileMapLayer.get_cell_source_id
     */
    fun getCellSourceId(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellSourceIdBind, handle, coords)
    }

    /**
     * Returns the tile atlas coordinates ID of the cell at coordinates `coords`. Returns `Vector2i(-1,
     * -1)` if the cell does not exist.
     *
     * Generated from Godot docs: TileMapLayer.get_cell_atlas_coords
     */
    fun getCellAtlasCoords(coords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getCellAtlasCoordsBind, handle, coords)
    }

    /**
     * Returns the tile alternative ID of the cell at coordinates `coords`.
     *
     * Generated from Godot docs: TileMapLayer.get_cell_alternative_tile
     */
    fun getCellAlternativeTile(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellAlternativeTileBind, handle, coords)
    }

    /**
     * Returns the `TileData` object associated with the given cell, or `null` if the cell does not
     * exist or is not a `TileSetAtlasSource`.
     *
     * Generated from Godot docs: TileMapLayer.get_cell_tile_data
     */
    fun getCellTileData(coords: Vector2i): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithVector2iArgRetObject(getCellTileDataBind, handle, coords))
    }

    /**
     * Returns `true` if the cell at coordinates `coords` is flipped horizontally. The result is valid
     * only for atlas sources.
     *
     * Generated from Godot docs: TileMapLayer.is_cell_flipped_h
     */
    fun isCellFlippedH(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellFlippedHBind, handle, coords)
    }

    /**
     * Returns `true` if the cell at coordinates `coords` is flipped vertically. The result is valid
     * only for atlas sources.
     *
     * Generated from Godot docs: TileMapLayer.is_cell_flipped_v
     */
    fun isCellFlippedV(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellFlippedVBind, handle, coords)
    }

    /**
     * Returns `true` if the cell at coordinates `coords` is transposed. The result is valid only for
     * atlas sources.
     *
     * Generated from Godot docs: TileMapLayer.is_cell_transposed
     */
    fun isCellTransposed(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isCellTransposedBind, handle, coords)
    }

    /**
     * Returns a `Vector2i` array with the positions of all cells containing a tile. A cell is
     * considered empty if its source identifier equals `-1`, its atlas coordinate identifier is
     * `Vector2(-1, -1)` and its alternative identifier is `-1`.
     *
     * Generated from Godot docs: TileMapLayer.get_used_cells
     */
    fun getUsedCells(): List<Vector2i> {
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
    fun getUsedCellsById(sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1): List<Vector2i> {
        return ObjectCalls.ptrcallWithIntVector2iAndIntArgsRetVector2iList(getUsedCellsByIdBind, handle, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Returns a rectangle enclosing the used (non-empty) tiles of the map.
     *
     * Generated from Godot docs: TileMapLayer.get_used_rect
     */
    fun getUsedRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    /**
     * Creates and returns a new `TileMapPattern` from the given array of cells. See also
     * `set_pattern`.
     *
     * Generated from Godot docs: TileMapLayer.get_pattern
     */
    fun getPattern(coordsArray: List<Vector2i>): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithVector2iListArgRetObject(getPatternBind, handle, coordsArray))
    }

    /**
     * Pastes the `TileMapPattern` at the given `position` in the tile map. See also `get_pattern`.
     *
     * Generated from Godot docs: TileMapLayer.set_pattern
     */
    fun setPattern(position: Vector2i, pattern: TileMapPattern?) {
        ObjectCalls.ptrcallWithVector2iAndObjectArg(setPatternBind, handle, position, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
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
    fun setCellsTerrainConnect(cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
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
    fun setCellsTerrainPath(path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, path, terrainSet, terrain, ignoreEmptyTerrains)
    }

    /**
     * Returns whether the provided `body` `RID` belongs to one of this `TileMapLayer`'s cells.
     *
     * Generated from Godot docs: TileMapLayer.has_body_rid
     */
    fun hasBodyRid(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasBodyRidBind, handle, body)
    }

    /**
     * Returns the coordinates of the physics quadrant (see `physics_quadrant_size`) for given physics
     * body `RID`. Such an `RID` can be retrieved from `KinematicCollision2D.get_collider_rid`, when
     * colliding with a tile. Note: Higher values of `physics_quadrant_size` will make this function
     * less precise. To get the exact cell coordinates, you need to set `physics_quadrant_size` to `1`,
     * which disables physics chunking.
     *
     * Generated from Godot docs: TileMapLayer.get_coords_for_body_rid
     */
    fun getCoordsForBodyRid(body: RID): Vector2i {
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(getCoordsForBodyRidBind, handle, body)
    }

    /**
     * Triggers a direct update of the `TileMapLayer`. Usually, calling this function is not needed, as
     * `TileMapLayer` node updates automatically when one of its properties or cells is modified.
     * However, for performance reasons, those updates are batched and delayed to the end of the frame.
     * Calling this function will force the `TileMapLayer` to update right away instead. Warning:
     * Updating the `TileMapLayer` is computationally expensive and may impact performance. Try to
     * limit the number of updates and how many tiles they impact.
     *
     * Generated from Godot docs: TileMapLayer.update_internals
     */
    fun updateInternals() {
        ObjectCalls.ptrcallNoArgs(updateInternalsBind, handle)
    }

    /**
     * Notifies the `TileMapLayer` node that calls to `_use_tile_data_runtime_update` or
     * `_tile_data_runtime_update` will lead to different results. This will thus trigger a
     * `TileMapLayer` update. Warning: Updating the `TileMapLayer` is computationally expensive and may
     * impact performance. Try to limit the number of calls to this function to avoid unnecessary
     * update. Note: This does not trigger a direct update of the `TileMapLayer`, the update will be
     * done at the end of the frame as usual (unless you call `update_internals`).
     *
     * Generated from Godot docs: TileMapLayer.notify_runtime_tile_data_update
     */
    fun notifyRuntimeTileDataUpdate() {
        ObjectCalls.ptrcallNoArgs(notifyRuntimeTileDataUpdateBind, handle)
    }

    /**
     * Returns for the given coordinates `coords_in_pattern` in a `TileMapPattern` the corresponding
     * cell coordinates if the pattern was pasted at the `position_in_tilemap` coordinates (see
     * `set_pattern`). This mapping is required as in half-offset tile shapes, the mapping might not
     * work by calculating `position_in_tile_map + coords_in_pattern`.
     *
     * Generated from Godot docs: TileMapLayer.map_pattern
     */
    fun mapPattern(positionInTilemap: Vector2i, coordsInPattern: Vector2i, pattern: TileMapPattern?): Vector2i {
        return ObjectCalls.ptrcallWithTwoVector2iAndObjectArgRetVector2i(mapPatternBind, handle, positionInTilemap, coordsInPattern, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the list of all neighboring cells to the one at `coords`. Any neighboring cell is one
     * that is touching edges, so for a square cell 4 cells would be returned, for a hexagon 6 cells
     * are returned.
     *
     * Generated from Godot docs: TileMapLayer.get_surrounding_cells
     */
    fun getSurroundingCells(coords: Vector2i): List<Vector2i> {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2iList(getSurroundingCellsBind, handle, coords)
    }

    /**
     * Returns the neighboring cell to the one at coordinates `coords`, identified by the `neighbor`
     * direction. This method takes into account the different layouts a TileMap can take.
     *
     * Generated from Godot docs: TileMapLayer.get_neighbor_cell
     */
    fun getNeighborCell(coords: Vector2i, neighbor: Long): Vector2i {
        return ObjectCalls.ptrcallWithVector2iAndLongArgRetVector2i(getNeighborCellBind, handle, coords, neighbor)
    }

    /**
     * Returns the centered position of a cell in the `TileMapLayer`'s local coordinate space. To
     * convert the returned value into global coordinates, use `Node2D.to_global`. See also
     * `local_to_map`. Note: This may not correspond to the visual position of the tile, i.e. it
     * ignores the `TileData.texture_origin` property of individual tiles.
     *
     * Generated from Godot docs: TileMapLayer.map_to_local
     */
    fun mapToLocal(mapPosition: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(mapToLocalBind, handle, mapPosition)
    }

    /**
     * Returns the map coordinates of the cell containing the given `local_position`. If
     * `local_position` is in global coordinates, consider using `Node2D.to_local` before passing it to
     * this method. See also `map_to_local`.
     *
     * Generated from Godot docs: TileMapLayer.local_to_map
     */
    fun localToMap(localPosition: Vector2): Vector2i {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2i(localToMapBind, handle, localPosition)
    }

    /**
     * The raw tile map data as a byte array.
     *
     * Generated from Godot docs: TileMapLayer.set_tile_map_data_from_array
     */
    fun setTileMapDataFromArray(tileMapLayerData: ByteArray) {
        ObjectCalls.ptrcallWithByteArrayArg(setTileMapDataFromArrayBind, handle, tileMapLayerData)
    }

    /**
     * The raw tile map data as a byte array.
     *
     * Generated from Godot docs: TileMapLayer.get_tile_map_data_as_array
     */
    fun getTileMapDataAsArray(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getTileMapDataAsArrayBind, handle)
    }

    /**
     * If `false`, disables this `TileMapLayer` completely (rendering, collision, navigation, scene
     * tiles, etc.)
     *
     * Generated from Godot docs: TileMapLayer.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * If `false`, disables this `TileMapLayer` completely (rendering, collision, navigation, scene
     * tiles, etc.)
     *
     * Generated from Godot docs: TileMapLayer.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * The `TileSet` used by this layer. The textures, collisions, and additional behavior of all
     * available tiles are stored here.
     *
     * Generated from Godot docs: TileMapLayer.set_tile_set
     */
    fun setTileSet(tileSet: TileSet?) {
        ObjectCalls.ptrcallWithObjectArgs(setTileSetBind, handle, listOf(tileSet?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `TileSet` used by this layer. The textures, collisions, and additional behavior of all
     * available tiles are stored here.
     *
     * Generated from Godot docs: TileMapLayer.get_tile_set
     */
    fun getTileSet(): TileSet? {
        return TileSet.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTileSetBind, handle))
    }

    /**
     * This Y-sort origin value is added to each tile's Y-sort origin value. This allows, for example,
     * to fake a different height level. This can be useful for top-down view games.
     *
     * Generated from Godot docs: TileMapLayer.set_y_sort_origin
     */
    fun setYSortOrigin(ySortOrigin: Int) {
        ObjectCalls.ptrcallWithIntArg(setYSortOriginBind, handle, ySortOrigin)
    }

    /**
     * This Y-sort origin value is added to each tile's Y-sort origin value. This allows, for example,
     * to fake a different height level. This can be useful for top-down view games.
     *
     * Generated from Godot docs: TileMapLayer.get_y_sort_origin
     */
    fun getYSortOrigin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getYSortOriginBind, handle)
    }

    /**
     * If `CanvasItem.y_sort_enabled` is enabled, setting this to `true` will reverse the order the
     * tiles are drawn on the X-axis.
     *
     * Generated from Godot docs: TileMapLayer.set_x_draw_order_reversed
     */
    fun setXDrawOrderReversed(xDrawOrderReversed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setXDrawOrderReversedBind, handle, xDrawOrderReversed)
    }

    /**
     * If `CanvasItem.y_sort_enabled` is enabled, setting this to `true` will reverse the order the
     * tiles are drawn on the X-axis.
     *
     * Generated from Godot docs: TileMapLayer.is_x_draw_order_reversed
     */
    fun isXDrawOrderReversed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isXDrawOrderReversedBind, handle)
    }

    /**
     * The `TileMapLayer`'s rendering quadrant size. A quadrant is a group of tiles to be drawn
     * together on a single canvas item, for optimization purposes. `rendering_quadrant_size` defines
     * the length of a square's side, in the map's coordinate system, that forms the quadrant. Thus,
     * the default quadrant size groups together `16 * 16 = 256` tiles. The quadrant size does not
     * apply on a Y-sorted `TileMapLayer`, as tiles are grouped by Y position instead in that case.
     * Note: As quadrants are created according to the map's coordinate system, the quadrant's "square
     * shape" might not look like square in the `TileMapLayer`'s local coordinate system.
     *
     * Generated from Godot docs: TileMapLayer.set_rendering_quadrant_size
     */
    fun setRenderingQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderingQuadrantSizeBind, handle, size)
    }

    /**
     * The `TileMapLayer`'s rendering quadrant size. A quadrant is a group of tiles to be drawn
     * together on a single canvas item, for optimization purposes. `rendering_quadrant_size` defines
     * the length of a square's side, in the map's coordinate system, that forms the quadrant. Thus,
     * the default quadrant size groups together `16 * 16 = 256` tiles. The quadrant size does not
     * apply on a Y-sorted `TileMapLayer`, as tiles are grouped by Y position instead in that case.
     * Note: As quadrants are created according to the map's coordinate system, the quadrant's "square
     * shape" might not look like square in the `TileMapLayer`'s local coordinate system.
     *
     * Generated from Godot docs: TileMapLayer.get_rendering_quadrant_size
     */
    fun getRenderingQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderingQuadrantSizeBind, handle)
    }

    /**
     * Enable or disable collisions.
     *
     * Generated from Godot docs: TileMapLayer.set_collision_enabled
     */
    fun setCollisionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionEnabledBind, handle, enabled)
    }

    /**
     * Enable or disable collisions.
     *
     * Generated from Godot docs: TileMapLayer.is_collision_enabled
     */
    fun isCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionEnabledBind, handle)
    }

    /**
     * If `true`, this `TileMapLayer` collision shapes will be instantiated as kinematic bodies. This
     * can be needed for moving `TileMapLayer` nodes (i.e. moving platforms).
     *
     * Generated from Godot docs: TileMapLayer.set_use_kinematic_bodies
     */
    fun setUseKinematicBodies(useKinematicBodies: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseKinematicBodiesBind, handle, useKinematicBodies)
    }

    /**
     * If `true`, this `TileMapLayer` collision shapes will be instantiated as kinematic bodies. This
     * can be needed for moving `TileMapLayer` nodes (i.e. moving platforms).
     *
     * Generated from Godot docs: TileMapLayer.is_using_kinematic_bodies
     */
    fun isUsingKinematicBodies(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingKinematicBodiesBind, handle)
    }

    /**
     * Show or hide the `TileMapLayer`'s collision shapes. If set to `DEBUG_VISIBILITY_MODE_DEFAULT`,
     * this depends on the show collision debug settings.
     *
     * Generated from Godot docs: TileMapLayer.set_collision_visibility_mode
     */
    fun setCollisionVisibilityMode(visibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionVisibilityModeBind, handle, visibilityMode)
    }

    /**
     * Show or hide the `TileMapLayer`'s collision shapes. If set to `DEBUG_VISIBILITY_MODE_DEFAULT`,
     * this depends on the show collision debug settings.
     *
     * Generated from Godot docs: TileMapLayer.get_collision_visibility_mode
     */
    fun getCollisionVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionVisibilityModeBind, handle)
    }

    /**
     * The `TileMapLayer`'s physics quadrant size. Within a physics quadrant, cells with similar
     * physics properties are grouped together and their collision shapes get merged.
     * `physics_quadrant_size` defines the length of a square's side, in the map's coordinate system,
     * that forms the quadrant. Thus, the default quadrant size groups together `16 * 16 = 256` tiles.
     * Note: As quadrants are created according to the map's coordinate system, the quadrant's "square
     * shape" might not look like square in the `TileMapLayer`'s local coordinate system. Note: This
     * impacts the value returned by `get_coords_for_body_rid`. Higher values will make that function
     * less precise. To get the exact cell coordinates, you need to set `physics_quadrant_size` to `1`,
     * which disables physics chunking.
     *
     * Generated from Godot docs: TileMapLayer.set_physics_quadrant_size
     */
    fun setPhysicsQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsQuadrantSizeBind, handle, size)
    }

    /**
     * The `TileMapLayer`'s physics quadrant size. Within a physics quadrant, cells with similar
     * physics properties are grouped together and their collision shapes get merged.
     * `physics_quadrant_size` defines the length of a square's side, in the map's coordinate system,
     * that forms the quadrant. Thus, the default quadrant size groups together `16 * 16 = 256` tiles.
     * Note: As quadrants are created according to the map's coordinate system, the quadrant's "square
     * shape" might not look like square in the `TileMapLayer`'s local coordinate system. Note: This
     * impacts the value returned by `get_coords_for_body_rid`. Higher values will make that function
     * less precise. To get the exact cell coordinates, you need to set `physics_quadrant_size` to `1`,
     * which disables physics chunking.
     *
     * Generated from Godot docs: TileMapLayer.get_physics_quadrant_size
     */
    fun getPhysicsQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsQuadrantSizeBind, handle)
    }

    /**
     * Enable or disable light occlusion.
     *
     * Generated from Godot docs: TileMapLayer.set_occlusion_enabled
     */
    fun setOcclusionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOcclusionEnabledBind, handle, enabled)
    }

    /**
     * Enable or disable light occlusion.
     *
     * Generated from Godot docs: TileMapLayer.is_occlusion_enabled
     */
    fun isOcclusionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOcclusionEnabledBind, handle)
    }

    /**
     * If `true`, navigation regions are enabled.
     *
     * Generated from Godot docs: TileMapLayer.set_navigation_enabled
     */
    fun setNavigationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNavigationEnabledBind, handle, enabled)
    }

    /**
     * If `true`, navigation regions are enabled.
     *
     * Generated from Godot docs: TileMapLayer.is_navigation_enabled
     */
    fun isNavigationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNavigationEnabledBind, handle)
    }

    /**
     * Sets a custom `map` as a `NavigationServer2D` navigation map. If not set, uses the default
     * `World2D` navigation map instead.
     *
     * Generated from Godot docs: TileMapLayer.set_navigation_map
     */
    fun setNavigationMap(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, map)
    }

    /**
     * Returns the `RID` of the `NavigationServer2D` navigation used by this `TileMapLayer`. By default
     * this returns the default `World2D` navigation map, unless a custom map was provided using
     * `set_navigation_map`.
     *
     * Generated from Godot docs: TileMapLayer.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    /**
     * Show or hide the `TileMapLayer`'s navigation meshes. If set to `DEBUG_VISIBILITY_MODE_DEFAULT`,
     * this depends on the show navigation debug settings.
     *
     * Generated from Godot docs: TileMapLayer.set_navigation_visibility_mode
     */
    fun setNavigationVisibilityMode(showNavigation: Long) {
        ObjectCalls.ptrcallWithLongArg(setNavigationVisibilityModeBind, handle, showNavigation)
    }

    /**
     * Show or hide the `TileMapLayer`'s navigation meshes. If set to `DEBUG_VISIBILITY_MODE_DEFAULT`,
     * this depends on the show navigation debug settings.
     *
     * Generated from Godot docs: TileMapLayer.get_navigation_visibility_mode
     */
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
