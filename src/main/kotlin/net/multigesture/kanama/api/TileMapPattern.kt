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
    /**
     * Sets the tile identifiers for the cell at coordinates `coords`. See `TileMap.set_cell`.
     *
     * Generated from Godot docs: TileMapPattern.set_cell
     */
    fun setCell(coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1) {
        ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(setCellBind, handle, coords, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Returns whether the pattern has a tile at the given coordinates.
     *
     * Generated from Godot docs: TileMapPattern.has_cell
     */
    fun hasCell(coords: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(hasCellBind, handle, coords)
    }

    /**
     * Remove the cell at the given coordinates.
     *
     * Generated from Godot docs: TileMapPattern.remove_cell
     */
    fun removeCell(coords: Vector2i, updateSize: Boolean) {
        ObjectCalls.ptrcallWithVector2iAndBoolArg(removeCellBind, handle, coords, updateSize)
    }

    /**
     * Returns the tile source ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_source_id
     */
    fun getCellSourceId(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellSourceIdBind, handle, coords)
    }

    /**
     * Returns the tile atlas coordinates ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_atlas_coords
     */
    fun getCellAtlasCoords(coords: Vector2i): Vector2i {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(getCellAtlasCoordsBind, handle, coords)
    }

    /**
     * Returns the tile alternative ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_alternative_tile
     */
    fun getCellAlternativeTile(coords: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getCellAlternativeTileBind, handle, coords)
    }

    /**
     * Returns the list of used cell coordinates in the pattern.
     *
     * Generated from Godot docs: TileMapPattern.get_used_cells
     */
    fun getUsedCells(): List<Vector2i> {
        return ObjectCalls.ptrcallNoArgsRetVector2iList(getUsedCellsBind, handle)
    }

    /**
     * Returns the size, in cells, of the pattern.
     *
     * Generated from Godot docs: TileMapPattern.get_size
     */
    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    /**
     * Sets the size of the pattern.
     *
     * Generated from Godot docs: TileMapPattern.set_size
     */
    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    /**
     * Returns whether the pattern is empty or not.
     *
     * Generated from Godot docs: TileMapPattern.is_empty
     */
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
