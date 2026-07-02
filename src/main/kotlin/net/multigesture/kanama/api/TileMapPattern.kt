package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * Holds a pattern to be copied from or pasted into `TileMap`s.
 *
 * Generated from Godot docs: TileMapPattern
 */
class TileMapPattern(handle: MemorySegment) : Resource(handle) {
    fun setCell(coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1) {
        ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(setCellBind, handle, coords, sourceId, atlasCoords, alternativeTile)
    }

    fun hasCell(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(hasCellBind, handle, coords)
    }

    fun removeCell(coords: Vector2i, updateSize: Boolean) {
        ObjectCalls.ptrcallWithVector2iAndBoolArg(removeCellBind, handle, coords, updateSize)
    }

    fun getCellSourceId(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellSourceIdBind, handle, coords)
    }

    fun getCellAtlasCoords(coords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getCellAtlasCoordsBind, handle, coords)
    }

    fun getCellAlternativeTile(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellAlternativeTileBind, handle, coords)
    }

    fun getUsedCells(): List<Vector2i> {
        return ObjectCalls.ptrcallNoArgsRetVector2iList(getUsedCellsBind, handle)
    }

    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    fun isEmpty(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TileMapPattern? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TileMapPattern? =
            if (handle.address() == 0L) null else TileMapPattern(handle)

        private const val SET_CELL_HASH = 2224802556L
        private val setCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "set_cell", SET_CELL_HASH)
        }

        private const val HAS_CELL_HASH = 3900751641L
        private val hasCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "has_cell", HAS_CELL_HASH)
        }

        private const val REMOVE_CELL_HASH = 4153096796L
        private val removeCellBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "remove_cell", REMOVE_CELL_HASH)
        }

        private const val GET_CELL_SOURCE_ID_HASH = 2485466453L
        private val getCellSourceIdBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_source_id", GET_CELL_SOURCE_ID_HASH)
        }

        private const val GET_CELL_ATLAS_COORDS_HASH = 3050897911L
        private val getCellAtlasCoordsBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_atlas_coords", GET_CELL_ATLAS_COORDS_HASH)
        }

        private const val GET_CELL_ALTERNATIVE_TILE_HASH = 2485466453L
        private val getCellAlternativeTileBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_alternative_tile", GET_CELL_ALTERNATIVE_TILE_HASH)
        }

        private const val GET_USED_CELLS_HASH = 3995934104L
        private val getUsedCellsBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "get_used_cells", GET_USED_CELLS_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SIZE_HASH = 1130785943L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "set_size", SET_SIZE_HASH)
        }

        private const val IS_EMPTY_HASH = 36873697L
        private val isEmptyBind by lazy {
            ObjectCalls.getMethodBind("TileMapPattern", "is_empty", IS_EMPTY_HASH)
        }
    }
}
