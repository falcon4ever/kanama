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

    /**
     * Assigns `map` as a `NavigationServer2D` navigation map for the specified TileMap layer `layer`.
     *
     * Generated from Godot docs: TileMap.set_navigation_map
     */
    fun setNavigationMap(layer: Int, map: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(setNavigationMapBind, handle, layer, map)
    }

    /**
     * Returns the `RID` of the `NavigationServer2D` navigation map assigned to the specified TileMap
     * layer `layer`.
     *
     * Generated from Godot docs: TileMap.get_navigation_map
     */
    fun getNavigationMap(layer: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getNavigationMapBind, handle, layer)
    }

    /**
     * Forces the TileMap and the layer `layer` to update.
     *
     * Generated from Godot docs: TileMap.force_update
     */
    fun forceUpdate(layer: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(forceUpdateBind, handle, layer)
    }

    /**
     * The `TileSet` used by this `TileMap`. The textures, collisions, and additional behavior of all
     * available tiles are stored here.
     *
     * Generated from Godot docs: TileMap.set_tileset
     */
    fun setTileset(tileset: TileSet?) {
        ObjectCalls.ptrcallWithObjectArgs(setTilesetBind, handle, listOf(tileset?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `TileSet` used by this `TileMap`. The textures, collisions, and additional behavior of all
     * available tiles are stored here.
     *
     * Generated from Godot docs: TileMap.get_tileset
     */
    fun getTileset(): TileSet? {
        return TileSet.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTilesetBind, handle))
    }

    /**
     * The TileMap's quadrant size. A quadrant is a group of tiles to be drawn together on a single
     * canvas item, for optimization purposes. `rendering_quadrant_size` defines the length of a
     * square's side, in the map's coordinate system, that forms the quadrant. Thus, the default
     * quadrant size groups together `16 * 16 = 256` tiles. The quadrant size does not apply on
     * Y-sorted layers, as tiles are grouped by Y position instead in that case. Note: As quadrants are
     * created according to the map's coordinate system, the quadrant's "square shape" might not look
     * like square in the TileMap's local coordinate system.
     *
     * Generated from Godot docs: TileMap.set_rendering_quadrant_size
     */
    fun setRenderingQuadrantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderingQuadrantSizeBind, handle, size)
    }

    /**
     * The TileMap's quadrant size. A quadrant is a group of tiles to be drawn together on a single
     * canvas item, for optimization purposes. `rendering_quadrant_size` defines the length of a
     * square's side, in the map's coordinate system, that forms the quadrant. Thus, the default
     * quadrant size groups together `16 * 16 = 256` tiles. The quadrant size does not apply on
     * Y-sorted layers, as tiles are grouped by Y position instead in that case. Note: As quadrants are
     * created according to the map's coordinate system, the quadrant's "square shape" might not look
     * like square in the TileMap's local coordinate system.
     *
     * Generated from Godot docs: TileMap.get_rendering_quadrant_size
     */
    fun getRenderingQuadrantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderingQuadrantSizeBind, handle)
    }

    /**
     * Returns the number of layers in the TileMap.
     *
     * Generated from Godot docs: TileMap.get_layers_count
     */
    fun getLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayersCountBind, handle)
    }

    /**
     * Adds a layer at the given position `to_position` in the array. If `to_position` is negative, the
     * position is counted from the end, with `-1` adding the layer at the end of the array.
     *
     * Generated from Godot docs: TileMap.add_layer
     */
    fun addLayer(toPosition: Int) {
        ObjectCalls.ptrcallWithIntArg(addLayerBind, handle, toPosition)
    }

    /**
     * Moves the layer at index `layer` to the given position `to_position` in the array.
     *
     * Generated from Godot docs: TileMap.move_layer
     */
    fun moveLayer(layer: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveLayerBind, handle, layer, toPosition)
    }

    /**
     * Removes the layer at index `layer`.
     *
     * Generated from Godot docs: TileMap.remove_layer
     */
    fun removeLayer(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(removeLayerBind, handle, layer)
    }

    /**
     * Sets a layer's name. This is mostly useful in the editor. If `layer` is negative, the layers are
     * accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_name
     */
    fun setLayerName(layer: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setLayerNameBind, handle, layer, name)
    }

    /**
     * Returns a TileMap layer's name. If `layer` is negative, the layers are accessed from the last
     * one.
     *
     * Generated from Godot docs: TileMap.get_layer_name
     */
    fun getLayerName(layer: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getLayerNameBind, handle, layer)
    }

    /**
     * Enables or disables the layer `layer`. A disabled layer is not processed at all (no rendering,
     * no physics, etc.). If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_enabled
     */
    fun setLayerEnabled(layer: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerEnabledBind, handle, layer, enabled)
    }

    /**
     * Returns if a layer is enabled. If `layer` is negative, the layers are accessed from the last
     * one.
     *
     * Generated from Godot docs: TileMap.is_layer_enabled
     */
    fun isLayerEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerEnabledBind, handle, layer)
    }

    /**
     * Sets a layer's color. It will be multiplied by tile's color and TileMap's modulate. If `layer`
     * is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_modulate
     */
    fun setLayerModulate(layer: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setLayerModulateBind, handle, layer, modulate)
    }

    /**
     * Returns a TileMap layer's modulate. If `layer` is negative, the layers are accessed from the
     * last one.
     *
     * Generated from Godot docs: TileMap.get_layer_modulate
     */
    fun getLayerModulate(layer: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getLayerModulateBind, handle, layer)
    }

    /**
     * Enables or disables a layer's Y-sorting. If a layer is Y-sorted, the layer will behave as a
     * CanvasItem node where each of its tile gets Y-sorted. Y-sorted layers should usually be on
     * different Z-index values than not Y-sorted layers, otherwise, each of those layer will be
     * Y-sorted as whole with the Y-sorted one. This is usually an undesired behavior. If `layer` is
     * negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_y_sort_enabled
     */
    fun setLayerYSortEnabled(layer: Int, ySortEnabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerYSortEnabledBind, handle, layer, ySortEnabled)
    }

    /**
     * Returns if a layer Y-sorts its tiles. If `layer` is negative, the layers are accessed from the
     * last one.
     *
     * Generated from Godot docs: TileMap.is_layer_y_sort_enabled
     */
    fun isLayerYSortEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerYSortEnabledBind, handle, layer)
    }

    /**
     * Sets a layer's Y-sort origin value. This Y-sort origin value is added to each tile's Y-sort
     * origin value. This allows, for example, to fake a different height level on each layer. This can
     * be useful for top-down view games. If `layer` is negative, the layers are accessed from the last
     * one.
     *
     * Generated from Godot docs: TileMap.set_layer_y_sort_origin
     */
    fun setLayerYSortOrigin(layer: Int, ySortOrigin: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLayerYSortOriginBind, handle, layer, ySortOrigin)
    }

    /**
     * Returns a TileMap layer's Y sort origin. If `layer` is negative, the layers are accessed from
     * the last one.
     *
     * Generated from Godot docs: TileMap.get_layer_y_sort_origin
     */
    fun getLayerYSortOrigin(layer: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLayerYSortOriginBind, handle, layer)
    }

    /**
     * Sets a layers Z-index value. This Z-index is added to each tile's Z-index value. If `layer` is
     * negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_z_index
     */
    fun setLayerZIndex(layer: Int, zIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLayerZIndexBind, handle, layer, zIndex)
    }

    /**
     * Returns a TileMap layer's Z-index value. If `layer` is negative, the layers are accessed from
     * the last one.
     *
     * Generated from Godot docs: TileMap.get_layer_z_index
     */
    fun getLayerZIndex(layer: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLayerZIndexBind, handle, layer)
    }

    /**
     * Enables or disables a layer's built-in navigation regions generation. Disable this if you need
     * to bake navigation regions from a TileMap using a `NavigationRegion2D` node.
     *
     * Generated from Godot docs: TileMap.set_layer_navigation_enabled
     */
    fun setLayerNavigationEnabled(layer: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerNavigationEnabledBind, handle, layer, enabled)
    }

    /**
     * Returns if a layer's built-in navigation regions generation is enabled.
     *
     * Generated from Godot docs: TileMap.is_layer_navigation_enabled
     */
    fun isLayerNavigationEnabled(layer: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLayerNavigationEnabledBind, handle, layer)
    }

    /**
     * Assigns `map` as a `NavigationServer2D` navigation map for the specified TileMap layer `layer`.
     * By default the TileMap uses the default `World2D` navigation map for the first TileMap layer.
     * For each additional TileMap layer a new navigation map is created for the additional layer. In
     * order to make `NavigationAgent2D` switch between TileMap layer navigation maps use
     * `NavigationAgent2D.set_navigation_map` with the navigation map received from
     * `get_layer_navigation_map`. If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_layer_navigation_map
     */
    fun setLayerNavigationMap(layer: Int, map: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(setLayerNavigationMapBind, handle, layer, map)
    }

    /**
     * Returns the `RID` of the `NavigationServer2D` navigation map assigned to the specified TileMap
     * layer `layer`. By default the TileMap uses the default `World2D` navigation map for the first
     * TileMap layer. For each additional TileMap layer a new navigation map is created for the
     * additional layer. In order to make `NavigationAgent2D` switch between TileMap layer navigation
     * maps use `NavigationAgent2D.set_navigation_map` with the navigation map received from
     * `get_layer_navigation_map`. If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_layer_navigation_map
     */
    fun getLayerNavigationMap(layer: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getLayerNavigationMapBind, handle, layer)
    }

    /**
     * If enabled, the TileMap will see its collisions synced to the physics tick and change its
     * collision type from static to kinematic. This is required to create TileMap-based moving
     * platform. Note: Enabling `collision_animatable` may have a small performance impact, only do it
     * if the TileMap is moving and has colliding tiles.
     *
     * Generated from Godot docs: TileMap.set_collision_animatable
     */
    fun setCollisionAnimatable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionAnimatableBind, handle, enabled)
    }

    /**
     * If enabled, the TileMap will see its collisions synced to the physics tick and change its
     * collision type from static to kinematic. This is required to create TileMap-based moving
     * platform. Note: Enabling `collision_animatable` may have a small performance impact, only do it
     * if the TileMap is moving and has colliding tiles.
     *
     * Generated from Godot docs: TileMap.is_collision_animatable
     */
    fun isCollisionAnimatable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionAnimatableBind, handle)
    }

    /**
     * Show or hide the TileMap's collision shapes. If set to `VISIBILITY_MODE_DEFAULT`, this depends
     * on the show collision debug settings.
     *
     * Generated from Godot docs: TileMap.set_collision_visibility_mode
     */
    fun setCollisionVisibilityMode(collisionVisibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionVisibilityModeBind, handle, collisionVisibilityMode)
    }

    /**
     * Show or hide the TileMap's collision shapes. If set to `VISIBILITY_MODE_DEFAULT`, this depends
     * on the show collision debug settings.
     *
     * Generated from Godot docs: TileMap.get_collision_visibility_mode
     */
    fun getCollisionVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionVisibilityModeBind, handle)
    }

    /**
     * Show or hide the TileMap's navigation meshes. If set to `VISIBILITY_MODE_DEFAULT`, this depends
     * on the show navigation debug settings.
     *
     * Generated from Godot docs: TileMap.set_navigation_visibility_mode
     */
    fun setNavigationVisibilityMode(navigationVisibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setNavigationVisibilityModeBind, handle, navigationVisibilityMode)
    }

    /**
     * Show or hide the TileMap's navigation meshes. If set to `VISIBILITY_MODE_DEFAULT`, this depends
     * on the show navigation debug settings.
     *
     * Generated from Godot docs: TileMap.get_navigation_visibility_mode
     */
    fun getNavigationVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNavigationVisibilityModeBind, handle)
    }

    /**
     * Sets the tile identifiers for the cell on layer `layer` at coordinates `coords`. Each tile of
     * the `TileSet` is identified using three parts: - The source identifier `source_id` identifies a
     * `TileSetSource` identifier. See `TileSet.set_source_id`, - The atlas coordinates identifier
     * `atlas_coords` identifies a tile coordinates in the atlas (if the source is a
     * `TileSetAtlasSource`). For `TileSetScenesCollectionSource` it should always be `Vector2i(0,
     * 0)`), - The alternative tile identifier `alternative_tile` identifies a tile alternative in the
     * atlas (if the source is a `TileSetAtlasSource`), and the scene for a
     * `TileSetScenesCollectionSource`. If `source_id` is set to `-1`, `atlas_coords` to `Vector2i(-1,
     * -1)` or `alternative_tile` to `-1`, the cell will be erased. An erased cell gets all its
     * identifiers automatically set to their respective invalid values, namely `-1`, `Vector2i(-1,
     * -1)` and `-1`. If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_cell
     */
    fun setCell(layer: Int, coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = 0) {
        ObjectCalls.ptrcallWithIntVector2iIntVector2iIntArgs(setCellBind, handle, layer, coords, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Erases the cell on layer `layer` at coordinates `coords`. If `layer` is negative, the layers are
     * accessed from the last one.
     *
     * Generated from Godot docs: TileMap.erase_cell
     */
    fun eraseCell(layer: Int, coords: Vector2i) {
        ObjectCalls.ptrcallWithIntAndVector2iArg(eraseCellBind, handle, layer, coords)
    }

    /**
     * Returns the tile source ID of the cell on layer `layer` at coordinates `coords`. Returns `-1` if
     * the cell does not exist. If `use_proxies` is `false`, ignores the `TileSet`'s tile proxies,
     * returning the raw source identifier. See `TileSet.map_tile_proxy`. If `layer` is negative, the
     * layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_cell_source_id
     */
    fun getCellSourceId(layer: Int, coords: Vector2i, useProxies: Boolean = false): Int {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetInt(getCellSourceIdBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns the tile atlas coordinates ID of the cell on layer `layer` at coordinates `coords`.
     * Returns `Vector2i(-1, -1)` if the cell does not exist. If `use_proxies` is `false`, ignores the
     * `TileSet`'s tile proxies, returning the raw atlas coordinate identifier. See
     * `TileSet.map_tile_proxy`. If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_cell_atlas_coords
     */
    fun getCellAtlasCoords(layer: Int, coords: Vector2i, useProxies: Boolean = false): Vector2i {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetVector2i(getCellAtlasCoordsBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns the tile alternative ID of the cell on layer `layer` at `coords`. If `use_proxies` is
     * `false`, ignores the `TileSet`'s tile proxies, returning the raw alternative identifier. See
     * `TileSet.map_tile_proxy`. If `layer` is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_cell_alternative_tile
     */
    fun getCellAlternativeTile(layer: Int, coords: Vector2i, useProxies: Boolean = false): Int {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetInt(getCellAlternativeTileBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns the `TileData` object associated with the given cell, or `null` if the cell does not
     * exist or is not a `TileSetAtlasSource`. If `layer` is negative, the layers are accessed from the
     * last one.
     *
     * Generated from Godot docs: TileMap.get_cell_tile_data
     */
    fun getCellTileData(layer: Int, coords: Vector2i, useProxies: Boolean = false): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetObject(getCellTileDataBind, handle, layer, coords, useProxies))
    }

    /**
     * Returns `true` if the cell on layer `layer` at coordinates `coords` is flipped horizontally. The
     * result is valid only for atlas sources.
     *
     * Generated from Godot docs: TileMap.is_cell_flipped_h
     */
    fun isCellFlippedH(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellFlippedHBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns `true` if the cell on layer `layer` at coordinates `coords` is flipped vertically. The
     * result is valid only for atlas sources.
     *
     * Generated from Godot docs: TileMap.is_cell_flipped_v
     */
    fun isCellFlippedV(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellFlippedVBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns `true` if the cell on layer `layer` at coordinates `coords` is transposed. The result is
     * valid only for atlas sources.
     *
     * Generated from Godot docs: TileMap.is_cell_transposed
     */
    fun isCellTransposed(layer: Int, coords: Vector2i, useProxies: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iAndBoolArgRetBool(isCellTransposedBind, handle, layer, coords, useProxies)
    }

    /**
     * Returns the coordinates of the tile for given physics body RID. Such RID can be retrieved from
     * `KinematicCollision2D.get_collider_rid`, when colliding with a tile.
     *
     * Generated from Godot docs: TileMap.get_coords_for_body_rid
     */
    fun getCoordsForBodyRid(body: RID): Vector2i {
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(getCoordsForBodyRidBind, handle, body)
    }

    /**
     * Returns the tilemap layer of the tile for given physics body RID. Such RID can be retrieved from
     * `KinematicCollision2D.get_collider_rid`, when colliding with a tile.
     *
     * Generated from Godot docs: TileMap.get_layer_for_body_rid
     */
    fun getLayerForBodyRid(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(getLayerForBodyRidBind, handle, body)
    }

    /**
     * Creates a new `TileMapPattern` from the given layer and set of cells. If `layer` is negative,
     * the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_pattern
     */
    fun getPattern(layer: Int, coordsArray: List<Vector2i>): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithIntAndVector2iListArgsRetObject(getPatternBind, handle, layer, coordsArray))
    }

    /**
     * Returns for the given coordinate `coords_in_pattern` in a `TileMapPattern` the corresponding
     * cell coordinates if the pattern was pasted at the `position_in_tilemap` coordinates (see
     * `set_pattern`). This mapping is required as in half-offset tile shapes, the mapping might not
     * work by calculating `position_in_tile_map + coords_in_pattern`.
     *
     * Generated from Godot docs: TileMap.map_pattern
     */
    fun mapPattern(positionInTilemap: Vector2i, coordsInPattern: Vector2i, pattern: TileMapPattern?): Vector2i {
        return ObjectCalls.ptrcallWithTwoVector2iAndObjectArgRetVector2i(mapPatternBind, handle, positionInTilemap, coordsInPattern, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Paste the given `TileMapPattern` at the given `position` and `layer` in the tile map. If `layer`
     * is negative, the layers are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.set_pattern
     */
    fun setPattern(layer: Int, position: Vector2i, pattern: TileMapPattern?) {
        ObjectCalls.ptrcallWithIntVector2iAndObjectArg(setPatternBind, handle, layer, position, pattern?.requireOpenHandle() ?: MemorySegment.NULL)
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
    fun setCellsTerrainConnect(layer: Int, cells: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
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
    fun setCellsTerrainPath(layer: Int, path: List<Vector2i>, terrainSet: Int, terrain: Int, ignoreEmptyTerrains: Boolean = true) {
        ObjectCalls.ptrcallWithIntVector2iListTwoIntAndBoolArgs(setCellsTerrainPathBind, handle, layer, path, terrainSet, terrain, ignoreEmptyTerrains)
    }

    /**
     * Clears cells that do not exist in the tileset.
     *
     * Generated from Godot docs: TileMap.fix_invalid_tiles
     */
    fun fixInvalidTiles() {
        ObjectCalls.ptrcallNoArgs(fixInvalidTilesBind, handle)
    }

    /**
     * Clears all cells on the given layer. If `layer` is negative, the layers are accessed from the
     * last one.
     *
     * Generated from Godot docs: TileMap.clear_layer
     */
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

    /**
     * Triggers a direct update of the TileMap. Usually, calling this function is not needed, as
     * TileMap node updates automatically when one of its properties or cells is modified. However, for
     * performance reasons, those updates are batched and delayed to the end of the frame. Calling this
     * function will force the TileMap to update right away instead. Warning: Updating the TileMap is
     * computationally expensive and may impact performance. Try to limit the number of updates and how
     * many tiles they impact.
     *
     * Generated from Godot docs: TileMap.update_internals
     */
    fun updateInternals() {
        ObjectCalls.ptrcallNoArgs(updateInternalsBind, handle)
    }

    /**
     * Notifies the TileMap node that calls to `_use_tile_data_runtime_update` or
     * `_tile_data_runtime_update` will lead to different results. This will thus trigger a TileMap
     * update. If `layer` is provided, only notifies changes for the given layer. Providing the `layer`
     * argument (when applicable) is usually preferred for performance reasons. Warning: Updating the
     * TileMap is computationally expensive and may impact performance. Try to limit the number of
     * calls to this function to avoid unnecessary update. Note: This does not trigger a direct update
     * of the TileMap, the update will be done at the end of the frame as usual (unless you call
     * `update_internals`).
     *
     * Generated from Godot docs: TileMap.notify_runtime_tile_data_update
     */
    fun notifyRuntimeTileDataUpdate(layer: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(notifyRuntimeTileDataUpdateBind, handle, layer)
    }

    /**
     * Returns the list of all neighbourings cells to the one at `coords`.
     *
     * Generated from Godot docs: TileMap.get_surrounding_cells
     */
    fun getSurroundingCells(coords: Vector2i): List<Vector2i> {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2iList(getSurroundingCellsBind, handle, coords)
    }

    /**
     * Returns a `Vector2i` array with the positions of all cells containing a tile in the given layer.
     * A cell is considered empty if its source identifier equals -1, its atlas coordinates identifiers
     * is `Vector2(-1, -1)` and its alternative identifier is -1. If `layer` is negative, the layers
     * are accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_used_cells
     */
    fun getUsedCells(layer: Int): List<Vector2i> {
        return ObjectCalls.ptrcallWithIntArgRetVector2iList(getUsedCellsBind, handle, layer)
    }

    /**
     * Returns a `Vector2i` array with the positions of all cells containing a tile in the given layer.
     * Tiles may be filtered according to their source (`source_id`), their atlas coordinates
     * (`atlas_coords`) or alternative id (`alternative_tile`). If a parameter has its value set to the
     * default one, this parameter is not used to filter a cell. Thus, if all parameters have their
     * respective default value, this method returns the same result as `get_used_cells`. A cell is
     * considered empty if its source identifier equals -1, its atlas coordinates identifiers is
     * `Vector2(-1, -1)` and its alternative identifier is -1. If `layer` is negative, the layers are
     * accessed from the last one.
     *
     * Generated from Godot docs: TileMap.get_used_cells_by_id
     */
    fun getUsedCellsById(layer: Int, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1): List<Vector2i> {
        return ObjectCalls.ptrcallWithTwoIntVector2iAndIntArgsRetVector2iList(getUsedCellsByIdBind, handle, layer, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Returns a rectangle enclosing the used (non-empty) tiles of the map, including all layers.
     *
     * Generated from Godot docs: TileMap.get_used_rect
     */
    fun getUsedRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getUsedRectBind, handle)
    }

    /**
     * Returns the centered position of a cell in the TileMap's local coordinate space. To convert the
     * returned value into global coordinates, use `Node2D.to_global`. See also `local_to_map`. Note:
     * This may not correspond to the visual position of the tile, i.e. it ignores the
     * `TileData.texture_origin` property of individual tiles.
     *
     * Generated from Godot docs: TileMap.map_to_local
     */
    fun mapToLocal(mapPosition: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(mapToLocalBind, handle, mapPosition)
    }

    /**
     * Returns the map coordinates of the cell containing the given `local_position`. If
     * `local_position` is in global coordinates, consider using `Node2D.to_local` before passing it to
     * this method. See also `map_to_local`.
     *
     * Generated from Godot docs: TileMap.local_to_map
     */
    fun localToMap(localPosition: Vector2): Vector2i {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2i(localToMapBind, handle, localPosition)
    }

    /**
     * Returns the neighboring cell to the one at coordinates `coords`, identified by the `neighbor`
     * direction. This method takes into account the different layouts a TileMap can take.
     *
     * Generated from Godot docs: TileMap.get_neighbor_cell
     */
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
