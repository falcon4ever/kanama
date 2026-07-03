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

    /**
     * The atlas texture.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The atlas texture.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * Margins, in pixels, to offset the origin of the grid in the texture.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_margins
     */
    fun setMargins(margins: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setMarginsBind, handle, margins)
    }

    /**
     * Margins, in pixels, to offset the origin of the grid in the texture.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_margins
     */
    fun getMargins(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getMarginsBind, handle)
    }

    /**
     * Separation, in pixels, between each tile texture region of the grid.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_separation
     */
    fun setSeparation(separation: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSeparationBind, handle, separation)
    }

    /**
     * Separation, in pixels, between each tile texture region of the grid.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_separation
     */
    fun getSeparation(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSeparationBind, handle)
    }

    /**
     * The base tile size in the texture (in pixel). This size must be bigger than or equal to the
     * TileSet's `tile_size` value.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_texture_region_size
     */
    fun setTextureRegionSize(textureRegionSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setTextureRegionSizeBind, handle, textureRegionSize)
    }

    /**
     * The base tile size in the texture (in pixel). This size must be bigger than or equal to the
     * TileSet's `tile_size` value.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_texture_region_size
     */
    fun getTextureRegionSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getTextureRegionSizeBind, handle)
    }

    /**
     * If `true`, generates an internal texture with an additional one pixel padding around each tile.
     * Texture padding avoids a common artifact where lines appear between tiles. Disabling this
     * setting might lead a small performance improvement, as generating the internal texture requires
     * both memory and processing time when the TileSetAtlasSource resource is modified.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_use_texture_padding
     */
    fun setUseTexturePadding(useTexturePadding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTexturePaddingBind, handle, useTexturePadding)
    }

    /**
     * If `true`, generates an internal texture with an additional one pixel padding around each tile.
     * Texture padding avoids a common artifact where lines appear between tiles. Disabling this
     * setting might lead a small performance improvement, as generating the internal texture requires
     * both memory and processing time when the TileSetAtlasSource resource is modified.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_use_texture_padding
     */
    fun getUseTexturePadding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseTexturePaddingBind, handle)
    }

    /**
     * Creates a new tile at coordinates `atlas_coords` with the given `size`.
     *
     * Generated from Godot docs: TileSetAtlasSource.create_tile
     */
    fun createTile(atlasCoords: Vector2i, size: Vector2i) {
        ObjectCalls.ptrcallWithTwoVector2iArgs(createTileBind, handle, atlasCoords, size)
    }

    /**
     * Remove a tile and its alternative at coordinates `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.remove_tile
     */
    fun removeTile(atlasCoords: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(removeTileBind, handle, atlasCoords)
    }

    /**
     * Move the tile and its alternatives at the `atlas_coords` coordinates to the `new_atlas_coords`
     * coordinates with the `new_size` size. This functions will fail if a tile is already present in
     * the given area. If `new_atlas_coords` is `Vector2i(-1, -1)`, keeps the tile's coordinates. If
     * `new_size` is `Vector2i(-1, -1)`, keeps the tile's size. To avoid an error, first check if a
     * move is possible using `has_room_for_tile`.
     *
     * Generated from Godot docs: TileSetAtlasSource.move_tile_in_atlas
     */
    fun moveTileInAtlas(atlasCoords: Vector2i, newAtlasCoords: Vector2i, newSize: Vector2i) {
        ObjectCalls.ptrcallWithThreeVector2iArgs(moveTileInAtlasBind, handle, atlasCoords, newAtlasCoords, newSize)
    }

    /**
     * Returns the size of the tile (in the grid coordinates system) at coordinates `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_size_in_atlas
     */
    fun getTileSizeInAtlas(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileSizeInAtlasBind, handle, atlasCoords)
    }

    /**
     * Returns whether there is enough room in an atlas to create/modify a tile with the given
     * properties. If `ignored_tile` is provided, act as is the given tile was not present in the
     * atlas. This may be used when you want to modify a tile's properties.
     *
     * Generated from Godot docs: TileSetAtlasSource.has_room_for_tile
     */
    fun hasRoomForTile(atlasCoords: Vector2i, size: Vector2i, animationColumns: Int, animationSeparation: Vector2i, framesCount: Int, ignoredTile: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithTwoVector2iIntVector2iIntVector2iArgsRetBool(hasRoomForTileBind, handle, atlasCoords, size, animationColumns, animationSeparation, framesCount, ignoredTile)
    }

    /**
     * Returns an array of tiles coordinates ID that will be automatically removed when modifying one
     * or several of those properties: `texture`, `margins`, `separation` or `texture_region_size`.
     * This can be used to undo changes that would have caused tiles data loss.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tiles_to_be_removed_on_change
     */
    fun getTilesToBeRemovedOnChange(texture: Texture2D?, margins: Vector2i, separation: Vector2i, textureRegionSize: Vector2i): List<Vector2> {
        return ObjectCalls.ptrcallWithObjectAndThreeVector2iArgsRetPackedVector2List(getTilesToBeRemovedOnChangeBind, handle, texture?.requireOpenHandle() ?: MemorySegment.NULL, margins, separation, textureRegionSize)
    }

    /**
     * If there is a tile covering the `atlas_coords` coordinates, returns the top-left coordinates of
     * the tile (thus its coordinate ID). Returns `Vector2i(-1, -1)` otherwise.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_at_coords
     */
    fun getTileAtCoords(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileAtCoordsBind, handle, atlasCoords)
    }

    /**
     * Checks if the source has any tiles that don't fit the texture area (either partially or
     * completely).
     *
     * Generated from Godot docs: TileSetAtlasSource.has_tiles_outside_texture
     */
    fun hasTilesOutsideTexture(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasTilesOutsideTextureBind, handle)
    }

    /**
     * Removes all tiles that don't fit the available texture area. This method iterates over all the
     * source's tiles, so it's advised to use `has_tiles_outside_texture` beforehand.
     *
     * Generated from Godot docs: TileSetAtlasSource.clear_tiles_outside_texture
     */
    fun clearTilesOutsideTexture() {
        ObjectCalls.ptrcallNoArgs(clearTilesOutsideTextureBind, handle)
    }

    /**
     * Sets the number of columns in the animation layout of the tile at coordinates `atlas_coords`. If
     * set to 0, then the different frames of the animation are laid out as a single horizontal line in
     * the atlas.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_columns
     */
    fun setTileAnimationColumns(atlasCoords: Vector2i, frameColumns: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(setTileAnimationColumnsBind, handle, atlasCoords, frameColumns)
    }

    /**
     * Returns how many columns the tile at `atlas_coords` has in its animation layout.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_columns
     */
    fun getTileAnimationColumns(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getTileAnimationColumnsBind, handle, atlasCoords)
    }

    /**
     * Sets the margin (in grid tiles) between each tile in the animation layout of the tile at
     * coordinates `atlas_coords` has.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_separation
     */
    fun setTileAnimationSeparation(atlasCoords: Vector2i, separation: Vector2i) {
        ObjectCalls.ptrcallWithTwoVector2iArgs(setTileAnimationSeparationBind, handle, atlasCoords, separation)
    }

    /**
     * Returns the separation (as in the atlas grid) between each frame of an animated tile at
     * coordinates `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_separation
     */
    fun getTileAnimationSeparation(atlasCoords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getTileAnimationSeparationBind, handle, atlasCoords)
    }

    /**
     * Sets the animation speed of the tile at coordinates `atlas_coords` has.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_speed
     */
    fun setTileAnimationSpeed(atlasCoords: Vector2i, speed: Double) {
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(setTileAnimationSpeedBind, handle, atlasCoords, speed)
    }

    /**
     * Returns the animation speed of the tile at coordinates `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_speed
     */
    fun getTileAnimationSpeed(atlasCoords: Vector2i): Double {
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getTileAnimationSpeedBind, handle, atlasCoords)
    }

    /**
     * Sets the tile animation mode of the tile at `atlas_coords` to `mode`. See also
     * `get_tile_animation_mode`.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_mode
     */
    fun setTileAnimationMode(atlasCoords: Vector2i, mode: Long) {
        ObjectCalls.ptrcallWithVector2iAndLongArg(setTileAnimationModeBind, handle, atlasCoords, mode)
    }

    /**
     * Returns the tile animation mode of the tile at `atlas_coords`. See also
     * `set_tile_animation_mode`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_mode
     */
    fun getTileAnimationMode(atlasCoords: Vector2i): Long {
        return ObjectCalls.ptrcallWithVector2iArgRetLong(getTileAnimationModeBind, handle, atlasCoords)
    }

    /**
     * Sets how many animation frames the tile at coordinates `atlas_coords` has.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_frames_count
     */
    fun setTileAnimationFramesCount(atlasCoords: Vector2i, framesCount: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(setTileAnimationFramesCountBind, handle, atlasCoords, framesCount)
    }

    /**
     * Returns how many animation frames has the tile at coordinates `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_frames_count
     */
    fun getTileAnimationFramesCount(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getTileAnimationFramesCountBind, handle, atlasCoords)
    }

    /**
     * Sets the animation frame `duration` of frame `frame_index` for the tile at coordinates
     * `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_tile_animation_frame_duration
     */
    fun setTileAnimationFrameDuration(atlasCoords: Vector2i, frameIndex: Int, duration: Double) {
        ObjectCalls.ptrcallWithVector2iIntDoubleArgs(setTileAnimationFrameDurationBind, handle, atlasCoords, frameIndex, duration)
    }

    /**
     * Returns the animation frame duration of frame `frame_index` for the tile at coordinates
     * `atlas_coords`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_frame_duration
     */
    fun getTileAnimationFrameDuration(atlasCoords: Vector2i, frameIndex: Int): Double {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetDouble(getTileAnimationFrameDurationBind, handle, atlasCoords, frameIndex)
    }

    /**
     * Returns the sum of the sum of the frame durations of the tile at coordinates `atlas_coords`.
     * This value needs to be divided by the animation speed to get the actual animation loop duration.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_animation_total_duration
     */
    fun getTileAnimationTotalDuration(atlasCoords: Vector2i): Double {
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getTileAnimationTotalDurationBind, handle, atlasCoords)
    }

    /**
     * Creates an alternative tile for the tile at coordinates `atlas_coords`. If
     * `alternative_id_override` is -1, give it an automatically generated unique ID, or assigns it the
     * given ID otherwise. Returns the new alternative identifier, or -1 if the alternative could not
     * be created with a provided `alternative_id_override`.
     *
     * Generated from Godot docs: TileSetAtlasSource.create_alternative_tile
     */
    fun createAlternativeTile(atlasCoords: Vector2i, alternativeIdOverride: Int = -1): Int {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetInt(createAlternativeTileBind, handle, atlasCoords, alternativeIdOverride)
    }

    /**
     * Remove a tile's alternative with alternative ID `alternative_tile`. Calling this function with
     * `alternative_tile` equals to 0 will fail, as the base tile alternative cannot be removed.
     *
     * Generated from Godot docs: TileSetAtlasSource.remove_alternative_tile
     */
    fun removeAlternativeTile(atlasCoords: Vector2i, alternativeTile: Int) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(removeAlternativeTileBind, handle, atlasCoords, alternativeTile)
    }

    /**
     * Change a tile's alternative ID from `alternative_tile` to `new_id`. Calling this function with
     * `new_id` of 0 will fail, as the base tile alternative cannot be moved.
     *
     * Generated from Godot docs: TileSetAtlasSource.set_alternative_tile_id
     */
    fun setAlternativeTileId(atlasCoords: Vector2i, alternativeTile: Int, newId: Int) {
        ObjectCalls.ptrcallWithVector2iAndTwoIntArgs(setAlternativeTileIdBind, handle, atlasCoords, alternativeTile, newId)
    }

    /**
     * Returns the alternative ID a following call to `create_alternative_tile` would return.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_next_alternative_tile_id
     */
    fun getNextAlternativeTileId(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getNextAlternativeTileIdBind, handle, atlasCoords)
    }

    /**
     * Returns the `TileData` object for the given atlas coordinates and alternative ID.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_data
     */
    fun getTileData(atlasCoords: Vector2i, alternativeTile: Int): TileData? {
        return TileData.wrap(ObjectCalls.ptrcallWithVector2iAndIntArgRetObject(getTileDataBind, handle, atlasCoords, alternativeTile))
    }

    /**
     * Returns the atlas grid size, which depends on how many tiles can fit in the texture. It thus
     * depends on the `texture`'s size, the atlas `margins`, and the tiles' `texture_region_size`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_atlas_grid_size
     */
    fun getAtlasGridSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getAtlasGridSizeBind, handle)
    }

    /**
     * Returns a tile's texture region in the atlas texture. For animated tiles, a `frame` argument
     * might be provided for the different frames of the animation.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_tile_texture_region
     */
    fun getTileTextureRegion(atlasCoords: Vector2i, frame: Int = 0): Rect2i {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetRect2i(getTileTextureRegionBind, handle, atlasCoords, frame)
    }

    /**
     * If `use_texture_padding` is `false`, returns `texture`. Otherwise, returns an internal
     * `ImageTexture` created that includes the padding.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_runtime_texture
     */
    fun getRuntimeTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRuntimeTextureBind, handle))
    }

    /**
     * Returns the region of the tile at coordinates `atlas_coords` for the given `frame` inside the
     * texture returned by `get_runtime_texture`. Note: If `use_texture_padding` is `false`, returns
     * the same as `get_tile_texture_region`.
     *
     * Generated from Godot docs: TileSetAtlasSource.get_runtime_tile_texture_region
     */
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
