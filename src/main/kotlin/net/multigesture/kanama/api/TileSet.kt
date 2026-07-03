package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2i

/**
 * Tile library for tilemaps.
 *
 * Generated from Godot docs: TileSet
 */
class TileSet(handle: MemorySegment) : Resource(handle) {
    var tileShape: Long
        @JvmName("tileShapeProperty")
        get() = getTileShape()
        @JvmName("setTileShapeProperty")
        set(value) = setTileShape(value)

    var tileLayout: Long
        @JvmName("tileLayoutProperty")
        get() = getTileLayout()
        @JvmName("setTileLayoutProperty")
        set(value) = setTileLayout(value)

    var tileOffsetAxis: Long
        @JvmName("tileOffsetAxisProperty")
        get() = getTileOffsetAxis()
        @JvmName("setTileOffsetAxisProperty")
        set(value) = setTileOffsetAxis(value)

    var tileSize: Vector2i
        @JvmName("tileSizeProperty")
        get() = getTileSize()
        @JvmName("setTileSizeProperty")
        set(value) = setTileSize(value)

    var uvClipping: Boolean
        @JvmName("uvClippingProperty")
        get() = isUvClipping()
        @JvmName("setUvClippingProperty")
        set(value) = setUvClipping(value)

    /**
     * Returns a new unused source ID. This generated ID is the same that a call to `add_source` would
     * return.
     *
     * Generated from Godot docs: TileSet.get_next_source_id
     */
    fun getNextSourceId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNextSourceIdBind, handle)
    }

    /**
     * Adds a `TileSetSource` to the TileSet. If `atlas_source_id_override` is not -1, also set its
     * source ID. Otherwise, a unique identifier is automatically generated. The function returns the
     * added source ID or -1 if the source could not be added. Warning: A source cannot belong to two
     * TileSets at the same time. If the added source was attached to another `TileSet`, it will be
     * removed from that one.
     *
     * Generated from Godot docs: TileSet.add_source
     */
    fun addSource(source: TileSetSource?, atlasSourceIdOverride: Int = -1): Int {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(addSourceBind, handle, source?.requireOpenHandle() ?: MemorySegment.NULL, atlasSourceIdOverride)
    }

    /**
     * Removes the source with the given source ID.
     *
     * Generated from Godot docs: TileSet.remove_source
     */
    fun removeSource(sourceId: Int) {
        ObjectCalls.ptrcallWithIntArg(removeSourceBind, handle, sourceId)
    }

    /**
     * Changes a source's ID.
     *
     * Generated from Godot docs: TileSet.set_source_id
     */
    fun setSourceId(sourceId: Int, newSourceId: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSourceIdBind, handle, sourceId, newSourceId)
    }

    /**
     * Returns the number of `TileSetSource` in this TileSet.
     *
     * Generated from Godot docs: TileSet.get_source_count
     */
    fun getSourceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSourceCountBind, handle)
    }

    /**
     * Returns the source ID for source with index `index`.
     *
     * Generated from Godot docs: TileSet.get_source_id
     */
    fun getSourceId(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSourceIdBind, handle, index)
    }

    /**
     * Returns if this TileSet has a source for the given source ID.
     *
     * Generated from Godot docs: TileSet.has_source
     */
    fun hasSource(sourceId: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSourceBind, handle, sourceId)
    }

    /**
     * Returns the `TileSetSource` with ID `source_id`.
     *
     * Generated from Godot docs: TileSet.get_source
     */
    fun getSource(sourceId: Int): TileSetSource? {
        return TileSetSource.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSourceBind, handle, sourceId))
    }

    /**
     * The tile shape.
     *
     * Generated from Godot docs: TileSet.set_tile_shape
     */
    fun setTileShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileShapeBind, handle, shape)
    }

    /**
     * The tile shape.
     *
     * Generated from Godot docs: TileSet.get_tile_shape
     */
    fun getTileShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileShapeBind, handle)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), changes the way tiles
     * are indexed in the `TileMapLayer` grid.
     *
     * Generated from Godot docs: TileSet.set_tile_layout
     */
    fun setTileLayout(layout: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileLayoutBind, handle, layout)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), changes the way tiles
     * are indexed in the `TileMapLayer` grid.
     *
     * Generated from Godot docs: TileSet.get_tile_layout
     */
    fun getTileLayout(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileLayoutBind, handle)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), determines the offset
     * axis.
     *
     * Generated from Godot docs: TileSet.set_tile_offset_axis
     */
    fun setTileOffsetAxis(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileOffsetAxisBind, handle, alignment)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), determines the offset
     * axis.
     *
     * Generated from Godot docs: TileSet.get_tile_offset_axis
     */
    fun getTileOffsetAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileOffsetAxisBind, handle)
    }

    /**
     * The tile size, in pixels. For all tile shapes, this size corresponds to the encompassing
     * rectangle of the tile shape. This is thus the minimal cell size required in an atlas.
     *
     * Generated from Godot docs: TileSet.set_tile_size
     */
    fun setTileSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTileSizeBind, handle, size)
    }

    /**
     * The tile size, in pixels. For all tile shapes, this size corresponds to the encompassing
     * rectangle of the tile shape. This is thus the minimal cell size required in an atlas.
     *
     * Generated from Godot docs: TileSet.get_tile_size
     */
    fun getTileSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTileSizeBind, handle)
    }

    /**
     * Enables/Disable uv clipping when rendering the tiles.
     *
     * Generated from Godot docs: TileSet.set_uv_clipping
     */
    fun setUvClipping(uvClipping: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUvClippingBind, handle, uvClipping)
    }

    /**
     * Enables/Disable uv clipping when rendering the tiles.
     *
     * Generated from Godot docs: TileSet.is_uv_clipping
     */
    fun isUvClipping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUvClippingBind, handle)
    }

    /**
     * Returns the occlusion layers count.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layers_count
     */
    fun getOcclusionLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOcclusionLayersCountBind, handle)
    }

    /**
     * Adds an occlusion layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Occlusion layers allow assigning occlusion
     * polygons to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_occlusion_layer
     */
    fun addOcclusionLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addOcclusionLayerBind, handle, toPosition)
    }

    /**
     * Moves the occlusion layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_occlusion_layer
     */
    fun moveOcclusionLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveOcclusionLayerBind, handle, layerIndex, toPosition)
    }

    /**
     * Removes the occlusion layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_occlusion_layer
     */
    fun removeOcclusionLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeOcclusionLayerBind, handle, layerIndex)
    }

    /**
     * Sets the occlusion layer (as in the rendering server) for occluders in the given TileSet
     * occlusion layer.
     *
     * Generated from Godot docs: TileSet.set_occlusion_layer_light_mask
     */
    fun setOcclusionLayerLightMask(layerIndex: Int, lightMask: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOcclusionLayerLightMaskBind, handle, layerIndex, lightMask)
    }

    /**
     * Returns the light mask of the occlusion layer.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layer_light_mask
     */
    fun getOcclusionLayerLightMask(layerIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOcclusionLayerLightMaskBind, handle, layerIndex)
    }

    /**
     * Enables or disables SDF collision for occluders in the given TileSet occlusion layer.
     *
     * Generated from Godot docs: TileSet.set_occlusion_layer_sdf_collision
     */
    fun setOcclusionLayerSdfCollision(layerIndex: Int, sdfCollision: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setOcclusionLayerSdfCollisionBind, handle, layerIndex, sdfCollision)
    }

    /**
     * Returns if the occluders from this layer use `sdf_collision`.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layer_sdf_collision
     */
    fun getOcclusionLayerSdfCollision(layerIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getOcclusionLayerSdfCollisionBind, handle, layerIndex)
    }

    /**
     * Returns the physics layers count.
     *
     * Generated from Godot docs: TileSet.get_physics_layers_count
     */
    fun getPhysicsLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsLayersCountBind, handle)
    }

    /**
     * Adds a physics layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Physics layers allow assigning collision
     * polygons to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_physics_layer
     */
    fun addPhysicsLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addPhysicsLayerBind, handle, toPosition)
    }

    /**
     * Moves the physics layer at index `layer_index` to the given position `to_position` in the array.
     * Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_physics_layer
     */
    fun movePhysicsLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(movePhysicsLayerBind, handle, layerIndex, toPosition)
    }

    /**
     * Removes the physics layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_physics_layer
     */
    fun removePhysicsLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removePhysicsLayerBind, handle, layerIndex)
    }

    /**
     * Sets the collision layer (as in the physics server) for bodies in the given TileSet physics
     * layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_layer
     */
    fun setPhysicsLayerCollisionLayer(layerIndex: Int, layer: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setPhysicsLayerCollisionLayerBind, handle, layerIndex, layer)
    }

    /**
     * Returns the collision layer (as in the physics server) bodies on the given TileSet's physics
     * layer are in.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_layer
     */
    fun getPhysicsLayerCollisionLayer(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getPhysicsLayerCollisionLayerBind, handle, layerIndex)
    }

    /**
     * Sets the collision mask for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_mask
     */
    fun setPhysicsLayerCollisionMask(layerIndex: Int, mask: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setPhysicsLayerCollisionMaskBind, handle, layerIndex, mask)
    }

    /**
     * Returns the collision mask of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_mask
     */
    fun getPhysicsLayerCollisionMask(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getPhysicsLayerCollisionMaskBind, handle, layerIndex)
    }

    /**
     * Sets the collision priority for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_priority
     */
    fun setPhysicsLayerCollisionPriority(layerIndex: Int, priority: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPhysicsLayerCollisionPriorityBind, handle, layerIndex, priority)
    }

    /**
     * Returns the collision priority of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_priority
     */
    fun getPhysicsLayerCollisionPriority(layerIndex: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPhysicsLayerCollisionPriorityBind, handle, layerIndex)
    }

    /**
     * Sets the physics material for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_physics_material
     */
    fun setPhysicsLayerPhysicsMaterial(layerIndex: Int, physicsMaterial: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setPhysicsLayerPhysicsMaterialBind, handle, layerIndex, physicsMaterial?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the physics material of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_physics_material
     */
    fun getPhysicsLayerPhysicsMaterial(layerIndex: Int): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPhysicsLayerPhysicsMaterialBind, handle, layerIndex))
    }

    /**
     * Returns the terrain sets count.
     *
     * Generated from Godot docs: TileSet.get_terrain_sets_count
     */
    fun getTerrainSetsCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTerrainSetsCountBind, handle)
    }

    /**
     * Adds a new terrain set at the given position `to_position` in the array. If `to_position` is -1,
     * adds it at the end of the array.
     *
     * Generated from Godot docs: TileSet.add_terrain_set
     */
    fun addTerrainSet(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addTerrainSetBind, handle, toPosition)
    }

    /**
     * Moves the terrain set at index `terrain_set` to the given position `to_position` in the array.
     * Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_terrain_set
     */
    fun moveTerrainSet(terrainSet: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveTerrainSetBind, handle, terrainSet, toPosition)
    }

    /**
     * Removes the terrain set at index `terrain_set`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_terrain_set
     */
    fun removeTerrainSet(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTerrainSetBind, handle, terrainSet)
    }

    /**
     * Sets a terrain mode. Each mode determines which bits of a tile shape is used to match the
     * neighboring tiles' terrains.
     *
     * Generated from Godot docs: TileSet.set_terrain_set_mode
     */
    fun setTerrainSetMode(terrainSet: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTerrainSetModeBind, handle, terrainSet, mode)
    }

    /**
     * Returns a terrain set mode.
     *
     * Generated from Godot docs: TileSet.get_terrain_set_mode
     */
    fun getTerrainSetMode(terrainSet: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTerrainSetModeBind, handle, terrainSet)
    }

    /**
     * Returns the number of terrains in the given terrain set.
     *
     * Generated from Godot docs: TileSet.get_terrains_count
     */
    fun getTerrainsCount(terrainSet: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTerrainsCountBind, handle, terrainSet)
    }

    /**
     * Adds a new terrain to the given terrain set `terrain_set` at the given position `to_position` in
     * the array. If `to_position` is -1, adds it at the end of the array.
     *
     * Generated from Godot docs: TileSet.add_terrain
     */
    fun addTerrain(terrainSet: Int, toPosition: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(addTerrainBind, handle, terrainSet, toPosition)
    }

    /**
     * Moves the terrain at index `terrain_index` for terrain set `terrain_set` to the given position
     * `to_position` in the array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_terrain
     */
    fun moveTerrain(terrainSet: Int, terrainIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithThreeIntArgs(moveTerrainBind, handle, terrainSet, terrainIndex, toPosition)
    }

    /**
     * Removes the terrain at index `terrain_index` in the given terrain set `terrain_set`. Also
     * updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_terrain
     */
    fun removeTerrain(terrainSet: Int, terrainIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(removeTerrainBind, handle, terrainSet, terrainIndex)
    }

    /**
     * Clears all terrain properties for the given terrain set.
     *
     * Generated from Godot docs: TileSet.clear_terrains
     */
    fun clearTerrains(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(clearTerrainsBind, handle, terrainSet)
    }

    /**
     * Sets a terrain's name.
     *
     * Generated from Godot docs: TileSet.set_terrain_name
     */
    fun setTerrainName(terrainSet: Int, terrainIndex: Int, name: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(setTerrainNameBind, handle, terrainSet, terrainIndex, name)
    }

    /**
     * Returns a terrain's name.
     *
     * Generated from Godot docs: TileSet.get_terrain_name
     */
    fun getTerrainName(terrainSet: Int, terrainIndex: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getTerrainNameBind, handle, terrainSet, terrainIndex)
    }

    /**
     * Sets a terrain's color. This color is used for identifying the different terrains in the TileSet
     * editor.
     *
     * Generated from Godot docs: TileSet.set_terrain_color
     */
    fun setTerrainColor(terrainSet: Int, terrainIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setTerrainColorBind, handle, terrainSet, terrainIndex, color)
    }

    /**
     * Returns a terrain's color.
     *
     * Generated from Godot docs: TileSet.get_terrain_color
     */
    fun getTerrainColor(terrainSet: Int, terrainIndex: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getTerrainColorBind, handle, terrainSet, terrainIndex)
    }

    /**
     * Returns the navigation layers count.
     *
     * Generated from Godot docs: TileSet.get_navigation_layers_count
     */
    fun getNavigationLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNavigationLayersCountBind, handle)
    }

    /**
     * Adds a navigation layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Navigation layers allow assigning a
     * navigable area to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_navigation_layer
     */
    fun addNavigationLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addNavigationLayerBind, handle, toPosition)
    }

    /**
     * Moves the navigation layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_navigation_layer
     */
    fun moveNavigationLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveNavigationLayerBind, handle, layerIndex, toPosition)
    }

    /**
     * Removes the navigation layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_navigation_layer
     */
    fun removeNavigationLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeNavigationLayerBind, handle, layerIndex)
    }

    /**
     * Sets the navigation layers (as in the navigation server) for navigation regions in the given
     * TileSet navigation layer.
     *
     * Generated from Godot docs: TileSet.set_navigation_layer_layers
     */
    fun setNavigationLayerLayers(layerIndex: Int, layers: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setNavigationLayerLayersBind, handle, layerIndex, layers)
    }

    /**
     * Returns the navigation layers (as in the Navigation server) of the given TileSet navigation
     * layer.
     *
     * Generated from Godot docs: TileSet.get_navigation_layer_layers
     */
    fun getNavigationLayerLayers(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getNavigationLayerLayersBind, handle, layerIndex)
    }

    /**
     * Based on `value`, enables or disables the specified navigation layer of the TileSet navigation
     * data layer identified by the given `layer_index`, given a navigation_layers `layer_number`
     * between 1 and 32.
     *
     * Generated from Godot docs: TileSet.set_navigation_layer_layer_value
     */
    fun setNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setNavigationLayerLayerValueBind, handle, layerIndex, layerNumber, value)
    }

    /**
     * Returns whether or not the specified navigation layer of the TileSet navigation data layer
     * identified by the given `layer_index` is enabled, given a navigation_layers `layer_number`
     * between 1 and 32.
     *
     * Generated from Godot docs: TileSet.get_navigation_layer_layer_value
     */
    fun getNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(getNavigationLayerLayerValueBind, handle, layerIndex, layerNumber)
    }

    /**
     * Returns the custom data layers count.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layers_count
     */
    fun getCustomDataLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCustomDataLayersCountBind, handle)
    }

    /**
     * Adds a custom data layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Custom data layers allow assigning custom
     * properties to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_custom_data_layer
     */
    fun addCustomDataLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addCustomDataLayerBind, handle, toPosition)
    }

    /**
     * Moves the custom data layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_custom_data_layer
     */
    fun moveCustomDataLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveCustomDataLayerBind, handle, layerIndex, toPosition)
    }

    /**
     * Removes the custom data layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_custom_data_layer
     */
    fun removeCustomDataLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeCustomDataLayerBind, handle, layerIndex)
    }

    /**
     * Returns the index of the custom data layer identified by the given name.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_by_name
     */
    fun getCustomDataLayerByName(layerName: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(getCustomDataLayerByNameBind, handle, layerName)
    }

    /**
     * Sets the name of the custom data layer identified by the given index. Names are identifiers of
     * the layer therefore if the name is already taken it will fail and raise an error.
     *
     * Generated from Godot docs: TileSet.set_custom_data_layer_name
     */
    fun setCustomDataLayerName(layerIndex: Int, layerName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setCustomDataLayerNameBind, handle, layerIndex, layerName)
    }

    /**
     * Returns if there is a custom data layer named `layer_name`.
     *
     * Generated from Godot docs: TileSet.has_custom_data_layer_by_name
     */
    fun hasCustomDataLayerByName(layerName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasCustomDataLayerByNameBind, handle, layerName)
    }

    /**
     * Returns the name of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_name
     */
    fun getCustomDataLayerName(layerIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getCustomDataLayerNameBind, handle, layerIndex)
    }

    /**
     * Sets the type of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.set_custom_data_layer_type
     */
    fun setCustomDataLayerType(layerIndex: Int, layerType: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCustomDataLayerTypeBind, handle, layerIndex, layerType)
    }

    /**
     * Returns the type of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_type
     */
    fun getCustomDataLayerType(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCustomDataLayerTypeBind, handle, layerIndex)
    }

    /**
     * Creates a source-level proxy for the given source ID. A proxy will map set of tile identifiers
     * to another set of identifiers. Both the atlas coordinates ID and the alternative tile ID are
     * kept the same when using source-level proxies. Proxied tiles can be automatically replaced in
     * TileMapLayer nodes using the editor.
     *
     * Generated from Godot docs: TileSet.set_source_level_tile_proxy
     */
    fun setSourceLevelTileProxy(sourceFrom: Int, sourceTo: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSourceLevelTileProxyBind, handle, sourceFrom, sourceTo)
    }

    /**
     * Returns the source-level proxy for the given source identifier. If the TileSet has no proxy for
     * the given identifier, returns -1.
     *
     * Generated from Godot docs: TileSet.get_source_level_tile_proxy
     */
    fun getSourceLevelTileProxy(sourceFrom: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSourceLevelTileProxyBind, handle, sourceFrom)
    }

    /**
     * Returns if there is a source-level proxy for the given source ID.
     *
     * Generated from Godot docs: TileSet.has_source_level_tile_proxy
     */
    fun hasSourceLevelTileProxy(sourceFrom: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSourceLevelTileProxyBind, handle, sourceFrom)
    }

    /**
     * Removes a source-level tile proxy.
     *
     * Generated from Godot docs: TileSet.remove_source_level_tile_proxy
     */
    fun removeSourceLevelTileProxy(sourceFrom: Int) {
        ObjectCalls.ptrcallWithIntArg(removeSourceLevelTileProxyBind, handle, sourceFrom)
    }

    /**
     * Creates a coordinates-level proxy for the given identifiers. A proxy will map set of tile
     * identifiers to another set of identifiers. The alternative tile ID is kept the same when using
     * coordinates-level proxies. Proxied tiles can be automatically replaced in TileMapLayer nodes
     * using the editor.
     *
     * Generated from Godot docs: TileSet.set_coords_level_tile_proxy
     */
    fun setCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, sourceTo: Int, coordsTo: Vector2i) {
        ObjectCalls.ptrcallWithIntVector2iIntVector2iArgs(setCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom, sourceTo, coordsTo)
    }

    /**
     * Returns the coordinate-level proxy for the given identifiers. The returned array contains the
     * two target identifiers of the proxy (source ID and atlas coordinates ID). If the TileSet has no
     * proxy for the given identifiers, returns an empty Array.
     *
     * Generated from Godot docs: TileSet.get_coords_level_tile_proxy
     */
    fun getCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iArgsRetArray(getCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    /**
     * Returns if there is a coodinates-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.has_coords_level_tile_proxy
     */
    fun hasCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithIntAndVector2iArgRetBool(hasCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    /**
     * Removes a coordinates-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.remove_coords_level_tile_proxy
     */
    fun removeCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i) {
        ObjectCalls.ptrcallWithIntAndVector2iArg(removeCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    /**
     * Create an alternative-level proxy for the given identifiers. A proxy will map set of tile
     * identifiers to another set of identifiers. Proxied tiles can be automatically replaced in
     * TileMapLayer nodes using the editor.
     *
     * Generated from Godot docs: TileSet.set_alternative_level_tile_proxy
     */
    fun setAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int, sourceTo: Int, coordsTo: Vector2i, alternativeTo: Int) {
        ObjectCalls.ptrcallWithIntVector2iTwoIntVector2iIntArgs(setAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom, sourceTo, coordsTo, alternativeTo)
    }

    /**
     * Returns the alternative-level proxy for the given identifiers. The returned array contains the
     * three proxie's target identifiers (source ID, atlas coords ID and alternative tile ID). If the
     * TileSet has no proxy for the given identifiers, returns an empty Array.
     *
     * Generated from Godot docs: TileSet.get_alternative_level_tile_proxy
     */
    fun getAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(getAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Returns if there is an alternative-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.has_alternative_level_tile_proxy
     */
    fun hasAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetBool(hasAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Removes an alternative-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.remove_alternative_level_tile_proxy
     */
    fun removeAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int) {
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(removeAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * According to the configured proxies, maps the provided identifiers to a new set of identifiers.
     * The source ID, atlas coordinates ID and alternative tile ID are returned as a 3 elements Array.
     * This function first look for matching alternative-level proxies, then coordinates-level proxies,
     * then source-level proxies. If no proxy corresponding to provided identifiers are found, returns
     * the same values the ones used as arguments.
     *
     * Generated from Godot docs: TileSet.map_tile_proxy
     */
    fun mapTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(mapTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Clears tile proxies pointing to invalid tiles.
     *
     * Generated from Godot docs: TileSet.cleanup_invalid_tile_proxies
     */
    fun cleanupInvalidTileProxies() {
        ObjectCalls.ptrcallNoArgs(cleanupInvalidTileProxiesBind, handle)
    }

    /**
     * Clears all tile proxies.
     *
     * Generated from Godot docs: TileSet.clear_tile_proxies
     */
    fun clearTileProxies() {
        ObjectCalls.ptrcallNoArgs(clearTileProxiesBind, handle)
    }

    /**
     * Adds a `TileMapPattern` to be stored in the TileSet resource. If provided, insert it at the
     * given `index`.
     *
     * Generated from Godot docs: TileSet.add_pattern
     */
    fun addPattern(pattern: TileMapPattern?, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(addPatternBind, handle, pattern?.requireOpenHandle() ?: MemorySegment.NULL, index)
    }

    /**
     * Returns the `TileMapPattern` at the given `index`.
     *
     * Generated from Godot docs: TileSet.get_pattern
     */
    fun getPattern(index: Int = -1): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPatternBind, handle, index))
    }

    /**
     * Remove the `TileMapPattern` at the given index.
     *
     * Generated from Godot docs: TileSet.remove_pattern
     */
    fun removePattern(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removePatternBind, handle, index)
    }

    /**
     * Returns the number of `TileMapPattern` this tile set handles.
     *
     * Generated from Godot docs: TileSet.get_patterns_count
     */
    fun getPatternsCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPatternsCountBind, handle)
    }

    companion object {
        const val TILE_SHAPE_SQUARE: Long = 0L
        const val TILE_SHAPE_ISOMETRIC: Long = 1L
        const val TILE_SHAPE_HALF_OFFSET_SQUARE: Long = 2L
        const val TILE_SHAPE_HEXAGON: Long = 3L
        const val TILE_LAYOUT_STACKED: Long = 0L
        const val TILE_LAYOUT_STACKED_OFFSET: Long = 1L
        const val TILE_LAYOUT_STAIRS_RIGHT: Long = 2L
        const val TILE_LAYOUT_STAIRS_DOWN: Long = 3L
        const val TILE_LAYOUT_DIAMOND_RIGHT: Long = 4L
        const val TILE_LAYOUT_DIAMOND_DOWN: Long = 5L
        const val TILE_OFFSET_AXIS_HORIZONTAL: Long = 0L
        const val TILE_OFFSET_AXIS_VERTICAL: Long = 1L
        const val CELL_NEIGHBOR_RIGHT_SIDE: Long = 0L
        const val CELL_NEIGHBOR_RIGHT_CORNER: Long = 1L
        const val CELL_NEIGHBOR_BOTTOM_RIGHT_SIDE: Long = 2L
        const val CELL_NEIGHBOR_BOTTOM_RIGHT_CORNER: Long = 3L
        const val CELL_NEIGHBOR_BOTTOM_SIDE: Long = 4L
        const val CELL_NEIGHBOR_BOTTOM_CORNER: Long = 5L
        const val CELL_NEIGHBOR_BOTTOM_LEFT_SIDE: Long = 6L
        const val CELL_NEIGHBOR_BOTTOM_LEFT_CORNER: Long = 7L
        const val CELL_NEIGHBOR_LEFT_SIDE: Long = 8L
        const val CELL_NEIGHBOR_LEFT_CORNER: Long = 9L
        const val CELL_NEIGHBOR_TOP_LEFT_SIDE: Long = 10L
        const val CELL_NEIGHBOR_TOP_LEFT_CORNER: Long = 11L
        const val CELL_NEIGHBOR_TOP_SIDE: Long = 12L
        const val CELL_NEIGHBOR_TOP_CORNER: Long = 13L
        const val CELL_NEIGHBOR_TOP_RIGHT_SIDE: Long = 14L
        const val CELL_NEIGHBOR_TOP_RIGHT_CORNER: Long = 15L
        const val TERRAIN_MODE_MATCH_CORNERS_AND_SIDES: Long = 0L
        const val TERRAIN_MODE_MATCH_CORNERS: Long = 1L
        const val TERRAIN_MODE_MATCH_SIDES: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileSet? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileSet? =
            if (handle.address() == 0L) null else TileSet(handle)

        private const val GET_NEXT_SOURCE_ID_HASH = 3905245786L
        private val getNextSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_next_source_id", GET_NEXT_SOURCE_ID_HASH)
        }

        private const val ADD_SOURCE_HASH = 1059186179L
        private val addSourceBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_source", ADD_SOURCE_HASH)
        }

        private const val REMOVE_SOURCE_HASH = 1286410249L
        private val removeSourceBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_source", REMOVE_SOURCE_HASH)
        }

        private const val SET_SOURCE_ID_HASH = 3937882851L
        private val setSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_source_id", SET_SOURCE_ID_HASH)
        }

        private const val GET_SOURCE_COUNT_HASH = 3905245786L
        private val getSourceCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_source_count", GET_SOURCE_COUNT_HASH)
        }

        private const val GET_SOURCE_ID_HASH = 923996154L
        private val getSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_source_id", GET_SOURCE_ID_HASH)
        }

        private const val HAS_SOURCE_HASH = 1116898809L
        private val hasSourceBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "has_source", HAS_SOURCE_HASH)
        }

        private const val GET_SOURCE_HASH = 1763540252L
        private val getSourceBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_source", GET_SOURCE_HASH)
        }

        private const val SET_TILE_SHAPE_HASH = 2131427112L
        private val setTileShapeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_tile_shape", SET_TILE_SHAPE_HASH)
        }

        private const val GET_TILE_SHAPE_HASH = 716918169L
        private val getTileShapeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_tile_shape", GET_TILE_SHAPE_HASH)
        }

        private const val SET_TILE_LAYOUT_HASH = 1071216679L
        private val setTileLayoutBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_tile_layout", SET_TILE_LAYOUT_HASH)
        }

        private const val GET_TILE_LAYOUT_HASH = 194628839L
        private val getTileLayoutBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_tile_layout", GET_TILE_LAYOUT_HASH)
        }

        private const val SET_TILE_OFFSET_AXIS_HASH = 3300198521L
        private val setTileOffsetAxisBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_tile_offset_axis", SET_TILE_OFFSET_AXIS_HASH)
        }

        private const val GET_TILE_OFFSET_AXIS_HASH = 762494114L
        private val getTileOffsetAxisBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_tile_offset_axis", GET_TILE_OFFSET_AXIS_HASH)
        }

        private const val SET_TILE_SIZE_HASH = 1130785943L
        private val setTileSizeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_tile_size", SET_TILE_SIZE_HASH)
        }

        private const val GET_TILE_SIZE_HASH = 3690982128L
        private val getTileSizeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_tile_size", GET_TILE_SIZE_HASH)
        }

        private const val SET_UV_CLIPPING_HASH = 2586408642L
        private val setUvClippingBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_uv_clipping", SET_UV_CLIPPING_HASH)
        }

        private const val IS_UV_CLIPPING_HASH = 36873697L
        private val isUvClippingBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "is_uv_clipping", IS_UV_CLIPPING_HASH)
        }

        private const val GET_OCCLUSION_LAYERS_COUNT_HASH = 3905245786L
        private val getOcclusionLayersCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layers_count", GET_OCCLUSION_LAYERS_COUNT_HASH)
        }

        private const val ADD_OCCLUSION_LAYER_HASH = 1025054187L
        private val addOcclusionLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_occlusion_layer", ADD_OCCLUSION_LAYER_HASH)
        }

        private const val MOVE_OCCLUSION_LAYER_HASH = 3937882851L
        private val moveOcclusionLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_occlusion_layer", MOVE_OCCLUSION_LAYER_HASH)
        }

        private const val REMOVE_OCCLUSION_LAYER_HASH = 1286410249L
        private val removeOcclusionLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_occlusion_layer", REMOVE_OCCLUSION_LAYER_HASH)
        }

        private const val SET_OCCLUSION_LAYER_LIGHT_MASK_HASH = 3937882851L
        private val setOcclusionLayerLightMaskBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_occlusion_layer_light_mask", SET_OCCLUSION_LAYER_LIGHT_MASK_HASH)
        }

        private const val GET_OCCLUSION_LAYER_LIGHT_MASK_HASH = 923996154L
        private val getOcclusionLayerLightMaskBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layer_light_mask", GET_OCCLUSION_LAYER_LIGHT_MASK_HASH)
        }

        private const val SET_OCCLUSION_LAYER_SDF_COLLISION_HASH = 300928843L
        private val setOcclusionLayerSdfCollisionBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_occlusion_layer_sdf_collision", SET_OCCLUSION_LAYER_SDF_COLLISION_HASH)
        }

        private const val GET_OCCLUSION_LAYER_SDF_COLLISION_HASH = 1116898809L
        private val getOcclusionLayerSdfCollisionBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layer_sdf_collision", GET_OCCLUSION_LAYER_SDF_COLLISION_HASH)
        }

        private const val GET_PHYSICS_LAYERS_COUNT_HASH = 3905245786L
        private val getPhysicsLayersCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_physics_layers_count", GET_PHYSICS_LAYERS_COUNT_HASH)
        }

        private const val ADD_PHYSICS_LAYER_HASH = 1025054187L
        private val addPhysicsLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_physics_layer", ADD_PHYSICS_LAYER_HASH)
        }

        private const val MOVE_PHYSICS_LAYER_HASH = 3937882851L
        private val movePhysicsLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_physics_layer", MOVE_PHYSICS_LAYER_HASH)
        }

        private const val REMOVE_PHYSICS_LAYER_HASH = 1286410249L
        private val removePhysicsLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_physics_layer", REMOVE_PHYSICS_LAYER_HASH)
        }

        private const val SET_PHYSICS_LAYER_COLLISION_LAYER_HASH = 3937882851L
        private val setPhysicsLayerCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_layer", SET_PHYSICS_LAYER_COLLISION_LAYER_HASH)
        }

        private const val GET_PHYSICS_LAYER_COLLISION_LAYER_HASH = 923996154L
        private val getPhysicsLayerCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_layer", GET_PHYSICS_LAYER_COLLISION_LAYER_HASH)
        }

        private const val SET_PHYSICS_LAYER_COLLISION_MASK_HASH = 3937882851L
        private val setPhysicsLayerCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_mask", SET_PHYSICS_LAYER_COLLISION_MASK_HASH)
        }

        private const val GET_PHYSICS_LAYER_COLLISION_MASK_HASH = 923996154L
        private val getPhysicsLayerCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_mask", GET_PHYSICS_LAYER_COLLISION_MASK_HASH)
        }

        private const val SET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH = 1602489585L
        private val setPhysicsLayerCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_priority", SET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH)
        }

        private const val GET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH = 2339986948L
        private val getPhysicsLayerCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_priority", GET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH)
        }

        private const val SET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH = 1018687357L
        private val setPhysicsLayerPhysicsMaterialBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_physics_material", SET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH)
        }

        private const val GET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH = 788318639L
        private val getPhysicsLayerPhysicsMaterialBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_physics_material", GET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH)
        }

        private const val GET_TERRAIN_SETS_COUNT_HASH = 3905245786L
        private val getTerrainSetsCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_terrain_sets_count", GET_TERRAIN_SETS_COUNT_HASH)
        }

        private const val ADD_TERRAIN_SET_HASH = 1025054187L
        private val addTerrainSetBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_terrain_set", ADD_TERRAIN_SET_HASH)
        }

        private const val MOVE_TERRAIN_SET_HASH = 3937882851L
        private val moveTerrainSetBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_terrain_set", MOVE_TERRAIN_SET_HASH)
        }

        private const val REMOVE_TERRAIN_SET_HASH = 1286410249L
        private val removeTerrainSetBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_terrain_set", REMOVE_TERRAIN_SET_HASH)
        }

        private const val SET_TERRAIN_SET_MODE_HASH = 3943003916L
        private val setTerrainSetModeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_terrain_set_mode", SET_TERRAIN_SET_MODE_HASH)
        }

        private const val GET_TERRAIN_SET_MODE_HASH = 2084469411L
        private val getTerrainSetModeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_terrain_set_mode", GET_TERRAIN_SET_MODE_HASH)
        }

        private const val GET_TERRAINS_COUNT_HASH = 923996154L
        private val getTerrainsCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_terrains_count", GET_TERRAINS_COUNT_HASH)
        }

        private const val ADD_TERRAIN_HASH = 1230568737L
        private val addTerrainBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_terrain", ADD_TERRAIN_HASH)
        }

        private const val MOVE_TERRAIN_HASH = 1649997291L
        private val moveTerrainBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_terrain", MOVE_TERRAIN_HASH)
        }

        private const val REMOVE_TERRAIN_HASH = 3937882851L
        private val removeTerrainBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_terrain", REMOVE_TERRAIN_HASH)
        }

        private const val CLEAR_TERRAINS_HASH = 1286410249L
        private val clearTerrainsBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "clear_terrains", CLEAR_TERRAINS_HASH)
        }

        private const val SET_TERRAIN_NAME_HASH = 2285447957L
        private val setTerrainNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_terrain_name", SET_TERRAIN_NAME_HASH)
        }

        private const val GET_TERRAIN_NAME_HASH = 1391810591L
        private val getTerrainNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_terrain_name", GET_TERRAIN_NAME_HASH)
        }

        private const val SET_TERRAIN_COLOR_HASH = 3733378741L
        private val setTerrainColorBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_terrain_color", SET_TERRAIN_COLOR_HASH)
        }

        private const val GET_TERRAIN_COLOR_HASH = 2165839948L
        private val getTerrainColorBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_terrain_color", GET_TERRAIN_COLOR_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_COUNT_HASH = 3905245786L
        private val getNavigationLayersCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layers_count", GET_NAVIGATION_LAYERS_COUNT_HASH)
        }

        private const val ADD_NAVIGATION_LAYER_HASH = 1025054187L
        private val addNavigationLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_navigation_layer", ADD_NAVIGATION_LAYER_HASH)
        }

        private const val MOVE_NAVIGATION_LAYER_HASH = 3937882851L
        private val moveNavigationLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_navigation_layer", MOVE_NAVIGATION_LAYER_HASH)
        }

        private const val REMOVE_NAVIGATION_LAYER_HASH = 1286410249L
        private val removeNavigationLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_navigation_layer", REMOVE_NAVIGATION_LAYER_HASH)
        }

        private const val SET_NAVIGATION_LAYER_LAYERS_HASH = 3937882851L
        private val setNavigationLayerLayersBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_navigation_layer_layers", SET_NAVIGATION_LAYER_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYER_LAYERS_HASH = 923996154L
        private val getNavigationLayerLayersBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layer_layers", GET_NAVIGATION_LAYER_LAYERS_HASH)
        }

        private const val SET_NAVIGATION_LAYER_LAYER_VALUE_HASH = 1383440665L
        private val setNavigationLayerLayerValueBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_navigation_layer_layer_value", SET_NAVIGATION_LAYER_LAYER_VALUE_HASH)
        }

        private const val GET_NAVIGATION_LAYER_LAYER_VALUE_HASH = 2522259332L
        private val getNavigationLayerLayerValueBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layer_layer_value", GET_NAVIGATION_LAYER_LAYER_VALUE_HASH)
        }

        private const val GET_CUSTOM_DATA_LAYERS_COUNT_HASH = 3905245786L
        private val getCustomDataLayersCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layers_count", GET_CUSTOM_DATA_LAYERS_COUNT_HASH)
        }

        private const val ADD_CUSTOM_DATA_LAYER_HASH = 1025054187L
        private val addCustomDataLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_custom_data_layer", ADD_CUSTOM_DATA_LAYER_HASH)
        }

        private const val MOVE_CUSTOM_DATA_LAYER_HASH = 3937882851L
        private val moveCustomDataLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "move_custom_data_layer", MOVE_CUSTOM_DATA_LAYER_HASH)
        }

        private const val REMOVE_CUSTOM_DATA_LAYER_HASH = 1286410249L
        private val removeCustomDataLayerBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_custom_data_layer", REMOVE_CUSTOM_DATA_LAYER_HASH)
        }

        private const val GET_CUSTOM_DATA_LAYER_BY_NAME_HASH = 1321353865L
        private val getCustomDataLayerByNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_by_name", GET_CUSTOM_DATA_LAYER_BY_NAME_HASH)
        }

        private const val SET_CUSTOM_DATA_LAYER_NAME_HASH = 501894301L
        private val setCustomDataLayerNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_custom_data_layer_name", SET_CUSTOM_DATA_LAYER_NAME_HASH)
        }

        private const val HAS_CUSTOM_DATA_LAYER_BY_NAME_HASH = 3927539163L
        private val hasCustomDataLayerByNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "has_custom_data_layer_by_name", HAS_CUSTOM_DATA_LAYER_BY_NAME_HASH)
        }

        private const val GET_CUSTOM_DATA_LAYER_NAME_HASH = 844755477L
        private val getCustomDataLayerNameBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_name", GET_CUSTOM_DATA_LAYER_NAME_HASH)
        }

        private const val SET_CUSTOM_DATA_LAYER_TYPE_HASH = 3492912874L
        private val setCustomDataLayerTypeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_custom_data_layer_type", SET_CUSTOM_DATA_LAYER_TYPE_HASH)
        }

        private const val GET_CUSTOM_DATA_LAYER_TYPE_HASH = 2990820875L
        private val getCustomDataLayerTypeBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_type", GET_CUSTOM_DATA_LAYER_TYPE_HASH)
        }

        private const val SET_SOURCE_LEVEL_TILE_PROXY_HASH = 3937882851L
        private val setSourceLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_source_level_tile_proxy", SET_SOURCE_LEVEL_TILE_PROXY_HASH)
        }

        private const val GET_SOURCE_LEVEL_TILE_PROXY_HASH = 3744713108L
        private val getSourceLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_source_level_tile_proxy", GET_SOURCE_LEVEL_TILE_PROXY_HASH)
        }

        private const val HAS_SOURCE_LEVEL_TILE_PROXY_HASH = 3067735520L
        private val hasSourceLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "has_source_level_tile_proxy", HAS_SOURCE_LEVEL_TILE_PROXY_HASH)
        }

        private const val REMOVE_SOURCE_LEVEL_TILE_PROXY_HASH = 1286410249L
        private val removeSourceLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_source_level_tile_proxy", REMOVE_SOURCE_LEVEL_TILE_PROXY_HASH)
        }

        private const val SET_COORDS_LEVEL_TILE_PROXY_HASH = 1769939278L
        private val setCoordsLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_coords_level_tile_proxy", SET_COORDS_LEVEL_TILE_PROXY_HASH)
        }

        private const val GET_COORDS_LEVEL_TILE_PROXY_HASH = 2856536371L
        private val getCoordsLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_coords_level_tile_proxy", GET_COORDS_LEVEL_TILE_PROXY_HASH)
        }

        private const val HAS_COORDS_LEVEL_TILE_PROXY_HASH = 3957903770L
        private val hasCoordsLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "has_coords_level_tile_proxy", HAS_COORDS_LEVEL_TILE_PROXY_HASH)
        }

        private const val REMOVE_COORDS_LEVEL_TILE_PROXY_HASH = 2311374912L
        private val removeCoordsLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_coords_level_tile_proxy", REMOVE_COORDS_LEVEL_TILE_PROXY_HASH)
        }

        private const val SET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 3862385460L
        private val setAlternativeLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "set_alternative_level_tile_proxy", SET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)
        }

        private const val GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 2303761075L
        private val getAlternativeLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_alternative_level_tile_proxy", GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)
        }

        private const val HAS_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 180086755L
        private val hasAlternativeLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "has_alternative_level_tile_proxy", HAS_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)
        }

        private const val REMOVE_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 2328951467L
        private val removeAlternativeLevelTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_alternative_level_tile_proxy", REMOVE_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)
        }

        private const val MAP_TILE_PROXY_HASH = 4267935328L
        private val mapTileProxyBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "map_tile_proxy", MAP_TILE_PROXY_HASH)
        }

        private const val CLEANUP_INVALID_TILE_PROXIES_HASH = 3218959716L
        private val cleanupInvalidTileProxiesBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "cleanup_invalid_tile_proxies", CLEANUP_INVALID_TILE_PROXIES_HASH)
        }

        private const val CLEAR_TILE_PROXIES_HASH = 3218959716L
        private val clearTileProxiesBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "clear_tile_proxies", CLEAR_TILE_PROXIES_HASH)
        }

        private const val ADD_PATTERN_HASH = 763712015L
        private val addPatternBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "add_pattern", ADD_PATTERN_HASH)
        }

        private const val GET_PATTERN_HASH = 4207737510L
        private val getPatternBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_pattern", GET_PATTERN_HASH)
        }

        private const val REMOVE_PATTERN_HASH = 1286410249L
        private val removePatternBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "remove_pattern", REMOVE_PATTERN_HASH)
        }

        private const val GET_PATTERNS_COUNT_HASH = 2455072627L
        private val getPatternsCountBind by lazy {
            ObjectCalls.getMethodBind("TileSet", "get_patterns_count", GET_PATTERNS_COUNT_HASH)
        }
    }
}
