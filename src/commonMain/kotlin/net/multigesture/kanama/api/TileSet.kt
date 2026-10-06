package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2i

/**
 * Tile library for tilemaps.
 *
 * Generated from Godot docs: TileSet
 */
class TileSet(handle: GodotHandle) : Resource(handle) {
    var tileShape: TileSet.TileShape
        @JvmName("tileShapeProperty")
        get() = getTileShape()
        @JvmName("setTileShapeProperty")
        set(value) = setTileShape(value)

    var tileLayout: TileSet.TileLayout
        @JvmName("tileLayoutProperty")
        get() = getTileLayout()
        @JvmName("setTileLayoutProperty")
        set(value) = setTileLayout(value)

    var tileOffsetAxis: TileSet.TileOffsetAxis
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getNextSourceIdBind, segment)
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
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(Binds.addSourceBind, segment, source?.requireOpenHandle() ?: NULL_SEGMENT, atlasSourceIdOverride)
    }

    /**
     * Removes the source with the given source ID.
     *
     * Generated from Godot docs: TileSet.remove_source
     */
    fun removeSource(sourceId: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeSourceBind, segment, sourceId)
    }

    /**
     * Changes a source's ID.
     *
     * Generated from Godot docs: TileSet.set_source_id
     */
    fun setSourceId(sourceId: Int, newSourceId: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setSourceIdBind, segment, sourceId, newSourceId)
    }

    /**
     * Returns the number of `TileSetSource` in this TileSet.
     *
     * Generated from Godot docs: TileSet.get_source_count
     */
    fun getSourceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSourceCountBind, segment)
    }

    /**
     * Returns the source ID for source with index `index`.
     *
     * Generated from Godot docs: TileSet.get_source_id
     */
    fun getSourceId(index: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getSourceIdBind, segment, index)
    }

    /**
     * Returns if this TileSet has a source for the given source ID.
     *
     * Generated from Godot docs: TileSet.has_source
     */
    fun hasSource(sourceId: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasSourceBind, segment, sourceId)
    }

    /**
     * Returns the `TileSetSource` with ID `source_id`.
     *
     * Generated from Godot docs: TileSet.get_source
     */
    fun getSource(sourceId: Int): TileSetSource? {
        checkOpen()
        return TileSetSource.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSourceBind, segment, sourceId))
    }

    /**
     * The tile shape.
     *
     * Generated from Godot docs: TileSet.set_tile_shape
     */
    fun setTileShape(shape: TileSet.TileShape) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTileShapeBind, segment, shape.value)
    }

    /**
     * The tile shape.
     *
     * Generated from Godot docs: TileSet.get_tile_shape
     */
    fun getTileShape(): TileSet.TileShape {
        checkOpen()
        return TileSet.TileShape(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTileShapeBind, segment))
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), changes the way tiles
     * are indexed in the `TileMapLayer` grid.
     *
     * Generated from Godot docs: TileSet.set_tile_layout
     */
    fun setTileLayout(layout: TileSet.TileLayout) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTileLayoutBind, segment, layout.value)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), changes the way tiles
     * are indexed in the `TileMapLayer` grid.
     *
     * Generated from Godot docs: TileSet.get_tile_layout
     */
    fun getTileLayout(): TileSet.TileLayout {
        checkOpen()
        return TileSet.TileLayout(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTileLayoutBind, segment))
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), determines the offset
     * axis.
     *
     * Generated from Godot docs: TileSet.set_tile_offset_axis
     */
    fun setTileOffsetAxis(alignment: TileSet.TileOffsetAxis) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTileOffsetAxisBind, segment, alignment.value)
    }

    /**
     * For all half-offset shapes (Isometric, Hexagonal and Half-Offset square), determines the offset
     * axis.
     *
     * Generated from Godot docs: TileSet.get_tile_offset_axis
     */
    fun getTileOffsetAxis(): TileSet.TileOffsetAxis {
        checkOpen()
        return TileSet.TileOffsetAxis(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTileOffsetAxisBind, segment))
    }

    /**
     * The tile size, in pixels. For all tile shapes, this size corresponds to the encompassing
     * rectangle of the tile shape. This is thus the minimal cell size required in an atlas.
     *
     * Generated from Godot docs: TileSet.set_tile_size
     */
    fun setTileSize(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setTileSizeBind, segment, size)
    }

    /**
     * The tile size, in pixels. For all tile shapes, this size corresponds to the encompassing
     * rectangle of the tile shape. This is thus the minimal cell size required in an atlas.
     *
     * Generated from Godot docs: TileSet.get_tile_size
     */
    fun getTileSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getTileSizeBind, segment)
    }

    /**
     * Enables/Disable uv clipping when rendering the tiles.
     *
     * Generated from Godot docs: TileSet.set_uv_clipping
     */
    fun setUvClipping(uvClipping: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUvClippingBind, segment, uvClipping)
    }

    /**
     * Enables/Disable uv clipping when rendering the tiles.
     *
     * Generated from Godot docs: TileSet.is_uv_clipping
     */
    fun isUvClipping(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUvClippingBind, segment)
    }

    /**
     * Returns the occlusion layers count.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layers_count
     */
    fun getOcclusionLayersCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOcclusionLayersCountBind, segment)
    }

    /**
     * Adds an occlusion layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Occlusion layers allow assigning occlusion
     * polygons to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_occlusion_layer
     */
    fun addOcclusionLayer(toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addOcclusionLayerBind, segment, toPosition)
    }

    /**
     * Moves the occlusion layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_occlusion_layer
     */
    fun moveOcclusionLayer(layerIndex: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveOcclusionLayerBind, segment, layerIndex, toPosition)
    }

    /**
     * Removes the occlusion layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_occlusion_layer
     */
    fun removeOcclusionLayer(layerIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeOcclusionLayerBind, segment, layerIndex)
    }

    /**
     * Sets the occlusion layer (as in the rendering server) for occluders in the given TileSet
     * occlusion layer.
     *
     * Generated from Godot docs: TileSet.set_occlusion_layer_light_mask
     */
    fun setOcclusionLayerLightMask(layerIndex: Int, lightMask: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setOcclusionLayerLightMaskBind, segment, layerIndex, lightMask)
    }

    /**
     * Returns the light mask of the occlusion layer.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layer_light_mask
     */
    fun getOcclusionLayerLightMask(layerIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getOcclusionLayerLightMaskBind, segment, layerIndex)
    }

    /**
     * Enables or disables SDF collision for occluders in the given TileSet occlusion layer.
     *
     * Generated from Godot docs: TileSet.set_occlusion_layer_sdf_collision
     */
    fun setOcclusionLayerSdfCollision(layerIndex: Int, sdfCollision: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setOcclusionLayerSdfCollisionBind, segment, layerIndex, sdfCollision)
    }

    /**
     * Returns if the occluders from this layer use `sdf_collision`.
     *
     * Generated from Godot docs: TileSet.get_occlusion_layer_sdf_collision
     */
    fun getOcclusionLayerSdfCollision(layerIndex: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getOcclusionLayerSdfCollisionBind, segment, layerIndex)
    }

    /**
     * Returns the physics layers count.
     *
     * Generated from Godot docs: TileSet.get_physics_layers_count
     */
    fun getPhysicsLayersCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPhysicsLayersCountBind, segment)
    }

    /**
     * Adds a physics layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Physics layers allow assigning collision
     * polygons to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_physics_layer
     */
    fun addPhysicsLayer(toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addPhysicsLayerBind, segment, toPosition)
    }

    /**
     * Moves the physics layer at index `layer_index` to the given position `to_position` in the array.
     * Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_physics_layer
     */
    fun movePhysicsLayer(layerIndex: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.movePhysicsLayerBind, segment, layerIndex, toPosition)
    }

    /**
     * Removes the physics layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_physics_layer
     */
    fun removePhysicsLayer(layerIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removePhysicsLayerBind, segment, layerIndex)
    }

    /**
     * Sets the collision layer (as in the physics server) for bodies in the given TileSet physics
     * layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_layer
     */
    fun setPhysicsLayerCollisionLayer(layerIndex: Int, layer: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndUInt32Args(Binds.setPhysicsLayerCollisionLayerBind, segment, layerIndex, layer)
    }

    /**
     * Returns the collision layer (as in the physics server) bodies on the given TileSet's physics
     * layer are in.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_layer
     */
    fun getPhysicsLayerCollisionLayer(layerIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetUInt32(Binds.getPhysicsLayerCollisionLayerBind, segment, layerIndex)
    }

    /**
     * Sets the collision mask for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_mask
     */
    fun setPhysicsLayerCollisionMask(layerIndex: Int, mask: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndUInt32Args(Binds.setPhysicsLayerCollisionMaskBind, segment, layerIndex, mask)
    }

    /**
     * Returns the collision mask of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_mask
     */
    fun getPhysicsLayerCollisionMask(layerIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetUInt32(Binds.getPhysicsLayerCollisionMaskBind, segment, layerIndex)
    }

    /**
     * Sets the collision priority for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_collision_priority
     */
    fun setPhysicsLayerCollisionPriority(layerIndex: Int, priority: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setPhysicsLayerCollisionPriorityBind, segment, layerIndex, priority)
    }

    /**
     * Returns the collision priority of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_collision_priority
     */
    fun getPhysicsLayerCollisionPriority(layerIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getPhysicsLayerCollisionPriorityBind, segment, layerIndex)
    }

    /**
     * Sets the physics material for bodies in the given TileSet physics layer.
     *
     * Generated from Godot docs: TileSet.set_physics_layer_physics_material
     */
    fun setPhysicsLayerPhysicsMaterial(layerIndex: Int, physicsMaterial: PhysicsMaterial?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setPhysicsLayerPhysicsMaterialBind, segment, layerIndex, physicsMaterial?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the physics material of bodies on the given TileSet's physics layer.
     *
     * Generated from Godot docs: TileSet.get_physics_layer_physics_material
     */
    fun getPhysicsLayerPhysicsMaterial(layerIndex: Int): PhysicsMaterial? {
        checkOpen()
        return PhysicsMaterial.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPhysicsLayerPhysicsMaterialBind, segment, layerIndex))
    }

    /**
     * Returns the terrain sets count.
     *
     * Generated from Godot docs: TileSet.get_terrain_sets_count
     */
    fun getTerrainSetsCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTerrainSetsCountBind, segment)
    }

    /**
     * Adds a new terrain set at the given position `to_position` in the array. If `to_position` is -1,
     * adds it at the end of the array.
     *
     * Generated from Godot docs: TileSet.add_terrain_set
     */
    fun addTerrainSet(toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addTerrainSetBind, segment, toPosition)
    }

    /**
     * Moves the terrain set at index `terrain_set` to the given position `to_position` in the array.
     * Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_terrain_set
     */
    fun moveTerrainSet(terrainSet: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveTerrainSetBind, segment, terrainSet, toPosition)
    }

    /**
     * Removes the terrain set at index `terrain_set`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_terrain_set
     */
    fun removeTerrainSet(terrainSet: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeTerrainSetBind, segment, terrainSet)
    }

    /**
     * Sets a terrain mode. Each mode determines which bits of a tile shape is used to match the
     * neighboring tiles' terrains.
     *
     * Generated from Godot docs: TileSet.set_terrain_set_mode
     */
    fun setTerrainSetMode(terrainSet: Int, mode: TileSet.TerrainMode) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTerrainSetModeBind, segment, terrainSet, mode.value)
    }

    /**
     * Returns a terrain set mode.
     *
     * Generated from Godot docs: TileSet.get_terrain_set_mode
     */
    fun getTerrainSetMode(terrainSet: Int): TileSet.TerrainMode {
        checkOpen()
        return TileSet.TerrainMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTerrainSetModeBind, segment, terrainSet))
    }

    /**
     * Returns the number of terrains in the given terrain set.
     *
     * Generated from Godot docs: TileSet.get_terrains_count
     */
    fun getTerrainsCount(terrainSet: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getTerrainsCountBind, segment, terrainSet)
    }

    /**
     * Adds a new terrain to the given terrain set `terrain_set` at the given position `to_position` in
     * the array. If `to_position` is -1, adds it at the end of the array.
     *
     * Generated from Godot docs: TileSet.add_terrain
     */
    fun addTerrain(terrainSet: Int, toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.addTerrainBind, segment, terrainSet, toPosition)
    }

    /**
     * Moves the terrain at index `terrain_index` for terrain set `terrain_set` to the given position
     * `to_position` in the array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_terrain
     */
    fun moveTerrain(terrainSet: Int, terrainIndex: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntArgs(Binds.moveTerrainBind, segment, terrainSet, terrainIndex, toPosition)
    }

    /**
     * Removes the terrain at index `terrain_index` in the given terrain set `terrain_set`. Also
     * updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_terrain
     */
    fun removeTerrain(terrainSet: Int, terrainIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.removeTerrainBind, segment, terrainSet, terrainIndex)
    }

    /**
     * Clears all terrain properties for the given terrain set.
     *
     * Generated from Godot docs: TileSet.clear_terrains
     */
    fun clearTerrains(terrainSet: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.clearTerrainsBind, segment, terrainSet)
    }

    /**
     * Sets a terrain's name.
     *
     * Generated from Godot docs: TileSet.set_terrain_name
     */
    fun setTerrainName(terrainSet: Int, terrainIndex: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(Binds.setTerrainNameBind, segment, terrainSet, terrainIndex, name)
    }

    /**
     * Returns a terrain's name.
     *
     * Generated from Godot docs: TileSet.get_terrain_name
     */
    fun getTerrainName(terrainSet: Int, terrainIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(Binds.getTerrainNameBind, segment, terrainSet, terrainIndex)
    }

    /**
     * Sets a terrain's color. This color is used for identifying the different terrains in the TileSet
     * editor.
     *
     * Generated from Godot docs: TileSet.set_terrain_color
     */
    fun setTerrainColor(terrainSet: Int, terrainIndex: Int, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndColorArg(Binds.setTerrainColorBind, segment, terrainSet, terrainIndex, color)
    }

    /**
     * Returns a terrain's color.
     *
     * Generated from Godot docs: TileSet.get_terrain_color
     */
    fun getTerrainColor(terrainSet: Int, terrainIndex: Int): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(Binds.getTerrainColorBind, segment, terrainSet, terrainIndex)
    }

    /**
     * Returns the navigation layers count.
     *
     * Generated from Godot docs: TileSet.get_navigation_layers_count
     */
    fun getNavigationLayersCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getNavigationLayersCountBind, segment)
    }

    /**
     * Adds a navigation layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Navigation layers allow assigning a
     * navigable area to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_navigation_layer
     */
    fun addNavigationLayer(toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addNavigationLayerBind, segment, toPosition)
    }

    /**
     * Moves the navigation layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_navigation_layer
     */
    fun moveNavigationLayer(layerIndex: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveNavigationLayerBind, segment, layerIndex, toPosition)
    }

    /**
     * Removes the navigation layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_navigation_layer
     */
    fun removeNavigationLayer(layerIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeNavigationLayerBind, segment, layerIndex)
    }

    /**
     * Sets the navigation layers (as in the navigation server) for navigation regions in the given
     * TileSet navigation layer.
     *
     * Generated from Godot docs: TileSet.set_navigation_layer_layers
     */
    fun setNavigationLayerLayers(layerIndex: Int, layers: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndUInt32Args(Binds.setNavigationLayerLayersBind, segment, layerIndex, layers)
    }

    /**
     * Returns the navigation layers (as in the Navigation server) of the given TileSet navigation
     * layer.
     *
     * Generated from Godot docs: TileSet.get_navigation_layer_layers
     */
    fun getNavigationLayerLayers(layerIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetUInt32(Binds.getNavigationLayerLayersBind, segment, layerIndex)
    }

    /**
     * Based on `value`, enables or disables the specified navigation layer of the TileSet navigation
     * data layer identified by the given `layer_index`, given a navigation_layers `layer_number`
     * between 1 and 32.
     *
     * Generated from Godot docs: TileSet.set_navigation_layer_layer_value
     */
    fun setNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int, value: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(Binds.setNavigationLayerLayerValueBind, segment, layerIndex, layerNumber, value)
    }

    /**
     * Returns whether or not the specified navigation layer of the TileSet navigation data layer
     * identified by the given `layer_index` is enabled, given a navigation_layers `layer_number`
     * between 1 and 32.
     *
     * Generated from Godot docs: TileSet.get_navigation_layer_layer_value
     */
    fun getNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.getNavigationLayerLayerValueBind, segment, layerIndex, layerNumber)
    }

    /**
     * Returns the custom data layers count.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layers_count
     */
    fun getCustomDataLayersCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCustomDataLayersCountBind, segment)
    }

    /**
     * Adds a custom data layer to the TileSet at the given position `to_position` in the array. If
     * `to_position` is -1, adds it at the end of the array. Custom data layers allow assigning custom
     * properties to atlas tiles.
     *
     * Generated from Godot docs: TileSet.add_custom_data_layer
     */
    fun addCustomDataLayer(toPosition: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addCustomDataLayerBind, segment, toPosition)
    }

    /**
     * Moves the custom data layer at index `layer_index` to the given position `to_position` in the
     * array. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.move_custom_data_layer
     */
    fun moveCustomDataLayer(layerIndex: Int, toPosition: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveCustomDataLayerBind, segment, layerIndex, toPosition)
    }

    /**
     * Removes the custom data layer at index `layer_index`. Also updates the atlas tiles accordingly.
     *
     * Generated from Godot docs: TileSet.remove_custom_data_layer
     */
    fun removeCustomDataLayer(layerIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeCustomDataLayerBind, segment, layerIndex)
    }

    /**
     * Returns the index of the custom data layer identified by the given name.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_by_name
     */
    fun getCustomDataLayerByName(layerName: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetInt(Binds.getCustomDataLayerByNameBind, segment, layerName)
    }

    /**
     * Sets the name of the custom data layer identified by the given index. Names are identifiers of
     * the layer therefore if the name is already taken it will fail and raise an error.
     *
     * Generated from Godot docs: TileSet.set_custom_data_layer_name
     */
    fun setCustomDataLayerName(layerIndex: Int, layerName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setCustomDataLayerNameBind, segment, layerIndex, layerName)
    }

    /**
     * Returns if there is a custom data layer named `layer_name`.
     *
     * Generated from Godot docs: TileSet.has_custom_data_layer_by_name
     */
    fun hasCustomDataLayerByName(layerName: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasCustomDataLayerByNameBind, segment, layerName)
    }

    /**
     * Returns the name of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_name
     */
    fun getCustomDataLayerName(layerIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getCustomDataLayerNameBind, segment, layerIndex)
    }

    /**
     * Sets the type of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.set_custom_data_layer_type
     */
    fun setCustomDataLayerType(layerIndex: Int, layerType: VariantType) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setCustomDataLayerTypeBind, segment, layerIndex, layerType.value)
    }

    /**
     * Returns the type of the custom data layer identified by the given index.
     *
     * Generated from Godot docs: TileSet.get_custom_data_layer_type
     */
    fun getCustomDataLayerType(layerIndex: Int): VariantType {
        checkOpen()
        return VariantType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getCustomDataLayerTypeBind, segment, layerIndex))
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setSourceLevelTileProxyBind, segment, sourceFrom, sourceTo)
    }

    /**
     * Returns the source-level proxy for the given source identifier. If the TileSet has no proxy for
     * the given identifier, returns -1.
     *
     * Generated from Godot docs: TileSet.get_source_level_tile_proxy
     */
    fun getSourceLevelTileProxy(sourceFrom: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getSourceLevelTileProxyBind, segment, sourceFrom)
    }

    /**
     * Returns if there is a source-level proxy for the given source ID.
     *
     * Generated from Godot docs: TileSet.has_source_level_tile_proxy
     */
    fun hasSourceLevelTileProxy(sourceFrom: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.hasSourceLevelTileProxyBind, segment, sourceFrom)
    }

    /**
     * Removes a source-level tile proxy.
     *
     * Generated from Godot docs: TileSet.remove_source_level_tile_proxy
     */
    fun removeSourceLevelTileProxy(sourceFrom: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeSourceLevelTileProxyBind, segment, sourceFrom)
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
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntVector2iArgs(Binds.setCoordsLevelTileProxyBind, segment, sourceFrom, coordsFrom, sourceTo, coordsTo)
    }

    /**
     * Returns the coordinate-level proxy for the given identifiers. The returned array contains the
     * two target identifiers of the proxy (source ID and atlas coordinates ID). If the TileSet has no
     * proxy for the given identifiers, returns an empty Array.
     *
     * Generated from Godot docs: TileSet.get_coords_level_tile_proxy
     */
    fun getCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iArgsRetArray(Binds.getCoordsLevelTileProxyBind, segment, sourceFrom, coordsFrom)
    }

    /**
     * Returns if there is a coodinates-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.has_coords_level_tile_proxy
     */
    fun hasCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndVector2iArgRetBool(Binds.hasCoordsLevelTileProxyBind, segment, sourceFrom, coordsFrom)
    }

    /**
     * Removes a coordinates-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.remove_coords_level_tile_proxy
     */
    fun removeCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(Binds.removeCoordsLevelTileProxyBind, segment, sourceFrom, coordsFrom)
    }

    /**
     * Create an alternative-level proxy for the given identifiers. A proxy will map set of tile
     * identifiers to another set of identifiers. Proxied tiles can be automatically replaced in
     * TileMapLayer nodes using the editor.
     *
     * Generated from Godot docs: TileSet.set_alternative_level_tile_proxy
     */
    fun setAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int, sourceTo: Int, coordsTo: Vector2i, alternativeTo: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iTwoIntVector2iIntArgs(Binds.setAlternativeLevelTileProxyBind, segment, sourceFrom, coordsFrom, alternativeFrom, sourceTo, coordsTo, alternativeTo)
    }

    /**
     * Returns the alternative-level proxy for the given identifiers. The returned array contains the
     * three proxie's target identifiers (source ID, atlas coords ID and alternative tile ID). If the
     * TileSet has no proxy for the given identifiers, returns an empty Array.
     *
     * Generated from Godot docs: TileSet.get_alternative_level_tile_proxy
     */
    fun getAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(Binds.getAlternativeLevelTileProxyBind, segment, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Returns if there is an alternative-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.has_alternative_level_tile_proxy
     */
    fun hasAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetBool(Binds.hasAlternativeLevelTileProxyBind, segment, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Removes an alternative-level proxy for the given identifiers.
     *
     * Generated from Godot docs: TileSet.remove_alternative_level_tile_proxy
     */
    fun removeAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(Binds.removeAlternativeLevelTileProxyBind, segment, sourceFrom, coordsFrom, alternativeFrom)
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
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(Binds.mapTileProxyBind, segment, sourceFrom, coordsFrom, alternativeFrom)
    }

    /**
     * Clears tile proxies pointing to invalid tiles.
     *
     * Generated from Godot docs: TileSet.cleanup_invalid_tile_proxies
     */
    fun cleanupInvalidTileProxies() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.cleanupInvalidTileProxiesBind, segment)
    }

    /**
     * Clears all tile proxies.
     *
     * Generated from Godot docs: TileSet.clear_tile_proxies
     */
    fun clearTileProxies() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearTileProxiesBind, segment)
    }

    /**
     * Adds a `TileMapPattern` to be stored in the TileSet resource. If provided, insert it at the
     * given `index`.
     *
     * Generated from Godot docs: TileSet.add_pattern
     */
    fun addPattern(pattern: TileMapPattern?, index: Int = -1): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(Binds.addPatternBind, segment, pattern?.requireOpenHandle() ?: NULL_SEGMENT, index)
    }

    /**
     * Returns the `TileMapPattern` at the given `index`.
     *
     * Generated from Godot docs: TileSet.get_pattern
     */
    fun getPattern(index: Int = -1): TileMapPattern? {
        checkOpen()
        return TileMapPattern.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPatternBind, segment, index))
    }

    /**
     * Remove the `TileMapPattern` at the given index.
     *
     * Generated from Godot docs: TileSet.remove_pattern
     */
    fun removePattern(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removePatternBind, segment, index)
    }

    /**
     * Returns the number of `TileMapPattern` this tile set handles.
     *
     * Generated from Godot docs: TileSet.get_patterns_count
     */
    fun getPatternsCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPatternsCountBind, segment)
    }

    /**
     * Godot's `TileSet.TileShape` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TileSet.TileShape.<NAME>`).
     *
     * Generated from Godot docs: TileSet.TileShape
     */
    @JvmInline
    value class TileShape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Rectangular tile shape.
             *
             * Generated from Godot docs: TileSet.TILE_SHAPE_SQUARE
             */
            val SQUARE: TileShape get() = TileShape(0L)
            /**
             * Diamond tile shape (for isometric look). Note: Isometric `TileSet` works best if all sibling
             * `TileMapLayer`s and their parent inheriting from `Node2D` have Y-sort enabled.
             *
             * Generated from Godot docs: TileSet.TILE_SHAPE_ISOMETRIC
             */
            val ISOMETRIC: TileShape get() = TileShape(1L)
            /**
             * Rectangular tile shape with one row/column out of two offset by half a tile.
             *
             * Generated from Godot docs: TileSet.TILE_SHAPE_HALF_OFFSET_SQUARE
             */
            val HALF_OFFSET_SQUARE: TileShape get() = TileShape(2L)
            /**
             * Hexagonal tile shape.
             *
             * Generated from Godot docs: TileSet.TILE_SHAPE_HEXAGON
             */
            val HEXAGON: TileShape get() = TileShape(3L)
        }
    }

    /**
     * Godot's `TileSet.TileLayout` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TileSet.TileLayout.<NAME>`).
     *
     * Generated from Godot docs: TileSet.TileLayout
     */
    @JvmInline
    value class TileLayout(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Tile coordinates layout where both axis stay consistent with their respective local horizontal
             * and vertical axis.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_STACKED
             */
            val STACKED: TileLayout get() = TileLayout(0L)
            /**
             * Same as `TileLayout.STACKED`, but the first half-offset is negative instead of positive.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_STACKED_OFFSET
             */
            val STACKED_OFFSET: TileLayout get() = TileLayout(1L)
            /**
             * Tile coordinates layout where the horizontal axis stay horizontal, and the vertical one goes
             * down-right.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_STAIRS_RIGHT
             */
            val STAIRS_RIGHT: TileLayout get() = TileLayout(2L)
            /**
             * Tile coordinates layout where the vertical axis stay vertical, and the horizontal one goes
             * down-right.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_STAIRS_DOWN
             */
            val STAIRS_DOWN: TileLayout get() = TileLayout(3L)
            /**
             * Tile coordinates layout where the horizontal axis goes up-right, and the vertical one goes
             * down-right.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_DIAMOND_RIGHT
             */
            val DIAMOND_RIGHT: TileLayout get() = TileLayout(4L)
            /**
             * Tile coordinates layout where the horizontal axis goes down-right, and the vertical one goes
             * down-left.
             *
             * Generated from Godot docs: TileSet.TILE_LAYOUT_DIAMOND_DOWN
             */
            val DIAMOND_DOWN: TileLayout get() = TileLayout(5L)
        }
    }

    /**
     * Godot's `TileSet.TileOffsetAxis` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TileSet.TileOffsetAxis.<NAME>`).
     *
     * Generated from Godot docs: TileSet.TileOffsetAxis
     */
    @JvmInline
    value class TileOffsetAxis(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Horizontal half-offset.
             *
             * Generated from Godot docs: TileSet.TILE_OFFSET_AXIS_HORIZONTAL
             */
            val HORIZONTAL: TileOffsetAxis get() = TileOffsetAxis(0L)
            /**
             * Vertical half-offset.
             *
             * Generated from Godot docs: TileSet.TILE_OFFSET_AXIS_VERTICAL
             */
            val VERTICAL: TileOffsetAxis get() = TileOffsetAxis(1L)
        }
    }

    /**
     * Godot's `TileSet.CellNeighbor` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TileSet.CellNeighbor.<NAME>`).
     *
     * Generated from Godot docs: TileSet.CellNeighbor
     */
    @JvmInline
    value class CellNeighbor(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Neighbor on the right side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_RIGHT_SIDE
             */
            val RIGHT_SIDE: CellNeighbor get() = CellNeighbor(0L)
            /**
             * Neighbor in the right corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_RIGHT_CORNER
             */
            val RIGHT_CORNER: CellNeighbor get() = CellNeighbor(1L)
            /**
             * Neighbor on the bottom right side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_RIGHT_SIDE
             */
            val BOTTOM_RIGHT_SIDE: CellNeighbor get() = CellNeighbor(2L)
            /**
             * Neighbor in the bottom right corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_RIGHT_CORNER
             */
            val BOTTOM_RIGHT_CORNER: CellNeighbor get() = CellNeighbor(3L)
            /**
             * Neighbor on the bottom side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_SIDE
             */
            val BOTTOM_SIDE: CellNeighbor get() = CellNeighbor(4L)
            /**
             * Neighbor in the bottom corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_CORNER
             */
            val BOTTOM_CORNER: CellNeighbor get() = CellNeighbor(5L)
            /**
             * Neighbor on the bottom left side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_LEFT_SIDE
             */
            val BOTTOM_LEFT_SIDE: CellNeighbor get() = CellNeighbor(6L)
            /**
             * Neighbor in the bottom left corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_BOTTOM_LEFT_CORNER
             */
            val BOTTOM_LEFT_CORNER: CellNeighbor get() = CellNeighbor(7L)
            /**
             * Neighbor on the left side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_LEFT_SIDE
             */
            val LEFT_SIDE: CellNeighbor get() = CellNeighbor(8L)
            /**
             * Neighbor in the left corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_LEFT_CORNER
             */
            val LEFT_CORNER: CellNeighbor get() = CellNeighbor(9L)
            /**
             * Neighbor on the top left side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_LEFT_SIDE
             */
            val TOP_LEFT_SIDE: CellNeighbor get() = CellNeighbor(10L)
            /**
             * Neighbor in the top left corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_LEFT_CORNER
             */
            val TOP_LEFT_CORNER: CellNeighbor get() = CellNeighbor(11L)
            /**
             * Neighbor on the top side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_SIDE
             */
            val TOP_SIDE: CellNeighbor get() = CellNeighbor(12L)
            /**
             * Neighbor in the top corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_CORNER
             */
            val TOP_CORNER: CellNeighbor get() = CellNeighbor(13L)
            /**
             * Neighbor on the top right side.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_RIGHT_SIDE
             */
            val TOP_RIGHT_SIDE: CellNeighbor get() = CellNeighbor(14L)
            /**
             * Neighbor in the top right corner.
             *
             * Generated from Godot docs: TileSet.CELL_NEIGHBOR_TOP_RIGHT_CORNER
             */
            val TOP_RIGHT_CORNER: CellNeighbor get() = CellNeighbor(15L)
        }
    }

    /**
     * Godot's `TileSet.TerrainMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TileSet.TerrainMode.<NAME>`).
     *
     * Generated from Godot docs: TileSet.TerrainMode
     */
    @JvmInline
    value class TerrainMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Requires both corners and side to match with neighboring tiles' terrains.
             *
             * Generated from Godot docs: TileSet.TERRAIN_MODE_MATCH_CORNERS_AND_SIDES
             */
            val CORNERS_AND_SIDES: TerrainMode get() = TerrainMode(0L)
            /**
             * Requires corners to match with neighboring tiles' terrains.
             *
             * Generated from Godot docs: TileSet.TERRAIN_MODE_MATCH_CORNERS
             */
            val CORNERS: TerrainMode get() = TerrainMode(1L)
            /**
             * Requires sides to match with neighboring tiles' terrains.
             *
             * Generated from Godot docs: TileSet.TERRAIN_MODE_MATCH_SIDES
             */
            val SIDES: TerrainMode get() = TerrainMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TileSet? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TileSet? =
            if (handle.address() == 0L) null else RefCounted.owned(TileSet(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TileSet? =
            if (handle.address() == 0L) null else TileSet(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_NEXT_SOURCE_ID_HASH = 3905245786L
        @JvmField
        val getNextSourceIdBind =
            ObjectCalls.getMethodBind("TileSet", "get_next_source_id", GET_NEXT_SOURCE_ID_HASH)

        private const val ADD_SOURCE_HASH = 1059186179L
        @JvmField
        val addSourceBind =
            ObjectCalls.getMethodBind("TileSet", "add_source", ADD_SOURCE_HASH)

        private const val REMOVE_SOURCE_HASH = 1286410249L
        @JvmField
        val removeSourceBind =
            ObjectCalls.getMethodBind("TileSet", "remove_source", REMOVE_SOURCE_HASH)

        private const val SET_SOURCE_ID_HASH = 3937882851L
        @JvmField
        val setSourceIdBind =
            ObjectCalls.getMethodBind("TileSet", "set_source_id", SET_SOURCE_ID_HASH)

        private const val GET_SOURCE_COUNT_HASH = 3905245786L
        @JvmField
        val getSourceCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_source_count", GET_SOURCE_COUNT_HASH)

        private const val GET_SOURCE_ID_HASH = 923996154L
        @JvmField
        val getSourceIdBind =
            ObjectCalls.getMethodBind("TileSet", "get_source_id", GET_SOURCE_ID_HASH)

        private const val HAS_SOURCE_HASH = 1116898809L
        @JvmField
        val hasSourceBind =
            ObjectCalls.getMethodBind("TileSet", "has_source", HAS_SOURCE_HASH)

        private const val GET_SOURCE_HASH = 1763540252L
        @JvmField
        val getSourceBind =
            ObjectCalls.getMethodBind("TileSet", "get_source", GET_SOURCE_HASH)

        private const val SET_TILE_SHAPE_HASH = 2131427112L
        @JvmField
        val setTileShapeBind =
            ObjectCalls.getMethodBind("TileSet", "set_tile_shape", SET_TILE_SHAPE_HASH)

        private const val GET_TILE_SHAPE_HASH = 716918169L
        @JvmField
        val getTileShapeBind =
            ObjectCalls.getMethodBind("TileSet", "get_tile_shape", GET_TILE_SHAPE_HASH)

        private const val SET_TILE_LAYOUT_HASH = 1071216679L
        @JvmField
        val setTileLayoutBind =
            ObjectCalls.getMethodBind("TileSet", "set_tile_layout", SET_TILE_LAYOUT_HASH)

        private const val GET_TILE_LAYOUT_HASH = 194628839L
        @JvmField
        val getTileLayoutBind =
            ObjectCalls.getMethodBind("TileSet", "get_tile_layout", GET_TILE_LAYOUT_HASH)

        private const val SET_TILE_OFFSET_AXIS_HASH = 3300198521L
        @JvmField
        val setTileOffsetAxisBind =
            ObjectCalls.getMethodBind("TileSet", "set_tile_offset_axis", SET_TILE_OFFSET_AXIS_HASH)

        private const val GET_TILE_OFFSET_AXIS_HASH = 762494114L
        @JvmField
        val getTileOffsetAxisBind =
            ObjectCalls.getMethodBind("TileSet", "get_tile_offset_axis", GET_TILE_OFFSET_AXIS_HASH)

        private const val SET_TILE_SIZE_HASH = 1130785943L
        @JvmField
        val setTileSizeBind =
            ObjectCalls.getMethodBind("TileSet", "set_tile_size", SET_TILE_SIZE_HASH)

        private const val GET_TILE_SIZE_HASH = 3690982128L
        @JvmField
        val getTileSizeBind =
            ObjectCalls.getMethodBind("TileSet", "get_tile_size", GET_TILE_SIZE_HASH)

        private const val SET_UV_CLIPPING_HASH = 2586408642L
        @JvmField
        val setUvClippingBind =
            ObjectCalls.getMethodBind("TileSet", "set_uv_clipping", SET_UV_CLIPPING_HASH)

        private const val IS_UV_CLIPPING_HASH = 36873697L
        @JvmField
        val isUvClippingBind =
            ObjectCalls.getMethodBind("TileSet", "is_uv_clipping", IS_UV_CLIPPING_HASH)

        private const val GET_OCCLUSION_LAYERS_COUNT_HASH = 3905245786L
        @JvmField
        val getOcclusionLayersCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layers_count", GET_OCCLUSION_LAYERS_COUNT_HASH)

        private const val ADD_OCCLUSION_LAYER_HASH = 1025054187L
        @JvmField
        val addOcclusionLayerBind =
            ObjectCalls.getMethodBind("TileSet", "add_occlusion_layer", ADD_OCCLUSION_LAYER_HASH)

        private const val MOVE_OCCLUSION_LAYER_HASH = 3937882851L
        @JvmField
        val moveOcclusionLayerBind =
            ObjectCalls.getMethodBind("TileSet", "move_occlusion_layer", MOVE_OCCLUSION_LAYER_HASH)

        private const val REMOVE_OCCLUSION_LAYER_HASH = 1286410249L
        @JvmField
        val removeOcclusionLayerBind =
            ObjectCalls.getMethodBind("TileSet", "remove_occlusion_layer", REMOVE_OCCLUSION_LAYER_HASH)

        private const val SET_OCCLUSION_LAYER_LIGHT_MASK_HASH = 3937882851L
        @JvmField
        val setOcclusionLayerLightMaskBind =
            ObjectCalls.getMethodBind("TileSet", "set_occlusion_layer_light_mask", SET_OCCLUSION_LAYER_LIGHT_MASK_HASH)

        private const val GET_OCCLUSION_LAYER_LIGHT_MASK_HASH = 923996154L
        @JvmField
        val getOcclusionLayerLightMaskBind =
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layer_light_mask", GET_OCCLUSION_LAYER_LIGHT_MASK_HASH)

        private const val SET_OCCLUSION_LAYER_SDF_COLLISION_HASH = 300928843L
        @JvmField
        val setOcclusionLayerSdfCollisionBind =
            ObjectCalls.getMethodBind("TileSet", "set_occlusion_layer_sdf_collision", SET_OCCLUSION_LAYER_SDF_COLLISION_HASH)

        private const val GET_OCCLUSION_LAYER_SDF_COLLISION_HASH = 1116898809L
        @JvmField
        val getOcclusionLayerSdfCollisionBind =
            ObjectCalls.getMethodBind("TileSet", "get_occlusion_layer_sdf_collision", GET_OCCLUSION_LAYER_SDF_COLLISION_HASH)

        private const val GET_PHYSICS_LAYERS_COUNT_HASH = 3905245786L
        @JvmField
        val getPhysicsLayersCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_physics_layers_count", GET_PHYSICS_LAYERS_COUNT_HASH)

        private const val ADD_PHYSICS_LAYER_HASH = 1025054187L
        @JvmField
        val addPhysicsLayerBind =
            ObjectCalls.getMethodBind("TileSet", "add_physics_layer", ADD_PHYSICS_LAYER_HASH)

        private const val MOVE_PHYSICS_LAYER_HASH = 3937882851L
        @JvmField
        val movePhysicsLayerBind =
            ObjectCalls.getMethodBind("TileSet", "move_physics_layer", MOVE_PHYSICS_LAYER_HASH)

        private const val REMOVE_PHYSICS_LAYER_HASH = 1286410249L
        @JvmField
        val removePhysicsLayerBind =
            ObjectCalls.getMethodBind("TileSet", "remove_physics_layer", REMOVE_PHYSICS_LAYER_HASH)

        private const val SET_PHYSICS_LAYER_COLLISION_LAYER_HASH = 3937882851L
        @JvmField
        val setPhysicsLayerCollisionLayerBind =
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_layer", SET_PHYSICS_LAYER_COLLISION_LAYER_HASH)

        private const val GET_PHYSICS_LAYER_COLLISION_LAYER_HASH = 923996154L
        @JvmField
        val getPhysicsLayerCollisionLayerBind =
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_layer", GET_PHYSICS_LAYER_COLLISION_LAYER_HASH)

        private const val SET_PHYSICS_LAYER_COLLISION_MASK_HASH = 3937882851L
        @JvmField
        val setPhysicsLayerCollisionMaskBind =
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_mask", SET_PHYSICS_LAYER_COLLISION_MASK_HASH)

        private const val GET_PHYSICS_LAYER_COLLISION_MASK_HASH = 923996154L
        @JvmField
        val getPhysicsLayerCollisionMaskBind =
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_mask", GET_PHYSICS_LAYER_COLLISION_MASK_HASH)

        private const val SET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH = 1602489585L
        @JvmField
        val setPhysicsLayerCollisionPriorityBind =
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_collision_priority", SET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH)

        private const val GET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH = 2339986948L
        @JvmField
        val getPhysicsLayerCollisionPriorityBind =
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_collision_priority", GET_PHYSICS_LAYER_COLLISION_PRIORITY_HASH)

        private const val SET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH = 1018687357L
        @JvmField
        val setPhysicsLayerPhysicsMaterialBind =
            ObjectCalls.getMethodBind("TileSet", "set_physics_layer_physics_material", SET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH)

        private const val GET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH = 788318639L
        @JvmField
        val getPhysicsLayerPhysicsMaterialBind =
            ObjectCalls.getMethodBind("TileSet", "get_physics_layer_physics_material", GET_PHYSICS_LAYER_PHYSICS_MATERIAL_HASH)

        private const val GET_TERRAIN_SETS_COUNT_HASH = 3905245786L
        @JvmField
        val getTerrainSetsCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_terrain_sets_count", GET_TERRAIN_SETS_COUNT_HASH)

        private const val ADD_TERRAIN_SET_HASH = 1025054187L
        @JvmField
        val addTerrainSetBind =
            ObjectCalls.getMethodBind("TileSet", "add_terrain_set", ADD_TERRAIN_SET_HASH)

        private const val MOVE_TERRAIN_SET_HASH = 3937882851L
        @JvmField
        val moveTerrainSetBind =
            ObjectCalls.getMethodBind("TileSet", "move_terrain_set", MOVE_TERRAIN_SET_HASH)

        private const val REMOVE_TERRAIN_SET_HASH = 1286410249L
        @JvmField
        val removeTerrainSetBind =
            ObjectCalls.getMethodBind("TileSet", "remove_terrain_set", REMOVE_TERRAIN_SET_HASH)

        private const val SET_TERRAIN_SET_MODE_HASH = 3943003916L
        @JvmField
        val setTerrainSetModeBind =
            ObjectCalls.getMethodBind("TileSet", "set_terrain_set_mode", SET_TERRAIN_SET_MODE_HASH)

        private const val GET_TERRAIN_SET_MODE_HASH = 2084469411L
        @JvmField
        val getTerrainSetModeBind =
            ObjectCalls.getMethodBind("TileSet", "get_terrain_set_mode", GET_TERRAIN_SET_MODE_HASH)

        private const val GET_TERRAINS_COUNT_HASH = 923996154L
        @JvmField
        val getTerrainsCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_terrains_count", GET_TERRAINS_COUNT_HASH)

        private const val ADD_TERRAIN_HASH = 1230568737L
        @JvmField
        val addTerrainBind =
            ObjectCalls.getMethodBind("TileSet", "add_terrain", ADD_TERRAIN_HASH)

        private const val MOVE_TERRAIN_HASH = 1649997291L
        @JvmField
        val moveTerrainBind =
            ObjectCalls.getMethodBind("TileSet", "move_terrain", MOVE_TERRAIN_HASH)

        private const val REMOVE_TERRAIN_HASH = 3937882851L
        @JvmField
        val removeTerrainBind =
            ObjectCalls.getMethodBind("TileSet", "remove_terrain", REMOVE_TERRAIN_HASH)

        private const val CLEAR_TERRAINS_HASH = 1286410249L
        @JvmField
        val clearTerrainsBind =
            ObjectCalls.getMethodBind("TileSet", "clear_terrains", CLEAR_TERRAINS_HASH)

        private const val SET_TERRAIN_NAME_HASH = 2285447957L
        @JvmField
        val setTerrainNameBind =
            ObjectCalls.getMethodBind("TileSet", "set_terrain_name", SET_TERRAIN_NAME_HASH)

        private const val GET_TERRAIN_NAME_HASH = 1391810591L
        @JvmField
        val getTerrainNameBind =
            ObjectCalls.getMethodBind("TileSet", "get_terrain_name", GET_TERRAIN_NAME_HASH)

        private const val SET_TERRAIN_COLOR_HASH = 3733378741L
        @JvmField
        val setTerrainColorBind =
            ObjectCalls.getMethodBind("TileSet", "set_terrain_color", SET_TERRAIN_COLOR_HASH)

        private const val GET_TERRAIN_COLOR_HASH = 2165839948L
        @JvmField
        val getTerrainColorBind =
            ObjectCalls.getMethodBind("TileSet", "get_terrain_color", GET_TERRAIN_COLOR_HASH)

        private const val GET_NAVIGATION_LAYERS_COUNT_HASH = 3905245786L
        @JvmField
        val getNavigationLayersCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layers_count", GET_NAVIGATION_LAYERS_COUNT_HASH)

        private const val ADD_NAVIGATION_LAYER_HASH = 1025054187L
        @JvmField
        val addNavigationLayerBind =
            ObjectCalls.getMethodBind("TileSet", "add_navigation_layer", ADD_NAVIGATION_LAYER_HASH)

        private const val MOVE_NAVIGATION_LAYER_HASH = 3937882851L
        @JvmField
        val moveNavigationLayerBind =
            ObjectCalls.getMethodBind("TileSet", "move_navigation_layer", MOVE_NAVIGATION_LAYER_HASH)

        private const val REMOVE_NAVIGATION_LAYER_HASH = 1286410249L
        @JvmField
        val removeNavigationLayerBind =
            ObjectCalls.getMethodBind("TileSet", "remove_navigation_layer", REMOVE_NAVIGATION_LAYER_HASH)

        private const val SET_NAVIGATION_LAYER_LAYERS_HASH = 3937882851L
        @JvmField
        val setNavigationLayerLayersBind =
            ObjectCalls.getMethodBind("TileSet", "set_navigation_layer_layers", SET_NAVIGATION_LAYER_LAYERS_HASH)

        private const val GET_NAVIGATION_LAYER_LAYERS_HASH = 923996154L
        @JvmField
        val getNavigationLayerLayersBind =
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layer_layers", GET_NAVIGATION_LAYER_LAYERS_HASH)

        private const val SET_NAVIGATION_LAYER_LAYER_VALUE_HASH = 1383440665L
        @JvmField
        val setNavigationLayerLayerValueBind =
            ObjectCalls.getMethodBind("TileSet", "set_navigation_layer_layer_value", SET_NAVIGATION_LAYER_LAYER_VALUE_HASH)

        private const val GET_NAVIGATION_LAYER_LAYER_VALUE_HASH = 2522259332L
        @JvmField
        val getNavigationLayerLayerValueBind =
            ObjectCalls.getMethodBind("TileSet", "get_navigation_layer_layer_value", GET_NAVIGATION_LAYER_LAYER_VALUE_HASH)

        private const val GET_CUSTOM_DATA_LAYERS_COUNT_HASH = 3905245786L
        @JvmField
        val getCustomDataLayersCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layers_count", GET_CUSTOM_DATA_LAYERS_COUNT_HASH)

        private const val ADD_CUSTOM_DATA_LAYER_HASH = 1025054187L
        @JvmField
        val addCustomDataLayerBind =
            ObjectCalls.getMethodBind("TileSet", "add_custom_data_layer", ADD_CUSTOM_DATA_LAYER_HASH)

        private const val MOVE_CUSTOM_DATA_LAYER_HASH = 3937882851L
        @JvmField
        val moveCustomDataLayerBind =
            ObjectCalls.getMethodBind("TileSet", "move_custom_data_layer", MOVE_CUSTOM_DATA_LAYER_HASH)

        private const val REMOVE_CUSTOM_DATA_LAYER_HASH = 1286410249L
        @JvmField
        val removeCustomDataLayerBind =
            ObjectCalls.getMethodBind("TileSet", "remove_custom_data_layer", REMOVE_CUSTOM_DATA_LAYER_HASH)

        private const val GET_CUSTOM_DATA_LAYER_BY_NAME_HASH = 1321353865L
        @JvmField
        val getCustomDataLayerByNameBind =
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_by_name", GET_CUSTOM_DATA_LAYER_BY_NAME_HASH)

        private const val SET_CUSTOM_DATA_LAYER_NAME_HASH = 501894301L
        @JvmField
        val setCustomDataLayerNameBind =
            ObjectCalls.getMethodBind("TileSet", "set_custom_data_layer_name", SET_CUSTOM_DATA_LAYER_NAME_HASH)

        private const val HAS_CUSTOM_DATA_LAYER_BY_NAME_HASH = 3927539163L
        @JvmField
        val hasCustomDataLayerByNameBind =
            ObjectCalls.getMethodBind("TileSet", "has_custom_data_layer_by_name", HAS_CUSTOM_DATA_LAYER_BY_NAME_HASH)

        private const val GET_CUSTOM_DATA_LAYER_NAME_HASH = 844755477L
        @JvmField
        val getCustomDataLayerNameBind =
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_name", GET_CUSTOM_DATA_LAYER_NAME_HASH)

        private const val SET_CUSTOM_DATA_LAYER_TYPE_HASH = 3492912874L
        @JvmField
        val setCustomDataLayerTypeBind =
            ObjectCalls.getMethodBind("TileSet", "set_custom_data_layer_type", SET_CUSTOM_DATA_LAYER_TYPE_HASH)

        private const val GET_CUSTOM_DATA_LAYER_TYPE_HASH = 2990820875L
        @JvmField
        val getCustomDataLayerTypeBind =
            ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_type", GET_CUSTOM_DATA_LAYER_TYPE_HASH)

        private const val SET_SOURCE_LEVEL_TILE_PROXY_HASH = 3937882851L
        @JvmField
        val setSourceLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "set_source_level_tile_proxy", SET_SOURCE_LEVEL_TILE_PROXY_HASH)

        private const val GET_SOURCE_LEVEL_TILE_PROXY_HASH = 3744713108L
        @JvmField
        val getSourceLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "get_source_level_tile_proxy", GET_SOURCE_LEVEL_TILE_PROXY_HASH)

        private const val HAS_SOURCE_LEVEL_TILE_PROXY_HASH = 3067735520L
        @JvmField
        val hasSourceLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "has_source_level_tile_proxy", HAS_SOURCE_LEVEL_TILE_PROXY_HASH)

        private const val REMOVE_SOURCE_LEVEL_TILE_PROXY_HASH = 1286410249L
        @JvmField
        val removeSourceLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "remove_source_level_tile_proxy", REMOVE_SOURCE_LEVEL_TILE_PROXY_HASH)

        private const val SET_COORDS_LEVEL_TILE_PROXY_HASH = 1769939278L
        @JvmField
        val setCoordsLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "set_coords_level_tile_proxy", SET_COORDS_LEVEL_TILE_PROXY_HASH)

        private const val GET_COORDS_LEVEL_TILE_PROXY_HASH = 2856536371L
        @JvmField
        val getCoordsLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "get_coords_level_tile_proxy", GET_COORDS_LEVEL_TILE_PROXY_HASH)

        private const val HAS_COORDS_LEVEL_TILE_PROXY_HASH = 3957903770L
        @JvmField
        val hasCoordsLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "has_coords_level_tile_proxy", HAS_COORDS_LEVEL_TILE_PROXY_HASH)

        private const val REMOVE_COORDS_LEVEL_TILE_PROXY_HASH = 2311374912L
        @JvmField
        val removeCoordsLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "remove_coords_level_tile_proxy", REMOVE_COORDS_LEVEL_TILE_PROXY_HASH)

        private const val SET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 3862385460L
        @JvmField
        val setAlternativeLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "set_alternative_level_tile_proxy", SET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)

        private const val GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 2303761075L
        @JvmField
        val getAlternativeLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "get_alternative_level_tile_proxy", GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)

        private const val HAS_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 180086755L
        @JvmField
        val hasAlternativeLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "has_alternative_level_tile_proxy", HAS_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)

        private const val REMOVE_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 2328951467L
        @JvmField
        val removeAlternativeLevelTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "remove_alternative_level_tile_proxy", REMOVE_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)

        private const val MAP_TILE_PROXY_HASH = 4267935328L
        @JvmField
        val mapTileProxyBind =
            ObjectCalls.getMethodBind("TileSet", "map_tile_proxy", MAP_TILE_PROXY_HASH)

        private const val CLEANUP_INVALID_TILE_PROXIES_HASH = 3218959716L
        @JvmField
        val cleanupInvalidTileProxiesBind =
            ObjectCalls.getMethodBind("TileSet", "cleanup_invalid_tile_proxies", CLEANUP_INVALID_TILE_PROXIES_HASH)

        private const val CLEAR_TILE_PROXIES_HASH = 3218959716L
        @JvmField
        val clearTileProxiesBind =
            ObjectCalls.getMethodBind("TileSet", "clear_tile_proxies", CLEAR_TILE_PROXIES_HASH)

        private const val ADD_PATTERN_HASH = 763712015L
        @JvmField
        val addPatternBind =
            ObjectCalls.getMethodBind("TileSet", "add_pattern", ADD_PATTERN_HASH)

        private const val GET_PATTERN_HASH = 4207737510L
        @JvmField
        val getPatternBind =
            ObjectCalls.getMethodBind("TileSet", "get_pattern", GET_PATTERN_HASH)

        private const val REMOVE_PATTERN_HASH = 1286410249L
        @JvmField
        val removePatternBind =
            ObjectCalls.getMethodBind("TileSet", "remove_pattern", REMOVE_PATTERN_HASH)

        private const val GET_PATTERNS_COUNT_HASH = 2455072627L
        @JvmField
        val getPatternsCountBind =
            ObjectCalls.getMethodBind("TileSet", "get_patterns_count", GET_PATTERNS_COUNT_HASH)
    }
}
