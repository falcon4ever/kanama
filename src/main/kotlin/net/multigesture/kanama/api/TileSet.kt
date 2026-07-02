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

    fun getNextSourceId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNextSourceIdBind, handle)
    }

    fun addSource(source: TileSetSource?, atlasSourceIdOverride: Int = -1): Int {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(addSourceBind, handle, source?.requireOpenHandle() ?: MemorySegment.NULL, atlasSourceIdOverride)
    }

    fun removeSource(sourceId: Int) {
        ObjectCalls.ptrcallWithIntArg(removeSourceBind, handle, sourceId)
    }

    fun setSourceId(sourceId: Int, newSourceId: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSourceIdBind, handle, sourceId, newSourceId)
    }

    fun getSourceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSourceCountBind, handle)
    }

    fun getSourceId(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSourceIdBind, handle, index)
    }

    fun hasSource(sourceId: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSourceBind, handle, sourceId)
    }

    fun getSource(sourceId: Int): TileSetSource? {
        return TileSetSource.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSourceBind, handle, sourceId))
    }

    fun setTileShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileShapeBind, handle, shape)
    }

    fun getTileShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileShapeBind, handle)
    }

    fun setTileLayout(layout: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileLayoutBind, handle, layout)
    }

    fun getTileLayout(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileLayoutBind, handle)
    }

    fun setTileOffsetAxis(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTileOffsetAxisBind, handle, alignment)
    }

    fun getTileOffsetAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTileOffsetAxisBind, handle)
    }

    fun setTileSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTileSizeBind, handle, size)
    }

    fun getTileSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTileSizeBind, handle)
    }

    fun setUvClipping(uvClipping: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUvClippingBind, handle, uvClipping)
    }

    fun isUvClipping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUvClippingBind, handle)
    }

    fun getOcclusionLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOcclusionLayersCountBind, handle)
    }

    fun addOcclusionLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addOcclusionLayerBind, handle, toPosition)
    }

    fun moveOcclusionLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveOcclusionLayerBind, handle, layerIndex, toPosition)
    }

    fun removeOcclusionLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeOcclusionLayerBind, handle, layerIndex)
    }

    fun setOcclusionLayerLightMask(layerIndex: Int, lightMask: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setOcclusionLayerLightMaskBind, handle, layerIndex, lightMask)
    }

    fun getOcclusionLayerLightMask(layerIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getOcclusionLayerLightMaskBind, handle, layerIndex)
    }

    fun setOcclusionLayerSdfCollision(layerIndex: Int, sdfCollision: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setOcclusionLayerSdfCollisionBind, handle, layerIndex, sdfCollision)
    }

    fun getOcclusionLayerSdfCollision(layerIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getOcclusionLayerSdfCollisionBind, handle, layerIndex)
    }

    fun getPhysicsLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsLayersCountBind, handle)
    }

    fun addPhysicsLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addPhysicsLayerBind, handle, toPosition)
    }

    fun movePhysicsLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(movePhysicsLayerBind, handle, layerIndex, toPosition)
    }

    fun removePhysicsLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removePhysicsLayerBind, handle, layerIndex)
    }

    fun setPhysicsLayerCollisionLayer(layerIndex: Int, layer: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setPhysicsLayerCollisionLayerBind, handle, layerIndex, layer)
    }

    fun getPhysicsLayerCollisionLayer(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getPhysicsLayerCollisionLayerBind, handle, layerIndex)
    }

    fun setPhysicsLayerCollisionMask(layerIndex: Int, mask: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setPhysicsLayerCollisionMaskBind, handle, layerIndex, mask)
    }

    fun getPhysicsLayerCollisionMask(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getPhysicsLayerCollisionMaskBind, handle, layerIndex)
    }

    fun setPhysicsLayerCollisionPriority(layerIndex: Int, priority: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPhysicsLayerCollisionPriorityBind, handle, layerIndex, priority)
    }

    fun getPhysicsLayerCollisionPriority(layerIndex: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPhysicsLayerCollisionPriorityBind, handle, layerIndex)
    }

    fun setPhysicsLayerPhysicsMaterial(layerIndex: Int, physicsMaterial: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setPhysicsLayerPhysicsMaterialBind, handle, layerIndex, physicsMaterial?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getPhysicsLayerPhysicsMaterial(layerIndex: Int): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPhysicsLayerPhysicsMaterialBind, handle, layerIndex))
    }

    fun getTerrainSetsCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTerrainSetsCountBind, handle)
    }

    fun addTerrainSet(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addTerrainSetBind, handle, toPosition)
    }

    fun moveTerrainSet(terrainSet: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveTerrainSetBind, handle, terrainSet, toPosition)
    }

    fun removeTerrainSet(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTerrainSetBind, handle, terrainSet)
    }

    fun setTerrainSetMode(terrainSet: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTerrainSetModeBind, handle, terrainSet, mode)
    }

    fun getTerrainSetMode(terrainSet: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTerrainSetModeBind, handle, terrainSet)
    }

    fun getTerrainsCount(terrainSet: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTerrainsCountBind, handle, terrainSet)
    }

    fun addTerrain(terrainSet: Int, toPosition: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(addTerrainBind, handle, terrainSet, toPosition)
    }

    fun moveTerrain(terrainSet: Int, terrainIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithThreeIntArgs(moveTerrainBind, handle, terrainSet, terrainIndex, toPosition)
    }

    fun removeTerrain(terrainSet: Int, terrainIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(removeTerrainBind, handle, terrainSet, terrainIndex)
    }

    fun clearTerrains(terrainSet: Int) {
        ObjectCalls.ptrcallWithIntArg(clearTerrainsBind, handle, terrainSet)
    }

    fun setTerrainName(terrainSet: Int, terrainIndex: Int, name: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(setTerrainNameBind, handle, terrainSet, terrainIndex, name)
    }

    fun getTerrainName(terrainSet: Int, terrainIndex: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getTerrainNameBind, handle, terrainSet, terrainIndex)
    }

    fun setTerrainColor(terrainSet: Int, terrainIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setTerrainColorBind, handle, terrainSet, terrainIndex, color)
    }

    fun getTerrainColor(terrainSet: Int, terrainIndex: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getTerrainColorBind, handle, terrainSet, terrainIndex)
    }

    fun getNavigationLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNavigationLayersCountBind, handle)
    }

    fun addNavigationLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addNavigationLayerBind, handle, toPosition)
    }

    fun moveNavigationLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveNavigationLayerBind, handle, layerIndex, toPosition)
    }

    fun removeNavigationLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeNavigationLayerBind, handle, layerIndex)
    }

    fun setNavigationLayerLayers(layerIndex: Int, layers: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setNavigationLayerLayersBind, handle, layerIndex, layers)
    }

    fun getNavigationLayerLayers(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getNavigationLayerLayersBind, handle, layerIndex)
    }

    fun setNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setNavigationLayerLayerValueBind, handle, layerIndex, layerNumber, value)
    }

    fun getNavigationLayerLayerValue(layerIndex: Int, layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(getNavigationLayerLayerValueBind, handle, layerIndex, layerNumber)
    }

    fun getCustomDataLayersCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCustomDataLayersCountBind, handle)
    }

    fun addCustomDataLayer(toPosition: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addCustomDataLayerBind, handle, toPosition)
    }

    fun moveCustomDataLayer(layerIndex: Int, toPosition: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveCustomDataLayerBind, handle, layerIndex, toPosition)
    }

    fun removeCustomDataLayer(layerIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(removeCustomDataLayerBind, handle, layerIndex)
    }

    fun getCustomDataLayerByName(layerName: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(getCustomDataLayerByNameBind, handle, layerName)
    }

    fun setCustomDataLayerName(layerIndex: Int, layerName: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setCustomDataLayerNameBind, handle, layerIndex, layerName)
    }

    fun hasCustomDataLayerByName(layerName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasCustomDataLayerByNameBind, handle, layerName)
    }

    fun getCustomDataLayerName(layerIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getCustomDataLayerNameBind, handle, layerIndex)
    }

    fun setCustomDataLayerType(layerIndex: Int, layerType: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCustomDataLayerTypeBind, handle, layerIndex, layerType)
    }

    fun getCustomDataLayerType(layerIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCustomDataLayerTypeBind, handle, layerIndex)
    }

    fun setSourceLevelTileProxy(sourceFrom: Int, sourceTo: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSourceLevelTileProxyBind, handle, sourceFrom, sourceTo)
    }

    fun getSourceLevelTileProxy(sourceFrom: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSourceLevelTileProxyBind, handle, sourceFrom)
    }

    fun hasSourceLevelTileProxy(sourceFrom: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSourceLevelTileProxyBind, handle, sourceFrom)
    }

    fun removeSourceLevelTileProxy(sourceFrom: Int) {
        ObjectCalls.ptrcallWithIntArg(removeSourceLevelTileProxyBind, handle, sourceFrom)
    }

    fun setCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, sourceTo: Int, coordsTo: Vector2i) {
        ObjectCalls.ptrcallWithIntVector2iIntVector2iArgs(setCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom, sourceTo, coordsTo)
    }

    fun getCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iArgsRetArray(getCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    fun hasCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithIntAndVector2iArgRetBool(hasCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    fun removeCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i) {
        ObjectCalls.ptrcallWithIntAndVector2iArg(removeCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
    }

    fun setAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int, sourceTo: Int, coordsTo: Vector2i, alternativeTo: Int) {
        ObjectCalls.ptrcallWithIntVector2iTwoIntVector2iIntArgs(setAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom, sourceTo, coordsTo, alternativeTo)
    }

    fun getAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(getAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    fun hasAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): Boolean {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetBool(hasAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    fun removeAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int) {
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(removeAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    fun mapTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(mapTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
    }

    fun cleanupInvalidTileProxies() {
        ObjectCalls.ptrcallNoArgs(cleanupInvalidTileProxiesBind, handle)
    }

    fun clearTileProxies() {
        ObjectCalls.ptrcallNoArgs(clearTileProxiesBind, handle)
    }

    fun addPattern(pattern: TileMapPattern?, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(addPatternBind, handle, pattern?.requireOpenHandle() ?: MemorySegment.NULL, index)
    }

    fun getPattern(index: Int = -1): TileMapPattern? {
        return TileMapPattern.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPatternBind, handle, index))
    }

    fun removePattern(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removePatternBind, handle, index)
    }

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
