package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Settings for a single tile in a `TileSet`.
 *
 * Generated from Godot docs: TileData
 */
class TileData(handle: GodotHandle) : GodotObject(handle) {
    var flipH: Boolean
        @JvmName("flipHProperty")
        get() = getFlipH()
        @JvmName("setFlipHProperty")
        set(value) = setFlipH(value)

    var flipV: Boolean
        @JvmName("flipVProperty")
        get() = getFlipV()
        @JvmName("setFlipVProperty")
        set(value) = setFlipV(value)

    var transpose: Boolean
        @JvmName("transposeProperty")
        get() = getTranspose()
        @JvmName("setTransposeProperty")
        set(value) = setTranspose(value)

    var textureOrigin: Vector2i
        @JvmName("textureOriginProperty")
        get() = getTextureOrigin()
        @JvmName("setTextureOriginProperty")
        set(value) = setTextureOrigin(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    var zIndex: Int
        @JvmName("zIndexProperty")
        get() = getZIndex()
        @JvmName("setZIndexProperty")
        set(value) = setZIndex(value)

    var ySortOrigin: Int
        @JvmName("ySortOriginProperty")
        get() = getYSortOrigin()
        @JvmName("setYSortOriginProperty")
        set(value) = setYSortOrigin(value)

    var terrainSet: Int
        @JvmName("terrainSetProperty")
        get() = getTerrainSet()
        @JvmName("setTerrainSetProperty")
        set(value) = setTerrainSet(value)

    var terrain: Int
        @JvmName("terrainProperty")
        get() = getTerrain()
        @JvmName("setTerrainProperty")
        set(value) = setTerrain(value)

    var probability: Double
        @JvmName("probabilityProperty")
        get() = getProbability()
        @JvmName("setProbabilityProperty")
        set(value) = setProbability(value)

    /**
     * If `true`, the tile will have its texture flipped horizontally.
     *
     * Generated from Godot docs: TileData.set_flip_h
     */
    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipHBind, segment, flipH)
    }

    /**
     * If `true`, the tile will have its texture flipped horizontally.
     *
     * Generated from Godot docs: TileData.get_flip_h
     */
    fun getFlipH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFlipHBind, segment)
    }

    /**
     * If `true`, the tile will have its texture flipped vertically.
     *
     * Generated from Godot docs: TileData.set_flip_v
     */
    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipVBind, segment, flipV)
    }

    /**
     * If `true`, the tile will have its texture flipped vertically.
     *
     * Generated from Godot docs: TileData.get_flip_v
     */
    fun getFlipV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFlipVBind, segment)
    }

    /**
     * If `true`, the tile will display transposed, i.e. with horizontal and vertical texture UVs
     * swapped.
     *
     * Generated from Godot docs: TileData.set_transpose
     */
    fun setTranspose(transpose: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTransposeBind, segment, transpose)
    }

    /**
     * If `true`, the tile will display transposed, i.e. with horizontal and vertical texture UVs
     * swapped.
     *
     * Generated from Godot docs: TileData.get_transpose
     */
    fun getTranspose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getTransposeBind, segment)
    }

    /**
     * The `Material` to use for this `TileData`. This can be a `CanvasItemMaterial` to use the default
     * shader, or a `ShaderMaterial` to use a custom shader.
     *
     * Generated from Godot docs: TileData.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Material` to use for this `TileData`. This can be a `CanvasItemMaterial` to use the default
     * shader, or a `ShaderMaterial` to use a custom shader.
     *
     * Generated from Godot docs: TileData.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * Offsets the position of where the tile is drawn.
     *
     * Generated from Godot docs: TileData.set_texture_origin
     */
    fun setTextureOrigin(textureOrigin: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setTextureOriginBind, segment, textureOrigin)
    }

    /**
     * Offsets the position of where the tile is drawn.
     *
     * Generated from Godot docs: TileData.get_texture_origin
     */
    fun getTextureOrigin(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getTextureOriginBind, segment)
    }

    /**
     * Color modulation of the tile.
     *
     * Generated from Godot docs: TileData.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setModulateBind, segment, modulate)
    }

    /**
     * Color modulation of the tile.
     *
     * Generated from Godot docs: TileData.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getModulateBind, segment)
    }

    /**
     * Ordering index of this tile, relative to `TileMapLayer`.
     *
     * Generated from Godot docs: TileData.set_z_index
     */
    fun setZIndex(zIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setZIndexBind, segment, zIndex)
    }

    /**
     * Ordering index of this tile, relative to `TileMapLayer`.
     *
     * Generated from Godot docs: TileData.get_z_index
     */
    fun getZIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getZIndexBind, segment)
    }

    /**
     * Vertical point of the tile used for determining y-sorted order.
     *
     * Generated from Godot docs: TileData.set_y_sort_origin
     */
    fun setYSortOrigin(ySortOrigin: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setYSortOriginBind, segment, ySortOrigin)
    }

    /**
     * Vertical point of the tile used for determining y-sorted order.
     *
     * Generated from Godot docs: TileData.get_y_sort_origin
     */
    fun getYSortOrigin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getYSortOriginBind, segment)
    }

    /**
     * Sets the occluder polygon count in the TileSet occlusion layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_occluder_polygons_count
     */
    fun setOccluderPolygonsCount(layerId: Int, polygonsCount: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setOccluderPolygonsCountBind, segment, layerId, polygonsCount)
    }

    /**
     * Returns the number of occluder polygons of the tile in the TileSet occlusion layer with index
     * `layer_id`.
     *
     * Generated from Godot docs: TileData.get_occluder_polygons_count
     */
    fun getOccluderPolygonsCount(layerId: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getOccluderPolygonsCountBind, segment, layerId)
    }

    /**
     * Adds an occlusion polygon to the tile on the TileSet occlusion layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.add_occluder_polygon
     */
    fun addOccluderPolygon(layerId: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.addOccluderPolygonBind, segment, layerId)
    }

    /**
     * Removes the polygon at index `polygon_index` for TileSet occlusion layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.remove_occluder_polygon
     */
    fun removeOccluderPolygon(layerId: Int, polygonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.removeOccluderPolygonBind, segment, layerId, polygonIndex)
    }

    /**
     * Sets the occluder for polygon with index `polygon_index` in the TileSet occlusion layer with
     * index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_occluder_polygon
     */
    fun setOccluderPolygon(layerId: Int, polygonIndex: Int, polygon: OccluderPolygon2D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(Binds.setOccluderPolygonBind, segment, layerId, polygonIndex, polygon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the occluder polygon at index `polygon_index` from the TileSet occlusion layer with
     * index `layer_id`. The `flip_h`, `flip_v`, and `transpose` parameters can be `true` to transform
     * the returned polygon.
     *
     * Generated from Godot docs: TileData.get_occluder_polygon
     */
    fun getOccluderPolygon(layerId: Int, polygonIndex: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): OccluderPolygon2D? {
        return OccluderPolygon2D.wrapOwned(ObjectCalls.ptrcallWithTwoIntAndThreeBoolArgsRetObject(Binds.getOccluderPolygonBind, segment, layerId, polygonIndex, flipH, flipV, transpose))
    }

    /**
     * Sets the occluder for the TileSet occlusion layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_occluder
     */
    fun setOccluder(layerId: Int, occluderPolygon: OccluderPolygon2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setOccluderBind, segment, layerId, occluderPolygon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the occluder polygon of the tile for the TileSet occlusion layer with index `layer_id`.
     * `flip_h`, `flip_v`, and `transpose` allow transforming the returned polygon.
     *
     * Generated from Godot docs: TileData.get_occluder
     */
    fun getOccluder(layerId: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): OccluderPolygon2D? {
        return OccluderPolygon2D.wrapOwned(ObjectCalls.ptrcallWithIntAndThreeBoolArgsRetObject(Binds.getOccluderBind, segment, layerId, flipH, flipV, transpose))
    }

    /**
     * Sets the constant linear velocity. This does not move the tile. This linear velocity is applied
     * to objects colliding with this tile. This is useful to create conveyor belts.
     *
     * Generated from Godot docs: TileData.set_constant_linear_velocity
     */
    fun setConstantLinearVelocity(layerId: Int, velocity: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(Binds.setConstantLinearVelocityBind, segment, layerId, velocity)
    }

    /**
     * Returns the constant linear velocity applied to objects colliding with this tile.
     *
     * Generated from Godot docs: TileData.get_constant_linear_velocity
     */
    fun getConstantLinearVelocity(layerId: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getConstantLinearVelocityBind, segment, layerId)
    }

    /**
     * Sets the constant angular velocity. This does not rotate the tile. This angular velocity is
     * applied to objects colliding with this tile.
     *
     * Generated from Godot docs: TileData.set_constant_angular_velocity
     */
    fun setConstantAngularVelocity(layerId: Int, velocity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setConstantAngularVelocityBind, segment, layerId, velocity)
    }

    /**
     * Returns the constant angular velocity applied to objects colliding with this tile.
     *
     * Generated from Godot docs: TileData.get_constant_angular_velocity
     */
    fun getConstantAngularVelocity(layerId: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getConstantAngularVelocityBind, segment, layerId)
    }

    /**
     * Sets the polygons count for TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_collision_polygons_count
     */
    fun setCollisionPolygonsCount(layerId: Int, polygonsCount: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setCollisionPolygonsCountBind, segment, layerId, polygonsCount)
    }

    /**
     * Returns how many polygons the tile has for TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.get_collision_polygons_count
     */
    fun getCollisionPolygonsCount(layerId: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getCollisionPolygonsCountBind, segment, layerId)
    }

    /**
     * Adds a collision polygon to the tile on the given TileSet physics layer.
     *
     * Generated from Godot docs: TileData.add_collision_polygon
     */
    fun addCollisionPolygon(layerId: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.addCollisionPolygonBind, segment, layerId)
    }

    /**
     * Removes the polygon at index `polygon_index` for TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.remove_collision_polygon
     */
    fun removeCollisionPolygon(layerId: Int, polygonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.removeCollisionPolygonBind, segment, layerId, polygonIndex)
    }

    /**
     * Sets the points of the polygon at index `polygon_index` for TileSet physics layer with index
     * `layer_id`.
     *
     * Generated from Godot docs: TileData.set_collision_polygon_points
     */
    fun setCollisionPolygonPoints(layerId: Int, polygonIndex: Int, polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithTwoIntAndPackedVector2ListArg(Binds.setCollisionPolygonPointsBind, segment, layerId, polygonIndex, polygon)
    }

    /**
     * Returns the points of the polygon at index `polygon_index` for TileSet physics layer with index
     * `layer_id`.
     *
     * Generated from Godot docs: TileData.get_collision_polygon_points
     */
    fun getCollisionPolygonPoints(layerId: Int, polygonIndex: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoIntArgsRetPackedVector2List(Binds.getCollisionPolygonPointsBind, segment, layerId, polygonIndex)
    }

    /**
     * Enables/disables one-way collisions on the polygon at index `polygon_index` for TileSet physics
     * layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_collision_polygon_one_way
     */
    fun setCollisionPolygonOneWay(layerId: Int, polygonIndex: Int, oneWay: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(Binds.setCollisionPolygonOneWayBind, segment, layerId, polygonIndex, oneWay)
    }

    /**
     * Returns whether one-way collisions are enabled for the polygon at index `polygon_index` for
     * TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.is_collision_polygon_one_way
     */
    fun isCollisionPolygonOneWay(layerId: Int, polygonIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.isCollisionPolygonOneWayBind, segment, layerId, polygonIndex)
    }

    /**
     * Sets the one-way margin (for one-way platforms) of the polygon at index `polygon_index` for
     * TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_collision_polygon_one_way_margin
     */
    fun setCollisionPolygonOneWayMargin(layerId: Int, polygonIndex: Int, oneWayMargin: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCollisionPolygonOneWayMarginBind, segment, layerId, polygonIndex, oneWayMargin)
    }

    /**
     * Returns the one-way margin (for one-way platforms) of the polygon at index `polygon_index` for
     * TileSet physics layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.get_collision_polygon_one_way_margin
     */
    fun getCollisionPolygonOneWayMargin(layerId: Int, polygonIndex: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCollisionPolygonOneWayMarginBind, segment, layerId, polygonIndex)
    }

    /**
     * ID of the terrain set that the tile uses.
     *
     * Generated from Godot docs: TileData.set_terrain_set
     */
    fun setTerrainSet(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTerrainSetBind, segment, terrainSet)
    }

    /**
     * ID of the terrain set that the tile uses.
     *
     * Generated from Godot docs: TileData.get_terrain_set
     */
    fun getTerrainSet(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTerrainSetBind, segment)
    }

    /**
     * ID of the terrain from the terrain set that the tile uses.
     *
     * Generated from Godot docs: TileData.set_terrain
     */
    fun setTerrain(terrain: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTerrainBind, segment, terrain)
    }

    /**
     * ID of the terrain from the terrain set that the tile uses.
     *
     * Generated from Godot docs: TileData.get_terrain
     */
    fun getTerrain(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTerrainBind, segment)
    }

    /**
     * Sets the tile's terrain bit for the given `peering_bit` direction. To check that a direction is
     * valid, use `is_valid_terrain_peering_bit`.
     *
     * Generated from Godot docs: TileData.set_terrain_peering_bit
     */
    fun setTerrainPeeringBit(peeringBit: TileSet.CellNeighbor, terrain: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(Binds.setTerrainPeeringBitBind, segment, peeringBit.value, terrain)
    }

    /**
     * Returns the tile's terrain bit for the given `peering_bit` direction. To check that a direction
     * is valid, use `is_valid_terrain_peering_bit`.
     *
     * Generated from Godot docs: TileData.get_terrain_peering_bit
     */
    fun getTerrainPeeringBit(peeringBit: TileSet.CellNeighbor): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(Binds.getTerrainPeeringBitBind, segment, peeringBit.value)
    }

    /**
     * Returns whether the given `peering_bit` direction is valid for this tile.
     *
     * Generated from Godot docs: TileData.is_valid_terrain_peering_bit
     */
    fun isValidTerrainPeeringBit(peeringBit: TileSet.CellNeighbor): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.isValidTerrainPeeringBitBind, segment, peeringBit.value)
    }

    /**
     * Sets the navigation polygon for the TileSet navigation layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_navigation_polygon
     */
    fun setNavigationPolygon(layerId: Int, navigationPolygon: NavigationPolygon?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setNavigationPolygonBind, segment, layerId, navigationPolygon?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the navigation polygon of the tile for the TileSet navigation layer with index
     * `layer_id`. `flip_h`, `flip_v`, and `transpose` allow transforming the returned polygon.
     *
     * Generated from Godot docs: TileData.get_navigation_polygon
     */
    fun getNavigationPolygon(layerId: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): NavigationPolygon? {
        return NavigationPolygon.wrapOwned(ObjectCalls.ptrcallWithIntAndThreeBoolArgsRetObject(Binds.getNavigationPolygonBind, segment, layerId, flipH, flipV, transpose))
    }

    /**
     * Relative probability of this tile being selected when drawing a pattern of random tiles.
     *
     * Generated from Godot docs: TileData.set_probability
     */
    fun setProbability(probability: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setProbabilityBind, segment, probability)
    }

    /**
     * Relative probability of this tile being selected when drawing a pattern of random tiles.
     *
     * Generated from Godot docs: TileData.get_probability
     */
    fun getProbability(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getProbabilityBind, segment)
    }

    /**
     * Sets the tile's custom data value for the TileSet custom data layer with name `layer_name`.
     *
     * Generated from Godot docs: TileData.set_custom_data
     */
    fun setCustomData(layerName: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(Binds.setCustomDataBind, segment, layerName, value)
    }

    /**
     * Returns the custom data value for custom data layer named `layer_name`. To check if a custom
     * data layer exists, use `has_custom_data`.
     *
     * Generated from Godot docs: TileData.get_custom_data
     */
    fun getCustomData(layerName: String): Any? {
        return ObjectCalls.ptrcallWithStringArgRetVariantScalar(Binds.getCustomDataBind, segment, layerName)
    }

    /**
     * Returns whether there exists a custom data layer named `layer_name`.
     *
     * Generated from Godot docs: TileData.has_custom_data
     */
    fun hasCustomData(layerName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasCustomDataBind, segment, layerName)
    }

    /**
     * Sets the tile's custom data value for the TileSet custom data layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.set_custom_data_by_layer_id
     */
    fun setCustomDataByLayerId(layerId: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setCustomDataByLayerIdBind, segment, layerId, value)
    }

    /**
     * Returns the custom data value for custom data layer with index `layer_id`.
     *
     * Generated from Godot docs: TileData.get_custom_data_by_layer_id
     */
    fun getCustomDataByLayerId(layerId: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getCustomDataByLayerIdBind, segment, layerId)
    }

    /** Signal `changed()`; see [TypedSignal]. */
    val changed: Signal0
        @JvmName("changedTypedSignal")
        get() = Signal0(this, "changed")

    object Signals {
        const val changed: String = "changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TileData? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TileData? =
            if (handle.address() == 0L) null else TileData(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FLIP_H_HASH = 2586408642L
        @JvmField
        val setFlipHBind =
            ObjectCalls.getMethodBind("TileData", "set_flip_h", SET_FLIP_H_HASH)

        private const val GET_FLIP_H_HASH = 36873697L
        @JvmField
        val getFlipHBind =
            ObjectCalls.getMethodBind("TileData", "get_flip_h", GET_FLIP_H_HASH)

        private const val SET_FLIP_V_HASH = 2586408642L
        @JvmField
        val setFlipVBind =
            ObjectCalls.getMethodBind("TileData", "set_flip_v", SET_FLIP_V_HASH)

        private const val GET_FLIP_V_HASH = 36873697L
        @JvmField
        val getFlipVBind =
            ObjectCalls.getMethodBind("TileData", "get_flip_v", GET_FLIP_V_HASH)

        private const val SET_TRANSPOSE_HASH = 2586408642L
        @JvmField
        val setTransposeBind =
            ObjectCalls.getMethodBind("TileData", "set_transpose", SET_TRANSPOSE_HASH)

        private const val GET_TRANSPOSE_HASH = 36873697L
        @JvmField
        val getTransposeBind =
            ObjectCalls.getMethodBind("TileData", "get_transpose", GET_TRANSPOSE_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("TileData", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("TileData", "get_material", GET_MATERIAL_HASH)

        private const val SET_TEXTURE_ORIGIN_HASH = 1130785943L
        @JvmField
        val setTextureOriginBind =
            ObjectCalls.getMethodBind("TileData", "set_texture_origin", SET_TEXTURE_ORIGIN_HASH)

        private const val GET_TEXTURE_ORIGIN_HASH = 3690982128L
        @JvmField
        val getTextureOriginBind =
            ObjectCalls.getMethodBind("TileData", "get_texture_origin", GET_TEXTURE_ORIGIN_HASH)

        private const val SET_MODULATE_HASH = 2920490490L
        @JvmField
        val setModulateBind =
            ObjectCalls.getMethodBind("TileData", "set_modulate", SET_MODULATE_HASH)

        private const val GET_MODULATE_HASH = 3444240500L
        @JvmField
        val getModulateBind =
            ObjectCalls.getMethodBind("TileData", "get_modulate", GET_MODULATE_HASH)

        private const val SET_Z_INDEX_HASH = 1286410249L
        @JvmField
        val setZIndexBind =
            ObjectCalls.getMethodBind("TileData", "set_z_index", SET_Z_INDEX_HASH)

        private const val GET_Z_INDEX_HASH = 3905245786L
        @JvmField
        val getZIndexBind =
            ObjectCalls.getMethodBind("TileData", "get_z_index", GET_Z_INDEX_HASH)

        private const val SET_Y_SORT_ORIGIN_HASH = 1286410249L
        @JvmField
        val setYSortOriginBind =
            ObjectCalls.getMethodBind("TileData", "set_y_sort_origin", SET_Y_SORT_ORIGIN_HASH)

        private const val GET_Y_SORT_ORIGIN_HASH = 3905245786L
        @JvmField
        val getYSortOriginBind =
            ObjectCalls.getMethodBind("TileData", "get_y_sort_origin", GET_Y_SORT_ORIGIN_HASH)

        private const val SET_OCCLUDER_POLYGONS_COUNT_HASH = 3937882851L
        @JvmField
        val setOccluderPolygonsCountBind =
            ObjectCalls.getMethodBind("TileData", "set_occluder_polygons_count", SET_OCCLUDER_POLYGONS_COUNT_HASH)

        private const val GET_OCCLUDER_POLYGONS_COUNT_HASH = 923996154L
        @JvmField
        val getOccluderPolygonsCountBind =
            ObjectCalls.getMethodBind("TileData", "get_occluder_polygons_count", GET_OCCLUDER_POLYGONS_COUNT_HASH)

        private const val ADD_OCCLUDER_POLYGON_HASH = 1286410249L
        @JvmField
        val addOccluderPolygonBind =
            ObjectCalls.getMethodBind("TileData", "add_occluder_polygon", ADD_OCCLUDER_POLYGON_HASH)

        private const val REMOVE_OCCLUDER_POLYGON_HASH = 3937882851L
        @JvmField
        val removeOccluderPolygonBind =
            ObjectCalls.getMethodBind("TileData", "remove_occluder_polygon", REMOVE_OCCLUDER_POLYGON_HASH)

        private const val SET_OCCLUDER_POLYGON_HASH = 164249167L
        @JvmField
        val setOccluderPolygonBind =
            ObjectCalls.getMethodBind("TileData", "set_occluder_polygon", SET_OCCLUDER_POLYGON_HASH)

        private const val GET_OCCLUDER_POLYGON_HASH = 971166743L
        @JvmField
        val getOccluderPolygonBind =
            ObjectCalls.getMethodBind("TileData", "get_occluder_polygon", GET_OCCLUDER_POLYGON_HASH)

        private const val SET_OCCLUDER_HASH = 914399637L
        @JvmField
        val setOccluderBind =
            ObjectCalls.getMethodBind("TileData", "set_occluder", SET_OCCLUDER_HASH)

        private const val GET_OCCLUDER_HASH = 2377324099L
        @JvmField
        val getOccluderBind =
            ObjectCalls.getMethodBind("TileData", "get_occluder", GET_OCCLUDER_HASH)

        private const val SET_CONSTANT_LINEAR_VELOCITY_HASH = 163021252L
        @JvmField
        val setConstantLinearVelocityBind =
            ObjectCalls.getMethodBind("TileData", "set_constant_linear_velocity", SET_CONSTANT_LINEAR_VELOCITY_HASH)

        private const val GET_CONSTANT_LINEAR_VELOCITY_HASH = 2299179447L
        @JvmField
        val getConstantLinearVelocityBind =
            ObjectCalls.getMethodBind("TileData", "get_constant_linear_velocity", GET_CONSTANT_LINEAR_VELOCITY_HASH)

        private const val SET_CONSTANT_ANGULAR_VELOCITY_HASH = 1602489585L
        @JvmField
        val setConstantAngularVelocityBind =
            ObjectCalls.getMethodBind("TileData", "set_constant_angular_velocity", SET_CONSTANT_ANGULAR_VELOCITY_HASH)

        private const val GET_CONSTANT_ANGULAR_VELOCITY_HASH = 2339986948L
        @JvmField
        val getConstantAngularVelocityBind =
            ObjectCalls.getMethodBind("TileData", "get_constant_angular_velocity", GET_CONSTANT_ANGULAR_VELOCITY_HASH)

        private const val SET_COLLISION_POLYGONS_COUNT_HASH = 3937882851L
        @JvmField
        val setCollisionPolygonsCountBind =
            ObjectCalls.getMethodBind("TileData", "set_collision_polygons_count", SET_COLLISION_POLYGONS_COUNT_HASH)

        private const val GET_COLLISION_POLYGONS_COUNT_HASH = 923996154L
        @JvmField
        val getCollisionPolygonsCountBind =
            ObjectCalls.getMethodBind("TileData", "get_collision_polygons_count", GET_COLLISION_POLYGONS_COUNT_HASH)

        private const val ADD_COLLISION_POLYGON_HASH = 1286410249L
        @JvmField
        val addCollisionPolygonBind =
            ObjectCalls.getMethodBind("TileData", "add_collision_polygon", ADD_COLLISION_POLYGON_HASH)

        private const val REMOVE_COLLISION_POLYGON_HASH = 3937882851L
        @JvmField
        val removeCollisionPolygonBind =
            ObjectCalls.getMethodBind("TileData", "remove_collision_polygon", REMOVE_COLLISION_POLYGON_HASH)

        private const val SET_COLLISION_POLYGON_POINTS_HASH = 3230546541L
        @JvmField
        val setCollisionPolygonPointsBind =
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_points", SET_COLLISION_POLYGON_POINTS_HASH)

        private const val GET_COLLISION_POLYGON_POINTS_HASH = 103942801L
        @JvmField
        val getCollisionPolygonPointsBind =
            ObjectCalls.getMethodBind("TileData", "get_collision_polygon_points", GET_COLLISION_POLYGON_POINTS_HASH)

        private const val SET_COLLISION_POLYGON_ONE_WAY_HASH = 1383440665L
        @JvmField
        val setCollisionPolygonOneWayBind =
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_one_way", SET_COLLISION_POLYGON_ONE_WAY_HASH)

        private const val IS_COLLISION_POLYGON_ONE_WAY_HASH = 2522259332L
        @JvmField
        val isCollisionPolygonOneWayBind =
            ObjectCalls.getMethodBind("TileData", "is_collision_polygon_one_way", IS_COLLISION_POLYGON_ONE_WAY_HASH)

        private const val SET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH = 3506521499L
        @JvmField
        val setCollisionPolygonOneWayMarginBind =
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_one_way_margin", SET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH)

        private const val GET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH = 3085491603L
        @JvmField
        val getCollisionPolygonOneWayMarginBind =
            ObjectCalls.getMethodBind("TileData", "get_collision_polygon_one_way_margin", GET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH)

        private const val SET_TERRAIN_SET_HASH = 1286410249L
        @JvmField
        val setTerrainSetBind =
            ObjectCalls.getMethodBind("TileData", "set_terrain_set", SET_TERRAIN_SET_HASH)

        private const val GET_TERRAIN_SET_HASH = 3905245786L
        @JvmField
        val getTerrainSetBind =
            ObjectCalls.getMethodBind("TileData", "get_terrain_set", GET_TERRAIN_SET_HASH)

        private const val SET_TERRAIN_HASH = 1286410249L
        @JvmField
        val setTerrainBind =
            ObjectCalls.getMethodBind("TileData", "set_terrain", SET_TERRAIN_HASH)

        private const val GET_TERRAIN_HASH = 3905245786L
        @JvmField
        val getTerrainBind =
            ObjectCalls.getMethodBind("TileData", "get_terrain", GET_TERRAIN_HASH)

        private const val SET_TERRAIN_PEERING_BIT_HASH = 1084452308L
        @JvmField
        val setTerrainPeeringBitBind =
            ObjectCalls.getMethodBind("TileData", "set_terrain_peering_bit", SET_TERRAIN_PEERING_BIT_HASH)

        private const val GET_TERRAIN_PEERING_BIT_HASH = 3831796792L
        @JvmField
        val getTerrainPeeringBitBind =
            ObjectCalls.getMethodBind("TileData", "get_terrain_peering_bit", GET_TERRAIN_PEERING_BIT_HASH)

        private const val IS_VALID_TERRAIN_PEERING_BIT_HASH = 845723972L
        @JvmField
        val isValidTerrainPeeringBitBind =
            ObjectCalls.getMethodBind("TileData", "is_valid_terrain_peering_bit", IS_VALID_TERRAIN_PEERING_BIT_HASH)

        private const val SET_NAVIGATION_POLYGON_HASH = 2224691167L
        @JvmField
        val setNavigationPolygonBind =
            ObjectCalls.getMethodBind("TileData", "set_navigation_polygon", SET_NAVIGATION_POLYGON_HASH)

        private const val GET_NAVIGATION_POLYGON_HASH = 2907127272L
        @JvmField
        val getNavigationPolygonBind =
            ObjectCalls.getMethodBind("TileData", "get_navigation_polygon", GET_NAVIGATION_POLYGON_HASH)

        private const val SET_PROBABILITY_HASH = 373806689L
        @JvmField
        val setProbabilityBind =
            ObjectCalls.getMethodBind("TileData", "set_probability", SET_PROBABILITY_HASH)

        private const val GET_PROBABILITY_HASH = 1740695150L
        @JvmField
        val getProbabilityBind =
            ObjectCalls.getMethodBind("TileData", "get_probability", GET_PROBABILITY_HASH)

        private const val SET_CUSTOM_DATA_HASH = 402577236L
        @JvmField
        val setCustomDataBind =
            ObjectCalls.getMethodBind("TileData", "set_custom_data", SET_CUSTOM_DATA_HASH)

        private const val GET_CUSTOM_DATA_HASH = 1868160156L
        @JvmField
        val getCustomDataBind =
            ObjectCalls.getMethodBind("TileData", "get_custom_data", GET_CUSTOM_DATA_HASH)

        private const val HAS_CUSTOM_DATA_HASH = 3927539163L
        @JvmField
        val hasCustomDataBind =
            ObjectCalls.getMethodBind("TileData", "has_custom_data", HAS_CUSTOM_DATA_HASH)

        private const val SET_CUSTOM_DATA_BY_LAYER_ID_HASH = 2152698145L
        @JvmField
        val setCustomDataByLayerIdBind =
            ObjectCalls.getMethodBind("TileData", "set_custom_data_by_layer_id", SET_CUSTOM_DATA_BY_LAYER_ID_HASH)

        private const val GET_CUSTOM_DATA_BY_LAYER_ID_HASH = 4227898402L
        @JvmField
        val getCustomDataByLayerIdBind =
            ObjectCalls.getMethodBind("TileData", "get_custom_data_by_layer_id", GET_CUSTOM_DATA_BY_LAYER_ID_HASH)
    }
}
