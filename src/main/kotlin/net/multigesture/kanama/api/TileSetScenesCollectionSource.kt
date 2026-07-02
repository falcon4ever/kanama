package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Exposes a set of scenes as tiles for a `TileSet` resource.
 *
 * Generated from Godot docs: TileSetScenesCollectionSource
 */
class TileSetScenesCollectionSource(handle: MemorySegment) : TileSetSource(handle) {
    fun getSceneTilesCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSceneTilesCountBind, handle)
    }

    fun getSceneTileId(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSceneTileIdBind, handle, index)
    }

    fun hasSceneTileId(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSceneTileIdBind, handle, id)
    }

    fun createSceneTile(packedScene: PackedScene?, idOverride: Int = -1): Int {
        return ObjectCalls.ptrcallWithObjectAndIntArgRetInt(createSceneTileBind, handle, packedScene?.requireOpenHandle() ?: MemorySegment.NULL, idOverride)
    }

    fun setSceneTileId(id: Int, newId: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSceneTileIdBind, handle, id, newId)
    }

    fun setSceneTileScene(id: Int, packedScene: PackedScene?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSceneTileSceneBind, handle, id, packedScene?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSceneTileScene(id: Int): PackedScene? {
        return PackedScene.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSceneTileSceneBind, handle, id))
    }

    fun setSceneTileDisplayPlaceholder(id: Int, displayPlaceholder: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSceneTileDisplayPlaceholderBind, handle, id, displayPlaceholder)
    }

    fun getSceneTileDisplayPlaceholder(id: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getSceneTileDisplayPlaceholderBind, handle, id)
    }

    fun removeSceneTile(id: Int) {
        ObjectCalls.ptrcallWithIntArg(removeSceneTileBind, handle, id)
    }

    fun getNextSceneTileId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNextSceneTileIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileSetScenesCollectionSource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileSetScenesCollectionSource? =
            if (handle.address() == 0L) null else TileSetScenesCollectionSource(handle)

        private const val GET_SCENE_TILES_COUNT_HASH = 2455072627L
        private val getSceneTilesCountBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "get_scene_tiles_count", GET_SCENE_TILES_COUNT_HASH)
        }

        private const val GET_SCENE_TILE_ID_HASH = 3744713108L
        private val getSceneTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "get_scene_tile_id", GET_SCENE_TILE_ID_HASH)
        }

        private const val HAS_SCENE_TILE_ID_HASH = 3067735520L
        private val hasSceneTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "has_scene_tile_id", HAS_SCENE_TILE_ID_HASH)
        }

        private const val CREATE_SCENE_TILE_HASH = 1117465415L
        private val createSceneTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "create_scene_tile", CREATE_SCENE_TILE_HASH)
        }

        private const val SET_SCENE_TILE_ID_HASH = 3937882851L
        private val setSceneTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "set_scene_tile_id", SET_SCENE_TILE_ID_HASH)
        }

        private const val SET_SCENE_TILE_SCENE_HASH = 3435852839L
        private val setSceneTileSceneBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "set_scene_tile_scene", SET_SCENE_TILE_SCENE_HASH)
        }

        private const val GET_SCENE_TILE_SCENE_HASH = 511017218L
        private val getSceneTileSceneBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "get_scene_tile_scene", GET_SCENE_TILE_SCENE_HASH)
        }

        private const val SET_SCENE_TILE_DISPLAY_PLACEHOLDER_HASH = 300928843L
        private val setSceneTileDisplayPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "set_scene_tile_display_placeholder", SET_SCENE_TILE_DISPLAY_PLACEHOLDER_HASH)
        }

        private const val GET_SCENE_TILE_DISPLAY_PLACEHOLDER_HASH = 1116898809L
        private val getSceneTileDisplayPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "get_scene_tile_display_placeholder", GET_SCENE_TILE_DISPLAY_PLACEHOLDER_HASH)
        }

        private const val REMOVE_SCENE_TILE_HASH = 1286410249L
        private val removeSceneTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "remove_scene_tile", REMOVE_SCENE_TILE_HASH)
        }

        private const val GET_NEXT_SCENE_TILE_ID_HASH = 3905245786L
        private val getNextSceneTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetScenesCollectionSource", "get_next_scene_tile_id", GET_NEXT_SCENE_TILE_ID_HASH)
        }
    }
}
