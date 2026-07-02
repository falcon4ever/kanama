package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * Exposes a set of tiles for a `TileSet` resource.
 *
 * Generated from Godot docs: TileSetSource
 */
open class TileSetSource(handle: MemorySegment) : Resource(handle) {
    fun getTilesCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTilesCountBind, handle)
    }

    fun getTileId(index: Int): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(getTileIdBind, handle, index)
    }

    fun hasTile(atlasCoords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(hasTileBind, handle, atlasCoords)
    }

    fun getAlternativeTilesCount(atlasCoords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getAlternativeTilesCountBind, handle, atlasCoords)
    }

    fun getAlternativeTileId(atlasCoords: Vector2i, index: Int): Int {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetInt(getAlternativeTileIdBind, handle, atlasCoords, index)
    }

    fun hasAlternativeTile(atlasCoords: Vector2i, alternativeTile: Int): Boolean {
        return ObjectCalls.ptrcallWithVector2iAndIntArgRetBool(hasAlternativeTileBind, handle, atlasCoords, alternativeTile)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileSetSource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileSetSource? =
            if (handle.address() == 0L) null else TileSetSource(handle)

        private const val GET_TILES_COUNT_HASH = 3905245786L
        private val getTilesCountBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "get_tiles_count", GET_TILES_COUNT_HASH)
        }

        private const val GET_TILE_ID_HASH = 880721226L
        private val getTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "get_tile_id", GET_TILE_ID_HASH)
        }

        private const val HAS_TILE_HASH = 3900751641L
        private val hasTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "has_tile", HAS_TILE_HASH)
        }

        private const val GET_ALTERNATIVE_TILES_COUNT_HASH = 2485466453L
        private val getAlternativeTilesCountBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "get_alternative_tiles_count", GET_ALTERNATIVE_TILES_COUNT_HASH)
        }

        private const val GET_ALTERNATIVE_TILE_ID_HASH = 89881719L
        private val getAlternativeTileIdBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "get_alternative_tile_id", GET_ALTERNATIVE_TILE_ID_HASH)
        }

        private const val HAS_ALTERNATIVE_TILE_HASH = 1073731340L
        private val hasAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileSetSource", "has_alternative_tile", HAS_ALTERNATIVE_TILE_HASH)
        }
    }
}
