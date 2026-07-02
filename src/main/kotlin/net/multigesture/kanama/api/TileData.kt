package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Settings for a single tile in a `TileSet`.
 *
 * Generated from Godot docs: TileData
 */
class TileData(handle: MemorySegment) : GodotObject(handle) {
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

    fun setFlipH(flipH: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, handle, flipH)
    }

    fun getFlipH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipHBind, handle)
    }

    fun setFlipV(flipV: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, handle, flipV)
    }

    fun getFlipV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipVBind, handle)
    }

    fun setTranspose(transpose: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransposeBind, handle, transpose)
    }

    fun getTranspose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getTransposeBind, handle)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    fun setTextureOrigin(textureOrigin: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTextureOriginBind, handle, textureOrigin)
    }

    fun getTextureOrigin(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTextureOriginBind, handle)
    }

    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    fun setZIndex(zIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setZIndexBind, handle, zIndex)
    }

    fun getZIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getZIndexBind, handle)
    }

    fun setYSortOrigin(ySortOrigin: Int) {
        ObjectCalls.ptrcallWithIntArg(setYSortOriginBind, handle, ySortOrigin)
    }

    fun getYSortOrigin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getYSortOriginBind, handle)
    }

    fun setOccluderPolygonsCount(layerId: Int, polygonsCount: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOccluderPolygonsCountBind, handle, layerId, polygonsCount)
    }

    fun getOccluderPolygonsCount(layerId: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOccluderPolygonsCountBind, handle, layerId)
    }

    fun addOccluderPolygon(layerId: Int) {
        ObjectCalls.ptrcallWithIntArg(addOccluderPolygonBind, handle, layerId)
    }

    fun removeOccluderPolygon(layerId: Int, polygonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(removeOccluderPolygonBind, handle, layerId, polygonIndex)
    }

    fun setOccluderPolygon(layerId: Int, polygonIndex: Int, polygon: OccluderPolygon2D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(setOccluderPolygonBind, handle, layerId, polygonIndex, polygon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getOccluderPolygon(layerId: Int, polygonIndex: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): OccluderPolygon2D? {
        return OccluderPolygon2D.wrap(ObjectCalls.ptrcallWithTwoIntAndThreeBoolArgsRetObject(getOccluderPolygonBind, handle, layerId, polygonIndex, flipH, flipV, transpose))
    }

    fun setOccluder(layerId: Int, occluderPolygon: OccluderPolygon2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setOccluderBind, handle, layerId, occluderPolygon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getOccluder(layerId: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): OccluderPolygon2D? {
        return OccluderPolygon2D.wrap(ObjectCalls.ptrcallWithIntAndThreeBoolArgsRetObject(getOccluderBind, handle, layerId, flipH, flipV, transpose))
    }

    fun setConstantLinearVelocity(layerId: Int, velocity: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setConstantLinearVelocityBind, handle, layerId, velocity)
    }

    fun getConstantLinearVelocity(layerId: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getConstantLinearVelocityBind, handle, layerId)
    }

    fun setConstantAngularVelocity(layerId: Int, velocity: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setConstantAngularVelocityBind, handle, layerId, velocity)
    }

    fun getConstantAngularVelocity(layerId: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getConstantAngularVelocityBind, handle, layerId)
    }

    fun setCollisionPolygonsCount(layerId: Int, polygonsCount: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCollisionPolygonsCountBind, handle, layerId, polygonsCount)
    }

    fun getCollisionPolygonsCount(layerId: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCollisionPolygonsCountBind, handle, layerId)
    }

    fun addCollisionPolygon(layerId: Int) {
        ObjectCalls.ptrcallWithIntArg(addCollisionPolygonBind, handle, layerId)
    }

    fun removeCollisionPolygon(layerId: Int, polygonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(removeCollisionPolygonBind, handle, layerId, polygonIndex)
    }

    fun setCollisionPolygonPoints(layerId: Int, polygonIndex: Int, polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithTwoIntAndPackedVector2ListArg(setCollisionPolygonPointsBind, handle, layerId, polygonIndex, polygon)
    }

    fun getCollisionPolygonPoints(layerId: Int, polygonIndex: Int): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoIntArgsRetPackedVector2List(getCollisionPolygonPointsBind, handle, layerId, polygonIndex)
    }

    fun setCollisionPolygonOneWay(layerId: Int, polygonIndex: Int, oneWay: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setCollisionPolygonOneWayBind, handle, layerId, polygonIndex, oneWay)
    }

    fun isCollisionPolygonOneWay(layerId: Int, polygonIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isCollisionPolygonOneWayBind, handle, layerId, polygonIndex)
    }

    fun setCollisionPolygonOneWayMargin(layerId: Int, polygonIndex: Int, oneWayMargin: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(setCollisionPolygonOneWayMarginBind, handle, layerId, polygonIndex, oneWayMargin)
    }

    fun getCollisionPolygonOneWayMargin(layerId: Int, polygonIndex: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getCollisionPolygonOneWayMarginBind, handle, layerId, polygonIndex)
    }

    fun setTerrainSet(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(setTerrainSetBind, handle, terrainSet)
    }

    fun getTerrainSet(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTerrainSetBind, handle)
    }

    fun setTerrain(terrain: Int) {
        ObjectCalls.ptrcallWithIntArg(setTerrainBind, handle, terrain)
    }

    fun getTerrain(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTerrainBind, handle)
    }

    fun setTerrainPeeringBit(peeringBit: Long, terrain: Int) {
        ObjectCalls.ptrcallWithLongAndIntArgs(setTerrainPeeringBitBind, handle, peeringBit, terrain)
    }

    fun getTerrainPeeringBit(peeringBit: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getTerrainPeeringBitBind, handle, peeringBit)
    }

    fun isValidTerrainPeeringBit(peeringBit: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isValidTerrainPeeringBitBind, handle, peeringBit)
    }

    fun setNavigationPolygon(layerId: Int, navigationPolygon: NavigationPolygon?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setNavigationPolygonBind, handle, layerId, navigationPolygon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getNavigationPolygon(layerId: Int, flipH: Boolean = false, flipV: Boolean = false, transpose: Boolean = false): NavigationPolygon? {
        return NavigationPolygon.wrap(ObjectCalls.ptrcallWithIntAndThreeBoolArgsRetObject(getNavigationPolygonBind, handle, layerId, flipH, flipV, transpose))
    }

    fun setProbability(probability: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setProbabilityBind, handle, probability)
    }

    fun getProbability(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProbabilityBind, handle)
    }

    fun setCustomData(layerName: String, value: Any?) {
        ObjectCalls.ptrcallWithStringAndVariantArg(setCustomDataBind, handle, layerName, value)
    }

    fun getCustomData(layerName: String): Any? {
        return ObjectCalls.ptrcallWithStringArgRetVariantScalar(getCustomDataBind, handle, layerName)
    }

    fun hasCustomData(layerName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasCustomDataBind, handle, layerName)
    }

    fun setCustomDataByLayerId(layerId: Int, value: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setCustomDataByLayerIdBind, handle, layerId, value)
    }

    fun getCustomDataByLayerId(layerId: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getCustomDataByLayerIdBind, handle, layerId)
    }

    object Signals {
        const val changed: String = "changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileData? =
            if (handle.address() == 0L) null else TileData(handle)

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val GET_FLIP_H_HASH = 36873697L
        private val getFlipHBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_flip_h", GET_FLIP_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val GET_FLIP_V_HASH = 36873697L
        private val getFlipVBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_flip_v", GET_FLIP_V_HASH)
        }

        private const val SET_TRANSPOSE_HASH = 2586408642L
        private val setTransposeBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_transpose", SET_TRANSPOSE_HASH)
        }

        private const val GET_TRANSPOSE_HASH = 36873697L
        private val getTransposeBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_transpose", GET_TRANSPOSE_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_material", GET_MATERIAL_HASH)
        }

        private const val SET_TEXTURE_ORIGIN_HASH = 1130785943L
        private val setTextureOriginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_texture_origin", SET_TEXTURE_ORIGIN_HASH)
        }

        private const val GET_TEXTURE_ORIGIN_HASH = 3690982128L
        private val getTextureOriginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_texture_origin", GET_TEXTURE_ORIGIN_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_Z_INDEX_HASH = 1286410249L
        private val setZIndexBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_z_index", SET_Z_INDEX_HASH)
        }

        private const val GET_Z_INDEX_HASH = 3905245786L
        private val getZIndexBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_z_index", GET_Z_INDEX_HASH)
        }

        private const val SET_Y_SORT_ORIGIN_HASH = 1286410249L
        private val setYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_y_sort_origin", SET_Y_SORT_ORIGIN_HASH)
        }

        private const val GET_Y_SORT_ORIGIN_HASH = 3905245786L
        private val getYSortOriginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_y_sort_origin", GET_Y_SORT_ORIGIN_HASH)
        }

        private const val SET_OCCLUDER_POLYGONS_COUNT_HASH = 3937882851L
        private val setOccluderPolygonsCountBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_occluder_polygons_count", SET_OCCLUDER_POLYGONS_COUNT_HASH)
        }

        private const val GET_OCCLUDER_POLYGONS_COUNT_HASH = 923996154L
        private val getOccluderPolygonsCountBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_occluder_polygons_count", GET_OCCLUDER_POLYGONS_COUNT_HASH)
        }

        private const val ADD_OCCLUDER_POLYGON_HASH = 1286410249L
        private val addOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "add_occluder_polygon", ADD_OCCLUDER_POLYGON_HASH)
        }

        private const val REMOVE_OCCLUDER_POLYGON_HASH = 3937882851L
        private val removeOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "remove_occluder_polygon", REMOVE_OCCLUDER_POLYGON_HASH)
        }

        private const val SET_OCCLUDER_POLYGON_HASH = 164249167L
        private val setOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_occluder_polygon", SET_OCCLUDER_POLYGON_HASH)
        }

        private const val GET_OCCLUDER_POLYGON_HASH = 971166743L
        private val getOccluderPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_occluder_polygon", GET_OCCLUDER_POLYGON_HASH)
        }

        private const val SET_OCCLUDER_HASH = 914399637L
        private val setOccluderBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_occluder", SET_OCCLUDER_HASH)
        }

        private const val GET_OCCLUDER_HASH = 2377324099L
        private val getOccluderBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_occluder", GET_OCCLUDER_HASH)
        }

        private const val SET_CONSTANT_LINEAR_VELOCITY_HASH = 163021252L
        private val setConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_constant_linear_velocity", SET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_LINEAR_VELOCITY_HASH = 2299179447L
        private val getConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_constant_linear_velocity", GET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val SET_CONSTANT_ANGULAR_VELOCITY_HASH = 1602489585L
        private val setConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_constant_angular_velocity", SET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_ANGULAR_VELOCITY_HASH = 2339986948L
        private val getConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_constant_angular_velocity", GET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_COLLISION_POLYGONS_COUNT_HASH = 3937882851L
        private val setCollisionPolygonsCountBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_collision_polygons_count", SET_COLLISION_POLYGONS_COUNT_HASH)
        }

        private const val GET_COLLISION_POLYGONS_COUNT_HASH = 923996154L
        private val getCollisionPolygonsCountBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_collision_polygons_count", GET_COLLISION_POLYGONS_COUNT_HASH)
        }

        private const val ADD_COLLISION_POLYGON_HASH = 1286410249L
        private val addCollisionPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "add_collision_polygon", ADD_COLLISION_POLYGON_HASH)
        }

        private const val REMOVE_COLLISION_POLYGON_HASH = 3937882851L
        private val removeCollisionPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "remove_collision_polygon", REMOVE_COLLISION_POLYGON_HASH)
        }

        private const val SET_COLLISION_POLYGON_POINTS_HASH = 3230546541L
        private val setCollisionPolygonPointsBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_points", SET_COLLISION_POLYGON_POINTS_HASH)
        }

        private const val GET_COLLISION_POLYGON_POINTS_HASH = 103942801L
        private val getCollisionPolygonPointsBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_collision_polygon_points", GET_COLLISION_POLYGON_POINTS_HASH)
        }

        private const val SET_COLLISION_POLYGON_ONE_WAY_HASH = 1383440665L
        private val setCollisionPolygonOneWayBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_one_way", SET_COLLISION_POLYGON_ONE_WAY_HASH)
        }

        private const val IS_COLLISION_POLYGON_ONE_WAY_HASH = 2522259332L
        private val isCollisionPolygonOneWayBind by lazy {
            ObjectCalls.getMethodBind("TileData", "is_collision_polygon_one_way", IS_COLLISION_POLYGON_ONE_WAY_HASH)
        }

        private const val SET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH = 3506521499L
        private val setCollisionPolygonOneWayMarginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_collision_polygon_one_way_margin", SET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH)
        }

        private const val GET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH = 3085491603L
        private val getCollisionPolygonOneWayMarginBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_collision_polygon_one_way_margin", GET_COLLISION_POLYGON_ONE_WAY_MARGIN_HASH)
        }

        private const val SET_TERRAIN_SET_HASH = 1286410249L
        private val setTerrainSetBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_terrain_set", SET_TERRAIN_SET_HASH)
        }

        private const val GET_TERRAIN_SET_HASH = 3905245786L
        private val getTerrainSetBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_terrain_set", GET_TERRAIN_SET_HASH)
        }

        private const val SET_TERRAIN_HASH = 1286410249L
        private val setTerrainBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_terrain", SET_TERRAIN_HASH)
        }

        private const val GET_TERRAIN_HASH = 3905245786L
        private val getTerrainBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_terrain", GET_TERRAIN_HASH)
        }

        private const val SET_TERRAIN_PEERING_BIT_HASH = 1084452308L
        private val setTerrainPeeringBitBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_terrain_peering_bit", SET_TERRAIN_PEERING_BIT_HASH)
        }

        private const val GET_TERRAIN_PEERING_BIT_HASH = 3831796792L
        private val getTerrainPeeringBitBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_terrain_peering_bit", GET_TERRAIN_PEERING_BIT_HASH)
        }

        private const val IS_VALID_TERRAIN_PEERING_BIT_HASH = 845723972L
        private val isValidTerrainPeeringBitBind by lazy {
            ObjectCalls.getMethodBind("TileData", "is_valid_terrain_peering_bit", IS_VALID_TERRAIN_PEERING_BIT_HASH)
        }

        private const val SET_NAVIGATION_POLYGON_HASH = 2224691167L
        private val setNavigationPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_navigation_polygon", SET_NAVIGATION_POLYGON_HASH)
        }

        private const val GET_NAVIGATION_POLYGON_HASH = 2907127272L
        private val getNavigationPolygonBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_navigation_polygon", GET_NAVIGATION_POLYGON_HASH)
        }

        private const val SET_PROBABILITY_HASH = 373806689L
        private val setProbabilityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_probability", SET_PROBABILITY_HASH)
        }

        private const val GET_PROBABILITY_HASH = 1740695150L
        private val getProbabilityBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_probability", GET_PROBABILITY_HASH)
        }

        private const val SET_CUSTOM_DATA_HASH = 402577236L
        private val setCustomDataBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_custom_data", SET_CUSTOM_DATA_HASH)
        }

        private const val GET_CUSTOM_DATA_HASH = 1868160156L
        private val getCustomDataBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_custom_data", GET_CUSTOM_DATA_HASH)
        }

        private const val HAS_CUSTOM_DATA_HASH = 3927539163L
        private val hasCustomDataBind by lazy {
            ObjectCalls.getMethodBind("TileData", "has_custom_data", HAS_CUSTOM_DATA_HASH)
        }

        private const val SET_CUSTOM_DATA_BY_LAYER_ID_HASH = 2152698145L
        private val setCustomDataByLayerIdBind by lazy {
            ObjectCalls.getMethodBind("TileData", "set_custom_data_by_layer_id", SET_CUSTOM_DATA_BY_LAYER_ID_HASH)
        }

        private const val GET_CUSTOM_DATA_BY_LAYER_ID_HASH = 4227898402L
        private val getCustomDataByLayerIdBind by lazy {
            ObjectCalls.getMethodBind("TileData", "get_custom_data_by_layer_id", GET_CUSTOM_DATA_BY_LAYER_ID_HASH)
        }
    }
}
