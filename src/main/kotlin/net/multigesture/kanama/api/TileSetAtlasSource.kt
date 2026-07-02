package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Exposes a 2D atlas texture as a set of tiles for a `TileSet` resource.
 *
 * Generated from Godot docs: TileSetAtlasSource
 */
class TileSetAtlasSource(handle: MemorySegment) : TileSetSource(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var margins: Vector2i
        @JvmName("marginsProperty")
        get() = getMargins()
        @JvmName("setMarginsProperty")
        set(value) = setMargins(value)

    var separation: Vector2i
        @JvmName("separationProperty")
        get() = getSeparation()
        @JvmName("setSeparationProperty")
        set(value) = setSeparation(value)

    var textureRegionSize: Vector2i
        @JvmName("textureRegionSizeProperty")
        get() = getTextureRegionSize()
        @JvmName("setTextureRegionSizeProperty")
        set(value) = setTextureRegionSize(value)

    var useTexturePadding: Boolean
        @JvmName("useTexturePaddingProperty")
        get() = getUseTexturePadding()
        @JvmName("setUseTexturePaddingProperty")
        set(value) = setUseTexturePadding(value)

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setMargins(margins: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setMarginsBind, handle, margins)
    }

    fun getMargins(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getMarginsBind, handle)
    }

    fun setSeparation(separation: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSeparationBind, handle, separation)
    }

    fun getSeparation(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSeparationBind, handle)
    }

    fun setTextureRegionSize(textureRegionSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTextureRegionSizeBind, handle, textureRegionSize)
    }

    fun getTextureRegionSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTextureRegionSizeBind, handle)
    }

    fun setUseTexturePadding(useTexturePadding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTexturePaddingBind, handle, useTexturePadding)
    }

    fun getUseTexturePadding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseTexturePaddingBind, handle)
    }

    fun createTile(atlasCoords: Vector2i, size: Vector2i) {
        ObjectCalls.ptrcallWithTwoVector2iArgs(createTileBind, handle, atlasCoords, size)
    }

    fun removeTile(atlasCoords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(removeTileBind, handle, atlasCoords)
    }

    fun moveTileInAtlas(atlasCoords: Vector2i, newAtlasCoords: Vector2i, newSize: Vector2i) {
        ObjectCalls.ptrcallWithThreeVector2iArgs(moveTileInAtlasBind, handle, atlasCoords, newAtlasCoords, newSize)
    }

    fun getTileSizeInAtlas(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileSizeInAtlasBind, handle, atlasCoords)
    }

    fun hasRoomForTile(atlasCoords: Vector2i, size: Vector2i, animationColumns: Int, animationSeparation: Vector2i, framesCount: Int, ignoredTile: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithTwoVector2iIntVector2iIntVector2iArgsRetBool(hasRoomForTileBind, handle, atlasCoords, size, animationColumns, animationSeparation, framesCount, ignoredTile)
    }

    fun getTilesToBeRemovedOnChange(texture: Texture2D?, margins: Vector2i, separation: Vector2i, textureRegionSize: Vector2i): List<Vector2> {
        return ObjectCalls.ptrcallWithObjectAndThreeVector2iArgsRetPackedVector2List(getTilesToBeRemovedOnChangeBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, margins, separation, textureRegionSize)
    }

    fun getTileAtCoords(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileAtCoordsBind, handle, atlasCoords)
    }

    fun hasTilesOutsideTexture(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasTilesOutsideTextureBind, handle)
    }

    fun clearTilesOutsideTexture() {
        ObjectCalls.ptrcallNoArgs(clearTilesOutsideTextureBind, handle)
    }

    fun setTileAnimationColumns(atlasCoords: Vector2i, frameColumns: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(setTileAnimationColumnsBind, handle, atlasCoords, frameColumns)
    }

    fun getTileAnimationColumns(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getTileAnimationColumnsBind, handle, atlasCoords)
    }

    fun setTileAnimationSeparation(atlasCoords: Vector2i, separation: Vector2i) {
        ObjectCalls.ptrcallWithTwoVector2iArgs(setTileAnimationSeparationBind, handle, atlasCoords, separation)
    }

    fun getTileAnimationSeparation(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileAnimationSeparationBind, handle, atlasCoords)
    }

    fun setTileAnimationSpeed(atlasCoords: Vector2i, speed: Double) {
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(setTileAnimationSpeedBind, handle, atlasCoords, speed)
    }

    fun getTileAnimationSpeed(atlasCoords: Vector2i): Double {
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getTileAnimationSpeedBind, handle, atlasCoords)
    }

    fun setTileAnimationMode(atlasCoords: Vector2i, mode: Long) {
        ObjectCalls.ptrcallWithVector2iAndLongArg(setTileAnimationModeBind, handle, atlasCoords, mode)
    }

    fun getTileAnimationMode(atlasCoords: Vector2i): Long {
        return ObjectCalls.ptrcallWithVector2iArgRetLong(getTileAnimationModeBind, handle, atlasCoords)
    }

    fun setTileAnimationFramesCount(atlasCoords: Vector2i, framesCount: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(setTileAnimationFramesCountBind, handle, atlasCoords, framesCount)
    }

    fun getTileAnimationFramesCount(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getTileAnimationFramesCountBind, handle, atlasCoords)
    }

    fun setTileAnimationFrameDuration(atlasCoords: Vector2i, frameIndex: Int, duration: Double) {
        ObjectCalls.ptrcallWithVector2iIntDoubleArgs(setTileAnimationFrameDurationBind, handle, atlasCoords, frameIndex, duration)
    }

    fun getTileAnimationFrameDuration(atlasCoords: Vector2i, frameIndex: Int): Double {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetDouble(getTileAnimationFrameDurationBind, handle, atlasCoords, frameIndex)
    }

    fun getTileAnimationTotalDuration(atlasCoords: Vector2i): Double {
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getTileAnimationTotalDurationBind, handle, atlasCoords)
    }

    fun createAlternativeTile(atlasCoords: Vector2i, alternativeIdOverride: Int = -1): Int {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetInt(createAlternativeTileBind, handle, atlasCoords, alternativeIdOverride)
    }

    fun removeAlternativeTile(atlasCoords: Vector2i, alternativeTile: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(removeAlternativeTileBind, handle, atlasCoords, alternativeTile)
    }

    fun setAlternativeTileId(atlasCoords: Vector2i, alternativeTile: Int, newId: Int) {
        ObjectCalls.ptrcallWithVector2iAndTwoIntArgs(setAlternativeTileIdBind, handle, atlasCoords, alternativeTile, newId)
    }

    fun getNextAlternativeTileId(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getNextAlternativeTileIdBind, handle, atlasCoords)
    }

    fun getTileData(atlasCoords: Vector2i, alternativeTile: Int): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithVector2iAndIntArgRetObject(getTileDataBind, handle, atlasCoords, alternativeTile))
    }

    fun getAtlasGridSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getAtlasGridSizeBind, handle)
    }

    fun getTileTextureRegion(atlasCoords: Vector2i, frame: Int = 0): Rect2i {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetRect2i(getTileTextureRegionBind, handle, atlasCoords, frame)
    }

    fun getRuntimeTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRuntimeTextureBind, handle))
    }

    fun getRuntimeTileTextureRegion(atlasCoords: Vector2i, frame: Int): Rect2i {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetRect2i(getRuntimeTileTextureRegionBind, handle, atlasCoords, frame)
    }

    companion object {
        const val TRANSFORM_FLIP_H: Long = 4096L
        const val TRANSFORM_FLIP_V: Long = 8192L
        const val TRANSFORM_TRANSPOSE: Long = 16384L
        const val TILE_ANIMATION_MODE_DEFAULT: Long = 0L
        const val TILE_ANIMATION_MODE_RANDOM_START_TIMES: Long = 1L
        const val TILE_ANIMATION_MODE_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileSetAtlasSource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileSetAtlasSource? =
            if (handle.address() == 0L) null else TileSetAtlasSource(handle)

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_MARGINS_HASH = 1130785943L
        private val setMarginsBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_margins", SET_MARGINS_HASH)
        }

        private const val GET_MARGINS_HASH = 3690982128L
        private val getMarginsBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_margins", GET_MARGINS_HASH)
        }

        private const val SET_SEPARATION_HASH = 1130785943L
        private val setSeparationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_separation", SET_SEPARATION_HASH)
        }

        private const val GET_SEPARATION_HASH = 3690982128L
        private val getSeparationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_separation", GET_SEPARATION_HASH)
        }

        private const val SET_TEXTURE_REGION_SIZE_HASH = 1130785943L
        private val setTextureRegionSizeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_texture_region_size", SET_TEXTURE_REGION_SIZE_HASH)
        }

        private const val GET_TEXTURE_REGION_SIZE_HASH = 3690982128L
        private val getTextureRegionSizeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_texture_region_size", GET_TEXTURE_REGION_SIZE_HASH)
        }

        private const val SET_USE_TEXTURE_PADDING_HASH = 2586408642L
        private val setUseTexturePaddingBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_use_texture_padding", SET_USE_TEXTURE_PADDING_HASH)
        }

        private const val GET_USE_TEXTURE_PADDING_HASH = 36873697L
        private val getUseTexturePaddingBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_use_texture_padding", GET_USE_TEXTURE_PADDING_HASH)
        }

        private const val CREATE_TILE_HASH = 190528769L
        private val createTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "create_tile", CREATE_TILE_HASH)
        }

        private const val REMOVE_TILE_HASH = 1130785943L
        private val removeTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "remove_tile", REMOVE_TILE_HASH)
        }

        private const val MOVE_TILE_IN_ATLAS_HASH = 3870111920L
        private val moveTileInAtlasBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "move_tile_in_atlas", MOVE_TILE_IN_ATLAS_HASH)
        }

        private const val GET_TILE_SIZE_IN_ATLAS_HASH = 3050897911L
        private val getTileSizeInAtlasBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_size_in_atlas", GET_TILE_SIZE_IN_ATLAS_HASH)
        }

        private const val HAS_ROOM_FOR_TILE_HASH = 3018597268L
        private val hasRoomForTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "has_room_for_tile", HAS_ROOM_FOR_TILE_HASH)
        }

        private const val GET_TILES_TO_BE_REMOVED_ON_CHANGE_HASH = 1240378054L
        private val getTilesToBeRemovedOnChangeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tiles_to_be_removed_on_change", GET_TILES_TO_BE_REMOVED_ON_CHANGE_HASH)
        }

        private const val GET_TILE_AT_COORDS_HASH = 3050897911L
        private val getTileAtCoordsBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_at_coords", GET_TILE_AT_COORDS_HASH)
        }

        private const val HAS_TILES_OUTSIDE_TEXTURE_HASH = 36873697L
        private val hasTilesOutsideTextureBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "has_tiles_outside_texture", HAS_TILES_OUTSIDE_TEXTURE_HASH)
        }

        private const val CLEAR_TILES_OUTSIDE_TEXTURE_HASH = 3218959716L
        private val clearTilesOutsideTextureBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "clear_tiles_outside_texture", CLEAR_TILES_OUTSIDE_TEXTURE_HASH)
        }

        private const val SET_TILE_ANIMATION_COLUMNS_HASH = 3200960707L
        private val setTileAnimationColumnsBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_columns", SET_TILE_ANIMATION_COLUMNS_HASH)
        }

        private const val GET_TILE_ANIMATION_COLUMNS_HASH = 2485466453L
        private val getTileAnimationColumnsBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_columns", GET_TILE_ANIMATION_COLUMNS_HASH)
        }

        private const val SET_TILE_ANIMATION_SEPARATION_HASH = 1941061099L
        private val setTileAnimationSeparationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_separation", SET_TILE_ANIMATION_SEPARATION_HASH)
        }

        private const val GET_TILE_ANIMATION_SEPARATION_HASH = 3050897911L
        private val getTileAnimationSeparationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_separation", GET_TILE_ANIMATION_SEPARATION_HASH)
        }

        private const val SET_TILE_ANIMATION_SPEED_HASH = 2262553149L
        private val setTileAnimationSpeedBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_speed", SET_TILE_ANIMATION_SPEED_HASH)
        }

        private const val GET_TILE_ANIMATION_SPEED_HASH = 719993801L
        private val getTileAnimationSpeedBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_speed", GET_TILE_ANIMATION_SPEED_HASH)
        }

        private const val SET_TILE_ANIMATION_MODE_HASH = 3192753483L
        private val setTileAnimationModeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_mode", SET_TILE_ANIMATION_MODE_HASH)
        }

        private const val GET_TILE_ANIMATION_MODE_HASH = 4025349959L
        private val getTileAnimationModeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_mode", GET_TILE_ANIMATION_MODE_HASH)
        }

        private const val SET_TILE_ANIMATION_FRAMES_COUNT_HASH = 3200960707L
        private val setTileAnimationFramesCountBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_frames_count", SET_TILE_ANIMATION_FRAMES_COUNT_HASH)
        }

        private const val GET_TILE_ANIMATION_FRAMES_COUNT_HASH = 2485466453L
        private val getTileAnimationFramesCountBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_frames_count", GET_TILE_ANIMATION_FRAMES_COUNT_HASH)
        }

        private const val SET_TILE_ANIMATION_FRAME_DURATION_HASH = 2843487787L
        private val setTileAnimationFrameDurationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_tile_animation_frame_duration", SET_TILE_ANIMATION_FRAME_DURATION_HASH)
        }

        private const val GET_TILE_ANIMATION_FRAME_DURATION_HASH = 1802448425L
        private val getTileAnimationFrameDurationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_frame_duration", GET_TILE_ANIMATION_FRAME_DURATION_HASH)
        }

        private const val GET_TILE_ANIMATION_TOTAL_DURATION_HASH = 719993801L
        private val getTileAnimationTotalDurationBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_animation_total_duration", GET_TILE_ANIMATION_TOTAL_DURATION_HASH)
        }

        private const val CREATE_ALTERNATIVE_TILE_HASH = 2226298068L
        private val createAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "create_alternative_tile", CREATE_ALTERNATIVE_TILE_HASH)
        }

        private const val REMOVE_ALTERNATIVE_TILE_HASH = 3200960707L
        private val removeAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "remove_alternative_tile", REMOVE_ALTERNATIVE_TILE_HASH)
        }

        private const val SET_ALTERNATIVE_TILE_ID_HASH = 1499785778L
        private val setAlternativeTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "set_alternative_tile_id", SET_ALTERNATIVE_TILE_ID_HASH)
        }

        private const val GET_NEXT_ALTERNATIVE_TILE_ID_HASH = 2485466453L
        private val getNextAlternativeTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_next_alternative_tile_id", GET_NEXT_ALTERNATIVE_TILE_ID_HASH)
        }

        private const val GET_TILE_DATA_HASH = 3534028207L
        private val getTileDataBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_data", GET_TILE_DATA_HASH)
        }

        private const val GET_ATLAS_GRID_SIZE_HASH = 3690982128L
        private val getAtlasGridSizeBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_atlas_grid_size", GET_ATLAS_GRID_SIZE_HASH)
        }

        private const val GET_TILE_TEXTURE_REGION_HASH = 241857547L
        private val getTileTextureRegionBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_tile_texture_region", GET_TILE_TEXTURE_REGION_HASH)
        }

        private const val GET_RUNTIME_TEXTURE_HASH = 3635182373L
        private val getRuntimeTextureBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_runtime_texture", GET_RUNTIME_TEXTURE_HASH)
        }

        private const val GET_RUNTIME_TILE_TEXTURE_REGION_HASH = 104874263L
        private val getRuntimeTileTextureRegionBind by lazy {
            ObjectCalls.getMethodBind("TileSetAtlasSource", "get_runtime_tile_texture_region", GET_RUNTIME_TILE_TEXTURE_REGION_HASH)
        }
    }
}
